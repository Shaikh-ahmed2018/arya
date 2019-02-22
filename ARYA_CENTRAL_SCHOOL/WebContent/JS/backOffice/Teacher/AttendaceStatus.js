$(document).ready(function() {
	
	$("#Acyearid").val($("#globalAcademicYear").val());
	dateRange();
	var locationid=$("#locationname").val();
	if(locationid != "" && locationid != null){
		getTeacherList(locationid);
	}
	
	$("#locationname").change(function(){
		var locationid=$("#locationname").val();
		getClassList();
		var classname=$("#classId").val();
		getSectionList(classname);
		getSpecilization(classname);
		if(locationid == '' || locationid == 'all'){
			$("#Acyearid option[value='all']").attr('selected', 'true');
			$("#classId option[value='all']").attr('selected', 'true');
			$("#section option[value='all']").attr('selected', 'true');
			$("#specialization option[value='']").attr('selected', 'true');
		}
		changeAccYear();
		getTeacherList(locationid);
	});
	
	$("#Acyearid").change(function(){
		changeAccYear();
		dateRange();
		$("#startdate").datepicker('destroy');
		$("#enddate").datepicker('destroy');
		var accyear=$("#Acyearid").val();
		if(accyear == '' || accyear == 'all'){
			getClassList();
			var classname=$("#classId").val();
			getSectionList(classname);
			getSpecilization(classname);
			$("#classId option[value='All']").attr('selected', 'true');
			$("#section option[value='all']").attr('selected', 'true');
		}else{
			getClassList();
			var classname=$("#classId").val();
			getSectionList(classname);
			getSpecilization(classname);			
		}
		$('#divIdList').hide();
		$('#teacher').val("");
		$("#startdate").datepicker({
			dateFormat : "dd-mm-yy",
			minDate:minDate-1,
			maxDate : maxDate,
			changeMonth : "true",
			changeYear : "true",
			buttonImage : "images/calendar.GIF",
			buttonImageOnly : true
		});
		
		$("#enddate").datepicker({
			dateFormat : "dd-mm-yy",
			minDate:minDate-1,
			maxDate : maxDate,
			changeMonth : "true",
			changeYear : "true",
			buttonImage : "images/calendar.GIF",
			buttonImageOnly : true
		});
	});

	$("#classId").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classId").val();
		getSectionList(classname);
		getSpecilization(classname);
		getAttendenceByClassList(locationid,accyear,classname);
		getTeacherList(locationid);
		$("#teacher option[value='all']").attr('selected', 'true');
		$("#section option[value='all']").attr('selected', 'true');
	});
	
	$("#section").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classId").val();
		var sectionid=$("#section").val();
		
		getStudentListBySection(locationid,accyear,classname,sectionid);
	});
	$("#teacher").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classId").val();
		var sectionid=$("#section").val();
		var teacherid=$("#teacher").val();
		
		getAttendanceTeacherListByAll(locationid,accyear,classname,sectionid,teacherid);
	});
	
	
	var flag = false;
	var dd =new Date();
	var day=dd.getDate();
	if(day < 10){
		day = "0" + day;
	}
	var month = dd.getMonth()+1;

	if(month < 10){
		month = "0" + month;
	}
	var year = dd.getFullYear();
	
	var date = day +"-"+(month)+"-"+year;
	$("#date").val(date);
	
	var classId=$("#classId").val();
	var section=$("#section").val();
	var date=$("#date").val();
	var teacher = $("#teacher").val();
	
	
	
	$("#date").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true
		
	});
	
	$("#startdate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate:minDate-1,
		maxDate : maxDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var min = $(this).datepicker('getDate'); // Get selected date
		    $("#enddate").datepicker('option', 'minDate', min || $("#lastDate").val());
		    getAttendanceListByDate();
		}
	});
	
	$("#enddate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate:minDate-1,
		maxDate : maxDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var max = $(this).datepicker('getDate'); // Get selected date
	        $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
	        getAttendanceListByDate();
		}
	});
	
	$(".buttonstyle").click(function(){
		
		var idString =$( this ).attr( "id" );
	
		 window.location.href="StudentAttendanceActionPath.html?method=editAttendance&idString="+idString;
			
	});

	$("#search").click(function(){
		
		flag = true;
		var classId=$("#classId").val();
		var section=$("#section").val();
		var date=$("#date").val();
		var teacher = $("#teacher").val();
		var spec = $("#specialization").val();
		
		
		
		if(classId==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select class");
			document.getElementById("classId").style.border = "1px solid #AF2C2C";
			document.getElementById("classId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			
		}else if(section==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select section");
			document.getElementById("section").style.border = "1px solid #AF2C2C";
			document.getElementById("section").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
		}else if(date==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Date");
			document.getElementById("date").style.border = "1px solid #AF2C2C";
			document.getElementById("date").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
		}else{
			
			window.location.href="StudentAttendanceActionPath.html?method=getStudentAttendanceDetails&classId="+classId+"&section="+section+"&date="+date+"&spec="+spec+"&teacher="+teacher;
			
		}
	});


	
	$("#saveAttendance").click(function(){
	
		var date =$("#date").val();
		var location=$('#locationname').val();
		var teacherid=$('#teacherid').val();

		var teacherIdArray=[];
		var statusArray=[];
		var period1=[];
		var period2=[];
		var period3=[];
		var period4=[];
		var period5=[];
		var period6=[];
		var period7=[];
		var period8=[];
		var period9=[];
		
		
		$('#allstudent tbody tr').each(function(){
			var studentID=$(this).attr("class");
			var status = $(this).find('.statusclass').val();
			var p1 = $(this).find("select[name='period1']").val();
			var p2 = $(this).find("select[name='period2']").val();
			var p3 = $(this).find("select[name='period3']").val();
			var p4 = $(this).find("select[name='period4']").val();
			var p5 = $(this).find("select[name='period5']").val();
			var p6 = $(this).find("select[name='period6']").val();
			var p7 = $(this).find("select[name='period7']").val();
			var p8 = $(this).find("select[name='period8']").val();
			var p9 = $(this).find("select[name='period9']").val();

			
			teacherIdArray.push(studentID.trim());
			
			if(status!=undefined && status!=""){
				
				statusArray.push(status);
			}
			
			if(p1!=undefined && p1!=""){
				period1.push(p1);
			}
			
			if(p2!=undefined && p2!=""){
				period2.push(p1);
			}
			
			if(p3!=undefined && p3!=""){
				period3.push(p3);
			}
			
			if(p4!=undefined && p4!=""){
				period4.push(p4);
			}
			
			if(p5!=undefined && p5!=""){
				period5.push(p5);
			}
			
			if(p6!=undefined && p6!=""){
				period6.push(p6);
			}
			
			if(p7!=undefined && p7!=""){
				period7.push(p7);
			}
			
			if(p8!=undefined && p8!=""){
				period8.push(p8);
			}
			
			if(p9!=undefined && p9!=""){
				period9.push(p9);
			}
		});
		
		
		var datalist={
				
				"date" : date,
				"teacherid":teacherid,
				"locationId":location,
				"teacherIdArray" : teacherIdArray.join(),
				"statusArray" : statusArray.join(),
				"period1":period1.join(),
				"period2":period2.join(),
				"period3":period3.join(),
				"period4":period4.join(),
				"period5":period5.join(),
				"period6":period6.join(),
				"period7":period7.join(),
				"period8":period8.join(),
				"period9":period9.join()
		};
		$.ajax({
			type : "POST",
			url : "StudentAttendanceActionPath.html?method=updateAttendanceStatus",
			data :datalist,
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				
				if(result.status=="true"){
					
					$('.successmessagediv').show();
					$('.successmessage').text("Attendance updated successfully");
					
				setTimeout(function(){
					
					 window.location.href="teachermenuaction.html?method=attendaceStatus";
				 
				 },6000);
				
				}else{
					
					$('.errormessagediv').show();
					$('.validateTips').text("Attendance not updated,Try later");

				}
			
			}
		});
	});

	/*$.ajax({
		url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
		async:false,
		success : function(data) {
			var result = $.parseJSON(data);
			$("#classId").html("");
			$("#classId").append('<option value="' + "" + '">' + "-------------Select------------" + '</option>');
			for ( var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {
				$('#classId').append('<option value="'
						+ result.classDetailsIDandClassDetailsNameList[j].secDetailsId
						+ '">'
						+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName
						+ '</option>');

			}
		}
	});*/
	
	var hclassId=$("#hclass").val();
	
	if(hclassId!=undefined && hclassId!=""){
		
		$("#classId option[value='"+hclassId +"']").attr('selected', 'true');
		
		
		var classidVal = $("#classId").val();

		datalist = {
			"classidVal" : classidVal
		},
		
		$.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=getClassSection",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				$("#section").html("");
				$("#section").append(
						'<option value="all">' + "-------------Select------------" + '</option>');

				for ( var j = 0; j < result.sectionList.length; j++) {
					$("#section").append(
							'<option value="' + result.sectionList[j].sectioncode
									+ '">' + result.sectionList[j].sectionnaem
									+ '</option>');
				}

			}
		});
		
		$("#section option[value=" + $("#hsection").val().trim() + "]").attr('selected','true');
		$("#date").val($("#hdate").val().trim());
		$("#teacher").val($("#hteachername").val().trim());
		
		
	}
	

	$('#allstudent tr td').each(function(){
		
		
		var status=$(this).find('.statusclass').val();
		
		if(status!=undefined){
		
		var rowid=$(this).find('.statusclass').attr("id");

		$('#'+rowid).find('option').remove();
	
		var statusList=[];
		statusList.push("Present");
		statusList.push("Absent");
		statusList.push("Leave");
		
		for (var j = 0; j < statusList.length; j++) {
			

			$("#"+rowid).append('<option value="'+ statusList[j]+ '">'
									+  statusList[j]+ '</option>');
		}
		
		$("#"+rowid+" option[value=" + status + "]").attr('selected', 'true');
		
		}
		
	});
	

