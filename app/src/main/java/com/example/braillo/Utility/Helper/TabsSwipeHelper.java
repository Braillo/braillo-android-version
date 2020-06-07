package com.example.braillo.Utility.Helper;

import android.app.Activity;
import android.util.Log;

import com.example.braillo.Utility.Gestures;
import com.example.braillo.Utility.Voice;

public class TabsSwipeHelper {
    boolean currencyFlag = false, detectionFlag = true, barcodeFlag = false, ocrFlag = false, colorFlag = false;
    Gestures gestures;
    ThreadHelper threadHelper;

    public TabsSwipeHelper(Gestures gestures, ThreadHelper threadHelper) {
        this.gestures = gestures;
        this.threadHelper = threadHelper;
    }

    public void convertFlags(int swipeStep) {
        switch (swipeStep) {
            case 1:
                currencyFlag = true;
                detectionFlag = false;
                colorFlag = false;
                ocrFlag = false;
                barcodeFlag = false;

                break;
            case 3:
                currencyFlag = false;
                detectionFlag = false;
                colorFlag = false;
                ocrFlag = true;
                barcodeFlag = false;
                break;
            case 4:
                currencyFlag = false;
                detectionFlag = false;
                colorFlag = false;
                ocrFlag = false;
                barcodeFlag = true;
                break;
            case 2:
                currencyFlag = false;
                detectionFlag = false;
                colorFlag = true;
                ocrFlag = false;
                barcodeFlag = false;
                break;
            default:
                currencyFlag = false;
                detectionFlag = true;
                colorFlag = false;
                ocrFlag = false;
                barcodeFlag = false;
                break;
        }
    }

    public void tabs(Activity activity) {
        switch (gestures.getSwipeStep()) {
            case 1:
                if (!currencyFlag) {
                    Voice.speak(activity, "AppCommands/currency detection.mp3", true);
                    convertFlags(gestures.getSwipeStep());
                } else {

                    threadHelper.currencyThread();
                }
                Log.d("tabs", "currency");

                return;
            case 3:
                if (!ocrFlag) {
                    Voice.speak(activity, "AppCommands/Text recognizer.mp3", true);
                    convertFlags(gestures.getSwipeStep());
                } else {

                    threadHelper.OCRThread();
                }
                Log.d("tabs", "OCR");

                return;
            case 4:
                if (!barcodeFlag) {
                    Voice.speak(activity, "AppCommands/barcode Recognizer.mp3", true);
                    convertFlags(gestures.getSwipeStep());
                } else {
                    threadHelper.barcodeThread();
                }
                Log.d("tabs", "barcode");
                //Barcode
                return;
            case 2:
                if (!colorFlag) {
                    Voice.speak(activity, "AppCommands/color recognizer.mp3", true);
                    convertFlags(gestures.getSwipeStep());
                } else {
                    threadHelper.ColorThread();
                }
                Log.d("tabs", "color");
                //color
                return;
            default:
                if (!detectionFlag) {
                    Voice.speak(activity, "AppCommands/object detection.mp3", true);
                    convertFlags(gestures.getSwipeStep());
                } else {
                    threadHelper.detectionThread();
                }
                //detection
                Log.d("tabs", "detection");
                return;

        }
    }
}
