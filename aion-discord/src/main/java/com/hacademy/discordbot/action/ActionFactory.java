package com.hacademy.discordbot.action;

import com.hacademy.discordbot.parse.Command;

import discord4j.core.object.entity.Message;

public class ActionFactory {
	public static DiscordAction createAction(Message message) {
		String[] payloads = message.getContent().toLowerCase().split("\\s");
		if(payloads == null || payloads.length == 0) 
			throw new IllegalArgumentException("payload is empty");
		
		String cmd = payloads[0];
		if(!cmd.startsWith("-")) 
			throw new IllegalArgumentException("wrong command type");
		
		if(Command.HELP.has(cmd))
			return Command.HELP.getAction();
		
		if(Command.FIND.has(cmd))
			return Command.FIND.getAction().payloads(payloads);
		
		if(Command.DUNGEON.has(cmd))
			return Command.DUNGEON.getAction().payloads(payloads);
		
		if(Command.ENCHANT.has(cmd))
			return Command.ENCHANT.getAction().payloads(payloads);
		
		return null;
	}
}
