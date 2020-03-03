package io.github.nearchos.rovercoder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.github.nearchos.rovercoder.api.APIConstants;
import robutev3.android.BrickService;
import robutev3.android.Device;
import robutev3.android.EV3Service;
import robutev3.core.Color;
import robutev3.core.Interval;
import robutev3.core.Speed;

import static robutev3.android.BluetoothCommunicationInterface.BLUETOOTH_OUI_LEGO;

public class MainActivity extends AppCompatActivity implements RoverCoderInterface {

    public static final String TAG = "demo";

    private BrickService brickService;

    private ToggleButton toggleButton;
    private Button runNextCodeButton;
    private Button viewCodeButton;
    private TextView currentUserTextView;
    private TextView scoreTextView;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        toggleButton = findViewById(R.id.toggleButton);
        runNextCodeButton = findViewById(R.id.runNextCodeButton);
        viewCodeButton = findViewById(R.id.viewCodeButton);

        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> updateRoverState(isChecked));

        // Instantiate the RequestQueue.

        queue = Volley.newRequestQueue(this);

        runNextCodeButton.setOnClickListener(view -> {
            queue.add(stringRequest); // Add the request to the RequestQueue.
        });

        viewCodeButton.setOnClickListener(view -> {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.View_code)
                    .setMessage(javaScriptCode)
                    .setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss())
                    .create()
                    .show();
        });

        currentUserTextView = findViewById(R.id.currentUserTextView);
        currentUserTextView.setText(getString(R.string.Current_User, getString(R.string.None)));

        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText(getString(R.string.Score, 0));

        registerToEv3Events();
    }

    @Override
    protected void onStart() {
        super.onStart();

        startJavaScript();

        // Get currently paired Bluetooth devices
        Device device = null;

        final Vector<BluetoothDevice> bluetoothDevices = new Vector<>(BluetoothAdapter.getDefaultAdapter().getBondedDevices());
        for(final BluetoothDevice bluetoothDevice : bluetoothDevices) {
            if(bluetoothDevice.getAddress().startsWith(BLUETOOTH_OUI_LEGO)) {
                device = new Device(Device.Type.TYPE_BLUETOOTH, bluetoothDevice.getName(), bluetoothDevice.getAddress());
            }
        }
        if(device == null) {
            Toast.makeText(this, "Could not find a Bluetooth connection to EV3 - shutting app down", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Could not find a Bluetooth connection to EV3 - shutting app down");
            finish();
        } else {
            Toast.makeText(this, "Connecting to EV3 ...", Toast.LENGTH_SHORT).show();
            final Intent intent = new Intent(this, EV3Service.class);
            intent.putExtra(Device.EXTRA_DEVICE, device);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    }

    public static final long EXECUTION_INTERVAL_MILLISECONDS = 1000;

    private final ScheduledExecutorService scheduledWorkExecutor = Executors.newSingleThreadScheduledExecutor();

    @Override
    protected void onResume() {
        super.onResume();
        // set initial JavaScript code - todo must remove after the code is 'picked' from server
        setJavaScript(JAVASCRIPT_CODE);
        // create periodic handler
        scheduledWorkExecutor.scheduleAtFixedRate(codeExecutionRunnable, 0L, EXECUTION_INTERVAL_MILLISECONDS, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // terminate periodic handler
        scheduledWorkExecutor.shutdown();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Stop motors is running
        if(brickService != null) {
            Log.d(TAG, "Stopping all motors");
            brickService.brick().motor().stopAll().coast().go();
            brickService.brick().light().off();
        }
        // Unbind from EV3Service
        unbindService(connection);

        stopJavaScript();
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to EV3Service, cast the IBinder and get EV3Service instance
            final EV3Service.EV3Binder binder = (EV3Service.EV3Binder) service;
            brickService = binder.getService();
            Log.d(TAG, "EV3 Service connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            Log.d(TAG, "EV3 Service disconnected");
        }
    };

    private void registerToEv3Events() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(EV3Service.EV3_ACTION_STARTED);
        intentFilter.addAction(EV3Service.EV3_ACTION_STOPPED);
        intentFilter.addAction(EV3Service.EV3_SENSED_VALUES);
        intentFilter.addAction(EV3Service.EV3_ERROR_DISCONNECTED);
        registerReceiver(ev3BroadcastReceiver, intentFilter);
    }

    private boolean started = false;

    public static final int DEFAULT_SPEED = 20;

    public void updateRoverState(final boolean started) {
        if(started) {
            // beep
            brickService.brick().sound().beep();
            // pulse red light when ready
            brickService.brick().light().red().pulse();
            // start motors moving forward
            brickService.brick().motor().portsBandC().turnIndefinitely(DEFAULT_SPEED).go();
        } else {
            brickService.brick().sound().beep(50, 100, 10);
            brickService.brick().light().red().on();
            brickService.brick().motor().stopAll().coast().go();
        }
        this.started = started;
    }

    public void goForward(View view) {
        moveForward();
    }

    public void goBackward(View view) {
        moveBackward();
    }

    public void turnLeft(View view) {
        turnLeft();
    }

    public void turnRight(View view) {
        turnRight();
    }

    private void moveForward() {
        moveForward(500);
    }

    public void moveForward(final int ms) {
        Log.d(TAG, "MOVE FORWARD");
        brickService.brick().tank().forward(Interval.milliseconds(ms), Speed.fromValue(DEFAULT_SPEED)).go();
    }

    private void moveBackward() {
        moveBackward(500);
    }

    public void moveBackward(final int ms) {
        Log.d(TAG, "MOVE BACKWARD");
        brickService.brick().tank().backward(Interval.milliseconds(500), Speed.fromValue(DEFAULT_SPEED)).go();
    }

    private void turnLeft() {
        turnLeft(438);
    }

    public void turnLeft(final int ms) {
        Log.d(TAG, "TURN LEFT (" + ms + ")");
        brickService.brick().tank().turnLeft(Speed.fromValue(DEFAULT_SPEED), Interval.milliseconds(ms)).go();
    }

    private void turnRight() {
        turnRight(438);
    }

    public void turnRight(final int ms) {
        Log.d(TAG, "TURN RIGHT (" + ms + ")");
        brickService.brick().tank().turnRight(Speed.fromValue(DEFAULT_SPEED), Interval.milliseconds(ms)).go();
    }

    public int distance() {
        Log.d(TAG, "DISTANCE ...");
        final int distance = brickService.brick().sensor().port4().ultrasonic().centimeters();
        Log.d(TAG, "DISTANCE -> " + distance + " mm");
        return distance;
    }

    @Override
    public Color color() {
        Log.d(TAG, "COLOR ...");
        final Color color = brickService.brick().sensor().port3().color().sense();
        if(color == Color.RED) brickService.brick().light().red().flash();
        Log.d(TAG, "COLOR -> " + color.name());
        return color;
    }

    private BroadcastReceiver ev3BroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            assert action != null;
            switch (action) {
                case EV3Service.EV3_ACTION_STARTED:
                    brickService.brick().sound().beep(50, 50, 10);
                    brickService.brick().light().red().on();
                    Log.d(TAG, "EV3 Service started!");
                    break;
                case EV3Service.EV3_ACTION_STOPPED:
                    Toast.makeText(context, "Connection broken", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "EV3 Service stopped!");
                    break;
                case EV3Service.EV3_SENSED_VALUES:
                    // ignore
            }
        }
    };

    public static final String JAVASCRIPT_CODE =
                    "function runJavaScript(rover) {\n" +
                    "  if (rover.distance() < 200) {\n" +
                    "    rover.turnRight(500);\n" +
                    "  } else if (rover.color() == rover.RED) {\n" +
                    "    rover.turnLeft(500);\n" +
                    "  } else {\n" +
                    "    rover.moveForward(100);\n" +
                    "  }\n" +
                    "}";

    public static final String RUN_FUNCTION = "runJavaScript";

    private org.mozilla.javascript.Function function = null;

    private org.mozilla.javascript.Context rhinoContext;
    private org.mozilla.javascript.ScriptableObject scope;

    public void startJavaScript() {
        rhinoContext = org.mozilla.javascript.Context.enter();
        rhinoContext.setOptimizationLevel(-1);
        scope = rhinoContext.initStandardObjects();
    }

    private String javaScriptCode = JAVASCRIPT_CODE;

    public void setJavaScript(String javascriptCode) {
        this.javaScriptCode = javascriptCode;
        this.score = 0;
        rhinoContext.evaluateString(scope, javascriptCode, RUN_FUNCTION, 1, null);
        function = (org.mozilla.javascript.Function) scope.get(RUN_FUNCTION, scope);
    }

    public void stopJavaScript() {
        org.mozilla.javascript.Context.exit();
        function = null;
    }

    private int score = 0;

    private Runnable codeExecutionRunnable = () -> {

        final RoverCoderInterface roverCoderInterface = this;

        if(started && function != null) {
            Log.d(TAG, "Running JS code ...");
            brickService.brick().light().green().pulse();
            function.call(
                    org.mozilla.javascript.Context.enter(),
                    org.mozilla.javascript.Context.enter().initStandardObjects(),
                    org.mozilla.javascript.Context.enter().initStandardObjects(),
                    new Object[] {roverCoderInterface}
            );
            if(brickService.brick().sensor().port3().color().sense() == Color.RED) {
                score++;
                scoreTextView.setText(getString(R.string.Score, score));
            }
        }
    };

//    public Button getRunNextCodeButton() {
//        return runNextCodeButton;
//    }
//
//    public Button getStopCodeButton() {
//        return stopCodeButton;
//    }

    // Request a string response from the provided URL.
    private final StringRequest stringRequest = new StringRequest(Request.Method.GET, APIConstants.GET_NEXT_CODE_URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String jsonResponse) {
                    final com.panickapps.response.Response responseJsonObject = new Gson().fromJson(jsonResponse, com.panickapps.response.Response.class);

                    if (responseJsonObject.getData() != null) {
                        if (responseJsonObject.getData().has("code")) {
                            String code = responseJsonObject.getData().getAsJsonObject("code").get("code").getAsString();
                            Log.d(MainActivity.TAG, code);
                            setJavaScript(code);
//                            updateRoverState(true);
                            currentUserTextView.setText(getString(R.string.Current_User, responseJsonObject.getData().getAsJsonObject("code").get("playerName").getAsString()));

                        }
                    }
                    Toast.makeText(MainActivity.this, responseJsonObject.getMessage(), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "I/O Error: " + error, Toast.LENGTH_SHORT).show();
                }
            }
    );
}