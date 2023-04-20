package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

@WebServlet("/RequestReject")
public class RequestReject extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int adminId = (int)session.getAttribute("AdminId");
		int custId = Integer.parseInt(request.getParameter("customerId"));
		String requestType = request.getParameter("requestType");
		String replyMessage = request.getParameter("message");
		String requestResult = request.getParameter("result");
		
		Model.getInstance().setReplyMessage(custId,adminId,requestType,requestResult,replyMessage);
		
		response.getWriter().println("1");
		
	}

}
