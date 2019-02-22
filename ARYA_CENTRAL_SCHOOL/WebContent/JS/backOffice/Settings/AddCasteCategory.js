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
	
	var url=window.location.href.substring(window.location.href.lastIndexOf('?') + 1);

	getReligionDetails();
	
	var hiddenreligionId=$('#hiddenreligionId').val();
	var hiddencasteId=$('#hiddencasteId').val();
	if(hiddenreligionId != "" || hiddenreligionId == null){
		$("#religion option[value='"+ hiddenreligionId +"']").attr('selected', 'true');
		
		getCasteDetails();
		$("#casteId option[value='"+ hiddencasteId +"']").attr('selected', 'true');
	}
	
	
	$('#religion').change(function() {
		getCasteDetails();
	});
	
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);
	

	$("#saveCasteCategoryId").click(function(){

		$(".successmessagediv").hide();
		$(".errormessagediv").hide();

		var religionId = $("#religion").val();
		var casteId = $("#casteId").val();
		var casteCategoryId=$("#casteCategoryNameId").val();
		var castecateVal=$("#castecategoryId").val();
		var hiddencastecategory=$("#hiddencastecategory").val().trim();
		
		if(religionId == "" || religionId == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Select The Religion");
			$("#religionNameId").focus();
			document.getElementById("religion").style.border = "1px solid #FF0000";
			document.getElementById("religion").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}else if (casteId == "" || casteId==null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select The Caste");
			$("#religionNameId").focus();
			document.getElementById("casteId").style.border = "1px solid #FF0000";
			document.getElementById("casteId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}else if (casteCategoryId == "" || casteCategoryId==null) {

			$(".errormessagediv").show();

			$(".validateTips").text("Enter Caste Category");
			$("#religionNameId").focus();
			document.getElementById("casteCategoryId").style.border = "1px solid #FF0000";
			document.getElementById("casteCategoryId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}else if(!casteCategoryId.match(/^[a-zA-Z0-9]*$/)) {

			$(".errormessagediv").show();
			$(".validateTips").text("Special Characters are not allowed in Cast Category!");
			$("#casteCategoryId").focus();
			document.getElementById("casteCategoryId").style.border = "1px solid #AF2C2C";
			document.getElementById("casteCategoryId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false; 
		}
		else{

			datalist = {
					"casteId" : casteId, "religionId":religionId,"casteCategoryId" : casteCategoryId,"castecateVal":castecateVal,"hiddencastecategory":hiddencastecategory
			};

			$.ajax({
				type : 'POST',
				url : "religionCastCasteCategory.html?method=insertCasteCategory",
				data : datalist,
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					//alert("response"+result.jsonResponse);

					if(result.jsonResponse=="Caste Category Added Successfully"){

						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Add Caste Category progressing...");
						setTimeout(function(){

							window.location.href = "adminMenu.html?method=casteCategoryDetails";

						},3000);

					}

					if(result.jsonResponse=="Caste Category Updated Successfully"){

						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Update Caste Category progressing...");
						setTimeout(function(){

							window.location.href = "adminMenu.html?method=casteCategoryDetails";

						},3000);
					}	
					if(result.jsonResponse=="Caste Category Already Exist"){

						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("Caste Category Already exists in this Religion & Caste");

						$("#religionNameId").focus();
						document.getElementById("religionNameId").style.border = "1px solid #AF2C2C";
						document.getElementById("religionNameId").style.backgroundColor = "#FFF7F7";
						
						setTimeout(function(){
							
							window.location.href = "adminMenu.html?method=casteCategoryDetails";
					 
					 },3000);
					}
			
				 
				}
			});
		}
	});

});

/*function getCasteDetails(){
	var religionId=$('#religion').val();

	var data = {
			"religionId" : religionId
	};

	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=getCasteForDropDown",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#casteId').empty();
			$('#casteId').append(
					'<option value="' + "" + '">' + "---------Select----------"	+ '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#casteId').append(
						'<option value="'
						+ result.jsonResponse[j].casteId
						+ '">'
						+ result.jsonResponse[j].caste
						+ '</option>');
			}


		}
	});
}*/


function getReligionDetails(){
	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=getReligionForDropDown",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#religion').empty();
			$('#religion').append('<option value="' + "" + '">'	+ "----------Select----------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#religion').append(
						'<option value="'
						+ result.jsonResponse[j].religionId
						+ '">'
						+ result.jsonResponse[j].religion
						+ '</option>');
			}


		}
	});
}

function getCasteDetails(){
	var religionId = null;
	if (($('#hiddenreligionId').val() != "" && $('#hiddenreligionId').val() != null && $("#religion").val() =="" )) {
		religionId = $("#hiddenreligionId").val();
	}else if($('#hiddenreligionId').val() == $("#religion").val()){
		religionId = $("#hiddenreligionId").val();
	}else {
		religionId = $('#religion').val();
	}
	
	var data = {
			"religionId" : religionId
	};

	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=getCasteForDropDown",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#casteId').empty();
			$('#casteId').append('<option value="' + "" + '">'+ "----------Select----------"+ '</option>');
			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#casteId').append(
						'<option value="'
						+ result.jsonResponse[j].casteId
						+ '">'
						+ result.jsonResponse[j].caste
						+ '</option>');
			}
			$('#casteCategoryId').empty();
			$('#casteCategoryId').append('<option value="' + "" + '">'+ "----------Select----------"+ '</option>');
		}
	});
}

function HideError() 
{
	
/*document.getElementById("religionNameId").style.border = "1px solid #ccc";
document.getElementById("religionNameId").style.backgroundColor = "#fff";*/

} 