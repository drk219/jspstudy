<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>if</title>
</head>
<body>

  <%--  
    <c:if>
    
    1. else 의 개념이 없는 if 문 태그이다. (else 처리를 할 수 없기 때문에 코드를 적는다 번거로움)
    2. 형식
      <c:if test="조건식">
        실행문
      </c:if>
  --%>
  
  <c:set var="age" value="<%=(int)(Math.random() * 100 + 1)%>" scope="page"/>   <%-- 1 ~ 99 --%>
  <c:if test="${age ge 20}">
    <div>${age }살은 성인입니다.</div>
  </c:if>
  <c:if test="${age lt 20}">
    <div>${age }살은 미성년자입니다.</div>
  </c:if>
  
  <hr>
  
  <%-- 점수대별 학점 설정하기 --%>
  <%-- 강사님 풀이 --%>
  <c:set var="score" value="<%=(int)(Math.random()*101)%>"/>
  <c:if test="${score >= 90}">
    <c:set var="mark" value="A"/>
  </c:if>
  <c:if test="${score < 90 && score >= 80}">    <%-- 90점대 아래는 else 처리가 없기 때문에 and 연산자로 더 촘촘하게 범위를 설정한다 --%>
    <c:set var="mark" value="B"/>
  </c:if>
  <c:if test="${score < 80 && score >= 70}">
    <c:set var="mark" value="C"/>
  </c:if>
  <c:if test="${score < 70 && score >= 60}">
    <c:set var="mark" value="D"/>
  </c:if>
  <c:if test="${score < 60}">
    <c:set var="mark" value="F"/>
  </c:if>
  <div>점수는 ${score}점이고, 학점은 ${mark}입니다.</div>
  
  
  <%-- 내 풀이 --%>
  <c:set var="score" value="<%=(int)(Math.random() * 101)%>" />
  <c:if test="${score ge 90}">
    <div>${score }점은 A학점입니다.</div>
  </c:if>
  <c:if test="${score ge 80 and score lt 90}">
    <div>${score }점은 B학점입니다.</div>
  </c:if>
  <c:if test="${score ge 70 and score lt 80}">
    <div>${score }점은 C학점입니다.</div>
  </c:if>
  <c:if test="${score ge 60 and score lt 70}">
    <div>${score }점은 D학점입니다.</div>
  </c:if>
  <c:if test="${score lt 60}">
    <div>${score}점은 F학점입니다.</div>
  </c:if>


</body>
</html>