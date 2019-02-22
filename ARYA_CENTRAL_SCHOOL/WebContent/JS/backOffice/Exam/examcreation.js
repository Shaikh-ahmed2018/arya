function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document)
		.ready(
				function() {
					
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv1").hide();
					}, 3000);

					$("#allstudent .subjectid").hide();
					$("#allstudent .classId").hide();
					$("#save")
							.click(
									function() {

										var stDate = $('#startDate').val();
										var endDate = $('#endDate').val();
										var status = null;
										var rc = 1;

										$('table#allstudent tr:gt(0)')
												.each(
														function() {

															var examName = $(
																	this)
																	.find(
																			'[id=subName]')
																	.val();
															var examDate = $(
																	this)
																	.find(
																			'[name=startDate]')
																	.val();
															var startTime = $(
																	this)
																	.find(
																			'[id=startTime]')
																	.val();
															var endTime = $(
																	this)
																	.find(
																			'[id=endTime]')
																	.val();

															var examdate = Date
																	.parse(dateConverter(examDate));
															var startdate = Date
																	.parse(dateConverter(stDate));
															var enddate = Date
																	.parse(dateConverter(endDate));

															if (examDate.trim() == "") {
																$(
																		".errormessagediv1")
																		.show();
																$(
																		".validateTips")
																		.text(
																				" Exam Date should not be empty for "
																						+ examName
																						+ " subject");

																status = false;
																return false;

															} else if (startdate > examdate
																	|| examdate > enddate) {
																$(
																		".errormessagediv1")
																		.show();
																$(
																		".validateTips")
																		.text(
																				"Exam Date is in between startdate and enddate for "
																						+ examName
																						+ " subject");

																status = false;
																return false;

															} else if (examDate
																	.trim() != '') {

																status = true;
																$(
																		".errormessagediv1")
																		.hide();
															}
															if (startTime
																	.trim() == "") {
																$(
																		".errormessagediv1")
																		.show();
																$(
																		".validateTips")
																		.text(
																				"Select Start time for "
																						+ examName
																						+ " subject");
																status = false;
																return false;
															} else if (startTime
																	.trim() != '') {
																status = true;
																$(
																		".errormessagediv1")
																		.hide();

															}
															if (endTime.trim() == "") {
																$(
																		".errormessagediv1")
																		.show();
																$(
																		".validateTips")
																		.text(
																				"Select End time for "
																						+ examName
																						+ " subject");
																status = false;
																return false;
															} else if (checkTime(
																	startTime
																			.trim(),
																	endTime
																			.trim())) {

																$(
																		".errormessagediv1")
																		.show();
																$(
																		".validateTips")
																		.text(
																				"The End Time Should Not less than Start Time for "
																						+ examName
																						+ " subject");
																status = false;
																return false;

															} else if (endTime
																	.trim() != undefined
																	&& endTime
																			.trim() != '') {
																status = true;
																$(
																		".errormessagediv1")
																		.hide();
															}

															rc++;

														});
										if (status == true) {
											var answer = confirm("Are you sure to Enter Exam Details");
											if (answer) {

												var subjectId = [];
												$(
														'table#allstudent  td.subjectid')
														.map(
																function() {

																	subjectId
																			.push($(
																					this)
																					.text()
																					.trim());

																}).get();

												var date = [];
												$(
														'table#allstudent  input.datepicker')
														.map(
																function() {

																	date
																			.push($(
																					this)
																					.val()
																					.trim());

																}).get();

												var time = [];
												$(
														'table#allstudent  input.timepicker')
														.map(
																function() {

																	time
																			.push($(
																					this)
																					.val()
																					.trim());

																}).get();

												var endtime = [];
												$(
														'table#allstudent  input.endtimepicker')
														.map(
																function() {

																	endtime
																			.push($(
																					this)
																					.val()
																					.trim());

																}).get();

												var examId = $('#examId').val();
												var classId = $('#classid')
														.val();

												$
														.ajax({
															type : "GET",
															url : "examTimetablePath.html?method=saveExaminationClassMapping",
															data : {
																"classId" : classId,
																"examId" : examId,
																"subjectid_str" : subjectId
																		.join(","),
																"date_str" : date
																		.join(","),
																"time_str" : time
																		.join(","),
																"end_time_str" : endtime
																		.join(",")
															},
															async : false,
															success : function(
																	data) {
																var result = $
																		.parseJSON(data);

																window.location = "examTimetablePath.html?method=getExamDetails&Status="
																		+ result.status;
															}
															
														});
											}
										}
									});
					$('#date1').datepicker({
						minDate : 0,
						dateFormat : "dd-mm-yy",
						changeMonth : "true",
						changeYear : "true",
						altFormat : "DD",

					});

					$('#endDate').datepicker({
						minDate : 0,
						dateFormat : "dd-mm-yy",
						changeMonth : "true",
						changeYear : "true",
						altFormat : "DD",

					});

					$('#allstudent').on('focus', '.datepicker', function() {
						$(this).datepicker({
							dateFormat : "dd-mm-yy",
						});
						$('div#ui-datepicker-div').css({
							'font-size' : '15px'
						});
					});

					$('#allstudent').delegate(
							'div.input-append img',
							'click',
							function() {

								$('#allstudent div.input-append')
										.datetimepicker({
											pickDate : false
										});
								$('div.timepicker').css({
									'font-size' : '13px'
								});
								$('div.timepicker table.table-condensed').find(
										'td.separator')
										.attr('valign', 'middle');
							});

					$('#datetimepicker3').datetimepicker({
						pickDate : false
					});
					$('#datetimepicker4').datetimepicker({
						pickDate : false
					});
				});

