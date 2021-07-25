package com.hacademy.macro.ui;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.KeyStroke;

import com.hacademy.macro.ApplicationMain;
import com.hacademy.macro.key.KeyHookProc;

public class TrayUI {
	
	private PopupMenu popup = new PopupMenu();
	
	private MenuItem info = new MenuItem("Developer");
	private MenuItem key = new MenuItem("Change key");
	private MenuItem begin = new MenuItem("Macro start");
	private MenuItem end = new MenuItem("Macro finish");
	private MenuItem exit = new MenuItem("Exit");
	
	public void start() throws AWTException, IOException{
		InputStream in = ApplicationMain.class.getResourceAsStream("resource/icon.png");
		BufferedImage img = ImageIO.read(in);
		TrayIcon trayIcon = new TrayIcon(img);
		trayIcon.setToolTip("aion macro");
		
		prepareMenu();
		
		trayIcon.setPopupMenu(popup);
		KeyHookProc.start();
		
		SystemTray.getSystemTray().add(trayIcon);
	}
	
	private void prepareMenu() {
		popup.add(info);
		popup.addSeparator();
		popup.add(key);
		popup.addSeparator();
		popup.add(begin);
		popup.add(end);
		popup.addSeparator();
		popup.add(exit);
		
		begin.setEnabled(false);
		
		info.addActionListener(e->{
			try {
				Runtime.getRuntime().exec("cmd /c start https://aion.plaync.com/characters/server/24/id/129742/home");
			}
			catch(Exception ex) {}
		});
		
		key.addActionListener(e->{
			KeyStroke stroke = KeyListenDialog.open();
			System.out.println(stroke);
		});
		
		begin.addActionListener(e->{
			KeyHookProc.start();
			begin.setEnabled(false);
			end.setEnabled(true);
		});
		end.addActionListener(e->{
			KeyHookProc.stop();
			begin.setEnabled(true);
			end.setEnabled(false);
		});
		exit.addActionListener(e->{
			KeyHookProc.terminate();
			System.exit(0);
		});
	}
}
