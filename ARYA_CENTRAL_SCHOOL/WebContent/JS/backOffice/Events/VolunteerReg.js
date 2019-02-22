$(document).ready(function(){
	
	getEventName();
	getEventNameList();
	$("#eventNameList").change(function(){
		if($(this).val()==""){
			$("#allstudent tbody").empty();
			$("#allstudent tbody").append("<tr><td colspan='6'>No Record Found</td></tr>");
		}else{
			getVolunteerList();
		}
		
	});
	$("#schoolNameList").change(function(){
		
	});
	
	$("#addgroup").click(function(){
		$("#volunteer").dialog("open");
	});

	$("#eventName").change(function(){
		getGreenRoom();
		getstageName();
	});
	
	$("#volunteer").dialog({
	    autoOpen  : false,	
	    maxWidth  :	900,
        maxHeight : 550,
        width     : 580,
        height    : 410,
	    modal     : true,
	    title     : "volunteer Setting",
	    buttons   : {
	    	'Save'  : function() {
	    		var pointer =$(this);
	    		savevolunteer(pointer);
	    	},
	    	'Close' : function() {
	    		
	    		$(this).dialog("close");
	    		$('input:text').val("");
	    	
	    		$('#volunteerIdHidden').val("");
	    	},
	    }
	});
	$("#datetimepicker3").datetimepicker({
		pickDate : false
	});
	$("#datetimepicker4").datetimepicker({
		pickDate : false
	});
	
	$("#volanteeradmissionno").autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "EventsMenu.html?method=getAdmissionNoByVolanteer",
				data : {
					searchTerm : request.term,
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					response($.map(result.jsonResponse,function(item) {
						return {
							label : item.volanteerAdmissionNo,
							value : item.volanteerAdmissionNo,
						}
					}));
				}
			});
		},
		select : function(event, ui) {
			var searchTerm = ui.item.value;
			studentDetails = {
					'searchTerm' : searchTerm
			};
			$("#volanteeradmissionno").val(ui.item.label);
			return false;
		}
	});

	$("#deleteDialog").dialog({
		autoOpen  : false,
		maxWidth  :	300,
		maxHeight : 200,
		width     : 300,
		height    : 165,
		modal     : true,
		title     : "Volanteer Details",
		buttons   : {
			'Yes'  : function() {
			
				 deleteReport(id);
				$(this).dialog('close');
			},
			'No' : function() {
				$(this).dialog('close');
				$(".errormessagediv").hide();
			}
		}
	});
	
	$("#printIdCard").click(function(){
		var volId=[];
		$("#allstudent tbody tr").each(function(){
			volId.push($(this).attr("id"));
		});
		if(volId.length>0){
			window.location.href="EventsMenu.html?method=PrintPreviewEventMultipleIDCard&evenm="+$("#eventNameList").val()+"&evecat=VOLANTEER&prognm="+volId;
		}
		else{
			alert("No Record to print.")
		}
		
	});
 });

