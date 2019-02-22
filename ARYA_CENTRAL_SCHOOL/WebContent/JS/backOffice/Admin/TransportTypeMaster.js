function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document)
		.ready(
				function() {
					
				});
					
					

					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
					
					
					$("#selectall").change(function(){
						$(".typeDetails_Checkbox_Class").prop("checked",$(this).prop("checked"));
					});
					


					var alphabetRegex = "/^[a-zA-Z0-9]+$/i";
					if ($("#hcollectionType").val() != "") {

						$(
								"input[name='transporttype'][value="
										+ $("#hcollectionType").val() + "]")
								.attr("checked", 'true');
					}

					$('#save')
							.click(
									function() {

										var typeId = $("#typeId").val();

										var typeName = $("#name").val();
										var typeDescription = $("#description")
												.val();
										var collectionType = $(
												"input[type='radio'][name='transporttype']:checked")
												.val();

										if (typeId == "") {
											if (typeName == "") {

												$('.errormessagediv').show();
												$('.validateTips').text("Enter  Name");
												document.getElementById("name").style.border = "1px solid #AF2C2C";
												document.getElementById("name").style.backgroundColor = "#FFF7F7";
												setTimeout(function() {
													$('.errormessagediv').fadeOut();
												}, 3000);
												return false;
											} else if (!typeName.match(/^[a-zA-Z\s]+$/i)) {

												$('.errormessagediv').show();
												$('.validateTips').text("Name should be Alphabet");
												document.getElementById("name").style.border = "1px solid #AF2C2C";
												document.getElementById("name").style.backgroundColor = "#FFF7F7";
												setTimeout(function() {
													$('.errormessagediv').fadeOut();
												}, 3000);
												return false;

											} else if (collectionType == undefined) {

												$('.errormessagediv').show();
												$('.validateTips').text("Select Concession Type");
												
												return false;
												
											} else if (validateTypeName() == 1) {

												$('.errormessagediv').show();
												$('.validateTips').text("Name Already Exist");
												return false;
											} 
											else {

												var datalist = {

													"typeName" : typeName,
													"typeDescription" : typeDescription,
													"collectionType" : collectionType
												};

												$
														.ajax({
															type : "GET",
															url : "transportType.html?method=Add",
															data : datalist,
															async : false,
															success : function(
																	data) {
																var result = $
																		.parseJSON(data);

																if (result.status == "true") {

																	$(
																			'.errormessagediv')
																			.hide();
																	$(
																			'.successmessagediv')
																			.show();
																	$(
																			'.successmessage')
																			.text(
																					"Transport Type Created Successfully");
																	
																	setTimeout(
																			function() {
																				window.location.href = "adminMenu.html?method=transportTypeHome";

																			},
																			3000);
																	
																	
																	

																} else {
																	$(
																			'.errormessagediv')
																			.show();
																	$(
																			'.validateTips')
																			.text(
																					"Transport Type Not Create,Please try again");
																	
																	setTimeout(
																			function() {

																				location
																						.reload();

																			}, 3000);
																	
																	
																	
																}

																

															}
														});
											}

										} else {

											if (typeName == "") {

												$('.errormessagediv').show();
												$('.validateTips').text(
														"Enter  Name");
												return false;
											} else if (!typeName
													.match(/^[a-zA-Z\s]+$/i)) {

												$('.errormessagediv').show();
												$('.validateTips')
														.text(
																"Name should be Alphabet");
												return false;

											} else if (collectionType == undefined) {

												$('.errormessagediv').show();
												$('.validateTips')
														.text(
																"Select Concession Type");
												return false;
											} else if (validateTypeNameUpdate() == 1) {

												$('.errormessagediv').show();
												$('.validateTips').text(
														"Name already exist");
												return false;
											} else {

												var datalist = {
													"typeId" : typeId,
													"typeName" : typeName,
													"typeDescription" : typeDescription,
													"collectionType" : collectionType
												};

												$
														.ajax({
															type : "GET",
															url : "transportType.html?method=Update",
															data : datalist,
															async : false,
															success : function(
																	data) {
																var result = $
																		.parseJSON(data);

																$("#name").val(	"");
																$("#description").val("");
																$("input[name=isTransporttype]").attr("checked",'false');

																if (result.status == "true") {

																	$(
																			'.errormessagediv')
																			.hide();
																	$(
																			'.successmessagediv')
																			.show();
																	$(
																			'.successmessage')
																			.text(
																					"Transport Type Updated Successfully");

																	setTimeout(
																			function() {
																				window.location.href = "adminMenu.html?method=transportTypeHome";

																			},
																			3000);

																} else {
																	$(
																			'.errormessagediv')
																			.show();
																	$(
																			'.validateTips')
																			.text(
																					"Transport Type Not Update,Please try again");
																	setTimeout(
																			function() {
																				location
																						.reload();

																			},
																			3000);
																}

															}
														});

											}
										}

									});

					$('#edit')
							.click(
									function() {

										var count = 0;
										var type_Code = null;

										$('input[name="checkboxname"]:checked')
												.each(function() {
													count = count + 1;
													type_Code = this.id;
												});

										if (count > 1 || count == 0) {

											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Select Any One Transport Type");

											return false;

										} else {

											window.location.href = "transportType.html?method=editType&TypeCode="
													+ type_Code;

										}

									});

					$('#delete')
							.click(
									function() {
										
										var getDataArray=[]; //add array1
										var count = 0;
										var type_Code = null;

										$('input[name="checkboxname"]:checked')
												.each(function() {
													
											
													count = count + 1;
													type_Code = this.id;
													
													getDataArray.push(getData[0]);//push data in array2
													
												});

										/*if (count == 0 || count > 1) {
											$('.errormessagediv').show();
											$('.validateTips')
													.text(
															"Select Any One Transport Type");

											return false;

										} else {
*/
											var x = confirm("Are you sure to Delete Transport Type");
											if (x) {

											/*	var type = {
													"typeCode" : type_Code
												};*/
												$
														.ajax({
															type : "GET",
															url : "transportType.html?method=deleteType",
															data : type,
															async : false,

															success : function(
																	data) {
																var result = $
																		.parseJSON(data);

																$(
																		'.errormessagediv')
																		.hide();

																if (result.status == "true") {

																	$(
																			".successmessagediv")
																			.show();
																	$(
																			".successmessage")
																			.text(
																					"Transport Type Deleted SuccessFully");

																} else {

																	$(
																			'.errormessagediv')
																			.show();
																	$(
																			'.validateTips')
																			.text(
																					"Transport Type Not Delete,Please try again");

																}

																setTimeout(
																		function() {

																			location
																					.reload();

																		}, 3000);

															

															}
														});

											}

									

									});

					$("#search")
							.click(
									function()

									{

										var searchTerm = $("#searchterm").val()
												.trim();

										window.location.href = "adminMenu.html?method=transportTypeHome&searchTerm="
												+ searchTerm;

									});

					$("#excelDownload")
							.click(
									function() {

										var searchTerm = $("#transportsearchid")
												.val().trim();

										window.location.href = "transportType.html?method=downloadTransportTypeXLS&searchTerm="
												+ searchTerm;

									});
					$("#pdfDownload")
							.click(
									function() {

										var searchTerm = $("#transportsearchid")
												.val().trim();

										window.location.href = "transportType.html?method=downloadTransportTypePDF&searchTerm="
												+ searchTerm;

									});

				

