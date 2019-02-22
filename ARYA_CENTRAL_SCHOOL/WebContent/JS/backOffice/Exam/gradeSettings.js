
$(document).ready(function(){
	
	var hacademicyear=$('#hiddenaccyear').val();
	$("#accyear").val(hacademicyear);
	
	var year =$("#hiddenlocationid").val();
	$("#location").val(year);
	
	gradeList($("#location"),$("#accyear"));
	
	$("#accyear").change(function(){
		gradeList($("#location"),$(this));
		$("#allstudent tbody tr").click(function(){
			
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			window.location.href="examCreationPath.html?method=addGradeSettings&accyear="+accyear+"&location="+location;
		});
	});
	

	$("#location").change(function(){
		gradeList($(this),$("#accyear"));
		$("#allstudent tbody tr").click(function(){
			
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			window.location.href="examCreationPath.html?method=addGradeSettings&accyear="+accyear+"&location="+location;
		});
	});
	
	$("#allstudent tbody tr").click(function(){
		
		var accyear = $( this ).find(".accyear").attr("id");
		var location = $( this ).find(".locname").attr("id");
		window.location.href="examCreationPath.html?method=addGradeSettings&accyear="+accyear+"&location="+location;
	});
	
	$(".grade").click(function(){
		$("#gradeOne").slideToggle();
	});
	
});

function gradeList(pointer,pointer1){
	$.ajax({
		
		type : "POST",
		url : "examCreationPath.html?method=gradeList",
		data : {"location":pointer.val(),"accyear":pointer1.val()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			
			$("#markstable").empty();
			$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
					"<th>Sl.No</th>" +
					"<th>Academic Year</th>" +
					"<th>Location</th>" +
					"<th>Status</th>"+
					"</tr></thead>" +
					"<tbody></tbody></table>"
					);
			
			for(var i=0;i<result.accYearList.length;i++){
				
				$("#markstable #allstudent tbody").append(
						"<tr>"+
						"<td>"+result.accYearList[i].sno1+"</td>"+
						"<td class='accyear' id='"+result.accYearList[i].accyearid+"'>"+result.accYearList[i].accyear+"</td>"+
						"<td  class='locname' id='"+result.accYearList[i].locationid+"'>"+result.accYearList[i].locname+"</td>"
						+"<td><span class="+result.accYearList[i].status+">"+result.accYearList[i].status+"</span></td>"
						+"</tr>"
				);
				
			}

		}
	});
}