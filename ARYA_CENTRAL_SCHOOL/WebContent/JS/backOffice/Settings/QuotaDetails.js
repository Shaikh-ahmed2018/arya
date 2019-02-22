$(document).ready(function()

{
	$(".errormessagediv").hide();
	$("successmessagediv").hide();
	
		
	
	
	$('#saveid').click(function() {

		var quotaname = $("#quotaid").val().trim();
		var quotadesc = $("#descriptionid").val().trim();
		var quota_code = $("#updatedepartmentid").val().trim();

		if (quotaname == "" || quotaname == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Quota Name");
			
			
			document.getElementById("quotaid").style.border = "1px solid #AF2C2C";
			document.getElementById("quotaid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			
			return false;

		} else if(!quotaname.match(/[A-Za-z]+$/i)) {
			
			$('.errormessagediv').show();
			$('.validateTips').text("Quota Name should be only Alphabet");
			
			
			
			document.getElementById("quotaid").style.border = "1px solid #AF2C2C";
			document.getElementById("quotaid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
			
		}
				
		else if (validateQuotaName() == 1) {
		  
			$('.errormessagediv').show();
			$('.validateTips').text("Quota Name already Exist");
			return false;
			
		} else if (validateQuotaNameUpdate() == 1) {
			
			$('.errormessagediv').show();
			$('.validateTips').text("Quota Name already Exist");
			return false;
		}

		else {

			datalist = {
				"quotaname" : quotaname,
				"quota_description" : quotadesc,
				"quota_update_id" : quota_code
			},

			$.ajax({

				type : "POST",

				url : "QuotaMenu.html?method=insertQuotaDetails",

				data : datalist,
				
				async : false,

				success : function(data) {

					var result = $.parseJSON(data);

					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".successmessage").text(result.status);

					setTimeout(function() {

						location.reload();

					}, 3000);
				}

			});
			return false;
		}

	});

});

function validateQuotaName() {
	var completeurl=window.location.href;
	var splitedurl=(((completeurl.split("="))[1]).split("&"))[0];
	
	if(splitedurl=="editQuotaDetails"){
		quotaname_available = 0;
	}else{
		var quotaname = $('#quotaid').val();
		var quotaid = $('#updatedepartmentid').val();

		var quotaname_object = {
			"quotaname" : quotaname,
			"quotaid" : quotaid
		};
		
		$.ajax({
			

			type : "GET",
			url : "QuotaMenu.html?method=validateQuotaName",
			data : quotaname_object,
			async : false,

			success : function(data) {

				var result = $.parseJSON(data);

				if (result) {
					$(".errormessagediv1").show();
					$(".validateTips1").text("Quota Already Exist");
					quotaname_available = 1;

				} else {
					quotaname_available = 0;
				}

			}

		});
	}	


	return quotaname_available;

}

function validateQuotaNameUpdate() {
	
	var completeurl=window.location.href;
	var splitedurl=(((completeurl.split("="))[1]).split("&"))[0];
	 if(splitedurl=="editQuotaDetails"){
		 QuotaName_validate_update = 0;
	  }else{
		  var QuotaName_validate_update = 0;

			var quotaname = $('#quotaid').val();
			var quotaid = $('#updatedepartmentid').val();

			var quotaname_object = {
				"quotaname" : quotaname,
				"quotaid" : quotaid
			};
		  
			$.ajax({

				type : "GET",
				url : "QuotaMenu.html?method=validateQuotaNameUpdate",
				data : quotaname_object,
				async : false,

				success : function(data) {

					var result = $.parseJSON(data);

					if (result) {
						$('.errormessagediv').show();
						$('.validateTips').text("Quota Name Already Exists");
						QuotaName_validate_update = 1;

					} else {
						
						QuotaName_validate_update = 0;
					}

				}

			});
	  }




	return QuotaName_validate_update;

}
function HideError() 
{
	
document.getElementById("quotaid").style.border = "1px solid #ccc";
document.getElementById("quotaid").style.backgroundColor = "#fff";

}