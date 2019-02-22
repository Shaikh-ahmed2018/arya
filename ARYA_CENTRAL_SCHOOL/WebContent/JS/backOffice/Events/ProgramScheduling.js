$(document).ready(function(){
	
	
	$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			
		}
	});	
	
	
	$("#datetimepicker3").datetimepicker({
		pickDate : false
		
	});
	
	$("#eventNameList").change(function()
			{
	
			});
			
			$("#Fromdate").change(function()
					{
				getProgramSchedulingList();
					});
	
			$("#excelDownload").click(function(){

				var eventNameList = $("#eventNameList").val();
				var prog_date=$("#Fromdate").val();
				
				
				

					window.location.href = 'EventsMenu.html?method=getProgramSchedulingExcelReport&eventNameList='+eventNameList+'&prog_date='+prog_date+'';
						
				
			});

			$("#pdfDownload").click(function(){
				
				var eventNameList = $("#eventNameList").val();
				var prog_date=$("#Fromdate").val();
				
				
				
				
				window.location.href = 'EventsMenu.html?method=getProgramSchedulingPdfReport&eventNameList='+eventNameList+'&prog_date='+prog_date+'';
				
			});
			$("#print").click(function(){
				
				
				var eventNameList = $("#eventNameList").val();
				var prog_date=$("#Fromdate").val();
				
				
				
				
				$.ajax({
					type: "POST",
					url:"EventsMenu.html?method=printProgramScheduledList&eventNameList="+eventNameList+"&prog_date="+prog_date,
					success : function(data){
						
					}
				});
			});
		
	$("#programScheduling").dialog({
			
		    autoOpen  : false,
		    maxWidth  :	950,
	        maxHeight : 400,
	        width     : 700,
	        height    : 525,
		    modal     : true,
		    title     : "Program Scheduling",
		    
		    buttons   : {
		    	'Save'  : function() {
		    		
		    		var pointer=$(this);
		    		saveProgramScheduling(pointer);
		    	},
		    	'Close' : function() {
		    		$('input:text').val("");
		    		$('textarea').val("");
		    		$('select.event').val("");
		    		
		    		$(this).dialog('close');
		    		$("#eventName option").hide();
		    		
		    		$(".errormessagediv").hide();
		    		$(".form-control").css({
		    			"border":"1px solid #ccc",
		    			"background-color":"#fff"
		    		});
	             }
		    }
		});
		
		
});
function saveProgramScheduling(pointer){
	
	var eventName = $("#eventName").val();
	var programmeName = $("#programmeList").val();
	var FromDate = $("#FromDate").val();
	var stageNameList = $("#stageNameList").val();
	var greenRoomList = $("#greenRoomList").val();
	var makeup =$("#makeup").val();
	var backStage =$("#backStage").val();
	var programTime=$("#programTime").val();
	var hiddenCriteriaId=$("#hiddenCriteriaId").val();
	

	if(eventName=="" || eventName==undefined){
		showError("#eventName","Enter Event Name");
		return false;
	}
	else if(programmeName=="" || programmeName==undefined){
		showError("#programmeName","Enter programme Name");
		return false;
	}
	else if(FromDate=="" || FromDate==undefined){
		showError("#FromDate","Enter Programme Date");
		return false;
	}
	else if(stageNameList=="" || stageNameList==undefined){
		showError("#stageNameList","Enter Stage Name");
		return false;
	}
	else if(greenRoomList=="" || greenRoomList==undefined){
		showError("#greenRoomList","Enter Green Room Name ");
		return false;
	}
	else if(programTime=="" || programTime==undefined){
		showError("#programTime","Enter Program Time  ");
		return false;
	}
	else if(programTime=="00:00:00")
	 {
		showError("#programTime","Enter  Valid Program Time  ");
		return false; 
	 }
	else if(programTime.substring(0,5)=="00:00")
		{
		showError("#programTime","Enter  Valid Program Time  ");
		return false; 
		}
else if(checkSchedulingDuplication() == 1){
		$("#FromDate").val("");
		$("#stageNameList").val("");
		$("#greenRoomList").val("");
		$("#programmeName").val("");
		$(".errormessagediv").show();
		$(".validateTips").text("Program Scheduling Details Already Exist..");
		$(".errormessagediv").delay(3000).fadeOut();
	}

	else{
		
	datalist={
			"eventName":eventName,
			"programmeName":programmeName,
			"FromDate":FromDate,
			"stageNameList":stageNameList,
			"greenRoomList":greenRoomList,
			"makeup":makeup,
			"backStage":backStage,
			"hiddenCriteriaId":hiddenCriteriaId,
			"programTime":programTime,
			};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=saveProgramScheduling",
		data:datalist,
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
				
				if(result.status=="true"){
		 			$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Adding Record Progressing....");
					$(".successmessagediv").delay(2000).fadeOut();
					$('input:text').val("");
		    		$('textarea').val("");
		    		$('select.event').val("");
		    		$("#eventName option").show();
					$("#programScheduling").dialog('close');
					getProgramSchedulingList();
				}
				else if(result.status=="updateTrue"){
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Updating Record Progressing......");
					$(".successmessagediv").delay(2000).fadeOut();
					
					$('input:text').val("");
		    		$('textarea').val("");
		    		$('select.event').val("");
					$("#programScheduling").dialog('close');
					$("#eventName option").show();
					getProgramSchedulingList();
				}
				else{
		 			$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Failed to Add Record...");
					$(".errormessagediv").delay(2000).fadeOut();
		 		 }
			}//success
		});//ajax
}
}
function rowClickable(){
	$("#allstudent tbody tr td").not("#allstudent tbody tr td:last-child").click(function(){
		id = $(this).closest("tr").attr("id");
		evenid = $(this).closest("tr").attr("class");
		updateCriteria(evenid);
		$("#hiddenCriteriaId").val(id);
		
	});
}

