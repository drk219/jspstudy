package controller;

import java.io.IOException;

import common.ActionForward;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MyInterface;
import model.MyInterfaceImpl;


public class MyController extends HttpServlet {
  
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	  // 요청 & 인코딩
	  request.setCharacterEncoding("UTF-8");
	  
	  // 어떤 요청인지 확인 (urlMapping 확인)
	  String requestURI = request.getRequestURI();    //  http://localhost:8080/mvc/getDate.do
	  String contextPath = request.getContextPath();  //  /mvc
	  String urlMapping = requestURI.substring(requestURI.indexOf(contextPath) + contextPath.length());  // contextPath의 뒷부분을 추출. ( '/getDate.do' )
	  //                                       ------------------------------------------------------
	  //                                      -> contextPath의 인덱스부터 contextPath 길이만큼을 잘라낸다.
	  //                  ---------------------------------------------------------------------------
	  //                   -> URI 경로에서 위에서 잘라낸 contextPath 만큼 잘라내고 맵핑에 저장한다.  
	  
	  
	  /* MyInterface 타입의 MyInterfaceImpl 객체 생성 */
	  MyInterface myInterface = new MyInterfaceImpl();
	  
	  /* 메소드 호출(뫄뫄.jsp) 결과 (view + forward/redirect) 를 저장할 ActionForward 객체 선언 */
	  ActionForward actionForward = null;
	  
	  
	  /* 요청에 따른 메소드 호출 */
	 	switch (urlMapping) {
	 	
  	 	case "/getDate.do":
  	 	  actionForward = myInterface.getDate(request);
  	 	  //System.out.println(request.getAttribute("date"));  => 2024. 03. 07.
  	 	  break;
  	 	case "/getTime.do":
  	 	  actionForward = myInterface.getTime(request);
  	 	  //System.out.println(request.getAttribute("time"));  => 11:49:30.209
  	 	  break;
  	 	case "/getDateTime.do":
  	 	  actionForward = myInterface.getDateTime(request);
  	 	  //System.out.println(request.getAttribute("datetime"));  => 2024. 03. 07. 11:49:36.509
  	 	  break;
	 	}
	 	
	 	//System.out.println(path);  // 각 요청이 전달될 경로 뫄뫄.jsp 출력
	 	
	 	/* 어디로 어떻게 이동할 것인지 결정 */  // 컨트롤러의 마지막은 항상 어디로 어떻게 이동을 할지 결정해줘야 한다.
	 	if(actionForward != null) {
	 	  if(actionForward.isRedirect()) {
	 	   response.sendRedirect(actionForward.getView());
	 	  } else {
	 	    request.getRequestDispatcher(actionForward.getView()).forward(request, response);
	 	  }
	 	}
	 	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
