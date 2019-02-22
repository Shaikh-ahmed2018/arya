
$(document).ready(function(){
	var eid = $("#eventNameList").val().trim();
	var cid = $("#eventcategory").val().trim();
	var pid = $("#registrationNumberList").val().trim();

	
	$("#eventNameList").change(function(){
		
			var eid = $("#eventNameList").val().trim();
			var cid = $("#eventcategory").val().trim();
			var pid = $("#registrationNumberList").val().trim();
			
			if($("#eventNameList").click){
				$("#eventcategory").val("");
				$("#registrationNumberList").val("");
			}
			
			getEventCategory(eid);
			
	});
	
	$("#eventcategory").change(function(){
		var eid = $("#eventNameList").val().trim();
		var cid = $("#eventcategory").val().trim();
		var pid = $("#registrationNumberList").val().trim();
		
		
		if($("#eventcategory").click){
			$("#registrationNumberList").val("");
		}
		
		getProgramName(cid);
		
		
	});
	
	$("#registrationNumberList").change(function(){
		var eid = $("#eventNameList").val().trim();
		var cid = $("#eventcategory").val().trim();
		var pid = $("#registrationNumberList").val().trim();
		
		
		if($("#eventNameList").val() == ""){
			$("#eventcategory").val("");
			$("#registrationNumberList").val("");
		}
		getEventStudentRegList(eid,cid,pid);
	});
	
	 $("#selectall").change(function () {
         $(".select").attr('checked', true);
         if($(".select").attr('checked', this.checked).length<0){
        	 $("#allstudent tbody").append("<tr>"+"<td colspan='5'>No Records Founds</td>" +"</tr>");
         }
     });

	

$(".select").change(function() {
	if($(".select").length == $(".select:checked").length){
		$("#selectall").prop("checked",true);
	}
	else{
		$("#selectall").prop("checked",false);
	}
	});


$("#printall").click(function(){
	
	
	
	if($("#allstudent tbody tr").length>0){
	window.location.href="EventsMenu.html?method=PrintPreviewEventMultipleIDCard&evenm="+$("#eventNameList").val()
		+"&evecat="+$("#eventcategory").val()
		+"&prognm="+$("#registrationNumberList").val();
		
	}
	else{
		$("#errormessagediv").show();
		$(".validateTips").text("No Record.");
		$("#errormessagediv").delay(2000).fadeOut();
	}
});
	
});//JQUERY ENDS

function getEventCategory(eid){
	
	$.ajax({
		type:'POST',
		url : "EventsMenu.html?method=getEventCategory",
		data:{'eid':eid},
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#eventcategory').empty();
			$('#eventcategory').append('<option value="">-------Select-------</option>');
			for ( var j = 0; j < result.eventCategory.length; j++) {

				$('#eventcategory').append('<option value="'+ result.eventCategory[j].categoryId+ '">'
						+ result.eventCategory[j].categoryName+ '</option>');

			}

		}
	});
}

function getProgramName(cid){
	
	$.ajax({
		type:'POST',
		url : "EventsMenu.html?method=getProgramName",
		data:{'cid':cid},
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#registrationNumberList').empty();
			$('#registrationNumberList').append('<option value="">-------Select-------</option>');
			for ( var j = 0; j < result.eventProgramName.length; j++) {

				$('#registrationNumberList').append('<option value="'+ result.eventProgramName[j].programmeId+ '">'
						+ result.eventProgramName[j].programmeName+ '</option>');
			}

		}
	});
}

function saveStudentReg(pointer){
	var participantsArray=[];
	var evId= $("#eventName").val();
	var catId = $("#categoryName").val();
	var progId = $("#programName").val();
	var houseId = $("#houseName").val();
	var programCaptain =$("#programCaptain").val().split("_")[0 ];
	var participantsName= $("#participantsList").val();
	participantsArray.push(participantsName);
	participantsArray.push(programCaptain);
	var info_staff = $("#info_staff").val();
	var info_synopsis =$("#info_synopsis").val();
	var info_req =$("#info_req").val();
	
datalist ={
			"evId":evId,
			"catId":catId,
			"progId":progId,
			"houseId":houseId,
			"programCaptain":programCaptain,
			"participantsName":participantsArray.toString(),
			"info_staff":info_staff,
			"info_synopsis":info_synopsis,
			"info_req":info_req,
				};
alert(datalist.programCaptain);
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=saveEventStudentReg",
		data:datalist,
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			if(result.status=="true"){
				
				if(result.status=="true"){
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Adding Record Progressing...");
					$(".successmessagediv").delay(3000).fadeOut();
				}
    		else{
		 		$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Fail to Add Record...");
				$(".errormessagediv").delay(3000).fadeOut();
		 		 }
    		}
		}
	});
}

function getEventStudentRegList(eid,cid,pid){
	var evId= eid;
	var catId= cid;
	var regNo= pid;
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getEventIdPrintList",
		data:{	"evId":evId,
				"catId":catId,
				"regNo":regNo
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#allstudent > tbody").empty();
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#allstudent > tbody").append("<tr>" +
							"<td>"+(i+1)+"</td>"+
							"<td>"+result.data[i].admissionNo+"</td>"+
							"<td>"+result.data[i].studentName+"</td>"+
							"<td>"+result.data[i].rollNumber+"</td>" +
							"<td>"+result.data[i].className+"</td>" +
							"<td>"+result.data[i].location+"</td>" +
							"<td><img src='"+result.data[i].imageUrl+"' width='20' height='20' /></td>" +
					"</tr>"); 
					
					
				}
				
				
				 
			}else{
				$("#allstudent tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
			}
			
			
			}
		});
}




