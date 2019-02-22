$(document).ready(function(){
	
	var hacademicyear=$('#hiddenaccyear').val();
	$("#acayear").val(hacademicyear);
	
	var year =$("#hiddenlocationid").val();
	$("#location").val(year);
	
	displaysubmarkslist($("#acayear"),$("#location"));
	
	$("#acayear").change(function(){
		displaysubmarkslist($(this),$("#location"));
		$("#allstudent tbody tr").click(function(){

			var accyear = $( this ).find(".accyear").attr("id");
			var hschoolLocation=$( this ).find(".locname").attr("id");
			window.location.href="examCreationPath.html?method=getSubjectmarksStatus&accyear="+accyear+"&hschoolLocation="+hschoolLocation;
		});
		
	});
	$("#location").change(function(){
		displaysubmarkslist($("#acayear"),$(this));
		$("#allstudent tbody tr").click(function(){

			var accyear = $( this ).find(".accyear").attr("id");
			var hschoolLocation=$( this ).find(".locname").attr("id");
			window.location.href="examCreationPath.html?method=getSubjectmarksStatus&accyear="+accyear+"&hschoolLocation="+hschoolLocation;
		});
		
	});
	
	
	$("#allstudent tbody tr").click(function(){

		var accyear = $( this ).find(".accyear").attr("id");
		var hschoolLocation=$( this ).find(".locname").attr("id");
		window.location.href="examCreationPath.html?method=getSubjectmarksStatus&accyear="+accyear+"&hschoolLocation="+hschoolLocation;
	});
	
	$(".grade").click(function(){
		$("#gradeOne").slideToggle();
	});
	
});

function displaysubmarkslist(pointer,pointer1){

	

	$.ajax({
		
		type : "POST",
		url : "examTimetablePath.html?method=displaysubmarkslist",
		data : {"accyear":pointer.val(),"location":pointer1.val()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			
			$("#markstable").empty();
			$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
					"<th>Sl.No</th>" +
					"<th>Academic Year</th>" +
					"<th>Location</th>" +
					"<th>Status</th>"+
					"</tr></thead><tbody></tbody>" +
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