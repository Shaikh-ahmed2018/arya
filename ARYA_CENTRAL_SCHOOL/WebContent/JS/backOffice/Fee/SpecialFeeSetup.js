$(document).ready(function(){
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='8'>NO Records Found</td></tr>");
	}
	
	
	$("#Acyearid").val($("#hacademicyaer").val());
	

	
	$("#search").click(function(){
		academicYear=$("#Acyearid").val();
		classId=$("#classname").val();
		searchTerm=$("#searchvalue").val();
		getStudentListByJs("all",academicYear,"all","all",searchTerm);
	});
	$("#searchvalue").keypress(function(e){
		if(e.keyCode=="13"){
		academicYear=$("#Acyearid").val();
		searchTerm=$("#searchvalue").val();
		getStudentListByJs("all",academicYear,"all","all",searchTerm);
		}
	});

	
});




function getSpecialFee(locationId,accyearId,specialFee){
	var dataList={
			"locationId":locationId,
			"academicYear":accyearId,
			"searchTerm":"",
	};
	$.ajax({
		type:'POST',
		url:'feecollection.html?method=feeDetailsListbyjs',
		data:dataList,
		async:false,
		success: function(data){
			
			var result = $.parseJSON(data);
			
			$(specialFee).html("");
			
			
			for ( var i = 0; i < result.feelist.length; i++) {

				$(specialFee).append('<option value="' + result.feelist[i].id
						+ '">' + result.feelist[i].name
						+ '</option>');
			}
		}
	});
}
function getTermListByJs(locationId,accyear,specialFee){
		$.ajax({
			type:'POST',
			url:'termfee.html?method=termListByJs',
			data:{"locationId":locationId,"accyear":accyear},
			async:false,
			success:function(response){
				var result=$.parseJSON(response);
			
			$(specialFee).html("");
			
			
			for(var i=0;i<result.termlist.length;i++){

				$(specialFee).append('<option value="' + result.termlist[i].termid
						+ '">' + result.termlist[i].termname
						+ '</option>');
			}
		}
	});
}
function handle(event){
	if(event.keyCode==13){
		var stuId=$("#searchvalue").val().trim();
		window.location.href="adminMenu.html?method=feeCollection&stuId="+stuId;
	}
}


function getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm){
	datalist={
			"locationId" : locationId,
			"academicYear" : academicYear,
			"classId" : classId,
			"divisionId" : divisionId,
			"searchTerm" : searchTerm
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=studentListByJs",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			$('#allstudent tbody').empty();
			var i;
			var leng=result.studentdetailslist.length;
			if(leng>0){
				for( i=0;i<leng;i++){
					var rel=result.studentdetailslist[i];
					$('#allstudent tbody').append("<tr class='"+rel.studentId+" "+rel.academicYearId+" "+rel.locationId+"'>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+rel.academicYear+"</td>" +
							"<td>"+rel.studentAdmissionNo+"</td>" +
							"<td>"+rel.studentFullName+"</td>" +
							"<td>"+rel.classSectionId+"</td>" +
							"<td><select id='specialFee' name='accyear' class='form-control' required></select></td>" +
							"<td><select id='term' name='term' class='form-control' required></select></td>" +
							"<td><input type='text' name='specialFeeAmount' id='specialFeeAmount' maxlength='50' value='0.0' style='text-align: right;'></td>" +
							"<td><button type='button' class='btn btn-info' onclick='SpecialFeeSave(this);'>Save</button></td>" +
							"</tr>");
					
					getSpecialFee(rel.locationId,rel.academicYearId,"#specialFee");
					getTermListByJs(rel.locationId,rel.academicYearId,"#term");
				}
			
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='8'>NO Records Found</td></tr>");
			}
				
		
		}
	});
}
function SpecialFeeSave(pointer){
	var dataList={
			"studentId":$(pointer).closest("tr").attr("class").split(" ")[0],
			"accyearId":$(pointer).closest("tr").attr("class").split(" ")[1],
			"feeCode":$("#specialFee").val(),
			"feeAmount":$("#specialFeeAmount").val(),
			"term":$("#term").val(),
	};
	$.ajax({
		type:'POST',
		url:'feecollection.html?method=saveSpecialFee',
		data:dataList,
		async:false,
		success: function(data){
			
			var result = $.parseJSON(data);
			if(result.status=="true"){
				alert("Fee Settings Updated for this student");
			}
			else{
				alert("Fee Settings Not Updated for this student");
			}
			
			
		}
	});
}
