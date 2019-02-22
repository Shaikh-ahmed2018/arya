function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction() {
    
    document.getElementById("myForm").submit();   
 }

function myFunction2() {
	var x = document.getElementById("routeLogicName").value;
	if(!x.match(/[A-Za-z]+$/i)){
		$('.errormessagediv1').show();
		$('.validateTips1').text("Route Short Name Should be Alphabet");

		document.getElementById("routeLogicName").style.border = "1px solid #AF2C2C";
		document.getElementById("routeLogicName").style.backgroundColor = "#FFF7F7";
		$('#routeLogicName').val('');
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
	}

}



function myFunction3() {
	var x1 = document.getElementById("totalDistance").value;
	if(!x1.match(/^\d+(\.\d+)?$/)){
		$('.errormessagediv1').show();
		$('.validateTips1').text("Total Distance Should be number");

		document.getElementById("totalDistance").style.border = "1px solid #AF2C2C";
		document.getElementById("totalDistance").style.backgroundColor = "#FFF7F7";
		$('#totalDistance').val('');
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
	}

}


function myFunction4() {
	var x1 = document.getElementById("halttime").value;
	if(!x1.match(/^[0-9]+$/)){
		$('.errormessagediv1').show();
		$('.validateTips1').text("Halt time Should be number");

		document.getElementById("halttime").style.border = "1px solid #AF2C2C";
		document.getElementById("halttime").style.backgroundColor = "#FFF7F7";
		$('#halttime').val('');
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
	}

}
 function haltName1(){
	 var x2 = document.getElementById("halttime").value;
	 if(!x2.match(/^[0-9]+$/)){
			$('.errormessagediv1').show();
			$('.validateTips1').text("Halt time Should be number");
			document.getElementById("halttime").style.border = "1px solid #AF2C2C";
			document.getElementById("halttime").style.backgroundColor = "#FFF7F7";
			$('#halttime').val('');
			setTimeout(function() {
				$('.errormessagediv1').fadeOut();
			}, 3000);
		} else if(!x2 < 0 || x2 > 59){
			
			$('.errormessagediv1').show();
			$('.validateTips1').text("Halt time Should be between 0 to 59");

			document.getElementById("halttime").style.border = "1px solid #AF2C2C";
			document.getElementById("halttime").style.backgroundColor = "#FFF7F7";
			$('#halttime').val('');
			setTimeout(function() {
				$('.errormessagediv1').fadeOut();
			}, 3000);
		}
	 
 }

