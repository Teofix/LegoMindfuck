package com.adev.android.legomindfuck.Thread;

import android.util.Log;

import static com.adev.android.legomindfuck.Activity.MainMenuActivity.colors;

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
                colors.setColorTower(split[i], i-2);
                Log.i("Color:", split[i]);
            }
        }

        else if (split[0].equals("#r") && split[1].equals("c") && split[3].equals("#")) {

            colors.setColorChecked(split[2]);
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
