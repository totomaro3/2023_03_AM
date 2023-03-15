package com.KoreaIT.java.AM.service;

import java.util.List;

import com.KoreaIT.java.AM.Container.Container;
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
		
		List<Member> members = memberDao.getArticles(searchKeyword);
		
		return members;
	}

}