var   stagesidArray = [];
$(document)
		.ready(
				function() {
					$("#totalStops").val($("#myTable tbody tr").length);
					
					$("#Acyearlist").val($("#haccyearstage").val());
					$("#Acyearid").val($("#hacademicyaer").val());
					if($("#allstudent tbody tr").length ==0){
						$("#allstudent tbody").append("<tr><td colspan='8'>NO Records Found</td></tr>");
					}
					$("#selectall").change(function(){

						 $(".select").prop('checked', $(this).prop("checked"));
						 
						});
					
					
					$("#route").click(function(){
						$("#routeclose").slideToggle();
					});
					
					
					$(".selectAll").change(function(){
					
						$(".selectBox").prop("checked", $(this).prop("checked"));
					});
			var pageurl=window.location.href.substring(window.location.href.lastIndexOf("=")+1);
			if(pageurl!="addRouteScreen"){
				$("#myTable").attr("style","display:table");
				$("#myTable tbody tr").each(function(){
					$("#tableid .selectBox[value="+$(this).attr("id")+"]").attr("checked",true);
					
				});
			}
					/*$('.errormessagediv1').hide();
					$('.successmessagediv').hide();*/
					
					
					
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv1").hide();
					}, 3000);
					
					
					
					$('#excelDownload')
					.click(
							function() {
								
								window.location.href = 'transport.html?method=RouteDetailsXLS';
								
							});
					
					$("#pdfDownload").click(function(){
						
						window.location.href = "transport.html?method=RouteDetailsPDFReport";
							
					});	


					$("#plus").click(
									function() {
										window.location.href = "transport.html?method=addRouteScreen&accyear="+$("#Acyearlist").val();
									});
					
					
					
					$("#editDesignationId").click(
							function() {

								var cnt = 0;
								$('input[type="checkbox"]:checked')
										.map(
													function() {
													
													
													getData = $(this).attr("id");
													
													cnt++;
												});
								
								
													
								if (cnt == 0 || cnt > 1) {
									
				     				$(".errormessagediv1").show();
				     				$(".validateTips1").text("Select any one Route to Edit");
				     				
				     				return false;
				     			}  else {

				     				var routeIdlist=getData;
				     				
									window.location.href = "transport.html?method=editRouteMasterDetails&routeIdlist="+routeIdlist.trim()+"&accyear="+$("#Acyearlist").val();
				     				
								}

							});
					
					
					$('#trash').click(function() {

								var count =0;
								routeIdlist=[];
								$(".select:checked").each(function(){

									var list=$(this).attr("id");
									routeIdlist.push(list);
									count ++;
								});	
								
								if(count == 0)	{
							 		
							 		$('.errormessagediv1').show();
									$('.validateTips1').text("Select Any Record to Delete");
									$('.errormessagediv1').delay(3000).slideUp();
							 		
							 	}
							 	else 	{
									
									  $("#dialog").dialog("open");
									  $("#dialog").empty();
									  $("#dialog").append("<p>Are you sure to delete?</p>");
								}
								
							});
					
					
							
								
					$("#savestops").click(
									function() {

										var allstudent = 1;
										var stopNoArray = [];
										var stopArrivalTimeArray = [];
										var stopDepartureTimeArray = [];
										var stopNameArray = [];
										var stopHaltTimeArray = [];
										var stopDistanceArray = [];
										var status = true;

										var regexpforalphabets = /^[a-zA-Z ]+$/i;
										var regexpforInteger = /^([0-9])+$/;

										$('table#allstudent tr:gt(0)')
												.each(
														function() {

															var stopNo = $(this)
																	.find(
																			'[name=stopNo]')
																	.val()
																	.trim();
															var stop_Name = $(
																	this)
																	.find(
																			'[name=stopName]')
																	.val()
																	.trim();
															var stop_arrTime = $(
																	this)
																	.find(
																			'[name=arrTime]')
																	.val()
																	.trim();
															var stop_haltTime = $(
																	this)
																	.find(
																			'[name=haltTime]')
																	.val()
																	.trim();
															var stop_deptTime = $(
																	this)
																	.find(
																			'[name=depTime]')
																	.val()
																	.trim();
															var stop_distance = $(
																	this)
																	.find(
																			'[name=distance]')
																	.val()
																	.trim();

															if (stopNo != undefined
																	&& stopNo != '') {
																stopNoArray
																		.push(stopNo);
																status = true;
															}

															if (stop_Name == ""
																	|| stop_Name == null) {

																$(
																		'.errormessagediv1')
																		.show();
																$(
																		'.validateTips1')
																		.text(
																				"Stop Name field is not entered for "
																						+ stopNo);
																status = false;
																return false;
															} else if (stop_Name != undefined
																	&& stop_Name != '') {
																stopNameArray
																		.push(stop_Name);
																status = true;
															}

															if (stop_arrTime == ''
																	|| stop_arrTime == null) {

																$(
																		'.errormessagediv1')
																		.show();
																$(
																		'.validateTips1')
																		.text(
																				"Arrival Time field is not selected for "
																						+ stopNo);
																status = false;
																return false;
															} else if (stop_arrTime != undefined
																	&& stop_arrTime != '') {
																stopArrivalTimeArray
																		.push(stop_arrTime);
																status = true;
															}

															if (stop_haltTime == ''
																	|| stop_haltTime == null) {

																$(
																		'.errormessagediv1')
																		.show();
																$(
																		'.validateTips1')
																		.text(
																				"Halt Time field is not entered for "
																						+ stopNo);
																status = false;
																return false;
															} else if (!(regexpforInteger
																	.test(stop_haltTime
																			.trim()))) {

																$(
																		'.errormessagediv1')
																		.show();
																$(
																		'.validateTips1')
																		.text(
																				"Halt Time field allows Number only for "
																						+ stopNo);
																status = false;
																return false;
															} else if (stop_haltTime != undefined
																	&& stop_haltTime != '') {
																stopHaltTimeArray
																		.push(stop_haltTime);
																status = true;
															}

															if (stop_deptTime == ''
																	|| stop_deptTime == null) {

																$(
																		'.errormessagediv1')
																		.show();
																$(
																		'.validateTips1')
																		.text(
																				"Departure Time field is not selected for "
																						+ stopNo);
																status = false;
																return false;
															} else if (stop_deptTime != undefined
																	&& stop_deptTime != '') {
																stopDepartureTimeArray
																		.push(stop_deptTime);
																status = true;
															}

															if (stop_distance == ''
																	|| stop_distance == null) {

																('.errormessagediv1')
																		.show();
																$(
																		'.validateTips1')
																		.text(
																				"Distance field is not entered for "
																						+ stopNo);
																status = false;
																return false;
															} else if (!(regexpforInteger
																	.test(stop_distance
																			.trim()))) {

																('.errormessagediv1')
																		.show();
																$(
																		'.validateTips1')
																		.text(
																				"Distance field allows Number only for "
																						+ stopNo);
																status = false;
																return false;
															} else if (stop_distance != undefined
																	&& stop_distance != '') {
																stopDistanceArray
																		.push(stop_distance);
																status = true;
															}
															allstudent++;
														});
										if (status == true) {

											var answer = confirm("Are you sure to Add Route Details");
											if (answer) {

												$('#stopNoArray').val(
														stopNoArray);
												$('#stopArrivalTimeArray').val(
														stopArrivalTimeArray);
												$('#stopNameArray').val(
														stopNameArray);
												$('#stopHaltTimeArray').val(
														stopHaltTimeArray);
												$('#stopDistanceArray').val(
														stopDistanceArray);
												$('#stopDepartureTimeArray')
														.val(
																stopDepartureTimeArray);

												document.getElementById(
														"formRoute").submit();

										
											}
										}

									});

					$("#list")
							.click(
									function() {
										window.location.href = "adminMenu.html?method=routeMasterSettings";
									});

					$("#searchid")
							.click(
									function() {
										var searchid = $("#search").val()
												.trim();
										window.location.href = "adminMenu.html?method=routeMasterSettings&searhval="
												+ searchid;

									});
										
										$("#dialog").dialog({
									 		
									 		 autoOpen: false,
										     modal: true,					    
										     title:'Route Details',
										     buttons : {
										    	 "Yes" : function() {
														
														$.ajax({
															type : 'POST',
															url : "transport.html?method=removeRouteMasterDetails",

															data :{"routeIdlist" :routeIdlist.toString()},
															
															success : function(response){
																
																var result = $.parseJSON(response);
																$('.errormessagediv').hide();
																
																if(result.status=="Route Deleted SuccessFully"){
																	
																	$(".successmessagediv").show();
																	$(".successmessage").text("Deleting Record Progressing...");
																}
																
																else if(result.status=="Route not Deleted SuccessFully"){
																	$('.errormessagediv1').show();
																	$('.validateTips1').text("Selected Route is Mapped Cannot Delete");
																}
																	
																	setTimeout(function(){
																		   
																		 window.location.href="adminMenu.html?method=routeMasterSettings";
																	 
																	 },2000);
															}

														
															
														});
														

												 		 $(this).dialog("close");
													 	  
										          },
									 		
										          "No" : function() {
											            $(this).dialog("close");
											          }
									 		
										     }
									 	});
										
										
										
								
					$("#myDialog").dialog({
					    autoOpen  : false,
					    modal     : true,
					    title     : "Stages",
					    buttons   : {
					              'OK' : function() {
					            	  $("#myTable tbody").empty();
					               
					                  var stagesNameArray=[];
					                  $('.selectBox:checked').each(function(){
					                	  var check=$(this).val();
					                	  stagesidArray.push(check);
					                	  stagesNameArray.push($(this).parent('td').next().text());
					                	 
					                	 
					                  });
					                 
					               $("#totalStops").val(stagesNameArray.length);
					                  $("#myTable").attr("style","display:table");   	
					                 
					      			for(var i=0;i<stagesNameArray.length;i++){
					      			$("#myTable tbody").append("<tr><td>"+(i+1)+"</td><td>"+ stagesNameArray[i]+"</td></tr>")	
					      			}
					      			 $(this).dialog('close');	
					              },
					              'Close' : function() {
					           
					                  $(this).dialog('close');
					              }
					                }
					});

					//Open the dialog box when the button is clicked.
					$('#checklocations').click(function() {
						  $("#myDialog").dialog("open");
					});
					

					$("#save").click(function() {
										if(stagesidArray.length==0){
											$("#myTable tbody tr").each(function(){
												stagesidArray.push($(this).attr("id"));	
											});	
										}
										var routeName = $('#routeName').val().trim();
										var routeNo = $('#routeNo').val().trim();
										var routeLogicName = $('#routeLogicName').val();
										var totalStops = $('#totalStops').val().trim();
										var startTime = $('#starttime').val().trim();
												
										var endTime = $('#endtime').val().trim();

										var totalDistance = $('#totalDistance').val().trim();

										var halttime = $('#halttime').val().trim();

										var routeid = $('#routeid').val().trim();
										
										if (!checkTime()) {
											$('.errormessagediv1').show();
											$('.validateTips1').text("Start time should be less then End time");
											return false;
										}
										if (routeNo == "" || routeNo == null) {
											$('.errormessagediv1').show();
											$('.validateTips1').text("Field Required Route No");
											document.getElementById("routeNo").style.border = "1px solid #AF2C2C";
											document.getElementById("routeNo").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											
											return false;
										} 
										
										else if (checkRouteNo()) {
											$('.errormessagediv1').show();
											$('.validateTips1').text("Route No already Exist");
											document.getElementById("routeNo").style.border = "1px solid #AF2C2C";
											document.getElementById("routeNo").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										} else if (routeName == "" || routeName == null) {
											$('.errormessagediv1').show();
											$('.validateTips1').text("Field Required Route Name");
											document.getElementById("routeName").style.border = "1px solid #AF2C2C";
											document.getElementById("routeName").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											
											return false;
										} else if (startTime == ""|| startTime == null) {
											$('.errormessagediv1').show();
											$('.validateTips1').text(
													"Select Start Time");
											document.getElementById("starttime").style.border = "1px solid #AF2C2C";
											document.getElementById("starttime").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										} else if (endTime == "" || endTime == null) {
											$('.errormessagediv1').show();
											$('.validateTips1').text(
													"Select End Time");
											document.getElementById("endtime").style.border = "1px solid #AF2C2C";
											document.getElementById("endtime").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}  else if (stagesidArray == ""
												|| stagesidArray == null) {
											$('.errormessagediv1').show();
											$('.validateTips1').text(
													"Select stage name");
											document.getElementById("stagesidArray").style.border = "1px solid #AF2C2C";
											document.getElementById("stagesidArray").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										} else {
											if (routeid == "" || routeid == null) {
												routeid = "NULL";
											}
											var Check = {
												"routeName" : routeName,
												"routeNo" : routeNo,
												"routeLogicName" : routeLogicName,
											"totalStops" : totalStops,
												"startTime" : startTime,
												"endTime" : endTime,
											"totalDistance" : totalDistance,
												"routeid" : routeid,
												"stagesidArray":stagesidArray.toString(),
												"haltTime": halttime,
												"hRouteCode":$("#hRouteCode").val(),
												"accyear":$("#haccyear").val()

											};
											
										
											
											$.ajax({
												type : "POST",
												url : "transport.html?method=insertRouteMasterDetails",
												data : Check,
												async : false,
												success : function(data) {
													var result = $
													.parseJSON(data);
													if (result.status == "success") {
														
														$('.successmessagediv').show();
														
														$('.successmessage').text("Adding Record Progressing...");
														
														setTimeout(
																function() {

																	window.location.href = "adminMenu.html?method=routeMasterSettings";

																}, 3000);
														
														
														
													}
													else if (result.status == "faild") {
														
														$('.errormessagediv1').show();
														
														$('.validateTips1').text("Route Insertion failed");
														
														setTimeout(function() {

																	window.location.href = "transport.html?method=addRouteScreen";

																}, 3000);
													}
													else if(result.status == "success1") {
															
															$('.successmessagediv').show();
															
															$('.successmessage').text("Updating Record Progressing...");
															
															setTimeout(
																	function() {

																		window.location.href = "adminMenu.html?method=routeMasterSettings";

																	}, 3000);
															
															
														}
												}
											});

										}
									});
					
					
					$("#displayid").change(function(){

						 $(".select").prop('checked', $(this).prop("checked"));
						 
						 
						});
			
		
		

				});

