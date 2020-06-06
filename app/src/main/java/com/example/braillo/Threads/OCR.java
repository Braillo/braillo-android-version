package com.example.braillo.Threads;

import android.app.Activity;
import android.graphics.Bitmap;

import com.example.braillo.Utility.UI_Connection;

public class OCR extends Thread {
    Activity activity;
    Bitmap bitmap;

    public OCR(Activity activity, Bitmap bitmap) {
        this.activity = activity;
        this.bitmap = bitmap;
    }
    public void reSetData(Activity activity, Bitmap bitmap){
        this.activity = activity;
        this.bitmap = bitmap;
    }
    @Override
    public void run() {
        UI_Connection.OCR(bitmap, activity);
    }



}
