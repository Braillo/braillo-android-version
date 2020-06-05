package com.example.braillo.Utility.Configurations;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

import static io.fotoapparat.selector.FlashSelectorsKt.autoFlash;
import static io.fotoapparat.selector.FlashSelectorsKt.autoRedEye;
import static io.fotoapparat.selector.FlashSelectorsKt.off;
import static io.fotoapparat.selector.FlashSelectorsKt.torch;
import static io.fotoapparat.selector.LensPositionSelectorsKt.back;
import static io.fotoapparat.selector.SelectorsKt.firstAvailable;

public class CameraConfiguration {

    private final int CameraCode = 1;
    private Frame frame;
    private Fotoapparat foto;

    public CameraConfiguration(CameraView cameraView, final Context activity, FocusView focusView) {
        foto = cameraConfiguration(cameraView, activity, focusView);
    }

    public Frame getFrame() {

        return frame;
    }

    private Fotoapparat cameraConfiguration(CameraView cameraView, final Context activity, FocusView focusView) {
        return Fotoapparat
                .with(activity)
                .into(cameraView)
                .previewScaleType(ScaleType.CenterCrop)
                .previewResolution(ResolutionSelectorsKt.highestResolution())
                .photoResolution(ResolutionSelectorsKt.highestResolution())
                .lensPosition(back())
                //.focusView(focusView)
                .flash(firstAvailable(
                        autoRedEye(),
                        autoFlash(),
                        torch(),
                        off()))
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

    public void toggleFlash(boolean flag) {

        foto.updateConfiguration(
                UpdateConfiguration.builder()
                        .flash(
                                flag ? torch() : off()
                        )
                        .build()
        );
    }

    private class SampleFrameProcessor implements FrameProcessor {
        @Override
        public void process(Frame frame) {

            CameraConfiguration.this.frame = frame;

        }
    }
}
