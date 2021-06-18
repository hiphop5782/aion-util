package com.hacademy.macro;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacademy.macro.key.Key;
import com.hacademy.macro.key.KeyJson;

public class Test05 {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		InputStream in = Test05.class.getResourceAsStream("macro.json");
		ObjectMapper mapper = new ObjectMapper();
		KeyJson[] list = mapper.readValue(in, KeyJson[].class);
		for(KeyJson json : list) {
			Key key = json.getKey();
			key.action();
		}
	}
}
