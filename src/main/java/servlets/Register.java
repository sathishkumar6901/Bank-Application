package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

@WebServlet("/Register")
public class Register extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		String emailId = req.getParameter("emailId");
		String phoneNumber = req.getParameter("phoneNumber");
		String accountType = req.getParameter("accountType");
		String dateOfBirth = req.getParameter("dateOfBirth");
		String aadharNumber = req.getParameter("aadharNumber");
		String password = req.getParameter("password");

		boolean isExistingCustomer = Model.getInstance().isCustomerExist(emailId, aadharNumber);
		if (!isExistingCustomer) {
			Model.getInstance().addNewCustomer(name, emailId, accountType, phoneNumber, dateOfBirth, aadharNumber,
					password);
			res.getWriter().println("2");
		} else {
			res.getWriter().println("3");
		}
	}
}
