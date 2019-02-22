$(document).ready(function() {

	$('#statusId').css("background-color", "green");
	
	$('#statusId').change(function() {
	    var selectedItem = $(this).find("option:selected");
	    $(this).css('backgroundColor', selectedItem.css('backgroundColor'));
	});
	
	$("#allstudent tbody tr").click(function(){

		window.location.href="adminMenu.html?method=studentWithheld";

	});
	
	if($('#hiddenPromotedStatus').val() != null || $('#hiddenPromotedStatus').val() != ""){
		$("#statusId option[value="+$('#hiddenPromotedStatus').val()+"]").attr('selected', 'true').css('backgroundColor');
	}
	
	if($('#hiddenClassId').val() != null || $('#hiddenClassId').val() != ""){
		var splitvalue=0;
		var columnValue = $('#hiddenClassId').val();
		var locationId=$('#hschoolLocation').val();
		
		getClassSection(columnValue, locationId);
		getClassSpecilization(columnValue,locationId);
	}
	
	$("#back").click(function(){

		window.location.href="adminMenu.html?method=NewstudentPromotionList";

	});


	$("#save").click(function(){
		
		var studentId=$('#studentid').val().trim();
		var academicyear_fromid=$('#hiddenFromAccyearId').val().trim();
		var locationId=$('#hiddenLocationId').val().trim();
		var status=$('#statusId').val().trim();
		var newsection =$('#newdivisionId').val();
		var newspec = $('#newspecilaizationId').val().trim();
		var promoted = $('#hiddenPromotedId').val().trim();
		
		var datalist = {
				
				"studentId": studentId,
				"accyear": academicyear_fromid,
				"locationId": locationId,
				"status": status,
				"newsection": newsection,
				"newspec": newspec,
				"promotedId": promoted,
		};
		
		if(newsection != "" ){

			$.ajax({

				type : 'POST',
				url : 'studentRegistration.html?method=updateStudentPromotion',
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
						$('.validateTips').text("Updating Record Progressing...");


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
			$('.validateTips').text("Field Requeired New Division.");
			$('.errormessagediv').delay(3000).slideUp("slow");
			
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
			$("#newdivisionId option[value='"+$('#hiddenSectiontoId').val()+"']").attr('selected', true);
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
			$("#newspecilaizationId option[value='"+$('#hiddenSpecilizationId').val()+"']").attr('selected', true);
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
				$('.validateTips1').text("The Promotion of Next Class doesn't Exits In Current Location");
				$('.errormessagediv').delay(3000).slideUp("slow");
				
				flag=false; 
			}else{
				flag=true;
			}
		}
	});
	return flag;
}