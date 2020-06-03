package com.example.braillo.Threads;

import android.app.Activity;
import android.graphics.Bitmap;

import com.example.braillo.Utility.UI_Connection;
import com.example.braillo.Utility.Voice;


public class CurrencyDetection extends Thread {
    Activity activity;
    Bitmap bitmap;

    public CurrencyDetection(Activity activity, Bitmap bitmap) {
        this.activity = activity;
        this.bitmap = bitmap;
    }

    @Override
    public void run() {
        String s = UI_Connection.currency(bitmap, activity);
        Voice.speak(activity, s,true);
    }
}