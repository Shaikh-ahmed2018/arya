$(document).ready(function(){

 $("#Edit").click(function(){

		var count = 0;
		

		/*$('input[name="checkboxname"]:checked').map(function() {*/
			
			$('input[type="checkbox"]:checked')
			.map(
					function() {
						
						getData = $(this).attr("id");
						
						count++;
			/*count = count + 1;
			event_Code = this.id;*/
		});

		if (count > 1 || count == 0) {
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Any One Acadamic Year Plan");
			
			return false;

		} else {
			
			var event_Code = getData;
			
			window.location.href="acadamicYearPlan.html?method=editAcadamicYearPlan&Event_Code="+event_Code;
			
			
		}

 });
 
 $("#selectall").change(function(){

	 $(".select").prop('checked', $(this).prop("checked"));
	 
	 
	});
 
 var event_list=[];
 
 $("#delete").click(function(){
	 
	    var count = 0;
	   
		var event_Code = null;

		$(".select:checked").each(function(){
			 
			var list=$(this).attr("id");
			event_list.push(list);
			count ++;
			 
		 
		});

	 	if(count == 0)	{
	 		
	 		$('.errormessagediv').show();
			$('.validateTips').text("Select Event to Delete");
			$('.errormessagediv').delay(3000).slideUp();
	 		
	 	}
	 	else 	{
			
			  $("#dialog").dialog("open");
			  $("#dialog").empty();
			  $("#dialog").append("<p>Are you sure to delete?</p>")
		}
		
	});

	$("#dialog").dialog({
		
		
 		
		 autoOpen: false,
	     modal: true,
	     title:'Delete Activities Details',
	     buttons : {
	          "Yes" : function() {
	        	  
	        		$.ajax({
						type : 'POST',
						url : "acadamicYearPlan.html?method=deleteAcadamicYearPlan",
						data : {"event_list":event_list.toString()},
						success : function(
								response) {
							var result = $
									.parseJSON(response);
							
							
							$('.errormessagediv').hide();
							
							if (result.status == "Acadamic Year Plan Deleted Successfully") {

								$(".errormessagediv").hide();
							//	$(".successmessagediv").attr("style","width:150%;margin-left:300px;").show();
								$(".successmessagediv").show();
								$(".successmessage").text("Delete Unmapped Activity Plan Progressing...");

							} else {
								$(".successmessagediv").show();
								$(".validateTips").text(result.status);
							}
							
							 setTimeout(function(){
								   
								 window.location.href="adminMenu.html?method=acdamicYearPlanList";
							 
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
 
 
 
 
	$("#search").click(function(){
		
		var searchTerm=$("#searchname").val().trim();
		
			
		window.location.href="adminMenu.html?method=acdamicYearPlanList&searchTerm="+searchText+"&school="+$("#school").val();	
			
		
		
	});
	$('#excelDownload')
	.click(
			function() {
				
				window.location.href = "acadamicYearPlan.html?method=AcadamicYearPlanXLS";
				
			});
	
	$("#pdfDownload").click(function(){
		window.location.href = "acadamicYearPlan.html?method=AcadamicYearPlanPDF";
			
	});	

 
	
	
	
	
});