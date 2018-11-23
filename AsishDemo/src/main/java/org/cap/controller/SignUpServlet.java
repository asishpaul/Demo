package org.cap.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cap.model.LoginBean;
import org.cap.service.ILoginService;
import org.cap.service.LoginServiceImpl;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ILoginService loginService=new LoginServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String username=request.getParameter("Username");
		String password=request.getParameter("Password");
		
		LoginBean loginBean=new LoginBean();
		
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		
		if(loginService.signupRequest(loginBean) != null) {
			PrintWriter pw=response.getWriter();
			pw.println("<h1>Sign Up Successful</h1>");
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}else {
			
			
			PrintWriter pw=response.getWriter();
			pw.println("<h1>Enter valid username and password</h1>");
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
		
	}

}
