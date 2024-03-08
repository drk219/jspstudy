<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<style>
  .row > span{
    display: inline-block;
  }
  .row > span:first-of-type {
    width: 150px;
  }
  .row > span:last-of-type {
    width: 100px;
  }
</style>
</head>
<body>

  <div>
    <a href="${contextPath}/board/write.brd">새게시글작성</a>
  </div>

  <hr>
  
  <div>
    <span>게시글 개수</span>
    <span>${boardCount}</span>
  </div>
  
  <div>
    <c:if test="${empty boardList}">
      <div>작성된 게시글이 없습니다. 첫 게시글의 주인공이 되어보세요.</div>
    </c:if> 
    <c:if test="${not empty boardList}">
      <c:forEach items="${boardList}" var="board">
	      <div class="row">
	        <span><a href="${contextPath}/board/detail.brd?board_no=${board.board_no}">${board.title}</a></span>
	        <span>${board.created_at}</span>
	      </div>
      </c:forEach>
    </c:if>
  </div>
  
</body>
</html>