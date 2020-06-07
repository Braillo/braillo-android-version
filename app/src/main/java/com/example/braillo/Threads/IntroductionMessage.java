package com.example.braillo.Threads;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.braillo.Utility.Voice;

import static android.content.Context.MODE_PRIVATE;

public class IntroductionMessage extends Thread {
    Activity activity;
    String S;
    SharedPreferences prefArabic, prefEnglish;

    public IntroductionMessage(Activity activity, String s) {
        this.activity = activity;
        this.S = s;
        prefArabic = activity.getSharedPreferences("arabicIntro", MODE_PRIVATE);
        prefEnglish = activity.getSharedPreferences("EnglishIntro", MODE_PRIVATE);
    }

    @Override
    public void run() {
        if (prefArabic.getBoolean("arabicIntro", true) ||
                prefEnglish.getBoolean("EnglishIntro", true)) {
            Voice.speak(activity, "AppCommands/application language is English.mp3", true);
        }


        Voice.speak(activity, S, true);
    }
}
