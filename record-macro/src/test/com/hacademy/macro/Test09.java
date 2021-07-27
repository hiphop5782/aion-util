package com.hacademy.macro;

import java.awt.Window;
import java.util.Arrays;

import javax.swing.FocusManager;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class Test09 {
	public static String getCurrentWindowTitle() {
		HWND hwnd = User32.INSTANCE.GetForegroundWindow();
		
		int nChar = 256;
		char[] lpString = new char[nChar];
		int size = User32.INSTANCE.GetWindowText(hwnd, lpString, nChar);
		
		String title = new String(lpString, 0, size);
		return title;
	}
	
	public static void main(String[] args) throws InterruptedException {
//		Thread.sleep(3000);
		
//		구동 안됨
//		Window[] windows = Window.getWindows();
//		Window[] windows = Window.getOwnerlessWindows();
//		System.out.println(windows.length);
		
//		JNA 사용
//		HWND hwnd = User32.INSTANCE.GetForegroundWindow();
//		System.out.println("hwnd = " + hwnd);
//		
//		int nChar = 256;
//		char[] lpString = new char[nChar];
//		int size = User32.INSTANCE.GetWindowText(hwnd, lpString, nChar);
//		System.out.println("size = " + size);
//		
//		String title = new String(lpString, 0, size);
//		System.out.println(title);
		
		while(true) {
			System.out.println(getCurrentWindowTitle());
			Thread.sleep(500);
		}
	}
}
