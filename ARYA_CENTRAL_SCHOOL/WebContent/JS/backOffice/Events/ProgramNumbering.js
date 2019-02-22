$(document).ready(function(){
	$("#radio").change(function() {
		$(".selected").prop('checked', $(this).prop("checked"));

	});
	
	$("#radios").change(function() {
		$(".select").prop('checked', $(this).prop("checked"));
		
		
	});
	
	
	$("#event").change(function()
			{
		
	getstageName();
	
			});
	
	$("#stage").change(function() {
		table1();
		table2();
		$('.select').prop('checked',false);
		$('.selectall').prop('checked',false);
	});
	
		$("#allstudent1 tbody tr td").click(function(){
			id = $(this).closest("tr").attr("id");
			evenid = $(this).closest("tr").attr("class");
		});
	
		$("#excelDownload").click(function(){

			var event = $("#event").val();
			var stage=$("#stage").val();
			
			if (event =="") {
				showError("#event","Select Event Name");
				
				return false;
			}
			else if(stage=="")
				{
				showError("#stage","Select Stage Name");
				HideError();	 
				return false;
				}
			

				window.location.href = 'EventsMenu.html?method=getProgramNumberingExcelReport&event='+event+'&stage='+stage+'';
					
			
		});

		$("#pdfDownload").click(function(){
			
			var event = $("#event").val();
			var stage=$("#stage").val();
			
			if (event =="") {
				showError("#event","Select Event Name");
				
				return false;
			}
			else if(stage=="")
				{
				showError("#stage","Select Stage Name");
				HideError();	 
				return false;
				}
		
		
				window.location.href = 'EventsMenu.html?method=getProgramNumberingPdfReport&event='+event+'&stage='+stage+'';
			
		});
		$("#print").click(function(){
			
			var event = $("#event").val();
			var stage=$("#stage").val();
			
			if (event =="") {
				showError("#event","Select Event Name");
				
				return false;
			}
			else if(stage=="")
				{
				showError("#stage","Select Stage Name");
				HideError();	 
				return false;
				}
			
			
			$.ajax({
				type: "POST",
				url:"EventsMenu.html?method=printProgramNumberingList&event="+event+"&stage="+stage,
				success : function(data){
					
				}
			});
		});
	
	
});



function addIndividualParticipant() {
	
	category = [];
	progparticipent=[];	
	participent=[];
	regid=[];
	stuid=[];
	progid=[];
	catid=[];
	
	id=[];
	tablerowlength =0;
	checkedid = "";
	checked="";
	var event=$("#event").val();
	var stage=$("#stage").val();
	
	i=Number($("#tdcount").val());
	
	if($(".selected:checked").length == 0){
		$(".errormessagediv").show();
		$(".validateTips").text("Participants Details Not Found...");
		$(".errormessagediv").delay(1000).fadeOut();
	}else{
		
	
 		$("input[class='selected']:checked").each(function(){
 			regid.push($(this).val());
 			category.push($(this).closest('tr').find('.category').text());
 			progparticipent.push($(this).closest('tr').find('.progparticipent').text());
 			participent.push($(this).closest('tr').find('.participent').text());
 			stuid.push($(this).closest('tr').find('.participent').attr("id"));
 			progid.push($(this).closest('tr').find('.progparticipent').attr("id"));
 			catid.push($(this).closest('tr').find('.category').attr("id"));
 		
 			 i++;
 			id.push(i);
 			
 			$(this).closest("tr").remove();
 			$('.select').prop('checked',false);
			$('.selectedall').prop('checked',false);
			 
 		});
 		$("#allstudents tbody tr").each(function (){
 			regid.push($(this).find(".select").val());
 			category.push($(this).find('.cat').text());
 			progparticipent.push($(this).find('.prog').text());
 			participent.push($(this).find('.part').text());
 			stuid.push($(this).find('.part').attr("id"));
 			progid.push($(this).find('.prog').attr("id"));
 			catid.push($(this).find('.cat').attr("id"));
 			id.push($(this).find('.id').attr("id"));
 		});
 		
 		$.ajax({
 			
 		   
			   type: 'post',
				url : "EventsMenu.html?method=saveProgramNumberingDetails",
				data : {
					"regid":regid.toString(),
					"id":id.toString(),
					"stageId" : stage,
					"event":event,
					"category" : catid.toString(),
					"progparticipant":progid.toString(),
					"participants":stuid.toString(),
					"checked":checked,
					"tablerowlength":tablerowlength,
					"checkedid":checkedid,
					"allocate" : "rightallocation",
					
				},
				success : function(response) {
					var result = $.parseJSON(response);
				
				if (result.status=="true") {
					table2();
		    	}
				
				$('.select').prop('checked',false);
				$('.selectall').prop('checked',false);
				}
			});
 		
 		
}}

