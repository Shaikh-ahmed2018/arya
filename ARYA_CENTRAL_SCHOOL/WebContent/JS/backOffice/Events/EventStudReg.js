$(document).ready(function(){
	$(".data").hide();
	$(".Option").show();
//	for group
	//getStageNameListGroup();
	getHouseNameListGroup();
	getEventNameList_Group();
	getEventStudentRegList();
	//progNameListGroup();
	getCategoryNameList_Group();
	getStageNameList_Group();

//	for indivR
	getCategoryNameList_Indiv();
	getEventStudentRegListIndividual();
	getHouseNameListIndiv();
	getEventNameList_Indiv();
	progNameListIndiv();

	$(".IndividualData").hide();
	$(".GroupStaffInc,.GroupSyn,.GroupStageReq").show();
	$(".indivStaffInc,.indivStageReq,.IndivSyn").hide();
	$(".Group").show();
	$(".GroupIndividual").hide();
	$("#individualPart").hide();

//	group onchange functions
	$("#eventNameListGroup").change(function(){
		getHouseNameListGroup();
		getEventStudentRegList();
		getCategoryNameList_Group();
		//getStageNameListGroup();
		progNameListGroup();
		getStageNameList_Group();
	});
	$("#CategoryNameListGroup").change(function(){
		getHouseNameListGroup();
		progNameListGroup();
		getEventStudentRegList(); // listing page
	});
	$("#progNameListGroup").change(function(){
		getHouseNameListGroup();
		getEventStudentRegList();
		//getStageNameListGroup();
	});
	$("#houseNameListGroup").change(function(){
		getEventStudentRegList();//for group list
	});

//	individual onchange 
	$("#eventNameListIndiv").change(function(){
		getEventStudentRegListIndividual();
		getCategoryNameList_Indiv();
		progNameListIndiv();
		getHouseNameListIndiv();
	});
	$("#catNameListIndiv").change(function(){
		getEventStudentRegListIndividual();
		progNameListIndiv();
		getEventStudentRegList(); // listing page
		getHouseNameListIndiv();
	});
	$("#progNameListIndiv").change(function(){
		getHouseNameListIndiv();
		getEventStudentRegListIndividual();
	});
	$("#houseNameListIndiv").change(function(){
		getEventStudentRegListIndividual();
	});
	
	
	$("#houseName").change(function(){
		$("#programCaptain").val("");
		$("#participantsList").val("");
	});
	
	
	
	
//	clicking tabs
	$("#groupTab").click(function(){
		$(".groupData").show();
		$(".IndividualData").hide();
		$("#individualPart").hide();
		$("#allstudent").show();
		getEventStudentRegList();
	});
	$("#individualTab").click(function(){
		$(".groupData").hide();
		$(".IndividualData").show();
		$("#allstudent").hide();
		$("#individualPart").show();
		getEventStudentRegListIndividual();
		$("#individualPart tbody tr").show();
	});


//	click_radioButtons
	$("input[id$='group']").click(function() { 
		$(".groupData").show();
		getEventNameList_Group();
		$(".GroupIndividual").hide();
		$(".Group").show();
		$("#studentName").val("");
		$("#programCaptain").val("");
		$("#programCaptainHidden").val("");
		$("#eventName option").show();
		$("#categoryName option").show();
		$("#eventName").val("");
		$("#categoryName").val("");
		$("#houseName").val("");
		$(".GroupParti").show();
	});

	$("input[id$='individual']").click(function() {
		$(".groupData").hide();
		$(".Group").hide();
		$(".GroupParti").hide();
		$(".GroupIndividual").show();
		$("#studentName").val("");
		$("#programCaptain").val("");
		$("#programCaptainHidden").val("");
		$("#eventName option").show();
		$("#categoryName option").show();
		$("#eventName").val("");
		$("#categoryName").val("");
		$("#houseName").val("");
	});
	getEventStudentRegList();
	getEventStudentRegListIndividual();
	
	
	//register page 
	
	$("#eventName").change(function(){
		$(".data").hide();
		getCategoryDropDown();
		getProgramDropDown($("input[name='requested_by']:checked").val());
		
	});
	$("#categoryName").change(function(){
		$(".data").hide();
		getProgramDropDown($("input[name='requested_by']:checked").val());
		
		
	});
	$("#programName").change(function(){
	/*	$("#programCaptain").val("");
		$("#participantsList").val("");*/
		$(".data").hide();
		checkIsHouseWiseForEventReg();
	});
	$("#houseName").change(function(){
		$("#programCaptain").val("");
		$("#participantsList").val("");
	});
	
	$("input[name='requested_by']").change(function(){
		getProgramDropDown($(this).val());
		$(".data").hide();
	});


	$("#addgroup").click(function(){
		
		//$(".col-md-6 input[type=text],select").not(".Option").val("");
		$(".Option").show();
		$("input:text").val('');
		$("select").val('');
		$("textarea").val("");	
		$("#eventName").val("");
		$("#eventName").val("");
		$("#categoryName").val("");
		$("#houseName").val("");
		$("#studentReg").dialog("open");
	});

	$("#studentReg").dialog({
		autoOpen  : false,
		maxWidth  :	950,
		maxHeight : 500,
		width     : 700,
		height    : 550,
		modal     : true,
		title     : "Student Registration",
		buttons   : {
			'Save'  : function() {
				pointer = $(this);
				saveStudentReg(pointer);
			},
			'Close' : function() {
				$(this).dialog('close');
				$("input:text").val("");
				$("select").val("");
				$("textarea").val("");
				$("#programCaptainHidden").val("");
				$("#eventName option").show();
	    		$("#categoryName option").show();
	    		$("#eventName").val("");
				$("#categoryName").val("");
				$("#houseName").val("");
			}
		}
	});



	$("#programCaptain,#studentName").autocomplete({
		source : function(request, response) {
			
			var houseName =$("#houseName").val();
			if(houseName==""){
				houseName="all";
			}
			
			$.ajax({
				url : "EventsMenu.html?method=getAdmissionNoForEventStdReg",
				data : {
					searchTerm : request.term,
					"category":$("#categoryName").val(),
					"houseName":houseName,
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					response($.map(result.jsonResponse,function(item) {
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
			$("#programCaptain,#studentName").val(ui.item.label);
			studentAdmnName(ui.item.label.split("_")[0]);
			return false;
		}
	});

	$("#deleteDialog").dialog({
		autoOpen: false,
		modal: true,					    
		title:'Student Registration',
		buttons : {

			"Yes" : function() {

				datalist={
						"id":id,
				};
				$.ajax({
					type:"post",
					url:"EventsMenu.html?method=deleteEventStudReg",
					data:{"id":id},
					async:false,
					success:function(data){
						var result = $.parseJSON(data);
						if(result.status=="true"){
							$(".errormessagediv").hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Delete Record Progressing");
							$(".successmessagediv").delay(3000).fadeOut();
							getEventStudentRegListIndividual();
							getEventStudentRegList();
						}
					}
				});
				$(this).dialog('close');
			},
			"No" : function() {
				$(this).dialog('close');
				$("#eventName").val("");
				$("#categoryName").val("");
				$("#houseName").val("");
				hideError("#programCaptain");

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
});//JQUERY ENDS

function getHouseNamebyCategoryId(){
		var catId=$("#categoryName").val();
		var evnId=$("#eventName").val();
		datalist={
				"catId":catId,
				"evnId":evnId,
		};
		$.ajax({
			type:'post',
			url:"EventsMenu.html?method=getHouseNamebyCategoryId",
			async:false,
			data:datalist,
			success:function(data){
				var result = $.parseJSON(data);
				$("#houseName").empty();
				$("#houseName").append("<option value=''>----Select----</option>");
				for(var i=0;i<result.data.length;i++){
					$("#houseName").append("<option value='"+result.data[i].houseId+"'>" 
							+result.data[i].houseName+"</option>");
				}
			}
		});
		
	
}
function getStageNameList_Group(){
	var evId = $("#eventNameListGroup").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getstageList",
		data :{"eventname":evId,
			"flag":"regG",
		},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			$("#stageNameListGroup").empty();
			$("#stageNameListGroup").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
				$("#stageNameListGroup").append("<option value='"+result.data[i].houseId+"'>" 
						+result.data[i].houseName+ "</option>");
			}
		}
	});
}

function getHouseNameListGroup(){
	var evId = $("#eventNameListGroup").val();
	var catId = $("#CategoryNameListGroup").val();
	var progId = $("#progNameListGroup").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getHouseNameListGroup",
		data :{"evId":evId,
			"catId":catId,
			"progId":progId,
			"flag":"group",
		},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);

			$("#houseNameListGroup").empty();
			$("#houseNameListGroup").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
				$("#houseNameListGroup").append("<option value='"+result.data[i].houseId+"'>" 
						+result.data[i].houseName+ "</option>");
			}
		}
	});
}
function getHouseNameListIndiv(){
	var evId = $("#eventNameListIndiv").val();
	var catId = $("#catNameListIndiv").val();
	var progId = $("#progNameListIndiv").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getHouseNameListGroup",
		data :{"evId":evId,
			"catId":catId,
			"progId":progId,
			"flag":"Indiv",
		},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);

			$("#houseNameListIndiv").empty();
			$("#houseNameListIndiv").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
				$("#houseNameListIndiv").append("<option value='"+result.data[i].houseId+"'>" 
						+result.data[i].houseName+ "</option>");
			}
		}
	});
}
function progNameListGroup(){
	var catId = $("#eventNameListGroup").val();
	var evId = $("#CategoryNameListGroup").val();
	datalist ={
			"catId":catId,
			"evId":evId,
			"flag":"GroupS", // for differncite in daoimpl queries
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getprogramName",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			$("#progNameListGroup").empty();
			$("#progNameListGroup").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
				$("#progNameListGroup").append("<option value='"+result.data[i].progId+"'>" 
						+result.data[i].progName+"</option>");
			}
		}
	});
}

