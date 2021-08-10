package com.hacademy.discordbot;

import com.hacademy.discordbot.entity.CharacterDetailInformation;
import com.hacademy.discordbot.entity.CharacterEquipmentsInformation;
import com.hacademy.discordbot.entity.CharacterInformation;
import com.hacademy.discordbot.entity.SearchUnit;
import com.hacademy.discordbot.http.AionRequestSender;

public class Test04 {
	public static void main(String[] args) {
		SearchUnit unit = AionRequestSender.findByServerNameAndUsername("바이젤", "프리마베라");
		System.out.println(unit);
		
		if(unit == null) {
			System.out.println("캐릭터가 없습니다");
		}
		else {
			System.out.println("캐릭터 정보");
			CharacterInformation information = AionRequestSender.findByServerCodeAndUserCode(unit.getServerId(), unit.getCharId());
			System.out.println(information);
			CharacterDetailInformation equipments = AionRequestSender.findEquipmentByServerCodeAndUserCode(unit.getServerId(), unit.getCharId());
			for(CharacterEquipmentsInformation equip : equipments.getCharacter_equipments()) {
				System.out.println(equip);
			}
		}
	}
}
