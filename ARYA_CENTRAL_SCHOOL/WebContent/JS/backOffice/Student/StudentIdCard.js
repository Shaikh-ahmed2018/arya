$(document).ready(function(){
	getIdCardStreamList();
$("#locationname").change(function(){
	getStream($(this));
	getSearchIdCardStreamList();
});
$("#streamId").change(function(){
	getSearchIdCardStreamList();
});
$("#accyear").change(function(){
	getSearchIdCardStreamList();
});

});
function getStream(pointer){
	$.ajax({
		url : "classPath.html?method=getStreamDetailAction",
		async : false,
		data:{'school':pointer.val().trim()},
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#streamId').empty();
			$('#streamId').append('<option value="">-------Select-------</option>');
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#streamId').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');

			}

		}
	});
}
function getIdCardStreamList(){
	var dataList={"academicYear":$("#globalAcademicYear").val(),
					"locationId":$("#school").val(),
					"streamId":"all",
	};
	$.ajax({
		url : "adminMenu.html?method=NewstudentIdCardDesignList",
		data:dataList,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#allstudent tbody').empty();
			
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#allstudent tbody').append("<tr class='"+ result.streamList[j].academicYearCode+" "+ result.streamList[j].locationId+" "+ result.streamList[j].streamId+"'>" +
						"<td>"+ result.streamList[j].sno+ "</td>" +
						"<td>"+ result.streamList[j].academicYear+ "</td>" +
						"<td>"+ result.streamList[j].locationName+ "</td>" +
						"<td>"+ result.streamList[j].streamName+ "</td>" +
						"</tr>" +
						"");

			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.streamList.length);
			rowClickable();
		}
	});
	
}
function getSearchIdCardStreamList(){
	
	var academicYear=$("#accyear").val();
	if($("#accyear").val()==""){
		academicYear="all";
	}
	var locationId=$("#locationname").val();
	if($("#locationname").val()==""){
		locationId="all";
	}
	var streamId=$("#streamId").val();
	if($("#streamId").val()==""){
		streamId="all";
	}
	
	
	var dataList={"academicYear":academicYear,
					"locationId":locationId,
					"streamId":streamId,
					
	};
	$.ajax({
		url : "adminMenu.html?method=NewstudentIdCardDesignList",
		data:dataList,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#allstudent tbody').empty();
			
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#allstudent tbody').append("<tr class='"+ result.streamList[j].academicYearCode+" "+ result.streamList[j].locationId+" "+ result.streamList[j].streamId+"'>" +
						"<td>"+ result.streamList[j].sno+ "</td>" +
						"<td>"+ result.streamList[j].academicYear+ "</td>" +
						"<td>"+ result.streamList[j].locationName+ "</td>" +
						"<td>"+ result.streamList[j].streamName+ "</td>" +
						"</tr>" +
						"");

			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.streamList.length);
			rowClickable();
		}
	});
	
}
function rowClickable(){
	$("#allstudent tbody tr").click(function(){
		
		window.location.href="adminMenu.html?method=NewstudentIdCardPrint&accyear="+$(this).attr("class").split(" ")[0]+"&schoolId="+$(this).attr("class").split(" ")[1]+"&streamId="+$(this).attr("class").split(" ")[2];

	});
}