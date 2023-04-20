<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Model" %>
<%@ page import="java.util.*" %>
<%@ page import="dto.Customer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CustomerMenu</title>
<link rel="stylesheet" href="Customer.css">
<script src="Customer.js" type="text/javascript"></script>
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
		Customer customer = Model.getInstance().getPersonalDetails(custId);
	%>
    <div id="nav">
    	<div id="logo">
    		<img alt="sk" src="../images/banklogo.png">
			<h3 > SK BANK</h3>
		</div>
		<h2>Customer Menu Page</h2>
		<div class="menu">
			<a style="margin-top: 20px;" href="../Home/Home.jsp"><i class="fa-solid fa-circle-user fa-xl"></i> <%=custName %></a>
		</div>		
	</div>
	<div class="image">
		<a href="#" onclick="getNotifications('<%= custId%>')"><figure><img src="../images/notification.jpg" alt="Notifications"><figcaption>Notifications</figcaption></figure></a>
		<a href="#" onclick="profilePage()"><figure><img src="../images/addAdmin.png" alt="profile"><figcaption>Profile</figcaption></figure></a>
		<a href="../Account/Account.jsp"><figure><img src="../images/account.jpg" alt="Account"><figcaption>Account</figcaption></figure></a>
		<a href="#"><figure><img src="../images/loan.png" alt="Loan"><figcaption>Apply Loan</figcaption></figure></a>
	</div>
	
	<div id="notification">
		<div class="head">
			<h2>Notifications</h2>
			<button onclick="colsePopup()">X</button>
		</div>
		<table>
		<thead>
		<tr>
			<th>Request Id</th>
			<th>Request Type</th>
			<th>Requested Date</th>
			<th>Request Result</th>
			<th>Replied Date</th>
			<th>Action</th>
		</tr>
		</thead>
		<tbody id="tableBody">
		</tbody>
	</table>
	</div>
	<template>
     	<tr>
        	<th id="requestId" scope="row"> </th>
            <td id="requestType"></td>
            <td id="requestedDate"> </td>
            <td id="requestResult"> </td>
            <td id="repliedDate"> </td>
            <td id=action><button class="btn1" onclick="">MarkAsRead</button> </td>              
        </tr>
	</template>
	
	<div id="profile" style="left:35%;">
		<div class="head">
			<h2 style="margin-left:130px;">Profile</h2>
			<button onclick="colsePopup()">X</button>
		</div>
		<table>
		<tr>
			<th>Customer Id</th>
			<td><%= customer.getCustomerId() %></td>
		</tr>
		<tr>
			<th>Email Id</th>
			<td><%= customer.getEmailId() %></td>
		</tr>
		<tr>
			<th>Name</th>
			<td><%= customer.getCustomerName() %></td>
		</tr>
		<tr>
			<th>Date of Birth</th>
			<td><%= customer.getDob() %></td>
		</tr>
		<tr>
			<th>PhoneNumber</th>
			<td><%= customer.getPhoneNumber() %></td>
		</tr>
		<tr>
			<th>AadharNumber</th>
			<td><%= customer.getAadharNumber()%></td>
		</tr>
		</table>
	</div>
	
</body>
</html>