function progNameListIndiv(){
	var catId = $("#eventNameListIndiv").val();
	var evId = $("#catNameListIndiv").val();

	datalist ={
			"catId":catId,
			"evId":evId,
			"flag":"Indiv", // for differncite in daoimpl queries
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getprogramName",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			$("#progNameListIndiv").empty();
			$("#progNameListIndiv").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
				$("#progNameListIndiv").append("<option value='"+result.data[i].progId+"'>" 
						+result.data[i].progName+"</option>");
			}
		}
	});
}
function getCategoryNameList_Group(){
	var id = $("#eventNameListGroup").val();
	$.ajax({
		type:'post',  
		url:"EventsMenu.html?method=getCategoryName",
		data :{"id":id,
			"flag":"Group",
		},

		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			$("#CategoryNameListGroup").empty();
			$("#CategoryNameListGroup").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
				$("#CategoryNameListGroup").append("<option value='"+result.data[i].categoryId+"'>" 
						+result.data[i].categoryName+ "</option>");
			}
		}
	});
}
function getCategoryNameList_Indiv(){
	var id = $("#eventNameListIndiv").val();
	$.ajax({
		type:'post',  
		url:"EventsMenu.html?method=getCategoryName",
		data :{"id":id,
			"flag":"Indiv",
			},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			$("#catNameListIndiv").empty();
			$("#catNameListIndiv").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
				$("#catNameListIndiv").append("<option value='"+result.data[i].categoryId+"'>" 
						+result.data[i].categoryName+ "</option>");
			}
		}
	});
}

