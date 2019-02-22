function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv1").hide();

}


$(document).ready(function() {

	$("#locationname").change(function(){
	
		var locationid=$("#locationname").val();
		var streamId=$("#streamId").val();

		if(locationid==""){
			locationid="all";
		}
	
		if(streamId==""){
			streamId="all";
		}
		getStream($(this));
		getStudentListforPrint(locationid,streamId);
	});
	$("#streamId").change(function(){
		var locationid=$("#locationname").val();
		var streamId=$("#streamId").val();

		if(locationid==""){
			locationid="all";
		}
	
		if(streamId==""){
			streamId="all";
		}
		getStudentListforPrint(locationid,streamId);
		
	});
	/*$("#streamId").change(function(){
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();

		if(locationid==""){
			locationid="all";
		}
	
		if(streamId==""){
			streamId="all";
		}

		//getClassList();
		getStudentListforPrint(locationid,streamId);
	//});
	});*/
	
	
	
	
	
		$(".class").click(function(){
				$("#classOne").slideToggle();
			});


			$("#selectall").change(function(){

				$(".select").prop('checked', $(this).prop("checked"));
			});

			setTimeout("removeMessage()", 3000);
			setInterval(function() {
				$(".errormessagediv").hide();
			}, 3000);	


			$('#editClass')
			.click(
					function() {

						var count = 0;
						var class_id=null;
						var locationId=null;
						$('input[type="checkbox"]:checked').map(
										function() {

											class_id = $(this).attr("id").split(",")[0];
											locationId=$(this).attr("id").split(",")[1];
											

											count++;
										});
						if (count == 0 || count > 1) {
							$(".errormessagediv").show();
							$(".validateTips").text(
							"Select any one Class");
							return false;

						} else {

							window.location.href = "classPath.html?method=editClass&classCode="+class_id;
						}
					});

			$('#classDelete')
			.click(
					function() {

						var count =0;
						classIdlist=[];
						locationList=[];
						$(".select:checked").each(function(){

							var list=$(this).attr("id").split(",")[0];
							var locationId=$(this).attr("id").split(",")[1];
							classIdlist.push(list);
							locationList.push(locationId);
							count ++;

						});	
						if (count == 0 ) {
							$(".errormessagediv").show();
							$(".validateTips").text(
							"Select Class to Delete");
							return false;

						} 
						else {

							$("#dialog").dialog("open");
							$("#dialog").empty();
							$("#dialog").append("<p>Are you sure to delete?</p>")
						}
					});	
			$("#dialog").dialog({


				autoOpen: false,
				modal: true,
				title : "Class Details",
				buttons : {

					"Yes" : function() {


						var datalist = {
								"classCode" : classIdlist,
								"locationCode":locationList,

						};

						$.ajax({type : 'POST',
							url : "classPath.html?method=deleteClass",
							data : datalist,
							success : function(
									response) {
								var result = $.parseJSON(response);

								if (result.status == true) {
									var msg = "Delete Unmapped Class Details Progressing...";


									$(".successmessagediv").show();
									$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");

									$(".validateTips").text(msg);

									setTimeout(function(){

										location.reload();

									},3000);

								} else if (result.status == false){
									var msg = "Selected Class is Mapped Cannot Delete";


									$(".errormessagediv").show();
									$(".validateTips").text(msg)

									setTimeout(function(){

										window.href.location="adminMenu.html?method=classList"

									},3000);

								}
								setTimeout(function(){

									window.href.location="adminMenu.html?method=classList"

								},3000);	

							}
						});
						$(this).dialog("close");
					},

					"No" : function() {
						$(this).dialog("close");
					}
				}
			});




			$("#search").click(function(){
				var searchname = $("#searchname").val().trim();
				window.location.href = "adminMenu.html?method=classList&searchname="
					+ searchname+"&school="+$("#school").val();
			});


			$("#searchname").keypress(function(event) {
				var searchText = $("#searchname").val();
				if (event.keyCode == 13) {

					window.location.href ="adminMenu.html?method=classList&searchname="
						+ searchText+"&school="+$("#school").val();;
				}

			});



			$('#excelDownload')
			.click(
					function() {

						var searchTerm = $("#searchexamid").val().trim();

						window.location.href = 'classPath.html?method=classPathDetailsXLS&searchTerm='+ searchTerm;

					});

			$("#pdfDownload").click(function(){

				var searchTerm = $("#searchexamid").val().trim();

				window.location.href = "classPath.html?method=classPathDetailsPDFReport&searchTerm="+ searchTerm;

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
function checkUpdateClassName() {
	var className = $("#className").val();
	var stream = $("#stream").val();
	var updateClassCode = $('#updateClassCode').val();
	var checkClassName = {
			"className" : className,
			"stream" : stream,
			"updateClassCode":updateClassCode,
	};

	var status = false;

	$.ajax({
		type : "POST",
		url : "classPath.html?method=classNameCheck",
		data : checkClassName,
		async : false,
		success : function(data) {

			var result = $.parseJSON(data);

			status = result.status;

		}
	});

	return status;

}


function removeMessage() {


	$.ajax({
		type : "GET",
		url : "transport.html?method=removeMessage",
		async : false,
		success : function(data) {
			$('#serverMessage').empty();
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

function getStudentListforPrint(locationid,streamId){

	var datalist = {
			
			"location" :locationid,
			"streamId" :streamId,
			
		}; 
		$.ajax({
			type : 'POST',
			url : "adminMenu.html?method=classListforListOnchangeMethod",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
					for(var i=0;i<result.list.length;i++){
					//	alert(result.list.length);
					
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' id='"+result.list[i].classId+","+result.list[i].locationId+"' /></td>"
							+"<td> "+result.list[i].locationName+" </td>"
							+"<td> "+result.list[i].streamName+" </td>"
							+"<td> "+result.list[i].className+" </td>"
							+"</tr>");
					}	
					}
					else{
						$("#allstudent tbody").append("<tr>" +"<td colspan='4'>No Records Founds</td>" +"</tr>");
					}
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
			}
		});

}

