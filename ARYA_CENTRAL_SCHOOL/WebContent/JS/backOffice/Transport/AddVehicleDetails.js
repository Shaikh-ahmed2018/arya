$(document)
		.ready(
				function() {

					
					$("#uploadinsurance").show();
					$("#document1btn").hide();
					$("#deleteProfile").hide();
					
					$("#uploadrcfile").show();
					$("#document2btn").hide();
					$("#deleteIDProof").hide();
					
					$("#taxpaid").datepicker({
						dateFormat : "dd-mm-yy",
						maxDate : -1,
						yearRange : 'c-5:c+5',
						changeMonth : "true",
						changeYear : "true",
				      
				        onSelect: function (date) {
				            var date2 = $('#taxpaid').datepicker('getDate');
				          
				            
				            date2.setMonth(date2.getMonth()+12);
				            date2.setDate(date2.getDate()-1);
				            var day=date2.getDate();
				            if(day<10){
				            	day="0"+day;
				            }
				            var month=date2.getMonth()+1;
				            if(month<10){
				            	month="0"+month;
				            }
				            var year=date2.getFullYear();
				            $('#taxexpirydate').val(day+"-"+month+"-"+year);
				            //sets minDate to dt1 date + 1
				           
				        }
					
				    });
					
					//$("#taxexpirydate").attr( 'readOnly' , 'true' );
					
					$("#pollution").datepicker({

						dateFormat : "dd-mm-yy",
						maxDate : 0,
						yearRange : "1997",
						changeMonth : true,
						changeYear : true,
						dateFormat : 'dd-mm-yy',
						numberOfMonths : 1,

					});
					
					
					$("#issueddate").datepicker({
						dateFormat : "dd-mm-yy",
						maxDate : -1,
						yearRange : 'c-5:c+5',
						changeMonth : "true",
						changeYear : "true",
				      
				        onSelect: function (date) {
				            var date2 = $('#issueddate').datepicker('getDate');
				            date2.setMonth(date2.getMonth() + 12);
				            date2.setDate(date2.getDate()-1);
				            var day=date2.getDate();
				            if(day<10){
				            	day="0"+day;
				            }
				            var month=date2.getMonth()+1;
				            if(month<10){
				            	month="0"+month;
				            }
				            var year=date2.getFullYear();
				            $('#expirydate').val(day+"-"+month+"-"+year);
				            //sets minDate to dt1 date + 1
				           
				        }
				    });
					
					$("#expirydate").attr( 'readOnly' , 'true' );
					
				
					
					$("#fc").datepicker({
						dateFormat : "dd-mm-yy",
						 minDate : 0,
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true
						
					});
					
					$("#permitvalidity").datepicker({
						dateFormat : "dd-mm-yy",
						 minDate : 0,
						 yearRange : 'c-30:c+30',
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true
						
					});
					
					
					$("#todate").datepicker({
						dateFormat : "dd-mm-yy",
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true
						
					});	
					
					

					var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
					if (pageUrl == "transport.html?method=saveVehicleDetails"){
						
						$(".successmessagediv").show();

						 setTimeout(function(){
							   
							 window.location.href = "adminMenu.html?method=vehicleList";
						 
						 },2000);
						
						
					}
				
					
					setTimeout("removeMessage()", 4000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 2000);

					/*$.ajax({
								type : 'POST',
								url : "transport.html?method=getRouteDetails",
								async : false,
								success : function(response) {
									var result = $.parseJSON(response);
									$('#routename').html("");
									$('#routename').append(
											'<option value="' + "" + '">'
													+ "-----Select-----"
													+ '</option>');
									for ( var j = 0; j < result.routelist.length; j++) {
										$('#routename')
												.append(
														'<option value="'
																+ result.routelist[j].routeCode
																+ '">'
																+ result.routelist[j].routeName
																+ '</option>');
									}
									
									$('#routename').val($('routecodeid').val());

								}
							});*/
					
					
					
					/*$
					.ajax({
						type : 'POST',
						url : "transport.html?method=getDriverDetails",
						async : false,
						success : function(response) {
							var result = $.parseJSON(response);
							$('#drivername').html("");
							$('#drivername').append(
									'<option value="' + "" + '">'
											+ "-----Select-----"
											+ '</option>');

							for ( var j = 0; j < result.drivernamelist.length; j++) {
								$('#drivername')
										.append(
												'<option value="'
														+ result.drivernamelist[j].driverCode
														+ '">'
														+ result.drivernamelist[j].driverName
														+ '</option>');
							}
							

						}
					});*/
					
					
					
				
					var vehicleidHidden=$('#hvehicleid').val().trim();
					//alert(vehicleidHidden);
					
					if (vehicleidHidden == "" || vehicleidHidden == undefined ) {
						driverCode();
					} else {
						
						driverCode();
						$("#drivername option[value="+ $('#driverCode').val() + "]").attr('selected', 'true');

						$("#routename option[value="+ $('#routecodeid').val() + "]").attr('selected', 'true');
					}
					
					
					
					var vehicleidHidden=$('#hvehicleid').val().trim();
					//alert(vehicleidHidden);
					
					if (vehicleidHidden == "" || vehicleidHidden == undefined ) {
						routeCode();
					} else {
						
						routeCode();
						$("#routename option[value="+ $('#routecodeid').val() + "]").attr('selected', 'true');

						$("#routename option[value="+ $('#routecodeid').val() + "]").attr('selected', 'true');
					}
						
						
						
						
						
						
						/*var dataval = {
							"vehicleCode" : hvehicleid,
						};

						$.ajax({
							type : 'POST',
							url : "transport.html?method=getDriverDetailsWhileUpdate",
							data : dataval,
							async : false,
							success : function(response) {
								var result = $.parseJSON(response);

								for ( var j = 0; j < result.drivernamelist.length; j++) {
									$('#drivername').append('<option value="'
											+ result.drivernamelist[j].driverCode
											+ '">'
											+ result.drivernamelist[j].driverName
											+ '</option>');
								}


								$("#drivername option[value="+ $('#hvehicleid').val() + "]").attr('selected', 'true');

								$("#routename option[value="+ $('#routecodeid').val() + "]").attr('selected', 'true');
							}
						});*/
					
					
					
					

					$("#saveid").click(function() {
						
						
						
						var fistInput = document.getElementById("enginenumber").value;
						var secondInput = document.getElementById("chassisno").value;
						
						
						
						
									 
										var driverCode = "";
										var routename = $("#routename").val();
										if ($("#driverCode").val() == null || $("#driverCode").val() == "") {
											driverCode = $("#drivername").val();
										} else {
											driverCode = $("#driverCode").val();
										}
										var updatevehiclecode = $(
												"#hvehicleid").val();

										var status = $("#statusId").val();

										var vehicleregno = $("#vehicleregno")
												.val();
										
										
										var vehicletype = $("#vehicletype")
												.val();
										
										var chassisno = $("#chassisno").val();

										var vehiclename = $("#vehiclename")
												.val();
										
										var nameofvehicle = $("#nameofvehicle")
										.val();
										
										
										var enginenumber = $("#enginenumber")
										.val();
										
										var taxpaid = $("#taxpaid").val();
										var pollution = $("#pollution").val();
										var fc = $("#fc")
										.val();
										
										var permitvalidity = $("#permitvalidity")
										.val();
										
										
										var companyname = $("#companyname")
												.val();
										var issueddate = $("#issueddate").val();
										var expirydate = $("#expirydate").val();
										var doneby = $("#doneby").val();

										if (vehicleregno == ""
												|| vehicleregno == null) {
											$(".errormessagediv").show();
											$(".validateTips")
													.text(
															"Field Required Registration No");
											
											document.getElementById("vehicleregno").style.border = "1px solid #AF2C2C";
											document.getElementById("vehicleregno").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											
											return false;
										}/*else if(!vehicleregno.match(/[0-9\s]+$/i)) {
											$('.errormessagediv').show();
											$('.validateTips').text("Field Required a Valid Registration No");
											$("#vehicleregno").focus();
											document.getElementById("vehicleregno").style.border = "1px solid #AF2C2C";
											document.getElementById("vehicleregno").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);

											return false;

										} */
										
										else if (registernumberValidation() == true) {

											$(".errormessagediv").show();
											$(".validateTips")
													.text(
															"Vehicle Register No already Exists");
											
											document.getElementById("vehicleregno").style.border = "1px solid #AF2C2C";
											document.getElementById("vehicleregno").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											
											return false;

										} else if (vehiclename == ""
												|| vehiclename == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Field Required Vehicle Name");
											
											document.getElementById("vehiclename").style.border = "1px solid #AF2C2C";
											document.getElementById("vehiclename").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											
											return false;
										} /*else if (!vehiclename
												.match(/[A-Za-z]+$/i)) {
											$(".errormessagediv").show();
											$(".validateTips")
													.text(
															"Vehicle Name Should be Alphabet");
											
											document.getElementById("vehiclename").style.border = "1px solid #AF2C2C";
											document.getElementById("vehiclename").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											
											return false;
										}*/
										 else if (enginenumber == ""
												|| enginenumber == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Field Required Engine No");
											document.getElementById("enginenumber").style.border = "1px solid #AF2C2C";
											document.getElementById("enginenumber").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}/*else if(!enginenumber.match(/[0-9\s]+$/i)) {
											$('.errormessagediv').show();
											$('.validateTips').text("Field Required a Valid Engine No");
											$("#enginenumber").focus();
											document.getElementById("enginenumber").style.border = "1px solid #AF2C2C";
											document.getElementById("enginenumber").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);

											return false;

										} */
							
										 else if (fistInput === secondInput) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Engine and Chassis Number Cannot be Same");
											document.getElementById("chassisno").style.border = "1px solid #AF2C2C";
											document.getElementById("chassisno").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}/*else if(!chassisno.match(/[0-9\s]+$/i)) {
											$('.errormessagediv').show();
											$('.validateTips').text("Field Required a Valid Chassis No");
											$("#chassisno").focus();
											document.getElementById("chassisno").style.border = "1px solid #AF2C2C";
											document.getElementById("chassisno").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);

											return false;

										} */
										
										else if (chassisno == "") {
										$(".errormessagediv").show();
										$(".validateTips").text(
												"Field Required Chassis No");
										document.getElementById("chassisno").style.border = "1px solid #AF2C2C";
										document.getElementById("chassisno").style.backgroundColor = "#FFF7F7";
										setTimeout(function() {
											$('.errormessagediv').fadeOut();
										}, 3000);
										return false;
									}
										

										else if (vehicletype == ""
												|| vehicletype == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Field Required Vehicle Type");
											
											document.getElementById("vehicletype").style.border = "1px solid #AF2C2C";
											document.getElementById("vehicletype").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											
											return false;
										}/* else if (!vehicletype
												.match(/[A-Za-z]+$/i)) {
											$(".errormessagediv").show();
											$(".validateTips")
													.text(
															"Vehicle Type should be Alphabet");
											
											document.getElementById("vehicletype").style.border = "1px solid #AF2C2C";
											document.getElementById("vehicletype").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											
											return false;
										}*/
										else if (pollution == ""
											|| pollution == null) {
										$(".errormessagediv").show();
										$(".validateTips").text(
												"Field Required Last Emission Test Date");
										
										document.getElementById("pollution").style.border = "1px solid #AF2C2C";
										document.getElementById("pollution").style.backgroundColor = "#FFF7F7";
										setTimeout(function() {
											$('.errormessagediv').fadeOut();
										}, 3000);
										return false;
									}	
										
										else if (taxpaid == ""
												|| taxpaid == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Tax Issued Date");
											
											document.getElementById("taxpaid").style.border = "1px solid #AF2C2C";
											document.getElementById("taxpaid").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}									
										else if (companyname == ""
												|| companyname == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Field Required Company Name");
											document.getElementById("companyname").style.border = "1px solid #AF2C2C";
											document.getElementById("companyname").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}/* else if (!companyname
												.match(/^[a-z]([a-z_" "])+$/i)) {
											$(".errormessagediv").show();
											$(".validateTips")
													.text(
															"Company Name Should be Alphabet");
											document.getElementById("companyname").style.border = "1px solid #AF2C2C";
											document.getElementById("companyname").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}*/else if (issueddate == ""
												|| issueddate == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Issued Date");
											document.getElementById("issueddate").style.border = "1px solid #AF2C2C";
											document.getElementById("issueddate").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										} else if (expirydate == ""
												|| expirydate == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Expiry Date");
											document.getElementById("expirydate").style.border = "1px solid #AF2C2C";
											document.getElementById("expirydate").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										} else if (chkdate == true) {
											$(".errormessagediv").show();
											$(".validateTips")
													.text(
															"Vehicle Insurance dates already Exists");
											document.getElementById("expirydate").style.border = "1px solid #AF2C2C";
											document.getElementById("expirydate").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}else if (doneby == ""
												|| doneby == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Field Required Policy Number");
											document.getElementById("doneby").style.border = "1px solid #AF2C2C";
											document.getElementById("doneby").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}
										/*else if(!doneby
												.match(/^[0-9]+$/))
												
								      {  
										 $(".errormessagediv").show();
										 $(".validateTips")
											.text(
													"Policy Number should be number");
								      
										    document.getElementById("doneby").style.border = "1px solid #AF2C2C";
											document.getElementById("doneby").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
										}, 3000);
											return false;
								      }  */
										else if (fc == "" || fc == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select FC Date");
											
											document.getElementById("fc").style.border = "1px solid #AF2C2C";
											document.getElementById("fc").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}else if (permitvalidity == ""
												|| permitvalidity == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Permit Validity Date");
											
											document.getElementById("permitvalidity").style.border = "1px solid #AF2C2C";
											document.getElementById("permitvalidity").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}else if (status == "update"
												&& updateregisternumberValidation()) {

											$(".errormessagediv").show();
											$(".validateTips")
													.text(
															"Vehicle Register No already Exists");
											document.getElementById("vehicleregno").style.border = "1px solid #AF2C2C";
											document.getElementById("vehicleregno").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										} else if (status == "update"
												&& updatechassisnovalidation()) {

											$(".errormessagediv").show();
											$(".validateTips")
													.text(
															"Chassis No already Exists");
											document.getElementById("chassisno").style.border = "1px solid #AF2C2C";
											document.getElementById("chassisno").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										} else if (checkforduplicateSave()) {

											$(".errormessagediv").show();
											$(".validateTips")
													.text(
															"Vehicle Details Already Exists");
											
											return false;

										} else if (checkforduplicateupdate()
												&& status == "update") {

											$(".errormessagediv").show();
											$(".validateTips")
													.text(
															"Vehicle Details Already Exists");
											
											return false;
										} else if (routename = ""
												&& routename == null) {

											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select Route Name");
											document.getElementById("routename").style.border = "1px solid #AF2C2C";
											document.getElementById("routename").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}

										else {
											

											document.getElementById("myForm").submit();		

											
											
										}
											

											 
									});

					var updateVehicleCode = $("#hvehicleid").val();
					
				/*	
					if(updateVehicleCode = null || updateVehicleCode == ""){
						$("#uploadinsurance").show();
						$("#uploadrcfile").show();
						$("#document1btn").hide();
						$("#deleteProfile").hide();
						$("#document2btn").hide();
						$("#deleteIDProof").hide();
						
					}
					*/
					
					
					if(updateVehicleCode != null || updateVehicleCode != "")
							
						
							{
						
						/*$("#uploadinsurance").hide();
						$("#uploadrcfile").hide();
						$("#document1btn").show();
						$("#deleteProfile").show();
						$("#document2btn").show();
						$("#deleteIDProof").show();*/
						
						
						
						
						var hrcfileid = $("#hrcfileid").val();
						
						var hinsurancefileid = $("#hinsurancefileid").val();
						
					
						if(hinsurancefileid !="" && hinsurancefileid !=undefined){
							
							$("#document1btn").attr('name',hinsurancefileid);
							
							$("#uploadinsurance").hide();
							$("#document1btn").show();
							$("#deleteProfile").show();
							
						}
						
						if(hrcfileid !="" && hrcfileid !=undefined){
							
							
							$("#document2btn").attr('name',hrcfileid);
							$("#uploadrcfile").hide();
							$("#document2btn").show();
							$("#deleteIDProof").show();
							
						}
						
						$("#deleteIDProof").click(function(){
							
							$("#uploadrcfile").show();
							
							$("#document2btn").hide();
							$("#deleteIDProof").hide();
							
							$("#hrcfileid").val("");
							
							
							
						});
						
						$("#deleteProfile").click(function(){
							
							$("#uploadinsurance").show();
							
							$("#document1btn").hide();
							$("#deleteProfile").hide();
							$("#hinsurancefileid").val("");
							
							
						});
						
						
						$('.downloadDoc1').click(
								
								function() {
									
									var path = $(this).attr('name');
									
									/*alert("insurance path "+path);*/
									
									window.location.href = "transport.html?method=downloadInsuranceFile&Path="
											+ path.trim();
								});
						
						
						$('.downloadDoc2').click(
								
								function() {
									
									var path = $(this).attr('name');
									
									/*alert("rc path "+path);*/
						 			
									window.location.href = "transport.html?method=downloadRcFile&Path="
											+ path.trim();
								});
						
						
						
						
						
						
						
						
						
						
						
					}
					
					
					
					var hiddenfuel = $("#hiddenfuelId").val();
					$("#fuelengine option[value=" + hiddenfuel + "]").attr(
							'selected', 'true');

					var chkdate = false;

					/*$("#manufacturerdate").datepicker({

						dateFormat : "dd-mm-yy",
						maxDate : 0,
						yearRange : "1997",
						changeMonth : true,
						changeYear : true,
						dateFormat : 'dd-mm-yy',
						numberOfMonths : 1,

					});*/

					/*$("#issueddate").datepicker(
							{
								dateFormat : "dd-mm-yy",
								yearRange : 'c-65:c+65',
								maxDate : -1,
								changeMonth : "true",
								changeYear : "true",
								yearRange : '1997:' + (new Date).getFullYear(),
								onClose : function(selectedDate) {
									var date2 = $('#issueddate').datepicker(
											'getDate');
									date2.setDate(date2.getDate() + 1);
									$("#expirydate").datepicker("option",
											"minDate", date2);

								}
							});
					$("#expirydate").datepicker(
							{

								dateFormat : "dd-mm-yy",
								yearRange : 'c-65:c+65',
								changeMonth : "true",
								changeYear : "true",
								onClose : function(selectedDate) {

									var date2 = $('#expirydate').datepicker(
											'getDate');
									date2.setDate(date2.getDate() - 1);
									$("#issueddate").datepicker("option",
											"maxDate", date2);

								}
							});*/
					/*$("#taxpaid").datepicker(
							{
								dateFormat : "dd-mm-yy",
								yearRange : 'c-65:c+65',
								maxDate : -1,
								changeMonth : "true",
								changeYear : "true",
								yearRange : '1997:' + (new Date).getFullYear(),
								onClose : function(selectedDate) {
									var date2 = $('#issueddate').datepicker(
											'getDate');
									date2.setDate(date2.getDate() + 1);
									$("#expirydate").datepicker("option",
											"minDate", date2);

								}
							});*/
					
					
					
					/*$("#fc").datepicker(
							{
								dateFormat : "dd-mm-yy",
								yearRange : 'c-65:c+65',
								maxDate : -1,
								changeMonth : "true",
								changeYear : "true",
								yearRange : '1997:' + (new Date).getFullYear(),
								onClose : function(selectedDate) {
									var date2 = $('#issueddate').datepicker(
											'getDate');
									date2.setDate(date2.getDate() + 1);
									$("#expirydate").datepicker("option",
											"minDate", date2);

								}
							});*/
					
					
					/*$("#permitvalidity").datepicker(
							{
								dateFormat : "dd-mm-yy",
								yearRange : 'c-65:c+65',
								maxDate : -1,
								changeMonth : "true",
								changeYear : "true",
								yearRange : '1997:' + (new Date).getFullYear(),
								onClose : function(selectedDate) {
									var date2 = $('#issueddate').datepicker(
											'getDate');
									date2.setDate(date2.getDate() + 1);
									$("#expirydate").datepicker("option",
											"minDate", date2);

								}
							});*/
					
					
					
					

				});

