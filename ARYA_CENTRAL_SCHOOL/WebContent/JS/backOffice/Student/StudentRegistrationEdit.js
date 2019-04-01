function viewStudentDetails(studentList) {
	

	$("#fatherNameId").val(studentList[0].fatherName);
	$("#fatherMobileNoId").val(studentList[0].fatherMobileNo);
	$("#fatherQualificationId").val(studentList[0].fatherQualification);
	$("#fatheremailId").val(studentList[0].fatheremailId);

	$("#motherNameId").val(studentList[0].motherName);
	$("#motherMobileNoId").val(studentList[0].motherMobileNo);
	$("#motherQualificationId").val(studentList[0].motherQualification);
	$("#motheremailId").val(studentList[0].motheremailId);

	$("#gaurdianNameId").val(studentList[0].gaurdianName);
	$("#guardianMobileNoId").val(studentList[0].guardianMobileNo);
	$("#guardianemailId").val(studentList[0].guardianemailId);

	$("#sibilingadminnoId").val(studentList[0].sibilingadminno);
	$("#parentId").val(studentList[0].parentId);
	$("#sibilingClassId").val(studentList[0].sibilingClass);
	$("#primarypersonId option[value='"+ studentList[0].primaryPerson+"']").attr('selected', 'true');
	$("#hprymarycntperson").val(studentList[0].primaryPerson);
	$("#studentSibilingIdIntId").val(studentList[0].studentSibilingIdInt);
	$("#sibilingClassID").val(studentList[0].sibilingClassId);
	
	$("#motheroccupationId").val(studentList[0].motheroccupation);
	$("#fatheroccupationId").val(studentList[0].fatheroccupation);
	$("#paddrs").val(studentList[0].address);
	
	
	
	$("#fatherNameId").attr('readonly', true);
	$("#fatherMobileNoId").attr('readonly', true);

	$("#fatherQualificationId").attr('readonly', true);
	$("#fatheremailId").attr('readonly', true);

	$("#motherNameId").attr('readonly', true);
	$("#motherMobileNoId").attr('readonly', true);
	$("#motherQualificationId").attr('readonly', true);
	$("#motheremailId").attr('readonly', true);

	$("#gaurdianNameId").attr('readonly', true);
	$("#guardianMobileNoId").attr('readonly', true);
	$("#guardianemailId").attr('readonly', true);

	$("#motheroccupationId").attr('readonly', true);
	$("#fatheroccupationId").attr('readonly', true);
	$("#paddrs").attr('readonly', true);
	




}
function callAjax(urlWithMethod, dataToBeSend) {
	var jsonResult = "";
	try {
		$.ajax({
			type : "GET",
			url : urlWithMethod,
			data : dataToBeSend,
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);

				jsonResult = result.jsonResponse;
			}
		});
	} catch (e) {
		jsonResult = "";
	}
	return jsonResult;
}
function readURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imagePreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}

}
$(document)
		.ready(
				function() {
				
					
					
					$("#search")
					.click(
							function()

							{
								var searchname = $('#searchvalue').val();
						
												
								
								window.location.href = "adminMenu.html?method=studentList&searchname="
									+ searchname;
								

							});
					
					
					if($("#sibilingadminnoId").val()!="" && $("#sibilingadminnoId").val()!=undefined){
						
						$("#fatherNameId").attr('readonly', true);
						$("#fatherMobileNoId").attr('readonly', true);

						$("#fatherQualificationId").attr('readonly', true);
						$("#fatheremailId").attr('readonly', true);

						$("#motherNameId").attr('readonly', true);
						$("#motherMobileNoId").attr('readonly', true);
						$("#motherQualificationId").attr('readonly', true);
						$("#motheremailId").attr('readonly', true);

						$("#gaurdianNameId").attr('readonly', true);
						$("#guardianMobileNoId").attr('readonly', true);
						$("#guardianemailId").attr('readonly', true);
						$("#motheroccupationId").attr('readonly', true);
						$("#fatheroccupationId").attr('readonly', true);
						$("#paddrs").attr('readonly', true);

						
					}
					
					
					$("#primarypersonId").change(function(){
						
						$("#hprymarycntperson").val($("#primarypersonId").val());
					});
					
					// saving detils with validations
					
					$("#document1btn").hide();
					$("#document2btn").hide();
					$("#deleteProfile").hide();
					$("#deleteIDProof").hide();
					
					$("#save").click(
									function() {
										
										
										if (Validate())
										{
											
											if ($('#studentid').val().trim() == ""|| $('#studentid').val().trim() == null) {
												
												
												$("#formstudent")
														.attr('action',
																'studentRegistration.html?method=saveStudentRegistration');
												
												$("#formstudent").submit();
											} 
											else
											{
												
												$("#formstudent").attr('action','studentRegistration.html?method=modifyStudentDetails');
												$("#formstudent").submit();
											}
										}

									});


				
					$("#imagePreview").hide();

					$("#studentstatuslable").hide();


					$("#studentImageId1").change(function() {
						$("#imagePreview").show();
						readURL(this);
					});
					$("#cencessionY").click(function() {
						$("#scholarShipId").show();
						$("#cencessionlable").show();

					});

					$("#cencessionN").click(function() {

						$("#scholarShipId").hide();
						$("#cencessionlable").hide();

					});

					$("#transportIdY").click(function() {

						$("#transcategory").show();
						$("#transportcategorylabel").show();
						


						getTransportCategory();

					});
					
					
					$("#translocation").click(function() {

						getRouteDetails();

					});
					

					$("#physicalchalreason").hide();
					$("#physicalchlngres").hide();
					
					$("#physicallyChallengedIdY").click(function(){
						
						$("#physicalchalreason").show();
						$("#physicalchlngres").show();
						
					});
					
					$("#physicallyChallengedIdN").click(function(){
						
						$("#physicalchalreason").val("");
						$("#physicalchalreason").hide();
						$("#physicalchlngres").hide();
						
					});
					
					$("#transportIdN").click(function() {

						$("#transportlocationlabel").hide();
						$("#transcategory").hide();
						$("#transportcategorylabel").hide();
						$("#translocation").hide();
						
						$("#routelabel").hide();
						$("#route").hide();

					});
					$("#transcategory")
							.change(
									function() {
										if ($("#transcategory").val().trim() != '') {

											if (getTransportCategoryType($("#transcategory").val().trim()) == 'Y') {

												$("#trnporttypestatus").val('Y');

												$("#transportlocationlabel").show();
												$("#translocation").show();
												getTransportLocations();

											} else {

												$("#trnporttypestatus")
														.val('N');

												$("#transportlocationlabel")
														.hide();
												$("#translocation").hide();
												
												$("#routelabel").hide();
												$("#route").hide();

											}

										} else {

											$("#trnporttypestatus").val('N');

											$("#transportlocationlabel").hide();
											$("#translocation").hide();
											$("#routelabel").hide();
											$("#route").hide();

										}

									});
					
					
					
					$("#translocation")
					.change(
							function() {
										
										$("#routelabel").show();
										$("#route").show();

							});
					
					
					// hiding ids
					$('.successmessagediv').hide();

					$('#cencessionlable').hide();
					$('#transportcategorylabel').hide();
					$('#transportlocationlabel').hide();

					$("#transcategory").hide();
					$("#translocation").hide();
					
					$("#routelabel").hide();
					$("#route").hide();

					// Editing Fuction

					$("#editStudent")
							.click(
									function() {
										var stdId=$('input[name="selectBox"]:checked').val();
										
										if (stdId==undefined) {
											$('.errormessagediv').show();
											$('.validateTips').text("Select Any One Student");
										} else {

											$('#studentid').stdId;
											window.location.href = "studentRegistration.html?method=editStudent&searchTerm="
													+ stdId;

										}

									});

					// Delete Function

					$("#trash")
							.click(
									function() {
											var stdId=$('input[name="selectBox"]:checked').val();
										
										if (stdId==undefined)  {
											$('.errormessagediv').show();
											$('.validateTips').text(
													"Select Any One Student");
										} else {

											$('#studentid').stdId;

											var Check = {
												"studentid" : stdId
											};
											$
													.ajax({
														type : "POST",
														url : "studentRegistration.html?method=deactivateStudent",
														data : Check,
														async : false,
														success : function(data) {
															var result = $
																	.parseJSON(data);

															if (result.status == true) {
																$(
																		'.successmessagediv')
																		.show();
																$(
																		'.successmessage')
																		.text(
																				"Student Deleted Successfully");
																setTimeout(
																		function() {

																			location
																					.reload();

																		}, 3000);
															} else {
																$(
																		'.errormessagediv')
																		.show();
																$(
																		'.validateTips')
																		.text(
																				"Student already mapped can't Delete");

																setTimeout(
																		function() {

																			location
																					.reload();

																		}, 3000);
															}

														}
													});

										}

									});
					
					$('#excelDownload')
					.click(
							function() {
								
								window.location.href = 'studentRegistration.html?method=downloadStudentDetailsXLS';
								
							});
					$("#pdfDownload").click(function(){
            			
            			window.location.href = "studentRegistration.html?method=downloadStudentDetailsPDF"
            				
            		});
					
					
		
					// calling methods

					getAcademicYear();
					getQuota();
					getConssition();

					var categoryId = "category";
					getCategory(categoryId);

			/*	$('#category').change(function() {
						
						var classId = "studClassId";
						getClassName(classId, category);
					});*/
					
					
				
					$('#category').change(function(){
						
						var categoryVal = $('#category').val();
						//var steamId=$('#streamhiddenId').val();
						
					/*	if ($('#streamhiddenId').val() == null || $('#streamhiddenId').val() == "") {
							
							categoryVal = $("#category").val();
						} else {
							categoryVal = $('#streamhiddenId').val();
						}
					*/
						datalist = {
							"categoryVal" : categoryVal
						}, $
								.ajax({
									type : 'POST',
									url : "studentRegistration.html?method=getClassDetail",
									data : datalist,
									async : false,
									success : function(response) {
										var result = $.parseJSON(response);
										$(studClassId).html("");
										$(studClassId).append(
												'<option value="' + steamId + '">' + "" + '</option>');

										for ( var j = 0; j < result.ClassList.length; j++) {
											$(studClassId).append(
													'<option value="'
															+ result.ClassList[j].classcode + '">'
															+ result.ClassList[j].classname
															+ '</option>');
										}
										

									}
								});


						
						
					});
					
					
					
					
					

					$('#studClassId').change(function() {

						var section = "#" + "studSectionId";
						var subjectId = "#" + "studSubId";
						getClassSection(section, studClassId);

					});

					if ($('#streamhiddenId').val() != null|| $('#streamhiddenId').val() != "") {
						
						getAcademicYear();
						getQuota();
						getConssition();
						getTransportLocations();
						getTransportCategory();

						var categoryId = "category";
						
						getCategory(categoryId);

						var classId1 = "studClassId";
						
						getClassName(classId1, $('#streamhiddenId').val());

						var subjectId1 = "#" + "studSectionId";
						
						getClassSection(subjectId1, $('#classhiddenid').val());

						$('#studentIdhidden').val($('#studentid').val().trim());
						
						var StudentImage=$("#photohiddenid").val().trim();
						
						if(StudentImage!=""){
							
							$("#imagePreview").show();
							$('#imagePreview').attr('src', StudentImage);
						}
						
						var birthcertificate=$("#birthcertificatehiddenid").val();
						var transfercertificate=$("#transfercertificatehiddenid").val();
						
						
						if(birthcertificate!="" && birthcertificate!=undefined){
							
							$("#document1btn").attr('name',birthcertificate);
							
							$("#uploadBirth").hide();
							$("#document1btn").show();
							$("#deleteProfile").show();
							
							}
						
						if(transfercertificate!="" && transfercertificate!=undefined){
							
							$("#document2btn").attr('name',transfercertificate);
							
							$("#uploadTransfer").hide();
							$("#document2btn").show();
							$("#deleteIDProof").show();
							
						}
							
							
							$('.downloadDoc').click(
									
									function() {
										
										var path = $(this).attr('name');
										window.location.href = "studentRegistration.html?method=downloadDocument&Path="
												+ path.trim();
									});
							
							$("#deleteIDProof").click(function(){
								
								$("#uploadTransfer").show();
								
								$("#document2btn").hide();
								$("#deleteIDProof").hide();
								
							});
							
							$("#deleteProfile").click(function(){
								
								$("#uploadBirth").show();
								
								$("#document1btn").hide();
								$("#deleteProfile").hide();
								
							});
						
						
						
						$("#primarypersonId option[value='"+ $('#selected_Primary_hiddenId').val().trim()+"']").attr('selected', 'true');
						
						$('#parentId').val($('#parenthiddenId').val());

						$("#academicYear option[value="+ $('#acchiddenId').val() + "]").attr('selected', 'true');

						$("#category option[value="+ $('#streamhiddenId').val() + "]").attr('selected', 'true');

						$("#studClassId option[value="+ $('#classhiddenid').val() + "]").attr('selected', 'true');
						
						$("#studSectionId option[value="+ $('#sectionhiddenid').val() + "]").attr('selected', 'true');

						$("#studentquotaname option[value="+ $('#quotahiddenid').val() + "]").attr('selected', 'true');

						$('input[name=rte][value='+ $('#rtehiddenid').val() + ']').attr('checked', true);

						$('input[name=hostel][value='+ $('#hostelhiddenid').val() + ']').attr('checked', true);

						$('input[name=cencession][value='+ $('#concessionaplicablehidden').val()+ ']').attr('checked', true);

						if ($("#concessionaplicablehidden").val().trim() == "Y") {

							$('#cencessionlable').show();
							
							$("#scholarShipId option[value="+ $('#concessionhiddenid').val()+ "]").attr('selected', 'true');
						}

						$('input[name=transport][value='+ $('#transporthiddenid').val() + ']').attr('checked', true);

						if ($('#transporthiddenid').val().trim() == "Y") {
							
							$("#transcategory").show();
							
							$("#transportcategorylabel").show();

							$("#transcategory option[value="+ $('#transportcategoryhiddenid').val() + "]").attr('selected', 'true');

							if ($('#typecollectfeehidden').val().trim() == "Y") {

								$("#trnporttypestatus").val($('#typecollectfeehidden').val().trim());

								$("#transportlocationlabel").show();
								
								$("#translocation").show();
								
								
								getTransportLocations();

								$("#translocation option[value="+ $('#transportlocationhiddenid').val() + "]").attr('selected', 'true');
								$("#route").show();
								
								$("#routelabel").show();
								getRouteDetails();
								
								$("#route option[value="+ $('#transportroutehiddenid').val() + "]").attr('selected', 'true');
								
								

							}
						}

						$('input[name=gender][value='+ $('#genderhiddenid').val().trim()+ ']').attr('checked', true);
						

						$("#bloodGroupId  option[value='"+$('#bloodhiddenid').val().trim()+"' ]").attr('selected', 'true');

						$('input[name=physicallyChallenged][value='+ $('#physicallychallengedhiddenid').val().trim() + ']').attr('checked', true);

						$("#physicalchalreason").val($('#physicallychallengeddescriptionhiddenid').val());

						$("#studentstatuslable").show();

						$("#studentStatusId option[value="+ $('#studentStatushiddentid').val()+ "]").attr('selected', 'true');

						$('input[name=primaryPerson][value='+ $('#selected_Primary_hiddenId').val()+ ']').attr('checked', true);

						$("#paddrs").val($('#addresshiddenid').val());
						
						

					}

					// date pickers
					$("#dateofBirthId").datepicker(
							{
								dateFormat : "dd-mm-yy",
								yearRange : 'c-65:c+65',
								maxDate : -1,
								changeMonth : "true",
								changeYear : "true",
								yearRange : '1997:' + (new Date).getFullYear(),
								onClose : function(selectedDate) {
									if ((selectedDate != "")
											&& (selectedDate != undefined)) {
										var date2 = $('#dateofBirthId')
												.datepicker('getDate');
										date2.setDate(date2.getDate() + 1);
										$("#dateofJoinId").datepicker("option",
												"minDate", date2);
									}
								}
							});
					$("#dateofJoinId").datepicker(
							{

								dateFormat : "dd-mm-yy",
								yearRange : 'c-65:c+65',
								maxDate : 0,
								changeMonth : "true",
								changeYear : "true",
								onClose : function(selectedDate) {
									if ((selectedDate != "")
											&& (selectedDate != undefined)) {
										var date2 = $('#dateofJoinId')
												.datepicker('getDate');
										date2.setDate(date2.getDate() - 1);
										$("#dateofBirthId").datepicker(
												"option", "maxDate", date2);
									}
								}
							});

					// sibling search
					
					$("#SearchStudent").autocomplete(
									
							{
								source : function(request, response) {
									
									$("#studentSibilingIdIntId").val("");
									
									$("#fatherNameId").attr('readonly', false);
									$("#fatherMobileNoId").attr('readonly', false);

									$("#fatherQualificationId").attr('readonly', false);
									$("#fatheremailId").attr('readonly', false);

									$("#motherNameId").attr('readonly', false);
									$("#motherMobileNoId").attr('readonly', false);
									$("#motherQualificationId").attr('readonly', false);
									$("#motheremailId").attr('readonly', false);

									$("#gaurdianNameId").attr('readonly', false);
									$("#guardianMobileNoId").attr('readonly', false);
									$("#guardianemailId").attr('readonly', false);
									
									$("#motheroccupationId").attr('readonly', false);
									$("#fatheroccupationId").attr('readonly', false);
									$("#paddrs").attr('readonly', false);


									
									

										$("#motherNameId").val('');
										$("#motherMobileNoId").val('');
										$("#motherQualificationId").val('');
										$("#fatherNameId").val('');
										$("#fatherMobileNoId").val('');
										$("#fatherQualificationId").val('');
										$("#fatheremailId").val('');
										$("#motheremailId").val('');
										$("#gaurdianNameId").val('');
										$("#guardianMobileNoId").val('');
										$("#guardianemailId").val('');


										$("#parentId").val('');
										$("#sibilingClassId").val('');
										$("#studentSibilingIdIntId").val('');
										$("#sibilingadminnoId").val('');
										$("#paddrs").val('');
										$("#motheroccupationId").val('');
										$("#fatheroccupationId").val('');
										
										
										$("#primarypersonId option[value='']").attr('selected', 'true');
										$("#hprymarycntperson").val("");
								/*	}*/
									

											$.ajax({

														url : "studentRegistration.html?method=studentSearchbySibling",
														data : {
															searchTerm : request.term
														},
														async : false,
														success : function(data) {
															var result = $
																	.parseJSON(data);

															response($
																	.map(
																			result.jsonResponse,
																			function(
																					item) {
																				return {
																					label : item.studentnamelabel,
																					value : item.studentidlabel,
																				}
																			}));
														}
													});
										},
										select : function(event, ui) {

											var searchTerm = ui.item.value;

											studentDetails = {
												'searchTerm' : searchTerm
											};
											var studentList = callAjax(
													"studentRegistration.html?method=studentSearchByParent",studentDetails);

											viewStudentDetails(studentList);
											$("#SearchStudent").val(ui.item.label);
											$("#studentSibilingIdIntId").val(searchTerm);
											
											return false;
										}
									});
					
					
					
					
if($("span.successmessage").text()=="Student details updated Successfully"){
						
						$("#div1 #successmessages").attr("style","display:block");
					
					} 	


				});
