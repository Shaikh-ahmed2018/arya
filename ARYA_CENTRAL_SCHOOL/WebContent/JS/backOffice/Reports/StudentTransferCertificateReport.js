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
		
		
		
		datalist={
				"sectionid" : sectionid
				
		},
		
		$.ajax({
			
			type : 'POST',
			url : "studentTransferReport.html?method=getAllStudentNames",
			data : datalist,
			async : false,
			success : function(response) {
				
				
				
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
	
	
	
	$("#pdfDownload").click(function(){
		
		
		var accyear=$("#accyear").val();
		var sectionid=$("#sectionid").val();
		var reason=$("#reason").val();
		var classname=$("#classname").val();
		var studentname=$("#studentname").val();
		var description=$("#description").val();
		
		if(accyear==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Academic Year");
			
			return false;
			
		}
		
 	 if(classname==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Class Name ");
			
			return false;
			
		}
 	 if(sectionid==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Section Name ");
			
			return false;
			
		}
		
 	 if(studentname==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Student Name ");
			
			return false;
			
		}
 	 if(reason==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Write the Reason");
			
			return false;
			
		}
 	 else{
 		 
 		window.location.href = 'studentTransferReport.html?method=studentTransferCertificatePDFReport&accyear='
			+accyear
			+ ' &sectionid='
			+sectionid
			+ ' &reason='
			+ reason
			+ ' &classname='
			+classname
			+ ' &classname='
			+ classname
			+ ' &studentname='
			+studentname
			+ '&description='
			+description;
		
 		 
 	 }

	
		
		
		
		
		
		
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});	