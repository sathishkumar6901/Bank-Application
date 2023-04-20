package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String cardNumber = req.getParameter("cardNumber");
		String cvvNumber = req.getParameter("cvvNumber");
		String expiryDate = req.getParameter("expiryDate");
		float amount = Float.parseFloat(req.getParameter("amount"));
		String pin = req.getParameter("pinNumber");;
		
		
		int accountNumber = Model.getInstance().isCardExist(cardNumber,cvvNumber,expiryDate,pin);
		float balance = Model.getInstance().getBalance(accountNumber);
		if(accountNumber==-1) {
			res.getWriter().println("-11");
		}
		else if((int)amount>(int)balance) {
			res.getWriter().println("-13");
		}
		else {
			Model.getInstance().makeTransaction(accountNumber,"ATM","debited",amount,balance-amount);
			res.getWriter().println("1");
		}
	}

}

