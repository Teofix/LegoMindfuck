package com.adev.android.legomindfuck.Thread;

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import static com.adev.android.legomindfuck.Thread.SocketManager.accessSender;
import static com.adev.android.legomindfuck.Thread.SocketManager.ev3Socket;
import static com.adev.android.legomindfuck.Thread.SocketManager.ip;
import static com.adev.android.legomindfuck.Thread.SocketManager.isReady;
import static com.adev.android.legomindfuck.Activity.MainMenuActivity.ev3;

public class OpenerThread extends Thread {

    @Override
    public void run() {
        bind();
    }

    private void bind() {
        SocketAddress sockaddr = null;
        try {
            sockaddr = new InetSocketAddress(InetAddress.getByName(ip), 8888);
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
        synchronized (accessSender) {
            accessSender.notifyAll();
        }
    }
}
