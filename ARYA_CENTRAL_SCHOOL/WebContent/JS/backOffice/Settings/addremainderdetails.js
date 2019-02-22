function myFunction()

{
	
	var status = false;
	var id=$('#remainderid').val();
	var tittle = $('#remaindername').val();
	var description = $('#remdescription').val();
	var remainderto=$('input[name=remain]:checked').val();
	
	
	if(tittle=="" || tittle==null)
		
	{
	
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Enter Tittle Name");
		document.getElementById("remaindername").style.border = "1px solid #AF2C2C";
		document.getElementById("remaindername").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
		
		return false;
	
	}
	
	
	
	if (remainderto == "" || remainderto == undefined) {
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Select Remainder Type");
		
		return false;
	}
	
	
		datalist = {"name" : tittle, "id" : id},
		
		$.ajax({
			
		type : "POST",
		
		url : "remainder.html?method=getnamecount",
		
		data : datalist,
		async:false,
		
		success : function(data)
		
			{
			
				var result = $.parseJSON(data);
				
				if(result.message)
					
					{
					
						$('#remaindername').val("");
				
						$(".successmessagediv").hide();
				
						$(".errormessagediv1").show();
				
						$(".validateTips1").text("Name Already Exists");
						
						status=false;
						
						
					}
				
					else
					
					{
						
						status=true;
					
					}
				
				
			}
		
		
	   	
	});
		
		return status;	




}


$(document).ready(function() 
		
		{
	
	
	var remtype = $('#radio').val();
	
	if (remtype == "All") {
		$("#remainIdAll").attr("checked", true);
	} else if (remtype == "Backoffice") {
		$("#remainIdBack").attr("checked", true);
	}
	else if (remtype == "Teachers") {
		$("#remainIdTea").attr("checked", true);
	}
	else if (remtype == "Parents") {
		$("#remainIdPar").attr("checked", true);
	}
	
	$("#startdate").datepicker({

		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",

	});

	$("#enddate").datepicker({

		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",

	});
	
	$("#AccStartDate").datepicker({

		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",

	});

	$("#AccEndDate").datepicker({

		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",

	});
	
	
	
	$("#save")
		.click(
				function() 
				
				{
					var id=$('#remainderid').val();
					var tittle = $('#remaindername').val();
					var description = $('#remdescription').val();
					var remainderto=$('input[name=remain]:checked').val();
					if (myFunction())
					
					{
						
					datalist = {"name" : tittle, "description" : description, "remainderto":remainderto, "id" : id},
					
					$.ajax({
						
					type : "POST",
					
					url : "remainder.html?method=addremainderdata",
					
					data : datalist,
					
					success : function(data)
					
						{
						
							var result = $.parseJSON(data);
							
							if(result.jsonResponse=="Remainder Details Not Added Successfully")
								
								{
								
								$(".successmessagediv").hide();
								$(".errormessagediv1").show();
								$(".validateTips1").text("Remainder Details Not Added Successfully");
								
								}
							
							if(result.jsonResponse=="Remainder Details Not Updated Successfully")
								
							{
							
							$(".successmessagediv").hide();
							$(".errormessagediv1").show();
							$(".validateTips1").text("Remainder Details Not Updated Successfully");
							
							}
							
							window.location.href = "remainder.html?method=addremainderdetails&result="+ result.jsonResponse;
							
							setTimeout(function(){
								
								 window.location.href = "adminMenu.html?method=remainderdetails";
							 
							 },3000);
						}
			 	   	
				});
					
				
					}
				
				
				});
	
	
	
	
	$("#view").click(function(event)
			
			
			{
				window.location.href = "adminMenu.html?method=remainderdetails";
				
			});
	
		});


function HideError() 
{
	
document.getElementById("remaindername").style.border = "1px solid #ccc";
document.getElementById("remaindername").style.backgroundColor = "#fff";

}