$(document).ready(function() {
	
	
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
			 var subjectid = $("#subjectid option:selected").text();
			
				$('#smstext').val("Dear Parent, Please check your child's Diary. There is Home Work to be completed in "+subjectid+" on "+today_date+" ");           
				
			
		});
		
		
	     $("#subjectid").change(function(){
	    	 
	    	 var subjectid = $("#subjectid option:selected").text();
			
				$('#smstext').val("Dear Parent, Please check your child's Diary. There is Home Work to be completed in "+subjectid+" ");           
				
			
		});
		

		$('#smstext').val("Dear Parent, Please check your child's Diary.There is Home Work to be completed");
	


	
	$("#classid").change(function(){
		
		 $('#classid').show();
	    $('#sectionid').show();
		
		getSection();
		
	});
	
	$("#sectionid").change(function(){
		
		   $('#classid').show();
		    $('#sectionid').show();
		    
		    getSubject();
		
	});
	
	
	
	$("#save").click(function(){
		
		$(".successmessagediv").hide();
      	$(".errormessagediv").hide();
		
		
		var dateId = $("#date").val();
		var classid = $("#classid").val();
		var sectionid = $("#sectionid").val();
		var subjectid = $("#subjectid").val();
		var description = $("#smstext").val();
		
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
		}
		else
			{
		
		datalist={
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
			
		});
		
			}
	});


});



/*var sectionlength=0;
function getSection() {
	
	var classid = $("#classid").val();
	
	
	
	datalist={
			"classid" : classid 
	},
	
	$.ajax({
		
		type : 'POST',
		url : "smsPath.html?method=getSection",
		data : datalist,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			$('#sectionid').append();
			
			('<option value="' + "" + '">' + ""
					
			+ '</option>');
					
				for(var j = 0; j < result.seclist.length; j++){
					
					$('#sectionid').append(

							'<option value="'

							+ result.seclist[j].secId + '">'

							+ result.seclist[j].sectionName

							+ '</option>');
				}	
		}
	});
	
}*/


var sectionlength=0;
function getSection(sectionid, classname) {
	
	var classid = $("#classid").val();
	
	datalist={
			"classid" : classid 
	},
	
	$.ajax({
		
		type : 'POST',
		url : "smsPath.html?method=getSection",
		data : datalist,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append(
					
					'<option value="' + "" + '">' + ""
							
					+ '</option>');
			
			for ( var j = 0; j < result.seclist.length; j++) {

				$('#sectionid').append(

				'<option value="'

				+ result.seclist[j].secId + '">'

				+ result.seclist[j].sectionName

				+ '</option>');

			}
			
		}
		
		
	});
	
	
}


var sectionlength=0;
function getSubject() {
	
	var classid = $("#classid").val();
	
	var sectionid = $("#sectionid").val();
	
	
	
	datalist={
			"classid" : classid ,
			"sectionid" : sectionid
	},
	
	$.ajax({
		
		type : 'POST',
		url : "smsPath.html?method=getSubject",
		data : datalist,
		success : function(response) {
			
			
           var result = $.parseJSON(response);
			
		
           $('#subjectid').html("");
           
           
           $('#subjectid').append(
					
					'<option value="' + "" + '">' + ""
							
					+ '</option>');
           
           				
				for(var j = 0; j < result.sublist.length; j++){
					
					$('#subjectid').append(

							'<option value="'

							+ result.sublist[j].subjectid + '">'

							+ result.sublist[j].subjectname

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




