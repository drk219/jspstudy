package test02;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class test02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // 2-9까지 2개의 난수를 가져와서 구구단 만들기
	  // 랜덤문제 만들어서 html화면에 출력해서
	  // 텍스트 상자에 답과 맞으면 정답 alert, 틀리면 오답 alert
	  
	  int num1 = (int) ((Math.random() * 9) + 2);
	  int num2 = (int) ((Math.random() * 9) + 2);
	  
	  String quiz = num1 + "x" + num2 + "=";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
