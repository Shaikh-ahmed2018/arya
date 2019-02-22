$(document).ready(function(){
	
	var hacademicyear=$('#hiddenaccyear').val();
	$("#acayear").val(hacademicyear);
	
	var year =$("#hiddenlocationid").val();
	$("#location").val(year);
	
	getexamsettingslist($("#acayear"),$("#location"));
	
	$("#acayear").change(function(){
		getexamsettingslist($(this),$("#location"));
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			window.location.href="examTimetablePath.html?method=setExamDetails&accyear="+accyear+"&location="+location;
		});
	});
	
	$("#location").change(function(){
		getexamsettingslist($("#acayear"),$(this));
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			window.location.href="examTimetablePath.html?method=setExamDetails&accyear="+accyear+"&location="+location;
		});});
	
	$("#allstudent tbody tr").click(function(){
		var accyear = $( this ).find(".accyear").attr("id");
		var location = $( this ).find(".locname").attr("id");
		window.location.href="examTimetablePath.html?method=setExamDependency&accyear="+accyear+"&location="+location;
	});
	
	$(".exam").click(function(){
		$("#ExamOne").slideToggle();
	});
	
});

function getexamsettingslist(pointer,pointer1){
	$.ajax({
		
		type : "POST",
		url : "examCreationPath.html?method=getexamsettingslistfordep",
		data : {"location":pointer.val(),"accyear":pointer1.val()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			
			$("#markstable").empty();
			
			$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<tr>"+
					"<th>Sl.No</th>" +
					"<th>Academic Year</th>" +
					"<th>Location</th>" +
					"<th>Status</th>"+
					"</tr>" +
					"</table>"
					);
		
			for(var i=0;i<result.examlist.length;i++){
				
				$("#markstable #allstudent").append(
						"<tr>"+
						"<td>"+result.examlist[i].sno1+"</td>"+
						"<td class='accyear' id='"+result.examlist[i].accyearid+"'>"+result.examlist[i].accyear+"</td>"+
						"<td  class='locname' id='"+result.examlist[i].locationid+"'>"+result.examlist[i].locname+"</td>"
						+"<td><span class="+result.examlist[i].status+">"+result.examlist[i].status+"</span></td>"
						+"</tr>"
				);
			}
		}
	});

	
	
}