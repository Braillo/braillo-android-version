package com.example.braillo.Utility.Helper;

import android.app.Activity;
import android.app.Application;

import com.example.braillo.Threads.Barcode;
import com.example.braillo.Threads.ColorDetection;
import com.example.braillo.Threads.CurrencyDetection;
import com.example.braillo.Threads.OCR;
import com.example.braillo.Threads.ObjectDetection;
import com.example.braillo.Utility.Configurations.BitmapConfiguration;
import com.example.braillo.Utility.Configurations.CameraConfiguration;
import com.example.braillo.Utility.Voice;

public class ThreadHelper {
    CurrencyDetection currencyDetectionThread;
    ObjectDetection objectDetectionThread;
    Barcode barcodeThread;
    OCR ocrThread;
    ColorDetection colorThread;
    Activity activity;
    Application application;
    BitmapConfiguration bitmapConfiguration;
    CameraConfiguration cameraConfigurations;

    public ThreadHelper(Activity activity, BitmapConfiguration bitmapConfiguration, CameraConfiguration cameraConfigurations ,Application application) {
        this.activity = activity;
        this.bitmapConfiguration = bitmapConfiguration;
        this.cameraConfigurations = cameraConfigurations;
        this.application = application;
    }

    public void ColorThread() {
        killAllThreadsAndReleaseVoice();
        colorThread = new ColorDetection(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()));

        colorThread.start();
    }

    public void OCRThread() {
        killAllThreadsAndReleaseVoice();

        ocrThread = new OCR(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()));
        ocrThread.start();
    }

    public void barcodeThread() {
        killAllThreadsAndReleaseVoice();

        barcodeThread = new Barcode(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()),application);
        barcodeThread.start();
    }

    public void currencyThread() {
        killAllThreadsAndReleaseVoice();

        currencyDetectionThread = new CurrencyDetection(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()));
        currencyDetectionThread.start();
    }

    public void detectionThread() {
        killAllThreadsAndReleaseVoice();

        objectDetectionThread = new ObjectDetection(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()));
        objectDetectionThread.start();
    }

    public void killAllThreadsAndReleaseVoice() {

        if (currencyDetectionThread != null && currencyDetectionThread.isAlive()) {
            currencyDetectionThread.interrupt();
        }
        if (objectDetectionThread != null && objectDetectionThread.isAlive()) {
            objectDetectionThread.interrupt();
        }
        if (barcodeThread != null && barcodeThread.isAlive()) {
            barcodeThread.interrupt();
        }
        if (ocrThread != null && ocrThread.isAlive()) {
            ocrThread.interrupt();
        }
        if (colorThread != null && colorThread.isAlive()) {
            colorThread.interrupt();
        }
        Voice.release();
    }
}
