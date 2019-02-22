
$(document).ready(function() 
		
		{
	
	
	$('.accordHead').click(function() {
		var displaypro = $(this).next('div').css('display');
		if (displaypro == 'none') {
			$(this).next('div').css({
				'display' : 'block'
			});
		} else {
			$(this).next('div').css({
				'display' : 'none'
			});
		}
	});
	
	
	$(".check")
	.click(
			function()
			{
				
				var id = this.id;
				
				 $("#checkval").val(id);
			});
	
	
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
										url : "termfee.html?method=deleteTermDetails",
										data : datalist,
										async : false,

										success : function(response) {
											
											var result = $.parseJSON(response);
											
											if (result.jsonResponse == "Term Details Deleted Successfully")
											{
												
												
												window.location.href = "adminMenu.html?method=termList&result="+ result.jsonResponse;
												
											}
											if(result.jsonResponse == "Term Details not Deleted Successfully")
											{
												$(".errormessagediv").show();
												$(".validateTips").text(result.jsonResponse);
											}
											
											else if(result.jsonResponse == "Term Details Already Mapped")
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
	
	
	
	
		
	

	
	$("#mapedit")
	.click(
			function() {
				 var id=$("#checkval").val();
				 alert(id);
				 if ($('input[type=checkbox]:checked').length == 0) {
			            alert('not selected!');
			            return false;
			        }
			        else if($('input[type=checkbox]:checked').length >= 2){
			            alert('Select any one!');
			        }
				
				else {
					
					
					window.location.href = "teachmap.html?method=editclassdetails&id="+ id;
					
	

}
			 	   	
				});
	
	
	
	
	$("#search")
	.click(
			function() 
			
			{
				var searchvalue=$('#searchvalue').val();
				
				
				window.location.href = "adminMenu.html?method=termList&searchvalue="+ searchvalue;
				
				
				
			});
					
			

			});
	
	
	
	
	
	



	
	
	