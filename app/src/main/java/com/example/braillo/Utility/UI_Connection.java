package com.example.braillo.Utility;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.braillo.Models.Helper.CurrencyModelClassifier;
import com.example.braillo.Models.Helper.DetectionModelClassifier;
import com.example.braillo.Models.Models;
import com.example.braillo.Models.PreTrainedModel.BarcodeRecognizer;
import com.example.braillo.Models.PreTrainedModel.OCRRecognizer;
import com.example.braillo.Models.Recognition;

import java.util.HashMap;
import java.util.List;

public class UI_Connection {
    private static DetectionModelClassifier classifier;
    private static List<Recognition> list;
    private static BarcodeRecognizer barcodeRecognizer;
    private static OCRRecognizer ocrRecognizer;
    private static ColorHelper color;
    private static HashMap<Integer, String[]> map;
    private static String[] Label;
    private static String[] conf;
    private static HashMap<String, String> hashMap = new HashMap<String, String>();

    public static List<Recognition> detection(Bitmap bitmap, Activity activity) {
        classifier = new DetectionModelClassifier(activity,
                bitmap, Models.DETECTIONMODEL, Models.DETECTIONLABEL, true);
        classifier.classifer();
        list = classifier.getAllOutPut();

        return list;
    }

    public static String currency(Bitmap bitmap, Activity activity) {
        String s;
        CurrencyModelClassifier classifier = new CurrencyModelClassifier(activity,
                bitmap, Models.CURRENCYMODEL, Models.CURRENCYLABEL, false);

        map = classifier.classifer();
        Label = map.get(0);
        conf = map.get(1);
        for (int i = 0; i < conf.length; i++) {
            Log.i("label", Label[i]);

            Log.i("Conf", conf[i]);
        }

        if (Float.parseFloat(conf[conf.length - 1]) > 0.4) {
            s = hashMap.get(Label[conf.length - 1]);
        } else {
            s = "AppCommand/can not identify.mp3";
        }
        return s;
    }

    public static void get_Barcode(Bitmap bitmap, final Activity activity, Application application) {

        BarcodeRecognizer.getBarcode(bitmap, activity, application);


    }

    public static void OCR(Bitmap bitmap, final Activity activity) {

        Voice.speak(activity, OCRRecognizer.OCR(bitmap, activity), false);
    }

    public static void colorDetection(Bitmap bitmap, final Activity activity) {
        color = new ColorHelper(activity, bitmap);
        color.paletteProcessing();
    }

    //static voice
    public static void fillMap() {
        hashMap.put("10", "CurrencyDetection/c10.mp3");
        hashMap.put("11", "CurrencyDetection/c10.mp3");
        hashMap.put("20", "CurrencyDetection/c20.mp3");
        hashMap.put("22", "CurrencyDetection/c20.mp3");
        hashMap.put("30", "CurrencyDetection/c30.mp3");
        hashMap.put("33", "CurrencyDetection/c30.mp3");
        hashMap.put("40", "CurrencyDetection/c40.mp3");
        hashMap.put("44", "CurrencyDetection/c40.mp3");
        hashMap.put("50", "CurrencyDetection/c50.mp3");
        hashMap.put("55", "CurrencyDetection/c50.mp3");
        hashMap.put("60", "CurrencyDetection/c60.mp3");
        hashMap.put("66", "CurrencyDetection/c60.mp3");
        hashMap.put("70", "CurrencyDetection/c70.mp3");
        hashMap.put("77", "CurrencyDetection/c70.mp3");
        hashMap.put("80", "CurrencyDetection/c80.mp3");
        hashMap.put("88", "CurrencyDetection/c80.mp3");
        hashMap.put("90", "CurrencyDetection/c90.mp3");
        hashMap.put("99", "CurrencyDetection/c90.mp3");

    }

}
