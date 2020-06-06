package com.example.braillo.Models.PreTrainedModel;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.braillo.Utility.Voice;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

public class OCRRecognizer {


    FirebaseVisionImage image;
    FirebaseVisionTextRecognizer textRecognizer;

    public OCRRecognizer() {
        textRecognizer = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();
    }

    public void OCR(Bitmap bitmap, final Activity activity ) {

        image = FirebaseVisionImage.fromBitmap(bitmap);
        textRecognizer.processImage(image)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText result) {

                        Voice.speak(activity, result.getText(), false);
                        // TextTranslation.translation(result.getText());
                        Log.d("IN OCR",result.getText());
                    }
                })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {

                                Voice.speak(activity, "no Text found", false);

                            }
                        });

    }
}
