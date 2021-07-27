package com.hacademy.macro.util;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class WindowUtility {
	public static String getActiveWindowTitle() {
		HWND hwnd = User32.INSTANCE.GetForegroundWindow();
		int nChar = 256;
		char[] lpString = new char[nChar];
		int size = User32.INSTANCE.GetWindowText(hwnd, lpString, nChar);
		return new String(lpString, 0, size);
	}
}
