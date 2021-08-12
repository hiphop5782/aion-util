package com.hacademy.discordbot.parse;

import com.hacademy.discordbot.entity.CharacterDetailInformation;
import com.hacademy.discordbot.entity.CharacterInformation;
import com.hacademy.discordbot.entity.SearchUnit;
import com.hacademy.discordbot.http.AionRequestSender;

import discord4j.core.object.entity.Message;
import lombok.Data;

public class MessageFactory {
	private static final String defaultServerName = "바이젤";
	
	public static String createMessage(Message message) {
		if(message == null) 
			throw new IllegalArgumentException("message is emtpy");
		
		String[] payloads = message.getContent().toLowerCase().split("\\s");
		if(payloads == null || payloads.length == 0) 
			throw new IllegalArgumentException("payload is empty");
		
		String cmd = payloads[0];
		
		if(!cmd.startsWith("-")) 
			throw new IllegalArgumentException("wrong command type");
		
		MarkdownMaker maker = new MarkdownMaker();
		
		if(Command.FIND.has(cmd)) {
			if(payloads.length <= 3) {
				User user = parse(payloads);
				
				SearchUnit unit = AionRequestSender.findByServerNameAndUsername(user);

				if(unit == null) {
					maker.addLine("캐릭터가 존재하지 않습니다");
				}
				else {
					CharacterInformation info = AionRequestSender.findByServerCodeAndUserCode(unit.getServerId(), unit.getCharId());
					CharacterDetailInformation detail = AionRequestSender.findEquipmentByServerCodeAndUserCode(unit.getServerId(), unit.getCharId());
					detail.sort();
					
					maker.add(info, detail);
				}
			}
			else {
				maker.addLine("잘못된 형식의 검색 명령입니다");
				maker.addLine("`-h` 또는 `-help`를 눌러 사용법을 확인하세요");
			}
		}
		else if(Command.HELP.has(cmd)){
			maker.addQuote("아이온 비서 for Genius Legion");
			maker.nextLine();
			
			maker.addLine("아래 명령을 이용하여 캐릭터 검색이 가능합니다");
			maker.nextLine();
			
			maker.addLine("캐릭터 검색");
			maker.startBlock();
			maker.addLine("-f 캐릭터명");
			maker.addLine("-f 서버명 캐릭터명");
			maker.addLine("-find 캐릭터명");
			maker.addLine("-find 서버명 캐릭터명");
			maker.addLine("-검색 캐릭터명");
			maker.addLine("-검색 서버명 캐릭터명");
			maker.addLine("-찾기 캐릭터명");
			maker.addLine("-찾기 서버명 캐릭터명");
			maker.endBlock();
			
			maker.nextLine();
			
			maker.addLine("암흑의 포에타 점수 확인");
			maker.startBlock();
			maker.addLine("-d");
			maker.addLine("-darkpoeta");
			maker.addLine("-암포");
			maker.addLine("-암흑의포에타");
			maker.endBlock();
			maker.nextLine();
			
			maker.addLine("레기온 공식 홈페이지");
			maker.addLine("https://hiphop5782.gitbook.io/aion/");
			maker.addLine("레기온 놀이터");
			maker.addLine("https://hiphop5782.github.io/aion/");
			maker.add("제작자",":","부루마블");
		}
		else if(Command.DARKPOETA.has(cmd)) {
			maker.addQuote("암흑의 포에타 랭크별 점수");
			maker.startCssBlock();
			maker.add("S랭크",":","15,200점","(2시간 이내 클리어)");
			maker.add("A랭크",":","14,320점","(2시간 30분 이내 클리어)");
			maker.add("B랭크",":","10,913점","(3시간 이내 클리어)");
			maker.add("C랭크",":","6,656점","(3시간 30분 이내 클리어)");
			maker.add("F랭크",":","0점","(시간 제한 없음)");
			maker.endCssBlock();
		}
		
		if(maker.isEmpty())
			throw new RuntimeException("message is empty");
		
		return maker.getString();
	}
	
	@Data
	public static class User {
		private String serverName, userName;
	}
	
	public static User parse(String[] payloads) {
		User user = new User();
		if(payloads.length == 2) {
			user.serverName = "바이젤";
			user.userName = payloads[1];
		}
		else {
			user.serverName = payloads[1];
			user.userName = payloads[2];
		}
		return user;
	}
}
