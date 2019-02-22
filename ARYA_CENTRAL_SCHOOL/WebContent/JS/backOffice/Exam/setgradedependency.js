$(document).ready(function(){
        accyear=$("#hiddenaccyear").val();
      
		$("#allstudent tbody tr").click(function(){
			   var examid = $( this ).find(".examCode").attr("id");
			   hschoolLocation=$("#hiddenlocation").val();
		       window.location.href="examTimetablePath.html?method=setGradeDependencyinDetail&accyear="+accyear+"&examid="+examid+"&hschoolLocation="+hschoolLocation;
			
		});
		$("#back").click(function()
				{
			    window.location.href="examTimetablePath.html?method=gradeDependency";
				});
	          });