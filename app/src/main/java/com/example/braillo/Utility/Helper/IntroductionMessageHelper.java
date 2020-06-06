package com.example.braillo.Utility.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.braillo.Threads.IntroductionMessage;
import com.example.braillo.Utility.Voice;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class IntroductionMessageHelper {
    Activity activity;

    Context context;
    boolean firstStartArabic , firstStartEnglish;
    SharedPreferences prefArabic , prefEnglish;
    SharedPreferences.Editor editorA , editorE;
    IntroductionMessage aintroductionMessage , eintroductionMessage;

    public IntroductionMessageHelper(Activity activity, Context context) {
        this.activity = activity;

        this.context = context;
    }

    public void introductionMessage(boolean hasCameraPermission) {
        prefArabic = activity.getSharedPreferences("arabicIntro", MODE_PRIVATE);
        prefEnglish = activity.getSharedPreferences("EnglishIntro", MODE_PRIVATE);
        firstStartArabic = prefArabic.getBoolean("arabicIntro", true);
        firstStartEnglish = prefEnglish.getBoolean("EnglishIntro" , true);
        if(Voice.Language.equals("en")) {
            Log.d("lang" , Voice.Language);
            if (firstStartEnglish) {
                removeIntroductionMessageEnglish(hasCameraPermission);
            }
        }else{
            if (firstStartArabic) {
                removeIntroductionMessageArabic(hasCameraPermission);
            }
        }
    }

    public void removeIntroductionMessageArabic(boolean hasCameraPermission) {
        if (hasCameraPermission) {
            aintroductionMessage = new IntroductionMessage(activity , "AppCommand/welcomeMessage.mp3");
            aintroductionMessage.start();
            prefArabic = activity.getSharedPreferences("arabicIntro", MODE_PRIVATE);
            editorA = prefArabic.edit();
            editorA.putBoolean("arabicIntro", false);
            editorA.apply();
            prefEnglish = activity.getSharedPreferences("EnglishIntro", MODE_PRIVATE);
            editorE = prefEnglish.edit();
            editorE.putBoolean("EnglishIntro", false);
            editorE.apply();
        }

    }
    public void removeIntroductionMessageEnglish(boolean hasCameraPermission) {
        if (hasCameraPermission) {
            //todo : put the english message
            eintroductionMessage = new IntroductionMessage(activity , "AppCommand/welcomeMessage.mp3");
            eintroductionMessage.start();
            prefEnglish = activity.getSharedPreferences("EnglishIntro", MODE_PRIVATE);
            editorE = prefEnglish.edit();
            editorE.putBoolean("EnglishIntro", false);
            editorE.apply();
            prefArabic = activity.getSharedPreferences("arabicIntro", MODE_PRIVATE);
            editorA = prefArabic.edit();
            editorA.putBoolean("arabicIntro", false);
            editorA.apply();
        }

    }
}
