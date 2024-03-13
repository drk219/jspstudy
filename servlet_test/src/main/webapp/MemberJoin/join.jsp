<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/memberJoin" method="post">
        <div>
            <label for="member_id">아이디</label><br>
            <input type="text" name="member_id" id="member_id">@naver.com
        </div>
        <div>
            <label for="member_pw">비밀번호</label><br>
            <input type="password" name="member_pw" id="member_pw">
        </div>
        <div>
            <label for="member_pw">비밀번호 재확인</label><br>
            <input type="password" name="member_pw_confirm" id="member_pw_confirm">  
        </div>
        <div>
            <label for="member_name">이름</label><br>
            <input type="text" name="member_name" id="member_name">
        </div>
        <div>
            <label for="birth_date">생년월일</label><br>
            <input type="text" name="birth_year" id="birth_year" placeholder="년(4자)">
            <select name="birth_month" id="birth_month">
            <% for(int i = 1; i <= 12; i++) { %>
            	<option value="<%=i%>"><%=i%>월</option>
           	<% } %>
            </select>
            <input type="text" name="birth_day" id="birth_day" placeholder="일">
        </div>
        <div>
            <label for="gender">성별</label><br>
            <select name="gender" id="gender">
            	<option value="m">남자</option>
                <option value="f">여자</option>
            </select>
        </div>
        <div>
            <label for="member_email">본인 확인 이메일(선택)</label><br>
            <input type="text" name="member_email" id="member_email" placeholder="선택입력">
        </div>
        <div>
            <label for="member_phone">휴대전화</label><br>
            <select name="member_nation" id="member_nation">
                <option value="">대한민국 +82</option>
            </select><br/>
            <input type="text" name="member_phone" id="member_phone placeholder="전화번호 입력">
            <input type="button" value="인증번호 받기"><br/>
            <input type="text" name="member_confirm" id="member_confirm" placeholder="인증번호 입력하세요" readonly>
        </div><br/>
        <div>
            <input type="submit" id="btn_join" value="가입하기">
        </div>
    </form>
    
    <jsp:include page="../footer.jsp">
    	<jsp:param value="바닥" name="footer"/>
  	</jsp:include>
    
    <script>
    	document.querySelector('form').addEventListener("submit", (event) => {
    		let memberId = document.querySelector('#member_id');
	    	if(memberId.value === '') {
	    		alert('아이디는 필수입니다.');
	    		event.preventDefault();
	    		return;
	    	}
	    	
	    	let memberPw = document.querySelector('#member_pw');
	    	let memberPw2 = document.querySelector('#member_pw_confirm');
	    	
	    	if(memberPw.value === '' || memberPw2.value == '') {
	    		alert('비밀번호는 필수입니다.');
	    		event.preventDefault();
	    		return;
	    	}
	    	
	    	if(memberPw.value !== memberPw2.value) {
	    		alert('비밀번호를 확인하세요.');
	    		event.preventDefault();
	    		return;
	    	}
    	});
	</script>
</body>
</html>