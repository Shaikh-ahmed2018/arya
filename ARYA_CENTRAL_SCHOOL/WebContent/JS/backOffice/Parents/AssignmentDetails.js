$(document).ready(function() {
	
	
	 var s1=$("#hiddenid").val();
	 
	 var ss=$("#studentid").val();

		
	   
		if($("#hiddenid").val()!="")
		{
			
			$("#studAssiId option[value="+$("#hiddenid").val().trim()+"]").attr("selected",'true');
		}

	$("#studAssiId").change(function(){
		var studentid = $('#studAssiId').val();
		var hiddenid = $('#parenthidden').val();
		window.location.href = "parentMenu.html?method=getmoreassimentlist&hiddenid="+hiddenid+"&studentid="+studentid;
	});

	
	$("#editAssId").click(function(){

		 var studentid=$("#studentid").val();
		 var assgnmentid=$("input[type='checkbox']:checked").attr("id");
		 var hiddenaccyear = $("#hiddenaccyear").val();
		 var count=0;
		 $("input[type='checkbox']:checked").each(function(){
			  count++;	  
		  });
		
		 if(assgnmentid==""||assgnmentid==null || assgnmentid==undefined || count>1){
				$(".errormessagediv").show();
 				$(".validateTips").text("Select any one Assignment");
 				$(".errormessagediv").delay(3000).slideUp();
 				return false;
		 }
		 else{
			 window.location.href = "parentMenu.html?method=viewAssignmentDetails&assgnmentid="+assgnmentid+"&studentid="+studentid+"&hiddenaccyear="+hiddenaccyear;
		 }
	});
});


function getvaldetails(value){
	
	var s1 =value.id;
	
		var assgnmentid = s1;
	
	$("#assgnmentid").val(assgnmentid);
	
}