function getEventNameList_Group(){
	$.ajax({
		type:'post',
		data:{"flag":"Group"},
		url:"EventsMenu.html?method=getEventNameList_Group",
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			$("#eventNameListGroup").empty();
			$("#eventNameListGroup").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
				$("#eventNameListGroup").append("<option value='"+result.data[i].eventId+"'>" 
						+result.data[i].eventName+ "</option>");
			}
		}
	});
}
function getEventNameList_Indiv(){
	data={"flag":"Indiv"};
	$.ajax({
		type:'post',
		data:{"flag":"Indiv"},
		url:"EventsMenu.html?method=getEventNameList_Group",
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			$("#eventNameListIndiv").empty();
			$("#eventNameListIndiv").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
				$("#eventNameListIndiv").append("<option value='"+result.data[i].eventId+"'>" 
						+result.data[i].eventName+ "</option>");
			}
		}
	});
}

function getCategoryDropDown(){
	var id = $("#eventName").val();

	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getCategoryName",
		data :{"id":id,
			"flag":"onLoad",
		},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);

			$("#categoryName").empty();
			$("#categoryName").append("<option value=''>----Select----</option>");
			for(var i=0;i<result.data.length;i++){

				$("#categoryName").append("<option value='"+result.data[i].categoryId+"'>" 
						+result.data[i].categoryName+ "</option>");
			}
		}
	});
}
function getProgramDropDown(proType){
	var catId = $("#categoryName").val();
	var evId = $("#eventName").val();
	datalist ={
			"catId":catId,
			"evId":evId,
			"proType":proType,
			"flag":"onLoad",
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getprogramName",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			
			$("#programName").empty();
			$("#programName").append("<option value=''>----Select----</option>");
			for(var i=0;i<result.data.length;i++){
				$("#programName").append("<option value='"+result.data[i].progId+"'>" 
						+result.data[i].progName+"</option>");
			}
		}
	});
}
function  checkIsHouseWiseForEventReg(){
	var evId=$("#eventName").val();
	var catId=$("#categoryName").val();
	var progId=$("#programName").val();
	
	datalist={
			"evId":evId,
			"catId":catId,
			"progId":progId.toString(),
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=checkIsHouseWiseForEventReg",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			for(var i=0;i<result.data.length;i++){
				if(result.data[i].ishouseWise=="yes"){
					getHouseNamebyCategoryId();
					$(".data").show(); //house wise
				}else
					$(".data").hide();
					$("#houseName").val("");
					$("#programCaptain").val("");
					$("#participantsList").val("");
			}
			}
	});
	
}
function saveStudentReg(pointer){
	var participantsArray=[];
	var accId= $("#hacademicyaer").val();
	var evId= $("#eventName").val();
	var catId = $("#categoryName").val();
	var progId = $("#programName").val();
	var houseId = $("#houseName").val();
	var info_staff = $("#info_staffG").val();
	var info_synopsis =$("#info_synopsisG").val();
	var info_req =$("#info_reqG").val();
	var programCaptainHidden =$("#programCaptainHidden").val();
	
	var eventIdHidden =$("#eventIdHidden").val();
	var categoryIdHidden =$("#categoryIdHidden").val();
	var progIdHidden =$("#progIdHidden").val();
	var progCaptainHidden=$("#progCaptainHidden").val();
	var CaptainCompare = $("#programCaptain").val(); //comparing values while update
	var eventSubstr = $("#eventName :selected").text().substring(0,1).toUpperCase();
	var progSubstr = $("#programName :selected").text().substring(0,1).toUpperCase();
	var registrationId = $("#registrationId").val();

	if(evId=="" || evId==undefined){
		showError("#eventName","Select Event Name");
		return false;
	}
	else if(catId=="" || catId==undefined){
		showError("#categoryName","Select Category Name");
		return false;
	}
	else if(progId=="" || progId==undefined){
		showError("#programName","Select Program Name");
		return false;
	}
	else if($(".data").is(":visible") && $("#houseName").val()==""){
		showError("#houseName","Select House Name");
		return false;
	}
	if($("#group").prop("checked")==true){
		grpSubstr =eventSubstr+progSubstr+"G"; //for generationg Registration Number::Group
		participationType="group";				//for setting in table
		programCaptain =$("#programCaptain").val().split("_")[0];
		var participantsName= $("#participantsList").val();
		participantsArray.push(programCaptain);
		participantsArray.push(participantsName);
		
			if(programCaptain=="" || programCaptain==undefined){
					showError("#programCaptain","Select Captain Name");
					return false;
				}
			}
	
	else {
		grpSubstr =eventSubstr+progSubstr+"I"; //for generationg Registration Number::Individual
		programCaptain =$("#studentName").val().split("_")[0];
		participantsArray.push(programCaptain);
		participationType="individual";

		if(programCaptain=="" || programCaptain==undefined){
			showError("#programCaptain","Select Student Name");
			return false;
		}
	}

	//VALIDATION 
for(var i=0;i<progId.length;i++){
	datalist ={
			"evId":evId,
			"catId":catId,
			"progId":progId[i],
			"houseId":houseId,
			"programCaptain":programCaptain,
			"participantsName":participantsArray.toString(),
			"info_staff":info_staff,
			"info_synopsis":info_synopsis,
			"info_req":info_req,
			"programCaptainHidden":programCaptainHidden,
			"participationType":participationType,
			"grpSubstr":grpSubstr,
			"accId":accId,
			"eventIdHidden":eventIdHidden,
			"categoryIdHidden":categoryIdHidden,
			"progIdHidden":progIdHidden,
			"progCaptainHidden":progCaptainHidden,
			"CaptainCompare":CaptainCompare,// temporary for comparing values in daoimpl
			"registrationId":registrationId,
		};
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=saveEventStudentReg",
		data:datalist,
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			if(result.status=="true"){
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Adding Record Progressing...");
				setTimeout(function(){
					 window.location.href ='EventsMenu.html?method=EventstudentRegistration';
				 },3000);
				$("#studentReg").dialog("close");
				$("input:text").val('');
				$("select").val('');
				$("textarea").val("");
				$("#programCaptain").val("");
				$("#programCaptainHidden").val("");
				$("#eventName option").show();
				$("#eventName").val("");
				$("#categoryName").val("");
				$("#houseName").val("");
	    		$("#categoryName option").show();
				getEventStudentRegListIndividual();
				getEventStudentRegList();

				$("input[id$='individual']").prop("checked",false);
				$("input[id$='group']").prop("checked",true);
				$(".GroupParti,.Group").show();
				$(".GroupIndividual").hide();
			}
			else if(result.status=="updateTrue"){
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Update Record Progressing...");
				setTimeout(function(){
					 window.location.href ='EventsMenu.html?method=EventstudentRegistration';
				 },3000);
				$("#studentReg").dialog("close");
				
				$("textarea").val("");
				$("#programCaptain").val("");
				$("#programCaptainHidden").val("");
				getEventStudentRegListIndividual();
				getEventStudentRegList();
				$("input:text").val('');
				$("select").val('');
				$("#eventName option").show();
	    		$("#categoryName option").show();
			}
			else if(result.status=="duplicateRecord"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Captain Already Registered");
				showError("#programCaptain");
				$(".errormessagediv").delay(3000).fadeOut();
				
			}
			else if(result.status=="duplicateUpdate"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Duplicate data");
				showError("#programCaptain");
				$(".errormessagediv").delay(3000).fadeOut();
			}
			else if(result.status=="alreadyC"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Captain Already Registred");
				showError("#programCaptain");
				$(".errormessagediv").delay(3000).fadeOut();
				
			}
			else if(result.status=="falseif"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Captain Already Registered ");
				showError("#programCaptain");
				$(".errormessagediv").delay(3000).fadeOut();
			}
			else if(result.status=="falseupdate"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Fail to Add Record...");
				showError("#programCaptain");
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
	
}                                                           


function studentAdmnName(id){
	var houseName =$("#houseName").val();
	if(houseName==""){
		houseName="all";
	}
	var dataList={
			"eventName":$("#eventName").val(),
			"categoryName":$("#categoryName").val(),
			"id":id,
			"houseName":houseName,
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=studentAdmnNameForEvent",
		data:dataList,
		async:false,
		success:function(data){
			result =$.parseJSON(data);
			$("#participantsList").empty();
			//$("#participantsList").append("<option value=''>-----------Select-----------</option>");
			for(var i=0;i<result.data.length;i++){
				$("#participantsList").append("<option value='"+result.data[i].admissionNo+"'>" 
						+result.data[i].studentName+ "</option>");
				$("#studentIdHidden").val(result.data[i].studentId);

			}
		} 
	});
}

function getEventStudentRegListIndividual(){
	var evId= $("#eventNameListIndiv").val();
	var catId= $("#catNameListIndiv").val();
	var progId= $("#progNameListIndiv").val();
	var houseId = $("#houseNameListIndiv").val();
	var accId = $("#hacademicyaer").val();
	if(evId=="" ||evId==null){
		evId="%%";
	}
	if(catId=="" ||catId==null){
		catId="%%";
	}
	if(progId=="" ||progId==null){
		progId="%%";
	}
	if(houseId=="" ||houseId==null){
		houseId="%%";
	}
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getEventStudentRegListIndividual",
		data:{	"evId":evId,
			"catId":catId,
			"progId":progId,
			"houseId":houseId,
			"accId":accId,
		},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#individualPart tbody").empty();

			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){
					$("#individualPart tbody").append("<tr id='"+result.data[i].registrationId+"' class='"+result.data[i].participateType+"'>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+result.data[i].registrationNo+"</td>"+
							"<td>"+result.data[i].captainAdmnNo+"</td>"+
							"<td>"+result.data[i].studentName+"</td>"+
							"<td>"+result.data[i].className+"</td>" +
							"<td>"+result.data[i].classSec+"</td>" +
							"<td>"+result.data[i].progName+"</td>" +
							"<td><img class='fancybox' src='"+result.data[i].imageUrl+"' width='20' height='20' /></td>" +
							"<td>"+result.data[i].houseName+"</td>"+
							"<td style='position:relative;width:30px;border: none !important;'><span  id='"+result.data[i].registrationId+"' class='glyphicon glyphicon-trash deleteRowInd'></span></td>"+
					"</tr>");
				}
				//rowClickableInd();
				deleteRowIndInd();

			}else{
				$("#individualPart tbody").append('<tr><td colspan="8">No records Found</td></tr>');
			}
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			pagination(50);
		}
	});
}

