$(document).ready(function(){	
	
	$("#accyear").val($("#hacademicyaer").val());
	$("#allstudent").hide();
	$(".selecteditems").hide();
	$("#genderName").hide();
	$("#gender").hide();

	if($("#hideenId").val()!="" && $("#hideenId").val()!=undefined ){

		$(".selecteditems").show(1000);
		$("#allstudent").show(1000);
		$("#txtstyle, #txtstyle").slideToggle();
	}
	
	$("#location").change(function(){
		getClassList();
		
	});
	
	$("#selection").change(function() {
		if($("#selection").val() == 'stulistgenwise'){
			$("#genderName").show();
			$("#gender").show();
		}else{
			$("#genderName").hide();
			$("#gender").hide();
		}
		

	});
	
	$("#search").click(function(){

		var accyear=$("#accyear").val();
		var selection=$("#selection").val();
		var classId=$("#class").val();
		var section=$("#section").val();
		var location =$("#location").val();
		

		if(accyear == "all"){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Academic Year");
			document.getElementById("abbreviate").style.border = "1px solid #AF2C2C";
			document.getElementById("abbreviate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}
		
		else if(location == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Select School Name");
			document.getElementById("abbreviate").style.border = "1px solid #AF2C2C";
			document.getElementById("abbreviate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}
		
    /* else if(classId == "all"){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Class");
			document.getElementById("abbreviate").style.border = "1px solid #AF2C2C";
			document.getElementById("abbreviate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}
		
     else if(section == "all"){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Section");
			document.getElementById("abbreviate").style.border = "1px solid #AF2C2C";
			document.getElementById("abbreviate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}*/
		
		else if(selection == "all"){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Report Type");
			document.getElementById("abbreviate").style.border = "1px solid #AF2C2C";
			document.getElementById("abbreviate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}

		else if(selection == 'studob'){
			getstudentDOBWise(accyear,selection,classId,section,location);
		}
		else if(selection == "stufatheroccu"){
			getstudentfatheroccu(accyear,selection,classId,section,location);
		}
		else if(selection == "stumotheroccu"){
			getstudentMotherOccuWise(accyear,selection,classId,section,location);
		}
		else if(selection == "sturelig"){
			getstudentDetailsReligionWise(accyear,selection,classId,section,location);
		}
		else if(selection == "stulcatwise"){
			getstudentCategoryWise(accyear,selection,classId,section,location);
		}
		else if(selection == "stulistadmission"){
			getstudentAdmissionWise(accyear,selection,classId,section,location);
		}
		else if(selection == "stulistrollwise"){
			getstudentRollNoWise(accyear,selection,classId,section,location);
		}
		
		else if(selection == "stulisthousewise"){
			getstudentHouseWise(accyear,selection,classId,section,location);
		}
		else if(selection == "stulistalpha"){
			getstudentAlphaWise(accyear,selection,classId,section,location);
		}
		else if(selection == "stuparlist"){
			getstudentParentWise(accyear,selection,classId,section,location);
		}

		else if(selection == "stulist"){
			getstudentList(accyear,selection,classId,section,location);
		}

		else if(selection == "stustanwise"){
			getstudentStandardWise(accyear,selection,classId,section,location);
		}
		else if(selection == "stuconde"){
			getstudentContactDetails(accyear,selection,classId,section,location);
		}
		
		else if(selection == "studept"){
			getstudentDepartmentList(accyear,selection,classId,section,location);
		}
		
		else if(selection == "busroutewise"){
			getstudentBusRouteWise(accyear,selection,classId,section,location);
		}
		
		else if(selection == "stuoptsub"){
			getstudentOptionalSubjectDetails(accyear,selection,classId,section,location);
		}
		
		else if(selection == "stuphonnum"){
			getstudentsWithPhoneNumbers(accyear,selection,classId,section,location);
		}
		else if(selection == "stuwithheld"){
			getstudentsWithheld(accyear,selection,classId,section,location);
		}
		
		else if(selection == "oldstulist"){
			getOldStudentsList(accyear,selection,classId,section,location);
		}
		
		else if(selection == "stustrngth"){
			getStudentsStrength(accyear,selection,classId,section,location);
		}
		
		else if(selection == "newadmlist"){
			getStudentsNewAdmissionList(accyear,selection,classId,section,location);
		}
		
		else if(selection == "newtempadmlist"){
			getStudentsTempNewAdmissionList(accyear,selection,classId,section,location);
		}
		else if(selection == "prmlist"){
			getStudentPromotionList(accyear,selection,classId,section,location);
		}
		
		else if(selection == "stulistgenwise"){
			$("#genderName").show();
			$("#gender").show();
			var gender =$("#gender").val();
			if(gender == "all"){
				$(".errormessagediv").show();
				$(".validateTips").text("Select Gender Type");
				document.getElementById("abbreviate").style.border = "1px solid #AF2C2C";
				document.getElementById("abbreviate").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 2000);
				return false;
			}
			else{
			getStudentListGenderWise(accyear,selection,classId,section,location,gender);
			}
		}
		
	});

	$("#excelDownload").click(function(){

		var selection = $("#selection").val();
		var report = "excel";
		if(selection == "studob"){
			window.location.href = 'reportaction.html?method=getstudentDOBWiseXL';
		}
		else if(selection == "stufatheroccu"){
			window.location.href = 'reportaction.html?method=getstudentFatherOccuWiseXL';
		}
		else if(selection == "stumotheroccu"){
			window.location.href = 'reportaction.html?method=getstudentMotherOccuWiseXL';
		}

		else if(selection == "sturelig"){
			window.location.href = 'reportaction.html?method=studentReligionWiseXL';
		}

		else if(selection == "stulcatwise"){
			window.location.href = 'reportaction.html?method=getstudentCategoryWiseXL';
		}
		else if(selection == "stulist"){
			window.location.href = 'reportaction.html?method=getstudentListXL';
		}

		else if(selection == "stuparlist"){
			window.location.href = 'reportaction.html?method=getstudentParentWiseXL';
		}

		else if(selection == "stustanwise"){
			window.location.href = 'reportaction.html?method=getstudentStandardWiseXL';
		}
		
		else if(selection == "stuconde"){
			window.location.href = 'reportaction.html?method=getstudentContactDetailsXL';
		}
		
		else if(selection == "studept"){
			window.location.href = 'reportaction.html?method=getstudentDepartmentListXL';
		}
		
		else if(selection == "busroutewise"){
			window.location.href = 'reportaction.html?method=getstudentBusRouteWiseXL';
		}
		
		else if(selection == "stuoptsub"){
			window.location.href = 'reportaction.html?method=getstudentOptionalSubjectDetailsXL';
		}
		
		else if(selection == "stuphonnum"){
			window.location.href = 'reportaction.html?method=getstudentWithPhoneNumbersXL';
		}
		
		else if(selection == "stuwithheld"){
			window.location.href = 'reportaction.html?method=getstudentsWithheldXL';
		}
		else if(selection == "oldstulist"){
			window.location.href = 'reportaction.html?method=getOLdStudentsListXL';
		}
		
		else if(selection == "stustrngth"){
			window.location.href = 'reportaction.html?method=getStudentsStrengthXL';
		}
		
		else if(selection == "newadmlist"){
			window.location.href = 'reportaction.html?method=getStudentsNewAdmissionListXL';
		}
		else if(selection == "newtempadmlist"){
			window.location.href = 'reportaction.html?method=getStudentsTempNewAdmissionListXL';
		}
		else if(selection == "prmlist"){
			window.location.href = 'reportaction.html?method=getStudentPromotionListXL';
		}
		
		else if(selection == "stulistgenwise"){
			window.location.href = 'reportaction.html?method=getStudentListGenderWiseXL';
		}
		else if(selection == "stulisthousewise"){
			window.location.href = 'reportaction.html?method=getstudentHouiseWiseXL';
		}
		
		else if(selection == "stulistrollwise"){
				window.location.href="reportaction.html?method=getstudentListRollNoWise&accyear="+$("#accyear").val()+
				"&classId="+$("#class").val()+"&section="+$("#section").val()+"&location="+$("#location").val()+"&name="+$("#accyear option:selected").text()+"&locName="+$("#location option:selected").text()+
				"&classname="+$("#class option:selected").text()+"&sectionnname="+$("#section option:selected").text()+"&report="+report; 
		}
		else if(selection == "stulistalpha"){
			window.location.href="reportaction.html?method=getstudentListAlphaWise&accyear="+$("#accyear").val()+
			"&classId="+$("#class").val()+"&section="+$("#section").val()+"&location="+$("#location").val()+"&name="+$("#accyear option:selected").text()+"&locName="+$("#location option:selected").text()+
			"&classname="+$("#class option:selected").text()+"&sectionnname="+$("#section option:selected").text()+"&report="+report; 
		}
		else if(selection == "stulistadmission"){
			window.location.href="reportaction.html?method=getstudentListAdmisNoWise&accyear="+$("#accyear").val()+
			"&classId="+$("#class").val()+"&section="+$("#section").val()+"&location="+$("#location").val()+"&name="+$("#accyear option:selected").text()+"&locName="+$("#location option:selected").text()+
			"&classname="+$("#class option:selected").text()+"&sectionnname="+$("#section option:selected").text()+"&report="+report; 
	}

	});

	$("#pdfDownload").click(function(){

		var selection = $("#selection").val();
		var report = "pdf";

		if(selection == "studob"){
			window.location.href = 'reportaction.html?method=studentDOBWisePDFReport';
		}
		else if(selection == "stufatheroccu"){
			window.location.href = 'reportaction.html?method=getstudentFatherOccuWisePDFReport';
		}
		else if(selection == "stumotheroccu"){
			window.location.href = 'reportaction.html?method=getstudentMotherOccuWisePDFReport';			  
		}
		else if(selection == "sturelig"){
			window.location.href = 'reportaction.html?method=studentReligionWisePDFReport';			  
		}
		else if(selection == "stulcatwise"){
			window.location.href = 'reportaction.html?method=getstudentCategoryPDFReport';			  
		}
		else if(selection == "stulisthousewise"){
			window.location.href = 'reportaction.html?method=getstulisthousewisePDFReport';
		}
		
		else if(selection == "stulistrollwise"){
			window.location.href="reportaction.html?method=getstudentListRollNoWise&accyear="+$("#accyear").val()+
			"&classId="+$("#class").val()+"&section="+$("#section").val()+"&location="+$("#location").val()+"&name="+$("#accyear option:selected").text()+"&locName="+$("#location option:selected").text()+
			"&classname="+$("#class option:selected").text()+"&sectionnname="+$("#section option:selected").text()+"&report="+report; 
		}
		else if(selection == "stulistalpha"){
			window.location.href="reportaction.html?method=getstudentListAlphaWise&accyear="+$("#accyear").val()+
			"&classId="+$("#class").val()+"&section="+$("#section").val()+"&location="+$("#location").val()+"&name="+$("#accyear option:selected").text()+"&locName="+$("#location option:selected").text()+
			"&classname="+$("#class option:selected").text()+"&sectionnname="+$("#section option:selected").text()+"&report="+report; 
		}
		else if(selection == "stulistadmission"){
			window.location.href="reportaction.html?method=getstudentListAdmisNoWise&accyear="+$("#accyear").val()+
			"&classId="+$("#class").val()+"&section="+$("#section").val()+"&location="+$("#location").val()+"&name="+$("#accyear option:selected").text()+"&locName="+$("#location option:selected").text()+
			"&classname="+$("#class option:selected").text()+"&sectionnname="+$("#section option:selected").text()+"&report="+report; 
	}
		else if(selection == "stuparlist"){
			window.location.href = 'reportaction.html?method=getstudentParentWisePDFReport';			  
		}

		else if(selection == "stulist"){
			window.location.href = 'reportaction.html?method=getstudentListPDFReport';			  
		}

		else if(selection == "stustanwise"){
			window.location.href = 'reportaction.html?method=getstudentStandardWisePDFReport';			  
		}
		
		else if(selection == "stuconde"){
			window.location.href = 'reportaction.html?method=getstudentContactDetailsPDFReport';			  
		}
		else if(selection == "studept"){
			window.location.href = 'reportaction.html?method=getstudentDepartmentListPDFReport';			  
		}
		
		else if(selection == "busroutewise"){
			window.location.href = 'reportaction.html?method=getstudentBusRouteWisePDFReport';			  
		}
		
		else if(selection == "stuoptsub"){
			window.location.href = 'reportaction.html?method=getstudentOptionalSubjectDetailsPDFReport';
		}
		
		else if(selection == "stuphonnum"){
			window.location.href = 'reportaction.html?method=getstudentWithPhoneNumbersPDFReport';
		}
		else if(selection == "stuwithheld"){
			window.location.href = 'reportaction.html?method=getstudentWithheldPDFReport';
		}
		else if(selection == "oldstulist"){
			window.location.href = 'reportaction.html?method=getOldStudentsListPDFReport';
		}
		
		else if(selection == "stustrngth"){
			window.location.href = 'reportaction.html?method=getStudentsStrengthPDFReport';
		}
		
		else if(selection == "newadmlist"){
			window.location.href = 'reportaction.html?method=getStudentsNewAdmissionListPDFReport';
		}
		else if(selection == "newtempadmlist"){
			window.location.href = 'reportaction.html?method=getStudentsTempNewAdmissionListPDFReport';
		}
		
		else if(selection == "prmlist"){
			window.location.href = 'reportaction.html?method=getStudentPromotionListPDFReport';
		}
		
		else if(selection == "stulistgenwise"){
			alert(Vidya);
			window.location.href = 'reportaction.html?method=getStudentListGenderWisePDFReport';
		}
		
		
	});

	$("#class").change(function(){

		var classId=$("#class").val();
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

function getstudentDOBWise(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentDOBWise",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>DOB</th>"+
					"</tr>" +
					"</table>"
			);
			if(result.studentdobList.length>0){
			for(var i=0;i<result.studentdobList.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentdobList[i].count+"</td>"+
						"<td>"+result.studentdobList[i].admissionNo+"</td>"+
						"<td>"+result.studentdobList[i].studentRollNo+"</td>"+
						"<td>"+result.studentdobList[i].studentnamelabel+"</td>"+
						"<td>"+result.studentdobList[i].dob+"</td>"
						+"</tr>"

				);
			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
		}
	});
}	

function getstudentfatheroccu(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentFatherOccuWise",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Father's Name</th>"+
					"<th>Father's Occupation</th>"+
					"</tr>" +
					"</table>"
			);
			if(result.studentfatheroccuList.length>0){
			for(var i=0;i<result.studentfatheroccuList.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentfatheroccuList[i].count+"</td>"+
						"<td>"+result.studentfatheroccuList[i].admissionNo+"</td>"+
						"<td>"+result.studentfatheroccuList[i].studentRollNo+"</td>"+
						"<td>"+result.studentfatheroccuList[i].studentnamelabel+"</td>"+
						"<td>"+result.studentfatheroccuList[i].fatherName+"</td>"+
						"<td>"+result.studentfatheroccuList[i].occupation+"</td>"
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
			
		}

	});
}

function getstudentMotherOccuWise(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentMotherOccuWise",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Mother's Name</th>"+
					"<th>Mother's Occupation</th>"+
					"</tr>" +
					"</table>"
			);
			if(result.studentmotheroccuList.length>0){
			for(var i=0;i<result.studentmotheroccuList.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentmotheroccuList[i].count+"</td>"+
						"<td>"+result.studentmotheroccuList[i].admissionNo+"</td>"+
						"<td>"+result.studentmotheroccuList[i].studentRollNo+"</td>"+
						"<td>"+result.studentmotheroccuList[i].studentnamelabel+"</td>"+
						"<td>"+result.studentmotheroccuList[i].student_mothername_var+"</td>"+
						"<td>"+result.studentmotheroccuList[i].occupation+"</td>"
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
		}

	});
}

function getstudentDetailsReligionWise(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentDetailsReligionWise",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Religion</th>"+
					"<th>Caste</th>"+
					"</tr>" +
					"</table>"
			);
			if(result.studentReligionWiseList.length>0){
			for(var i=0;i<result.studentReligionWiseList.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentReligionWiseList[i].count+"</td>"+
						"<td>"+result.studentReligionWiseList[i].admissionNo+"</td>"+
						"<td>"+result.studentReligionWiseList[i].studentRollNo+"</td>"+
						"<td>"+result.studentReligionWiseList[i].studentnamelabel+"</td>"+
						"<td>"+result.studentReligionWiseList[i].religion+"</td>"+
						"<td>"+result.studentReligionWiseList[i].caste+"</td>"
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
			
		}

	});
}

