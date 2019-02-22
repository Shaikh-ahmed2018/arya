$(document)
		.ready(
				function() {
					$(".LstmsSuccessMessage").show();
					setTimeout(function() {
						$(".LstmsSuccessMessage").hide();
					}, 7000);

					$('.errormessagediv').hide();

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

					getAcademicYear();
					var categoryId = "category";
					getCategory(categoryId);

					$('#category').change(function() {
						var classId = "studClassId";
						getClassName(classId, category);
					});

					$('#studClassId').change(function() {

						var section = "#" + "studSectionId";
						var subjectId = "#" + "studSubId";
						getClassSection(section, studClassId);

					});

					$('#submitId')
							.click(
									function() {
										var appno = $('#appnoid').val();
										var firstname = $('#FirstNameId').val();
										var dateofBirth = $('#dateofBirthId')
												.val();
										var age = $('#ageId').val();
										var LastName = $('#LastNameId').val();
										var gen = $('#genderId').val();
										var phyChallenged = $(
												'#physicallyChallengedId')
												.val();
										var gender = $("#genderId:checked").length;
										var physicallyChallenged = $("#physicallyChallengedId:checked").length;
										var religion = $('#religionId').val();
										var category = $('#category').val();
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
										var location = $('#location').val();
										var townandcity = $('#townandcity')
												.val();
										var zippostcode = $('#zippostcode')
												.val();
										var state = $('#state').val();
										var country = $('#country').val();
										var source = $('#source').val();

										var phonenoResult = checkvalidPhoneNumber();
										if (appno == "" || appno == null) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Enter Application No");
											return false;
										}
										if (firstname == ""
												|| firstname == null) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Enter First Name");
											return false;
										} else if (!firstname
												.match(/^[a-zA-Z\s]+$/g)) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"First Name should be Alphabet");
											return false;

										}

										else if (firstname.length < 3) {

											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"First Name should be Mininmum 3 characters");
											return false;

										} else if ((LastName != "")
												&& (!LastName
														.match(/^[a-zA-Z\s]+$/g))) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Last Name should be Alphabet");
											return false;
										} else if (dateofBirth == ""
												|| dateofBirth == null) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Select Date of Birth");
											return false;
										} else if (!age.match(/^[0-9+]/)) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Age should be Number(0-9)");
											return false;
										} else if (gender == 0) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Select Gender");
											return false;
										} else if (physicallyChallenged == 0) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Select Physically Challenged");
											return false;
										} else if ((religion != "")
												&& (!religion
														.match(/^[a-zA-Z\s]+$/g))) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Religion should be Alphabet");
											return false;
										} else if (dateofjoining == dateofBirth) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Date of Birth Should be Less Than Date of Joining");
											return false;
										}

										else if (category == ""
												|| category == null) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Select Stream");
											return false;
										} else if (studClassId == ""
												|| studClassId == null) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Select Class");
											return false;
										} else if (academicYear == ""
												|| academicYear == null) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Select Academic Year");
											return false;
										} else if (source == ""
												|| source == null) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Enter Source");
											return false;
										} else if (!source
												.match(/^[a-zA-Z\s]+$/g)) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Source should be Alphabet");
											return false;
										} else if (cantacttype == ""
												|| cantacttype == null) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Select Contact Type");
											return false;
										} else if (cantactname == ""
												|| cantactname == null) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Enter Contact Name");
											return false;
										} else if (!cantactname
												.match(/^[a-zA-Z\s]+$/g)) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Contact Name should be Alphabet");
											return false;
										} else if (mobileno == ""
												|| mobileno == null) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Enter Mobile No");
											return false;
										} else if (!mobileno.match(/^[0-9+]/)) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Mobile Number should be Numeric");
											return false;
										} else if (mobileno.length < 10) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Mobile No contain minimum 10 digits");
											return false;
										} else if (!phonenoResult) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Enter Valid Mobile No");
											return false;
										} else if (!mobileno.match(/^[0-9+]/)) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Mobile No should be Number(0-9)");
											return false;
										} else if ((contactmailId != "")
												&& (!contactmailId
														.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/))) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Enter valid Email ID");
											return false;
										} else if ((location != "")
												&& (!location
														.match(/^[a-zA-Z\s]+$/g))) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Location should be Alphabet");
											return false;
										} else if ((townandcity != "")
												&& (!townandcity
														.match(/^[a-zA-Z\s]+$/g))) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"City should be Alphabet");
											return false;
										} else if ((zippostcode != "")
												&& (!zippostcode
														.match(/^[0-9+]/))) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Post Code should be Number(0-9)");
											return false;
										} else if ((state != "")
												&& (!state
														.match(/^[a-zA-Z\s]+$/g))) {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"State should be Alphabet");
											return false;
										} else if ((country != "")
												&& (!country
														.match(/^[a-zA-Z\s]+$/g))) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Country should be Alphabet");
											return false;
										} else {

											var enquirylist = {

												"firstname" : firstname,
												"dateofBirth" : dateofBirth,
												"gen" : gen,
												"phyChallenged" : phyChallenged,
												"category" : category,
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
											}
											return true;
										}

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

function getCategory(categoryval) {
	var category = "#" + categoryval;
	$.ajax({
		type : 'GET',
		url : "childinfo.html?method=getCategory",
		success : function(response) {
			$(category).html("");
			$(category).append(
					'<option value="' + "" + '">' + "-----Select-----"
							+ '</option>');
			var result = $.parseJSON(response);

			for ( var j = 0; j < result.parentVOList.length; j++) {

				$(category)
						.append(
								'<option value="'
										+ result.parentVOList[j].classStreamID
										+ '">'
										+ result.parentVOList[j].category
										+ '</option>');
			}
		}
	});
}
function getClassName(classidval, category) {
	var classid = "#" + classidval;
	var categoryVal = $("#category").val();

	datalist = {
		"categoryVal" : categoryVal
	}, $.ajax({
		type : 'POST',
		url : "childinfo.html?method=getClassDetail",
		data : datalist,
		success : function(response) {
			var result = $.parseJSON(response);
			$(classid).html("");
			$(classid).append(
					'<option value="' + "" + '">' + "-----Select-----"
							+ '</option>');
			for ( var j = 0; j < result.parentVOList.length; j++) {
				$(classid).append(
						'<option value="'
								+ result.parentVOList[j].classDetailId + '">'
								+ result.parentVOList[j].classDetailsName
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

function cancel() {

	window.location.href = "login.html?method=adminLogin";
}

function checkvalidPhoneNumber() {
	if (($("#MobileNoid").val().trim() == "")) {
		$(".validateTips").text("Enter Mobile No");
		$(".errormessagediv").show();
		return false;
	}
	if (isNaN($("#MobileNoid").val().trim())) {
		$(".validateTips").text("Enter valid Mobile No");
		$(".errormessagediv").show();
		return false;
	}
	if (!$("#MobileNoid").val().trim().match(/^(?!0{3})([0-9])+$/i)) {
		$(".validateTips").text("Enter valid Mobile No");
		$(".errormessagediv").show();
		return false;
	} else {
		$(".errormessagediv").hide();
		return true;
	}
}