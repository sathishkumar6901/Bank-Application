package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Admin;
import dto.Customer;
import model.Model;



@WebServlet("/Login")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String emailId = req.getParameter("emailId");
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession();
		PrintWriter out = res.getWriter();
		Admin admin = Model.getInstance().chackAdminLogin(emailId,password);
		Customer customer = Model.getInstance().checkCustomerLogin(emailId,password);
		if(admin!=null) {
			session.setAttribute("AdminId", admin.getAdminId());
			session.setAttribute("AdminName", admin.getAdminName());
			out.println("1");
		}
		else if(customer!=null) {
			System.out.println("costomer"+" "+customer.getCustomerName());
			session.setAttribute("CustomerId", customer.getCustomerId());
			session.setAttribute("CustomerName", customer.getCustomerName());
			out.println("2");
		}
		else {
			System.out.println("login invalid-servlet");
			out.println("0");
		}
	}

}