function savevolunteer(pointer){
	var eventName=$("#eventName").val();
	var schoolNameList=$("#schoolName").val();
	var volanteeradmissionno=$("#volanteeradmissionno").val().split("-")[0];
	var greenroomName=$("#greenroomname").val();
	var stagename=$("#stagername").val();
	var starttime = $("#starttime").val();
	var endtime = $("#endtime").val();
	var volnteerhiddenid=$("#volunteerIdHidden").val();
	
	if(schoolNameList=="" || schoolNameList==undefined){
		$(".errormessagediv").show();
		$(".validateTips").text("select school Name");
		showError("#schoolName");
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
			return false;
	}
	
	else if((eventName=="") || eventName==undefined || eventName==null){
		$(".errormessagediv").show();
		$(".validateTips").text("select Event Name");
		showError("#eventName");
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
			return false;
	}
	
	else if(stagename=="" || stagename==undefined){
		$(".errormessagediv").show();
		$(".validateTips").text("select Stage Name");
		showError("#stagername");
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
			return false;
	}
	else if(greenroomName=="" || greenroomName==undefined){
		$(".errormessagediv").show();
		$(".validateTips").text("selct GreenRoom");
		showError("#greenroomname");
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
			return false;
	}
	else if(volanteeradmissionno=="" || volanteeradmissionno==undefined){
		$(".errormessagediv").show();
		$(".validateTips").text("enter Admission No");
		showError("#volanteeradmissionno");
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
			return false;
	}
	
	else if(starttime=="" || starttime==undefined){
		$(".errormessagediv").show();
		$(".validateTips").text("enter Start Time");
		showError("#starttime");
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
			return false;
	}
	else if(endtime=="" || endtime==undefined){
		$(".errormessagediv").show();
		$(".validateTips").text("enter End Time");
		showError("#endtime");
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
			return false;
	}else if(!checkTime()){
		   return false;

	}
		datalist = {
			"eventName":eventName,
			"schoolNameList":schoolNameList,
			"volanteeradmissionno":volanteeradmissionno,
			"greenroomName":greenroomName,
			"stagename":stagename,
			"starttime":starttime,
			"endtime":endtime,
			"volnteerhiddenid":volnteerhiddenid
		},
		$.ajax({
		type : 'POST',
		url : "EventsMenu.html?method=savevolunteer",
		data:datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			if(result.status=="success"){
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Adding Record Progressing...");
					$(".successmessagediv").delay(3000).fadeOut();
					$("#volunteer").dialog("close");
					getVolunteerList();
					$('#volunteerIdHidden').val("");
					$('input:text').val("");
		    		$('#volunteer select').val("");
		    		$('#volunteerIdHidden').val("");
			}else if(result.status=="update"){
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Updating Record Progressing...");
				$(".successmessagediv").delay(3000).fadeOut();
				$("#volunteer").dialog("close");
				getVolunteerList();
				$('#volunteerIdHidden').val("");
				$('input:text').val("");
	    		$('#volunteer select').val("");
	    		$('#volunteerIdHidden').val("");
			}
    		else{
		 		$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Fail to Add Record...");
				$(".errormessagediv").delay(3000).fadeOut();
		 		 }
    		}
	});
}
function getEventName(){
	var id = $("#eventName").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getEventName",
		data :{"id":id},
		async :false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#eventName").empty();
			$("#eventName").append("<option value=''>--------Select--------</option>");
			
			for(var i=0;i<result.data.length;i++){
			$("#eventName").append("<option value='"+result.data[i].eventId+"'>" 
					+result.data[i].eventName+"</option>");
			}
		}
	});
}
function getEventNameList(){
	var id = $("#eventNameList").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getEventNameList",
		data :{"id":id},
		async :false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#eventNameList").empty();
			$("#eventNameList").append("<option value=''>------SELECT--------</option>");
			
			for(var i=0;i<result.data.length;i++){
			$("#eventNameList").append("<option value='"+result.data[i].eventId+"'>" 
					+result.data[i].eventName+"</option>");
			}
		}
	});
	}

function getGreenRoom(){
	var id = $("#eventName").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getGreenRoom",
		data :{"id":id},
		async :false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#greenroomname").empty();
			$("#greenroomname").append("<option value=''>--------Select--------</option>");
			
			for(var i=0;i<result.data.length;i++){
			$("#greenroomname").append("<option value='"+result.data[i].greenRoomId+"'>" 
					+result.data[i].greenRoomName+"</option>");
			}
		}
	});
	}
