$(document).ready(function(){
	
	/*$(document).on('dblclick','.sorting',function(){
	    if(this.checked){
	        $(this).prop('checked', false);
	    }
	});*/
	
	var hacademicyear=$('#hacademicyaer').val();
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	
	$("#allstudent tbody tr").click(function(){
		var student_Id = $( this ).find(".studentid").attr("class").split(" ");
		individualStudentSearch(student_Id[0],student_Id[1],student_Id[2]);
		showContactDetails();
		$("#pageLoader").show();
		//window.location.href="adminMenu.html?method=individualStudentSearch&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
	
	});
	
	
	$("#resetbtn").on("click", function (e) {
		$("#locationname,#classname,#sectionid").val("all");
		$("#Acyearid").val($('#hacademicyaer').val());
		$("#searchvalue").val("");
		searchList();
	});
	
	$("#search").click(function(){
		searchList();
	});	
	
	$("#Acyearid").change(function(){
		var classname=$("#classname").val();
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		
		getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
	
	});
	
	$("#locationname").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		getClassList();
		getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
	$("#classname").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		getSectionList(classname);
		getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
	$("#sectionid").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		
		getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
	$("#sortingby").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		
		
		if($("#sortingby").val()=="Gender"){
			orderby=$("input[name='sorting1']:checked").val();
			 $("#ASC").hide();
			 $("p1").hide();
			 $("#DESC").hide();
			 $("p2").hide();
			 
			 	$("#Female").show();
		        $("p3").show();
		        $("#Male").show();
		        $("p4").show();
			}else{	
				orderby=$("input[name='sorting1']:checked").val();
				$("#Male").hide();	
				$("p3").hide();
		        $("#Female").hide();
		        $("p4").hide();
		        
				 $("#ASC").show();
				 $("p1").show();
				 $("#DESC").show();
				 $("p2").show();   
		}
		
		getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
	$("input[type='radio']").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		
		if($("#sortingby").val()=="Gender"){
			orderby=$("input[name='sorting1']:checked").val();
		}
		
		
		getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	$("#close").click(function(){
		
		$("#pageLoader").hide();
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
						$("#allstudent tbody").append("<tr><td ColSpan='5'>No Records Found</td></tr>");
					}
					
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
				$("#allstudent tbody tr").click(function(){
					var student_Id = $( this ).find(".studentid").attr("class").split(" ");
					individualStudentSearch(student_Id[0],student_Id[1],student_Id[2]);
					showContactDetails();
					$("#pageLoader").show();	
					//window.location.href="adminMenu.html?method=individualStudentSearch&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
				
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
			
			$('#sectionid').append('<option value="all">' + "ALL"	+ '</option>');
			
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
						$("#allstudent tbody").append("<tr><td ColSpan='5'>No Records Found</td></tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getClassWiseList.length);
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						individualStudentSearch(student_Id[0],student_Id[1],student_Id[2]);
						showContactDetails();
						$("#pageLoader").show();	
						//window.location.href="adminMenu.html?method=individualStudentSearch&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
					
					});
					pagination(100);
			}
		});
	}

function getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"sortingby" :sortingby,
			"orderby" :orderby,
			
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
							+"<td> "+result.getSectionWiseList[i].classsection+" </td>"
							+"</tr>");
					};
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='5'>No Records Founds</td>" +"</tr>");
					}
						
						
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getSectionWiseList.length);
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						individualStudentSearch(student_Id[0],student_Id[1],student_Id[2]);
						showContactDetails();
						$("#pageLoader").show();
						//window.location.href="adminMenu.html?method=individualStudentSearch&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
					
					});	
					pagination(100);
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
							+"<td> "+result.SearchList[i].classsection+" </td>"
							+"</tr>");
					}	
					}
					else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='5'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						individualStudentSearch(student_Id[0],student_Id[1],student_Id[2]);
						showContactDetails();
						$("#pageLoader").show();	
						//window.location.href="adminMenu.html?method=individualStudentSearch&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
					
					});
					
					pagination(100);
			}
		});
}

function individualStudentSearch(studentId,accyear,locationId){
	var jsonValue={
			"studentId":studentId,
			"accyear":accyear,
			"locationId":locationId,
	}
	$.ajax({
		type:'POST',
		url:'adminMenu.html?method=individualStudentSearchPopUp',
		data:jsonValue,
		async:false,
		success: function(response){
			var result=$.parseJSON(response);
			$("#academicYear").val(result.studentSearchList[0].academicYear);
			$("#schoolName").val(result.studentSearchList[0].location);
			$("#studentFullName").val(result.studentSearchList[0].studentFullName);
			$("#admissionNo").val(result.studentSearchList[0].studentAdmissionNo);
			$("#studentRollNo").val(result.studentSearchList[0].studentrollno);
			$("#classId").val(result.studentSearchList[0].classname);
			$("#sectionId").val(result.studentSearchList[0].sectionnaem);
			$("#routeId").val(result.studentSearchList[0].route);
			$("#stageId").val(result.studentSearchList[0].stage_name);
			$("#studentStatusId").val(result.studentSearchList[0].studentStatus);
			$("#confidentialStatusId").val(result.studentSearchList[0].confidentialStatus);
			$("#houseId").val(result.studentSearchList[0].houseName);
			$("#secondLanguageId").val(result.studentSearchList[0].secondLanguage);
			$("#thirdLanguageId").val(result.studentSearchList[0].thirdLanguage);
			$("#imagePreview").attr("src",result.studentSearchList[0].studentPhoto);
			$("#hstudentid").val(result.studentSearchList[0].studentId);
			$("#hacademicYearId").val(result.studentSearchList[0].academicYearId);
			$("#hschoolNameId").val(result.studentSearchList[0].locationId);
			$("#photohiddenid").val(result.studentSearchList[0].studentPhoto);
			
			
			
		}
	
	});
}

