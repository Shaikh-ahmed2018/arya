$(document).ready(function(){
	getEventRegistrationList();
	$("#academicYear").val($("#hacademicyaer").val());
	
	callDatePicker();
	$("#location").change(function(){
		getEventRegistrationList();
	});
	$("#academicYear").change(function(){
		getEventRegistrationList();
	});
	$("#searchname").keypress(function(e){
		getEventRegistrationList();
	});
	$("#search").click(function(){
		getEventRegistrationList();
	});
	
	$("#Register").click(function(){ 
		$("#hiddenEventId").val("");
		if($("#location").val()==undefined || $("#location").val()==""){
			showError("#location","Select School Name");
			return false;
		}
		else{
			$(".importSett").show();
		$("#StudentNomineeDialog").dialog("open");
		
		
		}
		});

	$("#StudentNomineeDialog").dialog({
	    autoOpen  : false,
	    maxWidth  :	950,
        maxHeight : 500,
        width     : 900,
        height    : 480,
	    modal     : true,
	    title     : "Event Registration",
	    buttons   : {
	    	'Save'  : function() {
	    	var 	pointer=$(this);
	    		saveEvenRegistation(pointer);//saving the data into database:define
	    	},
	    	'Close' : function() {
                 $(this).dialog('close');
		    	 $("#imagePreview").attr("src","images/girl.png");
		    		$('input:text').val("");
					$('select').val("");
					$('textarea').val("");
					$('input:checkbox').prop("checked",false);
					$("#academicYear").val($("#hacademicyaer").val());
					getEventRegistrationList();
					
					$(".errormessagediv").hide();
		    		$(".form-control").css({
		    			"border":"1px solid #ccc",
		    			"background-color":"#fff"
		    		});
             }
	    }
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
	getEventRegistrationList();
	
	$("#resetbtn").click(function(){
		$('text').val("");
		$("input#searchname").val("");
		$('select').val("");
		$("#academicYear").val($("#hacademicyaer").val());
		getEventRegistrationList();
	});
	
	
$("#deleteDialog").dialog({
	autoOpen: false,
    modal: true,					    
    title:'Event Details',
    buttons : {

		"Yes" : function() {
			  datalist={
					"id":id,
					};
			$.ajax({
				type : 'POST',
				url:"EventsMenu.html?method=deleteEventRegistration",
				data:datalist,
				success : function(response) {
					var result = $.parseJSON(response);
					if(result.status=="true"){
						$(".errormessagediv").show();
						$(".validateTips").text("Selected Event is Mapped Cannot be Deleted");
						$('.errormessagediv').delay(3000).slideUp();
						getEventRegistrationList();
					}
					else if(result.status == "false"){
						$(".successmessagediv").show();
						$(".validateTips").text("Delete Record Progressing...");
						$('.successmessagediv').delay(3000).slideUp();
						getEventRegistrationList();
						/*setTimeout(function(){
							location.reload();
						},3000*///)
					}
				  }
				});
			 $(this).dialog('close');
			  },

		"No" : function() {
			  $(this).dialog('close');
			  
		}
	}
});

eventdeatils();
	
});// end jquery

 
function saveEvenRegistation(pointer){
	var accyear= $("#academicYear").val();
	var location=$("#location").val();
	var eventName =$("#eventNameSave").val().trim();
	var startsOn = $("#startsOn").val();
	var endsOn = $("#endsOn").val();
	var eventType = $("#eventType").val();
	var strtReg = $("#strtReg").val();
	var endReg = $("#endReg").val();
	var isAprovPps = $("#isAprovPps").val();
	var eventNameHidden = $("#eventNameHidden").val();
	
	var locationIdHidden = $("#locationIdHidden").val();
	var importSettings=$("#importSettings").val();
	
	if($("#isAprovPps").prop("checked")==true){
		isAprovPps="yes";
	}
	else{
		isAprovPps="no";
	}
	var isHouseWise = $('#isHouseWise').val();
	if($("#isHouseWise").prop("checked")==true){
		isHouseWise="yes";
	}
	else{
		isHouseWise="no";
	}
	var status = $("#status").val();
	var description = $("#description").val().trim();
	var hiddenEventId =$("#hiddenEventId").val();
	datalist ={
			"accyear":accyear,
			"location":location,
			"eventName":eventName,
			"startsOn":startsOn,
			"endsOn":endsOn,
			"eventType":eventType,
			"strtReg":strtReg,
			"endReg":endReg,
			"isAprovPps":isAprovPps,
			"isHouseWise":isHouseWise,
			"status":status,
			"description":description,
			"hiddenEventId":hiddenEventId,
			"eventNameHidden":eventNameHidden,
			"locationIdHidden":locationIdHidden,
			"importSettings":importSettings,
	};
	if(eventName=="" || eventName==undefined){
		showError("#eventNameSave","Enter Event Name");
		return false;
	}
	else if(accyear=="" || accyear==undefined){
		showError("#location","Select Academic Year");
		return false;
	}
	else if(startsOn=="" || startsOn==undefined){
		showError("#startsOn","Enter Event Start On");
		return false;
	}
	else if(endsOn=="" || endsOn==undefined){
		showError("#endsOn","Enter Event Ends On");
		return false;
	}
	else if(eventType=="" || eventType==undefined){
		showError("#eventType","Enter EventType");
		return false;
	}
	else if(strtReg=="" || strtReg==undefined){
		showError("#strtReg","Enter Start Registration Date");
		return false;
	}
	else if(endReg=="" || endReg==undefined){
		showError("#endReg","Enter End Registration Date");
		return false;
	}
	else{
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=SaveEventRegistration",
		data:datalist,
		async:false,
		beforeSend:function(){
			if(importSettings!="noImport"){
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Importing Settings...");
			}
		},
		success: function(data){
				var result =$.parseJSON(data);
		
			if(result.status=="true"){
	 			$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Adding Record Progressing...");
				$(".successmessagediv").delay(3000).fadeOut();
				setTimeout(function() {
					window.location.href="EventsMenu.html?method=EventRegistration";
			}, 2000);
				
				
				pointer.dialog('close');
				getEventRegistrationList();	
				$("input:text").val("");
				$("textarea").val("");
				$("input:checkbox").prop("checked",false);
		 }	
			else if(result.status=="UpdateTrue"){
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Update Record Progressing...");
				$(".successmessagediv").delay(3000).fadeOut();
				getEventRegistrationList();	
				$("input:text").val("");
				$("textarea").val("");
				$("input:checkbox").prop("checked",false);
				$("#StudentNomineeDialog").dialog('close');
			
			}
			else if(result.status=="insertDuplicate"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Event Name Already Exists");
				$(".errormessagediv").delay(3000).fadeOut();
			
			}
			else if(result.status=="duplicateUpdate"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Event Name Already Exists");
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
  }//else
}//function


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
	$(".errormessagediv").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}
 function getEventRegistrationList(){
	 var locationId = $("#location").val();
	 var accyear =  $("#academicYear").val();
	 var saerchTerm  = $("#searchname").val().trim();
	 if(locationId =="" || locationId==undefined){
		 locationId = "all";
	 }
	 if(accyear ==" " || accyear==undefined){
		 accyear="all";
	 }
	 if(saerchTerm ==" " || saerchTerm==undefined){
		 saerchTerm="all";
	 }
	
	 var dataList={
			 "locationId":locationId,
			 "accyear":accyear,
			 "saerchTerm":saerchTerm,
	 };
	 
	 $.ajax({
			type:'POST',
			url:"EventsMenu.html?method=getEventRegistrationList",
			data:dataList,
			async:false,
			
			success:function(data){
				var result =$.parseJSON(data);
				$("#allstudent tbody").empty();
				if(result.data.length>0){
					for(var i=0;i<result.data.length;i++){
						
					$("#allstudent tbody").append("<tr id='"+result.data[i].eventId+"' class='"+result.data[i].status+"'>"
					+"<td>"+(i+1)+"</td>"
					+"<td>"+result.data[i].location+"</td>"
					+"<td>"+result.data[i].accyear+"</td>"
					+"<td>"+result.data[i].eventName+"</td>"
					+"<td> &nbsp  From  &nbsp "+result.data[i].startsOn+"  &nbsp  To  &nbsp "+result.data[i].endsOn+"</td>"
					+"<td style='text-align:center' class='column_"+result.data[i].status+"'><span  class='"+result.data[i].status+"'>"+result.data[i].status+"</span></td>"
					+"<td><span style='height:20px;line-height: 16px;' id='"+result.data[i].eventId+"' class='glyphicon glyphicon-trash deleteRow'></span></td>" +
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
					
					
					deleteSelectedRow();
				}else{
					$("#allstudent tbody").append("<tr><td colspan='6'>No Record Found</td></tr>");
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No of Records "+result.data.length+".");
				pagination(50);
			}
		});
 }
 function rowClickable(){
	 
	
			$(".importSett").hide();
		
		$("#allstudent tbody tr td").not("#allstudent tbody tr td:last-child").click(function(){
			var id = $(this).closest("tr").attr("id");
			statusreg=$(this).closest("tr").attr("class");
			if(statusreg=="CLOSED"){
				$("input,textarea").attr("readonly",true);
				$("#startsOn,#strtReg,#endsOn,#endReg").datepicker("destroy");
				$("input[type='checkbox']").change(function(){
					if(statusreg=="CLOSED"){
					if($(this).prop("checked")==false){
						$(this).prop("checked",true);
					}
					else{
						$(this).prop("checked",false);
					}
					}
				});
				$(".ui-dialog-buttonset button:first").hide();
			}
			else{
				
				$(".ui-dialog-buttonset button:first").show();
				$("input,textarea").not("#startsOn,#strtReg,#endsOn,#endReg").attr("readonly",false);
				callDatePicker();
			}
			$("#StudentNomineeDialog").dialog("open");
			$("#hiddenEventId").val(id);
			getDataForUpdate(id);
			}); 
	 
 }
function deleteSelectedRow(){
	
	$(".deleteRow").click(function(){
		id=$(this).attr("id");
		
			$("#deleteDialog").dialog("open");
			$("#deleteDialog").empty();
			$("#deleteDialog").append("<p>Are you sure to delete?</p>");
	});
	}
function getDataForUpdate(id){
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getDataForUpdate",
		data:{"id":id},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			
			$("#eventNameSave").val(result.DataList[0].eventName);
			$("#startsOn").val(result.DataList[0].startsOn);
			$("#endsOn").val(result.DataList[0].endsOn);
			$("#eventType").val(result.DataList[0].eventType);
			$("#strtReg").val(result.DataList[0].startsRegis).attr("");
			$("#endReg").val(result.DataList[0].endsRegis);
			$("#locationIdHidden").val(result.DataList[0].locationIdHidden);
			$("#eventNameHidden").val(result.DataList[0].eventName);
			
			if(result.DataList[0].isAprovPps=="yes"){
				$("#isAprovPps").prop("checked",true);
			}
			else{
				$("#isAprovPps").prop("checked",false);
			}
			if(result.DataList[0].ishouseWise=="yes"){
				$("#isHouseWise").prop("checked",true);
			}
			else{
				$("#isHouseWise").prop("checked",false);
			}
			
			$("#status").val(result.DataList[0].status);
			$("#description").val(result.DataList[0].description);
		}
		//if($(".CLOSED"))
		
		});
	}
	
function callDatePicker(){
	
	$("#startsOn").datepicker({
		dateFormat : "yy-mm-dd",
		minDate : 0,
		changeMonth : true,
		changeYear : true,
	
			onSelect:function(dateStr){
				
				
		        var min = $(this).datepicker('getDate'); // Get selected date
		        var max=$(this).datepicker('getDate');
		        max.setDate(max.getDate()-1);
		      
		       
		        $("#endsOn").datepicker('option', 'minDate', min || $("#startsOn").val()); 
		        $("#strtReg").datepicker('option', 'maxDate', max || $("#startsOn").val()-1); 
		        $("#endReg").datepicker('option', 'maxDate', max || $("#startsOn").val()-1); 
		   
		
	}
		
	});	

	$("#endsOn").datepicker({
		dateFormat : "yy-mm-dd",
		minDate : 0,
		changeMonth : true,
		changeYear : true,
		onSelect:function(dateStr){
			
			
	        var max = $(this).datepicker('getDate'); // Get selected date
	        $("#startsOn").datepicker('option', 'maxDate', max || $("#endsOn").val()); // Set other min, default to today
	   
	
}
	});	
	
	$("#strtReg").datepicker({
		dateFormat : "yy-mm-dd",
		minDate : 0,
		changeMonth : true,
		changeYear : true,
	
			onSelect:function(dateStr){
				
				
		        var min = $(this).datepicker('getDate'); // Get selected date
		        $("#endReg").datepicker('option', 'minDate', min || $("#strtReg").val()); // Set other min, default to today
		   
		
	}
		
	});
	
	$("#endReg").datepicker({
		dateFormat : "yy-mm-dd",
		minDate : 0,
		changeMonth : true,
		changeYear : true,
		onSelect:function(dateStr){
			
			
	        var max = $(this).datepicker('getDate'); // Get selected date
	        $("#strtReg").datepicker('option', 'maxDate', max || $("#endReg").val()); // Set other min, default to today
	   
	
}
	});	
}
function eventdeatils(){
	
	 var dataList={
			 "locationId":'all',
			 "accyear":'all',
			 "saerchTerm":'all',
	 };
	$.ajax({
		type:"POST",
		url:"EventsMenu.html?method=getEventRegistrationList",
		data:dataList,
		async:false,
		
		success:function(data){
			var result =$.parseJSON(data);
			
			$("#importSettings").empty();
			$("#importSettings").append('<option value="noImport">----------SELECT----------</option>');
			for(var i=0;i<result.data.length;i++){
			
				$("#importSettings").append('<option value="'+result.data[i].eventId+'">'+result.data[i].eventName+'</option>');
			}
			
		}
		
	});
	
}