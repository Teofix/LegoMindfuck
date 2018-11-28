package com.adev.android.legomindfuck;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static com.adev.android.legomindfuck.SocketManager.ev3Socket;
import static com.adev.android.legomindfuck.SocketManager.isReady;

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