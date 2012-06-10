package com.mro.receivers;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;

public class ConnectedBroadcastReceiver extends BroadcastReceiver {

	private Handler handler;

	public ConnectedBroadcastReceiver(Context context, Handler handler) {
		this.handler = handler;

		IntentFilter connectedFilter = new IntentFilter();
		connectedFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
		context.registerReceiver(this, connectedFilter);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Message msg = new Message();
		handler.sendMessage(msg);
	}
}
