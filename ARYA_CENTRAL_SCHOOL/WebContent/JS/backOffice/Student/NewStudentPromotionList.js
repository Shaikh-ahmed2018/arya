$(document).ready(function() {
	studentDemotedList();
	var hacademicyear=$('#hacademicyaer').val();
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	$(".Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	
	$("#resetbtn").on("click", function (e) {
		$("#locationname,#Acyearid,#classname,#sectionid").val("all");
		$("#searchBy").val("");
	});
	
	$("#allstudent tbody tr").click(function(){
		var student_Id = $(this).find(".studentid").attr("class").split(" ");
		
		window.location.href="adminMenu.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];
	
	});
	
	getClassListDemoted();
	getClassList();
	$("#locationname").change(function(){
		$("#searchBy").val("");
		changeAccYear();
		getClassList();
		var classname=$("#classname").val();
		getSectionList(classname);
	});		

	$("#classname").change(function(){
		$("#searchBy").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
	
		getSectionList(classname);
		getStudentPromotedClassList(locationid,accyear,classname);
	});
	
	$("#classid").change(function(){
		$("#searchBy").val("");
		var locationid=$("#locationname").val();
		var accyear=$(".Acyearid").val();
		var classname=$("#classid").val();
	
		getSectionListDemoted(classname);
		getStudentDemotedClassList(locationid,accyear,classname);
	});
	
	$("#sectionid").change(function(){
		$("#searchBy").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		
		getStudentPromotedClassSectionList(locationid,accyear,classname,sectionid);
	});
	
	$("#sectionid1").change(function(){
		$("#searchBy").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classid").val();
		var sectionid=$("#sectionid1").val();
		
		getStudentDemotedClassSectionList(locationid,accyear,classname,sectionid);
	});
	
	$("#Acyearid").change(function(){
		$("#searchBy").val("");
		changeAccYear();
		getClassList();
		var classname=$("#classname").val();
		getSectionList(classname);
	});
	
	$(".Acyearid").change(function(){
		$("#searchBy").val("");
		$("#classid,#sectionid1").val("");
		changeAccYearByDemoted();
		getClassListDemoted();
		var classname=$("#classid").val();
		getSectionListDemoted(classname);
	});
	
	$(".locationname").change(function(){
		$("#searchBy").val("");
		changeAccYearByDemoted();
		getClassListDemoted();
		var classname=$("#classid").val();
		getSectionListDemoted(classname);
	});
	
	$("#search").click(function(){
		searchList();
	});
	
	$("#searchdemoted").click(function(){
		searchDemotedList();
	});
	
	$(".locationname").change(function(){
		getClassListDemoted();
	});
	$('#domoted').click(function(){
		
		$('#promotedId').hide();
		$('#demotedDiv').addClass("in");
		$('#demotedDiv').css({
			'display':'block !important'
		});
		
	});
		
		$("#addnew").click(function(){
			
			window.location.href="adminMenu.html?method=getStudentForPromotion";

		});
});

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

			$('#classname').append('<option value="all">' + "----------Select----------"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');

			}

		}


	});

}
function getClassListDemoted(){
	var locationid=$(".locationname").val();
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

			$('#classid').html("");

			$('#classid').append('<option value="all">' + "----------Select----------"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classid').append('<option value="'

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
			
			$('#sectionid').append('<option value="all">' + "----------Select----------"	+ '</option>');
			
			for ( var j = 0; j <result.sectionList.length; j++) {
                   
				$('#sectionid').append('<option value="'

						+ result.sectionList[j].sectioncode + '">'

						+ result.sectionList[j].sectionnaem

						+ '</option>');
			}
			
		}
		
		
	});
}

function getSectionListDemoted(classname){
	
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname1").val()
	},
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid1').html("");
			
			$('#sectionid1').append('<option value="all">' + "----------Select----------"	+ '</option>');
			
			for ( var j = 0; j <result.sectionList.length; j++) {
                   
				$('#sectionid1').append('<option value="'

						+ result.sectionList[j].sectioncode + '">'

						+ result.sectionList[j].sectionnaem

						+ '</option>');
			}
			
		}
		
		
	});
}

