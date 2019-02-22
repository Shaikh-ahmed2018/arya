$(document).ready(function() {
	
	  var parentid = $('#parentvalid').val();
	  var feedbackid = $('#feedbackcodeid').val();
	
	$("#saveid").click(function(){
		
		var feedbckto = $('#toid').val();
		var description= $('#descriptionid').val();
		var addfile= $('#addfile').val();
		
		if(feedbckto==""||feedbckto==null){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select FeedBack To");
			return false;
			
		}
		
		else if(description==""||description==null){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Write the description");
			return false;
			
		}
		
		else{
			
			document.getElementById("feedbackid").submit();

			
			$(".errorMessage").hide();
			$(".successMessage").show();
		
			
			 setTimeout(function(){
				   
				 location.reload();
			 
			 },2000);
		}
		
		
		 setTimeout(function(){
			   
			 location.reload();
		 
		 },2000);
	});
	
	
	
});  




