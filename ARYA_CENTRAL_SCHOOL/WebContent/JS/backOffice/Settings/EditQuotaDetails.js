$(document)
		.ready(
				function() {

					$('#deleteid')
							.click(
									function() {

										var quotacode = [];

										var count = 0;
										$(
												"input.quotaDetails_Checkbox_Class:checkbox:checked")
												.map(
														function() {

															var term_id = $(
																	this).attr(
																	"id");
															var split_id = term_id
																	.split('_');
															getData = split_id[1]
																	.split(',');
															quotacode
																	.push(getData[0]);
															count++;

														});
										if (count == 0 || count > 1) {

											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select any one Quota");
											return false;

										} else if (quotacode != "") {
											var quotacode1 = quotacode
													.toString();
											var x = confirm("Are you sure you want to delete this Quota Details");

											if (x) {
												var termcodeToBeDeleted = {
													"quotaid" : quotacode1
												};
												$
														.ajax({
															type : 'POST',
															url : "QuotaMenu.html?method=deleteQuotaDetails",
															data : termcodeToBeDeleted,
															async : false,
															success : function(
																	data) {
																var result = $
																		.parseJSON(data);

																if (result.status == "Quota Deleted Successfully") {

																	$(
																			".errormessagediv")
																			.hide();
																	$(
																			".successmessagediv")
																			.show();
																	$(
																			".successmessage")
																			.text(
																					result.status);

																} else {
																	$(
																			".errormessagediv")
																			.show();
																	$(
																			".validateTips")
																			.text(
																					result.status);
																}

																setTimeout(
																		function() {

																			location
																					.reload();

																		}, 1000);

															}

														});
											}
										} else {
											$(".validateTips")
													.html(
															"Select any Department to Delete");
										}
									});

					$('#editdep')
							.click(
									function() {

										$(".errormessagediv").hide();
										$(".errormessagediv1").hide();

										var count = 0;

										$(
												"input.quotaDetails_Checkbox_Class:checkbox:checked")
												.map(
														function() {

															var checkdep_id = $(
																	this).attr(
																	"id");
															var split_id = checkdep_id
																	.split('_');
															getData = split_id[1]
																	.split(',');
															count++;
														});

										if (count == 0 || count > 1) {

											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select any one Quota");

											return false;

										}

										else {

											var id = getData[0];

											window.location.href = "QuotaMenu.html?method=editQuotaDetails&edit_name="
													+ id;
   
										}
										
										
										
										
									});

					$('#search').click(function() {

						var searchname = $("#searchname").val().trim();

						myFunction();
						$("#searchname").val("");
						
					});
					
					$('#xls').click(
							function() {
								
								var searchTerm = $("#searchexamid").val().trim();
								window.location.href = 'QuotaMenu.html?method=downloadQuotaDetailsXLS&searchTerm='+ searchTerm;
							});
					
					
					
					
					$('#pdfDownload').click(
							function() {
								
								var searchTerm = $("#searchexamid").val().trim();								
								window.location.href = 'QuotaMenu.html?method=downloadQuotaDetailsPDF&searchTerm='+ searchTerm;
							});
					
					
					
					
					
					
					

				});

function myFunction() {

	document.getElementById("myForm").submit();

}
