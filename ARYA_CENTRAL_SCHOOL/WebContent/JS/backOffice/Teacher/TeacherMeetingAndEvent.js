$(document).ready(function() {
	
	
    var s1=$("#hiddenid").val();
    
  
	
	
	 
	if($("#hiddenid").val()!="")
	{
		
		$("#parentchild option[value="+$("#hiddenid").val().trim()+"]").attr("selected",'true');
	}
 
	
	
	$("#parentchild").change(function(){
		
	
		
		
		var hiddenid = $('#parenthidden').val();
	
		
		
		
		window.location.href = "teachermenuaction.html?method=getTeacherMeeting&hiddenid="+hiddenid;
		
		
	});
	
	
	$('#excelDownload')
	.click(
			function() {
				
				window.location.href = 'teachermenuaction.html?method=downloadmeetingsandeventsXLS';
				
			});
	$("#pdfDownload").click(function(){
		
		window.location.href = "teachermenuaction.html?method=downloadmeetingsandeventsPDF";
			
	});
	
	
	
	$('#excel')
	.click(
			function() {
				
				window.location.href = 'teachermenuaction.html?method=downloadcircularandremainderXLS';
				
			});
	$("#pdf").click(function(){
		
		window.location.href = "teachermenuaction.html?method=downloadcircularandremainderPDF";
			
	});
			

	
});