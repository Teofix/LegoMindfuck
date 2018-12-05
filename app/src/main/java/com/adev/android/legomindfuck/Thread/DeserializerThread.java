package com.adev.android.legomindfuck.Thread;

import android.util.Log;

import static com.adev.android.legomindfuck.Activity.PlayMenuActivity.sColorSensor;

public class DeserializerThread extends Thread {

    String message;

    public DeserializerThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        deserialize();
    }

    private void deserialize() {
        String[] split = message.split("&");

        if (split[0].equals("#r") && split[4].equals("#")) {
            switch (split[1]) {
                case "m":
                    String newPos = split[3];
                    switch (split[2]) {
                        case "1":
                            //aggiornare motore base con newPos
                            break;
                        case "2":
                            //aggiornare motore braccio con newPos
                            break;
                        case "3":
                            //aggiornare motore sollevatore con newPos
                            break;
                        default:
                            break;
                    }
                    break;
                case "s":
                    String info = split[3]; //in base al tipo di sensore devo gestire una info diversa
                    switch (split[2]) {
                        case "1":
                            //sensore tocco -> false non toccato / true toccato
                            break;
                        case "2":
                            //sensore colore -> codifica vedi sotto
                            break;
                        case "3":
                            //sensore ultrasuoni -> distanza in cm
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        } else if(split[0].equals("c")) {
            String light = split[1];
            int lightValue = Integer.parseInt(light);

            sColorSensor.setLight(lightValue);

            String color = split[2];

            Log.i("Color", color + " Light: " + lightValue);

            switch (color) {
                case "Black":
                    sColorSensor.setColor(1);
                    break;
                case "Blue":
                    sColorSensor.setColor(2);
                    break;
                case "Green":
                    sColorSensor.setColor(3);
                    break;
                case "Yellow":
                    sColorSensor.setColor(4);
                    break;
                case "Red":
                    sColorSensor.setColor(5);
                    break;
                case "White":
                    sColorSensor.setColor(6);
                    break;
                case "Brown":
                    sColorSensor.setColor(7);
                    break;
                default:
                    sColorSensor.setColor(0);
                    break;
            }
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
