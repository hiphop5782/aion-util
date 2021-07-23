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
import com.tulskiy.keymaster.common.Provider;

public class KeyHookProc {
	
	private static Provider provider = Provider.getCurrentProvider(false);
	private static KeyStroke trigger = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
	
	public static void start(ApplicationUI ui) {
		provider.register(trigger, e->{
			ui.fireEvent();
		});
	}
	public static void start() {
		loadMacroFile();
		provider.register(trigger, e->{
			if(macro == null || macro.isEmpty()) return;
			Keybot.action(macro);
		});
	}
	public static void stop() {
		provider.unregister(trigger);
	}
	private static List<Key> macro;
	public static void loadMacroFile() {
		JFileChooser chooser = new JFileChooser();
		int choice = chooser.showOpenDialog(null);
		if(choice == JFileChooser.APPROVE_OPTION) {
			File json = chooser.getSelectedFile();
			ObjectMapper mapper = new ObjectMapper();
			try {
				KeyJson[] jsonArray = mapper.readValue(json, KeyJson[].class);
				if(macro == null) {
					macro = new ArrayList<>();
				}
				else {
					macro.clear();
				}
				
				for(KeyJson kjs : jsonArray) {
					macro.add(kjs.getKey());
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "매크로 파일을 선택해야 실행할 수 있습니다");
			System.exit(-1);
		}
	}
	public static void terminate() {
		provider.close();
	}
}
