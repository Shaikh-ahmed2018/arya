function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
function myFunction() {
			         window.location.href="adminMenu.html?method=occupationDetails&searchname="+$("#searchname").val();   
				  }


$(document)
		.ready(
				function() {
					
					
					if($("#allstudent tbody tr").length==0){
						$("#allstudent tbody").append("<tr><td ColSpan='2'>No Records Found</td></tr>");
					}
					
					
				$("#searchname").focus(function(e){
					
					if(e.keycode==13)
						 window.location.href="adminMenu.html?method=occupationDetails&searchname="+$("#searchname").val();
				});	
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
				
				
					$("#editOccupation").click(function(){
						 
						$(".successmessagediv").hide();
						
						var cnt = 0;

						$('.select:checked').map(function() {
											getData = $(this).attr("id");
											
											cnt++;
										});
						
											
						if (cnt == 0 || cnt > 1) {
							
		     				$(".errormessagediv").show();
		     				$(".validateTips").text("Select any one Occupation");
		     				
		     				return false;
		     			} 
		     			else
		     				{
		     			
							var occupationId = getData;
							
							
							window.location.href = "religionCastCasteCategory.html?method=editOccupation&occupationId="+ occupationId;
							var result = $.parseJSON(response);
		     				}

					});
					$('#selectall').change(function(){
						$(".select").prop('checked', $(this).prop("checked"));
					});
					
					$("#delete").click(function(e){
						var cnt=0;
						 occupationList=[];
						$('input[name="select"]:checked').each(function() {
							
							var getData = $(this).attr("id");
							
							occupationList.push(getData);
							cnt++;
						});
						
						if(cnt==0){
							$(".errormessagediv").show();
		     				$(".validateTips").text("Select any one Occupation");
		     				
		     				return false;
							
						}else{
							
							 $("#dialog").dialog("open");
								$("#dialog").empty();
								$("#dialog").append("<p>Are you sure to delete?</p>");
						}
									
					});
					$("#dialog").dialog({
						
						
					     autoOpen: false,
					     modal: true,
					     title:'Ocupation details',
					     buttons : {
					          "Yes" : function() {
									
									$.ajax({
												type : 'POST',
												url : "religionCastCasteCategory.html?method=deleteOccupation",
												data : {'occupationList':occupationList},
												success : function(
														response) {
													var result = $
															.parseJSON(response);
													
													
													$('.errormessagediv').hide();
													
													if(result.status=="Selected Occupation Deleted"){
														
														$(".successmessagediv").show();
														$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
														$(".validateTips").text("Delete Unmapped Occupation Details Progressing...");
														$(".successmessagediv").css({
															'z-index':'9999999'
														});
													}
													
													else if(result.status=="Selected Occupation Not Deleted"){
														$('.successmessagediv').show();
														$('.validateTips').text("Selected Occupation is not Deleted");
														$(".successmessagediv").css({
															'z-index':'9999999'
														});
													}
													
													 setTimeout(function(){
														   
														 window.location.href="adminMenu.html?method=occupationDetails";
													 
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
				
});				