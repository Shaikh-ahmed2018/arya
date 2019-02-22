$(document).ready(function(){
	getStageSettingList();
	$("#eventNameList").change(function(){
		getstageName();
		getStageSettingList();
	});
	$("#stageNameList").change(function(){
		getStageSettingList();
	});
	$("#addgroup").click(function(){
		$('input:text').val("");
		$("eventName option").show();
		$('textarea').val("");
		$('select').val("");
		$("#hiddenStageId").val("");
		$("#stageSetting").dialog("open");
	});

	$("#stageSetting").dialog({
		autoOpen  : false,
		maxWidth  :	950,
		maxHeight : 500,
		width     : 850,
		height    : 340,
		modal     : true,
		title     : "Stage Setting",
		buttons   : {
			'Save'  : function() {
				var pointer=$(this);
				saveStageSetting(pointer);
			},
			'Close' : function() {
				$('input:text').val("");
				$('textarea').val("");
				$('select').val("");
				$("#eventName option").show();
				$(this).dialog('close');
			
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

	$("#deleteDialog").dialog({
		autoOpen: false,
		modal: true,					    
		title:'Stage Details',
		buttons : {
			"Yes" : function() {
				datalist={
						"id":id,
				};
				$.ajax({
					type:"post",
					url:"EventsMenu.html?method=deleteStage",
					data:{"id":id},
					async:false,
					success:function(data){
						var result = $.parseJSON(data);
						if(result.status=="true"){
							$(".errormessagediv").hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Delete Record Progressing");
							$(".successmessagediv").delay(3000).fadeOut();
							getStageSettingList();
						}
					}
				});
				$(this).dialog('close');
				$("#hiddenStageId").val("");
			},

			"No" : function() {
				$("#hiddenStageId").val("");
				$(this).dialog('close');
				
				

			}
		}
	});



});//JQUERY

function saveStageSetting(pointer){

	var eventName = $("#eventName").val();
	var stageName = $("#stageName").val();
	var building = $("#building").val();
	var floorName = $("#floorName").val();
	var roomNumber = $("#roomNumber").val();
	var info = $("#info").val();
	var hiddenStageId =$("#hiddenStageId").val();
	var stageNameHidden =$("#stageNameHidden").val();

	if(eventName=="" || eventName==undefined){
		showError("#eventName","Enter Event Name");
		return false;
	}
	if(stageName=="" || stageName==undefined){
		showError("#stageName","Enter Stage Name");
		return false;
	}
	if(floorName=="" || floorName==undefined){
		showError("#floorName","Enter floor Name");
		return false;
	}
	if(building=="" || building==undefined){
		showError("#building","Enter Building Name");
		return false;
	}
	if(roomNumber=="" || roomNumber==undefined){
		showError("#roomNumber","Enter Room Number");
		return false;
	}

	datalist={
			"eventName":eventName,
			"stageName":stageName,
			"building":building,
			"floorName":floorName,
			"roomNumber":roomNumber,
			"info":info,
			"hiddenStageId":hiddenStageId,
			"stageNameHidden":stageNameHidden,
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=saveStageSetting",
		data:datalist,
		async:false,
		success:function(data){
			var result=$.parseJSON(data);

			if(result.status=="true"){
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Adding Record Progressing...");
				$(".successmessagediv").delay(3000).fadeOut();
				$('input:text').val("");
				$('textarea').val("");
				$('select').val("");
				$("#hiddenStageId").val("");
				$("#eventName option").show();
				$("#stageSetting").dialog('close');
				getStageSettingList();
			}
			else if(result.status=="updateTrue"){
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Update Record Progressing...");
				$(".successmessagediv").delay(3000).fadeOut();
				$("#hiddenStageId").val("");
				$('input:text').val("");
				$('textarea').val("");
				$('select').val("");
				$("#eventName option").show();
				$("#stageSetting").dialog('close');
				getStageSettingList();
				
			}	else if(result.status=="Duplicate"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Stage Already Allocated");
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
function getstageName(){
	var id = $("#eventNameList").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getstageList",
		async:false,
		data:{"id":id,
		},
		success:function(data){
			var result = $.parseJSON(data);
			$("#stageNameList").empty();
			$("#stageNameList").append("<option value=''>----------Select----------</option>");

			for(var i=0;i<result.data.length;i++){
				$("#stageNameList").append("<option value='"+result.data[i].stageId+"'>" 
						+result.data[i].stageName+"</option>");
			}
		}
	});
}

function getStageSettingList(){
	var id =$("#eventNameList").val();
	var stageNameList = $("#stageNameList").val();
	if(id==""){
		id="all";
	}
	if(stageNameList==""){
		stageNameList="all";
	}

	datalist={
			"id":id,
			"stageNameList":stageNameList,
	},
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getstageSettingList",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);

			$("#allstudent tbody").empty();                                  
			if(result.data.length>0){
				for(var i=0; i<result.data.length; i++){

					$("#allstudent tbody").append("<tr id='"+result.data[i].stageId+"'>" 
							+"<td>"+(i+1)+"</td>"
							+"<td>"+result.data[i].eventName+"</td>"
							+"<td>"+result.data[i].stageName+"</td>"
							+"<td>"+result.data[i].building+"</td>" 
							+"<td>"+result.data[i].floorName+"</td>"
							+"<td>"+result.data[i].roomNo+"</td>" 
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
				deleteStage();
			}else{
				$("#allstudent tbody").append("<tr><td colspan=6'>No Record Found</td></tr>");
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No of Records "+result.data.length+".");
			pagination(50);
		}
	});
}
function rowClickable(){
	$("#allstudent tbody tr td").not("#allstudent tbody tr td:last-child").click(function(){
		var id = $(this).closest("tr").attr("id");
		updateStage(id);
	});
}
function deleteStage(){
	$(".deleteRow").click(function(){
		id=$(this).closest("tr").attr("id");

		$("#deleteDialog").dialog("open");
		$("#deleteDialog").empty();
		$("#deleteDialog").append("<p>Are you sure to delete?</p>");
	});
}
function updateStage(id){
	$("#stageSetting").dialog("open");
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getDataForUpdateStage",
		data:{"id":id},
		asunc:false,
		success:function(data){
			var result =$.parseJSON(data);

			/*$("#eventName option").val(result.data[0].eventName);*/
			$("#eventName").val(result.data[0].eventId);
			$("#stageNameHidden").val(result.data[0].stageName);
			$("#stageName").val(result.data[0].stageName);	
			$("#building").val(result.data[0].building);
			$("#floorName").val(result.data[0].floorName);
			$("#roomNumber").val(result.data[0].roomNo);
			$("#info").val(result.data[0].description);
			$("#hiddenStageId").val(result.data[0].stageId);
		}
	});
}

function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
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
	$(".errormessagediv").delay(3000).fadeOut();
}