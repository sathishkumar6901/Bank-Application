function noAccount(){
	swal("Your Account is temporarily hold for verification. Please check your mail regularly!!!").then(function() {
		window.location = "../Customer/CustomerMenu.jsp";
	});
}	
function hideOthers(){
	ShowHideForm("deposit","Hide");
	ShowHideForm("withdraw","Hide");
	ShowHideForm("fundTransfer","Hide");
	ShowHideForm("account","Hide");
	ShowHideForm("resetPassword","Hide");
	ShowHideForm("resetPin","Hide");
}

function depositPage(){
	ShowHideForm("deposit","Show");
	ShowHideForm("withdraw","Hide");
	ShowHideForm("fundTransfer","Hide");
	ShowHideForm("account","Hide");
	ShowHideForm("history","Hide");
	ShowHideForm("resetPassword","Hide");
	ShowHideForm("resetPin","Hide");
}
function withdrawPage(){
	ShowHideForm("deposit","Hide");
	ShowHideForm("withdraw","Show");
	ShowHideForm("fundTransfer","Hide");
	ShowHideForm("account","Hide");
	ShowHideForm("history","Hide");
	ShowHideForm("resetPassword","Hide");
	ShowHideForm("resetPin","Hide");
}
function fundTransferPage(){
	ShowHideForm("deposit","Hide");
	ShowHideForm("withdraw","Hide");
	ShowHideForm("fundTransfer","Show");
	ShowHideForm("account","Hide");
	ShowHideForm("history","Hide");
	ShowHideForm("resetPassword","Hide");
	ShowHideForm("resetPin","Hide");
}
function accountPage(){
	ShowHideForm("deposit","Hide");
	ShowHideForm("withdraw","Hide");
	ShowHideForm("fundTransfer","Hide");
	ShowHideForm("account","Show");
	ShowHideForm("history","Hide");
	ShowHideForm("resetPassword","Hide");
	ShowHideForm("resetPin","Hide");
}
function historyPage(){
	ShowHideForm("deposit","Hide");
	ShowHideForm("withdraw","Hide");
	ShowHideForm("fundTransfer","Hide");
	ShowHideForm("account","Hide");
	ShowHideForm("history","Show");
	ShowHideForm("resetPassword","Hide");
	ShowHideForm("resetPin","Hide");
}
function resetPasswordPage(){
	ShowHideForm("deposit","Hide");
	ShowHideForm("withdraw","Hide");
	ShowHideForm("fundTransfer","Hide");
	ShowHideForm("account","Hide");
	ShowHideForm("history","Hide");
	ShowHideForm("resetPassword","Show");
	ShowHideForm("resetPin","Hide");
}
function resetPinPage(){
	ShowHideForm("deposit","Hide");
	ShowHideForm("withdraw","Hide");
	ShowHideForm("fundTransfer","Hide");
	ShowHideForm("account","Hide");
	ShowHideForm("history","Hide");
	ShowHideForm("resetPassword","Hide");
	ShowHideForm("resetPin","Show");
}

function ShowHideForm(FormID,ShowOrHide){
	var Form = document.getElementById(FormID);

	if(ShowOrHide == "Show"){
		Form.style.display = 'block';
	}else{
		Form.style.display = 'none';
	}
}

function colsePopup(){
	ShowHideForm("deposit","Hide");
	ShowHideForm("withdraw","Hide");
	ShowHideForm("fundTransfer","Hide");
	ShowHideForm("account","Hide");
	ShowHideForm("history","Hide");
	ShowHideForm("resetPassword","Hide");
	ShowHideForm("resetPin","Hide");
}

