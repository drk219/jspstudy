<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>date_time</title>
</head>
<body>
  
  <%-- 날짜 / 시간 --%>
  
  <c:set var="now" value="<%=new Date()%>"/>
  
  <div>
    <h1><fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일 E요일"/></h1>   <%-- 2024년 03월 06일 수요일 --%>
  </div>
  
  <div>
    <h1><fmt:formatDate value="${now}" pattern="a hh시 mm분 ss초 SSS"/></h1>     <%-- 오후 04시 20분 35초 135 --%>
  </div>
  
  
</body>
</html>