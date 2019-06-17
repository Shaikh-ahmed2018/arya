$(document).ready(function(){
	$("#locationname,#sectionid").val("all");
	$("#searchvalue").val("");
	
	
	var hacademicyear=$('#hacademicyaer').val();
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');

	$("#allstudent tbody tr").click(function(){
		var student_Id = $( this ).find(".studentid").attr("class").split(" ");
		studentId=student_Id[0];
	    accyear=student_Id[1];
		locationId=student_Id[2];
		classId=student_Id[3];
		SectionId = student_Id[4];
		$('#hstudentId').val(studentId);
		$('#haccyear').val(accyear);
		$('#hlocationId').val(locationId);
		$('#hclassId').val(classId);
		$('#hSectionId').val(SectionId);
		$("#dialog1").dialog("open");
		selectExam(classId,SectionId,accyear,locationId,studentId);
	});
	
	
	$("#dialog1").dialog({

		autoOpen  : false,
		modal     : true,
		title     :"Select Exam",
		buttons   : {
			"Result": {
				text: 'Result',
				click: function() {
		    var	ExamCode=$('#Exam').val();
			var	studentId=$('#hstudentId').val();
			var	accyear=$('#haccyear').val();
			var	locationId=	$('#hlocationId').val();
			var	classId=$('#hclassId').val();
			var	SectionId=$('#hSectionId').val();
				
				 if(ExamCode==""||ExamCode==null){
						$(".errormessagediv").show();
						$(".validateTips").text("Select Exam");
						showError("#Exam");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false;
				 }else{
					 
					 datalist={
							   "ExamCode":ExamCode,
							   "studentId":studentId,
								"accyear":accyear,
								"locationId":locationId,
								"classId":classId,
								"SectionId":SectionId,
					 },
					 window.location.href="reportaction.html?method=individualStudentProgress&studentId="+studentId+"&accyear="+accyear+"&locationId="+locationId+"&classId="+classId+"&SectionId="+SectionId+"&ExamCode="+ExamCode;
					 
				 }
				}
			},
			'Back' : function() {
				$(this).dialog('close');
			}
		}
	});
	getClassList();
	
	$("#resetbtn").on("click", function (e) {
		$("#locationname").val("all");
		$("#Acyearid").val($('#hacademicyaer').val());
		$("#searchvalue").val("");
		getClassList();
		getSectionList();
		searchList();
	});
	
	$("#search").click(function(){
		searchList();
	});	
	$("#Acyearid").change(function(){
		if($("#classname").val()!='all'){
			changeAccYear();
		}
		else{
			getClassList();
			
			
		}
		var classname=$("#classname").val();
		getSectionList(classname);
		
	});
	$("#locationname").change(function(){
		$("#searchvalue").val("");
		if($("#classname").val()!='all'){
			changeAccYear();
		}
		else{
			getClassList();
		}
	
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
						+"<td> "+result.SearchList[i].sno+"</td>" 
						+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+result.SearchList[i].classDetailId+" "+result.SearchList[i].sectioncode+" "+"studentid"+"'> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classname+" </td>"
						+"<td> "+result.SearchList[i].sectionnaem+" </td>"
						+"</tr>");
				}
				}
				else{
					$("#allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
				$("#allstudent tbody tr").click(function(){
					var student_Id = $( this ).find(".studentid").attr("class").split(" ");
					studentId=student_Id[0];
				    accyear=student_Id[1];
					locationId=student_Id[2];
					classId=student_Id[3];
					SectionId = student_Id[4];
					$('#hstudentId').val(studentId);
					$('#haccyear').val(accyear);
					$('#hlocationId').val(locationId);
					$('#hclassId').val(classId);
					$('#hSectionId').val(SectionId);
					$("#dialog1").dialog("open");
					selectExam(classId,SectionId,accyear,locationId,studentId);
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

			$('#classname').append('<option value="all">' +"ALL"+ '</option>');

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
							+"<td> "+result.getClassWiseList[i].count+"</td>" 
							+"<td class='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].academicYearId+" "+result.getClassWiseList[i].locationId+" "+result.getClassWiseList[i].classDetailId+" "+result.getClassWiseList[i].sectioncode+" "+"studentid"+"'> "+result.getClassWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getClassWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getClassWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getClassWiseList[i].classname+" </td>"
							+"<td> "+result.getClassWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					}
					else{
						$("#allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getClassWiseList.length);
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						studentId=student_Id[0];
					    accyear=student_Id[1];
						locationId=student_Id[2];
						classId=student_Id[3];
						SectionId = student_Id[4];
						$('#hstudentId').val(studentId);
						$('#haccyear').val(accyear);
						$('#hlocationId').val(locationId);
						$('#hclassId').val(classId);
						$('#hSectionId').val(SectionId);
						$("#dialog1").dialog("open");
						selectExam(classId,SectionId,accyear,locationId,studentId);
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
							+"<td> "+result.getSectionWiseList[i].count+"</td>" 
							+"<td class='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].locationId+" "+result.getSectionWiseList[i].classDetailId+" "+result.getSectionWiseList[i].sectioncode+" "+"studentid"+"'> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getSectionWiseList[i].classname+" </td>"
							+"<td> "+result.getSectionWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}
			     }
					else{
						$("#allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getSectionWiseList.length);
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						studentId=student_Id[0];
					    accyear=student_Id[1];
						locationId=student_Id[2];
						classId=student_Id[3];
						SectionId = student_Id[4];
						$('#hstudentId').val(studentId);
						$('#haccyear').val(accyear);
						$('#hlocationId').val(locationId);
						$('#hclassId').val(classId);
						$('#hSectionId').val(SectionId);
						$("#dialog1").dialog("open");
						selectExam(classId,SectionId,accyear,locationId,studentId);
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
							+"<td> "+result.SearchList[i].sno+"</td>" 
							+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+result.SearchList[i].classDetailId+" "+result.SearchList[i].sectioncode+" "+"studentid"+"'> "+result.SearchList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.SearchList[i].studentFullName+"</td>"
							+"<td> "+result.SearchList[i].studentrollno+" </td>"
							+"<td> "+result.SearchList[i].classname+" </td>"
							+"<td> "+result.SearchList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					}
					else{
						$("#allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						studentId=student_Id[0];
					    accyear=student_Id[1];
						locationId=student_Id[2];
						classId=student_Id[3];
						SectionId = student_Id[4];
						$('#hstudentId').val(studentId);
						$('#haccyear').val(accyear);
						$('#hlocationId').val(locationId);
						$('#hclassId').val(classId);
						$('#hSectionId').val(SectionId);
						$("#dialog1").dialog("open");
						selectExam(classId,SectionId,accyear,locationId,studentId);
					});
			}
		});
}
function selectExam(classId,SectionId,accyear,locationId,studentId){
	datalist={
			"studentId": studentId,
			"accyear":accyear,
			"locationId":locationId,
			"classId":classId,
			"SectionId":SectionId,
	},
	
	$.ajax({
		
		type : 'POST',
		url : "reportaction.html?method=getExam",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#Exam').html("");
			
			$('#Exam').append('<option value="">' + "-----Select-----"	+ '</option>');
			
			for ( var j = 0; j < result.ExamList.length; j++) {

				$('#Exam').append('<option value="'+ result.ExamList[j].examName
						+ '">' + result.ExamList[j].examCode
						+ '</option>');
			}
		}
	});
}


function showError(id){
	
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	
}
