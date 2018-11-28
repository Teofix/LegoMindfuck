package com.adev.android.legomindfuck;

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import static com.adev.android.legomindfuck.SocketManager.ev3Socket;
import static com.adev.android.legomindfuck.SocketManager.isReady;

public class OpenerThread extends Thread {

    @Override
    public synchronized void run() {
        SocketAddress sockaddr = null;
        try {
            sockaddr = new InetSocketAddress(InetAddress.getByName("192.168.1.25"), 8888);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ev3Socket = new Socket();
        try {
            ev3Socket.connect(sockaddr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("OpenSocket: ", "Thread has bind socket: " + sockaddr + " : " + ev3Socket.isConnected());
        isReady = true;
        notifyAll();
    }
}

/*
    #: No color.
    COLOR_NOCOLOR = 0

    #: Black color.
    COLOR_BLACK = 1

    #: Blue color.
    COLOR_BLUE = 2

    #: Green color.
    COLOR_GREEN = 3

    #: Yellow color.
    COLOR_YELLOW = 4

    #: Red color.
    COLOR_RED = 5

    #: White color.
    COLOR_WHITE = 6

    #: Brown color.
    COLOR_BROWN = 7
 */
