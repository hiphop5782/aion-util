package com.hacademy.macro.key;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MacroProperties {
	private static final File macroFile = new File(System.getProperty("user.dir"), "macro.properties");
	
	public static List<Key> reload(){
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		int choice = chooser.showOpenDialog(null);
		if(choice == JFileChooser.APPROVE_OPTION) {
			File json = chooser.getSelectedFile();
			try {
				List<Key> macro = convert(json);
				saveMacroProperty(json);
				return macro;
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "올바른 형식의 매크로 파일이 아닙니다");
				return load();
			}
		}
		else {
			return null;
		}
	}
	public static List<Key> load(){
		File jsonFile = loadMacroProperty();
		
		if(jsonFile == null) {//기존 파일이 없는 경우
			return reload();
		}
		else {//기존 파일이 있는 경우
			try {
				return convert(jsonFile);
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "매크로 파일을 선택해주세요");
				return reload();
			}
		}
		
	}
	
	public static void clearMacroProperty() {
		macroFile.delete();
	}
	
	public static void saveMacroProperty(File json) {
		try(
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(macroFile)));
		){
			out.writeObject(json);
		}
		catch(Exception ex) {}
	}
	
	public static File loadMacroProperty() {
		try (
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(macroFile)));
		){
			File json = (File) in.readObject();
			return json;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	private static List<Key> convert(File json) throws JsonParseException, JsonMappingException, IOException{
		List<Key> macro = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		KeyJson[] jsonArray = mapper.readValue(json, KeyJson[].class);
		for(KeyJson kjs : jsonArray) {
			macro.add(kjs.getKey());
		}
		return macro;
	}
}
