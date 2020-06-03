package com.example.braillo.Utility.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class IntroductionMessageHelper {
    Activity activity;

    Context context;

    public IntroductionMessageHelper(Activity activity, Context context) {
        this.activity = activity;

        this.context = context;
    }



    public void introductionMessage(boolean hasCameraPermission) {
        SharedPreferences prefs = activity.getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            removeIntroductionMessage(hasCameraPermission);
        }
    }

    public void removeIntroductionMessage(boolean hasCameraPermission) {
        if (hasCameraPermission) {
            com.example.braillo.Threads.IntroductionMessage introductionMessage = new com.example.braillo.Threads.IntroductionMessage(context);
            introductionMessage.start();
            SharedPreferences prefs = activity.getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstStart", false);
            editor.apply();
        } else {
            Toast.makeText(activity, "no voice itro", Toast.LENGTH_SHORT).show();
        }

    }
}