function getStudentPromotedClassList(locationid,accyear,classname){

	datalist = {

			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,

	}, $.ajax({
		type : 'POST',
		url : "adminMenu.html?method=getStudentPromotedClassList",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.getPromotedClassWiseList.length>0){
			for(var i=0;i<result.getPromotedClassWiseList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.getPromotedClassWiseList[i].studentId+" "+result.getPromotedClassWiseList[i].locationId+" "+result.getPromotedClassWiseList[i].academicYearId+" "+result.getPromotedClassWiseList[i].promotionId+" "+"studentid"+"'>"+result.getPromotedClassWiseList[i].count+"</td>" 
						+"<td> "+result.getPromotedClassWiseList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.getPromotedClassWiseList[i].studentFullName+" </td>"
						+"<td> "+result.getPromotedClassWiseList[i].studentrollno+" </td>"
						+"<td> "+result.getPromotedClassWiseList[i].classname+" </td>"
						+"<td> "+result.getPromotedClassWiseList[i].sectionnaem+" </td>"
						+"<td> "+result.getPromotedClassWiseList[i].specilizationname+" </td>"
						+"</tr>");
			}	
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}
			$("#allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				
				window.location.href="adminMenu.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];
			
			});
		}
	});
}

function getStudentPromotedClassSectionList(locationid,accyear,classname,sectionid){

	datalist = {

			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
	}, $.ajax({
		type : 'POST',
		url : "adminMenu.html?method=getStudentPromotedClassSectionList",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.getPromotedSectionWiseList.length>0){
				
			
			for(var i=0;i<result.getPromotedSectionWiseList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.getPromotedSectionWiseList[i].studentId+" "+result.getPromotedSectionWiseList[i].locationId+" "+result.getPromotedSectionWiseList[i].academicYearId+" "+result.getPromotedSectionWiseList[i].promotionId+" "+"studentid"+"'>"+result.getPromotedSectionWiseList[i].count+"</td>" 
						+"<td> "+result.getPromotedSectionWiseList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.getPromotedSectionWiseList[i].studentFullName+" </td>"
						+"<td> "+result.getPromotedSectionWiseList[i].studentrollno+" </td>"
						+"<td> "+result.getPromotedSectionWiseList[i].classname+" </td>"
						+"<td> "+result.getPromotedSectionWiseList[i].sectionnaem+" </td>"
						+"<td> "+result.getPromotedSectionWiseList[i].specilizationname+" </td>"
						+"</tr>");
			}	
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}
			$("#allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				
				window.location.href="adminMenu.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];
			
			});
		}
	});
}

function changeAccYear(){
	var locationId = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAllAcademicYearPromotedDetails",
		data : {
			"locationId" :locationId,
			"accyear" :accyear,				
		},
		async : false,
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				if(result.StudentPromotedList.length>0){
				for(var i=0;i<result.StudentPromotedList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.StudentPromotedList[i].studentId+" "+result.StudentPromotedList[i].locationId+" "+result.StudentPromotedList[i].academicYearId+" "+result.StudentPromotedList[i].promotionId+" "+"studentid"+"'>"+result.StudentPromotedList[i].count+"</td>" 
						+"<td> "+result.StudentPromotedList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.StudentPromotedList[i].studentFullName+"</td>"
						+"<td> "+result.StudentPromotedList[i].studentrollno+" </td>"
						+"<td> "+result.StudentPromotedList[i].classname+" </td>"
						+"<td> "+result.StudentPromotedList[i].sectionnaem+" </td>"
						+"<td> "+result.StudentPromotedList[i].specilizationname+" </td>"
						+"</tr>");
				}	
				}
				else{
					$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
				}
				
				$("#allstudent tbody tr").click(function(){
					var student_Id = $(this).find(".studentid").attr("class").split(" ");
					
					window.location.href="adminMenu.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];
				
				});
			
		}
	});
}

function searchList(){

	var searchname = $("#searchBy").val().trim();

	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();

	datalist = {

			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :searchname,
	}, $.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getStudentPromotedSearchList",
		data: datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#divIdVal').show();
			$('#allstudent tbody').empty();
			if(result.StudentPromotedSearchList.length>0){
			for(var i=0;i<result.StudentPromotedSearchList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.StudentPromotedSearchList[i].studentId+" "+result.StudentPromotedSearchList[i].locationId+" "+result.StudentPromotedSearchList[i].academicYearId+" "+result.StudentPromotedSearchList[i].promotionId+" "+"studentid"+"'>"+result.StudentPromotedSearchList[i].count+"</td>" 
						+"<td> "+result.StudentPromotedSearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.StudentPromotedSearchList[i].studentFullName+"</td>"
						+"<td> "+result.StudentPromotedSearchList[i].studentrollno+" </td>"
						+"<td> "+result.StudentPromotedSearchList[i].classname+" </td>"
						+"<td> "+result.StudentPromotedSearchList[i].sectionnaem+" </td>"
						+"<td> "+result.StudentPromotedSearchList[i].specilizationname+" </td>"
						+"</tr>");
			}
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}
			$("#allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				
				window.location.href="adminMenu.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];
			
			});
		}
	
	});
}

