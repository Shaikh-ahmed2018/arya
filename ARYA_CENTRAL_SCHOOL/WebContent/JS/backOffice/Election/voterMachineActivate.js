$(document).ready(function(){
	
	history.pushState(null, null, location.href);
	window.onpopstate = function(event) {
	    history.go(1);
	};
	
	$("#admissionNo").autocomplete({
		source : function(request, response) {

			$.ajax({

				url : "ElectionMenu.html?method=studentSearchbyadmissionNoForApproval",
				data : {
					searchTerm : request.term,
					"localIp":$("#LocalIp").val(),
					"accyear":$("#accyear").val()
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					response($.map(	result.jsonResponse,function(item) {
						return {
							label : item.admissionNo,
							value : item.admissionNo,
						};
					}));
				}
			});
		},
		select : function(event, ui) {

			var searchTerm = ui.item.value;

			studentDetails = {
					'searchTerm' : searchTerm
			};

			
			$("#admissionNo").val(ui.item.label);
			SingleDetails(ui.item.label);
			return false;
		}
	});
});


function SingleDetails(admissionNo){//for pop up data
	var academicYear = $("#academicYear").val();
	datalist={
			"academicYear":academicYear,
			"admissionNo":admissionNo,
	};
	$.ajax({
		type:'POST',
		url :"ElectionMenu.html?method=getSingleNomineeDetails",
		data: datalist,
		async :false,
		success :function(data)
		{
			var result = $.parseJSON(data);
			if(result.nominee.length>0){
			
	$("#studentName").val(result.nominee[0].studentName);
	$("#className").val(result.nominee[0].className);
	$("#division").val(result.nominee[0].sectionName);
	$("#house").val(result.nominee[0].houseName);
	$("#imagePreview").attr("src",result.nominee[0].imgUrl);
	
	$("#hStudentId").val(result.nominee[0].studentId);
	$("#hclassId").val(result.nominee[0].classId);
	$("#hdivisionId").val(result.nominee[0].sectionId);
	$("#hhouseId").val(result.nominee[0].houseId);
	getUpdatePollingDetails($(".boothName").attr("id"),result.nominee[0].classId,result.nominee[0].studentId,result.nominee[0].houseId);
	
		MachineActivationClick(result.nominee[0].classId,result.nominee[0].studentId,result.nominee[0].houseId,$("#accyear").val(),$("#group").val(),$("#titleID").val());
	
			}
			else{
	 			
				document.getElementById("admissionNo").style.border = "1px solid #AF2C2C";
				document.getElementById("admissionNo").style.backgroundColor = "#FFF7F7";
				$(".errormessagediv").delay(3000).fadeOut();
				$("#admissionNo").val("");
				$("#studentName").val("");
				$("#className").val("");
				$("#division").val("");
				$("#imagePreview").attr("src","images/girl.png");
				
				$("#hStudentId").val("");
				$("#hclassId").val("");
				$("#hdivisionId").val("");
	 		 }
	
			
		}
});	
}

function getUpdatePollingDetails(id,classId,studentId,houseId){
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
				"classId":classId,
				};
	$.ajax({
		type:'POST',
		url:"ElectionMenu.html?method=getUpdatePollingDetails",
		data:datalist,
		async:false,
		success :function(response){
			var result = $.parseJSON(response);
			$("#pollingMachine tbody").empty();
			if(result.DataList.length>0){
			for(var i=0; i<result.DataList.length; i++){
				$("#pollingMachine tbody").append("<tr>" +
						"<td><input type='radio' name='mipaddress' class='pollingmachineradio' value='"+result.DataList[i].pollingSystemIp+"' /></td>" +
						"<td>"+result.DataList[i].pollingMachineName+"</td>" +
						"<td>"+result.DataList[i].pollingSystemName+"</td>" +
						"<td>"+result.DataList[i].pollingSystemIp+"</td>" +
						"</tr>");
				
			
				}
			getCategoryList(classId,studentId,houseId);
			}
			else{
				$("#pollingMachine tbody").append("<tr><td colspan='4'>No Record Found</td></tr>");
				}
	}
	});
	}

function getCategoryList(classId,studentId,houseId){
	
	datalist={
			"classId":classId,
			"accyear":$("#accyear").val(),
			"group":$("#group").val(),
			"titleID":$("#titleID").val(),
			"houseId":houseId,
			"studentId":studentId,
			
	};
	
	$.ajax({
		type:'POST',
		url :"ElectionMenu.html?method=getCategoryList",
		data: datalist,
		async :false,
		success :function(data)
		{
			var result = $.parseJSON(data);
			$("#categoryList tbody").empty();
			if(result.categoryList.length>0){
			for(var i=0; i<result.categoryList.length; i++){
				$("#categoryList tbody").append("<tr class='"+result.categoryList[i].ElectionCategoryId+"'>" +
						"<td>"+result.categoryList[i].sno+"</td>" +
						"<td>"+result.categoryList[i].electionCategory+"</td>" +
						"</tr>");
				}
		
			}
			else{
				$("#categoryList tbody").append("<tr><td colspan='2'>No Record Found</td></tr>");
				}
		}
});	
}
function MachineActivationClick(classId,studentId,houseId,accyear,group,titleID){
	$("#startActivation").click(function(){
		
		var dataList={
				"localIp":$(".pollingmachineradio:checked").val(),
				"classId":classId,
				"houseId":houseId,
				"accyear":accyear,
				"group":group,
				"titleID":titleID,
				"studentId":studentId,
		};
		
		if($(".pollingmachineradio:checked").length>0){
		$.ajax({
			type:'POST',
			url:'ElectionMenu.html?method=MachicneActivation',
			data:dataList,
			async:false,
			success:function(response){
				var result=$.parseJSON(response);
				if(result.status=="true"){
					$(".errormessagedivRequest").hide();
					$(".successmessagedivRequest").show();
					$(".validateTips").text("Machine Activated");
				}
				else if(result.status=="already"){
					$(".successmessagedivRequest").hide();
					$(".errormessagedivRequest").show();
					$(".errormessagedivRequest .imgsrc").attr("src",$("#imagePreview").attr("src"));
					$(".validateTips").text("Already verified");
					
				}
				else if(result.status=="polling"){
					$(".successmessagedivRequest").hide();
					$(".errormessagedivRequest").show();
					$(".validateTips").text("polling Inprogess...");
				}
				
			
				else if(result.status=="notStart"){
					$(".successmessagedivRequest").hide();
					$(".errormessagedivRequest").show();
					$(".validateTips").text("Election Not Running...");
				}
				else if(result.status=="casted"){
					$(".successmessagedivRequest").hide();
					$(".errormessagedivRequest").show();
					$(".errormessagedivRequest .imgsrc").attr("src",$("#imagePreview").attr("src"));
					$(".validateTips").text("Already Vote Casted..");
				}
				else{
					$(".successmessagedivRequest").hide();
					$(".errormessagedivRequest").show();
					$(".validateTips").text("No Machine Mapped with this Center");
				}
				setTimeout(function(){
					location.reload();
				},3000);
			}
		});
		
		
	}
		else{
			alert("Select Poling Machine First..");
		}
	});
}
function HideError(){
	document.getElementById("admissionNo").style.backgroundColor = "#fff";
	document.getElementById("admissionNo").style.border = "1px solid #ccc";
}
 