function deselectIndivivdualParticipant() {
	id=[];
	cat = [];
	cattid=[];
	prog=[];
	programid=[];
	participentid=[];
	part=[];
	numid=[];
	regid=[];
	
	if($(".select:checked").length == 0){
		$(".errormessagediv").show();
		$(".validateTips").text("Participants Details Not Found...");
		$(".errormessagediv").delay(1000).fadeOut();
	}else{
		
 		$("input[class='select']:checked").each(function(){
 			
 			id.push($(this).closest('tr').find('.categoryid').attr("id"));
 			regid.push($(this).val());
 			
 			cat.push($(this).closest('tr').find('.cat').text());
 			prog.push($(this).closest('tr').find('.prog').text());
 			part.push($(this).closest('tr').find('.part').text());
 			cattid.push($(this).closest('tr').find('.cat').attr("id"));
 			programid.push($(this).closest('tr').find('.prog').attr("id"));
 			participentid.push($(this).closest('tr').find('.part').attr("id"));
 			$(this).closest("tr").remove();
 			$('.select').prop('checked',false);
			$('.selectall').prop('checked',false);
 		});
 		
 		
 		deleterow(id);
 		
 		if($(".select").length!=$(".select:checked").length){
 			
        }
 		
 		
 		
	}
	assign();
 		if($("#allrecords").text()=="No Record Found")
	{
	$("#allstudent1 tbody").empty();
 		for(var i=0;i<cat.length;i++){
 			$("#allstudent1 tbody").append("<tr>" 
 					+"<td class='catid'><input type='checkbox' class='selected' value='"+regid[i]+"'/></td>"+
 			"<td class='category' id='"+cattid[i]+"'>"+cat[i]+"</td><td class='progparticipent' id='"+programid[i]+"'>"+prog[i]+"</td><td class='participent' id='"+participentid[i]+"'>"+part[i]+"</td></tr>");
 		}
 		$(".selected").change(function(){
	        if($(".selected").length==$(".selected:checked").length){
		         $("#radio").prop("checked",true);
	         }
	       else{
		           $("#radio").prop("checked",false);
	           }
	});
	}
else
	{
	for(var i=0;i<cat.length;i++){
		
			$("#allstudent1 tbody").append("<tr>" 
					+"<td class='catid'><input type='checkbox' class='selected' value='"+regid[i]+"'/></td>"+
			"<td class='category' id='"+cattid[i]+"'>"+cat[i]+"</td><td class='progparticipent' id='"+programid[i]+"'>"+prog[i]+"</td><td class='participent' id='"+participentid[i]+"'>"+part[i]+"</td></tr>");
		}
	$(".selected").change(function(){
        if($(".selected").length==$(".selected:checked").length){
	         $("#radio").prop("checked",true);
         }
       else{
	           $("#radio").prop("checked",false);
           }
});
	}
 		}
 	function deleterow(id){
 		
 		$.ajax({
			type:"post",
			url:"EventsMenu.html?method=deleteProgramNumber",
			data:{"id":id.toString()
				},
			async:false,
			success:function(data){
				var result = $.parseJSON(data);
				if(result.status=="true"){
					table2();
					recalculate();
					
				}
			}
		});
 		
 	}
	
	
	
		function getstageName(){
	
			var id = $("#event").val();
			$.ajax({
			type:'post',
			url:"EventsMenu.html?method=getstageList",
			async:false,
			data:{"id":id},
			success:function(data){
				var result = $.parseJSON(data);
				$("#stage").empty();
				$("#stage").append("<option value=''>-------select-------</option>");
				
				for(var i=0;i<result.data.length;i++){
				$("#stage").append("<option value='"+result.data[i].stageId+"'>" 
						+result.data[i].stageName+"</option>");
				}
			}
		});
		}
		
		function recalculate(){
			
 			var tlength=$("#allstudents tbody tr").length;
 		
 			$("#allstudents tbody tr").each(function(tlength){
 				$(this).closest("tr").find(".id").text(tlength+1);
 			});
 			
 			}
	
		function assign() {
		
			
			var event=$("#event").val();
			var stage=$("#stage").val();
			
			if (event =="") {
				showError("#event","Select Event Name");
				
				return false;
			}
			else if(stage=="")
				{
				showError("#stage","Select Stage Name");
				HideError();	 
				return false;
				}
			else{
				HideError();
				$(".errormessagediv").hide();
				
            count=0;
            regid=[];
            id=[];
            checkedid=[];
			category = [];
			progparticipent=[];	
			participent=[];
		 		$("#allstudents tbody tr").each(function(){
		 		
		 			regid.push($(this).closest('tr').find('input[type="checkbox"]').val());
		 			checkedid.push($(this).closest('tr').find('.categoryid').attr('id'));
		 			id.push($(this).closest('tr').find('.id').text());
		 			category.push($(this).closest('tr').find('.cat').attr('id'));
		 			progparticipent.push($(this).closest('tr').find('.prog').attr('id'));
		 			participent.push($(this).closest('tr').find('.part').attr('id'));
		 			count++;
		 		});
		 		
		 		
		 		
			$.ajax({
   
			   type: 'post',
				url : "EventsMenu.html?method=saveProgramNumberingDetails",
				data : {
					"regid":regid.toString(),
					"id":id.toString(),
					"stageId" : stage,
					"event":event,
					"category" : category.toString(),
					"progparticipant":progparticipent.toString(),
					"participants":participent.toString(),
					
					"allocate" : "allocation",
				},
				success : function(response) {
					var result = $.parseJSON(response);
			/*	
				if (result.status=="true") {
					table2();
		    	}*/
				if (result.status=="true") {
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Program Numbering Done Successfully...");
					$(".successmessagediv").delay(2000).fadeOut();
					table2();
					
				    	 
		    	}
				
				$('.select').prop('checked',false);
				$('.selectall').prop('checked',false);
				}
			});
		 				}
			}
			
	

		function stageDetailMapping() {
			

			var stageVal = $("#stage > option:selected").val();
			$("#copy").empty();
			if (stageVal != 0) {
				$.ajax({
					type : "POST",
					url :  "EventsMenu.html?method=getMappedParticipants",
					data : {"stageVal": stageVal},
					success : function(data) {
						var result= $.parseJSON(data);
					
						for ( var i = 0; i < result.list.length; i++) {
							$('#copy').append(
									'<option value="' + result.list[i].participantId
											+ '">' +result.list[i].participantName +'</option>');
						}
					}
				});
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
		document.getElementById("event").style.border = "1px solid #ccc";
		document.getElementById("event").style.backgroundColor = "#fff";
		}
		
		function table1()
		{
			var stage = $("#stage").val();
			var event=$("#event").val();
			$.ajax({
				type:'post',
				url:"EventsMenu.html?method=getProgramNumberingDetails",
				data:{
					"stageId" : stage,
					"event":event,
				},
				async:false,
			    success:function(data){
					var result = $.parseJSON(data);
				
					$("#allstudent1 tbody").empty();                                  
					if(result.data.length>0){
					for(var i=0; i<result.data.length; i++){
						$("#allstudent1 tbody").append("<tr class='program'>"
								+"<td class='catid'><input type='checkbox' class='selected' id='radio'  value='"+result.data[i].registrationId+"' /></td>"
								+"<td class='category' id='"+result.data[i].categoryId+"' >"+result.data[i].categoryName +"</td>"
								+"<td class='progparticipent' id='"+result.data[i].programmeId+"' >"+result.data[i].programmeName +"</td>"
								+"<td class='participent' id='"+result.data[i].studentId+"'>"+result.data[i].participantName +"</td>"
								+"</tr>");
							}
				
					}else{
						$("#allstudent1 tbody").append("<tr><td colspan=4' id='allrecords'>No Record Found</td></tr>");
						}
					$(".selected").change(function(){
				        if($(".selected").length==$(".selected:checked").length){
					         $("#radio").prop("checked",true);
				         }
				       else{
					           $("#radio").prop("checked",false);
				           }
				});
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.data.length);
					pagination(50);
				}
			}); 
		}
		
		function table2()
		{
			var stage = $("#stage").val();
			var event=$("#event").val();
			$.ajax({
				type:'post',
				url:"EventsMenu.html?method=getMappedProgramNumberingDetails",
				data:{
					"stageId" : stage,
					"event":event,
				},
				async:false,
			    success:function(data){
					var result = $.parseJSON(data);
				
					$("#allstudents tbody").empty();    
					
					$("#tdcount").val(result.data.length);
					if(result.data.length==0)
						{
						$("#iconsimg").hide();
						$("#print").hide();
						
						return false;
						}
					else
						{$("#iconsimg").show();
						$("#print").show();
						
						}
					if(result.data.length>0){
					for(var i=0; i<result.data.length; i++){
						$("#allstudents tbody").append("<tr class='programs'>"
								+"<td class='categoryid' id='"+result.data[i].programNumberId+"'><input type='checkbox' class='select' id='radios' value='"+result.data[i].registrationId+"'  /></td>"
								+"<td class='id' id="+result.data[i].programNumber+">"+result.data[i].programNumber +"</td>"
								+"<td class='cat' id="+result.data[i].categoryId+">"+result.data[i].categoryName +"</td>"
								+"<td class='prog' id="+result.data[i].programmeId+">"+result.data[i].programmeName +"</td>"
								+"<td class='part' id="+result.data[i].participantId+">"+result.data[i].participantName +"</td>"
								+"</tr>");
							}
				
					}else{
						$("#allstudents tbody").append("<tr><td colspan=5' id='records'>No Record Found</td></tr>");
						}
					$(".select").change(function(){
				        if($(".select").length==$(".select:checked").length){
					         $("#radios").prop("checked",true);
				         }
				       else{
					           $("#radios").prop("checked",false);
				           }
				});
					
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.data.length);
					pagination(50);
				}
			}); 
		}
		
	