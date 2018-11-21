package com.adev.android.legomindfuck;

import android.util.Log;
import java.net.Socket;

public class SocketManager {

    static Socket ev3Socket;
    static boolean isReady;

    public void openSocket() {
        OpenerThread open = new OpenerThread();
        open.start();
    }

    public void sendMessage(String message) {
        Log.i("SocketManager: ", "a thread is taking: " + message);
        SenderThread s = new SenderThread(message);
        s.start();
    }

}
