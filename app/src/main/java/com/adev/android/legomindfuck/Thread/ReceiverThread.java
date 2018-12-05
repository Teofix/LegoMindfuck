package com.adev.android.legomindfuck;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static com.adev.android.legomindfuck.SocketManager.accessSender;
import static com.adev.android.legomindfuck.SocketManager.ev3Socket;
import static com.adev.android.legomindfuck.SocketManager.isReady;

public class ReceiverThread extends Thread {

    public ReceiverThread() {
        super();
    }

    @Override
    public synchronized void run() {
        receive();
    }

    private void receive() {
        while (!isReady) {
            try {
                synchronized (accessSender) {
                    accessSender.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            try {
                //Log.i("Receiver: ", "Thread is ready to receive");
                int read;
                char[] buffer = new char[2048];
                String message;
                BufferedReader in = new BufferedReader(new InputStreamReader(ev3Socket.getInputStream()));

                while ((read = in.read(buffer)) != -1) {
                    message = new String(buffer, 0, read);
                    //Log.i("Receiver", "Thread has read:" + message);
                    DeserializerThread deserializerThread = new DeserializerThread(message);
                    deserializerThread.start();
                }
                //Log.i("Receiver", "Thread has read:" + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
