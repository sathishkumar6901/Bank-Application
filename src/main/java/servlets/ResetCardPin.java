package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

@WebServlet("/ResetCardPin")
public class ResetCardPin extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		String otp = request.getParameter("otp");
		String pin = request.getParameter("pinNumber");
		
		boolean isValidOtp = Model.getInstance().resetCardPin(accountNumber,otp,pin);
		
		if(isValidOtp)
			response.getWriter().println("1");
		else
			response.getWriter().println("-1");

	}

}
