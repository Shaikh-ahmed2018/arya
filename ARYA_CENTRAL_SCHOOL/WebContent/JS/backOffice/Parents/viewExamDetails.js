$(document).ready(function(){
	
	viewExamdetails();
	
	$(".collapseOne").click(function(){
		$("#collapseOne").slidetoggle();
	});
	
});

function viewExamdetails(){
	
	datalist = {
			"locid" : $("#hiddenloc").val(),
			"examid" : $("#hiddenexamid").val(),
			"classid" : $("#hiddenclass").val(),
			"secid" : $("#hiddensecrtionid").val()
	}
	$.ajax({
		type : "POST",
		url : "parentMenu.html?method=displayExamdetail",
		data : datalist,
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			$("#mytable").empty();
			if(result.dataList.length >0){
			$("#mytable").append("<table  class='table' id='allstudent' width='100%'><thead><tr><th>Sl.No" +
			"</th><th>Subject Name</th><th>Exam Date</th><th>Start Time</th><th>End Time</th></tr></thead><tbody></tbody></table>")
			for(var i=0;i<result.dataList.length;i++){
				$("#allstudent").append("<tr>"+
				"<td>"+result.dataList[i].slno+"</td>"+
				"<td>"+result.dataList[i].subname+"</td>"+
				"<td>"+result.dataList[i].startdate+"</td>"+
				"<td>"+result.dataList[i].sttime+"</td>"+
				"<td>"+result.dataList[i].endtime+"</td>"+
				"</tr>");
			}
			}
			else{
				$(".errormessagediv").show();
				$(".validateTips").text("No Record Found");
				$(".errormessagediv").fadeOut(3000);
			}
		}
	});
}