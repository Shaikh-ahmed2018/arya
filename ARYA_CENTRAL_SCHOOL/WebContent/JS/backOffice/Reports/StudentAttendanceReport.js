$(document).ready(function() {
	$("#location").change(function(){
		getClassList();
	});
	
	$("#class").change(function(){
		getSectionList();
	});

	
	$("#accyear").val($("#hacademicyaer").val());
	if(!$("#successid").val()=="")
	
	{
		$("#txtstyle, #txtstyle").hide();
		
		$("#allstudent").show();
		$(".selecteditems").show();
		
		
	}
	else
	{
		$("#allstudent").hide();
		$(".selecteditems").hide();
		
	}
	
	$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		defaultDate : "+1w",
		changeMonth : true,
		changeYear : true
		
	});	

	
	$("#todate").datepicker({
		dateFormat : "dd-mm-yy",
		defaultDate : "+1w",
		changeMonth : true,
		changeYear : true
		
	});	
	
	$("#dropdown").click(function(){
		$("#hbox").slideToggle("slow");
		
	});

	$('.col-md-10, .vertical').click(function(){
		$("#hbox").hide();
	});
	
	
	$("#streamname").change(function(){
		
		
		
		 $('#classid').show();
	    $('#section').show();
		
		getClass(classid,streamname);
		
	});
	
	$("#classid").change(function(){
		
		
		
		 $('#classid').show();
	    $('#section').show();
		
		getSection();
		
	});
	
	
	$('#section').change(function() {
		getStudentName();
		
		
	});
	
	
	
	$("#search").click(function(){
		
		
	
		 var accyear=$("#accyear").val();
	     var fromdate=$("#Fromdate").val();
		 var todate=$("#todate").val();
	   	var stream=$("#streamname").val();
		var classname=$("#classid").val();
		var section=$("#sectionid").val();
		var studentname=$("#studentname").val();
		
		
    if(accyear=="" && fromdate=="" && todate=="" && stream=="" && classname=="" && section=="" && studentname==""){
			
			$("#txtstyle, #txtstyle").slideToggle();
			
		}
		
	if(accyear==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Select Academic Year");
		
		return false;
		
	}
	
	if(stream==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Select Stream Name");
		
		return false;
		
	}
	
	if(classname==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Select Class Name");
		
		return false;
		
	}
	if(section==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Select Section Name");
		
		return false;
		
	}
	
if(fromdate==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Select From Date");
		
		return false;
		
	}
if(todate==""){
	
	$('.errormessagediv').show();
	$('.validateTips').text("Select To Date");
	
	return false;
	
}


if(studentname==""){
	
	$('.errormessagediv').show();
	$('.validateTips').text("Select Student Name");
	
	return false;
	
}

    
else{
	

	return true;
	
	/*document.getElementById("stuattnid").submit();*/
}
    
		
	
	});
	
	
	
	$("#excelDownload").click(function(){
		
		 var accyear=$("#haccyear").val();
	     var fromdate=$("#hfromdate").val();
		 var todate=$("#htodate").val();
	   	var stream=$("#hstream").val();
		var classname=$("#hclass").val();
		var section=$("#hsection").val();
	
		
		
		var studentname = $("#hallstudent").val();
		
		
		
		if(studentname=="all"){
			
		
			 studentname = $("#hallstudent").val();
				
		}
		
		else{
		
			    studentname=$("#hstudent").val();	
				
		}
		
		
		
		
		
		
		/*var succ =$("#successid").val();
		
		alert("succ "+succ);
		
		if(succ=="success")
		{
			
			alert("if");
			
			 var accyear=$("#haccyear").val();
		     var fromdate=$("#hfromdate").val();
			 var todate=$("#htodate").val();
		   	var stream=$("#hstream").val();
			var classname=$("#hclass").val();
			var section=$("#hsection").val();
			var studentname=$("#hstudent").val();
			
			
			alert("accyear"+accyear);
			alert("fromdate"+fromdate);
			alert("todate"+todate);
			alert("stream"+stream);
			alert("classname"+classname);
			alert("section"+section);
			alert("studentname"+studentname);
			
		}
		else
		{
			
			alert("else");
			var accyear=$("#accyear").val();
		     var fromdate=$("#Fromdate").val();
			 var todate=$("#todate").val();
		   	var stream=$("#streamname").val();
			var classname=$("#classid").val();
			var section=$("#sectionid").val();
			var studentname=$("#studentname").val();
			
			
			alert("accyear"+accyear);
			alert("fromdate"+fromdate);
			alert("todate"+todate);
			alert("stream"+stream);
			alert("classname"+classname);
			alert("section"+section);
			alert("studentname"+studentname);
			
		}*/
		 
		
		window.location.href = 'studentattendanceReport.html?method=studentAttendanceExcelReport&accyear='
			+accyear
			+ ' &fromdate='
			+fromdate
			+ ' &todate='
			+ todate
			+ ' &stream='
			+stream
			+ ' &classname='
			+ classname
			+ ' &section='
			+section
			+ '&studentname='
			+studentname;
		
	});

	
	
