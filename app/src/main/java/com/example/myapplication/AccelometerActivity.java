package com.example.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;


import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class AccelometerActivity extends AppCompatActivity  implements SensorEventListener {

    private TextView xText, yText, zText;


    private SensorManager sensorManager;
    private Sensor aSensor;
    private boolean isSensorAvailable, itIsNotFirstTime = false;

    private float currentX, currentY, currentZ, lastX, lastY, lastZ;
    private float xDifference, yDifference, zDifference;
    private float shakeThreshold = 5f;
    private Vibrator v;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.position);

        yText = findViewById(R.id.x);
        xText = findViewById(R.id.y);
        zText = findViewById(R.id.z);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            aSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isSensorAvailable = true;
        } else {
            xText.setText("Accelerometer is not available");
            isSensorAvailable = false;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xText.setText(event.values[0] + "m/s2");
        yText.setText(event.values[1] + "m/s2");
        zText.setText(event.values[2] + "m/s2");

        currentX = event.values[0];
        currentY = event.values[1];
        currentZ = event.values[2];

        if (itIsNotFirstTime) {
            xDifference = Math.abs(lastX - currentX);
            yDifference = Math.abs(lastY - currentY);
            zDifference = Math.abs(lastZ - currentZ);

            if ((xDifference > shakeThreshold && yDifference > shakeThreshold) ||
                    (xDifference > shakeThreshold && zDifference > shakeThreshold) ||
                    (yDifference > shakeThreshold && zDifference > shakeThreshold)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));

                } else {
                    v.vibrate(1000);
                }
            }

        }

        lastX = currentX;
        lastY = currentY;
        lastZ = currentY;
        itIsNotFirstTime = true;

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (isSensorAvailable)
            sensorManager.registerListener(this , aSensor , SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    protected void onPause() {
        super.onPause();
        if (isSensorAvailable) {
            sensorManager.unregisterListener(this);

        }
    }
}
