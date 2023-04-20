<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Model" %>
<%@ page import="dto.Account" %>
<%@ page import="dto.DebitCard"%>
<%@ page import="java.util.*" %>
<%@ page import="dto.Transaction"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account</title>
<link rel="stylesheet" href="Account.css">
<script src="Account.js" type="text/javascript"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'></link>
</head>
<body>
	<%
		int custId = (int) session.getAttribute("CustomerId"); 
		String custName = session.getAttribute("CustomerName").toString();
		Account account = Model.getInstance().getAccountDetails(custId);
		DebitCard card = Model.getInstance().getCardDetails(custId);
	%>
	<%if(account==null){%>
		<script type="text/javascript" language="JavaScript">
		noAccount();
		</script>
	<%} %> 
	<div id="nav">
    	<div id="logo">
    		<img alt="sk" src="../images/banklogo.png">
			<h3 > SK BANK</h3>
		</div>
		<h2 style="width:40%;">Customer Account Page</h2>
		<div class="menu">
			<a style="margin-top: 20px;" href="../Customer/CustomerMenu.jsp"><i class="fa-solid fa-backward fa-rotate-180"></i> Back </a>
			<a style="margin-top: 20px;" href="../Home/Home.jsp"><i class="fa-solid fa-circle-user fa-xl"></i> <%=custName %></a>
		</div>		
	</div>
	<h1 id="custId" style="display:none;"><%=custId %></h1>
	<div class="images">
		<div class="image1">
		<a href="#" onclick="withdrawPage()"><figure><img src="../images/withdraw.jpg" alt="Withdraw"><figcaption>Withdraw</figcaption></figure></a>
		<a href="#" onclick="depositPage()"><figure><img src="../images/deposit.jpg" alt="Deposit"><figcaption>Deposit</figcaption></figure></a>
		<a href="#" onclick="fundTransferPage()"><figure><img src="../images/fundTransfer.png" alt="Fund Transfer"><figcaption>Fund Transfer</figcaption></figure></a>
		<a href="#" onclick="accountPage()"><figure><img src="../images/account.jpg" alt="Account"><figcaption>Account Details</figcaption></figure></a>
		</div>
		<div class="image2">
		<a href="#" onclick="getTransactions('<%= custId%>')"><figure><img src="../images/transactions.jpg" alt="Transaction History"><figcaption>Transaction History</figcaption></figure></a>
		<a href="#" onclick="resetPasswordPage()"><figure><img src="../images/resetpassword.jpg" alt="Reset Transaction Password"><figcaption>Reset Transaction Password</figcaption></figure></a>
		<a href="#" onclick="resetPinPage()"><figure><img src="../images/resetpin.png" alt="ResetPin"><figcaption>Reset Card Pin</figcaption></figure></a>
		</div>
	</div>
	<div id="deposit">
		<div class="head">
			<h2 style="margin-left: 240px;">Deposit</h2>
			<button onclick="colsePopup()">X</button>
		</div>
		<table>
			<tr>
				<td><label>Account Number</label></td>
				<td><input type="text" id="daccountnumber"></td>
			</tr>
			<tr>
				<td><label>IFSC Code</label></td>
				<td><input type="text" id="difsccode"></td>
			</tr>
			<tr>
				<td><label>Amount</label></td>
				<td><input type="number" id="damount" min="1" max="1000000"></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="submit" type="submit" onclick="depositAmount()"></td>
			</tr>
		</table>
	</div>
	<div id="withdraw">
		<div class="head">
			<h2 style="margin-left: 180px;">Withdraw</h2>
			<button onclick="colsePopup()">X</button>
		</div>
		<table>
			<tr>
				<td><label>Card Number</label></td>
				<td><input type="text" id="wcardnumber"></td>
			</tr>
			<tr>
				<td><label>CVV Number</label></td>
				<td><input type="text" id="wcvvnumber"></td>
			</tr>
			<tr>
				<td><label>Expiry Date</label></td>
				<td><input type="date" id="wexpirydate"></td>
			</tr>
			<tr>
				<td><label>Amount</label></td>
				<td><input type="number" id="wamount"></td>
			</tr>
			<tr>
				<td><label>PIN Number</label></td>
				<td><input type="password" id="wpin"></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="submit" type="submit" onclick="withdrawAmount()"></td>
			</tr>
		</table>
	</div>
	
	<div id="fundTransfer" style="left:30%;">
		<div class="head">
			<h2 style="margin-left: 240px;">Fund Transfer</h2>
			<button onclick="colsePopup()">X</button>
		</div>
		<table>
			<tr>
				<td><label>Recipient Account Number</label></td>
				<td><input type="text" id="ftaccount"></td>
			</tr>
			<tr>
				<td><label>Recipient IFSC Code</label></td>
				<td><input type="text" id="ftifsccode"></td>
			</tr>
			<tr>
				<td><label>Transaction Amount</label></td>
				<td><input type="number" id="ftamount"></td>
			</tr>
			<tr>
				<td><label>Transaction Password</label></td>
				<td><input type="password" id="ftpassword"></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="submit" type="submit" onclick="fundTransafer()"></td>
			</tr>
		</table>
	</div>
	
	<div id="account" style="left:36%;">
		<div class="head">
			<h2 style="margin-left: 100px;">Account Details</h2>
			<button onclick="colsePopup()">X</button>
		</div>
		<div class="show">
		<table>
			<tr>
				<th>Account Number</th>
				<td><%= account.getAccountNumber()%></td>
			</tr>
			<tr>
				<th>Account Type</th>
				<td><%= account.getAccountType()%></td>
			</tr>
			<tr>
				<th>IFSC Code</th>
				<td><%= account.getIFSCCode()%></td>
			</tr>
			<tr>
				<th>Card Number</th>
				<td><%= card.getCardNumber()%></td>
			</tr>
			<tr>
				<th>Expiry Date</th>
				<td><%= card.getExpiryDate()%></td>
			</tr>
			<tr>
				<th>CVV Number</th>
				<td><%= card.getCvvNumber()%></td>
			</tr>
			<tr>
				<th>Balance</th>
				<td><%= account.getBalance()%></td>
			</tr>
		</table>
		</div>
	</div>
	<div id=history style="left:30%;">
		<div class="head">
			<h2 style="margin-left: 180px;">Transaction History</h2>
			<button onclick="colsePopup()">X</button>
		</div>
		<div class="show">
			<table>
			<thead>
				<tr>
					<th>Id </th>
					<th>Date </th>
					<th>Method </th>
					<th>Amount </th>
					<th>Type </th>
					<th>Balance </th>
				</tr>
			</thead>
			<tbody id="tableBody"></tbody>
			</table>
		</div>
	</div>
	<template>
		<tr>
			<th id="tid"> </th>
			<th id="tdate"> </th>
			<th id="tmethod"> </th>
			<th id="tamount"> </th>
			<th id="ttype"> </th>
			<th id="tbalance"> </th>
		</tr>
	</template>
	<div id="resetPassword">
		<div class="head">
			<h2 style="margin-left: 110px;">Reset Transaction Password</h2>
			<button onclick="colsePopup()">X</button>
		</div>
		<table>
			<tr>
				<td><label>Account Number</label></td>
				<td><input type="text" id="rpaccount"></td>
			</tr>
			<tr>
				<td><label>Email-Id</label></td>
				<td><input type="text" id="rpemailid"></td>
			</tr>
			<tr>
				<td></td>
				<td ><button style="margin-left: 20px;" id="btn1" onclick="getPasswordOTP()">Generate OTP</button></td>
			</tr>
			<tr>
				<td><label>OTP Number</label></td>
				<td><input type="text" id="rpotp"></td>
			</tr>
			<tr>
				<td><label>New Password</label></td>
				<td><input type="password" id="rppassword"></td>
			</tr>
			<tr>
				<td></td>
				<td><button style="margin-left: 20px;" id="btn2" onclick="resetTransactionPassword()">Submit</button></td>
			</tr>
		</table>
	</div>
	<div id="resetPin">
		<div class="head">
			<h2 style="margin-left: 180px;">Reset Card Pin</h2>
			<button onclick="colsePopup()">X</button>
		</div>
		<table>
			<tr>
				<td><label>Card Number</label></td>
				<td><input type="text" id="rscardnumber"></td>
			</tr>
			<tr>
				<td><label>CVV Number</label></td>
				<td><input type="text" id="rscvvnumber"></td>
			</tr>
			<tr>
				<td><label>Expiry Date</label></td>
				<td><input type="date" id="rsexpirydate"></td>
			</tr>
			<tr>
				<td><label>Account Number</label></td>
				<td><input type="text" id="rsaccount"></td>
			</tr>
			<tr>
				<td><label>Email-Id</label></td>
				<td><input type="text" id="rsemailid"></td>
			</tr>
			<tr>
				<td></td>
				<td><button style="margin-left: 20px;" id="btn1" onclick="getPinOTP()">Generate OTP</button></td>
			</tr>
			<tr>
				<td><label>OTP Number</label></td>
				<td><input type="text" id="rsotp"></td>
			</tr>
			<tr>
				<td><label>New PIN</label></td>
				<td><input type="password" id="rspin"></td>
			</tr>
			<tr>
				<td></td>
				<td><button style="margin-left: 20px;" id="btn2" onclick="resetCardPin()">Submit</button></td>
			</tr>
		</table>
	</div>
</body>
</html>