$(document).ready(function(){

	getAccyearbyGroup();

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
			if($(this).val()!="")
			checkDateOverLap($(this).val(),$(this));
		}
	});	

	$("#todate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate : 0,
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			$("#Fromdate").datepicker("option","maxDate",selectedDate);
			if($(this).val()!="")
			checkDateOverLap($(this).val(),$(this));
		}
	});	
	

	$("#academicYear").change(function(){ 
		
		if($(this).val()!=$("#hacademicyaer").val()){
			$('#Fromdate').datepicker('destroy');
			$('#todate').datepicker('destroy');
			dateRange($(this).val());
			
			$("#Fromdate").datepicker({
				maxDate:maxDate-1,
				minDate:minDate,
				changeMonth : true,
				changeYear : true,
				dateFormat : "dd-mm-yy",
				onSelect:function(dateStr){
					
					
					        var min = $(this).datepicker('getDate'); // Get selected date
					        $("#todate").datepicker('option', 'minDate', min || $("#lastDate").val()); // Set other min, default to today
					   
					
				}

			});

			$("#todate").datepicker({
				maxDate:maxDate-1,
				minDate:minDate,
				changeMonth : true,
				changeYear : true,
				dateFormat : "dd-mm-yy",
				onSelect:function(){
					
					var max = $(this).datepicker('getDate'); // Get selected date
			        $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
				}

			});
			
		//});
		
			
		
	}
		
		else{
			$('#Fromdate').datepicker('destroy');
			$('#todate').datepicker('destroy');
			$("#Fromdate").datepicker({
				dateFormat : "dd-mm-yy",
				minDate : 0,
				changeMonth : true,
				changeYear : true,
				onClose : function(selectedDate) {
					$("#todate").datepicker("option","minDate",selectedDate);
					if($(this).val()!="")
					checkDateOverLap($(this).val(),$(this));
				}
			});	

			$("#todate").datepicker({
				dateFormat : "dd-mm-yy",
				minDate : 0,
				changeMonth : true,
				changeYear : true,
				onClose : function(selectedDate) {
					$("#Fromdate").datepicker("option","maxDate",selectedDate);
					if($(this).val()!="")
					checkDateOverLap($(this).val(),$(this));
				}
			});	
		}
		$.ajax({
			type: 'POST',
			url : "ElectionMenu.html?method=getGroupNamebyAcademicYear",
			data :{
				"accyear" : $("#academicYear").val()
				},
		
			success :function(data){
			var result = $.parseJSON(data);
		
			$('#groupName').empty();
			$('#groupName').append('<option value="">----Select----</option>');
			for( var j=0;j<result.groupName.length; j++) {
				$('#groupName').append('<option value="'+result.groupName[j].groupid+ '">'
						+result.groupName[j].groupName+ '</option>'
						
				);
			}
			}
		});
	});
	

