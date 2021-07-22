package com.hacademy.macro.ui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.hacademy.macro.ApplicationMain;
import com.hacademy.macro.key.KeyHookProc;

public class TrayUI {
	
	private PopupMenu popup = new PopupMenu();
	
	private MenuItem begin = new MenuItem("macro start");
	private MenuItem end = new MenuItem("macro finish");
	private MenuItem exit = new MenuItem("exit");
	
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
		popup.add(begin);
		popup.add(end);
		popup.add(exit);
		
		begin.setEnabled(false);
		
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
