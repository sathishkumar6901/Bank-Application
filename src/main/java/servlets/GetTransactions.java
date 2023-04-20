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
import dto.Transaction;
import model.Model;


@WebServlet("/GetTransactions")
public class GetTransactions extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int custId = Integer.parseInt(req.getParameter("CustId"));
		
		List<Transaction> transactions = Model.getInstance().getTransactions(custId);
		
		JSONArray jsonArray = new JSONArray();		
		
		for (Transaction transaction : transactions) {
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("TransactionId", transaction.getTransactionId());
			jsonObject.put("TransactionDate", transaction.getTransactionDate());
			jsonObject.put("TransactionMethod", transaction.getTransactionMethod());
			jsonObject.put("TransactionAmount", transaction.getTransactionAmount());
			jsonObject.put("TransactionType", transaction.getTransationType());
			jsonObject.put("Balance", transaction.getCurrentBalance());
			jsonArray.add(jsonObject);
		}
		res.getWriter().print(jsonArray);
	}

}
