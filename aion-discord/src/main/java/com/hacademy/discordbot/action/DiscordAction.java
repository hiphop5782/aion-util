package com.hacademy.discordbot.action;

public abstract class DiscordAction {
	protected String[] payloads; 
	public DiscordAction payloads(String...payloads) {
		this.payloads = payloads;
		return this;
	}
	public abstract String process();
	
	public abstract String title();
	public abstract String information();
}
