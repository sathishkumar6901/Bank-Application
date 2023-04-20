<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<style>
* {
	padding: 0;
	margin: 0;
}

body {
	background-image: url('../images/homeBank.jpg');
	background-size: 1600px 800px;
}

h2{
	width: 57%;
	margin-top: 10px;
	font-size: 40px;
	text-align: right;
	color: white;
}

#nav {
	display: flex;
	justify-content: space-between;
	background-color: #1a1a00;
	height:70px;
}

#nav img {
	width: 70px;
	height: 50px;
}

#nav .menu {
	margin-right: 20px;
	width: 270px;
	display: flex;
	justify-content: space-between;
}

#nav .menu li {
	list-style-type: none;
}

#nav .menu li a {
	font-size: 20px;
	text-decoration: none;
	margin-left:70px;
	color: white;
}

#nav .menu .anchor1:hover a, #nav .menu .anchor2:hover a {
	color: #b3ff1a;
}

.anchor1, .anchor2 {
	padding: 15px 10px;
}
#logo img{
	position:absolute;
	top:291px;
	right:399px;
	width:60px;
	height:40px;
}
</style>

<title>Home</title>
</head>
<body>
	<div id="nav">
		<h2 style="display: flex; justify-content: center; margin-left: 250px">
			<img alt="sk" src="../images/banklogo.png"> SK BANK
		</h2>
		<ul class="menu">
			<li class="anchor2"><a href="Login.jsp">Login</a></li>
		</ul>
	</div>
	<div id="logo">
		<img alt="sk" src="../images/hbanklogo.png">;
	</div>
</body>
</html>