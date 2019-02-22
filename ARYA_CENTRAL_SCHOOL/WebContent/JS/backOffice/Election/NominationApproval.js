$(document).ready(function() {
	$("#selectAll").change(function(){
		$(".selectBox[value='Pending']").prop("checked", $(this).prop("checked"));
	});
	
		getAccyearbyGroup(); //get academic year from group setting
		
		$("#academicYear").val($("#hacademicyaer").val());
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
			getElectionApprovalList();
			}
		});
		
		
		

	
	//change function for listing page
		$("#academicYear").change(function(){
		getElectionApprovalList();
		
		});
		$("#groupName").change(function(){
		getElectionApprovalList();
		});
		$("#electionTitle").change(function(){
			getElectionApprovalList();
		});
		$("#electionCategory").change(function(){
			getElectionApprovalList();
		});
	
	
	$("#academicYear").change(function(){ // get Group name by academic year
		getGroupName();
	});//end
	
	$("#groupName").change(function(){
	
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
			getElectionApprovalList();//for getting listing page
			}
		});
		});//end

$("#electionTitle").change(function(){
	var academicYear = $("#academicYear").val();
	var groupName = $("#groupName").val();
	var electionTitle = $("#electionTitle").val();
	
	if(academicYear==""){
		academicYear="all";
	}
	if(groupName==""){
		groupName="all";
	}
	if(electionTitle==""){
		electionTitle="all";
	}
	datalist={
			"academicYear":academicYear,
			"groupName":groupName,
			"electionTitle":electionTitle,
	};
	
	$.ajax({
		type:'POST',
		url :"ElectionMenu.html?method=getNominationRegistrationList",
		data: datalist,
		async :false,
		success :function(data)
		{
			var result = $.parseJSON(data);
			$('#electionCategory').empty();
			$('#electionCategory').append('<option value="all">----Select----</option>');
			for(var i=0; i<result.DataList.length; i++){
				$('#electionCategory').append('<option value="'+result.DataList[i].electionCategoryId+ '">'
													+result.DataList[i].electionCategory+'</option>');
				
				}
			getElectionApprovalList();//for getiing lising page
		}
});	
});

$("#approve").click(function(){
	var count =0;
	admissionId=[];
	
	$(".selectBox:checked").each(function(){
		var list=$(this).attr("id").split(",");
		admissionId.push(list);
		count ++;

	});	
if(count==0){
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text("please select checkbox");
	$(".errormessagediv").delay(3000).fadeOut();
	
}else{
	$.ajax({
		type:'POST',
		url :"ElectionMenu.html?method=saveApproval",
		data: {'admissionId':admissionId.toString()},
		async :false,
		success :function(data)
		{
			var result = $.parseJSON(data);
			 if(result.status=="true"){
		 			$('.errormessagediv').hide();
					$('.successmessagediv').show();
					$(".successmessage").text("Approving Record Progressing...");
					$(".successmessagediv").delay(3000).fadeOut();
					setTimeout(function() {
						location.reload();
				}, 2000);
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
});	//end function

$("#reject").click(function(){
	var count =0;
	admissionId=[];
	
	$(".selectBox:checked").each(function(){
		var list=$(this).attr("id").split(",");
		admissionId.push(list);
		count ++;

	});	
if(count==0){
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text("please select checkbox");
	$(".errormessagediv").delay(3000).fadeOut();
	
}else{
	$.ajax({
		type:'POST',
		url :"ElectionMenu.html?method=rejectApproval",
		data: {'admissionId':admissionId.toString()},
		async :false,
		success :function(data)
		{
			var result = $.parseJSON(data);
			 if(result.status=="true"){
		 			$('.errormessagediv').hide();
					$('.successmessagediv').show();
					$(".successmessage").text("Reject Record Progressing...");
					$(".successmessagediv").delay(3000).fadeOut();
					setTimeout(function() {

						location.reload();
				}, 2000);
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
});	//end function

pagination(100);

});//jquery end-------------------------------------------------
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
}//end

function getElectionApprovalList(){//adding dynamic check box
	
	var academicYear = $("#academicYear").val();
	var groupName = $("#groupName").val();
	var electionTitle = $("#electionTitle").val();
	var electionCategory = $("#electionCategory").val();
	
		if(academicYear==""){
		academicYear="all";
		}
		if(groupName==""){
		groupName="all";
		}
		if(electionTitle==""){
		electionTitle="all";
		}
		if(electionCategory==""){
		electionCategory="all";
		}
		
	datalist={
			"academicYear":academicYear,
			"groupName":groupName,
			"electionTitle":electionTitle,
			"electionCategory":electionCategory,
	};
	
	$.ajax({
		type:'POST',
		url :"ElectionMenu.html?method=getNominationApprovalList",
		data: datalist,
		async :false,
		success :function(data)
		{
			var result = $.parseJSON(data);
			$("#allstudent tbody").empty();
			if(result.DataList.length>0){
			for(var i=0; i<result.DataList.length; i++){
				$("#allstudent tbody").append("<tr class='"+result.DataList[i].ElectionCategoryId+"'>" +
						
						"<td><input type='checkbox' id='"+result.DataList[i].admissionNo+"'class='selectBox' value='"+result.DataList[i].status+"'/></td>"+
						"<td>"+result.DataList[i].sno+"</td>" +
						"<td>"+result.DataList[i].admissionNo+"</td>" +
						"<td>"+result.DataList[i].studentName+"</td>" +
						"<td>"+result.DataList[i].className+"</td>" +
						"<td>"+result.DataList[i].sectionName+"</td>" +
						"<td><span class="+result.DataList[i].status+">"+result.DataList[i].status+"</span></td>" +
					"</tr>");
				}
			//rowClickable();
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='7'>No Record Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.DataList.length);
			pagination(100);
			$(".selectBox").change(function(){
				if(this.value !='Pending'){
					$(this).prop("checked",false);
				}
			});
		}
});	
}//end

function getGroupName(){
	
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
		//getElectionList();
		}
	});
}






