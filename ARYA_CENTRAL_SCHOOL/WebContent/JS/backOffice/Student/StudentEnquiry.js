$(document)
		.ready(
				function() {

					$('#completiondatelable').hide();
					$('#admissiondatelable').hide();

					$('#interaction')
							.click(
									function() {
										if ($('#interaction').val().trim() == "rejected"
												|| $('#interaction').val()
														.trim() == "completed"
												|| $('#interaction').val()
														.trim() == "visited") {
											$('#completiondatelable').show();
										} else {
											$('#completiondatelable').hide();
										}
									});

					$('#admissionstatus')
							.click(
									function() {
										if ($('#admissionstatus').val().trim() == "rejected"
												|| $('#admissionstatus').val()
														.trim() == "completed"
												|| $('#admissionstatus').val()
														.trim() == "visited") {
											$('#admissiondatelable').show();
										} else {
											$('#admissiondatelable').hide();
										}
									});

					if ($('#hidstatus').val() == "update") {
						$(
								"#studClassId option[value="
										+ $('#hidclassname').val().trim() + "]")
								.attr('selected', 'true');
						$(
								"#academicYear option[value="
										+ $('#hidaccId').val().trim() + "]")
								.attr('selected', 'true');
						$(
								'input[name=enq_gender][value='
										+ $('#hidgender').val().trim() + ']')
								.attr('checked', true);
						$(
								'input[name=enq_physicallyChallenged][value='
										+ $('#hidphyhand').val().trim() + ']')
								.attr('checked', true);
						$(
								"#contacttypeId option[value="
										+ $('#hidcontype').val().trim() + "]")
								.attr('selected', 'true');
						$('#paddrs').val($('#hidaddress').val().trim());
						$(
								"#interaction option[value="
										+ $('#hidint_status').val().trim()
										+ "]").attr('selected', 'true');

						if ($('#hidint_status').val().trim() != "yettovisit") {
							$('#completiondatelable').show();
						}

						$(
								"#admissionstatus option[value="
										+ $('#hidreg_status').val().trim()
										+ "]").attr('selected', 'true');

						if ($('#hidreg_status').val().trim() != "yettovisit") {
							$('#admissiondatelable').show();
						}
					}

					$('#searchid')
							.click(
									function() {
										window.location.href = "adminMenu.html?method=studentEnquiryList&Code="
												+ $('#search').val();
									});

					$('#save')
							.click(
									function() {
										var appno = $('#appnoid').val();
										var firstname = $('#FirstNameId').val();
										var dateofBirth = $('#dateofBirthId')
												.val();
										var LastName = $('#LastNameId').val();
										var gen = $('#genderId').val();
										var phyChallenged = $(
												'#physicallyChallengedId')
												.val();
										var religion = $('#religionId').val();
										var studClassId = $('#studClassId')
												.val();
										var academicYear = $('#academicYear')
												.val();
										var cantacttype = $('#contacttypeId')
												.val();
										var dateofjoining = $('#dateofJoinId')
												.val();
										var cantactname = $('#contactnameid')
												.val();
										var mobileno = $('#MobileNoid').val();
										var contactmailId = $('#contactmailId')
												.val();
										var source = $('#source').val();

										var paddrs = $('#paddrs').val();

										var age = $('#ageId').val();

										var interaction = $('#interaction')
												.val();
										var admissionstatus = $(
												'#admissionstatus').val();
										var joiningId = $('#joiningId').val();
										var completiondate = $(
												'#completiondate').val();
										var admissiondate = $('#admissiondate')
												.val();

										var enquiryid = $('#enquiryid').val();
										var hidsatatus = $('#hidstatus').val();

										if (validatedFields() == true) {

											var enquirylist = {
												"firstname" : firstname,
												"dateofBirth" : dateofBirth,
												"gen" : gen,
												"phyChallenged" : phyChallenged,
												"studClassId" : studClassId,
												"academicYear" : academicYear,
												"cantacttype" : cantacttype,
												"cantactname" : cantactname,
												"mobileno" : mobileno,
											};
											var duplicateResult = checkDuplicate(enquirylist);

											if (!duplicateResult) {
												$('.errormessagediv').show();
												$('.validateTips')
														.text(
																"Student Details already Exists");
												return false;
											} else {
												var Check = {
													"firstname" : firstname,
													"appno" : appno,
													"LastName" : LastName,
													"religion" : religion,
													"dateofjoining" : dateofjoining,
													"contactmailId" : contactmailId,
													"source" : source,
													"paddrs" : paddrs,
													"dateofBirth" : dateofBirth,
													"gen" : gen,
													"phyChallenged" : phyChallenged,
													"studClassId" : studClassId,
													"academicYear" : academicYear,
													"cantacttype" : cantacttype,
													"cantactname" : cantactname,
													"mobileno" : mobileno,
													"age" : age,
													"interaction" : interaction,
													"admissionstatus" : admissionstatus,
													"joiningId" : joiningId,
													"completiondate" : completiondate,
													"admissiondate" : admissiondate,
													"enquiryid" : enquiryid,
													"hidsatatus" : hidsatatus
												};
												$
														.ajax({
															type : "POST",
															url : "enquiryDetails.html?method=saveStudentEnquiry",
															data : Check,
															async : false,
															success : function(
																	data) {
																var result = $
																		.parseJSON(data);
																if (result.status == "success") {
																	$(
																			'.successmessagediv')
																			.show();
																	$(
																			'.successmessage')
																			.text(
																					"Student Enquiry Details Inserted Successfully");
																	setTimeout(
																			function() {

																				window.location.href = "adminMenu.html?method=studentEnquiryList";

																			},
																			3000);
																} else {
																	$(
																			'.errormessagediv')
																			.show();
																	$(
																			'.validateTips')
																			.text(
																					"Student Enquiry Details Insertion failed");

																}

															}
														});
											}

										}

									});

					$("#editDesignationId")
							.click(
									function() {
										var cnt = 0;

										$(
												'input.enquiry_Checkbox_Class:checkbox:checked')
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
											$('.errormessagediv1').show();
											$('.validateTips1')
													.text(
															"Select Any One Enquiry Year");

										} else {

											window.location.href = "enquiryDetails.html?method=getSelectedEnquiryDetails&Code="
													+ getData[0];

										}

									});

					$("#trash")
							.click(
									function() {
										var cnt = 0;

										$(
												'input.enquiry_Checkbox_Class:checkbox:checked')
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
											$('.errormessagediv1').show();
											$('.validateTips1')
													.text(
															"Select Any One Enquiry Year");

										} else {

											var Check = {
												"Code" : getData[0],
											};
											$
													.ajax({
														type : "POST",
														url : "enquiryDetails.html?method=deleteEnquiryDetails",
														data : Check,
														async : false,
														success : function(data) {
															var result = $
																	.parseJSON(data);
															if (result.status = true) {
																$(
																		'.successmessagediv')
																		.show();
																$(
																		'.successmessage')
																		.text(
																				"Student Enquiry Details Deleted Successfully");
																setTimeout(
																		function() {

																			window.location.href = "adminMenu.html?method=studentEnquiryList";

																		}, 3000);
															} else {
																$(
																		'.errormessagediv')
																		.show();
																$(
																		'.validateTips')
																		.text(
																				"Student Enquiry Details Deletion failed");

															}

														}
													});

										}

									});
					
					
					$('#excelDownload')
					.click(
							function() {
								
								window.location.href = 'enquiryDetails.html?method=downloadenquiryDetailsXLS';
								
							});
					$("#pdfDownload").click(function(){
            			
            			window.location.href = "enquiryDetails.html?method=downloadenquiryDetailsPDF";
            				
            		});
						
					
					
					
					

					getClass();
					getAcademicYear();

					$('#studClassId').change(function() {
						var section = "#" + "studSectionId";
						var Classid = "#" + "studClassId";
						getClassSection(section, Classid);
					});

					$("#dateofBirthId").datepicker(
							{
								dateFormat : "dd-mm-yy",
								yearRange : 'c-65:c+65',
								maxDate : -1,
								changeMonth : "true",
								changeYear : "true",
								yearRange : '1997:' + (new Date).getFullYear(),
								onClose : function(selectedDate) {
									$("#dateofJoinId").datepicker("option",
											"minDate", selectedDate);
								}
							});

					$("#dateofJoinId").datepicker({
						dateFormat : "dd-mm-yy",
						minDate : 0,
						changeMonth : "true",
						changeYear : "true",
						buttonImage : "images/calendar.GIF",
						buttonImageOnly : true
					});

					$("#completiondate").datepicker({

						dateFormat : "dd-mm-yy",
						changeMonth : "true",
						changeYear : "true",
						buttonImage : "images/calendar.GIF",
						buttonImageOnly : true

					});
					$("#admissiondate").datepicker({

						dateFormat : "dd-mm-yy",
						changeMonth : "true",
						changeYear : "true",
						buttonImage : "images/calendar.GIF",
						buttonImageOnly : true

					});

				});

