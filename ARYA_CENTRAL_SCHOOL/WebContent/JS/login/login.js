function noBack() { window.history.forward(); }
$(document).ready(function(){
	$('input#user_name,input#user_password').bind('cut copy paste', function (e) {
	    e.preventDefault(); //disable cut,copy,paste
	});
	var pag=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	
	if(pag=="login.html?method=login"){
	window.history.forward();
	 
	}
	$("#username").text($("#username").text().substring(0,20)+"..");
	var windowheight=($(window).height()-120)+'px';
	$("#main").css({
		'height':windowheight
		
	});
	$("#overlay").css({
		'height':windowheight
		
	});
});
function validateFields() {
	
	
	var UserName = $("#user_name").val();
	var password = $("#user_password").val();


	if (UserName == '') {

		$(".loginTips").show();
		$(".loginTips").html("Enter Username ");
		
		return false;
	}else
	if (password == '') {
		
		$(".loginTips").show();
		$(".loginTips").html("Enter Password");
		
		return false;
		
	} else if(checkuser(UserName,password)){
		
		$(".loginTips").show();
		$(".loginTips").html("Enter valid username and password ");
		
		return false;
		
	}
	
	else if (checkLoginAuthenticationCase()) {
		
				$(".loginTips").show();
				$(".loginTips").html("Incorrect User Name or Password");

				return false;

			} 
	
	else {
		
		$(".loginTips").hide();
		return true;
	
	}
	
	
}

function  checkuser(username,password){
	
	
	var flag=false;
	
	var UserDetails={
			
			"UserName" : username,
			"password" : password
		};
		
		$.ajax({
			type : "POST",
			url : "login.html?method=checkValidateuser",
			data : UserDetails,
			async : false,
			success : function(data) {
				
				var response = $.parseJSON(data);
				
				if(response.Status==null || response.Status.trim()==""){
					
					$(".loginTips").show();
					$(".loginTips").html("Enter valid username and password ");
				
					flag=true;
					
				}
				
				}
			
		});
		return flag;
}

function gotoRespectiveLogin() {
	
	$(window.location).attr('href', 'login.html?method=login');

	/*var login_type = $("#usertypehidden").val().trim();

	if (login_type == 'Admin') {
		$(window.location).attr('href', 'login.html?method=login');
	}
	if (login_type == 'Teacher') {
		$(window.location).attr('href', 'login.html?method=teacherLogin');
	}
	if (login_type == 'Parent') {
		$(window.location).attr('href', 'login.html?method=parentLogin');
	}
	if (login_type == 'Principle') {
		$(window.location).attr('href', 'login.html?method=principleLogin');
	}*/
}




function removeMessage() {

	location.reload();
}

function opendialog(type) {
	if (type == 'admin') {
		$("#myModal").css('display', 'block');
		$("#myModal").attr('class', 'modal fade in');
		$("#myModal").attr('aria-hidden', 'false');
		$(".parentDisable").css('display', 'block');
	}
}

function closeDialog() {
	location.reload();
}

function checkLoginAuthenticationCase() {
	var uname = $("#user_name").val();
	var pword = $("#user_password").val();
	var logindetails = {
		"uname" : uname,
		"pword" : pword
	};

	$.ajax({
		type : "POST",
		url : "login.html?method=userValidCase",
		data : logindetails,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			if (result.status) {

				loginusercase = false;

			} else {
				loginusercase = true;
				$("#error").text("Incorrect User Name or Password");
			}
		}
	});

	return loginusercase;

}