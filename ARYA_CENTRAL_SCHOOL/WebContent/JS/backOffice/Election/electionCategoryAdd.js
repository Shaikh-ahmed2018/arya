$(document).ready(function(){

	getElectionCategoryList();
	$("#save").click(function() {
		$("#electionCategoryAddDialog").dialog("open");
		
	getClassList();// for getiing all classes
});
	
$("#allstudent tbody tr td").not("#allstudent tbody tr td:nth-child(10)").click(function(){
	getClassList();
	$("#electionCategoryAddDialog").dialog("open"); 
	$("#categoryName").attr("readonly",true);//for making readonly while updationg
	$("#priority").attr("readonly",true);//for making readonly for updating
	var categoryId=$(this).closest("tr").attr("id"); // previously it was var 
	$("#categoryNameIdHidden").val(categoryId);
	getUpdateElectionCategory(categoryId);//for carreying data for update page
});
$("#electionCategoryAddDialog").dialog({
    autoOpen  : false,
    maxWidth  :	750,
    maxHeight : 500,
    width     : 900,
    height    : 450,
    modal     : true,
    title     : "Category Setting",
    buttons   : {
    	'Save'  : function() {
    		savePopUpData();
    		$("#categoryName").attr("readonly",false);
    		$("#priority").attr("readonly",false);
    	},
    	'Close' : function() {
    		$("#categoryName").attr("readonly",false);
    		$("#priority").attr("readonly",false);
    		$("#categoryNameIdHidden").val("");
    		$("#categoryName").val("");
			$("#participateClass").val("");
			$("#genderWise").val("");
			$("#houseWise").val("");
			$("#priority").val("");
			$("#nominLevel").val("");
			$("#nominFor").val("");
			$("#classWise").val("");
			$("#houseList").val("All");
            $(this).dialog('close');
         }
    }
});
$("#houseWise").change(function(){
	if($(this).val()=="YES"){
		$(".houseListDiv").show();
	}
	else{
		$(".houseListDiv").hide();
	}
});
});
	
function getClassDropdown(){
	
	$.ajax({
		type : 'POST',
		url : "ElectionMenu.html?method=getClassforDropdown",
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
}
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

			$('#participateClass,#nominLevel').html("");
			$('#participateClass,#nominLevel').append('<option value="all">' +"ALL"+ '</option>');
			for ( var j = 0; j < result.ClassList.length; j++) {
				$('#participateClass,#nominLevel').append('<option value="'
						+ result.ClassList[j].classcode + '">'
						+ result.ClassList[j].classname
						+ '</option>');
			}
		}
	});
}
		
