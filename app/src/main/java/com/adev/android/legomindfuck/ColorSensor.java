package com.adev.android.legomindfuck;

import android.util.Log;

public class ColorSensor {

    private int mColor;

    public synchronized int getColor() {
        return mColor;
    }

    public synchronized void setColor(int color) {
        Log.i("color", " " + color);
        mColor = color;
    }
}
