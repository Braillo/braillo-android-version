package com.example.braillo.Threads;

import android.app.Activity;

import com.example.braillo.Utility.Voice;

public class LanguageToggle extends Thread {
    Activity activity;

    public LanguageToggle(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        Voice.speak(activity, "AppCommand/application language is English.mp3", true);
    }

}
