package com.example.braillo.Utility.Configurations;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.braillo.Utility.Voice;

import androidx.core.app.ActivityCompat;
import io.fotoapparat.Fotoapparat;
import io.fotoapparat.configuration.UpdateConfiguration;
import io.fotoapparat.error.CameraErrorListener;
import io.fotoapparat.exception.camera.CameraException;
import io.fotoapparat.parameter.ScaleType;
import io.fotoapparat.preview.Frame;
import io.fotoapparat.preview.FrameProcessor;
import io.fotoapparat.selector.ResolutionSelectorsKt;
import io.fotoapparat.view.CameraView;
import io.fotoapparat.view.FocusView;

import static io.fotoapparat.selector.FlashSelectorsKt.off;
import static io.fotoapparat.selector.FlashSelectorsKt.torch;
import static io.fotoapparat.selector.LensPositionSelectorsKt.back;

public class CameraConfiguration {

    private final int CameraCode = 1;
    private Frame frame;
    private Fotoapparat foto;
    private CameraView cameraView;
    private Context activity;
    private FocusView focusView;
    private boolean flash;

    public CameraConfiguration(CameraView cameraView, Context activity, FocusView focusView) {
        this.cameraView = cameraView;
        this.activity = activity;
        this.focusView = focusView;
        flash = false;

        foto = cameraConfiguration(cameraView, activity, focusView);
    }

    public Frame getFrame() {

        return frame;
    }

    private Fotoapparat cameraConfiguration(CameraView cameraView, Context activity, FocusView focusView ) {
        return Fotoapparat
                .with(activity)
                .into(cameraView)
                .previewScaleType(ScaleType.CenterCrop)
                .previewResolution(ResolutionSelectorsKt.highestResolution())
                .photoResolution(ResolutionSelectorsKt.highestResolution())
                .lensPosition(back())
                //.focusView(focusView)
                .flash(flash ? torch() : off())
                .frameProcessor(new CameraConfiguration.SampleFrameProcessor())
                .cameraErrorCallback(new CameraErrorListener() {
                    @Override
                    public void onError(CameraException e) {

                        Log.i("main", e.toString());
                    }
                })
                .build();
    }

    public void startCamera() {
        foto.start();
    }

    public void KillCamera() {
        foto.stop();
    }

    public void requestCameraPermission(final Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.CAMERA}, CameraCode);
    }

    UpdateConfiguration OnTorch = UpdateConfiguration.builder().flash(torch()).build();
    UpdateConfiguration offTorch = UpdateConfiguration.builder().flash(off()).build();

    public void toggleFlash(Activity activity) {

        foto.updateConfiguration(!flash ? OnTorch : offTorch);


        if(!flash){
            Voice.speak(activity, "AppCommands/flash opened.mp3",true);
        }else
            Voice.speak(activity, "AppCommands/flash closed.mp3",true);

        flash = flash != true;
    }

    private class SampleFrameProcessor implements FrameProcessor {
        @Override
        public void process(Frame frame) {

            CameraConfiguration.this.frame = frame;

        }
    }
}
