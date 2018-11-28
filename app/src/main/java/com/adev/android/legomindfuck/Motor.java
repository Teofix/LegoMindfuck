package com.adev.android.legomindfuck;

import android.util.Log;

public class Motor {

    private int mCategory;
    private int mDegrees;

    private static String sPrefix = "#ae";
    private static int sEncoding = 110;

    public Motor(int type, int degrees) {
        mCategory = type;
        mDegrees = degrees;
    }

    public String move(int degrees, int speed, String direction) {
        if (direction.equals("+")) {
            mDegrees = (mDegrees + degrees) % 360;
        } else {
            mDegrees = (mDegrees - degrees + 360) % 360;
        }
        //Log.i("Message", sPrefix + mCategory + direction + (sEncoding + degrees) + (sEncoding + speed) + "#");
        return sPrefix + mCategory + direction + (sEncoding + degrees) + (sEncoding + speed) + "#";
    }


    // Getters and Setters

    public int getCategory() {
        return mCategory;
    }

    public int getDegrees() {
        return mDegrees;
    }

    public void setCategort(int category) {
        mCategory = category;
    }

    public void setDegrees(int degrees) {
        mDegrees = degrees;
    }
}
