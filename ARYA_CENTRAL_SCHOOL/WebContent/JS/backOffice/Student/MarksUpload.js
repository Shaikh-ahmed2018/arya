$(document).ready(
		function() {
			
			// Editing Fuction

			$("#editStudent")
					.click(function() {
						
								var cnt = 0;
								var getData=null;
								
								$(
										'input.Checkbox_Class:checkbox:checked')
										.map(
												function() {

													var term_id = $(this).attr("id");
													var split_id = term_id.split('_');
													getData = split_id[1];

													cnt++;
												});
								
								if (cnt == 0 || cnt > 1) {
									$('.errormessagediv').show();
									$('.validateTips').text("Select Any One Student");
								} else {

									$('#studentid').val();
									window.location.href = "MarksUploadActionPath.html?method=getStudentMarks&searchTerm="
											+ getData;

								}

							});
			
			$("#saveid").click(function(){
				
					
					var hExamId=$("#hExamId").val();
					var hclass=$("#hclass").val();
					var hSectionId=$("#hSectionId").val();
					var hSubjectId=$("#hSubjectId").val();
					var maxmarks=$("#maxmarks").val();
					var minPassmarks =$("#minmarks").val();
					
					
					var studentIdArray=[];
					var acuiredmarksArray=[];
					
					$('#allstudent tr').each(function(){
						
						var studentid=$(this).find('.studentid').val();
						var acuiredmarks  =$(this).find('.concessionamt').val();
						
						
						if(studentid!=undefined && studentid!=""){
							
							studentIdArray.push(studentid.trim());
						}
						
						if(acuiredmarks!=undefined){
							
							acuiredmarksArray.push(acuiredmarks.trim());
						}
						
						
						
					});
					
				
					var reg = new RegExp('^[0-9]+$');
					
					  for (var i = 0; i < studentIdArray.length; i++) {
						 
				    	  
					  		if(maxmarks==null || maxmarks==""){
								 $('.errormessagediv').show();
								$('.validateTips').text("Max marks should not empty");
								document.getElementById("maxmarks").style.border = "1px solid #AF2C2C";
								document.getElementById("maxmarks").style.backgroundColor = "#FFF7F7";
								setTimeout(function() {
									$('.errormessagediv').fadeOut();
								}, 3000);
								
								
								
								return false;
								
							}else if(!(reg.test(maxmarks.trim()))){
								
								 $('.errormessagediv').show();
								$('.validateTips').text("Max marks should be integers");
								document.getElementById("maxmarks").style.border = "1px solid #AF2C2C";
								document.getElementById("maxmarks").style.backgroundColor = "#FFF7F7";
								setTimeout(function() {
									$('.errormessagediv').fadeOut();
								}, 3000);
								
								
								
								return false;
								
							}if(minPassmarks==null || minPassmarks==""){
					  			
								 $('.errormessagediv').show();
									$('.validateTips').text("Min Pass marks should not empty");
									document.getElementById("minmarks").style.border = "1px solid #AF2C2C";
									document.getElementById("minmarks").style.backgroundColor = "#FFF7F7";
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);
									
								
								return false;
						
							}else if(!(reg.test(minPassmarks.trim()))){
								
								 $('.errormessagediv').show();
									$('.validateTips').text("Min pass marks should be integers");
									document.getElementById("minmarks").style.border = "1px solid #AF2C2C";
									document.getElementById("minmarks").style.backgroundColor = "#FFF7F7";
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);
									
						 		
								return false;
								
							}else if(parseInt(minPassmarks)>parseInt(maxmarks)){
								
								 $('.errormessagediv').show();
									$('.validateTips').text("Min pass marks should LessThan Max marks");
									document.getElementById("minmarks").style.border = "1px solid #AF2C2C";
									document.getElementById("minmarks").style.backgroundColor = "#FFF7F7";
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);
									
						 		
								return false;
								
							}else  if(!(acuiredmarksArray[i]=="") && !(reg.test(acuiredmarksArray[i].trim()))){
								
								 $('.errormessagediv').show();
									$('.validateTips').text("Scored markes should be integers");
									
									
								 return false;
								 
							}else if(parseInt(acuiredmarksArray[i])>parseInt(maxmarks)){
								
					    		 $('.errormessagediv').show();
									$('.validateTips').text("Scored marks should LessThan Max marks");
									
									
					    		return false;
							
							}else{
								 $('.errormessagediv').hide();
								
							   }
							
						}
					
					var datalist={
							
							"hExamId":hExamId.trim(),
							"hclass":hclass.trim(),
							"hSectionId":hSectionId.trim(),
							"hSubjectId":hSubjectId.trim(),
							"studentIdArray" : studentIdArray.join(","),
							"acuiredmarksArray" : acuiredmarksArray.join(","),
							"maxmarks" : maxmarks.trim(),
							"minPassmarks" : minPassmarks.trim()
							
					};
					
					
					$.ajax({
						type : "GET",
						url : "MarksUploadActionPath.html?method=uploadMarks",
						data :datalist,
						async : false,
						success : function(data) {
							var result = $.parseJSON(data);
							
							if(result.Status=="true"){
								
								$('.successmessagediv').show();
								$('.validateTips').text("Marks Uploaded Successfully");
								
							setTimeout(function(){
								
								 window.location.href="teachermenuaction.html?method=marksStatus";
							 
							 },6000);
							
							}else{
								
								$('.errormessagediv').show();
								$('.validateTips').text("Marks not uploaded,Try later");

							}
						
						}
					});
				
				
			});
			
			
			$("#searchbtn").click(function(){
				
				window.location.href = "teachermenuaction.html?method=marksStatus&searchTerm="+ $("#searchterm").val().trim();

				
			});
			
			
			$('#excelDownload')
			.click(
					function() {
						
						window.location.href = 'MarksUploadActionPath.html?method=downloadmarksDetailsXLS';
						
					});
			$("#pdfDownload").click(function(){
    			
    			window.location.href = "MarksUploadActionPath.html?method=downloadmarksDetailsPDF"
    				
    		});
					
			
			
		});



function HideError() 
{
	
document.getElementById("maxmarks").style.border = "1px solid #ccc";
document.getElementById("maxmarks").style.backgroundColor = "#fff";
document.getElementById("minmarks").style.border = "1px solid #ccc";
document.getElementById("minmarks").style.backgroundColor = "#fff";

}