$(document).ready(function() {
	
	
	$("#addappraisal").click(function(){
		
		window.location.href="adminMenu.html?method=findStudentForAppraisal";

	});
	
	
	$("#allstudent tbody tr").click(function(){
		
		window.location.href="adminMenu.html?method=studentAppraisalPage";

	});
});
	