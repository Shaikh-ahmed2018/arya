 errorMsg=null;
$(document).ready(function() {
	var StudentImage=$("#hiddenPhotoId").val().trim();
	if(StudentImage!=""){

		$("#imagePreview").show();
		$('#imagePreview').attr('src', StudentImage);
	}
	$('#statusId').css("background-color", "green");
	
	$('#statusId').change(function() {
	    var selectedItem = $(this).find("option:selected");
	    $(this).css('backgroundColor', selectedItem.css('backgroundColor'));
	});
	
	$('#statusId').change(function(){
		var studentstatus=$('#statusId').val();
		if(studentstatus == 'demoted'){
			$('#newsectiondivid').hide();
			$('#newspecdivid').hide();
		}else{
			$('#newsectiondivid').show();
			$('#newspecdivid').show();
		}
	});
	
	$("#allstudent tbody tr").click(function(){

		window.location.href="adminMenu.html?method=studentWithheld";

	});
	if($('#hiddenClassId').val() != null || $('#hiddenClassId').val() != ""){
		var splitvalue=0;
		var columnValue = $('#hiddenClassId').val().trim();
		splitvalue=parseInt(columnValue.split("D")[1]);
		splitvalue=splitvalue+1;
		columnValue="CCD"+splitvalue;
		var locationId=$('#hschoolLocation').val();
		
		getClassSection(columnValue, locationId);
		getClassSpecilization(columnValue,locationId);
	}
	
	
	$("#back").click(function(){

		window.location.href="adminMenu.html?method=NewstudentPromotionList";

	});


	$("#save").click(function(){
		var studentIdArray = [];
		var academicyear_fromArray = [];
		var locationIdArray = [];
		var admissionNoArray = [];
		var rollNoArray = [];
		var class_fromArray = [];
		var section_fromArray = [];
		var specilization_fromArray = [];
		var class_toArray = [];
		var section_toArray = [];
		var specilization_toArray = [];
		var academicyear_toArray = [];
		var statusArray = [];
		var bvalid=true;

		var studentId=$('#studentid').val().trim();
		var academicyear_fromid=$('#hiddenFromAccyearId').val().trim();
		var locationId=$('#hiddenLocationId').val().trim();
		var academicyear_toid=$('#hiddenToAccyearId').val().trim();
		var admissionno = $('#admissionNo').val().trim();
		var rollno = $('#studentRollNo').val().trim();
		var classId = $('#hiddenClassId').val().trim();
		splitvalue=parseInt(classId.split("D")[1]);
		splitvalue=splitvalue+1;
		var toClass="CCD"+splitvalue;
		/*var toStream=toGetStreamName(toClass,locationId);
		alert(toStream);*/
		var sectionId = $('#hiddenSectionId').val().trim();
		var specilizationId = $('#hiddenSpecilizationId').val().trim();
		var status=$('#statusId').val().trim();
		var newsection =$('#newdivisionId').val();
		var newspec = $('#newspecilaizationId').val().trim();

		studentIdArray.push(studentId);
		academicyear_fromArray.push(academicyear_fromid);
		//locationIdArray.push(locationId);
		admissionNoArray.push(admissionno);
		rollNoArray.push(rollno);
		section_fromArray.push(sectionId);
		specilization_fromArray.push(specilizationId);
		class_fromArray.push(classId);
		if(status == "promoted"){
			class_toArray.push(toClass);
			section_toArray.push(newsection);
			specilization_toArray.push(newspec);
		}else{
			class_toArray.push(classId);
			section_toArray.push(sectionId);
			specilization_toArray.push(specilizationId);
		}
		statusArray.push(status);
		academicyear_toArray.push(academicyear_toid);
		
		var datalist = {
				"studentIdArray" : studentIdArray.toString(),
				"academicyear_fromArray": academicyear_fromArray.toString(),
				"locationIdArray" : locationId.toString(),
				"admissionNoArray": admissionNoArray.toString(),
				"rollNoArray" : rollNoArray.toString(),
				"class_fromArray": class_fromArray.toString(),
				"section_fromArray" : section_fromArray.toString(),
				"specilization_fromArray": specilization_fromArray.toString(),
				"class_toArray" : class_toArray.toString(),
				"statusArray": statusArray.toString(),
				"section_toArray" : section_toArray.toString(),
				"specilization_toArray": specilization_toArray.toString(),
				"academicyear_toArray": academicyear_toArray.toString(),
		};
		
		if(status == 'promoted'){
			if(newsection != "" ){
				if(toCheckNextYearAvailable()){
					$.ajax({

						type : 'POST',
						url : 'studentRegistration.html?method=saveStudentPromotion',
						data : datalist,

						beforeSend: function() {

							$('#loader').show();

						},
						success:function(response){
							var result = $.parseJSON(response);

							$('#loader').hide();

							if(result.expense_Result == "success"){
								$('.successmessagediv').show();
								$('.validateTips').css({
									'visibility' : 'visible',
								});
								$('.validateTips').text("Adding Record Progressing...");


								setTimeout(function () {

									window.location.href ="adminMenu.html?method=NewstudentPromotionList";
								}, 3000);


							}
							else{
								$('.errormessagediv').show();
								$('.validateTips1').css({
									'visibility' : 'visible'
								});
								$('.validateTips1').text("Please try again, after some time");
								$('.errormessagediv').delay(3000).slideUp("slow");
							}	
						}
					});
				}else{
					$('.errormessagediv').show();
					$('.validateTips').css({
						'visibility' : 'visible'
					});
					$('.validateTips').text("Next Academic Year Is Not Found, Please Create.");
					$('.errormessagediv').delay(3000).slideUp("slow");
					return false;
				}
			}else{
				$('.errormessagediv').show();
				$('.validateTips').css({
					'visibility' : 'visible'
				});
				$('.validateTips').text("Field Requeired New Division.");
				$('.errormessagediv').delay(3000).slideUp("slow");
				
			}
		}else{
			if(toCheckNextYearAvailable()){
				$.ajax({
					type : 'POST',
					url : 'studentRegistration.html?method=saveStudentPromotion',
					data : datalist,
					beforeSend: function() {

						$('#loader').show();

					},
					success:function(response){
						var result = $.parseJSON(response);

						$('#loader').hide();

						if(result.expense_Result == "success"){
							$('.successmessagediv').show();
							$('.validateTips').css({
								'visibility' : 'visible',
							});
							$('.validateTips').text("Adding Record Progressing...");


							setTimeout(function () {

								window.location.href ="adminMenu.html?method=NewstudentPromotionList";
							}, 3000);


						}
						else{
							$('.errormessagediv').show();
							$('.validateTips1').css({
								'visibility' : 'visible'
							});
							$('.validateTips1').text("Please try again, after some time");
							$('.errormessagediv').delay(3000).slideUp("slow");
						}	
					}
				});
			}
		}
	});
});