function registernumberValidation() {

	var regno = null;
	var vehicleregno = $("#vehicleregno").val();
	var status = $("#statusId").val();

	var vehicleregnumber = {
		"vehicleregno" : vehicleregno

	};
	if (status != "update") {
		$.ajax({
			type : 'POST',
			url : "transport.html?method=registernumberValidation",
			data : vehicleregnumber,

			success : function(response) {

				var result = $.parseJSON(response);

				if (result.status) {
					regno = true;

					$(".errormessagediv").show();
					$(".validateTips").text(
							"Vehicle Register No already Exists");
					$("#vehicleregno").val("");
					return false;

				} else {
					regno = false;

					$(".errormessagediv1").hide();
				}

			}

		});
	}

	return regno;

}
var regno = null;
function updateregisternumberValidation() {

	var vehicleregno = $("#vehicleregno").val();
	var vehicleCode = $("#updatevehicleCode").val();

	var vehicleregnumber = {
		"vehicleregno" : vehicleregno,
		"vehicleCode" : vehicleCode
	};

	$.ajax({
		type : 'POST',
		url : "transport.html?method=updateregisternumberValidation",
		data : vehicleregnumber,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);

			if (result.status == true) {
				regno = true;

			} else {
				regno = false;

			}

		}

	});

	return regno;

}

