function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function callAjax(urlWithMethod, dataToBeSend) {
	
	$.ajax({
         
		url : urlWithMethod,
		data : dataToBeSend,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			jsonResult = result.status;
			
			 setTimeout(function(){
				   
				 location.reload();
			 
			 },5000);
	
		}
	});
	return jsonResult;
}

function insertValidate() {

	
	
	$(".errormessagediv").hide();
	
	tips = $(".validateTips");
	var bValid = true;

	bValid = bValid && checkLengthText($('#streamName'), "Stream Name", 0, 30);

	bValid = bValid
			&& checkRegexpText($('#streamName'), /^[a-zA-Z\s]+$/g,
					"Name should be Alphabet");

	if (!bValid) {
		$(".errormessagediv").show();
	} else {

		$(".errormessagediv").hide();
	}

	return bValid;
		
}

$(document).ready(function() {

	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);

	var hmain_religion = $("#hmain_religion").val().trim();

	if(hmain_religion!=null)
	{

		$("#main_religion option[value='"+ $("#hmain_religion").val().trim()+"']").attr('selected', 'true');


	}



	$("#saveReligionId").click(function(){

		$(".successmessagediv").hide();
		$(".errormessagediv").hide();


		var religion = $("#religionNameId").val().trim();
		var religionId = $("#relgnId").val();
		var main_religion =$("#main_religion").val();
		var hiddencaste =$("#hiddencaste").val().trim();

		if (main_religion == "" || main_religion==null) {

			$(".errormessagediv").show();

			$(".validateTips").text("Select Religion");
			$("#religionNameId").focus();
			document.getElementById("main_religion").style.border = "1px solid #FF0000";
			document.getElementById("main_religion").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}

		else if (religion == "" || religion==null) {

			$(".errormessagediv").show();

			$(".validateTips").text("Enter Caste");
			$("#religionNameId").focus();
			document.getElementById("religionNameId").style.border = "1px solid #FF0000";
			document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}else if(!religion.match(/^[a-zA-Z ]*$/)) {

			$(".errormessagediv").show();
			$(".validateTips").text("Caste name should be characters!");
			$("#religionNameId").focus();
			document.getElementById("religionNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false; 
		}else{

			datalist = {
					"religion" : religion,
					"religionId":religionId,
					"main_religion" : main_religion,
					"hiddencaste":hiddencaste
			};
			$.ajax({
				type : 'POST',
				url : "religionCastCasteCategory.html?method=insertCaste",
				data : datalist,
				async : false,
				success : function(data) {

					var result = $.parseJSON(data);

					if(result.jsonResponse=="Caste Added Successfully"){
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Add Caste Details progressing...");

						setTimeout(function(){

							window.location.href = "adminMenu.html?method=casteDetails";

						},3000);

					}

					if(result.jsonResponse=="Caste Updated Successfully"){

						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Update Caste Details progressing...");

						setTimeout(function(){

							window.location.href = "adminMenu.html?method=casteDetails";

						},3000);

					}	
					if(result.jsonResponse=="Caste Already Exist"){

						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("Caste Already Exist");

						$("#religionNameId").focus();
						document.getElementById("religionNameId").style.border = "1px solid #AF2C2C";
						document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
					}		
				}
			});
		}
	});
});




function HideError() 
{
	
document.getElementById("religionNameId").style.border = "1px solid #ccc";
document.getElementById("religionNameId").style.backgroundColor = "#fff";
document.getElementById("main_religion").style.border = "1px solid #ccc";
document.getElementById("main_religion").style.backgroundColor = "#fff";



}