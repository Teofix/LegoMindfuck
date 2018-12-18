package com.adev.android.legomindfuck.Thread;

import android.util.Log;

import static com.adev.android.legomindfuck.Activity.PlayMenuActivity.sColorSensor;
import static com.adev.android.legomindfuck.Activity.PlayMenuActivity.sUltrasonicSensor;
import static com.adev.android.legomindfuck.Activity.TowerActivity.colorTower;

public class DeserializerThread extends Thread {

    private String message;

    public DeserializerThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        deserialize();
    }

    private void deserialize() {

        Log.i("DeserializerThread:", message);

        String[] split = message.split("&");

        if (split[0].equals("#r") && split[1].equals("t") && split[6].equals("#")) {

            for (int i = 2; i < 6; i++) {
                colorTower[i-2] = split[i];
                Log.i("Color:", split[i]);
            }
        }

        else if (split[0].equals("#r") && split[1].equals("c") && split[3].equals("#")) {
            String color = split[2];
            // mettere il colore nell'imageview del blocco preso e nell'array dei colori presi per fare poi il controllo in motor oppure dove??
        }
    }

}

/*
    #: No color.
    COLOR_NOCOLOR = 0

    #: Black color.
    COLOR_BLACK = 1

    #: Blue color.
    COLOR_BLUE = 2

    #: Green color.
    COLOR_GREEN = 3

    #: Yellow color.
    COLOR_YELLOW = 4

    #: Red color.
    COLOR_RED = 5

    #: White color.
    COLOR_WHITE = 6

    #: Brown color.
    COLOR_BROWN = 7
 */
