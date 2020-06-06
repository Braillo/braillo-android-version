package com.example.braillo.Activities;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.braillo.R;
import com.example.braillo.Utility.Configurations.BitmapConfiguration;
import com.example.braillo.Utility.Configurations.CameraConfiguration;
import com.example.braillo.Utility.Gestures;
import com.example.braillo.Utility.Helper.IntroductionMessageHelper;
import com.example.braillo.Utility.Helper.TabsSwipeHelper;
import com.example.braillo.Utility.Helper.ThreadHelper;
import com.example.braillo.Utility.UI_Connection;
import com.example.braillo.Utility.Voice;
import com.github.pwittchen.swipe.library.rx2.Swipe;
import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;

import androidx.appcompat.app.AppCompatActivity;
import io.fotoapparat.view.CameraView;
import io.fotoapparat.view.FocusView;

/*
* TODO :

   ** settings --> toggle buttons , up-down volume buttons , flash , English language support

food detection -->
open with Google assistance
introduction message  finished
orientation --> ml kit simi finished

refactoring
hide notification bar --> activity helper class finished



future work -->
age and gender detection

* */
public class MainActivity extends AppCompatActivity {

    private final int CameraCode = 1;
    BitmapConfiguration bitmapConfiguration;

    //layout
    LinearLayout linearLayout;
    //threads
    ThreadHelper threadHelper;
    TabsSwipeHelper tabsSwipeHelper;
    IntroductionMessageHelper introductionMessageHelper;

    private FocusView focusView;
    private boolean hasCameraPermission = false;
    private CameraView cameraView;
    private CameraConfiguration cameraConfigurations;
    //SWIP
    private Swipe swipe;
    private Gestures gestures;

    public CameraConfiguration getCameraConfigurations() {
        return cameraConfigurations;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        importViews();
        cameraConfigurations = new CameraConfiguration(cameraView, this, focusView);
        if (hasCameraPermission)
            cameraConfigurations.startCamera();
        bitmapConfiguration = new BitmapConfiguration();


        threadHelper = new ThreadHelper(this, bitmapConfiguration, cameraConfigurations, getApplication());
        introductionMessageHelper = new IntroductionMessageHelper(this, this);
        swipeConfiguration();
        tabsSwipeHelper = new TabsSwipeHelper(gestures, threadHelper);


        ActivityHelper.hideNotificationBar(this);

        UI_Connection.fillMap();
        if (hasCameraPermission)
            introductionMessageHelper.introductionMessage(hasCameraPermission);
        mainScreen();


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (hasCameraPermission) {
            cameraConfigurations.startCamera();

        } else {
            cameraConfigurations.requestCameraPermission(this);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (hasCameraPermission) {

            cameraConfigurations.KillCamera();
        }

        threadHelper.killAllThreadsAndReleaseVoice();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CameraCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                hasCameraPermission = true;
                introductionMessageHelper.introductionMessage(hasCameraPermission);
                cameraConfigurations.startCamera();


            } else {

                hasCameraPermission = false;


                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void mainScreen() {
        final Activity activity = this;
        linearLayout.setOnClickListener(new DoubleClick(new DoubleClickListener() {

            @Override
            public void onSingleClick(View view) {
                if (hasCameraPermission)
                    tabsSwipeHelper.tabs(activity);
            }

            @Override
            public void onDoubleClick(View view) {
                if (hasCameraPermission) {
                    threadHelper.flashToggleThread();

                }
            }
        }));

        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (hasCameraPermission) {
                    Voice.Language = Voice.Language == "ar" ? "en" : "ar";
                    threadHelper.languageToggleThread();
                    introductionMessageHelper.introductionMessage(hasCameraPermission);
                }
                return true;
            }
        });
    }

    private void swipeConfiguration() {
        swipe = new Swipe();
        gestures = new Gestures();
        swipe.setListener(gestures);

    }

    private void importViews() {
        cameraView = findViewById(R.id.cameraView);
        linearLayout = findViewById(R.id.linearLayout);
        focusView = findViewById(R.id.focusView);
    }


}
