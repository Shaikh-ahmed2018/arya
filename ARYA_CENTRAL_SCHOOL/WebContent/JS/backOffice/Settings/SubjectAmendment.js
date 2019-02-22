$(document)
		.ready(
				function() {
					$("#subjectCode").on('blur keyup paste change',function(e){
						maxlength=4;
						this.value=this.value.toUpperCase();
						if(this.value.length > maxlength){
						this.value = this.value.substr(0,maxlength);
						
						}
					});
					
					if($("#schoolId").val()!=""){
						$("#locationname").val($("#schoolId").val());
						getClassName($("#schoolId"));
						}
					$("#locationname").change(function(){
						getClassName($(this));
						checkSubjectduplication();
					});
					
				$("#isLangauge").val($("#hLanguage").val());
				$("#isExam").val($("#hExam").val());
				
					if($("#hiddenclassid").val()!=""){
						$("#classnameid").val($("#hiddenclassid").val());
						getClassSpecilization($("#hiddenclassid").val());
						
						}
					
					var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
					var url=pageUrl.split("&");
					var finalurl=url[0];
					if(finalurl=="subject.html?method=updateSubject")
						{
						  if($("#errormessagediv  .validateTips").text()==""){
							$(".successmessagediv").show();
							setTimeout(function(){
							window.location.href="adminMenu.html?method=subjectdetails";
							},3000);
						}
						}
				
					var hiddensubjectname=$("#hiddensubject").val();
					var hiddenclassid=$("#hiddenclassid").val();
					
					
					$("#document1btn").hide();
					$("#deleteProfile").hide();	
					
					$("#classnameid").change(function(){
						checkSubjectduplication();
						getClassSpecilization($(this).val(),$("#locationname").val());
					
				});	
					
					$("#subjtname").change(function(){
						if(hiddensubjectname !=$("#subjtname").val() || hiddenclassid!=$("#classnameid").val()){
							checkSubjectduplication();
							
						}
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
					
				
					
					var hiddenclass=$("#hiddenclassid").val().trim(); 
					
					
					
				  
					$("#classnameid option[value="+ hiddenclass + "]").attr('selected', 'true');
					
					
				    var hiddensubject=$("#hiddensubject").val();
					var hiddendescription=$("#hiddendescription").val();
					
					
					$("#subjtname").val(hiddensubject);
					$("#description").val(hiddendescription);
					
					
					
				    setTimeout("removeMessage()" ,3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
					
					
					$('#save').click(function() {


						var tm=$('#totalMarks').val();
						var pm=$('#passMarks').val();
						var status=$("#statusid").val();
						var locationId=$("#locationname").val();
						var reg = /^\d+$/;

						$(".errormessagediv").show();
						var  classname = $("#classname").val(), subjtname = $("#subjtname").val(), file = $("#file").val(), description = $("#description").val();


						var docreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.doc|.docx|.DOC|.DOCX)$/;
						var pdfreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.pdf|.PDF)$/;
						
						if(locationId=="")
                     	{
					    	$(".errormessagediv").show();	
					    	$(".validateTips").text("Select School Name");
									document.getElementById("locationname").style.border = "1px solid #AF2C2C";
									document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);
						
						
									return false;
                     	 }
						
						else if(classname=="")
						{
							$(".errormessagediv").show();
							$(".validateTips").text("Select Class ");
							return false;
						}

						else if(subjtname=="")
						{
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Subject");
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
  							$(".validateTips").text("Total Marks should not be Empty");
  							document.getElementById("totalMarks").style.border = "1px solid #AF2C2C";
                         	document.getElementById("totalMarks").style.backgroundColor = "#FFF7F7";
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
                         	$(".validateTips").text("Total Marks is less than Pass Marks.");
   							return false;
                          }
                         else if($("#subjectCode").val()==""){
                        	 $(".errormessagediv").show();
   							$(".validateTips").text("Enter Subject Code");
   							document.getElementById("subjectCode").style.border = "1px solid #AF2C2C";
                          	document.getElementById("subjectCode").style.backgroundColor = "#FFF7F7";
   							return false; 
                         }
						

						else {
							$(".errormessagediv").hide();
							document.getElementById("SubjectForm").submit();

						}
					});
		
		var path=$("#hiddenfile").val();
		if(path !== "" && path!=undefined){
			
			$("#document1btn").attr('name',path);
			$("#file").hide();
			
			$("#document1btn").show();
			$("#deleteProfile").show();
			
		}
		
		$("#deleteProfile").click(function(){
			
			$("#file").show();
			
			$("#document1btn").hide();
			$("#deleteProfile").hide();
			
		});
		
		
	
		
		
		$("#document1btn").click(function(){
			
			
			var path = $(this).attr('name');
			
			
			if(path == ""){
				
				$(".errormessagediv").show();
				$(".validateTips")
				.text(
						"No file for downloading");
			}
			else{
				
				
				window.location.href = "subject.html?method=getpathdownload&Path="+ path;
			}
		
			
		});
		
		
		
		
				
					
				});
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
			$("#specialization").val($("#hspecialization").val());

		}
	});
}
function getClassName(pointer) {
	
	
	
	$.ajax({
	url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
	data:{"locationId":pointer.val()},
	async:false,

	success : function(data) {

		

		var result = $.parseJSON(data);
		$('#classnameid').html("");
	    $('#classnameid').append('<option value="">' + "---------Select---------" + '</option>');

		for (var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

			$('#classnameid').append(
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
	


	var classId = $("#classnameid").val();
	var subject = $("#subjtname").val().trim(); 
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