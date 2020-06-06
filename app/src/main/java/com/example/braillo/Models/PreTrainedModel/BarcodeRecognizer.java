package com.example.braillo.Models.PreTrainedModel;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.example.braillo.DataBase.BarcodeRepository;
import com.example.braillo.Utility.Voice;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BarcodeRecognizer {


    private FirebaseVisionBarcodeDetector detector;
    private FirebaseVisionBarcodeDetectorOptions options;
    private FirebaseVisionImage image;
    private Task<List<FirebaseVisionBarcode>> result;
    private BarcodeRepository barcodeRepository;

    public BarcodeRecognizer( ){
        options = new FirebaseVisionBarcodeDetectorOptions.Builder()
                .setBarcodeFormats(
                        FirebaseVisionBarcode.FORMAT_ALL_FORMATS)
                .build();
        detector = FirebaseVision.getInstance()
                .getVisionBarcodeDetector(options);
    }
    public void getBarcode(Bitmap bitmap, final Activity activity, final Application application) {


        image = FirebaseVisionImage.fromBitmap(bitmap);
        result = detector.detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionBarcode> barcodes) {
                        for (FirebaseVisionBarcode barcode : barcodes) {


                            barcodeRepository = new BarcodeRepository(application);
                            Log.d("barcode ", barcode.getRawValue());
                            //  Toast.makeText(activity, barcode.getRawValue(), Toast.LENGTH_SHORT).show();
                            try {
                                if (barcodeRepository.getNameCode(barcode.getRawValue()) != null &&
                                        barcodeRepository.getNameCode(barcode.getRawValue()).size() != 0) {
                                    Voice.speak(activity, barcodeRepository.getNameCode(barcode.getRawValue()).get(0).getBarcodeName(),false);
                                    Log.d("barcode", barcodeRepository.getNameCode(barcode.getRawValue()).get(0).getBarcodeName());
                                } else
                                    //Log.d("barcode" , barcode.getRawValue());
                                    Voice.speak(activity,Voice.getCanNot(),false);

                                Log.d("barcode", barcode.getFormat() + "");
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            int valueType = barcode.getValueType();

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.d("barcode", "Cant not find any code");
                        Toast.makeText(activity, "Cant not find any code", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
