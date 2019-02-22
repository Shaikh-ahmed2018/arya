$(document).ready(function(){
	

getAccyearbyGroup();
	$("#academicYear").val($("#hacademicyaer").val());
	getGroupName($("#hacademicyaer").val());
	getElectionListForReport();

	/*----------------change functions OF groupname based on accyear*/
	$("#electionGroup").change(function(){
		getElectionList();
		getElectionListForReport();
	});

	//--------------end here
	
	//Get group name  based on academic year
	$("#academicYear").change(function(){ // get Group name by academic year
		getGroupName($(this).val());
		getElectionListForReport();
	});
	$("#electionTitle").change(function(){
		getElectionListForReport();
	});
	rowClickable();	
});
	
function getGroupName(accyear){
	
	$.ajax({
		type: 'POST',
		url : "ElectionMenu.html?method=getGroupNamebyAcademicYear",
		data :{
			"accyear" : accyear,
			},
	
		success :function(data){
		var result = $.parseJSON(data);
	
		$('#electionGroup').empty();
		$('#electionGroup').append('<option value="all">----Select----</option>');
		for( var j=0;j<result.groupName.length; j++) {
			$('#electionGroup').append('<option value="'+result.groupName[j].groupid+ '">'
					+result.groupName[j].groupName+ '</option>'
			);
		}
		
		}
	});
	
}
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
	var groupName=$("#electionGroup").val();
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
		url :"adminMenu.html?method=getElectionList",
		data : dataList,
		async : false,
	
		success: function(response)
		{
			var result = $.parseJSON(response);
			$("#electionTitle").empty();
			$('#electionTitle').append('<option value="">----Select----</option>');
			for(var i=0; i<result.DataList.length; i++){
				$('#electionTitle').append('<option value="'+ result.DataList[i].electionTitleId+ '">'
						+ result.DataList[i].electionTitle+ '</option>');
				
				}
			
			
			
			
	}
});
}
function getElectionListForReport(){

	var academicYear=$("#academicYear").val();
	var groupName=$("#electionGroup").val();
	var electionId=$("#electionTitle").val();
	if(academicYear==""){
		academicYear="all";
	}
	if(groupName==""){
		groupName="all";
	}
	if(electionId==""){
		electionId="all";
	}
	
	var dataList={	"academicYear":academicYear,
					"groupName":groupName,
					"electionId":electionId,
	};

	$.ajax({
		type :'POST',
		url :"ElectionMenu.html?method=getElectionList",
		data : dataList,
		async : false,
	
		success: function(response)
		{
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.DataList.length>0){
			for(var i=0; i<result.DataList.length; i++){
				$("#allstudent tbody").append("<tr class='"+result.DataList[i].accid+" "+result.DataList[i].groupid+" "+result.DataList[i].electionTitleId+" "+result.DataList[i].status+"'>" +
						"<td>"+result.DataList[i].sno+"</td>" +
						"<td>"+result.DataList[i].accyear+"</td>" +
						"<td>"+result.DataList[i].groupName+"</td>" +
						"<td>"+result.DataList[i].electionTitle+"</td>" +
						"<td>"+result.DataList[i].status+"</td>" +
						"</tr>");
				}
			
			rowClickable();
				}
			else{
				$("#allstudent tbody").append("<tr><td colspan='5'>No Record Found</td></tr>");
				}
			
	}
});
}

function rowClickable(){
	$("#allstudent tbody tr").click(function(){
		
		var accyear=$(this).attr("class").split(" ")[0];
		var group =$(this).attr("class").split(" ")[1];
		var titleID =$(this).attr("class").split(" ")[2];
		
			window.location.href="adminMenu.html?method=winnerList&accyear="+accyear+"&group="+group+"&titleID="+titleID;
		
		
		});
}
