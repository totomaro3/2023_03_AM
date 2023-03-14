package com.KoreaIT.java.AM.controller;

import java.util.*;

import com.KoreaIT.java.AM.dto.Member;

public class MemberController extends Controller {
	
	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	
	
	public MemberController(Scanner sc) {
		this.members = new ArrayList<Member>();
		this.sc = sc;
	}
	
	public void doAction(String actionMethodName, String command) {
		this.actionMethodName = actionMethodName;
		this.command = command;
		
		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "profile":
			doProfile();
			break;
		case "list":
			doList();
			break;
		default:
			System.out.println("해당 기능은 사용할 수 없습니다.");
		}
	}
	
	private void doJoin(){
		String id = "";
		String pw = "";
		String name = "";
		
		while(true) {
			System.out.print("가입할 아이디 : ");
			id = sc.nextLine();
			
			if(getMemberById(id) == null) break;
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
	
	private void doLogin() {
		
		System.out.print("로그인 아이디 : ");
		String id = sc.nextLine();
		System.out.print("로그인 비밀번호 : ");
		String pw = sc.nextLine();
		
		Member member = getMemberById(id);
		
		if(member == null) {
			System.out.println("아이디가 일치하지 않습니다.");
			return;
		}
		
		if(member.loginPw.equals(pw) == false) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		loginMember = member;
		System.out.printf("로그인 성공. %s님.\n",loginMember.name);
	}
	
	private void doLogout() {
		
		loginMember = null;
		System.out.println("로그아웃 성공");
		
	}
	
	private void doProfile() {
		
		System.out.println("== 현재 로그인 중인 회원 정보 ==");
		System.out.println("로그인 아이디 : "+ loginMember.loginId);
		System.out.println("이름 : "+ loginMember.name);
	}
	
	private void doList() {
		if(members.size() == 0) {
			System.out.println("멤버가 없습니다.");
			return ;
			}
		
		String searchKeyword = command.substring("member list".length()).trim();
		
		List<Member> listmembers = members;
		
		if (searchKeyword.length() > 0) {
			System.out.println("searchKeyword : " + searchKeyword);
			listmembers = new ArrayList<>();

			for (Member member : members) {
				if (member.loginId.contains(searchKeyword)) {
					listmembers.add(member);
				}
			}
			if (listmembers.size() == 0) {
				System.out.println("검색 결과가 없습니다.");
				return ;
			}
		}
		
		System.out.println("번호\t/\t아이디\t");
		for(int i=listmembers.size()-1;i>=0;i--) {
			Member member = listmembers.get(i);
			System.out.printf(" %d\t/\t%s\t\n",member.id,member.loginId);
		}
	}
	
	private Member getMemberById(String id) {
		for (Member member : members) {
			if (member.loginId.equals(id)) {
				return member;
			}
		}
		return null;
	}
	
	public void makeTestData() {
		for(int i = 0; i<3; i++)
		{
			String id = "id"+(i+1);
			String pw = "pw"+(i+1);
			String name = "name"+(i+1);
			Member member = new Member(id, pw, name);
			
			members.add(member);
		}
	}
}
