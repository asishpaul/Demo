package org.cap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cap.model.PassRequestForm;
import org.cap.service.ILoginService;
import org.cap.service.LoginServiceImpl;

/**
 * Servlet implementation class PassRequestServlet
 */
@WebServlet("/PassRequestServlet")
public class PassRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ILoginService loginService=new LoginServiceImpl();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String empid=request.getParameter("empid");
		String fname=request.getParameter("firstname");
		String lname=request.getParameter("lastname");
		String emailid=request.getParameter("emailid");
		String gender=request.getParameter("gender");
		String address=request.getParameter("address");
		String doj=request.getParameter("doj");
		String location=request.getParameter("location");
		String pickuplocation=request.getParameter("pickuplocation");
		String ptime=request.getParameter("pickuptime");
		String designation=request.getParameter("designation");
		
		PassRequestForm busBean=new PassRequestForm();
		
		busBean.setEmployeeid(empid);
		busBean.setFirstname(fname);
		busBean.setLastname(lname);
		busBean.setEmail(emailid);
		busBean.setGender(gender);
		busBean.setAddress(address);
		busBean.setLocation(location);
		busBean.setPickUpLoc(pickuplocation);
		busBean.setDesignation(designation);
		String[] dpart=doj.split("-");
		
		LocalDate dateofjoininig=LocalDate.of(Integer.parseInt(dpart[0]),Integer.parseInt(dpart[1]), Integer.parseInt(dpart[2]));
		String[] tpart=ptime.split(":");
		LocalTime pickuptime=LocalTime.of(Integer.parseInt(tpart[0]),Integer.parseInt(tpart[1]) );
		busBean.setDoj(dateofjoininig);
		busBean.setPickUpTime(pickuptime);
		
		if(loginService.createRequest(busBean) != null) {
			response.sendRedirect("pages/request.html");
		}else {
			
			
			PrintWriter pw=response.getWriter();
			pw.println("<h1>Enter valid username and password</h1>");
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
		
	}

}
