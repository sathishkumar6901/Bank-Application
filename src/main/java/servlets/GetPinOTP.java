package servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

@WebServlet("/GetPinOTP")
public class GetPinOTP extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		String cardNumber = request.getParameter("cardNumber");
		String cvvNumber = request.getParameter("cvvNumber");
		String expiryDate = request.getParameter("expiryDate");
		String emailId = request.getParameter("emailId");
		
		int id = (int) session.getAttribute("CustomerId");
		
		System.out.println(accountNumber+" "+cardNumber+" "+cvvNumber+" "+emailId);
		
		boolean check = Model.getInstance().checkValidCard(cardNumber,cvvNumber,expiryDate,accountNumber,id);
		
		if(check) {
			System.out.println("valid account");
			if(processRequest(request,response)) {
				System.out.println("process completed");
				System.out.println("hello");
				response.getWriter().println("1");
			}	
		}
		else
			response.getWriter().println("-1");
	}
	
	private boolean processRequest(HttpServletRequest request, HttpServletResponse response) {
		String emailId = request.getParameter("emailId");
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		String subject = "Verification Email for Reset Card Pin";
		MailSending sc = new MailSending();
		String otp = sc.generateOTP();
		boolean test = sc.sendMail(emailId,subject);
		if(test) {
			Model.getInstance().setPinOTP(accountNumber,otp);
			return true;
		}
	return false;
	}
}
