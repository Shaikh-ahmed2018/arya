$(document).ready(function(){
	
	$("#allstudent tbody tr").click(function(){

		var accyear = $("#hiddenaccyid").val();
		var examid =  $(this).find(".examCode").attr("id");
		var hschoolLocation=$("#hiddenlocid").val();
		window.location.href="examCreationPath.html?method=getSubjectClass&accyear="+accyear+"&examid="+examid+"&hschoolLocation="+hschoolLocation;
	});
	

	
});













