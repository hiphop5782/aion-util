package com.hacademy.discordbot.parse;

import java.text.DecimalFormat;
import java.text.Format;

import com.hacademy.discordbot.entity.CharacterDetailInformation;
import com.hacademy.discordbot.entity.CharacterEquipmentsInformation;
import com.hacademy.discordbot.entity.CharacterInformation;
import com.hacademy.discordbot.entity.ItemType;

public class MarkdownMaker {
	
	private StringBuffer buffer;
	private final static Format commaFormat = new DecimalFormat("#,###");
	private final static Format twoFormat = new DecimalFormat("00");
	
	public MarkdownMaker() {
		buffer = new StringBuffer();
	}
	
	public void startBlock() {
		endBlock();
	}
	
	public void endBlock() {
		addLine("```");
	}
	
	public void startCssBlock() {
		addLine("```css");
	}
	
	public void endCssBlock() {
		endBlock();
	}
	
	public void startCsBlock() {
		addLine("```cs");
	}
	
	public void endCsBlock() {
		endBlock();
	}
	
	public void add(String str) {
		buffer.append(str);
	}
	
	public void add(Object ... objs) {
		for(Object o : objs) {
			add(o.toString());
			addSpace();
		}
		nextLine();
	}
	
	public void addLine(String str) {
		add(str);
		nextLine();
	}
	
	public void nextLine() {
		buffer.append('\n');
	}
	
	@Override
	public String toString() {
		return buffer.toString();
	}
	
	public void addQuote(String ... str) {
		add("> ");
		for(String s : str) {
			add(s);
			addSpace();
		}
		nextLine();
	}
	
	public void addSpace() {
		addSpace(1);
	}
	
	public void addSpace(int size) {
		for(int i=0 ; i < size ; i++) {
			add(" ");
		}
	}
	
	public void addHighlightValue(Object ... objs) {
		add("[");
		for(Object o : objs) {
			add(String.valueOf(o));
		}
		add("]");
	}
	
	public void addNormalValue(Object ... objs) {
		add("(");
		for(Object o : objs) {
			add(String.valueOf(o));
		}
		add(")");
	}
	
	public void add(CharacterInformation info, CharacterDetailInformation detail) {
		//캐릭터 기본정보
		addBasicCharacterInformation(info, detail);
		nextLine();
		
		//캐릭터 수치정보
		addAdvancedCharacterInformation(info, detail);
		nextLine();
		
		//캐릭터 장비정보
		addEquipInformation(info, detail);
		nextLine();
		
		//홈페이지 링크
		addLinkInformation(info);
	}
	
	private void addPhysicalInformation(CharacterDetailInformation detail) {
		if(detail.getCharacter_stats().getTotalStat().getPhysicalLeft() > 0) {
			add("공격력(우/좌)", ":", 
					String.valueOf(detail.getCharacter_stats().getTotalStat().getPhysicalRight()),
					"/",
					String.valueOf(detail.getCharacter_stats().getTotalStat().getPhysicalLeft()));
			
			add("명중(우/좌)", ":", 
					String.valueOf(detail.getCharacter_stats().getTotalStat().getAccuracyRight()),
					"/",
					String.valueOf(detail.getCharacter_stats().getTotalStat().getAccuracyLeft()));
			
			add("치명타(우/좌)", ":", 
					String.valueOf(detail.getCharacter_stats().getTotalStat().getCriticalRight()),
					"/",
					String.valueOf(detail.getCharacter_stats().getTotalStat().getCriticalLeft()));
		}
		else {
			add("공격력", ":", String.valueOf(detail.getCharacter_stats().getTotalStat().getPhysicalRight()));
			add("명중", ":", String.valueOf(detail.getCharacter_stats().getTotalStat().getAccuracyRight()));
			add("치명타", ":",String.valueOf(detail.getCharacter_stats().getTotalStat().getCriticalRight()));
		}
	}
	
	private void addMagicalInformation(CharacterDetailInformation detail) {
		add("마법증폭력", ":", String.valueOf(detail.getCharacter_stats().getTotalStat().getMagicalBoost()));
		add("마법적중", ":", String.valueOf(detail.getCharacter_stats().getTotalStat().getMagicalAccuracy()));
		add("마법치명타", ":", String.valueOf(detail.getCharacter_stats().getTotalStat().getMagicalCriticalRight()));
	}
	
	private void addMagicRegistInformation(CharacterDetailInformation detail) {
		add("마법저항",":",String.valueOf(detail.getCharacter_stats().getTotalStat().getMagicResist()));
	}
	
	private void addBlockInformation(CharacterDetailInformation detail) {
		add("방패방어",":",String.valueOf(detail.getCharacter_stats().getTotalStat().getBlock()));
	}
	
	private void addBasicCharacterInformation(CharacterInformation info, CharacterDetailInformation detail) {
		addQuote("아이디", ":", info.getCharacterName());
		addQuote("클래스", ":", info.getJobName());
		addQuote("레벨", ":", String.valueOf(info.getLevel()));
		addQuote("계급", ":", detail.getCharacter_abyss().getRankName());
		addQuote("킬수", ":", commaFormat.format(detail.getCharacter_abyss().getTotalKillCount()));
		addQuote("HP", ":", commaFormat.format(detail.getCharacter_stats().getTotalStat().getHp()));
		addQuote("MP", ":", commaFormat.format(detail.getCharacter_stats().getTotalStat().getMp()));
	}
	
	private void addAdvancedCharacterInformation(CharacterInformation info, CharacterDetailInformation detail) {
		//클래스에 따른 정보 표시
		//- 물리 : 물공 / 명중 / 치명 / 마저
		//- 마법 : 마증 / 마적 / 마치 / 마저
		startCsBlock();
		switch(info.getJobName()) {
		case "수호성": case "검성": case "살성": case "궁성": case "호법성":
			addPhysicalInformation(detail);
			break;
		case "마도성": case "정령성": case "치유성":
			addMagicalInformation(detail);
		}
		addMagicRegistInformation(detail);
		
		switch(info.getJobName()) {
		case "수호성":
		case "치유성":
			addBlockInformation(detail);
		}
		endCsBlock();
	}
	
	private void addEquipInformation(CharacterInformation info, CharacterDetailInformation detail) {
		startCssBlock();
		for(CharacterEquipmentsInformation equip : detail.getCharacter_equipments()) {
			ItemType category1 = ItemType.valueOf(equip.getCategory1().getAlias());
			ItemType category2 = ItemType.valueOf(equip.getCategory2().getAlias());
			if(category1.isEnchantEnable() && category2.isEnchantEnable()) {
				if(equip.isHighEnchant()) {
					addHighlightValue(twoFormat.format(equip.getEnchantCount()));
				}
				else {
					addNormalValue("+", equip.getEnchantCount());
				}
			}
			addSpace();
			addLine(equip.getName());
		}
		endCssBlock();
	}
	
	private void addLinkInformation(CharacterInformation info) {
		addLine(String.format("아이온 검색화면으로 이동 : https://aion.plaync.com/characters/server/%d/id/%d/home", info.getServerId(), info.getCharacterId()));
	}
	
	public boolean hasText() {
		return !isEmpty();
	}
	
	public boolean isEmpty() {
		return buffer.length() == 0;
	}
	
}
