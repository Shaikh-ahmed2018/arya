$(document).ready(function(){
	$(".class").click(function(){
		$("#classOne").slideToggle();
	});
	var year =$("#hiddenlocationid").val();
	$("#location").val(year);
	
	var hacademicyear=$('#hiddenaccyear').val();
	$("#accyear").val(hacademicyear);
	
	getaccyeardetails($("#accyear"),$("#location"));
	
	$("#accyear").change(function(){
		getaccyeardetails($("#accyear"),$("#location"));
	});
	
	$("#location").change(function(){
		getaccyeardetails($("#accyear"),$("#location"));
	});
	
	$("#allstudent tbody tr").click(function(){
		var accyear = $(this).find(".accyear").attr("class").split(" ")[0];
		var locid = $(this).find(".locname").attr("class").split(" ")[0];
		
		window.location.href = "examTimetablePath.html?method=getexamtimetableclass&accyear="+accyear+
		"&locid="+locid;
	});
	
});


function getaccyeardetails(accyear,locationid){
	
	datalist = {
			
			"location" :locationid.val(),
			"accyear" :accyear.val(),
		}, $.ajax({
			type : 'POST',
			url : "examTimetablePath.html?method=getaccyeardetails",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#mytable").empty();
					$("#mytable").append("<table class='table' name='stuid' id='allstudent' width='100%'>"+"<tr>"+
							"<th>Sl.No</th>" +
							"<th>Accademic Year</th>" +
							"<th>Location</th>" +
							"<th>Status</th>"+
							"</tr>" +
							"</table>"
							);

					for(var i=0;i<result.examTimeTableListYear.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td>"+result.examTimeTableListYear[i].sno1+"</td>" 
							+"<td class='"+result.examTimeTableListYear[i].accyearid+" accyear'>"+result.examTimeTableListYear[i].accyear+"</td>"
							+"<td  class='"+result.examTimeTableListYear[i].locationid+" locname'>"+result.examTimeTableListYear[i].locname+"</td>"
							+"<td><span class='"+result.examTimeTableListYear[i].status1+"'>"+result.examTimeTableListYear[i].status+"</span></td>"
							+"</tr>");
					}	
					
					$("#allstudent").after("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select></div><div class='pagination pagelinks'></div>")
					pagination(50);
					$("#show_per_page").change(function(){
						pagination($(this).val());
					});
					$("#allstudent tbody tr").click(function(){
						var accyear = $(this).find(".accyear").attr("class").split(" ")[0];
						var locid = $(this).find(".locname").attr("class").split(" ")[0];
						
						window.location.href = "examTimetablePath.html?method=getexamtimetableclass&accyear="+accyear+
						"&locid="+locid;
					});
			}
				
		
		});
	
	}