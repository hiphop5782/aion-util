package com.hacademy.macro.key;

import java.awt.event.KeyEvent;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hacademy.macro.exception.KeyException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@JsonIgnoreProperties
public class KeyJson {
	private String key;
	private double delay;
	
	public Key getKey() throws KeyException{
		Key k = Key.builder().build();
		if(key != null) {
			Scanner sc = new Scanner(key.toLowerCase());
			sc.useDelimiter("[\\s+]");
			while(sc.hasNext()) {
				String token = sc.next().trim();
				switch(token) {
				case "shift": 
					k.setShift(true); break;
				case "control": case "ctrl": 
					k.setControl(true); break;
				case "alt": 
					k.setAlt(true); break;
				default:
					if(token.length() != 1) throw new KeyException(token);
					char ch = token.charAt(0);
					k.setKeyCode(KeyEvent.getExtendedKeyCodeForChar(ch));
				}
			}
			sc.close();
		}
		else {
			k.setDelay((int)(delay*1000));
		}
		return k; 
	}
}