function depositAmount(){
	let accountNumber = document.querySelector("#daccountnumber");
	let ifscCode = document.querySelector("#difsccode");
	let amount = document.querySelector("#damount");
	let obj = new XMLHttpRequest();
	obj.onreadystatechange = function() {
		if (obj.readyState == 4) {
			let result = obj.responseText;
			if(result==-4){
				swal("Please enter a Valid Account Details");
			}
			else if(result==-3){
				swal("Please enter the amount in valid format");
			}
			else if(result==1){
				swal("Your Transaction completed successfully!!!").then(function() {
					window.location = "Account.jsp";
				});	
			}
			else{
				swal("Please enter valid Details");
			}
		}
	};
	obj.open("POST", "http://localhost:8080/BankingApplication/Deposit");
	obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	let data = "accountNumber="+accountNumber.value+"&ifscCode="+ifscCode.value+"&amount="+amount.value;
	obj.send(data);
}

function withdrawAmount(){
	let cardNumber = document.querySelector("#wcardnumber");
	let cvvNumber = document.querySelector("#wcvvnumber");
	let expiryDate = document.querySelector("#wexpirydate");
	let amount = document.querySelector("#wamount");
	let pinNumber = document.querySelector("#wpin");
	let obj = new XMLHttpRequest();
	obj.onreadystatechange = function() {
		if (obj.readyState == 4) {
			let result = obj.responseText;
			if(result==-1){
				swal("Please enter a card details in valid format");
			}
			else if(result==-3){
				swal("Please enter the amount in valid format");
			}
			else if(result==-6){
				swal("Please enter the pin in valid format");
			}
			else if(result==-11){
				swal("Please enter valid card details");
			}
			else if(result==-13){
				swal("Insufficient balance");
			}
			else if(result==1){
				swal("Your Transaction completed successfully!!!").then(function() {
					window.location = "Account.jsp";
				});	
			}
			else{
				swal("Please enter valid Details");
			}
		}
	};
	obj.open("POST", "http://localhost:8080/BankingApplication/Withdraw");
	obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	let data = "cardNumber="+cardNumber.value+"&cvvNumber="+cvvNumber.value+"&expiryDate="+expiryDate.value+"&amount="+amount.value+"&pinNumber="+pinNumber.value;
	obj.send(data);
}

function fundTransafer(){
	let accountNumber = document.querySelector("#ftaccount");
	let ifscCode = document.querySelector("#ftifsccode");
	let amount = document.querySelector("#ftamount");
	let password = document.querySelector("#ftpassword");
	let obj = new XMLHttpRequest();
	obj.onreadystatechange = function() {
		if (obj.readyState == 4) {
			let result = obj.responseText;
			if(result==-4){
				swal("Please enter a valid Account details");
			}
			else if(result==-3){
				swal("Please enter the amount in valid format");
			}
			else if(result==-13){
				swal("Please enter the amount less than your balance");
			}
			else if(result==-5){
				swal("Please enter a valid transaction password");
			}
			else if(result==1){
				swal("Your Transaction completed successfully!!!").then(function() {
					window.location = "Account.jsp";
				});	
			}
			else{
				swal("Please enter valid Details");
			}
		}
	};
	obj.open("POST", "http://localhost:8080/BankingApplication/FundTransfer");
	obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	let data = "accountNumber="+accountNumber.value+"&ifscCode="+ifscCode.value+"&amount="+amount.value+"&password="+password.value;
	obj.send(data);
}

function getTransactions(custId){
	let obj=new XMLHttpRequest();
	obj.onreadystatechange = function() {
		if (obj.readyState == 4) {
			let result = JSON.parse(obj.responseText);
			
			hideOthers();
			if(result.length==0){
				swal("You didn't make any transactions");
				return;
			}
			ShowHideForm("history","Show");
			document.querySelector('#tableBody').innerHTML="";
			for(var i=0;i<result.length;i++){
				var eachObject = result[i];
				var templateTag = document.getElementsByTagName("template")[0];
				var templateContent = templateTag.content;
				var copiedDiv = templateContent.cloneNode(true);
				
				copiedDiv.querySelector("#tid").innerText = eachObject.TransactionId;
				copiedDiv.querySelector("#tdate").innerText = eachObject.TransactionDate;
				copiedDiv.querySelector("#tmethod").innerText = eachObject.TransactionMethod;
				copiedDiv.querySelector("#tamount").innerText = eachObject.TransactionAmount;
				copiedDiv.querySelector("#ttype").innerText = eachObject.TransactionType;
				copiedDiv.querySelector("#tbalance").innerText = eachObject.Balance;
				
				document.querySelector('#tableBody').append(copiedDiv);
			}
		}
	};
	obj.open("POST","http://localhost:8080/BankingApplication/GetTransactions");
	obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	var data = "CustId="+custId;
	obj.send(data); 
}



