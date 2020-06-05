package com.example.braillo.Activities;

import android.app.Activity;
import android.view.View;

public class ActivityHelper {


    public static void hideNotificationBar(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

    }

}
