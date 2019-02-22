$(document).ready(function() {
	
	$("#selectAll").change(function(){
		$(".select").prop("checked",$(this).prop("checked"));
	});
	
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	});

	$("#submission").datepicker({
		dateFormat : "dd-mm-yy",
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(selectedDate){
			 var max = $(this).datepicker('getDate');
			  $("#assdate").datepicker('option', 'maxDate', max || $("#submission").val()); // Set other min, default to today
		}
	});
	
	$("#assdate").datepicker({
		dateFormat : "dd-mm-yy",
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(selectedDate){
			 var min = $(this).datepicker('getDate');
			  $("#submission").datepicker('option', 'minDate', min || $("#assdate").val()); // Set other min, default to today
		}
	});

	$('#edit').click(function() {
		var count = 0;
		var projectId = null;

		$('input[name="selectBox"]:checked').each(function() {
			count = count + 1;
			projectId = this.value;
		});


		if (count > 1 || count == 0) {
			$('.errormessagediv').show();
			$('.validateTips').text("Select any one record");
		} else {
			window.location.href = "assignmentupload.html?method=editProject&projectId="
				+ projectId;
		}
	});

	$('#save').click(function() {

		var projectname = $("#projectname").val(); 
		var assignedDate = $("#assdate").val(); 
		var submissionDate = $("#submission").val(); 
		var maxMarks = $("#maxid").val(); 
		var schoolName = $("#locationname").val(); 
		var className = $("#classname").val(); 
		var division = $("#sectionid").val(); 
		var description = $("#description").val(); 
		var specialization = $("#specializationid").val();
	    var subject=$("#subjectid").val();
		var classid=$("#classid").val();
		var section=$("#section").val();
		
		if(specialization == "all")
		{
			specialization="-";
		}
		
		var StudentId = [];
		var rollNo = [];
		var admissionId = [];
		var classId = [];
		var sectionId = [];
		var specId = [];
		var counts = 0;



		$("#allstudent tbody tr").find(".select:checked").each(function(){
			var arrayval=$(this).val();

			values=arrayval.split(",");

			StudentId.push(values[0]);
			rollNo.push(values[1]);
			admissionId.push(values[2]);
			classId.push(values[3]);
			sectionId.push(values[4]);
			specId.push(values[5]);

			counts++;
		});

		if (counts == 0) {

			$(".errormessagediv").show();
			$(".validateTips").text("Please Fill Fields & Select Atleast One Record !");
			$(".errormessagediv").delay(3000).fadeOut();

			return false;
		} 
		
		if (projectname=="") {

			$(".errormessagediv").show();
			$(".validateTips").text("Project Name Should Not Be Empty");
			
			document.getElementById("projectname").style.border = "1px solid #AF2C2C";
			document.getElementById("projectname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);


			return false;
		}else if (assignedDate=="") {
			$(".errormessagediv").show();
			$(".validateTips").text("Assigned Date Should Not Be Empty");
			document.getElementById("assdate").style.border = "1px solid #AF2C2C";
			document.getElementById("assdate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if (submissionDate=="") {

			$(".errormessagediv").show();
			$(".validateTips").text("Submission Date Should Not Be Empty");
			document.getElementById("submission").style.border = "1px solid #AF2C2C";
			document.getElementById("submission").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}else if (subject=="") {

			$(".errormessagediv").show();
			$(".validateTips").text("Subject Should Not Be Empty");
			document.getElementById("subjectid").style.border = "1px solid #AF2C2C";
			document.getElementById("subjectid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}
		else if (maxMarks=="") {

			$(".errormessagediv").show();
			$(".validateTips").text("Max Marks Should Not Be Empty");
			document.getElementById("maxid").style.border = "1px solid #AF2C2C";
			document.getElementById("maxid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}else if (classid=="") {
			
			$(".errormessagediv").show();
			$(".validateTips").text("Class Should Not Be Empty");
			document.getElementById("classid").style.border = "1px solid #AF2C2C";
			document.getElementById("classid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}else if (section=="") {

			$(".errormessagediv").show();
			$(".validateTips").text("Section Should Not Be Empty");
			document.getElementById("section").style.border = "1px solid #AF2C2C";
			document.getElementById("section").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}else{
			var datalist = {
					"studentId": StudentId.toString(),
					"rollNo": rollNo.toString(),
					"admissionId": admissionId.toString(),
					"classId": classId.toString(),
					"sectionId": sectionId.toString(),
					"specId": specId.toString(),
					"projectname" : projectname ,
					"assignedDate" : assignedDate ,
					"submissionDate": submissionDate ,
					"max_mark" : maxMarks,
					"sch_Name" : schoolName,
					"cls_Name" : className,
					"division" : division,
					"desc" : description,
					"special" : specialization,
					"subject":subject,
			}
			$.ajax({
				type : 'POST',
				url : "assignmentupload.html?method=addnewProject",
				data : datalist,
				success : function(response) {
					var result = $.parseJSON(response);
					$('.errormessagediv').hide();

					if (result.ProjectStatus=="true") 
					{
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
						$(".validateTips").text("Adding Record Progressing...");
						$('.successmessagediv').delay(3000).slideUp();
					} 
					else
					{
						$(".successmessagediv").hide();
						$(".errormessagediv").show();
						$(".validateTips").text("Adding Project Failed.Try Again !");
						$('.errormessagediv').delay(3000).slideUp();
					}
					
					setTimeout(function(){
						window.location.href="teachermenuaction.html?method=projectAssign";
					},3000);
				}

			}); 
		}
	});

	getClassList();
	$("#locationname").change(function(){
		getClassList();
		var classname=$("#classname").val();
		getSectionList(classname);
	});

	$("#classname").change(function(){
		var classname=$("#classname").val();
		var locationId=$("#locationname").val().trim();
		getSectionList(classname);
		getClassSpecilization(classname,locationId);

		if(classname == "CCD14" || classname == "CCD15")
		{
			$('#subjectid').empty();
		
			$('#subjectid').click(function(){
			
				if($('#specializationid').val()=="all"){
				$(".errormessagediv").show();
				$(".validateTips").text("Select Specialization To Select Subject");
				$('.errormessagediv').delay(3000).slideUp();
				}
			});
		}

		else
		{
			var specializationname=$("#specializationid").val();
			getSubjectList(classname,specializationname,locationId);
		}
	});

	$("#specializationid").change(function(){
		var classname=$("#classname").val();
		var locationId=$("#locationname").val().trim();
		var sectionid=$("#sectionid").val();
		var specializationname=$("#specializationid").val();

		var specializationname=$("#specializationid").val();
		getSubjectList(classname,specializationname,locationId);
		getStudentListBySpecialization(classname,locationId,sectionid,specializationname);
	});

	$("#sectionid").change(function(){
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		getStudentListBySection(locationid,classname,sectionid);
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

function getClassSpecilization(classId,locationId){
	var data = {
			"classId" : classId,
			"locationId":locationId,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specializationid').empty();
			$('#specializationid').append('<option value="all">'+ "ALL" + '</option>');
			if(result.jsonResponse.length>0){
				for ( var j = 0; j < result.jsonResponse.length; j++) {
					$('#specializationid').append('<option value="'+ result.jsonResponse[j].spec_Id+ '">'+ result.jsonResponse[j].spec_Name+ '</option>');
				}
			}
		}
	});
}

function getSubjectList(classId,specializationname,locationId){
	var data = {
			"classname" : classId,
			"location":locationId,
			"specialization":specializationname
	};
	$.ajax({
		type : 'POST',
		url : "adminMenu.html?method=SubjectListforListOnchangeMethod",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#subjectid').empty();
			$('#subjectid').append('<option value="all">'+ "ALL" + '</option>');
			if(result.list.length>0){
				for ( var j = 0; j < result.list.length; j++) {
					$('#subjectid').append('<option value="'+ result.list[j].subjectid+ '">'+ result.list[j].subjectname+ '</option>');
				}
			}
		}
	});
}

function getStudentListBySection(locationid,classname,sectionid){

	datalist = {

			"location" :locationid,
			"classId" :classname,
			"sectionid" :sectionid,
	}, $.ajax({
		type : 'POST',
		url : "adminMenu.html?method=getStudentListByDivision",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			for(var i=0;i<result.getSectionWiseList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td><input type='checkbox' class='select' value='"+result.getSectionWiseList[i].studentId+","
						+result.getSectionWiseList[i].studentrollno+","+result.getSectionWiseList[i].studentAdmissionNo+","
						+result.getSectionWiseList[i].classDetailId+","
						+result.getSectionWiseList[i].classSectionId+","+result.getSectionWiseList[i].specilization+"'/></td>"

						+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
						+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.getSectionWiseList[i].studentFullName+"</td>"
						+"<td> "+result.getSectionWiseList[i].classname+" </td>"
						+"<td> "+result.getSectionWiseList[i].sectionnaem+" </td>"
						+"<td> "+result.getSectionWiseList[i].specilizationname+" </td>"
						+"</tr>");
			}
		}
	});
}

function getStudentListBySpecialization(classname,locationId,sectionid,specializationname){
	datalist = {

			"location" :locationId,
			"classId" :classname,
			"sectionid" :sectionid,
			"spec_Name" :specializationname,
		},
	$.ajax({
		type : 'POST',
		url : "adminMenu.html?method=getStudentListBySpecialization",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			for(var i=0;i<result.getSpecializationWiseList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td><input type='checkbox' class='select' value='"+result.getSpecializationWiseList[i].studentId+","
						+result.getSpecializationWiseList[i].studentrollno+","+result.getSpecializationWiseList[i].studentAdmissionNo+","
						+result.getSpecializationWiseList[i].classDetailId+","
						+result.getSpecializationWiseList[i].classSectionId+","+result.getSpecializationWiseList[i].specilization+"'/></td>"

						+"<td> "+result.getSpecializationWiseList[i].studentrollno+" </td>"
						+"<td> "+result.getSpecializationWiseList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.getSpecializationWiseList[i].studentFullName+"</td>"
						+"<td> "+result.getSpecializationWiseList[i].classname+" </td>"
						+"<td> "+result.getSpecializationWiseList[i].sectionnaem+" </td>"
						+"<td> "+result.getSpecializationWiseList[i].specilizationname+" </td>"
						+"</tr>");
			}
		}
	});
}

function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}

