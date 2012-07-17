package com.mro.commands;

import java.io.Serializable;

//The most low-level command, just gets the pwm output for the left and the right motors
public interface PWMCommand extends Serializable {
	public Integer getPWMRight();

	public Integer getPWMLeft();
}
