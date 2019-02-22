

function myFunction() {
    
	 var searchText = $("#searchValue").val().trim();
			
	       
			window.location.href = "adminMenu.html?method=sectionList&searchText="
			+ searchText+"&school="+$('#school').val();
 }

$(document).ready(
				function() {
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
						getStudentListforPrint(locationid,classname,streamId)
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
						getStudentListforPrint(locationid,classname,streamId);
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

						getStudentListforPrint(locationid,classname,streamId);
					});
						
					
					
				$(".section").click(function(){
						$("#sectionOne").slideToggle();
					});
					
					
					setTimeout("removeMessage()" ,2000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
					
				
					$("#selectall").change(function(){

						 $(".select").prop('checked', $(this).prop("checked"));
						 
						 
						});
					
					
					
					$('#editClass')
					.click(
							function() {
								
								var count = 0;
								
								$('input[type="checkbox"]:checked').map(function() {
													 class_id = $(this).attr("id").split(",")[0];
													count++;
												});
								if (count == 0 || count > 1) {
									$(".errormessagediv").show();
									$(".validateTips").text(
											"Select any one Division");
									return false;

								} else {
									
									window.location.href = "sectionPath.html?method=editSection&classCode="+class_id;
								}
							});
					
					$('#sectionDelete')
					.click(
							function() {
								
								var count =0;
								 sectionIdlist=[];
								 locationList=[];
						 		$(".select:checked").each(function(){
									 
									var list=$(this).attr("id").split(",")[0];
									var location=$(this).attr("id").split(",")[1];
									locationList.push(location);
									sectionIdlist.push(list);
									count ++;
									 
								 });
								
								if (count == 0) {
									$(".errormessagediv").show();
									$(".validateTips").text(
											"Select Division to Delete");
									return false;

								} else {
									
									jQuery("#dialog").dialog("open");
									 $("#dialog").empty();
									  $("#dialog").append("<p>Are you sure to delete?</p>");
								}

							});
									
					jQuery("#dialog").dialog({
						
						 autoOpen: false,
					     modal: true,					    
					     title:'Section Details',
					     buttons : {
					    	 
					    	 "Yes" : function() {
					    		 
					    		 var datalist = {
											"sectionCode" : sectionIdlist,
											"locationList":locationList
										};
									
									$.ajax({
										type : 'POST',
										url : "sectionPath.html?method=deleteCampusClassSectionAction",
										data : datalist,
										success : function(response) {
											var result = $.parseJSON(response);
											
											if (result.status == true) {
												var msg = "Delete Unmapped Division Details Progressing...";
												
												$(".successmessagediv").show();
												$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");

												$(".validateTips").text(
														msg);
												
												setTimeout(function(){
													 window.location.href = "adminMenu.html?method=sectionList";
												 },3000);
												
									
											} else {
												var msg = "Selected Division is Mapped Cannot Delete";
												
												$(".errormessagediv").show();
												$(".validateTips").text(
														msg);
												
												setTimeout(function(){
													 window.location.href = "adminMenu.html?method=sectionList";
												 },3000);
											}
										}

									});
									jQuery(this).dialog("close");
					    	 },
					    	 "No" : function() {
					    		 jQuery(this).dialog("close");
						          }
					     }
					});
						
				
					$("#search").click(function(){
						var searchText = $("#searchValue").val().trim();
						window.location.href = "adminMenu.html?method=sectionList&searchText="
							+ searchText+"&school="+$('#school').val();
					});
					
					
					$('#excelDownload')
					.click(
							function() {
								
								
								var searchTerm = $("#searchexamid").val().trim();
								
							
								
								window.location.href = "sectionPath.html?method=downloadSectionDetailsXLS&searchTerm="
									+ searchTerm;
								
							});

					$("#pdfDownload").click(function(){
						
						
						var searchTerm = $("#searchexamid").val().trim();
					
						
						window.location.href = "sectionPath.html?method=downloadSectionDetailsPDF&searchTerm="
									+ searchTerm;
							
					});	
					
					
				});
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


function getStudentListforPrint(locationid,classname,streamId){

	var datalist = {
			
			"location" :locationid,
			"classId" :classname,
			"streamId":streamId,
			
		}; 
		$.ajax({
			type : 'POST',
			url : "adminMenu.html?method=sectionListcheckTemporary",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
					for(var i=0;i<result.list.length;i++){

					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' id='"+result.list[i].secId+","+result.list[i].locationId+"' /></td>"
							+"<td> "+result.list[i].locationName+" </td>"
							+"<td> "+result.list[i].streamName+"</td>"
							+"<td> "+result.list[i].className+" </td>"
							+"<td> "+result.list[i].sectionName+" </td>"
							+"<td> "+result.list[i].strength+" </td>"
							+"</tr>");
					   }
					}
					else{
						$("#allstudent tbody").append("<tr>" +"<td colspan='6'>No Records Founds</td>"+"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
					pagination(100);
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
