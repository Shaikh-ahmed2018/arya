$(document).ready(function() {
	
	
	
	

	$('#datetimepicker3').datetimepicker({
		pickDate : false
	});
	$('#datetimepicker4').datetimepicker({
		pickDate : false
	});
	
	$("#dateId").datepicker({
		dateFormat : "dd-mm-yy",
		defaultDate : "+1w",
		changeMonth : true,
		changeYear : true
		
	});	
	
	
	var currentDate = new Date();  
	$("#dateId").datepicker("setDate",currentDate);
	
	$("#description").val("Your child came late to school today("+$("#dateId").val()+"). Please ensure that this does not happen again in the future.");
	


	
	
	
	
	$("#dateId").change(function(){
		
		$("#description").val("Your child came late to school today("+$("#dateId").val()+"). Please ensure that this does not happen again in the future.");
		
	});
	
	
	$("#classid").change(function(){
		
		 $('#classid').show();
	    $('#sectionid').show();
		
		getSection();
		
	});
	
	$("#sectionid").change(function(){
		
		   $('#classid').show();
		    $('#sectionid').show();
		    
		    getStudent();
		
	});
	
	
	
	$("#save").click(function(){
		
		
		$(".successmessagediv").hide();
      	$(".errormessagediv").hide();
      	
      	
      	var studentList = []; 
		$('#studentid :selected').each(function(i, selected){ 
			studentList[i] = $(selected).val(); 
		});
		
		
		var dateId = $("#dateId").val();
		var classid = $("#classid").val();
		var studentid = $("#studentid").val();
		/*var titleid = $("#titleid").val();*/
		var sectionid = $("#sectionid").val();
		var description = $("#description").val();
		
		
		if(dateId==""||dateId==null){
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Select Date");
			return false;
		}
		/*else if(titleid==""||titleid==null){
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Write Title");
			return false;
		}*/
		else if(classid==""||classid==null){
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Select Class");
			return false;
		}
		else if(sectionid==""||sectionid==null){
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Select Section");
			return false;
		}
		
		else if(studentid==""||studentid==null){
			
			$(".errormessagediv").show();
	
			$(".validateTips").text("Select StudentName");
			return false;
		}
		
		
		else{
		
			
		datalist={
				"dateId" : dateId ,
				"classid" : classid.join(","),
				"studentid" : studentList,
				"sectionid" : sectionid.join(","),
				"description" : description
				
		},
		
		$.ajax({
			
			type : 'POST',
			url : "smsPath.html?method=addlatecomers",
			data : datalist,
			async : false,
			success : function(response) {
				
				var result = $.parseJSON(response);
				
				if(result.jsonResponse=="Message sent successfully"){
					
					$(".errormessagediv").hide();
					$(".successmessagediv").show();
					 $(".validateTips").text("Message sent successfully");
					 
					 setTimeout(function(){
							
							window.location.href = "adminMenu.html?method=addlatecomers";
					 
					 },3000);
				}
				else{
					
					$(".errormessagediv").show();
					$(".successmessagediv").hide();
					 $(".validateTips").text("Message sending failed");
					 
					 setTimeout(function(){
							
							window.location.href = "adminMenu.html?method=addlatecomers";
					 
					 },3000);
					
				}
				
				
				
			}
			
			
		});
		
		
	}
		
		
		
		
		
		
		
	});
	
	
	
	
	
	
	
	
	
	


});



var sectionlength=0;
function getSection() {
	
	
	
	var classid = $("#classid").val();
	
	
	datalist={
			"classid" : classid.join() 
	},
	
$.ajax({
		
		type : 'POST',
		url : "smsPath.html?method=getSectionMeeting",
		data : datalist,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append();
					
					'<option value="' + "" + '">' + " "
							
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




var sectionlength=0;
function getSubject() {
	
	var classid = $("#classid").val();
	
	
	
	
	
	
	datalist={
			"classid" : classid.join()  
			
	},
	
	$.ajax({
		
		type : 'POST',
		url : "smsPath.html?method=getSubjectMeeting",
		data : datalist,
		success : function(response) {
			
			
           var result = $.parseJSON(response);
			
          
			
			$('#subjectid').append();
			
			('<option value="' + "" + '">' + ""
					
			+ '</option>');
					
				for(var j = 0; j < result.subjectlist.length; j++){
					
					$('#subjectid').append(

							'<option value="'

							+ result.subjectlist[j].subjectId + '">'

							+ result.subjectlist[j].subjectName

							+ '</option>');
				}
		}
		
	});
}

var sectionlength=0;
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
	
	
}





function HideError() 
{
	
document.getElementById("dateId").style.border = "1px solid #ccc";
document.getElementById("dateId").style.backgroundColor = "#fff";
document.getElementById("classid").style.border = "1px solid #ccc";
document.getElementById("classid").style.backgroundColor = "#fff";
document.getElementById("sectionid").style.border = "1px solid #ccc";
document.getElementById("sectionid").style.backgroundColor = "#fff";
document.getElementById("remarksid").style.border = "1px solid #ccc";
document.getElementById("remarksid").style.backgroundColor = "#fff";
document.getElementById("meetingtitle").style.border = "1px solid #ccc";
document.getElementById("meetingtitle").style.backgroundColor = "#fff";
document.getElementById("starttime").style.border = "1px solid #ccc";
document.getElementById("starttime").style.backgroundColor = "#fff";
document.getElementById("endtime").style.border = "1px solid #ccc";
document.getElementById("endtime").style.backgroundColor = "#fff";
document.getElementById("venueid").style.border = "1px solid #ccc";
document.getElementById("venueid").style.backgroundColor = "#fff";
document.getElementById("subjectid").style.border = "1px solid #ccc";
document.getElementById("subjectid").style.backgroundColor = "#fff";
document.getElementById("remarks").style.border = "1px solid #ccc";
document.getElementById("remarks").style.backgroundColor = "#fff";

}




