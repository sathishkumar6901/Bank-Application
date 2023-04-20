<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Model" %>
<%@ page import="dto.Account" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accounts</title>
<style type="text/css">
*{
	padding:0;
	margin:0;
}
body{
	background-color:#ffffff;
	background-size:cover;
}
h2{
	width:30%;
	margin-top:10px;
	font-size:40px;
	text-align:right;
	color:white;
}
#nav{
	display:flex;
	align-items:center;
	background-color: #1a1a00;
	justify-content:space-between;
}
#nav h3{
	color:white;
	padding: 15px 10px;
	font-size: 20px;
}
#nav .menu {
	margin-right:70px;
	width:150px;
	display:flex;
	justify-content:space-between;
}
#nav .menu a{
	font-size:20px;
	text-decoration:none;	
	color:white;
}
#nav .menu a:hover{
	color:#b3ff1a;
}
a{
	padding:15px 10px;
}
table {
	justify-content: center;
	margin-top: 50px;
	margin-left: 300px;
	font-size: 25px;
	border: solid;
}
tr, th, td {
	border: solid 2px;
	font-size: 20px;
	padding: 10px;
}
</style>
<script type="text/javascript" language="JavaScript">
function alertMessage(){
	alert("You didn't receive any request from customer");
	location='AdminMenu.jsp';
}		
</script>
</head>
<body>
	
	<%
		List<Account> accounts= Model.getInstance().getAccounts();
	%>
	<%if(accounts.isEmpty()) {%>
	<script type="text/javascript" language="JavaScript">
		alertMessage();
	</script>
	<%} %>
	<div id="nav">
		<h3>SK BANK</h3>
		<h2>Bank Account Page</h2>
		<div class="menu">
			<a href="AdminMenu.jsp">Back</a>
			<a href="../Home/Login.jsp">LogOut</a>
		</div>		
	</div>
	<h2>Bank Accounts</h2>
	<div class="container">
	<table>
		<tr>
			<th>Customer Id</th>
			<th>Account Number</th>
			<th>Account Type</th>
			<th>IFSC Code</th>
			<th>Balance</th>
		</tr>
		<%for(Account a:accounts){%>
		<tr>
			<td><%= a.getCustomerId() %></td>
			<td><%= a.getAccountNumber() %></td>
			<td><%= a.getAccountType() %></td>
			<td><%= a.getIFSCCode() %></td>
			<td><%= a.getBalance()%></td>
		</tr>
		<%} %>
	</table>
	</div>
</body>
</html>