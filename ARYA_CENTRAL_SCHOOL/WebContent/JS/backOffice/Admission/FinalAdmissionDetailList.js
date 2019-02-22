$(document)
		.ready(
				function()

				{
					

					var classhiddenid = $("#classhiddenid").val();
					if (classhiddenid != null) {

						$(
								"#classname option[value="
										+ $("#classhiddenid").val() + "]")
								.attr("selected", 'true');

					}

					var hprevious_classname = $("#hprevious_classname").val();
					if (hprevious_classname != null) {

						$("#previous_classname option[value="+ $("#hprevious_classname").val() + "]").attr("selected", 'true');

					}

					var htesttype = $("#htesttype").val();
					if (htesttype != null) {

						$("#test option[value="+ $("#htesttype").val() + "]").attr("selected", 'true');

					}
					
					
					var hrecommendation_status = $("#hrecommendation_status").val();
					if (hrecommendation_status != null) {

						$("#recommended_status option[value="+ $("#hrecommendation_status").val() + "]").attr("selected", 'true');

					}
					
					
					
					
					$("#save")
							.click(
									function()

									{
										var temporary_id = $('#temporary_id').val();
										var approval_status = $('#approval_status').val();
										var principal_remarks = $('#principal_remarks').val();
										

										
										if (approval_status == ""|| approval_status == null) {
											  
											  $(".errormessagediv1").show();
											  
											  $(".validateTips").text(" Select Approval Status"); $("#remarks").focus();
											  document.getElementById("approval_status").style.border ="1px solid #AF2C2C";
											  document.getElementById("approval_status").style.backgroundColor = "#FFF7F7"; setTimeout( function() {
											  $('#errormessagediv1') .fadeOut(); },
											  3000);
											   }	
										
									
										  
										  else {


										window.location.href = "parentrequiresappointment.html?method=UpdatingFinalApprovalAdmissionStatus&temporary_id="
												+ temporary_id
												+ "&approval_status="
												+ approval_status
												+ "&principal_remarks="
												+ principal_remarks;

										 } 

									});


					$('#editconfirmedadmission')
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

											window.location.href = "parentrequiresappointment.html?method=EditingForConfirmingAdmissionDetails&name="
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
	
	
	
	document.getElementById("approval_status").style.border = "1px solid #ccc";
	document.getElementById("approval_status").style.backgroundColor = "#fff";

}