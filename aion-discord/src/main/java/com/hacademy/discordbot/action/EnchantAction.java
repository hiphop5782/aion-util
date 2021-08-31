package com.hacademy.discordbot.action;

import com.hacademy.discordbot.entity.Item;
import com.hacademy.discordbot.parse.MarkdownMaker;
import com.hacademy.discordbot.util.ItemDatabase;

public class EnchantAction extends DiscordAction {
	@Override
	public String process() {
		MarkdownMaker maker = new MarkdownMaker();

		try {
			if (payloads.length == 2) {
				String grade = payloads[1];

				maker.startCssBlock();
				switch (grade) {
				case "일반":
				case "희귀":
				case "전승":
					maker.addLine("일반, 희귀, 전승은 지원하지 않습니다");
					break;
				case "유일":
					maker.addLine("- 유일 아이템");
					maker.addLine("Lv 0 → 1 : (강화석레벨 - 아이템레벨 + 15) 의 두 배");
					maker.addLine("Lv 1 → 2 : (강화석레벨 - 아이템레벨 + 15) 의 두 배");
					maker.addLine("Lv 2 → 3 : (강화석레벨 - 아이템레벨 + 15) 의 두 배");
					maker.addLine("Lv 3 → 4 : (강화석레벨 - 아이템레벨 + 15) 의 두 배");
					maker.addLine("Lv 4 → 5 : (강화석레벨 - 아이템레벨 + 15) 의 두 배");
					maker.addLine("Lv 5 → 6 : (강화석레벨 - 아이템레벨 + 15) 의 두 배");
					maker.addLine("Lv 6 → 7 : (강화석레벨 - 아이템레벨 + 15) 의 두 배");
					maker.addLine("Lv 7 → 8 : (강화석레벨 - 아이템레벨 + 15) 의 두 배");
					maker.addLine("Lv 8 → 9 : (강화석레벨 - 아이템레벨 + 15) 의 두 배");
					maker.addLine("Lv 9 → 10 : (강화석레벨 - 아이템레벨 + 15) 의 두 배");
					maker.addLine("Lv 10 → 11 : (강화석레벨 - 아이템레벨 + 15)");
					maker.addLine("Lv 11 → 12 : (강화석레벨 - 아이템레벨 + 15)");
					maker.addLine("Lv 12 → 13 : (강화석레벨 - 아이템레벨 + 15)");
					maker.addLine("Lv 13 → 14 : (강화석레벨 - 아이템레벨 + 15)");
					maker.addLine("Lv 14 → 15 : (강화석레벨 - 아이템레벨 + 15)");

					maker.addLine("(ex)");
					maker.addLine("50 유일 아이템 1강을 70레벨 강화석으로 강화 성공할 확률은 60% 입니다");
					maker.addLine("50 유일 아이템 11강을 70레벨 강화석으로 강화 성공할 확률은 30% 입니다");
					maker.addLine("50 유일 아이템 10강을 70레벨 강화석으로 15강할 확률은 0.243% 입니다");
					break;
				case "영웅":
					maker.addLine("- 영웅 아이템");
					maker.addLine("Lv 0 → 1 : (강화석레벨 - 아이템레벨) * 1.5 + 15 의 두 배");
					maker.addLine("Lv 1 → 2 : (강화석레벨 - 아이템레벨) * 1.5 + 15 의 두 배");
					maker.addLine("Lv 2 → 3 : (강화석레벨 - 아이템레벨) * 1.5 + 15 의 두 배");
					maker.addLine("Lv 3 → 4 : (강화석레벨 - 아이템레벨) * 1.5 + 15 의 두 배");
					maker.addLine("Lv 4 → 5 : (강화석레벨 - 아이템레벨) * 1.5 + 15 의 두 배");
					maker.addLine("Lv 5 → 6 : (강화석레벨 - 아이템레벨) * 1.5 + 15 의 두 배");
					maker.addLine("Lv 6 → 7 : (강화석레벨 - 아이템레벨) * 1.5 + 15 의 두 배");
					maker.addLine("Lv 7 → 8 : (강화석레벨 - 아이템레벨) * 1.5 + 15 의 두 배");
					maker.addLine("Lv 8 → 9 : (강화석레벨 - 아이템레벨) * 1.5 + 15 의 두 배");
					maker.addLine("Lv 9 → 10 : (강화석레벨 - 아이템레벨) * 1.5 + 15 의 두 배");
					maker.addLine("Lv 10 → 11 : (강화석레벨 - 아이템레벨) * 1.5 + 15");
					maker.addLine("Lv 11 → 12 : (강화석레벨 - 아이템레벨) * 1.5 + 15");
					maker.addLine("Lv 12 → 13 : (강화석레벨 - 아이템레벨) * 1.5 + 15");
					maker.addLine("Lv 13 → 14 : (강화석레벨 - 아이템레벨) * 1.5 + 15");
					maker.addLine("Lv 14 → 15 : (강화석레벨 - 아이템레벨) * 1.5 + 15");

					maker.addLine("(ex)");
					maker.addLine("50 영웅 아이템 1강을 80레벨 강화석으로 강화 성공할 확률은 60% 입니다");
					maker.addLine("50 영웅 아이템 11강을 80레벨 강화석으로 강화 성공할 확률은 30% 입니다");
					maker.addLine("50 영웅 아이템 10강을 80레벨 강화석으로 15강할 확률은 0.243% 입니다");
					break;
				default:
					throw new Exception();
				}
				maker.endCssBlock();
			} else if (payloads.length == 3) {
				int enchantLevel = Integer.parseInt(payloads[1]);
				String itemName = payloads[2];

				Item item = ItemDatabase.find(itemName);
				int itemLevel = item.getLevel();
				String grade = item.getGrade();

				maker.startCssBlock();
				maker.add("-", grade, "등급", itemLevel, "레벨", "아이템", "강화", "확률");

				int min = getMinimumStoneLevel(grade, itemLevel);
				int max = getMaximumStoneLevel(grade, itemLevel);

				for (int i = min; i <= max; i++) {
					maker.add(i, "레벨", "강화석", ":", getEnchantSuccessRate(grade, itemLevel, enchantLevel, i), "%");
				}
				maker.endCssBlock();
			} else if (payloads.length == 4) {
				int enchantLevel = Integer.parseInt(payloads[1]);
				String itemName = payloads[2];

				Item item = ItemDatabase.find(itemName);
				int itemLevel = item.getLevel();
				String grade = item.getGrade();
				
				int stoneLevel = Integer.parseInt(payloads[3]);

				maker.startCssBlock();
				maker.add(
						"-", grade, "등급", 
						"[", "+"+enchantLevel , itemName, "]", "아이템을",
						stoneLevel,"레벨 강화석으로 강화합니다.");
				
				float choice = (float)Math.random() * 100;
				float successRate = getEnchantSuccessRate(grade, itemLevel, enchantLevel, stoneLevel);
				if(choice < successRate) {
					enchantLevel++;
					maker.add("==>","강화 성공!","[","+"+enchantLevel,itemName, "]");
				}
				else {
					enchantLevel = enchantLevel > 10? 10 : enchantLevel - 1;
					maker.add("==>","강화 실패..","[","+"+enchantLevel,itemName, "]");
				}
					
				maker.endCssBlock();
					
			} else {
				throw new Exception();
			}
		}
		catch(Exception e) {
			maker.add(information());
		}

		return maker.toString();
	}

