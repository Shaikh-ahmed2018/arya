$(document).ready(function(){
	
	getProgramSettingList();
	
	
	$('#datetimepicker3').datetimepicker({
		pickDate : false
	});
	$(".data").hide();
	$(".dataTeam").hide();
		$("#eventNameList").change(function(){
			getCategoryNameList();
			getProgramNameList();
			getProgramSettingList();
		});
		$("#categoryNameList").change(function(){
			getProgramNameList();
			getProgramSettingList();
		});
		$("#programNameList").change(function(){
			getProgramSettingList();
		});
		$("#eventName").change(function(){
			getCategoryName();
			getProgramDate();
		});
		
		$("#progType").change(function(){
			if($(this).val()=="individual"){
				$(".dataTeam").hide();
			}
			else $(".dataTeam").show();
			
		});
		
		
		
		
		
		$("#addgroup").click(function(){
			$("#progIdHidden").val("");
			$("#eventName option").show();
			$("#categoryName option").show();
			$("#progCreation").dialog("open");
		});
			
		$("#progCreation").dialog({
		    autoOpen  : false,
		    maxWidth  :	950,
	        maxHeight : 500,
	        width     : 950,
	        height    :	650,
		    modal     : true,
		    title     : "Program Setting",
		    buttons   : {
		    	'Save'  : function() {
		    		var pointer=$(this);
		    		saveProgram(pointer);
		    	},
		    	'Close' : function() {
		    		$('input:text').val("");
		    		$('textarea').val("");
		    		$('select').val(""); 
		    		$('input:checkbox').prop("checked",false);
		    		$(".data").hide();
		    		$("#progIdHidden").val("");
		    		
		    		getProgramSettingList();
		    		$(this).dialog('close');
		    		$("#eventName option").show();
		    		$("#categoryName option").show();
		    		
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
		
		$("#PartiNo,#termsAllowed,#NoOfChildHouse").keypress(function(e){
			if(e.which !=8 && e.which !=0 && (e.which < 48 || e.which > 57)){
				return false;
			}
		});
		
		
		$("#deleteDialog").dialog({
			autoOpen: false,
		    modal: true,					    
		    title:'Program Details',
		    buttons : {
				"Yes" : function() {
					  datalist={
							"id":id,
							};
					  $.ajax({
							type:"post",
							url:"EventsMenu.html?method=deleteProgram",
							data:{"id":id},
							async:false,
							success:function(data){
								var result = $.parseJSON(data);
								if(result.status=="true"){
									$(".successmessagediv").show();
									$(".validateTips").text("Delete Record Progressing...");
									$('.successmessagediv').delay(3000).slideUp();
									getProgramSettingList();
								}
								else if(result.status == "false"){
									$(".errormessagediv").show();
									$(".validateTips").text("Selected Category is Mapped Cannot be Deleted");
									$('.errormessagediv').delay(3000).slideUp();
									getProgramSettingList();
									
								}
							}
						});
					 $(this).dialog('close');
					  },

				"No" : function() {
					  $(this).dialog('close');
					  
				}
			}
		});
	
		
});//jquery ends
function getProgramNameList(){
	var catId = $("#categoryNameList").val();
	var evId = $("#eventNameList").val();
	datalist ={
			"catId":catId,
			"evId":evId,
			"flag":"onLoad",
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getprogramName",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			$("#programNameList").empty();
			$("#programNameList").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
			$("#programNameList").append("<option value='"+result.data[i].progId+"'>" 
					+result.data[i].progName+"</option>");
			}
		}
	});
}

function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}
function getCategoryNameList(){
	var id = $("#eventNameList").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getCategoryName",
		async:false,
		data:{"id":id,
			"flag":"onLoad",
			},
		success:function(data){
			var result = $.parseJSON(data);
			$("#categoryNameList").empty();
			$("#categoryNameList").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
			$("#categoryNameList").append("<option value='"+result.data[i].categoryId+"'>" 
					+result.data[i].categoryName+"</option>");
			}
		}
	});	
}


function getCategoryName(){
	$("#progIdHidden").val("");
	$("#eventName option").show();
	$("#categoryName option").show();
	var id = $("#eventName").val();
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getCategoryName",
		async:false,
		data:{"id":id,
				"flag":"onLoad"
				},
		success:function(data){
			var result = $.parseJSON(data);
			$("#categoryName").empty();
			$("#categoryName").append("<option value=''>----Select----</option>");
			for(var i=0;i<result.data.length;i++){
			$("#categoryName").append("<option value='"+result.data[i].categoryId+"'>" 
					+result.data[i].categoryName+"</option>");
			}
		}
	});
}

