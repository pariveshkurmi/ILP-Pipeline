package com.wipro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Parivesh
 *
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		
		if (email.isEmpty() || pass.isEmpty()) {
			  RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			   out.println("<font color=red>Please fill all the fields</font>");
			   rd.include(request, response);
			  } else {
				
					PrintWriter pout= response.getWriter();
					if (Objects.equals(email, "parivesh.kurmi@gmail.com") ) {
						pout.write("Login successfull...");
						//RequestDispatcher rd = request.getRequestDispatcher("/success.jsp");
						//rd.forward(request,response);
						response.sendRedirect("./book-ticket.jsp");
						return;
					}
					pout.write("Login fail...");
					 
					RequestDispatcher rd = request.getRequestDispatcher("/fail.jsp");
					rd.forward(request,response);
					
					
			  }
	}

	
	

}
