package com.KoreaIT.java.AM.controller;

import java.util.*;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class ArticleController extends Controller {
	
	private List<Article> articles;
	
	private Scanner sc;
	private String command;
	private String actionMethodName;
	
	public ArticleController( Scanner sc) {
		this.articles = new ArrayList<Article>();
		this.sc = sc;
	}
	
	public void doAction(String actionMethodName, String command) {
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
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("해당 기능은 사용할 수 없습니다.");
				
		}
	}
	
	private void doList() {
		if(articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return ;
			}
		
		String searchKeyword = command.substring("article list".length()).trim();
		
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
		
		System.out.println("번호\t|제목\t|작성자\t|조회");
		for(int i=listArticles.size()-1;i>=0;i--) {
			Article article = listArticles.get(i);
			System.out.printf(" %d\t|%s\t|%s\t| %d\n",article.id,article.title,article.memberId,article.views);
		}
	}
	
	private void doWrite() {
		
		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String content = sc.nextLine();
		
		Article article = new Article(title, content, loginMember.id);
		
		articles.add(article);
		System.out.printf("%d번글이 생성되었습니다.\n",article.id);
	}
	
	private void doDetail() {
		String[] splitCommand = command.split(" ");
		
		if(splitCommand.length < 2) {
			System.out.println("명령어를 확인해 주세요.");
			return ;
		}
		
		int id = Integer.parseInt(splitCommand[1]);
		
		Article foundArticle = getArticle(id);
		
		if (foundArticle == null) {
			System.out.println("글이 존재하지 않습니다.");
			return;
		}
		
		foundArticle.views++;
		System.out.println("번호 : "+foundArticle.id);
		System.out.println("날짜 : "+foundArticle.regDate);
		System.out.println("수정 : "+foundArticle.modDate);
		System.out.println("제목 : "+foundArticle.title);
		System.out.println("내용 : "+foundArticle.content);
		System.out.println("작성 : "+foundArticle.memberId);
		System.out.println("조회 : "+foundArticle.views);
	}
	
	private void doModify() {
		
		String[] splitCommand = command.split(" ");
		
		if(splitCommand.length < 2) {
			System.out.println("명령어를 확인해 주세요.");
			return ;
		}
		
		int id = Integer.parseInt(splitCommand[2]);
		
		Article foundArticle = getArticle(id);
		
		if (foundArticle == null) {
			System.out.println("글이 존재하지 않습니다.");
			return;
		}
		
		if(foundArticle.memberId != loginMember.id) {
			System.out.println("수정 권한이 없습니다.");
			return;
		}
		
		System.out.print("새 제목 : ");
		foundArticle.title = sc.nextLine();
		System.out.print("새 내용 : ");
		foundArticle.content = sc.nextLine();
		foundArticle.modDate = Util.getNow();
		
		System.out.printf("%d번글이 수정되었습니다.\n",foundArticle.id);
	}
	
	private void doDelete() {
		
		String[] splitCommand = command.split(" ");
		
		if(splitCommand.length < 2) {
			System.out.println("명령어를 확인해 주세요.");
			return;
		}
		
		int id = Integer.parseInt(splitCommand[2]);
		
		Article foundArticle = getArticle(id);
		
		if (foundArticle == null) {
			System.out.println("글이 존재하지 않습니다.");
			return;
		}
		
		if(foundArticle.memberId != loginMember.id) {
			System.out.println("삭제 권한이 없습니다.");
			return;
		}
		
		articles.remove(foundArticle);
		System.out.println(id+"번 게시물이 삭제되었습니다.");
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
			String title = "title"+(i+1);
			String content = "content"+(i+1);
			String name = "name"+(i+1);
			int memberId = i+1;
			Article article = new Article(title, content, memberId);
			article.views = (i + 1) * 10;
			
			articles.add(article);
		}

		System.out.println("테스트를 위한 데이터를 생성합니다.");
	}
}
