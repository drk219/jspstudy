<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PLEASE CLICK HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!</title>
</head>
<style>
li {
font-size: 30px;
}
</style>
<body>
	<ul>
		<li><a href="<%=request.getContextPath()%>/MemberJoin/join.jsp">회원 가입 확인하기</a></li>
		<li><a href="<%=request.getContextPath()%>/randomMuliple">구구단 확인하기</a></li>
		<li><a href="<%=request.getContextPath()%>/RequestDate/requestDate.jsp">현재 날짜/시간 요청 확인하기</a></li>
		<li><a href="<%=request.getContextPath()%>/SendNotice/sendNotice.jsp">공지사항 쓰기 확인하기</a></li>
	</ul>
</body>
</html>