<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="<%=request.getContextPath()%>/requestDate">
		<select name="date_option" id="date_option">
        	<option value="current_date">현재날짜</option>
            <option value="current_time">현재시간</option>
        </select>
        <input type="submit" value="요청하기">
	</form>
	
	<jsp:include page="../footer.jsp">
    	<jsp:param value="바닥" name="footer"/>
  	</jsp:include>
</body>
</html>