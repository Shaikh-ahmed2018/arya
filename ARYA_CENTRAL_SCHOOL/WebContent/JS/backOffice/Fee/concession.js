function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction()

{

	var id = $('#concessionId').val();

	var name = $('#concessionname').val();
	
	var percentage = $('#percentage').val();
	

	if (name == "" || name == null)

	{

		$(".errormessagediv1").show();

		$(".validateTips1").text("Enter Concession Name");
		 
		document.getElementById("concessionname").style.border = "1px solid #AF2C2C";
		document.getElementById("concessionname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);

		return false;

	}
	if (percentage == "" || percentage == null)

	{

		$(".errormessagediv1").show();

		$(".validateTips1").text("Enter Concession Percentage");
		document.getElementById("percentage").style.border = "1px solid #AF2C2C";
		document.getElementById("percentage").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);

		return false;

	}



	var status = false;

	datalist = {
		"name" : name,
		"id" : id,
		"percentage" : percentage
	},

	$.ajax({

		type : "POST",

		url : "feeconcession.html?method=getnamecount",

		data : datalist,

		async : false,

		success : function(data)

		{

			var result = $.parseJSON(data);

			if (result.message)

			{

				$('#concessionname').val("");

				$(".successmessagediv").hide();

				$(".errormessagediv1").show();

				$(".validateTips1").text("Name Already Exists");

				status = false;

			}

			else

			{

				status = true;

			}

		}

	});

	return status;

}

