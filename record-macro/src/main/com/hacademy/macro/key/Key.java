package com.hacademy.macro.key;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.hacademy.macro.RobotManager;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = {"r"})
public class Key {
	@Setter @Getter		int keyCode, delay;
	@Setter @Getter		boolean shift, control, alt;
	Robot r = RobotManager.getInstance();

	public Key(int keyCode) {
		this(keyCode, false, false, false, 0);
	}

	public Key(int keyCode, int delay) {
		this(keyCode, false, false, false, delay);
	}

	public Key(int keyCode, boolean shift, boolean control, boolean alt) {
		this(keyCode, shift, control, alt, 0);
	}

	@Builder
	public Key(int keyCode, boolean shift, boolean control, boolean alt, int delay) {
		this.keyCode = keyCode;
		this.shift = shift;
		this.control = control;
		this.alt = alt;
		this.delay = delay;
	}

	public boolean hasDelay() {
		return delay > 0;
	}

	public void action() {
		if (shift)
			r.keyPress(KeyEvent.VK_SHIFT);
		if (control)
			r.keyPress(KeyEvent.VK_CONTROL);
		if (alt)
			r.keyPress(KeyEvent.VK_ALT);

		if(keyCode > 0)
			r.keyPress(keyCode);

		if (delay > 0) {
			r.delay(delay);
		}

		if(keyCode > 0)
			r.keyRelease(keyCode);

		if (shift)
			r.keyRelease(KeyEvent.VK_SHIFT);
		if (control)
			r.keyRelease(KeyEvent.VK_CONTROL);
		if (alt)
			r.keyRelease(KeyEvent.VK_ALT);
	}

}