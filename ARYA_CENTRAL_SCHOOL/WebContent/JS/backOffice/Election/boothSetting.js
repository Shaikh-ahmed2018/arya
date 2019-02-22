var pattern = /\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/;
$(document).ready(function() {
	var boothid = null;
	var pollingId =null;
	getBoothNameDropdown();
	getClassList();//for getting all classes list
	getStaffNameList();
	getPollingMachineList();
	deletePoleSelectedRow();
$("#pollingMachineName").keypress(function (e) {
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
     return false;
    }
});
	//change function for listing page
	$("#academicYear").change(function(){
		getBoothDetailsList();
		getPollingMachineList();
	});
	$("#groupName").change(function(){
		getBoothDetailsList();
		getPollingMachineList();
	});
	$("#electionTitle").change(function(){
		getBoothDetailsList();
		getPollingMachineList();
	});

	$("#save").click(function() {
		if($("#academicYear").val()=="all"){
			$('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Select Academic Year.");
			$(".errormessagediv").delay(2000).fadeOut();
		}
		else if($("#groupName").val()=="all"){
			$('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Select Group Name.");
			$(".errormessagediv").delay(2000).fadeOut();
		}
		else if($("#electionTitle").val()=="all"){
			$('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Select Election Title.");
			$(".errormessagediv").delay(2000).fadeOut();
		}
		else{
		$("#electionCategoryAddDialog").dialog("open"); 
		}
		});
	$("#savedata").click(function() {
		if($("#academicYear").val()=="all"){
			$('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Select Academic Year.");
			$(".errormessagediv").delay(2000).fadeOut();
		}
		else if($("#groupName").val()=="all"){
			$('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Select Group Name.");
			$(".errormessagediv").delay(2000).fadeOut();
		}
		else if($("#electionTitle").val()=="all"){
			$('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Select Election Title.");
			$(".errormessagediv").delay(2000).fadeOut();
		}
		else{
		$("#electionCategoryAddDialog1").dialog("open"); 
		getBoothNameDropdown();
		}
	});

	$("#electionCategoryAddDialog").dialog({
	    autoOpen  : false,
	    maxWidth  :	750,
	    maxHeight : 500,
	    width     : 750,
	    height    : 450,
	    modal     : true,
	    title     : "Category Setting",
	    buttons   : {
	    	'Save'  : function() {
	    		saveBoothDetails($(this));
	    		getBoothNameDropdown();
	    		
	    	},
	    	'Close' : function() {
	    		getBoothNameDropdown();
	             $(this).dialog('close');
	         }
	    }
	});
	$("#electionCategoryAddDialog1").dialog({
	    autoOpen  : false,
	    maxWidth  :	750,
	    maxHeight : 500,
	    width     : 750,
	    height    : 300,
	    modal     : true,
	    title     : "Polling Machine",
	    buttons   : {
	    	'Save'  : function() {
	    				savePollingMachineDetails($(this));
	    	},
	    	'Close' : function() {
	             $(this).dialog('close');
	         }
	    }
	});
$("#boothNameforPolling2").change(function(){
	boothid = $("#boothNameforPolling2").val();
	$("#boothNameforPolling").val(boothid);
	//getUpdatePollingDetails(boothid);
	getPollingMachineList();
	
});

getAccyearbyGroup(); //get academic year from group setting

$("#academicYear").change(function(){ // get Group name by academic year
	$.ajax({
		type: 'POST',
		url : "ElectionMenu.html?method=getGroupNamebyAcademicYear",
		data :{
			"accyear" : $("#academicYear").val()
			},
	
		success :function(data){
		var result = $.parseJSON(data);
	
		$('#groupName').empty();
		$('#groupName').append('<option value="all">----Select----</option>');
		for( var j=0;j<result.groupName.length; j++) {
			$('#groupName').append('<option value="'+result.groupName[j].groupid+ '">'
					+result.groupName[j].groupName+ '</option>'
			);
		}
		//getNominationRegistrationList();
		}
	});
});//end
$("#groupName").change(function(){ //get class by election title and accyeaer
	
	$.ajax({
		type: 'POST',
		url : "ElectionMenu.html?method=getElectionTitleByGroupName",
		data :{
			"groupName" : $("#groupName").val(),
			"accyear"	: $("#academicYear").val(),
			},
	
		success :function(data){
		var result = $.parseJSON(data);
	
		$('#electionTitle').empty();
		$('#electionTitle').append('<option value="all">----Select----</option>');
		for( var j=0;j<result.electionTitle.length; j++) {
			$('#electionTitle').append('<option value="'+result.electionTitle[j].electionTitleId+ '">'
					+result.electionTitle[j].electionTitle+ '</option>'
			);
		}
		//getElectionApprovalList();//for getting listing page
		}
	});
	});//end

$('#centralSystemIp').change(function (e) {
    if (e.which != 8 && e.which != 0 && e.which != 46 && (e.which < 48 || e.which > 57)) {
        return false;
    }
    if (!pattern.test($(this).val())) {
    	$('.successmessagediv').hide();
		$(".errormessagediv").show();
		$(".validateTips").text("Enter Valid Ip.");
		$(".errormessagediv").delay(2000).fadeOut();
    } 
});
$('#pollingSystemIp').change(function (e) {
    if (e.which != 8 && e.which != 0 && e.which != 46 && (e.which < 48 || e.which > 57)) {
        return false;
    }
    if (!pattern.test($(this).val())) {
    	$('.successmessagediv').hide();
		$(".errormessagediv").show();
		$(".validateTips").text("Enter Valid Ip.");
		$(".errormessagediv").delay(2000).fadeOut();
    } 
});

});//JQUERY ENDS----------
function getClassList(){
	var locationid=$("#locationname").val();
	datalist={
			"locationid" : "LOC2"
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#voterClass').html("");
			$('#voterClass').append('<option value="all">' +"ALL"+ '</option>');
			for ( var j = 0; j < result.ClassList.length; j++) {
				$('#voterClass').append('<option value="'
						+ result.ClassList[j].classcode + '">'
						+ result.ClassList[j].classname
						+ '</option>');
			}
		}
	});
}
function getStaffNameList(){
$.ajax({
	type : 'POST',
		url : "ElectionMenu.html?method=getStaffNameList",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#staffIncharge').html("");
			$('#staffIncharge').append('<option value="all">' +"ALL"+ '</option>');
			for ( var j = 0; j < result.teacherData.length; j++) {
				$('#staffIncharge').append('<option value="'
						+ result.teacherData[j].staffId + '">'
						+ result.teacherData[j].staffName
						+ '</option>');
			}
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
	$('#academicYear').append('<option value="all">----Select----</option>');
	for ( var j = 0; j < result.accyarByGroup.length; j++) {

		$('#academicYear').append('<option value="'+ result.accyarByGroup[j].accid+ '">'
				+ result.accyarByGroup[j].accyear+ '</option>');
		}
	}
});	
}//end

function saveBoothDetails(pointer){

	var boothName = $("#boothName").val().trim();
	var boothNameHidden =$("#boothNameHidden").val().trim();//for update
	var staffIncharge =$("#staffIncharge").val();
	var centralSystem =$("#centralSystem").val().trim();
	var centralSystemIp = $("#centralSystemIp").val().trim();
	var voterClass = $("#voterClass").val().toString();

	var academicYear=$("#academicYear").val();
	var groupName=$("#groupName").val();
	var electionTitle = $("#electionTitle").val();

datalist ={
			"boothName":boothName,
			"staffIncharge" :staffIncharge,
			"centralSystem":centralSystem,
			"centralSystemIp":centralSystemIp,
			"voterClass":voterClass,
		
			"academicYear" :academicYear,
			"groupName":groupName,
			"electionTitle":electionTitle,
			"boothNameHidden":boothNameHidden,
			
			};
if (!pattern.test($("#centralSystemIp").val())) {
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text("Enter Valid Ip.");
	$(".errormessagediv").delay(2000).fadeOut();
} 
else{
	$.ajax({
		type:'POST',
		url :"ElectionMenu.html?method=saveBoothDetails",
		data : datalist,
		async: false,
		success : function(data){
			var result = $.parseJSON(data);
		
			 
			 if(result.status.substring(0,3)=="EBS"){
				 pointer.dialog('close');
					$("#allstudent1 tbody").append("<tr id='"+result.status+"'>" +
							"<td>"+($("#allstudent1 tbody tr").length+1)+"</td>" +
							"<td>"+boothName+"</td>" +
							"<td>"+$("#staffIncharge option:selected").text()+"</td>" +
							"<td>"+centralSystem+"</td>" +
							"<td>"+centralSystemIp+"</td>" +
							"<td>"+ $("#voterClass option:selected").text()+"</td>" +
							"<td><span id='"+result.status+"' class='glyphicon glyphicon-trash deleteBooth'></span></td>" +
							"</tr>");
					deleteBoothSelectedRow();
			 }	
			//checking the update data 
			 else  if(result.status=="updatetrue"){
				 $('.errormessagediv').hide();
					$('.successmessagediv').show();
					$(".validateTips").text("Update Record Progressing...");
					$(".successmessagediv").delay(3000).fadeOut();
					pointer.dialog('close');
					setTimeout(function() {

					location.reload();
				}, 2000);
			 }	
			 else  if(result.status=="dupPri"){
		 			$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Booth Name Already Exist ");
					document.getElementById("categoryName").style.border = "1px solid #AF2C2C";
					document.getElementById("categoryName").style.backgroundColor = "#FFF7F7";
					$(".errormessagediv").delay(2000).fadeOut();
					
		 		 }
		 		 else  if(result.status=="dupStaff"){
			 			$('.successmessagediv').hide();
						$(".errormessagediv").show();
						$(".validateTips").text("staff  Already Exist ");
						document.getElementById("categoryName").style.border = "1px solid #AF2C2C";
						document.getElementById("categoryName").style.backgroundColor = "#FFF7F7";
						$(".errormessagediv").delay(2000).fadeOut();
			 		 }
		 		 else  if(result.status=="Ipexist"){
			 			$('.successmessagediv').hide();
						$(".errormessagediv").show();
						$(".validateTips").text("IP Address Already Exist.");
						document.getElementById("centralSystemIp").style.border = "1px solid #AF2C2C";
						document.getElementById("centralSystemIp").style.backgroundColor = "#FFF7F7";
						$(".errormessagediv").delay(2000).fadeOut();
			 		 }
		 		 else  if(result.status=="polingIp"){
			 			$('.successmessagediv').hide();
						$(".errormessagediv").show();
						$(".validateTips").text("Ip Already Mapped with polling Machine");
						document.getElementById("centralSystemIp").style.border = "1px solid #AF2C2C";
						document.getElementById("centralSystemIp").style.backgroundColor = "#FFF7F7";
						$(".errormessagediv").delay(2000).fadeOut();
			 		 }
		 		else  if(result.status=="started"){
		 			$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Election Already Started.");
					$(".errormessagediv").delay(2000).fadeOut();
		 		 }
			  else{
		 			$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Fail to Add Record...");
					$(".errormessagediv").delay(3000).fadeOut();
					
		 		 }
 		}//success
	});//ajax
	

}
	return false;

}

function getBoothDetailsList(){
	
	var academicYear=$("#academicYear").val();
	var groupName=$("#groupName").val();
	var electionTitle = $("#electionTitle").val();

	datalist ={
				"academicYear" :academicYear,
				"groupName":groupName,
				"electionTitle":electionTitle,
				};
	$.ajax({
		type:'POST',
		url:"ElectionMenu.html?method=getBoothDetailsList",
		data:datalist,
		async:false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#allstudent1 tbody").empty();
			if(result.DataList.length>0){
				
				
			for(var i=0; i<result.DataList.length; i++){
				$("#allstudent1 tbody").append("<tr id='"+result.DataList[i].boothNameId+ "'>" +
						"<td>"+result.DataList[i].sno+"</td>" +
						"<td>"+result.DataList[i].boothName+"</td>" +
						"<td>"+result.DataList[i].staffName+"</td>" +
						"<td>"+result.DataList[i].centralSystem+"</td>" +
						"<td>"+result.DataList[i].centralSystemIp+"</td>" +
						"<td>"+result.DataList[i].voterClass+"</td>" +
						"<td><span id='"+result.DataList[i].boothNameId+"'  class='glyphicon glyphicon-trash deleteBooth'></span></td>" +
				"</tr>");
				}
			deleteBoothSelectedRow();
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='10'>No Record Found</td></tr>");
				}
		}
});
}

function deleteBoothSelectedRow() {
	
	$(".deleteBooth").click(function(){
	datalist =$(this).closest("tr").attr("id");	
	$(this).closest("tr").remove();
		$.ajax({
			type :'POST',
			url : "ElectionMenu.html?method=deleteBoothSelectedRow",
			data :{"id":datalist},
			async :false,
			success : function(data){
				
				var result=$.parseJSON(data);
				if(result.status=="true"){
					getBoothNameDropdown();
				}
}
});
});
}

//for Fetching data for update page
function getUpdateBoothSetting(boothNameId){
		datalist ={
				"boothNameId" :boothNameId,
				};
	$.ajax({
		type:'POST',
		url:"ElectionMenu.html?method=getUpdateBoothSetting",
		data:datalist,
		async:false,
		success :function(response){
			var result = $.parseJSON(response);
		
			$("#boothName").val(result.DataList[0].boothName);
			$("#staffIncharge").val(result.DataList[0].staffIncharge);
			$("#centralSystem").val(result.DataList[0].centralSystem);
			$("#centralSystemIp").val(result.DataList[0].centralSystemIp);
			$("#voterClass").val(result.DataList[0].voterClass);
			
	}
	});
}

$("#allstudent1 tbody tr td").click(function(){
	
	alert("wadfsd");
	getClassList();
	$("#electionCategoryAddDialog").dialog("open"); 
	var boothNameId=$(this).closest("tr").attr("id");
	
	$("#boothNameHidden").val(boothNameId);
	$("#boothName").attr("readonly",true);//for making readonly while updationg
	getUpdateBoothSetting(boothNameId);//for carreying data for update page

});
//polling machine setting----------------------------------
function getBoothNameDropdown(){

	var academicYear=$("#academicYear").val();
	var groupName=$("#groupName").val();
	var electionTitle = $("#electionTitle").val();
	datalist ={
				"academicYear" :academicYear,
				"groupName":groupName,
				"electionTitle":electionTitle,
				};
	$.ajax({
		type:'POST',
		url:"ElectionMenu.html?method=getBoothNameDropdown",
		data:datalist,
		async:false,
		success:function(data){
			var result = $.parseJSON(data);
			$('#boothNameforPolling,#boothNameforPolling2').empty();

			$('#boothNameforPolling,#boothNameforPolling2').append('<option value="all">----Select----</option>');
			for ( var j = 0; j < result.DataList.length; j++) {

				$('#boothNameforPolling,#boothNameforPolling2').append('<option value="'+ result.DataList[j].boothNameId+ '">'
						+ result.DataList[j].boothName+ '</option>');
				}
			}
});
}

function savePollingMachineDetails(pointer){
	
	var boothNameforPolling = $("#boothNameforPolling").val(); 
	var pollingMachineName = $("#pollingMachineName").val(); 
	var pollingSystemName = $("#pollingSystemName").val(); 
	var pollingSystemIp = $("#pollingSystemIp").val(); 
	
	var pollingHiddenId =$("#pollingHiddenId").val();
	var academicYear=$("#academicYear").val();
	var groupName=$("#groupName").val();
	var electionTitle = $("#electionTitle").val();
	
	datalist ={
			
				"boothNameforPolling":boothNameforPolling,
				"pollingMachineName":pollingMachineName,
				"pollingSystemName":pollingSystemName,
				"pollingSystemIp":pollingSystemIp,
				
				"academicYear" :academicYear,
				"groupName":groupName,
				"electionTitle":electionTitle,
				"pollingHiddenId" :pollingHiddenId,
				};
	if (!pattern.test($("#pollingSystemIp").val())) {
		$('.successmessagediv').hide();
		$(".errormessagediv").show();
		$(".validateTips").text("Enter Valid Ip.");
		$(".errormessagediv").delay(2000).fadeOut();
	} 
	else{
	$.ajax({
		type:'POST',
		url:"ElectionMenu.html?method=savePollingMachineDetails",
		data:datalist,
		async:false,
		success:function(data){
					var result = $.parseJSON(data);
				
					 
					 if(result.status=="true"){
				 			$('.errormessagediv').hide();
							$('.successmessagediv').show();
							$(".validateTips").text("Adding Record Progressing...");
							$(".successmessagediv").delay(3000).fadeOut();
							pointer.dialog('close');
							setTimeout(function() {

							location.reload();
						}, 2000);
					 }
					 else if(result.status=="updatetrue"){
				 			$('.errormessagediv').hide();
							$('.successmessagediv').show();
							$(".validateTips").text("Update Record Progressing...");
							$(".successmessagediv").delay(3000).fadeOut();
							pointer.dialog('close');
							setTimeout(function() {

							location.reload();
						}, 2000);
					 }
					 else if(result.status=="Ipexist"){
						 $('.successmessagediv').hide();
							$(".errormessagediv").show();
							$(".validateTips").text("IP Address Already Mapped with Central Machine.");
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
	
	
	}
			return false;

		}
function getPollingMachineList(){
	
	var academicYear=$("#academicYear").val();
	var groupName=$("#groupName").val();
	var electionTitle = $("#electionTitle").val();
	var boothNameforPolling2 = $("#boothNameforPolling2").val();
	//alert(boothNameforPolling2);


	datalist ={
				"academicYear" :academicYear,
				"groupName":groupName,
				"electionTitle":electionTitle,
				"boothNameforPolling2":boothNameforPolling2,
				
				};
	$.ajax({
		type:'POST',
		url:"ElectionMenu.html?method=getPollingMachineList",
		data:datalist,
		async:false,
		success:function(data){
			
			var result = $.parseJSON(data);
			$("#allstudent2 tbody").empty();
			if(result.DataList.length>0){
				
			for(var i=0; i<result.DataList.length; i++){
				$("#allstudent2 tbody").append("<tr id='"+result.DataList[i].pollingMachineId+ "'>" +
						"<td>"+result.DataList[i].sno+"</td>" +
			
						"<td>"+result.DataList[i].pollingMachineName+"</td>" +
						"<td>"+result.DataList[i].pollingSystemName+"</td>" +
						"<td>"+result.DataList[i].pollingSystemIp+"</td>" +
						
						"<td><span id='"+result.DataList[i].pollingMachineId+"'  class='glyphicon glyphicon-trash delete'></span><span id='DeActive_"+result.DataList[i].pollingMachineId+"'  class='DeActive'>Deactive</span></td>" +
				"</tr>");
				}
			rowClickable();
			pollingMachineDeAtive();
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='10'>No Record Found</td></tr>");
				}
		}
});
}
function deletePoleSelectedRow() {
	$(".delete").click(function(){
	
		datalist =$(this).attr("id");
			
		$.ajax({
			type :'POST',
			url : "ElectionMenu.html?method=deletePoleSelectedRow",
			data :{"id":datalist},
			async :false,
			success : function(data){
				
				var result=$.parseJSON(data);
			if(result.DataList=="true"){
				$('.successmessagediv').show();
				$(".errormessagediv").hide();
				$(".validateTips").text("Delete Record Progressing...");
				setTimeout(function(){
					location.reload();
				},2000);
				
			}
			else{
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Fail to delete...");
			}
		}
		});
	});
}
function rowClickable(){//for update
	$("#allstudent2 tbody tr td").not("#allstudent2 tbody tr td:nth-child(5)").click(function(){
		if($("#boothNameforPolling2").val()=="all" || $("#boothNameforPolling2").val()=="" ){
			$('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Please Select Booth Name.");
			$(".errormessagediv").delay(2000).fadeOut();
		
	}
	else{
		
		if($("#academicYear").val()=="all"){
			$('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Select Academic Year.");
			$(".errormessagediv").delay(2000).fadeOut();
		}
		else if($("#groupName").val()=="all"){
			$('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Select Group Name.");
			$(".errormessagediv").delay(2000).fadeOut();
		}
		else if($("#electionTitle").val()=="all"){
			$('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Select Election Title.");
			$(".errormessagediv").delay(2000).fadeOut();
		}
		else{
		$("#electionCategoryAddDialog1").dialog("open");
		pollingId=$(this).closest("tr").attr("id");
	
		$("#boothNameforPolling").attr("readonly",true);
		getUpdatePollingDetails($(this).closest("tr").attr("id"));//for carreying data for update page
	
		}
		}

		 var id =$(this).closest("tr").attr("id");
		var pollingId =$("#boothNameforPolling2").val();
		$("#boothNameforPolling").val(pollingId);
		$("#boothNameforPolling").attr("readonly",true);//for making readonly while updationg
	});
}
function getUpdatePollingDetails(id){
var status=null;
	if(id.substring(0,3)=="EBS"){
	status=id.substr(0,3);
	}
	else{
		status="EPS";
	}
	datalist ={
			"boothNameId" :id,
			"status":status,
			};
$.ajax({
	type:'POST',
	url:"ElectionMenu.html?method=getUpdatePollingDetails",
	data:datalist,
	async:false,
	success :function(response){
		var result = $.parseJSON(response);
		$("#pollingMachineName").val(result.DataList[0].pollingMachineName);
		$("#pollingSystemName").val(result.DataList[0].pollingSystemName);
		$("#pollingSystemIp").val(result.DataList[0].pollingSystemIp);
}
});

}
function pollingMachineDeAtive(){
	$(".DeActive").click(function(){
		
		datalist ={
				"pollingMachineId" :$(this).attr("id").split("_")[1],
				
				};
	$.ajax({
		type:'POST',
		url:"ElectionMenu.html?method=getMachineDeActivation",
		data:datalist,
		async:false,
		success :function(response){
			
			var result=$.parseJSON(response);
			
			if(result.DataList=="true"){
				$('.successmessagediv').show();
				$(".errormessagediv").hide();
				$(".validateTips").text("Polling Machine Deactivating...");
				setTimeout(function(){
					location.reload();
				},2000);
				
			}
			else{
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("DeActivation Fail...");
			}
	}
	});
	});
}

/*function  checkDuplicateStaff(){
	boolean flag = 0;
	var boothName = $("#boothName").val().trim();
	var staffIncharge =$("#staffIncharge").val();

	var academicYear=$("#academicYear").val();
	var groupName=$("#groupName").val();
	var electionTitle = $("#electionTitle").val();



datalist ={
			"boothName":boothName,
			"staffIncharge" :staffIncharge,
			
			"academicYear" :academicYear,
			"groupName":groupName,
			"electionTitle":electionTitle,
			"boothNameHidden":boothNameHidden,
			
			};
	
	$.ajax({
		type:'POST',
		url :"ElectionMenu.html?method=saveBoothDetails",
		data : datalist,
		async: false,
		success : function(data){
			var result = $.parseJSON(data);
		
			 
			 if(result.status=="true"){
				 flag 1;
			 }
			 else if(result.status=="false"){
				 flag 0;
			 }
		}

});
	return flag;
	}*/
