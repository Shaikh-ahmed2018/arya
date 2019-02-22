$(document).ready(function(){
	
	
$("#save").click(function(){
		
	var selectUser = $("#hUser").val().trim();
	var newpasswrd = 	$("#newpassword").val().trim();
	var confirmpasswrd =	$("#confirmpassword").val().trim();
	
	
	if (newpasswrd.trim() == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Enter New Password");
		document.getElementById("newpassword").style.border = "1px solid #AF2C2C";
		document.getElementById("newpassword").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		
		return false;
	 }else if(confirmpasswrd.trim() == ""){
			
		   $(".errormessagediv").show();
			$(".validateTips").text("Enter Confirm Password");
			document.getElementById("confirmpassword").style.border = "1px solid #AF2C2C";
			document.getElementById("confirmpassword").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false; 
		 
	 }else if( !(newpasswrd.trim() == confirmpasswrd.trim()) ){
		 
		   $(".errormessagediv").show();
			$(".validateTips").text("Failed! Passwords are Not Matching");
			document.getElementById("newpassword").style.border = "1px solid #AF2C2C";
			document.getElementById("newpassword").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			document.getElementById("confirmpassword").style.border = "1px solid #AF2C2C";
			document.getElementById("confirmpassword").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false; 
		 
	 }else{
		 
		  $(".errormessagediv").hide();
		 $
			.ajax({
				type : "POST",
				url : "userManagement.html?method=changePassword",
				data :{"selectUser":selectUser,"confirmpasswrd":confirmpasswrd},		
			 	success : function(response) {
			 		
			 	var result = $.parseJSON(response);
			 	
		         if(result.status=="true"){	 	
		        	 
					 $(".successmessagediv").show();
				 $(".successmessagediv").attr("style","width:150%;margin-right:-270px;");
					 $(".successmessage").text("Users Change Password Progressing...");
				/*	 $("#newpassword").val("");
				   	$("#confirmpassword").val("");*/
			 	 }else{
			 		 
			 		  $(".errormessagediv").show();
						$(".validateTips").text("Failed! Password not changed, Please try again");
						 $("#newpassword").val("");
						   	$("#confirmpassword").val("");
			 	 }
			 	
			 		
		         setTimeout(function(){
					   
					 window.location.href="adminMenu.html?method=getUserRecords";
					
				 
				 },3000);
		         
		         
		         
		         
				}
			});
	
		 return false;
	 }
	
	
});
	
	

	$("#listing").click(function(){
		
		window.location.href="adminMenu.html?method=getUserRecords";
	});
	
	
	
});



function HideError() 
{
	
document.getElementById("newpassword").style.border = "1px solid #ccc";
document.getElementById("newpassword").style.backgroundColor = "#fff";

document.getElementById("confirmpassword").style.border = "1px solid #ccc";
document.getElementById("confirmpassword").style.backgroundColor = "#fff";


}
