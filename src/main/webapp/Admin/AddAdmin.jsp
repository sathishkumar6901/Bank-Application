<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Admin</title>
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
}
h1 {
	text-align: center;
	color: blue;
	font-family: "calibri";
}

.container {
	width: 300px;
	margin: 10px auto 0 auto;
	background-color: #ccffcc;
	border-radius: 10px;
	padding: 10px;
}

.input-group {
	display: flex;
	flex-direction: column;
	margin-bottom: 15px;
}
.container .input-group input {
	border-radius: 5px;
	margin-top: 5px;
	width: 70%;
	height: 25px;
	padding-left: 10px;
	font-size: 15px;
}

.input-group .format {
	display: flex;
	flex-direction: row;
	border-radius: 5px;
	align-items: center;
	justify-content: space-between;
}

.input-group .format button {
	height: 25px;
	background-color: #c0c0c0;
	border-radius: 5px;
	padding: revert;
	cursor: pointer;
}

.submit {
	background-color: #c0c0c0;
	font-size: 15px;
	color: black;
	padding: 5px;
	margin: 5px 0px;
	border-radius: 10px;
	width: 100%;
	cursor: pointer;
}
.swal2-popup #swal2-title {
    display: block;
    position: relative;
    max-width: 100%;
    margin: 0 0 .4em;
    padding: 0;
    color: #595959;
    font-size: 1.875em;
    font-weight: 400;
    text-align: left;
    text-transform: none;
    word-wrap: break-word;
    font-size: 20px;
}
</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'></link>
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
		<h2>Add New Admin Page</h2>
		<div class="menu">
			<a style="margin-top: 20px;" href="AdminMenu.jsp"><i class="fa-solid fa-backward fa-rotate-180"></i> Back </a>
			<a style="margin-top: 20px;" href="../Home/Home.jsp"><i class="fa-solid fa-circle-user fa-xl"></i> <%=adminName %></a>
		</div>		
	</div>
	<div class="container">
		<h1>Registration</h1>
		<div class="input-group">
			<label for="adminname">Admin Name</label>
			<div class="format">
				<input type="text" id="adminname" name="adminname" placeholder="Enter adminname">
				<button id="adminnameformat"  onclick="adminNameFormat()">Format</button>
			</div>
		</div>
		<div class="input-group">
			<label for="emailid">Email-Id</label>
			<div class="format">
				<input type="email" id="emailid" name="emailid" placeholder="Enter emailid">
				<button  id="emailidformat" onclick="emailIdFormat()">Format</button>
			</div>
		</div>
		<div class="input-group">
			<label for="phonenumber">Phone Number</label>
			<div class="format">
				<input type="text" id="phonenumber" name="phonenumber" placeholder="Enter phonenumber">
				<button id="phonenumberformat" onclick="phoneNumberFormat()">Format</button>
			</div>
		</div>
		<div class="input-group">
			<label for="password">Password</label>
			<div class="format">
				<input type="password" id="password" name="password" placeholder="Enter password">
				<button id="passwordformat" onclick="passwordFormat()">Format</button>
			</div>
		</div>
		<button class="submit" onclick="addAdmin()">Submit</button>
	</div>
</body>
<script>
function adminNameFormat(){
	swal("1.Username must contains atleast 5 characters\n2.Username must in English Alphabets letters");
}

function emailIdFormat(){
	swal("1.EmailId may contains alphabets and numeric values\n2.EmailId must ends with @gmail.com")
}

function phoneNumberFormat(){
	swal("1.Phonenumber must be a numeric values\n2.Phonenumber must have 10 letters\n")
}

function passwordFormat(){
	swal("1.Password must be 8 to 15 characters\n2.Password must contain atleast 1 alphabet\n"+ 
	"3.Password must contain atleast 1 numeric value\n4.Password must contain atlest 1 special character");
}

function addAdmin() {
	let name = document.querySelector("#adminname");
	let emailId = document.querySelector("#emailid");
	let phoneNumber = document.querySelector("#phonenumber");
	let password = document.querySelector("#password");
	console.log(password.value);
	var obj = new XMLHttpRequest();
	obj.onreadystatechange = function() {
		if (obj.readyState == 4) {
			var result = obj.responseText;
			if (result == -1) {
				swal("Please enter a admin-name in valid format");
			} else if (result == -2) {
				swal("Please enter a Email-Id in valid format");
			} else if (result == -3) {
				swal("Please enter a Phone Number in valid format");
			} else if (result == -5) {
				swal("Please enter a Password in valid format");
			} else if (result == 3) {
				swal("The given Email-Id already Exist!!!");
			} else {
				swal("Your Process completed successfully!!!").then(function() {
						window.location = "AdminMenu.jsp";
					});
			}
		}
	};
	obj.open("POST", "http://localhost:8080/BankingApplication/AddAdmin");
	obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	var data = "name=" + name.value.trim() + "&emailId=" + emailId.value +
				"&phoneNumber="+ phoneNumber.value +"&password="+ password.value;
	obj.send(data);
}
</script>
</html>