package com.adev.android.legomindfuck;

import android.util.Log;

import static com.adev.android.legomindfuck.Activity.PlayMenuActivity.sColorSensor;

public class ColorSensor {

    private int mColor = 0;
    private int mReceivedColor = 0;
    private int mLight = 0;
    private Boolean mFound = false;

    private void calculateColor() {
        if (mLight > 0) {
            switch(mReceivedColor) {
                case 1:     // Nero
                    if (mLight < 10) {
                        mColor = mReceivedColor;
                        mFound = true;
                    } else {
                        mColor = 0;
                        mFound = false;
                    }
                    break;
                case 2:     // Blu
                    if (mLight < 15) {
                        mColor = mReceivedColor;
                        mFound = true;
                    } else {
                        mColor = 0;
                        mFound = false;
                    }
                    break;
                case 4:     // Giallo
                    if (mLight > 25) {
                        mColor = mReceivedColor;
                        mFound = true;
                    } else {
                        mColor = 0;
                        mFound = false;
                    }
                    break;
                case 5:     // Rosso
                    if (mLight > 10 && mLight < 35) {
                        mColor = mReceivedColor;
                        mFound = true;
                    } else {
                        mColor = 0;
                        mFound = false;
                    }
                    break;
                default:
                    mColor = 0;
                    mFound = false;
                    break;
            }

            sColorSensor.notify();
        }
    }

    public synchronized String colorToString(int c) {
        switch (c) {
            case 1:
                return "Black";
            case 2:
                return "Blue";
            case 3:
                return "Green";
            case 4:
                return "Yellow";
            case 5:
                return "Red";
            case 6:
                return "White";
            case 7:
                return "Brown";
        }
        return "No Color";
    }

    public synchronized int getColor() {
        return mColor;
    }

    public synchronized int getLight() {
        return mLight;
    }

    public synchronized void setColor(int color) {
        mReceivedColor = color;
        calculateColor();
    }

    public synchronized void setLight(int light) {
        mLight = light;
    }

    public synchronized Boolean found() {
        return mFound;
    }
}
