package controller;

import java.io.IOException;

import common.ActionForward;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import service.StudentServiceImpl;

public class StudentController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  
  private StudentService studentService = new StudentServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  request.setCharacterEncoding("UTF-8");
	  response.setContentType("text/html; charset=UTF-8");
	  
	  String requestURI = request.getRequestURI();
	  String contextPath = request.getContextPath();
	  String urlMapping = requestURI.substring(contextPath.length());
	  
	  ActionForward actionForward = null;
	  
	  switch(urlMapping) {
	  case "/student/list.do":
	    actionForward = studentService.studentList(request);
	    break;
	  case "/student/register.do":
	    studentService.studentAdd(request, response);
	    break;
	  case "/student/query.do":
	    actionForward = studentService.studentQuery(request);
	    break;
	  case "/student/delete.do":
	    studentService.studentDelete(request, response); 
	    break;
	  case "/student/detail.do":
	    actionForward = studentService.studentDetail(request);
	    break;
	  case "/student/modify.do":
	    studentService.studentModify(request, response);
	    break;
	  case "/student/write.do":
	    actionForward = new ActionForward("/student/write.jsp", false);
	    break;
	  }
	  
	  if(actionForward != null) {
	    response.sendRedirect(actionForward.getView());
	  } else {
	    request.getRequestDispatcher(actionForward.getView()).forward(request, response);
	  }
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		doGet(request, response);
	
	}

}
