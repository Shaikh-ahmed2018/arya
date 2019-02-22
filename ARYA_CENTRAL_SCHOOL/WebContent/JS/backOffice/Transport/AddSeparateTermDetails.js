var dateexist=null;
var maxDate=0;
minDate=0;
function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction()

{
	
	var status = false;
	var id=$('#termmasterid').val();
	var name = $('#termname').val();
	var description = $('#termdescription').val();
	var startdate = $('#startdate').val();
	var enddate = $('#enddate').val();   
	var transId=$('input[name=trans]:checked').val();
	
	
	if(name=="" || name==null || name.trim()=="")
		
	{
	
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Enter Term Name");
		document.getElementById("termname").style.border = "1px solid #AF2C2C";
		document.getElementById("termname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
		
		return false;
	
	}
	
	
	
	if (startdate == "" || startdate == undefined) {
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Select the StartDate");
		document.getElementById("startdate").style.border = "1px solid #AF2C2C";
		document.getElementById("startdate").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
		return false;
	}
	
	

	if (enddate == "" || enddate == undefined) {
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Select the EndDate");
		document.getElementById("enddate").style.border = "1px solid #AF2C2C";
		document.getElementById("enddate").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
		return false;
	}
	
	
		datalist = {"name" : name, "id" :id, "locationId":$("#locationname").val(),"accyear":$("#Acyearid").val()},
		$.ajax({
			
		type : "POST",
		
		url : "transport.html?method=getTermnamecount",
		
		data : datalist,
		async:false,
		
		success : function(data)
		
			{
			
				var result = $.parseJSON(data);
				
				if(result.message)
					
					{
					
						$('#termname').val("");
				
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
	
	
	$("#termname").keydown(function(e){
		 var key=e.keyCode;
			if(!((key == 8) || (key == 16) || (key == 189) || !(e.shiftKey && (key >= 48 && key <= 57)) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90) ||  (key >= 96 && key <= 105))){
				  e.preventDefault();
				$(".errormessagediv1").show();
				$(".validateTips1").text("Sorry You Can't Enter Special Character");
				return false;
			}
	});
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv1").hide();
	}, 3000);
	
	
	
	var trans = $('#radio').val();
	
	if (trans == "YES") {
		$("#transIdY").attr("checked", true);
	} else if (trans == "NO") {
		$("#transIdN").attr("checked", true);
	}
	
	$("#locationname").change(function(){
		$('#startdate').datepicker('destroy');
		$('#enddate').datepicker('destroy');
		dateRange($("#Acyearid").val());
		
		$("#startdate").datepicker({
			maxDate:maxDate-1,
			minDate:minDate,
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			onSelect:function(dateStr){
				
				
				        var min = $(this).datepicker('getDate'); // Get selected date
				        $("#enddate").datepicker('option', 'minDate', min || $("#lastDate").val()); // Set other min, default to today
				   
				
			}

		});

		$("#enddate").datepicker({
			maxDate:maxDate-1,
			minDate:minDate,
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			onSelect:function(){
				
				var max = $(this).datepicker('getDate'); // Get selected date
		        $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
			}

		});
		
	});
	
	$("#Acyearid").change(function(){
		$('#startdate').datepicker('destroy');
		$('#enddate').datepicker('destroy');
		dateRange($(this).val());
		
		$("#startdate").datepicker({
			maxDate:maxDate-1,
			minDate:minDate,
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			onSelect:function(dateStr){
				
				
				        var min = $(this).datepicker('getDate'); // Get selected date
				        $("#enddate").datepicker('option', 'minDate', min || $("#lastDate").val()); // Set other min, default to today
				   
				
			}

		});

		$("#enddate").datepicker({
			maxDate:maxDate-1,
			minDate:minDate,
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			onSelect:function(){
				
				var max = $(this).datepicker('getDate'); // Get selected date
		        $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
			}

		});
		
	});
	
	$("#save")
		.click(
				function() 
				
				{
					
					var id=$('#termmasterid').val();
					var name = $('#termname').val();
					var description = $('#termdescription').val();
					var startdate = $('#startdate').val();
					var enddate = $('#enddate').val();
					var transId='Y';
					
					if (myFunction())
					
					{
						
					datalist = {"name" : name, 
							"description" : description, 
							"startdate":startdate, 
							"enddate":enddate, 
							"transId":transId,
							"locationId":$("#locationname").val(),
							"academic_year":$("#Acyearid").val(),
							"id" :id},
					
					$.ajax({
						
					type : "POST",
					
					url : "transport.html?method=addtermSeparatefeedetails",
					
					data : datalist,
					
					success : function(data)
					
						{
						
						
						var result = $.parseJSON(data);


						if (result.jsonResponse == "Term Details Added Successfully") {
							$(
									".errormessagediv")
									.hide();
							$(
									".successmessagediv")
									.show();
							$(
									".validateTips")
									.text(
											"Adding Record Progressing...");

							setTimeout(
									function() {

										window.location.href = "adminMenu.html?method=separateTransportTermList";

									},
									3000);

						}

						if (result.jsonResponse == "Term Details not Added Successfully") {

							$(
									".errormessagediv")
									.hide();
							$(
									".successmessagediv")
									.show();
							$(
									".validateTips")
									.text(
											"Term Details not Added Successfully");

							setTimeout(
									function() {

										window.location.href = "transport.html?method=addtermSeparatefeedetails";

									},
									3000);

						}
						if (result.jsonResponse == "Term Details Updated Successfully") {
							$(
									".errormessagediv")
									.hide();
							$(
									".successmessagediv")
									.show();
							$(
									".validateTips")
									.text(
											"Updating Record progressing..");

							setTimeout(
									function() {

										window.location.href = "adminMenu.html?method=separateTransportTermList";

									},
									2000);

						}

						if (result.jsonResponse == "Term Details not Updated Successfully") {

							$(
									".errormessagediv")
									.hide();
							$(
									".successmessagediv")
									.show();
							$(
									".validateTips")
									.text(
											"Term Details not Updated Successfully");

							setTimeout(
									function() {

										window.location.href = "transport.html?method=addtermSeparatefeedetails";

									},
									3000);

						}

									
						
						}
			 	   	
				});
					
				
					}
				
				
				});
	
	
	
	
	$("#view").click(function(event)
			
			
			{
				window.location.href = "adminMenu.html?method=separateTransportTermList";
				
			});
	
		});

function HideError() 
{
document.getElementById("termname").style.border = "1px solid #ccc";
document.getElementById("termname").style.backgroundColor = "#fff";
document.getElementById("startdate").style.border = "1px solid #ccc";
document.getElementById("startdate").style.backgroundColor = "#fff";
document.getElementById("enddate").style.border = "1px solid #ccc";
document.getElementById("enddate").style.backgroundColor = "#fff";
}
function dateRange(accyear){
	
	$.ajax({
		type:"POST", 
		url:"termfee.html?method=transportdaterange",
		data:{"accyear":accyear,
		"locationId":$("#locationname").val()	
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			minDate=result.startDate.split(",")[0];
			maxDate=result.startDate.split(",")[1];
			$("#lastDate").val(result.startDate.split(",")[0]);
			$("#enddates").val(result.startDate.split(",")[1]);
		}
	
	});
}