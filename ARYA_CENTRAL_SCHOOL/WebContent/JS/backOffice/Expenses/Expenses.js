function myFunction() {

	document.getElementById("myForm").submit();
}
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
					}, 3000);

					$("#editexam")
							.click(
									function() {

										var cnt = 0;

										$(
												'input.class_Checkbox_Exam:checkbox:checked')
												.map(
														function() {

															var check_id = $(
																	this).attr(
																	"id");
															var split_id = check_id
																	.split('_');
															getData = split_id[1]
																	.split(',');

															cnt++;
														});

										if (cnt == 0 || cnt > 1) {

											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select any one Exam");

											return false;
										}

										else {

											var examid = getData[0];

											window.location.href = "examCreationPath.html?method=editExamAction&examid="
													+ examid;

											$('.errormessagediv').hide();
											$(".successmessagediv").show();
											$(".validateTips")
													.text(
															"Exam Updated SuccessFully");
										}

										setTimeout(
												function() {

													window.location.href = "examCreationPath.html?method=getExam";

												}, 2000);

									});

					$("#delete")
							.click(
									function() {

										var depcode = [];
										var count = 0;

										$(
												'input.class_Checkbox_Exam:checkbox:checked')
												.map(
														function() {

															var term_id = $(
																	this).attr(
																	"id");
															var split_id = term_id
																	.split('_');
															getData = split_id[1]
																	.split(',');
															depcode
																	.push(getData[0]);
															// alert(getData[0]);

															count++;
														});

										if (count == 0) {

											$('.errormessagediv').show();
											$('.validateTips').text(
													"Select Any one Exam");
											return false;
										}

										else if (depcode != "") {
											var depcode1 = depcode.toString();
											var x = confirm("Are you sure you want to delete this EXAM");

											if (x) {

												var datalist = 'examid='
														+ depcode1;

												$
														.ajax({
															type : "GET",
															url : "examCreationPath.html?method=deleteExamAction",
															data : datalist,
															async : false,

															success : function(
																	response) {

																var result = $
																		.parseJSON(response);

																$(
																		'.errormessagediv')
																		.hide();
																$(
																		".successmessagediv")
																		.show();
																$(
																		".validateTips")
																		.text(
																				"Exam Deleted SuccessFully");

																setTimeout(
																		function() {

																			location
																					.reload();

																		}, 2000);
															}

														});

											}

										}

									});

					$('#excelDownload')
							.click(
									function() {

										var searchTerm = $("#searchexamid").val()
												.trim();

										window.location.href = 'examCreationPath.html?method=downloadExamDetailsXLS&searchTerm='
											+ searchTerm;

									});
					$("#pdfDownload")
							.click(
									function() {
										var searchTerm = $("#searchexamid").val()
										.trim();

										window.location.href = "examCreationPath.html?method=downloadExamDetailsPDF&searchTerm="
												+ searchTerm;

									});

					$("#search")
							.click(
									function() {

										var searchTerm = $("#searchterm").val()
												.trim();

										window.location.href = "adminMenu.html?method=examList&searchTerm="
												+ searchTerm;

									});

				});