function getstudentCategoryWise(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentCategoryWise",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Religion</th>"+
					"<th>Caste</th>"+
					"<th>Category</th>"+
					"</tr>" +
					"</table>"
			);
			if(result.studentCategoryWiseList.length>0){
			for(var i=0;i<result.studentCategoryWiseList.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentCategoryWiseList[i].count+"</td>"+
						"<td>"+result.studentCategoryWiseList[i].admissionNo+"</td>"+
						"<td>"+result.studentCategoryWiseList[i].studentRollNo+"</td>"+
						"<td>"+result.studentCategoryWiseList[i].studentnamelabel+"</td>"+
						"<td>"+result.studentCategoryWiseList[i].religion+"</td>"+
						"<td>"+result.studentCategoryWiseList[i].caste+"</td>"+
						"<td>"+result.studentCategoryWiseList[i].casteCategory+"</td>"
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
		}

	});
}

function getstudentParentWise(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentParentWise",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Father's Name</th>"+
					"<th>Mobile Number</th>"+
					"<th>Mother's Name</th>"+
					"<th>Mobile Number</th>"+
					"<th>Address</th>"+
					"</tr>" +
					"</table>"
			);
			if(result.studentParentList.length>0){
			for(var i=0;i<result.studentParentList.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentParentList[i].count+"</td>"+
						"<td>"+result.studentParentList[i].admissionNo+"</td>"+
						"<td>"+result.studentParentList[i].studentRollNo+"</td>"+
						"<td>"+result.studentParentList[i].studentnamelabel+"</td>"+
						"<td>"+result.studentParentList[i].fatherName+"</td>"+
						"<td>"+result.studentParentList[i].mobileno+"</td>"+
						"<td>"+result.studentParentList[i].student_mothername_var+"</td>"+
						"<td>"+result.studentParentList[i].student_mothermobileno_var+"</td>"+
						"<td>"+result.studentParentList[i].address+"</td>"
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
			
		}

	});
}

