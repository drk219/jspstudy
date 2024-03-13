package test01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class test01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // 입력된 정보들을 html 목록보기로 출력하기..?
	  
	  // 요청 입력하기
	  String id = request.getParameter("id");
	  String pw = request.getParameter("pw");
	  String name = request.getParameter("name");
	  String year = request.getParameter("year");
	  String month = request.getParameter("month");
	  String day = request.getParameter("day");
	  String gender = request.getParameter("gender");
	  String email = request.getParameter("email");
	  String country = request.getParameter("country");
	  String tel = request.getParameter("tel");
	  
	  response.setContentType("text/html; charset=UTF-8");
	  
	  PrintWriter out = response.getWriter();
	  
    // 응답
    out.println("<!DOCTYPE html>");
    out.println("<html lang=\"ko\">");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>회원가입정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<ul>");
    out.println("<li>아이디: " + id + "</li>");
    out.println("<li>비밀번호: " + pw + "</li>");
    out.println("<li>이름: " + name + "</li>");
    int m = month.isEmpty() ? 0 : Integer.parseInt(month);
    int d = day.isEmpty() ? 0 : Integer.parseInt(day);
    out.println("<li>생년월일: " + year + "년 " + String.format("%02d", m) + "월 " + String.format("%02d", d) + "일" + "</li>");
    out.println("<li>성별: " + (gender.equals("man") ? "남자" : gender.equals("woman") ? "여자" : "선택안함") + "</li>");
    out.println("<li>이메일: " + (email.isEmpty() ? "없음" : email) + "</li>");
    out.println("<li>휴대전화: " + country + " " + tel.substring(1) + "</li>");
    out.println("</body>");
    out.println("</html>");
    out.flush();
    out.close();
	  
	}

	  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
