	$(document).ready(function() {
		
	
		$(".errormessagediv").hide();
		
		
	   $('#delete').click(function() {
		   
	 		
	 		
	 		var FuelCode = [];
	 		var count=0;
			$('input.fuelDetails_Checkbox_Class:checkbox:checked')
					.map(
							function() {
								var check_id = $(
										this).attr(
										"id");
								var split_id1 = check_id
										.split('_');
								var split_id = split_id1[1]
										.split(',');
								FuelCode
										.push(split_id[0]);
								count++;
							});
			if (count == 0 || count > 1) {

				$(".errormessagediv").show();
				$(".validateTips")
						.text("Select any fuel Details");
				return false;

			} 
			else if (FuelCode != "") {
				var fuelcode1 = FuelCode.toString();
				var x = confirm("Are you sure you want to delete this Fuel Details");

				if (x) {
					var termcodeToBeDeleted = {
						"FuelCode" : fuelcode1
					};
					$
							.ajax({
								type : 'POST',
								url : "fuelMenu.html?method=deleteFuelDetails",
								data : termcodeToBeDeleted,
								async : false,
								success : function(
										data) {
									var result = $
											.parseJSON(data);
                                                                        
									if (result.status == "Fuel Maintenance Details Deleted Successfully") {
										
										                                     
										$(".errormessagediv").hide();
																				
										$(".successmessagediv").show();
										$(".successmessage").text(result.status);
										
					//window.location.href = "adminMenu.html?method=fuelMaintenance&result="+ result.status;

									}else {
										
										
										$(".successmessagediv").show();
										
										$(".validateTips").text(result.status);
									}

									setTimeout(
											function() {

												location.reload();

											}, 3000);
								}
							});
				}
			} else {
				$(".validateTips")
						.html(
								"Select any Fuel Details to Delete");
			}
		});
	 			
	   
	   $('#edit').click(function() {

					
					$(".errormessagediv").hide();
					$(".errormessagediv1").hide();

					var count = 0;
					
				    
				   
					$('input.fuelDetails_Checkbox_Class:checkbox:checked')
							.map(function() {

										var checkdep_id = $(
												this).attr(
												"id");
										var split_id = checkdep_id
												.split('_');
										getData = split_id[1]
												.split(',');
										count++;
									});

					if (count == 0 || count > 1) {

						$(".errormessagediv").show();
						$(".validateTips")
								.text(
										"Select any one Fuel Details");

						return false;

					}

					else {

						var id = getData[0];

						window.location.href = "fuelMenu.html?method=editFuelDetails&name="+id;

					}
				});
	   
	   
	   $('#search').click(function() {

			var searchname = $("#searchname").val().trim();

			myFunction();
			$("#searchname").val("");
				
		});
	   						
		$('#excelDownload')
		.click(
				function() {
					
					window.location.href = 'fuelMenu.html?method=downloadFuelMaintenanceDetailsXLS';
					
				});
		
		$("#pdfDownload").click(function(){
			
			window.location.href = "fuelMenu.html?method=downloadFuelMaintenanceDetailsPDF";
				
		});	

		
		
	
});
	
	function myFunction() {

		document.getElementById("myForm").submit();

		}


