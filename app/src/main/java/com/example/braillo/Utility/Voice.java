package com.example.braillo.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class Voice {

    private static MediaPlayer mediaPlayer;
    private static TextToSpeech mTTS;

    private static void stopTTS() {
        if (Voice.mTTS != null) {
            Voice.mTTS.stop();
        }
    }

    private static void releaseTTS() {
        if (Voice.mTTS != null) {
            Voice.mTTS.stop();
            Voice.mTTS.shutdown();
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
        releaseTTS();
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
        if (flag) {
            releaseTTS();
            playAssetSound(activity, s);
            Log.i("speak debug", "speak: in currency & detection");
        } else {
            Log.i("speak debug", "speak: in ocr :: "+s);
            release();
            mTTS = new TextToSpeech(activity, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = mTTS.setLanguage(Locale.ENGLISH);
                        mTTS.speak(s, TextToSpeech.QUEUE_FLUSH, null);

                    } else {
                        Log.e("TTS", "Initialization failed");
                    }
                }
            });
        }

    }
}
