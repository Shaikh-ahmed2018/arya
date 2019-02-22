$(document).ready(function(){

	$(".errormessagediv").hide();
	$("successmessagediv").hide();

	$('#saveid').click(function() {

		var locname = $("#locationname").val().trim();
		var descname = $("#descriptionid").val().trim();

		var location_code = $("#updatelocationid").val().trim();


		if (locname == "" || locname == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Location Name");
			document.getElementById("locationname").style.border = "1px solid #AF2C2C";
			document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;

		} 
		/*else if(!locname.match(/^[a-zA-Z-,]+(\s{0,1}[a-zA-Z-, ])*$/)) {

			$('.errormessagediv').show();
			$('.validateTips').text("Location Name should be characters");
			document.getElementById("locationname").style.border = "1px solid #AF2C2C";
			document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;

		}*/
		else if(locname.length <3){
			$('.errormessagediv').show();
			$('.validateTips').text("Location Name should Contain Atleast 3 Characters");
			document.getElementById("locationname").style.border = "1px solid #AF2C2C";
			document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(locname.length >50 ){
			$('.errormessagediv').show();
			$('.validateTips').text("Location Name Too Long!!!");
			document.getElementById("locationname").style.border = "1px solid #AF2C2C";
			document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}
		else if (validateDeptName() == 1) {

			$('.errormessagediv').show();
			$('.validateTips').text("Location Name already Exist");
			return false;
		}

		else if (validateLocNameUpdate() == 1) {

			$('.errormessagediv').show();
			$('.validateTips').text("Location Name already Exist");
			return false;
		}

		else {

			datalist = {
					"locationname" : locname,
					"description" : descname,
					"update_loc" : location_code
			},

			$.ajax({

				type : "POST",

				url : "locationDetails.html?method=insertLocation",

				data : datalist,

				async : false,

				success : function(data) {

					var result = $.parseJSON(data);

					$('.errormessagediv').hide();
					$(".successmessagediv").show();

					$(".successmessage").text(result.status);

					setTimeout(function() {

						window.location.href="adminMenu.html?method=locationList";
					}, 2000);
				}

			});
			return false;

		}

	});

});

function validateDeptName() {
	var completeurl=window.location.href;
	var splitedurl=(((completeurl.split("="))[1]).split("&"))[0];
	var hiddenlocName =$("#updatelocationname").val().trim();
	
		
			var locname = $('#locationname').val().trim();
			var locid = $('#updatelocationid').val().trim();
			if(hiddenlocName != locname){
			var deptname_object = {
				"locname" : locname,
				"locid" : locid
			};

			$.ajax({

				type : "GET",
				url : "locationDetails.html?method=validatelocationname",
				data : deptname_object,
				async : false,

				success : function(data) {

					var result = $.parseJSON(data);

					if (result) {
						$(".errormessagediv1").show();
						$(".validateTips1").text("Location Already Exist");
						desname_validate = 1;

					} else {
						desname_validate = 0;
					}

				}

			});}else{
				
				desname_validate = 0;
			}

		


	return desname_validate;

}

function validateLocNameUpdate() {
	
	var completeurl=window.location.href;
	var splitedurl=(((completeurl.split("="))[1]).split("&"))[0];
	
	 if(splitedurl=="editLocation"){
		 DeptName_validate_update = 0;
	 }else{

			var DeptName_validate_update = 0;
			var locname = $('#locationname').val();
			var locid = $('#updatelocationid').val();
			var deptObject = {
				"locname" : locname,
				"locid" : locid
			};

			$.ajax({

				type : "GET",
				url : "locationDetails.html?method=validateLocNameUpdate",
				data : deptObject,
				async : false,

				success : function(data) {

					var result = $.parseJSON(data);

					if (result) {
						$('.errormessagediv').show();
						$('.validateTips').text("Location Name Already Exists");
						DeptName_validate_update = 1;

					} else {
						DeptName_validate_update = 0;
					}

				}

			});
	 }
	 

	return DeptName_validate_update;

}
function HideError() 
{
	
document.getElementById("locationname").style.border = "1px solid #ccc";
document.getElementById("locationname").style.backgroundColor = "#fff";

}