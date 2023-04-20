package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;


@WebServlet("/AddAdmin")
public class AddAdmin extends HttpServlet {
	private Model model = Model.getInstance();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		String emailId = req.getParameter("emailId");
		String phoneNumber = req.getParameter("phoneNumber");
		String password = req.getParameter("password");
		
		model.addNewAdmin(name,emailId,phoneNumber,password);
		
		PrintWriter out = res.getWriter(); 
		out.println("<script type=\"text/javascript\">"); 
		out.println("alert('New Admin added successfully...');"); 
		out.println("location='AdminMenu.jsp';"); 
		out.println("</script>");
	}

}
