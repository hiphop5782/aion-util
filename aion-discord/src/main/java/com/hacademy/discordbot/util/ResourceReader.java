package com.hacademy.discordbot.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacademy.discordbot.App;
import com.hacademy.discordbot.entity.ItemData;

public class ResourceReader {
	public static String readToken() throws IOException {
		Properties props = new Properties();
		props.load(App.class.getResourceAsStream("/token.properties"));
		return props.getProperty("token");
	}
	public static ItemData readItemDatabase() throws IOException {
		InputStream in = App.class.getResourceAsStream("/item.db");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(in, ItemData.class);
	}
}
