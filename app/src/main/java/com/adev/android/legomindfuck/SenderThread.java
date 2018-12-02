package com.adev.android.legomindfuck;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static com.adev.android.legomindfuck.SocketManager.ev3Socket;
import static com.adev.android.legomindfuck.SocketManager.isReady;
import static com.adev.android.legomindfuck.SocketManager.accessSender;

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
            } catch (IOException e) {
                e.printStackTrace();
            }
            outToServer.print(message);
            outToServer.flush();
            Log.i("Sender", "messaggio inviato: " + message);
        }
    }

}
