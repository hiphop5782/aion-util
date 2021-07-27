package com.hacademy.macro.key;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacademy.macro.ui.ApplicationUI;
import com.hacademy.macro.util.WindowUtility;
import com.tulskiy.keymaster.common.Provider;

public class KeyHookProc {
	
	private static Provider provider = Provider.getCurrentProvider(false);
	private static List<Key> macro;
	static {
		macro = MacroProperties.load();
	}
	
	public static void refresh() {
		stop();
		macro = MacroProperties.reload();
		if(macro == null) {
			JOptionPane.showMessageDialog(null, "매크로 파일을 선택해야 실행할 수 있습니다");
			System.exit(-1);
		}
		start();
	}
	
	private static KeyStroke trigger = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
	
	public static void start(ApplicationUI ui) {
		trigger = KeyProperties.load();
		provider.register(trigger, e->{
			ui.fireEvent();
		});
	}
	public static void start() {
		trigger = KeyProperties.load();
		provider.register(trigger, e->{
			//아이온이 실행중일 경우가 아니면 차단
			String title = WindowUtility.getActiveWindowTitle();
			if(!title.startsWith("AION Client")) return;
			if(macro == null || macro.isEmpty()) return;
			Keybot.action(macro);
		});
	}
	public static void stop() {
		provider.unregister(trigger);
	}
	
	public static void terminate() {
		provider.close();
	}
}