function getstudentList(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentList",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"</tr>" +
					"</table>"
			);
			if(result.getstudentDetailsList.length>0){
			for(var i=0;i<result.getstudentDetailsList.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.getstudentDetailsList[i].count+"</td>"+
						"<td>"+result.getstudentDetailsList[i].admissionNo+"</td>"+
						"<td>"+result.getstudentDetailsList[i].studentRollNo+"</td>"+
						"<td>"+result.getstudentDetailsList[i].studentnamelabel+"</td>"
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
			
		}

	});
}

function getstudentStandardWise(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentStandardWise",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Gender</th>"+
					"<th>Religion</th>"+
					"<th>Caste</th>"+
					"<th>Category</th>"+
					"<th>DOB</th>"+
					"</tr>" +
					"</table>"
			);
			if(result.studentStandardList.length>0){
			for(var i=0;i<result.studentStandardList.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentStandardList[i].count+"</td>"+
						"<td>"+result.studentStandardList[i].admissionNo+"</td>"+
						"<td>"+result.studentStandardList[i].studentRollNo+"</td>"+
						"<td>"+result.studentStandardList[i].studentnamelabel+"</td>"+
						"<td>"+result.studentStandardList[i].student_gender_var+"</td>"+
						"<td>"+result.studentStandardList[i].religion+"</td>"+
						"<td>"+result.studentStandardList[i].caste+"</td>"+
						"<td>"+result.studentStandardList[i].casteCategory+"</td>"+
						"<td>"+result.studentStandardList[i].dob+"</td>"
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
			
		}

	});
}	

