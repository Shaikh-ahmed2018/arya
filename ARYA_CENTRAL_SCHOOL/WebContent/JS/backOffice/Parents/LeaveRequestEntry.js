$(document).ready(function() {
	
	
	
	$('#datetimepicker3').datetimepicker({
		pickDate : false
	});
	$('#datetimepicker4').datetimepicker({
		pickDate : false
	});
	
	
	
	$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		defaultDate : "+1w",
		changeMonth : true,
		changeYear : true
		
	});	

	
	$("#todate").datepicker({
		dateFormat : "dd-mm-yy",
		defaultDate : "+1w",
		changeMonth : true,
		changeYear : true
		
	});	

	
	
	
	var fileupload = $("#hiddenprofile").val();
	
	
	
	
	$("#document2btn").attr('name',$("#hiddenprofile").val());
	
	
	

	
	$('.downloadDoc')
	.click(
			function() {
				
				var path = $(this).attr('name');
				
				
				if(path == "" || path ==null){
					
					$('.errormessagediv').show();
					$('.validateTips').text("No File for download");
					return false;
					
				}
				else{
					
					
					window.location.href = "parentMenu.html?method=downloadfile&Path="
						+ path.trim();
					
				}
				
			});
	
	
	
	
	
	
	
	
	
	 var userhiddenid=$("#userhiddenid").val();
	
	var hidden = $('#hiddenrequestto').val();
	var hidden1 = $('#hiddenleavetype').val();
	
	
	var startsession = $('#hiddenstartsession').val();
	var endsession = $('#hiddenendsession').val();
	
	var studentname = $('#hiddenstudent').val();
	
	/*alert("studentname "+studentname);*/
	
	
	$("#requesttoid option[value=" + hidden + "]").attr('selected', 'true');
	$("#leavetype option[value=" + hidden1 + "]").attr('selected', 'true');
	
	$("#startsessionDay option[value=" + startsession + "]").attr('selected', 'true');
	$("#endsessionDay option[value=" + endsession + "]").attr('selected', 'true');
	
	$("#parentchild option[value=" + studentname + "]").attr('selected', 'true');
	
	
	
	
	
	
	
	$("#save").click(function(){
		
		var requestto = $('#requesttoid').val();
		var Fromdate = $('#Fromdate').val();
		var starttime = $('#startsessionDay').val();
		var leavetype = $('#leavetype').val();
		var reason = $('#reason').val();
		var totalleaves = $('#totalleaves').val();
		var todate = $('#todate').val();
		var endtime = $('#endsessionDay').val();
		var fileupload = $('#fileupload').val();
		var studentnameid = $('#parentchild').val();
		
		var sno = $('#hiddensno').val();
		
		
		
		
		
     if(requestto==""||requestto==null){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select RequestTo ");
			return false;
			
		}
		
     if(studentnameid==""||studentnameid==null){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select StudentName ");
			return false;
			
		}
		
      if(Fromdate==""||Fromdate==null){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select From Date ");
			return false;
			
		}
		
     if(todate==""||todate==null){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select To Date ");
			return false;
			
		}
    if(starttime==""||starttime==null){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Session Start Session ");
			return false;
			
		}
		
     if(endtime==""||endtime==null){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Session End Session ");
			return false;
			
		}
		
     if(leavetype==""||leavetype==null){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select  Leave Type");
			return false;
			
		}
		
     if(reason==""||reason==null){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Write The Reason ");
			return false;
			
		}
    /* else if(fileupload==""||fileupload==null){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Upload the file ");
			return false;
			
		}*/
		
     else{
    	 
    	
    	 
    	 document.getElementById("leaverequestid").submit();
    	 
    	 
    	 $('.errormessagediv').hide();
			$(".successmessagediv").show();
			$(".validateTips").text("Leave Request Created Successfully");
			
			 setTimeout(function(){
				   
				 location.reload();
			 
			 },5000);
    	 
    	 
     }
	
    
		
     setTimeout(function(){
		   
		 location.reload();
	 
	 },2000);
 
	
		
	});
	
	
	
	
	
	$("#leavetype").change(function(){
		
		
		
		
		
		var total ="";
		var shalfday = $('#startsessionDay').val();
		var ehalfday = $('#endsessionDay').val();
		
		
		
		var date1 = $('#Fromdate').val();
		startDate = date1.split("-");
		var dstartdate = new Date(startDate[2], startDate[1] - 1, startDate[0]);

		
		
		var date2 = $('#todate').val();
		endDate = date2.split("-");
		var denddate = new Date(endDate[2], endDate[1] - 1, endDate[0]);
		
	
		
		var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
		
		var diffDays = Math.round(Math.abs((dstartdate.getTime() - denddate.getTime())/(oneDay)));
		
		var total_leave = diffDays + 1;
		
		
		
		
		if(shalfday == "FH" && ehalfday == "FH" ){
			
			total = total_leave-0.5;
			
		}
		
		else if(shalfday == "FH" && ehalfday == "SH"){
			
			total = total_leave;
		}
		
		else if(shalfday == "SH" && ehalfday == "SH" ){
			
			total = total_leave-0.5;
			
		}
		else if( (shalfday == "SH" && ehalfday == "FH"))
			{
			  
			total = total_leave-1;
			
			}
		
		
		
		var d1 = Date.parse(dateConverter(date1));
		var d2 = Date.parse(dateConverter(date2));
		 if(d1 > d2)
		 { 
			  $('.errormessagediv').show();
				$('.validateTips').text("FromDate Should Be Less Then ToDate");
				return false;
		}
		
		 else{
			
			 
			 $('#totalleaves').val(total);
			 
		 }
		
		
		

		
	});
	
	
});

function dateConverter(dateString){
	var dateArray=[];
	var dateStringArray=dateString.split("-");
	dateArray.push(dateStringArray[2]);
	dateArray.push(dateStringArray[1]);
	dateArray.push(dateStringArray[0]);
	var dateString1 = dateArray.join("-");
	return dateString1;
	
}



