package com.hacademy.macro.key;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import com.hacademy.macro.RobotManager;

public class Keybot {

	private static Robot robot = RobotManager.getInstance();
	
	public static void action(int keyCode) {
		robot.keyPress(keyCode);
		robot.keyRelease(keyCode);
	}
	
	public static void action(int keyCode, int duration) {
		robot.keyPress(keyCode);
		robot.delay(duration);
		robot.keyRelease(keyCode);
	}
	
	public static void action(Key[] macro) {
		for(Key key : macro) {
			key.action();
		}
	}
	
	public static void action(KeyJson[] macro) {
		for(KeyJson json : macro) {
			json.getKey().action();
		}
	}
	
	public static void action(List<Key> macro) {
		for(Key key : macro) {
			key.action();
		}
	}
	
}
