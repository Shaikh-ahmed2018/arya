
$(document).ready(function(){
	
	
	$('#validateTable').hide();
	
	/*$('#category').change(function() {
		
	
		var classId = "classname";
		getClassName(classId, category);
	});*/
	
	
	
	$('#save').click(function(){
		
		
		var date=$('#date').val();
		var classid=$('#classid').val();
		var section=$('#sectionid').val();
		var student=$('#studentid').val();
		
		
		var classlist = []; 
		$('#classid :selected').each(function(i, selected){ 
			classlist[i] = $(selected).val(); 
			
			
		});

		var sectionlist = []; 
		$('#sectionid :selected').each(function(i, selected){ 
			sectionlist[i] = $(selected).val(); 
			
		});
		
		
		var studentlist = []; 
		$('#studentid :selected').each(function(i, selected){ 
			studentlist[i] = $(selected).val(); 
			
		});
		
		
		
		$("#hclassid").val(classlist);
		$("#hsectionid").val(sectionlist);
		$("#hstudentid").val(studentlist);
		
		
		
		
		
		if(classid==""||classid==null){
			
			
			$('.errormessagediv').show();
			$('.validateTips').text("please select ClassName");
			
			return false;
			
		}
		
		else if(section==""||section==null){
			
			$('#errormessagediv').show();
			$('.validateTips').text("please select SectionName");
			
			return false;
			
		}
		
		else if(student==""||student==null){
			
			$('#errormessagediv').show();
			$('.validateTips').text("please select StudentName");
			
			return false;
			
		}
		
		else{
			
			document.getElementById("uniform").submit(); 
			
		}
		
		
	});
	
	$("#classid").change(function(){
		
		
		
		 $('#classid').show();
	    $('#sectionid').show();
		
	    getClassSection();
		
	});
	
	
	
	$("#sectionid").change(function(){
		
		
		
		 $('#sectionid').show();
	    $('#studentid').show();
		
	    getStudent();
		
	});
	
	
	
	
	$('#sectionid,#classid')
	.change(
			function() {
				var secList = []; 
				var classList = []; 
				
				$('#sectionid :selected').each(function(i, selected){ 
					secList[i] = $(selected).val(); 
								
					$("#hsectionid").val(secList);
					
					
				});
	
				$('#classid :selected').each(function(i, selected){ 
					classList[i] = $(selected).val(); 
					
					
					$("#hclassid").val(classList);
					
				});
				
				
				var studentlist = []; 
				$('#studentid :selected').each(function(i, selected){ 
					studentlist[i] = $(selected).val(); 
					
					$("#hstudentid").val(studentlist);
				});
	
});
	
	$('#section').change(function() {
		 $('#studentcheckbox').show();
		getStudents();
		
		
	});
	
	$('#student').change(function() {
		onchangeStudents();
		
	});
	
	  var today = new Date();
	    var dd = today.getDate();
	    var mm = today.getMonth()+1;
	    var yyyy = today.getFullYear();
	    if(dd<10){
	        dd='0'+dd;
	    } 
	    if(mm<10){
	        mm='0'+mm;
	    } 
	    var today = dd+'-'+mm+'-'+yyyy;
	
	$("#date").datepicker({
		
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		minDate : -30,
		changeMonth : "true",
		changeYear : "true",
	
	}).datepicker('setDate', today);
	
	var today_date=$('#date').val();
	
$("#date").change(function(){
		
		today_date=$('#date').val();
		
			$('#smstext').val("Your child did not wear uniform to school on " +today_date+". Please mahe sure this does not happen in the future");
			
		
	});

$('#smstext').val("Your child did not wear uniform to school today.Please ensure that this does not happen in the future.");
	
	
	
	
});

var classlength=0;

function getClassName(classidval, category) {
	
	var categoryVal = $("#category").val();
	
	
	if (categoryVal == "all") {
		
		 $('#sectioncheckbox').hide();
         $('#studentcheckbox').hide();
         $('#classcheckbox').hide();
         
    	 $("#classname").html("");
    	 $("#section").html("");
    	 $("#student").html("");
		document.getElementById("classname").disabled = true;
		document.getElementById("section").disabled = true;
		document.getElementById("student").disabled = true;
		document.getElementById("classname").style.background = "#B8B894";
		document.getElementById("section").style.background = "#B8B894";
		document.getElementById("student").style.background = "#B8B894";

	} else {
		
		$('#sectioncheckbox').show();
        $('#studentcheckbox').show();
        $('#classcheckbox').show();
        
        $('#classcheckbox').attr('checked', false);
        
		document.getElementById("classname").disabled = false;
		document.getElementById("section").disabled = false;
		document.getElementById("student").disabled = false;
		document.getElementById("classname").style.background = "#FFFFFF";
		document.getElementById("section").style.background = "#FFFFFF";
		document.getElementById("student").style.background = "#FFFFFF";
	
		
	

	var classid = "#" + classidval;
	var categoryVal = $("#category").val();

			datalist = {
				"categoryVal" : categoryVal
			},
			$
					.ajax({
						type : 'POST',
						url : "childinfo.html?method=getClassDetail",
						data : datalist,
						success : function(response) {
							var result = $
									.parseJSON(response);
							$(classid).html("");
							classlength=result.parentVOList.length;
							for ( var j = 0; j < result.parentVOList.length; j++) {
								$(classid)
										.append(
												'<option value="'
														+ result.parentVOList[j].classDetailId
														+ '">'
														+ result.parentVOList[j].classDetailsName
														+ '</option>');
							}

						}
					});
			
	}
}

