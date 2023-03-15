package com.KoreaIT.java.AM.dto;

import com.KoreaIT.java.AM.util.Util;

public class Member extends Dto {
	public String loginId;
	public String loginPw;
	public String name;
	
	public Member(int id, String loginId, String loginPw, String name) {
		this.id = id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.regDate = Util.getNow();
		this.modDate = regDate;
	}
}