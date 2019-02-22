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


	/*$("#streamname").change(function(){
		
	
		
		 $('#classid').show();
	    $('#sectionid').show();
		
		getClass(classid,streamname);
		
	});
*/	
	
	
	$("#classid").change(function(){
		
	
		
		 $('#classid').show();
	    $('#sectionid').show();
		
		getSection();
		
	});
	
	
	
	
	
$(".radioactive").click(function(){
	

	var s1=$('.radioactive[name="gender"]:checked').val();
	
	if(s1=="S")
	
	{
		
		$("#streamname").attr('readonly', false);
		
		$("#classid").attr('readonly', false);
		
		$("#sectionid").attr('readonly', false);
		
		datalist={"student" : s1 },
		
		$.ajax({
			
			type : 'POST',
			url : "communicationPath.html?method=getStudent",
			data : datalist,
			success : function(response) {
				
				var result = $.parseJSON(response);
				
				$('#remarksid').html("");
				$('#remarksid').append();
						
						/*'<option value="' + "" + '">' + "---Select---"
								
						+ '</option>');*/
				for ( var j = 0; j < result.studentlist.length; j++) {

					$('#remarksid').append(

					'<option value="'

					+ result.studentlist[j].studentId + '">'

					+ result.studentlist[j].name

					+ '</option>');
				
				
			}
		
			}
		});
		
	}
	
	else if(s1=="T")
	{
		
		$("#streamname").attr('readonly', true);
		$('#streamname').html("");
		
		$("#classid").attr('readonly', true);
		$('#classid').html("");
		
		$("#sectionid").attr('readonly', true);
		$('#sectionid').html("");
		

		datalist={"teacher" : s1 },
		
		$.ajax({
			type : 'POST',
			url : "communicationPath.html?method=getTeacher",
			data : datalist,
			success : function(response) {
				
				
				var result = $.parseJSON(response);
				
				$('#remarksid').html("");
				$('#remarksid').append();
						
						/*'<option value="' + "" + '">' + "---Select---"
								
						+ '</option>');*/
				for ( var j = 0; j < result.teacherlist.length; j++) {

					$('#remarksid').append(

					'<option value="'

					+ result.teacherlist[j].teacherId + '">'

					+ result.teacherlist[j].tfastname

					+ '</option>');
				
				
			}
			
			}
		});
		
		
	}
	
	
});




$(".istrans").click(function(){
	

var s2=$('.istrans[name="selectType"]:checked').val();




if(s2=="S"){
	
	
	$("#subjectid").attr('readonly', false);
	datalist={"subject" : s2 },
	
		$.ajax({
		
		type : 'POST',
		url : "communicationPath.html?method=getSubject",
		data : datalist,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#subjectid').html("");
			$('#subjectid').append(
					
					'<option value="' + "" + '">' + "General"
							
					+ '</option>');
			for ( var j = 0; j < result.subjectlist.length; j++) {

				$('#subjectid').append(

				'<option value="'

				+ result.subjectlist[j].subjectId + '">'

				+ result.subjectlist[j].subjectName

				+ '</option>');
			
			
		}
			
			
		}
		
	});
	
	
}
else if(s2=="G"){
	
	$("#subjectid").attr('readonly', true);
	$('#subjectid').html("");
	
}

});





