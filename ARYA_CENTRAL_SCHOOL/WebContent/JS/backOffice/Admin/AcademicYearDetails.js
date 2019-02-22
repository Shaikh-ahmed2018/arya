$(document)
.ready(
		function() {

			$("#selectall").change(function(){
				$(".academic_Checkbox_Class").prop("checked",$(this).prop("checked"));
			});

			$('.errormessagediv1').hide();
			if ($('#accid').val() == null || $('#accid').val() == "") {

			} 

			else {

				$("#description").val($("#descriptionId").val().trim());
			}


			$('#excelDownload').click(function() {

				var searchTerm = $("#searchexamid").val().trim();

				window.location.href = 'AcademicYearPath.html?method=AcademicYearPathDetailsXLS&searchTerm='+ searchTerm;

			});

			$("#pdfDownload")
			.click(
					function() {
						var searchTerm = $("#searchexamid").val().trim();

						window.location.href = "AcademicYearPath.html?method=AcademicYearPathDetailsPDFReport&searchTerm="+ searchTerm;

					});	



			$("#editDesignationId").click(function() {
				var cnt = 0;

				$('input.academic_Checkbox_Class:checkbox:checked').map(function() {

					var term_id = $(this).attr("id");
					var split_id = term_id.split('_');
					getData = split_id[1].split(',');

					cnt++;
				});
				if (cnt == 0 || cnt > 1) {

					$('.errormessagediv1').show();
					$('.validateTips1').text("Select Any One Academic Year");
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 2000);

				} else {
					window.location.href = "AcademicYearPath.html?method=editAcademicYear&Acy_Code="
						+ getData[0];

				}

			});

			$("#list").click(function() {
				window.location.href = "adminMenu.html?method=academicyear";
			});

			$("#searchname").keydown(function(event) {
				var searchText = $("#searchname").val();
				if (event.keyCode == 13) {
					window.location.href = "adminMenu.html?method=academicyear&searchText="
						+ searchText;
				}

			});

			$("#search").click(function(){
				var searchText = $("#searchname").val().trim();
				window.location.href ="adminMenu.html?method=academicyear&searchText="
					+ searchText;
			});

			$("#trash").click(function() {

				var cnt = 0;

				$('input.academic_Checkbox_Class:checkbox:checked').map(function() {

					var term_id = $(this).attr("id");
					var split_id = term_id.split('_');
					getData = split_id[1].split(',');

					cnt++;
				});
				if (cnt == 0 || cnt > 1) {

					$('.errormessagediv1').show();
					$('.validateTips1').text("Select Any One Academic Year");
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 2000);

				} else {

					var Check = {
							"Code" : getData[0]
					};
					$
					.ajax({
						type : "POST",
						url : "AcademicYearPath.html?method=deleteAcademicYear",
						data : Check,
						async : false,
						success : function(data) {
							var result = $
							.parseJSON(data);

							if (result.status == true) {
								$('.successmessagediv').show();
								$('.successmessage').text("Delete Academic Academic Year Progressing...");
								setTimeout(function() {
									location.reload();
								}, 2000);
							} else {
								$('.errormessagediv1').show();
								$('.validateTips1').text("Academic Year already mapped can't Delete");

								setTimeout(function() {
									location.reload();
								}, 2000);
							}

						}
					});

				}

			});

			$(".errormessagediv").hide();

			$("#startdate").datepicker({

				changeMonth : true,
				changeYear : true,
				dateFormat : "dd-mm-yy",

			});

			$("#enddate").datepicker({

				dateFormat : "dd-mm-yy",
				changeMonth : "true",
				changeYear : "true",
				buttonImage : "images/calendar.GIF",
				buttonImageOnly : true

			});

			$("#save").click(function() {
				var accyear = $('#accyearname').val().trim();
				var startdate = $('#startdate').val().trim();
				var enddate = $('#enddate').val().trim();
				var description = $('#description').val().trim();
				var accid = $('#accid').val().trim();

				if (accyear == "" || accyear == null) {
					$('.errormessagediv1').show();
					$('.validateTips1').text("Enter Academic Year");
					document.getElementById("accyearname").style.border = "1px solid #AF2C2C";
					document.getElementById("accyearname").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 2000);

					return false;
				} 

				else if (!accyear.match("^(?=.*?[1-9])[0-9()-]+$")) {
					$('.errormessagediv1').show();
					$('.validateTips1').text("Enter Alpha Numeric");
					document.getElementById("accyearname").style.border = "1px solid #AF2C2C";
					document.getElementById("accyearname").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 2000);
					return false;
				}

				else if (startdate == ""|| startdate == null) {
					$('.errormessagediv1').show();
					$('.validateTips1').text("Enter Start Date");
					document.getElementById("startdate").style.border = "1px solid #AF2C2C";
					document.getElementById("startdate").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 2000);
					return false;
				} else if (enddate == ""
					|| enddate == null) {
					$('.errormessagediv1').show();
					$('.validateTips1').text("Enter End Date");
					document.getElementById("enddate").style.border = "1px solid #AF2C2C";
					document.getElementById("enddate").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 2000);
					return false;
				}


				else if (checkName() == 1) {
					$('.errormessagediv1').show();
					$('.validateTips1').text("Academic Name or Dates Already Exists");
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 2000);
					return false;

				} 


				else {

					if (accid == "" || accid == null) {
						accid = "NULL";
					}

					var Check = {
							"accyear" : accyear,
							"startdate" : startdate,
							"enddate" : enddate,
							"description" : description,
							"accid" : accid
					};
					$
					.ajax({
						type : "POST",
						url : "AcademicYearPath.html?method=createAcademicYear",
						data : Check,
						async : false,
						success : function(data) {
							var result = $
							.parseJSON(data);
							if (result.status == "success") {
								$('.successmessagediv').show();
								/*	$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");*/
								var page=window.location.href.substring(window.location.href.lastIndexOf("?")+1);
								if(page.split("&")[0]=="method=editAcademicYear")
								$('.successmessage').text("Update Academic Year Details Progressing...");
								else
								$('.successmessage').text("Add Academic Year Details Progressing...");
								setTimeout(function() {
									window.location.href = "adminMenu.html?method=academicyear";
								}, 3000);
							} else {
								$('.errormessagediv1').show();
								$('.validateTips1').text("Failed to Add Academic Details! Try Again...");

							}

						}
					});

				}
			});

			function checkName() {

				accid = $("#accid").val().trim();



				if (accid == "" || accid == null) {
					accid = "NULL";
				}

				var checkspecializationName = {
						"accyearname" : $("#accyearname").val(),
						"startdate" : $("#startdate").val(),
						"enddate" : $("#enddate").val(),
						"accid" : accid
				};
				var status = false;

				$
				.ajax({
					type : "POST",
					url : "AcademicYearPath.html?method=accyearNameCheck",
					data : checkspecializationName,
					async : false,
					success : function(data) {

						var result = $.parseJSON(data);

						text = result.status;


						if (text == "Academic Year already Exist" || text == "Academic year already created using this dates") {

							status = true;
						}
						else
						{

							status= false;
						}
					}
				});

				return status;

			}

		});

