package com.KoreaIT.java.AM.dto;

import com.KoreaIT.java.AM.util.Util;

public class Member extends Dto {
	public static int lastId = 0;
	public String loginId;
	public String loginPw;
	public String name;
	
	public Member(int id, String loginId, String loginPw, String name) {
		this.id = ++lastId;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.regDate = Util.getNow();
		this.modDate = regDate;
	}
}