function getstudentContactDetails(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentContactDetails",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Address</th>" +
					"<th>Father's Name</th>" +
					"<th>Mobile No </th>" +
					"<th>Mother's Name</th>" +
					"<th>Mobile No</th>" +
					"</tr>" +
					"</table>"
			);
			if(result.studentContactDetails.length>0){
			for(var i=0;i<result.studentContactDetails.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentContactDetails[i].count+"</td>"+
						"<td>"+result.studentContactDetails[i].admissionNo+"</td>"+
						"<td>"+result.studentContactDetails[i].studentRollNo+"</td>"+
						"<td>"+result.studentContactDetails[i].studentnamelabel+"</td>"+
						"<td>"+result.studentContactDetails[i].address+"</td>"+
						"<td>"+result.studentContactDetails[i].fatherName+"</td>"+
						"<td>"+result.studentContactDetails[i].mobileno+"</td>"+
						"<td>"+result.studentContactDetails[i].student_mothername_var+"</td>"+
						"<td>"+result.studentContactDetails[i].student_mothermobileno_var+"</td>"
						+"</tr>"

				);

			}
		}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
		}

	});
}

function getstudentDepartmentList(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentDepartmentList",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"<th>Standard</th>" +
					"<th>Division</th>" +
					"<th>Department</th>" +
					"</tr>" +
					"</table>"
			);
			if(result.studentDepartmentList.length>0){
			for(var i=0;i<result.studentDepartmentList.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentDepartmentList[i].count+"</td>"+
						"<td>"+result.studentDepartmentList[i].admissionNo+"</td>"+
						"<td>"+result.studentDepartmentList[i].studentnamelabel+"</td>"+
						"<td>"+result.studentDepartmentList[i].classname+"</td>"+
						"<td>"+result.studentDepartmentList[i].sectionname+"</td>"+
						"<td>"+result.studentDepartmentList[i].specializationName+"</td>"+
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
		}

	});
}

