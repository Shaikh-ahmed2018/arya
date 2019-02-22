function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction() {
	    
			         document.getElementById("myForm").submit();   
				  }







$(document).ready(function() {
	
	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
	}
	
	$("#selectall").change(function(){

		 $(".select").prop('checked', $(this).prop("checked"));
		 
		 
		});
	
	
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);
	
	
	
$("#editdriver").click(function(){
		
	
	
		 var cnt = 0;
		 $('input[type="checkbox"]:checked')
			.map(
					function() {
						
						getData = $(this).attr("id");
						
						cnt++;
					});
		
		 if (cnt == 0 || cnt > 1) {
				
				$(".errormessagediv").show();
				$(".validateTips").text("Select any one Driver");
				
				return false;
			} 
		 
		 else
			 {
			 
			 var driverCode = getData;
			 
			
				window.location.href = "driverDetailsPath.html?method=editDriver&driverCode="+driverCode;
				
			
			 }
		 
});
		    


$("#delete").click(function(){
	
	
	var count =0;
	driverCode=[];
		$(".select:checked").each(function(){
		 
		var list=$(this).attr("id");
		driverCode.push(list);
		count ++;
		 
	 });	

	if(count == 0)	{
		
		$('.errormessagediv').show();
	$('.validateTips').text("Select Driver to Delete");
	
		
	}
	else 	{
	
	  $("#dialog").dialog("open");
	  $("#dialog").empty();
	  $("#dialog").append("<p>Are you sure to delete?</p>");
}
	
	

});


$('#excelDownload')
.click(
		function() {
			
			window.location.href = 'driverDetailsPath.html?method=DriverDetailsXLS';
			
		});



$("#dialog").dialog({
	
	
	 autoOpen: false,
   modal: true,					    
   title:'Driver Details',
   buttons : {
  	 "Yes" : function() {
      	  
      		$.ajax({
					type : 'POST',
					url : "driverDetailsPath.html?method=deleteDriver",
					data : {"driverCode" :driverCode.toString()},
					success : function(response) {
						var result = $.parseJSON(response);
						$('.errormessagediv').hide();
						
						if(result.status=="Driver Deleted Successfully"){
							
							$(".successmessagediv").show();
							$(".validateTips").text("Deleting Record Progressing...");
						}
						
						else if(result.status=="Driver not Deleted Successfully"){
							$('.errormessagediv').show();
							$('.validateTips').text("Selected Driver is Mapped Cannot Delete");
						}else
							{
							$('.errormessagediv').show();
							$('.validateTips').text("Selected Driver is Mapped Cannot Delete");
							
							}
						

						setTimeout(function(){
							   
							 window.location.href="adminMenu.html?method=driverList";
						 
						 },3000);
					
					}


				});
      	  
      		 $(this).dialog("close");
	  
        },
	
        "No" : function() {
	            $(this).dialog("close");
	          }
	
   }
});




$("#pdfDownload").click(function(){
	
	window.location.href = "driverDetailsPath.html?method=DriverDetailsPDFReport";
		
});	




	
});
	
	
	
	
	