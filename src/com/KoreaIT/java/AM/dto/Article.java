package com.KoreaIT.java.AM.dto;

import com.KoreaIT.java.AM.util.Util;

public class Article extends Dto{
	public static int lastArticleId = 0;
	
	public String title;
	public String content;
	public int views;
	
	public Article(String title, String content) {
		this.id = ++lastArticleId;
		this.regDate = Util.getNow();
		this.modDate = regDate;
		this.title = title;
		this.content = content;
		this.views = 0;
	}
}