function getstudentBusRouteWise(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentBusRouteWise",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Standard</th>" +
					"<th>Division</th>" +
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"<th>Bus Point Name</th>" +
					"<th>Amount</th>" +
					"</tr>" +
					"</table>"
			);
			if(result.studentBusRouteWise.length>0){
			for(var i=0;i<result.studentBusRouteWise.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentBusRouteWise[i].count+"</td>"+
						"<td>"+result.studentBusRouteWise[i].classname+"</td>"+
						"<td>"+result.studentBusRouteWise[i].sectionname+"</td>"+
						"<td>"+result.studentBusRouteWise[i].admissionNo+"</td>"+
						"<td>"+result.studentBusRouteWise[i].studentnamelabel+"</td>"+
						"<td>"+result.studentBusRouteWise[i].busStageName+"</td>"+
						"<td>"+result.studentBusRouteWise[i].stageAmount+"</td>"+
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
		}

	});
}

function getstudentOptionalSubjectDetails(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentOptionalSubjectDetails",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"<th>Class</th>"+
					"<th>Second Language</th>" +
					"<th>Third Language</th>" +
					"</tr>" +
					"</table>"
			);
			if(result.studentOptionalSubject.length>0){
			for(var i=0;i<result.studentOptionalSubject.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentOptionalSubject[i].count+"</td>"+
						"<td>"+result.studentOptionalSubject[i].admissionNo+"</td>"+
						"<td>"+result.studentOptionalSubject[i].studentnamelabel+"</td>"+
						"<td>"+result.studentOptionalSubject[i].class_and_section+"</td>"+
						"<td>"+result.studentOptionalSubject[i].secondLanguage+"</td>"+
						"<td>"+result.studentOptionalSubject[i].thirdLanguage+"</td>"+
						+"</tr>"

				);
			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
			
		}

	});
}

