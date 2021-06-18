package com.hacademy.macro;

import javax.swing.UIManager;

import com.hacademy.macro.ui.ApplicationUI;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;

public class Test07 {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new McWinLookAndFeel());
		}
		catch(Exception e) {}
		ApplicationUI ui = new ApplicationUI();
	}
}
