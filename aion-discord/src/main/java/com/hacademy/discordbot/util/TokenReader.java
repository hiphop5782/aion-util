package com.hacademy.discordbot.util;

import java.io.IOException;
import java.util.Properties;

import com.hacademy.discordbot.App;

public class TokenReader {
	public static String read() throws IOException {
		Properties props = new Properties();
		props.load(App.class.getResourceAsStream("/token.properties"));
		return props.getProperty("token");
	}
}
