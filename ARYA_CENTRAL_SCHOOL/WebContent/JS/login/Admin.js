function noBack() { window.history.forward(); }
$(document).ready(function() {
	getAcademicYear();
	 getSchool();
	var hPriveliges = $("#hPriveliges").val();
	
	if(hPriveliges=="Y") 
		{
		$("#globalAcademicYear option[value="+$("#hacademicyaer").val()+"]").attr("selected",true);
		$("#globalAcademicYear").attr("disabled",true);
		$("#school").val($("#hschoolLocation").val());
		$("#school").attr("disabled",true);
		}
	else
		{
		
		$("#globalAcademicYear option[value="+$("#hacademicyaer").val()+"]").attr("selected",true);
		$("#globalAcademicYear").attr("disabled",true);
		$("#school").val($("#hschoolLocation").val());
		$("#school").attr("disabled",true);
		}
	$("body").not("#dropdown").click(function(){
		$("#hbox").hide();
	});
	
	var reg=window.location.href.substr(window.location.href.lastIndexOf('&')+1);
	var splitr=reg.split('=');
	var checker=splitr[0];
	if(checker=='re_enter_password'){
		$(".successmessagediv").show();
		$(".successmessagediv").children("span").text("You Are register. Now You can Login");
		
		$(".successmessagediv").delay(3000).fadeOut("slow");
		setTimeout(function() {
		window.location.href="http://localhost:9090/ARYA_CENTRAL_SCHOOL/";
		}, 3000);
		
	}
	$(".successmessagediv").hide();
	$(".loginTips").hide();
	
	
	var UserName = $('#usernamehidden').val();
	if (UserName != 'null') {
		$('#adminname').text(UserName);
	} else {
		window.location.href = "index.jsp";
	}

	$('.ui-x .ui-collapse .ui-close').click(function(e) {
		e.preventDefault();
		$(this).parents(".ui-collapse").css("display", "none");
	});

	$('button.close').click(function(e) {
		$("#myModal").css('display', 'none');
		$("#myModal").attr('class', 'modal fade');
		$("#myModal").attr('aria-hidden', 'true');
		$(".parentDisable").css('display', 'none');
	}); 
	if($("#hschoolLocation").val() !="all"){
		$("#locationname").find("option").not("option[value="+$("#hschoolLocation").val()+"],option[value='']").remove();
		$(".locationname").find("option").not("option[value="+$("#hschoolLocation").val()+"],option[value='']").remove();
	}
	if(hPriveliges=="N") 
	{
		$("#Acyearid").find("option").not("option[value="+$("#hacademicyaer").val()+"],option[value='']").remove();
	}

	pagination(100);
	$("#show_per_page").change(function(){
		pagination($(this).val());
	});
	
	
	$("#back").click(function(){
		window.history.back();
	});
	
	numberOfItem=$('#allstudent > tbody tr,.allstudent > tbody tr').length;
	
	$(".numberOfItem").append("   No. of Records "+numberOfItem+".");
	
});

function gotoLogout() {
	$(window.location).attr('href', 'login.html?method=logout');
}

function changePassword() {

	var oldPassword = $("#oldpassword").val();
	var newPassword = $("#newPassword").val();
	var confirmPassword = $("#confirmPassword").val();

	if (oldPassword.trim() == "") {

		$(".errormessagediv1").show();
		$(".validateTips").text("Enter Old Password");

	} else if (newPassword.trim() == "") {

		$(".errormessagediv1").show();
		$(".validateTips").text("Enter New Password");

	} else if (confirmPassword.trim() == "") {

		$(".errormessagediv1").show();
		$(".validateTips").text("Enter Confirm Password");

	} else if (newPassword.trim() != confirmPassword.trim()) {
		$(".errormessagediv1").show();
		$(".validateTips").text(
				"New password and Confirmed password should be same");

	} else if (checkCurrentPassword() == "false") {

		$(".errormessagediv1").show();
		$(".validateTips").text("Enter Correct Old Password");

	} else {

		$(".errormessagediv1").hide();

		var passwordDetails = {
			"oldPassword" : oldPassword,
			"newPassword" : newPassword,
			"confirmPassword" : confirmPassword
		};
		$.ajax({
			type : 'POST',
			url : 'login.html?method=changePassword',
			data : passwordDetails,
			async : false,
			success : function(response) {

				var data = $.parseJSON(response);

				if (data.status == "true") {

					$(".successmessagediv").show();
					$("#sucessmessage").text("PassWord Changed Successfully");

					setTimeout("removeMessage()", 3000);

				}
			}
		});
	}
}
function checkCurrentPassword() {

	var status1 = null;

	var currentPassword = {
		"oldPassword" : $("#oldpassword").val()
	};
	$.ajax({
		type : 'POST',
		url : 'login.html?method=checkCurrentPassword',
		data : currentPassword,
		async : false,
		success : function(response) {

			var data = $.parseJSON(response);

			status1 = data.status;

		}
	});

	return status1;
}

