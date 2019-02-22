$(document).ready(function() {
		
			var s1=$("#studentid1").val();
			
			if($("#studentid1").val()!="")
			{
				$("#studAttnId").show();
				$("#studAttnId option[value="+$("#studentid1").val().trim()+"]").attr("selected",'true');
			}
		
			  $("#viewAttendanceId").click(function(){
				  
				  var studentid1=$("#studentid1").val();
				  var studentid=$("#studentid").val();
				  var monthid=null;
				  var currentyearid=null;
				  var count=0;
				  $("input[type='checkbox']:checked").each(function(){
					  count++;	  
				  });
				 
				  if($("input[type='checkbox']:checked").attr("id")!=undefined){
					  monthid=$("input[type='checkbox']:checked").attr("id").split(",")[0];
					  currentyearid=$("input[type='checkbox']:checked").attr("id").split(",")[1]; 
				  }
					 if(monthid=="" || monthid==null || monthid==undefined || count>1){
						 
							$(".errormessagediv").show();
		     				$(".validateTips").text("Select any one Month");
		     				$(".errormessagediv").delay(4000).slideUp("slow");
		     				
		     				return false;
					 }
					
					 else{
						 window.location.href = "parentMenu.html?method=getAttendanceView&studentid="+studentid+"&monthid="+monthid+"&currentyearid="+currentyearid+"&studentid1="+studentid1;
					 }
			  });
			  
			  $("#studAttnId").change(function(){
				  var studentid = $('#studAttnId').val();
				  var parentid = $('#parenthidden').val();
				  window.location.href = "parentMenu.html?method=getNextChildAttendance&studentid="+studentid+"&parentid="+parentid;
			  });
		});



function getvaldetails(value){
	
	var s1 =value.id;
	var attendance = s1.split(",");
	
	$("#monthid").val(attendance[0]);
	$("#currentyearid").val(attendance[1]);
}








