package com.example.braillo.Threads;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.braillo.Utility.UI_Connection;
import com.example.braillo.Models.Recognition;
import com.example.braillo.Utility.Voice;

import java.util.List;

public class ObjectDetection extends Thread {
    Activity activity;
    Bitmap bitmap;

    public ObjectDetection(Activity activity, Bitmap bitmap) {
        this.activity = activity;
        this.bitmap = bitmap;
    }

    @Override
    public void run() {
        List<Recognition> list = UI_Connection.detection(bitmap, activity);


        if( list.get(0).getConfidence() > 0.6) {
            Log.d("object",list.get(0).getId()+"::"+list.get(0).getTitle());
            Voice.speak(activity, "ObjectDetection/"+list.get(0).getTitle()+".mp3",true);
        }else{
            Voice.speak(activity, "AppCommand/can not identify.mp3",true);
        }
    }
}
