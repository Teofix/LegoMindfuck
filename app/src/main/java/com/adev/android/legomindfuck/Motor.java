package com.adev.android.legomindfuck;

public class Motor {

    private int mCategory;
    private int mDegrees;

    private static String sPrefix = "";

    public Motor(int type, int degrees) {
        mCategory = type;
        mDegrees = degrees;
    }

    public String move(int degrees, int speed, int direction) {
        if (direction > 0) {
            mDegrees = (mDegrees + degrees) % 360;
        } else {
            mDegrees = (mDegrees - degrees + 360) % 360;
        }

        return sPrefix + degrees + speed + "" + "#";
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
