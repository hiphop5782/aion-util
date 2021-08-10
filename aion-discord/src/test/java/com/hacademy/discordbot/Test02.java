package com.hacademy.discordbot;

import java.text.DecimalFormat;
import java.text.Format;

import com.hacademy.discordbot.entity.CharacterDetailInformation;
import com.hacademy.discordbot.entity.CharacterEquipmentsInformation;
import com.hacademy.discordbot.entity.CharacterInformation;
import com.hacademy.discordbot.entity.ItemType;
import com.hacademy.discordbot.entity.SearchUnit;
import com.hacademy.discordbot.http.AionRequestSender;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;

public class Test02 {
	public static void main(String[] args) {
		Format fmt = new DecimalFormat("#,###");
		Format fmt2 = new DecimalFormat("00");
		String token = "ODczNjM3NjM3MTcxMzMxMTEy.YQ7UdA.VgHBkJ337OYxuFi4gqDmWLxr6b4";
		GatewayDiscordClient client = DiscordClientBuilder.create(token).build().login().block(); 
		
		client.on(ReadyEvent.class)
				.subscribe(event->{
					User self = event.getSelf();
					System.out.printf("Logged in as %s#%s\n", self.getUsername(), self.getDiscriminator());
				});
		
		client.on(MessageCreateEvent.class).subscribe(event->{
			Message message = event.getMessage();
			if(message == null) return;
			
			String[] payloads = message.getContent().toLowerCase().split("\\s");
			if(payloads == null || payloads.length == 0) return;
			
			if(!payloads[0].startsWith("-")) return;
			
			StringBuffer buffer = new StringBuffer();
			MessageChannel channel = message.getChannel().block();
			if(
					payloads[0].equals("-find")
					|| payloads[0].equals("-f")
					|| payloads[0].equals("-검색")
			) {
				if(payloads.length <= 3) {
					String serverName;
					String userName;
					if(payloads.length == 2) {
						serverName = "바이젤";
						userName = payloads[1];
					}
					else {
						serverName = payloads[1];
						userName = payloads[2];
					}
					SearchUnit unit = AionRequestSender.findByServerNameAndUsername(serverName, userName);
					if(unit == null) {
						buffer.append("캐릭터가 존재하지 않습니다");
					}
					else {
						CharacterInformation info = AionRequestSender.findByServerCodeAndUserCode(unit.getServerId(), unit.getCharId());
						CharacterDetailInformation detail = AionRequestSender.findEquipmentByServerCodeAndUserCode(unit.getServerId(), unit.getCharId());
						detail.sort();
						
						buffer.append("> 아이디 : ");
						buffer.append(info.getCharacterName());
						buffer.append("\n");
						buffer.append("> 클래스 : ");
						buffer.append(info.getJobName());
						buffer.append("\n");
						buffer.append("> 레벨 : ");
						buffer.append(info.getLevel());
						buffer.append("\n");
						buffer.append("> 계급 : ");
						buffer.append(detail.getCharacter_abyss().getRankName());
						buffer.append("\n");
						buffer.append("> 킬수 : ");
						buffer.append(fmt.format(detail.getCharacter_abyss().getTotalKillCount()));
						buffer.append("\n");
						buffer.append("> HP : ");
						buffer.append(fmt.format(detail.getCharacter_stats().getTotalStat().getHp()));
						buffer.append("\n");
						buffer.append("> MP : ");
						buffer.append(fmt.format(detail.getCharacter_stats().getTotalStat().getMp()));
						buffer.append("\n\n");
						
						//클래스에 따른 정보 표시
						//- 물리 : 물공 / 명중 / 치명 / 마저
						//- 마법 : 마증 / 마적 / 마치 / 마저
						buffer.append("```cs\n");
						switch(info.getJobName()) {
						case "수호성": case "검성": case "살성": case "궁성": case "호법성":
							buffer.append("공격력(우/좌) : ");
							buffer.append(detail.getCharacter_stats().getTotalStat().getPhysicalRight());
							buffer.append(" / ");
							buffer.append(detail.getCharacter_stats().getTotalStat().getPhysicalLeft());
							buffer.append("\n");
							
							buffer.append("명중(우/좌) : ");
							buffer.append(detail.getCharacter_stats().getTotalStat().getAccuracyRight());
							buffer.append(" / ");
							buffer.append(detail.getCharacter_stats().getTotalStat().getAccuracyLeft());
							buffer.append("\n");
							
							buffer.append("치명타(우/좌) : ");
							buffer.append(detail.getCharacter_stats().getTotalStat().getCriticalRight());
							buffer.append(" / ");
							buffer.append(detail.getCharacter_stats().getTotalStat().getCriticalLeft());
							buffer.append("\n");
							break;
						case "마도성": case "정령성": case "치유성":
							buffer.append("마법증폭력 : ");
							buffer.append(detail.getCharacter_stats().getTotalStat().getMagicalBoost());
							buffer.append("\n");
							
							buffer.append("마법적중 : ");
							buffer.append(detail.getCharacter_stats().getTotalStat().getMagicalAccuracy());
							buffer.append("\n");
							
							buffer.append("마법치명타 : ");
							buffer.append(detail.getCharacter_stats().getTotalStat().getMagicalCriticalRight());
							buffer.append("\n");
						}
						buffer.append("마법저항 : ");
						buffer.append(detail.getCharacter_stats().getTotalStat().getMagicResist());
						buffer.append("\n");
						
						switch(info.getJobName()) {
						case "수호성":
						case "치유성":
							buffer.append("방패방어 : ");
							buffer.append(detail.getCharacter_stats().getTotalStat().getBlock());
							buffer.append("\n");
						}
						buffer.append("```\n");
						
						buffer.append("```css\n");
						for(CharacterEquipmentsInformation equip : detail.getCharacter_equipments()) {
							if(ItemType.valueOf(equip.getCategory1().getAlias()).isEnchantEnable()) {
								if(ItemType.valueOf(equip.getCategory2().getAlias()).isEnchantEnable()) {
									if(equip.getEnchantCount() >= 10) {
										buffer.append("[");
										buffer.append(fmt2.format(equip.getEnchantCount()));
										buffer.append("]");
									}
									else {
										buffer.append("(");
										buffer.append("+");
										buffer.append(equip.getEnchantCount());
										buffer.append(")");
									}
								}
							}
							buffer.append(" ");
							buffer.append(equip.getName());
							buffer.append("\n");
						}
						buffer.append("```");
						buffer.append("\n");
						buffer.append(String.format("아이온 검색화면으로 이동 : https://aion.plaync.com/characters/server/%d/id/%d/home", info.getServerId(), info.getCharacterId()));
					}
				}
				else {
					buffer.append("잘못된 형식의 검색 명령입니다");
				}
			}
			else if(payloads[0].equals("-h") || payloads[0].equals("-help")) {
				buffer.append("> 아이온 비서 for Genius Legion\n\n");
				buffer.append("아래 명령을 이용하여 캐릭터 검색이 가능합니다\n\n");
				buffer.append("`-f 캐릭터명` 또는 `-f 서버명 캐릭터명`\n");
				buffer.append("`-find 캐릭터명` 또는 `-find 서버명 캐릭터명`\n");
				buffer.append("`-검색 캐릭터명` 또는 `-검색 서버명 캐릭터명`\n\n");
				buffer.append("");
				buffer.append("\n");
				buffer.append("레기온 공식 홈페이지 \n→ https://hiphop5782.gitbook.io/aion/\n");
				buffer.append("레기온 놀이터 \n→ https://hiphop5782.github.io/aion/\n");
			}
			else if(
					payloads[0].equals("-암포")
					|| payloads[0].equals("-암포 점수")
					|| payloads[0].equals("-암포점수")
			) {
				buffer.append("> 암흑의 포에타 랭크별 점수\n");
				buffer.append("```css\n");
				buffer.append("S랭크 : 15,200점 (2시간 이내 클리어)\n");
				buffer.append("A랭크 : 14,320점 (2시간 30분 이내 클리어)\n");
				buffer.append("B랭크 : 10,913점 (3시간 이내 클리어)\n");
				buffer.append("C랭크 : 6,656점 (3시간 30분 이내 클리어)\n");
				buffer.append("F랭크 : 0점 (시간 제한 없음)\n");
				buffer.append("```\n");
			}
			
			//메세지가 있을 경우만 전송
			if(buffer.length() > 0) {
				channel.createMessage(buffer.toString()).block();
			}
		});
		
		client.onDisconnect().block();
	}
}
