package com.adev.android.legomindfuck;


import android.util.Log;

public class ColorSensor {

    private int mColor;
    private int mReceivedColor = 0;
    private int mLight;
    private Boolean mFound = false;

    private void calculateColor() {
        switch(mReceivedColor) {
            case 1:     // Nero
                if (mLight > 4 && mLight < 10) {
                    mColor = mReceivedColor;
                    mFound = true;
                }
                else {
                    mColor = 0;
                    mFound = false;
                }
                break;
            case 2:     // Blu
                if (mLight > 5) {
                    mColor = mReceivedColor;
                    mFound = true;
                }
                else {
                    mColor = 0;
                    mFound = false;
                }
                break;
            case 4:     // Giallo
                if (mLight > 35) {
                    mColor = mReceivedColor;
                    mFound = true;
                }
                else {
                    mColor = 0;
                    mFound = false;
                }
                break;
            case 5:     // Rosso
                if (mLight > 15) {
                    mColor = mReceivedColor;
                    mFound = true;
                }
                else {
                    mColor = 0;
                    mFound = false;
                }
                break;
            default:
                mColor = 0;
                mFound = false;
                break;

        }
    }

    public synchronized int getColor() {
        return mColor;
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
