package com.KoreaIT.java.AM.controller;

import java.util.*;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;

public class ArticleController extends Controller {
	
	private List<Article> articles;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	
	public ArticleController(List<Article> articles, Scanner sc) {
		this.articles = articles;
		this.sc = sc;
	}
	
	public void doAction(String actionMethodName, String command ) {
		this.actionMethodName = actionMethodName;
		this.command = command;
		
		switch (actionMethodName) {
		case "list":
			doList();
			break;
		case "write":
			doWrite();
			break;
		case "detail":
			doDetail();
		case "modify":
			doModify();
		case "delete":
			doDelete();
		}
	}
	
	public void doList(String command) {
		if(articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return ;
			}
		
		String searchKeyword = command.substring("list".length()).trim();
		
		List<Article> listArticles = articles;
		
		if (searchKeyword.length() > 0) {
			System.out.println("searchKeyword : " + searchKeyword);
			listArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					listArticles.add(article);
				}
			}
			if (listArticles.size() == 0) {
				System.out.println("검색 결과가 없습니다.");
				return ;
			}
		}
		
		System.out.println("번호\t/\t제목\t/\t조회");
		for(int i=listArticles.size()-1;i>=0;i--) {
			Article article = listArticles.get(i);
			System.out.printf(" %d\t/\t%s\t/\t %d\n",article.id,article.title,article.views);
		}
	}
	
	public void doWrite() {
		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String content = sc.nextLine();
		
		Article article = new Article(title, content);
		
		articles.add(article);
		System.out.printf("%d번글이 생성되었습니다.\n",article.id);
	}
	
	public void doDetail(String command) {
		String[] splitCommand = command.split(" ");
		
		if(splitCommand.length < 2) {
			System.out.println("명령어를 확인해 주세요.");
			return ;
		}
		
		int id = Integer.parseInt(splitCommand[1]);
		
		Article foundArticle = getArticle(id);
		foundArticle.views++;
		System.out.println("번호 : "+foundArticle.id);
		System.out.println("날짜 : "+foundArticle.regDate);
		System.out.println("수정 : "+foundArticle.modDate);
		System.out.println("제목 : "+foundArticle.title);
		System.out.println("내용 : "+foundArticle.content);
		System.out.println("조회 : "+foundArticle.views);
	}
	
	public void doModify(String command) {
		String[] splitCommand = command.split(" ");
		
		if(splitCommand.length < 2) {
			System.out.println("명령어를 확인해 주세요.");
			return ;
		}
		
		int id = Integer.parseInt(splitCommand[1]);
		
		Article foundArticle = getArticle(id);
		System.out.print("새 제목 : ");
		foundArticle.title = sc.nextLine();
		System.out.print("새 내용 : ");
		foundArticle.content = sc.nextLine();
		foundArticle.modDate = Util.getNow();
		
		System.out.printf("%d번글이 수정되었습니다.\n",foundArticle.id);
	}
	
	public void doDelete(String command) {
		String[] splitCommand = command.split(" ");
		
		if(splitCommand.length < 2) {
			System.out.println("명령어를 확인해 주세요.");
			return ;
		}
		
		int id = Integer.parseInt(splitCommand[1]);

		if(command.startsWith("delete")) {
			int foundIndex = getArticleIndex(id);
			articles.remove(foundIndex);
			System.out.println(id+"번 게시물이 삭제되었습니다.");
		}
	}
	
	private int getArticleIndex(int id) {
		
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.id == id) {
				return i;
			}
		}
		return -1;
	}

	private Article getArticle(int id) {
		int index = getArticleIndex(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}
	
	public void makeTestData() {
		for(int i = 0; i<3; i++)
		{
			String title = "제목"+(i+1);
			String content = "내용"+(i+1);
			Article article = new Article(title, content);
			article.views = (i + 1) * 10;
			
			articles.add(article);
		}
		
		/*
		for(int i = 0; i<3; i++)
		{
			String id = "아이디"+(i+1);
			String pw = "비밀번호"+(i+1);
			String name = "이름"+(i+1);
			Member member = new Member(id, pw, name);
			
			members.add(member);
		}
		*/

		System.out.println("테스트를 위한 데이터를 생성합니다.");
	}
}
