$(document).ready(function(){
	getPrizeSettingList();
	
	$("#eventNameList").change(function(){
		getCategoryName();
		getProgrameeName();
		getPrizeList();
		getPrizeSettingList();
	});
	
	$("#categoryList").change(function(){
	    getProgrameeName();
	    getPrizeSettingList();
	});
	$("#programmeList").change(function(){
		getPrizeList();
	    getPrizeSettingList();
	});
	/*$("#prizelist").change(function(){

	});*/
	
	
	
	$("#eventName").change(function(){
		getPopCategoryName();
		getPopProgrameeName();
	
	});
	$("#category").change(function(){
		getPopProgrameeName();
	
	});
	
	
	
	
		
		$("#addgroup").click(function(){
		
		$("#stageSetting").dialog("open");
		
		
		});
		
		$("#stageSetting").dialog({
		    autoOpen  : false,
		    maxWidth  :	950,
	        maxHeight : 500,
	        width     : 800,
	        height    : 390,
		    modal     : true,
		    title     : "Prize Level Setting",
		    buttons   : {
		    	'Save'  : function() {
		    		var pointer=$(this);
		    		saveCriteriaSetting(pointer);
		    	
		    	},
		    	'Close' : function() {
		    		$('input:text').val("");
		    		$('textarea').val("");
		    		$('select.event').val("");
		    		$('select.prog').val("");
		    		$('select.cat').val("");
		    		$("#hiddenCriteriaId").val("");
		    		HideError();
		    		
		    		$(this).dialog('close');
	             }
		    }
		});
});
function getCategoryName(){
	var id = $("#eventNameList").val();
	
	$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getPrizeCategoryName",
	async:false,
	data:{"id":id},
	success:function(data){
		var result = $.parseJSON(data);
		$("#categoryList").empty();
		$("#categoryList").append("<option value=''>All</option>");
		
		for(var i=0;i<result.data.length;i++){
		$("#categoryList").append("<option value='"+result.data[i].categoryId+"'>" 
				+result.data[i].categoryName+"</option>");
		}
	}
});
}
function saveCriteriaSetting(pointer){
	

	var eventName = $("#eventName").val();
	var programmeName = $("#programmeName").val(); 
	var category = $("#category").val();
	var seqNo = $("#seqNo").val();
	var prizelevel = $("#prizelevel").val();
	var points = $("#points").val();
	var hiddenCriteriaId =$("#hiddenCriteriaId").val();
	var description = $("#description").val();
	
	if(eventName=="" || eventName==undefined){
		showError("#eventName","Select Event Name");
		return false;
	}
	else if(category=="" || category==undefined){
		showError("#category","Select Category Name");
		return false;
	}
	else if(programmeName=="" || programmeName==undefined){
		showError("#programmeName","Select Programme Name");
		return false;
	}
	else if(seqNo=="" || seqNo==undefined){
		showError("#seqNo","Enter Sequence Number");
		return false;
	}
	else if(prizelevel=="" || prizelevel==undefined){
		showError("#prizelevel","Enter prize level ");
		return false;
	}
	else if(points=="" || points==undefined){
		showError("#points","Enter points ");
		return false;
	}
	else if(checkSeqDuplication() == 1)
		{
	
		$("#seqNo").val("");
		$("#prizelevel").val("");
		$("#points").val("");
		$(".errormessagediv").show();
		$(".validateTips").text("Prize Level Details Already Exist..");
		$(".errormessagediv").delay(3000).fadeOut();
		}
	else if(checkPrizeDuplication() == 1){
	
		$("#seqNo").val("");
		$("#prizelevel").val("");
		$("#points").val("");
		$(".errormessagediv").show();
		$(".validateTips").text("Prize Level Details Already Exist..");
		$(".errormessagediv").delay(3000).fadeOut();
	}

	else{
		$(".errormessagediv").hide();
	
	datalist={
			"eventName":eventName,
			"programmeName":programmeName,
			"category":category,
			"prizelevel":prizelevel,
			"points":points,
			"seqNo":seqNo,
			"hiddenCriteriaId":hiddenCriteriaId,
			"description":description,
			};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=savePrizelevelSetting",
		data:datalist,
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
				
				if(result.status=="true"){
		 			$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Adding Record....");
					$(".successmessagediv").delay(2000).fadeOut();
					$('input:text').val("");
		    		$('textarea').val("");
		    		$('select.event').val("");
		    		$('select.prog').val("");
		    		$('select.cat').val("");
		    		$("#eventName option").show();
					$("#stageSetting").dialog('close');
					$("#hiddenCriteriaId").val("");
					getPrizeSettingList();
				}
				else if(result.status=="updateTrue"){
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Updating Record......");
					$(".successmessagediv").delay(2000).fadeOut();
					
					$('input:text').val("");
		    		$('textarea').val("");
		    		$('select.event').val("");
		    		$('select.prog').val("");
		    		$('select.cat').val("");
					$("#stageSetting").dialog('close');
					$("#hiddenCriteriaId").val("");
					$("#eventName option").show();
					getPrizeSettingList();
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

function getProgrameeName(){
	var event_id = $("#eventNameList").val();
	var id = $("#categoryList").val();
	
	$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getProgrammeName",
	async:false,
	data:{"id":id,"event_id":event_id,},
	success:function(data){
		var result = $.parseJSON(data);
		$("#programmeList").empty();
		$("#programmeList").append("<option value=''>All</option>");
		
		for(var i=0;i<result.data.length;i++){
		$("#programmeList").append("<option value='"+result.data[i].programmeId+"'>" 
				+result.data[i].programmeName+"</option>");
		}
	}
});
}
function getPopProgrameeName(){
	var event_id = $("#eventName").val();
	var id = $("#category").val();
	
	$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getProgrammeName",
	async:false,
	data:{"id":id,"event_id":event_id},
	success:function(data){
		var result = $.parseJSON(data);
		$("#programmeName").empty();
		$("#programmeName").append("<option value=''>----------Select----------</option>");
		
		for(var i=0;i<result.data.length;i++){
		$("#programmeName").append("<option value='"+result.data[i].programmeId+"'>" 
				+result.data[i].programmeName+"</option>");
		}
	}
});
}