function getstudentsWithPhoneNumbers(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentsWithPhoneNumbers",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>" +
					"<th>Name</th>"+
					"<th>Father's Name</th>" +
					"<th>Father's Mobile No</th>" +
					"<th>Mother's Name</th>" +
					"<th>Mother's Mobile No</th>" +
					"<th>Emergency Name</th>" +
					"<th>Emergency Contact No</th>" +
					"</tr>" +
					"</table>"
			);
			if(result.studentWithPhoneNumber.length>0){
			for(var i=0;i<result.studentWithPhoneNumber.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentWithPhoneNumber[i].count+"</td>"+
						"<td>"+result.studentWithPhoneNumber[i].admissionNo+"</td>"+
						"<td>"+result.studentWithPhoneNumber[i].studentRollNo+"</td>"+
						"<td>"+result.studentWithPhoneNumber[i].studentnamelabel+"</td>"+
						"<td>"+result.studentWithPhoneNumber[i].fatherName+"</td>"+
						"<td>"+result.studentWithPhoneNumber[i].fatherMobileNo+"</td>"+
						"<td>"+result.studentWithPhoneNumber[i].student_mothername_var+"</td>"+
						"<td>"+result.studentWithPhoneNumber[i].student_mothermobileno_var+"</td>"+
						"<td>"+result.studentWithPhoneNumber[i].guardianName+"</td>"+
						"<td>"+result.studentWithPhoneNumber[i].guardianMobileNo+"</td>"+
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
		}

	});
}

function getOldStudentsList(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getOldStudentsList",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Std & Div</th>" +
					"<th>Name</th>"+
					"<th>Gender</th>" +
					"<th>Tc No</th>" +
					"<th>Tc Date</th>" +
					"<th>Reason For Leaving</th>" +
					"<th>Last Attendance Date</th>" +
					"</tr>" +
					"</table>"
			);
			if(result.oldStudentList.length>0){
			for(var i=0;i<result.oldStudentList.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.oldStudentList[i].count+"</td>"+
						"<td>"+result.oldStudentList[i].admissionNo+"</td>"+
						"<td>"+result.oldStudentList[i].studentnamelabel+"</td>"+
						"<td>"+result.oldStudentList[i].fatherName+"</td>"+
						"<td>"+result.oldStudentList[i].fatherMobileNo+"</td>"+
						"<td>"+result.oldStudentList[i].student_mothername_var+"</td>"+
						"<td>"+result.oldStudentList[i].student_mothermobileno_var+"</td>"+
						"<td>"+result.oldStudentList[i].guardianName+"</td>"+
						"<td>"+result.oldStudentList[i].guardianMobileNo+"</td>"+
						+"</tr>"

				);

			}
		}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
			
		}

	});
}

function getStudentsStrength(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getStudentsStrength",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Class</th>"+
					"<th>Div</th>" +
					"<th>Girls</th>"+
					"<th>Boys</th>" +
					"<th>Total</th>" +
					"<th></th>" +
					"</tr>" +
					"</table>"
			);
			if(result.studentsStrength.length>0){
			for(var i=0;i<result.studentsStrength.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentsStrength[i].classname+"</td>"+
						"<td>"+result.studentsStrength[i].sectionname+"</td>"+
						"<td>"+result.studentsStrength[i].girls+"</td>"+
						"<td>"+result.studentsStrength[i].boys+"</td>"+
						"<td>"+result.studentsStrength[i].totalStudentsInDiv+"</td>"+
						"<td>"+result.studentsStrength[i].totalStudentsInCls+"</td>"+
						+"</tr>"

				);

			}
		}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
			
		}

	});
}

