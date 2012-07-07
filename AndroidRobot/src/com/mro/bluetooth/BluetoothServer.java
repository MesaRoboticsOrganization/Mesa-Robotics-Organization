package com.mro.bluetooth;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

/**
 * @author lam. Become server and try to get a connection from a client
 */

public class BluetoothServer extends Thread {

    private final static String TAG = BluetoothServer.class.getSimpleName();

    public final int MILITOSECS = 1000;

    private BluetoothServerSocket serverSocket;
    private BluetoothSocketHandler handler;
    private boolean shouldContinue;

    public BluetoothServer(BluetoothAdapter adapter, String name, UUID uuid,
            BluetoothSocketHandler handler) {
        this.handler = handler;
        this.shouldContinue = true;

        try {
            serverSocket = adapter.listenUsingRfcommWithServiceRecord(name, uuid);
        } catch (IOException e) {
            Log.e(TAG, "", e);
        }
    }

    @Override
    public void run() {
        BluetoothSocket socket = null;

        while (shouldContinue) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                Log.e(TAG, "", e);
                break;
            }

            if (serverSocket != null) {
                handler.handleSocket(socket);
                break;
            }
        }
    }

    public void stopServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "", e);
        }

        shouldContinue = false;
    }
}
