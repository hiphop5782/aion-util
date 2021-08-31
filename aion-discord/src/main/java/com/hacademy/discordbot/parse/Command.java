package com.hacademy.discordbot.parse;

import java.util.Arrays;

import com.hacademy.discordbot.action.DiscordAction;
import com.hacademy.discordbot.action.DungeonInformationAction;
import com.hacademy.discordbot.action.EnchantAction;
import com.hacademy.discordbot.action.FindCharacterAction;
import com.hacademy.discordbot.action.HelpAction;

public enum Command {
	FIND(new FindCharacterAction(), "-f", "-find", "-검색", "-찾기"), 
	HELP(new HelpAction(), "-h", "-help", "-도움말", "-정보"),
	DUNGEON(new DungeonInformationAction(), "-d", "-dungeon", "-던전", "-던전정보", "-인던", "-인던정보"),
	ENCHANT(new EnchantAction(), "-e", "-enchant", "-강화", "-인챈트", "-아이템강화");
	
	private DiscordAction action;
	private String[] commands;
	private Command(DiscordAction action, String ... commands) {
		this.action = action;
		this.commands = commands;
	}
	
	public String[] getCommands() {
		return this.commands;
	}
	public boolean has(String command) {
		return Arrays.binarySearch(commands, command) >= 0;
	}
	public DiscordAction getAction() {
		return action;
	}
}
