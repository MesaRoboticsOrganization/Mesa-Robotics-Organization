package com.mro.receivers;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;

public class ConnectedReceiver extends BroadcastReceiver {

	private final static String TAG = ConnectedReceiver.class.getSimpleName();

	private Handler handler;

	public ConnectedReceiver(Context context, Handler handler) {
		this.handler = handler;

		IntentFilter connectedFilter = new IntentFilter();
		connectedFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
		context.registerReceiver(this, connectedFilter);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "Received connected event!");

		handler.sendEmptyMessage(0);
	}
}
