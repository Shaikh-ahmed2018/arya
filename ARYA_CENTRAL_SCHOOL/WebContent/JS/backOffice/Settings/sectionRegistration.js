function removeMessage() {

	$(".successmessagediv").hide();
	$(".errormessagediv").hide();
	$(".errormessagediv1").hide();

}
$(document).ready(function() 
		{
	if($("#schoolId").val()!=""){
		$("#locationname").val($("#schoolId").val());
		$("#locationname").find("option").not("option[value*=LOC]").remove();
		getClass($("#schoolId"));
	}
	$("#locationname").change(function(){
		getClass($("#locationname").val());

	});

	var completeurl=window.location.href;
	var splitedurl=(((completeurl.split("="))[1]).split("&"))[0];


	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);

	var hiddenStreamId = $("#hiddenStreamId").val().trim();
	if (hiddenStreamId != undefined) {

		$("#classId option[value=" + hiddenStreamId + "]")
		.attr('selected', 'true');

	}

	$('#classSave').click(
			function() {
				var classId = $('#classId').val();
				var sectionName = $('#sectionName')
				.val().toUpperCase();
				var sectionStrength = $(
				'#sectionStrength').val();
				var status = $('#statusId').val();
				var updateClassCode = $(
				'#updateClassCode').val();
				if($("#locationname").val() == "" || $("#locationname").val() == null){
					$(".errormessagediv").show();
					$(".validateTips").text("Select School Name");
					document.getElementById("locationname").style.border = "1px solid #AF2C2C";
					document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);

					return false;
				}

				else if (classId == "" || classId == null) {
					$(".errormessagediv").show();
					$(".validateTips").text("Select Class");
					document.getElementById("classId").style.border = "1px solid #AF2C2C";
					document.getElementById("classId").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);

					return false;
				} else if (sectionName == ""
					|| sectionName == null) {
					$(".errormessagediv").show();
					$(".validateTips").text(
					"Enter Division Name");

					document.getElementById("sectionName").style.border = "1px solid #AF2C2C";
					document.getElementById("sectionName").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);

					return false;
				} else if (!sectionName
						.match(/^[a-zA-Z\s]+$/i)) {
					$(".errormessagediv").show();
					$(".validateTips").text(
					"Section Name Allows Only Characters");

					document.getElementById("sectionName").style.border = "1px solid #AF2C2C";
					document.getElementById("sectionName").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);

					return false;
				} 

				else if (sectionStrength == ""
					|| sectionStrength == null) {
					$(".errormessagediv").show();
					$(".validateTips").text(
					"Enter Division Strength");

					document.getElementById("sectionStrength").style.border = "1px solid #AF2C2C";
					document.getElementById("sectionStrength").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);

					return false;
				} 
				else if(!sectionStrength
						.match(/^[0-9]+$/)){

					$(".errormessagediv").show();
					$(".validateTips").text(
					"Only Numbers are Allowed for Strength");


					document.getElementById("sectionStrength").style.border = "1px solid #AF2C2C";
					document.getElementById("sectionStrength").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);

					return false;
				}
				else if(sectionStrength  <=0
				){

					$(".errormessagediv").show();
					$(".validateTips").text(
					"Division Strength Should be Greater Than 0");


					document.getElementById("sectionStrength").style.border = "1px solid #AF2C2C";
					document.getElementById("sectionStrength").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);

					return false;
				}

				else if(sectionNameCheck() == true){
					$(".errormessagediv").show();
					$(".validateTips").text(
					"Division Name Already Exists With This Class");

					document.getElementById("sectionName").style.border = "1px solid #AF2C2C";
					document.getElementById("sectionName").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);

					return false;
				}

				else if(sectionNameUpdate() == true){
					$(".errormessagediv").show();
					$(".validateTips").text(
					"Division Name Already Exists With This Class");

					document.getElementById("sectionName").style.border = "1px solid #AF2C2C";
					document.getElementById("sectionName").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);

					return false;
				}

				else {

					dataList={

							"classId":classId,
							"sectionName":sectionName,
							"sectionStrength":sectionStrength,
							"status":status,
							"updateClassCode":updateClassCode,
							"locationId":$("#locationname").val(),
					};
					$.ajax({

						type : "post",

						url : "sectionPath.html?method=insertSection",

						data : dataList,

						success : function(
								response) {
							var result = $.parseJSON(response);

							if(result.status == "Section Details Added Successfully"){

								$(".successmessagediv").show();
								$(".validateTips").text(
								"Adding Record progressing...");

								setTimeout(function(){
									window.location.href = "adminMenu.html?method=sectionList";
								},2000);
							}

							else if(result.status == "Section Details Updated Successfully"){

								$(".successmessagediv").show();
								$(".validateTips").text(
								"Updating Record progressing...");

								setTimeout(function(){
									window.location.href = "adminMenu.html?method=sectionList";
								},2000);
							}

							else{

								$(".errormessagediv").show();
								$(".validateTips").text("Division Faild to Save");

								setTimeout(function(){

									window.location.href = "sectionPath.html?method=addSection";

								},3000);


							}

						}

					});
				}

			});

	$('#editClass')
	.click(
			function() {

				$(".errormessagediv1").hide();
				var cnt = 0;
				$(
						'input.class_Checkbox_Class:checkbox:checked')
						.map(
								function() {
									if (cnt > 0) {
										$(".errormessagediv").show();
										$('.validateTips').text(
										"You can update only one Class Details at a time");
										cnt++;
										return false;
									} else {
										var check_id = $(this).attr("id");
										var split_id = check_id.split('_');
										getData = split_id[1].split(',');
										var classCode = getData[0];
										cnt++;
									}
								});
				if (cnt == 0) {

					$(".errormessagediv").show();
					$('.validateTips').text(
					"Select Class to Update");
					$(".errormessagediv").delay(3000).slideUp();
				}
				if (cnt == 1) {

					window.location.href = "classPath.html?method=editClass&classCode="
						+ classCode;

				}

			});

		});

