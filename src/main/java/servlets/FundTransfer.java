package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

@WebServlet("/FundTransfer")
public class FundTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		int id = (int) session.getAttribute("CustomerId");
		
		
		int recipientAccountNumber = Integer.parseInt(req.getParameter("accountNumber"));
		String ifscCode = req.getParameter("ifscCode");
		float amount = Float.parseFloat(req.getParameter("amount"));
		String password = req.getParameter("password");
		
		int  accountNumber = Model.getInstance().isCorrectPassword(id,password);
		float balance = Model.getInstance().getBalance(accountNumber);
		float reciepientBalance = Model.getInstance().getBalance(recipientAccountNumber);
		
		if(!Model.getInstance().isAccountExist(recipientAccountNumber,ifscCode)) {
			/*
			 * out.println("<script type=\"text/javascript\">");
			 * out.println("alert('The Recipient Account Number and IFSCCode are invalid');"
			 * ); out.println("location='Account.jsp';"); out.println("</script>");
			 */
			res.getWriter().println("-4");
		}
		else if((int)amount>(int)balance && accountNumber!=-1) {
			/*
			 * out.println("<script type=\"text/javascript\">");
			 * out.println("alert('Please enter a amount less than your balance');");
			 * out.println("location='Account.jsp';"); out.println("</script>");
			 */
			res.getWriter().println("-13");
		}
		else if(accountNumber==-1) {
			/*
			 * out.println("<script type=\"text/javascript\">");
			 * out.println("alert('Please enter a valid transaction password');");
			 * out.println("location='Account.jsp';"); out.println("</script>");
			 */
			res.getWriter().println("-5");
		}
		else {
			Model.getInstance().makeTransaction(accountNumber,"Account","debited",amount,balance-amount);
			Model.getInstance().makeTransaction(recipientAccountNumber,"Account","credited",amount,reciepientBalance+amount);
			/*
			 * out.println("<script type=\"text/javascript\">");
			 * out.println("alert('Your Transaction completed successfully!!!');");
			 * out.println("location='Account.jsp';"); out.println("</script>");
			 */
			res.getWriter().println("1");
		}
	}

}
