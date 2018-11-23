package org.cap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cap.model.LoginBean;
import org.cap.model.Routetable;
import org.cap.service.ILoginService;
import org.cap.service.LoginServiceImpl;

/**
 * Servlet implementation class AdminDetails
 */
@WebServlet("/AdminDetails")
public class AdminDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ILoginService loginService=new LoginServiceImpl();
    public AdminDetails() {
        super();
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		List<LoginBean>admin = loginService.listAdmin();
		
		PrintWriter out = response.getWriter();
		
		out.println("<h3>All Admin Details</h3>"
				+"<table border=2>"
				+"<tr>"
				+"<th>Admin Name</th>"
				+"<th>Admin Password</th>"
				+"</tr>");
		
		for(LoginBean details:admin) {
			
			out.println("<tr>"
					+"<td>"+details.getUsername()+"</td>"
					+"<td>"+details.getPassword()+"</td>"
					+"</tr>");
		}
	
	}

}