function clearAll() {

	$("#vehicleregno").val("");
	$("#vehicletype").val("");
	$("#typeofbody").val("");
	$("#makersname").val("");
	$("#manufacturerdate").val("");
	$("#chassisno").val("");
	$("#settingcapacity").val("");
	$("#fuelengine").val("");
	$("#colorofbody").val("");

	$("#vehiclename").val("");

	/*
	 * var vehiclename = $( "#vehiclename") .val();
	 */
	$("#companyname").val("");
	$("#issueddate").val("");
	$("#expirydate").val("");
	$("#doneby").val("");
}
function chassisnovalidation() {

	var chas = null;
	var chassisno = $("#chassisno").val();
	var status = $("#statusId").val();

	var chassisnumber = {
		"chassisno" : chassisno

	};
	if (status != "update") {
		$.ajax({
			type : 'POST',
			url : "transport.html?method=chassisnovalidationvalidation",
			data : chassisnumber,

			success : function(response) {

				var result = $.parseJSON(response);

				if (result.status) {
					chas = true;

					$(".errormessagediv").show();
					$(".validateTips").text("Chassis No already Exists");
					return false;

				} else {
					chas = false;

					$(".errormessagediv").hide();
				}

			}

		});
	}

	return chas;

}

var updatechass = null;
function updatechassisnovalidation() {

	var updatechassisno = $("#chassisno").val();
	var vehicleCode = $("#updatevehicleCode").val();

	var chassisnumber = {
		"chassisno" : updatechassisno,
		"vehicleCode" : vehicleCode
	};
	$.ajax({
		type : 'POST',
		url : "transport.html?method=updatechassisnovalidation",
		data : chassisnumber,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);

			if (result.status) {
				updatechass = true;

				/*
				 * $(".errormessagediv").show();
				 * $(".validateTips").text("Chassis No already Exists");
				 * 
				 * return false;
				 */

			} else {
				updatechass = false;
				/* $(".errormessagediv").hide(); */

			}

		}

	});

	return updatechass;

}

