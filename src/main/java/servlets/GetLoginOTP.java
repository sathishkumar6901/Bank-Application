package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

@WebServlet("/GetLoginOTP")
public class GetLoginOTP extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emailId = request.getParameter("emailId");
		boolean check = Model.getInstance().isEmailExist(emailId);
		if(check) {
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
			String subject = "Verification Email for Reset Login Password";
			MailSending sc = new MailSending();
			String otp = sc.generateOTP();
			boolean test = sc.sendMail(emailId,subject);
			if(test) {
				Model.getInstance().setLoginOTP(emailId,otp);
				return true;
			}
		return false;
	}
}
