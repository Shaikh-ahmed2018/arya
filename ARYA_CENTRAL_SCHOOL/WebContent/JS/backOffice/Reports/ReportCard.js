$(document).ready(function(){	
	
	$("#accyear").val($("#hiddenaccyear").val());
	
	$("#location").change(function(){
		getClassList();
		
	});
	$("#reset").click(function(){
		$('input[name="selectBox"]').prop('checked', false);
	});
	
	$("#class").change(function(){
		var classId=$("#class").val();
		var accyear=$("#accyear").val();
		var locationId=$("#location").val();
		
		$.ajax({
			type : "GET",
			url : "reportaction.html?method=getSectionByClass",
			data : {"classId":classId,
					"location":locationId},
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);

				$("#section").html("");
				$("#section").append('<option value="all">' + "ALL"	+ '</option>');

				for (var j = 0; j < result.SectionList.length; j++) {

					$("#section").append(
							'<option value="'
							+ result.SectionList[j].sectionId
							+ '">'
							+ result.SectionList[j].sectionname
							+ '</option>');
				}
			}
		});

		if(classId == 'CCD12' || classId == 'CCD13'|| classId == 'CCD14' || classId == 'CCD15'  ){
			$("#spanaccyear").show();
			$("#term1").attr("checked",false);
			$("#term2").attr("checked",false);
			$("#spanterms").hide();
		}else if(classId == "all"){
			$("#spanterms").show();
			$("#spanaccyear").show();
			$("#term1").attr("checked",false);
			$("#term2").attr("checked",false);
			$("#reportaccyear").attr("checked",false);
		}else{
			$("#spanterms").show();
			$("#term1").attr("checked",false);
			$("#term2").attr("checked",false);
			$("#reportaccyear").attr("checked",false);
			$("#spanaccyear").hide();
		}
		//getStudentList(locationId,accyear,classId);
	
	});
	
	$("#term2").click(function(){
		var classval=$("#class").val();
		
		if(classval == "all"){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Class.");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}
		
		if($("#term1:checked").val() != undefined){
			var termval=$("#term2").val();
			var examstypeprefix=$("#examstypeprefixforterm2").val();
			var splitPrefix=examstypeprefix.split(',');
			
			if(termval == ""){
				$(".errormessagediv").show();
				$(".validateTips").text("Please Set Term II Exam For Selected Class.");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 2000);
				return false;
			}else if(splitPrefix[0] == "prdxm" && splitPrefix[1] == ""){
				$(".errormessagediv").show();
				$(".validateTips").text("Please Set Term II Yearly Exam For Selected Class.");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 2000);
				return false;
			}else if((splitPrefix[0] == "hlfym" && splitPrefix[1] == "") || (splitPrefix[0] == "yrlym" && splitPrefix[1] == "")){
				$(".errormessagediv").show();
				$(".validateTips").text("Please Set Term II Periodic Exam For Selected Class.");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 2000);
				return false;
			}
		}else{
			$(".errormessagediv").show();
			$(".validateTips").text("Please Select The Term-I And Then Select Term-II .");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			$("#term2").attr("checked",false);
			return false;
		}
	});
	
	$("#term1").click(function(){
		var classval=$("#class").val();
		var termval=$("#term1").val();
		var examstypeprefix=$("#examstypeprefix").val();
		var splitPrefix=examstypeprefix.split(',');
		
		if(classval == "all"){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Class.");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}
		
		if(termval == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Please Set Term I Exam For Selected Class.");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		
		}else if(splitPrefix[0] == "prdxm" && splitPrefix[1] == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Please Set Term I Half Yearly Exam For Selected Class.");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}else if(splitPrefix[0] == "hlfym" && splitPrefix[1] == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Please Set Term I Periodic Exam For Selected Class.");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}
	});
	
	$("#download").click(function(){
		var chkArray = [];
		var locationId=$("#location").val();
		var accyear=$("#accyear").val();
		var classId=$("#class").val();
		var sectionId=$("#section").val();
		var examstypeid=$("#examstypeid").val();
		var examstypeidterm2=$("#examstypeidterm2").val();
		var examstypeidaccyear=$("#examstypeidaccyear").val();
		var stdId=$('input[name="selectBox"]:checked').val();
		if(stdId == undefined){
			stdId="";
		}
		var checkterm1=$("#term1:checked").val();
		if(checkterm1 == undefined){
			checkterm1="";
		}
		var checkterm2=$("#term2:checked").val();
		if(checkterm2 == undefined){
			checkterm2="";
		}
		var reportaccyear=$("#reportaccyear:checked").val();
		
		//var checkterm2=$("#term2").attr("checked",true).val();
		//var reportaccyear=$("#reportaccyear").attr("checked",true).val();
		
		var saveFlag=true;

		/* look for all checkboes that have a parent id called 'checkboxlist' attached to it and check if it was checked */
		$("#checkboxlist input:checked").each(function() {
			chkArray.push($(this).val());
		});
		// we join the array separated by the comma 
		
		/* check if there is selected checkboxes, by default the length is 1 as it contains one single comma */
		if(accyear  == null || accyear  == ""){
			
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Academic Year.");
			$("#academicYear").focus();
			document.getElementById("accyear").style.border = "1px solid #AF2C2C";
			document.getElementById("accyear").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				document.getElementById("accyear").style.border = "1px solid #ccc";
				document.getElementById("accyear").style.backgroundColor = "#fff";
			}, 500);
			saveFlag=false;
			return false;

		}else if(locationId == 'all' || locationId  == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required School Name.");
			$("#location").focus();
			document.getElementById("location").style.border = "1px solid #AF2C2C";
			document.getElementById("location").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				document.getElementById("location").style.border = "1px solid #ccc";
				document.getElementById("location").style.backgroundColor = "#fff";
			}, 500);
			saveFlag=false;
			return false;
		}else if(classId == 'all' || classId  == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required School Name.");
			$("#class").focus();
			document.getElementById("class").style.border = "1px solid #AF2C2C";
			document.getElementById("class").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				document.getElementById("class").style.border = "1px solid #ccc";
				document.getElementById("class").style.backgroundColor = "#fff";
			}, 500);
			saveFlag=false;
			return false;
		}else if(chkArray.length == 0){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Scholastic Areas.");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			saveFlag=false;
			return false;
		}else if(saveFlag){
			//alert(reportaccyear);
			if(reportaccyear != undefined){
				window.location.href = 'reportaction.html?method=downloadAcademicYearReportCard&classId='+classId+'&locationId='+locationId+'&accyearId='+accyear+'&terms='+reportaccyear+'&examstypeid='+examstypeidaccyear+'&stdId='+stdId+'&sectionId='+sectionId;
			}else{
				window.location.href = 'reportaction.html?method=downloadReportCard&classId='+classId+'&locationId='+locationId+'&accyearId='+accyear+'&terms1='+checkterm1+'&terms2='+checkterm2+'&examstypeid='+examstypeid+'&examstypeidterm2='+examstypeidterm2+'&stdId='+stdId+'&sectionId='+sectionId;
			}
		}
	});

	$("#class").change(function(){
		getTerm1Exams($("#accyear").val());
		getTerm2Exams($("#accyear").val());
		getFinalExams($("#accyear").val());
	});
	/*$("#section").change(function(){
		var classId=$("#class").val();
		var accyear=$("#accyear").val();
		var locationId=$("#location").val();
		var sectionid=$("#section").val();
		getStudentListBySection(locationId,accyear,classId,sectionid);
	});*/
	
	$("#search").click(function(){
		var classId=$("#class").val();
		var accyear=$("#accyear").val();
		var locationId=$("#location").val();
		var sectionid=$("#section").val();
		getStudentListBySection(locationId,accyear,classId,sectionid);
	});

});

