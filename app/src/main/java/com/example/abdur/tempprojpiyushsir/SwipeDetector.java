package com.example.abdur.tempprojpiyushsir;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.logging.Logger;

/**
 * Created by abdur on 1/7/15.
 */
public class SwipeDetector implements View.OnTouchListener {

    public static enum Action {
        LR, // Left to right
        RL, // Right to left
        TB, // Top to bottom
        BT, // Bottom to top
        None // Action not found
    }

    private static final int HORIZONTAL_MIN_DISTANCE = 100; // The minimum distance for horizontal swipe
    private static final int VERTICAL_MIN_DISTANCE = 80; // The minimum distance for vertical swipe
    private float downX, downY, upX, upY; // Coordinates
    private Action mSwipeDetected = Action.None; // Last action

    public boolean swipeDetected() {
        return mSwipeDetected != Action.None;
    }

    public Action getAction() {
        return mSwipeDetected;
    }

    /**
     * Swipe detection
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                mSwipeDetected = Action.None;
                return false;
            }
            case MotionEvent.ACTION_MOVE: {
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;


                // Detection of horizontal swipe
                if (Math.abs(deltaX) > HORIZONTAL_MIN_DISTANCE) {
                    // Left to right
                    if (deltaX < 0) {
                        mSwipeDetected = Action.LR;
                        return true;
                    }
                    // Right to left
                    if (deltaX > 0) {
                        mSwipeDetected = Action.RL;
                        return true;
                    }
                } else

                    // Detection of vertical swipe
                    if (Math.abs(deltaY) > VERTICAL_MIN_DISTANCE) {
                        // Top to bottom
                        if (deltaY < 0) {
                            mSwipeDetected = Action.TB;
                            return false;
                        }
                        // Bottom to top
                        if (deltaY > 0) {
                            mSwipeDetected = Action.BT;
                            return false;
                        }
                    }
                return true;
            }
        }
        return false;
    }
}
