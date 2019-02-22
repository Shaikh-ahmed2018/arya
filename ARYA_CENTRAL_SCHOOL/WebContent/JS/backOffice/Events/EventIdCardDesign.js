$(document).ready(function(){
	
	getSearchIdCardStreamList("all","all","all");
	
	
$("#eventList").change(function(){
	var eid = $("#eventList").val().trim();
	var cid = $("#eventcategory").val().trim();
	var pid = $("#eventprogramname").val().trim();
	
	if($("#eventList").click){
		$("#eventcategory").val("");
		$("#eventprogramname").val("");
	}
	
	getEventCategory(eid);
	getSearchIdCardStreamList(eid,cid,pid);
});

$("#eventcategory").change(function(){
	var eid = $("#eventList").val().trim();
	var cid = $("#eventcategory").val().trim();
	var pid = $("#eventprogramname").val().trim();
	
	if($("#eventcategory").click){
		$("#eventprogramname").val("");
	}
	
	getProgramName(cid);
	getSearchIdCardStreamList(eid,cid,pid);
});


$("#eventprogramname").change(function(){
	var eid = $("#eventList").val().trim();
	var cid = $("#eventcategory").val().trim();
	var pid = $("#eventprogramname").val().trim();
	
	
	if($("#eventList").val() == ""){
		$("#eventcategory").val("");
		$("#eventprogramname").val("");
	}
	
	
	getSearchIdCardStreamList(eid,cid,pid);
});

});

function getEventCategory(eid){
	
	
	$.ajax({
		type:'POST',
		url : "EventsMenu.html?method=getEventCategory",
		data:{'eid':eid},
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#eventcategory').empty();
			$('#eventcategory').append('<option value="">-------Select-------</option>');
			for ( var j = 0; j < result.eventCategory.length; j++) {

				$('#eventcategory').append('<option value="'+ result.eventCategory[j].categoryId+ '">'
						+ result.eventCategory[j].categoryName+ '</option>');

			}

		}
	});
}

function getProgramName(cid){
	
	
	$.ajax({
		type:'POST',
		url : "EventsMenu.html?method=getProgramName",
		data:{'cid':cid},
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#eventprogramname').empty();
			$('#eventprogramname').append('<option value="">-------Select-------</option>');
			for ( var j = 0; j < result.eventProgramName.length; j++) {

				$('#eventprogramname').append('<option value="'+ result.eventProgramName[j].programmeId+ '">'
						+ result.eventProgramName[j].programmeName+ '</option>');

			}

		}
	});
}

function getSearchIdCardStreamList(eventList,eventcategory,eventprogramname){
	
	if($("#eventList").val()==""){
		eventList="all";
	}
	
	if($("#eventcategory").val()==""){
		eventcategory="all";
	}
	
	if($("#eventprogramname").val()==""){
		eventprogramname="all";
	}

	
	var dataList={"eventList":eventList,
					"eventcategory":eventcategory,
					"eventprogramname":eventprogramname,
					
	};
	$.ajax({
		type : 'POST',
		url : "EventsMenu.html?method=getEventIdList",
		data:dataList,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#allstudent tbody').empty();
			
			if(result.streamList.length>0){
			for ( var j = 0; j < result.streamList.length; j++) {
				k=j;
				k++;
				$('#allstudent tbody').append("<tr class='"+ result.streamList[j].eventId+"_PARTICIPANT_"+result.streamList[j].eventName+"'>" +
						"<td>"+ result.streamList[j].sno+ "</td>" +
						/*"<td>"+ result.streamList[j].sno+ "</td>" +*/
						"<td>"+ result.streamList[j].eventName+ "</td>" +
						"<td>PARTICIPANT</td>" +
					
						"</tr>" +
						"");

				}
			
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#allstudent tbody').append("<tr class='"+ result.streamList[j].eventId+"_VOLANTEER_"+result.streamList[j].eventName+"'>" +
						"<td>"+(k+1)+ "</td>" +
						/*"<td>"+ result.streamList[j].sno+ "</td>" +*/
						"<td>"+ result.streamList[j].eventName+ "</td>" +
						"<td>Volenteer</td>" +
						
						"</tr>" +
						"");

				}
			
			rowClickable();
			}else{
				$("#allstudent tbody").append("<tr>"+"<td colspan='3'>No Records Founds</td>" +"</tr>");
			}
				
				
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.streamList.length);
			
		}
	});
	
}

function rowClickable(){
	$("#allstudent tbody tr").click(function(){
		var eventList=$(this).attr("class").split("_")[0];
		var eventcategory =$(this).attr("class").split("_")[1];
		var evntname =$(this).attr("class").split("_")[2];
	
		
		window.location.href = "EventsMenu.html?method=newEventIdCardPrintTemplate&eventList="+eventList+"&eventcategory="+eventcategory+"&evntname="+evntname;
	});
}

