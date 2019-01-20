package com.adev.android.legomindfuck;

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
        if(mCategory == 7) speed *= 2;
        if (direction.equals("+")) {
            mDegrees = (mDegrees + degrees) % 360;
        } else {
            mDegrees = (mDegrees - degrees + 360) % 360;
        }
        return sPrefix + mCategory + direction + (sEncoding + degrees) +  (sEncoding + speed) + "#";
    }

    public String motorOn(int speed, String direction) {
        if(mCategory == 7) speed *= 2;
        return sPrefix + (mCategory + 1) + direction + "000" + (sEncoding + speed) + "#";
    }

    public String motorOff() {
        return sPrefix + (mCategory + 2) + "+" + "000" + "000" + "#";
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
