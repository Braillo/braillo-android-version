package com.example.braillo.Utility;

import android.content.Context;
import android.view.MotionEvent;

import com.github.pwittchen.swipe.library.rx2.SimpleSwipeListener;

public class Gestures extends SimpleSwipeListener {
    private int swipeStep = 0;

    public int getSwipeStep() {
        return swipeStep;
    }

    @Override
    public boolean onSwipedLeft(MotionEvent event) {
        swipeStep = (swipeStep + 1) % 5;
        return false;
    }

    @Override
    public boolean onSwipedRight(MotionEvent event) {
        swipeStep = ((swipeStep - 1) % 5 + 5 ) % 5; // to handel Negative value
        return false;
    }


}
