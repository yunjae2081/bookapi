package com.example.demo.domain;

public class PopularSearch {

	private String SearchWord;
	private Long cnt;

	public PopularSearch(String SearchWord, Long cnt) {
		this.SearchWord = SearchWord;
		this.cnt = cnt;
	}

	public String getSearchWord() {
		return SearchWord;
	}

	public Long getCnt() {
		return cnt;
	}

}
