package io.github.nearchos.rovercoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Locale;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    private TextView executedCodeTextView;
    private Spinner totalStepsSpinner;
    private TextView javaScriptCodeTextView;

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

        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> updateRoverState(isChecked));

        // Instantiate the RequestQueue.

        queue = Volley.newRequestQueue(this);

        runNextCodeButton.setOnClickListener(view -> {
            new AlertDialog.Builder(this)
                    .setTitle("Please confirm")
                    .setMessage("Are you sure you want to download and run the next player's code?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        getNextCode();
                        dialog.dismiss();
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .create()
                    .show();
        });

        javaScriptCodeTextView = findViewById(R.id.javaScriptCodeTextView);
        javaScriptCodeTextView.setMovementMethod(new ScrollingMovementMethod());

        executedCodeTextView = findViewById(R.id.executedCodeTextView);
        executedCodeTextView.setMovementMethod(new ScrollingMovementMethod());

        totalStepsSpinner = findViewById(R.id.totalStepsSpinner);
        totalStepsSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Step.values()));
        totalStepsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.this.stepsTotal = Step.values()[position].getNumberOfSteps();
                updateUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // ignore
            }
        });
        totalStepsSpinner.setSelection(4);

        registerToEv3Events();
    }

    public enum Step {
        STEP_1(1),
        STEP_5(5),
        STEP_10(10),
        STEP_30(30),
        STEP_60(60),
        STEP_100(100),
        STEP_120(120),
        ;

        private int numberOfSteps;

        Step(int numberOfSteps) {
            this.numberOfSteps = numberOfSteps;
        }

        int getNumberOfSteps() {
            return numberOfSteps;
        }

        @NonNull
        @Override
        public String toString() {
            return String.format(Locale.US, "%3d steps", numberOfSteps);
        }
    }

    private String playerName = "None";
    private long entryId = 0L;
    private String javaScriptCode = JAVASCRIPT_CODE;
    private int stepsCurrent = 0;
    private int stepsTotal = 60;

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
        playerName = getString(R.string.None);
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
        this.started = started;
        if(started) {
            // beep to indicate started
            brickService.brick().sound().beep();
            // pulse red light when ready
            brickService.brick().light().red().pulse();
        } else {
            brickService.brick().sound().beep(50, 100, 10);
            brickService.brick().light().red().on();
            brickService.brick().motor().stopAll().coast().go();
        }
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
        codeExecutionTrace.append(stepsCurrent + ": FORWARD " + ms + " ms\n");
        brickService.brick().tank().forward(Interval.milliseconds(ms), Speed.fromValue(DEFAULT_SPEED)).go();
    }

    private void moveBackward() {
        moveBackward(500);
    }

    public void moveBackward(final int ms) {
        Log.d(TAG, "MOVE BACKWARD");
        codeExecutionTrace.append(stepsCurrent + ": BACKWARD " + ms + " ms\n");
        brickService.brick().tank().backward(Interval.milliseconds(500), Speed.fromValue(DEFAULT_SPEED)).go();
    }

    private void turnLeft() {
        turnLeft(438);
    }

    public void turnLeft(final int ms) {
        Log.d(TAG, "TURN LEFT (" + ms + ")");
        codeExecutionTrace.append(stepsCurrent + ": TURN LEFT " + ms + " ms\n");
        brickService.brick().tank().turnLeft(Speed.fromValue(DEFAULT_SPEED), Interval.milliseconds(ms)).go();
    }

    private void turnRight() {
        turnRight(438);
    }

    public void turnRight(final int ms) {
        Log.d(TAG, "TURN RIGHT (" + ms + ")");
        codeExecutionTrace.append(stepsCurrent + ": TURN RIGHT " + ms + " ms\n");
        brickService.brick().tank().turnRight(Speed.fromValue(DEFAULT_SPEED), Interval.milliseconds(ms)).go();
    }

    public int distance() {
        Log.d(TAG, "DISTANCE ...");
        final int distance = brickService.brick().sensor().port4().ultrasonic().centimeters();
        codeExecutionTrace.append(stepsCurrent + ": DISTANCE() -> " + distance + " mm\n");
        Log.d(TAG, "DISTANCE -> " + distance + " mm");
        return distance;
    }

    @Override
    public Color color() {
        Log.d(TAG, "COLOR ...");
        final Color color = brickService.brick().sensor().port3().color().sense();
        if(color == Color.RED) brickService.brick().light().red().flash();
        codeExecutionTrace.append(stepsCurrent + ": COLOR() -> " + color.name() + "\n");
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
                    "var x;\n" +
                    "var inc;\n" +
                    "\n" +
                    "function initializeJavaScript(rover) {\n" +
                    "  x = 100;\n" +
                    "  inc = 100;\n" +
                    "}\n" +
                    "\n" +
                    "function runJavaScript(rover) {\n" +
                    "  if (rover.distance() < 200) {\n" +
                    "    rover.turnRight(500);\n" +
                    "    return;\n" +
                    "  } else if (rover.color() == rover.RED) {\n" +
                    "    rover.turnLeft(500);\n" +
                    "    return;\n" +
                    "  } else {\n" +
                    "    x = x + inc;\n" +
                    "    if(x >= 800) inc = -100;\n" +
                    "    else if(x <= 100) inc = 100;\n" +
                    "    rover.moveForward(x);\n" +
                    "    return;\n" +
                    "  }\n" +
                    "}";

    public static final String INIT_FUNCTION = "initializeJavaScript";
    public static final String RUN_FUNCTION = "runJavaScript";

    private org.mozilla.javascript.Function initFunction = null;
    private org.mozilla.javascript.Function runFunction = null;

    private org.mozilla.javascript.Context rhinoContext;
    private org.mozilla.javascript.ScriptableObject scope;

    public void startJavaScript() {
        rhinoContext = org.mozilla.javascript.Context.enter();
        rhinoContext.setOptimizationLevel(-1);
        scope = rhinoContext.initStandardObjects();
    }

    public void setJavaScript(String javascriptCode) {
        this.javaScriptCode = javascriptCode;
        this.score = 0;
        this.stepsCurrent = 0;
        this.codeExecutionTrace.setLength(0); // clear trace
        rhinoContext.evaluateString(scope, javascriptCode, RUN_FUNCTION, 1, null);
        initFunction = (org.mozilla.javascript.Function) scope.get(INIT_FUNCTION, scope);
        final RoverCoderInterface roverCoderInterface = this;
        initFunction.call(
                org.mozilla.javascript.Context.enter(),
                org.mozilla.javascript.Context.enter().initStandardObjects(),
                org.mozilla.javascript.Context.enter().initStandardObjects(),
                new Object[] {roverCoderInterface}
        );
        runFunction = (org.mozilla.javascript.Function) scope.get(RUN_FUNCTION, scope);
        updateUI();
    }

    public void stopJavaScript() {
        org.mozilla.javascript.Context.exit();
        initFunction = null;
        runFunction = null;
    }

    private int score = 0;

    private Runnable codeExecutionRunnable = () -> {

        final RoverCoderInterface roverCoderInterface = this;

        Log.d(TAG, "started: " + started + ", runFunction: " + runFunction + ", score: " + score + ", stepsCurrent: " + stepsCurrent + ", stepsTotal: " + stepsTotal);

        if(started && runFunction != null) {
            Log.d(TAG, "Running JS code ...");
            brickService.brick().light().green().pulse();
            runFunction.call(
                    org.mozilla.javascript.Context.enter(),
                    org.mozilla.javascript.Context.enter().initStandardObjects(),
                    org.mozilla.javascript.Context.enter().initStandardObjects(),
                    new Object[] {roverCoderInterface}
            );
            if(brickService.brick().sensor().port3().color().sense() == Color.GREEN) {
                score++;
            }

            stepsCurrent++;
            // check if session finished: stepsCurrent > stepsTotal
            if(stepsCurrent > stepsTotal) {
                runOnUiThread(() -> {
                    updateUI();
                    postScore(entryId, score); // upload score
                    stopJavaScript();
                }); // as this is a separate thread, all UI actions must be done in a synchronized manner
            }
        }

        runOnUiThread(this::updateUI); // as this is a separate thread, all UI actions must be done in a synchronized manner
    };

    private StringBuilder codeExecutionTrace = new StringBuilder();

    private void updateUI() {
        javaScriptCodeTextView.setText(javaScriptCode);
        executedCodeTextView.setText(playerName + ": " + score + "\n\n" + codeExecutionTrace.toString());
    }

    // Volley / Networking code

    public static final String API_BASE_URL = "https://rovercoder.appspot.com/api";
    public static final String GET_NEXT_CODE_URL = API_BASE_URL + "/getNextCode";
    public static final String POST_SCORE_URL = API_BASE_URL + "/updateScore?entryID=%1&score=%2";

    // Request a string response from the provided URL.
    private final StringRequest stringRequest = new StringRequest(
            Request.Method.GET,
            GET_NEXT_CODE_URL,
            this::handleJsonResponse,
            this::handleError
    );

    private void handleJsonResponse(final String jsonResponse) {
        final com.panickapps.response.Response responseJsonObject = new Gson().fromJson(jsonResponse, com.panickapps.response.Response.class);

        if (responseJsonObject.getData() != null) {
            if (responseJsonObject.getData().has("code")) {
                String code = responseJsonObject.getData().getAsJsonObject("code").get("code").getAsString();
                Log.d(MainActivity.TAG, code);
                playerName =  responseJsonObject.getData().getAsJsonObject("code").get("playerName").getAsString();
                entryId = responseJsonObject.getData().getAsJsonObject("code").get("id").getAsLong();
                setJavaScript(code);
            }
        }
        Toast.makeText(MainActivity.this, responseJsonObject.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void handleScoreJsonResponse(final String jsonResponse) {
        Log.d(TAG, "POST score response -> " + jsonResponse);
        final com.panickapps.response.Response responseJsonObject = new Gson().fromJson(jsonResponse, com.panickapps.response.Response.class);
        Toast.makeText(MainActivity.this, responseJsonObject.getMessage(), Toast.LENGTH_LONG).show();
        updateUI();
    }

    private void handleError(final VolleyError error) {
        Toast.makeText(MainActivity.this, "I/O Error: " + error.getMessage() + " (" + error.networkResponse + ")", Toast.LENGTH_SHORT).show();
    }

    private void getNextCode() {
        queue.add(stringRequest); // Add the request to the RequestQueue
    }

    private void postScore(final long entryId, final int score) {
        Log.d(TAG, "Posting score: " + entryId + " -> " + score);
        if(entryId == 0L) {
            Log.d(TAG, "Ignoring score for entryId==0... " + score);
            Toast.makeText(MainActivity.this, "Ignoring score for entryId==0... " + score, Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "Posting score for player '" + playerName + "' (" + entryId + ") ... " + score);
            Toast.makeText(MainActivity.this, "Posting score for player '" + playerName + "' (" + entryId + ") ... " + score, Toast.LENGTH_SHORT).show();
            final String url = POST_SCORE_URL.replaceAll("%1", Long.toString(entryId)).replaceAll("%2", Integer.toString(score));
            Log.d(TAG, "POSTing request to: " + url);
            codeExecutionTrace.append("Posting score: ").append(score);
            queue.add(new StringRequest(
                    Request.Method.POST,
                    url,
                    this::handleScoreJsonResponse,
                    this::handleError));
        }
    }
}