package com.KoreaIT.java.AM;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		int lastArticleId = 0;
		
		System.out.println("==게시판 프로그램==");
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine();
			
			if(command.equals("article list")) {
				if(lastArticleId == 0) {System.out.print("게시글이 없습니다.\n");}
				else System.out.printf("%d개의 글이 존재합니다.\n",lastArticleId);
			}
			else if(command.equals("article write")) {
				int id = ++lastArticleId;
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String content = sc.nextLine();
				System.out.printf("%d번글이 생성되었습니다.\n",id);
			}
			else if(command.equals("exit")) {break;}
			else if(command.equals("")) {System.out.print("명령어를 입력해주세요.\n");}
			else System.out.print("존재하지 않는 명령어 입니다.\n");
		}
		
		System.out.println("==프로그램 종료==");
		
		sc.close();

	}

}

//git add .
//git status
//git commit -m "~"
//git push origin master