$("#save").click(function(){
		var acyear=$("#academicYear").val();
		var groupname=$("#groupName").val();
		var electionTitle=$("#electionTitle").val().trim();
		var Fromdate = $('#Fromdate').val();
		var todate = $('#todate').val();
		var starttime = $('#starttime').val();
		var endtime = $('#endtime').val();
	
		if(acyear == "" ||acyear == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Feild Required Academic Year");
			document.getElementById("academicYear").style.border = "1px solid #AF2C2C";
			document.getElementById("academicYear").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}
		
		else if(groupname == "" ||groupname == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Group Name");
			document.getElementById("groupName").style.border = "1px solid #AF2C2C";
			document.getElementById("groupName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}
		else if(electionTitle == "" ||electionTitle == null){
				$(".errormessagediv").show();
				$(".validateTips").text("Feild Required Election Title");
				document.getElementById("electionTitle").style.border = "1px solid #AF2C2C";
				document.getElementById("electionTitle").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				
				return false;
			}
		
		else if (Fromdate == "" || Fromdate == undefined) {
			//checkDateOverLap($(this).val(),$(this));
			$(".successmessagediv").hide();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Select the From Date");
			document.getElementById("Fromdate").style.border = "1px solid #AF2C2C";
			document.getElementById("Fromdate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv1').fadeOut();
			}, 3000);
			return false;
		}
		
		else if (todate == "" || todate == undefined) {
			//checkDateOverLap($(this).val(),$(this));
			$(".successmessagediv").hide();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Select the EndDate");
			document.getElementById("todate").style.border = "1px solid #AF2C2C";
			document.getElementById("todate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv1').fadeOut();
			}, 3000);
			return false;
		}
		else if(Fromdate > todate){
			checkDateOverLap($(this).val(),$(this));
			$(".successmessagediv").hide();
			$(".errormessagediv1").show();
			$(".validateTips1").text("End Date should greater than Start Date");
			document.getElementById("todate").style.border = "1px solid #AF2C2C";
			document.getElementById("todate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv1').fadeOut();
			}, 3000);
			return false;
		}
		else if (starttime == ""|| starttime == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Select Start Time");
		document.getElementById("starttime").style.border = "1px solid #AF2C2C";
		document.getElementById("starttime").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	} 
		else if (endtime == ""|| endtime == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Select End Time");
		document.getElementById("endtime").style.border = "1px solid #AF2C2C";
		document.getElementById("endtime").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
  	  }else if(!checkTime()){
		   return false;

	}
		datalist ={
				"acyear" : acyear,
				"groupname": groupname,
				"electionTitle": electionTitle,
				"Fromdate" : Fromdate,
				"todate" : todate,
				"starttime" : starttime,
				"endtime" : endtime,
	
			};
				$.ajax({
					type :'POST',
					url :"ElectionMenu.html?method=saveElectionDetails",
					data : datalist,
					async:false,
					success: function(data){
						var result = $.parseJSON(data);
				 		 
				 		 if(result.status=="true"){
				 			$('.errormessagediv').hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Adding Record Progressing...");
							$(".successmessagediv").delay(3000).fadeOut();
							setTimeout(function() {

								window.location.href="adminMenu.html?method=electionList";
						}, 2000);
				 		 }else if(result.status=="exists"){
					 			$('.successmessagediv').hide();
								$(".errormessagediv").show();
								$(".validateTips").text("Election Title is Already Exist in this Group");
								document.getElementById("electionTitle").style.border = "1px solid #AF2C2C";
								document.getElementById("electionTitle").style.backgroundColor = "#FFF7F7";
								$(".errormessagediv").delay(3000).fadeOut();
								
					 		 }
				 		else{
				 			$('.successmessagediv').hide();
							$(".errormessagediv").show();
							$(".validateTips").text("Fail to Add Record...");
							$(".errormessagediv").delay(3000).fadeOut();
							
				 		 }
			 		}//success
				});//ajax
				return false;
	});

