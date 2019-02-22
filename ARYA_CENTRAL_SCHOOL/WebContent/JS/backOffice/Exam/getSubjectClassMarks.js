$(document).ready(function(){
	    hschoolLocation=$("#hiddenlocid").val();
	    $("#allstudent tbody tr").click(function(){
		var classId = $( this ).find(".classId").attr("id");
		var sectionId = $( this ).find(".sectioId").attr("id");
		var examid = $("#hiddenexamid").val();
		 var accyear = $("#hiddenaccyid").val();
		
		window.location.href = "examCreationPath.html?method=classWiseSubject&classId="+classId+
		"&sectionId="+sectionId+"&examid="+examid+"&accyear="+accyear+"&hschoolLocation="+hschoolLocation;
		
	});
	
	$("#back").click(function()
	{
		 var accyear = $("#hiddenaccyid").val();
		window.location.href="examCreationPath.html?method=getSubjectmarksStatus&accyear="+accyear+"&hschoolLocation="+hschoolLocation;
	});
	
});