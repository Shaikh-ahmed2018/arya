function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document)
		.ready(
				function()

				{
					$("#Acyearid").val($("#hacademicyaer").val());
					$("#locationname").change(function(){
						var locationId=$(this).val();
						if(locationId=="" || locationId == undefined){
							locationId="all";
						}
						getTermListByJs(locationId,$("#Acyearid").val());
						
					});
					$("#Acyearid").change(function(){
						var locationId=$("#locationname").val();
						if(locationId=="" || locationId == undefined){
							locationId="all";
						}
						getTermListByJs(locationId,$(this).val());
					});
				
					
					$("#selectall").change(function(){
						$(".academic_Checkbox_Class").prop("checked",$(this).prop("checked"));
						
					});
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv1").hide();
					}, 3000);
					
					
					
					
					$("#delete").click(function()
									{
							 getDataArray=[]; //add array1
								getDataArray.push($(this).closest("tr").find("span.academic_Checkbox_Class").attr("id").split('_')[1]);		
											$("#dialog").dialog("open");
											$("#dialog").empty();
											$("#dialog").append("<p>Are You Sure to Delete?</p>");
									});
					
					$("#dialog").dialog({
					    autoOpen  : false,
					    modal     : true,
					    title     : "Term Details",
					    buttons   : {
					              'Yes' : function() {

										/*var datalist = 'name='
												+ getData[0];
*/								var datalist = {'getDataArray':getDataArray.toString()};//create json data3
										$
												.ajax({
													type : "GET",
													url : "termfee.html?method=deleteTermDetails",
													data : datalist,
													async : false,

													success : function(response) {

														var result = $.parseJSON(response);

														if (result.jsonResponse == "Term Details Deleted Successfully") {

															window.location.href = "adminMenu.html?method=termList&result="
																	+ result.jsonResponse;

														}
														if (result.jsonResponse == "Term Details not Deleted Successfully") {
															$(".errormessagediv").show();
															$(".validateTips").text(result.jsonResponse);
														}

														else if (result.jsonResponse == "Term Details Already Mapped") {

															$(".errormessagediv").show();
															$(".validateTips").text("Cannot Delete Record. "+result.jsonResponse);

														}

														setTimeout(function() {

																	location.reload();

																},2000);

													}
												});
										$(this).dialog('close');

									},
					              'No' : function() {
					                 
					                  $(this).dialog('close');
					              }
					                }
					});

					

					

					
					$("#allstudent tbody tr:last td:last").append("<span' class='glyphicon glyphicon-trash' id='delete' style='position: absolute;top:0;bottom:0;margin:auto;'></span>");
				});

function getTermListByJs(locationId,accyear){
	$.ajax({
		type:'POST',
		url:'termfee.html?method=termListByJs',
		data:{"locationId":locationId,"accyear":accyear},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.termlist.length>0){
				for(var i=0;i<result.termlist.length;i++){
					$("#allstudent tbody").append("<tr>" +
							"<td><span name='select' class='academic_Checkbox_Class' id='academicCheckBox_"+result.termlist[i].termid+",'>"+(i+1)+"</span></td>" +
							"<td>"+result.termlist[i].accyear+"</td>" +
							"<td>"+result.termlist[i].locationName+"</td>" +
							"<td>"+result.termlist[i].termname+"</td>" +
							"<td>"+result.termlist[i].startdate+"</td>" +
							"<td>"+result.termlist[i].enddate+"" +
							"</tr>");
				}
				$("#allstudent tbody tr:last td:last").append("<span' class='glyphicon glyphicon-trash' id='delete' style='position: absolute;top:0;bottom:0;margin:auto;'></span>");
				
				$("#delete").click(function()
						{
				 getDataArray=[]; //add array1
					getDataArray.push($(this).closest("tr").find("span.academic_Checkbox_Class").attr("id").split('_')[1]);		
								$("#dialog").dialog("open");
								$("#dialog").empty();
								$("#dialog").append("<p>Are You Sure to Delete?</p>");
						});
		
		$("#dialog").dialog({
		    autoOpen  : false,
		    modal     : true,
		    title     : "Term Details",
		    buttons   : {
		              'Yes' : function() {

							/*var datalist = 'name='
									+ getData[0];
*/								var datalist = {'getDataArray':getDataArray.toString()};//create json data3
							$
									.ajax({
										type : "GET",
										url : "termfee.html?method=deleteTermDetails",
										data : datalist,
										async : false,

										success : function(response) {

											var result = $.parseJSON(response);

											if (result.jsonResponse == "Term Details Deleted Successfully") {

												window.location.href = "adminMenu.html?method=termList&result="
														+ result.jsonResponse;

											}
											if (result.jsonResponse == "Term Details not Deleted Successfully") {
												$(".errormessagediv").show();
												$(".validateTips").text(result.jsonResponse);
											}

											else if (result.jsonResponse == "Term Details Already Mapped") {

												$(".errormessagediv").show();
												$(".validateTips").text("Cannot Delete Record. "+result.jsonResponse);

											}

											setTimeout(function() {

														location.reload();

													},2000);

										}
									});
							$(this).dialog('close');

						},
		              'No' : function() {
		                 
		                  $(this).dialog('close');
		              }
		                }
		});
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='6'>No Record Found</td></tr>");
			}
		}
	});
}
