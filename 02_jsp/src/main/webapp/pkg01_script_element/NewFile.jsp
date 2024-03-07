<!-- HTML 주석 : "소스 보기"에서 보인다. 자바 코드는 주석처리할 수 없다. -->
<%-- JSP 주석  : "소스 보기"에서 안 보인다. 자바 코드는 주석처리할 수 있다. --%>

<%--
  < 지시어(directive) >
  1. <%@ page %>    : 현재 페이지 설정, import
  2. <%@ include %> : 현재 페이지에 다른 페이지를 포함
  3. <%@ taglib %>  : JSTL(JavaServerPage Standard Tag Library)
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>script element</title>
</head>
<body>

  <%--
    < 스크립트릿 (scriptlet) >
    1. 자바 코드를 작성할 때 사용한다.
    2. <% 자바 코드 %>
  --%>
  <% int a = 10; %>
  
  
  <%--
    < 표현식 (Expression) >
    1. 자바 값을 나타낼 때 사용한다. (변수, 메소드 반환 등)
    2. <%= 자바값 %>
  --%>
  <div><%=a%></div>
  
  
  <%--
    < 선언부 (Declaration) >
    1. 자바 메소드를 정의할 때 사용한다.
    2. <%! 메소드정의 %>
  --%>
  <%! 
    // 정수 10을 반환하는 메소드
    public int getTen(){
      return 10;
    }
  %>
  <div><%=getTen()%></div>
  
  
  <%-- 
    자바와 자바 스크립트
    1. 자바 변수를 자바 스크립트에서 사용할 수 있다. (자바스크립트에서 표현식을 사용하면 된다.)
    2. 반대는 안 된다.
  --%>
  <script>
    alert(<%=a%>);
  </script>
  
  
  <%-- 1. if 문  --%>
  <% if(getTen() == 10) { %>
    <div>10 이다.</div>
  <% } else {  %>
    <div>10 아니다.</div>
  <% } %>
  
  <%-- 2. for 문  --%>
  <div>
    <select>
      <% for( int m = 1; m <= 12; m++) { %>
        <option><%=m%></option>
      <% } %>
    </select>
  </div>
  
  <%-- 연습. 현재 날짜와 시간을 yyyy-MM-dd HH:mm:ss:SSS 형식의 문자열을 반환하는 getNow() 메소드를 만들고 호출하기 --%>
    <%!
	    public String getNow() {      
	      return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now());
	    }
	  %>
  <div>
    <%=getNow()%>
  </div>  
  
  
</body>
</html>