function checkDuplicate(enquirylist) {
	var result1 = false;
	$.ajax({
		type : 'POST',
		url : "enquiryDetails.html?method=duplicateStudentChecking",
		data : enquirylist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			if (result.status == true) {
				result1 = false;
			} else {
				result1 = true;
			}
		}

	});

	return result1;
}
function getClass() {
	$
			.ajax({
				url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
				async : false,
				success : function(data) {

					var result = $.parseJSON(data);
					for ( var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

						$('#studClassId')
								.append(
										'<option value="'
												+ result.classDetailsIDandClassDetailsNameList[j].secDetailsId
												+ '">'
												+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName
												+ '</option>');

					}

				}
			});
}
function getAcademicYear() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAcademicYear",
		success : function(response) {
			var result = $.parseJSON(response);
			$(academicYear).html("");
			$(academicYear).append(
					'<option value="' + "" + '">' + "-----Select-----"
							+ '</option>');
			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$(academicYear).append(
						'<option value="'
								+ result.jsonResponse[j].academicYearId + '">'
								+ result.jsonResponse[j].academicYear
								+ '</option>');
			}
		}
	});
}
function getClassSection(sectionid, studClassId) {

	var classidVal = $("#studClassId").val();
	datalist = {
		"classidVal" : classidVal
	}, $.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$(sectionid).html("");
			$(sectionid).append(
					'<option value="' + "" + '">' + "-----select-----"
							+ '</option>');
			for ( var j = 0; j < result.sectionList.length; j++) {
				$(sectionid).append(
						'<option value="' + result.sectionList[j].sectioncode
								+ '">' + result.sectionList[j].sectionnaem
								+ '</option>');
			}

		}
	});

}
function ageCalculate() {
	var doofBirth = $('#dateofBirthId').val();

	var birthYear = doofBirth.split("-")[2];

	var currentYear = new Date().getFullYear();

	var yearDiff = parseInt(currentYear) - parseInt(birthYear);

	$('#ageId').val(yearDiff);

}
function validateMobileno() {
	var mobileno = $('#MobileNoid').val();

	var phoneno = {
		"mobileno" : mobileno
	};

	$.ajax({
		type : 'POST',
		url : "enquiryDetails.html?method=validationMobileno",
		data : phoneno,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			if (result.status == true) {
				$('.errormessagediv').show();
				$('.validateTips').text("Mobile No already Exists");
				return false;
			} else {
				return true;
			}
		}

	});
}