function getPopCategoryName(){
	var id = $("#eventName").val();
	
	$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getPrizeCategoryName",
	async:false,
	data:{"id":id},
	success:function(data){
		var result = $.parseJSON(data);
		$("#category").empty();
		$("#category").append("<option value=''>----------Select----------</option>");
		
		for(var i=0;i<result.data.length;i++){
		$("#category").append("<option value='"+result.data[i].categoryId+"'>" 
				+result.data[i].categoryName+"</option>");
		}
	}
});
}
function getPrizeList(){
	var event_id = $("#eventNameList").val();
	var cat_id = $("#categoryList").val();
	var id = $("#programmeList").val();
	
	$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getPrizeList",
	async:false,
	data:{"id":id,"event_id":event_id,"cat_id":cat_id},
	success:function(data){
		var result = $.parseJSON(data);
		$("#prizelist").empty();
		$("#prizelist").append("<option value=''>All</option>");
		
		for(var i=0;i<result.data.length;i++){
		$("#prizelist").append("<option value='"+result.data[i].prizeLevel+"'>" 
				+result.data[i].prizeLevel+"</option>");
		}
	}
});
}
function getseqNoList(){
	var event_id = $("#eventNameList").val();
	var cat_id = $("#categoryList").val();
	var prog_id = $("#programmeList").val();
	var id=$("#prizelist").val();
	
	$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getSeqList",
	async:false,
	data:{"id":id,"event_id":event_id,"cat_id":cat_id,"prog_id":prog_id,},
	success:function(data){
		var result = $.parseJSON(data);
		$("#seqNoList").empty();
		$("#seqNoList").append("<option value=''>All</option>");
		
		for(var i=0;i<result.data.length;i++){
		$("#seqNoList").append("<option value='"+result.data[i].seqNo+"'>" 
				+result.data[i].seqNo+"</option>");
		}
	}
});
}
function getPointsList(){
	var event_id = $("#eventNameList").val();
	var cat_id = $("#categoryList").val();
	var prog_id = $("#programmeList").val();
	var prize_id =$("#prizelist").val();
	var id=$("#seqNoList").val();
	$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getPointsList",
	async:false,
	data:{"id":id,"event_id":event_id,"cat_id":cat_id,"prog_id":prog_id,"prize_id":prize_id,},
	success:function(data){
		var result = $.parseJSON(data);
		$("#pointsList").empty();
		$("#pointsList").append("<option value=''>All</option>");
		
		for(var i=0;i<result.data.length;i++){
		$("#pointsList").append("<option value='"+result.data[i].points+"'>" 
				+result.data[i].points+"</option>");
		}
	}
});
}

