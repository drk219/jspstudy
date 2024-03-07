package test04;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class test04 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
    String uploadPath = request.getServletContext().getRealPath("upload");  
    File uploadDir = new File(uploadPath);
    if(!uploadDir.exists()) {  
      uploadDir.mkdirs();
    }
    
    request.setCharacterEncoding("UTF-8");

    String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    String writer = request.getParameter("writer");
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");
    String uploadName = date + "_" + writer + "_" + title + ".txt";
    
    File uploadFile = new File(uploadDir, uploadName);
    
    BufferedWriter bw = new BufferedWriter(new FileWriter(uploadFile));
    bw.write(writer + "\n");
    bw.write(title + "\n");
    bw.write(contents + "\n");
    bw.flush();
    bw.close();
     
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
