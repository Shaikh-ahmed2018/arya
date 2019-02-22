$(document).ready(function(){	 
	
	
$("#excelDownload").click(function(){
		
		
		window.location.href = 'adminMenu.html?method=UsageInventoryXLS';
			
	});

$("#pdfDownload").click(function(){
	
	
	window.location.href = 'adminMenu.html?method=UsageInventoryPDF';
		
});

});
