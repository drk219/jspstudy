package pkg07_Cookie;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DeleteCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // 요청 및 요청 파라미터
	  request.setCharacterEncoding("UTF-8");
	  String cookieName = request.getParameter("cookieName");
	  
	  
	  // 쿠키 삭제
	  // 특정 이름의 쿠키를 삭제하기 위해 만료 기간을 0으로 설정하여 쿠키를 무효화하고, 그 결과를 클라이언트에게 전달
	  Cookie cookie = new Cookie(cookieName, "아무의미없는값");   // cookieName은 삭제할 쿠키의 이름을 나타냅니다, "아무의미없는값" => 쿠키를 삭제하기 위한 임의의 값
	  cookie.setMaxAge(0);
	  // 쿠키의 만료 기간을 설정합니다. 여기서는 0으로 설정하여 쿠키의 존재 기간을 0으로 만듭니다. 이렇게 하면 쿠키가 즉시 만료되어 삭제됩니다.
	  response.addCookie(cookie);
	  //새로 생성한 쿠키를 HTTP 응답에 추가합니다. 이를 통해 클라이언트에게 쿠키가 삭제되었음을 알립니다
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		doGet(request, response);
	
	}

}
