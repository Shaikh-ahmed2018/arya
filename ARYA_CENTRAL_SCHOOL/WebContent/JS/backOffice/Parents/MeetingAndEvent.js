$(document).ready(function() {
	
	
    var s1=$("#hiddenid").val();
    
  
	
   
	if($("#hiddenid").val()!="")
	{
		
		$("#parentchild option[value="+$("#hiddenid").val().trim()+"]").attr("selected",'true');
	}
 
	
	
	$("#parentchild").change(function(){
		
	
		
		var studentid = $('#parentchild').val();
		var hiddenid = $('#parenthidden').val();
	
		
		
		
		window.location.href = "parentMenu.html?method=getstudentmeetinglist&hiddenid="+hiddenid+"&studentid="+studentid;
		
		
	});

	
});