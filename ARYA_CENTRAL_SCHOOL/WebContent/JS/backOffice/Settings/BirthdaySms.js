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

	
	$("#streamname").change(function(){
		
	
		
		 $('#classid').show();
	    $('#sectionid').show();
		
		getClass(classid,streamname);
		
	});
	
	
	
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
					
					'<option value="' + "" + '">' + "---Select---"
							
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





$("#bdaysave").click(function(){
	
	
	
	var s2=$('.istrans[name="selectType"]:checked').val();
	
	var  s1=$('.radioactive[name="gender"]:checked').val();
	
	
	var dateId = $('#dateId').val();
	var streamname= $('#streamname').val();
	var classname= $('#classid').val();
	var sectionname= $('#sectionid').val();
	var bdayto = $('#remarksid').val();
	var content = $('#remarks').val();
	
	
	
	
	
	

   if(s1==""||s1==null){
		
		
		$(".errormessagediv").show();
		$(".validateTips").text("Select Wishes To");

		
		
		
		return false;	
		
	}
	
 
	if(s1=="S"){
		
		
	  if(streamname==""||streamname==null){
				
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Stream Name");
				
				document.getElementById("streamname").style.border = "1px solid #AF2C2C";
				document.getElementById("streamname").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				
				return false;	
				
			}
		
	  

		
	    else if(classname ==""||classname ==null){
	    	
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
		
		 
		  else if(dateId==""||dateId==null){
				
				
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Date");
				
				document.getElementById("dateId").style.border = "1px solid #AF2C2C";
				document.getElementById("sectionid").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				
				return false;	
				
			}
		
		 
		   else if(bdayto ==""||bdayto ==null){
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Student Name");
				document.getElementById("remarksid").style.border = "1px solid #AF2C2C";
				document.getElementById("remarksid").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				
				return false;	
		
	}
		
	  

		   else if(content ==""||content ==null){
				$(".errormessagediv").show();
				
				$(".validateTips").text("Write the Content");
				
				document.getElementById("remarks").style.border = "1px solid #AF2C2C";
				document.getElementById("remarks").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;	
		
	}
	  
	  
	}

	
	
	
	
      else if(s1=="T"){
			   
    	  
    	  if(dateId==""||dateId==null){
				
				
				$(".errormessagediv").show();
				
				$(".validateTips").text("Select Date");
				document.getElementById("dateId").style.border = "1px solid #AF2C2C";
				document.getElementById("sectionid").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;	
				
			}
		
    	  else if(bdayto ==""||bdayto ==null){
    		$(".errormessagediv").show();
			
			$(".validateTips").text("Select Teacher Name");
			
			document.getElementById("remarksid").style.border = "1px solid #AF2C2C";
			document.getElementById("remarksid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;	
    		
    	}
    	
    	   else if(content ==""||content ==null){
				$(".errormessagediv").show();
				
				$(".validateTips").text("Write the Content");
				
				document.getElementById("remarks").style.border = "1px solid #AF2C2C";
				document.getElementById("remarks").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				
				return false;	
		
	}

       
    	
    	
		   }
	
	
  
	
	datalist={
			"studentid" : s1,
			"classname": classname,
			"sectionname": sectionname,
			"bdayto" : bdayto.join(","),
			"content" : content,
			"dateId" : dateId
			
	},
	
	$.ajax({
		
		type : 'POST',
		url : "communicationPath.html?method=saveBdayAction",
		data : datalist,
		async : false,
	
		success : function(data) {
			
	
			
			var result = $.parseJSON(data);
			
			
			 
			 if(result.jsonResponse=="Created Successfully")
				{
				
				 
				 $(".successmessagediv").show();
			     $(".errormessagediv").hide();
				
				 $(".validateTips").text("Created Successfully");
				 
				 
					setTimeout(function(){
						
						 location.reload();

					},5000);

				 
				} 
			 
			 
			 if(result.jsonResponse=="Creation failed")
				{
				
				 
				 $(".successmessagediv").hide();
			     $(".errormessagediv").show();
				
				 $(".validateTips").text("Creation failed");
				 
				 
				 
					setTimeout(function(){
						
						 location.reload();

					},5000);

				 
				} 
			 
			 
			 
			 
			 
			 
			
			setTimeout(function(){
				
				 location.reload();

			},5000);

			
		}
		
	
		
	});
	

	
	
	
   
});


setTimeout(function(){
	
	 location.reload();

},50000);



});



var classlength=0;
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


var sectionlength=0;
function getSection() {
	
	var classid = $("#classid").val();
	
	
	datalist={
			"classid" : classid
	},
	
	
	
	$.ajax({
		
		type : 'POST',
		url : "communicationPath.html?method=getSection",
		data : datalist,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append(
					
					'<option value="' + "" + '">' + "---Select---"
							
					+ '</option>');
			
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
document.getElementById("streamname").style.border = "1px solid #ccc";
document.getElementById("streamname").style.backgroundColor = "#fff";
document.getElementById("classid").style.border = "1px solid #ccc";
document.getElementById("classid").style.backgroundColor = "#fff";
document.getElementById("sectionid").style.border = "1px solid #ccc";
document.getElementById("sectionid").style.backgroundColor = "#fff";
document.getElementById("remarksid").style.border = "1px solid #ccc";
document.getElementById("remarksid").style.backgroundColor = "#fff";
document.getElementById("remarks").style.border = "1px solid #ccc";
document.getElementById("remarks").style.backgroundColor = "#fff";
}



