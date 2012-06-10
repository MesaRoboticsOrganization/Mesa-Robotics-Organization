package com.mro.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mro.android.R;
import com.mro.bluetooth.BluetoothConnection;
import com.mro.bluetooth.BluetoothServer;
import com.mro.bluetooth.BluetoothUtil;
import com.mro.receivers.ConnectedReceiver;
import com.mro.receivers.DisconnectedReceiver;
import com.mro.receivers.FoundDeviceReceiver;
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
	private CheckBox connectionCheckBox;

	private ConnectedReceiver connectedReceiver;
	private FoundDeviceReceiver foundReceiver;
	private DisconnectedReceiver disconnectedReceiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		BluetoothUtil.enableBlueTooth(this);

		messageField = (TextView) findViewById(R.id.message_field);
		deviceName = (TextView) findViewById(R.id.device_name);
		sendButton = (Button) findViewById(R.id.send_button);
		serverButton = (Button) findViewById(R.id.server_button);
		clientButton = (Button) findViewById(R.id.client_button);
		resetButton = (Button) findViewById(R.id.reset_button);
		connectionCheckBox = (CheckBox) findViewById(R.id.connection_check_box);

		connectedReceiver = new ConnectedReceiver(this, new Handler() {

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

		disconnectedReceiver = new DisconnectedReceiver(this, new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				connectionCheckBox.setChecked(false);

				resetViews();
			}
		});

		androidRobotData = (AndroidRobotData) getApplicationContext();

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
				// Only try to setup the entire connection if we do not have a
				// connection already
				if (androidRobotData.btConnection == null) {
					// Make sure we don't try to be a client
					clientButton.setClickable(false);
					clientButton.setText("Disabled!");

					// Allow the device to be discoverable
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

					// Create the server to allow other client(s) to connect to
					BluetoothServer btServer = new BluetoothServer(
							androidRobotData.bluetoothAdapter, "Server",
							androidRobotData.serverUUID, btConnection);

					androidRobotData.server = btServer;

					// Start the server right away to allow other client(s) to
					// try to connect
					btServer.start();
					btConnection.start();
				}
			}
		});

		clientButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Only try to setup the entire connection if we do not have a
				// connection already
				if (androidRobotData.btConnection == null) {
					// Make sure we don't try to be a client
					serverButton.setClickable(false);
					serverButton.setText("Disabled!");

					final BluetoothConnection btConnection = new BluetoothConnection(
							new Handler() {
								public void handleMessage(Message msg) {
									String str = (String) msg.obj;
									messageField.setText(str);
								}
							});

					if (androidRobotData.btConnection != null) {
						androidRobotData.btConnection.stopConnection();
					}

					androidRobotData.btConnection = btConnection;

					if (foundReceiver != null) {
						unregisterReceiver(foundReceiver);
					}

					foundReceiver = new FoundDeviceReceiver(
							AndroidRobotActivity.this, new Handler());

					AndroidRobotData.bluetoothAdapter.startDiscovery();
				}
			}
		});

		resetButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				deviceName.setText("Remote: ");
				connectionCheckBox.setChecked(false);

				androidRobotData.reset();
				resetViews();
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

		if (foundReceiver != null) {
			unregisterReceiver(foundReceiver);
		}

		if (connectedReceiver != null) {
			unregisterReceiver(connectedReceiver);
		}

		if (disconnectedReceiver != null) {
			unregisterReceiver(disconnectedReceiver);
		}
	}

	private void resetViews() {
		// Reset the views

		deviceName.setText("Remote: ");
		messageField.setText("");

		clientButton.setClickable(true);
		clientButton.setText("Become client");

		serverButton.setClickable(true);
		serverButton.setText("Become server");
	}
}