function saveProgram(pointer){
	var judgeList = [];
	var eventName =$("#eventName").val();
	var categoryName =$("#categoryName").val();
	var progName =$("#progName").val().trim();
	var partiType =$("#partiType").val();
	
	var PartiNo =$("#PartiNo").val();
	var duration =$("#duration").val();
	
	var progType =$("#progType").val();

	var progDate =$("#progDate").val();

	var infoStaff =$("#infoStaff").val();
	var infoGeneral =$("#infoGeneral").val();
	var progIdHidden =$("#progIdHidden").val();
	var categoryIdHidden=$("#categoryIdHidden").val();
	var eventIdHidden=$("#eventIdHidden").val();
	var programNameHidden =$("#programNameHidden").val();
	var termsAllowed = $("#termsAllowed").val();
	
	judgeList.push($("#judgeName").val());
	
	if($("#progType").val()=="group" && $("#termsAllowed").val()==""){
			showError("#termsAllowed","Enter Number of Participating teams");
			return false;
	}else
	if($("#isHouse").prop("checked")==true){
		isHouse="yes";
		var NoOfChildHouse =$("#NoOfChildHouse").val();
	}
	else {
		isHouse="no";
	}
	if(eventName=="" || eventName==undefined){
		showError("#eventName","Enter Event Name");
		return false;
	}
	if(categoryName=="" || categoryName==undefined){
		showError("#categoryName","Enter Category Name");
		return false;
	}
	if(progType=="" || progType==undefined){
		showError("#progType","Enter Program Type");
		return false;
	}
	if(progName=="" || progName==undefined){
		showError("#progName","Enter Program Name");
		return false;
	}
	if(progDate=="" || progDate==undefined){
		showError("#progDate","Enter the Program date");
		return false;
	}

	if(PartiNo=="" || PartiNo==undefined){
		showError("#PartiNo","Enter Number of Participants");
		return false;
	}
	if(duration=="" || duration==undefined){
		showError("#duration","Enter Duration");
		return false;
	}
	if(partiType=="" || partiType==undefined){
		showError("#partiType","Enter Participants Type");
		return false;
	}
	if(judgeName=="" || judgeName==undefined){
		showError("#judgeName","Select judge for this Program");
		return false;
	}

	datalist ={
			"eventName":eventName,
			"categoryName":categoryName,
			"progName":progName,
			"partiType":partiType,
			
			"termsAllowed":termsAllowed,
			"PartiNo":PartiNo,
			"duration":duration,
			
			"progType":progType,
			"NoOfChildHouse":NoOfChildHouse,
			"progDate":progDate,
			
			"infoStaff":infoStaff,
			"infoGeneral":infoGeneral,
			"isHouse":isHouse,
			"progIdHidden":progIdHidden,
			"eventIdHidden":eventIdHidden,
			"categoryIdHidden":categoryIdHidden,
			"programNameHidden":programNameHidden,	
			"judgeList":judgeList.toString(),
	};
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=saveProgram",
		data:datalist,
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			if(result.status=="true"){
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Adding Record Progressing...");
					setTimeout(function() {
						window.location.href="EventsMenu.html?method=programSetting";
					}, 3000);
					$('input:text').val("");
					$('select').val("");
					$('textarea').val("");
					$('input:checkbox').prop("checked",false);
					$(".data").hide();
					$("#progIdHidden").val("");
					$("#progCreation").dialog('close');
					getProgramSettingList();
			}
			else if(result.status=="updateDuplicate"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				showError("#progName","Program Name Already Exists");
				$("#progName").val("");
				$(".errormessagediv").delay(3000).fadeOut();
			}
			
			else if(result.status=="Duplicate"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Program Name Already Exists");
				$("#progName").val("");
				$(".errormessagediv").delay(3000).fadeOut();
			}
			
			else if(result.status=="updateTrue"){
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Update Record Progressing...");
				setTimeout(function() {
					window.location.href="EventsMenu.html?method=programSetting";
				}, 3000);
				$('input:text').val("");
				$('select').val("");
				$('textarea').val("");
				$('input:checkbox').prop("checked",false);
				$(".data").hide();
				$("#progIdHidden").val("");
				$("#eventName option").show();
				$("#categoryName option").show();
				$("#progCreation").dialog('close');
				getProgramSettingList();
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

function getProgramSettingList(){
	
	var catId = $("#categoryNameList").val();
	var evId = $("#eventNameList").val();
	var progId = $("#programNameList").val();

	if(catId=="" || catId==null){
		catId="all";
	}
	if(evId=="" || evId==null){
		evId="all";
	}
	if(catId=="" || catId==null){
		catId="all";
	}
	datalist ={
			"catId":catId,
			"evId":evId,
			"progId":progId
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getProgramSettingList",
		data:datalist,
		async:false,
		success:function(data){
			var result = $.parseJSON(data);
			$("#allstudent tbody").empty();                                  
			if(result.data.length>0){
			for(var i=0; i<result.data.length; i++){
				$("#allstudent tbody").append("<tr id='"+result.data[i].progId+"'>" 
						+"<td>"+(i+1)+"</td>"
						+"<td>"+result.data[i].eventName+"</td>"
						+"<td>"+result.data[i].categoryName+"</td>"
						+"<td>"+result.data[i].programmeName+"</td>"
						
						+"<td>"+result.data[i].progType+"</td>"
					//	+"<td>"+result.data[i].partiType+"</td>"
					//	+"<td>"+result.data[i].termsAllowed+"</td>" 
					//	+"<td>"+result.data[i].partiNo+"</td>" 
						+"<td>"+result.data[i].duration+" Min</td>" 
						+"<td>"+result.data[i].progDate+"</td>" 
					//	+"<td>"+result.data[i].isHouse+"</td>" 
					//	+"<td>"+result.data[i].noOfChildHouse+"</td>"
						+"<td><span class='glyphicon glyphicon-trash deleteRow'></span></td>"
						+"</tr>");
			}
			deleteRow();
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
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='9' style='border-right: 1px solid #dedede;'>No Record Found</td></tr>");
				$("#allstudent tbody").not($("#allstudent tbody tr td:nth-child(10)")).css({"border":"1px solid #dedede"});
				}
			$(".numberOfItems").empty();
			$(".numberOfItems").append("No of records "+result.data.length+ ".");
			pagination(50);
		}
		});
}

function deleteRow(){
		$(".deleteRow").click(function(){
			id=$(this).closest("tr").attr("id");
				$("#deleteDialog").dialog("open");
				$("#deleteDialog").empty();
				$("#deleteDialog").append("<p>Are you sure to delete?</p>");
		});
}
	
function rowClickable(){
	$("#allstudent tbody tr td").not("#allstudent tbody tr td:last-child").click(function(){
		id=$(this).closest("tr").attr("id");
			getDataforUpdateProgram(id);
		});
	}
	
function getDataforUpdateProgram(id){
	
	$("#progCreation").dialog("open");
	
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getDataforUpdateProgram",
		data:{"id":id},
		async:false,
		success:function(data){
			var result =$.parseJSON(data);

		
			
			$("#eventName option").hide();
			$("#eventName").keypress(function(e){
				if(e.which !=8 && e.which!=0 && (e.which<48 || e.which>57)){
					return false;
				}
			});
			$("#eventName").val(result.data[0].eventId);
			$("#eventIdHidden").val(result.data[0].eventId);
			$("#categoryName option").hide();
			getCategoryName();
			$("#categoryName").val(result.data[0].categoryId);
			$("#categoryIdHidden").val(result.data[0].categoryId);
			
			$("#progIdHidden").val(result.data[0].progId);
			$("#progName").val(result.data[0].progName);
			$("#partiType").val(result.data[0].partiType);
			$("#PartiNo").val(result.data[0].partiNo);
				
			$("#termsAllowed").val(result.data[0].termsAllowed);
			$("#duration").val(result.data[0].duration);
			$("#progType").val(result.data[0].progType);
			$("#progDate").val(result.data[0].progDate);
		
			$("#judgeName").val(result.data[0].judgeList);
		
			$("#infoStaff").val(result.data[0].infoStaff);
			$("#infoGeneral").val(result.data[0].infoGeneral);
			$("#programNameHidden").val(result.data[0].progName);
			
			
			if(result.data[0].isHouse=="yes"){
				$("#isHouse").prop("checked",true);
				$(".data").show();
				$("#NoOfChildHouse").val(result.data[0].noOfChildHouse);
				
			}else{
				$("#isHouse").prop("checked",false);
				$(".data").hide();
			};
		}
		
		
	});
	
}
function getProgramDate(){
	$("#progDate").datepicker("destroy");
	var evId=$("#eventName").val();
		$.ajax({
			type:'post',
			url:"EventsMenu.html?method=getProgramDateByEventID",
			data:{"evId":evId},
			asunc:false,
			success:function(data){
				var result =$.parseJSON(data);
				$("#progDate").datepicker({
					dateFormat : "dd-mm-yy",
					minDate : result.data[0].startFrom,
					maxDate:result.data[0].endTo,
					changeMonth : true,
					changeYear : true,
					onClose : function(selectedDate) {
					}
				});
			}
		});
}

function getJudgeList(){
		$.ajax({
			type:'post',
			url:"EventsMenu.html?method=getJudgeListforProgram",
			asunc:false,
			success:function(data){
					var result = $.parseJSON(data);
					$("#judgeName").empty();
					$("#judgeName").append("<option value=''>----Select----</option>");
					for(var i=0;i<result.data.length;i++){
					$("#judgeName").append("<option value='"+result.data[i].teacherId+"'>" 
							+result.data[i].teacherName+"</option>");
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
	$(".errormessagediv").delay(3000).fadeOut();
}
