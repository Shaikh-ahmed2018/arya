
$(document).ready(function() {


	$("#locationname").change(function(){
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var specialization=$("#specialization").val();
		

		if(locationid==""){
			locationid="all";
		}
		if(classname==""){
			classname="all";
		}
		if(specialization==""){
			specialization="all";
		}
		getClassName($("#locationname").val());
		getSubject(locationid,classname,specialization);
	});
	$("#classname").change(function(){
		getClassSpecilization($(this).val(),$("#locationname").val());
			//checkSubjectduplication();
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var specialization=$("#specialization").val();
		

		if(locationid==""){
			locationid="all";
		}
		if(classname==""){
			classname="all";
		}
		if(specialization==""){
			specialization="all";
		}
		getSubject(locationid,classname,specialization);
		
	});	
	
	$("#specialization").change(function(){

		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var specialization=$("#specialization").val();
		

		if(locationid==""){
			locationid="all";
		}
		if(classname==""){
			classname="all";
		}
		if(specialization==""){
			specialization="all";
		}
		getSubject(locationid,classname,specialization);
		
	});	

	$(".subject").click(function(){
		$("#subjectOne").slideToggle();
	});

	$("#selectall").change(function(){

		$(".select").prop('checked', $(this).prop("checked"));
	});

	$("#editsubject").click(function(){
		var cnt = 0;

		$('.select:checked').map(function() {
			getData = $(this).attr("id").split(",")[0];
			cnt++;
		});

		if (cnt == 0 || cnt > 1) {

			$(".errormessagediv").show();
			$(".validateTips").text("Select any one Subject");

			return false;
		} 
		else
		{

			var subId = getData;

			window.location.href = "subject.html?method=getSubjectDetails&subjectcode="+subId;
			var result = $.parseJSON(response);
		}

	});

	$('#search').click(function() {
		var searchname = $("#searchname").val().trim();
		window.location.href = "adminMenu.html?method=subjectdetails&searchname="+searchname+"&school="+$('#school').val().trim();
	});
	$("#searchname").keypress(function(e){
		if(e.keyCode==13){
			var searchname = $("#searchname").val().trim();
			window.location.href = "adminMenu.html?method=subjectdetails&searchname="+searchname+"&school="+$('#school').val().trim();
		}
	});

	$("#delete").click(function() {

		var count=0;
		subjectlist=[];
		locationList=[];
		$(".select:checked").each(function(){

			var list=$(this).attr("id").split(",")[0];
			var location=$(this).attr("id").split(",")[1];
			subjectlist.push(list);
			locationList.push(location);
			count++;

		});

		if(subjectlist.length==0)

		{
			$('.errormessagediv').show();
			$('.validateTips').text("Select any Subject to delete");
		}

		else{
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are you sure to delete?</p>");
		}

	});
	$("#dialog").dialog({		

		autoOpen: false,
		modal: true,
		title:'Subject Details',
		buttons : {
			"Yes" : function() {

				$.ajax({

					type : "POST",

					url :"subject.html?method=DeleteSubject",

					data :{"subjectlist" :subjectlist.toString(),
						"locationList":locationList.toString(),
					},

					success : function(data)
					{
						var result = $.parseJSON(data);
						if(result.status == true){
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
							$(".validateTips").text("Delete Unmapped Subject Details Progressing...");
							setTimeout(function(){
								window.location.href="adminMenu.html?method=subjectdetails";

							},2000);
						}
					}
				});
				$(this).dialog("close");

			},
			"No" : function() {
				$(this).dialog("close");
			}
		}
	});

	$('#excelDownload').click(function() {
		window.location.href = 'subject.html?method=downloadsubjectDetailsXLS';
	});
	$("#pdfDownload").click(function(){
		window.location.href = "subject.html?method=downloadsubjectDetailsPDF";
	});

	function removeMessage() {
		$(".successmessagediv").hide();
		$(".successmessagediv").hide();
	}
});


function getClassName(val) {
	
	
	
	$.ajax({
	url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
	data:{"locationId":val},
	async:false,

	success : function(data) {

		

		var result = $.parseJSON(data);
		$(classname).html("");
		
	    $(classname).append('<option value="">' + "---------Select---------" + '</option>');

		for (var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

			$(classname).append(
					'<option value="' + result.classDetailsIDandClassDetailsNameList[j].secDetailsId + '">'
							+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName + '</option>');
		}
	
	}
});}

function getClassSpecilization(classId,location){
	var data = {
			"classId" : classId,
			"locationId":location,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specialization').empty();
			$('#specialization').append('<option value="-">'+ "General" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#specialization').append(
						'<option value="'
						+ result.jsonResponse[j].spec_Id
						+ '">'
						+ result.jsonResponse[j].spec_Name
						+ '</option>');
			}


		}
	});
}
	
function getSubject(locationid,classname,specialization){

	var datalist = {
			
			"location" :locationid,
			"classname" :classname,
			"specialization":specialization,
			
		}; 
		$.ajax({
			type : 'POST',
			url : "adminMenu.html?method=SubjectListforListOnchangeMethod",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
					for(var i=0;i<result.list.length;i++){
					//	alert(result.list.length);

					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' name='select' class='select' id='"+result.list[i].subjectid+","+result.list[i].locationId+"' /></td>"
							+"<td> "+result.list[i].locationName+" </td>"
							+"<td> "+result.list[i].classname+"</td>"
							+"<td> "+result.list[i].specializationName+" </td>"
							+"<td> "+result.list[i].subjectname+" </td>"
							+"<td> "+result.list[i].totalMarks+" </td>"
							+"<td> "+result.list[i].passMarks+" </td>"
							+"<td> "+result.list[i].description+" </td>"
							+"<td> "+result.list[i].path+" </td>"
						+"</tr>");
					}	
			}
					else{
						$("#allstudent tbody").append("<tr>" +"<td colspan='9'>No Records Founds</td>" +"</tr>");
					}
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
			}
		});

	}
