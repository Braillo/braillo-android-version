package com.example.braillo.Threads;

import android.app.Activity;

import com.example.braillo.Activities.MainActivity;
import com.example.braillo.Utility.Configurations.CameraConfiguration;

public class FlashToggle extends Thread {
    CameraConfiguration cameraConfigurations;
    Activity activity;
    public FlashToggle(Activity activity ,CameraConfiguration cameraConfigurations){
        this.activity = activity;
        this.cameraConfigurations = cameraConfigurations;
    }

    @Override
    public void run() {

        cameraConfigurations.toggleFlash(activity);
    }
}
