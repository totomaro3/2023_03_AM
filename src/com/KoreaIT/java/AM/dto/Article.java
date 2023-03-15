package com.KoreaIT.java.AM.dto;

import com.KoreaIT.java.AM.util.Util;

public class Article extends Dto {
	public String title;
	public String content;
	public int memberId;
	public int views;
	
	public Article(int id, String title, String content, int memberId) {
		this.id = id;
		this.regDate = Util.getNow();
		this.modDate = regDate;
		this.title = title;
		this.content = content;
		this.memberId = memberId;
		this.views = 0;
	}
}
