package com.adev.android.legomindfuck.Thread;

public class ReceiverThread extends Thread {

    private String message;

    public ReceiverThread(String message) {
        super();
        this.message = message;
    }

    @Override
    public synchronized void run() {

    }

}