function getClassList(){
	var locationid=$("#location").val();
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

			$('#class').html("");

			$('#class').append('<option value="all">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#class').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function getTerm1Exams(accyear){
	var classId=$("#class").val();
	var locationid=$("#location").val();
	datalist={
			"accyear" : accyear,
			"classId" : classId,
			"locationid" : locationid,
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getTerm1Exams",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			alert(result.examsid);
			$("#term1").val(result.examsid);
			$("#examstypeid").val(result.examstypeid);
			$("#examstypeprefix").val(result.examstypeprefix);
		}
	});

}

function getTerm2Exams(accyear){
	var classId=$("#class").val();
	var locationid=$("#location").val();
	datalist={
			"accyear" : accyear,
			"classId" : classId,
			"locationid" : locationid,
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getTerm2Exams",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#term2").val(result.examsid);
			$("#examstypeidterm2").val(result.examstypeid);
			$("#examstypeprefixforterm2").val(result.examstypeprefix);
		}
	});

}

function getFinalExams(accyear){
	var classId=$("#class").val();
	var locationid=$("#location").val();
	datalist={
			"accyear" : accyear,
			"classId" : classId,
			"locationid" : locationid,
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getFinalExams",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#reportaccyear").val(result.examsid);
			$("#examstypeidaccyear").val(result.examstypeid);
			$("#examstypeprefix").val(result.examstypeprefix);
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
							+"<td><input type='radio' name='selectBox' class='stidentid' value='"+result.getClassWiseList[i].studentId+"'></td>" 
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
							+"<td> <input type='radio' name='selectBox' class='stidentid' value='"+result.getSectionWiseList[i].studentId+"'></td>" 
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
		}
	});
}