$("#searchAttendanceList").click(function(){
	
	var startDate=$("#startdate").val();
	var endDate=$("#enddate").val();
	
	window.location.href="teachermenuaction.html?method=attendaceStatus&startDate="+startDate+"&endDate="+endDate;	
	
});

$("#reset").click(function(){
	
	$("#classId").val("");
	$("#section").val("");
	$("#date").val("");
	$("#teacher").val("");
});

$(".GetTimeTable").click(function(){
	
	var stuId=(this).id;
	var classId=$("#classId option:selected").text();
	var classname=$("#classId option:selected").val();
	
	var sectionId=$("#section option:selected").text();
	var sectionName=$("#section option:selected").val();
	var date=$("#date").val();
	var status=$(this).prev().find('input').val();
		
	window.location.href="StudentAttendanceActionPath.html?method=getStudentPeriodAttendance&classId="+classId+","+classname+"&section="+sectionId+","+sectionName+"&stuId="+stuId+"&date="+date+"&status"+status;
	
});

$("#UpdatePeriodAtt").click(function(){
	
	var studentId=$("#hstudentId").val();
	var classsId=$("#hclassId").val();
	var sectionId=$("#hsectionId").val();
	var date=$("#attendancedate").val();
	
	var period1=$("#period1").val();
	var period2=$("#period2").val();
	var period3=$("#period3").val();
	var period4=$("#period4").val();
	var period5=$("#period5").val();
	var period6=$("#period6").val();
	var period7=$("#period7").val();
	var period8=$("#period8").val();
	
	var datalist={
			
			"studentId":studentId,
			"classsId":classsId,
			"sectionId":sectionId,
			"date":date,
			"period1":period1,
			"period2":period2,
			"period3":period3,
			"period4":period4,
			"period5":period5,
			"period6":period6,
			"period7":period7,
			"period8":period8,
	};
	
	$.ajax({
		type : 'POST',
		url : "StudentAttendanceActionPath.html?method=updateStudentPeriodAtt",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
		
			if(result.status=="true"){
				
				$('.successmessagediv').show();
				$('.successmessage').text("Period Attendance Saved Succesfully");
				
				setInterval(
						function() {
							
							var classId=$("#hclassId").val();
							var section=$("#hsectionId").val();
							var date=$("#attendancedate").val();
							
							window.location.href="StudentAttendanceActionPath.html?method=getStudentAttendanceDetails&classId="+classId+"&section="+section+"&date="+date;
							
						}, 3000);
			
				
			}else{
				
				$('.errormessagediv').show();
				$('.validateTips').text("Period Attendance not Saved,Try later");
				
			}

		}
	});
});

