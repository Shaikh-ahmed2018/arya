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

	
	
	$("#titleid").change(function(){
		
		
		
		
		var title = $("#titleid").val();
		
		if(title=="meeting")
			
			{
			
				
				$("#description").val("Dear Parents, Kindly attend the meeting to be conducted in our school on("+$("#dateId").val()+"). Your presence is our pleasure");			
			
			}
		
		else
			
			{
			
			
				$("#description").val("Our School will conduct some special events to celebrate the ("+$("#venueid").val()+") and would like to invite you. It will be held on 15-07-2016.");
			
			}
		
		
		
	});
	
	$("#classid").change(function(){
		
		 $('#classid').show();
	    $('#sectionid').show();
		
		getSection();
		  getSubject();
		
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
		var starttime = $("#starttime").val();
		var subjectid = $("#subjectid").val();
		var studentid = $("#studentid").val();
		var titleid = $("#titleid").val();
		var sectionid = $("#sectionid").val();
		var endtime = $("#endtime").val();
		var venueid = $("#venueid").val();
		var description = $("#description").val();
		
		if(dateId==""||dateId==null){
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Select Date");
			return false;
		}
		
		/*else if(venueid==""||venueid==null){
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Please enter Meeting/Event name");
			return false;
		}
		
		*/
		
		else if(titleid==""||titleid==null){
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Write Title");
			return false;
		}
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
		else if(starttime==""||starttime==null){
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Select StartTime");
			return false;
		}
		else if(endtime==""||endtime==null){
	
			$(".errormessagediv").show();
	
			$(".validateTips").text("Select EndTime");
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
				"starttime" : starttime,
				"subjectid" : subjectid,
				"studentid" : studentList,
				"titleid" : titleid,
				"sectionid" : sectionid.join(","),
				"endtime" : endtime,
				
				"description" : description
				
		},
		
		$.ajax({
			
			type : 'POST',
			url : "smsPath.html?method=insertmeeting",
			data : datalist,
			async : false,
			success : function(response) {
				
				var result = $.parseJSON(response);
				
				if(result.jsonResponse=="Meeting/Event sent successfully"){
					
					$(".errormessagediv").hide();
					$(".successmessagediv").show();
					 $(".validateTips").text("Meeting/Event sent successfully");
					 
					 setTimeout(function(){
							
							window.location.href = "adminMenu.html?method=addMeeting";
					 
					 },3000);
				}
				else{
					
					$(".errormessagediv").show();
					$(".successmessagediv").hide();
					 $(".validateTips").text("Meeting/Event sending failed");
					 
					 setTimeout(function(){
							
							window.location.href = "adminMenu.html?method=addMeeting";
					 
					 },3000);
					
				}
				
				
				
			}
			
			
		});
		
		
	}
		
		
		
		
		
		
		/*
		if(dateId==""||dateId==null){
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Select Date");
			return false;
		}
	
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
		else if(subjectid==""||subjectid==null){
	
			$(".errormessagediv").show();
			
			$(".validateTips").text("Select Subject");
			return false;
		}
		else if(description==""||description==null){
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Write The Description");
			return false;
		}*/
		/*else
			{*/
		
		/*datalist={
				"dateId" : dateId ,
				"classid" : classid,
				"sectionid" : sectionid,
				"subjectid" : subjectid,
				"description" : description
		},
		
		$.ajax({
			type : 'POST',
			url : "smsPath.html?method=insertsms",
			data : datalist,
			async : false,
			success : function(response) {
				
				var result = $.parseJSON(response);
				
				if(result.jsonResponse=="HomeWork Sent Successfully"){
					
					$(".errormessagediv").hide();
					$(".successmessagediv").show();
					 $(".validateTips").text("HomeWork Sent Successfully");
					 
					 setTimeout(function(){
							
							window.location.href = "adminMenu.html?method=addHomeWork";
					 
					 },3000);
				}
				else{
					
					$(".errormessagediv").show();
					$(".successmessagediv").hide();
					 $(".validateTips").text("HomeWork Sending Failed");
					 
					 setTimeout(function(){
							
							window.location.href = "adminMenu.html?method=addHomeWork";
					 
					 },3000);
					
				}
				
			}
			
		});*/
		
			/*}*/
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




