package com.mro.bluetooth;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class BluetoothConnection extends Thread implements BluetoothHandler {
	public final static String TAG = BluetoothConnection.class.getSimpleName();

	private BufferedReader reader;
	private BufferedWriter writer;
	private boolean shouldContinue;
	private BluetoothSocket socket;
	private InputStream inStream;
	private OutputStream outStream;
	private Handler handler;

	public BluetoothConnection(BluetoothSocket socket, Handler handler) {
		this.handler = handler;
		this.shouldContinue = true;

		handleSocket(socket);
	}

	public BluetoothConnection(Handler handler) {
		this.handler = handler;
		this.shouldContinue = true;
	}

	@Override
	public void handleSocket(BluetoothSocket socket) {
		Log.d(TAG, "Handling socket!");

		this.socket = socket;
		InputStream inStream = null;
		OutputStream outStream = null;

		try {
			inStream = new BufferedInputStream(socket.getInputStream());
			outStream = new BufferedOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			Log.e(TAG, "Failed to create connection streams!", e);
		}

		try {
			reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer = new BufferedWriter(new OutputStreamWriter(outStream));
	}

	public void run() {
		String line;

		try {
			while (shouldContinue) {

				// NOTE: This will short-circuit if socket is null
				if (socket != null) {
					if ((line = reader.readLine()) != null) {
						handler.obtainMessage(MAX_PRIORITY, line)
								.sendToTarget();
					}
				}

				Thread.sleep(200);
			}
		} catch (IOException e) {
			Log.e(TAG,
					"Failed to read from Bluetooth socket! Have fun debugging!",
					e);
		} catch (InterruptedException e) {
			Log.d(TAG, "I am out of my slumbering sleep!");
		}
	}

	public void write(String output) {
		try {
			writer.write(output + "\n");

			// Should we try to flush it right away?
			// not really sure here, I would imagine so
			writer.flush();
		} catch (IOException e) {
			Log.e(TAG, "Failed to write to stream!", e);
		}
	}

	public void stopConnection() {
		shouldContinue = false;
	}
}
