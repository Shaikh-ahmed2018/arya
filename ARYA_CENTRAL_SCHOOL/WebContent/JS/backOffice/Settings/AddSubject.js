$(document)
		.ready(
				function() {
					$("#locationname").change(function(){
						getClassName($("#locationname").val());
					});
					$("#subjtname").change(function(){
						checkSubjectduplication();

					});	
					$("#subjectCode").on('blur keyup paste change',function(e){
						maxlength=4;
						this.value=this.value.toUpperCase();
						if(this.value.length > maxlength){
						this.value = this.value.substr(0,maxlength);
						
						}
					});
					$("#classname").change(function(){
						getClassSpecilization($(this).val(),$("#locationname").val());
							checkSubjectduplication();
						
					});	
					
					
					  $('input[type=file]').change(function () {
 						 var val = $(this).val().toLowerCase();
 						 var regex = new RegExp("(.*?)\.(docx|doc|pdf|ppt|xls|jpg|jpeg|txt|png|xlsx)$");
 						  if(!(regex.test(val))) {
 						 $(this).val('');
 						 $(
 							".errormessagediv")
 							.show();
 					     $(".validateTips")
 							.text(
 									"Select Correct file format ");
 						 } }); 
				var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
				if(pageUrl=="subject.html?method=addSubject"){
					$(".successmessagediv").show();
					setTimeout(function(){
						window.location.href="adminMenu.html?method=subjectdetails";
					},2500);
				}
					
					
					
					var hiddenclass=$("#hiddenclassid").val().trim(); 
					
					
					
				  
					$("#classname option[value="+ hiddenclass + "]").attr('selected', 'true');
				    var hiddensubject=$("#hiddensubject").val();
					var hiddendescription=$("#hiddendescription").val();
					var hiddenfile=$("#hiddenfile").val();
					
					$("#subjtname").val(hiddensubject);
					$("#description").val(hiddendescription);
					
					
					
				    setTimeout("removeMessage()" ,3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
					
					
		$('#save').click(function() 
				{
			var reg = /^\d+$/;
	 		var tm=$("#totalMarks").val();
	 		var pm=$("#passMarks").val();
             var status=$("#statusid").val();
             var isLanguage=$("#isLangauge").val();
                                
								$(".errormessagediv").show();
								var  classname = $("#classname").val(), subjtname = $("#subjtname").val(), file = $("#file").val(), description = $("#description").val();
								var locationId=$("#locationname").val();
								
								 var docreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.doc|.docx|.DOC|.DOCX)$/;
							    var pdfreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.pdf|.PDF)$/;
								
							    if(locationId=="")
                             	{
									$(".errormessagediv").show();
									$(".validateTips").text("Select Location");
									showError("#locationname");
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);
									return false;
								
                             	}
							    
							    else   if(classname==""){
									$(".errormessagediv").show();
									$(".validateTips").text("Select Class");
									showError("#classname");
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);
									return false;
								
                             	}
                                
                                 
                                else if(subjtname=="")
                            	{
									$(".errormessagediv").show();
									$(".validateTips").text("Select Subject");
									showError("#subjtname");
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);
									return false;
								
                             	}
                          
                                 
                                 else if(subjtname.length < 2)
         						{
         							$(".errormessagediv").show();
         							$(".validateTips").text("Subject Should Contain min 2 Characters");
         							return false;
         						}
                                 
                                 else if(tm==""){
                     				
                     				$(".errormessagediv").show();
                     				$(".validateTips").text("Total marks should not be empty");
                     				showError("#totalMarks");
                     				setTimeout(function() {
                     					$('.errormessagediv').fadeOut();
                     				}, 3000);
                     				return false;
                     			}
                                 
                                 else if(!tm.match(/[0-9]+/)){
                                	 $(".errormessagediv").show();
          							$(".validateTips").text("Total Marks should be in Numbers");
          							document.getElementById("totalMarks").style.border = "1px solid #AF2C2C";
                                 	document.getElementById("totalMarks").style.backgroundColor = "#FFF7F7";
          							return false;
                                 }
                                 
                                 else if(pm==""){
                                	 $(".errormessagediv").show();
          							$(".validateTips").text("Pass Marks should not be Empty.");
          							document.getElementById("totalMarks").style.border = "1px solid #AF2C2C";
                                 	document.getElementById("totalMarks").style.backgroundColor = "#FFF7F7";
          							return false;
                                 }
                                 else if(!pm.match(/[0-9]+/)){
                                	 $(".errormessagediv").show();
          							$(".validateTips").text("Pass Marks should be in Numbers");
          							document.getElementById("passMarks").style.border = "1px solid #AF2C2C";
                                 	document.getElementById("passMarks").style.backgroundColor = "#FFF7F7";
          							return false;
          							
                                 }
                                 
                                 else if(parseFloat($("#totalMarks").val()) < parseFloat($("#passMarks").val())){
                                	$(".errormessagediv").show();
          							$(".validateTips").text("Total Marks should be greater than Pass Marks.");
          							return false;
                                 }
                                 else if($("#subjectCode").val()==""){
                     				
                     				$(".errormessagediv").show();
                     				$(".validateTips").text("Enter Subject Code");
                     				showError("#subjectCode");
                     				setTimeout(function() {
                     					$('.errormessagediv').fadeOut();
                     				}, 3000);
                     				return false;
                     			}
                                 

								 else {
									 $(".errormessagediv").hide();
									 document.getElementById("SubjectForm").submit();
									
								 }

								if (!bValid) {
									$(".errormessagediv").show();
								} else {

									$(".errormessagediv").hide();
								}
								return bValid;
							
							});
						
					
				});


