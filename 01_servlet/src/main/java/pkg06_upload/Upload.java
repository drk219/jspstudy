package pkg06_upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
                 maxFileSize = 1024 * 1024 * 5, 
                 maxRequestSize = 1024 * 1024 * 50)

public class Upload extends HttpServlet {
  
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // realPath 경로 : C:\GDJ77\jspstudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\01_servlet
	  
	  // 업로드할 경로 (톰캣 내부 경로)
	  String uploadPath = request.getServletContext().getRealPath("upload");  // upload 경로 설정
	  File uploadDir = new File(uploadPath);
	  if(!uploadDir.exists()) {  // upload 파일 생성
	    uploadDir.mkdirs();
	  }
	  
	  // 첨부된 파일 정보
	  String originalFilename = null;                // 원 파일명
	  String filesystemName = null;                  // 시스템에서 다시 설정한 이름
	  Collection<Part> parts = request.getParts();   // 첨부파일을 배열로 가져온다
	  for(Part part : parts) {                       // 향상 for 문 이용
	    
	    //System.out.println(part.getName() + ", " + part.getContentType() + ", " + part.getSize() + ", " + part.getSubmittedFileName());
	    // title, null, 8, null
	    // file, image/jpeg, 463394, animal1.jpg

	    //System.out.println(part.getHeader("Content-Disposition"));
	    // form-data; name="title"
	    // form-data; name="file"; filename="animal1.jpg"
	    
	    
	    /*
	     * < Content-Disposition >
	     * HTTP Response Header 에 들어가는 Content-Disposition은 
	     * HTTP Response Body 에 오는 콘텐츠의 성향을 알려주는 속성 Content-Disposition에 attachment 를 주고 
	     * filename 과 함께 주게 되면 body 에 오는 값을 다운로드 받으라는 뜻이 된다.
	     */
	    if(part.getHeader("Content-Disposition").contains("filename")) {   // 파일명을 가지고 있는가
	      if(part.getSize() > 0) {
	        originalFilename = part.getSubmittedFileName();	   // 업로드된 파일의 원래 파일 이름을 가져온다
	      }
	    }
	    if(originalFilename != null) {
	      int point = originalFilename.lastIndexOf(".");             // . 의 위치 찾기
	      String extName = originalFilename.substring(point);        // .jpg
	      String fileName = originalFilename.substring(0, point);    // animal1
	      filesystemName = fileName + "_" + System.currentTimeMillis() + extName;  
	      // 확장자를 제외하고 파일명에 첨부된 시간의 밀리초를 추가하여 파일 이름을 수정한다. 
	    }
	    if(filesystemName != null) {
	      part.write(uploadPath + File.separator + filesystemName);
	      //                      --------------  <- 운영체제마다 \,/ 사용법이 달라서 separator 설정
	      /*
	       * File.separator라는 상수값을 사용했습니다
	       * File.separator는 운영체제별로 다른 파일경로 구분자를 담고 있습니다
	       * 예를 들어 Windows 환경에서는 각 디렉터리를 구분할 때 \를 사용하며 linux 계열은 /를 사용합니다
	       * 따라서 업로드한 파일 경로의 마지막 separator 뒤에 오는 값이 실제 파일명이라 할 수 있습니다
	       */
	    }
	  }
	  
	  // 응답
	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  // 서식이 지정된 개체의 표현을 텍스트 출력 스트림에 인쇄
	  // 현재 요청에 대한 응답을 생성하기 위해 사용할 PrintWriter 객체를 얻는 것 
	  
	  out.println("<div><a href=\"/servlet/pkg06_upload/NewFile.html\">입력폼으로 돌아가기</a></div>");
	  out.println("<hr>");
	  out.println("<div>첨부파일명 : " + originalFilename + "</div>");   // 첨부파일명
	  out.println("<div>저장파일명 : " + filesystemName + "</div>");     // 저장파일명
	  out.println("<div>저장경로 : " + uploadPath + "</div>");           // 저장경로
	  out.println("<hr>");
	  
	  
	  // 문제 (올린 파일의 이름에 링크 태그가 걸리게)
	  File[] files = uploadDir.listFiles();     // 업로드 디렉토리를 파일 배열로 가져오기
	  	    
	    for(File file : files) {
	      String fileName = file.getName();                                     // 파일명 가져오기
	      String ext = fileName.substring(fileName.lastIndexOf("."));           // 확장자명 추출 
	      String fileName2 = fileName.substring(0, fileName.lastIndexOf("_"));  // 파일명 중 확장자 제외한 부분
	      out.println("<div><a href=\"/servlet/download?filename=" + URLEncoder.encode(fileName, "UTF-8") + "\">" + fileName2 + ext + "</a></div>");
	                                                               //-----------------------------------           ----------   ---
	                                                               // 글자 안 깨지게 인코딩 해주기               수정한 파일명  .확장자
	    }
	  
	  out.flush();
	  out.close();
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		doGet(request, response);
		
	}

}