var sectionlength=0;
function getClassSection(sectionid, classname) {
	
	var classid = $("#classid").val();
	
	
	
	datalist={
			"classid" : classid.join()
	},
	
	
	
	$.ajax({
		
		type : 'POST',
		url : "communicationPath.html?method=getSection",
		data : datalist,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append();
					
					'<option value="' + "" + '">' + "---Select---"
							
					+ '</option>';
			
			for ( var j = 0; j < result.seclist.length; j++) {

				$('#sectionid').append(

				'<option value="'

				+ result.seclist[j].sectionId + '">'

				+ result.seclist[j].sectionName

				+ '</option>');

			}
			
		}
		
		
	});
	
	
}

var studentlength=0;
function getStudent() {
	
	
	var sectionid = $("#sectionid").val();
	
	
	
	datalist={
			"sectionid" : sectionid.join()
	},
	
	
	
	$.ajax({
		
		type : 'POST',
		url : "smsPath.html?method=getStudentMeeting",
		data : datalist,
		success : function(response) {
			

	        var result = $.parseJSON(response);
				
	        $('#studentid').html("");
				
				$('#studentid').append();
				
				('<option value="' + "" + '">' + ""
						
				+ '</option>');
						
					for(var j = 0; j < result.studentlist.length; j++){
						
						$('#studentid').append(

								'<option value="'

								+ result.studentlist[j].id + '">'

								+ result.studentlist[j].name

								+ '</option>');
					}
			
			
			
			
		}
		
		
	});
	
	
	
	
	
	
	

	/*var sectionval = $("#section").val();	
	
	
	
	if(sectionlength==sectionval.length && sectionlength>1){
		
		$('#studentcheckbox').hide();
		
		 $("#student").html("");
		
		$('#sectioncheckbox').attr('checked', true);
		document.getElementById("student").disabled = true;
		document.getElementById("student").style.background = "#B8B894";
	}else{
		
		$('#sectioncheckbox').attr('checked', false);
		$('#studentcheckbox').attr('checked', false);
		
		document.getElementById("student").disabled = false;
		document.getElementById("student").style.background = "#FFFFFF";
		
		

				var studentdetails = {
					"sectionval" : sectionval.join()
				};
				
				
				$
						.ajax({
							type : 'POST',
							url : "uniformsms.html?method=getStudentsforMultipleSections",
							data : studentdetails,
							success : function(response) {
								
								var optionString = "";
								var result = $
								.parseJSON(response);
								
								$("#student").html("");
								
								studentlength=result.studentlist.length;
								for ( var j = 0; j < result.studentlist.length; j++) {

									optionString = optionString
											+ '<option value="'
											+ result.studentlist[j].registration_num
											+ '">'
											+result.studentlist[j].studentname+"-"+ result.studentlist[j].classname+"-"+result.studentlist[j].sectionname
											+ '</option>';

								}
								
								$('#student')
								.html(optionString);
								
								
							}
						});
}*/
}

function onchangeStudents() {
	

	var student = $("#student").val();	
	
	if(studentlength==student.length){
		
		$('#studentcheckbox').attr('checked', true);
	}else{
	
		$('#studentcheckbox').attr('checked', false);
		
	}
}