function checkforduplicateSave() {

	var status = $("#statusId").val();

	var isduplicate = null;

	var vehicleregno = $("#vehicleregno").val();
	var vehiclename = $("#vehiclename").val();
	var enginenumber = $("#enginenumber").val();
	var chassisno = $("#chassisno").val();
	var vehicletype = $("#vehicletype").val();
	var taxpaid = $("#taxpaid").val();
	//alert(taxpaid);

	var vehicledata = {
		"vehicleregno" : vehicleregno,
		"vehiclename" : vehiclename,
		"enginenumber" : enginenumber,
		"chassisno" : chassisno,
		"vehicletype" : vehicletype,
		"taxpaid" : taxpaid,
	};
	if (status != "update") {
		$.ajax({
			type : 'POST',
			url : "transport.html?method=checkforduplicateAddTime",
			data : vehicledata,
			async : false,

			success : function(response) {

				var result = $.parseJSON(response);

				if (result.status) {
					isduplicate = true;

				} else {
					isduplicate = false;

				}

			}

		});
	}

	return isduplicate;

}

var isduplicateupdate = null;
function checkforduplicateupdate() {

	var vehicleCode = $("#hvehicleid").val();
	
	var vehicleregno = $("#vehicleregno").val();
	var vehiclename = $("#vehiclename").val();
	var enginenumber = $("#enginenumber").val();
	var chassisno = $("#chassisno").val();
	var vehicletype = $("#vehicletype").val();
	var taxpaid = $("#taxpaid").val();
	var pollution = $("#pollution").val();
	
	
	var vehicledata = {
		"vehicleregno" : vehicleregno,
		"vehiclename" : vehiclename,
		"enginenumber" : enginenumber,
		"chassisno" : chassisno,
		"vehicletype" : vehicletype,
		"taxpaid" : taxpaid,
		"pollution" : pollution,
		"vehicleCode" : vehicleCode
	};
	$.ajax({
		type : 'POST',
		url : "transport.html?method=checkforduplicateUpdateTime",
		data : vehicledata,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);

			if (result.status) {
				isduplicateupdate = true;

			} else {
				isduplicateupdate = false;

			}

		}

	});

	return isduplicateupdate;

}

