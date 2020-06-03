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

    private Bitmap convertBitMap(Frame frame) {
        YuvImage yuvImage = new YuvImage(frame.getImage(), ImageFormat.NV21, frame.getSize().width, frame.getSize().height, null);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, frame.getSize().width, frame.getSize().height), 100, os);
        byte[] jpegByteArray = os.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(jpegByteArray, 0, jpegByteArray.length);
        return bitmap;
    }


    public Bitmap getBitmap(Frame frame) {

        Bitmap bitmap = this.convertBitMap(frame);

        Matrix matrix = new Matrix();



            matrix.postRotate(90);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

            Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
            return  rotatedBitmap;

    }
}
