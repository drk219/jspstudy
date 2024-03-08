<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세화면</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>

  <div>제목 : ${board.title}</div>
  <div>내용</div>
  ${board.contents}
  <div>최종수정일 : ${board.modified_at}</div>
  <div>작성일 : ${board.created_at}</div>
  
  <div>
	  <form method="POST"
	        id="frm-btn">
	    <input type="hidden" name="board_no" value="${board.board_no}">
	    <button type="button" id="btn-edit">편집</button>
	    <button type="button" id="btn-remove">삭제</button>
	  </form>
  </div>
  
  <script>
    
  </script>
  
</body>
</html>