function getEventStudentRegList(){
	var evId= $("#eventNameListGroup").val();
	var catId= $("#CategoryNameListGroup").val();
	var progId= $("#progNameListGroup").val();
	var houseId= $("#houseNameListGroup").val();
	var accId =$("#hacademicyaer").val();

	if(evId=="" || evId==null){
		evId="%%";
	}
	if(catId=="" || catId==null){
		catId="%%";
	}
	if(progId=="" || progId==null){
		progId="%%";
	}
	if(houseId=="" || houseId==null){
		houseId="%%";
	}
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getEventStudentRegList",
		data:{	"evId":evId,
			"catId":catId,
			"progId":progId,
			"houseId":houseId,
			"accId":accId,
		},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#allstudent > tbody").empty();
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#allstudent > tbody").append("<tr class='dispaly-table' id='"+result.data[i].registrationId+"'>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+result.data[i].registrationNo+"</td>" +
							"<td><table id='participants"+i+"'><thead><tr>" +
							"<th>Ad.No.</th><th>Name</th><th>Roll No.</th><th>Class</th><th>School</th><th>Image</th>" +
							"</tr></thead></table></td>"+
							"<td>"+result.data[i].houseName+"</td>"+
							"<td><span class='glyphicon glyphicon-trash deleteRow'></span></td>"+
					"</tr>");
					for(var j=0;j<result.data[i].participantsList.length;j++){
						$("#participants"+i).append("<tr class='"+result.data[i].participantsList[j].captainHighlight+"'>" +
								"<td>"+result.data[i].participantsList[j].admissionNo+"</td>"+
								"<td>"+result.data[i].participantsList[j].studentName+"</td>"+
								"<td>"+result.data[i].participantsList[j].rollNumber+"</td>" +
								"<td>"+result.data[i].participantsList[j].className+"</td>" +
								"<td>"+result.data[i].participantsList[j].location+"</td>" +
								"<td><img  class='fancybox'  src='"+result.data[i].participantsList[j].imageUrl+"' width='20' height='20' /></td>" +
						"</tr>");

					}
				}
				rowClickable();
				deleteRow();
			}else{
				$("#allstudent > tbody").append('<tr><td colspan="5">No records Found</td></tr>');
			}
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			pagination(50);
		}
	});
}

