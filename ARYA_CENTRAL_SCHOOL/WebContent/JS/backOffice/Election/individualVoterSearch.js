$(document).ready(function(){
	getAccyearbyGroup();//get academic year from group setting
	getVoterSearchList();
	

	
	$("#academicYear").change(function(){ // get election title  by academic year
		$.ajax({
			type: 'POST',
			url : "ElectionMenu.html?method=getElectionTitleByAccYear",
			data :{
				"accyear" : $("#academicYear").val()
				},
		
			success :function(data){
			var result = $.parseJSON(data);
		
			$('#electionTitle').empty();
			$('#electionTitle').append('<option value="all">----Select----</option>');
			for( var j=0;j<result.title.length; j++) {
				$('#electionTitle').append('<option value="'+result.title[j].electionTitleId+ '">'
						+result.title[j].electionTitle+ '</option>'
				);
			}
			getVoterSearchList();
			}
		});
	});//end

	$("#electionTitle").change(function(){ //get className title by accyear election title
		
		$.ajax({
			type: 'POST',
			url : "ElectionMenu.html?method=getClassByAccyearTitle",
			data :{
				"electionTitle" : $("#electionTitle").val(),
				"accyear"	: $("#academicYear").val(),
				},
		
			success :function(data){
			var result = $.parseJSON(data);
		
			$('#className').empty();
			$('#className').append('<option value="all">----Select----</option>');
			for( var j=0;j<result.className.length; j++) {
				$('#className').append('<option value="'+result.className[j].classId+ '">'
						+result.className[j].className+ '</option>'
				);
			}
			getVoterSearchList();
			}
		});
		});//end

$("#className").change(function(){ //get section  by accyear and  election title
		
		$.ajax({
			type: 'POST',
			url : "ElectionMenu.html?method=getSectionByAccyearTitle",
			data :{
				"electionTitle" : $("#electionTitle").val(),
				"accyear"	: $("#academicYear").val(),
				},
		
			success :function(data){
			var result = $.parseJSON(data);
		
			$('#sectionName').empty();
			$('#sectionName').append('<option value="all">----Select----</option>');
			for( var j=0;j<result.sectionName.length; j++) {
				$('#sectionName').append('<option value="'+result.sectionName[j].sectionId+ '">'
						+result.sectionName[j].sectionName+ '</option>'
				);
			}
			getVoterSearchList();
			}
		});
		});//end

	
	
	
/*
$("#allstudent tbody tr").click(function(){
			
			window.location.href="adminMenu.html?method=voterDetailsView";
		});*/
});
	

function getAccyearbyGroup(){
	$.ajax({
	type : 'POST',
	url : "ElectionMenu.html?method=getAccYearByGroup",
	async : false,
	success : function(data) {
	var result = $.parseJSON(data);
	$('#academicYear').empty();
	$('#academicYear').append('<option value="all">----Select----</option>');
	for ( var j = 0; j < result.accyarByGroup.length; j++) {

		$('#academicYear').append('<option value="'+ result.accyarByGroup[j].accid+ '">'
				+ result.accyarByGroup[j].accyear+ '</option>');
		}
	}
});	
}//end

	
function getVoterSearchList(){//adding dynamic check box
		var academicYear = $("#academicYear").val();
		var className = $("#className").val();
		var electionTitle = $("#electionTitle").val();
		var sectionName = $("#sectionName").val();
	
		datalist={
				"academicYear":academicYear,
				"className":className,
				"electionTitle":electionTitle,
				"sectionName":sectionName,
		};
		
	$.ajax({
			type:'POST',
			url :"ElectionMenu.html?method=getVoterSearchList",
			data: datalist,
			async :false,
			success :function(data){
	
				var result = $.parseJSON(data);
				$("#allstudent tbody").empty();
				if(result.DataList.length>0){
				for(var i=0; i<result.DataList.length; i++){
					$("#allstudent tbody").append("<tr class='"+result.DataList[i].admissionNo+" "+result.DataList[i].studentId+" "+result.DataList[i].accyear+"'>" +

							"<td>"+result.DataList[i].sno+"</td>" +
							"<td>"+result.DataList[i].admissionNo+"</td>" +
							"<td>"+result.DataList[i].studentName+"</td>" +
							"<td>"+result.DataList[i].rollNumber+"</td>" +
							"<td>"+result.DataList[i].className+"</td>" +
							"<td>"+result.DataList[i].sectionName+"</td>" +
							"<td><span class="+result.DataList[i].status+">"+result.DataList[i].status+"</span></td>" +
						"</tr>");
					}
			rowClickable();
				}
				else{
					$("#allstudent tbody").append("<tr><td colspan='7'>No Record Found</td></tr>");
					}
		
		}
	});	
	}//end	

	
function rowClickable(){
$("#allstudent tbody tr").click(function(){
			
			var admissionNo=$(this).attr("class").split(" ")[0];
			var studentId =$(this).attr("class").split(" ")[1];
			///var accyear =$(this).attr("class").split(" ")[2];
			window.location.href="ElectionMenu.html?method=voterDetailsView&admissionNo="+admissionNo+"&studentId="+studentId;
		});

}

	
