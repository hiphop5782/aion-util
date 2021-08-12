package com.hacademy.discordbot.parse;

import java.util.Arrays;

public enum Command {
	FIND("-f", "-find", "-검색", "-찾기"), 
	HELP("-h", "-help", "-도움말", "-정보"),
	DARKPOETA("-d", "-darkpoeta", "-암포", "-암흑의포에타");
	
	private String[] commands;
	private Command(String ... commands) {
		this.commands = commands;
	}
	
	public String[] getCommands() {
		return this.commands;
	}
	public boolean has(String command) {
		return Arrays.binarySearch(commands, command) >= 0;
	}
}
