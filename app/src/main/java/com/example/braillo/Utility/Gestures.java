package com.example.braillo.Utility;

import android.view.MotionEvent;

import com.github.pwittchen.swipe.library.rx2.SimpleSwipeListener;

public class Gestures extends SimpleSwipeListener {
    private int swipeStep = 0;
    public static int swipesNumber;

    public int getSwipeStep() {
        return swipeStep;
    }



    @Override
    public boolean onSwipedLeft(MotionEvent event) {
        swipeStep = (swipeStep + 1) % swipesNumber;
        return false;
    }

    @Override
    public boolean onSwipedRight(MotionEvent event) {
        swipeStep = ((swipeStep - 1) % swipesNumber+ swipesNumber) % swipesNumber; // to handel Negative value
        return false;
    }


}
