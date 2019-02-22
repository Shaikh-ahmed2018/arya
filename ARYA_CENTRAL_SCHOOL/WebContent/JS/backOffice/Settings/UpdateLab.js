$(document).ready(function() {
	
	$("#lab_name").change(function(){
		value=$("#lab_name").val();
		var  id="lab_name";
			capital(value,id);
	});
	
	$("#goback").click(function()
	{
		window.location.href="adminMenu.html?method=laboratory";
		});

  if($("#hiddenlocationid").val()!="" & $("#hiddenlocationid").val()!=null )
  {
	$("#locationname").val($("#hiddenlocationid").val()) ; 
	getClassName($("#locationname").val());
	$("#classname").val($("#hiddenclassid").val());
	getSubjectName($("#classname").val(),$("#locationname").val());
	$("#subject").val($("#hiddensubject").val());
	
  }
	$("#locationname").change(function(){
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var specialization=$("#specialization").val();
		

		if(locationid==""){
			locationid="all";
		}
		if(classname==""){
			classname="all";
		}
		if(specialization==""){
			specialization="all";
		}
		getClassName($("#locationname").val());
		getSubjectName($("#classname").val(),$("#locationname").val());
	});
	
	$("#classname").change(function(){
		getSubjectName($("#classname").val(),$("#locationname").val());
		getClassSpecilization($(this).val(),$("#locationname").val());
	});
	
	$("#subject").change(function(){
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
	if(pageUrl=="subject.html?method=updateLab"){
		$(".successmessagediv").show();
		setTimeout(function(){
			window.location.href="adminMenu.html?method=laboratory";
		},2500);
	}
		
	
	
	
	

	
	
	$('#save').click(function() 
			{
		
 		var tm=$("#totalMarks").val();
 		var pm=$("#passMarks").val();
     
        
                            
							$(".errormessagediv").show();
							var  classname = $("#classname").val(), subject = $("#subject").val(),lab_name=$("#lab_name").val(), file = $("#file").val(), description = $("#description").val();
							var locationId=$("#locationname").val();
							
							 var docreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.doc|.docx|.DOC|.DOCX)$/;
						    var pdfreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.pdf|.PDF)$/;
							
						    if(locationId=="")
                         	{
						    	$(".errormessagediv").show();	
						    	$(".validateTips").text("Select School Name");
						    	showError("#locationname");
										setTimeout(function() {
											$('.errormessagediv').fadeOut();
										}, 3000);
							return false;
                         	 }
						    
						    else   if(classname==""){
						    	$(".errormessagediv").show();
                             	$(".validateTips").text("Select Class ");
							
                             	showError("#classname");
                             	setTimeout(function() {
                             		$('.errormessagediv').fadeOut();
                             	}, 3000);
                             		return false;
                            }
                            
                             
                            else if(subject=="")
                        	{
                        	 $(".errormessagediv").show();	
                                $(".validateTips").text("Enter Subject Name..");
     							showError("#subject");
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);
						
						
									return false;
                        	 }
                      
                             
                             else if(subject.length < 2)
     						{
     							$(".errormessagediv").show();
     							$(".validateTips").text("Subject name Should Contain min 2 Characters");
     							showError("#subject");
  								setTimeout(function() {
  									$('.errormessagediv').fadeOut();
  								}, 2000);
     							return false;
     						}
                             else if(lab_name=="")
                          	{
  								$(".errormessagediv").show();
  								$(".validateTips").text("Enter Lab Name....");
  								showError("#lab_name");
  								setTimeout(function() {
  									$('.errormessagediv').fadeOut();
  								}, 2000);
  								return false;
  							}
                              else if(lab_name.length < 2)
       						{
       							$(".errormessagediv").show();
       							$(".validateTips").text("Lab Name cannot be empty...");
  								showError("#lab_name");
  								setTimeout(function() {
  									$('.errormessagediv').fadeOut();
  								}, 2000);
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
      							$(".validateTips").text("Total Marks should be greater than Pass Marks.");
      							return false;
                             }
                             else if($("#subjectCode").val()==""){
                            	 $(".errormessagediv").show();
       							$(".validateTips").text("Enter Lab Code");
       							document.getElementById("subjectCode").style.border = "1px solid #AF2C2C";
                              	document.getElementById("subjectCode").style.backgroundColor = "#FFF7F7";
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
			
			
			window.location.href = "subject.html?method=getpathdownloadOfLab&Path="+ path;
		}
	
		
	});
	
	
				
			});

function checkSubjectduplication() {

	var classId = $("#classname").val();
	var subject = $("#subject").val(); 
	var subname = $("#subject").text(); 
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
		url : "subject.html?method=validateLabNameUpdate",
		data : checkSubjectName,
		async : false,
		success : function(data) {

			
			var result = $.parseJSON(data);


			if(result.des_available =="true" ) {

				$(".errormessagediv").show();
				$(".validateTips").text("Subject Name Already Exists With this Class");
				
				$("#subject").css({'border-color':'#f00'});
				$("#subject").val("");
				setTimeout(function(){
					$(".errormessagediv").hide();
				},3000);

			}

		}
	});
}



function getSubjectName(classId,location){

	var data = {
			"classId" : classId,
			"locationId":location,
	};
	$.ajax({
		type : 'POST',
		url : "subject.html?method=getSubjectByClass",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			
			$('#subject').empty();
			$('#subject').append('<option value="-">'+ "---select-------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#subject').append(
						'<option value="'
						+ result.jsonResponse[j].subjectId
						+ '">'
						+ result.jsonResponse[j].subjectName
						+ '</option>');
			}


		}
	});
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
function capital(value,id)
{
	if(value.length>0)
		{
		var str=value.replace(value.substr(0,1),value.substr(0,1).toUpperCase());
		document.getElementById(id).value=str;
		
		}
		}