$(document).ready(function() {
	$('#datetimepicker3').datetimepicker({
		pickDate : false
	});
	$('#datetimepicker4').datetimepicker({
		pickDate : false
	});
	
	
	
});

function createRow(val1) {
	/*
	 * var tt = $('table#allstudent tr:last input[name=stopNo]').val(); if ((tt !=
	 * undefined) && (tt != '')) { var thenum = tt.replace(/^\D+/g, ''); for (
	 * var i = thenum; i > 0; i--) { removeRow(i); } }
	 */

	var value = val1;
	allstudent = 0;
	for ( var j = 0; j < value; j++) {
		addMoreRows();
	}
}

function removeRow(removeNum) {

	jQuery('#rowId' + removeNum).remove();
}

var allstudent = 0;
function addMoreRows() {
	allstudent++;
	$('#allstudent tr')
			.last()
			.after(
					'<tr id="rowId'
							+ allstudent
							+ '" cellpadding="5" cellspacing="5"><td><input value="    '
							+ allstudent
							+ '" type="text" name="stopNo" readonly="readonly" style="margin-left: 8px;height: 108%" size="3%""/></td><td><select name="stopName" id="stopName'
							+ allstudent
							+ '" class="form-control" onClick="return testFunction(this)"><option value=" ">----------Select----------</option></select><input type="hidden" class="htageid" id="htageid'
							+ allstudent
							+ '"></td><td class="arrivalTime"><div onclick="return getTime();" class="datetimepicker input-append"><input type="text" class="form-control" data-format="hh:mm" size="20%" name="arrTime" onClick="getDeptTime('
							+ allstudent
							+ ');" /><img src="./images/time1.jpg" width="25" height="20" class="add-on" style="margin-top:-14%;margin-left: 86%;"/></div></td><td><input name="haltTime" class="haltTime" id="haltTimeID" readonly="readonly" type="text"  maxlength="2" style="width: 38px;height: 32px;margin: 0 40px;    text-align: center;" /></td><td><input name="depTime" readonly="readonly" class="form-control" type="text" maxlength="5" /></td><td><input type="text" name="distance" class="form-control"   maxlength="3" /></td></td></tr>');

	$('.haltTime').val($('#hidstopid').val());

	$.ajax({
		type : 'POST',
		url : "transport.html?method=getStopNames",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			for ( var j = 0; j < result.stopslist.length; j++) {
				$("#stopName" + allstudent).append(
						'<option value="' + result.stopslist[j].stageCode
								+ '">' + result.stopslist[j].stageName
								+ '</option>');
			}

		}
	});

	/*
	 * <select class="form-control" name="category" id="category"> <option
	 * value=" ">----------Select----------</option> </select>
	 */

}
function getTime() {
	$('.datetimepicker').datetimepicker({
		pickDate : false,
		onClose : function() {
			getDeptTime(removeNum);
		}
	});
}
function getDeptTime(removeNum) {
	var dept_time = "";

	var regexpforInteger = /^([0-9])+$/;
	$('table#allstudent td input[name=arrTime]').blur(
			function() {
				var arrival_Time = $(this).parents('tr:first').find(
						'td:nth-child(3)').find('input').val().trim();
				var halt_Time = $(this).parents('tr:first').find(
						'td:nth-child(4)').find('input').val().trim();

				if ((arrival_Time != '') && (halt_Time != '')
						&& (regexpforInteger.test(halt_Time.trim()))) {

					var t1 = arrival_Time.split(/\D/);

					var x1 = parseInt(t1[0]) * 60 * 60 + parseInt(t1[1]) * 60;
					var x2 = parseInt('00') * 60 * 60 + parseInt(halt_Time)
							* 60;
					var s = x1 + x2;
					var m = Math.floor(s / 60);
					s = s % 60;
					var h = Math.floor(m / 60);
					m = m % 60;
					if (h.toString().length == 1) {
						h = "0" + h;
					}
					if (m.toString().length == 1) {
						m = "0" + m;
					}
					dept_time = h + ":" + m;
					$(this).parents('tr:first').find('td:nth-child(5)').find(
							'input').val(dept_time);
				}
			});
}

