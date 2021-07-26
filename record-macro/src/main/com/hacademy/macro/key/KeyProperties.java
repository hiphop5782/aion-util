package com.hacademy.macro.key;

import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.KeyStroke;

public class KeyProperties {
	private static File propertyFile = new File(System.getProperty("user.dir"), "key.properties");
	
	public static KeyStroke load() {
		try(
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(propertyFile)));
		){
			KeyStroke key = (KeyStroke) in.readObject();
			return key;
		}
		catch(Exception e) {
			return KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
		}
	}
	
	public static void save(KeyStroke key) {
		try(
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(propertyFile)));
		){
			out.writeObject(key);
		}
		catch(Exception e) {}
	}
}
