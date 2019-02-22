$(document).ready(function() {
	
	
    var s1=$("#hiddenid").val();
	
   
	if($("#hiddenid").val()!="")
	{
		
		$("#parentchild option[value="+$("#hiddenid").val().trim()+"]").attr("selected",'true');
	}
 
	
	$("#parentchild").change(function(){
		var studentid = $('#parentchild').val();
		var hiddenid = $('#parenthidden').val();
		window.location.href = "parentMenu.html?method=getexamlist&hiddenid="+hiddenid+"&studentid="+studentid;
	});
	
	$("#viewExamdetails").click(function(){
		examid = $("input[type='radio']:checked").val();
		window.location.href = "parentMenu.html?method=viewExamdetails&locid="+$("#hiddenloc").val()+"&examid="+examid+
		"&classid="+$("#hiddenclassid").val()+"&secid="+$("#hiddensectionid").val();
		
	});

	
});