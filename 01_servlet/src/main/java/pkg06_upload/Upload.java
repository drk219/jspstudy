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
	  // lifecycle 은 프로젝트의 시작과 종료까지
	  
	  // 업로드할 경로 (톰캣 내부 경로)
	  String uploadPath = request.getServletContext().getRealPath("upload");
	  File uploadDir = new File(uploadPath);
	  if(!uploadDir.exists()) {
	    uploadDir.mkdirs();
	  }
	  
	  // 첨부된 파일 정보
	  String originalFilename = null;
	  String filesystemName = null;
	  Collection<Part> parts = request.getParts();
	  for(Part part : parts) {   // 향상 for 문 이용
	    //System.out.println(part.getName() + ", " + part.getContentType() + ", " + part.getSize() + ", " + part.getSubmittedFileName());
	    // title, null, 8, null
	    // file, image/jpeg, 463394, animal1.jpg

	    //System.out.println(part.getHeader("Content-Disposition"));
	    // form-data; name="title"
	    // form-data; name="file"; filename="animal1.jpg"
	    
	    if(part.getHeader("Content-Disposition").contains("filename")) {
	      if(part.getSize() > 0) {
	        originalFilename = part.getSubmittedFileName();	        
	      }
	    }
	    if(originalFilename != null) {
	      int point = originalFilename.lastIndexOf(".");
	      String extName = originalFilename.substring(point);   // .jpg
	      String fileName = originalFilename.substring(0, point);   // animal1
	      filesystemName = fileName + "_" + System.currentTimeMillis() + extName;
	    }
	    if(filesystemName != null) {
	      part.write(uploadPath + File.separator + filesystemName);
	    }
	  }
	  // 응답
	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  
	  out.println("<div><a href=\"/servlet/pkg06_upload/NewFile.html\">입력폼으로 돌아가기</a></div>");
	  out.println("<hr>");
	  out.println("<div>첨부파일명 : " + originalFilename + "</div>");
	  out.println("<div>저장파일명 : " + filesystemName + "</div>");
	  out.println("<div>저장경로 : " + uploadPath + "</div>");
	  out.println("<hr>");
	  
	  
	  // 문제 (올린 파일의 이름에 링크 태그가 걸리게)
	  File[] files = uploadDir.listFiles();
	  	    
	    for(File file : files) {
	      String fileName = file.getName();                            // 파일명 가져오기
	      String ext = fileName.substring(fileName.lastIndexOf("."));  // 확장자명 추출 
	      String fileName2 = fileName.substring(0, fileName.lastIndexOf("_"));   // 파일명 중 확장자 제외한 부분
	      out.println("<div><a href=\"/servlet/download?filename=" + URLEncoder.encode(fileName, "UTF-8") + "\">" + fileName2 + ext + "</a></div>");
	                                                               // 글자 안 깨지게 인코딩 해주기               수정한 파일명  .확장자
	    }
	  
	  out.flush();
	  out.close();
	  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		doGet(request, response);
		
	}

}