function appnovalidate() {

	var appno = $('#appnoid').val();

	var Appnumber = {
		"appnumber" : appno
	};

	$.ajax({
		type : 'POST',
		url : "enquiryDetails.html?method=applicationNoValidate",
		data : Appnumber,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			if (result.status == true) {
				$('.errormessagediv').show();
				$('.validateTips').text("Application No already Exists");
				return false;
			} else {
				return true;
			}
		}

	});

}

function checkvalidPhoneNumber() {
	if (($("#MobileNoid").val().trim() == "")) {
		$(".validateTips").text("Enter Mobile No");
		$(".errormessagediv").show();
		document.getElementById("MobileNoid").style.border = "1px solid #AF2C2C";
		document.getElementById("MobileNoid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		
		return false;
	}
	if (isNaN($("#MobileNoid").val().trim())) {
		$(".validateTips").text("Enter valid Mobile No");
		$(".errormessagediv").show();
		document.getElementById("MobileNoid").style.border = "1px solid #AF2C2C";
		document.getElementById("MobileNoid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		
		return false;
	}
	if (!$("#MobileNoid").val().trim().match(/^(?!0{3})([0-9])+$/i)) {
		$(".validateTips").text("Enter valid Mobile No");
		$(".errormessagediv").show();
		document.getElementById("MobileNoid").style.border = "1px solid #AF2C2C";
		document.getElementById("MobileNoid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		
		return false;
	} else {
		$(".errormessagediv").hide();
		return true;
	}
}

function validatedFields() {
	var appno = $('#appnoid').val();
	var firstname = $('#FirstNameId').val();
	var dateofBirth = $('#dateofBirthId').val();
	var LastName = $('#LastNameId').val();
	var gender = $("#genderId:checked").length;
	var physicallyChallenged = $("#physicallyChallengedId:checked").length;
	var religion = $('#religionId').val();
	var studClassId = $('#studClassId').val();
	var academicYear = $('#academicYear').val();
	var cantacttype = $('#contacttypeId').val();
	var dateofjoining = $('#dateofJoinId').val();
	var cantactname = $('#contactnameid').val();
	var mobileno = $('#MobileNoid').val();
	var contactmailId = $('#contactmailId').val();
	var source = $('#source').val();
	var paddrs = $('#paddrs').val();

	var phonenoResult = checkvalidPhoneNumber();

	if (firstname == "" || firstname == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Enter First Name");
		document.getElementById("FirstNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("FirstNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	} else if (!firstname.match(/^[a-zA-Z\s]+$/g)) {
		$('.errormessagediv').show();
		$('.validateTips').text("First Name should be Alphabet");
		document.getElementById("FirstNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("FirstNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;

	} else if (firstname.length < 3) {

		$('.errormessagediv').show();
		$('.validateTips').text("First Name should be Mininmum 3 Characters");
		document.getElementById("FirstNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("FirstNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;

	}

	else if ((LastName != "") && (!LastName.match(/^[a-zA-Z\s]+$/g))) {
		$('.errormessagediv').show();
		$('.validateTips').text("Last Name should be Alphabet");
		document.getElementById("LastNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("LastNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	}

	else if (studClassId == "" || studClassId == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Select Class");
		document.getElementById("studClassId").style.border = "1px solid #AF2C2C";
		document.getElementById("studClassId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	}

	else if (appno == "" || appno == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Enter Application No");
		document.getElementById("appnoid").style.border = "1px solid #AF2C2C";
		document.getElementById("appnoid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	}

	else if (source == "" || source == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Enter Source");
		document.getElementById("source").style.border = "1px solid #AF2C2C";
		document.getElementById("source").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	} else if (!source.match(/^[a-zA-Z\s]+$/g)) {
		$('.errormessagediv').show();
		$('.validateTips').text("Source should be Alphabet");
		document.getElementById("source").style.border = "1px solid #AF2C2C";
		document.getElementById("source").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	}

	else if (dateofjoining == dateofBirth) {
		$('.errormessagediv').show();
		$('.validateTips').text(
				"Date of Birth Should be Less Than Date of Joining");
		document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
		document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	}

	else if (academicYear == "" || academicYear == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Select Academic Year");
		document.getElementById("academicYear").style.border = "1px solid #AF2C2C";
		document.getElementById("academicYear").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	} else if (dateofBirth == "" || dateofBirth == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Select Date of Birth");
		document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
		document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		
		return false;
	}

	else if ((religion != "") && (!religion.match(/^[a-zA-Z\s]+$/g))) {
		$('.errormessagediv').show();
		$('.validateTips').text("Religion should be Alphabet");
		document.getElementById("religionId").style.border = "1px solid #AF2C2C";
		document.getElementById("religionId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	}

	else if (gender == 0) {
		$('.errormessagediv').show();
		$('.validateTips').text("Select Gender");
		return false;
	} else if (physicallyChallenged == 0) {
		$('.errormessagediv').show();
		$('.validateTips').text("Select Physically Challenged");
		return false;
	}

	else if (cantacttype == "" || cantacttype == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Select Contact Type");
		
		document.getElementById("contacttypeId").style.border = "1px solid #AF2C2C";
		document.getElementById("contacttypeId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		
		return false;
	} else if (cantactname == "" || cantactname == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Enter Contact Name");
		document.getElementById("contactnameid").style.border = "1px solid #AF2C2C";
		document.getElementById("contactnameid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	} else if (!cantactname.match(/^[a-zA-Z\s]+$/g)) {
		$('.errormessagediv').show();
		$('.validateTips').text("Contact Name should be Alphabet");
		document.getElementById("contactnameid").style.border = "1px solid #AF2C2C";
		document.getElementById("contactnameid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	} else if (mobileno == "" || mobileno == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Enter Mobile No");
		document.getElementById("MobileNoid").style.border = "1px solid #AF2C2C";
		document.getElementById("MobileNoid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	} else if (!mobileno.match(/^[0-9+]/)) {
		$('.errormessagediv').show();
		$('.validateTips').text("Mobile Number should be Numeric");
		document.getElementById("MobileNoid").style.border = "1px solid #AF2C2C";
		document.getElementById("MobileNoid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	} else if (mobileno.length < 10) {
		$('.errormessagediv').show();
		$('.validateTips').text("Mobile No contain minimum 10 digits");
		document.getElementById("MobileNoid").style.border = "1px solid #AF2C2C";
		document.getElementById("MobileNoid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	} else if (!phonenoResult) {
		$('.errormessagediv').show();
		$('.validateTips').text("Enter Valid Mobile No");
		document.getElementById("MobileNoid").style.border = "1px solid #AF2C2C";
		document.getElementById("MobileNoid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		
		return false;
	} else if (!mobileno.match(/^[0-9+]/)) {
		$('.errormessagediv').show();
		$('.validateTips').text("Mobile No should be Number(0-9)");
		document.getElementById("MobileNoid").style.border = "1px solid #AF2C2C";
		document.getElementById("MobileNoid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
	} else if ((contactmailId != "")
			&& (!contactmailId
					.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/))) {
		$('.errormessagediv').show();
		$('.validateTips').text("Enter valid Email ID");
		
		document.getElementById("contactmailId").style.border = "1px solid #AF2C2C";
		document.getElementById("contactmailId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		
		return false;
	} else if (paddrs == "" || paddrs == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Enter Address");
		document.getElementById("paddrs").style.border = "1px solid #AF2C2C";
		document.getElementById("paddrs").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
		
	} 
	else
	{
		return true;
	}

}



function HideError() 
{
	
document.getElementById("appnoid").style.border = "1px solid #ccc";
document.getElementById("appnoid").style.backgroundColor = "#fff";
document.getElementById("paddrs").style.border = "1px solid #ccc";
document.getElementById("paddrs").style.backgroundColor = "#fff";
document.getElementById("source").style.border = "1px solid #ccc";
document.getElementById("source").style.backgroundColor = "#fff";
document.getElementById("contactmailId").style.border = "1px solid #ccc";
document.getElementById("contactmailId").style.backgroundColor = "#fff";
document.getElementById("MobileNoid").style.border = "1px solid #ccc";
document.getElementById("MobileNoid").style.backgroundColor = "#fff";
document.getElementById("contactnameid").style.border = "1px solid #ccc";
document.getElementById("contactnameid").style.backgroundColor = "#fff";
document.getElementById("dateofJoinId").style.border = "1px solid #ccc";
document.getElementById("dateofJoinId").style.backgroundColor = "#fff";
document.getElementById("contacttypeId").style.border = "1px solid #ccc";
document.getElementById("contacttypeId").style.backgroundColor = "#fff";
document.getElementById("academicYear").style.border = "1px solid #ccc";
document.getElementById("academicYear").style.backgroundColor = "#fff";
document.getElementById("studClassId").style.border = "1px solid #ccc";
document.getElementById("studClassId").style.backgroundColor = "#fff";
document.getElementById("religionId").style.border = "1px solid #ccc";
document.getElementById("religionId").style.backgroundColor = "#fff";
document.getElementById("LastNameId").style.border = "1px solid #ccc";
document.getElementById("LastNameId").style.backgroundColor = "#fff";
document.getElementById("dateofBirthId").style.border = "1px solid #ccc";
document.getElementById("dateofBirthId").style.backgroundColor = "#fff";
document.getElementById("FirstNameId").style.border = "1px solid #ccc";
document.getElementById("FirstNameId").style.backgroundColor = "#fff";
}
