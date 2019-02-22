$(document).ready(function() {
	
		$("#allstudent tbody tr").click(function(){
			
			window.location.href="adminMenu.html?method=studentWithheld";

		});
		
		
$("#back").click(function(){
			
			window.location.href="adminMenu.html?method=studentWithheldList";

		});


$("#save").click(function(){
	
	window.location.href="adminMenu.html?method=studentWithheldList";

});
});