// fuctions to get values
function getAcademicYear() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAcademicYear",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$(academicYear).html("");
			$(academicYear).append(
					'<option value="' + "" + '">' + "" + '</option>');

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
function getQuota() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getStudentquota",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$(studentquotaname).html("");
			$(studentquotaname).append(
					'<option value="' + "" + '">' + "" + '</option>');
			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$(studentquotaname).append(
						'<option value="'
								+ result.jsonResponse[j].studentquotaid + '">'
								+ result.jsonResponse[j].studentquotaname
								+ '</option>');
			}

		}
	});
}
function getConssition() {

	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getConcessionDetails",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			$("#scholarShipId").html("");
			$("#scholarShipId").append(
					'<option value="' + "" + '">' + "" + '</option>');
			for ( var j = 0; j < result.ConcessionDetails.length; j++) {
				$("#scholarShipId").append(
						'<option value="'
								+ result.ConcessionDetails[j].concessionid
								+ '">' + result.ConcessionDetails[j].concession
								+ '</option>');
			}

		}
	});
}

function getCategory(categoryval) {
	var category = "#" + categoryval;
	$.ajax({
		type : 'GET',
		url : "studentRegistration.html?method=getCategory",
		async : false,
		success : function(response) {
			$(category).html("");
			$(category)
					.append('<option value="' + "" + '">' + "" + '</option>');
			var result = $.parseJSON(response);

			for ( var j = 0; j < result.CategoryList.length; j++) {

				$(category).append(
						'<option value="' + result.CategoryList[j].streemcode
								+ '">' + result.CategoryList[j].streemname
								+ '</option>');
			}
		}
	});
}


