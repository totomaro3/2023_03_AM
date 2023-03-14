package com.KoreaIT.java.AM.controller;

import com.KoreaIT.java.AM.dto.Member;

public abstract class Controller {
	
	public abstract void doAction(String actionMethodName, String command);
	
	public abstract void makeTestData();
	
	protected static Member loginMember = null;
	
	public static boolean isLogin() {
		return loginMember != null;
	}
	
}