function getStudentsTempNewAdmissionList(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getStudentsTempNewAdmissionList",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>SI.No</th>"+
					"<th>Temp Admn No</th>" +
					"<th>Name</th>" +
					"<th>Standard</th>"+
					"<th>D.O.B</th>"+
					"<th>Father's Name</th>" +
					"<th>Mother's Name</th>" +
					"<th>Father's Occupation</th>" +
					"<th>Mother's Occupation</th>" +
					"</tr>" +
					"</table>"
			);
			if(result.studentNewAdmissionList.length){
			for(var i=0;i<result.studentNewAdmissionList.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentNewAdmissionList[i].count+"</td>"+
						"<td>"+result.studentNewAdmissionList[i].admissionNo+"</td>"+
						"<td>"+result.studentNewAdmissionList[i].studentnamelabel+"</td>"+
						"<td>"+result.studentNewAdmissionList[i].classname+"</td>"+
						"<td>"+result.studentNewAdmissionList[i].dob+"</td>"+
						"<td>"+result.studentNewAdmissionList[i].fatherName+"</td>"+
						"<td>"+result.studentNewAdmissionList[i].motherName+"</td>"+
						"<td>"+result.studentNewAdmissionList[i].fatherOccupation+"</td>"+
						"<td>"+result.studentNewAdmissionList[i].motherOccupation+"</td>"+
						+"</tr>"

				);

			}
		}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='9'>NO Records Found</td></tr>");
			}
			
		}

	});
}


function getStudentsNewAdmissionList(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getStudentsNewAdmissionList",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>SI.No</th>"+
					"<th>Admn No</th>" +
					"<th>Name</th>" +
					"<th>Standard</th>"+
					"<th>Division</th>" +
					"<th>Date Of Admission</th>" +
					"</tr>" +
					"</table>"
			);
			if(result.studentNewAdmissionList.length){
			for(var i=0;i<result.studentNewAdmissionList.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentNewAdmissionList[i].count+"</td>"+
						"<td>"+result.studentNewAdmissionList[i].admissionNo+"</td>"+
						"<td>"+result.studentNewAdmissionList[i].studentnamelabel+"</td>"+
						"<td>"+result.studentNewAdmissionList[i].classname+"</td>"+
						"<td>"+result.studentNewAdmissionList[i].sectionname+"</td>"+
						"<td>"+result.studentNewAdmissionList[i].dateOfJoining+"</td>"+
						+"</tr>"

				);

			}
		}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
			
		}

	});
}

function getStudentPromotionList(accyear,selection,classId,section,location){
	
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},
	
	$.ajax({
		
		type : "POST",
		url : "reportaction.html?method=getStudentPromotionList",
		data : datalist,
		
		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>SI.No</th>"+
					"<th>Roll No</th>" +
					"<th>Name</th>" +
					"<th>Result</th>"+
					"</tr>" +
					"</table>"
			);
			if(result.studentPromotionList.length>0){
			for(var i=0;i<result.studentPromotionList.length;i++){
				
				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentPromotionList[i].count+"</td>"+
						"<td>"+result.studentPromotionList[i].studentRollNo+"</td>"+
						"<td>"+result.studentPromotionList[i].studentnamelabel+"</td>"+
						"<td>"+result.studentPromotionList[i].promotionStatus+"</td>"+
						+"</tr>"
						
				);
				
			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
		}
	
	});
}

function getStudentListGenderWise(accyear,selection,classId,section,location,gender){
	
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"gender":gender,
	},
	
	$.ajax({
		
		type : "POST",
		url : "reportaction.html?method=getStudentListGenderWise",
		data : datalist,
		
		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>SI.No</th>"+
					"<th>Roll No</th>" +
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"</tr>" +
					"</table>"
			);
			if(result.studentListGenderWise.length>0){
			for(var i=0;i<result.studentListGenderWise.length;i++){
				
				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentListGenderWise[i].count+"</td>"+
						"<td>"+result.studentListGenderWise[i].studentRollNo+"</td>"+
						"<td>"+result.studentListGenderWise[i].admissionNo+"</td>"+
						"<td>"+result.studentListGenderWise[i].studentnamelabel+"</td>"+
						+"</tr>"
						
				);
				
			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
		}
	
	});
}