function updateCriteria(id){
	
	$("#programScheduling").dialog("open");
	$("#eventName option").hide();
	$("#programmeList option").hide();
	$("#stageNameList option").hide();
	$("#greenRoomList option").hide();
			$("#eventName").val(evenid.split(",")[0]);
			getProgrameeName();
			$("#programmeList").val(evenid.split(",")[1]);
		
			getstageName();
			$("#stageNameList").val(evenid.split(",")[2]);
			getGreenRoom();
			$("#greenRoomList").val(evenid.split(",")[3]);
			$("#FromDate").val(evenid.split(",")[4]);
			
			
			if(evenid.split(",")[5]=='-'){
				$("#programTime").val("");
				$("#makeup").val("");
				$("#backStage").val("");
				
			}else{
				$("#programTime").val(evenid.split(",")[7]);
				$("#makeup").val(evenid.split(",")[5]);
				$("#backStage").val(evenid.split(",")[6]);
			}
			
			$("#hiddenEventId").val(evenid.split(",")[0]);
			$("#hiddenProgramId").val(evenid.split(",")[1]);
			$("#hiddenProgramDate").val(evenid.split(",")[4]);
			$("#hiddenStage").val(evenid.split(",")[2]);
			$("#hiddenGreenRoom").val(evenid.split(",")[3]);
			$("#hiddenProgTime").val(evenid.split(",")[7]);
	}
	

function  checkSchedulingDuplication() {
	
	flag=0;
	
	var eventName = $("#eventName").val();
		var programmeList = $("#programmeList").val();
		var FromDate = $("#FromDate").val();
		var stageNameList = $("#stageNameList").val();
		var greenRoomList = $("#greenRoomList").val();
		
		
		
		var datalist = {
				"eventName" : eventName,
				"programmeList" : programmeList,
				"FromDate":FromDate,
				"stageNameList":stageNameList,
				"greenRoomList":greenRoomList,
				
		};
		
		if((eventName==$("#hiddenEventId").val()) &&(programmeList==$("#hiddenProgramId").val())&&(FromDate==$("#hiddenProgramDate").val())&&(stageNameList==$("#hiddenStage").val())&&(greenRoomList==$("#hiddenGreenRoom").val()))
			{
			flag=0;
			}
		else
			{
		$.ajax({
			type : "POST",
			url : "EventsMenu.html?method=validateScheduling",
			data : datalist,
			async : false,
			success : function(data) {
					var result = $.parseJSON(data);
					if(result.list =="true" ) {
					flag= 1;
				}else{
						flag=0;
					}
			}
		});
			}
		return flag;
	
}


