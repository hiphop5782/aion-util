package com.hacademy.discordbot.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacademy.discordbot.entity.CharacterDetailInformation;
import com.hacademy.discordbot.entity.CharacterInformation;
import com.hacademy.discordbot.entity.SearchResult;
import com.hacademy.discordbot.entity.SearchUnit;
import com.hacademy.discordbot.parse.MessageFactory.User;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AionRequestSender {
	private static OkHttpClient client = new OkHttpClient();
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static Response get(String url) throws IOException {
		Request.Builder builder = new Request.Builder().url(url).get();
		return client.newCall(builder.build()).execute();
	}
	public static Response post(String url, String json) throws IOException {
		RequestBody body = RequestBody.Companion.create(MediaType.get("application/json"), json);
		Request request = new Request.Builder().url(url).post(body).build();
		return client.newCall(request).execute();
	}
	public static Response put(String url, String json) throws IOException {
		RequestBody body = RequestBody.Companion.create(MediaType.get("application/json"), json);
		Request request = new Request.Builder().url(url).put(body).build();
		return client.newCall(request).execute();
	}
	
	public static SearchResult findByUsername(String username) {
		try {
			String url = String.format("https://api-aion.plaync.com/search/v1/characters?classId=&pageSize=20&query=%s&raceId=&serverId=0&sort=rank", username);
			Response response = get(url);
			if(!response.isSuccessful() || response.body() == null) {
				throw new Exception();
			}
			
			return mapper.readValue(response.body().string(), SearchResult.class);
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public static SearchUnit findByServerNameAndUsername(User user) {
		return findByServerNameAndUsername(user.getServerName(), user.getUserName());
	}
	public static SearchUnit findByServerNameAndUsername(String serverName, String username) {
		SearchResult result = findByUsername(username);
		for(SearchUnit unit : result.getDocuments()) {
			if(unit.getServerName().equals(serverName)) {
				return unit;
			}
		}
		return null;
	}
	
	public static CharacterInformation findByServerCodeAndUserCode(int serverCode, int userCode) {
		try {
			String url = String.format("https://api-aion.plaync.com/game/v2/classic/characters/server/%d/id/%d", serverCode, userCode);
			Response response = get(url);
			if(!response.isSuccessful() || response.body() == null) {
				throw new Exception();
			}
			
			return mapper.readValue(response.body().string(), CharacterInformation.class);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public static CharacterDetailInformation findEquipmentByServerCodeAndUserCode(int serverCode, int userCode) {
		try {
			String url = String.format("https://api-aion.plaync.com/game/v2/classic/merge/server/%d/id/%d", serverCode, userCode);
			System.out.println(url);
			Map<String, String[]> map = new HashMap<>();
			map.put("keyList", new String[] {"character_stats", "character_equipments", "character_abyss", "character_stigma"});
			Response response = put(url, mapper.writeValueAsString(map));
			if(!response.isSuccessful() || response.body() == null) {
				throw new Exception();
			}

			return mapper.readValue(response.body().string(), CharacterDetailInformation.class);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
