package com.gdj77.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Servlet implementation class MemberJoin
 */
public class MemberJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		request.setAttribute("member_id", request.getParameter("member_id"));
		request.setAttribute("member_pw", request.getParameter("member_pw"));
		
		String memberName = request.getParameter("member_name");
		request.setAttribute("member_name", (memberName.equals("") ? "test" : memberName));
		
		String birthYear = request.getParameter("birth_year");
		request.setAttribute("birth_year", (birthYear.equals("") ? LocalDate.now().getYear() : birthYear));
		request.setAttribute("birth_month", request.getParameter("birth_month"));
		String birthDay = request.getParameter("birth_day");
		request.setAttribute("birth_day", (birthDay.equals("") ? LocalDate.now().getDayOfMonth() : birthDay));
		
		request.setAttribute("gender", request.getParameter("gender"));
		request.setAttribute("member_email", request.getParameter("member_email"));
		request.setAttribute("member_phone", request.getParameter("member_phone"));
		
		request.getRequestDispatcher("/MemberJoin/joinConfirm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