$("#back").click(function(){
	
	var classId=$("#hclassId").val();
	var section=$("#hsectionId").val();
	var date=$("#attendancedate").val();
	
	window.location.href="StudentAttendanceActionPath.html?method=getStudentAttendanceDetails&classId="+classId+"&section="+section+"&date="+date;
});
	
$('#excelDownload')
.click(
		function() {
			
			var startDate=$("#startdate").val();
			var endDate=$("#enddate").val();
			
			window.location.href = "StudentAttendanceActionPath.html?method=downloadStudentAttendanceDetailsXLS&startDate=" + startDate + "&endDate=" + endDate;
			
		});
$("#pdfDownload").click(function(){
	
	
	var startDate=$("#startdate").val();
	var endDate=$("#enddate").val();
	
	window.location.href = "StudentAttendanceActionPath.html?method=downloadStudentAttendanceDetailsPDF&startDate=" + startDate + "&endDate=" + endDate;
		
});

var hclassId=$("#hclass").val();

if(hclassId!=undefined && hclassId!=""){
	
	$("#classId option[value='"+hclassId +"']").attr('selected', 'true');
	
	
	var classidVal = $("#classId").val();

	datalist = {
		"classidVal" : classidVal
	},
	
	$.ajax({
		
		type : 'POST',
		url : "StudentAttendanceActionPath.html?method=getClassSpec",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#specialization").html("");
			$("#specialization").append(
					'<option value="all">' + "-------------Select------------" + '</option>');

			for ( var j = 0; j < result.specList.length; j++) {
				$("#specialization").append(
						'<option value="' + result.specList[j].specId
								+ '">' + result.specList[j].specName
								+ '</option>');
			}
			
		}
		});
	$("#specialization option[value=" + $("#hspec").val().trim() + "]").attr('selected','true');
}
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

			$('#classId').html("");

			$('#classId').append('<option value="all">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classId').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function HideError() 
{
	
document.getElementById("date").style.border = "1px solid #ccc";
document.getElementById("date").style.backgroundColor = "#fff";

document.getElementById("classId").style.border = "1px solid #ccc";
document.getElementById("classId").style.backgroundColor = "#fff";

document.getElementById("section").style.border = "1px solid #ccc";
document.getElementById("section").style.backgroundColor = "#fff";

}

