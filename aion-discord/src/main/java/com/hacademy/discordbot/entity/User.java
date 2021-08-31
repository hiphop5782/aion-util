package com.hacademy.discordbot.entity;

import com.hacademy.discordbot.parse.MessageFactory;

import lombok.Data;

@Data
public class User {
	private String serverName, userName;
}
