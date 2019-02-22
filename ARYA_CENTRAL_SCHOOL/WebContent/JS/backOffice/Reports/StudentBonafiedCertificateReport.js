$(document).ready(function() {
	
	$("#location").change(function(){
		getClassList();
		
		
	});
	
	$("#class").change(function(){
		getSectionList();
	});
	
	$("#accyear").val($("#hacademicyaer").val());
	$("#classname").change(function(){
		
		
		var classname=$("#classname").val();
		
		
		
		datalist={
				"classname" : classname
				
		},
		
		$.ajax({
			
			type : 'POST',
			url : "studentTransferReport.html?method=getSection",
			data : datalist,
			async : false,
			success : function(response) {
				
				var result = $.parseJSON(response);
				
				$('#sectionid').html("");
				
				$('#sectionid').append(
						
						'<option value="' + "" + '">' + " "
								
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
		
	});
	
	
	
	
	
	$("#sectionid").change(function(){
		
		var sectionid=$("#sectionid").val();
		
		
		
		datalist={
				"sectionid" : sectionid
				
		},
		
		$.ajax({
			
			type : 'POST',
			url : "studentTransferReport.html?method=getAllStudentNames",
			data : datalist,
			async : false,
			success : function(response) {
				
				
				
				var result = $.parseJSON(response);
				
				
				
				$('#studentname').html("");
				
				$('#studentname').append(
						
						'<option value="' + "" + '">' + " "
								
						+ '</option>');
				
				for ( var j = 0; j < result.studentlist.length; j++) {

					$('#studentname').append(

					'<option value="'

					+ result.studentlist[j].studentid + '">'

					+ result.studentlist[j].studentFnameVar

					+ '</option>');

				}
				
			}
		
		
	});
	
	
	
	});	
	
	
	
	$("#pdfDownload").click(function(){
		
		
		
		
		var accyear=$("#accyear").val();
		var sectionid=$("#sectionid").val();
		var description=$("#description").val();
		var classname=$("#classname").val();
		var studentname=$("#studentname").val();
		
		
		if(accyear==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Academic Year");
			
			return false;
			
		}
		
 	 if(classname==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Class Name ");
			
			return false;
			
		}
 	 if(sectionid==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Section Name ");
			
			return false;
			
		}
		
 	 if(studentname==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Student Name ");
			
			return false;
			
		}
 	
	 else{
 		 
 		window.location.href ='studentBonafiedReport.html?method=studentBonafiedCertificatePDFReport&accyear='
			+accyear
			+ ' &sectionid='
			+sectionid
			+ ' &classname='
			+classname
			+ ' &studentname='
			+studentname
			+ '&description='
			+description;
		
 		 
 	 }

	
		
		
	});	
});	
function getClassList(){
	var locationid=$("#location").val();
	datalist={
			"locationid" : locationid
	},

	$.ajax({

		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#class').html("");

			$('#class').append('<option value="all">' + "----------Select----------"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#class').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function getSectionList()
{
	

	var classId=$("#class").val();


	$.ajax({
		type : "GET",
		url : "reportaction.html?method=getSectionByClass",
		data : {"classId":classId},
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);

			$("#section").html("");
			$("#section").append('<option value="all">' + "----------Select----------"	+ '</option>');

			for (var j = 0; j < result.SectionList.length; j++) {

				$("#section").append(
						'<option value="'
						+ result.SectionList[j].sectionId
						+ '">'
						+ result.SectionList[j].sectionname
						+ '</option>');

			}
		}
	});

}