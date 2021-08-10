package com.hacademy.discordbot;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacademy.discordbot.entity.CharacterInformation;
import com.hacademy.discordbot.entity.SearchResult;
import com.hacademy.discordbot.entity.SearchUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Test03 {
	public static void main(String[] args) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request.Builder builder = new Request.Builder().url("https://api-aion.plaync.com/search/v1/characters?classId=&pageSize=20&query=부루마블&raceId=&serverId=0&sort=rank").get();
		
		Response response = client.newCall(builder.build()).execute();
		
		if(response.isSuccessful()) {
			if(response.body() != null) {
				String jsonString = response.body().string();
				ObjectMapper mapper = new ObjectMapper();
				System.out.println(jsonString);
				SearchResult info = mapper.readValue(jsonString, SearchResult.class);
				for(SearchUnit unit : info.getDocuments()) {
					System.out.println(unit);
				}
			}
		}
		else {
			System.out.println("error");
		}
	}
}
