package com.example.braillo.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class Voice {

    private static  SharedPreferences prefLangToggle  ;
    private static MediaPlayer mediaPlayer;
    private static TextToSpeech mTTS;
    public static String Language = Locale.getDefault().getLanguage() != "en" && Locale.getDefault().getLanguage() != "ar" ?
            "ar" : Locale.getDefault().getLanguage();
    private static String[] temp;
    private static SharedPreferences.Editor editorLangToggle;
    static String langToggle;

    public static void initToggle(Activity activity){
        prefLangToggle = activity.getSharedPreferences("LnagToggle" ,MODE_PRIVATE);
        langToggle = prefLangToggle.getString("LnagToggle" ,Language);
        Log.d("langToggle",langToggle);
        Language = langToggle;
        Log.d("lang" , Language);

    }

    public static void toggleLang(Activity activity){
        prefLangToggle = activity.getSharedPreferences("LnagToggle" ,MODE_PRIVATE);
        editorLangToggle = prefLangToggle.edit();
        editorLangToggle .putString("LnagToggle" , Language);
        editorLangToggle.apply();
    }
    public static void init(final Activity activity) {
        mTTS = new TextToSpeech(activity, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                } else {
                    playAssetSound(activity, "AppCommands/TTSError.mp3");
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
    }

    private static void stopTTS() {
        if (Voice.mTTS != null) {
            Voice.mTTS.stop();
        }
    }



    private static void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static void release() {
        releaseMediaPlayer();
        stopTTS();

    }

    public static void playAssetSound(Context context, String labelVoiceFile) {
        try {
            releaseMediaPlayer();

            mediaPlayer = new MediaPlayer();

            AssetFileDescriptor descriptor = context.getAssets().openFd(labelVoiceFile);
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());

            descriptor.close();

            mediaPlayer.prepare();
            mediaPlayer.setVolume(1f, 1f);
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void speak(Activity activity, final String s, boolean flag) {
        if (Language == "en") {
            if (s.contains("englishWelcomeMessage")) {
                playAssetSound(activity, s);
            } else {
                final String x = localizer(s);
                release();
                if (mTTS != null)
                    mTTS.speak(x, TextToSpeech.QUEUE_FLUSH, null);
            }

        } else {

            if (flag) {
                release();
                playAssetSound(activity, s);

                Log.i("speak debug", "speak: in currency & detection");

            } else {
                Log.i("speak debug", "speak: in ocr :: " + s);
                release();
                if (mTTS != null)
                    mTTS.speak(s, TextToSpeech.QUEUE_FLUSH, null);


            }
        }
    }

    private static String localizer(String s) {
        if (Language == "en" && s.contains(".mp3") ) {
            temp = s.split("/");
            return temp[1].substring(0, temp[1].length() - 4);
        }
        return s;
    }

    public static String getCanNot() {
        return "AppCommands/can not identify.mp3";
    }
}
