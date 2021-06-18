package com.hacademy.macro.key;

import com.hacademy.macro.ui.ApplicationUI;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class KeyHookProc {
	private static GlobalKeyboardHook hook;
	public static void start(ApplicationUI ui) {
		if(hook != null) return;
		hook = new GlobalKeyboardHook(true);
		hook.addKeyListener(new GlobalKeyAdapter() {
			@Override
			public void keyReleased(GlobalKeyEvent event) {
				switch(event.getVirtualKeyCode()) {
				case GlobalKeyEvent.VK_F6:
					ui.fireEvent();
				}
			}
		});
	}
	public static void stop() {
		if(hook == null) return;
		hook.shutdownHook();
		hook = null;
	}
}
