<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
        
	<% 
	  // 요청 인코딩
	  request.setCharacterEncoding("UTF-8");
	
	  // 요청 파라미터
	  String createdAt = request.getParameter("created-at");
	  String title = request.getParameter("title");
	  String contents = request.getParameter("contents");
	  
	  // 업로드 경로 (/upload 경로)
	  String uploadPath = application.getRealPath("upload");
	  
	  // 파일 생성
	  File uploadDir = new File(uploadPath);
	  if(!uploadDir.exists()){
	    uploadDir.mkdirs();
	  }
	  
	  // 요청 IP주소 
	  String ip = request.getRemoteAddr();
	  
	  // 업로드 파일명
	  String uploadName = title + "_" + createdAt.replace("-", "_") + "_" + ip.replace(":", "_") + ".txt";
	  //                                --------------------------          --------------------   --------
	  //                                작성된시간의 -을 _로                 ip주소의 :을 _로      텍스트파일로 저장
	  
	  // 업로드 파일명을 세션에 등록하기
	  session.setAttribute("uploadName", uploadName);  // 재설정한 파일명 등록
	  
	  // 업로드 파일 객체
	  File uploadFile = new File(uploadDir, uploadName);  // 올릴 파일에 경로와 파일명 부여
	  
	  // 문자 출력 스트림
	  BufferedWriter writer = new BufferedWriter(new FileWriter(uploadFile));  // 담고있는 내용 읽어드리기
	  
	  // 데이터 내보내기 (txt 파일에 저장할 내용)
	  writer.write(createdAt + "\n");
	  writer.write(title + "\n");
	  writer.write(contents + "\n");
	  writer.flush();
	  writer.close();
	  
	  // 파일 확인을 위한 페이지 이동
	  response.sendRedirect(request.getContextPath() + "/pkg02_builtin_object/confirm.jsp");
	%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>save</title>
</head>
<body>

  <div><%=createdAt%></div>
  <div><%=title%></div>
  <div><%=contents%></div>

</body>
</html>