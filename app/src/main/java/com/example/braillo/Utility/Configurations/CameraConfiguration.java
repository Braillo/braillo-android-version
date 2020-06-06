package com.example.braillo.Utility.Configurations;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import org.jetbrains.annotations.Nullable;

import io.fotoapparat.Fotoapparat;
import io.fotoapparat.configuration.Configuration;
import io.fotoapparat.configuration.UpdateConfiguration;
import io.fotoapparat.error.CameraErrorListener;
import io.fotoapparat.exception.camera.CameraException;
import io.fotoapparat.parameter.AntiBandingMode;
import io.fotoapparat.parameter.Flash;
import io.fotoapparat.parameter.FocusMode;
import io.fotoapparat.parameter.FpsRange;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.parameter.ScaleType;
import io.fotoapparat.preview.Frame;
import io.fotoapparat.preview.FrameProcessor;
import io.fotoapparat.selector.ResolutionSelectorsKt;
import io.fotoapparat.view.CameraView;
import io.fotoapparat.view.FocusView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.IntRange;

import static io.fotoapparat.selector.FlashSelectorsKt.autoFlash;
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

    public CameraConfiguration(CameraView cameraView, Context activity, FocusView focusView) {
        foto = cameraConfiguration(cameraView, activity, focusView, true);
        this.cameraView = cameraView;
        this.activity = activity;
        this.focusView = focusView;
    }

    public Frame getFrame() {

        return frame;
    }

    private Fotoapparat cameraConfiguration(CameraView cameraView, Context activity, FocusView focusView, boolean flag) {
        return Fotoapparat
                .with(activity)
                .into(cameraView)
                .previewScaleType(ScaleType.CenterCrop)
                .previewResolution(ResolutionSelectorsKt.highestResolution())
                .photoResolution(ResolutionSelectorsKt.highestResolution())
                .lensPosition(back())
                //.focusView(focusView)
                .flash(flag ? torch() : off())
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
    UpdateConfiguration offTorch = UpdateConfiguration.builder().flash( off()).build();
    public void toggleFlash(final boolean flag) {

        // foto.updateConfiguration( flag ? OnTorch:offTorch );

    }

    private class SampleFrameProcessor implements FrameProcessor {
        @Override
        public void process(Frame frame) {

            CameraConfiguration.this.frame = frame;

        }
    }
}
