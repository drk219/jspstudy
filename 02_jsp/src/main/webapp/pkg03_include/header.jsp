<%@page import="java.security.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
  request.setCharacterEncoding("UTF-8");
  String title = request.getParameter("title");   // 브라우저 제목을 title 파라미터로 
%>
<title><%=title%></title>

<%
  String contextPath = request.getContextPath();
  long timestamp = System.currentTimeMillis();
%>

<!-- custom css -->
<!-- css 를 수정한게 가끔 적용이 안 될 수가 있다. 그럴때 수정한 시간의 밀리초를 path 에 넣어주면 중복될 일이 없다. -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/header.css?dt=<%=timestamp%>">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/body.css?dt=<%=timestamp%>">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/footer.css?dt=<%=timestamp%>">
                                                                               <!---------------------------------- 이 부분은 프로젝트가 끝나면 지울것! (수정할 일 없기때문)-->

</head>
<body>

  <div class="header-wrap">
    <div>
	    <a href="<%=request.getContextPath()%>/pkg03_include/main1.jsp">main1</a>
	    <a href="<%=contextPath%>/pkg03_include/main2.jsp">main2</a>
    </div>
  </div>
  
  <div class="body-wrap">