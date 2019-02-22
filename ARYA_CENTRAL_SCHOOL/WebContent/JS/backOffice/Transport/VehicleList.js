function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction() {
    
    document.getElementById("myForm").submit();   
 }


$(document)
		.ready(
				function() {
					
					if($("#allstudent tbody tr").length ==0){
						$("#allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
					}
					
					$("#vehicle").click(function(){
						$("#vehicleclose").slideToggle();
					});
					setTimeout("removeMessage()", 4000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
					
					
					
					
					$('#editVehicle')
					.click(
							function() {
								
								
								var cnt = 0;

								
								$('input[type="checkbox"]:checked')
										.map(
												function() {
													
													
													
													getData = $(this).attr("id");
													
													cnt++;
												});
								
								
													
								if (cnt == 0 || cnt > 1) {
									
				     				$(".errormessagediv").show();
				     				$(".validateTips").text("Select any one Vehicle to Edit");
				     				
				     				return false;
				     			}  else {
									
									var vehicleIdlist = getData;
									
									
									window.location.href = "transport.html?method=getSingleVehicleDetails&vehicleIdlist="+vehicleIdlist;
								  	
									
								}
							});
					
					$("#selectall").change(function(){

						 $(".select").prop('checked', $(this).prop("checked"));
						 
						 
						});
					
					
					$('#deleteVehicle').click(
							function() {

								var count =0;
								vehicleIdlist=[];
								$(".select:checked").each(function(){

									var list=$(this).attr("id");
									vehicleIdlist.push(list);
									count ++;
								});	
								
								if(count == 0)	{
							 		
							 		$('.errormessagediv').show();
									$('.validateTips').text("Select Vehicle to Delete");
									$('.errormessagediv').delay(3000).slideUp();
							 		
							 	}
							 	else 	{
									
									  $("#dialog").dialog("open");
									  $("#dialog").empty();
									  $("#dialog").append("<p>Are you sure to delete?</p>");
								}
								
								$(".select").prop("checked",false);
								
							});

					
					/*$("#deleteVehicle").click(function(){
						var count =0;
						var vehicleIdlist=[];
						$(".select:checked").each(function(){

							var list=$(this).attr("id");
							vehicleIdlist.push(list);
						});	
						
						if(vehicleIdlist.length!=0){
							$.ajax({
								type : 'POST',
								url : "transport.html?method=deleteVehicleDetails",

								data :{"vehicleIdlist" :vehicleIdlist.toString()},
								
								success : function(response){
									
									var result = $.parseJSON(response);
									$('.errormessagediv').hide();
									
									if(result.status=="Vehicle Deleted SuccessFully"){
										
										$(".successmessagediv").show();
										$(".successmessage").text("Vehicle Deleted SuccessFully");
									}
									
									else if(result.status=="Vehicle not Deleted SuccessFully"){
										$('.successmessagediv').show();
										$('.validateTips').text("Vehicle not Deleted SuccessFully");
									}
									
									 setTimeout(function(){
										   
										 location.reload();
									 
									 },2000);
								
								}

							});
							
							}
							else{
								$('.errormessagediv').show();
								$('.validateTips').text("Select any record to Delete");
								$('.errormessagediv').delay(3000).slideUp();
							}
							
						$(".select").prop("checked",false);
					});
							
							});*/
					
					
					
					
					$('#search')
					.click(
							function() {
							
							var searchname=$("#searchname").val();
							
							window.location.href = "transport.html?method=searchvehicledetails&searchname="+searchname;
							
							});
					
					
					$("#dialog").dialog({
				 		
				 		 autoOpen: false,
					     modal: true,					    
					     title:'Vehicle Details',
					     buttons : {
					    	 "Yes" : function() {
					    		 $.ajax({
										type : 'POST',
										url : "transport.html?method=deleteVehicleDetails",

										data :{"vehicleIdlist" :vehicleIdlist.toString()},
										
										success : function(response){
											
											var result = $.parseJSON(response);
											$('.errormessagediv').hide();
											
											if(result.status=="Vehicle Deleted SuccessFully"){
												
												$(".successmessagediv").show();
												$(".successmessage").text("Deleting Record Progressing...");
											}
											
											else if(result.status=="Vehicle not Deleted SuccessFully"){
												$('.successmessagediv').show();
												$('.validateTips').text("Vehicle not Deleted SuccessFully");
											}
											
											 setTimeout(function(){
												   
												 location.reload();
											 
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

					
					
					
					
					
					
					$('#excelDownload')
					.click(
							function() {
								
								window.location.href = 'transport.html?method=VehicleDetailsXLS';
								
							});
					
					$("#pdfDownload").click(function(){
						
						window.location.href = "transport.html?method=VehicleDetailsPDFReport";
							
					});	

					
					
				});


/*function removeMessage() {
	

	$(".successmessagediv").hide();

}*/