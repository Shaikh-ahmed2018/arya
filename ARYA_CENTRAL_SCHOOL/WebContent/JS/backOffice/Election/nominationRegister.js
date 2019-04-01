$(document).ready(function() {
	getNominationRegistrationList();
	//change function for listing page
	$("#academicYear").change(function(){
		getNominationRegistrationList();
	});
	$("#groupName").change(function(){
		getNominationRegistrationList();
	});
	
	$("#electionTitle").change(function(){
		getNominationRegistrationList();
	});
	
	getAccyearbyGroup(); //get academic year from group setting
	$("#academicYear").val($("#hacademicyaer").val());//this and abve line for getting default academic year in the list. once academic year is loaded
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
		getNominationRegistrationList();
		}
	});
	
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
			getNominationRegistrationList();
			}
		});
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
			getNominationRegistrationList();
			}
		});
		});//end

//----------------save data in dialog box-------------------------
			$("#StudentNomineeDialog").dialog({
			    autoOpen  : false,
			    maxWidth  :	750,
		        maxHeight : 400,
		        width     : 800,
		        height    : 330,
			    modal     : true,
			    title     : "Student Nominee Registration",
			    buttons   : {
			    	'Save'  : function() {
			    		saveNewNomineeDetails();//saving the data into database:define
			    		$("#admissionNo").val("");
			    		$("#studentName").val("");
			    		$("#className").val("");
			    		$("#division").val("");
			    		$("#house").val("");
			    		$("#imagePreview").attr("src","images/girl.png");
			    		 //$(this).dialog('close');
			    		
			    	},
			    	'Close' : function() {
		                 $(this).dialog('close');
		                 $("#admissionNo").val("");
				    		$("#studentName").val("");
				    		$("#className").val("");
				    		$("#division").val("");
				    		$("#house").val("");
				    		$("#imagePreview").attr("src","images/girl.png");
		             }
			    }
			});
			
			$("#admissionNo").autocomplete({
				source : function(request, response) {
						
					$.ajax({

						url : "ElectionMenu.html?method=studentSearchbyadmissionNoForNomination",
						data : {
							searchTerm : request.term,
							"electionCategory":$("#hiddenElectionCategoryId").val()
						},
						async : false,
						success : function(data) {
							var result = $.parseJSON(data);
							response($.map(	result.jsonResponse,function(item) {
								return {
									label : item.admissionNo,
									value : item.admissionNo,
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

					
					$("#admissionNo").val(ui.item.label);
					getNomineeDetails();
					return false;
				}
			});

			
			
			
});//end of jqyery document

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
 function getNominationRegistrationList(){
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
			$("#allstudent tbody").empty();
			if(result.DataList.length>0){
			for(var i=0; i<result.DataList.length; i++){
				$("#allstudent tbody").append("<tr class='"+result.DataList[i].electionTitleId+","+result.DataList[i].groupid+"'>" +
						"<td>"+result.DataList[i].sno+"</td>" +
						"<td>"+result.DataList[i].electionCategory+"</td>" +
					
						"<td><span id='"+result.DataList[i].electionCategoryId+"'  class='buttons'>Click Here to Nominate</span></td>" +
						"</tr>");
				}
			nominationButton();
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='3'>No Record Found</td></tr>");
				}
			pagination(100);
		}
});	
}//end
 
 function nominationButton(){
	 $(".buttons").click(function(){
		 $("#hiddenGroupId").val($(this).closest("tr").attr("class").split(",")[1]);
		 $("#hiddenElectionId").val($(this).closest("tr").attr("class").split(",")[0]);
		 $("#hiddenElectionCategoryId").val($(this).attr("id"));
		 
		 if($("#academicYear").val()=="all"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Select Academic Year.");
				$(".errormessagediv").delay(2000).fadeOut();
			}
			
			else{
		selectedElectionId = $(this).attr("id");
			$("#StudentNomineeDialog").dialog("open");
			}
	 }); 
	 } 
 	
 function getNomineeDetails(){//for pop up data
		var academicYear = $("#academicYear").val();
		datalist={
				"academicYear":academicYear,
				"admissionNo":$("#admissionNo").val(),
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
		
				}
				
		
				
			}
	});	
	}
 
 
 
 function saveNewNomineeDetails(){
	 
/*	 	var admissionNo = $("#admissionNo").val().trim();
	 	var studentName = $("#hStudentId").val().trim();
		var classNameId= $("#hclassId").val().trim();
		var sectionNameId =$("#hdivisionId").val().trim();
		var categoryId=$("#hiddenElectionCategoryId").val();
		var groupId=$("#hiddenGroupId").val().trim();
		var titleId=$("#hiddenElectionId").val();
		var accyearId =$("#academicYear").val().trim();*/
	
	 	var admissionNo = $("#admissionNo").val().trim();
	 	var studentName = $("#hStudentId").val().trim();
		var classNameId= $("#hclassId").val().trim();
		var sectionNameId =$("#hdivisionId").val().trim();
		var categoryId=selectedElectionId;
		var groupId=$("#hiddenGroupId").val().trim();
		var titleId=$("#hiddenElectionId").val();
		var accyearId =$("#academicYear").val().trim();
		
		if(admissionNo == "" || admissionNo == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Feild Required Admission Number !");
			document.getElementById("admissionNo").style.border = "1px solid #AF2C2C";
			document.getElementById("admissionNo").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}
		else if(studentName=="" || studentName==undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("No Record Found For "+$("#academicYear option:selected").text()+" !");
			
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}
		
		datalist ={
			"admissionNo":admissionNo,
			"studentName":studentName,
			"classNameId":classNameId,
			"sectionNameId":sectionNameId,
			"categoryId":categoryId,
			"groupId":groupId,
			"titleId":titleId,
			"accyearId":accyearId,
		};
	$.ajax({
		type :'POST',
		url :"ElectionMenu.html?method=saveNewNomineeDetails",
		data :datalist,
		async: false,
		success : function(data){
			
			var result = $.parseJSON(data);
			if(result.status=="true"){
	 			$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Adding Record Progressing...");
				$(".successmessagediv").delay(3000).fadeOut();
				setTimeout(function() {

					window.location.href="adminMenu.html?method=nominationRegister";
			}, 2000);
		 }	

		//checking the repeated data for update time
		 else  if(result.status=="exist"){
	 			$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text(" This Admission Number Already Assigned To Other Category");
				document.getElementById("admissionNo").style.border = "1px solid #AF2C2C";
				document.getElementById("admissionNo").style.backgroundColor = "#FFF7F7";
				$(".errormessagediv").delay(2000).fadeOut();
				
	 		 }
		 else  if(result.status=="started"){
	 			$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Election Already Started");
		
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
	/*return false;//for else
		
		}//else
*/	}//function
 
 

 //-----------remove errors
 
function HideError(){
	document.getElementById("admissionNo").style.backgroundColor = "#fff";
	document.getElementById("admissionNo").style.border = "1px solid #ccc";
}
 
