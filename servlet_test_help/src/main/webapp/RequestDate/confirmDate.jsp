<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		alert('요청 결과는 <%=request.getAttribute("result")%> 입니다');
		location.href='<%=request.getContextPath()%>/RequestDate/requestDate.jsp';
	</script>
</body>
</html>