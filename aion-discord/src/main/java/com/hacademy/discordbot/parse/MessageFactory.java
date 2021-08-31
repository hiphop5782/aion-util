package com.hacademy.discordbot.parse;

import com.hacademy.discordbot.action.ActionFactory;
import com.hacademy.discordbot.action.DiscordAction;
import com.hacademy.discordbot.entity.CharacterDetailInformation;
import com.hacademy.discordbot.entity.CharacterInformation;
import com.hacademy.discordbot.entity.SearchUnit;
import com.hacademy.discordbot.http.AionRequestSender;

import discord4j.core.object.entity.Message;
import lombok.Data;

public class MessageFactory {
	public static String createMessage(Message message) {
		if(message == null) 
			throw new IllegalArgumentException("message is emtpy");
		
		DiscordAction action = ActionFactory.createAction(message);
		if(action == null) {
			throw new IllegalArgumentException("no suitable action");
		}
		return action.process();
	}
	
}
