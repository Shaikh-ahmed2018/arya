$(document).ready(function() {
	
	
	$("#cenceltc").click(function(){
		
		window.location.href="adminMenu.html?method=findStudentForTransferCertificate";

	});
	

	$("#back").click(function(){
		
		window.location.href="adminMenu.html?method=transferCertificatePage";

	});
	
	
});