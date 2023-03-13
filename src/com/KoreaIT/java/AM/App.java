package com.KoreaIT.java.AM;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class App {
	
	List<Article> articles;
	List<Member> members;
	
	public App() {
		articles = new ArrayList<Article>();
		members = new ArrayList<Member>();
	}
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("==게시판 프로그램==");
		
		makeTestData();
		
		while (true) {
			
			System.out.print("명령어 > ");
			String command = sc.nextLine().trim();
			
			if(command.startsWith("list")) {
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
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
						continue;
					}
				}
				
				System.out.println("번호\t/\t제목\t/\t조회");
				for(int i=listArticles.size()-1;i>=0;i--) {
					Article article = listArticles.get(i);
					System.out.printf(" %d\t/\t%s\t/\t %d\n",article.id,article.title,article.views);
				}
			}

			else if(command.equals("write")) {
				
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String content = sc.nextLine();
				
				Article article = new Article(title, content);
				
				articles.add(article);
				System.out.printf("%d번글이 생성되었습니다.\n",article.id);
			}

			else if(command.startsWith("detail")){
				
				String[] splitCommand = command.split(" ");
				
				if(splitCommand.length < 2) {
					System.out.println("명령어를 확인해 주세요.");
					continue;
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
			
			else if(command.startsWith("modify")){
				
				String[] splitCommand = command.split(" ");
				
				if(splitCommand.length < 2) {
					System.out.println("명령어를 확인해 주세요.");
					continue;
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
			
			else if(command.startsWith("delete")){
				
				String[] splitCommand = command.split(" ");
				
				if(splitCommand.length < 2) {
					System.out.println("명령어를 확인해 주세요.");
					continue;
				}
				
				int id = Integer.parseInt(splitCommand[1]);

				if(command.startsWith("delete")) {
					int foundIndex = getArticleIndex(id);
					articles.remove(foundIndex);
					System.out.println(id+"번 게시물이 삭제되었습니다.");
				}
			}
			

			else if(command.equals("join")) {
				
				String id = "";
				String pw = "";
				String name = "";
				
				while(true) {
					
					System.out.print("가입할 아이디 : ");
					id = sc.nextLine();
					
					if(isJoinableId(id)) break;
					else {
						System.out.println("동일한 ID가 있습니다.");
						continue;
						}
				}
				
				while(true) {
					
					System.out.print("가입할 비밀번호 : ");
					pw = sc.nextLine();
					System.out.print("가입할 비밀번호 재확인 : ");
					String confirmPw = sc.nextLine();
					if(!pw.equals(confirmPw)) {
						System.out.println("비밀번호가 일치하지 않습니다.");
						continue;
					}
					break;
				}
				
				System.out.print("이름 : ");
				name = sc.nextLine();
				
				Member member = new Member(id,pw,name);
				members.add(member);
				System.out.println(member.id+"번 회원이 가입되었습니다.");
				
			}
			
			else if(command.equals("exit")) {break;}
			
			else if(command.equals("")) {System.out.println("명령어를 입력해주세요.");}
			else System.out.println("존재하지 않는 명령어 입니다.");
		}
		
		System.out.println("==프로그램 종료==");
		sc.close();
	}
	
	void makeTestData() {
		for(int i = 0; i<3; i++)
		{
			String title = "제목"+(i+1);
			String content = "내용"+(i+1);
			Article article = new Article(title, content);
			article.views = (i + 1) * 10;
			
			articles.add(article);
		}

		System.out.println("테스트 데이터 생성이 완료되었습니다.");
	}

	boolean isJoinableId(String id) {
		for (Member member : members) {
			if (member.loginId.equals(id)) {
				return false;
			}
		}
		return true;
	}

	int getArticleIndex(int id) {
		
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.id == id) {
				return i;
			}
		}
		return -1;
	}

	Article getArticle(int id) {
		int index = getArticleIndex(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}
}


