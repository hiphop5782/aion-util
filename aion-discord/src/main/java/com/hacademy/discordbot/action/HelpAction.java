package com.hacademy.discordbot.action;

import com.hacademy.discordbot.parse.Command;
import com.hacademy.discordbot.parse.MarkdownMaker;

public class HelpAction extends DiscordAction{

	@Override
	public String process() {
		return information();
	}
	
	@Override
	public String information() {
		MarkdownMaker maker = new MarkdownMaker();
		
		maker.addQuote("아이온 비서 for Genius Legion");
		maker.nextLine();
		
		for(Command command : Command.values()) {
			if(command == Command.HELP) continue;
			maker.addLine(command.getAction().title());
			maker.add(command.getAction().information());
			maker.nextLine();
		}
		
		maker.nextLine();
		
		
		maker.nextLine();
		
		maker.addLine("레기온 공식 홈페이지");
		maker.addLine("https://hiphop5782.gitbook.io/aion/");
		maker.addLine("레기온 놀이터");
		maker.addLine("https://hiphop5782.github.io/aion/");
		maker.add("제작자",":","부루마블");
		
		return maker.toString();
	}
	
	@Override
	public String title() {
		return "도움말";
	}
}
