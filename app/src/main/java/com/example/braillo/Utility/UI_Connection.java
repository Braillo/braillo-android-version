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
    private static DetectionModelClassifier detectionClassifier;
    private static List<Recognition> list;
    private static BarcodeRecognizer barcodeRecognizer;
    private static OCRRecognizer ocrRecognizer;
    private static ColorHelper color;
    private static HashMap<Integer, String[]> map;
    private static String[] Label;
    private static String[] conf;
    private static HashMap<String, String> hashMap = new HashMap<String, String>();
    private static CurrencyModelClassifier currencyClassifier;


    public static List<Recognition> detection(Bitmap bitmap, Activity activity) {
        detectionClassifier = new DetectionModelClassifier(activity,
                bitmap, Models.DETECTIONMODEL, Models.DETECTIONLABEL, true);
        detectionClassifier.classifer();
        list = detectionClassifier.getAllOutPut();

        return list;
    }

    public static String currency(Bitmap bitmap, Activity activity) {
        String s;
        currencyClassifier = new CurrencyModelClassifier(activity,
                bitmap, Models.CURRENCYMODEL, Models.CURRENCYLABEL, false);

        map = currencyClassifier.classifer();
        Label = map.get(0);
        conf = map.get(1);
        for (int i = 0; i < conf.length; i++) {
            Log.i("label", Label[i]);

            Log.i("Conf", conf[i]);
        }

        if (Float.parseFloat(conf[conf.length - 1]) > 0.4) {
            s = hashMap.get(Label[conf.length - 1]);
        } else {
            s = Voice.getCanNot();
        }
        return s;
    }

    public static void get_Barcode(Bitmap bitmap, final Activity activity, Application application) {
        barcodeRecognizer = new BarcodeRecognizer();
        barcodeRecognizer.getBarcode(bitmap, activity, application);
    }

    public static void OCR(Bitmap bitmap, final Activity activity) {
        OCRRecognizer ocrRecognizer = new OCRRecognizer();
        ocrRecognizer.OCR(bitmap, activity);
    }

    public static void colorDetection(Bitmap bitmap, final Activity activity) {
        color = new ColorHelper(activity, bitmap);
        color.paletteProcessing();
    }

    //static voice
    public static void fillMap() {
        hashMap.put("10", "CurrencyDetection/Quarter Pound.mp3");
        hashMap.put("11", "CurrencyDetection/Quarter Pound.mp3");
        hashMap.put("20", "CurrencyDetection/Half Pound.mp3");
        hashMap.put("22", "CurrencyDetection/Half Pound.mp3");
        hashMap.put("30", "CurrencyDetection/1 Pound.mp3");
        hashMap.put("33", "CurrencyDetection/1 Pound.mp3");
        hashMap.put("40", "CurrencyDetection/5 Pound.mp3");
        hashMap.put("44", "CurrencyDetection/5 Pound.mp3");
        hashMap.put("50", "CurrencyDetection/10 Pound.mp3");
        hashMap.put("55", "CurrencyDetection/10 Pound.mp3");
        hashMap.put("60", "CurrencyDetection/20 Pound.mp3");
        hashMap.put("66", "CurrencyDetection/20 Pound.mp3");
        hashMap.put("70", "CurrencyDetection/50 Pound.mp3");
        hashMap.put("77", "CurrencyDetection/50 Pound.mp3");
        hashMap.put("80", "CurrencyDetection/100 Pound.mp3");
        hashMap.put("88", "CurrencyDetection/100 Pound.mp3");
        hashMap.put("90", "CurrencyDetection/200 Pound.mp3");
        hashMap.put("99", "CurrencyDetection/200 Pound.mp3");

    }

}
