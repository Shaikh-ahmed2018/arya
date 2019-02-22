$(document).ready(function(){
	
	getAccyearbyGroup();
	$("#academicYear").val($("#hacademicyaer").val());
	getElectionList();
	
	/*----------------change functions OF groupname based on accyear*/

	$("#groupName").change(function(){
		getElectionList();
	});
	
	//above method is to load data accarding to default academic year
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
	//--------------end here
	
	//Get group name  based on academic year
	$("#academicYear").change(function(){ // get Group name by academic year
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
	});
	
	rowClickable();
	
	 pageurl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(pageurl=="adminMenu.html?method=electionListForVoting"){
		$(".navbar-right").remove();
		$("#pageHeading").text("Election List");
	}
	
		
	
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
		url :"adminMenu.html?method=getElectionList",
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
						"</tr>");
				}
			
			rowClickable();
				}
			else{
				$("#allstudent tbody").append("<tr><td colspan='4'>No Record Found</td></tr>");
				}
			
	}
});
}

function rowClickable(){
	$("#allstudent tbody tr").click(function(){
		
		var accyear=$(this).attr("class").split(" ")[0];
		var group =$(this).attr("class").split(" ")[1];
		var titleID =$(this).attr("class").split(" ")[2];
		if(pageurl=="adminMenu.html?method=electionListForVoting"){
			window.location.href="adminMenu.html?method=voterMachineStart&accyear="+accyear+"&group="+group+"&titleID="+titleID;
		}
		else{
	window.location.href="ElectionMenu.html?method=ElectionUpdate&accyear="+accyear+"&group="+group+"&titleID="+titleID;
		}
		});
}


