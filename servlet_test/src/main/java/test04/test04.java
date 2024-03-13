package test04;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class test04 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    String filename = null;
    
    try {
      
      request.setCharacterEncoding("UTF-8");
      
      String writer = request.getParameter("writer");
      String title = request.getParameter("title");
      String contents = request.getParameter("contents");
      
      File uploadDir = new File("upload");
      if(!uploadDir.exists()) {  
        uploadDir.mkdirs();
      }
      
      String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
      String uploadName = date + "_" + writer + "_" + title + ".txt";
      
      File uploadFile = new File(uploadDir, uploadName);
      
      BufferedWriter bw = new BufferedWriter(new FileWriter(uploadFile));
      bw.write(writer + "\n");
      bw.write(title + "\n");
      bw.write(contents + "\n");
      bw.flush();
      bw.close();
      
      response.setContentType("text/html; charset=UTF-8");
      
      PrintWriter out = response.getWriter();
      
      // 응답하기
      out.println("<script>");
      out.println("alert('" + filename + "  파일이 생성되었습니다.')");
      out.println("location.href='/servlet_test/test04/04.html'");
      out.println("</script>");
      out.flush();
      out.close();
      
    } catch (Exception e) {
      
      response.setContentType("text/html; charset=UTF-8");
      
      PrintWriter out = response.getWriter();
      
      // 응답하기
      out.println("<script>");
      out.println("alert('" + filename + "  파일 생성에 실패하였습니다.')");
      out.println("location.href='/servlet_test/test04/04.html'");
      out.println("</script>");
      out.flush();
      out.close();
      
    }    
  
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}

