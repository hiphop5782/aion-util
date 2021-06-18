package com.hacademy.macro.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import com.hacademy.macro.key.KeyHookProc;

public class FireButton extends JButton{
	private boolean ing = false;
	private Color color1 = new Color(255, 255, 255);
	private Color color2 = new Color(0, 0, 0);
	public FireButton() {
		super("실행");
		setFont(new Font("", Font.BOLD, 30));
		setStatus(false);
	}
	public boolean isPlaying() {
		return ing;
	}
	public void setStatus(boolean status) {
		ing = status;
		setText(ing?"중지":"실행");
		setBackground(ing?color2:color1);
		setForeground(ing?color1:color2);
	}
}
