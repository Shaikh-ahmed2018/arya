$(document).ready(function(){
	
	var status=$("#confidentialStatusId").val();
	if(status == "AVAILABLE"){
		$('#confidentialStatusId').css("background-color", "yellow");
	}else{
		$('#confidentialStatusId').css("background-color", "#fff");
	}
	
	$('#goPage').click(function(){
		
		var forwardPage = $('.nil').val();
		
		if(forwardPage=='adm_Form'){
			window.open(
					  "studentRegistration.html?method=editStudent&searchTerm="+$('#hstudentid').val()+","+$('#hacademicYearId').val(),
					  '_blank' // <- This is what makes it open in a new window.
					);
			//window.location.href="studentRegistration.html?method=editStudent&searchTerm="+$('#hstudentid').val()+","+$('#hacademicYearId').val();
			
		}else if(forwardPage=='conf_Report'){
			window.open(
					  "adminMenu.html?method=AddStudentConfidentialReport&studentId="+$('#hstudentid').val()+"&accyear="+$('#hacademicYearId').val()+"&locationId="+$('#hschoolNameId').val(),
					  '_blank' // <- This is what makes it open in a new window.
					);
			//window.location.href="adminMenu.html?method=AddStudentConfidentialReport&studentId="+$('#hstudentid').val()+"&accyear="+$('#hacademicYearId').val()+"&locationId="+$('#hschoolNameId').val();
			
		}else if(forwardPage=='id_Card'){
			window.open(
					  "adminMenu.html?method=PrintStudentSingleIDCard&studentId="+$('#hstudentid').val()+"&accyear="+$('#hacademicYearId').val()+"&locationId="+$('#hschoolNameId').val(),
					  '_blank' // <- This is what makes it open in a new window.
					);
			//window.location.href="adminMenu.html?method=PrintStudentSingleIDCard&studentId="+$('#hstudentid').val()+"&accyear="+$('#hacademicYearId').val()+"&locationId="+$('#hschoolNameId').val();
			
		}else if(forwardPage=='mis_Report'){
			window.open(
					  "adminMenu.html?method=individualMisreport&studentId="+$('#hstudentid').val()+"&accyear="+$('#hacademicYearId').val()+"&locationId="+$('#hschoolNameId').val(),
					  '_blank' // <- This is what makes it open in a new window.
					);
			//window.location.href="adminMenu.html?method=individualMisreport&studentId="+$('#hstudentid').val()+"&accyear="+$('#hacademicYearId').val()+"&locationId="+$('#hschoolNameId').val();
			
		}else{
			window.open(
					  "adminMenu.html?method=studentTransferCertificateList",
					  '_blank' // <- This is what makes it open in a new window.
					);
			//window.location.href="adminMenu.html?method=studentTransferCertificateList";
		}
	});
	
	
	
	var StudentImage=$("#photohiddenid").val().trim();
	if(StudentImage!=""){

		$("#imagePreview").show();
		$('#imagePreview').attr('src', StudentImage);
	}
	
	$('#contacts').click(function(){
		showContactDetails();
	});
	$("#ContactAddr").click(function(){
		
		ShowStudentAddress();
	});
	
	$('#classHistory').click(function(){
		
		var studentId = $('#hstudentid').val();
		var accyear = $('#hacademicYearId').val();
		var locationId = $('#hschoolNameId').val();
	
		
		$('#studenttable').hide();
		$('#individualstudenttable').show();
		$('#Addressstudenttable').hide();
		
		$('#individualstudenttable').empty();
		$("#individualstudenttable").append("<table class='table allstudent' id='allstudent1' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Academic Year</th>" +
				"<th>Class</th>" +
				"<th>Division</th>"+
				"<th>Roll No</th>"+
				"<th>Status</th>"+
				"</center></tr>" +
				"</table>"
		);
		
		$.ajax({
			
			type : "POST",
			url : "studentRegistration.html?method=individualStudentSearch",
			data : {"studentId":studentId,
					"accyear":accyear,
					"locationId":locationId			
			},
			async : false,
			
			success : function(response) {

				var result = $.parseJSON(response);

			
				for (var j = 0; j < result.studentSearchList.length; j++) {
			
					$("#individualstudenttable #allstudent1").append("<tr>"
							+"<td>"+result.studentSearchList[j].count+"</td>" 
							+"<td> "+result.studentSearchList[j].academicYear+" </td>"
							+"<td> "+result.studentSearchList[j].classname+"</td>"
							+"<td> "+result.studentSearchList[j].sectionnaem+" </td>"
							+"<td> "+result.studentSearchList[j].studentrollno+" </td>"
							+"<td> "+result.studentSearchList[j].status+" </td>"
							+"</tr>");
					}	
			}
			
		});
	});
	
});

