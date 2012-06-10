package com.mro.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mro.android.R;
import com.mro.bluetooth.BluetoothClient;
import com.mro.bluetooth.BluetoothConnection;
import com.mro.bluetooth.BluetoothServer;
import com.mro.bluetooth.BluetoothUtil;
import com.mro.receivers.ConnectedBroadcastReceiver;
import com.mro.util.AndroidRobotData;

public class AndroidRobotActivity extends Activity {

	private final static String TAG = AndroidRobotActivity.class
			.getSimpleName();

	private AndroidRobotData androidRobotData;
	private TextView messageField;
	private TextView deviceName;
	private Button sendButton;
	private Button serverButton;
	private Button clientButton;
	private Button resetButton;
	private BroadcastReceiver clientReceiver;
	private CheckBox connectionCheckBox;

	private ConnectedBroadcastReceiver connectedReceiver;
	private BroadcastReceiver disconnectedReceiver;

	private boolean clientIsRegistered;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		messageField = (TextView) findViewById(R.id.message_field);
		deviceName = (TextView) findViewById(R.id.device_name);
		sendButton = (Button) findViewById(R.id.send_button);
		serverButton = (Button) findViewById(R.id.server_button);
		clientButton = (Button) findViewById(R.id.client_button);
		resetButton = (Button) findViewById(R.id.reset_button);
		connectionCheckBox = (CheckBox) findViewById(R.id.connection_check_box);

		connectedReceiver = new ConnectedBroadcastReceiver(this, new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				connectionCheckBox.setChecked(true);

				ArrayList<BluetoothDevice> connectedDevices = new ArrayList<BluetoothDevice>(
						AndroidRobotData.bluetoothAdapter.getBondedDevices());

				// Do something useful here
				deviceName.setText("Remote: "
						+ connectedDevices.get(0).getName());
			}
		});

		IntentFilter disconnectedFilter = new IntentFilter();
		disconnectedFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);

		disconnectedReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				Log.d(TAG, "Received disconnected event");
				connectionCheckBox.setChecked(false);
			}
		};

		androidRobotData = (AndroidRobotData) getApplicationContext();

		BluetoothUtil.enableBlueTooth(this);

		clientIsRegistered = false;

		sendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				BluetoothConnection btConnection = androidRobotData.btConnection;

				if (btConnection != null) {
					String text = messageField.getText().toString();

					if (!text.equals("")) {
						btConnection.write(text);
					}
				}
			}
		});

		serverButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Make sure we don't try to be a client
				clientButton.setClickable(false);
				clientButton.setText("Disabled!");

				// Only do this if we haven't set up a connection yet
				if (androidRobotData.btConnection == null) {

					Intent discoverableIntent = new Intent(
							BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
					discoverableIntent.putExtra(
							BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
					startActivity(discoverableIntent);

					BluetoothConnection btConnection = new BluetoothConnection(
							new Handler() {
								public void handleMessage(Message msg) {
									String str = (String) msg.obj;
									messageField.setText(str);
								}
							});

					androidRobotData.btConnection = btConnection;

					BluetoothServer btServer = new BluetoothServer(
							androidRobotData.bluetoothAdapter, "Server",
							androidRobotData.serverUUID, btConnection);

					androidRobotData.server = btServer;

					btServer.start();
					btConnection.start();
				}
			}
		});

		clientButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Make sure we don't try to be a client
				serverButton.setClickable(false);
				serverButton.setText("Disabled!");

				if (androidRobotData.btConnection == null) {

					IntentFilter filter = new IntentFilter(
							BluetoothDevice.ACTION_FOUND);

					clientReceiver = new BroadcastReceiver() {
						public void onReceive(Context context, Intent intent) {
							String action = intent.getAction();

							if (androidRobotData.client != null) {
								androidRobotData.client.stopClient();
								androidRobotData.client.stop();
							}

							BluetoothDevice device = null;

							// When discovery finds a device
							if (BluetoothDevice.ACTION_FOUND.equals(action)) {
								device = intent
										.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
							}

							BluetoothConnection btConnection = new BluetoothConnection(
									new Handler() {
										public void handleMessage(Message msg) {
											String str = (String) msg.obj;
											messageField.setText(str);
										}
									});

							androidRobotData.btConnection = btConnection;

							BluetoothClient btClient = new BluetoothClient(
									androidRobotData.bluetoothAdapter, device,
									androidRobotData.serverUUID, btConnection);

							androidRobotData.client = btClient;

							btClient.start();
							btConnection.start();
						}
					};

					if (!clientIsRegistered) {
						registerReceiver(clientReceiver, filter);
						clientIsRegistered = true;
					}
				}

				AndroidRobotData.bluetoothAdapter.startDiscovery();
			}
		});

		resetButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				deviceName.setText("Remote: ");
				connectionCheckBox.setChecked(false);

				if (androidRobotData.btConnection != null) {
					androidRobotData.btConnection.stop();
				}

				if (androidRobotData.server != null) {
					androidRobotData.server.stopServer();
					androidRobotData.server.stop();
				}

				if (androidRobotData.client != null) {
					androidRobotData.client.stopClient();
					androidRobotData.client.stop();
				}

				androidRobotData.btConnection = null;
				androidRobotData.server = null;
				androidRobotData.client = null;

				serverButton.setClickable(true);
				serverButton.setText("Become server");

				clientButton.setClickable(true);
				clientButton.setText("Become client");
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (clientIsRegistered) {
			unregisterReceiver(clientReceiver);
			clientIsRegistered = false;
		}

		unregisterReceiver(connectedReceiver);
	}
}
