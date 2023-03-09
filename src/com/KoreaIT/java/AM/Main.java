package com.KoreaIT.java.AM;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Article> articles = new ArrayList<Article>();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("==게시판 프로그램==");
		
		while (true) {
			
			System.out.print("명령어 > ");
			String command = sc.nextLine().trim();//양쪽 여백 떼내기
			
			// 조회
			if(command.equals("list")) {
				if(articles.size() == 0) {System.out.print("게시글이 없습니다.\n");}
				else {
					System.out.println("번호\t/\t제목\t/\t조회");
					for(int i=articles.size()-1;i>=0;i--) {
						Article article = articles.get(i);
						System.out.printf(" %d\t/\t%s\t/\t %d\n",article.id,article.title,article.views);
					}
				}
			}
			// 작성
			else if(command.equals("write")) {
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String content = sc.nextLine();
				
				Article article = new Article(title, content);
				articles.add(article);
				System.out.printf("%d번글이 생성되었습니다.\n",article.id);
			}
			// 보기
			else if(command.startsWith("detail")) {
				String[] splitCommand = command.split(" ");
				
				if(splitCommand.length < 2) {
					System.out.println("명령어를 확인해 주세요");
					continue;
				}
				
				int id = Integer.parseInt(splitCommand[1]);
				
				if(articles.size() == 0) {System.out.println(id+"번 게시물은 존재하지 않습니다.");}
				
				for(int i = 0;i < articles.size();i++) {
					Article article = articles.get(i);
					if(article.id == id){
						System.out.println("번호 : "+article.id);
						System.out.println("날짜 : "+article.regDate);
						System.out.println("수정 : "+article.modDate);
						System.out.println("제목 : "+article.title);
						System.out.println("내용 : "+article.content);
						
						article.views++;
					} else {
						System.out.println(id+"번 게시물은 존재하지 않습니다.");
					}
				} 
			}
			// 수정
			else if(command.startsWith("modify")) {
				String[] splitCommand = command.split(" ");
				
				if(splitCommand.length < 2) {
					System.out.println("명령어를 확인해 주세요");
					continue;
				}
				
				int id = Integer.parseInt(splitCommand[1]);

				if(articles.size() == 0) {System.out.println(id+"번 게시물은 존재하지 않습니다.");}
				
				for(int i = 0;i < articles.size();i++) {
					Article article = articles.get(i);
					if(article.id == id){
						System.out.print("제목 : ");
						article.title = sc.nextLine();
						System.out.print("내용 : ");
						article.content = sc.nextLine();
						article.modDate = Util.getNow();
						
						System.out.printf("%d번글이 수정되었습니다.\n",article.id);
					} else {
						System.out.println(id+"번 게시물은 존재하지 않습니다.");
					}
				} 
			}
			// 삭제
			else if(command.startsWith("delete")) {
				String[] splitCommand = command.split(" ");
				
				if(splitCommand.length < 2) {
					System.out.println("명령어를 확인해 주세요");
					continue;
				}
				
				int id = Integer.parseInt(splitCommand[1]);

				if(articles.size() == 0) {System.out.println(id+"번 게시물은 존재하지 않습니다.");}
				
				for(int i = 0;i < articles.size();i++) {
					Article article = articles.get(i);
					if(article.id == id){
						articles.remove(i);
						System.out.println(id+"번 게시물이 삭제되었습니다.");
					} else {
						System.out.println(id+"번 게시물은 존재하지 않습니다.");
					}
				}
			}
			// 종료
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
	String regDate;
	String modDate;
	String title;
	String content;
	int views;
	
	Article(String title, String content) {
		this.id = ++lastArticleId;
		this.regDate = Util.getNow();
		this.modDate = "";
		this.title = title;
		this.content = content;
		this.views = 0;
	}
}

class Util {
	/**현재 날짜와 시간 리턴 */
	public static String getNow() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}











//git add .
//git status
//git commit -m "~"
//git push origin master