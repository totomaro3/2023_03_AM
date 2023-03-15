package com.KoreaIT.java.AM.service;

import java.util.List;

import com.KoreaIT.java.AM.Container.Container;
import com.KoreaIT.java.AM.dao.ArticleDao;
import com.KoreaIT.java.AM.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	public List<Article> listArticles(String searchKeyword) {
		
		List<Article> articles = articleDao.getArticles(searchKeyword);
		
		return articles;
	}

	public int setNewId() {
		int id = articleDao.setNewId();
		
		return id;
	}

	public void add(Article article) {
		articleDao.add(article);
	}

	public Article getArticle(int id) {
		
		return articleDao.getArticle(id);
	}

	public void remove(Article article) {
		
		articleDao.remove(article);
	}
}