function showContactDetails(){
	$("#contacts").parent("li").addClass("active");
	$("#classHistory").parent("li").removeClass("active");
	$("#ContactAddr").parent("li").removeClass("active");
	var studentId = $('#hstudentid').val();
	var accyear = $('#hacademicYearId').val();
	var locationId = $('#hschoolNameId').val();

	
	$('#studenttable').show();
	$('#individualstudenttable').hide();
	$('#Addressstudenttable').hide();
	
	$('#studenttable').empty();
	$("#studenttable").append("<table class='table allstudent' id='allstudent2' width='100%'" +">"
			+"<center><tr><th>SI No</th>"+
			"<th>Relationship</th>" +
			"<th>Name</th>" +
			"<th>Mobile No</th>"+
			"</center></tr>" +
			"</table>"
	);
	
	$.ajax({
		
		type : "POST",
		url : "studentRegistration.html?method=showContactDetails",
		data : {"studentId":studentId,
				"accyear":accyear,
				"locationId":locationId			
		},
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

		
			for (var j = 0; j < result.studentSearchList.length; j++) {
		
				$("#studenttable #allstudent2").append(
						"<tr>"
						+"<td>1</td>" 
						+"<td>Father</td>"
						+"<td> "+result.studentSearchList[j].fatherName+"</td>"
						+"<td> "+result.studentSearchList[j].fatherMobileNo+" </td>"
						+"</tr>"
						+"<tr>"
						+"<td>2</td>" 
						+"<td>Mother</td>"
						+"<td> "+result.studentSearchList[j].motherName+"</td>"
						+"<td> "+result.studentSearchList[j].motherMobileNo+" </td>"
						+"</tr>"
						+"<tr>"
						+"<td>3</td>" 
						+"<td>Guardian</td>"
						+"<td> "+result.studentSearchList[j].gaurdianName+"</td>"
						+"<td> "+result.studentSearchList[j].guardianMobileNo+" </td>"
						+"</tr>"
					);
				}	
		}
		
	});
}




function ShowStudentAddress(){
	
	var studentId = $('#hstudentid').val();
	var accyear = $('#hacademicYearId').val();
	var locationId = $('#hschoolNameId').val();

	$('#Addressstudenttable').show();
	$('#studenttable').hide();
	$('#individualstudenttable').hide();
	
	
	$('#Addressstudenttable').empty();
	$("#Addressstudenttable").append("<table class='table allstudent' id='allstudent3' width='100%'" +">"
			+"<center><tr><th >SI No</th>"+
			"<th style='width:100px;'>Address</th>" +
			"<th style='width:100px;'>Present Address</th>" +
			"</table>"
	);
	
	$.ajax({
		
		type : "POST",
		url : "studentRegistration.html?method=ShowStudentAddress",
		data : {"studentId":studentId,
				"accyear":accyear,
				"locationId":locationId			
		},
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

		
			for (var j = 0; j < result.studentSearchList.length; j++) {
		
				$("#Addressstudenttable #allstudent3").append(
						"<tr>"
						+"<td>"+result.studentSearchList[j].count+"</td>" 
						+"<td style='width:100px;'> "+result.studentSearchList[j].address+" </td>"
						+"<td style='width:100px;'> "+result.studentSearchList[j].presentaddress+"</td>"
						+"</tr>"
					
					);
				}	
		}
		
	});
}












