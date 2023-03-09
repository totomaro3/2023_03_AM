package com.KoreaIT.java.AM;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		int lastArticleId = 0;
		ArrayList<Article> articles = new ArrayList<Article>();
		
		System.out.println("==게시판 프로그램==");
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine();
			
			if(command.equals("article list")) {
				if(articles.size() == 0) {System.out.print("게시글이 없습니다.\n");}
				else {
					System.out.println("번호\t/\t제목");
					for(int i = 0; i<articles.size();i++) {
						System.out.printf("%d\t/\t%s\n",articles.get(i).id,articles.get(i).title);
					}
				}
			}
			else if(command.equals("article write")) {
				
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String content = sc.nextLine();
				Article article = new Article(title, content);
				articles.add(article);
				System.out.printf("%d번글이 생성되었습니다.\n",article.id);
			}
			else if(command.equals("exit")) {break;}
			else if(command.equals("")) {System.out.print("명령어를 입력해주세요.\n");}
			else System.out.print("존재하지 않는 명령어 입니다.\n");
		}
		
		System.out.println("==프로그램 종료==");
		
		sc.close();

	}

}

class Article {
	static int lastArticleId = 0;
	int id;
	String title;
	String content;
	
	static { //프로그램이 시작되면 시작
		
	}
	
	Article() {
		
	}
	
	Article(String title, String content) {
		this.id = ++lastArticleId;
		this.title = title;
		this.content = content;
	}
}

//git add .
//git status
//git commit -m "~"
//git push origin master