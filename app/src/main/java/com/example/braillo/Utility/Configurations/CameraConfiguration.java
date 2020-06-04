package com.example.braillo.Utility.Configurations;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.io.File;

import androidx.core.app.ActivityCompat;
import io.fotoapparat.Fotoapparat;
import io.fotoapparat.error.CameraErrorListener;
import io.fotoapparat.exception.camera.CameraException;
import io.fotoapparat.parameter.ScaleType;
import io.fotoapparat.preview.Frame;
import io.fotoapparat.preview.FrameProcessor;
import io.fotoapparat.result.BitmapPhoto;
import io.fotoapparat.result.PhotoResult;
import io.fotoapparat.result.WhenDoneListener;
import io.fotoapparat.selector.FlashSelectorsKt;
import io.fotoapparat.selector.LensPositionSelectorsKt;
import io.fotoapparat.selector.ResolutionSelectorsKt;
import io.fotoapparat.view.CameraView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import static io.fotoapparat.result.transformer.ResolutionTransformersKt.scaled;

public class CameraConfiguration {

    private final int CameraCode = 1;
    private Frame  frame;
    public Frame getFrame() {

        return frame;
    }

    public Fotoapparat cameraConfiguration(CameraView cameraView , final Context activity) {
        return Fotoapparat
                .with(activity)
                .into(cameraView)

                .previewScaleType(ScaleType.CenterCrop)
                .previewResolution(ResolutionSelectorsKt.highestResolution())
                .photoResolution(ResolutionSelectorsKt.highestResolution())
                .lensPosition(LensPositionSelectorsKt.back())

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

    public void requestCameraPermission(final Activity activity) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA}, CameraCode);
    }

    public Fotoapparat cameraStart(Context context, CameraView cameraView) {

        return cameraConfiguration(cameraView , context);
    }


    private class SampleFrameProcessor implements FrameProcessor {
        @Override
        public void process(Frame frame) {

            CameraConfiguration.this.frame = frame;

        }
    }
}