function studentDemotedList(){
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +">"
			+"<tr><th style='width: 70px;'>SI No</th>"+
			"<th>Admission No</th>" +
			"<th style='width: 200px;'>Student Name</th>" +
			"<th>Roll No</th>" +
			"<th>Class</th>" +
			"<th>Division</th>" +
			"<th>Specilaization</th>" +
			"</tr>" +
			"</table>"
	);
	
	$.ajax({
		
		type : "POST",
		url : "adminMenu.html?method=studentDemotedList",
		async : false,
		
		success : function(response) {

			var result = $.parseJSON(response);
			if(result.getStudentDemotedList.length>0){
			for (var i = 0; i < result.getStudentDemotedList.length; i++) {

				$(".allstudent").append("<tr>"
						+"<td class='"+result.getStudentDemotedList[i].studentId+" "+result.getStudentDemotedList[i].locationId+" "+result.getStudentDemotedList[i].academicYearId+" "+result.getStudentDemotedList[i].promotionId+" "+"studentid"+"'>"+result.getStudentDemotedList[i].count+"</td>" 
						+"<td> "+result.getStudentDemotedList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.getStudentDemotedList[i].studentFullName+" </td>"
						+"<td> "+result.getStudentDemotedList[i].studentrollno+" </td>"
						+"<td> "+result.getStudentDemotedList[i].classname+" </td>"
						+"<td> "+result.getStudentDemotedList[i].sectionnaem+" </td>"
						+"<td> "+result.getStudentDemotedList[i].specilizationname+" </td>"
						+"</tr>");
			}	
		}
			else{
				$('.allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}
			
			$(".allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				
				window.location.href="adminMenu.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];
			
			});
		}
		
	});
}

function getStudentDemotedClassList(locationid,accyear,classname){
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +">"
			+"<tr><th style='width: 70px;'>SI No</th>"+
			"<th>Admission No</th>" +
			"<th style='width: 200px;'>Student Name</th>" +
			"<th>Roll No</th>" +
			"<th>Class</th>" +
			"<th>Division</th>" +
			"<th>Specilaization</th>" +
			"</tr>" +
			"</table>"
	);
	datalist = {

			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,

	}, $.ajax({
		type : 'POST',
		url : "adminMenu.html?method=getStudentDemotedClassList",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			if(result.getDemotedClassWiseList.length>0){
			for(var i=0;i<result.getDemotedClassWiseList.length;i++){
				$(".allstudent tbody").append("<tr>"
						+"<td class='"+result.getDemotedClassWiseList[i].studentId+" "+result.getDemotedClassWiseList[i].locationId+" "+result.getDemotedClassWiseList[i].academicYearId+" "+result.getDemotedClassWiseList[i].promotionId+" "+"studentid"+"'>"+result.getDemotedClassWiseList[i].count+"</td>" 
						+"<td> "+result.getDemotedClassWiseList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.getDemotedClassWiseList[i].studentFullName+" </td>"
						+"<td> "+result.getDemotedClassWiseList[i].studentrollno+" </td>"
						+"<td> "+result.getDemotedClassWiseList[i].classname+" </td>"
						+"<td> "+result.getDemotedClassWiseList[i].sectionnaem+" </td>"
						+"<td> "+result.getDemotedClassWiseList[i].specilizationname+" </td>"
						+"</tr>");
			}	
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}
			$(".allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				
				window.location.href="adminMenu.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];
			
			});
		}
	});
}