function rowClickable(){//for group

	$("#allstudent tbody tr td").not("#allstudent tbody tr td:last-child").click(function(){
		id = $(this).closest("tr").attr("id");
		$(".GroupParti").show();
		updateEventStdRegistration(id,"group");
	});
}
function rowClickableInd(){
	$("#individualPart tbody tr td").not("#individualPart tbody tr td:last-child").click(function(){
		id = $(this).closest("tr").attr("id");
		$(".GroupParti").hide();
		updateEventStdRegistration(id,"individual");
	});
}
function deleteRowIndInd(){
	$(".deleteRowInd").click(function(){

		id=	$(this).attr("id");
		$("#deleteDialog").dialog("open");
		$("#deleteDialog").empty();
		$("#deleteDialog").append("<p>Are you sure to delete?</p>");
	});
}

function udpateIndividualSave(){
	var eventName= $("#eventName").val();
	var categoryName = $("#categoryName").val();
	var programName = $("#programName").val();
	var houseName = $("#houseName").val();
	var info_staff = $("#info_staffI").val();
	var info_synopsis =$("#info_synopsis").val();
	var info_req =$("#info_reqI").val();
	var programCaptainHidden =$("#programCaptainHidden").val();


	datalist = {
			"eventName":eventName,
			"categoryName":categoryName,
			"programName":programName,
			"houseName":houseName,

			"info_staffI":info_staff,
			"info_synopsis":info_synopsis,
			"info_reqI":info_req,
			"programCaptainHidden":programCaptainHidden
	};

	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=udpateIndividualSave",
		data : datalist,
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			if(result.status =="updateTrue"){
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("update Record Progressing...");
				$(".successmessagediv").delay(3000).fadeOut();
				$("#studentReg").dialog("close");
				$("input:text").val("");
				$("select").val("");
				$("textarea").val("");
				$("#programCaptain").val("");
				$("#programCaptainHidden").val("");
				getEventStudentRegList();
				getEventStudentRegListIndividual();
				$("input[id$='individual']").prop("checked",false);
				$("input[id$='group']").prop("checked",true);
				$(".GroupParti,.Group").show();
				$(".GroupIndividual").hide();
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



function updateEventStdRegistration(id,regType){
	$("#studentReg").dialog("open");
	$(".Option").hide();
	$(".GroupSyn").show();
	$(".IndivSyn").hide();

	$(".GroupStaffInc,.GroupSyn,.GroupStageReq").show();
	$(".indivStaffInc,.indivStageReq,.IndivSyn").hide();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getDataForUpdateEventStdRegis",
		data:{"id":id},
		success:function(data){
			var result =$.parseJSON(data);
			
			$("#eventName option").hide();
			$("#eventName").val(result.mainList[0].eventId);
			$("#eventIdHidden").val(result.mainList[0].eventId);
			
			getCategoryDropDown();
			$("#categoryName option").hide();
			$("#categoryName").val(result.mainList[0].categoryId);
			$("#categoryIdHidden").val(result.mainList[0].categoryId);
			studentAdmnName((result.mainList[0].captainFullName).split("_")[0]);
			getProgramDropDown(regType);
			$("#programName option").hide();
			$("#programName").val(result.mainList[0].progId);
			$("#progIdHidden").val(result.mainList[0].progId);
			
			$("#houseName").val(result.mainList[0].houseName);
			$("#programCaptain").val(result.mainList[0].captainFullName);
			$("#progCaptainHidden").val(result.mainList[0].captainFullName);
			$("#progCaptainHidden").val(result.mainList[0].captainFullName);
			$("#participantsList").val(result.mainList[0].participantsAdmisNos);
			$("#info_staffG").val(result.mainList[0].infoStaff);
			$("#info_synopsisG").val(result.mainList[0].infoSynopsis);
			$("#info_reqG").val(result.mainList[0].infoReq);
			$("#programCaptainHidden").val(result.mainList[0].captainFullName);
			$("#registrationId").val(result.mainList[0].registrationId);
			
		}
	});
}

function deleteRow(){
	$(".deleteRow").click(function(){
		id=	$(this).closest("tr").attr("id");
		$("#deleteDialog").dialog("open");
		$("#deleteDialog").empty();
		$("#deleteDialog").append("<p>Are you sure to delete?</p>");
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
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}
