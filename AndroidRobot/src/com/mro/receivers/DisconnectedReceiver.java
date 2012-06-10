package com.mro.receivers;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;

import com.mro.util.AndroidRobotData;

public class DisconnectedReceiver extends BroadcastReceiver {

	private final static String TAG = DisconnectedReceiver.class
			.getSimpleName();

	private Handler handler;
	private AndroidRobotData androidRobotData;

	public DisconnectedReceiver(Context context, Handler handler) {
		this.androidRobotData = (AndroidRobotData) context
				.getApplicationContext();
		this.handler = handler;

		IntentFilter disconnectedFilter = new IntentFilter();
		disconnectedFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
		context.registerReceiver(this, disconnectedFilter);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "Received disconnected event!");

		handler.sendEmptyMessage(0);
		androidRobotData.reset();
	}
}
