<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>choose</title>
</head>
<body>

  <%-- 
    <c:choose>
    
    1. else 개념이 있는 if 문 태그이다. (자바의 if 문과 비슷)
    2. 형식
      <c:choose>
        <c:when test="조건식1">
          실행문
        </c:when>
        <c:when test="조건식2">
          실행문
        </c:when>
        <c:otherwise>
          실행문
        </c:otherwise>
      </c:choose>
  --%>
  
  <c:set var="age" value="<%=(int)(Math.random()*100+1)%>"/>
  <c:choose>
    <c:when test="${age >= 20}">
      <c:set var="result" value="adult"/>
    </c:when>
    <c:otherwise>
      <c:set var="result" value="under-age"/>   <%-- '그렇지않다면' 의미하는 otherwise 를 이용 --%>
    </c:otherwise>
  </c:choose>
  <div>${age} years old is ${result}.</div>

</body>
</html>