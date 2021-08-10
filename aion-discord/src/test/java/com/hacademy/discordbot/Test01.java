package com.hacademy.discordbot;

import java.util.Arrays;
import java.util.List;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;

public class Test01 {
	public static void main(String[] args) {
		String token = "ODczNjM3NjM3MTcxMzMxMTEy.YQ7UdA.VgHBkJ337OYxuFi4gqDmWLxr6b4";
		GatewayDiscordClient client = DiscordClientBuilder.create(token).build().login().block(); 
		
		client.getEventDispatcher().on(ReadyEvent.class)
				.subscribe(event->{
					User self = event.getSelf();
					System.out.printf("Logged in as %s#%s\n", self.getUsername(), self.getDiscriminator());
				});
		
		List<String> commands = Arrays.asList("-ping", "-find", "-hello");
		
		client.getEventDispatcher().on(MessageCreateEvent.class)
		        .map(MessageCreateEvent::getMessage)
		        .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
		        .filter(message -> commands.contains(message.getContent()))
		        .flatMap(Message::getChannel)
		        .flatMap(channel -> channel.createMessage("Pong!"))
		        .subscribe();

		client.onDisconnect().block();
	}
}
