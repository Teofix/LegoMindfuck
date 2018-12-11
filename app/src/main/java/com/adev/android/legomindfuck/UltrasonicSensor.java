package com.adev.android.legomindfuck;

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
        if (mDistance == 99) {
            synchronized (mutex) {
                mutex.notify();
            }
        }
    }
}