$("#pdfDownload").click(function(){
		
	/*	
	var succ =$("#successid").val();
	
	alert("succ "+succ);
	
	if(succ=="success")
	{
		 var accyear=$("#haccyear").val();
	     var fromdate=$("#hfromdate").val();
		 var todate=$("#htodate").val();
	   	var stream=$("#hstream").val();
		var classname=$("#hclass").val();
		var section=$("#hsection").val();
		var studentname=$("#hstudent").val();
		
		
		alert("accyear"+accyear);
		alert("fromdate"+fromdate);
		alert("todate"+todate);
		alert("stream"+stream);
		alert("classname"+classname);
		alert("section"+section);
		alert("studentname"+studentname);
		
	}
	else
	{
		var accyear=$("#accyear").val();
	     var fromdate=$("#Fromdate").val();
		 var todate=$("#todate").val();
	   	var stream=$("#streamname").val();
		var classname=$("#classid").val();
		var section=$("#sectionid").val();
		var studentname=$("#studentname").val();
		
		
		alert("accyear"+accyear);
		alert("fromdate"+fromdate);
		alert("todate"+todate);
		alert("stream"+stream);
		alert("classname"+classname);
		alert("section"+section);
		alert("studentname"+studentname);
		
	}
	*/
	
	
	 var accyear=$("#haccyear").val();
     var fromdate=$("#hfromdate").val();
	 var todate=$("#htodate").val();
   	var stream=$("#hstream").val();
	var classname=$("#hclass").val();
	var section=$("#hsection").val();


	
	var studentname = $("#hallstudent").val();
	
	
	
	if(studentname=="all"){
		
	
		 studentname = $("#hallstudent").val();
			
	}
	
	else{
	
		    studentname=$("#hstudent").val();	
			
	}
	
	
		window.location.href = 'studentattendanceReport.html?method=studentAttendancePDFReport&accyear='
			+accyear
			+ ' &fromdate='
			+fromdate
			+ ' &todate='
			+ todate
			+ ' &stream='
			+stream
			+ ' &classname='
			+ classname
			+ ' &section='
			+section
			+ '&studentname='
			+studentname;
		
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});

function getSectionList()
{

	var classId=$("#class").val();
	var locationid=$("#location").val();
	$.ajax({
		type : "GET",
		url : "reportaction.html?method=getSectionByClass",
		data : {"classId":classId,
				"location":locationid,
		},
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);

			$("#section").html("");
			$("#section").append('<option value="all">' + "----------Select----------"	+ '</option>');

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

			$('#class').append('<option value="all">' + "----------Select----------"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#class').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}


var classlength=0;
function getClass(classid,streamname) {
	
	var streamname = $("#streamname").val();
	datalist = {
			"streamname" : streamname
		},
		
		
		$.ajax({
			
			type : 'POST',
			url : "communicationPath.html?method=getClass",
			data : datalist,
			success : function(response) {
				
				
				var result = $.parseJSON(response);
				
				$('#classid').html("");
				$('#classid').append(
						
						'<option value="' + "" + '">' + " "
								
						+ '</option>');
				
				for ( var j = 0; j < result.classlist.length; j++) {

					$('#classid').append(

					'<option value="'

					+ result.classlist[j].classId + '">'

					+ result.classlist[j].className

					+ '</option>');

				}
				
			}	
		});
		
}



var sectionlength=0;
function getSection() {
	
	var classid = $("#classid").val();
	
	
	datalist={
			"classid" : classid
	},
	
	
	
	$.ajax({
		
		type : 'POST',
		url : "communicationPath.html?method=getSection",
		data : datalist,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append(
					
					'<option value="' + "" + '">' + " "
							
					+ '</option>');
			
			for ( var j = 0; j < result.seclist.length; j++) {

				$('#sectionid').append(

				'<option value="'

				+ result.seclist[j].sectionId + '">'

				+ result.seclist[j].sectionName

				+ '</option>');

			}
			
		}
		
		
	});
	
	
}


function getStudentName() {

	var sectionid = $("#section").val();
	var classid = $("#class").val();
	var location=$("#location").val();
	var accyear=$("#accyear").val();
	datalist={
			"classid" : classid,
			"sectionid" : sectionid,
			"locationid":location,
			"accyearid":accyear,
	},
	
	$.ajax({
		type : 'POST',
		url : "studentattendanceReport.html?method=getAllStudentListName",
		data : datalist,
		async : false,
		success : function(response) {
		
			var result = $.parseJSON(response);

	$('#studentname').html("");
	$('#studentname')
			.append(
					'<option value="'
							+ ""
							+'">'
							+ ""
							+ '</option>');
	$('#studentname').append(
			'<option value="'+ "all" + '">'+ "ALL"+ '</option>');
	for ( var j = 0; j < result.parentVOList.length; j++) {
		$('#studentname').append('<option value="'+ result.parentVOList[j].studentid+ '">'+ result.parentVOList[j].studentFnameVar+ '</option>');
	}
		
		
		}
	});
	
	

}

function validate(){
	
	
}	
