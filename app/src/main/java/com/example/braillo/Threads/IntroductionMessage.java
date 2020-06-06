package com.example.braillo.Threads;

import android.app.Activity;

import com.example.braillo.Utility.Voice;

public class IntroductionMessage extends Thread {
    Activity activity;

    public IntroductionMessage(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        Voice.speak(activity, "AppCommand/welcomeMessage.mp3", true);
    }
}
