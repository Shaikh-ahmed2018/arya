$(document).ready(function(){
	printpreview();
	var hacademicyear=$('#hacademicyaer').val();
	var globallocation=$('#school').val();
	
	$("#Acyearid").val($("#hiddenaccyear").val());
	
	
	$(".selectall").change(function(){
		$(".studentid").prop("checked",$(this).prop("checked"));
	});
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	
	
	$("#Acyearid").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();
		var sectionId=$("#sectionid").val();
		changeAccYear();
		if(sectionId==""){
			sectionId="all";
		}
		if(classname==""){
			classname="all";
		}
		if(streamId !="" && locationid !=""){
		getStudentListforPrint(locationid,accyear,classname,streamId,sectionId);
		}
	});
	$("#locationname").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();
		var sectionId=$("#sectionid").val();
	/*	changeAccYear();*/
		if(sectionId==""){
			sectionId="all";
		}
		if(classname==""){
			classname="all";
		}
		if(streamId !="" && accyear !=""){
		getStudentListforPrint(locationid,accyear,classname,streamId,sectionId);
		}
		getStream($(this));
	});
	$("#streamId").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();
		var sectionId=$("#sectionid").val();
		if(sectionId==""){
			sectionId="all";
		}
		if(classname==""){
			classname="all";
		}
		getClassList();
		getStudentListforPrint(locationid,accyear,classname,streamId,sectionId);
	});
	$("#classname").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();
		var sectionId=$("#sectionid").val();
		if(sectionId==""){
			sectionId="all";
		}
		
		getSectionList(classname);
		getStudentListforPrint(locationid,accyear,classname,streamId,sectionId);
	});
	
	$("#sectionid").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();
		var sectionId=$("#sectionid").val();
		
		getStudentListforPrint(locationid,accyear,classname,streamId,sectionId);
	});
	
});

function changeAccYear(){
	var locationId = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "transport.html?method=getStudentListByLoc",
		data : {
			"location" :locationId,
			"accyear" :accyear,				
		},
		async : false,
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				for(var i=0;i<result.SearchList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td><input type='checkbox' class='studentid' value='"+result.SearchList[i].studentId+" "+result.SearchList[i].locationId+" "+result.SearchList[i].academicYearId+" "+$('#streamId').val()+"'/></td>" 
						+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classname+" </td>"
						+"<td> "+result.SearchList[i].sectionnaem+" </td>"
						+"</tr>");
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.studentdetailslist.length);
				printpreview();	
				
		}
	});
}
function getClassList(){
	var streamId=$("#streamId").val();
	datalist={
			"streamId" : streamId
	},

	$.ajax({

		type : 'POST',
		url : "reportaction.html?method=getClassesByStream",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classname').html("");

			$('#classname').append('<option value="all">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassesList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassesList[j].classId + '">'

						+ result.ClassesList[j].classname

						+ '</option>');

			}

		}


	});

}

function getSectionList(classname){
	datalist={
			"classname" : classname,
			"location":$("#locationname").val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentTransferReport.html?method=getSectionList",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="' + "all" + '">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.seclist.length; j++) {

				$('#sectionid').append('<option value="'

						+ result.seclist[j].sectionId + '">'

						+ result.seclist[j].sectionName

						+ '</option>');
			}
			
		}
		
		
	});
}

function getStudentList(locationid,accyear,classname){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			
		}, $.ajax({
			type : 'POST',
			url : "transport.html?method=getStudentListByClass",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					for(var i=0;i<result.getClassWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='studentid' value='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].locationId+" "+result.getClassWiseList[i].academicYearId+" "+$('#streamId').val()+"' /></td>" 
							+"<td> "+result.getClassWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getClassWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getClassWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getClassWiseList[i].classname+" </td>"
							+"<td> "+result.getClassWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getClassWiseList.length);
					printpreview();	
				
			}
		});

	}



function printpreview(){
	$("#print").click(function(){
		var studentId=[];
		var locationId=[];
		var academicYearId=[];
		var streamId=[];
		var cnt = 0;
		
		$("#allstudent tbody tr").find(".studentid:checked").each(function(){
			studentId.push($(this).val().split(" ")[0]);
			locationId.push($(this).val().split(" ")[1]);
			academicYearId.push($(this).val().split(" ")[2]);
			streamId.push($(this).val().split(" ")[3]);
			cnt++;
			
		});
		if (cnt == 0) {
			
			$(".errormessagediv").show();
			$(".validateTips").text("Please select atleast one record !");
			$(".errormessagediv").delay(2000).fadeOut();
			
			return false;
		} else{
			
	
		window.location.href="adminMenu.html?method=PrintPreviewTransportMultipleIDCard&location="
			+locationId
			+"&accyear="
			+academicYearId
			+"&studentId="
			+studentId
			+"&streamId="
			+streamId;
		}
	});
	
}
function getStudentListforPrint(locationid,accyear,classname,streamId,sectionid){
	
	var datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"streamId":streamId,
			"sectionid" :sectionid,
			"flag":"transport",
		}; 
		$.ajax({
			type : 'POST',
			url : "adminMenu.html?method=StudentListforPrint",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					for(var i=0;i<result.getSectionWiseList.length;i++){

					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='studentid' value='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].locationId+" "+result.getSectionWiseList[i].academicYearId+" "+$('#streamId').val()+"' /></td>"
							+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getSectionWiseList[i].classname+" </td>"
							+"<td> "+result.getSectionWiseList[i].sectionnaem+" </td>"
							+"<td> <img class='thumbnai' src='"+result.getSectionWiseList[i].image+"' width='20' height='20' />  </td>"
							+"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getSectionWiseList.length);
					
					
				
			}
		});

	}
function getStream(pointer){
	$.ajax({
		url : "classPath.html?method=getStreamDetailAction",
		async : false,
		data:{'school':pointer.val().trim()},
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#streamId').empty();
			$('#streamId').append('<option value="">---------------Select-----------</option>');
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#streamId').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');

			}

		}
	});
}
