package com.adev.android.legomindfuck;

import android.util.Log;
import java.net.Socket;

public class SocketManager {

    static Socket ev3Socket;
    static boolean isReady = false;
    static String ip = "192.168.1.27";
    static final Object accessSender = new Object();

    public void openSocket() {
        OpenerThread open = new OpenerThread();
        open.start();
        //ReceiverThread rec = new ReceiverThread();
        //rec.start();
    }

    public void closeSocket() {
        CloserThread close = new CloserThread();
        close.start();
    }

    public void sendMessage(String message) {
        //Log.i("SocketManager: ", "a thread is taking: " + message);
        SenderThread s = new SenderThread(message);
        s.start();
    }

}
