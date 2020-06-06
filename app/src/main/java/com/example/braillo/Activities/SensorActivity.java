package com.example.braillo.Activities;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class SensorActivity implements SensorEventListener {
    private static final float SHAKE_THRESHOLD = 3.25f; // m/S**2
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 1000;
    private final SensorManager mSensorManager;
    private final Sensor sensor;
    MainActivity ma;
    private Sensor mLight;
    private long mLastShakeTime;

    public SensorActivity(SensorManager sm, MainActivity ma) {
        mSensorManager = sm;

        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        this.ma = ma;
    }


    protected void onResume() {

        mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);

    }

    protected void onPause() {
        mSensorManager.unregisterListener(this, sensor);
    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            Log.d("onSensorChanged", event.values[0] + "");
             if (event.values[0] >= 1.0)
                ma.getCameraConfigurations().toggleFlash(false);
            else
                ma.getCameraConfigurations().toggleFlash(true);
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(Math.pow(x, 2) +
                        Math.pow(y, 2) +
                        Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
                Log.d("acc", "Acceleration is " + acceleration + "m/s^2");

                if (acceleration > SHAKE_THRESHOLD) {
                    mLastShakeTime = curTime;
                    Log.d("shake", "Shake, Rattle, and Roll");
                }
            }
        }
    }
}

