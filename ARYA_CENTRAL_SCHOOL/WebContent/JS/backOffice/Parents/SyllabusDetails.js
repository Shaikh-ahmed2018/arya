
$(document).ready(function() {
	
	getSubjectDetails();	
	
	$("#stuid").change(function(){
		
		$.ajax({
			
			type : "POST",
			url : "parentMenu.html?method=getStudentDetails",
			data : {"studid" : $("#stuid").val()},
			async : false,
			success : function(data){
				var result = $.parseJSON(data);
				$("#hclassid").val(result.details.split(" ")[0]);
				$("#hlocid").val(result.details.split(" ")[1]);
				getSubjectDetails();
			}
		});
	});
});


function getSubjectDetails() {
				
	$.ajax({
		
		type : "POST",
		url : "parentMenu.html?method=getSubjectDetails",
		data : {"classid" : $("#hclassid").val(),"locid" : $("#hlocid").val()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			$("#mytable").empty();
			$("#mytable").append("<table id='allstudent' class='table' width='100%'><thead><th>Sl.No</th><th>Subject</th><th>Description</th><th>Download Syllabus</th></thead><tbody></tbody></table>")
			if(result.dataList.length >0){
			for(var i=0;i<result.dataList.length;i++){
				$("#allstudent tbody").append("<tr>" +"<td>"+result.dataList[i].sno+"</td>"+
						"<td>"+result.dataList[i].subjectname+"</td>"+
						"<td>"+result.dataList[i].description+"</td>"+
						"<td>"+"<span class='' onclick='downloadfunction(this)'id='${allstudent.subjectid},download'>Download</span></td>"+
						"</tr>");
			}
		}else{
			$("#allstudent tbody").append("<tr><td colspan='4'>No Records Found</td></tr>")
		}
		}
	});
}


function getvaldetails(value){
	
	var s1 =value.id;
	var subjectid = s1;
	$("#subjectid").val(subjectid);
	
}


function downloadfunction(val){
	
	var subid =val.id;
	var subjectid=subid.split(",");

	if(subjectid == ""|| subjectid ==null){
		
		$('.errormessagediv').show();
		$('.validateTips').text("No File for download");
		return false;
	}
	
	else{
		window.location.href = "parentMenu.html?method=downloadSubject&subjectid="+subjectid[0];
	}
	
}

