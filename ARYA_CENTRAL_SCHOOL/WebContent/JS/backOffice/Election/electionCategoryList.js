$(document).ready(function(){
	getAccyearbyGroup();
	$("#academicYear").val($("#hacademicyaer").val());
	
	/*----------------change functions OF groupname based on accyear*/

	$("#groupName").change(function(){
		getElectionList();
	});
	$.ajax({
		type: 'POST',
		url : "ElectionMenu.html?method=getGroupNamebyAcademicYear",
		data :{
			"accyear" : $("#academicYear").val()
			},
	
		success :function(data){
		var result = $.parseJSON(data);
	
		$('#groupName').empty();
		$('#groupName').append('<option value="all">----Select----</option>');
		for( var j=0;j<result.groupName.length; j++) {
			$('#groupName').append('<option value="'+result.groupName[j].groupid+ '">'
					+result.groupName[j].groupName+ '</option>'
			);
		}
		getElectionList();
		}
	});
	
	// get Group name by academic year
	$("#academicYear").change(function(){ 
		$.ajax({
			type: 'POST',
			url : "ElectionMenu.html?method=getGroupNamebyAcademicYear",
			data :{
				"accyear" : $("#academicYear").val()
				},
		
			success :function(data){
			var result = $.parseJSON(data);
		
			$('#groupName').empty();
			$('#groupName').append('<option value="all">----Select----</option>');
			for( var j=0;j<result.groupName.length; j++) {
				//alert(result.groupName.length);
				//alert (result.groupName[j].groupid);
				//alert (result.groupName[j].groupName);
				$('#groupName').append('<option value="'+result.groupName[j].groupid+ '">'
						+result.groupName[j].groupName+ '</option>'
						
				);
			}
			getElectionList();
			}
		});
	});
	

$("#allstudent tbody tr").click(function(){
			
			window.location.href="adminMenu.html?method=electionCategoryAdd";

});
pagination(100);
});

function getAccyearbyGroup(){
	$.ajax({
	type : 'POST',
	url : "ElectionMenu.html?method=getAccYearByGroup",
	async : false,
	success : function(data) {
	var result = $.parseJSON(data);
	$('#academicYear').empty();
	$('#academicYear').append('<option value="">----Select----</option>');
	for ( var j = 0; j < result.accyarByGroup.length; j++) {

		$('#academicYear').append('<option value="'+ result.accyarByGroup[j].accid+ '">'
				+ result.accyarByGroup[j].accyear+ '</option>');
		}
	

	}
});	
}

//Getting the Listing Page Data
function getElectionList(){

	var academicYear=$("#academicYear").val();
	var groupName=$("#groupName").val();
	if(academicYear==""){
		academicYear="all";
	}
	if(groupName==""){
		groupName="all";
	}
	
	var dataList={	"academicYear":academicYear,
					"groupName":groupName,
	};

	$.ajax({
		type :'POST',
		url :"adminMenu.html?method=getElectionCategoryList",
		data : dataList,
		async : false,
	
		success: function(response)
		{
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.DataList.length>0){
			for(var i=0; i<result.DataList.length; i++){
				$("#allstudent tbody").append("<tr class='"+result.DataList[i].accid+" "+result.DataList[i].groupid+" "+result.DataList[i].electionTitleId+"'>" +
						"<td>"+result.DataList[i].sno+"</td>" +
						"<td>"+result.DataList[i].accyear+"</td>" +
						"<td>"+result.DataList[i].groupName+"</td>" +
						"<td>"+result.DataList[i].electionTitle+"</td>" +
						
						"<td><span class='"+result.DataList[i].status+"'>"+result.DataList[i].status+"</span></td>" +
						"</tr>");
				}
			
			rowClickable();
				}
			else{
				$("#allstudent tbody").append("<tr><td colspan='5'>No Record Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.DataList.length);
			pagination(100);
			
	}
});
}

function rowClickable(){
	$("#allstudent tbody tr").click(function(){
		
		var accyear=$(this).attr("class").split(" ")[0];
		var group =$(this).attr("class").split(" ")[1];
		var titleID =$(this).attr("class").split(" ")[2];
		
	window.location.href="ElectionMenu.html?method=electionCategoryAdd&accyear="+accyear+"&group="+group+"&titleID="+titleID;
	});
}
