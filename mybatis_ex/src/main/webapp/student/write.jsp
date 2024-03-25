<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규학생등록</title>
</head>
<body>

  <div>
    <h1>신규학생등록 화면</h1>
  </div>
  
  <form action="${contextPath}/student/register.do"
        method="post"
        id="frm-register">
    <div>
      <label for="name">이름</label>
      <input type="text" name="name" id="name">
    </div>
    <div>
      <label for="kor">국어</label>
      <input type="text" name="kor" id="kor">
    </div>
    <div>
      <label for="eng">영어</label>
      <input type="text" name="eng" id="eng">
    </div>
    <div>
      <label for="math">수학</label>
      <input type="text" name="math" id="math">
    </div>
  </form>
  
  <hr>
  
  <div>
    <button type="submit" name="register" id="register">작성완료</button>
    <button type="reset">다시작성</button>
    <button type="submit" name="list" id="btn-list">목록보기</button>
  </div>
  
  <script>
    
    function fnStudentRegister(){
    	document.getElementById('frm-register').addEventListener('submit', (evt)=>{
    		
    		// 변수
    		const kor = document.getElemntById('kor');
    		const eng = document.getElemntById('eng');
    		const math = document.getElemntById('math');
    		
    		// 각 과목점수가 빈문자거나, 숫자가 아니거나, 음수거나, 100을 넘으면 경고
    		if(kor.value === '' || isNaN(kor.value) || kor.value < 0 || kor.value > 100){
    			alert('국어 점수를 확인하세요.');
    			kor.focus();
    			evt.preventDefault();
    			return;
    		} else if (eng.value === '' || isNaN(eng.value) || eng.value < 0 || eng.value > 100){
  	      alert('영어 점수를 확인하세요.');
  	      eng.focus();
  	      evt.preventDefault();
  	      return;
    		} else if (math.value === '' || isNaN(math.value) || math.value < 0 || math.value > 100){
 	        alert('수학 점수를 확인하세요.');
 	        math.focus();
 	        evt.preventDefault();
 	        return;
    		}
    	});
    }
    
    function fnStudentList(){
      document.getElementById('btn-list').addEventListener('click', (evt)=>{
        location.href = '${contextPath}/student/list.do';
      });
    }
    
    // 호출
    fnStudentRegister();
    fnStudentList();
  
  </script>

</body>
</html>