function getTeacherName(){
	var classId=$("#classId").val();
	var sectionId=$("#section").val()

	datalist ={
		"classId":classId,
		"sectionId":sectionId
	};

	$.ajax({
		
		type : "POST",
		url : "StudentAttendanceActionPath.html?method=getteacherByClass",
		data : datalist,
		async : false,
		success : function(response){
			var result = $.parseJSON(response);
			
			$("#teacher").val(result.teacherName[0].teacherName);
			$("#teacherid").val(result.teacherName[0].teacherID);
		}
	});
}

function getAttendenceStudentList(){
	var classId=$("#classId").val();
	var section=$("#section").val();
	var date=$("#date").val();
	var teacher = $("#teacher").val();
	var spec = $("#specialization").val();
	datalist ={
			"classId":classId,
			"section":section,
			"date":date,
			"spec":spec,
			"teacher":teacher
	};

	$.ajax({

		type : "POST",
		url : "StudentAttendanceActionPath.html?method=getStudentAttendanceList",
		data : datalist,
		async : false,
		success : function(response){
			var result = $.parseJSON(response);
			$('#divIdList').show();
			$("#allstudent thead").empty();
			$("#allstudent thead").append("<tr>"
					+"<th style='min-width: 45px;height: 35px;'>Sl No</th>"
					+"<th style='min-width: 130px;text-align:center;'>Admission No.</th>"
					+"<th style='min-width: 170px;text-align:center;'>Student Name</th>"
					+"<th style='min-width: 100px;text-align:center;'>Atten.Status</th>"
					+"<th style='min-width: 100px;text-align:center;'>Period1</th>"
					+"<th style='min-width: 100px;text-align:center;'>Period2</th>"
					+"<th style='min-width: 100px;text-align:center;'>Period3</th>"
					+"<th style='min-width: 100px;text-align:center;'>Period4</th>"
					+"<th style='min-width: 100px;text-align:center;'>Period5</th>"
					+"<th style='min-width: 100px;text-align:center;'>Period6</th>"
					+"<th style='min-width: 100px;text-align:center;'>Period7</th>"
					+"<th style='min-width: 100px;text-align:center;'>Period8</th>"
					+"<th style='min-width: 100px;text-align:center;'>Period9</th>"
					+"</tr>");
			
			if(result.attendanceList.length>0){
				$('#allstudent tbody').empty();
				for(var i=0;i<result.attendanceList.length;i++){
					$('#allstudent tbody').append("<tr class='"+result.attendanceList[i].studentid+"'>" 
							+"<td style='max-width: 45px;min-width: 45px;'>"+result.attendanceList[i].count+"</td>"
							+"<td style='max-width: 130px;min-width: 130px;'>"+result.attendanceList[i].addmissionNo+"</td>"
							+"<td style='max-width: 170px;min-width: 170px;'>"+result.attendanceList[i].studentname+"</td>"
							+"<td style='max-width: 100px;min-width: 100px;'><select name='status' class='form-control statusclass' id='"+result.attendanceList[i].studentid+"status'><option value="+result.attendanceList[i].status+">"+result.attendanceList[i].status+"</option></select></td>"
							+"<td style='max-width: 100px;min-width: 100px;'><select name='period1' class='form-control statusclass' id='"+result.attendanceList[i].studentid+"period1'><option value=''></option></select></td>"
							+"<td style='max-width: 100px;min-width: 100px;'><select name='period2' class='form-control statusclass' id='"+result.attendanceList[i].studentid+"period2'><option value=''></option></select></td>"
							+"<td style='max-width: 100px;min-width: 100px;'><select name='period3' class='form-control statusclass' id='"+result.attendanceList[i].studentid+"period3'><option value=''></option></select></td>"
							+"<td style='max-width: 100px;min-width: 100px;'><select name='period4' class='form-control statusclass' id='"+result.attendanceList[i].studentid+"period4'><option value=''></option></select></td>"
							+"<td style='max-width: 100px;min-width: 100px;'><select name='period5' class='form-control statusclass' id='"+result.attendanceList[i].studentid+"period5'><option value=''></option></select></td>"
							+"<td style='max-width: 100px;min-width: 100px;'><select name='period6' class='form-control statusclass' id='"+result.attendanceList[i].studentid+"period6'><option value=''></option></select></td>"
							+"<td style='max-width: 100px;min-width: 100px;'><select name='period7' class='form-control statusclass' id='"+result.attendanceList[i].studentid+"period7'><option value=''></option></select></td>"
							+"<td style='max-width: 100px;min-width: 100px;'><select name='period8' class='form-control statusclass' id='"+result.attendanceList[i].studentid+"period8'><option value=''></option></select></td>"
							+"<td style='max-width: 100px;min-width: 100px;'><select name='period9' class='form-control statusclass' id='"+result.attendanceList[i].studentid+"period9'><option value=''></option></select></td>"
							+"</tr>");
				}
				$("#studentname").change(function(){
					$("#divIdList").animate({
						 scrollTop: $("tr."+$(this).val()).css('top','0')
					});
					$("tr."+$(this).val()).css({
						"background":"#9CDDE3",
						"position":"absolute",
							"top":"36px"
					});
					$("tr").not("."+$(this).val()).css({
						"background":"#FFF",
							"position":"static",
					});
				});
				$('#allstudent tr td').each(function(){
					var status=$(this).find('.statusclass').val();
					if(status!=undefined){
						var rowid=$(this).find('.statusclass').attr("id");
						$('#'+rowid).find('option').remove();
						var statusList=[];
						statusList.push("Present");
						statusList.push("Absent");
						statusList.push("Leave");
						for (var j = 0; j < statusList.length; j++) {
							$("#"+rowid).append('<option value="'+ statusList[j]+ '">'+  statusList[j]+ '</option>');
						}
						$("#"+rowid+" option[value=" + status + "]").attr('selected', 'true');
					}
					
				});
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='10'>NO Records Found</td></tr>");
			}

		}
	});
}