function getClassName(classidval, category) {
	

	var classid = "#" + classidval;
	var categoryVal = null;

	if ($('#streamhiddenId').val() == null || $('#streamhiddenId').val() == "") {
		categoryVal = $("#category").val();
	} else {
		categoryVal = $('#streamhiddenId').val();
	}

	datalist = {
		"categoryVal" : categoryVal
	}, $
			.ajax({
				type : 'POST',
				url : "studentRegistration.html?method=getClassDetail",
				data : datalist,
				async : false,
				success : function(response) {
					var result = $.parseJSON(response);
					$(studClassId).html("");
					$(studClassId).append(
							'<option value="' + "" + '">' + "" + '</option>');

					for ( var j = 0; j < result.ClassList.length; j++) {
						$(studClassId).append(
								'<option value="'
										+ result.ClassList[j].classcode + '">'
										+ result.ClassList[j].classname
										+ '</option>');
					}
					

				}
			});

}

function getClassSection(sectionid, studClassId) {/*

	var classidVal = null;

	if ($('#classhiddenid').val() == null || $('#classhiddenid').val() == "") {
		classidVal = $("#studClassId").val();
	} else {
		classidVal = $('#classhiddenid').val();
	}
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
					'<option value="' + "" + '">' + "" + '</option>');

			for ( var j = 0; j < result.sectionList.length; j++) {
				$(sectionid).append(
						'<option value="' + result.sectionList[j].sectioncode
								+ '">' + result.sectionList[j].sectionnaem
								+ '</option>');
			}

		}
	});

*/}

