package com.example.braillo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.braillo.R;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        hideNotificationBar();

         i = new Intent(Splash.this,
                MainActivity.class);
        //Intent is used to switch from one activity to another.

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        intent();
    }

    private void intent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, 8000);
    }

    private void hideNotificationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ActivityHelper.hideNotificationBar(this);

    }
}
