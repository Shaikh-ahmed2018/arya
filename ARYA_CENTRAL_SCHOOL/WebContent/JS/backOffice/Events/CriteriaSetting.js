$(document).ready(function(){
	getCriteriaSettingList();
	i=1;
	$("#eventNameList").change(function(){
		getCategoryName();
		getProgrameeName();
		getCriteriaList();
		getCriteriaSettingList();
	});
	
	$("#categoryList").change(function(){
	    getProgrameeName();
		getCriteriaSettingList();
	});
	
	$("#programmeList").change(function(){
		getCriteriaList();
		getCriteriaSettingList();
	});
	
	$("#criteriaList").change(function(){
		getCriteriaSettingList();
	});
	
	
	$("#stageSetting").dialog({
		    autoOpen  : false,
		   
	        maxHeight : 400,
	        width     : 500,
	        height    : 400,
		    modal     : true,
		    title     : "Criteria Setting",
		    buttons   : {
		    	'Save'  : function() {
		    		
		    		criteria = [];
			    		$('input[name="criteria"]').each(function(){
			    		criteria.push($(this).val());
			    		});
			    		
			    		savecriteriadetails(criteria);
			    		/*$(".hidedetails").html("");
			    		$("#No").val("");
			    		$("#judgeName1").val("");
			    		$("#totalMarks").val("");
			    		 */
		    	},
		    	'Close' : function() {
		    		$(this).dialog('close');
		    		 $(".hidedetails").html("");
		    		$("#No").val("");
		    		$("#judgeName1").val("");
		    		$("#totalMarks").val("");
		    		$(".errormessagediv").hide();
		    		$(".form-control").css({
		    			"border":"1px solid #ccc",
		    			"background-color":"#fff"
		    		});
		    	
	             }
		    }
		});
		
		
		$("#plus").click(function(){
			addjudges();
		});
		$("#No").change(function(){
			addjudges();
		});
		/*$("#marksEntry").dialog({
		    autoOpen  : false,
		    maxWidth  :	950,
	        maxHeight : 750,
	        width     : 600,
	        height    : 500,
		    modal     : true,
		    title     : "Enter Judge",
		    buttons   : {
		    	'Save'  : function() {
		    		 no = $("#No").val();
		    		for(var k=0;k <(Number(no));k++){
		    			$("#judgemarks thead tr").append("<th>JUDGE "+k+"</th>");
		    			}
		    		   $(this).dialog('close');
		    	},
		    	'Close' : function() {
	                 $(this).dialog('close');
			    		}
		}
		});*/
		
		$("#delete").click(function(){
			$(".addjudges").remove();
		});
		
		

	 
	
});
function addjudges(){
	
	

	
		num=Number($("#No").val());
		i++;
		if(i<=num){
			$(".addjudges ").append('<div class="hidedetails"><div class="form-group clearfix delete" id="delete'+i+'"><label for="inputPassword" class="control-label col-xs-7" id="removeLabel'+i+'" style="margin-top: -22px;text-align: left;">Criteria</label><div class="col-xs-2">'+
			'<input type="text" name="criteria" class="form-control" id="criteria'+i+'" style="min-width:170px;mix-blend-mode: 119;margin-top: -18px;"/></div><span class="glyphicon glyphicon-trash" style="margin-top: -67px;margin-left: 323px;" onclick="removeTrash('+i+')"></div></span></div></div>');
		}
	
		
	}

	


function removeTrash(num){
	
	$('#delete'+num).remove();
	
}	
function  checkCriteriaDuplication() {
var eventName = $("#eventName").val();
	var programmeName = $("#programmeName").val();
	var category = $("#category").val();
	var Criteria = $("#Criteria").val();
	
	var datalist = {
			"eventName" : eventName,
			"programmeName" : programmeName,
			"category":category,
			"Criteria":Criteria,

	};

	$.ajax({
		type : "POST",
		url : "EventsMenu.html?method=validateCriteria",
		data : datalist,
		async : false,
		success : function(data) {
				var result = $.parseJSON(data);
				if(result.list =="true" ) {
				$("#Criteria").val("");
				showError("#Criteria","Criteria details already exist ");
				return false;
			}

		}
	});
}


