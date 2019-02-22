$(document)
		.ready(
				function() {
					
					
					$("#searchButtonId").click(function(){
						
						var className=$("#classname0").val();
						var sectionName=$("#section").val();
						
						window.location.href="adminMenu.html?method=studentPromotionSearchList&className="+className+"&sectionName="+sectionName;	
						
						
					});

					
					$("#AllProjectCheckBox").change(function(){
						//var allCheck=$("#AllProjectCheckBox").attr("checked");
						alert("allCheck");
						});

					$(".errormessagediv").hide();
					var acadamicyearid = "acadamicyear";
					getyeardetails("#" + acadamicyearid);
					// getting acadamicyear details
					function getyeardetails(acadamicyear) {

						var acadamicyearid = acadamicyear;
						$
								.ajax({
									type : 'POST',
									url : 'studentPromote.html?method=getAcadamicYear',
									async : false,
									success : function(response) {
										var result = $.parseJSON(response);
										for ( var j = 0; j < result.yearDetails.length; j++) {
											$(acadamicyearid)
													.append(
															'<option value="'
																	+ result.yearDetails[j].acadamicyear_id_int
																	+ '">'
																	+ result.yearDetails[j].acadamicyear_year
																	+ '</option>');
										}
									}
								});

					}
					var category = "category0";
					getCategory("#" + category);

					$('#category0').change(function() {

						var category = "category0";
						var classId = "classname0";
						getClassName("#" + classId, "#" + category);
					});
					// getting strean details
					function getCategory(categoryval) {
						var category = categoryval;
						$
								.ajax({
									url : "classPath.html?method=getStreamDetailAction",
									async : false,

									success : function(data) {

										var result = $.parseJSON(data);
										for ( var j = 0; j < result.streamList.length; j++) {

											$(category)
													.append(
															'<option value="'
																	+ result.streamList[j].streamId
																	+ '">'
																	+ result.streamList[j].streamName
																	+ '</option>');

										}

									}
								});

					}
					// getting class details
					function getClassName(classidval, category) {

						var classid = classidval;
						var categoryVal = $(category).val();

						$('#classname0').change(function() {
							var section = "section";
							var classname = "classname0";
							getClassSection("#" + section, "#" + classname);
						});

						if (classid == "") {
							classid = $(category).parent().next()
									.find('.class').attr('id');
							classid = "#" + classid;
						}

								datalist = {
									"categoryVal" : categoryVal
								},
								$
										.ajax({
											type : 'POST',
											url : "studentRegistration.html?method=getClassDetail",
											data : datalist,
											async : false,
											success : function(response) {
												var result = $
														.parseJSON(response);
												$(classid).html("");
												$(classid)
														.append(
																'<option value="'
																		+ ""
																		+ '">'
																		+ "-----select-----"
																		+ '</option>');
												for ( var j = 0; j < result.ClassList.length; j++) {

													$(classid)
															.append(
																	'<option value="'
																			+ result.ClassList[j].classcode
																			+ '">'
																			+ result.ClassList[j].classname
																			+ '</option>');
												}

											}
										});

					}

					// getting section details
					function getClassSection(sectionid, classname) {
						var sectionidid = sectionid;
						var classidVal = $(classname).val();

						if (sectionidid == "") {
							sectionidid = $(classname).parent().next().find(
									'.section').attr('id');
							sectionidid = "#" + sectionidid;
						}

								datalist = {
									"classidVal" : classidVal
								},
								$
										.ajax({
											type : 'POST',
											url : "studentRegistration.html?method=getClassSection",
											data : datalist,
											async : false,
											success : function(response) {
												var result = $
														.parseJSON(response);
												$(sectionidid).html("");
												$(sectionidid)
														.append(
																'<option value="'
																		+ ""
																		+ '">'
																		+ "-----select-----"
																		+ '</option>');
												for ( var j = 0; j < result.sectionList.length; j++) {

													$(sectionidid)
															.append(
																	'<option value="'
																			+ result.sectionList[j].sectioncode
																			+ '">'
																			+ result.sectionList[j].sectionnaem
																			+ '</option>');
												}

											}
										});

					}

					// for selection of all checkboxes
					
					
					
					
					
					$('#allstudent').delegate("#AllProjectCheckBox","change",function(){
						
						
							
							
						    $(".project_Checkbox_Class").prop('checked', $(this).prop("checked"));

						
						
					
					
					});

					
					$(document)
							.on(
									'click',
									'.pramote',
									function() {

										var tostreamvalues = [];
										var studentidvalues = [];
										var toclassvalues = [];
										var tosectionvalues = [];
										var toacadamicyearvalues = [];
										var pramotionstatus = [];

										var fromstream = $("#category0").val();
										var fromclass = $("#classname0").val();
										var fromacadamicyear = $(
												"#acadamicyear").val();
										var fromsection = $("#section").val();

										// getting the grid values
										$('#allstudent tr')
												.each(
														function() {
															var row = $(this);
															if (row
																	.find(
																			'input[type="checkbox"]')
																	.is(
																			':checked')) {

																studentidvalues
																		.push(row
																				.find(
																						'[name="studentid"]')
																				.val());
																tostreamvalues
																		.push(row
																				.find(
																						'[name="stream"]')
																				.val());
																toclassvalues
																		.push(row
																				.find(
																						'[name="class"]')
																				.val());
																tosectionvalues
																		.push(row.find('[name="sectionname"]').val());
																toacadamicyearvalues
																		.push(row.find('[name="acadamicyear"]')
																				.val());
																pramotionstatus
																		.push(row
																				.find(
																						'[name="pramotionstatus"]')
																				.val());

															}
														});

										if (studentidvalues.length == 0) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Check Box");
											setTimeout(
													"jQuery('.errormessagediv').hide();",
													5000);
										} else if (toclassvalues.length < 0) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Class");
											setTimeout(
													"jQuery('.errormessagediv').hide();",
													5000);
										} else if (tosectionvalues.length == 0) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Section");
											setTimeout(
													"jQuery('.errormessagediv').hide();",
													5000);
										} else {

											for ( var k = 0; k < studentidvalues.length; k++) {

												if (tostreamvalues[k] == '') {

													$(".errormessagediv")
															.show();
													$(".validateTips").text(
															"Select Steram");
													setTimeout(
															"jQuery('.errormessagediv').hide();",
															5000);

													return false;
												} else if (tostreamvalues[k] == '') {

													$(".errormessagediv")
															.show();
													$(".validateTips").text(
															"Select Class");
													setTimeout(
															"jQuery('.errormessagediv').hide();",
															5000);

													return false;
												} else if (tosectionvalues[k] == '') {

													$(".errormessagediv")
															.show();
													$(".validateTips").text(
															"Select Section");
													setTimeout(
															"jQuery('.errormessagediv').hide();",
															5000);

													return false;
												}
											}

											var x = confirm("Are You Sure you want to promote selected Students?");
											if (x) {

														datalist = {

															"studentidvalues" : studentidvalues,
															"fromstream" : fromstream,
															"tostreamvalues" : tostreamvalues,
															"fromclass" : fromclass,
															"fromacadamicyear" : fromacadamicyear,
															"fromsection" : fromsection,
															"toclassvalues" : toclassvalues,
															"tosectionvalues" : tosectionvalues,
															"toacadamicyearvalues" : toacadamicyearvalues,
															"pramotionstatus" : pramotionstatus

														},

														$
																.ajax({
																	type : 'GET',
																	url : 'studentPromote.html?method=insertStudentPromotion',
																	data : datalist,
																	success : function(
																			response) {
																		var result = $.parseJSON(response);


																		window.location.href = "adminMenu.html?method=studPromotion&notptomotedStudentList="
																				+ result.RESULT;

																	}

																});
											}
										}
									});

					// displaying the student details grid dynamically
					$('#search')
							.click(
									function() {
										// validations logic
										var acadamicyear = $("#acadamicyear");
										category = $("#category0"),
												classname = $("#classname0"),
												sectionname = $("#section"),

												allFields = $([]).add(
														acadamicyear).add(
														category)
														.add(classname).add(
																sectionname),
												tips = $(".validateTips");

										var bValid = true;

										bValid = bValid
												&& selectBoxElement(
														acadamicyear,
														"Select Acadamic Year");
										bValid = bValid
												&& selectBoxElement(category,
														"Select Stream");
										bValid = bValid
												&& selectBoxElement(classname,
														"Select Class");
										bValid = bValid
												&& selectBoxElement(
														sectionname,
														"Select Section");

										var acadamicyearVal = $("#acadamicyear")
												.val();
										var sectionVal = $("#section").val();

										// condition true create grid
										// dynamically
										if (bValid) {
											$(".errormessagediv").hide();
													datalist = {
														"acadamicyear" : acadamicyearVal,
														"section" : sectionVal
													},

													$
															.ajax({
																type : 'POST',
																url : "studentPromote.html?method=searchStudent",
																data : datalist,
																success : function(
																		response) {
																	var result = $
																			.parseJSON(response);

																	$('#allstudent').empty();

																	var tt = document.getElementById("allstudent");// Creating
																	// Table
																	// Tag
																	var tr1 = document
																			.createElement("tr"); // Creating
																	// TR
																	// Tag
																	// for
																	// header
																	var td1 = document
																			.createElement("td"); // Creating
																	// First
																	// TD

																	tr1
																			.setAttribute(
																					"style",
																					"background-color:#C8C8C8");
																	tr1.style.height = "35px";

																	var child = document
																			.createElement('input');
																	child.type = "checkbox";
																	child.id = "AllProjectCheckBox";

																	td1
																			.appendChild(child);

																	tr1
																			.appendChild(td1);

																	var td3 = document
																			.createElement("td"); // Creating
																	// Third
																	// TD
																	var child = document
																			.createElement('label');
																	child.innerHTML = "Student Name";
																	child.style.color = "#000";
																	child.style.fontFamily = "Segoe UI";
																	child.style.width = "138px";

																	td3
																			.appendChild(child);
																	td3.align = 'center';
																	br2 = document
																			.createElement("br");
																	td3
																			.appendChild(br2);
																	tr1
																			.appendChild(td3);

																	var td4 = document
																			.createElement("td"); // Creating
																	// Fourth
																	// TD
																	var child = document
																			.createElement('label');
																	child.innerHTML = "Stream";
																	child.class = "LblDialog";

																	child.style.color = "#000";

																	child.style.fontFamily = "Segoe UI";
																	child.style.width = "138px";

																	td4
																			.appendChild(child);
																	td4.align = 'center';
																	br3 = document
																			.createElement("br");
																	td4
																			.appendChild(br3);
																	tr1
																			.appendChild(td4);

																	var td5 = document
																			.createElement("td"); // Creating
																	// Fifth
																	// TD
																	var child = document
																			.createElement('label');
																	child.innerHTML = "Class";
																	child.class = "LblDialog";

																	child.style.color = "#000";

																	child.style.fontFamily = "Segoe UI";
																	child.style.width = "138px";

																	td5
																			.appendChild(child);
																	td5.align = 'center';
																	br4 = document
																			.createElement("br");
																	td5
																			.appendChild(br4);
																	tr1
																			.appendChild(td5);

																	var td7 = document
																			.createElement("td"); // Creating
																	// Sixth
																	// TD
																	var child = document
																			.createElement('label');
																	child.innerHTML = "Section";
																	child.class = "LblDialog";

																	child.style.color = "#000";

																	child.style.fontFamily = "Segoe UI";
																	child.style.width = "138px";

																	td7
																			.appendChild(child);
																	td7.align = 'center';
																	br5 = document
																			.createElement("br");
																	td7
																			.appendChild(br5);
																	tr1
																			.appendChild(td7);

																	var td8 = document
																			.createElement("td"); // Creating
																	// Sixth
																	// TD
																	var child = document
																			.createElement('label');
																	child.innerHTML = "Acadamic Year";
																	child.class = "LblDialog";

																	child.style.color = "#000";

																	child.style.fontFamily = "Segoe UI";
																	child.style.width = "138px";

																	td8
																			.appendChild(child);
																	td8.align = 'center';
																	br6 = document
																			.createElement("br");
																	td8
																			.appendChild(br6);
																	tr1
																			.appendChild(td8);

																	var td10 = document
																			.createElement("td"); // Creating
																	// Sixth
																	// TD
																	var child = document
																			.createElement('label');
																	child.innerHTML = "Promation Status";
																	child.class = "LblDialog";

																	child.style.color = "#000";

																	child.style.fontFamily = "Segoe UI";
																	child.style.width = "138px";
																	td10
																			.setAttribute(
																					"class",
																					"promotionLable");
																	td10
																			.appendChild(child);
																	td10.align = 'center';
																	br8 = document
																			.createElement("br");

																	td10
																			.appendChild(br8);
																	tr1
																			.appendChild(td10);
																	tt
																			.appendChild(tr1);// appended
																	// first
																	// row
																	// to
																	// table
																	 if (result.studentList.length == 0) {

																			$(
																					".errormessagediv")
																					.show();
																			$(
																					".validateTips")
																					.text(
																							"Students not available for selected Details");
																		}
																	 else if (result.studentList[0].lsitStatus == 'NoAccYear') {

																		$(
																				".errormessagediv")
																				.show();
																		$(
																				".validateTips")
																				.text(
																						"Next Accadamic year not created");

																	} /*else if (result.studentList.length == 0) {

																		$(
																				".errormessagediv")
																				.show();
																		$(
																				".validateTips")
																				.text(
																						"Students not available for selected Details");
																	}*/ else {

																		// iterate
																		// the
																		// student
																		// list
																		// details
																		for ( var i = 0; i < result.studentList.length; i++) {

																			var tr2 = document
																					.createElement("tr"); // Creating
																			// Second
																			// TR
																			// Tag
																			var td_check = document
																					.createElement("td"); // Creating
																			// First
																			// TD
																			// for
																			// value
																			var td_stname = document
																					.createElement("td");
																			var td_stream = document
																					.createElement("td");
																			var td_class = document
																					.createElement("td");
																			var td_section = document
																					.createElement("td");
																			var td_acadamicyear = document
																					.createElement("td");
																			var td_pramtestatus = document
																					.createElement("td");

																			var addtxt = document
																					.createElement("input");

																			addtxt.type = "checkbox";
																			addtxt.name = "studentid";
																			addtxt.id = "studentid"
																					+ i;
																			addtxt
																					.setAttribute(
																							"class",
																							"studentid");
																			addtxt.value = result.studentList[i].studentid;
																			addtxt
																					.setAttribute(
																							"class",
																							"project_Checkbox_Class");

																			td_check
																					.appendChild(addtxt);
																			tr2
																					.appendChild(td_check);

																			var addtxt = document
																					.createElement("span");
																			// br=document.createElement("br");
																			addtxt.name = "stuname";
																			addtxt.id = "stname";
																			addtxt
																					.setAttribute(
																							"class",
																							"form-control");
																			+i;
																			addtxt
																					.setAttribute(
																							"value",
																							result.studentList[i].stname);
																			addtxt.innerText = result.studentList[i].stname;

																			/*
																			 * addtxt.style.color =
																			 * "black";
																			 * addtxt.style.padding =
																			 * "3px";
																			 * addtxt.style.fontFamily =
																			 * "Segoe
																			 * UI";
																			 * addtxt.style.width =
																			 * "70px";
																			 */
																			td_stname
																					.appendChild(addtxt);
																			tr2
																					.appendChild(td_stname);

																			var addtxt2 = document
																					.createElement("select");

																			addtxt2.name = "stream";
																			addtxt2.id = "stream"
																					+ i;
																			// addtxt2.class="stream"+i;
																			addtxt2
																					.setAttribute(
																							"class",
																							"form-control stream");
																			/*
																			 * var
																			 * ov=document.createElement("option");
																			 * ov.setAttribute("value",
																			 * result.studentList[i].categoryname);
																			 * ov.setAttribute("value",
																			 * result.studentList[i].categoryid);
																			 * ov.innerText=result.studentList[i].categoryname;
																			 * addtxt2.appendChild(ov);
																			 */

																			/*
																			 * addtxt2.style.color =
																			 * "black";
																			 * addtxt2.style.padding =
																			 * "3px";
																			 * addtxt2.style.fontFamily =
																			 * "Segoe
																			 * UI";
																			 * addtxt2.style.width =
																			 * "140px";
																			 */

																			td_stream
																					.appendChild(addtxt2);
																			tr2
																					.appendChild(td_stream);

																			var addtxt3 = document
																					.createElement("select");

																			addtxt3.name = "class";
																			addtxt3.id = "class"
																					+ i;
																			addtxt3
																					.setAttribute(
																							"class",
																							"form-control class");

																			var ov1 = document
																					.createElement("option");
																			ov1
																					.setAttribute(
																							"value",
																							result.studentList[i].classname);
																			ov1
																					.setAttribute(
																							"value",
																							result.studentList[i].classid);
																			ov1.innerText = result.studentList[i].classname;
																			addtxt3
																					.appendChild(ov1);

																			/*addtxt3.style.color = "black";
																			addtxt3.style.padding = "3px";
																			addtxt3.style.fontFamily = "Segoe UI";
																			addtxt3.style.width = "140px";*/

																			td_class
																					.appendChild(addtxt3);
																			tr2
																					.appendChild(td_class);

																			var addtxt4 = document
																					.createElement("select");

																			addtxt4.name = "sectionname";
																			addtxt4.id = "section"
																					+ i;
																			addtxt4
																					.setAttribute(
																							"class",
																							"form-control section");

																			var ov2 = document
																					.createElement("option");
																			ov2
																					.setAttribute(
																							"value",
																							result.studentList[i].sectionname);
																			ov2
																					.setAttribute(
																							"value",
																							result.studentList[i].sectionid);
																			ov2.innerText = result.studentList[i].sectionname;
																			addtxt4
																					.appendChild(ov2);

																			/*addtxt4.style.color = "black";
																			addtxt4.style.padding = "3px";
																			addtxt4.style.fontFamily = "Segoe UI";
																			addtxt4.style.width = "140px";*/

																			td_section
																					.appendChild(addtxt4);
																			tr2
																					.appendChild(td_section);

																			var addtxt5 = document
																					.createElement("select");

																			addtxt5.name = "acadamicyear";
																			addtxt5.id = "acadamicyear"
																					+ i;
																			addtxt5
																					.setAttribute(
																							"class",
																							"form-control acadamicyear");

																			var ov3 = document
																					.createElement("option");
																			ov3
																					.setAttribute(
																							"value",
																							result.studentList[i].acadamicyear);
																			ov3
																					.setAttribute(
																							"value",
																							result.studentList[i].acadamicyearid);
																			ov3.innerText = result.studentList[i].acadamicyear;
																			addtxt5
																					.appendChild(ov3);

																			/*addtxt5.style.color = "black";
																			addtxt5.style.padding = "3px";
																			addtxt5.style.fontFamily = "Segoe UI";
																			addtxt5.style.width = "140px";*/

																			td_acadamicyear
																					.appendChild(addtxt5);
																			tr2
																					.appendChild(td_acadamicyear);

																			var addtxt7 = document
																					.createElement("select");

																			addtxt7.name = "pramotionstatus";
																			addtxt7.id = "pramotionstatus"
																					+ i;
																			addtxt7
																					.setAttribute(
																							"class",
																							"promotionstatus");

																			var ov4 = document
																					.createElement("option");
																			ov4
																					.setAttribute(
																							"value",
																							result.studentList[i].pramotionstatus);
																			ov4.innerText = result.studentList[i].pramotionstatus;
																			addtxt7
																					.appendChild(ov4);

																			var option = new Option(
																					"---select---",
																					"");
																			var option0 = new Option(
																					"Promoted",
																					"Promoted");
																			var option1 = new Option(
																					"Demoted",
																					"Demoted");
																			var option2 = new Option(
																					"Fail",
																					"Fail");

																			addtxt7.options[addtxt7.options.length] = option;
																			addtxt7.options[addtxt7.options.length] = option0;
																			addtxt7.options[addtxt7.options.length] = option1;
																			addtxt7.options[addtxt7.options.length] = option2;

																			addtxt7.style.color = "black";
																			addtxt7.style.padding = "3px";
																			addtxt7.style.fontFamily = "Segoe UI";
																			addtxt7.style.width = "140px";

																			td_pramtestatus
																					.appendChild(addtxt7);
																			tr2
																					.appendChild(td_pramtestatus);
																			tt
																					.appendChild(tr2);// appended
																			// second
																			// row
																			// to
																			// table

																			getCategory("#stream"
																					+ i);

																			$(
																					'.stream')
																					.change(
																							function() {
																								getClassName(
																										"",
																										this);

																							});
																			$(
																					'.class')
																					.change(
																							function() {
																								getClassSection(
																										"",
																										this);

																							});

																			$(
																					".promotionstatus")
																					.hide();
																			$(
																					".promotionLable")
																					.hide();

																			$(
																					"#"
																							+ "acadamicyear"
																							+ i)
																					.attr(
																							"disabled",
																							true);

																			$(
																					"#"
																							+ "stream"
																							+ i
																							+ "  option[value="
																							+ result.studentList[i].categoryid
																							+ "]")
																					.attr(
																							'selected',
																							'true');

																					datalist = {
																						"categoryVal" : result.studentList[i].categoryid
																					},
																					$
																							.ajax({
																								type : 'POST',
																								url : "childinfo.html?method=getClassDetail",
																								data : datalist,
																								async : false,
																								success : function(
																										response) {
																									var result = $
																											.parseJSON(response);

																									$(
																											"#"
																													+ "class"
																													+ i)
																											.html(
																													"");

																									$(
																											"#"
																													+ "class"
																													+ i)
																											.append(
																													'<option value="'
																															+ ""
																															+ '">'
																															+ "-----select-----"
																															+ '</option>');
																									for ( var j = 0; j < result.parentVOList.length; j++) {

																										$(
																												"#"
																														+ "class"
																														+ i)
																												.append(
																														'<option value="'
																																+ result.parentVOList[j].classDetailId
																																+ '">'
																																+ result.parentVOList[j].classDetailsName
																																+ '</option>');
																									}

																								}
																							});

																			$(
																					"#"
																							+ "class"
																							+ i
																							+ "  option[value="
																							+ result.studentList[i].classid
																							+ "]")
																					.attr(
																							'selected',
																							'true');

																					datalist = {
																						"classidVal" : result.studentList[i].classid
																					},

																					$
																							.ajax({
																								type : 'POST',
																								url : "childinfo.html?method=getClassSection",
																								data : datalist,
																								async : false,
																								success : function(
																										response) {
																									var result = $
																											.parseJSON(response);
																									$(
																											"#"
																													+ "section"
																													+ i)
																											.html(
																													"");
																									$(
																											"#"
																													+ "section"
																													+ i)
																											.append(
																													'<option value="'
																															+ ""
																															+ '">'
																															+ "-----select-----"
																															+ '</option>');

																									for ( var j = 0; j < result.parentVOList.length; j++) {

																										$(
																												"#"
																														+ "section"
																														+ i)
																												.append(
																														'<option value="'
																																+ result.parentVOList[j].classSectionId
																																+ '">'
																																+ result.parentVOList[j].classSectioName
																																+ '</option>');
																									}

																								}
																							});
																			$(
																					"#"
																							+ "section"
																							+ i
																							+ "  option[value="
																							+ result.studentList[i].sectionid
																							+ "]")
																					.attr(
																							'selected',
																							'true');

																		}

																	}

																	getyeardetails(".acadamicyear");

																	var pramoteBtn_tr1 = document
																			.createElement("tr"); // Creating
																	// TR
																	// Tag
																	// for
																	// header
																	var pramoteBtn_td1 = document
																			.createElement("td"); // Creating
																	// First
																	// TD
																	var br = document
																			.createElement("br");

																	tt
																			.appendChild(br);

																	pramoteBtn_td1
																			.setAttribute(
																					"colspan",
																					"7");
																	pramoteBtn_td1
																			.setAttribute(
																					"align",
																					"center");

																	var pramoteBtn = document
																			.createElement("input");
																	pramoteBtn
																			.setAttribute(
																					"type",
																					"button");
																	pramoteBtn
																			.setAttribute(
																					"value",
																					"Promote");
																	pramoteBtn
																			.setAttribute(
																					"name",
																					"pramote");
																	pramoteBtn
																			.setAttribute(
																					"class",
																					"pramote btn btn-success");

																	/*
																	 * pramoteBtn.style.background="#000000";
																	 * pramoteBtn.style.padding="5px";
																	 * pramoteBtn.style.border_style="hidden";
																	 * pramoteBtn.style.color="white";
																	 * pramoteBtn.style.align="center";
																	 * pramoteBtn.style.border_radius="6px";
																	 */

																	pramoteBtn_td1
																			.appendChild(pramoteBtn);
																	pramoteBtn_tr1
																			.appendChild(pramoteBtn_td1);
																	tt
																			.appendChild(pramoteBtn_tr1);
																}

															});
												
													
																					
													
													
													
										}
										if (!bValid)
											$(".errormessagediv").show();
										return bValid;
									
									
									
									});
					
					
					
					
					var selectAll=$("#AllProjectCheckBox").val();
					if(selectAll =="on") {
						alert("hello");
							
								
									$("input[name='studentid']").attr('checked', true);

							}
					
					
					
					$('#excelDownload')
					.click(
							function() {
								
								window.location.href = 'studentPromote.html?method=downloadStudentDetailsXLS';
								
							});
					$("#pdfDownload").click(function(){
            			
            			window.location.href = "studentPromote.html?method=downloadStudentDetailsPDF"
            				
            		});
							
					
				});
