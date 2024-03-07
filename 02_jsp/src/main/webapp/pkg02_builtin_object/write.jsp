<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write</title>
</head>
<body>

	<%-- 
			  webapp  ->  /jsp 
			  pkg     ->  /pkg02_builtin_object
			  jsp     ->  /save.jsp
	--%>
	
  <% String contextPath = request.getContextPath();   // = "/jsp" %>
  
  <%-- 작성화면 작성 --%>
  <form action="<%=contextPath%>/pkg02_builtin_object/save.jsp" 
        method="post">
    <div>
      <label for="created-at">작성일자</label>
      <input type="text" id="created-at" name="created-at" value="<%=LocalDate.now()%>" readonly="readonly">
    </div>
    
    <div>
      <label for="title"></label>
      <input type="text" id="title" name="title">
    </div>
    
    <div>
      <textarea rows="5" cols="50" name="contents" placeholder="내용"></textarea>
    </div>
    
    <div>
      <button type="submit">작성완료</button>
      <button type="reset">다시입력</button>
    </div>
  </form>

</body>
</html>