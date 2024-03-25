<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 관리</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>

  <div>
    <h1>학생 관리</h1>
  </div>
  <div>
    <button type="button" id="btn-write" >신규학생등록</button>
  </div>
  
  <hr>
  
  <div>
    <span>평균</span>
    <input type="number" placeholder="begin" id="begin" name="begin" min="0" max="100" size="5">
    ~
    <input type="number" placeholder="end" id="end" name="end" min="0" max="100" size="5">
    <input type="button" value="조회" id="btn-query">
    <input type="button" value="전체조회" id="btn-list">
  </div>
  
  <hr>
  
  <!-- 평균 점수가 상위 탑3인 학생 보기 -->
  <c:forEach var="top" items="${top3List}" varStatus="vs">
    <div>${vs.index + 1}위 ${top.name} <fmt:formatNumber value="${top.average}" pattern="0.00" />점</div>
  </c:forEach>
  
  <table border="1">
  
    <caption>전체 학생 ${count}명</caption>
    <tr>
      <td>학번</td>
      <td>성명</td>
      <td>국어</td>
      <td>영어</td>
      <td>수학</td>
      <td>평균</td>
      <td>학점</td>
      <td>버튼</td>
    </tr>
    
    <!-- 등록된 학생이 없다면 -->
    <c:if test="${count == 0}"> 
      <tr>
        <td colspan="8">등록된 학생이 없습니다.</td>
      </tr>
    </c:if>
    
    <!-- 등록된 학생 목록으로 뿌리기 -->
    <c:if test="${count != 0}">
      <c:forEach var="stu" items="${students}">
        <tr>
          <td>${stu.studentNo}</td>
          <td>${stu.name}</td>
          <td>${stu.korean}</td>
          <td>${stu.english}</td>
          <td>${stu.math}</td>
          <td><fmt:formatNumber value="${stu.average}" pattern="0.00" /></td>
          <td>${stu.grade}</td>
          <td data-stu-no="${stu.studentNo}">
            <button type="button" class="btn-detail">상세</button>
            <button type="button" class="btn-remove">삭제</button>
          </td>
        </tr>
      </c:forEach>
    </c:if>
    
    <tr>
      <td colspan="5">전체평균</td>
      <td><fmt:formatNumber value="${average}" pattern="0.00" /></td>
      <td colspan="2"></td>
    </tr>
    
  </table>
  
  <script>
    
    // 신규 학생 등록을 위해 작성화면으로 이동하는 함수
    function fnStudentWrite(){
    	document.getElementById('btn-write').addEventListener('click', (evt)=>{
    		location.href = '${contextPath}/student/write.do';
    	});
    }
  
    // 
    function fnStudentQuery(){
    	document.getElementById('btn-query').addEventListener('click', (evt)=>{
    		const begin = document.getElementById('begin');
    		const end = document.getElementById('end');
    		if(begin.value === '' || isNaN(begin.value) || begin.value < 0 || begin.value > 100){
    			alert('begin 값을 확인하세요.');
    			return;
    		} else if (end.value === '' || isNaN(end.value) || end.value < 0 || end.value > 100) {
    			alert('end 값을 확인하세요.');
    			return;
    		}
    		location.href = '${contextPath}/student/query.do?begin=' + begin.value + '&end=' + end.value;
    	});
    }
    
    // 목록으로 가기
    function fnStudentList(){
    	document.getElementById('btn-list').addEventListener('click', (evt)=>{
    		location.href = '${contextPath}/student/list.do';
    	});
    }
    
    // 학생 성적 상세보기 (제이쿼리 + 화살표함수)
    function fnStudentDetail(){
    	$('.btn-detail').on('click', (evt)=>{
    		location.href = '${contextPath}/student/detail.do?stuNo=' + $(evt.target).parent().data('studnetNo');
    	})
    }
    
    // 학생 삭제하기 (제이쿼리 + 화살표함수)
    function fnStudentDelete(){
    	$('.btn-remove').on('click', (evt)=>{
    		
    		// 삭제 확인
    		if(confirm('학생 정보를 삭제할까요?')){
    		  location.href = '${contextPath}/student/delete.do?stuNo=' + $(evt.target).parent().data('studentNo');
    		}
    	})
    }
    
    // 호출
    fnStudentWrite();
    fnStudentQuery();
    fnStudentList();
    fnStudentDetail();
    fnStudentDelete();
  
  </script>

</body>
</html>