function setEndDate() {

	var startDate = $('#startdate').val();

	startDate = startDate.split("-");

	var d = new Date(startDate[2], startDate[1] - 1, startDate[0]);

	d.setFullYear(d.getFullYear() + 1);
	d.setDate(d.getDate() - 1);

	var str = (d.getMonth() + 1).toString();
	var pad = "00";

	pad.substring(0, pad.length - str.length) + str;

	var ee = d.getDate() + "-" + pad.substring(0, pad.length - str.length)
	+ str + "-" + d.getFullYear();
	$('#enddate').val(ee);

	accid = $("#accid").val().trim();

	if (accid == "" || accid == null) {
		accid = "NULL";
	}
	var checkName = {
			"accyearname" : $("#accyearname").val(),
			"startdate" : $("#startdate").val(),
			"enddate" : $("#enddate").val(),
			"accid" : accid
	};
	var status = false;
	$
	.ajax({
		type : "POST",
		url : "AcademicYearPath.html?method=accyearNameCheck",
		data : checkName,
		async : false,
		success : function(data) {

			var result = $.parseJSON(data);
			text = result.status;
			if (text == "Academic Year already Exist") {
				$('.errormessagediv1').show();
				$('.validateTips1').text("Academic Year already Exist using this Dates");
				$('.errormessagediv1').delay(2000).fadeOut("slow");
				status = true;
			} else if (text == "Academic year already created using this dates") {
				$('.errormessagediv1').show();
				$('.validateTips1').text("Academic year already created using this dates");
				$('.errormessagediv1').delay(2000).fadeOut("slow");
				status = true;
			}
		}
	});

	return status;

}



function HideError() 
{

	document.getElementById("accyearname").style.border = "1px solid #ccc";
	document.getElementById("accyearname").style.backgroundColor = "#fff";
	document.getElementById("startdate").style.border = "1px solid #ccc";
	document.getElementById("startdate").style.backgroundColor = "#fff";
	document.getElementById("enddate").style.border = "1px solid #ccc";
	document.getElementById("enddate").style.backgroundColor = "#fff";

}