package com.KoreaIT.java.AM.dao;

import java.util.*;

import com.KoreaIT.java.AM.dto.Member;

public class MemberDao extends Dao {
	
	public List<Member> members;
	
	public MemberDao() {
		members = new ArrayList<>();
	}
	
	public void add(Member member) {
		members.add(member);
	}
	
	public void remove(Member member) {
		members.remove(member);
	}
	
	public int getLastId() {
		return lastId;
	}
	
	public int setNewId() {
		return lastId+1;
	}

	public List<Member> getmembers(String searchKeyword) {
		if (searchKeyword.length() > 0) {
			System.out.println("검색어 : " + searchKeyword);
			List<Member> listMembers = new ArrayList<>();

			for (Member member : members) {
				if (member.loginId.contains(searchKeyword)) {
					listMembers.add(member);
				}
			}
			return listMembers;
		}
		return members;
	}
	
	public Member getMemberById(int memberId) {
		for (Member member : members) {
			if (member.loginId.equals(memberId)) {
				return member;
			}
		}
		return null;
	}
	
	public Member getMemberByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
			i++;
		}
		return null;
	}

	public String getMemberNameById(int memberId) {
		for (Member member : members) {
			if (member.loginId.equals(memberId)) {
				return member.name;
			}
		}
		return null;
	}

	public List<Member> getMembers() {
		return members;
	}
}