function validations(){
	
	var category=$("#category").val();
	var classid=$('#classname').val();
	var section=$('#section').val();
	var date=$('#date').val();
	var smstext=$('#smstext').val();
	var student=$('#student').val();
	
	if(category==""){
		
		$('#validateTable').show();
		$('.validateTips').text("please select category");
		
		return false;
		
	}
	
	
	if(category=="all"){
		
		
		 if(date==""){
				
				$('#validateTable').show();
				$('.validateTips').text("please select date");
				
				return false;
				
			}else if(smstext==""){
				
				$('#validateTable').show();
				$('.validateTips').text("please enter text for sms");
				
				return false;
				
			}else{
				
				
				
			if(validateMeetingSms()){
				
				var x = confirm("Uniform message already exist with same content and same date do you want to send for principal approvel");
				
				if(x){
					
					return true;
					
				}else{
					
					$('#validateTable').show();
					$('.validateTips').text("Uniform message already exist");
					
					return false;
					
					
				}
				
			}else{
				
				return true;
			}

		}
		
	}else{
		
		 if(classid==null || classid==""){
				
				$('#validateTable').show();
				$('.validateTips').text("please select class");
				
				return false;
				
			}
		 
		 if(classid.length==classlength){
				
			  if(date==""){
					
					$('#validateTable').show();
					$('.validateTips').text("please select date");
					
					return false;
					
				}else if(smstext==""){
					
					$('#validateTable').show();
					$('.validateTips').text("please enter text for sms");
					
					return false;
					
				}else{
					
					
					
				if(validateMeetingSms()){
					
					var x = confirm("Uniform message already exist with same content and same date do you want to send for principal approvel");
					
					if(x){
						
						return true;
						
					}else{
						
						$('#validateTable').show();
						$('.validateTips').text("Uniform message already exist");
						
						return false;
						
						
					}
					
				}else{
					
					return true;
				}

			}
				
			}else{
		 
		  if(section==null || section==""){
				
				$('#validateTable').show();
				$('.validateTips').text("please select section");
				
				return false;
				
				
			}
		  
		  
		  
		  if(section.length==sectionlength){
			  
			  if(date==""){
					
					$('#validateTable').show();
					$('.validateTips').text("please select date");
					
					return false;
					
				}else if(smstext==""){
					
					$('#validateTable').show();
					$('.validateTips').text("please enter text for sms");
					
					return false;
					
				}else{
					
					
					
				if(validateMeetingSms()){
					
					var x = confirm("Uniform message already exist with same content and same date do you want to send for principal approvel");
					
					if(x){
						
						return true;
						
					}else{
						
						$('#validateTable').show();
						$('.validateTips').text("Uniform message already exist");
						
						return false;
						
						
					}
					
				}else{
					
					return true;
				}

			}
			  
		  }else{
			  
			  if(student==null || student==""){
				  
				  $('#validateTable').show();
					$('.validateTips').text("please select student");
					
					return false;
			  }else
		   if(date==""){
				
				$('#validateTable').show();
				$('.validateTips').text("please select date");
				
				return false;
				
			}else if(smstext==""){
				
				$('#validateTable').show();
				$('.validateTips').text("please enter text for sms");
				
				return false;
				
			}else{
				
				
				
			if(validateMeetingSms()){
				
				var x = confirm("Uniform message already exist with same content and same date do you want to send for principal approvel");
				
				if(x){
				
					return true;
					
				}else{
					
					$('#validateTable').show();
					$('.validateTips').text("Uniform message already exist");
					
					return false;
					
					
				}
				
			}else{
				
				return true;
			}

		}
		  
		  }
		  
			}
		
	}
	
	
}




function  validateMeetingSms(){
	
	var meetingstatus=false;
	var date=$('#date').val();
	var smstext=$('#smstext').val();
	
	
	var validatedetails = {
			"date" : date,
			"smstext" : smstext
			
		};
	
		$.ajax({
					type : 'POST',
					url : "uniformsms.html?method=validateUniformSms",
					data : validatedetails,
					async:false,
					
					success : function(response) {
						var result = $
						.parseJSON(response);
						
						meetingstatus=result.status;
						
						
					}
				});
		
		return meetingstatus;
		
}

function selectAllCLasses(){
	
	
	
	 if($('#classcheckbox').prop("checked") == true){
		 
         
         $('#classname option').prop('selected', true);
         
         $('#sectioncheckbox').hide();
         $('#studentcheckbox').hide();
         $("#section").html("");
         $("#student").html("");
         
         
         
         var classidVal=$("#classname").val();
         
         document.getElementById("section").disabled = true;
 		document.getElementById("student").disabled = true;
 		document.getElementById("section").style.background = "#B8B894";
 		document.getElementById("student").style.background = "#B8B894";
         
     }else{
    	 
    	 
    	    $('#sectioncheckbox').show();
            $('#studentcheckbox').show();
    	 
    	 $('#classname option').prop('selected', false);
    	 document.getElementById("section").disabled = false;
  		document.getElementById("student").disabled = false;
  		document.getElementById("section").style.background = "#FFFFFF";
  		document.getElementById("student").style.background = "#FFFFFF";
    	 $("#section").html("");
    	 
    	
    	 
     }
	
}


function selectAllSections(){
	
	
	 if($('#sectioncheckbox').prop("checked") == true){
		 
		 $('#studentcheckbox').hide();
         $('#section option').prop('selected', true);
         
    	 $("#student").html("");
         
 		document.getElementById("student").disabled = true;
 		document.getElementById("student").style.background = "#B8B894";
         
         
     }else{
    	 
    	 
    	 $('#section option').prop('selected', false);
    	 document.getElementById("student").disabled = false;
  		document.getElementById("student").style.background = "#FFFFFF";
    	 $("#student").html("");
    	 
    	
    	 
     }
	
}


function selectAllStudents(){
	
	
	 if($('#studentcheckbox').prop("checked") == true){
		 
        
        $('#student option').prop('selected', true);
       
    }else{
   	 
   	 
   	 $('#student option').prop('selected', false);
   	 
   	 document.getElementById("student").disabled = false;
 		document.getElementById("student").style.background = "#FFFFFF";
   	 
   	
   	 
    }
	
}





