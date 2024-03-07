<%@page import="pkg05_JSTL.Post"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forEach</title>
</head>
<body>

  <%--  
    <c:forEach>
    
    1. 일반 for 문, 향상 for 문을 모두 처리할 수 있다.
    2. 일반 for 문 형식
      <c:forEach var="속성" begin="시작값" end="종료값" step="증가값">  
        ${속성}
      </c:forEach>
    3. 향상 for 문 형식
      <c:forEach var="요소" items="배열/리스트" varStatus="vs">  
        저장 데이터 : ${요소}
        인덱스      : ${vs.index}
        인덱스 + 1  : ${vs.count}
      </c:forEach>  
  --%>
  
  <%-- 일반 for 문 수열 만들기 --%>
  <c:forEach var="number" begin="1" end="5" step="1">  <%-- 수열의 역순을 나타내게 <c:forEach var="number" begin="5" end="1" step="-1">는 불가능 (오류남!!) --%>
    <div>${number}</div>
  </c:forEach>
  
  <hr>
  
  <%-- 향상 for 문 배열 만들기 --%>
  <% pageContext.setAttribute("seasons", new String[]{"spring", "summer", "fall", "winter"});%>
  <c:forEach var="seasons" items="${seasons}" varStatus="vs">
    <div>${seasons} / ${vs.index} / ${vs.count}</div>      <%-- 계절 / 인덱스번호 / 순번 --%>
  </c:forEach>
  
  <hr>
  
  <%-- 향상 for 문 --%>
  <% pageContext.setAttribute("hobbies", Arrays.asList("travel", "cook", "read", "game"));%>
  <c:forEach var="hobby" items="${hobbies}" varStatus="vs">
    <div>${hobby} / ${vs.index} / ${vs.count}</div>        <%-- 취미 / 인덱스번호 / 순번 --%>
  </c:forEach>
  
  <hr>
  
  <%-- List 에 Post 객체를 3개 저장하고 <c:forEach> 태그로 확인하기 (( !!중요!! )) --%>
  <% 
    List<Post> posts = Arrays.asList(                 // JSP의 다른 부분에서 posts 리스트에 액세스할 수 있게
        new Post("기사제목1", "기자1", "작성일자1"),
        new Post("기사제목2", "기자2", "작성일자2"),
        new Post("기사제목3", "기자3", "작성일자3")
        ); 
    request.setAttribute("posts", posts);
  %>
  <c:forEach var="post" items="${requestScope.posts}" varStatus="vs">
    <div>
      <span>${vs.count}</span>    <%-- 현재 반복의 인덱스를 1부터 시작하여 표시 --%>
      <span>${post.title}</span>
      <span>${post.reporter}</span>
      <span>${post.createdAt}</span>
    </div>
  </c:forEach>
  
  
</body>
</html>