function testFunction(current) {

	var curid = "#" + current.id;

	// var arr = current.id.split('stopName');

	// var stagid = "#htageid" + arr[1].trim();

	$.ajax({
		type : 'POST',
		url : "transport.html?method=getStopNames",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			/* $(curid).html(""); */
			/*
			 * $(curid).append( '<option value="' + "" + '">' +
			 * "-----Select-----" + '</option>');
			 */

			for ( var j = 0; j < result.stopslist.length; j++) {
				$(curid).append(
						'<option value="' + result.stopslist[j].stageCode
								+ '">' + result.stopslist[j].stageName
								+ '</option>');
			}

		}
	});

	/*
	 * $(curid).autocomplete({ source : function(request, response) {
	 * 
	 * $.ajax({
	 * 
	 * url : "transport.html?method=getStopNames", data : { searchTerm :
	 * request.term }, async : false, success : function(data) { var result =
	 * $.parseJSON(data);
	 * 
	 * response($.map(result.stopslist, function(item) { return { label :
	 * item.stageName, value : item.stageCode, } })); } }); },
	 * 
	 * select : function(event, ui) {
	 * 
	 * $(curid).val(ui.item.label); $(stagid).val(ui.item.value);
	 * 
	 * return false; }
	 * 
	 * });
	 */

}
var status = false;
function checkTime() {

	var ftime = document.getElementById("starttime").value;
	var ttime = document.getElementById("endtime").value;

	if ((ftime != "") && (ttime != "")) {

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
			$(".errormessagediv").show();
			$(".validateTips").text(
					"The End Time should not less than Start Time");
			return false;
			status = false;
			document.getElementById("endtime").value = "";
		}
		if (ttimeSplitHour == ftimeSplitHour) {
			if (ftimeSplitMin > ttimeSplitMin) {
				$(".validateTips").text(
						"The End Time should not less than Start Time");
				$(".errormessagediv").show();

				return false;
				status = false;
				document.getElementById("endtime").value = "";

			}
			if (ftimeSplitMin == ttimeSplitMin) {
				if (ftimeSplitSec >= ttimeSplitSec) {
					$(".validateTips").text(
							"The End Time should not less than Start Time");
					$(".errormessagediv").show();

					return false;
					status = false;
					document.getElementById("endtime").value = "";
				}
			}
		} else {
			$(".errormessagediv").hide();
			status = true;
		}
	} else {
		status = true;
	}
	return status;
}