function getTransportCategory() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getTransportCategory",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			$("#transcategory").html("");
			$("#transcategory").append(
					'<option value="' + "" + '">' + "" + '</option>');
			for ( var j = 0; j < result.transportCategory.length; j++) {
				$("#transcategory").append(
						'<option value="'
								+ result.transportCategory[j].transptyId + '">'
								+ result.transportCategory[j].transptyname
								+ '</option>');
			}

		}
	});

}
function getTransportLocations() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getTransportStages",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			$("#translocation").html("");
			$("#translocation").append(
					'<option value="' + "" + '">' + "" + '</option>');
			for ( var j = 0; j < result.transportstages.length; j++) {
				$("#translocation").append(
						'<option value="' + result.transportstages[j].stageCode
								+ '">' + result.transportstages[j].stageName
								+ '</option>');
			}

		}
	});

}

function getTransportCategoryType(typeId) {

	var type = null;
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getTransportCategoryType",
		data : {
			"typeId" : typeId
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			type = result.transportTypeStatus;

		}
	});

	return type;

}
function validatePhoneNo() {
	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
		var phoneId = $('#motherMobileNoId').val();
		if ((phoneId != '' && phoneId != undefined)) {
			phoneNo = {
				"phoneId" : phoneId
			},

			$.ajax({
				url : "studentRegistration.html?method=validatePhoneNo",
				data : phoneNo,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);

					$('.errormessagediv').css({
						'display' : ''
					});
					$('.validateTips').text(response.message);
				}
			});
		}
	}
}
function fathervalidatePhoneNo() {
	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
		var phoneId = $('#fatherMobileNoId').val();
		if ((phoneId != '' && phoneId != undefined)) {
			phoneNo = {
				"phoneId" : phoneId
			},

			$.ajax({
				url : "studentRegistration.html?method=validatePhoneNo",
				data : phoneNo,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);
					$('.errormessagediv').css({
						'display' : ''
					});
					$('.validateTips').text(response.message);
				}

			});
		}
	}
}

