package com.adev.android.legomindfuck;

import android.util.Log;

import static com.adev.android.legomindfuck.Activity.PlayMenuActivity.sUltrasonicSensor;

public class UltrasonicSensor {

    private int mDistance = 0;
    public static final Object mutex = new Object();

    public synchronized int getDistance() {
        return mDistance;
    }

    public synchronized void setDistance(int distance) {
        mDistance = distance;
        //Log.i("Distance Set", "" + mDistance);
        sUltrasonicSensor.notify();
        if (mDistance == 18) {
            synchronized (mutex) {
                mutex.notify();
            }
        }
    }
}
