$(document)
		.ready(
				function() {
					
					
					
					
				/*	  $(".successmessagediv").hide();
					  $(".errormessagediv1").hide();
					  $(".successmessagediv").hide();*/
					 

					$('#group_name').hide();
					$('#group_label').hide();
					$('#group_name1').hide();
					$('#group_label1').hide();

					$('#classname').change(function()

					{

						var classname = $('#classname').val();

						if (classname == 'CCD11' || classname == 'CCD12') {

							$('#group_name').show();
							$('#group_label').show();

						} else {

							$('#group_name').hide();
							$('#group_label').hide();

						}
					});
					
					
					$('#previous_classname').change(function()

							{

								var previous_classname = $('#previous_classname').val();

								if (previous_classname == 'CCD11' || previous_classname == 'CCD12') {

									$('#group_name1').show();
									$('#group_label1').show();

								} else {

									$('#group_name1').hide();
									$('#group_label1').hide();

								}
							});

					$("#dateofBirthId").datepicker({
						dateFormat : "dd-mm-yy",
						defaultDate : "+1w",
						maxDate : 0,
						changeYear : true,
						changeMonth : true,
						numberOfMonths : 1
					});

					$('.save2')
							.click(
									function() {

										
										var school_name = $('#school_name').val();
										var percentage = $("#percentage").val();
										var previous_classname = $('#previous_classname').val();
										var group_name1 = $('#group_name1').val();
										
										var studentfirstName = $('#studentfirstName').val();
										var dateofBirthId = $("#dateofBirthId").val();
										var classname = $('#classname').val();
										var group_name = $('#group_name').val();
										var accyear = $('#accyear').val();
										var parentId = $('#parentId').val();
										var mobile_number = $('#mobile_number').val();
										var emailId = $('#emailId').val();
										var address = $('#address').val();
										var relationship = $("#relationship").val();
										var alternateMobileNo = $('#alternateMobileNo').val();
										var alternateemailId = $('#alternateemailId').val();
										var paper = $('#paper').is(':checked');
										var websites = $('#websites').is(':checked');
										var channels = $('#channels').is(':checked');
										var others = $('#others').is(':checked');
										var parents = $('#parents').is(':checked');
										var advertisement = $('#advertisement').is(':checked');
										
										

										if (studentfirstName == ""|| studentfirstName == null) {

											$(".errormessagediv").show();

											$(".validateTips").text("Enter  Student Name");
											$("#studentfirstName").focus();
											document.getElementById("studentfirstName").style.border = "1px solid #AF2C2C";
											document.getElementById("studentfirstName").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										} else if (!studentfirstName.match(/[A-Za-z]+$/i)) {

											$('.errormessagediv').show();
											$('.validateTips').text("Enter a Valid Student Name");
											$("#studentfirstName").focus();
											document.getElementById("studentfirstName").style.border = "1px solid #AF2C2C";
											document.getElementById("studentfirstName").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										} else if (dateofBirthId == ""|| dateofBirthId == null) {

											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Date Birth");
											$("#dateofBirthId").focus();
											document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
											document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										}
										
										
										
										/*else if (school_name == ""|| school_name == null) {

											$(".errormessagediv").show();
											$(".validateTips").text(
													"Enter School Name");
											$("#school_name").focus();
											document.getElementById("school_name").style.border = "1px solid #AF2C2C";
											document.getElementById("school_name").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										}*/
										
										
										/*else if (percentage == ""|| percentage == null) {

											$(".errormessagediv").show();
											$(".validateTips").text(
													"Enter Percentage");
											$("#percentage").focus();
											document.getElementById("percentage").style.border = "1px solid #AF2C2C";
											document.getElementById("percentage").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										}*/
										
										/*else if (previous_classname == ""|| previous_classname == null) {

											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Previous Class");
											$("#previous_classname").focus();
											document.getElementById("previous_classname").style.border = "1px solid #AF2C2C";
											document.getElementById("previous_classname").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										}	*/
										else if (classname == ""|| classname == null) {

											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Class Name");
											$("#classname").focus();
											document.getElementById("classname").style.border = "1px solid #AF2C2C";
											document.getElementById("classname").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										} 
										else if (accyear == ""|| accyear == null) {

											$(".errormessagediv").show();
											$(".validateTips").text("Enter  Academic Year");
											$("#mobile_number").focus();
											document.getElementById("accyear").style.border = "1px solid #AF2C2C";
											document.getElementById("accyear").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										} else if (parentId == ""|| parentId == null) {
											$(".errormessagediv").show();
											$(".validateTips").text("Enter  Name");
											$("#parentId").focus();
											document.getElementById("parentId").style.border = "1px solid #AF2C2C";
											document.getElementById("parentId").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										}

										else if (relationship == ""|| relationship == null) {
											$(".errormessagediv").show();
											$(".validateTips").text("Select Relationship");
											$("#relationship").focus();
											document.getElementById("relationship").style.border = "1px solid #AF2C2C";
											document.getElementById("relationship").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										}

										else if (mobile_number == ""|| mobile_number == null) {
											$(".errormessagediv").show();
											$(".validateTips").text("Enter Mobile Number");
											$("#mobile_number").focus();
											document.getElementById("mobile_number").style.border = "1px solid #AF2C2C";
											document.getElementById("mobile_number").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										}

										else if (!mobile_number.match(/[0-9\s]+$/i)) {
											$('.errormessagediv').show();
											$('.validateTips').text("Enter a Valid Phone Number Number");
											$("#mobile_number").focus();
											document.getElementById("mobile_number").style.border = "1px solid #AF2C2C";
											document.getElementById("mobile_number").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										}

										else if (mobile_number.length < 10) {
											$('.errormessagediv').show();
											$('.validateTips').text("Mobile Number Should Not Be Less Than 10 Digits");
											$("#mobile_number").focus();
											document.getElementById("mobile_number").style.border = "1px solid #AF2C2C";
											document.getElementById("mobile_number").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										}

										else if (address == ""|| address == null) {
											$(".errormessagediv").show();
											$(".validateTips").text("Enter Address");
											$("#address").focus();
											document.getElementById("address").style.border = "1px solid #AF2C2C";
											document.getElementById("address").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('#errormessagediv')
																.fadeOut();
													}, 3000);

										}

										else {
											
											
											window.location.href = "registration.html?method=saveparentsubmittingdetailstoschool&dateofBirthId="
												+dateofBirthId
												+"&classname="
												+classname
												+"&group_name="
												+group_name
												+"&accyear="
												+accyear
												+"&parentId="
												+parentId
												+"&mobile_number="
												+mobile_number
												+"&emailId="
												+emailId
												+"&address="
												+address
												+"&relationship="
												+relationship
												+"&alternateMobileNo="
												+alternateMobileNo
												+"&alternateemailId="
												+alternateemailId
												+"&advertisement="
												+advertisement
												+"&school_name="
												+school_name
												+"&percentage="
												+percentage
												+"&previous_classname="
												+previous_classname
												+"&group_name1="
												+group_name1
												+"&paper="
												+paper
												+"&websites="
												+websites
												+"&channels="
												+channels
												+"&others="
												+others
												+"&parents="
												+parents
												+"&studentfirstName="
												+studentfirstName;		
											
										}

									});

					var reg = window.location.href.substr(window.location.href
							.lastIndexOf('&') + 1);
					var splitr = reg.split('=');
					var checker = splitr[0];
					
					if (checker == 'studentfirstName') {
						setTimeout(function() {
							
						window.location.href="http://localhost:9090/ARYA_CENTRAL_SCHOOL/";
					}, 5000);
						
					}

				});

