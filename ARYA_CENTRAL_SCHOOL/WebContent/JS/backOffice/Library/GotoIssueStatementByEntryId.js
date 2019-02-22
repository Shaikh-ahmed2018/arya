$(document).ready(function(){

	var size=parseInt($("#hidensize").val());
	if(size==0){
		$("#allstudent tbody").empty();
		$("#allstudent tbody").append("<tr><td colspan=4>No Records Found</td></tr>");
	}
	

	var size1=parseInt($("#hidensize1").val());
	if(size1==0){
		$("#allstudent tbody").empty();
		$("#allstudent tbody").append("<tr><td colspan=5>No Records Found</td></tr>");
	}
});