function savePopUpData(){

		var categoryName = $("#categoryName").val();
		var participateClass = $("#participateClass").val();
		var genderWise =$("#genderWise").val();
		var houseWise =$("#houseWise").val();
		var priority = $("#priority").val();
		var nominLevel = $("#nominLevel").val();
		var nominFor = $("#nominFor").val();
		var classWise = $("#classWise").val();
		var houseId=$("#houseList").val();
		var accyear=$("#accyearh").val();
		var groupnameh=$("#groupnameh").val();
		var TitleHidden = $("#ElectionTitleh").val();
		var categoryNameIdHidden = $("#categoryNameIdHidden").val();
		
		if (categoryName == "" || categoryName == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Category Name");
			document.getElementById("categoryName").style.border = "1px solid #AF2C2C";
			document.getElementById("categoryName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		else if (participateClass == "" || participateClass == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter participateClass ");
			document.getElementById("participateClass").style.border = "1px solid #AF2C2C";
			document.getElementById("participateClass").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		else if (genderWise == "" || genderWise == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter genderWise ");
			document.getElementById("genderWise").style.border = "1px solid #AF2C2C";
			document.getElementById("genderWise").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		else if (houseWise == "" || houseWise == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter houseWise ");
			document.getElementById("houseWise").style.border = "1px solid #AF2C2C";
			document.getElementById("houseWise").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (priority == "" || priority == null ) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter priority ");
			document.getElementById("priority").style.border = "1px solid #AF2C2C";
			document.getElementById("priority").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		else if (Number(priority) < 1 ) {
			$(".errormessagediv").show();
			$(".validateTips").text("Priority Starts From 1 ");
			document.getElementById("priority").style.border = "1px solid #AF2C2C";
			document.getElementById("priority").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		else if (nominLevel == "" || nominLevel == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter nominLevel ");
			document.getElementById("nominLevel").style.border = "1px solid #AF2C2C";
			document.getElementById("nominLevel").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		else if (nominFor == "" || nominFor == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter nominFor ");
			document.getElementById("nominFor").style.border = "1px solid #AF2C2C";
			document.getElementById("nominFor").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		else if (classWise == "" || classWise == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter classWise ");
			document.getElementById("classWise").style.border = "1px solid #AF2C2C";
			document.getElementById("classWise").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
	
		datalist ={
			
				"categoryName":categoryName.trim().toUpperCase(),
				"participateClass" :participateClass.toString(),
				"genderWise":genderWise,
				"houseWise":houseWise,
				"priority":priority.toString(),
				"nominLevel":nominLevel.toString(),
				"nominFor":nominFor,
				"classWise":classWise,
				"houseId":houseId,
				"accyear" :accyear,
				"groupnameHidden":groupnameh,
				"TitleHidden":TitleHidden,
				"categoryNameIdHidden" :categoryNameIdHidden,
				
				};
		
		$.ajax({
			type:'POST',
			url :"ElectionMenu.html?method=electionCategoryAddPopUp",
			data : datalist,
			async: false,
			success : function(data){
				var result = $.parseJSON(data);
			
			
				
				
				 if(result.status=="true"){
			 			$('.errormessagediv').hide();
						$('.successmessagediv').show();
						$(".validateTips").text("Adding Record Progressing...");
						$(".successmessagediv").delay(3000).fadeOut();
						setTimeout(function() {

						location.reload();
					}, 2000);
				 }	

				//checking the repeated data for update time
				 else  if(result.status=="exists"){
			 			$('.successmessagediv').hide();
						$('.errormessagediv').show();
						$(".validateTips").text("Election Title Already Exists");
						document.getElementById("categoryName").style.border = "1px solid #AF2C2C";
						document.getElementById("categoryName").style.backgroundColor = "#FFF7F7";
						$(".errormessagediv").delay(3000).fadeOut();
						
			 		 }
				 else  if(result.status=="dupPri"){
			 			$('.successmessagediv').hide();
						$(".errormessagediv").show();
						$(".validateTips").text("Priority already assigned");
						document.getElementById("categoryName").style.border = "1px solid #AF2C2C";
						document.getElementById("categoryName").style.backgroundColor = "#FFF7F7";
						$(".errormessagediv").delay(3000).fadeOut();
						
			 		 }
				 else  if(result.status=="started"){
			 			$('.successmessagediv').hide();
						$(".errormessagediv").show();
						$(".validateTips").text("Election Already Started.");
						$(".errormessagediv").delay(3000).fadeOut();
						
			 		 }
				 else if(result.status=="updatetrue"){
			 			$('.errormessagediv').hide();
						$('.successmessagediv').show();
						$(".validateTips").text("Updating Record Progressing...");
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
		return false;
}

//Getting the Listing Page Data
function getElectionList(){



	$.ajax({
		type :'POST',
		url :"adminMenu.html?method=getElectionList",
		data : dataList,
		async : false,
	
		success: function(response)
		{
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.DataList.length>0){
			for(var i=0; i<result.DataList.length; i++){
				$("#allstudent tbody").append("<tr id='"+result.DataList[i].categoryName+"' class='"+result.DataList[i].accid+" "+result.DataList[i].groupid+" "+result.DataList[i].electionTitleId+"'>" +
						"<td>"+result.DataList[i].sno+"</td>" +
						"<td>"+result.DataList[i].accyear+"</td>" +
						"<td>"+result.DataList[i].groupName+"</td>" +
						"<td>"+result.DataList[i].electionTitle+"</td>" +
						"</tr>");
				}
			
			rowClickable();
				}
			else{
				$("#allstudent tbody").append("<tr><td colspan='4'>No Record Found</td></tr>");
				}
}
});
}


function getElectionCategoryList(){
	
	var accyear=$("#accyearh").val();
	var groupnameh=$("#groupnameh").val();
	var TitleHidden = $("#ElectionTitleh").val();
	
			datalist ={
				"accyear" :accyear,
				"groupnameHidden":groupnameh,
				"TitleHidden":TitleHidden,
				};
	$.ajax({
		type:'POST',
		url:"ElectionMenu.html?method=getElectionCategoryList",
		data:datalist,
		async:false,
		success :function(data){
			
			//var result = $.parseJSON(response);
			var result = $.parseJSON(data);
			
			$("#allstudent tbody").empty();
			if(result.DataList.length>0){
			for(var i=0; i<result.DataList.length; i++){
				$("#allstudent tbody").append("<tr id='"+result.DataList[i].electionCategoryId+"' class='"+result.DataList[i].accid+" "+result.DataList[i].groupid+" "+result.DataList[i].electionTitleId+"'>" +
						"<td>"+result.DataList[i].sno+"</td>" +
						"<td>"+result.DataList[i].categoryName+"</td>" +
						"<td>"+result.DataList[i].priority+"</td>" +
						"<td>"+result.DataList[i].participateClass+"</td>" +
						"<td>"+result.DataList[i].genderWise+"</td>" +
						"<td>"+result.DataList[i].houseWise+"</td>" +
						"<td>"+result.DataList[i].classWiseName+"</td>" +
						"<td>"+result.DataList[i].nominFor+"</td>" +
						"<td>"+result.DataList[i].nominLevel+"</td>" +
						"<td><span id='"+result.DataList[i].electionCategoryId+"'  class='glyphicon glyphicon-trash delete'></span></td>" +
				"</tr>");
				}
			
			deleteClassMapping();
			}
			
			else{
				$("#allstudent tbody").append("<tr><td colspan='10'>No Record Found</td></tr>");
				}
			
	}
	
	});
		
	
}


function deleteClassMapping() {
	$(".delete").click(function(){
		//alert($(this).attr("id"));
		
		datalist =$(this).attr("id");
			
		$.ajax({
			type :'POST',
			url : "ElectionMenu.html?method=DeleteElectionCategoryList",
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

//for Fetching data for update page
function getUpdateElectionCategory(categoryId){
	
			datalist ={
				"categoryId" :categoryId,
				};
	$.ajax({
		type:'POST',
		url:"ElectionMenu.html?method=getUpdateElectionCategory",
		data:datalist,
		async:false,
		success :function(response){
			var result = $.parseJSON(response);
		
			$("#categoryName").val(result.DataList[0].categoryName);
			$("#participateClass").val(result.DataList[0].participateClass);
			$("#genderWise").val(result.DataList[0].genderWise);
			$("#houseWise").val(result.DataList[0].houseWise);
			$("#houseList").val(result.DataList[0].houseId);
			if(result.DataList[0].houseWise=="YES"){
				$(".houseListDiv").show();
			}
			else{
				$(".houseListDiv").hide();
			}
			$("#priority").val(result.DataList[0].priority);
			$("#nominLevel").val(result.DataList[0].nominLevel);
			$("#nominFor").val(result.DataList[0].nominFor);
			$("#classWise").val(result.DataList[0].classWiseName);
	}
	});
}

function HideError() {
	document.getElementById("categoryName").style.backgroundColor = "#fff";
	document.getElementById("categoryName").style.border = "1px solid #ccc";
	
	document.getElementById("participateClass").style.border = "1px solid #ccc";
	document.getElementById("participateClass").style.border = "1px solid #ccc";
	
	document.getElementById("genderWise").style.backgroundColor = "#fff";
	document.getElementById("genderWise").style.border = "1px solid #ccc";
	
	document.getElementById("houseWise").style.backgroundColor = "#fff";
	document.getElementById("houseWise").style.border = "1px solid #ccc";
	
	document.getElementById("priority").style.backgroundColor = "#fff";
	document.getElementById("priority").style.border = "1px solid #ccc";
	
	document.getElementById("nominLevel").style.backgroundColor = "#fff";
	document.getElementById("nominLevel").style.border = "1px solid #ccc";
	
	document.getElementById("nominFor").style.backgroundColor = "#fff";
	document.getElementById("nominFor").style.border = "1px solid #ccc";
	
	document.getElementById("classWise").style.backgroundColor = "#fff";
	document.getElementById("classWise").style.border = "1px solid #ccc";
	

	
}