$(document)
		.ready(
				function()

				{
					
							
					
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
						$(".errormessagediv1").hide();

					}, 3000);

					
					
					$("#selectAll").change(function(){
						$(".streamDetails_Checkbox_Class").prop("checked",$(this).prop("checked"));
					
					
					
					
					$('#view')
					.click(
							function()

							{

								window.location.href = "adminMenu.html?method=FeeConcessionList";

							});


					
                          $('#submit')
							.click(
									function()

									{
										//alert("gfdsgf");
										
										var concessionId = $('#concessionId').val();
										
										//alert("concessionId"+concessionId);

										var concessionname = $('#concessionname').val();

										var percentage = $('#percentage').val().trim();

										var description = $('#description').val().trim();
										if (myFunction())
											{
										datalist = {
												    "concessionId" :concessionId,
													"concessionname" : concessionname,
													"percentage" : percentage,
													"description" : description
												},
                                                     //alert("ddd" +JSON.stringify(datalist));
												$
														.ajax({

															type : "POST",

															url : "adminMenu.html?method=insertConcesssionDetails",

															data : datalist,

															success : function(
																	data)

															{

																var result = $.parseJSON(data);
																// alert("response"+result.jsonResponse);

																if (result.jsonResponse == "Concession Details Added Successfully") {
																	$(
																			".errormessagediv1")
																			.hide();
																	$(
																			".successmessagediv")
																			.show();
																	$(
																			".validateTips")
																			.text(
																					"Concession Details Added Successfully");

																	setTimeout(
																			function() {

																				window.location.href = "adminMenu.html?method=FeeConcessionList";

																			},
																			3000);

																}

																if (result.jsonResponse == "Concession Details not Added Successfully") {

																	$(
																			".errormessagediv1")
																			.hide();
																	$(
																			".successmessagediv")
																			.show();
																	$(
																			".validateTips")
																			.text(
																					"Concession Details not Added Successfully");

																	setTimeout(
																			function() {

																				window.location.href = "adminMenu.html?method=addfeeconcession";

																			},
																			3000);

																}
																if (result.jsonResponse == "Concession Details  Update Successfully") {
																	$(
																			".errormessagediv1")
																			.hide();
																	$(
																			".successmessagediv")
																			.show();
																	$(
																			".validateTips")
																			.text(
																					"Concession Details  Update Successfully");

																	setTimeout(
																			function() {

																				window.location.href = "adminMenu.html?method=FeeConcessionList";

																			},
																			3000);

																}

																if (result.jsonResponse == "Concession Details  not Updated Successfully") {

																	$(
																			".errormessagediv1")
																			.hide();
																	$(
																			".successmessagediv")
																			.show();
																	$(
																			".validateTips")
																			.text(
																					"Concession Details  not Updated Successfully");

																	setTimeout(
																			function() {

																				window.location.href = "adminMenu.html?method=addfeeconcession";

																			},
																			3000);

																}

																/*	var result = $.parseJSON(data);
																	
																	
																	
																	window.location.href = "addfee.html?method=addfeedetails&result="+ result.jsonResponse;
																	
																	
																	if(result.jsonResponse=='')
																	{
																		
																	
																	 setTimeout(function(){
																			
																		 window.location.href="adminMenu.html?method=examList";
																	 
																	 },2000);
																	}*/

															

																/*var result = $
																		.parseJSON(data);

																window.location.href = "adminMenu.html?method=FeeConcessionList&result="
																		+ result.jsonResponse;*/

															}

														});

									}
									});
                          
                      	$('#editfee')
						.click(
								function()

								{
			
									var cnt = 0;
			
									$('input.streamDetails_Checkbox_Class:checkbox:checked')
											.map(
													function() {

														var term_id = $(this).attr("id");
														var split_id = term_id.split('_');
														getData = split_id[1].split(',');

														cnt++;
													});

									if (cnt == 0 || cnt > 1) {

										$(".errormessagediv").show();
										$(".validateTips")
												.text(
														"Select Any One Fee Concession Details");

									}

									else {
										

										var name = getData[0];

										window.location.href = "feeconcession.html?method=EditConcesssionFeeDetails&name="+name;
										
									}

								});
                      	
                    	$("#search")
						.click(
								function()

								{
									var searchvalue = $('#searchvalue').val();
							
													
									
									window.location.href = "adminMenu.html?method=FeeConcessionList&searchvalue="
										+ searchvalue;
									

								});
                    	
                    	$('#deletefee')
						.click(
								function() {

									var cnt = 0;

									$(
											'input.streamDetails_Checkbox_Class:checkbox:checked')
											.map(
													function()

													{

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
														"Select Any One Fee Concession Details");

									} else {

										var x = confirm("Are You Sure You Want To Delete This Fee Concession Details");
										if (x) {

											var datalist = 'concession_name='
													+ getData[0];

											$
													.ajax({
														type : "GET",
														url : "feeconcession.html?method=Delete",
														data : datalist,
														async : false,

														success : function(
																response) {
															var result = $
																	.parseJSON(response);

															if (result.jsonResponse == "Fee Concession Details Deleted Successfully") {

																window.location.href = "adminMenu.html?method=FeeConcessionList&value="
																		+ result.jsonResponse;

															}
															if (result.jsonResponse == "Fee Concession Details Not Deleted Successfully") {
																$(
																		".errormessagediv")
																		.show();
																$(
																		".validateTips")
																		.text(
																				result.jsonResponse);
															}

															else if (result.jsonResponse == "Concession Details Already Mapped") {

																$(
																		".errormessagediv")
																		.show();
																$(
																		".validateTips")
																		.text(
																				result.jsonResponse);

															}

															setTimeout(
																	function() {

																		location
																				.reload();

																	}, 3000);

														}

													});

										}
									}

								});
                    	$("#excelDownload").click(function(){
                    		
							var searchvalue = $('#feeconcessionsearchid').val();

                			
                			
                			window.location.href = "feeconcession.html?method=ConcessionDetailsExcelReport&searchvalue="
										+ searchvalue;
                				
                		});
                		
                		$("#pdfDownload").click(function(){
                			
							var searchvalue = $('#feeconcessionsearchid').val();

                			
                			window.location.href = "feeconcession.html?method=ConcessionDetailsPDFReport&searchvalue="
										+ searchvalue;
                				
                		});
									});


				});


function HideError() 
{
	
document.getElementById("concessionname").style.border = "1px solid #ccc";
document.getElementById("concessionname").style.backgroundColor = "#fff";

document.getElementById("percentage").style.border = "1px solid #ccc";
document.getElementById("percentage").style.backgroundColor = "#fff";

}