function getPrizeSettingList(){
	
	var id =$("#eventName").val();
	var hiddenCriteriaId =$("#hiddenCriteriaId").val();
	var eventNameList = $("#eventNameList").val();
	var categoryList = $("#categoryList").val();
	var programmeList = $("#programmeList").val();
	var prizelist = $("#prizelist").val();
	var seqNoList = $("#seqNoList").val();
	var pointsList = $("#pointsList").val();
	
	if(id==""){
		id="all";
	}
	if(programmeList==""){
		programmeList="all";
	}
	if(categoryList==""){
		categoryList="all";
	}
	if(prizelist==""){
		prizelist="all";
	}
	if(seqNoList==""){
		seqNoList="all";
	}
	if(pointsList==""){
		pointsList="all";
	}
	
	
	
datalist={
		"id":id,
		"eventNameList":eventNameList,
		"programmeList":programmeList,
		"categoryList":categoryList,
		"prizelist":prizelist,
		"seqNoList":seqNoList,
		"pointsList":pointsList,
		},
$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getPrizeSettingList",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
		
			$("#allstudent tbody").empty();                                  
			if(result.data.length>0){
			for(var i=0; i<result.data.length; i++){
				$("#allstudent tbody").append("<tr id='"+result.data[i].prize_id+"'>" 
						+"<td>"+(i+1)+"</td>"
						+"<td>"+result.data[i].eventName+"</td>"
						+"<td>"+result.data[i].category+"</td>"
						+"<td>"+result.data[i].programmeName+"</td>" 
						+"<td>"+result.data[i].prizeLevel+"</td>"
						+"<td>"+result.data[i].seqNo+"</td>" 
						+"<td>"+result.data[i].points+"</td>" 
						+"<td>"+result.data[i].description+"</td>"
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
			deletePrize();
			}else{
				$("#allstudent tbody").append("<tr><td colspan=9'>No Record Found</td></tr>");
				
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.data.length);
			pagination(50);
		}
	});  

}
function getPopProgrameeName(){
	var event_id = $("#eventName").val();
	var id = $("#category").val();
	
	$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getProgrammeName",
	async:false,
	data:{"id":id,"event_id":event_id},
	success:function(data){
		var result = $.parseJSON(data);
		$("#programmeName").empty();
		$("#programmeName").append("<option value=''>----------Select----------</option>");
		
		for(var i=0;i<result.data.length;i++){
		$("#programmeName").append("<option value='"+result.data[i].programmeId+"'>" 
				+result.data[i].programmeName+"</option>");
		}
	}
});
}

function rowClickable(){
	$("#allstudent tbody tr td").not("#allstudent tbody tr td:last-child").click(function(){
		id = $(this).closest("tr").attr("id");
		$("#hiddenCriteriaId").val(id);
		
		updatePrize(id);
	});
}