function gaurdianvalidatePhoneNo() {
	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
		var phoneId = $('#guardianMobileNoId').val();
		if ((phoneId != '' && phoneId != undefined)) {
			phoneNo = {
				"phoneId" : phoneId
			},

			$.ajax({
				url : "studentRegistration.html?method=validatePhoneNo",
				data : phoneNo,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);
					$('.errormessagediv').css({
						'display' : ''
					});
					$('.validateTips').text(response.message);
				}

			});
		}
	}
}
function mothervalidateEmail() {

	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {

		var emailid = $('#motheremailId').val();

		if ((emailid != '' && emailid != undefined)) {
			emailCheck = {
				"emailid" : emailid
			},

			$.ajax({
				url : "studentRegistration.html?method=validateEmail",
				data : emailCheck,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);

					$('.errormessagediv').css({
						'display' : ''
					});
					$('.validateTips').text(response.message);
				}

			});
		}
	}
}

function fathervalidateEmail() {

	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
		var emailid = $('#fatheremailId').val();

		if ((emailid != '' && emailid != undefined)) {
			emailCheck = {
				"emailid" : emailid
			},

			$.ajax({
				url : "studentRegistration.html?method=validateEmail",
				data : emailCheck,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);

					$('.errormessagediv').css({
						'display' : ''
					});
					$('.validateTips').text(response.message);
				}

			});
		}
	}
}

function gaurdianvalidateEmail() {

	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
		var emailid = $('#guardianemailId').val();

		if ((emailid != '' && emailid != undefined)) {
			emailCheck = {
				"emailid" : emailid
			},

			$.ajax({
				url : "studentRegistration.html?method=validateEmail",
				data : emailCheck,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);
					$('.errormessagediv').css({
						'display' : ''
					});
					$('.validateTips').text(response.message);
				}

			});
		}
	}
}

function ageCalculate() {
	var doofBirth = $('#dateofBirthId').val();

	var birthYear = doofBirth.split("-")[2];

	var currentYear = new Date().getFullYear();

	var yearDiff = parseInt(currentYear) - parseInt(birthYear);

	$('#ageId').val(yearDiff);

}

// validations