$("#update").click(function(){
	
	var acyearh=$("#accyearh").val();
	var groupnameh=$("#groupnameh").val();
	var electionTitleh=$("#ElectionTitleh").val().trim();
	var electionTitleHName=$("#electionTitleHName").val().trim();
	var electionTitle=$("#electionTitle").val().trim();
	var Fromdate = $('#Fromdate').val();
	var todate = $('#todate').val();
	var starttime = $('#starttime').val();
	var endtime = $('#endtime').val();
	
	if (todate == "" || todate == undefined) {
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Select the EndDate");
		document.getElementById("todate").style.border = "1px solid #AF2C2C";
		document.getElementById("todate").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
		return false;
	}
	else if(Fromdate > todate){
		
		$(".successmessagediv").hide();
		$(".errormessagediv").show();
		$(".validateTips").text("End Date should greater than Start Date");
		document.getElementById("todate").style.border = "1px solid #AF2C2C";
		document.getElementById("todate").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
		return false;
	}
	else if (starttime == "" || starttime == null) {
	$('.errormessagediv').show();
	$('.validateTips').text("Select Start Time");
	document.getElementById("starttime").style.border = "1px solid #AF2C2C";
	document.getElementById("starttime").style.backgroundColor = "#FFF7F7";
	setTimeout(function() {
		$('.errormessagediv').fadeOut();
	}, 3000);
	return false;
} 
	else if (endtime == ""|| endtime == null) {
	$('.errormessagediv').show();
	$('.validateTips').text("Select End Time");
	document.getElementById("endtime").style.border = "1px solid #AF2C2C";
	document.getElementById("endtime").style.backgroundColor = "#FFF7F7";
	setTimeout(function() {
		$('.errormessagediv').fadeOut();
	}, 3000);
	return false;
	  }else if(!checkTime()){
	   return false;

}
	datalist ={
			"acyearh" : acyearh,
			"groupnameh": groupnameh,
			"electionTitleh": electionTitleh,
			"electionTitleHName": electionTitleHName,
			"electionTitle": electionTitle,
			"Fromdate" : Fromdate,
			"todate" : todate,
			"starttime" : starttime,
			"endtime" : endtime,
		};
			$.ajax({
				type :'POST',
				url :"ElectionMenu.html?method=UpdateElectionDetails",
				data : datalist,
				async:false,
				success: function(data){
					var result = $.parseJSON(data);
			 		 
			 		 if(result.status=="true"){
			 			$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Update Record Progressing...");
						$(".successmessagediv").delay(3000).fadeOut();
						setTimeout(function() {

							window.location.href="adminMenu.html?method=electionList";
					}, 2000);
			 		 }
			 		 
					 //checking the repeated data for update time
					 else  if(result.status=="exists"){
				 			$('.successmessagediv').hide();
							$(".errormessagediv").show();
							$(".validateTips").text("Election Title is Already Exist in this Group");
							document.getElementById("electionTitle").style.border = "1px solid #AF2C2C";
							document.getElementById("electionTitle").style.backgroundColor = "#FFF7F7";
							$(".errormessagediv").delay(3000).fadeOut();
							
				 		 }
					 else  if(result.status=="started"){
				 			$('.successmessagediv').hide();
							$(".errormessagediv").show();
							$(".validateTips").text("Election is Already Started");
							$(".errormessagediv").delay(3000).fadeOut();
							
				 		 }
			 		else{
			 			$('.successmessagediv').hide();
						$(".errormessagediv").show();
						$(".validateTips").text("Fail to Add Record...");
						$(".errormessagediv").delay(3000).fadeOut();
						
			 		 }
		 		}//success
			});//ajax
			return false;
});
	
	
	
});

function dateRange(accyear){
	
	$.ajax({
		type:"POST", 
		url:"ElectionMenu.html?method=daterange",
		data:{"accyear":accyear},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			minDate=result.startDate.split(",")[0];
			maxDate=result.startDate.split(",")[1];
			
		}
	
	});
}


function getAccyearbyGroup(){

	$.ajax({
		type : 'POST',
		url : "ElectionMenu.html?method=getAccYearByGroup",
		async : false,
		success : function(data) {
			
		var result = $.parseJSON(data);
	
		$('#academicYear').empty();
		$('#academicYear').append('<option value="">----Select----</option>');
		for ( var j = 0; j < result.accyarByGroup.length; j++) {

			$('#academicYear').append('<option value="'+ result.accyarByGroup[j].accid+ '">'
					+ result.accyarByGroup[j].accyear+ '</option>');

		}
		
		}
	});	
}

//function checkDateOverLap(date,pointer){
function checkDateOverLap(date,pointer){

	var acyearh=$("#accyearh").val();
	if(acyearh=="" || acyearh == undefined){
		acyearh=$("#academicYear").val();
	}
	var groupnameh=$("#groupnameh").val();
	if(groupnameh =="" || groupnameh == undefined){
		groupnameh=$("#groupName").val();
	}
	var electionTitleh=$("#ElectionTitleh").val();
	datalist ={
			"acyearh" : acyearh,
			"groupnameh": groupnameh,
			"electionTitleh": electionTitleh,
			"date" :date,
	};
	
	$.ajax({
		type : 'POST',
		url : "ElectionMenu.html?method=checkDateOverLap",
		data: datalist,
		async : false,
		success : function(data) {
			
		var result = $.parseJSON(data);
	
		if(result.status=="true"){
			 if(result.status=="true"){
				 pointer.val("");
				// document.getElementBypO.style.border = "1px solid #AF2C2C";
					$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Date is Assigned! Please Choose Different Date");
					$(".errormessagediv").delay(3000).fadeOut();
		 		 }
			 else{
		 			$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Date is not Assigned");
					$(".errormessagediv").delay(3000).fadeOut();
					
		 		 }
		}
	 		}//success
		});//ajax
		return false;
	
	
}



