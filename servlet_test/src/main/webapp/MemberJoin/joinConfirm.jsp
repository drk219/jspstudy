<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul>
	<li>아이디 : <%=request.getAttribute("member_id")%></li>
	<li>비밀번호 : <%=request.getAttribute("member_pw")%></li>
	<li>이름 : <%=request.getAttribute("member_name")%></li>
	<li>
		생년월일 :  
		<%
		String dateStr = String.format("%d년 %02d월 %02d일",
				Integer.parseInt( request.getAttribute("birth_year").toString() ),
				Integer.parseInt( request.getAttribute("birth_month").toString() ),
				Integer.parseInt( request.getAttribute("birth_day").toString() ));
		%>
		<%=dateStr%>
	</li>
	<li>
		성별 : 
		<% if(request.getAttribute("gender").equals("m")) { %> 남자 <% }
		   else { %> 여자 <% } %>
	</li>
	<li>이메일 : <%=request.getAttribute("member_email")%></li>
	<li>휴대전화 : <%=request.getAttribute("member_phone")%></li>
</ul>

<jsp:include page="../footer.jsp">
    	<jsp:param value="바닥" name="footer"/>
</jsp:include>
  	
</body>
</html>