function getDriverEntireDetails() {
	var dataval = {
		"driverid" : $('#drivername').val(),
	};
	$.ajax({
		type : 'POST',
		url : "transport.html?method=getDriverEntireDetails",
		data : dataval,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			$('#experience').val(result.driverlist[0].experience);
			$('#dlissued').val(result.driverlist[0].dl_issued_date);
			$('#licencedrive').val(result.driverlist[0].license);
			$('#phoneno').val(result.driverlist[0].mobile);
			$('#doj').val(result.driverlist[0].dateofJoin);
			$('#dlno').val(result.driverlist[0].drivingliecenseNo);
			$('#dlexpiray').val(result.driverlist[0].dl_validity);

		}
	});
}



function getRouteEntireDetails() {

	var dataval = {
		"routeid" : $('#routename').val(),
	};
	$.ajax({
		type : 'POST',
		url : "transport.html?method=GetRouteEntireDetails",
		data : dataval,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#totalstops').val(result.routelist[0].totalStops);
			$('#totaldistance').val(result.routelist[0].totalDistance);
			$('#routeno').val(result.routelist[0].routeCode);
			$('#halttime').val(result.routelist[0].halttime);
			
		}
	});
}

function driverCode(){
		
	$.ajax({
		type : 'POST',
		url : "transport.html?method=getDriverDetails",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#drivername').html("");
			$('#drivername').append(
					'<option value="' + "" + '">'
					+ "-----Select-----"
					+ '</option>');
			for ( var j = 0; j < result.drivernamelist.length; j++) {
				$('#drivername').append(
						'<option value="'
						+ result.drivernamelist[j].driverCode
						+ '">'
						+ result.drivernamelist[j].driverName
						+ '</option>');
			}
		}
	});
}

