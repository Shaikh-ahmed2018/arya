$(document).ready(function(){
	
	$("#Acyearid").val($("#hacademicyaer").val());
	rowClick();
	$("#locationname").change(function(){
		getClassList();
		var location=$("#locationname").val();
		
		var accyear=$("#Acyearid").val();
		if(accyear=="" || accyear==undefined){
			
			accyear="all";
		}
		var classId=$("#classname").val();
		if(classId=="" || classId==undefined){
			
			classId="all";
		}
		var divisionId=$("#sectionid").val();
		if(divisionId=="" || divisionId==undefined){
			
			divisionId="all";
		}
		var searchby=$("#searchvalue").val();
		if(searchby=="" || searchby==undefined){
			
			searchby="all";
		}
		if(location!="" && location!=undefined){
			getStudentListForTranport(accyear,location,classId,divisionId,searchby);
			
		}
		
	});
	$("#Acyearid").change(function(){
		var location=$("#locationname").val();
		
		var accyear=$("#Acyearid").val();
		if(accyear=="" || accyear==undefined){
			
			accyear="all";
		}
		var classId=$("#classname").val();
		if(classId=="" || classId==undefined){
			
			classId="all";
		}
		var divisionId=$("#sectionid").val();
		if(divisionId=="" || divisionId==undefined){
			
			divisionId="all";
		}
		var searchby=$("#searchvalue").val();
		if(searchby=="" || searchby==undefined){
			
			searchby="all";
		}
		if(location!="" && location!=undefined){
			getStudentListForTranport(accyear,location,classId,divisionId,searchby);
			
		}
		
	});
	$("#classname").change(function(){
		var location=$("#locationname").val();
		
		var accyear=$("#Acyearid").val();
		if(accyear=="" || accyear==undefined){
			
			accyear="all";
		}
		var classId=$("#classname").val();
		if(classId=="" || classId==undefined){
			
			classId="all";
		}
		var divisionId=$("#sectionid").val();
		if(divisionId=="" || divisionId==undefined){
			
			divisionId="all";
		}
		var searchby=$("#searchvalue").val();
		if(searchby=="" || searchby==undefined){
			
			searchby="all";
		}
		getSectionList(classId);
		if(location!="" && location!=undefined){
			getStudentListForTranport(accyear,location,classId,divisionId,searchby);
			
		}
	});
	$("#sectionid").change(function(){
		var location=$("#locationname").val();
		if(location=="" || location==undefined){
			
			location="all";
		}
		var accyear=$("#Acyearid").val();
		if(accyear=="" || accyear==undefined){
			
			accyear="all";
		}
		var classId=$("#classname").val();
		if(classId=="" || classId==undefined){
			
			classId="all";
		}
		var divisionId=$("#sectionid").val();
		if(divisionId=="" || divisionId==undefined){
			
			divisionId="all";
		}
		var searchby=$("#searchvalue").val();
		if(searchby=="" || searchby==undefined){
			
			searchby="all";
		}
		getStudentListForTranport(accyear,location,classId,divisionId,searchby);
	});
	$("#searchvalue").keypress(function(e){
		if(e.keyCode==13){
			var location=$("#locationname").val();
			if(location=="" || location==undefined){
				
				location="all";
			}
			var accyear=$("#Acyearid").val();
			if(accyear=="" || accyear==undefined){
				
				accyear="all";
			}
			var classId=$("#classname").val();
			if(classId=="" || classId==undefined){
				
				classId="all";
			}
			var divisionId=$("#sectionid").val();
			if(divisionId=="" || divisionId==undefined){
				
				divisionId="all";
			}
			var searchby=$("#searchvalue").val();
			if(searchby=="" || searchby==undefined){
				
				searchby="all";
			}
			getStudentListForTranport(accyear,location,classId,divisionId,searchby);
		}
	});
	$("#search").click(function(){
		var location=$("#locationname").val();
		if(location=="" || location==undefined){
			
			location="all";
		}
		var accyear=$("#Acyearid").val();
		if(accyear=="" || accyear==undefined){
			
			accyear="all";
		}
		var classId=$("#classname").val();
		if(classId=="" || classId==undefined){
			
			classId="all";
		}
		var divisionId=$("#sectionid").val();
		if(divisionId=="" || divisionId==undefined){
			
			divisionId="all";
		}
		var searchby=$("#searchvalue").val();
		if(searchby=="" || searchby==undefined){
			
			searchby="all";
		}
		getStudentListForTranport(accyear,location,classId,divisionId,searchby);
	});
	$("#resetbtn").click(function(){
		$("#locationname").val("all");
		$("#Acyearid").val($("#hacademicyaer").val());
		$("#classname").val("all");
		$("#sectionid").val("all");
		$("#searchvalue").val("");
	});
	
	
	pagination(100);
	$("#ShowPerPage").val("100");
	$("#ShowPerPage").change(function(){
		pagination(this.value);
	});
});
function getStudentListForTranport(accyear,location,classId,divisionId,searchby){
	var dataList={
			"accyear":accyear,
			"location":location,
			"classId":classId,
			"divisionId":divisionId,
			"searchby":searchby,
	};
	$.ajax({
		type:"POST",
		url:"adminMenu.html?method=transportFeeCollectionForFilteration",
		data:dataList,
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.List.length>0){
				for(var i=0;i<result.List.length;i++){
					$("#allstudent tbody").append("<tr>"+
									"<td>"+(i+1)+"</td>"+
									"<td class='"+result.List[i].academicYearId+" "+result.List[i].locationId+" studentid'>"+result.List[i].studentAdmissionNo+"</td>"+
									"<td class='"+result.List[i].studentId+" stuid'>"+result.List[i].studentFullName+"</td>"+
									"<td class='classid'>"+result.List[i].classname+"</td>"+
									"<td class='sectionid'>"+result.List[i].sectionnaem+"</td></tr>");
				}
				pagination(100);
				$("#ShowPerPage").val("100");
				$("#ShowPerPage").change(function(){
					pagination(this.value);
				});
				rowClick();
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='5'>No Record Found</td></tr>");
				$("#allstudent").removeClass("table-striped");
			}
			
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.List.length);
		}
		
	});
}
function rowClick(){
	$("table#allstudent tbody tr").click(function(){
		var studentId=$(this).find(".stuid").attr("class").split(" ")[0];
		var accyId=$(this).find(".studentid").attr("class").split(" ")[0];
		var locId=$(this).find(".studentid").attr("class").split(" ")[1];
		window.location.href = "feecollection.html?method=TransportfeeCollectionStudentWise&student="
			+ studentId+"&accyId="+accyId+"&locId="+locId;
	});
	
}
function getClassList(){
	var locationid=$("#locationname").val();
	datalist={
			"locationid" : locationid
	},

	$.ajax({

		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classname').html("");

			$('#classname').append('<option value="all">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}
function getSectionList(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname").val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
	}