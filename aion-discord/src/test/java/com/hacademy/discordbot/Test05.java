package com.hacademy.discordbot;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacademy.discordbot.entity.SearchResult;
import com.hacademy.discordbot.entity.SearchUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Test05 {
	public static void main(String[] args) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request.Builder builder = new Request.Builder().url("http://aiondatabase.net/query.php?a=weapon&l=kr&_=1630389057507").get();
		
		Response response = client.newCall(builder.build()).execute();
		
		if(response.isSuccessful()) {
			if(response.body() != null) {
				String jsonString = response.body().string();
				System.out.println(jsonString);
			}
		}
		else {
			System.out.println("error");
		}
	}
}
