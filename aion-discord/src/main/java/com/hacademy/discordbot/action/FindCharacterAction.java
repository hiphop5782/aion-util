package com.hacademy.discordbot.action;

import com.hacademy.discordbot.entity.CharacterDetailInformation;
import com.hacademy.discordbot.entity.CharacterInformation;
import com.hacademy.discordbot.entity.SearchUnit;
import com.hacademy.discordbot.entity.User;
import com.hacademy.discordbot.http.AionRequestSender;
import com.hacademy.discordbot.parse.MarkdownMaker;

public class FindCharacterAction extends DiscordAction{

	@Override
	public String process() {
		MarkdownMaker maker = new MarkdownMaker();
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
			maker.add(information());
		}
		return maker.toString();
	}
	
	public User parse(String[] payloads) {
		User user = new User();
		if(payloads.length == 2) {
			user.setServerName("바이젤");
			user.setUserName(payloads[1]);
		}
		else {
			user.setServerName(payloads[1]);
			user.setUserName(payloads[2]);
		}
		return user;
	}
	
	@Override
	public String title() {
		return "아이온 캐릭터 검색";
	}
	
	@Override
	public String information() {
		MarkdownMaker maker = new MarkdownMaker();

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
		
		return maker.toString();
	}
}
