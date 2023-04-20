package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

@WebServlet("/ResetTransactionPassword")
public class ResetTransactionPassword extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		String otp = request.getParameter("otp");
		String password = request.getParameter("password");
		
		boolean isValidOtp = Model.getInstance().resetTransactionPassword(accountNumber,otp,password);
		
		if(isValidOtp)
			response.getWriter().println("1");
		else
			response.getWriter().println("-1");
	}

}