function savecriteriadetails(critId){
	
	
	crilen=[];
	var no=$("#No").val().trim();
	var judgeName1=$("#judgeName1").val().trim();
	var totalmarks=$("#totalMarks").val().trim();
	crilen=critId.toString().split(",");
	
	
	if(no=="" || no==undefined||no=="0"){
		showError("#no","Enter Maximum Criteria");
		return false;
	}
	else if(totalmarks==""|| totalmarks==undefined||totalmarks=="-")
	{
	showError("#totalmarks","Enter Total Marks");
	return false;
	}
	else if(judgeName1=="" || judgeName1==undefined||judgeName1=="-"){
		showError("#judgeName1","Enter Criteria Name");
		return false;
	}
	else if(crilen.length!=Number($("#No").val()))
		{
		showError("#no","Enter All "+" "+$("#No").val()+"  Criteria Name");
		return false;
		}
	
	
	else
		{
	datalist ={
			"criteria" : critId.toString(),
			"eventdetails" : $("#criteriade").val(),
			"criteriaid":$("#hiddencriteriaid").val(),
			"totalmarks":$("#totalMarks").val(),
	 };
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=saveCriteriaSetting",
		data:datalist,
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
				
				if(result.status=="true"){
		 			$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Adding Record....");
					$(".successmessagediv").delay(3000).fadeOut();
					$("#stageSetting").dialog('close');
					getCriteriaSettingList();
				 setTimeout(function()
						 {
						window.location.href="EventsMenu.html?method=CriteriaSetting";
						 
					},3000);
					
				}
				else if(result.status=="updateTrue"){
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Updating Record......");
					$(".successmessagediv").delay(3000).fadeOut();
					
					$('input:text').val("");
		    		$('textarea').val("");
		    		$('select.event').val("");
					$("#stageSetting").dialog('close');
				
					$("#eventName option").show();
					getCriteriaSettingList();
					setTimeout(function()
							 {
							window.location.href="EventsMenu.html?method=CriteriaSetting";
							 
						},3000);
					
				}
				else{
		 			$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Failed to Add Record...");
					$(".errormessagediv").delay(3000).fadeOut();
		 		 }
			}
		});
	
		}	
	
}
	
function rowClickable(){
	
	
	$("#allstudent tbody tr td").not ("#allstudent tbody tr td:last-child").click(function(){
		values = $(this).closest("tr").attr("class");
		var num=values.substring(values.lastIndexOf(',')+1);
		
		var critlist= $(this).closest("tr").find(".list").text().split(",");
			
		
		value=$(this).closest("tr").attr("id");
		
		$("#criteriade").val($(this).closest("tr").attr("class"));
		$("#hiddencriteriaid").val($(this).closest("tr").attr("id"));
		
		if($("#hiddencriteriaid").val().trim()=="")
		{
			
		$("#No").val("");
		$("#judgeName1").val("");
		$("#totalMarks").val("");
		}
		
		else if(value!=undefined && value!="")
			{
			criterialen=values.lastIndexOf(',');
			
			var num=values.substring(values.lastIndexOf(',')+1);
			$("#No").val(values.substring(values.lastIndexOf(',')+1));
			$("#judgeName1").val($(this).closest("tr").find(".list").text().split(",")[0]);
			$("#totalMarks").val($(this).closest("tr").find(".marks").text());
			//$("#totalMarks").val();
			for(var i=1;i<Number(num);i++)
				{

				$(".addjudges").append('<div class="hidedetails"><div class="form-group clearfix delete" id="delete'+i+'"><label for="inputPassword" class="control-label col-xs-7" id="removeLabel'+i+'" style="margin-top: -22px;text-align: left;">Criteria</label><div class="col-xs-2">'+
				'<input type="text" name="criteria" class="form-control" value="'+critlist[i]+'" id="criteria'+i+'" style="min-width:170px;mix-blend-mode: 119;margin-top: -18px;"/></div><span class="glyphicon glyphicon-trash" style="margin-top: -67px;margin-left: 323px;" onclick="removeTrash('+i+')"></div></span></div></div>');
			}
			}
		
		$("#stageSetting").dialog("open");
		
		
		
	});
}



