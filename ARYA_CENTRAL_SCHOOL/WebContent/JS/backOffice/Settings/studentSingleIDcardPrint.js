$(document).ready(function(){
	
	$(".section").each(function(){
		var qrId=$(this).find(".qrcodes").attr("id");
		var qrcode = new QRCode(qrId);
		qrcode.makeCode("Name:"+$(this).find("#nametext").text()+
				" AdmissionNo:"+$(this).find("#admNotext").text()+
				" Class:"+$(this).find("#classtext").text()+
				" House:"+$(this).find("#houNotext").text()+
				" Valid Upto:"+$(this).find("#validUptotext").text()+
				" Phone No.:"+$(this).find("#phonetext").text()+
				" Address:"+$(this).find("#addresstext").text()+
				" Father Name:"+$(this).find("#faterName").text()+
				" Mother Name:"+$(this).find("#motherName").text()+
				" Emergency No.:"+$(this).find("#emergencyNo").text());
	});
	$("#printsingle").click(function(){
		singleprint();
	});	
	var status=$("#confidentialStatusId").val();
	if(status == "AVAILABLE"){
		$('#confidentialStatusId').css("background-color", "yellow");
	}else{
		$('#confidentialStatusId').css("background-color", "#fff");
	}
	
	$('#goPage').click(function(){
		
		var forwardPage = $('.nil').val();
		
		if(forwardPage=='adm_Form'){
			alert('adm_Form');
			
			window.location.href="adminMenu.html?method=tempadmissionMenu";
			
		}else if(forwardPage=='conf_Report'){
			alert('conf_Report');
			window.location.href="adminMenu.html?method=studentConfidentialReport";
			
		}else if(forwardPage=='id_Card'){
			alert('id');
			window.location.href="adminMenu.html?method=NewstudentIdCardDesign";
			
		}else if(forwardPage=='mis_Report'){
			alert('mis');
			window.location.href="adminMenu.html?method=miscellaneousReport";
			
		}else{
			alert('tc');
			window.location.href="adminMenu.html?method=studentTransferCertificateList";
		}
	});
	
	showContactDetails();
	
	var StudentImage=$("#photohiddenid").val();
	if(StudentImage!=""){

		$("#imagePreview").show();
		$('#imagePreview').attr('src', StudentImage);
	}
	
	$('#contacts').click(function(){
		showContactDetails();
	});
	
	$('#classHistory').click(function(){
		
		var studentId = $('#hstudentid').val();
		var accyear = $('#hacademicYearId').val();
		var locationId = $('#hschoolNameId').val();
		
		
		$('#studenttable').hide();
		$('#individualstudenttable').show();
		
		$('#individualstudenttable').empty();
		$("#individualstudenttable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Academic Year</th>" +
				"<th>Class</th>" +
				"<th>Section</th>"+
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
			
					$("#individualstudenttable #allstudent").append("<tr>"
							+"<td>"+result.studentSearchList[j].count+"</td>" 
							+"<td> "+result.studentSearchList[j].academicYear+" </td>"
							+"<td> "+result.studentSearchList[j].classname+"</td>"
							+"<td> "+result.studentSearchList[j].sectionnaem+" </td>"
							+"<td> "+result.studentSearchList[j].studentrollno+" </td>"
							+"<td> "+result.studentSearchList[j].studentStatus+" </td>"
							+"</tr>");
					}	
			}
			
		});
	});

});

function showContactDetails(){
	
	var studentId = $('#hstudentid').val();
	var accyear = $('#hacademicYearId').val();
	var locationId = $('#hschoolNameId').val();

	
	$('#studenttable').show();
	$('#individualstudenttable').hide();
	
	$('#studenttable').empty();
	$("#studenttable").append("<table class='table' id='allstudent' width='100%'" +">"
			+"<center><tr><th>SI No</th>"+
			"<th>Relationship</th>" +
			"<th>Name</th>" +
			"<th>Mobile No</th>"+
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
		
				$("#studenttable #allstudent").append(
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

function singleprint(){
	
	 var a=$("#printing-css-a4").val();
		var b = document.getElementById("studentpreview").innerHTML;

	    var abd='<style>' + a +'</style>' + b;
	
	   
	    
  var frame1 = $('<iframe />');
  frame1[0].name = "frame1";
  frame1.css({ "position": "absolute", "top": "-1000000px" });
  $("body").append(frame1);
  var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
  frameDoc.document.open();
  //Create a new HTML document.
  frameDoc.document.write('<html><head><title>DIV Contents</title>');
  //Append the external CSS file.
  frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
  frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
  frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
  frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
  frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
  frameDoc.document.write('<link href="CSS/IdCard/IdCard.css" rel="stylesheet" type="text/css" />');
  frameDoc.document.write('<link href="CSS/IdCard/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
  frameDoc.document.write('</head><body>');

  
  //Append the DIV contents.
  frameDoc.document.write(abd);
  frameDoc.document.write('</body></html>');
  frameDoc.document.close();
  setTimeout(function () {
      window.frames["frame1"].focus();
      window.frames["frame1"].print();
      frame1.remove();
  }, 100);
}