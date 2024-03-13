<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../footer.jsp">
    	<jsp:param value="바닥" name="footer"/>
	</jsp:include>
	<script>
		alert('<%=request.getAttribute("resultString")%> 파일이 생성되었습니다');
	</script>
</body>
</html>