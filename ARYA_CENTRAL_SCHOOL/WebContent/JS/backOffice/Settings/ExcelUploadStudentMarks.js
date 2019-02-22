
function disableF5(e) { if ((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82) e.preventDefault(); };

$(document).ready(function(){
	$("#accyear").val($("#hacademicyaer").val());
	$("#accyear1").val($("#hacademicyaer").val());
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(pageUrl=="uploadStudentXSL.html?method=uploadStudentExamDetails_byExcel"){
		if($(".errormessagediv span").text()==""){
			$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
			setTimeout(function(){
				/*window.location.href="adminMenu.html?method=locationList";*/
			},3000);
		}
		if($(".errormessagediv span").text()!=""){
			$(document).on("keydown", disableF5);
			setTimeout(function(){
				$(".errormessagediv").empty();
			},3000);
		}
	}


	if(pageUrl=="settingXLUpload.html?method=uploadStudentExamDetails_byExcel"){
		$("#saveid").hide();
	}

	$("#exam").change(function(){
		$("#saveid").show();
	});
	
	$("#downloadid").click(function(){
		var locationId=$("#location").val();
		var accyearId=$("#accyear").val();
		var classId=$("#class").val();
		var sectionId=$("#section").val();
		var examname=$("#examname").val();
		var subjectid=$("#subjectid").val();
		var examdetailsval=$("#examname").val().split(",");
		var examid=examdetailsval[0];
		var downloadFlag=true;
		
		if(locationId == "all" || locationId == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required School Name.");
			$("#location").focus();
			document.getElementById("location").style.border = "1px solid #AF2C2C";
			document.getElementById("location").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("location").style.border = "1px solid #ccc";
				document.getElementById("location").style.backgroundColor = "#fff";
			}, 500);
			downloadFlag=false;
			return false;
		}else if(accyearId == null || accyearId == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Academic Year.");
			$("#accyear").focus();
			document.getElementById("accyear").style.border = "1px solid #AF2C2C";
			document.getElementById("accyear").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("accyear").style.border = "1px solid #ccc";
				document.getElementById("accyear").style.backgroundColor = "#fff";
			}, 500);
			downloadFlag=false;
			return false;
		}else if(classId == "all" || classId == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Class.");
			$("#class").focus();
			document.getElementById("class").style.border = "1px solid #AF2C2C";
			document.getElementById("class").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("class").style.border = "1px solid #ccc";
				document.getElementById("class").style.backgroundColor = "#fff";
			}, 500);
			downloadFlag=false;
			return false;
		}else if(sectionId == "all" || sectionId == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Division.");
			$("#section").focus();
			document.getElementById("section").style.border = "1px solid #AF2C2C";
			document.getElementById("section").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("section").style.border = "1px solid #ccc";
				document.getElementById("section").style.backgroundColor = "#fff";
			}, 500);
			downloadFlag=false;
			return false;
		}else if(examname == null || examname == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Exam Name.");
			$("#examname").focus();
			document.getElementById("examname").style.border = "1px solid #AF2C2C";
			document.getElementById("examname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("examname").style.border = "1px solid #ccc";
				document.getElementById("examname").style.backgroundColor = "#fff";
			}, 500);
			downloadFlag=false;
			return false;
		}else if(subjectid == null || subjectid == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Subject Name.");
			$("#subjectid").focus();
			document.getElementById("subjectid").style.border = "1px solid #AF2C2C";
			document.getElementById("subjectid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("subjectid").style.border = "1px solid #ccc";
				document.getElementById("subjectid").style.backgroundColor = "#fff";
			}, 500);
			downloadFlag=false;
			return false;
		}else if(downloadFlag){
			window.location.href = 'settingXLUpload.html?method=downloadClassWiseExcelUploadFormat&locationId='+locationId+'&accyearId='+accyearId+'&classId='+classId+'&sectionId='+sectionId+'&examid='+examid+'&subjectid='+subjectid;
		}
	});
	$("#downloadid1").click(function(){
		var locationId=$("#location1").val();
		var accyearId=$("#accyear1").val();
		var classId=$("#class1").val();
		var sectionId=$("#section1").val();
		var examname=$("#examname1").val();
		var examdetailsval=$("#examname1").val().split(",");
		var examid=examdetailsval[0];
		var downloadFlag=true;
		
		if(locationId == "all" || locationId == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required School Name.");
			$("#location1").focus();
			document.getElementById("location1").style.border = "1px solid #AF2C2C";
			document.getElementById("location1").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("location1").style.border = "1px solid #ccc";
				document.getElementById("location1").style.backgroundColor = "#fff";
			}, 500);
			downloadFlag=false;
			return false;
		}else if(accyearId == null || accyearId == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Academic Year.");
			$("#accyear1").focus();
			document.getElementById("accyear1").style.border = "1px solid #AF2C2C";
			document.getElementById("accyear1").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("accyear1").style.border = "1px solid #ccc";
				document.getElementById("accyear1").style.backgroundColor = "#fff";
			}, 500);
			downloadFlag=false;
			return false;
		}else if(classId == "all" || classId == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Class.");
			$("#class1").focus();
			document.getElementById("class1").style.border = "1px solid #AF2C2C";
			document.getElementById("class1").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("class1").style.border = "1px solid #ccc";
				document.getElementById("class1").style.backgroundColor = "#fff";
			}, 500);
			downloadFlag=false;
			return false;
		}else if(sectionId == "all" || sectionId == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Division.");
			$("#section1").focus();
			document.getElementById("section1").style.border = "1px solid #AF2C2C";
			document.getElementById("section1").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("section1").style.border = "1px solid #ccc";
				document.getElementById("section1").style.backgroundColor = "#fff";
			}, 500);
			downloadFlag=false;
			return false;
		}else if(examname == null || examname == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Exam Name.");
			$("#examname1").focus();
			document.getElementById("examname1").style.border = "1px solid #AF2C2C";
			document.getElementById("examname1").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("examname1").style.border = "1px solid #ccc";
				document.getElementById("examname1").style.backgroundColor = "#fff";
			}, 500);
			downloadFlag=false;
			return false;
		}else if(downloadFlag){
			window.location.href = 'settingXLUpload.html?method=downloadClassWiseCoScholasticAreaExcelUploadFormat&locationId='+locationId+'&accyearId='+accyearId+'&classId='+classId+'&sectionId='+sectionId+'&examid='+examid;
		}
	});

	$("#saveid").click(function(){
		if($("#exam").val()==""){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Excel File");
			$("#exam").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			return true;
		}


		var filename = $("#exam").val().split(".").pop().toLowerCase();
		var fileNameCheck=$("#exam").val();

		if(fileNameCheck==""){
			$(".validateTips").text("Select File");
			$(".errormessagediv").show();
			$("#exam").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			return false;
		}else if(filename !="xlsx"){
			$(".validateTips").text("Select Only Excel file with .xlsx extension.");
			$(".errormessagediv").show();

			$("#studentfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			return false;
		}
		else{
			document.getElementById("excelfileupload").submit();
		}


		if(filename=="xlsx" || filename=="xls"){
			$("#Add").val("xls");
			$(".errormessagediv").hide();
			$("#exam").css({'backgroundColor' : 'transparent','border':'1px solid #ccc'});
			return true;
		}else{
			$(".errormessagediv").show();
			$(".validateTips").text("Select Excel File Only");
			$("#exam").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			return false;
		}
	});
	
	$("#accyear").change(function(){
		getExamNameList();
		getExamDetails();
	});
	$("#location").change(function(){
		getClassList();
		getSectionList();
		getSubject();
		getExamNameList();
		getExamDetails();
	});
	
	$("#class").change(function(){
		getSectionList();
		getSubject();
		getExamNameList();
		getExamDetails();
	});
	$("#examname").change(function(){
		getExamDetails();
	});
	$("#accyear1").change(function(){
		getExamNameList1();
		getExamDetails1();
	});
	$("#location1").change(function(){
		getClassList1();
		getSectionList1();
		getExamNameList1();
		getExamDetails1();
	});
	
	$("#class1").change(function(){
		getSectionList1();
		getExamNameList1();
		getExamDetails1();
	});
	$("#examname1").change(function(){
		getExamDetails1();
	});
});

