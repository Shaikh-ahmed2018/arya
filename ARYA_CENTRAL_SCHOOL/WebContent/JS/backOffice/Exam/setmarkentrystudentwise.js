$(document).ready(function()
		{
	     accyear=$("#hiddenaccyear").val();
         var examid = $("#hiddenexamid").val();
         classId=$("#hiddenclassid").val();
         sectionId=$("#hiddensectionid").val();
         hschoolLocation=$("#hiddenlocid").val();
         
         
         $("#back").click(function()
			{
		    window.location.href="examTimetablePath.html?method=setMarkEntryStudentandClasswise&accyear="+accyear+"&examid="+examid+"&hschoolLocation="+hschoolLocation;
			});
	
	$("#allstudent tbody tr").click(function(){
		var examid = $("#hiddenexamid").val();
  		var accyear = $("#hiddenaccyear").val();
  		var admissionno =$( this ).find(".admissionno").text();
        var studentname =$( this ).find(".studentname").text();
        var studentid =$( this ).find(".studentid").val();
  	    window.location.href = "examTimetablePath.html?method=AddMarkEntryStudentWise&classId="+classId+
		"&sectionId="+sectionId+"&examid="+examid+"&accyear="+accyear+"&hschoolLocation="+hschoolLocation+"&admissionno="+admissionno+"&studentname="+studentname+"&studentid="+studentid;
  		
  	});
      
});