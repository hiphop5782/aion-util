package com.hacademy.discordbot;

import java.io.IOException;

import com.hacademy.discordbot.parse.MessageFactory;
import com.hacademy.discordbot.util.ResourceReader;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;

public class App {
	public static void main(String[] args) throws IOException {
		String token = ResourceReader.readToken();
		GatewayDiscordClient client = DiscordClientBuilder.create(token).build().login().block(); 
		
		client.on(ReadyEvent.class)
				.subscribe(event->{
					User self = event.getSelf();
					System.out.printf("Logged in as %s#%s\n", self.getUsername(), self.getDiscriminator());
				});
		
		client.on(MessageCreateEvent.class).subscribe(event->{
			try {
				Message message = event.getMessage();
				String text = MessageFactory.createMessage(message);
				MessageChannel channel = message.getChannel().block();
				channel.createMessage(text).block();
			}
			catch(Exception ex) {
				//ex.printStackTrace();
			}
		});
		client.onDisconnect().block();
	}
}