function updateCriteria(id){
	
	
	$("#stageSetting").dialog("open");
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getDataForUpdateCriteria",
		data:{"id":id},
		asunc:false,
		success:function(data){
			var result =$.parseJSON(data);
		
			
	}
	});
}





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

function getCriteriaList(){
	var event_id = $("#eventNameList").val();
	var cat_id = $("#categoryList").val();
	var id = $("#programmeList").val();
	
	$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getCriteriaList",
	async:false,
	data:{"id":id,"event_id":event_id,"cat_id":cat_id},
	success:function(data){
		var result = $.parseJSON(data);
		$("#criteriaList").empty();
		$("#criteriaList").append("<option value=''>All</option>");
		
		for(var i=0;i<result.data.length;i++){
		$("#criteriaList").append("<option value='"+result.data[i].criteria+"'>" 
				+result.data[i].criteria+"</option>");
		}
	}
});
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
function getCriteriaSettingList(){
	
	var id =$("#eventName").val();
	var hiddenCriteriaId =$("#hiddenCriteriaId").val();
	var eventNameList = $("#eventNameList").val();
	var categoryList = $("#categoryList").val();
	var programmeList = $("#programmeList").val();

	var criteriaList = $("#criteriaList").val();
	if(id==""){
		id="all";
	}
	if(programmeList==""){
		programmeList="all";
	}
	if(categoryList==""){
		categoryList="all";
	}
	if(criteriaList==""){
		criteriaList="all";
	}
	
datalist={
		"id":id,
		"eventNameList":eventNameList,
		"programmeList":programmeList,
		"categoryList":categoryList,
		"criteriaList":criteriaList,
		},
$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getCriteriaSettingList",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			
			$("#allstudent tbody").empty();                                  
			if(result.data.length>0){
			for(var i=0; i<result.data.length; i++){
				
				$("#allstudent tbody").append("<tr id='"+result.data[i].criteriaId+"'class='"+result.data[i].eventId+","+result.data[i].categoryId+","+result.data[i].progId+","+result.data[i].criterialength+"'>" 
						+"<td>"+(i+1)+"</td>"
						+"<td>"+result.data[i].eventName+"</td>"
						+"<td>"+result.data[i].category+"</td>"
						
						+"<td>"+result.data[i].programmeName+"</td>" 
						
						+"<td class='list'>"+result.data[i].criteria+"</td>"
						+"<td class='marks'>"+result.data[i].maxMarks+"</td>"
						
						
						
						+"<td><span class='glyphicon glyphicon-trash deleteRow'></span></td>"
						+"</tr>");
					}
			
		/*	$(".deleteRow").show();
			if($(".list").text() == "-"){
				$(".list").closest("tr").find(".deleteRow").hide();
			}*/
			
			$("#allstudent tr").each(function(){
				
				if($(this).find(".list").text() == "-"){
					$(this).find(".list").closest("tr").find(".deleteRow").hide();
				}
			});
			
			rowClickable();
			//css design
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
			
			deleteCriteria();
			}
			
			else{
				$("#allstudent tbody").append("<tr><td colspan=8'>No Record Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.data.length);
			pagination(50);
		}
	});     
}

function deleteCriteria(){
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
		title : "Criteria Setting Details",
		buttons : {

			"Yes" : function() {
            
            	
	$.ajax({
		type:"post",
		url:"EventsMenu.html?method=deleteCriteria",
		data:{"id":id},
		async:false,
		success:function(data){
			var result = $.parseJSON(data);
			if(result.status=="true"){
				$(".successmessagediv").show();
				$(".validateTips").text("Deleting Record Progressing......");
				$(".successmessagediv").delay(3000).fadeOut();
				getCriteriaSettingList();
				
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