function getProgramSchedulingList(){

	
	var eventNameList = $("#eventNameList").val();
	var prog_date=$("#Fromdate").val();
	
	if(eventNameList==""){
		eventNameList="all";
	}
	
	
	
$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getProgramSchedulingList",
		async:false,
		data:{"eventNameList":eventNameList,"prog_date":prog_date,},
		success:function(data){
			var result = $.parseJSON(data);
			
			
			$("#allstudent tbody").empty();  
			if(result.data.length==0)
			{
			$("#iconsimg").hide();
			$("#print").hide();
			
			return false; 
			
			}
		else
			{$("#iconsimg").show();
			$("#print").show();
			
			}
			if(result.data.length>0){
			for(var i=0; i<result.data.length; i++){
				
				$("#allstudent tbody").append("<tr id='"+result.data[i].scheduling_id+"' class='"+result.data[i].eventId+","+result.data[i].progId+","+result.data[i].stageId+","+result.data[i].greenRoomId+","+result.data[i].prog_date+","+result.data[i].makeup+","+result.data[i].backStage+","+result.data[i].prog_time+"'>" 
						+"<td>"+(i+1)+"</td>"
						+"<td>"+result.data[i].eventName+"</td>"
						+"<td>"+result.data[i].programmeName+"</td>"
						+"<td>"+result.data[i].stageName+"</td>"
						+"<td>"+result.data[i].greenRoomName+"</td>" 
						+"<td>"+result.data[i].prog_date+" &nbsp & &nbsp "+result.data[i].prog_time+"</td>"
						+"<td>"+result.data[i].makeup+"</td>" 
						+"<td>"+result.data[i].backStage+"</td>" 
					+"<td><span class='"+result.data[i].status1+"'>"+result.data[i].status+"</span></td>"
						+"<td><span class='glyphicon glyphicon-trash deleteRow'></span></td>"
						+"</tr>");
					}
			
			$(".deleteRow").hide();
			$(".Scheduled").closest("tr").find(".deleteRow").show();
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
			deleteProgramScheduling();
			}else{
				$("#allstudent tbody").append("<tr><td colspan=9'>No Record Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.data.length);
			pagination(50);
		}
	});     
}
function deleteProgramScheduling(){
	id=null;
	$(".deleteRow").click(function(){
		id=$(this).closest("tr").attr("id");
		$("#dialog2").dialog("open");
		$("#dialog2").empty();
		$("#dialog2").append("<p>Are you sure to delete?</p>");
});
	
	$("#dialog2").dialog({

		autoOpen: false,
		modal: true,
		title : "Program Scheduling Details",
		buttons : {

			"Yes" : function() {
            
            	
	$.ajax({
		type:"post",
		url:"EventsMenu.html?method=deleteProgramScheduling",
		data:{"id":id},
		async:false,
		success:function(data){
			var result = $.parseJSON(data);
			if(result.status=="true"){
				$(".successmessagediv").show();
				$(".validateTips").text("Deleting Record Progressing......");
				$(".successmessagediv").delay(3000).fadeOut();
				getProgramSchedulingList();
				
			}
		}
	});
	$(this).dialog("close");
			},
			"No" : function() {
				$(this).dialog("close");
			}
		}
	});

}
	
function getProgrameeName(){
	var id = $("#eventName").val();
	
	
	$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getProgramList",
	async:false,
	data:{"id":id,},
	success:function(data){
		var result = $.parseJSON(data);
		$("#programmeList").empty();
		$("#programmeList").append("<option value=''>-----select-----</option>");
		
		for(var i=0;i<result.data.length;i++){
		$("#programmeList").append("<option value='"+result.data[i].programmeId+"'>" 
				+result.data[i].programmeName+"</option>");
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
		$("#stageNameList").empty();
		$("#stageNameList").append("<option value=''>---select---</option>");
		
		for(var i=0;i<result.data.length;i++){
		$("#stageNameList").append("<option value='"+result.data[i].stageId+"'>" 
				+result.data[i].stageName+"</option>");
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
			$("#greenRoomList").empty();
			$("#greenRoomList").append("<option value=''>---select---</option>");
			
			for(var i=0;i<result.data.length;i++){
			$("#greenRoomList").append("<option value='"+result.data[i].greenRoomId+"'>" 
					+result.data[i].greenRoomName+"</option>");
			}
		}
	});
	}

	
	 
	
function showError(id,errorMessage){
	
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(2000).fadeOut();
}

