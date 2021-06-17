package com.hacademy.macro;

import java.awt.Robot;

public class RobotManager {
	private static Robot robot;
	static {
		try {
			robot = new Robot();
		}
		catch(Exception e) {}
	}
	
	public static Robot getInstance() {
		return robot;
	}
}
