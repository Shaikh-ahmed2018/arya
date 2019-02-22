$(document)
		.ready(
				function()

				{
					
					
				
					
					/*
					 * $("#dateofAppointmentId").datepicker({ dateFormat :
					 * "dd-mm-yy", defaultDate : "+1w", maxDate : 0, changeYear :
					 * true, changeMonth : true, numberOfMonths : 1 });
					 */

					
					
					
					/*
					 * var statusCheck=$("#allstudent tbody
					 * tr").find("td:nth-child(8)").text();
					 * if(statusCheck=="Pending Review"){
					 * $("#editadmission").attr("readonly",false); } else{
					 * $("#editadmission").attr("readonly",true); }
					 */

					var classhiddenid = $("#classhiddenid").val();
					if (classhiddenid != null) {

						$(
								"#classname option[value="
										+ $("#classhiddenid").val() + "]")
								.attr("selected", 'true');

					}

					var hprevious_classname = $("#hprevious_classname").val();
					if (hprevious_classname != null) {

						$(
								"#previous_classname option[value="
										+ $("#hprevious_classname").val() + "]")
								.attr("selected", 'true');

					}

					$("#save")
							.click(
									function()

									{
										var temporary_id = $('#temporary_id').val();
										var test = $('#test').val();
										var max_marks = $('#max_marks').val();
										var recommended_status = $('#recommended_status').val();
										var evaluation_date = $('#evaluation_date').val();
										var marks_secured = $('#marks_secured').val();
										var remarks = $('#remarks').val();
										var evaluated_by = $('#evaluated_by').val();

										
										if (evaluation_date == ""|| evaluation_date == null) {
											  
											  $(".errormessagediv1").show();
											  
											  $(".validateTips").text(" Select Date"); $("#remarks").focus();
											  document.getElementById("evaluation_date").style.border ="1px solid #AF2C2C";
											  document.getElementById("evaluation_date").style.backgroundColor = "#FFF7F7"; setTimeout( function() {
											  $('#errormessagediv1') .fadeOut(); },
											  3000);
											   }	
										
									else if (test == ""|| test == null) 
									
										{
									    $(".errormessagediv1").show();
									    $(".validateTips").text("Select Test");
										 $("#test").focus();
									 	  document.getElementById("test").style.border ="1px solid #AF2C2C";
										  document.getElementById("test").style.backgroundColor = "#FFF7F7"; setTimeout( function() {
										  $('#errormessagediv1') .fadeOut(); },
										  3000);
										   } 
									
									else if (max_marks == ""|| max_marks == null) {
										  
										  $(".errormessagediv1").show();
										  
										  $(".validateTips").text("Enter the Maximum Marks"); $("#remarks").focus();
										  document.getElementById("max_marks").style.border = "1px solid #AF2C2C";
										  document.getElementById("max_marks").style.backgroundColor = "#FFF7F7"; setTimeout( function() {
										  $('#errormessagediv1') .fadeOut(); },
										  3000);
										   }
									
									
									else if (marks_secured == ""|| marks_secured == null) {
										  
										  $(".errormessagediv1").show();
										  
										  $(".validateTips").text("Enter Marks Secured"); $("#remarks").focus();
										  document.getElementById("marks_secured").style.border = "1px solid #AF2C2C";
										  document.getElementById("marks_secured").style.backgroundColor ="#FFF7F7";
										  setTimeout( function() {
										  $('#errormessagediv1') .fadeOut(); },
										  3000);
										   }
										
									else if (recommended_status == ""|| recommended_status == null) {
										  
										  $(".errormessagediv1").show();
										  
										  $(".validateTips").text("Enter Recommended Status"); $("#remarks").focus();
										  document.getElementById("recommended_status").style.border = "1px solid #AF2C2C";
										  document.getElementById("recommended_status").style.backgroundColor ="#FFF7F7";
										  setTimeout( function() {
										  $('#errormessagediv1') .fadeOut(); },
										  3000);
										   }
										
									else if (remarks == ""|| remarks == null) {
										  
										  $(".errormessagediv1").show();
										  
										  $(".validateTips").text("Enter Remarks"); $("#remarks").focus();
										  document.getElementById("remarks").style.border = "1px solid #AF2C2C";
										  document.getElementById("remarks").style.backgroundColor ="#FFF7F7";
										  setTimeout( function() {
										  $('#errormessagediv1') .fadeOut(); },
										  3000);
										   }
										  
										  else {


										window.location.href = "parentrequiresappointment.html?method=UpdatingCalledForEvaluationStatus&temporary_id="
												+ temporary_id
												+ "&test="
												+ test
												+ "&max_marks="
												+ max_marks
												+ "&recommended_status="
												+ recommended_status
												+ "&evaluation_date="
												+ evaluation_date
												+ "&marks_secured="
												+ marks_secured
												+ "&remarks="
												+ remarks
												+ "&evaluated_by="
												+evaluated_by;

										 } 

									});


					$('#editcalledadmission')
							.click(
									function()

									{
										var cnt = 0;

										$(
												'input.temporary_admission_details_Checkbox_Class:checkbox:checked')
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

											$(".errormessagediv").show();
											$(".validateTips")
													.text(
															"Select Any One Admission Details");

										}

										else {

											var name = getData[0];

											window.location.href = "parentrequiresappointment.html?method=EditingForCalledAdmissionDetails&name="
													+ name;

										}

									});
					
					

					$('#delete')
							.click(
									function()

									{
										var cnt = 0;

										$(
												'input.temporary_admission_details_Checkbox_Class:checkbox:checked')
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

											$(".errormessagediv").show();
											$(".validateTips")
													.text(
															"Select Any One Admission Details");

										}

										else {

											var name = getData[0];

											window.location.href = "parentrequiresappointment.html?method=DeleteParentRequiresAppointment&name="
													+ name;

										}

									});
					
					
					$("#search")
					.click(
							function()

							{
								
								
								var searchname = $('#searchvalue').val();

								window.location.href = "adminMenu.html?method=CalledForEvaluationList&searchname="
										+ searchname;

							});

					$("#excelDownload")
							.click(
									function()

									{
										var searchname = $('#searchvalue')
												.val();

										window.location.href = "inventorymenu.html?method=InventoryTypeXLS&searchname="
												+ searchname;

									});

					$("#pdfDownload")
							.click(
									function()

									{
										var searchname = $('#searchvalue')
												.val();

										window.location.href = "inventorymenu.html?method=InventoryTypePDF&searchname="
												+ searchname;

									});
					
					$("#dateofAppointmentId").datepicker({
						dateFormat : "dd-mm-yy",
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true,
						numberOfMonths : 1,
						minDate : 0,
					});
					
					
					$("#evaluation_date").datepicker({
						dateFormat : "dd-mm-yy",
						defaultDate : "+1w",
						maxDate : 0,
						changeYear : true,
						changeMonth : true,
						numberOfMonths : 1
					});
					
					$('#datetimepicker3').datetimepicker({
						pickDate : false
					});
					$('#datetimepicker4').datetimepicker({
						pickDate : false
					});

				});

function HideError() {
	
	
	
	document.getElementById("remarks").style.border = "1px solid #ccc";
	document.getElementById("remarks").style.backgroundColor = "#fff";
	document.getElementById("evaluated_by").style.border = "1px solid #ccc";
	document.getElementById("evaluated_by").style.backgroundColor = "#fff";
	document.getElementById("marks_secured").style.border = "1px solid #ccc";
	document.getElementById("marks_secured").style.backgroundColor = "#fff";
	document.getElementById("evaluation_date").style.border = "1px solid #ccc";
	document.getElementById("evaluation_date").style.backgroundColor = "#fff";
	document.getElementById("recommended_status").style.border = "1px solid #ccc";
	document.getElementById("recommended_status").style.backgroundColor = "#fff";
	document.getElementById("max_marks").style.border = "1px solid #ccc";
	document.getElementById("max_marks").style.backgroundColor = "#fff";
	document.getElementById("test").style.border = "1px solid #ccc";
	document.getElementById("test").style.backgroundColor = "#fff";
	
	

}