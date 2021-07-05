package com.hacademy.macro;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.tulskiy.keymaster.common.Provider;

public class Test06KeyboardHook {
	public static void main(String[] args) {
		Provider provider = Provider.getCurrentProvider(false);
		provider.register(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), e->{
			System.out.println("close operation");
			provider.close();
		});
	}
}
