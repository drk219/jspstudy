package test03;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class test03 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // select 옵션중에 시간을 요청하면 시간을 팝업창에, 날짜를 요청하면 날짜를 팝업창에

	  // 요청
	  request.setCharacterEncoding("UTF-8");
	  
	  // 요청 파라미터
	  String type = request.getParameter("type");
	  
	  String result = null;
	  switch(type) {
	  case "1":
	    result = LocalDate.now().toString();
	    break;
	  case "2":
	    result = LocalTime.now().toString();
	    result = result.substring(0, result.indexOf("."));
	    break;
	  }
    
    // 응답
    response.setContentType("text/html; charset=UTF-8");
    
    // 출력
    PrintWriter out = response.getWriter();
    
    out.println("<script>");
    out.println("alert('요청 결과는 " + result + " 입니다.');");
    out.println("history.back()");  // 이전 화면 돌아가기
    out.println("</script>");
    out.flush();
    out.close();
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
