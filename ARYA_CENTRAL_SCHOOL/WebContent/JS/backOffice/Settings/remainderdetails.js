$(document).ready(function() 
		
		{
	
	
	
	$("#delete")
		.click(
				function() 
				
				{
					
					var cnt = 0;

					$(
							'input.academic_Checkbox_Class:checkbox:checked')
							.map(
									function() {

										var term_id = $(
												this).attr(
												"id");
										
										var split_id = term_id
												.split('_');
										getData = split_id[1]
												.split(',');

										cnt++;
									});
					
					if (cnt == 0 || cnt > 1) {
						
						$(".successmessagediv").hide();
						$(".errormessagediv1").show();
						$(".validateTips1").text("Select Any One Checkbox");
						

					} 
					
					
					else {
						
						
						var x = confirm("Are you sure want to delete this fee details");
						if (x)
						{
							
							var datalist = 'name=' + getData[0];
							


							$
									.ajax({
										type : "GET",
										url : "remainder.html?method=deleteRemainderDetails",
										data : datalist,
										async : false,

										success : function(response) {
											
											var result = $.parseJSON(response);
											
											if (result.jsonResponse == "Remainder Details Deleted Successfully")
											{
												
												
												window.location.href = "adminMenu.html?method=remainderdetails&result="+ result.jsonResponse;
												
											}
											if(result.jsonResponse == "Remainder Details Not Deleted Successfully")
											{
												$(".errormessagediv").show();
												$(".validateTips").text(result.jsonResponse);
											}
											
											
											
											 setTimeout(function(){										 
												   
												 location.reload();
											 
											 },3000);
											
											
										
											
										}
									});

						}

					
					}
				 	   	
					
					
					
					
					
					
				});
	
	
	
	
		
	

	
	$("#termedit")
	.click(
			function() {

				var cnt = 0;

				$(
						'input.academic_Checkbox_Class:checkbox:checked')
						.map(
								function() {
									
									var term_id = $(
											this).attr(
											"id");
									
									var split_id = term_id
											.split('_');
									getData = split_id[1]
											.split(',');

									cnt++;
								});
				
				
				
				if (cnt == 0 || cnt > 1) {
					

					$(".successmessagediv").hide();
					$(".errormessagediv1").show();
					$(".validateTips1").text("Select Any One Checkbox");

				} 
				
				
				else {
					
					
					var id = getData[0];
					
					window.location.href = "remainder.html?method=editremainderDetails&id="+ id;
					
				}
			 	   	
				});
	
	
	
	
	$("#search")
	.click(
			function() 
			
			{
				var searchvalue=$('#searchvalue').val();
				
				
				window.location.href = "adminMenu.html?method=remainderdetails&searchvalue="+ searchvalue;
				
				
				
			});
					
			
	
	$("#excelDownload").click(function(){
		
		var searchvalue=$('#searremainderid').val();
		
		
		
		
		window.location.href = "remainder.html?method=remainderdetailsXLSDownload&searchvalue="+ searchvalue;
		
		
		
	});
	
	
	$("#pdfDownload").click(function(){
		
		
		var searchvalue=$('#searremainderid').val();
		
		
		
		
		window.location.href = "remainder.html?method=remainderdetailsPDFDownload&searchvalue="+ searchvalue;
		
	});
	
	
	
	
	
	

			});
	
	
	
	
	
	



	
	
	