$("#meetingsave").click(function(){
	
	
	
	/*var s2=$('.istrans[name="selectType"]:checked').val();*/
	
	var dateId = $('#dateId').val();
	var meetingfor=$('.radioactive[name="gender"]:checked').val();
	var classname= $('#classid').val();
	var sectionname= $('#sectionid').val();
	var studentid = $('#remarksid').val();
	//var teacherid = $('#remarksid').val();
	var title = $('#meetingtitle').val();
	var starttime = $('#starttime').val();
	var endtime = $('#endtime').val();
	var venueid = $('#venueid').val();
	var subjectid = $('#subjectid').val();
	var description = $('#remarks').val();
	
	

	
	
	
	
	
	 if(dateId==""||dateId==null){
			
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Select Date");
			
			document.getElementById("dateId").style.border = "1px solid #AF2C2C";
			document.getElementById("dateId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;	
			
		}
	
	
	 
	 if(title==""||title==null){
			
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Write the Subject Name");
			
			document.getElementById("meetingtitle").style.border = "1px solid #AF2C2C";
			document.getElementById("meetingtitle").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			
			return false;	
			
		}
	 
	 
	 
	 
	 
	 
	
	 if(meetingfor==""||meetingfor==null){
			
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Select Meeting/Event with");
			
			
			
			return false;	
			
		}
	
	 
	 
	 if(venueid==""||title==venueid){
			
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Write the Venue Details");
			
			document.getElementById("venueid").style.border = "1px solid #AF2C2C";
			document.getElementById("venueid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;	
			
		}
	
	 if(meetingfor=="S"){
		 
		 if(classname ==""||classname ==null){
		    	
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Class Name");
				
				document.getElementById("classid").style.border = "1px solid #AF2C2C";
				document.getElementById("classid").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				
				return false;	
			 
		 }
		
		
		 else if(sectionname ==""||sectionname ==null){
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Section Name");
				
				
				document.getElementById("sectionid").style.border = "1px solid #AF2C2C";
				document.getElementById("sectionid").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				
				return false;	
			 
		 }
		 
		 
		/* else if(studentid ==""||studentid ==null){
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Student Name");
				return false;	
			 
		 }*/
		 
		 
	 }
	
	 
	 
	 /* else if(meetingfor=="T"){
		  
		  if(studentid ==""||studentid ==null){
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Teacher Name");
				return false;	
			 
		 }
		  
	  }*/
	
	
	
	 
	 if(starttime==""||starttime==null){
			
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Select Start Time");
			
			
			document.getElementById("starttime").style.border = "1px solid #AF2C2C";
			document.getElementById("starttime").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;	
			
		}
	 
	if(endtime==""||endtime==null){
			
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Select End Time");
			
			
			document.getElementById("endtime").style.border = "1px solid #AF2C2C";
			document.getElementById("endtime").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;	
			
		}
	 
	
	if(subjectid==""||subjectid==null){
		
		
		$(".errormessagediv").show();
		
		$(".validateTips").text("Select Class Subject Name");
		
		
		document.getElementById("subjectid").style.border = "1px solid #AF2C2C";
		document.getElementById("subjectid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		
		return false;	
		
	}
	
	
	
	 if(meetingfor=="S"){
		 
		 
		 if(studentid ==""||studentid ==null){
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Student Name");
				
				document.getElementById("remarksid").style.border = "1px solid #AF2C2C";
				document.getElementById("remarksid").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				
				return false;	
			 
		 }
		 
		 
	 }
	
	 
	 
	  else if(meetingfor=="T"){
		  
		  if(studentid ==""||studentid ==null){
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Teacher Name");
				
				document.getElementById("remarksid").style.border = "1px solid #AF2C2C";
				document.getElementById("remarksid").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				
				return false;	
			 
		 }
		  
	  }
		 
	
	
	
	
	
	
if(description==""||description==null){
		
		
		$(".errormessagediv").show();
		
		$(".validateTips").text("Write the Description");
		
		document.getElementById("remarks").style.border = "1px solid #AF2C2C";
		document.getElementById("remarks").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		
		return false;	
		
	}
	
	
	
	/* 
   if(s1==""||s1==null){
		
		
		$(".errormessagediv").show();
		
		$(".validateTips").text("Select Remarks To");
		return false;	
		
	}
	
 
	if(s1=="S"){
		
		 if(streamname==""||streamname==null){
				
				
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Stream Name");
				return false;	
				
			}
		
		
	    else if(classname ==""||classname ==null){
	    	
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Class Name");
				
				return false;	
			 
		 }
		
		
		 else if(sectionname ==""||sectionname ==null){
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Section Name");
				return false;	
			 
		 }
		
		   else if(rematksto ==""||rematksto ==null){
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Student Name");
				return false;	
		
	}
		   
		 
		   else if(s2==""||s2==null){
	        	$(".errormessagediv").show();
	    		$(".validateTips").text("Select the RemarkType");
	    		return false;	
	        	
	        }
	   
	}	 
	
	
	
	
      else if(s1=="T"){
			   
    	if(rematksto ==""||rematksto ==null){
    		$(".errormessagediv").show();
			
			$(".validateTips").text("Select Teacher Name");
			return false;	
    		
    	}
    	
    	

        else if(s2==""||s2==null){
        	$(".errormessagediv").show();
    		$(".validateTips").text("Select the RemarkType");
    		return false;	
        	
        }
    	
    	
		   }
	
	
   
	if(s2=="S"){
		
		
		
	 if(subjectid==""||subjectid==null){
		 
		 $(".errormessagediv").show();
 		$(".validateTips").text("Select Subject Name");
 		
 		return false;
		 
	}
		
     else if(remarks==""||remarks==null){
     	$(".errormessagediv").show();
 		
 		$(".validateTips").text("Write the Remark");
 		return false;	
     	
     }
	}
	
	
     else if(s2=="G"){
    	 
    	   if(remarks==""||remarks==null){
    	     	$(".errormessagediv").show();
    	 		
    	 		$(".validateTips").text("Write the Remark");
    	 		return false;	
    	     	
    	     } 
    	 
    	 
    	 
     }
	
     else if(dateId==""||dateId==null){
			
			
			$(".errormessagediv").show();
			
			$(".validateTips").text("Select Date");
			return false;	
			
		}
	 
	 else if(title==""||title==null){
				
				
				$(".errormessagediv").show();
				
				$(".validateTips").text("Write the Title");
				return false;	
				
			}
		 
		 else if(starttime==""||starttime==null){
				
				
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Start Time");
				return false;	
				
			}
		 
		 else if(endtime==""||endtime==null){
				
				
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select End Time");
				return false;	
				
			}
		 
		
		
		 

		  else if(validateRemark()==1){
	        	
	        	
	        	
	    		 $(".errormessagediv").show();
	    		 
	    			$(".validateTips").text("Remark Already Created");
	    			
	    			return false;
	    			
	    	      }   
		 
		 
	
	
	
	$(".successmessagediv").show();
	
	 $(".validateTips").text("Meeting Created Successfully");
	
	*/
	
	
	
	
	datalist={
		    "dateId" : dateId,
			"meetingfor" : meetingfor,
			
			"studentid" : studentid.join(","),
			"title" : title,
			"starttime" : starttime,
			"endtime" : endtime,
			"venueid" :venueid,
			"subjectid" : subjectid,
			"description" : description		
	},	
	$.ajax({
		
		type : 'POST',
		url : "communicationPath.html?method=saveMeetingAction",
		data : datalist,
		async : false,
	
		success : function(data) {
			
			
			$(".errormessagediv").hide();
			$(".successmessagediv").show();
			
			 $(".validateTips").text("Meeting Created Successfully");
			
			setTimeout(function(){
				
				 location.reload();

			},2000);

			
		}
		
		
	});
	
	
	
	setTimeout(function(){
		
		 location.reload();

	},2000);

	
	
   
});



















});



/*var classlength=0;
function getClass(classid,streamname) {
	
	var streamname = $("#streamname").val();
	datalist = {
			"streamname" : streamname
		},
		
		
		$.ajax({
			
			type : 'POST',
			url : "communicationPath.html?method=getClass",
			data : datalist,
			success : function(response) {
				
				
				var result = $.parseJSON(response);
				
				$('#classid').html("");
				$('#classid').append(
						
						'<option value="' + "" + '">' + "---Select---"
								
						+ '</option>');
				for ( var j = 0; j < result.classlist.length; j++) {

					$('#classid').append(

					'<option value="'

					+ result.classlist[j].classId + '">'

					+ result.classlist[j].className

					+ '</option>');

				}
				
			}	
		});
		
}
*/

var sectionlength=0;
function getSection() {
	
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
					
					/*'<option value="' + "" + '">' + "---Select---"
							
					+ '</option>');*/
			
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


function validateRemark(){
	
	
	
		
	var remark_Available=0;	
	
	var s2=$('.istrans[name="selectType"]:checked').val();
	var s1=$('.radioactive[name="gender"]:checked').val();
	var dateId = $('#dateId').val();
	var rematksto = $('#remarksid').val();
	var subjectid = $('#subjectid').val();
	var remarks = $('#remarks').val();
	
	
	datalist={
			"studentid" : s1,
			"remarksto" : rematksto,
			"remarkstype" : s2,
			"subjectid" : subjectid,
			"remarks" : remarks,
			"dateId" : dateId
	},
	
	$.ajax({
		type : "GET",
		url : 'communicationPath.html?method=validateRemark',
		data : datalist,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			
			
			
			if (result.status=="true") {
				
				remark_Available = 1;
				
				}
				else 
					
				{
					remark_Available = 0;
				}
			
			}
		});
		
		return remark_Available;
		
	
	
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




