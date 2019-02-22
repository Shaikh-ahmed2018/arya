$(document).ready(function(){
	
	var hacademicyear=$('#hiddenaccyear').val();
	$("#acayear").val(hacademicyear);
	
	var year =$("#hiddenlocationid").val();
	$("#location").val(year);
	disstumarksdetails($("#acayear"),$("#location"));
	
	$("#acayear").change(function(){
		disstumarksdetails($(this),$("#location"));
		
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			window.location.href="examTimetablePath.html?method=setMarkEntryDetails&accyear="+accyear+"&hschoolLocation="+location;
		}); 
		
		
	});
	$("#location").change(function(){
		disstumarksdetails($("#acayear"),$(this));
		
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			window.location.href="examTimetablePath.html?method=setMarkEntryDetails&accyear="+accyear+"&hschoolLocation="+location;
		}); 
		
		
	});
	
	
	
	$("#allstudent tbody tr").click(function(){
		var accyear = $( this ).find(".accyear").attr("id");
		var location = $( this ).find(".locname").attr("id");
		window.location.href="examTimetablePath.html?method=setGradeDependency&accyear="+accyear+"&hschoolLocation="+location;
	}); 
	

	$(".Student").click(function(){
		$("#StudentOne").slideToggle();
	});
});

function disstumarksdetails(pointer,pointer1){
	$.ajax({
		type : "POST",
		url : "examTimetablePath.html?method=disstudepdetails",
		data : {"location":pointer1.val(),"accyear":pointer.val()},
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
			
			for(var i=0;i<result.markslist.length;i++){
				$("#markstable #allstudent").append(
						"<tr>"+
						"<td>"+result.markslist[i].sno1+"</td>"+
						"<td class='accyear' id='"+result.markslist[i].accyearid+"'>"+result.markslist[i].accyear+"</td>"+
						"<td  class='locname' id='"+result.markslist[i].locationid+"'>"+result.markslist[i].locname+"</td>"
						+"<td><span class="+result.markslist[i].status+">"+result.markslist[i].status+"</span></td>"
						+"</tr>"
				);
				
			}

		}
	});
}

