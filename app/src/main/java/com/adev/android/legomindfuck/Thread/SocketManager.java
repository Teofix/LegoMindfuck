package com.adev.android.legomindfuck.Thread;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

public class SocketManager {

    static Socket ev3Socket;
    static boolean isReady = false;
    static String ip;
    static final Object accessSender = new Object();

    public void openSocket() {
        OpenerThread open = new OpenerThread();
        open.start();
        ReceiverThread rec = new ReceiverThread();
        rec.start();
    }

    public void closeSocket() {
        CloserThread close = new CloserThread();
        close.start();
    }

    public void sendMessage(String message) {
        SenderThread s = new SenderThread(message);
        s.start();
    }

    public void setIp(String mIp)   {
        ip = mIp;
    }

}
