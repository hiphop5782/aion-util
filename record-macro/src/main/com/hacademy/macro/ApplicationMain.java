package com.hacademy.macro;

import javax.swing.UIManager;

import com.hacademy.macro.key.KeyHookProc;
import com.hacademy.macro.ui.TrayUI;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;

public class ApplicationMain {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new McWinLookAndFeel());
			Class.forName("com.hacademy.macro.key.KeyHookProc");
//			ApplicationUI ui = new ApplicationUI();
			TrayUI ui = new TrayUI();
			ui.start();
		}
		catch(Exception e) {
			e.printStackTrace();
			KeyHookProc.terminate();
			System.exit(-1);
		}
	}
}
