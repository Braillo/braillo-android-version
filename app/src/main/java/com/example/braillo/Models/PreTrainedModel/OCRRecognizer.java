package com.example.braillo.Models.PreTrainedModel;

import android.app.Activity;
import android.graphics.Bitmap;

import com.example.braillo.Utility.Voice;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

public class OCRRecognizer {


    static FirebaseVisionImage image;
    static FirebaseVisionTextRecognizer textRecognizer;

    public static String OCR(Bitmap bitmap, final Activity activity) {
        final String[] s = new String[1];
        image = FirebaseVisionImage.fromBitmap(bitmap);
        textRecognizer = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();

        textRecognizer.processImage(image)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText result) {
                        Voice.speak(activity, result.getText(), false);
                        // TextTranslation.translation(result.getText());

                    }
                })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {

                                Voice.speak(activity, "no Text found", false);

                            }
                        });
        return s[0];
    }
}
