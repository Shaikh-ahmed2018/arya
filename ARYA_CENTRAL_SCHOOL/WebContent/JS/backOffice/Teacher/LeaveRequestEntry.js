function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document).ready(function() {
	
	var round = $("#hiddenleavecount").val();
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(pageUrl=="teachermenuaction.html?method=leaveRequestEntry"){
		
		$(".successmessagediv").show();
		$(".validateTips").text("Leave Request Adding Progressing...");
		$(".successmessagediv").attr("style","width:100%;");
		setTimeout(function() {
			window.location.href="teachermenuaction.html?method=leaveRequest";
		},3000);
	}

	/*$('#Fromdate,#todate,#startsessionDay,#endsessionDay').change(function(){

		var total ="";
		var shalfday = $('#startsessionDay').val();
		var ehalfday = $('#endsessionDay').val();
		var date6 = $('#todate').val();
		alert(date6);
		var date7 = $('#Fromdate').val();
		alert(date7.getTime());
		var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
		var oneDay1 = 24*60*60*1000; // hours*minutes*seconds*milliseconds

		var total_leave = Math.round(Math.abs((date7.getTime() - date6.getTime())/(oneDay1)));


		var date1 = $('#startdate').val();
		startDate = date1.split("-");
		var dstartdate = new Date(startDate[2], startDate[1] - 1, startDate[0]);


		var date2 = $('#enddate').val();
		endDate = date2.split("-");
		var denddate = new Date(endDate[2], endDate[1] - 1, endDate[0]);

		
		var diffDays = Math.round(Math.abs((dstartdate.getTime() - denddate.getTime())/(oneDay)));

		if(shalfday == "FH" && ehalfday == "FH" ){

			total = total_leave-0.5;

		}

		else if(shalfday == "FH" && ehalfday == "SH"){

			total = total_leave;
		}

		else if(shalfday == "SH" && ehalfday == "SH" ){

			total = total_leave-0.5;

		}
		else if( (shalfday == "SH" && ehalfday == "FH"))
		{
			total = total_leave-1;
		}

		var d1 = Date.parse(dateConverter(date1));
		var d2 = Date.parse(dateConverter(date2));
		if(d1 > d2){
			$("#errorhover").show();
			$(".error").text("StartDate Should Be Less Then EndDate");
			setTimeout(function() {$('#errorhover').fadeOut();}, 3000)	;
			return false;
		}
	});*/
	
	
	
	
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);
	
	var sno = $('#hiddensno').val();
	

	if(sno==null||sno=="")
		{
		
		$('#fileupload').show();
		$('#downloadIdTitle ,#document2btn').hide();
		}
	else
		{
		
		$('#downloadIdTitle ,#document2btn').show();
		$('#fileupload').hide();

		}
	/*var birthcertificate=$("#hiddenprofile").val();

	
	 $("#document2btn").attr('name',birthcertificate);
	
	  $('.document2btn').click(
			
			function() {
				
				var path = $(this).attr('name');
				
				
				window.location.href = "studentRegistration.html?method=downloadDocument&Path="
						+ path.trim();
			});*/
	
	$("#downloadIdTitle").click(function(){
		
		$("#fileupload").show();
		
		$("#downloadIdTitle").hide();
		$("#document2btn").hide();
		
	});
	
	/*
	if(!$("#successid").val()=="")
		
	{
		$('#allstudent').show();
		
	}
	else
	{
		$("#allstudent").hide();
		
	}*/
	
	
	$('#datetimepicker3').datetimepicker({
		pickDate : false
	});
	$('#datetimepicker4').datetimepicker({
		pickDate : false
	});
	
	$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate : 0,
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			$("#todate").datepicker("option","minDate",selectedDate);
		}
	});	

	$("#todate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate : 0,
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			$("#Fromdate").datepicker("option","maxDate",selectedDate);
		}
	});	
	
	$("#Fromdate,#todate").change(function(){
		
		var d1 = $('#Fromdate').datepicker('getDate');
		var d2 = $('#todate').datepicker('getDate');
		var shalfday = $('#startsessionDay').val();
		var ehalfday = $('#endsessionDay').val();
		
	});
	
	$("#Fromdate,#todate,#leavetype").change(function(){
		
		if($("#Fromdate").val() != "" && $("#todate").val() != "" && $('#leavetype').val() != ""){
			checkDateDuplicacy();
		}
		
	});
	
	
	
	
	$('#Fromdate,#todate,#startsessionDay,#endsessionDay,#leavetype').change(function(){
		var total ="";
		var shalfday = $('#startsessionDay').val();
		var ehalfday = $('#endsessionDay').val();
		var d1 = $('#Fromdate').datepicker('getDate');
		var d2 = $('#todate').datepicker('getDate');
		var startDate = $('#Fromdate').val();
		var endDate = $('#todate').val();
		var leavetype = $('#leavetype').val();
		var oneDay = 24*60*60*1000;
		var diff = 0;
		if (d1 && d2) {
			diff = Math.round(Math.abs((d2.getTime() - d1.getTime())/(oneDay))+1);
			var totalSaturday = 0;
		    var totalSundays = 0;
		    for (var i = d1; i <= d2;) {

		        if (i.getDay() == 0) {
		            totalSundays++;
		        }

		        if (i.getDay() == 6) {

		                var day=i.getDate() / 7;
		                var week = Math.floor(day);
		                if(day.toString().indexOf('.')==1)
		                {
		                    if(week =="1" || week == "3"){
		                        totalSaturday++;                        
		                    } 
		                }
		                else
		                {   if(week =="2" || week == "4"){
		                    totalSaturday++;                        
		                }
		                }
		            }

		        i.setTime(i.getTime() + 1000 * 60 * 60 * 24);
		    }
		}
		
		
	
		var leaveTypeText=$('#leavetype option:selected').text();
		
		
				
			
		var total_leave=diff;

		if(shalfday == "FH" && ehalfday == "FH" ){
			if(leaveTypeText.match('Half')=='Half'){
				if(Number($(".tdCasual").text())==0){
					total=(total_leave-0.5)*2;
				}
				else{
					total=(total_leave-0.5-totalSundays-totalSaturday)*2;
				}
				
			}
			else{
				total = total_leave-0.5-totalSundays-totalSaturday;
			}
			
		}

		else if(shalfday == "FH" && ehalfday == "SH"){
			if(leaveTypeText.match('Half')=='Half'){
				if(Number($(".tdCasual").text())==0){
					total=(total_leave)*2;
				}
				else{
					total=(total_leave-totalSundays-totalSaturday)*2;
				}
				
			}
			else{
				total = total_leave-totalSundays-totalSaturday;
			}
			
		}

		else if(shalfday == "SH" && ehalfday == "SH" ){
			if(leaveTypeText.match('Half')=='Half'){
				if(Number($(".tdCasual").text())==0){
					total=(total_leave-0.5)*2;
				}
				else{
					total=(total_leave-0.5-totalSundays-totalSaturday)*2;
				}
			}
			else{
				total = total_leave-0.5-totalSundays-totalSaturday;
			}
		}
		else if( (shalfday == "SH" && ehalfday == "FH"))
		{
			if(leaveTypeText.match('Half')=='Half'){
				if(leaveTypeText.match('Half')=='Half'){
					total = (total_leave-1)*2;
				}
				else{
					total = (total_leave-1-totalSundays-totalSaturday)*2;
				}
			
			}
			else{
				total = total_leave-1-totalSundays-totalSaturday;
			}
			
		}
		
		$('#totalleaves').val(total);
		
		if($("#Fromdate").val() != "" && $("#todate").val() != "" && $("#startsessionDay").val()!= "" && $("#endsessionDay").val() != "" && $('#leavetype').val() != ""){
			checkLeaveCount();
		}
		
		if($("#Fromdate").val() != "" && $("#todate").val() != "" && $("#startsessionDay").val()!= "" && $("#endsessionDay").val() != "" && $('#leavetype').val() != ""){
			checkLeaveEligibility();
		}
		
		
		/*if(round == 0.0){
			$('.errormessagediv').show();
			$('.validateTips').text("You Are Not Eligible to apply leave in this Month");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(total > round){
			$('.errormessagediv').show();
			$('.validateTips').text("You Are Eligible to apply only "+round+" Days");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}*/
		
	});
	
	
	
	
	
