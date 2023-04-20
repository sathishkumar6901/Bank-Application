function profilePage(){
	ShowHideForm("notification","Hide");
	ShowHideForm("profile","Show");
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
	ShowHideForm("notification","Hide");
	ShowHideForm("profile","Hide");
}

function getNotifications(custId){
	console.log(custId);
	let obj=new XMLHttpRequest();
	obj.onreadystatechange = function() {
		if (obj.readyState == 4) {
			let result = JSON.parse(obj.responseText);
			
			ShowHideForm("profile","Hide");
			if(result.length==0){
				swal("You didn't receive any notification from bank");
				return;
			}
			ShowHideForm("notification","Show");
			for(var i=0;i<result.length;i++){
				var eachObject = result[i];
				var templateTag = document.getElementsByTagName("template")[0];
				var templateContent = templateTag.content;
				var copiedDiv = templateContent.cloneNode(true);
				
				copiedDiv.querySelector("#requestId").innerText = eachObject.RequestId;
				copiedDiv.querySelector("#requestType").innerText = eachObject.RequestType;
				copiedDiv.querySelector("#requestedDate").innerText = eachObject.RequestedDate;
				copiedDiv.querySelector("#requestResult").innerText = eachObject.RequestResult;
				copiedDiv.querySelector("#repliedDate").innerText = eachObject.RepliedDate;
				copiedDiv.querySelector('.btn1').setAttribute('onclick',"setMessageAsViewed("+eachObject.RequestId+")");
				
				document.querySelector('#tableBody').append(copiedDiv);
			}
		}
	};
	obj.open("POST","http://localhost:8080/BankingApplication/GetNotifications");
	obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	var data = "CustId="+custId;
	obj.send(data); 
}


function setMessageAsViewed(requestId){
	var obj=new XMLHttpRequest();
	obj.onreadystatechange=function(){
		if(obj.readyState==4){
			window.location="CustomerMenu.jsp";
		}
	};
	obj.open("POST","http://localhost:8080/BankingApplication/SetMessageViewed");
	obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	var data = "requestId="+requestId;
	obj.send(data); 
}
