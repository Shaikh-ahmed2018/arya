$(document).ready(function(){
	
	$("#allstudent tr").click(function(){
		var classId = $("#hiddenclassid").val();
		var sectionId = $("#hiddensectionid").val();
		var examid = $("#hiddenexamid").val();
		var accyear = $("#hiddenaccyid").val();
		var subid = $(this).find(".subId").attr("id"); 
		var hschoolLocation=$("#hiddenlocid").val();
		window.location.href = "examCreationPath.html?method=savesubWiseMarksDetails&classId="+classId+
		"&sectionId="+sectionId+"&examid="+examid+"&accyear="+accyear+"&subid="+subid+"&hschoolLocation="+hschoolLocation;
		
	});
	
	$("#back").click(function(){
		var examid = $("#hiddenexamid").val();
		var accyear = $("#hiddenaccyid").val();
		var hschoolLocation=$("#hiddenlocid").val();
		window.location.href="examCreationPath.html?method=getSubjectClass&accyear="+accyear+"&examid="+examid+"&hschoolLocation="+hschoolLocation;
	});
	
	
	
});