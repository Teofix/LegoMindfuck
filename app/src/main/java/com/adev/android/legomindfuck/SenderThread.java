package com.adev.android.legomindfuck;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static com.adev.android.legomindfuck.SocketManager.ev3Socket;
import static com.adev.android.legomindfuck.SocketManager.isReady;

public class SenderThread extends Thread {

    private String message;

    public SenderThread(String message) {
        super();
        this.message = message;
    }

    @Override
    public synchronized void run() {
        Log.i("Sender: ", "Thread is trying to send: " + message);
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        PrintWriter outToServer = null;
        try {
            outToServer = new PrintWriter(new OutputStreamWriter(ev3Socket.getOutputStream()));
            outToServer.print(message);
            outToServer.flush();
            Log.i("Sender: ", "Thread has sent: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        notifyAll();
    }

}
