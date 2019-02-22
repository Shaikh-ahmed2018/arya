$(document).ready(function() 
		{
	getAcademicYears();
	getAcademicNextYears($("#academicYear").val());
	getClassListForPromotion($("#academicYear").val());
	$("#academicYear").change(function(){
		getAcademicNextYears($(this).val());
		getClassListForPromotion($(this).val());
		promotion();
	});
	promotion();
	
				});
function getAcademicYears() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAcademicYear",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#academicYear").empty();
			$("#academicYear").append(
					'<option value="' + "" + '">--Select--' + "" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$("#academicYear").append(
						'<option value="'
								+ result.jsonResponse[j].academicYearId + '">'
								+ result.jsonResponse[j].academicYear
								+ '</option>');
			}
			$("#academicYear").val($("#hacademicyaer").val());
		}
	});

}	
function getAcademicNextYears(currentYear){
	$.ajax({
		type : 'POST',
		url : "adminMenu.html?method=getAcademicNextYear",
		data:{'currentYear':currentYear},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			
		if(result.jsonResponse.length>0){
			$("#nextacademicYear").val(result.jsonResponse[1]);
			$("#nextacademicYear").attr("name",result.jsonResponse[0]);
			$(".errormessagediv").hide();
		}
		else{
			$("#nextacademicYear").val("");
			$(".successmessagediv").hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Next Academic Year Not Created!");
			
		}
		}
			
	});
}
function getClassListForPromotion(currentYear){
	$.ajax({
		type : 'POST',
		url : "adminMenu.html?method=getClassListForPromotion",
		data:{'currentYear':currentYear},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#promotionClass").empty();
			$("#promotionClass").css({
				'overflow-y':'scroll',
				'max-height':'300px'
			});
			$("#promotionClass").append(
					'<table id="allstudent" class="table" width="100%">'
					+'<tr>'
					+'<th>Sl.No.</th>'
					+'<th>Class</th>'
					+'<th>Section</th>'
					+'<th>Specialization</th>'
					+'<th>Total Students</th>'
					+'<th>Promoted Students</th>'
					+'<th>Demoted Students</th>'
					+'<th>status</th>'
					+'</tr>'
					+'</table>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$("#allstudent").append(
						'<tr id=rowid'+(j+1) + ' class="'+result.jsonResponse[j].classId+' '+result.jsonResponse[j].sectionId+' '+result.jsonResponse[j].specializationId+' '+currentYear+'" name='+result.jsonResponse[j].specializationName+'>'
								+'<td>'+(j+1) +'</td>'
								+'<td>'+result.jsonResponse[j].className +'</td>'
								+'<td>'+result.jsonResponse[j].sectionName +'</td>'
								+'<td>'+result.jsonResponse[j].specializationName+'</td>'
								+'<td>'+result.jsonResponse[j].studentStrength+'</td>'
								+'<td>'+result.jsonResponse[j].promotedStudent+'</td>'
								+'<td>'+result.jsonResponse[j].demotedStudent+'</td>'
								+'<td><span class="'+result.jsonResponse[j].status+'">'+result.jsonResponse[j].status+'</span></td>'
								+ '</tr>');
			}
			
		}
	});
	
}
function promotion(){
	$("#allstudent tbody tr[id]").click(function(){
		var allData=$(this).attr("class");
		var classId=allData.split(" ")[0];
		var sectionId=allData.split(" ")[1];
		var specializationId=allData.split(" ")[2];
		var accYear=allData.split(" ")[3];
		var specName=$(this).attr("name");
		window.location.href="studentPromote.html?method=studentPromotionList&classId="+classId+"&sectionId="+sectionId
		+"&specializationId="+specializationId
		+"&accYear="+accYear
		+"&specName="+specName;
	});
}