package com.example.braillo.Utility.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class IntroductionMessageHelper {
    Activity activity;

    Context context;
    boolean firstStart;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    com.example.braillo.Threads.IntroductionMessage introductionMessage;

    public IntroductionMessageHelper(Activity activity, Context context) {
        this.activity = activity;

        this.context = context;
    }

    public void introductionMessage(boolean hasCameraPermission) {
        prefs = activity.getSharedPreferences("prefs", MODE_PRIVATE);
        firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            removeIntroductionMessage(hasCameraPermission);
        }
    }

    public void removeIntroductionMessage(boolean hasCameraPermission) {
        if (hasCameraPermission) {
            introductionMessage = new com.example.braillo.Threads.IntroductionMessage(context);
            introductionMessage.start();
            prefs = activity.getSharedPreferences("prefs", MODE_PRIVATE);
            editor = prefs.edit();
            editor.putBoolean("firstStart", false);
            editor.apply();
        } else {
            Toast.makeText(activity, "no voice itro", Toast.LENGTH_SHORT).show();
        }

    }
}
