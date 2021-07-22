package com.hacademy.macro;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacademy.macro.key.Key;
import com.hacademy.macro.key.KeyHookProc;
import com.hacademy.macro.key.KeyJson;
import com.hacademy.macro.key.Keybot;

public class Test08 {
	public static List<Key> loadMacroFile() {
		File json = new File(System.getProperty("user.dir"), "test-macro.json");
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			KeyJson[] jsonArray = mapper.readValue(json, KeyJson[].class);
			List<Key> macro = new ArrayList<>();
			
			for(KeyJson kjs : jsonArray) {
				macro.add(kjs.getKey());
			}
			
			return macro;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		List<Key> macro = loadMacroFile();
		Keybot.action(macro);
	}
}