function routeCode(){
$.ajax({
	type : 'POST',
	url : "transport.html?method=getrouteDetails",
	async : false,
	success : function(response) {
		var result = $.parseJSON(response);
		$('#routename').html("");
		$('#routename').append(
				'<option value="' + "" + '">'
						+ "-----Select-----"
						+ '</option>');
		for ( var j = 0; j < result.routelist.length; j++) {
			$('#routename')
					.append(
							'<option value="'
									+ result.routelist[j].routeCode
									+ '">'
									+ result.routelist[j].routeName
									+ '</option>');
		}
		
		$('#routename').val($('routecodeid').val());

	}
});
}



function removeMessage() {

	$(".successmessagediv").hide();

}
function HideError() 
{
	
document.getElementById("vehicleregno").style.border = "1px solid #ccc";
document.getElementById("vehicleregno").style.backgroundColor = "#fff";
document.getElementById("vehiclename").style.border = "1px solid #ccc";
document.getElementById("vehiclename").style.backgroundColor = "#fff";
document.getElementById("enginenumber").style.border = "1px solid #ccc";
document.getElementById("enginenumber").style.backgroundColor = "#fff";
document.getElementById("chassisno").style.border = "1px solid #ccc";
document.getElementById("chassisno").style.backgroundColor = "#fff";
document.getElementById("vehicletype").style.border = "1px solid #ccc";
document.getElementById("vehicletype").style.backgroundColor = "#fff";
document.getElementById("taxpaid").style.border = "1px solid #ccc";
document.getElementById("taxpaid").style.backgroundColor = "#fff";
document.getElementById("pollution").style.border = "1px solid #ccc";
document.getElementById("pollution").style.backgroundColor = "#fff";
document.getElementById("companyname").style.border = "1px solid #ccc";
document.getElementById("companyname").style.backgroundColor = "#fff";
document.getElementById("issueddate").style.border = "1px solid #ccc";
document.getElementById("issueddate").style.backgroundColor = "#fff";
document.getElementById("expirydate").style.border = "1px solid #ccc";
document.getElementById("expirydate").style.backgroundColor = "#fff";
document.getElementById("doneby").style.border = "1px solid #ccc";
document.getElementById("doneby").style.backgroundColor = "#fff";
document.getElementById("fc").style.border = "1px solid #ccc";
document.getElementById("fc").style.backgroundColor = "#fff";





document.getElementById("manufacturerdate").style.border = "1px solid #ccc";
document.getElementById("manufacturerdate").style.backgroundColor = "#fff";

document.getElementById("settingcapacity").style.border = "1px solid #ccc";
document.getElementById("settingcapacity").style.backgroundColor = "#fff";
document.getElementById("fuelengine").style.border = "1px solid #ccc";
document.getElementById("fuelengine").style.backgroundColor = "#fff";
document.getElementById("colorofbody").style.border = "1px solid #ccc";
document.getElementById("colorofbody").style.backgroundColor = "#fff";
document.getElementById("vehiclename").style.border = "1px solid #ccc";
document.getElementById("vehiclename").style.backgroundColor = "#fff";




document.getElementById("routename").style.border = "1px solid #ccc";
document.getElementById("routename").style.backgroundColor = "#fff";

}