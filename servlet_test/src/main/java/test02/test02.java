package test02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class test02 extends HttpServlet {
  
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // 2-9단 & 1-9난수를 가져와서 구구단 만들기
	  // 랜덤문제 만들어서 화면에 출력해서
	  // 텍스트 상자에 답과 맞으면 정답 alert, 틀리면 오답 alert
	  
	  request.setCharacterEncoding("UTF-8");

	  String strDan = request.getParameter("dan");
	  String strNum = request.getParameter("num");
	  String strResult = request.getParameter("result");
	  
	  int dan = 0; 
	  int num = 0; 
	  int result = 0; 
	  if(strDan != null && !strDan.isEmpty()) {
	    dan = Integer.parseInt(strDan);
	  }
	  if(strNum != null && !strNum.isEmpty()) {
	    num = Integer.parseInt(strNum);
	  }
	  if(strResult != null && !strResult.isEmpty()) {
	    result = Integer.parseInt(strResult);
	  }
	  
	  response.setContentType("text/html; charset=UTF-8");
	  
	  PrintWriter out = response.getWriter();
	  
	  out.println("<script>");
	  if(dan * num == result) {
	    out.println("alert('정답입니다.')");
	  } else {
	    out.println("alert('오답입니다.')");
	  }
	  out.println("location.href='/test/test02/02.html'");
    out.println("</script>");
	  out.flush();
	  out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