/*time validation*/
var status = false;
function checkTime() {
	
	var ftime = document.getElementById("starttime").value;
	var ttime = document.getElementById("endtime").value;
	
	
	
	if ((ftime != "") && (ttime != "")) {

		var ftimeSplitHour = ftime.split(':')[0];
		var ftimeSplitMin = ftime.split(':')[1];
		var ftimeSplitSec = ftime.split(':')[2];

		var ttimeSplitHour = ttime.split(':')[0];
		var ttimeSplitMin = ttime.split(':')[1];
		var ttimeSplitSec = ttime.split(':')[2];

		if (ftimeSplitHour.charAt(0) == 0) {
			ftimeSplitHour = ftimeSplitHour.charAt(1);
		}
		if (ttimeSplitHour.charAt(0) == 0) {
			ttimeSplitHour = ttimeSplitHour.charAt(1);
		}

		if (ftimeSplitMin.charAt(0) == 0) {
			ftimeSplitMin = ftimeSplitMin.charAt(1);
		}
		if (ttimeSplitMin.charAt(0) == 0) {
			ttimeSplitMin = ttimeSplitMin.charAt(1);
		}

		if (ftimeSplitSec.charAt(0) == 0) {
			ftimeSplitSec = ftimeSplitSec.charAt(1);
		}
		if (ttimeSplitSec.charAt(0) == 0) {
			ttimeSplitSec = ttimeSplitSec.charAt(1);
		}

		ftimeSplitHour = parseInt(ftimeSplitHour);
		ttimeSplitHour = parseInt(ttimeSplitHour);
		ftimeSplitMin = parseInt(ftimeSplitMin);
		ttimeSplitMin = parseInt(ttimeSplitMin);

		ftimeSplitSec = parseInt(ftimeSplitSec);
		ttimeSplitSec = parseInt(ttimeSplitSec);
		
	

		if (ftimeSplitHour > ttimeSplitHour) {
			$(".errormessagediv").show();
			$(".validateTips").text(
					"The End Time should not less than Start Time");
			return false;
			status = false;
			document.getElementById("endtime").value = "";
		}
		if (ttimeSplitHour == ftimeSplitHour) {
			if (ftimeSplitMin > ttimeSplitMin) {
				$(".validateTips").text("The End Time should not less than Start Time");
				$(".errormessagediv").show();
				
				return false;
				status = false;
				document.getElementById("endtime").value = "";

			}
			if (ftimeSplitMin == ttimeSplitMin) {
				if (ftimeSplitSec >= ttimeSplitSec) {
					$(".validateTips").text("The End Time should not less than Start Time");
					$(".errormessagediv").show();
					
					return false;
					status = false;
					document.getElementById("endtime").value = "";
				}
			}
		} else {
			$(".errormessagediv").hide();
			status = true;
		}
	} else {
		status = true;
	}
	return status;
}


/*onchange="HideError()"*/
function HideError() {
	document.getElementById("academicYear").style.backgroundColor = "#fff";
	document.getElementById("academicYear").style.border = "1px solid #ccc";
	
	document.getElementById("groupName").style.border = "1px solid #ccc";
	document.getElementById("academicYear").style.border = "1px solid #ccc";
	//document.getElementById("groupName").style.backgroundColor = "#fff";
	
	document.getElementById("electionTitle").style.backgroundColor = "#fff";
	document.getElementById("electionTitle").style.border = "1px solid #ccc";
	
	document.getElementById("Fromdate").style.backgroundColor = "#fff";
	document.getElementById("Fromdate").style.border = "1px solid #ccc";
	
	document.getElementById("todate").style.backgroundColor = "#fff";
	document.getElementById("todate").style.border = "1px solid #ccc";
	
	document.getElementById("starttime").style.backgroundColor = "#fff";
	document.getElementById("starttime").style.border = "1px solid #ccc";
	
	document.getElementById("endtime").style.backgroundColor = "#fff";
	document.getElementById("endtime").style.border = "1px solid #ccc";
	

	
}