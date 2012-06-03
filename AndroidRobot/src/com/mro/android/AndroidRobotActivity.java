package com.mro.android;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.mro.bluetooth.BluetoothUtil;
import com.mro.util.AndroidRobotData;

public class AndroidRobotActivity extends Activity {

	private BluetoothAdapter bluetoothAdapter;
	private AndroidRobotData androidRobotData;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		androidRobotData = (AndroidRobotData) getApplicationContext();
		bluetoothAdapter = androidRobotData.bluetoothAdapter;

		BluetoothUtil.enableBlueTooth(this);

		Button reconnectButton = (Button) findViewById(R.id.reconnect_button);

		reconnectButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				reconnect();
			}
		});
	}

	private void reconnect() {
		BroadcastReceiver btReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				if (BluetoothDevice.ACTION_FOUND.equals(action)) {
					BluetoothDevice device = intent
							.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

					TextView deviceNameText = (TextView) findViewById(R.id.device_name);

					deviceNameText.setText(device.getName() + "\n"
							+ device.getAddress());

					Log.d("Bluetooth",
							"Getting device name:" + device.getName());
				}
			}
		};

		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		registerReceiver(btReceiver, filter);

		Intent discoverableIntent = new Intent(
				BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		discoverableIntent.putExtra(
				BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
		startActivity(discoverableIntent);

		if (bluetoothAdapter.startDiscovery()) {
			Log.d("Bluetooth", "DEVICE FOUND!");
		}
	}
}