function sectionNameCheck() {
	var locationId=$("#locationname").val();
	var classId = $('#classId').val();
	var sectionName = $('#sectionName').val();

	var updateClassCode = $('#updateClassCode').val();
	var checkClassName = {
			"classId" : classId,
			"stream" : sectionName,
			"updateClassCode" : updateClassCode,
			"locationId":locationId,
	};

	var status = false;

	var completeurl=window.location.href;
	var splitedurl=(((completeurl.split("="))[1]).split("&"))[0];

	if(splitedurl=="editSection"){
		status = false;
	}

	else {
		$.ajax({
			type : "POST",
			url : "sectionPath.html?method=sectionNameCheck",
			data : checkClassName,
			async : false,
			success : function(data) {

				var result = $.parseJSON(data);

				status = result.status;

				if(status == true){
					status = true;
				}else{
					status = false;
				}

			}
		});
	}
	return status;

}

function sectionNameUpdate(){

	var classId = $('#classId').val();
	var sectionName = $('#sectionName').val();
	var hiddenClassID=$("#hiddenStreamId").val();
	var hiddenStreamName = $('#hiddenStreamName').val();
	var checkClassName = {
			"classId" : classId,
			"stream" : sectionName,
			"hiddenStreamName" : hiddenStreamName,
	};

	var status = false;

	if(hiddenStreamName != sectionName || hiddenClassID != classId ){

		$.ajax({
			type : "POST",
			url : "sectionPath.html?method=sectionNameCheck",
			data : checkClassName,
			async : false,
			success : function(data) {


				var result = $.parseJSON(data);

				status = result.status;
				if(status == true) {
					status=true;
				}
				else{
					status=false;
				}

			}
		});
	}
	else{
		status=false;
	} 
	return status;
}

function getClass(pointer) {
	
	var div = $("#locationname").val();
	
	$
	.ajax({
		url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
		data:{'locationId':div},
		async : false,
		success : function(data) {
			$('#classId').empty();
			$('#classId').append('<option value="">-------------------Select-------------------</option>');
			var result = $.parseJSON(data);
			for ( var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

				$('#classId')
				.append(
						'<option value="'
						+ result.classDetailsIDandClassDetailsNameList[j].secDetailsId
						+ '">'
						+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName
						+ '</option>');

			}

		}
	});
}

function HideError() {
	document.getElementById("classId").style.border = "1px solid #ccc";
	document.getElementById("classId").style.backgroundColor = "#fff";
	document.getElementById("sectionName").style.border = "1px solid #ccc";
	document.getElementById("sectionName").style.backgroundColor = "#fff";
	document.getElementById("sectionStrength").style.border = "1px solid #ccc";
	document.getElementById("sectionStrength").style.backgroundColor = "#fff";

}