function getStudentDemotedClassSectionList(locationid,accyear,classname,sectionid){
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +">"
			+"<tr><th style='width: 70px;'>SI No</th>"+
			"<th>Admission No</th>" +
			"<th style='width: 200px;'>Student Name</th>" +
			"<th>Roll No</th>" +
			"<th>Class</th>" +
			"<th>Division</th>" +
			"<th>Specilaization</th>" +
			"</tr>" +
			"</table>"
	);
	datalist = {

			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
	}, $.ajax({
		type : 'POST',
		url : "adminMenu.html?method=getStudentDemotedClassSectionList",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			if(result.getDemotedSectionWiseList.length>0){
			for(var i=0;i<result.getDemotedSectionWiseList.length;i++){
				$(".allstudent tbody").append("<tr>"
						+"<td class='"+result.getDemotedSectionWiseList[i].studentId+" "+result.getDemotedSectionWiseList[i].locationId+" "+result.getDemotedSectionWiseList[i].academicYearId+" "+result.getDemotedSectionWiseList[i].promotionId+" "+"studentid"+"'>"+result.getDemotedSectionWiseList[i].count+"</td>" 
						+"<td> "+result.getDemotedSectionWiseList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.getDemotedSectionWiseList[i].studentFullName+" </td>"
						+"<td> "+result.getDemotedSectionWiseList[i].studentrollno+" </td>"
						+"<td> "+result.getDemotedSectionWiseList[i].classname+" </td>"
						+"<td> "+result.getDemotedSectionWiseList[i].sectionnaem+" </td>"
						+"<td> "+result.getDemotedSectionWiseList[i].specilizationname+" </td>"
						+"</tr>");
			}	
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}
			$(".allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				
				window.location.href="adminMenu.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];
			
			});
		}
	});
}

function searchDemotedList(){
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +">"
			+"<tr><th style='width: 70px;'>SI No</th>"+
			"<th>Admission No</th>" +
			"<th style='width: 200px;'>Student Name</th>" +
			"<th>Roll No</th>" +
			"<th>Class</th>" +
			"<th>Division</th>" +
			"<th>Specilaization</th>" +
			"</tr>" +
			"</table>"
	);
	var searchname = $("#searchBy1").val().trim();

	var locationid=$(".locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classid").val();
	var sectionid=$("#sectionid1").val();

	datalist = {

			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :searchname,
	}, $.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getStudentDemotedSearchList",
		data: datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			
			/*$('.allstudent tbody').empty();*/
				if(result.StudentDemotedSearchList.length>0){	
			for(var i=0;i<result.StudentDemotedSearchList.length;i++){
				$(".allstudent tbody").append("<tr>"
						+"<td class='"+result.StudentDemotedSearchList[i].studentId+" "+result.StudentDemotedSearchList[i].locationId+" "+result.StudentDemotedSearchList[i].academicYearId+" "+result.StudentDemotedSearchList[i].promotionId+" "+"studentid"+"'>"+result.StudentDemotedSearchList[i].count+"</td>" 
						+"<td> "+result.StudentDemotedSearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.StudentDemotedSearchList[i].studentFullName+"</td>"
						+"<td> "+result.StudentDemotedSearchList[i].studentrollno+" </td>"
						+"<td> "+result.StudentDemotedSearchList[i].classname+" </td>"
						+"<td> "+result.StudentDemotedSearchList[i].sectionnaem+" </td>"
						+"<td> "+result.StudentDemotedSearchList[i].specilizationname+" </td>"
						+"</tr>");
			}
				}
				else{
					$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
				}
			$(".allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				
				window.location.href="adminMenu.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];
			
			});
		}
	
	});
}

function changeAccYearByDemoted(){
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +">"
			+"<tr><th style='width: 70px;'>SI No</th>"+
			"<th>Admission No</th>" +
			"<th style='width: 200px;'>Student Name</th>" +
			"<th>Roll No</th>" +
			"<th>Class</th>" +
			"<th>Division</th>" +
			"<th>Specilaization</th>" +
			"</tr>" +
			"</table>"
	);
	var locationId = $(".locationname").val();
	var accyear = $(".Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAllAcademicYearDemotedDetails",
		data : {
			"locationId" :locationId,
			"accyear" :accyear,				
		},
		async : false,
		
		success : function(response) {

			var result = $.parseJSON(response);
			if(result.StudentDemotedList.length>0){
			for(var i=0;i<result.StudentDemotedList.length;i++){
				$(".allstudent tbody").append("<tr>"
						+"<td class='"+result.StudentDemotedList[i].studentId+" "+result.StudentDemotedList[i].locationId+" "+result.StudentDemotedList[i].academicYearId+" "+result.StudentDemotedList[i].promotionId+" "+"studentid"+"'>"+result.StudentDemotedList[i].count+"</td>" 
						+"<td> "+result.StudentDemotedList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.StudentDemotedList[i].studentFullName+"</td>"
						+"<td> "+result.StudentDemotedList[i].studentrollno+" </td>"
						+"<td> "+result.StudentDemotedList[i].classname+" </td>"
						+"<td> "+result.StudentDemotedList[i].sectionnaem+" </td>"
						+"<td> "+result.StudentDemotedList[i].specilizationname+" </td>"
						+"</tr>");
			}	
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}
			$(".allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");

				window.location.href="adminMenu.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];

			});

		}
	});
}