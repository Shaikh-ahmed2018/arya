	$(document).ready(function() {
		

		var hdrivercode=$("#hdrivercode").val();
		if(hdrivercode!="" || hdrivercode!=null){
			
			$("#driver option[value=" + hdrivercode + "]").attr('selected', 'true');
			getMappedVehicle(hdrivercode);
		}
		
		

	
		$('#save').click(
			function() {
				
				
				var driverVehicleMapCode = $("#hsno").val();
				var driverID = $("#driver").val();
				
				var mappedVehicles=[];
				var count=0;
				
				$("#copy option").each(function()
					{
					
					count++;
						mappedVehicles.push(this.value);
					});
				
				if(driverID==""){
					
					$('.errormessagediv').show();
					$('.validateTips').text("Select driver");
				}else if(mappedVehicles==""){
					$('.errormessagediv').show();
					$('.validateTips').text("Mapped vehicle should not empty");
				}else if(count>1){
					$('.errormessagediv').show();
					$('.validateTips').text("Map only one vehicle");
					
				}
				
				else{
					$('.errormessagediv').hide();
					var datalist={
							"DriverVehicleMapCode" : driverVehicleMapCode,
							"DriverId": driverID,
							"MappedVehicles": mappedVehicles.join(",")
							
					};
					
					if(driverVehicleMapCode=="" || driverVehicleMapCode==undefined){
				
					$.ajax({
						type : "GET",
						url : "vehicledrivermap.html?method=mapVehicle",
						data :datalist,
						async : false,
						success : function(data) {
							var result = $.parseJSON(data);
							
							$('.errormessagediv').hide();
							
							if(result.Status=="true"){
								
								$('.successmessagediv').show();
								$('.successmessage').text("Driver vehicle mapping done successfully");
								
							}else{
								
								$('.errormessagediv').show();
								$('.validateTips').text("Driver vehicle mapping not done,Try again");
							}
							
							
							 setTimeout(function(){
								   
								 location.reload();
							 
							 },3000);
						
						}
					});
				}else{
					
					$.ajax({
						type : "GET",
						url : "vehicledrivermap.html?method=updateMappedVehicle",
						data :datalist,
						async : false,
						success : function(data) {
							var result = $.parseJSON(data);
							
							$('.errormessagediv').hide();
							
							if(result.Status=="true"){
								
								$('.successmessagediv').show();
								$('.successmessage').text("Driver vehicle mapping updated successfully");
								
							}else{
								
								$('.errormessagediv').show();
								$('.validateTips').text("Driver vehicle mapping not updated,Try again");
							}
							
							
							 setTimeout(function(){
								   
								 location.reload();
							 
							 },3000);
						
						}
					});
				
					
				}
					
				}
				
					
			
		
	
});
	
	
	
	$('#edit').click(function() {
		
		
		var count = 0;
		var mappingSno = null;

		$('input[name="selectBox"]:checked').each(function() {
			count = count + 1;
			mappingSno = this.value;
		});
		

		if (count > 1 || count == 0) {
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select any one Record");

		} else {
			
			window.location.href="vehicledrivermap.html?method=editVehicleDriverMapping&MappingSno="+mappingSno;
			
			
		}

				
	});
	
	$("#searchname").click(function(){
		
		var searchTerm=$("#search").val().trim();
		
			window.location.href="adminMenu.html?method=getVehicleDriverMapping&searchTerm="+searchTerm;	
			
		
	});

	
	$("#delete").click(function() {
		
		var checkboxArray = document.getElementsByName('selectBox');
		
		var updaterowsArray = new Array();

		for ( var i = 0; i < checkboxArray.length; i++) {
			if (checkboxArray[i].checked == true) {
				updaterowsArray.push(checkboxArray[i].value);
			}
		}
		if (updaterowsArray.length <= 0) {
			$("#errordiv").show();
			$("#errordiv").text(" Select any record to delete");
			$('.error-box').css({
				'visibility' : 'visible'
			});
			return false;
		} else {

			var x = confirm("Are you sure you want to delete this record");
			if (x) {

				var vehicleDetails = {'vehicle_id':updaterowsArray.join(",")};
				
				$.ajax({
							type : "GET",
							url : "vehicledrivermap.html?method=deleteVehicleDriverMapping",
							data : vehicleDetails,
							async : false,

							success : function(data) {
								var result = $.parseJSON(data);
								
								if(result.Status=="true"){
									
									$('.successmessagediv').show();
									$('.successmessage').text("Selected record deleted successfully");
									
									 setTimeout(function(){
										   
										 window.location = "adminMenu.html?method=getVehicleDriverMapping";
									 
									 },3000);
									
								}else{
									
									$('.errormessagediv').show();
									$('.validateTips').text("Selected record not deleted,Try again");
								}
								
								
							

							}
						});

			}
		}
	});
	
	
	});
	
	
	
	function selectAll() {
		var selectall = document.getElementsByName("selectall");
		var checkboxArray = document.getElementsByName('selectBox');
		if (selectall[0].checked == true) {
			for ( var i = 0; i < checkboxArray.length; i++) {
				checkboxArray[i].checked = true;

			}
		} else {
			for ( var i = 0; i < checkboxArray.length; i++) {
				checkboxArray[i].checked = false;
			}
		}
	}
	
	
	function getMappedVehicle(driver) {
		
		$.ajax({
		      type : "POST",
				url : "vehicledrivermap.html?method=getAvailableVehicles",
				data:{"DriverCode":driver},
				success : function(response) {
					var result = $.parseJSON(response);
					
					$("#original").empty();
					for ( var j = 0; j < result.AvailableVehicleList.length; j++) {
						$("#original")
								.append(
										'<option value="'
												+ result.AvailableVehicleList[j].vehiclecode
												+ '">'
												+ result.AvailableVehicleList[j].vehiclename
												+ '</option>');
					}
				}
			});
		
		
		$.ajax({
		      type : "POST",
				url : "vehicledrivermap.html?method=getMappedVehicles",
				data:{"DriverCode":driver},
				success : function(response) {
					var result = $.parseJSON(response);
					
					$("#copy").empty();
					
					for ( var j = 0; j < result.MappedVehicleList.length; j++) {
						$("#copy")
								.append(
										'<option value="'
												+ result.MappedVehicleList[j].vehiclecode
												+ '">'
												+ result.MappedVehicleList[j].vehiclename
												+ '</option>');
					}
				}
			});
	}
	
	

