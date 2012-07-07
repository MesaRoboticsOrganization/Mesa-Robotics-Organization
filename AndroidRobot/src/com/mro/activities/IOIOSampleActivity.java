package com.mro.activities;

import android.os.Bundle;
import android.widget.SeekBar;

import com.mro.android.R;

import ioio.lib.api.AnalogInput;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.AbstractIOIOActivity;

public class IOIOSampleActivity extends AbstractIOIOActivity {
    // private TextView textView1_;
    // private TextView textView2_;
    // private TextView textView3_;
    private SeekBar seekBar1_;
    private SeekBar seekBar2_;
    private SeekBar seekBar3_;
    private SeekBar seekBar4_;

    // private SeekBar seekBar4_;
    // private ToggleButton toggleButton1_;
    // private ToggleButton toggleButton2_;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ioio_sample);

        // textView1_ = (TextView) findViewById(R.id.TextView1);
        // textView2_ = (TextView) findViewById(R.id.TextView2);
        // textView3_ = (TextView) findViewById(R.id.TextView3);
        seekBar1_ = (SeekBar) findViewById(R.id.seek_bar1);
        seekBar2_ = (SeekBar) findViewById(R.id.seek_bar2);
        seekBar3_ = (SeekBar) findViewById(R.id.seek_bar3);
        seekBar4_ = (SeekBar) findViewById(R.id.seek_bar4);
        // seekBar4_ = (SeekBar) findViewById(R.id.SeekBar4);
        // toggleButton1_ = (ToggleButton) findViewById(R.id.ToggleButton1);
        // toggleButton2_ = (ToggleButton) findViewById(R.id.ToggleButton2);
        enableUi(false);
    }

    class IOIOThread extends AbstractIOIOActivity.IOIOThread {
        private AnalogInput input1_;
        private AnalogInput input2_;
        private AnalogInput input3_;
        private PwmOutput pwmOutput1_;
        private PwmOutput pwmOutput2_;
        private PwmOutput pwmOutput3_;
        private PwmOutput pwmOutput4_;
        private DigitalOutput led1_;
        private DigitalOutput led2_;

        public void setup() throws ConnectionLostException {
            try {
                input1_ = ioio_.openAnalogInput(40);
                input2_ = ioio_.openAnalogInput(41);
                input3_ = ioio_.openAnalogInput(42);
                pwmOutput1_ = ioio_.openPwmOutput(10, 100);
                pwmOutput2_ = ioio_.openPwmOutput(11, 100);
                pwmOutput3_ = ioio_.openPwmOutput(12, 100);
                pwmOutput4_ = ioio_.openPwmOutput(13, 100);
                led1_ = ioio_.openDigitalOutput(25, false);
                led2_ = ioio_.openDigitalOutput(26, false);

                enableUi(true);
            } catch (ConnectionLostException e) {
                enableUi(false);
                throw e;
            }
        }

        public void loop() throws ConnectionLostException {
            try {
                final float reading1 = input1_.read();
                final float reading2 = input2_.read();
                final float reading3 = input3_.read();
                // setText1(Float.toString(reading1));
                // setText2(Float.toString(reading2));
                // setText3(Float.toString(reading3));

                pwmOutput1_.setPulseWidth(500 + seekBar1_.getProgress() * 2);
                pwmOutput2_.setPulseWidth(500 + seekBar2_.getProgress() * 2);
                pwmOutput3_.setPulseWidth(500 + seekBar3_.getProgress() * 2);
                pwmOutput4_.setPulseWidth(500 + seekBar4_.getProgress() * 2);
                // led1_.write(!toggleButton1_.isChecked());
                // led2_.write(!toggleButton2_.isChecked());
                sleep(10);
            } catch (InterruptedException e) {
                ioio_.disconnect();
            } catch (ConnectionLostException e) {
                enableUi(false);
                throw e;
            }
        }
    }

    @Override
    protected AbstractIOIOActivity.IOIOThread createIOIOThread() {
        return new IOIOThread();
    }

    private void enableUi(final boolean enable) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                seekBar1_.setEnabled(enable);
                seekBar2_.setEnabled(enable);
                seekBar3_.setEnabled(enable);
                seekBar4_.setEnabled(enable);
                // toggleButton1_.setEnabled(enable);
                // toggleButton2_.setEnabled(enable);
            }
        });
    }

    private void setText1(final String str1) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // textView1_.setText(str1);

            }
        });
    }

    private void setText2(final String str2) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // textView2_.setText(str2);

            }
        });
    }

    private void setText3(final String str3) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // textView3_.setText(str3);

            }
        });
    }
}
