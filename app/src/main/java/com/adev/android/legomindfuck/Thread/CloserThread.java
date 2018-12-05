package com.adev.android.legomindfuck.Thread;

import java.io.IOException;

import static com.adev.android.legomindfuck.Thread.SocketManager.ev3Socket;
import static com.adev.android.legomindfuck.Thread.SocketManager.isReady;

public class CloserThread extends Thread {

    @Override
    public void run() {
        try {
            detach();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void detach() throws IOException {
        ev3Socket.close();
        isReady = false;
    }
}