//fuctions to get values
function getAcademicYear() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAcademicYear",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#globalAcademicYear").html("");
			$("#globalAcademicYear").append(
					'<option value="' + "" + '">' + "" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$("#globalAcademicYear").append(
						'<option value="'
								+ result.jsonResponse[j].academicYearId + '">'
								+ result.jsonResponse[j].academicYear
								+ '</option>');
			}

		}
	});

}
function getSchool() {
	$.ajax({
		type : 'POST',
		url : "adminMenu.html?method=getSchool",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#school").html("");
			$("#school").append(
					'<option value="all">All' + "" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$("#school").append(
						'<option value="'
								+ result.jsonResponse[j].location_id + '">'
								+ result.jsonResponse[j].locationname
								+ '</option>');
			}

		}
	});

}

function pagination(list) {

	
	
	
	var show_per_page = list;
    var number_of_items = $('#allstudent > tbody > tr,.allstudent > tbody > tr').length;
   
    var number_of_pages = Math.ceil(number_of_items / show_per_page);
    
    $('.pagination').empty();
    $('.pagination').append('<div class=controls></div><input id=current_page type=hidden><input id=show_per_page type=hidden>');
    $('#current_page').val(0);
    $('#show_per_page').val(show_per_page);

    var navigation_html = '<a class="prev" onclick="previous()">Prev</a>';
    var current_link = 0;
    while (number_of_pages > current_link) {
        navigation_html += '<a class="page" onclick="go_to_page(' + current_link + ')" longdesc="' + current_link + '">' + (current_link + 1) + '</a>';
    	 current_link++;
    }
    navigation_html += '&nbsp;&nbsp;&nbsp;<a class="next" onclick="next()">Next</a>';

    $('.controls').html(navigation_html);
    $('.controls .page:first').addClass('active');
    $(".controls").find(".page").hide();
    $(".controls").find(".active").show();
    $(".controls").find(".active").prev().prev().show();	
    $(".controls").find(".active").prev().show();	
    $(".controls").find(".active").next().show();	
    $(".controls").find(".active").next().next().show();
   
    $('#allstudent > tbody > tr,.allstudent > tbody > tr').css('display', 'none');
    $('#allstudent > tbody > tr,.allstudent > tbody > tr').slice(0, show_per_page).css('display', 'table-row');

	
	

}

function go_to_page(page_num) {
    var show_per_page = parseInt($('#show_per_page').val(), 0);

    start_from = page_num * show_per_page;

    end_on = start_from + show_per_page;
    $(".controls").find(".page").hide();
    $(".controls").find(".active").show();
    $(".controls").find(".active").prev().prev().show();	
    $(".controls").find(".active").prev().show();	
    $(".controls").find(".active").next().show();	
    $(".controls").find(".active").next().next().show();	
    $('#allstudent tbody tr,.allstudent tbody tr').css('display', 'none').slice(start_from, end_on).css('display', 'table-row');

    $('.page[longdesc=' + page_num + ']').addClass('active').siblings('.active').removeClass('active');

    $('#current_page').val(page_num);
}



function previous() {

    new_page = parseInt($('#current_page').val(), 0) - 1;
    //if there is an item before the current active link run the function
    if ($('.active').prev('.page').length == true) {
        go_to_page(new_page);
    }

}

function next() {
    new_page = parseInt($('#current_page').val(), 0) + 1;
    //if there is an item after the current active link run the function
    if ($('.active').next('.page').length == true) {
        go_to_page(new_page);
    }
}
