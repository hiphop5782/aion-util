package com.hacademy.macro;

import java.awt.event.KeyEvent;

public class Test03 {
	public static void main(String[] args) {
		Key[] macro = {
			new Key(KeyEvent.VK_T, true, false, false),
			new Key(KeyEvent.VK_H, true, false, false),
			new Key(KeyEvent.VK_A, true, false, false),
			new Key(KeyEvent.VK_N, true, false, false),
			new Key(KeyEvent.VK_K, true, false, false),
			new Key(KeyEvent.VK_Y, true, false, false),
			new Key(KeyEvent.VK_O, true, false, false),
			new Key(KeyEvent.VK_U, true, false, false),
		};
		Keybot.action(macro);
	}
}
