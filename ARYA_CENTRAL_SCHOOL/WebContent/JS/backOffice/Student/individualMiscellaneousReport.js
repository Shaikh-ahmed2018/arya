$(document).ready(function(){	
	$('#contacttable').show();
	$('#individualtable').hide();
	$('#examdetailtable').hide();
	$('#feedetailtable').hide();
	$('#attendancetable').hide();
	$('#libraryinfotable').hide();
	$('#appraisaltable').hide();
	
	
	$('#contacttable').empty();
	$("#contacttable").append("<table class='table' id='allstudent' width='100%'" +">"
			+"<center><tr><th>SI No</th>"+
			"<th>Relationship</th>" +
			"<th>Name</th>" +
			"<th>Mobile No</th>"+
			"<th>Residential No</th>"+
			"</center></tr>" +
			"</table>"
	);
	
	$("#contacttable #allstudent").append(
			"<tr>"+
			"<td>1</td>"+
			"<td>Father</td>"+
			"<td>Abc</td>"+
			"<td>9876543210</td>"+
			"<td>123056</td>"+
			+"</tr>"
	);
	
	$('#contacts').click(function(){
		$('#contacttable').show();
		$('#individualtable').hide();
		$('#examdetailtable').hide();
		$('#feedetailtable').hide();
		$('#attendancetable').hide();
		$('#libraryinfotable').hide();
		$('#appraisaltable').hide();
		
		
		$('#contacttable').empty();
		$("#contacttable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Relationship</th>" +
				"<th>Name</th>" +
				"<th>Mobile No</th>"+
				"<th>Residential No</th>"+
				"</center></tr>" +
				"</table>"
		);
		
		$("#contacttable #allstudent").append(
				"<tr>"+
				"<td>1</td>"+
				"<td>Father</td>"+
				"<td>Abc</td>"+
				"<td>9876543210</td>"+
				"<td>123056</td>"+
				+"</tr>"
		);
	});

	
	$('#classHistory').click(function(){
		$('#individualtable').show();
		$('#contacttable').hide();
		$('#examdetailtable').hide();
		$('#feedetailtable').hide();
		$('#attendancetable').hide();
		$('#libraryinfotable').hide();
		$('#appraisaltable').hide();
		
		
		$('#individualtable').empty();
		$("#individualtable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Academic Year</th>" +
				"<th>Class</th>" +
				"<th>Section</th>"+
				"<th>Roll No</th>"+
				"<th>Status</th>"+
				"</center></tr>" +
				"</table>"
		);
		
		$("#individualtable #allstudent").append(
				"<tr>"+
				"<td>1</td>"+
				"<td>2017-2018</td>"+
				"<td>X</td>"+
				"<td>A</td>"+
				"<td>001</td>"+
				"<td>Studying</td>"+
				+"</tr>"
		);
	});
	
	$('#examDetails').click(function(){
		$('#examdetailtable').show();
		$('#contacttable').hide();
		$('#individualtable').hide();
		$('#feedetailtable').hide();
		$('#attendancetable').hide();
		$('#libraryinfotable').hide();
		$('#appraisaltable').hide();
		
		
		$('#examdetailtable').empty();
		$("#examdetailtable").append("<label class='examcodelabel'>Exam Code</label>&nbsp;&nbsp;"
					+"<select class='exam code'>"
						+"<option value='eysa1'>EYSA1</option>"
					+"</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		
		$("#examdetailtable").append("<label class='examnamelabel'>Exam Name</label>&nbsp;&nbsp;"
				+"<select class='exam name'>"
					+"<option value='sa1'>SA1</option>"
			    +"</select>");
		
		$("#examdetailtable").append("<br>"+"<br>");
		$("#examdetailtable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Subject Code</th>" +
				"<th>Subject Name</th>" +
				"<th>Pass Marks</th>"+
				"<th>Total Marks</th>"+
				"<th>Marks Scored</th>"+
				"<th>Grade</th>"+
				"</center></tr>" +
				"</table>"
		);
		
		$("#examdetailtable #allstudent").append(
				"<tr>"+
				"<td>1</td>"+
				"<td>ENG</td>"+
				"<td>English</td>"+
				"<td>50</td>"+
				"<td>100</td>"+
				"<td>90</td>"+
				"<td>A+</td>"+
				+"</tr>"
		);
	});
	
	$('#feedetails').click(function(){
		$('#feedetailtable').show();
		$('#contacttable').hide();
		$('#individualtable').hide();
		$('#examdetailtable').hide();
		$('#attendancetable').hide();
		$('#libraryinfotable').hide();
		$('#appraisaltable').hide();
		
		
		$('#feedetailtable').empty();
		$("#feedetailtable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Terms</th>" +
				"<th>Fee Amount</th>" +
				"<th>Fine Amount</th>"+
				"<th>Total Amount</th>"+
				"<th>Status</th>"+
				"</center></tr>" +
				"</table>"
		);
		
		$("#feedetailtable #allstudent").append(
				"<tr>"+
				"<td>1</td>"+
				"<td>TERM-1</td>"+
				"<td>25000</td>"+
				"<td>500</td>"+
				"<td>25500</td>"+
				"<td>Paid</td>"+
				+"</tr>"
		);
	});
	
	$('#attendance').click(function(){
		$('#attendancetable').show();
		$('#contacttable').hide();
		$('#individualtable').hide();
		$('#examdetailtable').hide();
		$('#feedetailtable').hide();
		$('#libraryinfotable').hide();
		$('#appraisaltable').hide();
		
		
		$('#attendancetable').empty();
		$("#attendancetable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Attendance</th>" +
				"<th>Date</th>" +
				"</center></tr>" +
				"</table>"
		);
		
		$("#attendancetable #allstudent").append(
				"<tr>"+
				"<td>1</td>"+
				"<td>Absent</td>"+
				"<td>11-May-2017</td>"+
				+"</tr>"
		);
	});
	
	$('#libraryInfo').click(function(){
		$('#libraryinfotable').show();
		$('#contacttable').hide();
		$('#individualtable').hide();
		$('#examdetailtable').hide();
		$('#feedetailtable').hide();
		$('#attendancetable').hide();
		$('#appraisaltable').hide();
		
		
		$('#libraryinfotable').empty();
		$("#libraryinfotable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Accession No</th>" +
				"<th>Title</th>" +
				"<th>Author</th>"+
				"<th>Issue Date</th>"+
				"<th>Return Date</th>"+
				"<th>Status</th>"+
				"</center></tr>" +
				"</table>"
		);
		
		$("#libraryinfotable #allstudent").append(
				"<tr>"+
				"<td>1</td>"+
				"<td>12345</td>"+
				"<td>Wings Of Fire</td>"+
				"<td>Dr.A.P.J.Abdul Kalam</td>"+
				"<td>11-May-2017</td>"+
				"<td>30-May-2017</td>"+
				"<td>Pending</td>"+
				+"</tr>"
		);
	});
	
	$('#appraisal').click(function(){
		$('#appraisaltable').show();
		$('#contacttable').hide();
		$('#individualtable').hide();
		$('#examdetailtable').hide();
		$('#feedetailtable').hide();
		$('#attendancetable').hide();
		$('#libraryinfotable').hide();
		
		
		$('#appraisaltable').empty();
		$("#appraisaltable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Meeting Date</th>" +
				"<th>Recommended By</th>" +
				"<th>Meeting With</th>"+
				"<th>Action Taken</th>"+
				"<th>Remarks</th>"+
				"<th>Status</th>"+
				"</center></tr>" +
				"</table>"
		);
		
		$("#appraisaltable #allstudent").append(
				"<tr>"+
				"<td>1</td>"+
				"<td>11-May-2017</td>"+
				"<td>Abc Teacher</td>"+
				"<td>Xyz Student</td>"+
				"<td>Warning</td>"+
				"<td>Given Warning</td>"+
				"<td>Completed</td>"+
				+"</tr>"
		);
	});
	
});
