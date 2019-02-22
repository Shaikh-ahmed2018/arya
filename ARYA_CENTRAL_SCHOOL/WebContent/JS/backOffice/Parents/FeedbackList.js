
$(document).ready(function() {
	
	  
	
});


function getvaldetails(value){
	
	var s1 =value.id;
	
	var feedbackid = s1;
	
	
	
	$("#feedbackid").val(feedbackid);
	
	
	
}



function downloadfunction(val){
	
	var feedbackhidden =val.id;
	
	var feedbackId=feedbackhidden.split(",");
	
	
	if(feedbackId == ""|| feedbackId ==null){
		
		$('.errormessagediv').show();
		$('.validateTips').text("No File for download");
		return false;
		
	}
	else{
		
		window.location.href = "parentMenu.html?method=downloadFeedback&feedbackId="+feedbackId[0];
		
	}
	
	
	
}