function Validate() {
	
	$("#hprymarycntperson").val($("#primarypersonId").val());

	//Student Details 
	
	var studentFirstName = $('#studentFirstNameId');
	var studentrollno = $('#studentrollno'); 
	var applicationNo = $("#applicationNoId"); 
	var dateofJoin = $('#dateofJoinId'); 
	var academicYear = $('#academicYear'); 
	var category = $('#category'); 
	var studClassId = $('#studClassId'); 
	var studSectionId = $('#studSectionId'); 
	var studentquotaname = $("#studentquotaname"); 
	var grade = $('#gradeId'); 
	var rtetype = $('.radio-inline[name="rte"]:checked').val();
	var hosteltype = $('.radio-inline[name="hostel"]:checked').val();
	var concessiontype = $('.radio-inline[name="cencession"]:checked').val();
	var scholarShip = $('#scholarShipId');
	var istransport = $('.radio-inline[name="transport"]:checked').val();
	var transportCategory = $('#transcategory'); 
	var transportLocation = $('#translocation');
	
	
	//For personal information
	
	var dateofBirth = $('#dateofBirthId'); 
	var gendertype = $('.radio-inline[name="gender"]:checked').val();
	var identificationMarks = $('#identificationMarksId'); 
	var bloodGroup = $('#bloodGroupId'); 
	var religion = $('#religionId'); 
	var caste = $('#casteId'); 
	var nationality = $('#nationalityId');
	var physicallychallengedtype = $('.radio-inline[name="physicallyChallenged"]:checked').val();
	var physicallychallengereason=$("#physicalchalreason").val();
	var studentStatus=$("#studentStatusId");
	
	
	//Prents Information
	
	var primaryperson = $('#hprymarycntperson').val();
	var fathername = $('#fatherNameId').val();
	var fatherqualification = $('#fatherQualificationId').val();
	var fathermobileno = $('#fatherMobileNoId').val();
	var motherNameId = $('#motherNameId').val();
	var motherQualificationId = $('#motherQualificationId').val();
	var motherMobileNoId = $('#motherMobileNoId').val();
	var gaurdianNameId = $('#gaurdianNameId').val();
	var guardianMobileNoId = $('#guardianMobileNoId').val();
	var addr = $("#paddrs").val().trim();
	
	
	
	var bValid = true;
	

	if (studentFirstName.val() == "" || studentFirstName.val() == null) {

		$(".errormessagediv").show();

		$(".validateTips").text("Enter Student First Name");
		$("#studentFirstNameId").focus();
		document.getElementById("studentFirstNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("studentFirstNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else
	
	if (!studentFirstName.val().match(/[A-Za-z]+$/i)) {
		
		$('.errormessagediv').show();
		$('.validateTips').text("Enter a Valid Student First Name");
		$("#studentFirstNameId").focus();
		document.getElementById("studentFirstNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("studentFirstNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else
	
	if (studentrollno.val() == "" || studentrollno.val() == null) {

		$(".errormessagediv").show();
		$(".validateTips").text("Enter Student Admission Number");
		$("#studentrollno").focus();
		document.getElementById("studentrollno").style.border = "1px solid #AF2C2C";
		document.getElementById("studentrollno").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else
	
	if (!studentrollno.val().match(/[0-9\s]+$/i)) {
		$('.errormessagediv').show();
		$('.validateTips').text("Enter a Valid Student Admission Number");
		$("#studentrollno").focus();
		document.getElementById("studentrollno").style.border = "1px solid #AF2C2C";
		document.getElementById("studentrollno").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else
	
	if (applicationNo.val() == "" || applicationNo.val() == null) {

		$(".errormessagediv").show();

		$(".validateTips").text("Enter Student Application Number");
		$("#applicationNoId").focus();
		document.getElementById("applicationNoId").style.border = "1px solid #AF2C2C";
		document.getElementById("applicationNoId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else
	
	if (!applicationNo.val().match(/[0-9\s]+$/i)) {
		$('.errormessagediv').show();
		$('.validateTips').text("Enter a Valid Student Application Number");
		$("#applicationNoId").focus();
		document.getElementById("applicationNoId").style.border = "1px solid #AF2C2C";
		document.getElementById("applicationNoId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else
	
	if (dateofJoin.val() == '') {
		
		$(".validateTips").text("Select Admission Date ");
		$(".errormessagediv").show();
		$("#dateofJoinId").focus();
		document.getElementById("dateofJoinId").style.border = "1px solid #AF2C2C";
		document.getElementById("dateofJoinId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;
		
	} else
	
	if (academicYear.val() == null || academicYear.val() == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Academic Year should not empty");
		$("#academicYear").focus();
		document.getElementById("academicYear").style.border = "1px solid #AF2C2C";
		document.getElementById("academicYear").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else
	
	if (category.val() == null || category.val() == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Select Stream Name");
		$("#category").focus();
		document.getElementById("category").style.border = "1px solid #AF2C2C";
		document.getElementById("category").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else
	
	if (studClassId.val() == null || studClassId.val() == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Select Class Name");
		$("#studClassId").focus();
		document.getElementById("studClassId").style.border = "1px solid #AF2C2C";
		document.getElementById("studClassId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else
	
	if (studSectionId.val() == null || studSectionId.val() == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Select Section Name");
		$("#studSectionId").focus();
		document.getElementById("studSectionId").style.border = "1px solid #AF2C2C";
		document.getElementById("studSectionId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else
	
	if (studentquotaname.val() == null || studentquotaname.val() == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Select Quota Name");
		$("#studentquotaname").focus();
		document.getElementById("studentquotaname").style.border = "1px solid #AF2C2C";
		document.getElementById("studentquotaname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else
	
	if (grade.val() == "" || grade.val() == null) {

		$(".errormessagediv").show();

		$(".validateTips").text("Enter Grade");
		$("#gradeId").focus();
		document.getElementById("gradeId").style.border = "1px solid #AF2C2C";
		document.getElementById("gradeId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else
	
	if (rtetype == "" || rtetype == null) {

		$('.errormessagediv').show();
		$('.validateTips').text("Select RTE Type Yes/No");
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);
		
		bValid= false;
		
	} else
	
	if (hosteltype == "" || hosteltype == null) {

		$('.errormessagediv').show();
		$('.validateTips').text("Select Hostel Type Yes/No");

		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);
		
		bValid= false;
		
	} else
	
	if (concessiontype == "" || concessiontype == null) {

		$('.errormessagediv').show();
		$('.validateTips').text("Select Concession Type Yes/No");

		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);
		
		bValid= false;
		
	} else
	
	if (concessiontype == "Y" && scholarShip.val().trim() == "") {
		
			
			$(".errormessagediv").show();
			$(".validateTips").text("Select Concession");
			$("#scholarShipId").focus();
			document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
			document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
			}, 3000);

			bValid=false;

	}else 
	

		if (istransport == "" || istransport == undefined) {

			$('.errormessagediv').show();
			$('.validateTips').text("Select Transport Yes/No");

			setTimeout(function() {
				$('#errorhover').fadeOut();
			}, 3000);
			
			bValid= false;
			
		} else
		
		if (istransport == "Y" && transportCategory.val().trim() == "") {
			
	
				
				$(".errormessagediv").show();
				$(".validateTips").text("Select Transpoer Category");
				$("#transcategory").focus();
				document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
				document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('#errorhover').fadeOut();
				}, 3000);

				bValid=false;
				
			
		}else 
			
		if((transportCategory.val().trim()!="") && (getTransportCategoryType($("#transcategory").val().trim()) == 'Y') && transportLocation.val().trim() == ""){
			
				
				$(".errormessagediv").show();
				$(".validateTips").text("Select Transpoer Location");
				$("#translocation").focus();
				document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
				document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('#errorhover').fadeOut();
				}, 3000);

				bValid=false;
				
			
			
			
		}else
	
		if (dateofBirth.val() == null || dateofBirth.val() == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Date Of Birth should not empty");
		$("#dateofBirthId").focus();
		document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
		document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid=false;

	} else if (gendertype == "" || gendertype == null) {

		$('.errormessagediv').show();
		$('.validateTips').text("Select Gender Type Yes/No");
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);
		
		bValid=false;
		
	} else
	
		if (identificationMarks.val() == "" || identificationMarks.val() == null)

		{

		$(".errormessagediv").show();

		$(".validateTips").text("Enter Identificaton Marks of Student ");
		$("#identificationMarksId").focus();
		document.getElementById("identificationMarksId").style.border = "1px solid #AF2C2C";
		document.getElementById("identificationMarksId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid=false;

	} else

		if (bloodGroup.val() == null || bloodGroup.val() == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Select Blood Group");
		$("#bloodGroupId").focus();
		document.getElementById("bloodGroupId").style.border = "1px solid #AF2C2C";
		document.getElementById("bloodGroupId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid=false;
	} else

		if (religion.val() == "" || religion.val() == null)

		{

		$(".errormessagediv").show();

		$(".validateTips").text("Enter Religion ");
		$("#religionId").focus();
		document.getElementById("religionId").style.border = "1px solid #AF2C2C";
		document.getElementById("religionId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid=false;

	} else
		
		if (caste.val() == "" || caste.val() == null)
		{

		$(".errormessagediv").show();

		$(".validateTips").text("Enter Caste");
		$("#casteId").focus();
		document.getElementById("casteId").style.border = "1px solid #AF2C2C";
		document.getElementById("casteId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid=false;

	} else

		if (nationality.val() == "" || nationality.val() == null)

		{

		$(".errormessagediv").show();

		$(".validateTips").text("Enter Nationality");
		$("#nationalityId").focus();
		document.getElementById("nationalityId").style.border = "1px solid #AF2C2C";
		document.getElementById("nationalityId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid=false;

	} else

		if (physicallychallengedtype == "" || physicallychallengedtype == null) {

		$('.errormessagediv').show();
		$('.validateTips').text("Select Physically Challenged Type Yes/No");

		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);
		
		bValid=false;
		
	}else

		if (physicallychallengedtype=='Y' && physicallychallengereason.trim()=="") {

		$('.errormessagediv').show();
		$('.validateTips').text("Enter Reason for Physically Challenged");

		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);
		
		bValid=false;
		
	}else 
		if(studentStatus.val()==""){

			$(".errormessagediv").show();

			$(".validateTips").text("Select Status");
			$("#studentStatusId").focus();
			document.getElementById("studentStatusId").style.border = "1px solid #AF2C2C";
			document.getElementById("studentStatusId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
			}, 3000);

			bValid=false;

		}else
	
		if (primaryperson == null || primaryperson == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Select Primary Contact Person Name");
		$("#primarypersonId").focus();
		document.getElementById("primarypersonId").style.border = "1px solid #AF2C2C";
		document.getElementById("primarypersonId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid=false;

		} else
	
		if (fathername == null || fathername == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Enter Father Name");
		$("#fatherNameId").focus();
		document.getElementById("fatherNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("fatherNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid=false;

		} else if (primaryperson.trim()=="father" && fatherqualification == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Enter Father Qualification");
		$("#fatherQualificationId").focus();
		document.getElementById("fatherQualificationId").style.border = "1px solid #AF2C2C";
		document.getElementById("fatherQualificationId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else if (primaryperson.trim()=="father" && fathermobileno == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Enter Father Mobile Number");
		$("#fatherMobileNoId").focus();
		document.getElementById("fatherMobileNoId").style.border = "1px solid #AF2C2C";
		document.getElementById("fatherMobileNoId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else if (motherNameId == null || motherNameId == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Enter Mother Name");
		$("#motherNameId").focus();
		document.getElementById("motherNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("motherNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else if (primaryperson.trim()=="mother" && motherQualificationId == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Enter Mother Qualification");
		$("#motherQualificationId").focus();
		document.getElementById("motherQualificationId").style.border = "1px solid #AF2C2C";
		document.getElementById("motherQualificationId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else if (primaryperson.trim()=="mother" && motherMobileNoId == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Enter Mother Mobile Number");
		$("#motherMobileNoId").focus();
		document.getElementById("motherMobileNoId").style.border = "1px solid #AF2C2C";
		document.getElementById("motherMobileNoId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else if (gaurdianNameId == null || gaurdianNameId == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Enter Guardian Name");
		$("#gaurdianNameId").focus();
		document.getElementById("gaurdianNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("gaurdianNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else if (primaryperson.trim()=="guardian" && guardianMobileNoId == "") {

		$(".errormessagediv").show();
		$(".validateTips").text("Select Guardian Mobile Number");
		$("#guardianMobileNoId").focus();
		document.getElementById("guardianMobileNoId").style.border = "1px solid #AF2C2C";
		document.getElementById("guardianMobileNoId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	} else

		if (addr == "" || addr == null)

		{

		$(".errormessagediv").show();

		$(".validateTips").text("Enter Address Of Student");
		$("#paddrs").focus();
		document.getElementById("paddrs").style.border = "1px solid #AF2C2C";
		document.getElementById("paddrs").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
		}, 3000);

		bValid= false;

	}
	
	
	else{
			
			return bValid;
			
		}
			
}


function checkRollnumber() {

	var status = false;

	var rollNumber = $("#studentrollno").val();

	if ((rollNumber != '' && rollNumber != undefined)) {
		rollNumberCheck = {
			"rollNumber" : rollNumber
		},

		$.ajax({
			url : "studentRegistration.html?method=validateroleNumber",
			data : rollNumberCheck,
			async : false,
			success : function(data) {

				var response = $.parseJSON(data);

				if (response.message == "True") {
					$('.errormessagediv').css({
						'display' : ''
					});
					$('.validateTips').text("Admission Number already exist");

					status = true;

				} else {

					$('.errormessagediv').css({
						'display' : 'none'
					});

					status = false;
				}
			}

		});

		return status;
	}
}

function readyOnly() {}

function HideError() {
	document.getElementById("studentFirstNameId").style.border = "1px solid #ccc";
	document.getElementById("studentFirstNameId").style.backgroundColor = "#fff";

	document.getElementById("studentFirstNameId").style.border = "1px solid #ccc";
	document.getElementById("studentFirstNameId").style.backgroundColor = "#fff";

	document.getElementById("studentLastNameId").style.border = "1px solid #ccc";
	document.getElementById("studentLastNameId").style.backgroundColor = "#fff";

	document.getElementById("studentrollno").style.border = "1px solid #ccc";
	document.getElementById("studentrollno").style.backgroundColor = "#fff";

	document.getElementById("applicationNoId").style.border = "1px solid #ccc";
	document.getElementById("applicationNoId").style.backgroundColor = "#fff";

	document.getElementById("dateofJoinId").style.border = "1px solid #ccc";
	document.getElementById("dateofJoinId").style.backgroundColor = "#fff";

	document.getElementById("academicYear").style.border = "1px solid #ccc";
	document.getElementById("academicYear").style.backgroundColor = "#fff";

	document.getElementById("category").style.border = "1px solid #ccc";
	document.getElementById("category").style.backgroundColor = "#fff";

	document.getElementById("studClassId").style.border = "1px solid #ccc";
	document.getElementById("studClassId").style.backgroundColor = "#fff";

	document.getElementById("studSectionId").style.border = "1px solid #ccc";
	document.getElementById("studSectionId").style.backgroundColor = "#fff";

	document.getElementById("studentquotaname").style.border = "1px solid #ccc";
	document.getElementById("studentquotaname").style.backgroundColor = "#fff";

	document.getElementById("gradeId").style.border = "1px solid #ccc";
	document.getElementById("gradeId").style.backgroundColor = "#fff";

	document.getElementById("dateofBirthId").style.border = "1px solid #ccc";
	document.getElementById("dateofBirthId").style.backgroundColor = "#fff";

	document.getElementById("identificationMarksId").style.border = "1px solid #ccc";
	document.getElementById("identificationMarksId").style.backgroundColor = "#fff";

	document.getElementById("bloodGroupId").style.border = "1px solid #ccc";
	document.getElementById("bloodGroupId").style.backgroundColor = "#fff";

	document.getElementById("religionId").style.border = "1px solid #ccc";
	document.getElementById("religionId").style.backgroundColor = "#fff";

	document.getElementById("casteId").style.border = "1px solid #ccc";
	document.getElementById("casteId").style.backgroundColor = "#fff";

	document.getElementById("nationalityId").style.border = "1px solid #ccc";
	document.getElementById("nationalityId").style.backgroundColor = "#fff";

	document.getElementById("primarypersonId").style.border = "1px solid #ccc";
	document.getElementById("primarypersonId").style.backgroundColor = "#fff";

	
	 document.getElementById("fatherNameId").style.border = "1px solid #ccc";
	  document.getElementById("fatherNameId").style.backgroundColor = "#fff";
	  
	 document.getElementById("fatherQualificationId").style.border = "1px solid #ccc";
	  document.getElementById("fatherQualificationId").style.backgroundColor =
	  "#fff";
	  
	  document.getElementById("fatherMobileNoId").style.border = "1px solid  #ccc";
	  document.getElementById("fatherMobileNoId").style.backgroundColor =
	  "#fff";
	  
	  document.getElementById("motherNameId").style.border = "1px solid #ccc";
	  document.getElementById("motherNameId").style.backgroundColor = "#fff";
	  
	  document.getElementById("motherQualificationId").style.border = "1px solid #ccc";
	  document.getElementById("motherQualificationId").style.backgroundColor =
	  "#fff";
	  
	  document.getElementById("motherMobileNoId").style.border = "1px solid #ccc"; 
	  document.getElementById("motherMobileNoId").style.backgroundColor =
	  "#fff";
	  
	  document.getElementById("gaurdianNameId").style.border = "1px solid #ccc";
	  document.getElementById("gaurdianNameId").style.backgroundColor =
	  "#fff";
	  
	  document.getElementById("guardianMobileNoId").style.border = "1px solid #ccc";
	  document.getElementById("guardianMobileNoId").style.backgroundColor =
	  "#fff";
	 

	document.getElementById("paddrs").style.border = "1px solid #ccc";
	document.getElementById("paddrs").style.backgroundColor = "#fff";

}





function getRouteDetails(){
	
	//alert("myfunction function");
								
	$
	.ajax({
		type : 'POST',
		url : "transport.html?method=getRouteDetails",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#route').html("");
			$('#route').append(
					'<option value="' + "" + '">'
							+ ""
							+ '</option>');
			for ( var j = 0; j < result.routelist.length; j++) {
				$('#route')
						.append(
								'<option value="'
										+ result.routelist[j].routeCode
										+ '">'
										+ result.routelist[j].routeName
										+ '</option>');
			}
			

		}
	});
		}