	@Override
	public String title() {
		return "장비 강화 테스트";
	}

	@Override
	public String information() {
		MarkdownMaker maker = new MarkdownMaker();

		maker.startBlock();
		maker.addLine("등급별 전체 강화 확률을 보고 싶으면 다음과 같이 작성합니다");
		maker.addLine("-강화 (등급)");

		maker.nextLine();

		maker.addLine("특정 강화레벨에서의 강화 확률을 보고 싶으면 다음과 같이 작성합니다");
		maker.addLine("-강화 (아이템레벨) (아이템이름)");

		maker.nextLine();

		maker.addLine("다음 명령 중 하나로 강화 테스트가 가능합니다");
		maker.addLine("테스트 결과는 \"강화 성공\" 또는 \"강화 실패\"로 나옵니다");
		maker.addLine("-강화 (아이템레벨) (아이템이름) (강화석레벨)");

		maker.endBlock();
		return maker.toString();
	}

	private int getMinimumStoneLevel(String grade, int itemLevel) {
		switch (grade) {
		case "영웅":
			return itemLevel - 10;
		case "유일":
			return itemLevel - 15;
		}
		return 1;
	}

	private int getMaximumStoneLevel(String grade, int itemLevel) {
		switch (grade) {
		case "영웅":
			return itemLevel + 50;
		case "유일":
			return itemLevel + 40;
		}
		return 99;
	}

	private float getEnchantSuccessRate(String grade, int itemLevel, int enchantLevel, int stoneLevel) {
		float rate = 0f;
		switch (grade) {
		case "영웅":
			rate = (stoneLevel - itemLevel) * 1.5f + 15;
			break;
		case "유일":
			rate = (stoneLevel - itemLevel + 15) * 2;
			break;
		}

		if (enchantLevel >= 10) {
			rate /= 2;
		}

		return rate;
	}
//	private float getEnchantRate(String grade, int itemLevel, int stoneLevel) {
//		
//	}
}
