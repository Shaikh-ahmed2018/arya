$(document).ready(function() {
	
	if(!$("#successid").val()=="")
	
	{
		$("#txtstyle, #txtstyle").hide();
		
		$("#allstudent").show();
		$(".selecteditems").show();
		
		
	}
	else
	{
		$("#allstudent").hide();
		$(".selecteditems").hide();
		
	}
	
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

	
	// for settings color js
	$("#dropdown").click(function(){
		$("#hbox").slideToggle("slow");
		
	});

	$('.col-md-10, .vertical').click(function(){
		$("#hbox").hide();
	});
	
	
	
	// for showing search destails
	
	
	
	
	
	

	$("#addsearch").click(function(){
		
		
		$("#allstudent").show();
		/*$(".selecteditems").show();*/
		//$("#txtstyle, #txtstyle").slideToggle();

		
		var accyear=$("#accyear").val();
		
		
		var fromdate=$("#Fromdate").val();
		var todate=$("#todate").val();
		var teachertype=$("#teachertype").val();
		var teachername=$("#teachername").val();
		
		
		
		
		
		
		
		
		/*if(accyear=="" && fromdate=="" && todate=="" && teachertype=="" && teachername==""){
			
			
			
			$("#txtstyle, #txtstyle").slideToggle();
			
			
		}
		
		if(accyear==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Academic Year");
			
			return false;
			
		}
		if(teachertype==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Teacher Type");
			
			return false;
			
		}
		
		if(fromdate==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select FromDate");
			
			return false;
			
		}if(todate==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select ToDate");
			
			return false;
			
		}
		
      if(teachername==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Teacher Name");
			
			return false;
			
		}	
		
		
		
		else{
			
			return true;
			document.getElementById("staffattendanceid").submit();
		}
		*/
		
		
	});
	

	
	$("#teachertype").change(function(){
		
		var teachertype=$("#teachertype").val();
		
		
		if(teachertype=="teaching"){
			
			
			

    		datalist={
    				"teachertype" : teachertype
    				
    		},
    		
    		$.ajax({
    			
    			type : 'POST',
    			url : "staffattendancereport.html?method=getTeachernameAction",
    			data : datalist,
    			async : false,
    			success : function(response) {
    				
    				
    				
    				var result = $.parseJSON(response);
    				
    				
    				
    				
    				$('#teachername').html("");
    				
    				$('#teachername')
    				.append(
    						'<option value="'
    						+ ""
							+'">'
							+ ""
    								
    								
    								+ '</option>');
    		$('#teachername').append(
    				'<option value="'
    						+ "all" + '">'
    						+ "ALL"
    						+ '</option>');
    		for ( var j = 0; j < result.teachinglist.length; j++) {
    			$('#teachername')
    					.append(
    							'<option value="'
    									+ result.teachinglist[j].teacherId
    									+ '">'
    									+ result.teachinglist[j].teacherName
    									+ '</option>');
    		}
    				
    			}
    			
    			
    		});
			
			
		}
		
		
		else
			{
			
			
			

    		datalist={
    				"teachertype" : teachertype
    		},
    		
    		$.ajax({
    			
    			type : 'POST',
    			url : "staffattendancereport.html?method=getTeachernameAction",
    			data : datalist,
    			async : false,
    			success : function(response) {
    				
    				
    				var result = $.parseJSON(response);

    				
    				
    				$('#teachername').html("");
    				
    				$('#teachername')
    				.append(
    						'<option value="'
    						+ ""
							+'">'
							+ ""
    								
    								
    								+ '</option>');
    		$('#teachername').append(
    				'<option value="'
    						+ "all" + '">'
    						+ "ALL"
    						+ '</option>');
    		for ( var j = 0; j < result.nonteachinglist.length; j++) {
    			$('#teachername')
    					.append(
    							'<option value="'
    									+ result.nonteachinglist[j].teacherId
    									+ '">'
    									+ result.nonteachinglist[j].teacherName
    									+ '</option>');
    		}
    				
    			}
    			
    			
    		});
			
			
			}
			
			
		
		
	});
	
	
	
	
	
	
	
	
	
	
	$("#excelDownload").click(function(){
		
		
         var accyear=$("#haccyear").val();
	     var fromdate=$("#hfromdate").val();
		 var todate=$("#htodate").val();
	   	var teachertype=$("#hteachertype").val();
		var teachername=$("#hteachername").val();
	
	   
	 	
		
		window.location.href = 'staffattendancereport.html?method=staffAttendanceExcelReport&accyear='
			+accyear
			+ ' &fromdate='
			+fromdate
			+ ' &todate='
			+ todate
			+ ' &teachertype='
			+teachertype
			+ ' &teachername='
			+teachername;
		
	});
	
	
	$("#pdfDownload").click(function(){
		
		
		  var accyear=$("#haccyear").val();
		     var fromdate=$("#hfromdate").val();
			 var todate=$("#htodate").val();
		   	var teachertype=$("#hteachertype").val();
			var teachername=$("#hteachername").val();
		
		   
		 	
			
		window.location.href = 'staffattendancereport.html?method=staffAttendancePDFReport&accyear='
			+accyear
			+ ' &fromdate='
			+fromdate
			+ ' &todate='
			+ todate
			+ ' &teachertype='
			+teachertype
			+ ' &teachername='
			+teachername;
	});
	
	
	
	
	
		

	
	
});





function validate(){
	
	
}	



















