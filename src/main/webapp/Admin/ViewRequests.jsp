<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="model.Model" %>
<%@ page import="dto.Request" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Requests</title>
<style>
*{
padding:0;
margin:0;
}
#nav h2{
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
	margin-right:100px;
	width:300px;
	display:flex;
	justify-content:flex-end;
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
table {
	justify-content: center;
	margin-top: 50px;
	margin-left: 400px;
	font-size: 25px;
	border: solid;
	border-radius: 10px;
	background-color: #ccffcc;
}
tr, th, td {
	border: solid 2px;
	font-size: 20px;
	padding: 10px;
	border-radius: 5px;
}
</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'></link>

<script type="text/javascript" language="JavaScript">
function noRequests(){
	swal("You didn't receive any request from customer").then(function() {
		window.location = "AdminMenu.jsp";
});
}		
</script>		
</head>
<body>
	<%
		int adminId = (int) session.getAttribute("AdminId"); 
		String adminName = session.getAttribute("AdminName").toString();
		List<Request> requests = Model.getInstance().getRequests();
	%>
	<%if(requests.isEmpty()) {%>
	<script type="text/javascript" language="JavaScript">
		noRequests();
	</script>
	<%} %>
	<div id="nav">
    	<div id="logo">
    		<img alt="sk" src="../images/banklogo.png">
			<h3 > SK BANK</h3>
		</div>
		<h2>View Request Page</h2>
		<div class="menu">
			<a style="margin-top: 20px;" href="AdminMenu.jsp"><i class="fa-solid fa-backward fa-rotate-180"></i> Back </a>
			<a style="margin-top: 20px;" href="../Home/Home.jsp"><i class="fa-solid fa-circle-user fa-xl"></i> <%=adminName %></a>
		</div>		
	</div>
	<div class="container">
	<table>
		<tr>
			<th>Request Id</th>
			<th>Customer Id</th>
			<th>Request Type</th>
			<th>Requested Date</th>
			<th>Action</th>
		</tr>
		<%for(Request r :requests) {%>
		<tr>
			<td><%= r.getRequestId() %></td>
			<td><%= r.getCustomerId() %></td>
			<td><%= r.getRequestType() %></td>
			<td><%= r.getRequestedDate() %></td>
			<td><a href="ViewDetails.jsp?id=<%= r.getCustomerId() %>&type=<%= r.getRequestType()%>">View</a></td>
		</tr>	
		<%} %>
	</table>
	</div>
</body>

</html>