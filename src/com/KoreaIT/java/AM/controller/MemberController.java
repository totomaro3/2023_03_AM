package com.KoreaIT.java.AM.controller;

import java.util.*;

import com.KoreaIT.java.AM.Container.Container;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.service.MemberService;

public class MemberController extends Controller {
	
	//private List<Member> members;
	private Scanner sc;
	private String command;
	private MemberService memberService;
	
	public MemberController(Scanner sc) {
		//this.members = Container.memberDao.members;
		this.memberService = Container.memberService;
		this.sc = sc;
	}
	
	public void doAction(String actionMethodName, String command) {
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
		String loginId = null;
		String loginPw = null;
		String name = null;
		
		while(true) {
			System.out.print("가입할 아이디 : ");
			loginId = sc.nextLine();
			
			if(loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요");
				continue;
			}
			
			if(getMemberById(loginId) == null) break;
			else {
				System.out.println("동일한 ID가 있습니다.");
				continue;
				}
		}
		
		while(true) {
			System.out.print("가입할 비밀번호 : ");
			loginPw = sc.nextLine();
			
			if(loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요");
				continue;
			}
			
			System.out.print("가입할 비밀번호 재확인 : ");
			String confirmPw = sc.nextLine();
			if(!loginPw.equals(confirmPw)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			break;
		}
		
		while(true) {
			System.out.print("가입할 이름 : ");
			name = sc.nextLine();
			
			if(name.length() == 0) {
				System.out.println("이름을 입력해주세요");
				continue;
			}
			break;
		}
		
		int id = Container.memberDao.setNewId();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		
		Member member = new Member(id, loginId, loginPw,name);
		Container.memberDao.add(member);
		System.out.println(member.id+"번 회원이 가입되었습니다.");
	}
	
	private void doLogin() {
		
		String id = null;
		String pw = null;
		
		System.out.print("로그인 아이디 : ");
		id = sc.nextLine();
		while (id.length() == 0) {
			System.out.println("아이디를 입력해 주십시오");
			System.out.print("로그인 아이디 : ");
			id = sc.nextLine();
		}
		
		while (true) {
			System.out.print("로그인 비밀번호 : ");
			pw = sc.nextLine();
			
			if(pw.length() == 0) {
				System.out.println("비밀번호를 입력해 주십시오");
				continue;
			}
			break;
		}
		
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
		
		String searchKeyword = command.substring("member list".length()).trim();
		
		List<Member> listmembers = memberService.listMembers(searchKeyword);
		
		if (listmembers.size() == 0) {
			System.out.println("회원이 없습니다.");
			return;
		}
		
		System.out.println("번호\t|아이디\t|이름");
		for(int i=listmembers.size()-1;i>=0;i--) {
			Member member = listmembers.get(i);
			System.out.printf(" %d\t|%s\t|%s\n",member.id,member.loginId,member.name);
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
			String loginId = ""+(i+1);
			String loginPw = ""+(i+1);
			String name = "name"+(i+1);
			
			int id = Container.memberDao.setNewId();     
			Member member = new Member(id, loginId, loginPw, name);
			
			Container.memberDao.add(member);
		}
		System.out.println("테스트를 위한 멤버 데이터를 생성합니다.");
	}
}
