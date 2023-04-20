<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AdminMenu</title>
<style>
*{
padding:0;
margin:0;
}
h2{
	width:30%;
	font-size:40px;
	text-align:right;
	color:white;
}
#logo{
	display: flex;
    flex-direction: row;
    align-items: center;
}
#logo img{
	width:60px;
	height:40px;
}
#nav{
	display:flex;
	align-items:center;
	background-color: #1a1a00;
	justify-content:space-between;
}
#nav h3{
	color:white;
	font-size: 20px;
}
#nav .menu {
	margin-right:10px;
	width:200px;
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
.image a img {
	width:150px;
	height:150px;
}
.image a img:hover{
	width:175px;
	height:175px;
}
.image{
	display: flex;
	justify-content: center;
	align-items:center;
}
.image a figure figcaption{
	align-text:center;
}
.image a{
	margin-top:70px;
	padding: 50px;
	text-decoration:none;
}
figure {
  float: right;
  width: 100%;
  text-align: center;
  font-style: italic;
  font-size: smaller;
  text-indent: 0;
  border: thin silver solid;
  border-radius: 15px;
  margin: 0.5em;
}

</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<% 
	int adminId = (int) session.getAttribute("AdminId"); 
	String adminName = session.getAttribute("AdminName").toString();
	%>
    <div id="nav">
    	<div id="logo">
    		<img alt="sk" src="../images/banklogo.png">
			<h3 > SK BANK</h3>
		</div>
		<h2>Admin Menu Page</h2>
		<div class="menu">
			<a style="margin-top: 20px;" href="../Home/Home.jsp"><i class="fa-solid fa-circle-user fa-xl"></i> <%=adminName %></a>
		</div>		
	</div>
	<div class="image">
	<a href="AddAdmin.jsp"><figure><img src="../images/addAdmin.png" alt="AddAdmin"><figcaption>Add Admin</figcaption></figure></a>
	
	<a href="ViewRequests.jsp"><figure><img src="../images/notification.jpg" alt="Notifications"><figcaption>View Requests</figcaption></figure></a>
	
	<a href="ViewAccounts.jsp"><figure><img src="../images/accounts.jpg" alt="Accounts"><figcaption>View Accounts</figcaption></figure></a>
	
	</div>
</body>
</html>