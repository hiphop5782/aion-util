package com.hacademy.macro.key;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.hacademy.macro.ui.ApplicationUI;
import com.tulskiy.keymaster.common.Provider;

public class KeyHookProc {
	
	private static Provider provider;
	public static void start(ApplicationUI ui) {
		if(provider != null) return;
		provider = Provider.getCurrentProvider(false);
		provider.register(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), e->{
			ui.fireEvent();
		});
	}
	public static void stop() {
		if(provider == null) return;
		provider.close();
		provider = null;
	}
}
