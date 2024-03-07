package test03;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class test03 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // select 옵션중에 시간을 요청하면 시간을 팝업창에, 날짜를 요청하면 날짜를 팝업창에
	  // 
	  
	  String time = DateTimeFormatter.ofPattern("HH:mm:SSS").format(LocalDateTime.now());
	  String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	  
	   // 요청
    request.setCharacterEncoding("UTF-8");
    String time = request.getParameter("time");
    
    // 응답
    response.setContentType("text/html; charset=UTF-8");
    
    PrintWriter out = response.getWriter();
    
    out.println("<script>");
    out.println("alert('요청 결과는 " + time + "입니다.');");
    out.println("</script>");
    out.flush();
    out.close();
	  
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
