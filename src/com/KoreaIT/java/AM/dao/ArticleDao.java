package com.KoreaIT.java.AM.dao;

import java.util.*;

import com.KoreaIT.java.AM.dto.Article;

public class ArticleDao extends Dao {
	public List<Article> articles;
	
	public ArticleDao() {
		articles = new ArrayList<>();
	}
	
	public void add(Article article) {
		articles.add(article);
	}
	
	public int getLastId() {
		return lastId;
	}
	
	public int setNewId() {
		return lastId+1;
	}
	
	public List<Article> listArticles(String searchKeyword) {
		if (searchKeyword.length() > 0) {
			System.out.println("검색어 : " + searchKeyword);
			List<Article> listArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					listArticles.add(article);
				}
			}
			return listArticles;
		}
		return articles;
	}
	
	public Article getArticle(int id) {
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}
	
	public void remove(Article article) {
		articles.remove(article);
	}
	
	
}
