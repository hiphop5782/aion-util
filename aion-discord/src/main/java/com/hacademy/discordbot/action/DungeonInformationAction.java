package com.hacademy.discordbot.action;

import com.hacademy.discordbot.parse.MarkdownMaker;

public class DungeonInformationAction extends DiscordAction{

	@Override
	public String process() {
		MarkdownMaker maker = new MarkdownMaker();
		
		if(payloads.length == 2) {
			String dungeon = payloads[1];
			
			switch(dungeon.replace(" ", "")) {
			case "암포": case "암흑의포에타":
				maker.addQuote("암흑의 포에타 랭크별 점수");
				maker.startCssBlock();
				maker.add("S랭크",":","15,200점","(2시간 이내 클리어)");
				maker.add("A랭크",":","14,320점","(2시간 30분 이내 클리어)");
				maker.add("B랭크",":","10,913점","(3시간 이내 클리어)");
				maker.add("C랭크",":","6,656점","(3시간 30분 이내 클리어)");
				maker.add("F랭크",":","0점","(시간 제한 없음)");
				maker.endCssBlock();
				break;
			case "드레드": case "드레드기온":
				maker.addQuote("드레드기온 점수 정보");
				maker.startCssBlock();
				maker.addLine("[상/하단 라인]");
				maker.addLine("- 무기고");
				maker.add("\t", "수르 : 500점");
				maker.addLine("- 핵 제어실");
				maker.add("\t", "수르 : 1,100점");
				maker.add("\t", "대장 : 200점");
				maker.addLine("- 강하 대기실");
				maker.add("\t", "수르 : 800점");
				maker.add("\t", "대장 : 200점");
				maker.addLine("- 주포 제어실");
				maker.add("\t", "수르 : 600점");
				maker.add("\t", "대장 : 200점");
				maker.addLine("- 포로 수용실");
				maker.add("\t", "수르 : 500점");
				maker.add("\t", "대장 : 200점");
				maker.add("\t", "포로 : 100점");
				
				maker.addLine("[중앙 라인]");
				maker.addLine("- 중력 제어실");
				maker.add("\t", "수르 : 900점");
				maker.add("\t", "대장 : 200점");
				
				maker.addLine("- 전투병 대기실");
				maker.add("\t", "수르 : 900점");
				maker.add("\t", "대장 : 200점");
				
				maker.addLine("- 사령실");
				maker.add("\t", "수르 : 700점");
				maker.add("\t", "대장 : 500점");
				
				maker.addLine("- 함장실");
				maker.add("\t", "수르 : 700점");
				maker.add("\t", "대장 : 1,000점");
				
				maker.endCssBlock();
				break;
			case "티아크": case "티아크연구기지":
				maker.addQuote("티아크 연구기지 정보");
//				maker.startCssBlock();
//				maker.addLine("미구현");
//				maker.endCssBlock();
				maker.addLine("https://aion.plaync.com/guidebook/classic/view?title=%ED%8B%B0%EC%95%84%ED%81%AC%20%EC%97%B0%EA%B5%AC%EA%B8%B0%EC%A7%80");
				break;
			}
			
		}
		else{
			maker.addLine("잘못된 명령입니다. 아래 형식을 확인하고 다시 작성해주세요.");
			maker.add(information());
		}
		
		return maker.toString();
	}
	
	@Override
	public String title() {
		return "던전 정보 확인";
	}
	
	@Override
	public String information() {
		MarkdownMaker maker = new MarkdownMaker();
		
		maker.startBlock();
		maker.addLine("-d (이름)");
		maker.addLine("-dungeon (이름)");
		maker.addLine("-인던 (이름)");
		maker.addLine("-인던정보 (이름)");
		maker.addLine("-던전 (이름)");
		maker.addLine("-던전정보 (이름)");
		
		maker.nextLine();
		maker.addLine("지원 던전 : 드레드기온, 티아크 연구기지, 암흑의 포에타");
		maker.endBlock();
		
		return maker.toString();
	}
	
}
