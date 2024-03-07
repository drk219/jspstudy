<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>builtin_object</title>
</head>
<body>

  <%-- 
    JSP 내장 객체
    1. 이미 생성된 9개의 객체가 있다.
    2. 주요 내장 객체
      1) request     --  HttpServletRequest request                               >> client 의 http 요청정보를 저장하고 있는 객체
      2) response    --  HttpServletResponse response                             >> http 요청에 대한 응답정보를 저장하는 객체
      3) session     --  HttpSession session                                      >> client 가 서버에 접속했을 때 세션정보를 저장한 객체
      4) application --  ServletContext application = request.getServletContext   >> 동일한 Application 의 Context 정보를 저장하고 있는 객체 
      5) out         --  JspWriter out                                            >> 응답 페이지 전송을 위한 출력 stream
  --%>
  

</body>
</html>