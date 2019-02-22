

$(document).ready(function(){
	$("#successMessage").show();
	setTimeout(function() {
		$("#successMessage").hide();
	}, 10000);
	
});
	  
	
	function validatePhoneNo(){
		var phoneId=$('#phoneNO').val();
phoneNo={
		"phoneId":phoneId
},

		$.ajax({
			url: "feedback.html?method=validatePhoneNo",
			data: phoneNo,
			success: function(data){
			
		      var response=$.parseJSON(data);
		      $('.validateTipsforError').text(response.message);
			}
			
		});
	}
	function gaurdianvalidateEmail(){
var emailid=$('#emailId').val();

emailCheck={
		"emailid":emailid
},

		$.ajax({
			url: "feedback.html?method=validateEmail",
			data: emailCheck,
			success: function(data){
			
		      var response=$.parseJSON(data);
		      $('.validateTipsforError').text(response.message);
			}
			
		});
	}
	
	
	function gaurdianvalidateEmail(){
		var emailid=$('#emailId').val();

		emailCheck={
				"emailid":emailid
		},

				$.ajax({
					url: "feedback.html?method=validateEmail",
					data: emailCheck,
					success: function(data){
					
				      var response=$.parseJSON(data);
				      $('.validateTipsforError').text(response.message);
					}
					
				});
			}
function validateFeedBack(){
	

var name = $("#nameId").val();

var phone = $("#phoneNO").val();
	var contactEmail = $("#emailId").val();
	
	var address = $("#address").val();
	  


	var regexpforInteger = /^([0-9])+$/;
	var regexpforalphabets = /^[a-zA-Z ]+$/i;
	var regexpforEmailId = /[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]+$/i;
	

	if (name=="") {
		
		$(".validateTipsforError").text("Name should not be Empty");

		return false;

	} else if (!(regexpforalphabets.test(name))) {

		$(".validateTipsforError").text(
				"Name field only allow Alphabet : a-z");

		return false;

	} else if (name.length > 80 || name.length < 4) {

		$(".validateTipsforError").text(
				"Length of Name must be in between 4 to 80");

		return false;

	} else if (phone.trim() == "") {
		$(".validateTipsforError").text(
				"PhoneNumber should not be Empty");

		return false;
	}

	 else if (!(regexpforInteger.test(phone.trim()))) {

		$(".validateTipsforError").text(
				"PhoneNumber  field only allow Integer : 0-9");

		return false;
	} else if (contactEmail == "") {
		$(".validateTipsforError").text(
				"Email ID field should not be Empty");
		return false;
	} else if (!(regexpforEmailId.test(contactEmail))) {
		$(".validateTipsforError").text("Enter ValidEmail Id");
		return false;
	}

	else if (address == "") {

		$(".validateTipsforError")
				.text("Address field should not be Empty");

		return false;

	} else if (address.length > 80 || address.length < 5) {

		$(".validateTipsforError").text(
				"Length of address must be in between 5 to 80");

		return false;

	}
	else if (address.length > 80 || address.length < 5) {

		$(".validateTipsforError").text(
				"Length of address must be in between 5 to 80");

		return false;

	}
	
	
	
	
	
	}