function getLanguage(){
	$.ajax({
	url : "sectionPath.html?method=",
	})
	
	
	
	
}

function getClassName(val) {
	
	
	
	$.ajax({
	url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
	data:{"locationId":val},
	async:false,

	success : function(data) {

		

		var result = $.parseJSON(data);
		$(classname).html("");
		
	    $(classname).append('<option value="">' + "---------Select---------" + '</option>');

		for (var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

			$(classname).append(
					'<option value="' + result.classDetailsIDandClassDetailsNameList[j].secDetailsId + '">'
							+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName + '</option>');
		}

	
	
	
	
	}
});}

function clearFields() {


	document.getElementById("classname").value = "";
	document.getElementById("subjtname").value = "";
	document.getElementById("file").value = "";
	document.getElementById("description").value = "";
}

function removeMessage() {
	

	
	$(".successmessagediv").hide();


}

function checkSubjectduplication() {
	


	var classId = $("#classname").val();
	var subject = $("#subjtname").val(); 
	var locationId=$("#locationname").val();
	var specialization=$("#specialization").val();
	var checkSubjectName = {
			"classId" : classId,
			"subject" : subject,
			"locationId":locationId,
			"specialization":specialization,

	};

	$.ajax({
		type : "POST",
		url : "subject.html?method=validateSubNameUpdate",
		data : checkSubjectName,
		async : false,
		success : function(data) {


			var result = $.parseJSON(data);


			if(result.des_available =="true" ) {

				$(".errormessagediv").show();
				$(".validateTips").text("Subject Name '"+$("#subjtname").val()+"' Already Exists With this Class");
				$("#subjtname").val("");
				$("#subjtname").css({'border-color':'#f00'});

				setTimeout(function(){
					$(".errormessagediv").hide();
				},3000);

			}

		}
	});
}


function HideError() 
{
	
document.getElementById("classname").style.border = "1px solid #ccc";
document.getElementById("classname").style.backgroundColor = "#fff";

document.getElementById("subjtname").style.border = "1px solid #ccc";
document.getElementById("subjtname").style.backgroundColor = "#fff";

}
function getClassSpecilization(classId,location){
	var data = {
			"classId" : classId,
			"locationId":location,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specialization').empty();
			$('#specialization').append('<option value="-">'+ "General" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#specialization').append(
						'<option value="'
						+ result.jsonResponse[j].spec_Id
						+ '">'
						+ result.jsonResponse[j].spec_Name
						+ '</option>');
			}


		}
	});
}
function showError(id){
	
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	
}	