/*	$('#allstudent').hide();*/
	
	
	var fileupload = $("#hiddenprofile").val();
	
	$("#document2btn").attr('name',$("#hiddenprofile").val());
	
	$('#document2btn')
	.click(
			function() {
				
				var path = $(this).attr('name');
			
				
				if(path == "" || path == null){
					
					$('.errormessagediv').show();
					$('.validateTips').text("No File for download");
					return false;
					
				}
				else{
					
					window.location.href = "teachermenuaction.html?method=downloadfile&Path="
						+ path.trim();
				}
				
			});
	
	
	var userhiddenid=$("#userhiddenid").val();
	
	var hidden = $('#hiddenrequestto').val();
	var hidden1 = $('#hiddenleavetype').val();
	
	
	var startsession = $('#hiddenstartsession').val();
	var endsession = $('#hiddenendsession').val();
	var studentname = $('#hiddenstudent').val();
	
	
	$("#requesttoid option[value=" + hidden + "]").attr('selected', 'true');
	$("#leavetype option[value=" + hidden1 + "]").attr('selected', 'true');
	
	$("#startsessionDay option[value=" + startsession + "]").attr('selected', 'true');
	$("#endsessionDay option[value=" + endsession + "]").attr('selected', 'true');
	
	$("#parentchild option[value=" + studentname + "]").attr('selected', 'true');
	
	
	
	
	$("#save").click(function(){

		var requestto = $('#requesttoid').val();
		var Fromdate = $('#Fromdate').val();
		var starttime = $('#startsessionDay').val();
		var leavetype = $('#leavetype').val();
		var reason = $('#reason').val();
		var totalleaves = $('#totalleaves').val();
		var todate = $('#todate').val();
		var endtime = $('#endsessionDay').val();
		var fileupload = $('#fileupload').val();
		var studentnameid = $('#parentchild').val();
		var sno = $('#hiddensno').val();

		if(requestto==""||requestto==null){

				$('.errormessagediv').show();
				$('.validateTips').text("Select RequestTo ");
				document.getElementById("requesttoid").style.border = "1px solid #AF2C2C";
				document.getElementById("requesttoid").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
		}

		if(Fromdate==""||Fromdate==null){

			$('.errormessagediv').show();
			$('.validateTips').text("Select From Date ");
			document.getElementById("Fromdate").style.border = "1px solid #AF2C2C";
			document.getElementById("Fromdate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		if(todate==""||todate==null){

			$('.errormessagediv').show();
			$('.validateTips').text("Select To Date ");
			document.getElementById("todate").style.border = "1px solid #AF2C2C";
			document.getElementById("todate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;

		}
		if(starttime==""||starttime==null){

			$('.errormessagediv').show();
			$('.validateTips').text("Select Session Start Session ");
			document.getElementById("startsessionDay").style.border = "1px solid #AF2C2C";
			document.getElementById("startsessionDay").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		if(endtime==""||endtime==null){

			$('.errormessagediv').show();
			$('.validateTips').text("Select Session End Session ");
			document.getElementById("endsessionDay").style.border = "1px solid #AF2C2C";
			document.getElementById("endsessionDay").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		if(leavetype==""||leavetype==null){
			$('.errormessagediv').show();
			$('.validateTips').text("Select  Leave Type");
			document.getElementById("leavetype").style.border = "1px solid #AF2C2C";
			document.getElementById("leavetype").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else
		{
			document.getElementById("leaverequestid").submit();
			$('.errormessagediv').hide();
			
		}

	});
	
	/*var sucessmsg = $('#success').val();

	if (!sucessmsg == "") {
		$(".errormessagediv").hide();
		$(".successmessagediv").show();
		$(".validateTips").show();
		$(".validateTips").text("Leave Updated Successfully");
	}*/
});

function callAjax(urlWithMethod,dataToBeSend){
	
	var jsonResult="";
	try{
	$.ajax({
		type : "GET",
		url : urlWithMethod,
		data : dataToBeSend,
		async:false,
		success : function(response) {
			
			var result = $.parseJSON(response);
		
			jsonResult=result;
		}
	});
	}
	catch (e) {
	
		jsonResult="";
	}

	return jsonResult;
}


function dateConverter(dateString){
	var dateArray=[];
	var dateStringArray=dateString.split("-");
	dateArray.push(dateStringArray[2]);
	dateArray.push(dateStringArray[1]);
	dateArray.push(dateStringArray[0]);
	var dateString1 = dateArray.join("-");
	return dateString1;
	
}



function view(){
	
	 var userhiddenid=$("#userhiddenid").val();
	 var teachername ={"teachername" : userhiddenid};
	
		$.ajax({
			
			type : "GET",
			url : "teachermenuaction.html?method=viewLeaveBalance",
			data : teachername,
			async : false,

			success : function(
					data) {
				
				var result = $.parseJSON(data);
				
				var openbalancesl = (result.leavedetails[0].openingbal);
				var consumedbalsl = (result.leavedetails[0].consumebal);
				var closingbalancesl = (result.leavedetails[0].closingbal);
			 
			    var openbalancepl = (result.leavedetails[1].openingbal);
			    var consumedbalpl = (result.leavedetails[1].consumebal);
				var closingbalancepl = (result.leavedetails[1].closingbal);
				
				var openbalancecl = (result.leavedetails[2].openingbal);
				var consumedbalcl = (result.leavedetails[2].consumebal);
				var closingbalancecl = (result.leavedetails[2].closingbal);
				$('#allstudent').show();
				
				$('#opensl').text(openbalancesl);
				$('#consumedsl').text(consumedbalsl);
				$('#closesl').text(closingbalancesl);
				
				$('#openpl').text(openbalancepl);
				$('#consumedpl').text(consumedbalpl);
				$('#closepl').text(closingbalancepl);
				
				$('#opencl').text(openbalancecl);
				$('#consumedcl').text(consumedbalcl);
				$('#closecl').text(closingbalancecl);
			}
		});
	
}

function calculate() {
    var d1 = $('#Fromdate').datepicker('getDate');
    var d2 = $('#todate').datepicker('getDate');
    var oneDay = 24*60*60*1000;
    var diff = 0;
    if (d1 && d2) {
      diff = Math.round(Math.abs((d2.getTime() - d1.getTime())/(oneDay))+1);
    }
   $('#totalleaves').val(diff);
}

function checkLeaveCount(){
	var d1 = $('#Fromdate').datepicker('getDate');
	var d2 = $('#todate').datepicker('getDate');
	var oneDay = 24*60*60*1000;
	var diff = 0;
	if (d1 && d2) {
		diff = Math.round(Math.abs((d2.getTime() - d1.getTime())/(oneDay))+1);
	}
	var startDate = $('#Fromdate').val();
	var endDate = $('#todate').val();
	var leavetype = $('#leavetype').val();
	var total = $('#totalleaves').val();
	var employeeType=$('#employeeType').val();
	datalist = {
			"total" : total,
			"startDate" :startDate,
			"endDate" :endDate,
			"daysDiff" : diff,
			"leavetype" : leavetype,
			"employeeType":employeeType
	};
	$.ajax({
		
		type : "POST",
		url : "teachermenuaction.html?method=checkLeaveCount",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			
			var tot_leaves = (result.tot_leaves[0].leaveCountSum);
		
			
			 if(total > tot_leaves ){
				$('.errormessagediv').show();
				$('.validateTips').text("You can take Maximum "+tot_leaves+" days leaves for this month");
				setTimeout(function() {
					$('#startsessionDay').val("");
					$('#endsessionDay').val("");
					$('#totalleaves').val("");
				},3000);
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				},10000);
				return false;
			}
		}
	});
}