function getStudentNameList(){
	var classId=$("#classId").val();
	var section=$("#section").val();
	var date=$("#date").val();
	var teacher = $("#teacher").val();
	var spec = $("#specialization").val();
	datalist ={
			"classId":classId,
			"section":section,
			"date":date,
			"spec":spec,
			"teacher":teacher
	};

	$.ajax({
		type : "POST",
		url : "StudentAttendanceActionPath.html?method=getStudentAttendanceList",
		data : datalist,
		async : false,
		success : function(response){
			var result = $.parseJSON(response);
			$('#studentname').empty();
			$('#studentname').append('<option value="all">' + "-------------Select-------------"	+ '</option>');
			for(var i=0;i<result.attendanceList.length;i++){
				$('#studentname').append('<option value="'+ result.attendanceList[i].studentid + '">'	+ result.attendanceList[i].studentname+ '</option>');
			}
		}
	});		
}


function getSectionList(classidVal){
	datalist = {
		"classidVal" : classidVal,
		"locationId":$("#locationname").val()
	},
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#section").html("");
			$("#section").append(
					'<option value="all">' + "-------------Select------------" + '</option>');

			for ( var j = 0; j < result.sectionList.length; j++) {
				$("#section").append(
						'<option value="' + result.sectionList[j].sectioncode
								+ '">' + result.sectionList[j].sectionnaem
								+ '</option>');
			}

		}
	});

}