function getPasswordOTP(){
	let obj=new XMLHttpRequest();
	let accountNumber = document.querySelector("#rpaccount");
	let emailId = document.querySelector("#rpemailid");
	obj.onreadystatechange=function(){
		if(obj.readyState==4){
			var result=obj.responseText;
			if (result==-2) {
				swal("Please enter a Email-Id in valid format");
			} else if(result==-4){
				swal("Please enter a Account number in valid format");
			} else if (result==1) {
				swal("The otp is send to your Email-Id");
			} else if(result==-1){
				swal("Please enter a valid Email-Id");
			}			 
		}
	};
	obj.open("POST","http://localhost:8080/BankingApplication/GetTransactionOTP");
	obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	let data = "accountNumber="+accountNumber.value+"&emailId="+emailId.value;
	obj.send(data);
}
function resetTransactionPassword(){
	let obj=new XMLHttpRequest();
	let accountNumber = document.querySelector("#rpaccount");
	let otp = document.querySelector("#rpotp");
	let password = document.querySelector("#rppassword");
	obj.onreadystatechange=function(){
		if(obj.readyState==4){
			let result = obj.responseText;
			if(result==1){
				swal("Your Transaction password Changed successfully!!!").then(function() {
					window.location = "Account.jsp";
				});
			}
			else if(result==-1){
				swal("please enter a correct otp");
			}
			else if(result==-6)
				swal("please enter a pin in valid format");
			else
			swal("Please enter a valid OTP");
		}
	};
	obj.open("POST","http://localhost:8080/BankingApplication/ResetTransactionPassword");
	obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	let data = "accountNumber="+accountNumber.value+"&otp="+otp.value+"&password="+password.value;
	obj.send(data);
}

function getPinOTP(){
	let obj=new XMLHttpRequest();
	let accountNumber = document.querySelector("#rsaccount");
	let cardNumber = document.querySelector("#rscardnumber");
	let cvvNumber = document.querySelector("#rscvvnumber");
	let expiryDate = document.querySelector("#rsexpirydate")
	let emailId = document.querySelector("#rsemailid");
	obj.onreadystatechange=function(){
		if(obj.readyState==4){
			var result=obj.responseText;
			if(result==-4)
				swal("Please enter a valid Account details");
			else if(result==-1)
				swal("Please enter a valid Card details");
			else if(result==-2)
				swal("Please enter a valid emailid");
			else if (result==1) 
				swal("The otp is send to your Email-Id");			 
		}
	};
	obj.open("POST","http://localhost:8080/BankingApplication/GetPinOTP");
	obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	let data = "accountNumber="+accountNumber.value+"&cardNumber="+cardNumber.value+"&cvvNumber="+cvvNumber.value+"&expiryDate="+expiryDate.value+"&emailId="+emailId.value;
	obj.send(data);
}

function resetCardPin(){
	let obj=new XMLHttpRequest();
	let accountNumber = document.querySelector("#rsaccount");
	let otp = document.querySelector("#rsotp");
	let pinNumber = document.querySelector("#rspin");
	obj.onreadystatechange=function(){
		if(obj.readyState==4){
			let result = obj.responseText;
			if(result==1){
				swal("Your card pin changed successfully!!!").then(function() {
					window.location = "Account.jsp";
				});
			}
			else if(result==-6)
				swal("please enter a pin in valid format");
			else if(result==-1)
				swal("Please enter a valid OTP");
		}
	};
	obj.open("POST","http://localhost:8080/BankingApplication/ResetCardPin");
	obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	let data = "accountNumber="+accountNumber.value+"&otp="+otp.value+"&pinNumber="+pinNumber.value;
	obj.send(data);
}
