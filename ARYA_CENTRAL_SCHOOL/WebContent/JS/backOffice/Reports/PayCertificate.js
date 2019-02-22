$(document).ready(function() {
	
	$("#accyear").val($("#hacademicyaer").val());
	
	$("#pdfDownload").click(function(){
		
		
		var accyear = $('#accyear').val();
		
		var teachername = $('#teachername').val();
		var monthid = $('#monthid').val();
		
		
		
		
		if(accyear==""){
			
			$(".errormessagediv").show();
			$(".validateTips").text("Select Academic Year");
			
			return false;
		}
		
		
		if(monthid==""){
			
			$(".errormessagediv").show();
			$(".validateTips").text("Select Month");
			
			return false;
		}
		
		if(teachername==""){
			
			$(".errormessagediv").show();
			$(".validateTips").text("Select Teacher Name");
			
			return false;
		}
		
		else{
			
			
			window.location.href = 'staffpayreport.html?method=staffPayCertificatePDFReport&accyear='
				+accyear
				+ ' &teachername='
				+teachername
				+ ' &monthid='
				+ monthid;
			
			
		}
		
		
		
		
	});

});