function getstageName(){
	var id = $("#eventName").val();
	$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getstageList",
	async:false,
	data:{"id":id},
	success:function(data){
		var result = $.parseJSON(data);
		$("#stagername").empty();
		$("#stagername").append("<option value=''>--------Select--------</option>");
		
		for(var i=0;i<result.data.length;i++){
		$("#stagername").append("<option value='"+result.data[i].stageId+"'>" 
				+result.data[i].stageName+"</option>");
		}
	}
});
}
function getVolunteerList(){
	var eveId = $("#eventNameList").val();
	var locId = $("#schoolNameList").val();
	var volanteeradmissionno=$("#volanteeradmissionno").val().split("-")[0];
	var greenroomName=$("#greenroomname").val();
	var stagename=$("#stagername").val();
	var starttime=$("#starttime").val();
	var endtime=$("#endtime").val();
	
	
	datalist ={
			"eveId":eveId,
			"locId":locId,
			"volanteeradmissionno":volanteeradmissionno,
			"greenroomName":greenroomName,
			"stagename":stagename,
			"starttime":starttime,
			"endtime":endtime
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getVolunteerList",
		data:datalist,
		async:false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#allstudent tbody").empty();                                  
			if(result.data.length>0){
			for(var i=0; i<result.data.length; i++){
				$("#allstudent tbody").append("<tr id='"+result.data[i].volanteerId+"'>" 
						+"<td>"+(i+1)+"</td>"
						+"<td>"+result.data[i].volanteerAdmissionNo+"</td>"
						+"<td>"+result.data[i].greenRoomName+"</td>" 
						+"<td>"+result.data[i].stageName+"</td>" 
						+"<td> &nbsp  From  &nbsp "+result.data[i].startTime+"  &nbsp  To  &nbsp "+result.data[i].endTime+"</td>" 
						+"<td><span class='glyphicon glyphicon-trash deleteRow'></span></td>"
						+"</tr>");
			}
			rowClickable();
			$("#allstudent tr td").mouseenter(function(){
				$(this).parent("tr").find("td").not("td:last-child").css({
					"background":"#9CDDE3"
				});
			});
			$("#allstudent tr td").mouseleave(function(){
				$(this).parent("tr").find("td").not("td:last-child").css({
					"background":"transparent"
				});
			});
			deleteRow();
			
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='6'>No Record Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append(" No. of Records "+result.data.length);
			pagination(100);
			
		}
		});
}

function deleteRow(){
	$(".deleteRow").click(function(){
	id =$(this).closest("tr").attr("id");
	$("#deleteDialog").dialog("open");
});
}
function rowClickable(){
	$("#allstudent tbody tr td").not("#allstudent tbody tr td:last-child").click(function(){
		id = $(this).closest("tr").attr("id");
		getDataforUpdateVolanteer(id);
	});
}
function getDataforUpdateVolanteer(id){
$("#volunteer").dialog("open");
$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getDataforUpdateVolanteer",
	data:{"id":id},
	asunc:false,
	success:function(data){
		var result =$.parseJSON(data);
		$("#eventName").val(result.data[0].eventId);
		getstageName();
		getGreenRoom();
		$("#schoolName").val(result.data[0].locId);
		$("#volanteeradmissionno").val(result.data[0].volanteerAdmissionNo);
		$("#greenroomname").val(result.data[0].greenRoomId);
		$("#stagername").val(result.data[0].stageId);
		$("#starttime").val(result.data[0].startTime);
		$("#endtime").val(result.data[0].endTime);
		$("#volunteerIdHidden").val(result.data[0].volanteerId)
	}
});
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
			$(".validateTips").text("The End Time should not less than Start Time");
			showError("#endtime");
			setTimeout(function() {
	            $('.errormessagediv').fadeOut();
                }, 3000);
			return false;
			status = false;
		}
		if (ttimeSplitHour == ftimeSplitHour) {
			if (ftimeSplitMin > ttimeSplitMin) {
				$(".validateTips").text("The End Time should not less than Start Time");
				$(".errormessagediv").show();
				showError("#endtime");
				setTimeout(function() {
		            $('.errormessagediv').fadeOut();
	                }, 3000);
				return false;
				status = false;
			}
			if (ftimeSplitMin == ttimeSplitMin) {
				if (ftimeSplitSec >= ttimeSplitSec) {
					$(".validateTips").text("The End Time should not less than Start Time");
					$(".errormessagediv").show();
					showError("#endtime");
					setTimeout(function() {
			            $('.errormessagediv').fadeOut();
		                }, 3000);
					return false;
					status = false;
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
function showError(id){
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
}
function deleteReport(id){
	 datalist = {
		"id" :id
	 };
	 $.ajax({
			type : "POST",
			url : "EventsMenu.html?method=deletevolanteer",
			data : datalist,
			async : false,
			success : function(data){
				var result = $.parseJSON(data);
				
				if((result.status=="true")){
					$(".successmessagediv").show();
					$(".validateTips").text("Deleting Record successfully...");
					$('.successmessagediv').fadeOut(3000);
					setTimeout(function() {
						location.reload();
					},2000);
			     }
			}
		});
}
