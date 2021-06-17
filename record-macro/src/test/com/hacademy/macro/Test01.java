package com.hacademy.macro;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Test01 {
	public static void main(String[] args) throws Exception{
		Robot r = new Robot();
		for(int key = KeyEvent.VK_0; key <= KeyEvent.VK_9; key++) {
			r.keyPress(key);
			r.keyRelease(key);
		}
	}
}