function getstudentAdmissionWise(accyear,selection,classId,section,location){
	
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},
	
	$.ajax({
		
		type : "POST",
		url : "reportaction.html?method=getStudentListAdmiWise",
		data : datalist,
		
		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>SI.No</th>"+
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"<th>Roll No</th>" +
					"<th>Class</th>" +
					"<th>Division</th>" +
					"</tr>" +
					"</table>"
			);
			if(result.stuList.length>0){
			for(var i=0;i<result.stuList.length;i++){
				
				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.stuList[i].sno+"</td>"+
						"<td>"+result.stuList[i].admissionNo+"</td>"+
						"<td>"+result.stuList[i].studentnamelabel+"</td>"+
						"<td>"+result.stuList[i].studentRollNo+"</td>"+
						"<td>"+result.stuList[i].classname+"</td>"+
						"<td>"+result.stuList[i].sectionname+"</td>"+
						+"</tr>"
				);
			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>No Records Found</td></tr>");
			}
		}
	});
}

function getstudentRollNoWise(accyear,selection,classId,section,location){
	
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},
	
	$.ajax({
		
		type : "POST",
		url : "reportaction.html?method=getstudentRollNoWise",
		data : datalist,
		
		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>SI.No</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>" +
					"<th>Name</th>"+
				
					"<th>Class</th>" +
					"<th>Division</th>" +
					"</tr>" +
					"</table>"
			);
			if(result.stuList.length>0){
			for(var i=0;i<result.stuList.length;i++){
				
				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.stuList[i].sno+"</td>"+
						"<td>"+result.stuList[i].admissionNo+"</td>"+
						"<td>"+result.stuList[i].studentRollNo+"</td>"+
						"<td>"+result.stuList[i].studentnamelabel+"</td>"+
					
						"<td>"+result.stuList[i].classname+"</td>"+
						"<td>"+result.stuList[i].sectionname+"</td>"+
						+"</tr>"
				);
			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>No Records Found</td></tr>");
			}
		}
	});
}
function getstudentHouseWise(accyear,selection,classId,section,location){
	
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},
	
	$.ajax({
		
		type : "POST",
		url : "reportaction.html?method=getstudentHouseWise",
		data : datalist,
		
		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>SI.No</th>"+
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"<th>Roll No</th>" +
					"<th>Class</th>" +
					"<th>Division</th>" +
					"<th>House</th>" +
					"</tr>" +
					"</table>"
			);
			if(result.stuList.length>0){
			for(var i=0;i<result.stuList.length;i++){
				
				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.stuList[i].sno+"</td>"+
						"<td>"+result.stuList[i].admissionNo+"</td>"+
						"<td>"+result.stuList[i].studentnamelabel+"</td>"+
						"<td>"+result.stuList[i].studentRollNo+"</td>"+
						"<td>"+result.stuList[i].classname+"</td>"+
						"<td>"+result.stuList[i].sectionname+"</td>"+
						"<td>"+result.stuList[i].studentHouse+"</td>"+
						+"</tr>"
				);
			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='7'>No Records Found</td></tr>");
			}
		}
	});
}

function getstudentAlphaWise(accyear,selection,classId,section,location){
	
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},
	
	$.ajax({
		
		type : "POST",
		url : "reportaction.html?method=getstudentAlphaWise",
		data : datalist,
		
		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>SI.No</th>"+
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"<th>Roll No</th>" +
					"<th>Class</th>" +
					"<th>Division</th>" +
					"</tr>" +
					"</table>"
			);
			if(result.stuList.length>0){
			for(var i=0;i<result.stuList.length;i++){
				
				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.stuList[i].sno+"</td>"+
						"<td>"+result.stuList[i].admissionNo+"</td>"+
						"<td>"+result.stuList[i].studentnamelabel+"</td>"+
						"<td>"+result.stuList[i].studentRollNo+"</td>"+
						"<td>"+result.stuList[i].classname+"</td>"+
						"<td>"+result.stuList[i].sectionname+"</td>"+
						+"</tr>"
				);
			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>No Records Found</td></tr>");
			}
		}
	});
}
function getstudentsWithheld(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentsWithheld",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>" +
					"<th>Name</th>"+
					"<th>School</th>" +
					"<th>Class</th>" +
					"<th>Section</th>" +
					"<th>Status</th>" +
					
					"</tr>" +
					"</table>"
			);
			if(result.studentWithheld.length>0){
			for(var i=0;i<result.studentWithheld.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentWithheld[i].count+"</td>"+
						"<td>"+result.studentWithheld[i].admissionNo+"</td>"+
						"<td>"+result.studentWithheld[i].studentRollNo+"</td>"+
						"<td>"+result.studentWithheld[i].studentnamelabel+"</td>"+
						"<td>"+result.studentWithheld[i].location+"</td>"+
						"<td>"+result.studentWithheld[i].classname+"</td>"+
						"<td>"+result.studentWithheld[i].section+"</td>"+
						"<td>"+result.studentWithheld[i].status+"</td>"+
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='8'>NO Records Found</td></tr>");
			}
		}

	});
}
function rollNoWiseDownload(){
	
	
}

