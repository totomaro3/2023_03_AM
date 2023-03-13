package com.KoreaIT.java.AM;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.Controller;
import com.KoreaIT.java.AM.controller.MemberController;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;

public class App {
	
	List<Article> articles;
	List<Member> members;
	
	public App() {
		articles = new ArrayList<Article>();
		members = new ArrayList<Member>();
	}
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(members,sc);
		ArticleController articleController = new ArticleController(articles,sc);
		Controller controller;
		
		System.out.println("==게시판 프로그램==");
		
		articleController.makeTestData();
		
		while (true) {
			
			System.out.print("명령어 > ");
			String command = sc.nextLine().trim();
			
			if(command.equals("exit")) {break;}
			if(command.equals("")) {System.out.println("명령어를 입력해주세요.");}
			
			String[] commandDiv = command.split(" ");
			String controllerName = commandDiv[0];
			if (commandDiv.length == 1) {
				System.out.println("명령어를 확인해주세요.");
				continue;
			}
			String actionMethodName = commandDiv[1];
			
			
			
			controller = null;
			
			if(controllerName.equals("article")) {
				controller = articleController;
			}
			else if(controllerName.equals("controller")) {
				controller = memberController;
			}
			else System.out.println("존재하지 않는 명령어 입니다.");
			
			controller.doAction(actionMethodName, command);
			
			
			
			
			
			
			
			if(command.startsWith("list")) {
				articleController.doList(command);
			}

			else if(command.equals("write")) {
				articleController.doWrite();
			}

			else if(command.startsWith("detail")){
				articleController.doDetail(command);
			}
			
			else if(command.startsWith("modify")){
				articleController.doModify(command);
			}
			
			else if(command.startsWith("delete")){
				articleController.doDelete(command);
			}
			
			else if(command.equals("join")) {
				memberController.doJoin();
			}
		}
		
		System.out.println("==프로그램 종료==");
		sc.close();
	}

}