function getClassSection(columnValue,locationId) {
	
	var datalist = {
		"classidVal" : columnValue,
		"locationId":locationId,
	}; 
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#newdivisionId').empty();
			$('#newdivisionId').append('<option value="' + "" + '">' + "----------Select----------" + '</option>');

			for ( var j = 0; j < result.sectionList.length; j++) {
				$('#newdivisionId').append(
						'<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}

		}
	});

}

function getClassSpecilization(columnValue,locationId){
	var data = {
			"classId" : columnValue,
			"locationId":locationId,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#newspecilaizationId').empty();
			$('#newspecilaizationId').append('<option value="' + "" + '">'+ "----------Select----------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#newspecilaizationId').append(
						'<option value="'
						+ result.jsonResponse[j].spec_Id
						+ '">'
						+ result.jsonResponse[j].spec_Name
						+ '</option>');
			}


		}
	});
}

function toCheckNextClassAvailable(toClass,locationId){
	var flag = true;
	var data = {
			"toClass" : toClass,
			"locationId":locationId,
	};
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=toCheckNextClassAvailable",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			if(result.checkClass == "notpresent"){
				$('.errormessagediv').show();
				$('.validateTips1').css({
					'visibility' : 'visible'
				});
				errorMsg="The Promotion of Next Class doesn't Exits In Current Location";
				$('.errormessagediv').delay(3000).slideUp("slow");
				
				flag=false; 
			}else{
				flag=true;
			}
		}
	});
	return flag;
}

function toCheckNextYearAvailable(){
	var accyearid=$('#hiddenFromAccyearId').val();
	var flag = true;
	var data = {
			"AccYear" : accyearid,
	};
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=toCheckNextYearAvailable",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
		
			if(result.checkNextYear.trim() == "Next Year Not Avaliable"){
				
				errorMsg="Next Academic Year Is Not Found, Please Create.";
								
				flag=false; 
			}else{
				flag=true;
			}
		}
	});
	return flag;
}

function toGetStreamName(toClass,locationId){
	
	var data = {
			"toClass" : toClass,
			"locationId" : locationId
	};
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=toGetStreamName",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			value=result.getStream;
		}
	});
	return value;
}