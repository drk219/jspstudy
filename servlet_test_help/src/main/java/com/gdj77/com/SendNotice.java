package com.gdj77.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Servlet implementation class SendNotice
 */
public class SendNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendNotice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String writerId = (request.getParameter("writer_id").equals("")) ? "default_writer" : request.getParameter("writer_id");
		String title = (request.getParameter("title").equals("")) ? "default_title" : request.getParameter("title");
		
		StringBuilder builder = new StringBuilder();
		builder.append(LocalDate.now().toString())
			   .append("-")
			   .append( writerId )
			   .append("-")
			   .append( title )
			   .append(".txt");

		String uploadPath = getServletContext().getRealPath("upload");
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) uploadDir.mkdir();
		
		String uploadName = builder.toString();
		File uploadFile = new File(uploadDir, uploadName);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(uploadFile)); 
		
		writer.write(request.getParameter("contents"));
		writer.flush();
		writer.close();
		
		request.setAttribute("path", uploadPath);
		request.setAttribute("resultString", builder.toString());
		request.getRequestDispatcher("/SendNotice/confirmNotice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
