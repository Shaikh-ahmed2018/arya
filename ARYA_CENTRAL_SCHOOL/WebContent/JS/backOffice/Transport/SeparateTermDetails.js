function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document)
		.ready(
				function()

				{
					
					
					if($("#allstudent tbody tr").length ==0){
						$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
					}
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
					var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("?")+1);
					if(pageUrl.split("=")[1]=="separateTransportTermList&searchvalue"){
						$("#searchvalue").focus();
					}
					$('#searchvalue').keypress(function(e){
						if(e.which==13)
						window.location.href = "adminMenu.html?method=separateTransportTermList&searchvalue="+$(this).val().trim();
						
					});
				
					$("#allstudent tbody tr:last td:last").append("<span' class='glyphicon glyphicon-trash' id='delete' style='position: absolute;top:0;bottom:0;margin:auto;margin-bottom: 13px;'></span>");
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
													url : "transport.html?method=deleteSeparateTermDetails",
													data : datalist,
													async : false,

													success : function(response) {

														var result = $.parseJSON(response);

														if (result.jsonResponse == "Term Details Deleted Successfully") {

															window.location.href = "adminMenu.html?method=separateTransportTermList&result="
																	+ result.jsonResponse;

														}
														if (result.jsonResponse == "Term Details not Deleted Successfully") {
															$(".errormessagediv").show();
															$(".validateTips").text(result.jsonResponse);
														}

														else if (result.jsonResponse == "Term Details Already Mapped") {

															$(".errormessagediv").show();
															$(".validateTips").text(result.jsonResponse);

														}

														setTimeout(function() {
																	location.reload();
																}, 3000);
													}
												});
										$(this).dialog('close');
									},
					              'No' : function() {
					                  $(this).dialog('close');
					              }
					                }
					});

					$("#termedit").click(
									function() {

										var cnt = 0;

										$(
												'input.academic_Checkbox_Class:checked')
												.map(
														function() {

															var term_id = $(
																	this).attr(
																	"id");

															var split_id = term_id
																	.split('_');
															getData = split_id[1]
																	.split(',');

															cnt++;
														});

										if (cnt == 0 || cnt > 1) {

											$(".successmessagediv").hide();
											$(".errormessagediv1").show();
											$(".validateTips1").text(
													"Select Any One Checkbox");

										}

										else {

											var id = getData[0];
											window.location.href = "termfee.html?method=edittermDetails&id="
													+ id;

										}

									});

					$("#search")
							.click(
									function()

									{
										var searchvalue = $('#searchvalue')
												.val();

										window.location.href = "adminMenu.html?method=separateTransportTermList&searchvalue="
												+ searchvalue;

									});

					$('#excelDownload')
							.click(
									function() {

										var searchvalue = $('#termsearchid')
										.val();
										window.location.href = 'termfee.html?method=downloadtermlistDetailsXLS&searchvalue='
											+ searchvalue;

									});
					$("#pdfDownload")
							.click(
									function() {
										
										var searchvalue = $('#termsearchid')
										.val();

										window.location.href = "termfee.html?method=downloadtermlistDetailsPDF&searchvalue="
												+ searchvalue;

									});

				});

function getTermListByJs(locationId,accyear){
	$.ajax({
		type:'POST',
		url:'termfee.html?method=TransporttermListByJs',
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
										url : "transport.html?method=deleteSeparateTermDetails",
										data : datalist,
										async : false,

										success : function(response) {

											var result = $.parseJSON(response);

											if (result.jsonResponse == "Term Details Deleted Successfully") {
												$(".successmessagediv").show();
												$(".successmessagediv .validateTips").text(result.jsonResponse);
												
												setTimeout(function() {

													location.reload();

												},2000);

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
