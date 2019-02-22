$(document)
		.ready(
				function() {

					$("#delayed_days").hide();
					$("#fine_amount").hide();
					$("#return_date").hide();

					$("#delayeddays").hide();
					$("#fineamount").hide();
					$("#returndate").hide();
					
					
					
					
					$("#Acyearid").change(function(){
						$("#searchvalue").val("");
						changeAccYear();
						getClassList();
						var classname=$("#classname").val();
						getSectionList(classname);
					});
					
					
					$("#search")
					.click(
							function()

							{
								var searchvalue = $('#searchvalue').val();
								
								window.location.href = "adminMenu.html?method=bookissuedlist&searchvalue="
									+ searchvalue;

							});
					
					$("#xls").click(function()

							{
								var searchvalue = $('#searchvalue').val();

								window.location.href = "bookmenu.html?method=downloadbookissuedetailsXLS&searchvalue="
									+ searchvalue;

							});
			

					
			$("#pdfDownload").click(function()

					{
						
						var searchvalue = $('#searchvalue').val();

						window.location.href = "bookmenu.html?method=downloadbookissuedetailsPDF&searchvalue="
									+ searchvalue;

					});

					$("#actual_return_date").datepicker({
						dateFormat : "dd-mm-yy",
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true

					});

					$("#return_date").datepicker({
						dateFormat : "dd-mm-yy",
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true
					});

					$("#book_issue_date").datepicker({
						dateFormat : "dd-mm-yy",
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true
					});
					
					// filtering from BORROWER TYPE TO BORROWE NAME.....
					
					$('#Borrowertype')
							.change(
									function(event)

									{

										var borrowertype = $(
												"select#Borrowertype").val();

										if (borrowertype == "stu")

										{

													datalist = {
														"student" : borrowertype
													},

													$
															.ajax({

																type : 'POST',
																url : "communicationPath.html?method=getStudent",
																data : datalist,
																success : function(
																		response) {

																	var result = $
																			.parseJSON(response);

																	$(
																			'#borrowername')
																			.html(
																					"");
																	$(
																			'#borrowername')
																			.append(

																					'<option value="'
																							+ ""
																							+ '">'
																							+ "---Select---"

																							+ '</option>');

																	for ( var j = 0; j < result.studentlist.length; j++) {

																		$(
																				'#borrowername')
																				.append(

																						'<option value="'

																								+ result.studentlist[j].studentId
																								+ '">'

																								+ result.studentlist[j].name

																								+ '</option>');

																	}

																}
															});

										}

										if (borrowertype == "tea") {

													datalist = {
														"teacher" : borrowertype
													},

													$
															.ajax({
																type : 'POST',
																url : "communicationPath.html?method=getTeacher",
																data : datalist,
																success : function(
																		response) {

																	var result = $
																			.parseJSON(response);

																	$(
																			'#borrowername')
																			.html(
																					"");
																	$(
																			'#borrowername')
																			.append(

																					'<option value="'
																							+ ""
																							+ '">'
																							+ "---Select---"

																							+ '</option>');

																	for ( var j = 0; j < result.teacherlist.length; j++) {

																		$(
																				'#borrowername')
																				.append(

																						'<option value="'

																								+ result.teacherlist[j].teacherId
																								+ '">'

																								+ result.teacherlist[j].tfastname

																								+ '</option>');

																	}

																}
															});

										}

									});

					// filtering from bookname to barcode

					$('#bookname')
							.change(
									function(event)

									{

										var bookname = $("select#bookname")
												.val();

												datalist = {
													"bookname" : bookname
												},

												$
														.ajax({

															type : 'POST',
															url : "bookmenu.html?method=getbarcodebybookname",
															data : datalist,
															success : function(
																	response) {

																var result = $
																		.parseJSON(response);

																$(
																		'#barcode_number')
																		.html(
																				"");
																$(
																		'#barcode_number')
																		.append(

																				'<option value="'
																						+ ""
																						+ '">'
																						+ "---Select---"

																						+ '</option>');

																for ( var j = 0; j < result.barcodeofbooknames.length; j++) {

																	$(
																			'#barcode_number')
																			.append(

																					'<option value="'
																							+ result.barcodeofbooknames[j].bookid
																							+ '">'

																							+ result.barcodeofbooknames[j].book_barcode

																							+ '</option>');

																}

															}
														});

									});

					$('#savelibrary_issue')
							.click(
									function()

									{

										var Bookissuerid = $('#Bookissuerid')
												.val();
										var Borrowertype = $('#Borrowertype')
												.val();
										var bookname = $('#bookname').val();
										var actual_return_date = $(
												'#actual_return_date').val();
										var fine_per_day = $('#fine_per_day')
												.val();
										var book_issue_date = $(
												'#book_issue_date').val();
										var borrowername = $('#borrowername')
												.val();
										var barcode_number = $(
												'#barcode_number').val();
										var actual_days = $('#actual_days')
												.val();
										var status = $('#status').val();

										if (book_issue_date == ""
												|| book_issue_date == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Book Issue Date");
											document
													.getElementById("book_issue_date").style.border = "1px solid #AF2C2C";
											document
													.getElementById("book_issue_date").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);
											return false;
										} else if (Borrowertype == ""
												|| Borrowertype == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Borrower Types");
											document
													.getElementById("Borrowertype").style.border = "1px solid #AF2C2C";
											document
													.getElementById("Borrowertype").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);
											return false;
										} else if (borrowername == ""
												|| borrowername == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Borrower Name");
											document
													.getElementById("borrowername").style.border = "1px solid #AF2C2C";
											document
													.getElementById("borrowername").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);
											return false;
										} else if (bookname == ""
												|| bookname == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Book Name");
											document.getElementById("bookname").style.border = "1px solid #AF2C2C";
											document.getElementById("bookname").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);
											return false;
										} else if (barcode_number == ""
												|| barcode_number == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Barcode Number");
											document
													.getElementById("barcode_number").style.border = "1px solid #AF2C2C";
											document
													.getElementById("barcode_number").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);
											return false;
										} else if (actual_return_date == ""
												|| actual_return_date == null) {
											$(".errormessagediv").show();
											$(".validateTips")
													.text(
															"Select Actual Return Date");
											document
													.getElementById("actual_return_date").style.border = "1px solid #AF2C2C";
											document
													.getElementById("actual_return_date").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);
											return false;
										} else if (fine_per_day == ""
												|| fine_per_day == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Enter Fine Per Day");
											document
													.getElementById("fine_per_day").style.border = "1px solid #AF2C2C";
											document
													.getElementById("fine_per_day").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);
											return false;
										} else if (status == ""
												|| status == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Status");
											document.getElementById("status").style.border = "1px solid #AF2C2C";
											document.getElementById("status").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);
											return false;
										}

										else 

										{
											

											$.ajax({

														type : "POST",

														url : "bookmenu.html?method=inserting_book_issue_details",

														data : datalist = {
															"Borrowertype" : Borrowertype,
															"bookname" : bookname,
															"actual_return_date" : actual_return_date,
															"fine_per_day" : fine_per_day,
															"book_issue_date" : book_issue_date,
															"borrowername" : borrowername,
															"barcode_number" : barcode_number,
															"actual_days" : actual_days,
															"status" : status

														},

														success : function(response)

														{
															
															var result = $.parseJSON(response);
																												
																										
													$('.errormessagediv').hide();
													
													if(result.insertbookissuelist==true){
														
														$(".successmessagediv").show();
														$(".validateTips").text("Library Book Issued Details Added SuccessFully");
													}
													
													else if(result.insertbookissuelist==false){
														$('.successmessagediv').show();
														$('.validateTips').text("Library Book Issued Not Details Added SuccessFully");
													}
													
																									
													
													 setTimeout(function(){
														   
														 location.reload();
													 
													 },2000);
												
												}

													});
										}

									});
					
					

				});

