package com.hacademy.discordbot.util;

import com.hacademy.discordbot.entity.Item;
import com.hacademy.discordbot.entity.ItemData;

public class ItemDatabase {
	
	private static ItemData data;
	static {
		try {
			data = ResourceReader.readItemDatabase();
		}
		catch(Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
	
	public static Item find(String itemName) {
		return data.getItemByName(itemName);
	}
	
}
