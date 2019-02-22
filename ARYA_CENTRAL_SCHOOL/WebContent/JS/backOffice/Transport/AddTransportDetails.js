$(document).ready(function()
{

	$(".errormessagediv").hide();
	$("successmessagediv").hide();
	
	$('#saveid').click(function() {
	
		var vehicletype = $("#vehicleType").val().trim();
		var descname = $("#descriptionid").val().trim();
		var hiddenvechileid=$('#transporttypeid').val();
		var updatevehicle = $("#hiddenvechiletype").val().trim();
		

		if (vehicletype == "" || vehicletype == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Transport Type");
			document.getElementById("vehicleType").style.border = "1px solid #AF2C2C";
			document.getElementById("vehicleType").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);          

		} 
		else if(vehicletype.length >50 ){
			$('.errormessagediv').show();
			$('.validateTips').text("Transport Type Too Long!!!");
			document.getElementById("vehicleType").style.border = "1px solid #AF2C2C";
			document.getElementById("vehicleType").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}
				
		else if (validateVechicleType() == 1) {

			$('.errormessagediv').show();
			$('.validateTips').text("Transport Type already Exist");
			return false;
		}
	
		else {

			datalist = {
				"vehicleType" : vehicletype,
				"desc" : descname,
				"upd_vehicle_id":hiddenvechileid,
				"update_vehicle" : updatevehicle,
			},
			//Always pass the values in key-value pair.("key" : value)
			$.ajax({

				type : "POST",

				url : "transport.html?method=insertVehicleType",

				data : datalist,

				async : false,
				
				success : function(data) {

					var result = $.parseJSON(data);//Here result is variable of Object.
					
					if(result.status == "Success")//status is one variable inside Object returning from insertVehicleType().
						{
							$(".successmessagediv").show();
							$(".successmessage").text("Adding Record Progressing...");
						}
					else if(result.status == "UpdateSuccess")
						{
							$(".successmessagediv").show();
							$(".successmessage").text("Updating Record Progressing...");
						}
					else if(result.status == "UpdateFailure")
					{
						$('.errormessagediv').show();
						$('.validateTips').text("Updating Record Failed.");
					}
					else
					{
						$('.errormessagediv').show();
						$('.validateTips').text("Adding Record Failed.");
					}

					setTimeout(function() {

							window.location.href="adminMenu.html?method=transportCategory";
					}, 2000);
				}

			});
			return false;
		}
	});
});

function validateVechicleType(){
	
	if($("#hiddenvechiletype").val()==$("#vehicleType").val())
	{
			status=0;
	}
	else
	{	
	var completeurl =$("#vehicleType").val();

	$.ajax({
		type : "POST",
		url : "transport.html?method=validateVehicleType",
		data : {'completeurl':	completeurl},
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			
			if(result.vehi_available == true)//vehi_available is one variable inside Object returning from validateVehicleType().
			{
				status=1;
			}
			else{
				status=0;
			}
		}
	});
	return status;
}
}
function HideError() 
{
	
document.getElementById("vehicleType").style.border = "1px solid #ccc";
document.getElementById("vehicleType").style.backgroundColor = "#fff";

}