function checkRouteNo() {
	routeid = $("#routeid").val().trim();

	if (routeid == "" || routeid == null) {
		routeid = "NULL";
	}
	var checkName = {
		"routeNo" : $("#routeNo").val(),
		"routeid" : routeid,
		"accyear":$("#haccyear").val(),
	};
	var status = false;
	$.ajax({
		type : "POST",
		url : "transport.html?method=checkrouteNo",
		data : checkName,
		async : false,
		success : function(data) {

			var result = $.parseJSON(data);
			text = result.status;

			if (text == true) {
				$('.errormessagediv1').show();
				$('.validateTips1').text("Route No already Exist");
				status = true;
			} else {
				$('.errormessagediv1').hide();
			}
		}
	});

	return status;

}




function HideError() 
{

document.getElementById("routeName").style.border = "1px solid #ccc";
document.getElementById("routeName").style.backgroundColor = "#fff";

document.getElementById("routeNo").style.border = "1px solid #ccc";
document.getElementById("routeNo").style.backgroundColor = "#fff";


document.getElementById("totalStops").style.border = "1px solid #ccc";
document.getElementById("totalStops").style.backgroundColor = "#fff";

document.getElementById("starttime").style.border = "1px solid #ccc";
document.getElementById("starttime").style.backgroundColor = "#fff";

document.getElementById("endtime").style.border = "1px solid #ccc";
document.getElementById("endtime").style.backgroundColor = "#fff";

document.getElementById("totalDistance").style.border = "1px solid #ccc";
document.getElementById("totalDistance").style.backgroundColor = "#fff";

document.getElementById("halttime").style.border = "1px solid #ccc";
document.getElementById("halttime").style.backgroundColor = "#fff";


}