function getSpecilization(classidVal){
	datalist = {
		"classidVal" : classidVal
	},
	
	$.ajax({
	
	type : 'POST',
	url : "StudentAttendanceActionPath.html?method=getClassSpec",
	data : datalist,
	async : false,
	success : function(response) {
		var result = $.parseJSON(response);
		$("#specialization").html("");
		$("#specialization").append(
				'<option value="">' + "-------------Select------------" + '</option>');

		for ( var j = 0; j < result.specList.length; j++) {
			$("#specialization").append(
					'<option value="' + result.specList[j].specId
							+ '">' + result.specList[j].specName
							+ '</option>');
		}
		
	}
	});
}

function changeAccYear(){
	var locationId = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "StudentAttendanceActionPath.html?method=searchAttendanceYearList",
		data : {
			"locationId" :locationId,
			"accyear" :accyear,				
		},
		async : false,
		
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
             if(result.SearchAttendanceList.length>0){
			for(var i=0;i<result.SearchAttendanceList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td>"+result.SearchAttendanceList[i].count+"</td>" 
						+"<td><a class='anchor'><input type='button' class='buttonstyle' id='"+result.SearchAttendanceList[i].classId+","+result.SearchAttendanceList[i].sectionId+","+result.SearchAttendanceList[i].date+","+result.SearchAttendanceList[i].specId+","+result.SearchAttendanceList[i].teacherID+"' value='"+result.SearchAttendanceList[i].date+"'></a></td>"
						+"<td> "+result.SearchAttendanceList[i].classsection+"</td>"
						+"<td> "+result.SearchAttendanceList[i].specName+" </td>"
						+"<td> "+result.SearchAttendanceList[i].teacherName+" </td>"
						+"<td> "+result.SearchAttendanceList[i].tot_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].present_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].absent_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].holiday_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].leave_count+" </td>"
						+"</tr>");
			}
             }
             else{
            	 $('#allstudent tbody').append("<tr><td colspan='10'>NO Records Found</td></tr>");
             }
			$(".buttonstyle").click(function(){
				
				var idString =$( this ).attr( "id" );
			
				 window.location.href="StudentAttendanceActionPath.html?method=editAttendance&idString="+idString;
					
			});
		}
	});
}

