package com.KoreaIT.java.AM;
import java.util.Scanner;

import com.KoreaIT.java.AM.controller.Controller;
import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.MemberController;
import com.KoreaIT.java.AM.controller.ExportController;

public class App {
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		Controller controller;
		
		System.out.println("==게시판 프로그램==");
		
		articleController.makeTestData();
		memberController.makeTestData();
		
		while (true) {
			
			System.out.print("명령어 > ");
			String command = sc.nextLine().trim();
			
			if(command.equals("exit")) {break;}
			if(command.equals("")) {
				System.out.println("명령어를 입력해주세요.");
				continue;
				}
			
			String[] commandDiv = command.split(" ");
			String controllerName = commandDiv[0];
			if (commandDiv.length == 1) {
				System.out.println("명령어를 확인해주세요.");
				continue;
			}
			String actionMethodName = commandDiv[1];
			
			String checkLogin = controllerName + "/" + actionMethodName;
			
			controller = null;
			
			if(controllerName.equals("article")) {
				controller = articleController;
			}
			else if(controllerName.equals("member")) {
				controller = memberController;
			}
			else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			
			switch(checkLogin) {
			case "article/write":
			case "article/modify":
			case "article/delete":
			case "member/logout":
			case "member/profile":
			case "member/modify":
			case "member/delete":
				if(Controller.isLogin() == false) {
					System.out.println("먼저 로그인을 해주세요.");
					continue;
				}
			}
			
			switch(checkLogin) {
			case "member/login":
			case "member/join":
				if(Controller.isLogin()) {
					System.out.println("먼저 로그아웃을 해주세요.");
					continue;
				}
			}
			
			controller.doAction(actionMethodName, command);
		}
		
		System.out.println("==프로그램 종료==");
		sc.close();
	}

}


