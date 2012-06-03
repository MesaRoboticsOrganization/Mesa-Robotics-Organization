package com.mro.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mro.util.AndroidRobotData;

public class BluetoothUtil {
	public final static String TAG = BluetoothUtil.class.getSimpleName();

	public static void enableBlueTooth(Context context) {

		BluetoothAdapter bluetoothAdapter = AndroidRobotData.bluetoothAdapter;

		if (bluetoothAdapter == null) {
			Log.d("ERROR", "Bluetooth not available!!");
		}

		int REQUEST_ENABLE_BT = 1;

		if (!bluetoothAdapter.isEnabled()) {
			Intent enableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			((Activity) context).startActivityForResult(enableIntent,
					REQUEST_ENABLE_BT);
		}
	}
}
