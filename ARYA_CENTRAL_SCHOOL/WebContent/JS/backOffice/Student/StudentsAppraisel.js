$(document).ready(function(){
	
	var hacademicyear=$('#hacademicyaer').val();
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	
	$("#allstudent tbody tr").click(function(){
		var student_Id = $( this ).find(".studentid").attr("class").split(" ");
		rowclick(student_Id[0],student_Id[1],student_Id[2]);
		//window.location.href="adminMenu.html?method=AddStudentAppraisalReport&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
	
	});
	
	getClassList();
	
	$("#resetbtn").on("click", function (e) {
		$("#locationname,#classname,#sectionid").val("all");
		$("#Acyearid").val($("#hacademicyaer").val());
		$("#searchvalue").val("");
		  searchList();
		
	});
	
	
	
	 $("#searchvalue").keypress(function(e){
		 var searchname = $("#searchvalue").val().trim();
		    if(e.keyCode == 13){
		        e.preventDefault();
		        searchList();
		  
 		 }	
 	 });
	
	
	$("#search").click(function(){
		searchList();
	});	
	$("#Acyearid").change(function(){
		$("#searchvalue").val("");
		changeAccYear();
		getClassList();
		var classname=$("#classname").val();
		getSectionList(classname);
	});
	$("#locationname").change(function(){
		$("#searchvalue").val("");
		changeAccYear();
		getClassList();
		var classname=$("#classname").val();
		getSectionList(classname);
	});
	
	$("#classname").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		
		
		getSectionList(classname);
		getStudentList(locationid,accyear,classname);
	});
	
	$("#sectionid").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		
		getStudentListBySection(locationid,accyear,classname,sectionid);
	});
	
});

function changeAccYear(){
	var locationId = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=searchAllAcademicYearDetails",
		data : {
			"locationId" :locationId,
			"accyear" :accyear,				
		},
		async : false,
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				if(result.SearchList.length>0){
				for(var i=0;i<result.SearchList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
						+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classname+" </td>"
						+"<td> "+result.SearchList[i].sectionnaem+" </td>"
						+"</tr>");
				}
				}
				else{
					$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
				}
				pagination(20);
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
				$("#allstudent tbody tr").click(function(){
					var student_Id = $( this ).find(".studentid").attr("class").split(" ");
					rowclick(student_Id[0],student_Id[1],student_Id[2]);
					//window.location.href="adminMenu.html?method=AddStudentAppraisalReport&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
				
				});
			
		}
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

			$('#classname').append('<option value="All">' + "----------Select----------"	+ '</option>');

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
	});}

function getStudentList(locationid,accyear,classname){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			
		}, $.ajax({
			type : 'POST',
			url : "adminMenu.html?method=getStudentDetailsLByFilter",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.getClassWiseList.length>0){
					for(var i=0;i<result.getClassWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].academicYearId+" "+result.getClassWiseList[i].locationId+" "+"studentid"+"'>"+result.getClassWiseList[i].count+"</td>" 
							+"<td> "+result.getClassWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getClassWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getClassWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getClassWiseList[i].classname+" </td>"
							+"<td> "+result.getClassWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					}
					else{
						$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
					}
					pagination(20);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						rowclick(student_Id[0],student_Id[1],student_Id[2]);
						//window.location.href="adminMenu.html?method=AddStudentAppraisalReport&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
					
					});
			}
		});
	}

function getStudentListBySection(locationid,accyear,classname,sectionid){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
		}, $.ajax({
			type : 'POST',
			url : "adminMenu.html?method=getStudentListBySection",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.getSectionWiseList.length>0){
					for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].locationId+" "+"studentid"+"'>"+result.getSectionWiseList[i].count+"</td>" 
							+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getSectionWiseList[i].classname+" </td>"
							+"<td> "+result.getSectionWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					}
					else{
						$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
					}
					pagination(20);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						rowclick(student_Id[0],student_Id[1],student_Id[2]);
						//window.location.href="adminMenu.html?method=AddStudentAppraisalReport&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
					
					});
			}
		});
	}

function searchList(){

	var searchname = $("#searchvalue").val().trim();
	
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
			url : "studentRegistration.html?method=getStudentSearchByList",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.SearchList.length>0){
					for(var i=0;i<result.SearchList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
							+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.SearchList[i].studentFullName+"</td>"
							+"<td> "+result.SearchList[i].studentrollno+" </td>"
							+"<td> "+result.SearchList[i].classname+" </td>"
							+"<td> "+result.SearchList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					}
					else{
						$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
					}
					pagination(20);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						rowclick(student_Id[0],student_Id[1],student_Id[2]);
						//window.location.href="adminMenu.html?method=AddStudentAppraisalReport&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
					
					});
			}
		});
}
function rowclick(studentId,accyear,locationId){
	window.open(
			  "adminMenu.html?method=AddStudentAppraisalReport&studentId="+studentId+"&accyear="+accyear+"&locationId="+locationId,
			  '_blank' // <- This is what makes it open in a new window.
			);
}