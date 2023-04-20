function loginForm(){	
	
	ShowHideForm("loginform","Show");
	ShowHideForm("registerform","Hide");
	ShowHideForm("resetpassword","Hide");
}

function registrationForm(){
		
	ShowHideForm("loginform","Hide");
	ShowHideForm("registerform","Show");
	ShowHideForm("resetpassword","Hide");
}

function passwordResetForm(){	
	
	ShowHideForm("loginform","Hide");
	ShowHideForm("registerform","Hide");
	ShowHideForm("resetpassword","Show");
}

function ShowHideForm(FormID,ShowOrHide){
	var Form = document.getElementById(FormID);

	if(ShowOrHide == "Show"){
		Form.style.display = 'block';
	}else{
		Form.style.display = 'none';
	}
}

function userNameFormat(){
	swal("1.Username must contains atleast 5 characters\n2.Username must in English Alphabets letters");
}

function emailIdFormat(){
	swal("1.EmailId may contains alphabets and numeric values\n2.EmailId must ends with @gmail.com")
}

function phoneNumberFormat(){
	swal("1.Phonenumber must be numeric values\n2.Phonenumber must have 10 numbers\n")
}

function aadharNumberFormat(){
	swal("1.Aadharnumber must be numeric values\n2.The aadharnumber must have 12 numbers\n")
}

function passwordFormat(){
	swal("1.Password must be 8 to 15 characters\n2.Password must contain atleast 1 alphabet\n"+ 
	"3.Password must contain atleast 1 numeric value\n4.Password must contain atlest 1 special character except %");
}

function login() {
	let emailId = document.querySelector("#lemailid");
	let password = document.querySelector("#lpassword");
	console.log(password.value);
	if(password.value.indexOf("%")!=-1){
		swal("Please enter a Password in valid format");
		return;
	}
	var obj = new XMLHttpRequest();
	obj.onreadystatechange = function() {
		if (obj.readyState == 4) {
			var result = obj.responseText;
			if (result == -2) {
				swal("Please enter a Email-Id in valid format");
			} else if (result == -5) {
				swal("please enter a Password in valid format");
			} else if (result == 0) {
				swal("Please enter a valid login details");
			} else if (result == 1) {
				swal("You are logged-in successfully!!!").then(function() {
					window.location = "../Admin/AdminMenu.jsp";
				});
			} else {
				swal("You are logged-in successfully!!!").then(function() {
					window.location = "../Customer/CustomerMenu.jsp";
				});
			}
		}
	};
	obj.open("POST", "http://localhost:8080/BankingApplication/Login");
	obj.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	var data = "emailId=" + emailId.value + "&password=" + password.value;
	obj.send(data);
}

function register() {
	let name = document.querySelector("#rusername");
	let emailId = document.querySelector("#remailid");
	let accountType = document.querySelector("#raccounttype");
	let phoneNumber = document.querySelector("#rphonenumber");
	let dateOfBirth = document.querySelector("#rdateofbirth");
	let aadharNumber = document.querySelector("#raadharnumber");
	let password = document.querySelector("#rpassword");
	if(password.value.indexOf("%")!=-1){
		swal("Please enter a Password in valid format");
		return;
	}
	var obj = new XMLHttpRequest();
	obj.onreadystatechange = function() {
		if (obj.readyState == 4) {
			var result = obj.responseText;
			console.log(result);
			if (result == -1) {
				swal("Please enter a UserName in valid format");
			} else if (result == -2) {
				swal("Please enter a Email-Id in valid format");
			} else if (result == -3) {
				swal("Please enter a Phone Number in valid format");
			} else if (result == -4) {
				swal("Please enter a Aadhar Number in valid format");
			} else if (result == -5) {
				swal("Please enter a Password in valid format");
			} else if (result == 3) {
				swal("The given Email-Id / Aadhar Number already Exist!!!");
			} else {
				swal("Your Registration completed successfully!!!").then(function() {
								window.location = "Home.jsp";
				});
			}
		}
	};
	obj.open("POST", "http://localhost:8080/BankingApplication/Register");
	obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	var data = "name=" + name.value.trim() + "&emailId=" + emailId.value
				+ "&accountType=" + accountType.value + "&phoneNumber="
				+ phoneNumber.value + "&dateOfBirth=" + dateOfBirth.value
				+ "&aadharNumber=" + aadharNumber.value + "&password="
				+ password.value.trim();
	obj.send(data);
}

function getLoginOTP(){
	let emailId = document.querySelector("#pemailid");
	var obj = new XMLHttpRequest();
	obj.onreadystatechange = function() {
		if (obj.readyState == 4) {
			var result = obj.responseText;
			console.log(result);
			if (result==-2) {
				swal("Please enter a Email-Id in valid format");
			} else if (result==1) {
				swal("The otp is send to your Email-Id");
			} else if(result==-1){
				swal("Please enter a valid Email-Id");
			}
		}
	};
	obj.open("POST", "http://localhost:8080/BankingApplication/GetLoginOTP");
	obj.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	var data = "emailId=" + emailId.value;
	obj.send(data);
}

function resetLoginPassword(){
	let emailId = document.querySelector("#pemailid");
	let otp = document.querySelector("#potp");
	let password = document.querySelector("#ppassword");
	var obj = new XMLHttpRequest();
	obj.onreadystatechange = function() {
		if (obj.readyState == 4) {
			var result = obj.responseText;
			if (result == -5) {
				swal("Please enter a Password in valid format");
			} else if (result == 1) {
				swal("The password changed successfully!!!").then(function(){
					window.location="Login.jsp";
				});
			} else if(result==-1){
				swal("Please enter a valid Email-Id");
			}
		}
	};
	obj.open("POST", "http://localhost:8080/BankingApplication/ResetLoginPassword");
	obj.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	var data = "emailId="+emailId.value+"&otp="+otp.value+"&password="+password.value;
	obj.send(data);
}