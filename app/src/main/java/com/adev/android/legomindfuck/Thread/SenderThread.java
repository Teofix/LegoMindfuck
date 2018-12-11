package com.adev.android.legomindfuck.Thread;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static com.adev.android.legomindfuck.Thread.SocketManager.ev3Socket;
import static com.adev.android.legomindfuck.Thread.SocketManager.isReady;
import static com.adev.android.legomindfuck.Thread.SocketManager.accessSender;

public class SenderThread extends Thread {

    private String message;

    public SenderThread(String message) {
        super();
        this.message = message;
    }

    @Override
    public void run() {
        send();
    }

    private void send() {
        synchronized (accessSender) {
            while (!isReady) {
                try {
                    accessSender.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            PrintWriter outToServer = null;
            try {
                outToServer = new PrintWriter(new OutputStreamWriter(ev3Socket.getOutputStream()));
                outToServer.print(message);
                outToServer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i("SenderThread", "sent: " + message);
        }
    }

}
