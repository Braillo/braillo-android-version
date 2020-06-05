package com.example.braillo.Threads;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class IntroductionMessage extends Thread {
    Context context;
    MediaPlayer mediaPlayer;
    AssetFileDescriptor descriptor;
    public IntroductionMessage(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        try {
            mediaPlayer = new MediaPlayer();
            descriptor = context.getAssets().openFd("AppCommand/welcomeMessage.mp3");
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
}
