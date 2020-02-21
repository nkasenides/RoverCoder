package io.github.nearchos.rovercoder;

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
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import robutev3.android.BrickService;
import robutev3.android.Device;
import robutev3.android.EV3Service;
import robutev3.core.Interval;
import robutev3.core.Speed;

import static robutev3.android.BluetoothCommunicationInterface.BLUETOOTH_OUI_LEGO;

public class MainActivity extends AppCompatActivity implements RoverCoderInterface {

    public static final String TAG = "demo";

    private BrickService brickService;

    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> updateRoverState(isChecked));

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
        public void onServiceDisconnected(ComponentName arg0) {
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

    private void updateRoverState(final boolean started) {
        if(started) {
            // beep
            brickService.brick().sound().beep();
            // start motors moving forward
            brickService.brick().motor().portsBandC().turnIndefinitely(20).go();
        } else {
            brickService.brick().sound().beep(100, 200, 30);
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
        brickService.brick().tank().forward(Interval.milliseconds(ms), Speed.fromValue(20)).go();
    }

    private void moveBackward() {
        moveBackward(500);
    }

    public void moveBackward(final int ms) {
        brickService.brick().tank().backward(Interval.milliseconds(500), Speed.fromValue(20)).go();
    }

    private void turnLeft() {
        turnLeft(438);
    }

    public void turnLeft(final int ms) {
        brickService.brick().tank().turnLeft(Interval.milliseconds(ms)).go();
    }

    private void turnRight() {
        turnRight(438);
    }

    public void turnRight(final int ms) {
        brickService.brick().tank().turnRight(Interval.milliseconds(ms)).go();
    }

    public int distance() {
        return brickService.brick().sensor().port4().ultrasonic().centimeters();
    }

    private BroadcastReceiver ev3BroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            assert action != null;
            switch (action) {
                case EV3Service.EV3_ACTION_STARTED:
                    brickService.brick().sound().beep();
                    Log.d(TAG, "EV3 Service started!");
                    break;
                case EV3Service.EV3_ACTION_STOPPED:
                    Toast.makeText(context, "Connection broken", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "EV3 Service stopped!");
                    break;
                case EV3Service.EV3_SENSED_VALUES:
            }
        }
    };

    public static final String JAVASCRIPT_CODE =
            "function runJavaScript(rover) {\n" +
                    "  if (rover.distance() < 200) {\n" +
                    "    rover.turnRight(500);\n" +
                    "  } else {\n" +
                    "    rover.moveForward(100);\n" +
                    "  }\n" +
                    "}";

    public static final String RUN_FUNCTION = "runJavaScript";

    private org.mozilla.javascript.Function function = null;

    public void runCode(View view) {
        runJavaScript(JAVASCRIPT_CODE);
    }

    private org.mozilla.javascript.Context rhinoContext;
    private org.mozilla.javascript.ScriptableObject scope;

    private void startJavaScript() {
        rhinoContext = org.mozilla.javascript.Context.enter();
        rhinoContext.setOptimizationLevel(-1);
        scope = rhinoContext.initStandardObjects();
    }

    private void runJavaScript(String javascriptCode) {
        rhinoContext.evaluateString(scope, javascriptCode, RUN_FUNCTION, 1, null);
        function = (org.mozilla.javascript.Function) scope.get(RUN_FUNCTION, scope);
    }

    private void stopJavaScript() {
        org.mozilla.javascript.Context.exit();
    }

    private Runnable codeExecutionRunnable = () -> {

        final RoverCoderInterface roverCoderInterface = this;

        if(started && function != null) {
            Log.d(TAG, "Running JS code ...");
            function.call(
                    org.mozilla.javascript.Context.enter(),
                    org.mozilla.javascript.Context.enter().initStandardObjects(),
                    org.mozilla.javascript.Context.enter().initStandardObjects(),
                    new Object[] {roverCoderInterface}
            );
        }
    };
}