function deletePrize(){
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
		title : "Prize Level Settings Details",
		buttons : {

			"Yes" : function() {
            
            	
	$.ajax({
		type:"post",
		url:"EventsMenu.html?method=deletePrize",
		data:{"id":id},
		async:false,
		success:function(data){
			var result = $.parseJSON(data);
			if(result.status=="true"){
				$(".successmessagediv").show();
				$(".validateTips").text("Deleting Record Progressing......");
				$(".successmessagediv").delay(3000).fadeOut();
				getPrizeSettingList();
				
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
	

function updatePrize(id){
	
	$("#stageSetting").dialog("open");
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getDataForUpdatePrize",
		data:{"id":id},
		asunc:false,
		success:function(data){
			var result =$.parseJSON(data);
			
			$("#eventName").val(result.data[0].eventName);
			getPopCategoryName();
			$("#category").val(result.data[0].categoryName);
			getPopProgrameeName();
			$("#programmeName").val(result.data[0].programmeName);
			$("#seqNo").val(result.data[0].seqNo);
			$("#prizelevel").val(result.data[0].prizeLevel);
			$("#points").val(result.data[0].points);
			$("#description").val(result.data[0].description);
		$("#hiddenCateg").val(result.data[0].categoryName);
			$("#hiddenprog").val(result.data[0].programmeName);
			$("#hiddenSeq").val(result.data[0].seqNo);
			$("#hiddenprize").val(result.data[0].prizeLevel);
			$("#hiddenpoints").val(result.data[0].points);
			
		}
	});
}
function  checkSeqDuplication() {
	
	var eventName = $("#eventName").val();
		var programmeName = $("#programmeName").val();
		var category = $("#category").val();
		var seqNo = $("#seqNo").val();
		

		var prizelevel = $("#prizelevel").val();
		var points = $("#points").val();
		
		var datalist = {
				"eventName" : eventName,
				"programmeName" : programmeName,
				"category":category,
				"seqNo":seqNo,
				"prizelevel":prizelevel,
				"points":points,
		};
if($("#hiddenCriteriaId").val() == "")
	{
		$.ajax({
			type : "POST",
			url : "EventsMenu.html?method=validateSeq",
			data : datalist,
			async : false,
			success : function(data) {
					var result = $.parseJSON(data);
					if(result.list =="true" ) {
					/*$("#seqNo").val("");
					showError("#seqNo","Sequence Number details already exist ");
					return false;*/
						flag= 1;
					}else{
							flag=0;
						}
				}
			});
	}else
		{
		flag=0;
		}
			return flag;
		}

	
	function  checkPrizeDuplication() {
	
		
		flag=0;
		
		var eventName = $("#eventName").val();
			var programmeName = $("#programmeName").val();
			var category = $("#category").val();
			var seqNo = $("#seqNo").val();
			var prizelevel = $("#prizelevel").val();
			var points=$("#points").val();
			
			var datalist = {
					"eventName" : eventName,
					"programmeName" : programmeName,
					"category":category,
					"seqNo":seqNo,
					"prizelevel":prizelevel,
					"points":points,
			};

			if($("#hiddenCriteriaId").val()!="" && $("#hiddenCateg").val()!= category || $("#hiddenprog").val()!=programmeName || $("#hiddenSeq").val()!=seqNo || $("#hiddenprize").val()!=prizelevel || $("#hiddenpoints").val()!= points )
				{
			$.ajax({
				type : "POST",
				url : "EventsMenu.html?method=validatePrize",
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
			
			
		
			return flag;
				}
			else
				{
				 flag=0;
				}
			
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

function HideError() 
{
document.getElementById("eventName").style.border = "1px solid #ccc";
document.getElementById("eventName").style.backgroundColor = "#fff";
document.getElementById("category").style.border = "1px solid #ccc";
document.getElementById("category").style.backgroundColor = "#fff";
document.getElementById("programmeName").style.border = "1px solid #ccc";
document.getElementById("programmeName").style.backgroundColor = "#fff";
document.getElementById("seqNo").style.border = "1px solid #ccc";
document.getElementById("seqNo").style.backgroundColor = "#fff";
document.getElementById("prizelevel").style.border = "1px solid #ccc";
document.getElementById("prizelevel").style.backgroundColor = "#fff";
document.getElementById("points").style.border = "1px solid #ccc";
document.getElementById("points").style.backgroundColor = "#fff";

}

