package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		int accountNumber = Integer.parseInt(req.getParameter("accountNumber"));
		String ifscCode = req.getParameter("ifscCode");
		float amount = Float.parseFloat(req.getParameter("amount"));
	
		float balance = Model.getInstance().getBalance(accountNumber);
		
		if(!Model.getInstance().isAccountExist(accountNumber,ifscCode)) {
			/*
			 * out.println("<script type=\"text/javascript\">");
			 * out.println("alert('The Recipient Account Number and IFSCCode are invalid');"
			 * ); out.println("location='Account.jsp';"); out.println("</script>");
			 */
			res.getWriter().println("-4");
		}
		else {
			Model.getInstance().makeTransaction(accountNumber,"ATM","credited",amount,balance+amount);
			/*
			 * out.println("<script type=\"text/javascript\">");
			 * out.println("alert('Your Transaction completed successfully!!!');");
			 * out.println("location='Account.jsp';"); out.println("</script>");
			 */
			res.getWriter().println("1");
		}
	}

}
