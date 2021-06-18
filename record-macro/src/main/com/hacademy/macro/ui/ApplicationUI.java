package com.hacademy.macro.ui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacademy.macro.key.Key;
import com.hacademy.macro.key.KeyHookProc;
import com.hacademy.macro.key.KeyJson;
import com.hacademy.macro.key.Keybot;

public class ApplicationUI extends JFrame{
	
	private FireButton button = new FireButton();
	private JMenuBar bar = new JMenuBar();
	private JMenu menu = new JMenu("메뉴");
	private JMenuItem open = new JMenuItem("매크로 열기");
	private JMenuItem exit = new JMenuItem("종료");
	
	private List<Key> macro;
	
	public ApplicationUI() {
		setLocationByPlatform(true);
		setAlwaysOnTop(true);
		setUndecorated(true);
		display();
		event();
		menu();
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	private void display() {
		add(button);
		button.setPreferredSize(new Dimension(200, 120));
	}
	private void event() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		open.addActionListener(e->{
			loadMacroFile();
		});
		exit.addActionListener(e->{
			exit();
		});
		
		button.addActionListener(e->{
			if(!button.isPlaying()) {
				if(macro == null || macro.isEmpty()) {
					loadMacroFile();
				}
				KeyHookProc.start(this);
				button.setStatus(true);
			}
			else {
				KeyHookProc.stop();
				button.setStatus(false);
			}
		});
	}
	private void menu() {
		setJMenuBar(bar);
		bar.add(menu);
		menu.add(open);
		menu.add(exit);
	}
	
	private void loadMacroFile() {
		JFileChooser chooser = new JFileChooser();
		int choice = chooser.showOpenDialog(getContentPane());
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
	}

	public void fireEvent() {
		if(macro == null || macro.isEmpty()) return;
		Keybot.action(macro);
	}
	
	public void exit() {
		KeyHookProc.stop();
		dispose();
		System.exit(0);
	}
}
