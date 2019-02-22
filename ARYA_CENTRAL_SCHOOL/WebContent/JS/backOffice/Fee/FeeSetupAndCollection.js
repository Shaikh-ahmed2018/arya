
var feeCount;
$(document).ready(function() {
	
	
	$("#classname").change(function(){
		
		
		var classname=$("#classname").val();
		
		
		
		datalist={
				"classname" : classname
				
		},
		
		$.ajax({
			
			type : 'POST',
			url : "studentTransferReport.html?method=getSection",
			data : datalist,
			async : false,
			success : function(response) {
				
				var result = $.parseJSON(response);
				
				$('#sectionid').html("");
				
				$('#sectionid').append(
						
						'<option value="' + "" + '">' + " "
								
						+ '</option>');
				
				for ( var j = 0; j < result.seclist.length; j++) {

					$('#sectionid').append(

					'<option value="'

					+ result.seclist[j].sectionId + '">'

					+ result.seclist[j].sectionName

					+ '</option>');

				}
				
			}
			
			
		});
		
	});
	
	
	
	
	
	$("#sectionid").change(function(){
		
		var sectionid=$("#sectionid").val();
		var accyear=$("#accyear").val();
		var classname=$("#classname").val();
		
		
		
		datalist={
				"sectionid" : sectionid,
				"accyear":accyear,
				"classname":classname
		},
		
		$.ajax({
			
			type : 'POST',
			url : "feecollection.html?method=getAllStudentNames",
			data : datalist,
			async : false,
			success : function(response) {
				
				/*feecollection*/
				
				var result = $.parseJSON(response);
				
				
				
				$('#studentname').html("");
				
				$('#studentname').append(
						
						'<option value="' + "" + '">' + " "
								
						+ '</option>');
				
				for ( var j = 0; j < result.studentlist.length; j++) {

					$('#studentname').append(

					'<option value="'

					+ result.studentlist[j].studentid + '">'

					+ result.studentlist[j].studentFnameVar

					+ '</option>');

				}
				
			}
		
		
	});
	
	
	
	});	
	
	
	$("#sectionid").change(function(){
		
		
		var accYear=$("#accyear").val();
		var classname=$("#classname").val();
		
		
		
		datalist={
				"accYear" : accYear,
				"classname":classname
				
		},
		
		$.ajax({
			
			type : 'POST',
			url : "feecollection.html?method=validateFeeCount",
			data : datalist,
			async : false,
			success : function(response) {
				
				var result = $.parseJSON(response);
				
					 feeCount = result.feeCount;
				
								}
			
			
		});
		
	});
	
	
	
	
	
	
	$("#pdfDownload").click(function(){
		
		
		var accyear=$("#accyear").val();
		var sectionid=$("#sectionid").val();
		var classname=$("#classname").val();
		var studentname=$("#studentname").val();
		
		if(accyear=="" &&classname=="" && sectionid=="" &&studentname==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select The Filters");
		}
	 else{
 		 
 		window.location.href ='reportaction.html?method=FeeDetailedReportsPDFDOWNLOAD&accyear='
			+accyear
			+ ' &sectionid='
			+sectionid
			+ ' &classname='
			+classname
			+ ' &studentname='
			+studentname;
		
 		 
 	 }

	
		
		
	});
	

	
});	


function validate()
{
		
	var accyear=$("#accyear").val();
	var sectionid=$("#sectionid").val();
	var classname=$("#classname").val();
	var studentname=$("#studentname").val();
	
	
	if(accyear==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Select Academic Year");
		
		return false;
		
	}
	
	else if(classname==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Select Class Name ");
		
		return false;
		
	}
	 if(sectionid==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Select Section Name ");
		
		return false;
		
	}
	
	 else if(studentname==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Select Student Name ");
		
		return false;
		
	}
	
	
	 else if (feeCount==0) {
		
		$('.errormessagediv').show();
		$('.validateTips').text("Please Setup Fee for Selected Class ");
		
		return false;
		
	}
	

}




