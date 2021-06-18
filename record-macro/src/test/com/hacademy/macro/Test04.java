package com.hacademy.macro;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Test04 {
	public static void main(String[] args) {
//		String keyText = "shift+alt+v";
		String keyText = "shift alt v";
		Scanner sc = new Scanner(keyText.toLowerCase());
		sc.useDelimiter("[\\s+]");
		while(sc.hasNext()) {
			String token = sc.next();
			switch(token.trim()) {
			case "shift": 
				System.out.println("shift found"); 
				break;
			case "control": case "ctrl": 
				System.out.println("control found");
				break;
			case "alt": 
				System.out.println("alt found");
				break;
			default:
				System.out.println("keyText = " + token);
			}
		}
	}
}
