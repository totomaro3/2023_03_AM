package com.KoreaIT.java.AM.service;

import java.util.List;

import com.KoreaIT.java.AM.container.Container;
import com.KoreaIT.java.AM.dao.ArticleDao;
import com.KoreaIT.java.AM.dao.MemberDao;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;

public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = Container.memberDao;
	}
	
	public List<Member> listMembers(String searchKeyword) {
		List<Member> members = memberDao.getmembers(searchKeyword);
		return members;
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public int setNewId() {
		return  memberDao.setNewId();
	}

	public void add(Member member) {
		memberDao.add(member);
	}

	public void remove(Member member) {
		memberDao.remove(member);
	}

	public String getMemberNameById(int memberId) {
		
		return memberDao.getMemberNameById(memberId);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}
	
	public List<Member> getMembers() {
		return memberDao.getMembers();
	}
}
