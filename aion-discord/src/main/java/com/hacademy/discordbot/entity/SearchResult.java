package com.hacademy.discordbot.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SearchResult {
	private String collectionId;
	private int totalCount;
	private int resultCount;
	private List<SearchUnit> documents;
}
