
function disableF5(e) { if ((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82) e.preventDefault(); };
//To disable f5
 /* jQuery < 1.7 */

$(document).ready(function(){
	
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	
	
	if(pageUrl=="uploadStaffXSL.html?method=insertStaffXSL"){
		
		if($(".errormessagediv span").text()==""){
			
			$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
			setTimeout(function(){
				window.location.href="adminMenu.html?method=staffList";
				
			},3000);
		}
	
		if($(".errormessagediv span").text()!=""){
			 $(document).on("keydown", disableF5);
			setTimeout(function(){
				
				$(".errormessagediv").empty();
			},3000);
			
		}
	}
	
	
	$("#saveid")
	.click(
			function() 
			
			{
				
					
				var fileName=$("#stageFile").val().split('.').pop().toLowerCase();
				var fileNameCheck=$("#stageFile").val();
				
				
				if(fileNameCheck==""){
				
					
					 $(".validateTips").text("Select Excel File");
						$(".errormessagediv").show();
					 
					 $("#stageFile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					return false;
				}else if(fileName !="xlsx"){
				
					$(".validateTips").text("Select Only Excel file with .xlsx extension.");
						$(".errormessagediv").show();
						$("#stageFile").val("");
					 $("#stageFile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					return false;
				}
				
				else{
					
					document.getElementById("excelfileupload").submit();
					
			 	}
				
			

});
});