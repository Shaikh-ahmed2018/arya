$(document).ready(function(){	

	$(".class").click(function(){
		$("#classOne").slideToggle();
	});
	
	var hacademicyear=$('#hiddenaccyear').val();
	$("#accyear").val(hacademicyear);
	
	var year =$("#hiddenlocationid").val();
	$("#location").val(year);
	houseSettings($("#location"),$("#accyear"));
	
	$("#location").change(function(){
	houseSettings($(this),$("#accyear"));
	
	$("#allstudent tr").click(function(){
		
		var accyearid = $(this).find(".accyearid").attr("id");
		var locid=  $(this).find(".locname").attr("id");
		
		window.location.href="houseSettings.html?method=AcademicYearHouseSetting&accyear="+accyearid+"&locid="+locid;
	});
});
	
	$("#accyear").change(function(){
		houseSettings($("#location"),$(this));
		
		$("#allstudent tr").click(function(){
			
			var accyearid = $(this).find(".accyearid").attr("id");
			var locid=  $(this).find(".locname").attr("id");
			
			window.location.href="houseSettings.html?method=AcademicYearHouseSetting&accyear="+accyearid+"&locid="+locid;
		});
	});
	
	
	$("#allstudent tr").click(function(){
		
		var accyearid = $(this).find(".accyearid").attr("id");
		var locid=  $(this).find(".locname").attr("id");
		window.location.href="houseSettings.html?method=AcademicYearHouseSetting&accyear="+accyearid+"&locid="+locid;
		
	});
	pagination(20);
});


function houseSettings(pointer,pointer1){
	
	
	$.ajax({
			
			type : "POST",
			url : "houseSettings.html?method=gethouseSettings",
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
				 if(result.AccYearList.length){
				for(var i=0;i<result.AccYearList.length;i++){
					
					$("#markstable #allstudent").append(
							"<tr>"+
							"<td>"+result.AccYearList[i].sno1+"</td>"+
							"<td class='accyearid' id='"+result.AccYearList[i].accyearid+"'>"+result.AccYearList[i].accyear+"</td>"+
							"<td  class='locname' id='"+result.AccYearList[i].locationid+"'>"+result.AccYearList[i].locname+"</td>"
							+"<td><span class="+result.AccYearList[i].status+">"+result.AccYearList[i].status+"</span></td>"
							+"</tr>"
					);
					
				}
				 }
				 else{
				     $("#markstable #allstudent").append("<tr><td colspan='8'>NO Records Found</td></tr>");
				 }
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.AccYearList.length);
	
			}
		});
}