$(document).ready(function() {
	
	
	
	$("#pdfDownload").click(function(){
		
		
		
		

		var teachername =$('#teachername').val();
		var description =$('#description').val();
		
		if(teachername==""){
			
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Teacher Name");
			
			return false;
			
		}
		
		else{
			
			
			window.location.href = 'staffservicereport.html?method=serviceCertificateReport&teachername='
				+teachername
				+ ' &description='
				+description;
				
			
		}
	
		
		
		
		
		
		
		
		
		
		
	});
	
});