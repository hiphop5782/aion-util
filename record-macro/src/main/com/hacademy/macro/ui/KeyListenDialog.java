package com.hacademy.macro.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.KeyStroke;

public class KeyListenDialog extends JDialog{
	private KeyStroke key;
	private KeyListenDialog() {
		setTitle("Change Macro Key");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				key = KeyStroke.getKeyStroke(e.getKeyCode(), e.getModifiersEx());
				System.out.println(key);
			}
		});
		setSize(300, 300);
	}
	
	private static KeyListenDialog dialog = new KeyListenDialog();
	public static KeyStroke open() {
		dialog.setVisible(true);
		return dialog.key;
	}
}
