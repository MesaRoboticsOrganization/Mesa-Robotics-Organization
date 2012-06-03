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
import android.util.Log;

public class BluetoothConnection extends Thread {
	public final static String TAG = BluetoothConnection.class.getSimpleName();

	private BufferedReader reader;
	private BufferedWriter writer;
	private BluetoothSocket socket;
	private BluetoothReader btReader;
	private boolean shouldContinue;

	public BluetoothConnection(BluetoothSocket socket, BluetoothReader btReader) {
		this.socket = socket;
		this.btReader = btReader;
		this.shouldContinue = true;

		InputStream inStream = null;
		OutputStream outStream = null;

		try {
			inStream = new BufferedInputStream(socket.getInputStream());
			outStream = new BufferedOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			Log.e(TAG, "Failed to create connection streams!", e);
		}

		reader = new BufferedReader(new InputStreamReader(inStream));
		writer = new BufferedWriter(new OutputStreamWriter(outStream));
	}

	public void run() {
		while (shouldContinue) {
			String lineInput = null;
			try {
				lineInput = reader.readLine();
			} catch (IOException e) {
				Log.e(TAG, "Failed to read line from stream!", e);
				break;
			}

			btReader.handleInput(lineInput);
		}
	}

	public void write(String output) {
		try {
			writer.write(output);

			// Should we try to flush it right away?
			// not really sure here, I would imagine so
			writer.flush();
		} catch (IOException e) {
			Log.e(TAG, "Failed to write to stream!", e);
		}
	}
}
