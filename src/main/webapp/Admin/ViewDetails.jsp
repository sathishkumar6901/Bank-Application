<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.Model"%>
<%@ page import="dto.Account"%>
<%@ page import="dto.Customer"%>
<%@ page import="dto.DebitCard"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Details</title>
<style>
*{
	padding:0;
	margin:0;
}
body{
	background-color:#ffffff;
	background-size:cover;
}
#nav h2{
	width:25%;
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
	text-decoration: none;
}
.container {
	height: 30px;
}
h2{
	font-size: 40px; 
	padding-left: 150px;
}
table {
	margin-left: 120px;
	font-size: 25px;
	border: solid;
}

tr, th, td {
	border: solid 2px;
	font-size: 20px;
	padding: 10px;
}
.buttons{
	display:flex;
	justify-content:space-between;
	float: right; 
	margin-right: 310px;
	height: 30px;
}
#btn1{
	margin-right: 20px;
	width: 80px;
	border-radius: 10px;
}
#btn2{
	margin-left: 20px;
	width: 80px;
	border-radius: 10px;
}
#btn1:hover{
	background-color: #b3ff1a;
}
#btn2:hover{
	background-color: #ff6666;
}
</style>
</head>
<body>
	<%
		int custId = Integer.parseInt(request.getParameter("id"));
		String rType = request.getParameter("type");
		Customer customer = Model.getInstance().getPersonalDetails(custId);
		Account account = Model.getInstance().getAccountDetails(custId);
		DebitCard card = Model.getInstance().getCardDetails(custId);
	%>
	<div id="nav">
		<h3>SK BANK</h3>
		<h2>Customer Detail Page</h2>
		<div class="menu">
			<a href="ViewRequests.jsp">Back</a>
			<a style="margin-right:10px;"href="Login.jsp">LogOut</a>
		</div>		
	</div>
	<div class="container"></div>
	<h2 >Personal Details</h2>
	<table>
		<tr>
			<th>Customer Id</th>
			<th>Email Id</th>
			<th>Customer Name</th>
			<th>Date of Birth</th>
			<th>Phone Number</th>
			<th>Aadhar Number</th>
			<th>Account Type</th>
		</tr>
		<tr>
			<td><%=customer.getCustomerId()%></td>
			<td><%=customer.getEmailId()%></td>
			<td><%=customer.getCustomerName()%></td>
			<td><%=customer.getDob()%></td>
			<td><%=customer.getPhoneNumber()%></td>
			<td><%=customer.getAadharNumber()%></td>
			<td id="accounttype"><%=customer.getAccountType()%></td>
		</tr>
	</table>
	<br>
	<br>
	<%
	if (account != null) {
	%>
	<h2>Account Details</h2>
	<table>
		<tr>
			<th>AccountNumber</th>
			<th>AccountType</th>
			<th>IFSCCode</th>
			<th>Card Number</th>
			<th>Expiry Date</th>
			<th>CVV Number</th>
			<th>Balance</th>
		</tr>
		<tr>
			<th><%=account.getAccountNumber()%></th>
			<th><%=account.getAccountType()%></th>
			<th><%=account.getIFSCCode()%></th>
			<th><%=card.getCardNumber() %></th>
			<th><%=card.getExpiryDate() %></th>
			<th><%=card.getCvvNumber() %></th>
			<th><%=account.getBalance()%></th>
		</tr>
	</table>
	<%
	}
	%>
	<div class="buttons">
	<button id="btn1" onclick="accept()" value="accept">Accept</button>
	<button id="btn2" onclick="reject()" value="reject">Reject</button>
	</div>

</body>
<script type="text/javascript">
function accept(){
	let rType ="<%out.print(rType);%>";
	let custId = "<%out.print(custId);%>";
	let aType = document.getElementById("accounttype").innerText;
	if(rType=="NewCustomer"){
		let message = "Congrats!!! Your Account created successfully!"
		let result = "approved";
		var obj1=new XMLHttpRequest();
		obj1.onreadystatechange=function(){
			if(obj1.readyState==4){
				alert("Your Response processed successfully!!!");
				location = "ViewRequests.jsp";
			}
		};
		obj1.open("POST","http://localhost:8080/BankingApplication/RequestAccept");
		obj1.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var data1 = "customerId="+custId+"&requestType="+rType+"&accountType="+aType+"&message="+message+"&result="+result;
		obj1.send(data1);
	}
}

function reject(){
	let rType ="<%out.print(rType);%>";
	let custId = "<%out.print(custId);%>";
	let message = "Sorry!!! We are unable to accept your request!";
	let result = "rejected";
	var obj2 = new XMLHttpRequest();
	obj2.onreadystatechange = function() {
		if (obj2.readyState == 4) {
			alert("Your Response processed successfully!!!");
			location = "ViewRequests.jsp";
		}
	};
	obj2.open("POST","http://localhost:8080/BankingApplication/RequestReject");
	obj2.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	var data2 = "customerId="+custId+"&requestType="+rType+"&message="+message+"&result="+result;
	obj2.send(data2);
	}
</script>
</html>