$(document).ready(function(){
	
	if($("#schoolId").val()!=""){
		$("#locationname").val($("#schoolId").val());
		$("#locationname").find("option").not("option[value*=LOC]").remove();
	}
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1)
		if(pageUrl=="acadamicYearPlan.html?method=insertAcadamicYearPlan"){
			if($(".errormessagediv .validateTips").text()==""){
				$(".successmessagediv").show();
			
				setTimeout(function(){
					window.location.href="adminMenu.html?method=acdamicYearPlanList";
				},2000);
			}
		}
	
	
/*	$('#deleteProfile').hide();
	$('#document1btn').hide();*/
	


	
	
	$('#datetimepicker3').datetimepicker({
		pickDate : false
	});
	$('#datetimepicker4').datetimepicker({
		pickDate : false
	});
	
	
	
$('#save').click(function() {
				
	
	
	
	            var eventId = $("#heventId").val();
	
                var title=$("#teachertype").val();
			
				var starttime = $("#starttime").val();	
				var endtime = $("#endtime").val();
				
				var docreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.doc|.docx|.DOC|.DOCX)$/;
			    var pdfreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.pdf|.PDF)$/;
				
                 if(title==""){
                	 $(".errormessagediv").show();
				     $(".validateTips").text("Select Staff Type");
				     document.getElementById("teachertype").style.border = "1px solid #AF2C2C";
					 document.getElementById("teachertype").style.backgroundColor = "#FFF7F7";
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
				    return false;
                 }else if(starttime == ""){
			          $(".errormessagediv").show();
					  $(".validateTips").text("Select StartTime");
					   document.getElementById("starttime").style.border = "1px solid #AF2C2C";
					   document.getElementById("starttime").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
					  return false;
            	
            	 }else if(endtime == ""){
            		   $(".errormessagediv").show();
 					  $(".validateTips").text("Select EndTime");
 					   document.getElementById("endtime").style.border = "1px solid #AF2C2C";
					   document.getElementById("endtime").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
 					 return false;
            	 
            	 }else if(!checkTime()){
          		   
					  return false;
         	  
            	 }
            	 
            	 else {
            		 document.getElementById("AcadamicYearPlanForm").submit();
            	 }
            	 
			
			});


 
    $(".download").click(function(){
    	
    	 var filepath = $("input[name=download]").attr('id');
    	 
		
			window.location.href="acadamicYearPlan.html?method=download&filePath="+filepath;	
			
	});
 
 
    
    
    
    
    var path=$("#hFile").val();
    
   
   
  /*  
    if(path==""||path==null){
    	
    	  $(".errormessagediv").show();
		   $(".validateTips").text("No file for Downloading");
			 return false;
    	
    }*/
    
	if(path !== "" && path!=undefined){
		
		$("#document1btn").attr('name',path);
		$("#file").hide();
		
		$(".download").show();
		$("#deleteProfile").show();
		
	}
	
	$("#deleteProfile").click(function(){
		
		$("#file").show();
		
		$(".download").hide();
		$("#deleteProfile").hide();
		
	});
	
	

	
	
	$("#document1btn").click(function(){
		
		
		var path = $(this).attr('name');
		
		
		if(path == ""){
			
			$(".errormessagediv").show();
			$(".validateTips")
			.text(
					"No file for downloading");
		}
		else{
			
			
			window.location.href = "acadamicYearPlan.html?method=getAcadamicYearPlanFilePath&Path="+ path;
		}
	
		
	});
	
	
    
    
    
    
    
    
    
    
    
    
    
    

	
});






var status = false;
function checkTime() {
	
	var ftime = document.getElementById("starttime").value;
	var ttime = document.getElementById("endtime").value;
	
	
	
	if ((ftime != "") && (ttime != "")) {

		var ftimeSplitHour = ftime.split(':')[0];
		var ftimeSplitMin = ftime.split(':')[1];
		var ftimeSplitSec = ftime.split(':')[2];

		var ttimeSplitHour = ttime.split(':')[0];
		var ttimeSplitMin = ttime.split(':')[1];
		var ttimeSplitSec = ttime.split(':')[2];

		if (ftimeSplitHour.charAt(0) == 0) {
			ftimeSplitHour = ftimeSplitHour.charAt(1);
		}
		if (ttimeSplitHour.charAt(0) == 0) {
			ttimeSplitHour = ttimeSplitHour.charAt(1);
		}

		if (ftimeSplitMin.charAt(0) == 0) {
			ftimeSplitMin = ftimeSplitMin.charAt(1);
		}
		if (ttimeSplitMin.charAt(0) == 0) {
			ttimeSplitMin = ttimeSplitMin.charAt(1);
		}

		if (ftimeSplitSec.charAt(0) == 0) {
			ftimeSplitSec = ftimeSplitSec.charAt(1);
		}
		if (ttimeSplitSec.charAt(0) == 0) {
			ttimeSplitSec = ttimeSplitSec.charAt(1);
		}

		ftimeSplitHour = parseInt(ftimeSplitHour);
		ttimeSplitHour = parseInt(ttimeSplitHour);
		ftimeSplitMin = parseInt(ftimeSplitMin);
		ttimeSplitMin = parseInt(ttimeSplitMin);

		ftimeSplitSec = parseInt(ftimeSplitSec);
		ttimeSplitSec = parseInt(ttimeSplitSec);
		
	

		if (ftimeSplitHour > ttimeSplitHour) {
			$(".errormessagediv").show();
			$(".validateTips").text(
					"The End Time should not less than Start Time");
			return false;
			status = false;
			document.getElementById("endtime").value = "";
		}
		if (ttimeSplitHour == ftimeSplitHour) {
			if (ftimeSplitMin > ttimeSplitMin) {
				$(".validateTips").text("The End Time should not less than Start Time");
				$(".errormessagediv").show();
				
				return false;
				status = false;
				document.getElementById("endtime").value = "";

			}
			if (ftimeSplitMin == ttimeSplitMin) {
				if (ftimeSplitSec >= ttimeSplitSec) {
					$(".validateTips").text("The End Time should not less than Start Time");
					$(".errormessagediv").show();
					
					return false;
					status = false;
					document.getElementById("endtime").value = "";
				}
			}
		} else {
			$(".errormessagediv").hide();
			status = true;
		}
	} else {
		status = true;
	}
	return status;
}


function HideError() 
{
	
document.getElementById("title").style.border = "1px solid #ccc";
document.getElementById("title").style.backgroundColor = "#fff";
document.getElementById("date").style.border = "1px solid #ccc";
document.getElementById("date").style.backgroundColor = "#fff";
document.getElementById("starttime").style.border = "1px solid #ccc";
document.getElementById("starttime").style.backgroundColor = "#fff";
document.getElementById("endtime").style.border = "1px solid #ccc";
document.getElementById("endtime").style.backgroundColor = "#fff";
document.getElementById("venue").style.border = "1px solid #ccc";
document.getElementById("venue").style.backgroundColor = "#fff";

}




