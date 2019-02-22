$(document)
		.ready(
				function() {
					if($("#allstudent tbody tr").length ==0){
						$("#allstudent tbody").append("<tr><td colspan='4'>NO Records Found</td></tr>");
					}
					
				var teachername = $('#hteacherid').val();	
					
				
					
					$("#teachernameid option[value=" + teachername + "]").attr('selected', 'true');			
					
					
				
				
				 $("#editId").click(function(){
					 
;
						
						
						
						if($("input[name='getempid']:checked").length != 1 ){
							
							 $(".errormessagediv").show();
							 $(".validateTips").text("Select Any one CheckBox");
							 
							 return false;
							
						}
						else{
							var classid = $("input[name='getempid']:checked").attr("class").split(" ")[0];
							var sectionid = $("input[name='getempid']:checked").attr("class").split(" ")[1];
							var teacherid = $("input[name='getempid']:checked").attr("class").split(" ")[2];
							var locationid=$("input[name='getempid']:checked").attr("class").split(" ")[3];
							 window.location.href = "classteachermapping.html?method=editClassTeacherAction&classid="+classid+"&sectionid="+sectionid+"&teacherid="+teacherid+"&locationid="+locationid;
						}
						
						 
				 });
				
				 
				 $("#saveid").click(function(){
				
					    var classid = $("#hclassid").val();
						var sectionid = $("#hsectionid").val();
						var teacherid = $("#teachernameid").val();
						var teacherid1 = $("#hteacherid").val();
						var locationid = $("#hlocationid").val();
						 
						 if(teacherid=="" || teacherid==null){
							 
							 $(".errormessagediv").show();
							 $(".validateTips").text("Select Teacher Name");
							 
							 document.getElementById("teachernameid").style.border = "1px solid #AF2C2C";
								document.getElementById("teachernameid").style.backgroundColor = "#FFF7F7";
								setTimeout(function() {
									$('.errormessagediv').fadeOut();
								}, 3000);
								
							 
							 return false;
							 
						 }
						 
						 if(validateclassTeacher() == 1 ) {
								
	                            $(".errormessagediv").show();
								
								$(".validateTips").text(" Teacher already Mapped for Same Class&Section");
								 document.getElementById("teachernameid").style.border = "1px solid #AF2C2C";
									document.getElementById("teachernameid").style.backgroundColor = "#FFF7F7";
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);
								
								return false;
							}
						 
						 
						 
						 else{
							 
						 
						 
						 datalist = {
									"classid" : classid,"sectionid" : sectionid, "teacherid" :teacherid, "teacherid1":teacherid1,"locationid":locationid
									
								};
						 $
							.ajax({
								
								type : 'POST',
								url : "classteachermapping.html?method=saveClassTeachetAction",
								data : datalist,
								async : false,
								success : function(
										data) {
									
									
									var result = $.parseJSON(data);
									
									if(result.jsonResponse=="Class Teacher Mapped Successfully"){
										
										$(".errormessagediv").hide();
										$(".successmessagediv").show();
										 $(".validateTips").text("Class Teacher Mapped Successfully");
										 
										 
										
										 
										 setTimeout(function(){
												
											 window.location.href = "adminMenu.html?method=getclassandteacherList";
										 
										 },3000);
										
									}
									
									if(result.jsonResponse=="Class Teacher Mapped Failed"){
										
										$(".errormessagediv").show();
										$(".successmessagediv").hide();
										 $(".validateTips").text("Class Teacher Mapped Failed");
										 
										 setTimeout(function(){
												
											 window.location.href = "adminMenu.html?method=getclassandteacherList";
										 
										 },3000);
										
									}

									
								}
								
							});
					 
						 
						 
						 }
				 });
				 
					
				 
					$("#search").click(function(){
						
					
						
						var searchTerm=$("#searchterm").val().trim();
						
						if(searchTerm==""){
						
							$('.errormessagediv').show();
							$('.validateTips').text("Enter search term");
						
						}else{
							
							window.location.href="adminMenu.html?method=getclassandteacherList&searchTerm="+searchTerm;	
							
						}
						
						
						
					});
				 
				 
				 
				 
				 
					$('#xls')
					.click(
							function() {
								
								window.location.href = "classteachermapping.html?method=classTeacherMappingXLSReport";
								
							});

					$("#pdfDownload").click(function(){
						
						window.location.href = "classteachermapping.html?method=classTeacherMappingPDFReport";
							
					});	
				 
				 
				 
				 
				 
				 
				 
				 
				 
				});


function validateclassTeacher(){
	
	var classteacher_validate=0;
	
	
	   var classid = $("#hclassid").val();
		var sectionid = $("#hsectionid").val();
		var teacherid = $("#teachernameid").val();
		
		
		
	var datalist = {
					"classid" : classid,"sectionid" : sectionid, "teacherid" :teacherid
					
				};
		 $
			.ajax({
				
				type : 'GET',
				url : "classteachermapping.html?method=viewValidateClassTeacherAction",
				data : datalist,
				async : false,
				success : function(
						data) {
					
					var result = $.parseJSON(data);
					
					
					
					if (result.status=="true") {
						
						classteacher_validate = 1;

					}
					else 
						
					{
						classteacher_validate = 0;
					}
					
					
				}
			});
		 return classteacher_validate;
	
}


function HideError() 
{
	
document.getElementById("teachernameid").style.border = "1px solid #ccc";
document.getElementById("teachernameid").style.backgroundColor = "#fff";

}









