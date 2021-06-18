package com.hacademy.macro;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class Test06KeyboardHook {
	public static void main(String[] args) {
		GlobalKeyboardHook hook = new GlobalKeyboardHook(true);
		hook.addKeyListener(new GlobalKeyAdapter() {
			@Override
			public void keyReleased(GlobalKeyEvent event) {
				System.out.println(event.getVirtualKeyCode());
				switch(event.getVirtualKeyCode()) {
				case GlobalKeyEvent.VK_ESCAPE: hook.shutdownHook();
				}
			}
		});
	}
}
