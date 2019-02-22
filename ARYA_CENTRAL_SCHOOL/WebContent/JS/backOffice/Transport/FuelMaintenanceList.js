$(document).ready(function() {
	
	$("#vehiclename").val($("#vehiclecode").val());
	$("#drivername").val($("#drivercode").val());
	

	$(".errormessagediv").hide();
	$(".successmessagediv").hide();
	
	$('#saveid').click(function() {
		
	
		var vehiclename = $('#vehiclename').val();
	 
		var date = $('#date').val();
		var time = $('#timeid').val();
		var drivername = $('#drivername').val();
		
		var fueltype = $('#fueltype').val();
		var quantity = $('#quantityid').val();
		var price = $('#priceid').val();
		var meter_reading = $('#meterreading').val();
		var location = $('#locationid').val();
		var vehicleno = $('#vehiclenoid').val();
		var vehiclecode = $("#vehiclename").val();

	    var drivercode = $("#drivername").val();
	    
		var regex_double = '^[0-9]*\.[0-9]*$';
		var fuelcode = $('#fuelcode').val();
		
	
		if (vehiclename == "" || vehiclename == null) {

			$(".errormessagediv").show();
			$(".validateTips").text("Enter Vehicle Name");
			
			document.getElementById("vehicleid").style.border = "1px solid #AF2C2C";
			document.getElementById("vehicleid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}

		/*else if (!vehiclename.match(/^[a-z]([a-z_" "])+$/i)) {

			$(".errormessagediv").show();
			$(".validateTips").text("Vehicle Name should be characters");
			
			document.getElementById("vehicleid").style.border = "1px solid #AF2C2C";
			document.getElementById("vehicleid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;

		}*/

		 else if (date == null || date == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Select Date");
			
			document.getElementById("date").style.border = "1px solid #AF2C2C";
			document.getElementById("date").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}

		else if (time == null || time == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Select Time");
			
			document.getElementById("timeid").style.border = "1px solid #AF2C2C";
			document.getElementById("timeid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}
		
		else if (vehicleno == "" || vehicleno == null) {

			$(".errormessagediv").show();
			$(".validateTips").text("Enter Vehicle Number");
			
			document.getElementById("vehiclenoid").style.border = "1px solid #AF2C2C";
			document.getElementById("vehiclenoid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;

		} else if (!vehicleno.match(/^([0-9])+$/i)) {

			$(".errormessagediv").show();
			$(".validateTips").text("vehicleno Should Be Numeric");
			
			document.getElementById("vehiclenoid").style.border = "1px solid #AF2C2C";
			document.getElementById("vehiclenoid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}
		

		else if (drivername == null || drivername == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Select Driver Name");
			
			document.getElementById("driverid").style.border = "1px solid #AF2C2C";
			document.getElementById("driverid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}

		else if (fueltype == null || fueltype == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Enter Fuel Type");
			
			document.getElementById("fueltype").style.border = "1px solid #AF2C2C";
			document.getElementById("fueltype").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}

		else if (!fueltype.match(/^[a-z]([a-z_ ])+$/i)) {

			$(".errormessagediv").show();
			$(".validateTips").text("Fuel Type Should Be Alphabet");
			
			document.getElementById("fueltype").style.border = "1px solid #AF2C2C";
			document.getElementById("fueltype").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			

			return false;
		}

		else if (quantity == null || quantity == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Enter Quantity");
			
			document.getElementById("streamName").style.border = "1px solid #AF2C2C";
			document.getElementById("streamName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}

		else if (!quantity.match(/^([0-9])+$/i)) {

			$(".errormessagediv").show();
			$(".validateTips").text("Quantity Should Be Numeric");
			
			document.getElementById("quantityid").style.border = "1px solid #AF2C2C";
			document.getElementById("quantityid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}

		else if (price == null || price == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Enter Price");
			
			document.getElementById("streamName").style.border = "1px solid #AF2C2C";
			document.getElementById("streamName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}

		else if (!price.match(regex_double)) {

			$(".errormessagediv").show();
			$(".validateTips").text("Price Should Be Numeric");
			
			document.getElementById("priceid").style.border = "1px solid #AF2C2C";
			document.getElementById("priceid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}

		else if (meter_reading == null || meter_reading == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Enter Meter Reading");
			
			document.getElementById("meterreading").style.border = "1px solid #AF2C2C";
			document.getElementById("meterreading").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}

		else if (!meter_reading.match(regex_double)) {

			$(".errormessagediv").show();
			$(".validateTips").text("Meter Reading  Should Be Numeric");
			
			document.getElementById("meterreading").style.border = "1px solid #AF2C2C";
			document.getElementById("meterreading").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}

		else if (location == null || location == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Enter Location");
			
			document.getElementById("streamName").style.border = "1px solid #AF2C2C";
			document.getElementById("streamName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
			
		} else if (!location.match(/^[a-z]([a-z_ ])+$/i)) {
			
			$(".errormessagediv").show();
			$(".validateTips").text("Location Should Be Alphabet");
			
			document.getElementById("locationid").style.border = "1px solid #AF2C2C";
			document.getElementById("locationid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
			
		} 

		else {

			datalist = {
					"vehiclename" : vehiclename,
					"date" : date,
					"time" : time,
					"drivername" : drivername,
					"fueltype" : fueltype,
					"quantity" : quantity,
					"price" : price,
					"meter_reading" : meter_reading,
					"location" : location,
					"vehicleno" : vehicleno,
					"vehiclecode" : vehiclecode,
					"drivercode" : drivercode,
					"fuelcode"  : fuelcode,
					
				
				},

				
				$.ajax({

					type : "POST",

					url : "fuelMenu.html?method=insertFuelDetails",

					data : datalist,

					async : false,
					
					success : function(data) {

						var result = $.parseJSON(data);

						$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".successmessage").text(result.status);

						setTimeout(function() {

							location.reload();

						}, 5000);
					}

				});
				
			}

		
		
		
	});

	$("#date").datepicker({

		dateFormat : "dd-mm-yy",
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true
	});

	$("#updatedate").datepicker({

		dateFormat : "dd-mm-yy",
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true
	});

	
	$('#timpicker').datetimepicker({
		pickDate : false
	});

	
	
/*$("#vehicleid").autocomplete(
				{
				source : function(request, response) {
			
						$.ajax({
							url : "fuelMenu.html?method=searchVehicle",
							data : {
								    searchterm : request.term
							
									},
									
									success : function(data) {
										
										
										
										var result = $.parseJSON(data);
										
                                        
                                        
										response($
												.map(
														result.vehicle,
														function(item) {
														
															return {
																label : item.vehicletype,
																value : item.vehiclecode,
															
															}
														}));
									}
									
								
								});
					},
					select : function(event, ui) {
						var searchterm = ui.item.value;
				vehicleDetail = {
								'searchterm' : searchterm
							};
					var vehicleList = callAjax("fuelMenu.html?method=getVehicleNoBycode",vehicleDetail);
					       
							viewVehicleDetails(vehicleList);
							$("#vehicleid").val(ui.item.label);
							$("#vehiclecode").val(ui.item.value);
							return false;
					}
				});
	   */
	
	$("#vehiclename").change(function(){
		var v_id=$("#vehiclename").val().trim();
		/*alert(v_id);
		$("#vehiclenoid").val(v_id);*/
		$.ajax({

			type : "POST",

			url : "fuelMenu.html?method=getVehicleNoBycode",

			data : {'v_id':v_id},

			async : false,
			
			success : function(data) {

				var result = $.parseJSON(data);

				$('.errormessagediv').hide();
				/*alert(result.status[0].vehicleregno);*/
				$("#vehiclenoid").val(result.status[0].vehicleregno);
				$("#fueltype").val(result.status[0].fuelusedintheengine);

			}

		});
	});

	/*$("#driverid").autocomplete(
		{
			source : function(request, response) {
				
			
				$.ajax({

							url : "fuelMenu.html?method=searchDriver",
							data : {
								searchTerm : request.term
							},
							success : function(data) {
								var result = $
										.parseJSON(data);
							

								response($
										.map(
												result.driver,
												function(
														item) {
													return {
														label : item.driverName,
														value : item.driverCode,
													}
												}));
							}
					
						});
			},
			select : function(event, ui) {
				var searchTerm = ui.item.value;
		vehicleDetail = {
						'searchTerm' : searchTerm
					};
		         $("#drivercode").val(ui.item.value);
				$("#driverid").val(ui.item.label);
				
					return false;
			}
		});*/

	
		

});


function callAjax(urlWithMethod, dataToBeSend) {

	$.ajax({

		url : urlWithMethod,
		data : dataToBeSend,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			jsonResult = result.status;

		}
	});
	return jsonResult;
}

function viewVehicleDetails(vehicleList){

	$("#vehiclenoid").val(vehicleList[0].vehicleregno);

		}



function HideError() 
{
	
document.getElementById("vehicleid").style.border = "1px solid #ccc";
document.getElementById("vehicleid").style.backgroundColor = "#fff";

document.getElementById("date").style.border = "1px solid #ccc";
document.getElementById("date").style.backgroundColor = "#fff";

document.getElementById("timeid").style.border = "1px solid #ccc";
document.getElementById("timeid").style.backgroundColor = "#fff";

document.getElementById("driverid").style.border = "1px solid #ccc";
document.getElementById("driverid").style.backgroundColor = "#fff";

document.getElementById("fueltype").style.border = "1px solid #ccc";
document.getElementById("fueltype").style.backgroundColor = "#fff";

document.getElementById("quantityid").style.border = "1px solid #ccc";
document.getElementById("quantityid").style.backgroundColor = "#fff";

document.getElementById("priceid").style.border = "1px solid #ccc";
document.getElementById("priceid").style.backgroundColor = "#fff";

document.getElementById("meterreading").style.border = "1px solid #ccc";
document.getElementById("meterreading").style.backgroundColor = "#fff";

document.getElementById("locationid").style.border = "1px solid #ccc";
document.getElementById("locationid").style.backgroundColor = "#fff";

document.getElementById("vehiclenoid").style.border = "1px solid #ccc";
document.getElementById("vehiclenoid").style.backgroundColor = "#fff";

}