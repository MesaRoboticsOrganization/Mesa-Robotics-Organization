package com.mro.commands;

public class PersistentPWMCommand implements PWMCommand {

	private static final long serialVersionUID = -7507631931177287145L;
	private final Integer pwmRight;
	private final Integer pwmLeft;

	public PersistentPWMCommand(Integer pwmRight, Integer pwmLeft) {
		this.pwmRight = pwmRight;
		this.pwmLeft = pwmLeft;
	}

	@Override
	public Integer getPWMRight() {
		return pwmRight;
	}

	@Override
	public Integer getPWMLeft() {
		return pwmLeft;
	}
}
