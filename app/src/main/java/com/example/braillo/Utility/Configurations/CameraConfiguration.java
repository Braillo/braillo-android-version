package com.example.braillo.Utility.Configurations;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import io.fotoapparat.Fotoapparat;
import io.fotoapparat.error.CameraErrorListener;
import io.fotoapparat.exception.camera.CameraException;
import io.fotoapparat.parameter.ScaleType;
import io.fotoapparat.preview.Frame;
import io.fotoapparat.preview.FrameProcessor;
import io.fotoapparat.selector.FlashSelectorsKt;
import io.fotoapparat.selector.LensPositionSelectorsKt;
import io.fotoapparat.selector.ResolutionSelectorsKt;
import io.fotoapparat.view.CameraView;
import io.fotoapparat.view.FocusView;

import static io.fotoapparat.selector.LensPositionSelectorsKt.back;

public class CameraConfiguration {

    private final int CameraCode = 1;
    private Frame  frame;
    private Fotoapparat foto;
    public Frame getFrame() {

        return frame;
    }

    public CameraConfiguration(CameraView cameraView, final Context activity, FocusView focusView) {
        foto = cameraConfiguration(cameraView,activity,focusView);
    }

    public Fotoapparat cameraConfiguration(CameraView cameraView, final Context activity, FocusView focusView) {
        return Fotoapparat
                .with(activity)
                .into(cameraView)
                .previewScaleType(ScaleType.CenterCrop)
                .previewResolution(ResolutionSelectorsKt.highestResolution())
                .photoResolution(ResolutionSelectorsKt.highestResolution())
                .lensPosition(back())
                //.focusView(focusView)
                .flash(FlashSelectorsKt.torch())
                .frameProcessor(new CameraConfiguration.SampleFrameProcessor())
                .cameraErrorCallback(new CameraErrorListener() {
                    @Override
                    public void onError(CameraException e) {
                        Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show();
                        Log.i("main", e.toString());
                    }
                })
                .build();
    }
    public void startCamera(){
        foto.start();
    }
    public void KillCamera(){
        foto.stop();
    }
    public void requestCameraPermission(final Activity activity) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA}, CameraCode);
    }

    public Fotoapparat cameraStart(Context context, CameraView cameraView, FocusView focusView) {

        return cameraConfiguration(cameraView , context,focusView );
    }


    private class SampleFrameProcessor implements FrameProcessor {
        @Override
        public void process(Frame frame) {

            CameraConfiguration.this.frame = frame;

        }
    }
}