function checkDateDuplicacy(){

	var Fromdate = $('#Fromdate').val();
	var todate = $('#todate').val();
	var leavetype = $('#leavetype').val();
	datalist ={
		"Fromdate":Fromdate,
		"todate":todate,
		"leavetype":leavetype
	}
	
	$.ajax({
		type :"post",
		url :"teachermenuaction.html?method=checkDateDuplicacy",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			if(result.status == "Leave applied pending for approve"){
				$('.errormessagediv').show();
				$('.validateTips').text("Leave already applied for these dates pending for approval");
				$('#Fromdate').val("");
				$('#todate').val("");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				},10000);
			}
			else if(result.status == "You already applied leave"){
				$('.errormessagediv').show();
				$('.validateTips').text("Leave already taken for these dates");
				$('#Fromdate').val("");
				$('#todate').val("");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				},10000);
			}
		}
	});
}

	function checkLeaveEligibility(){
		
		var Fromdate = $('#Fromdate').val();
		var leavetype = $('#leavetype').val();
		
		datalist = {
				"Fromdate" : Fromdate,
				"leavetype" :leavetype,
		}
		
		$.ajax({
			type :"POST",
			url :"teachermenuaction.html?method=checkLeaveEligibility",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				if(result.status == "true"){
					$('.errormessagediv').show();
					$('.validateTips').text("You already applied the leaves for the selected dates");
					$('#Fromdate').val("");
				    $('#todate').val("");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					},5000);
				}
			}
		});
	}

function HideError() 
{
document.getElementById("leavetype").style.border = "1px solid #ccc";
document.getElementById("leavetype").style.backgroundColor = "#fff";

document.getElementById("endsessionDay").style.border = "1px solid #ccc";
document.getElementById("endsessionDay").style.backgroundColor = "#fff";

document.getElementById("startsessionDay").style.border = "1px solid #ccc";
document.getElementById("startsessionDay").style.backgroundColor = "#fff";

document.getElementById("todate").style.border = "1px solid #ccc";
document.getElementById("todate").style.backgroundColor = "#fff";

document.getElementById("Fromdate").style.border = "1px solid #ccc";
document.getElementById("Fromdate").style.backgroundColor = "#fff";

document.getElementById("requesttoid").style.border = "1px solid #ccc";
document.getElementById("requesttoid").style.backgroundColor = "#fff";

}