function DaysCalculator()

{

	var date1 = $('#book_issue_date').val();
	startDate = date1.split("-");
	var dstartdate = new Date(startDate[2], startDate[1] - 1, startDate[0]);

	var date2 = $('#actual_return_date').val();
	endDate = date2.split("-");
	var denddate = new Date(endDate[2], endDate[1] - 1, endDate[0]);

	var oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds

	var diffDays = Math.round(Math.abs((dstartdate.getTime() - denddate
			.getTime())
			/ (oneDay)));

	var total_leave = diffDays + 1;

	$('#actual_days').val(total_leave);

}

function HideError() {

	document.getElementById("Borrowertype").style.border = "1px solid #ccc";
	document.getElementById("Borrowertype").style.backgroundColor = "#fff";
	document.getElementById("bookname").style.border = "1px solid #ccc";
	document.getElementById("bookname").style.backgroundColor = "#fff";
	document.getElementById("actual_return_date").style.border = "1px solid #ccc";
	document.getElementById("actual_return_date").style.backgroundColor = "#fff";
	document.getElementById("fine_per_day").style.border = "1px solid #ccc";
	document.getElementById("fine_per_day").style.backgroundColor = "#fff";
	document.getElementById("book_issue_date").style.border = "1px solid #ccc";
	document.getElementById("book_issue_date").style.backgroundColor = "#fff";
	document.getElementById("borrowername").style.border = "1px solid #ccc";
	document.getElementById("borrowername").style.backgroundColor = "#fff";
	document.getElementById("barcode_number").style.border = "1px solid #ccc";
	document.getElementById("barcode_number").style.backgroundColor = "#fff";
	document.getElementById("status").style.border = "1px solid #ccc";
	document.getElementById("status").style.backgroundColor = "#fff";

}
