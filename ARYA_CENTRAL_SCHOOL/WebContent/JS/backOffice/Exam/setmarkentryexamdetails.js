$(document).ready(function(){
	accyear=$("#hiddenaccyear").val();

	$("#allstudent tbody tr").click(function(){
		var examid = $( this ).find(".examCode").attr("id");
		var classid = $( this ).find(".classid").attr("id");
		hschoolLocation=$("#hiddenlocation").val();
		window.location.href="examTimetablePath.html?method=setMarkEntryStudentandClasswise&accyear="+accyear+"&examid="+examid+"&hschoolLocation="+hschoolLocation+"&classid="+classid;

	});
	$("#back").click(function()
			{
		window.location.href="examTimetablePath.html?method=getExamMarksStudentwise";
			});
});