function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document).ready(function() {
	$("#selectall").change(function(){
		$(".studentid").prop("checked",$(this).prop("checked"));
	});
	
	getSpecializationDetails("all","all","all");
	$("#locationname").change(function(){
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();

		if(locationid==""){
			locationid="all";
		}
		if(classname==""){
			classname="all";
		}
		if(streamId==""){
			streamId="all";
		}
		getStream($(this));
		getSpecializationDetails(locationid,classname,streamId);
	});
	$("#streamId").change(function(){
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();

		if(locationid==""){
			locationid="all";
		}
		if(classname==""){
			classname="all";
		}
		if(streamId==""){
			streamId="all";
		}

		getClassList();
		getSpecializationDetails(locationid,classname,streamId);
	});
	$("#classname").change(function(){
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();

		if(locationid==""){
			locationid="all";
		}
		if(classname==""){
			classname="all";
		}
		if(streamId==""){
			streamId="all";
		}

		getSpecializationDetails(locationid,classname,streamId);
	});

	
	
	var schoolName=$("#school").val().trim();
	
	$("#searchname").keypress(function(e){
		var searchText = $("#searchname").val();
		if(e.keyCode == 13){
			window.location.href ="adminMenu.html?method=SpecializationList&searchText="
				+ searchText+"&SchoolName="+$("#school").val().trim();
		}
	});
	
	$("#search").click(function(){
		var searchText = $("#searchname").val();
		window.location.href ="adminMenu.html?method=SpecializationList&searchText="
			+ searchText+"&SchoolName="+$("#school").val().trim();
	});

	$(".specializations").click(function(){
		$("#specializationOne").slideToggle();
	});

	$("#selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});

	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);


	$("#editspec").click(function(){

		$(".successmessagediv").hide();
		var cnt = 0;
		$('input[type="checkbox"]:checked').map(function() {
			getData = $(this).val();
			cnt++;
		});

		if (cnt == 0 || cnt > 1) {

			$(".errormessagediv").show();
			$(".validateTips").text("Select any one Specialization");
			return false;
		} 
		else
		{
			var specId = getData;
			window.location.href = "specialization.html?method=editSpecialization&specId="+ specId;
		}

	});

	$("#delete").click(function(){
		specidIdlist=[];
		locationList=[];
		var count =0;
		$(".select:checked").each(function(){

			var list=$(this).val();
			specidIdlist.push(list);
			count++;

		});		
		if(count==0){
			$('.errormessagediv').show();
			$('.validateTips').text("Select Specialization to Delete");
			$('.errormessagediv').delay(3000).slideUp();
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
		title:'Specialization Details',
		buttons : {

			"Yes" : function() {

				$
				.ajax({
					type : 'POST',
					url : "specialization.html?method=deleteSpec",
					data : {"specid":specidIdlist,
						
					},
					success : function(
							response) {
						var result = $
						.parseJSON(response);


						$('.errormessagediv').hide();

						if(result.status=="Specialization Deleted SuccessFully"){

							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-320px;");
							$(".validateTips").text("Delete Unmapped Specialization Progressing...");
						}

						else if(result.status=="Specialization not Deleted SuccessFully"){
							$('.successmessagediv').show();
							$('.validateTips').text(" Selected Specialization is Not Deleted");
						}

						else if(result.status=="Specialization Already Mapped"){
							$('.successmessagediv').show();
							$('.validateTips').text("Selected Specialization is Mapped Cannot Delete");
						}


						setTimeout(function(){

							window.location.href="adminMenu.html?method=SpecializationList";

						},2000);

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
		var searchTerm = $("#searchexamid").val().trim();
		window.location.href = 'streamDetails.html?method=downloadStreamDetailsXLS&searchTerm='+ searchTerm;

	});

	$("#pdfDownload").click(function(){
		var searchTerm = $("#searchexamid").val().trim();
		window.location.href = 'streamDetails.html?method=downloadStreamDetailsPDF&searchTerm='+ searchTerm;

	});

});	



function getSpecializationDetails(locationid,classname,streamId){

	var datalist = {
			
			"location" :locationid,
			"classId" :classname,
			"streamId":streamId,
			
		}; 
		$.ajax({
			type : 'POST',
			url : "adminMenu.html?method=SpecializationListforListOnchangeMethod",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
					for(var i=0;i<result.list.length;i++){
					

					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' value='"+result.list[i].spec_Id+"' /></td>"
							+"<td> "+result.list[i].locationName+" </td>"
							+"<td> "+result.list[i].stream_Name+"</td>"
							+"<td> "+result.list[i].class_Name+" </td>"
							+"<td> "+result.list[i].spec_Name+" </td>"
						
							+"</tr>");
					}
					}
					else{
						$("#allstudent tbody").append("<tr>" +"<td colspan='5'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. Of Records "+result.list.length);
					pagination(100);
					$(".studentid").change(function(){
						if($(".studentid:checked").length==$(".studentid").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
					});
			}
		});

	}

function getClassList(){
	var streamId=$("#streamId").val();
	datalist={
			"streamId" : streamId
	},

	$.ajax({

		type : 'POST',
		url : "reportaction.html?method=getClassesByStream",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classname').html("");

			$('#classname').append('<option value="All">' + "----------Select----------"	+ '</option>');

			for ( var j = 0; j < result.ClassesList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassesList[j].classId + '">'

						+ result.ClassesList[j].classname

						+ '</option>');

			}

		}


	});

}

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
