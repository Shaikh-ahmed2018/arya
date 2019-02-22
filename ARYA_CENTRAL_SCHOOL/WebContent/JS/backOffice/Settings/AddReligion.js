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

	/*					$('.errormessagediv').hide();
	 */					
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);



	$("#saveReligionId").click(function(){

		$(".successmessagediv").hide();
		$(".errormessagediv").hide();


		var religion = $("#religionNameId").val().trim();
		var religionId = $("#relgnId").val();
		var hiddenreligion = $("#hiddenreligion").val();

		if (religion == "" || religion==null) {

			$(".errormessagediv").show();

			$(".validateTips").text("Enter Religion Name");
			$("#religionNameId").focus();
			document.getElementById("religionNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);

			return false;
		}else if(!religion.match(/^[a-zA-Z]*$/)) {

			$(".errormessagediv").show();
			$(".validateTips").text("Religion name should be characters!");
			$("#religionNameId").focus();
			document.getElementById("religionNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false; 
		}


		else{
			datalist = {
					"religion" : religion, "religionId":religionId, "hiddenreligion":hiddenreligion
			};
			$.ajax({
				type : 'POST',
				url : "religionCastCasteCategory.html?method=insertReligion",
				data : datalist,
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);

					if(result.jsonResponse=="Religion Added Successfully"){

						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Add Religion Progressing...");


						setTimeout(function(){

							window.location.href = "adminMenu.html?method=religionDetails";

						},3000);

					}

					if(result.jsonResponse=="Religion Updated Successfully"){

						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Update Religion Progressing...");



						setTimeout(function(){

							window.location.href = "adminMenu.html?method=religionDetails";

						},3000);

					}	

					if(result.jsonResponse=="Religion Already Exist"){

						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("Religion Already Exist");

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

}