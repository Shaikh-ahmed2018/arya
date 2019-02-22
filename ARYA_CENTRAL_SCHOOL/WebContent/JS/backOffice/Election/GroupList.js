
$(document).ready(function(){

		getAccyear();
		$("#academicYear").val($("#hacademicyaer").val());
		getGroupList($("#hacademicyaer").val());
	
	$("#academicYear").change(function(){
		getGroupList($(this).val());
		
	});
	$("#addnew").click(function(){
		var accyear = $("#academicYear").val();
		var accyearname  =$("#academicYear :selected").text();
		window.location.href="ElectionMenu.html?method=addNewGroup&accyear="+accyear+"&name="+accyearname;
	});
	
});	
function getAccyear(count){

	$.ajax({
		type : 'POST',
		url : "ElectionMenu.html?method=getAccYear",
		async : false,
		success : function(data) {
			
		var result = $.parseJSON(data);
	
		$('#academicYear').empty();
		$('#academicYear').append('<option value>-------SELECT---------</option>');
		for ( var j = 0; j < result.accyar.length; j++) {

			$('#academicYear').append('<option value="'+ result.accyar[j].accyearId+ '">'
					+ result.accyar[j].accyearname+ '</option>');

		}
		
		}
	});	
}

function rowClickable(){
	$("#allstudent tbody tr").click(function(){
		var accyear = $(this).attr("class").split(" ")[0];
		
		var groupid = $(this).attr("class").split(" ")[1];
		window.location.href="ElectionMenu.html?method=UpdateGroupDetails&accyear="+accyear+"&groupid="+groupid;
	});
}
function getGroupList(accyear){
	$.ajax({
		type:'POST',
		url:'adminMenu.html?method=GroupListbyJS',
		data :{"accyear" : accyear},
		async: false,
		success: function(response){
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			
			if(result.DataList.length>0){
			for(var i=0; i<result.DataList.length; i++){
				$("#allstudent tbody").append("<tr class='"+result.DataList[i].accid+" "+result.DataList[i].groupid+"'>" +
						"<td>"+result.DataList[i].sno+"</td>" +
						"<td>"+result.DataList[i].accyear+"</td>" +
						"<td>"+result.DataList[i].groupName+"</td>" +
						"</tr>");
			}
			}
			else{
				$("#allstudent tbody").append("<tr>" +
						"<td colspan='3'>No Records Founds</td>" +
						"</tr>");
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.DataList.length);
			rowClickable();
			pagination(100);
		}
	});
}

