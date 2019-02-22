var value1;
var value2;
var value3;
function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
$(document)
		.ready(
				function() {

					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
						$(".errormessagediv1").hide();

					}, 3000);

					$('#excelDownload')
							.click(
									function() {

										var searchTerm = $(
												"#SerchTermstagesetupid").val()
												.trim();

										window.location.href = "stagefeesetup.html?method=downloadstagefeesetupXLS&searchTerm="
												+ searchTerm;

									});
					$("#pdfDownload")
							.click(
									function() {

										var searchTerm = $(
												"#SerchTermstagesetupid").val()
												.trim();

										window.location.href = "stagefeesetup.html?method=downloadstagefeesetupPDF&searchTerm="
												+ searchTerm;

									});

					$("#search")
							.click(
									function() {

										var searchTerm = $("#searchterm").val()
												.trim();

										window.location.href = "adminMenu.html?method=getStageFeeSetup&searchTerm="
												+ searchTerm;

									});

					$('#edit')
							.click(
									function() {

										var count = 0;
										var feecode = null;

										$('input[name="selectBox"]:checked')
												.each(function() {
													count = count + 1;
													feecode = this.value;
												});

										if (count > 1 || count == 0) {

											$('.errormessagediv').show();
											$('.validateTips').text(
													"Select any one record");

										} else {

											window.location.href = "stagefeesetup.html?method=getEditedFeeSetupDetails&FeeCode="
													+ feecode;

										}

									});

					$('#AllSessCheckBox')
							.change(
									function() {

										var bCheck = document
												.getElementById('AllSessCheckBox').checked;
										if (bCheck) {
											$(
													'input.feeSetupDetails_Checkbox_Class')
													.attr('checked', true);
										} else {
											$(
													'input.feeSetupDetails_Checkbox_Class')
													.attr('checked', false);
										}
									});

					$("#approve")
							.click(
									function() {

										var tot = 0;
										var getData = [];
										$(
												".feeSetupDetails_Checkbox_Class:checked")
												.each(
														function() {
															var id = $(this)
																	.attr('id');
															var split_id = id
																	.split('_');
															getData
																	.push(split_id[1]);
															tot++;
														});
										if (getData != null) {
											if (tot == 0) {
												$(".errormessagediv1").show();
												$(".validateTips")
														.text(
																"Select any Stage to be Approve");
											}
										}

										if (tot != 0) {

											var classID = $("#hclassId").val();
											var yearID = $("#hyearId").val();
											var termID = $("#htermId").val();

											document.location.href = "stagefeesetup.html?method=insertApproveFees&FeeCode="
													+ yearID
													+ ","
													+ classID
													+ ","
													+ termID
													+ "&FeeIdtoApprove="
													+ getData;

										}
									});

					var feearray = [];
					var feecodearray = [];
					$("#savefee")
							.click(
									function() {

										var classId = $("#hclassId").val();
										var yearId = $("#hyearId").val();
										var termCode = $("#htermId").val();

										$("table.main tr")
												.each(
														function() {

															var feeamount = $(
																	this)
																	.find(
																			"td input[name=feeamount]")
																	.val();
															var feecode = $(
																	this)
																	.find(
																			"td span[name=feecode]")
																	.text();

															if (feeamount != undefined) {

																feearray
																		.push(feeamount);
															}
															if (feecode != ""
																	&& feecode != undefined) {

																if (feearray.length == feecodearray.length) {

																} else {

																	feecodearray
																			.push(feecode);
																}

															}

														});

										var count = 0;

										var re_for_double = '^[0-9]*\.[0-9]*$';
										var int_regex = /^\d+$/;

										if (feecodearray.length != 0) {

											for ( var i = 0; i < feecodearray.length; i++) {

												if (feearray[i] != undefined) {
													if (!((int_regex
															.test(feearray[i])) || (feearray[i]
															.match(re_for_double)))) {

														$(".errormessagediv")
																.show();
														$(".validateTips")
																.text(
																		"Fee Amount should be Numbers");
														count++;
													} else {
														$(".errormessagediv")
																.hide();
														$('select')
																.prop(
																		'selectedIndex',
																		0);

													}

												}
											}

											if (count > 0) {

												$(".errormessagediv").show();
												$(".validateTips")
														.text(
																"Fee Amount should be Numbers");

												return false;

											} else {

												document.location.href = "stagefeesetup.html?method=insertFeeAmount&FeeCodeArray="
														+ feecodearray
														+ "&FeeArray="
														+ feearray
														+ "&classid="
														+ classId
														+ "&acadamicYear="
														+ yearId
														+ "&term="
														+ termCode;

											}
										}

									});

					$(".pagelinks").text(function() {

						var toreplace = $(this).html();
						toreplace = toreplace.replace("[", "");
						toreplace = toreplace.replace("]", "");
						toreplace = toreplace.replace(" [", "");
						toreplace = toreplace.replace("]", "");
						toreplace = toreplace.replace("/", "");

						$(this).html(toreplace);

					});

					

				});

function deleteFee(val1) {

	var x = confirm("Are you sure you want to delete this Fee");
	if (x) {

		var split_id = val1.split(',');

		window.location.href = "stagefeesetup.html?method=deleteFees&FeeSettingsCode="
				+ split_id[0].trim()
				+ "&FeeCode="
				+ split_id[1].trim()
				+ "&acadamicYear="
				+ split_id[2].trim()
				+ "&classid="
				+ split_id[3].trim() + "&term=" + split_id[4].trim();

	}

}
