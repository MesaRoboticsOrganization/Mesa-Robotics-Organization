package com.mro.bluetooth;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class BluetoothClient extends Thread {

	public final static String TAG = BluetoothClient.class.getSimpleName();

	private BluetoothSocket clientSocket;
	private BluetoothHandler handler;
	private BluetoothAdapter adapter;

	public BluetoothClient(BluetoothAdapter adapter, BluetoothDevice device,
			UUID uuid, BluetoothHandler handler) {

		try {
			clientSocket = device.createRfcommSocketToServiceRecord(uuid);
		} catch (IOException e) {
			Log.d(TAG, "Failed to create socket", e);
		}
	}

	public void run() {
		// Make sure we cancel whatever discovery might be going on before
		// trying to connect because discovery is expensive on the phone
		adapter.cancelDiscovery();

		try {
			clientSocket.connect();
		} catch (IOException e) {
			Log.e(TAG, "Could not connect to a server", e);

			try {
				clientSocket.close();
			} catch (IOException e1) {
				Log.e(TAG, "", e1);
			}
		}

		handler.handleSocket(clientSocket);
	}

	public void cancelClient() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			Log.e(TAG, "", e);
		}
	}
}
