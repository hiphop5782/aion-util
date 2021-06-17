package com.hacademy.macro;

import java.awt.event.KeyEvent;

public class Test02{
	public static void main(String[] args) {
		for(int key = KeyEvent.VK_0; key <= KeyEvent.VK_9; key++) {
			Keybot.action(key);
		}
	}
}
