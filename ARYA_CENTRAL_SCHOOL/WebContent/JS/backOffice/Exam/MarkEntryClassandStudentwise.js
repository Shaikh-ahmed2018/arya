$(document).ready(function(){
	accyear=$("#hiddenaccyear").val();
      $("#back").click(function()
			{
			window.location.href="examTimetablePath.html?method=setMarkEntryDetails&accyear="+accyear+"&hschoolLocation="+$("#hiddenlocid").val();
			});
        
   $("#allstudent tbody tr").click(function(){
	 
  		var classId =$( this ).find(".classId").attr("id");
        var sectionId =	$( this ).find(".sectionId").attr("id");
  		var examid = $("#hiddenexamid").val();
  		var accyear = $("#hiddenaccyear").val();
  		var subid = $(this).find(".subId").attr("id");
  		var hschoolLocation=$("#hiddenlocid").val();
  	    window.location.href = "examTimetablePath.html?method=setMarkEntryStudentwise&classId="+classId+
		"&sectionId="+sectionId+"&examid="+examid+"&accyear="+accyear+"&hschoolLocation="+hschoolLocation;
  		
  	});
      
      
   
      
      
      
      
	    
	
});

