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
					
					
					$("#selectall").change(function(){
						$(".temporary_admission_details_Checkbox_Class").prop("checked",$(this).prop("checked"));
					});
					
					

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
										var status = $('#status').val();
										var remarks = $('#remarks').val();
										
										var dateofAppointmentId = $('#dateofAppointmentId').val();
										var starttime = $('#starttime').val();

										
										
										
									if (status == ""|| status == null) 
									{
									    $(".errormessagediv1").show();
									    $(".validateTips").text("Please	Select Status");
										 $("#status").focus();
									 	  document.getElementById("status").style.border =
										  "1px solid #AF2C2C";
										  document.getElementById("status").style.backgroundColor =
										  "#FFF7F7"; setTimeout( function() {
										  $('#errormessagediv1') .fadeOut(); },
										  3000);
										   } 
									else if (remarks == ""|| remarks == null) {
										  
										  $(".errormessagediv1").show();
										  
										  $(".validateTips").text("Please Write Remarks"); $("#remarks").focus();
										  document.getElementById("remarks").style.border =
										  "1px solid #AF2C2C";
										  document.getElementById("remarks").style.backgroundColor =
										  "#FFF7F7"; setTimeout( function() {
										  $('#errormessagediv1') .fadeOut(); },
										  3000);
										   }
									
									
									else if (dateofAppointmentId == ""|| dateofAppointmentId == null) {
										  
										  $(".errormessagediv1").show();
										  
										  $(".validateTips").text("Please Select Date Of Appointment"); $("#remarks").focus();
										  document.getElementById("dateofAppointmentId").style.border =
										  "1px solid #AF2C2C";
										  document.getElementById("dateofAppointmentId").style.backgroundColor =
										  "#FFF7F7"; setTimeout( function() {
										  $('#errormessagediv1') .fadeOut(); },
										  3000);
										   }
									
									
									else if (starttime == ""|| starttime == null) {
										  
										  $(".errormessagediv1").show();
										  
										  $(".validateTips").text("Select Appointment Time"); $("#remarks").focus();
										  document.getElementById("starttime").style.border = "1px solid #AF2C2C";
										  document.getElementById("starttime").style.backgroundColor ="#FFF7F7";
										  setTimeout( function() {
										  $('#errormessagediv1') .fadeOut(); },
										  3000);
										   }
										  
										  else {
										 

										window.location.href = "parentrequiresappointment.html?method=UpdatingFirstLevelAdmissionApproval&temporary_id="
												+ temporary_id
												+ "&status="
												+ status
												+ "&remarks="
												+ remarks
												+ "&dateofAppointmentId="
												+ dateofAppointmentId
												+ "&starttime="
												+ starttime;

										 } 

									});

					$("#search")
							.click(
									function()

									{
										
										
										var searchname = $('#searchvalue').val();

										window.location.href = "adminMenu.html?method=admissionsList&searchname="
												+ searchname;

									});

					$('#editadmission')
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

											window.location.href = "parentrequiresappointment.html?method=EditingForAdmissionApproval&name="
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

}