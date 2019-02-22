$(document).ready(function() {
	
	$('#individualstudenttable').show();
	$('#studenttable').hide();
	
	$('#individualstudenttable').empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent' width='100%'" +">"
			+"<center><tr><th>SI No</th>"+
			"<th>Admission No</th>" +
			"<th>Student Name</th>" +
			"<th>Roll No</th>" +
			"<th>Class</th>" +
			"<th>Division</th>" +
			"<th>Date Of Leaving</th>" +
			"<th>Purpose Of Leaving</th>" +
			"</center></tr>" +
			"</table>"
	);
	
	$("#individualstudenttable #allstudent").append(

			"<tr>"+
			"<td>1</td>"+
			"<td>10225</td>"+
			"<td>Md Isteyak</td>"+
			"<td>0001</td>"+
			"<td>X</td>"+
			"<td>A</td>"+
			"<td>10-Apr-2017</td>"+
			"<td>Passed Out</td>"+
			+"</tr>"
			
	);
	
	$('#reporthistory').click(function(){
		//alert("from contacts");
		$('#individualstudenttable').show();
		$('#studenttable').hide();
		
		$('#individualstudenttable').empty();
		$("#individualstudenttable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Admission No</th>" +
				"<th>Student Name</th>" +
				"<th>Roll No</th>" +
				"<th>Class</th>" +
				"<th>Division</th>" +
				"<th>Date Of Leaving</th>" +
				"<th>Purpose Of Leaving</th>" +
				"</center></tr>" +
				"</table>"
		);
		
		$("#individualstudenttable #allstudent").append(

				"<tr>"+
				"<td>1</td>"+
				"<td>10225</td>"+
				"<td>Md Isteyak</td>"+
				"<td>0001</td>"+
				"<td>X</td>"+
				"<td>A</td>"+
				"<td>10-Apr-2017</td>"+
				"<td>Passed Out</td>"+
				+"</tr>"
				
		);
	
	});
	
	
	$('#contacts').click(function(){
		//alert("from class history");
		$('#individualstudenttable').hide();
		$('#studenttable').show();
		
	
		$('#studenttable').empty();
		$("#studenttable").append("<table class='table' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Admission No</th>" +
				"<th>Student Name</th>" +
				"<th>Roll No</th>" +
				"<th>Class</th>" +
				"<th>Division</th>" +
				"<th>Date Of Leaving</th>" +
				"<th>Purpose Of Leaving</th>" +
				"</center></tr>" +
				"</table>"
				
		);
		
		$("#studenttable #allstudent").append(
				"<tr>"+
				"<td>1</td>"+
				"<td>4111</td>"+
				"<td>Suresh</td>"+
				"<td>2222</td>"+
				"<td>X</td>"+
				"<td>A</td>"+
				"<td>10-Apr-2017</td>"+
				"<td>Passed Out</td>"+
				+"</tr>"
				
		);
	
	});

	$("#individualstudenttable tbody tr").click(function(){
		
		window.location.href="adminMenu.html?method=transferCertificatePage";

	});
	
		$("#withheld").click(function(){
			
			window.location.href="adminMenu.html?method=studentWithheldList";

		});
		
		$("#withheld").click(function(){
			
			window.location.href="adminMenu.html?method=studentWithheldList";

		});
		
		$("#addnew").click(function(){
			
			window.location.href="adminMenu.html?method=findStudentForTransferCertificate";

		});
		
		

});