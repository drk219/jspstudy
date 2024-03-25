<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 상세 조회</title>
</head>
<body>

  <!-- 상세조회 학번 평균 학점 수정 불가능 -->
  
  <div>
    <h1>학생 상세 조회</h1>
  </div>
  
  <form id="frm-detail"
        action="${contextPath}/student/modify.do"
        method="post">
    <div>
      <label for="studentNo">학번</label>
      <input type="text" name="studentNo" id="studentNo" value="${student.studentNo}" readonly="readonly">
    </div>
    <div>
      <label for="name">이름</label>
      <input type="text" name="name" id="name" value="${student.name}">
    </div>
    <div>
      <label for="kor">국어</label>
      <input type="text" name="" id="" value="${student.kor}">
    </div>
    <div>
      <label for="eng">영어</label>
      <input type="text" name="" id="" value="${student.eng}">
    </div>
    <div>
      <label for="math">수학</label>
      <input type="text" name="math" id="math" value="${student.math}">
    </div>
    <div>
      <label for="average">평균</label>
      <input type="text" name="average" id="average" value="${student.average}" disabled>
    </div>
    <div>
      <label for="grade">학점</label>
      <input type="text" name="grade" id="grade" value="${student.grade}" disabled>
    </div>
  </form>
  
  <hr>
  
  <div>
    <button type="submit" id="modify">수정하기</button>
    <button type="button" id="btn-list">목록보기</button>
  </div>
  
  
  <script>
    
    // 점수가 없거나 음수거나 100보다 클 경우 처리 
    function fnStudentDetail(){
    	
      document.getElementById('frm-detail').addEventListener('submit', (evt)=>{
    	  
    	  // 변수 처리
        const kor = document.getElementById('kor');
        const eng = document.getElementById('eng');
        const math = document.getElementById('math');
        
        // 국어 점수가 빈문자열이거나 음수거나 100보다 크면 이벤트리스너 취소
        if(kor.value === '' || isNaN(kor.value) || kor.value < 0 || kor.value > 100){
          alert('국어 점수를 확인하세요.');
          kor.focus();
          evt.preventDefault();
          return;
        }
        
        // 영어 점수가 빈문자열이거나 음수거나 100보다 크면 이벤트리스너 취소
        else if(eng.value === '' || isNaN(eng.value) || eng.value < 0 || eng.value > 100){
          alert('영어 점수를 확인하세요.');
          eng.focus();
          evt.preventDefault();
          return;
        }
        
        // 수학 점수가 빈문자열이거나 음수거나 100보다 크면 이벤트리스너 취소
        else if(math.value === '' || isNaN(math.value) || math.value < 0 || math.value > 100){
          alert('수학 점수를 확인하세요.');
          math.focus();
          evt.preventDefault();
          return;
        }
        
      });
    }
    
    // 목록보기로 돌아가는 버튼
    function fnStudentList(){
    	
      document.getElementById('btn-list').addEventListener('click', (evt)=>{
        location.href = '${contextPath}/student/list.do';
      });
      
    }
    
    // 각 멧소드 호출
    fnStudentDetail();
    fnStudentList();
  
  </script>

</body>
</html>