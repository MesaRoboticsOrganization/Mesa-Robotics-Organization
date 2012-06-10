package com.mro.receivers;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;

import com.mro.bluetooth.BluetoothClient;
import com.mro.util.AndroidRobotData;

public class FoundDeviceReceiver extends BroadcastReceiver {

	private final static String TAG = FoundDeviceReceiver.class.getSimpleName();

	private AndroidRobotData androidRobotData;
	private Handler handler;

	public FoundDeviceReceiver(Context context, Handler handler) {
		this.handler = handler;

		androidRobotData = (AndroidRobotData) context.getApplicationContext();

		IntentFilter connectedFilter = new IntentFilter();
		connectedFilter.addAction(BluetoothDevice.ACTION_FOUND);
		context.registerReceiver(this, connectedFilter);
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		Log.d(TAG, "Received found device event!");

		handler.sendEmptyMessage(0);

		String action = intent.getAction();

		// Stop whatever connection we had before
		if (androidRobotData.client != null) {
			androidRobotData.client.stopClient();
			androidRobotData.client.stop();
		}

		BluetoothDevice device = null;

		// When discovery finds a device
		if (BluetoothDevice.ACTION_FOUND.equals(action)) {
			device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		}

		// Create the Bluetooth client thread
		BluetoothClient btClient = new BluetoothClient(
				AndroidRobotData.bluetoothAdapter, device,
				AndroidRobotData.serverUUID, androidRobotData.btConnection);

		androidRobotData.client = btClient;

		// Start the client thread to try to create a connection to the sever
		btClient.start();

		// Start the Bluetooth connection thread to attempt to create a socket
		// to be able to do read / write
		androidRobotData.btConnection.start();
	}
}