function getExamDetails(){
	var examdetails=$("#examname").val().split(",");
	$("#examcode").val(examdetails[1]);
	$("#examstartdate").val(examdetails[2]);
	$("#examenddate").val(examdetails[3]);
}
function getExamDetails1(){
	var examdetails=$("#examname1").val().split(",");
	$("#examcode1").val(examdetails[1]);
	$("#examstartdate1").val(examdetails[2]);
	$("#examenddate1").val(examdetails[3]);
}
function getSectionList(){
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
}
function getSectionList1(){
	var classId=$("#class1").val();
	var accyear=$("#accyear1").val();
	var locationId=$("#location1").val();
	
	$.ajax({
		type : "GET",
		url : "reportaction.html?method=getSectionByClass",
		data : {"classId":classId,
			"location":locationId},
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				
				$("#section1").html("");
				$("#section1").append('<option value="all">' + "ALL"	+ '</option>');
				
				for (var j = 0; j < result.SectionList.length; j++) {
					
					$("#section1").append('<option value="' + result.SectionList[j].sectionId + '">' + result.SectionList[j].sectionname + '</option>');
				}
			}
	});
}
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

function getClassList1(){
	var locationid=$("#location1").val();
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

			$('#class1').html("");

			$('#class1').append('<option value="all">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#class1').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function getExamNameList(){
	var locationid=$("#location").val();
	var classid=$("#class").val();
	var accyear=$("#accyear").val();
	datalist={
			"locationid" : locationid,
			"classid" : classid,
			"accyear" : accyear,
	},
	$.ajax({
		type : 'POST',
		url : "examTimetablePath.html?method=getExamNameList",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#examname').html("");
			
			$('#examname').append('<option value="">' +"----------Select----------"+ '</option>');
			
			for ( var j = 0; j < result.ExamNameList.length; j++) {
				
				$('#examname').append('<option value="'+ result.ExamNameList[j].examid + ','+ result.ExamNameList[j].examcode + ','+ result.ExamNameList[j].examstartdate + ','+ result.ExamNameList[j].examenddate + ','+ result.ExamNameList[j].examtypeid + ','+ result.ExamNameList[j].examtypename + '">'
						
						+ result.ExamNameList[j].examName + '</option>');
			}
		}
	});
}
function getExamNameList1(){
	var locationid=$("#location1").val();
	var classid=$("#class1").val();
	var accyear=$("#accyear1").val();
	datalist={
			"locationid" : locationid,
			"classid" : classid,
			"accyear" : accyear,
	},
	$.ajax({
		type : 'POST',
		url : "examTimetablePath.html?method=getExamNameList1",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#examname1').html("");
			
			$('#examname1').append('<option value="">' +"----------Select----------"+ '</option>');
			
			for ( var j = 0; j < result.ExamNameList.length; j++) {
				
				$('#examname1').append('<option value="'+ result.ExamNameList[j].examid + ','+ result.ExamNameList[j].examcode + ','+ result.ExamNameList[j].examstartdate + ','+ result.ExamNameList[j].examenddate + ','+ result.ExamNameList[j].examtypeid + ','+ result.ExamNameList[j].examtypename + '">'
						
						+ result.ExamNameList[j].examName + '</option>');
			}
		}
	});
}

function getSubject() { 
	var classid=$("#class").val();
	$.ajax({
		type : "GET",
		url : "teacherregistration.html?method=getSubject",
		data : {
			"classidVal" : classid
		},
		async : false,

		success : function(data) {
			var result = $.parseJSON(data); 
		
			$("#subjectid").empty();
			$("#subjectid").append('<option value="' + "" + '">' + "----------Select----------"+ '</option>');

			for ( var i = 0; i < result.subjectList.length; i++) {

				$("#subjectid").append('<option value="' + result.subjectList[i].subjectid + '">' + result.subjectList[i].subjectname + '</option>');

			}

		}
	});

}
