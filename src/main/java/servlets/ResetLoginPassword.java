package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

@WebServlet("/ResetLoginPassword")
public class ResetLoginPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emailId = request.getParameter("emailId");
		String otp = request.getParameter("otp");
		String password = request.getParameter("password");
		
		boolean isValidOtp = Model.getInstance().resetLoginPassword(emailId,otp,password);
		
		if(isValidOtp)
			response.getWriter().println("1");
		else
			response.getWriter().println("-1");
	}

}
