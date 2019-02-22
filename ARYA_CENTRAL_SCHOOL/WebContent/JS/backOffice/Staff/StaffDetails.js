$(document)
		.ready(
				function() {
					
					if($("#allstudent tbody tr").length ==0){
						$("#allstudent tbody").append("<tr><td colspan='9'>NO Records Found</td></tr>");
					}
					
					 setTimeout("removeMessage()" ,3000);
						setInterval(function() {
							$(".errormessagediv").hide();
						}, 3000);

					$('#search')
					.click(
							function() {
								
								
								document.getElementById("searchForm").submit();

	 							/*var searchTerm=$("#searchname").val().trim();
								
								if(searchTerm==""){
								
									$('.errormessagediv').show();
									$('.validateTips').text("Enter search term");
								
								}else{
									
									document.getElementById("searchForm").submit();
									
								}
						   */
							
							
							
							});
					
					/*$('#deleteTeacher')
					.click(
							function() {
								
								
								
								
								if (teacherId==undefined)  {
									$(".errormessagediv").show();
									$(".validateTips").text(
											"Check any one checkbox");
									return false;

								} else {
									
									var datalist = {
											"teachercode" :teacherId
										};
									
									$
									.ajax({
										type : 'POST',
										url : "teacherregistration.html?method=deleteStaffDetails",
										data : datalist,
										async : false,
										success : function(data) {
											
											var result = $.parseJSON(data);
											
											if (result.status == true) {
												var msg = "Staff details Deleted Successfully";
												
												window.location.href = "adminMenu.html?method=staffList&message="+msg;

											} else {
												$(".errormessagediv").show();
												$(".validateTips").text("Staff details Deletion Failed");
											}
										}

									});
									
								}
								
							});*/
					
					
					
					
					
					
					
					
					$("#selectall").change(function(){

						 $(".select").prop('checked', $(this).prop("checked"));
						 
						 
						});
					
					
					 var studentIdlist=[];
					
					
					$("#deleteTeacher").click(function(){
						
						
						var count =0; 
						 studentIdlist=[];
				 		$(".select:checked").each(function(){
							 
							var list=$(this).attr("id");
							studentIdlist.push(list);
							count ++;
							 
						 });	
				 		
				 		
				 		/*var teacherId=$('input[name="selectBox"]:checked').val();*/
				 		
					
				 	if(count == 0)	{
				 		
				 		$('.errormessagediv').show();
						$('.validateTips').text("Select Staff to Delete");
						$('.errormessagediv').delay(3000).slideUp();
				 		
				 	}
				 	else 	{
						
						  $("#dialog").dialog("open");
						  $("#dialog").empty();
						  $("#dialog").append("<p>Are you sure to delete?</p>");
					}
					
				 	
				 	$(".select").prop("checked",false);
					});
					
					
					$("#dialog").dialog({
				 		
				 		
				 		 autoOpen: false,
					     modal: true,					    
					     title:'Staff Details',
					     buttons : {
					          "Yes" : function() {
					        	  
					        		$.ajax({
										type : 'POST',
										url : "teacherregistration.html?method=deleteStaffDetails",
										data : {"teachercode":studentIdlist.toString()},
										success : function(
												response) {
											var result = $.parseJSON(response);
											
											
											$('.errormessagediv').hide();
											
											if (result.status == true) {
												$(".errormessagediv").hide();
												$(".successmessagediv").show();
												$(".validateTips").text("Deleting Record Progressing...");

											} else {
												$(
												".successmessagediv").show();
												$(".validateTips").text(result.status);
											}
											setTimeout(function(){
												   
												 window.location.href="adminMenu.html?method=staffList";
											 
											 },2000);
										
										}

									});  
					        	  
					        		 $(this).dialog("close");
					 	  
					          },
				 		
					          "No" : function() {
					        	  $("#selectall").prop("checked",false);
						            $(this).dialog("close");
						          }
				 		
					     }
				 	});
					
					
					
					
					
					
					

					$('#editTeacher')
					.click(
							function() {
								
								var cnt = 0;

								$('input[type="checkbox"]:checked')
										.map(
												function() {

													/*var term_id = $(this).attr("id");*/
													 getData = $(this).attr("id");
													/*var split_id = term_id
															.split('_');
													getData = split_id[1]
															.split(',');*/

													cnt++;
												});

								if (cnt == 0 || cnt > 1) {

									$(".errormessagediv").show();
									$(".validateTips")
											.text(
													"Select Any One Staff");

								}
								
								/*var teacherId=$('input[name="selectBox"]:checked').val();
								if (teacherId==undefined) {
									$(".errormessagediv").show();
									$(".validateTips").text(
											"Check any one checkbox");
									return false;

								}*/ else {
									
									var name= getData;
								
									window.location.href = "teacherregistration.html?method=getTeacherDetails&teachercode="+name;
								
								}
							});
					
					
					
					$('#excelDownload')
					.click(
							function() {
								
								window.location.href = 'teacherregistration.html?method=downloadStaffDetailsXLS';
								
							});
					$("#pdfDownload").click(function(){
            			
            			window.location.href = "teacherregistration.html?method=downloadStaffDetailsPDF";
            				
            		});
							
					
					
					
					
				});



function salaryDeatails(){
	
	window.location.href = "teacherregistration.html?method=getTeacherSalary";
	
}

function removeMessage() {
	$(".successmessagediv").hide();
	
	

}