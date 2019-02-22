function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document).ready(function() {
	
	if($("#studentid").val() != " " || $("#studentid").val()!=null){
		$("#parentchild").val($("#studentid").val());
	}
	
	$("#parentchild").change(function(){
		window.location.href="parentMenu.html?method=getStuDetails&studentid="+$("#parentchild").val();
	});

});




/*$(document).ready(function() {
	
	
	$("#uploadTransfer").hide();
	
	$("#uploadBirth").hide();
	
	$("#imagePreview").show();
	readURL(this);
	
	
	if($("#hiddenimage").val()!="" && $("#hiddenimage").val()!=undefined){
		
		
		$('#imagePreview').attr('src', $("#hiddenimage").val());
		
		$("#document1btn").attr('name',$("#hiddenprofile").val());
		
		$("#document2btn").attr('name',$("#hiddenidproof").val());
		
		}
	
	
	$('.downloadDoc')
	.click(
			function() {
				
				var path = $(this).attr('name');
				
				
				
	        if(path =="" || path ==null){
					
					$('.errormessagediv').show();
					$('.validateTips').text("No File for download");
					$('.errormessagediv').delay(3000).slideUp();
					return false;
					
				}
	        else{
	        	window.location.href = "parentMenu.html?method=downloadTCfile&Path="
					+ path.trim();
	        	
	        }
				
			});
	
	
	
	
 var s1=$("#stuhiddenid").val();
	 
	
		
		if($("#stuhiddenid").val()!="")
		{
			
			$("#parentchild option[value="+$("#stuhiddenid").val().trim()+"]").attr("selected",'true');
		}
	 

		$("#parentchild").change(function(){
			
      
			
		    var studentid = $('#parentchild').val();
			var hiddenid = $('#parenthidden').val();
		
			
			
		
			
			
			window.location.href = "parentMenu.html?method=getnextchildInfo&hiddenid="+hiddenid+"&studentid="+studentid;
			
			
		});	
});






function readURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imagePreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}

}
*/












