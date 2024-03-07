<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>set</title>
</head>
<body>

  <%-- 
    <c:set>
    
    1. Bind 영역에 데이터를 저장할 때 사용한다.
    2. 형식
      (1) <c:set var="속성명" value="값" scope="Bind 영역"></c:set>
      (2) <c:set var="속성명" value="값" scope="Bind 영역"/>        => 작성할 내용이 없으면 / 추가해서 나홀로태그로 쓸 수 있다.
  --%>
  
  <c:set var="a" value="1" scope="page"/>   <%-- scope="page" : 기본값이라 생략 가능 / 제일 우선으로 실행된다. --%>
  <c:set var="a" value="2" scope="request"/>
  <c:set var="a" value="3" scope="session"/>
  <c:set var="a" value="4" scope="application"/>
  <%-- 위 <c:set>태그는 아래 자바를 대체
    pageContext.setAttribute("a", 1);
    request.setAttribute("a", 2);
    session.setAttribute("a", 3);
    application.setAttribute("a", 4);
  --%>
  
  <div>${pageScope.a}</div>        <%-- 각 스콥에 설정한 value 값이 나온다 --%>
  <div>${requestScope.a}</div>
  <div>${sessionScope.a}</div>
  <div>${applicationScope.a}</div>
  
  <hr>
  
  <%-- 내용을 안써도 되는 태그는 나홀로 태그로 변경 가능 </> 처럼 "/" 쓰면 된다 --%>

  <%-- 자주 사용하게 될 <c:set> --%> 
  <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page"/>
  <c:set var="contextPath" value="<%=request.getContextPath()%>"/>
  
  <div>${contextPath}</div>   <%-- /jsp --%>
  

</body>
</html>