$(document).ready(function() {

	$("#selectall").change(function() {

		$(".select").prop('checked', $(this).prop("checked"));

	});
	$(".select").change(function(){
        if($(".select").length==$(".select:checked").length){
	         $("#selectall").prop("checked",true);
         }
       else{
	           $("#selectall").prop("checked",false);
           }
});
	
	$("#locationname").change(function(){
		$("#selectall").prop("checked",false);
		$("#specialization").val("all");
		
		if($("#locationname").val() =="all" || $("#locationname").val()==""){
			
			$("#classname").html("");
			$("#classname").append("<option value='all'>ALL</option>");
			getStudentListforPrint();
		}
		else
			{

		
		getClassName($(this).val());
		getStudentListforPrint();
			}
		
	});
	$("#classname").change(function(){
		getClassSpecilization($(this).val(),$("#locationname").val());
			//checkSubjectduplication();
		$("#selectall").prop("checked",false);
		

		getStudentListforPrint();
		
	});	
	
	$("#specialization").change(function(){

		$("#selectall").prop("checked",false);

		getStudentListforPrint();
		
	});	

	
	
	
	$("#editlab").click(function(){
		
		var cnt = 0;

		$('.select:checked').map(function() {
			getData = $(this).attr("id").split(",")[0];
			cnt++;
		});

		if (cnt == 0 || cnt > 1) {

			$(".errormessagediv").show();
			$(".validateTips").text("Select any one record");

			return false;
		} 
		else
		{

			var labId = getData;

			window.location.href = "subject.html?method=getLabDetails&labcode="+labId;
			var result = $.parseJSON(response);
			
		}

	});
	
	$("#delete").click(function() {
	    
		var count=0;
		lablist=[];
		locationList=[];
		$(".select:checked").each(function(){

			var list=$(this).attr("id").split(",")[0];
			var location=$(this).attr("id").split(",")[1];
			lablist.push(list);
			locationList.push(location);
			count++;

		});

		if(lablist.length==0)

		{
			$('.errormessagediv').show();
			$('.validateTips').text("Select any record to delete");
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
		title:'Laboratory Details',
		buttons : {
			"Yes" : function() {

				$.ajax({

					type : "POST",

					url :"subject.html?method=DeleteLab",

					data :{"lablist" :lablist.toString(),
						"locationList":locationList.toString(),
					},

					success : function(data)
					{
						var result = $.parseJSON(data);
						if(result.status == true){
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
							$(".validateTips").text("Delete Unmapped Lab Details Progressing...");
							setTimeout(function(){
								window.location.href="adminMenu.html?method=laboratory";

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
		window.location.href = 'subject.html?method=downloadLabDetailsXLS';
	});
	$("#pdfDownload").click(function(){
		window.location.href = "subject.html?method=downloadLabDetailsPDF";
	});



});

function getClassSpecilization(classId,location){
	var data = {
			"classId" : classId,
			"locationId":location,
	};
	$.ajax({
		type : 'POST',
		url : "adminMenu.html?method=getSpecialization",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specialization').empty();
			$('#specialization').append('<option value="all">'+ "ALL" + '</option>');

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

function getSubject(classId,location){
	var data = {
			"classId" : classId,
			"locationId":location,
	};
	$.ajax({
		type : 'POST',
		url : "adminMenu.html?method=getSubjectByClass",
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


function getClassName(val) {
	
	$.ajax({
	url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
	data:{"locationId":val},
	async:false,

	success : function(data) {

		

		var result = $.parseJSON(data);
		$(classname).html("");
		
	    $(classname).append('<option value="all">'+"ALL"+ '</option>');

		for (var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

			$(classname).append(
					'<option value="' + result.classDetailsIDandClassDetailsNameList[j].secDetailsId + '">'
							+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName + '</option>');
		}
	
	}
});}






function getStudentListforPrint(){
	
	var locationid=$("#locationname").val();
	var classname=$("#classname").val();
	var specialization=$("#specialization").val();
	
	var datalist = {
			"location" :locationid,
			"classname" :classname,
			"specialization":specialization,
			
		}; 
		$.ajax({
			type : 'POST',
			url : "adminMenu.html?method=LabListforListOnchangeMethod",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					
					if(result.list.length>0){
					for(var i=0;i<result.list.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' name='select' class='select' id='"+result.list[i].lab_id+","+result.list[i].locationName+"' /></td>"
							+"<td> "+result.list[i].locationName+" </td>"
							+"<td> "+result.list[i].classname+"</td>"
							+"<td> "+result.list[i].specialization+" </td>"
							+"<td> "+result.list[i].subjtname+" </td>"
							+"<td> "+result.list[i].lab_name+" </td>"
							+"<td> "+result.list[i].totalMarks+" </td>"
							+"<td> "+result.list[i].passMarks+" </td>"
							+"<td> "+result.list[i].subjectCode+" </td>"
							+"<td style='width=93px'> "+result.list[i].description+" </td>"
							+"<td><a href='subject.html?method=getLabsyllabusdownload&labid='"+result.list[i].lab_id+"><img id='dwnd1' src='images/download.png'/></a></td>"
							
						+"</tr>");
					
					}	
					$(".select").change(function(){
				        if($(".select").length==$(".select:checked").length){
					         $("#selectall").prop("checked",true);
				         }
				       else{
					           $("#selectall").prop("checked",false);
				           }
				});
			}
					else{
						$("#allstudent tbody").append("<tr>" 
								+"<td colspan='11'>No Records Founds</td>"
								+"</tr>");
					}
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
			}
		});

	}