function dateConverter(dateString) {
	var dateArray = [];

	var dateStringArray = dateString.split("-");
	dateArray.push(dateStringArray[2]);
	dateArray.push(dateStringArray[1]);
	dateArray.push(dateStringArray[0]);
	var dateString1 = dateArray.join("-");
	return dateString1;
}

function checkTime(ftime, ttime) {

	var bflag = false;

	var ftimeSplitHour = ftime.split(':')[0];
	var ftimeSplitMin = ftime.split(':')[1];
	var ftimeSplitSec = ftime.split(':')[2];

	var ttimeSplitHour = ttime.split(':')[0];
	var ttimeSplitMin = ttime.split(':')[1];
	var ttimeSplitSec = ttime.split(':')[2];

	if (ftimeSplitHour.charAt(0) == 0) {
		ftimeSplitHour = ftimeSplitHour.charAt(1);
	}
	if (ttimeSplitHour.charAt(0) == 0) {
		ttimeSplitHour = ttimeSplitHour.charAt(1);
	}

	if (ftimeSplitMin.charAt(0) == 0) {
		ftimeSplitMin = ftimeSplitMin.charAt(1);
	}
	if (ttimeSplitMin.charAt(0) == 0) {
		ttimeSplitMin = ttimeSplitMin.charAt(1);
	}

	if (ftimeSplitSec.charAt(0) == 0) {
		ftimeSplitSec = ftimeSplitSec.charAt(1);
	}
	if (ttimeSplitSec.charAt(0) == 0) {
		ttimeSplitSec = ttimeSplitSec.charAt(1);
	}

	ftimeSplitHour = parseInt(ftimeSplitHour);
	ttimeSplitHour = parseInt(ttimeSplitHour);
	ftimeSplitMin = parseInt(ftimeSplitMin);
	ttimeSplitMin = parseInt(ttimeSplitMin);

	ftimeSplitSec = parseInt(ftimeSplitSec);
	ttimeSplitSec = parseInt(ttimeSplitSec);

	if (ftimeSplitHour > ttimeSplitHour) {

		bflag = true;

	}
	if (ttimeSplitHour == ftimeSplitHour) {
		if (ftimeSplitMin > ttimeSplitMin) {

			bflag = true;

		}
		if (ftimeSplitMin == ttimeSplitMin) {
			if (ftimeSplitSec >= ttimeSplitSec) {

				bflag = true;

			}
		}
	}
	return bflag;
}
