package com.adev.android.legomindfuQ.Thread;

import static com.adev.android.legomindfuQ.Activity.MainMenuActivity.colors;

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

        String[] split = message.split("&");

        if (split[0].equals("#r") && split[1].equals("t") && split[6].equals("#")) {

            for (int i = 2; i < 6; i++) {
                colors.setColorTower(split[i], i-2);
            }
        }

        else if (split[0].equals("#r") && split[1].equals("c") && split[3].equals("#")) {
            colors.setColorChecked(split[2]);
        }
    }

}