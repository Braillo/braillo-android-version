package com.example.braillo.Utility.Helper;

import android.app.Activity;
import android.app.Application;

import com.example.braillo.Threads.Barcode;
import com.example.braillo.Threads.ColorDetection;
import com.example.braillo.Threads.CurrencyDetection;
import com.example.braillo.Threads.FlashToggle;
import com.example.braillo.Threads.LanguageToggle;
import com.example.braillo.Threads.OCR;
import com.example.braillo.Threads.ObjectDetection;
import com.example.braillo.Utility.Configurations.BitmapConfiguration;
import com.example.braillo.Utility.Configurations.CameraConfiguration;
import com.example.braillo.Utility.Voice;

public class ThreadHelper {
    Activity activity;
    Application application;
    BitmapConfiguration bitmapConfiguration;
    CameraConfiguration cameraConfigurations;

    CurrencyDetection currencyDetectionThread;
    ObjectDetection objectDetectionThread;
    ColorDetection colorThread;
    Barcode barcodeThread;
    OCR ocrThread;

    LanguageToggle languageToggleThread;
    FlashToggle flashToggleThread;

    public ThreadHelper(Activity activity, BitmapConfiguration bitmapConfiguration,
                        CameraConfiguration cameraConfigurations, Application application) {
        this.activity = activity;
        this.bitmapConfiguration = bitmapConfiguration;
        this.cameraConfigurations = cameraConfigurations;
        this.application = application;
    }

    public void flashToggleThread() {
        killAllThreadsAndReleaseVoice();
        if (flashToggleThread != null) {
            flashToggleThread.run();
        } else {
            flashToggleThread = new FlashToggle(activity, cameraConfigurations);
            flashToggleThread.start();
        }
    }

    public void languageToggleThread() {
        killAllThreadsAndReleaseVoice();
        if (languageToggleThread != null) {
            languageToggleThread.run();
        } else {
            languageToggleThread = new LanguageToggle(activity);
            languageToggleThread.start();
        }

    }

    public void ColorThread() {
        killAllThreadsAndReleaseVoice();
        if (colorThread != null) {
            colorThread.reSetData(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()));
            colorThread.run();
        } else {
            colorThread = new ColorDetection(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()));

            colorThread.start();
        }
    }

    public void OCRThread() {
        killAllThreadsAndReleaseVoice();
        if (ocrThread != null) {
            ocrThread.reSetData(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()));
            ocrThread.run();
        } else {
            ocrThread = new OCR(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()));
            ocrThread.start();
        }
    }

    public void barcodeThread() {
        killAllThreadsAndReleaseVoice();
        if (barcodeThread != null) {
            barcodeThread.reSetData(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()), application);
            barcodeThread.run();
        } else {
            barcodeThread = new Barcode(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()), application);
            barcodeThread.start();
        }
    }

    public void currencyThread() {
        killAllThreadsAndReleaseVoice();
        if (currencyDetectionThread != null) {
            currencyDetectionThread.reSetData(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()));
            currencyDetectionThread.run();
        } else {
            currencyDetectionThread = new CurrencyDetection(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()));
            currencyDetectionThread.start();
        }
    }

    public void detectionThread() {
        killAllThreadsAndReleaseVoice();
        if (objectDetectionThread != null) {
            objectDetectionThread.reSetData(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()));
            objectDetectionThread.run();
        } else {
            objectDetectionThread = new ObjectDetection(activity, bitmapConfiguration.getBitmap(cameraConfigurations.getFrame()));
            objectDetectionThread.start();
        }
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
