package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.Request;
import model.Model;


@WebServlet("/GetNotifications")
public class GetNotifications extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int custId = Integer.parseInt(req.getParameter("CustId"));
		
		List<Request> requests = Model.getInstance().getNotifications(custId);
		
		JSONArray jsonArray = new JSONArray();		
		
		for (Request request : requests) {
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("RequestId", request.getRequestId());
			jsonObject.put("RequestType", request.getRequestType());
			jsonObject.put("RequestedDate", request.getRequestedDate());
			jsonObject.put("RequestResult", request.getRequestResult());
			jsonObject.put("RepliedDate", request.getRepliedDate());
			jsonArray.add(jsonObject);
		}
		res.getWriter().print(jsonArray);
	}

}
