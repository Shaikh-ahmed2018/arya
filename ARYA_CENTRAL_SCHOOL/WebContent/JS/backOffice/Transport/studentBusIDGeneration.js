$(document)
		.ready(
				function() {
				

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
										for (var j = 0; j < result.yearDetails.length; j++) {
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
										for (var j = 0; j < result.streamList.length; j++) {

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
												for (var j = 0; j < result.ClassList.length; j++) {

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
												for (var j = 0; j < result.sectionList.length; j++) {

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

					$('#allstudent').delegate(
							"#AllProjectCheckBox",
							"change",
							function() {

								$("input.select").prop('checked',
										$(this).prop("checked"));

							});

					

					
					

					// displaying the student details grid dynamically
					$('#search')
							.click(
									function() {
										// validations logic
										 acadamicyear = $("#acadamicyear");
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
										var classVal=$("#classname0").val();
										// condition true create grid
										// dynamically
										if (bValid) {
											$(".errormessagediv").hide();
													datalist = {
														"acadamicyear" : acadamicyearVal,
														"section" : sectionVal,
														"class":classVal
													},

													$
															.ajax({
																type : 'POST',
																url : "adminMenu.html?method=searchStudent",
																data : datalist,
																success : function(
																		response) {
																	var result = $
																			.parseJSON(response);

																	$('#allstudent')
																			.empty();

																	var tt = document
																			.getElementById("allstudent");// Creating
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
																					"background-color:#f5f5f5");
																	tr1.style.height = "35px";

																	var child = document
																			.createElement('input');
																	child.type = "checkbox";
																	child.id = "AllProjectCheckBox";
																	child.style.width = "35px";
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
																	

																	td3
																			.appendChild(child);
																	td3.align = 'left';
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
																	

																	td4
																			.appendChild(child);
																	td4.align = 'left';
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
																	

																	td5
																			.appendChild(child);
																	td5.align = 'left';
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
																	

																	td7
																			.appendChild(child);
																	td7.align = 'left';
																	br5 = document
																			.createElement("br");
																	td7
																			.appendChild(br5);
																	tr1
																			.appendChild(td7);
						
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
																						"No Records Found.");
																	} else {

																		// iterate
																		// the
																		// student
																		// list
																		// details
																		for (var i = 0; i < result.studentList.length; i++) {

																			$("#allstudent").append("<tr>" +
																					"<td><input type='checkbox' class='select' value="+result.studentList[i].stuId+" /></td>" +
																					"<td>"+result.studentList[i].stuName+"</td>" +
																					"<td>"+result.studentList[i].streamName+"</td>" +
																					"<td>"+result.studentList[i].className+"</td>" +
																					"<td>"+result.studentList[i].section+"</td>" +
																					"</tr>");

																		

																		}
																	$("#AllProjectCheckBox").change(function(){
																		$("input.select").prop("checked", $(this).prop("checked"));
																	})	;	
																	}

																	getyeardetails(".acadamicyear");

																	
																	
																}

															});

										}
										if (!bValid)
											$(".errormessagediv").show();
										return bValid;

									});

					var selectAll = $("#AllProjectCheckBox").val();
					if (selectAll == "on") {
						

						$("input.select").attr('checked', true);

					}

					$('#print')
							.click(
									function() {
										
										var stuIdArray=[];
									$("input.select:checked").each(function(){
										stuIdArray.push($(this).val());
										
									});	
									if(stuIdArray.length>0){
										var jdata={"AccId":$("#acadamicyear").val(),
													"Class":$("#classname0").val(),
													"Section":$("#section").val(),
													"studentname":stuIdArray.toString(),
										};
										 $.ajax({
											 type: 'POST', 
											  url: "adminMenu.html?method=studentBusIdCreationPrint",
									          data:jdata,
									         
											  success: function(response) {
												var result=$.parseJSON(response);
												if(result.studentList.length>0){
														$("#myDialog").dialog("open");
														$("#div").empty();
													for(var i=0;i<result.studentList.length;i++){	
														
													$("#div").append('<section class="col-md-4 sectionb">'
															+'<div class="bus-id-main-div" id="bus-id-main-div" >'
															+'<header>'
															+'<div class="schoolNameb" id="schoolNameb">ARYA CENTRAL SCHOOL</div>'
															+'</header>'
															+'<div class="photob" id="photob">'
															+'<img src="'+result.studentList[i].images+'" />'
															+'</div>'
															+'<div class="namedivb" id="namedivb">'
															+'<span class="nameb" id="nameb">Name :</span><span class="name1">'+result.studentList[i].stuName+'</span>'
															+'</div>'
													
															+'<div class="classDivisionb" id="classDivisionb">'
															+'<span class="label">Class:</span><span>'+result.studentList[i].className+'</span>'
															+'</div>'
															+'<div class="point" id="point">'
															+'<span class="label">Point Name:</span><span>'+result.studentList[i].point_name+'</span>'
													
															+'</div>'
															+'<div class="routeNo" id="routeNo">'
															+'<div class="routetext">Route No</div>'
															+'<span class="route">'+result.studentList[i].route_no+'</span>'
															+'</div>'
															
															+'</section>');
													}
											  }
											  }
											
										});
									}
									else{
										$(
										".errormessagediv")
										.show();
								$(
										".validateTips")
										.text(
												"Please Select Record.");
									}

									});

					$("#myDialog").dialog({
						
						 autoOpen: false,
					     modal: true,
					     title:'Print ID Card',
					     buttons : {
					    	 
					    	 "A4 Size" : function() {
					    		 var a=$("#printing-css").val();
					    			var b = document.getElementById("div").innerHTML;
					    	
					    		    var abd='<style>' + a +'</style>' + b;
					    		
					    		   
					    		    
					    	        var frame1 = $('<iframe />');
					    	        frame1[0].name = "frame1";
					    	        frame1.css({ "position": "absolute", "top": "-1000000px" });
					    	        $("body").append(frame1);
					    	        var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
					    	        frameDoc.document.open();
					    	        //Create a new HTML document.
					    	        frameDoc.document.write('<html><head><title>DIV Contents</title>');
					    	        //Append the external CSS file.
					    	        frameDoc.document.write('<link href="CSS/BusIdCard.css" rel="stylesheet" type="text/css" />');
					    	        frameDoc.document.write('</head><body>');
					    	      
					    	        
					    	        //Append the DIV contents.
					    	        frameDoc.document.write(abd);
					    	        frameDoc.document.write('</body></html>');
					    	        frameDoc.document.close();
					    	        setTimeout(function () {
					    	            window.frames["frame1"].focus();
					    	            window.frames["frame1"].print();
					    	            frame1.remove();
					    	        }, 100);
					    		    
					    		    $(this).dialog("close");
					    	 },
					    	 
					    	 
					    	 "Close" : function() {
						            $(this).dialog("close");
						          }
					     }
						
						
					});
				});
