$(document).ready(function(){
	getGreenRoomList();
	$("#eventNameList").change(function(){
		getGreenRoom();
		getGreenRoomList();
	});
	$("#greenRoomNameList").change(function(){
		getGreenRoomList();
	});
	$("#addgroup").click(function(){
		$("#eventName option").show();
		$("#greenRoom").dialog("open");
	});
	
		$("#greenRoom").dialog({
		    autoOpen  : false,	
		    maxWidth  :	950,
	        maxHeight : 500,
	        width     : 800,
	        height    : 260,
		    modal     : true,
		    title     : "GreenRoom Setting",
		    buttons   : {
		    	'Save'  : function() {
		    		 var pointer = $(this);
		    		saveGreenRoom(pointer);
		    	},
		    	'Close' : function() {
		    		$("input:text").val("");
		    		$("select").val("");
		    		$(this).dialog("close");
		    		$("#eventName option").show();
		    		$(".successmessagediv").hide();
		    		 $("#greenRoomIdHidden").val("");
		    		
		    		$(".errormessagediv").hide();
		    		$(".form-control").css({
		    			"border":"1px solid #ccc",
		    			"background-color":"#fff"
		    		});
		    	},
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
		    title:'Green Room Setting',
		    buttons : {
				"Yes" : function() {
					  datalist={
							"id":id,
							};
					  $.ajax({
							type:"post",
							url:"EventsMenu.html?method=deleteGreenRoom",
							data:{"id":id},
							async:false,
							success:function(data){
								var result = $.parseJSON(data);
								if(result.status=="true"){
									$(".errormessagediv").hide();
									$(".successmessagediv").show();
									$(".validateTips").text("Delete Record Progressing");
									$(".successmessagediv").delay(3000).fadeOut();
									getGreenRoomList();
								}
							}
						});
					 $(this).dialog('close');
					  },

				"No" : function() {
					  $(this).dialog('close');
					  $("#greenRoomIdHidden").val("");
				}
			}
		});

		
		
		
		
		
		
		
		
		
		
		
});//JQUERY

function saveGreenRoom(pointer){
	var eventId = $("#eventName").val();
	var greenRoomName = $("#greenRoomName").val();
	var greenRoomType = $("#greenRoomType").val();
	var building = $("#building").val();
	var floorName = $("#floorName").val();
	var roomNumber = $("#roomNumber").val();
	var greenRoomIdHidden =$("#greenRoomIdHidden").val();
	var greenRoomNameHidden = $("#greenRoomNameHidden").val();
	
	if(eventId=="" || eventId==undefined){
		showError("#eventName","Enter Event Name");
		return false;
	}
	if(greenRoomName=="" || greenRoomName==undefined){
		showError("#greenRoomName","Enter GreenRoom Name");
		return false;
	}
	if(greenRoomType=="" || greenRoomType==undefined){
		showError("#greenRoomType","Enter GreenRoom Type ");
		return false;
	}
	if(roomNumber=="" || roomNumber==undefined){
		showError("#roomNumber","Enter Room Number");
		return false;
	}
	if(floorName=="" || floorName==undefined){
		showError("#floorName","Enter Floor Name");
		return false;
	}
	if(building=="" || building==undefined){
		showError("#building","Enter Building");
		return false;
	}
	
	datalist = { "eventId":eventId,
		"greenRoomName":greenRoomName,
		"greenRoomType":greenRoomType,
		"building":building,
		"floorName":floorName,
		"roomNumber":roomNumber,
		"greenRoomIdHidden":greenRoomIdHidden,
		"greenRoomNameHidden":greenRoomNameHidden,
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=saveGreenRoom",
		data :datalist,
		async :false,
		success:function(data){
			var result =$.parseJSON(data);
			if(result.status=="true"){
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Adding Record Progressing...");
				$(".successmessagediv").delay(3000).fadeOut();
				
				$('input:text').val("");
	    		$('select').val("");
				$("#greenRoom").dialog('close');
				getGreenRoomList();
			}
			else if(result.status=="updateTrue"){
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Update Record Progressing...");
				$(".successmessagediv").delay(3000).fadeOut();
				$('input:text').val("");
	    		$('select').val("");
	    		$("#eventName option").show();
				$("#greenRoom").dialog('close');
				getGreenRoomList();
			}
			else if(result.status=="duplicateUpdate"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Green Room already Assigned");
				$(".errormessagediv").delay(3000).fadeOut();
			}
			else if(result.status=="duplicateInsert"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Green Room already Assigned");
				$(".errormessagediv").delay(3000).fadeOut();
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
function getGreenRoom(){
	var id = $("#eventNameList").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getGreenRoom",
		data :{"id":id},
		async :false,
		success:function(data){
			
			var result = $.parseJSON(data);
			$("#greenRoomNameList").empty();
			$("#greenRoomNameList").append("<option value=''>----------Select---------</option>");
			
			for(var i=0;i<result.data.length;i++){
			$("#greenRoomNameList").append("<option value='"+result.data[i].greenRoomId+"'>" 
					+result.data[i].greenRoomName+"</option>");
			}
		}
	});
	}

function getGreenRoomList(){
	var id = $("#eventNameList").val();
	var greenRoomId =$("#greenRoomNameList").val();
	if(id==null || id==""){
		id="all";
	}
	if(greenRoomId==null || greenRoomId==""){
		greenRoomId="all";
	}
	datalist ={"id":id,
		"greenRoomId":greenRoomId,
}
	
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getGreenRoomList",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			
			$("#allstudent tbody").empty();                                  
			if(result.data.length>0){
			for(var i=0; i<result.data.length; i++){
				
				$("#allstudent tbody").append("<tr id='"+result.data[i].greenRoomId+"'>" 
						+"<td>"+(i+1)+"</td>"
						+"<td>"+result.data[i].eventName+"</td>"
						+"<td>"+result.data[i].greenRoomName+"</td>"
						+"<td>"+result.data[i].greenRoomType+"</td>" 
						+"<td>"+result.data[i].floorName+"</td>"
						+"<td>"+result.data[i].roomNo+"</td>"
						+"<td>"+result.data[i].building+"</td>" 
						+"<td><span class='glyphicon glyphicon-trash deleteGreenRoom'></span></td>"
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
			deleteGreenRoom();
			}else{
				$("#allstudent tbody").append("<tr><td colspan='7'>No Record Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No of Records "+result.data.length+".");
			pagination(50);
		}
	});
}
function deleteGreenRoom(){
	$(".deleteGreenRoom").click(function(){
		 id =$(this).closest("tr").attr("id");
		$("#deleteDialog").dialog("open");
		$("#deleteDialog").empty();
		$("#deleteDialog").append("<p>Are you sure to delete?</p>");
});
	}
function rowClickable(){
		$("#allstudent tbody tr td").not("#allstudent tbody tr td:last-child").click(function(){
		 id = $(this).closest("tr").attr("id");
		$("#greenRoom").dialog("open");
		$.ajax({
			type:'post',
			url:"EventsMenu.html?method=getDataForUpdateGreenRoom",
			data:{"id":id},
			async:false,
			success:function(data){
				var result =$.parseJSON(data);
				
				$("#eventName").val(result.data[0].eventId);
				$("#eventName option").hide();
				$("#eventName").attr("readonly",true);
				$("#greenRoomName").val(result.data[0].greenRoomName);
				$("#greenRoomType").val(result.data[0].greenRoomType);
				$("#building").val(result.data[0].building);
				$("#floorName").val(result.data[0].floorName);
				$("#roomNumber").val(result.data[0].roomNo);
				$("#greenRoomIdHidden").val(result.data[0].greenRoomId);
				$("#greenRoomNameHidden").val(result.data[0].greenRoomName);
			}
		});
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