function getAttendenceByClassList(locationid,accyear,classname){

	datalist = {

			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,

	}, $.ajax({
		type : 'POST',
		url : "StudentAttendanceActionPath.html?method=getAttendenceByClassList",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.SearchAttendanceList.length>0){
			for(var i=0;i<result.SearchAttendanceList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td>"+result.SearchAttendanceList[i].count+"</td>" 
						+"<td><a class='anchor'><input type='button' class='buttonstyle' id='"+result.SearchAttendanceList[i].classId+","+result.SearchAttendanceList[i].sectionId+","+result.SearchAttendanceList[i].date+","+result.SearchAttendanceList[i].specId+","+result.SearchAttendanceList[i].teacherID+"' value='"+result.SearchAttendanceList[i].date+"'></a></td>"
						+"<td> "+result.SearchAttendanceList[i].classsection+"</td>"
						+"<td> "+result.SearchAttendanceList[i].specName+" </td>"
						+"<td> "+result.SearchAttendanceList[i].teacherName+" </td>"
						+"<td> "+result.SearchAttendanceList[i].tot_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].present_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].absent_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].holiday_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].leave_count+" </td>"
						+"</tr>");
			   }	
			}
			else{
		     	$('#allstudent tbody').append("<tr><td colspan='10'>NO Records Found</td></tr>");
			 }
			$(".buttonstyle").click(function(){
				
				var idString =$( this ).attr( "id" );
			
				 window.location.href="StudentAttendanceActionPath.html?method=editAttendance&idString="+idString;
					
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
		url : "StudentAttendanceActionPath.html?method=getAttendenceByClassSectionList",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.SearchAttendanceList.length>0){
				$('#allstudent tbody').append("<tr><td colspan='10'>NO Records Found</td></tr>");
			for(var i=0;i<result.SearchAttendanceList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td>"+result.SearchAttendanceList[i].count+"</td>" 
						+"<td><a class='anchor'><input type='button' class='buttonstyle' id='"+result.SearchAttendanceList[i].classId+","+result.SearchAttendanceList[i].sectionId+","+result.SearchAttendanceList[i].date+","+result.SearchAttendanceList[i].specId+","+result.SearchAttendanceList[i].teacherID+"' value='"+result.SearchAttendanceList[i].date+"'></a></td>"
						+"<td> "+result.SearchAttendanceList[i].classsection+"</td>"
						+"<td> "+result.SearchAttendanceList[i].specName+" </td>"
						+"<td> "+result.SearchAttendanceList[i].teacherName+" </td>"
						+"<td> "+result.SearchAttendanceList[i].tot_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].present_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].absent_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].holiday_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].leave_count+" </td>"
						+"</tr>");
			}	
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='10'>NO Records Found</td></tr>");
			}
			$(".buttonstyle").click(function(){
				
				var idString =$( this ).attr( "id" );
			
				 window.location.href="StudentAttendanceActionPath.html?method=editAttendance&idString="+idString;
					
			});
		}
	});
}

