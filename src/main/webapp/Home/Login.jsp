<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'></link>
<link rel="stylesheet" href="Login.css">
</head>
<body>
	<!-- header -->
	<div id="nav">
		<div id="logo">
			<img alt="sk" src="../images/banklogo.png">
			<h3>SK BANK</h3>
		</div>
		<h2>Login Page</h2>
		<div class="menu">
			<a class="anchor" href="Home.jsp"> 
			<i class="fa-solid fa-backward fa"></i>Back
			</a>
		</div>
	</div>
	
	<!-- Login Form -->
	<div id="loginform">
		<h1>Login</h1>
		
			<div class="input-group">
				<label for="lemailid">Email-Id</label> 
				<input type="email" id="lemailid" name="emailId" placeholder="Enter email-id" minlength="10" maxlength="25">
			</div>
			<div class="input-group">
				<label for="lpassword">Password</label> 
				<input type="password" id="lpassword" name="password" placeholder="Enter password" minlength="8" maxlength="15">
			</div>
			<div class="input-group">
				<a href="JavaScript:void(0);" onclick="passwordResetForm()">Forget Password?</a>
			</div>	
		
			<button class="submit" type="submit" onclick="login()">Login</button>
		<p>Don't have an Account?
			<a href="JavaScript:void(0);" onclick="registrationForm()">Register here</a>
		</p>
	</div>

	<!-- Registration form -->
	<div id="registerform">
		<h1>Registration</h1>
			<div class="input-group">
				<label for="rusername">User Name</label>
				<div class="format">
					<input type="text" id="rusername" name="userName" placeholder="Enter user name" minlength="5" maxlength="30">
					<button id="usernameformat"  onclick="userNameFormat()">Format</button>
				</div>
			</div>
			<div class="input-group">
				<label for="remailid">Email-Id</label>
				<div class="format">
					<input type="email" id="remailid" name="emailId" placeholder="Enter emailid" minlength="10" maxlength="25">
					<button  id="emailidformat" onclick="emailIdFormat()">Format</button>
				</div>
			</div>
			<div class="input-group">
				<label for="rphonenumber">Phone Number</label>
				<div class="format">
					<input type="text" id="rphonenumber" name="phoneNumber" placeholder="Enter phonenumber" minlength="10" maxlength="10">
					<button id="phonenumberformat" onclick="phoneNumberFormat()">Format</button>
				</div>
			</div>
			<div class="input-group">
				<label for="raadharnumber">Aadhar Number</label>
				<div class="format">
					<input type="text" id="raadharnumber" name="aadharNumber" placeholder="Enter aadharnumber" minlength="12" maxlength="12">
					<button id="aadharnumberformat" onclick="aadharNumberFormat()">Format</button>
				</div>
			</div>
			<div class="input-group">
				<label for="rpassword">Password</label>
				<div class="format">
					<input type="password" id="rpassword" name="password" placeholder="Enter password" minlength="8" maxlength="15">
					<button id="rpasswordformat" onclick="passwordFormat()">Format</button>
				</div>
			</div>
			<div class="input-group">
				<label for="accounttype">Account Type</label> 
				<select id="raccounttype" name="accounttype">
					<option value="savings">savings</option>
					<option value="salary">salary</option>
					<option value="NRI">NRI</option>
				</select>
			</div>
			<div class="input-group">
				<label for="dateofbirth">Date Of Birth</label> 
				<input id="rdateofbirth" type="date" name="dateOfBirth" max="" min="" onkeydown="return false">
			</div>
		
		<button class="submit" type="submit" onclick="register()">Register</button>
		<p>Already have an Account?
		<a href="JavaScript:void(0);" onclick="loginForm()">Login here</a>
		</p>
	</div>
	
	<!-- Reset Password form -->
	<div id="resetpassword">
		<h1>Forget Password</h1>
			<div class="input-group">
				<label for="pemailid">Email-Id</label> 
				<input type="email" id="pemailid" name="emailId" placeholder="Enter emailid" minlength="10" maxlength="25" required>
			</div>
			<button id="code" type="submit" onclick="getLoginOTP()">Generate OTP</button>
			<div class="input-group">
				<label for="otp">OTP Number</label> 
				<input type="text" id="potp" name="otp" placeholder="Enter OTP" required>
			</div>
			<div class="input-group">
				<label for="ppassword">Password</label>
				<div class="format">
					<input type="password" id="ppassword" name="password" placeholder="Enter password" minlength="8" maxlength="15" required style="width: 70%;">
					<button id="ppasswordformat" onclick="passwordFormat()">Format</button>
				</div>
			</div>
		<button class="submit" type="submit" onclick="resetLoginPassword()">Reset Password</button>
		<p style="text-align: center;">Back to the 
			<a href="JavaScript:void(0);" onclick="loginForm()">Login page</a> | 
			<a href="JavaScript:void(0);" onclick="registrationForm()">Register page</a>
		</p>
	</div>
</body>
<script src="Login.js" type="text/javascript"></script>
<script type="text/javascript">
	var todayDate = new Date();
	var month = todayDate.getMonth();
	var maxYear = todayDate.getUTCFullYear() - 10;
	var minYear = todayDate.getUTCFullYear()-130;
	var tdate = todayDate.getDate();
	if (month < 10) {
		month = "0" + month;
	}
	if (tdate < 10) {
		tdate = "0" + tdate;
	}
	var maxDate = maxYear + "-" + month + "-" + tdate;
	var minDate = minYear + "-" + month + "-" + tdate;
	document.getElementById("rdateofbirth").setAttribute("max", maxDate);
	document.getElementById("rdateofbirth").setAttribute("min", minDate);
</script>
</html>


