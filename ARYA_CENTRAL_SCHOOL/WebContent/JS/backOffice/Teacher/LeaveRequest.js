function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document)
		.ready(
				function() {

					if($("#allstudent tbody tr").length ==0){
						$("#allstudent tbody").append("<tr><td colspan='8'>NO Records Found</td></tr>");
					}
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
					
					
					var aendsess = $('#happroveendsession').val();
					$("#approveendsession option[value=" + aendsess + "]").attr('selected', 'true');
					
					var astartsess = $('#happrovedstartsession').val();
					$("#approvedstartsession option[value=" + astartsess + "]").attr('selected', 'true');
					
					
					
					

					
					  
					  
					/*  $("#editID").click(function(){
					  
					  
					  var snoid=$("#snoid").val();
					  
					  if(snoid==""||snoid==null){
					  
					  $(".errormessagediv").show();
					  $(".validateTips").text("Select any one CheckBox");
					  
					 return false; }
					  
					  
					  
					  else{
					  
					  window.location.href =
					  "parentMenu.html?method=editRequestLeaveAction&snoid="+snoid;
					  }
					 
					  
					 });*/
					 

					$("#delete").click(function() {

										var snoid = $("#snoid").val();
										var count =0;
										 requestList=[];
								 		$(".select:checked").each(function(){
											var list=$(this).attr("id");
											requestList.push(list);
											count ++;
											 
										 });	

										if (count == "" || count == null) {

											$(".errormessagediv").show();
											$(".validateTips").text(
													"Select any one CheckBox");
											return false;
										}else {
											 $("#dialog").dialog("open");
										}
										
										$(".select").prop("checked",false);
										
								});
					
									$("#dialog").dialog({
										
										 autoOpen: false,
									     modal: true,					    
									     title:'Leave Request Details',
									     buttons : {
									    	 "Yes" : function() {
									    		 
									    		 var datalist = {'requestList': requestList.toString()};

													$.ajax({
																type : "GET",
																url : "teachermenuaction.html?method=deleteLeaveRequestAction",
																data : datalist,
																async : false,
																cache : false,
																success : function(response) {
																	var result = $.parseJSON(response);
																	
																	if(result.status == "Leave Request Deleted Successfully"){
																	
																	$(".successmessagediv").show();
																	$(".validateTips").text("Leave Request Canceling Progressing...");
																	setTimeout(function() {
																		window.location.href="teachermenuaction.html?method=leaveRequest";
																	}, 3000);
																}
																	else{
																		$(".errormessagediv").show();
																		$(".validateTips").text("Leave Request not Canceled Successfully");
																		setTimeout(function() {
																			window.location.href="teachermenuaction.html?method=leaveRequest";
																		}, 3000);
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
									
					$('#excelDownload')
							.click(
									function() {
										
										var searchTerm = $("#searchterms").val()
										.trim();
										
										
										window.location.href = 'teachermenuaction.html?method=downloadLeaveRequestXLS&searchTerm='
											+ searchTerm;

									});
					$("#pdfDownload")
							.click(
									function() {
										
										var searchTerm = $("#searchterms").val()
										.trim();
										

										window.location.href = "teachermenuaction.html?method=downloadLeaveRequestPDF&searchTerm="
											+ searchTerm;

									});

					$("#search")
							.click(
									function() {
										var searchTerm = $("#searchterm").val()
												.trim();
										window.location.href = "teachermenuaction.html?method=leaveRequest&searchTerm="
												+ searchTerm;

									});

				});

function getvaldetails(value) {
	
	var status = value.id;
	var snoid=status.split(",");
	
	var hidden1 =snoid[0] ;
	var hidden =snoid[1] ;
	
	$("#snoid").val(hidden1);
	
	$("#leavestatusid").val(hidden);

}


function sendrequest() {

	var userhiddenid = $("#userhiddenid").val();

	if (userhiddenid.match("^PAR")) {

		window.location.href = "parentMenu.html?method=requestLeavescreenadd";
	}

	else {

		window.location.href = "teachermenuaction.html?method=teacherLeaveRequestAdd";
	}

}

function editequest() {

	$(".select:checked").each(function(){
		status=$(this).attr("value");
	})
	
	
	/*var status=$("#leavestatusid").val();*/
	
	var userhiddenid = $("#userhiddenid").val();

	if (status =="Pending")
		{
			if (userhiddenid.match("^PAR")) {

				var snoid = $("#snoid").val();
				var count =0;
				$(".select:checked").each(function(){
					list=$(this).attr("id");
					
					count++;
				});
					if(count == null|| count > 1)
					{
					$(".errormessagediv").show();
					$(".validateTips").text(
					"Select any one CheckBox");
					}
		
					else
					{
							window.location.href = "parentMenu.html?method=editRequestLeaveAction&snoid="
				+ list;
					}
	}


	else {

		var snoid = $("#snoid").val();
		var count = 0;
		$(".select:checked").each(function(){
			list=$(this).attr("id");
			count++;
		});
		
		if(count==0||count > 0)
		{
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one CheckBox");
		}
		else{
			window.location.href = "teachermenuaction.html?method=editRequestLeaveAction&snoid="
				+ list;
		}
	}

		}
	
	else
		{
		var count = 0;
		$(".select:checked").each(function(){
			list=$(this).attr("id");
			count++;
		});
		var snoid = $("#snoid").val();
		if(count==0||count > 0)
		{
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one CheckBox");
		}else{
		window.location.href = "teachermenuaction.html?method=getLeaveAprrovedDetails&snoid="
			+list ;
		}
		}

}
