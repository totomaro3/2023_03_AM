package com.KoreaIT.java.AM.dto;

import com.KoreaIT.java.AM.util.Util;

public class Member extends Dto {
	public static int lastMemberId = 0;
	public int id = 0;
	public String loginId;
	public String loginPw;
	public String name;
	public String regDate;
	
	public Member(String loginId, String loginPw, String name) {
		this.id = ++lastMemberId;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.regDate = Util.getNow();
	}
}