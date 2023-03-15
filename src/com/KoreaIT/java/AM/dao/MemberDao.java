package com.KoreaIT.java.AM.dao;

import java.util.*;

import com.KoreaIT.java.AM.dto.member;
import com.KoreaIT.java.AM.dto.Member;

public class MemberDao extends Dao {
	
	public List<Member> members;
	
	public MemberDao() {
		members = new ArrayList<>();
	}
	
	public void add(Member member) {
		members.add(member);
		lastId++;
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
}
