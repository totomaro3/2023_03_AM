package com.KoreaIT.java.AM.controller;

import java.util.*;

import com.KoreaIT.java.AM.dto.Member;

public class MemberController extends Controller {
	
	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
	}
	
	public void doAction(String actionMethodName, String command ) {
		this.actionMethodName = actionMethodName;
		this.command = command;
		
		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		}
	}
	
	public void doJoin(){
		String id = "";
		String pw = "";
		String name = "";
		
		while(true) {
			System.out.print("가입할 아이디 : ");
			id = sc.nextLine();
			
			if(isJoinableId(id)) break;
			else {
				System.out.println("동일한 ID가 있습니다.");
				continue;
				}
		}
		
		while(true) {
			System.out.print("가입할 비밀번호 : ");
			pw = sc.nextLine();
			System.out.print("가입할 비밀번호 재확인 : ");
			String confirmPw = sc.nextLine();
			if(!pw.equals(confirmPw)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			break;
		}
		
		System.out.print("가입할 이름 : ");
		name = sc.nextLine();
		
		Member member = new Member(id,pw,name);
		members.add(member);
		System.out.println(member.id+"번 회원이 가입되었습니다.");
	}
	
	boolean isJoinableId(String id) {
		for (Member member : members) {
			if (member.loginId.equals(id)) {
				return false;
			}
		}
		return true;
	}
}