function validateTypeNameUpdate() {

	var typeName_validate_update = 0;
	var typeName = $('#name').val();
	var typeId = $("#typeId").val();

	var typeObject = {
		"typeName" : typeName,
		"typeId" : typeId
	};

	$.ajax({

		type : "GET",
		url : "transportType.html?method=validateTypeNameUpdate",
		data : typeObject,
		async : false,

		success : function(data) {

			var result = $.parseJSON(data);

			if (result.status == "true") {

				$('.errormessagediv').show();
				$('.validateTips').text(" Name Already Exists");

				typeName_validate_update = 1;

			} else {
				typeName_validate_update = 0;
			}

		}

	});

	return typeName_validate_update;

}

function validateTypeName() {

	var typeName_validate = 0;
	var typeName = $('#name').val();
	var typeObject = {
		"typeName" : typeName
	};

	$.ajax({

		type : "GET",
		url : "transportType.html?method=validateTypeName",
		data : typeObject,
		async : false,

		success : function(data) {

			var result = $.parseJSON(data);

			if (result.status == "true") {

				$('.errormessagediv').show();
				$('.validateTips').text("Name already exist");
				document.getElementById("name").style.border = "1px solid #AF2C2C";
				document.getElementById("name").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);

				typeName_validate = 1;

			} else {
				typeName_validate = 0;
			}

		}

	});

	return typeName_validate;
}

function HideError() 
{
	
document.getElementById("name").style.border = "1px solid #ccc";
document.getElementById("name").style.backgroundColor = "#fff";


}