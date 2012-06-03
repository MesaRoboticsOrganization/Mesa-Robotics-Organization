package com.mro.util;

import java.util.UUID;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.widget.Toast;

import com.mro.bluetooth.BluetoothClient;
import com.mro.bluetooth.BluetoothConnection;
import com.mro.bluetooth.BluetoothServer;

public class AndroidRobotData extends Application {
	public final static String TAG = AndroidRobotData.class.getSimpleName();
	public final static UUID serverUUID = UUID
			.fromString("1fcafeb0-ad58-11e1-afa6-0800200c9a66");
//	public final static UUID clientUUID = UUID
//			.fromString("1fcafeb0-ad58-11e1-afa6-0800200c9a66");
	public final static UUID clientUUID = UUID
			.fromString("35c26620-ad5a-11e1-afa6-0800200c9a66");

	public final static BluetoothAdapter bluetoothAdapter = BluetoothAdapter
			.getDefaultAdapter();

	public BluetoothConnection btConnection;
	public BluetoothClient client;
	public BluetoothServer server;

	@Override
	public void onCreate() {
		super.onCreate();

		btConnection = null;
		client = null;
		server = null;

		if (bluetoothAdapter == null) {
			Toast.makeText(this, "Bluetooth is not available!",
					Toast.LENGTH_LONG);
		}
	}
}
