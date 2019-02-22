	$(document).ready(function(){	
		
	// for settings color js
		$("#dropdown").click(function(){
			$("#hbox").slideToggle("slow");
			
		});
	
		$('.col-md-10, .vertical').click(function(){
			$("#hbox").hide();
		});
		
		// for showing search details
	
		$("#allstudent").hide();
		$(".selecteditems").hide();
		
		if($("#hideenId").val()!="" && $("#hideenId").val()!=undefined ){
			
			$(".selecteditems").show(1000);
			$("#allstudent").show(1000);
			$("#txtstyle, #txtstyle").slideToggle();
			
		}
	
		
	$("#excelDownload").click(function(){
			
			var haccyear=$("#haccyear").val();
			
			alert(haccyear);
			
			
			if(haccyear=="" ){
				
				
				$('.errormessagediv').show();
				$('.validateTips').text("First Search the Student Details");
				
			}
			else{
				
				window.location.href = 'reportaction.html?method=geInactivetStudentFeeDetailExcelsReport&AccId='
					+ $("#haccyear").val();
				
				
			}
		
		$("#search").click(function(){
			
			var accyear=$("#accyear").val();
			
			
			if(accyear==""){
				
				$("#txtstyle, #txtstyle").slideToggle();
				
				
			}
			
			if(accyear==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Academic Year");
				
				return false;
				
			}else{
				
				return true;
			}
			
		});
		
		
	
			
			
		});
		
		$("#pdfDownload").click(function(){
			
			var haccyear=$("#haccyear").val();
			
			
			if(haccyear==""){
				
				
				$('.errormessagediv').show();
				$('.validateTips').text("First Search the Student Details");
				
			}
			else{
				
				window.location.href = 'reportaction.html?method=geInactivetStudentFeeDetailPDFReport&AccId='
					+ $("#haccyear").val();
				
			}
			
		});

		
});
	
	
	
function validate(){
	
	
}	