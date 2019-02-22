$(document).ready(function() {
	
	
	$("#admissionform").click(function(){
		
		window.location.href="adminMenu.html?method=getStudentConfidentialReport";

	});
	
	
	$("#allstudent tbody tr").click(function(){
		
		window.location.href="adminMenu.html?method=studentAppraisalPage";

	});
});