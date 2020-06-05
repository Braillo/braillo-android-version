package com.example.braillo.Utility.Configurations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;

import java.io.ByteArrayOutputStream;

import io.fotoapparat.preview.Frame;

public class BitmapConfiguration {
    YuvImage yuvImage;
    ByteArrayOutputStream os;
    byte[] jpegByteArray;
    Bitmap bitmap;
    Matrix matrix;
    Bitmap scaledBitmap, bitmap1, rotatedBitmap;

    private Bitmap convertBitMap(Frame frame) {
        yuvImage = new YuvImage(frame.getImage(), ImageFormat.NV21, frame.getSize().width, frame.getSize().height, null);
        os = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, frame.getSize().width, frame.getSize().height), 100, os);
        jpegByteArray = os.toByteArray();
        bitmap = BitmapFactory.decodeByteArray(jpegByteArray, 0, jpegByteArray.length);
        return bitmap;
    }

    public Bitmap getBitmap(Frame frame) {

        bitmap1 = convertBitMap(frame);

        matrix = new Matrix();


        matrix.postRotate(90);
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

        rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        return rotatedBitmap;

    }
}