function getTeacherList(locationid){
	datalist = {
			"locationId" : locationid,
	},

	$.ajax({
		type : 'POST',
		url : "StudentAttendanceActionPath.html?method=getTeacherList",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#teacher").html("");
			$("#teacher").append('<option value="all">' + "-------------Select------------" + '</option>');
			for ( var j = 0; j < result.getTeacherList.length; j++) {
				$("#teacher").append('<option value="' + result.getTeacherList[j].teacherID+'">' + result.getTeacherList[j].teacherName+ '</option>');
			}

		}
	});
}

function getAttendanceTeacherListByAll(locationid,accyear,classname,sectionid,teacherid){

	datalist = {

			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"teacherid" : teacherid,

	}, $.ajax({
		type : 'POST',
		url : "StudentAttendanceActionPath.html?method=getAttendanceListByTeacher",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.SearchAttendanceList.length>0){
			for(var i=0;i<result.SearchAttendanceList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td>"+result.SearchAttendanceList[i].count+"</td>" 
						+"<td><a class='anchor'><input type='button' class='buttonstyle' id='"+result.SearchAttendanceList[i].classId+","+result.SearchAttendanceList[i].sectionId+","+result.SearchAttendanceList[i].date+","+result.SearchAttendanceList[i].specId+","+result.SearchAttendanceList[i].teacherID+"' value='"+result.SearchAttendanceList[i].date+"'></a></td>"
						+"<td> "+result.SearchAttendanceList[i].classsection+"</td>"
						+"<td> "+result.SearchAttendanceList[i].specName+" </td>"
						+"<td> "+result.SearchAttendanceList[i].teacherName+" </td>"
						+"<td> "+result.SearchAttendanceList[i].tot_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].present_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].absent_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].holiday_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].leave_count+" </td>"
						+"</tr>");
			}
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='10'>NO Records Found</td></tr>");
			}
			$(".buttonstyle").click(function(){
				
				var idString =$( this ).attr( "id" );
			
				 window.location.href="StudentAttendanceActionPath.html?method=editAttendance&idString="+idString;
					
			});
		}
	});
}

function dateRange(){
	var accyear=$('#Acyearid').val();
	$.ajax({
		type:"POST", 
		url:"StudentAttendanceActionPath.html?method=daterange",
		data:{
			"accyear":accyear,
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			minDate=result.startDate.split(",")[0];
			maxDate=result.startDate.split(",")[1];
			
		}
	
	});
}

function getAttendanceListByDate(){
	var startdate=$('#startdate').val();
	var enddate=$('#enddate').val();
	datalist = {
			"startdate":startdate,
			"enddate":enddate,

	}, $.ajax({
		type : 'POST',
		url : "StudentAttendanceActionPath.html?method=getAttendanceListByDate",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.SearchAttendanceList.length>0){
				
	
			for(var i=0;i<result.SearchAttendanceList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td>"+result.SearchAttendanceList[i].count+"</td>" 
						+"<td><a class='anchor'><input type='button' class='buttonstyle' id='"+result.SearchAttendanceList[i].classId+","+result.SearchAttendanceList[i].sectionId+","+result.SearchAttendanceList[i].date+","+result.SearchAttendanceList[i].specId+","+result.SearchAttendanceList[i].teacherID+"' value='"+result.SearchAttendanceList[i].date+"'></a></td>"
						+"<td> "+result.SearchAttendanceList[i].classsection+"</td>"
						+"<td> "+result.SearchAttendanceList[i].specName+" </td>"
						+"<td> "+result.SearchAttendanceList[i].teacherName+" </td>"
						+"<td> "+result.SearchAttendanceList[i].tot_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].present_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].absent_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].holiday_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].leave_count+" </td>"
						+"</tr>");
			}	
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='10'>NO Records Found</td></tr>");
			}
			$(".buttonstyle").click(function(){
				
				var idString =$( this ).attr( "id" );
			
				 window.location.href="StudentAttendanceActionPath.html?method=editAttendance&idString="+idString;
					
			});
		}
	});
}