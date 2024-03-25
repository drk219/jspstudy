package test04;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class test04 extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    String fileName = null;
    
    try {
      
      // 요청 인코딩
      request.setCharacterEncoding("UTF-8");
      
      // 요청 파라미터
      String writer = request.getParameter("writer");
      String title = request.getParameter("title");
      String contents = request.getParameter("contents");
      
      // 폴더 생성
      File uploadDir = new File("upload");
      if(!uploadDir.exists()) {  
        uploadDir.mkdirs();
      }
      
      fileName = LocalDate.now().toString() + "-" + writer + "-" + title + ".txt";
      
      File file = new File(uploadDir, fileName);
      BufferedWriter bw = new BufferedWriter(new FileWriter(file));
      bw.write(contents);
      bw.flush();
      bw.close();
      
      /* 정상 처리 상황의 응답 처리 */

      response.setContentType("text/html; charset=UTF-8");
      
      PrintWriter out = response.getWriter();
      
      // 응답하기
      out.println("<script>");
      out.println("alert('" + fileName + "  파일이 생성되었습니다.')");
      out.println("location.href='/test/test04/04.html'");
      out.println("</script>");
      out.flush();
      out.close();
      
    } catch (Exception e) {
      
      /* 예외 상황의 응답 처리 */
      
      response.setContentType("text/html; charset=UTF-8");
      
      PrintWriter out = response.getWriter();
      
      // 응답하기
      out.println("<script>");
      out.println("alert('" + fileName + "  파일 생성에 실패하였습니다.')");
      out.println("location.href='/test/test04/04.html'");
      out.println("</script>");
      out.flush();
      out.close();
      
    }    
  
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}

