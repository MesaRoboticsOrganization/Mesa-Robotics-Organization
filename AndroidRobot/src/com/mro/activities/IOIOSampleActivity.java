package com.mro.activities;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.mro.android.R;

public class IOIOSampleActivity extends IOIOActivity {

	private static final String TAG = IOIOSampleActivity.class.getSimpleName();

	private ToggleButton button_;
	private SeekBar seekBar1;
	private SeekBar seekBar2;
	private SeekBar seekBar3;
	private SeekBar seekBar4;
	private TextView pwmText1;
	private TextView pwmText2;
	private TextView pwmText3;
	private TextView pwmText4;

	/**
	 * Called when the activity is first created. Here we normally initialize
	 * our GUI.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button_ = (ToggleButton) findViewById(R.id.button);
		seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
		seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
		seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
		seekBar4 = (SeekBar) findViewById(R.id.seekBar4);

		seekBar1.setProgress(500);
		seekBar2.setProgress(500);
		seekBar3.setProgress(500);
		seekBar4.setProgress(500);

		pwmText1 = (TextView) findViewById(R.id.pwmText1);
		pwmText2 = (TextView) findViewById(R.id.pwmText2);
		pwmText3 = (TextView) findViewById(R.id.pwmText3);
		pwmText4 = (TextView) findViewById(R.id.pwmText4);

		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onProgressChanged(SeekBar arg0, int value, boolean arg2) {
				pwmText1.setText("Pulse Width 1: " + (1000 + value));
			}
		});

		seekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onProgressChanged(SeekBar arg0, int value, boolean arg2) {
				pwmText2.setText("Pulse Width 2: " + (1000 + value));
			}
		});

		seekBar3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onProgressChanged(SeekBar arg0, int value, boolean arg2) {
				pwmText3.setText("Pulse Width 3: " + (1000 + value));
			}
		});

		seekBar4.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onProgressChanged(SeekBar arg0, int value, boolean arg2) {
				pwmText4.setText("Pulse Width 4: " + (1000 + value));
			}
		});
	}

	/**
	 * This is the thread on which all the IOIO activity happens. It will be run
	 * every time the application is resumed and aborted when it is paused. The
	 * method setup() will be called right after a connection with the IOIO has
	 * been established (which might happen several times!). Then, loop() will
	 * be called repetitively until the IOIO gets disconnected.
	 */
	class Looper extends BaseIOIOLooper {
		/** The on-board LED. */
		private DigitalOutput led_;
		private PwmOutput pwmOutput1;
		private PwmOutput pwmOutput2;
		private PwmOutput pwmOutput3;
		private PwmOutput pwmOutput4;

		/**
		 * Called every time a connection with IOIO has been established.
		 * Typically used to open pins.
		 * 
		 * @throws ConnectionLostException
		 *             When IOIO connection is lost.
		 * @see ioio.lib.util.AbstractIOIOActivity.IOIOThread#setup()
		 */
		@Override
		protected void setup() throws ConnectionLostException {
			led_ = ioio_.openDigitalOutput(IOIO.LED_PIN, true);
			pwmOutput1 = ioio_.openPwmOutput(10, 100);
			pwmOutput2 = ioio_.openPwmOutput(11, 100);
			pwmOutput3 = ioio_.openPwmOutput(12, 100);
			pwmOutput4 = ioio_.openPwmOutput(13, 100);
		}

		/**
		 * Called repetitively while the IOIO is connected.
		 * 
		 * @throws ConnectionLostException
		 *             When IOIO connection is lost.
		 * @see ioio.lib.util.AbstractIOIOActivity.IOIOThread#loop()
		 */
		@Override
		public void loop() throws ConnectionLostException {
			led_.write(!button_.isChecked());

			// pwmOutput1.setPulseWidth(1200);
			// pwmOutput2.setPulseWidth(1200);
			// pwmOutput3.setPulseWidth(1200);
			// pwmOutput4.setPulseWidth(1200);

			int pulseWidth1 = 1000 + seekBar1.getProgress();
			int pulseWidth2 = 1000 + seekBar2.getProgress();
			int pulseWidth3 = 1000 + seekBar3.getProgress();
			int pulseWidth4 = 1000 + seekBar4.getProgress();

			pwmOutput1.setPulseWidth(pulseWidth1);
			pwmOutput2.setPulseWidth(pulseWidth2);
			pwmOutput3.setPulseWidth(pulseWidth3);
			pwmOutput4.setPulseWidth(pulseWidth4);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * A method to create our IOIO thread.
	 * 
	 * @see ioio.lib.util.AbstractIOIOActivity#createIOIOThread()
	 */
	@Override
	protected IOIOLooper createIOIOLooper() {
		return new Looper();
	}
}
