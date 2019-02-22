$(document).ready(function() {
	
	
	$(".remarks").show();
	
	$(".meeting").hide();
	$(".birthday").hide();
	
	
	
	
	
	var s1=$("#hiddenid").val();
	var s2=$("#hiddenmeetid").val();
	var s3=$("#hiddenbdayid").val();
	
	
	
	if($("#hiddenid").val()!="")
	{
		
		
		$(".remarks").hide();
		$(".birthday").hide();
		$(".meeting").show();
		
		$("#remarktype option[value="+$("#hiddenid").val().trim()+"]").attr("selected",'true');
	}

	

	if($("#hiddenbdayid").val()!="")
	{
		
		$(".remarks").hide();
		$(".meeting").hide();
		$(".birthday").show();
		
		$("#remarktype option[value="+$("#hiddenbdayid").val()+"]").attr("selected",'true');
	}
	
	
	
	

	$("#remarktype").change(function() {
		
		var remarktype = $('#remarktype').val();
		
		if (remarktype == "meeting") {
			$(".remarks").hide();
			$(".birthday").hide();
			$(".meeting").show();
		}

		if (remarktype == "Remarks") {
			$(".meeting").hide();
			$(".birthday").hide();
			$(".remarks").show();
			
		}
		if (remarktype == "bday") {
			$(".meeting").hide();
			$(".remarks").hide();
			$(".birthday").show();
			
			
		}
		
		
		

		if (remarktype == "Remarks") {
			$(".remarks").show();
			window.location.href = "adminMenu.html?method=communicationRemarksList";
			
		}
		
		if (remarktype == "meeting") {
			$(".meeting").show();
			window.location.href = "adminMenu.html?method=getmeeting";
			
		}
	
		if (remarktype == "bday") {
			$(".birthday").show();
			window.location.href = "adminMenu.html?method=getbdaylist";
			
		}
	
		if (remarktype == "remainder") {
			/*$(".birthday").show();*/
			window.location.href = "adminMenu.html?method=remainderdetails";
			
		}
		
		

	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

});