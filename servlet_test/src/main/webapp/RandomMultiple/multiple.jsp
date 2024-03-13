<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
int element1 = (int)request.getAttribute("element1");
int element2 = (int)request.getAttribute("element2");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <button id="refresh">새로고침</button><br/>
	<%=element1%> × <%=element2%>
    <input type="text" placeholder="값 써줘" id="result">
    <input type="hidden" id="secret" value="<%=element1*element2%>">
    <button id="btnCal">계산</button>
    
    <jsp:include page="../footer.jsp">
    	<jsp:param value="바닥" name="footer"/>
  	</jsp:include>

    <script>
        document.querySelector('#btnCal').addEventListener("click", (event) => {
            let inputVal = document.querySelector('#result');
            let secret = document.querySelector('#secret');
            
            if(inputVal.value === '') {
            	alert('값을 써주세요');
            	return;
            }
            
            if(inputVal.value === secret.value) alert('정답입니다');
            else alert('오답입니다');
        });

        document.querySelector('#refresh').addEventListener('click', (event) => {
            window.location.reload();
        });
    </script>
</body>
</html>