function HideError() {

	document.getElementById("studentfirstName").style.border = "1px solid #ccc";
	document.getElementById("studentfirstName").style.backgroundColor = "#fff";
	document.getElementById("dateofBirthId").style.border = "1px solid #ccc";
	document.getElementById("dateofBirthId").style.backgroundColor = "#fff";
	document.getElementById("accyear").style.border = "1px solid #ccc";
	document.getElementById("accyear").style.backgroundColor = "#fff";
	document.getElementById("classname").style.border = "1px solid #ccc";
	document.getElementById("classname").style.backgroundColor = "#fff";
	document.getElementById("group_name").style.border = "1px solid #ccc";
	document.getElementById("group_name").style.backgroundColor = "#fff";
	document.getElementById("parentId").style.border = "1px solid #ccc";
	document.getElementById("parentId").style.backgroundColor = "#fff";
	document.getElementById("relationship").style.border = "1px solid #ccc";
	document.getElementById("relationship").style.backgroundColor = "#fff";
	document.getElementById("address").style.border = "1px solid #ccc";
	document.getElementById("address").style.backgroundColor = "#fff";
	document.getElementById("mobile_number").style.border = "1px solid #ccc";
	document.getElementById("mobile_number").style.backgroundColor = "#fff";
	document.getElementById("school_name").style.border = "1px solid #ccc";
	document.getElementById("school_name").style.backgroundColor = "#fff";
	document.getElementById("percentage").style.border = "1px solid #ccc";
	document.getElementById("percentage").style.backgroundColor = "#fff";
	document.getElementById("previous_classname").style.border = "1px solid #ccc";
	document.getElementById("previous_classname").style.backgroundColor = "#fff";
	
	
}