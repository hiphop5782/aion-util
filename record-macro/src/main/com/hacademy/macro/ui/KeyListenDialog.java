package com.hacademy.macro.ui;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

import com.hacademy.macro.key.KeyProperties;

public class KeyListenDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	private KeyStroke key;
	
	private JLabel label = new JLabel("", JLabel.CENTER);
	private KeyListenDialog() {
		key = KeyProperties.load();
		setTitle("Change Macro Key");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				key = KeyStroke.getKeyStroke(e.getKeyCode(), e.getModifiersEx());
				label.setText(key.toString());
			}
		});
		setSize(450, 150);
		getContentPane().add(label);
		label.setFont(new Font("", Font.BOLD, 35));
		label.setText(key.toString());
	}
	
	private static KeyListenDialog dialog = new KeyListenDialog();
	public static KeyStroke open() {
		dialog.setVisible(true);
		return dialog.key;
	}
}
