
$(document).ready(function(){
	
	var hacademicyear=$('#hacademicyaer').val();
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	
	$("#allstudent tbody tr").click(function(){
		var student_Id = $( this ).find(".studentid").attr("class").split(" ");
		
		window.location.href="adminMenu.html?method=PrintTransportIDCardSingle&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
		//window.location.href="adminMenu.html?method=PrintTransportIDCardSingle";
	});
	
	getClassList();
	
	$("#resetbtn").on("click", function (e) {
		$("#locationname,#Acyearid,#classname,#sectionid").val("all");
		$("#searchvalue").val("");
	});
	
	$("#search").click(function(){
		searchList();
	});	
	$("#Acyearid").change(function(){
		$("#searchvalue").val("");
		changeAccYear();
		getClassList();
	});
	$("#locationname").change(function(){
		$("#searchvalue").val("");
		changeAccYear();
		getClassList();
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
	
/*	rowClickable();*/
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
						+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
						+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classname+" </td>"
						+"<td> "+result.SearchList[i].sectionnaem+" </td>"
						+"</tr>");
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
				$("#allstudent tbody tr").click(function(){
					var student_Id = $( this ).find(".studentid").attr("class").split(" ");
					
					window.location.href="adminMenu.html?method=PrintTransportIDCardSingle&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
					//window.location.href="adminMenu.html?method=PrintTransportIDCardSingle";
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

			$('#classname').append('<option value="All">' + "ALL"	+ '</option>');

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
			
			$('#sectionid').append('<option value="All">' + "ALL"	+ '</option>');
			
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
							+"<td class='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].academicYearId+" "+result.getClassWiseList[i].locationId+" "+"studentid"+"'>"+result.getClassWiseList[i].count+"</td>" 
							+"<td> "+result.getClassWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getClassWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getClassWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getClassWiseList[i].classname+" </td>"
							+"<td> "+result.getClassWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}	
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getClassWiseList.length);
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						
						window.location.href="adminMenu.html?method=PrintTransportIDCardSingle&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
						//window.location.href="adminMenu.html?method=PrintTransportIDCardSingle";
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
			url : "transport.html?method=getStudentListBySection",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
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
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getSectionWiseList.length);
					
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						
						window.location.href="adminMenu.html?method=PrintTransportIDCardSingle&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
						//window.location.href="adminMenu.html?method=PrintTransportIDCardSingle";
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
			url : "transport.html?method=getStudentSearchByList",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
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
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
				
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						
						window.location.href="adminMenu.html?method=PrintTransportIDCardSingle&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
					});	
				
			}
		});
}













/*$(document).ready(function(){

$("#allstudent tbody tr").click(function(){
			
			window.location.href="adminMenu.html?method=PrintTransportIDCardSingle";

		});
});
	

$(document).ready(function(){
	printpreview();
	var hacademicyear=$('#hacademicyaer').val();
	var globallocation=$('#school').val();
	
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	
	
	$("#Acyearid").change(function(){
		changeAccYear();
		
	});
	$("#locationname").change(function(){
		changeAccYear();
		getStream($(this));
	});
	$("#streamId").change(function(){
		getClassList();
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
				for(var i=0;i<result.SearchList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td>"+result.SearchList[i].sno+"</td>" 
						+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classname+" </td>"
						+"<td> "+result.SearchList[i].sectionnaem+" </td>"
						+"</tr>");
				}	
				$("#allstudent tbody tr").click(function(){
					var student_Id = $( this ).find(".studentid").attr("class").split(" ");
					
					window.location.href="adminMenu.html?method=individualStudentSearch&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
				
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
			"classname" : classname
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentTransferReport.html?method=getSectionList",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="' + "" + '">' + "----------Select----------"	+ '</option>');
			
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
			url : "adminMenu.html?method=getStudentDetailsLByFilter",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					for(var i=0;i<result.getClassWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='studentid' value='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].locationId+" "+result.getClassWiseList[i].academicYearId+" />'</td>" 
							+"<td> "+result.getClassWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getClassWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getClassWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getClassWiseList[i].classname+" </td>"
							+"<td> "+result.getClassWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}	
					
				
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
			url : "adminMenu.html?method=getStudentListforPrint",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='studentid' value='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].locationId+" "+result.getClassWiseList[i].academicYearId+" />'</td>"
							+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getSectionWiseList[i].classname+" </td>"
							+"<td> "+result.getSectionWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}	
					
					
				
			}
		});

	}
function printpreview(){
	$("#print").click(function(){
		var studentId=[];
		var locationId=[];
		var academicYearId=[];
		
		$("#allstudent tbody tr").find(".studentid:checked").each(function(){
			studentId.push($(this).val().split(" ")[0]);
			locationId.push($(this).val().split(" ")[1]);
			academicYearId.push($(this).val().split(" ")[2]);
			
		});
		
	
		window.location.href="adminMenu.html?method=PrintPreviewStudentMultipleIDCard&location="
			+locationId
			+"&accyear="
			+academicYearId
			+"&studentId="
			+studentId;
		
	});
	
}
function getStudentListforPrint(locationid,accyear,classname,streamId,sectionid){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"streamId":streamId,
			"sectionid" :sectionid,
		}, $.ajax({
			type : 'POST',
			url : "adminMenu.html?method=getStudentListforPrint",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='studentid' value='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].locationId+" "+result.getClassWiseList[i].academicYearId+" />'</td>"
							+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getSectionWiseList[i].classname+" </td>"
							+"<td> "+result.getSectionWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}	
					
					
				
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
			$('#streamId').append('<option value="">-------Select-------</option>');
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#streamId').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');

			}

		}
	});
}
*/