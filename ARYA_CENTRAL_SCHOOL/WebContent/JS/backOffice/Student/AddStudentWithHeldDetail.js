$(document).ready(function() {
	reportHistory();
	var StudentImage=$("#photohiddenid").val();
	if(StudentImage!=""){
		$("#imagePreview").show();
		$('#imagePreview').attr('src', StudentImage);
	}
	$('#reportHistory').click(function(){
		reportHistory();
	});
	$('#contacts').click(function(){
		showCancelDetails();
	});
	$("#back").click(function(){
		window.location.href="adminMenu.html?method=studentWithheldList";
	});
	$('#addconfidential').click(function() {
		$("#admissionDialog").dialog("open");
	});
		$("#entryDate").datepicker({
			maxDate:0,
			dateFormat : "dd-mm-yy",
			yearRange : 'c-65:c+65',
			changeMonth : "true",
			changeYear : "true",
			yearRange : '1997:' + (new Date).getFullYear(),
			onClose : function(selectedDate) {
				$('#entryDate').datepicker('getDate');
			}
		});
		var dNow = new Date();
		var Day = dNow.getDate();
		if (Day < 10) {
			Day = '0' + Day;
		} // end if
		var Month = dNow.getMonth() + 1;
		if (Month < 10) {
			Month = '0' + Month;
		}
		var localdate = Day + '-' + Month + '-'
				+ dNow.getFullYear();
		$("#entryDate").val(localdate);
	$("#editentryDate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
	});
	$("#sectionid").change(function(){
		if($(this).val()=="cancel"){
			$(".fromdate").show();
			$("#cancelreason").show();
		}
		else if($(this).val()=="yes"){
			$(".fromdate").hide();
			$("#cancelreason").hide();
		}
	});
	$("#editfromdate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate : 0,
		changeMonth : true,
		changeYear : true,
	});
	$("#admissionDialog").dialog({
	    autoOpen  : false,
	    maxWidth  :	550,
        maxHeight : 500,
        width     : 550,
        height    : 400,
	    modal     : true,
	    title     : "Add WithHeld Detail",
	    buttons   : {
	    	'Save'  : function() {
	    		var entryDate = $("#entryDate").val();
	    		var comment = $("#comment").val();
	    		var cancelcomment = $("#cancelcomment").val();
	    		var Fromdate = $("#Fromdate").val();
	    		var sectionid= $("#sectionid").val();
	    		
    			 if(entryDate == "" || entryDate == null){
    				 $(".errormessagediv").show();
    					$(".validateTips").text("Select Date");
    					showError("#entryDate");
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
    			 }
    			 else if($("#sectionid").val()==undefined || $("#sectionid").val()=="" || $("#sectionid").val()==null){
    				 $(".errormessagediv").show();
    					$(".validateTips").text("Select the status");
    					showError("#sectionid");
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
    			 }
    			 else if(comment == "" || comment == null){
    				 $(".errormessagediv").show();
    					$(".validateTips").text("Field Required - Comment");
    					showError("#comment");
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
    			 }
    			 else{  
    				 setTimeout(function() {
 						location.reload();
 					}, 3000);
    				 $(this).dialog('close');
    				 addConfidetial();
    			 }
	    	}
	    }
	});
	$("#editsectionid").change(function(){
		var status=$(this).val();
		var editfromdate=$("#editfromdate").val();
		var editcancelcomment=$("#editcancelcomment").val();
		if($(this).val()=="cancel"){
			$("#fromdate1").show();
			$("#cancelreason").show();
			$("#editcomment").prop("readonly",true);
			$("#editentryDate").datepicker('disable');
		}
		else if($(this).val()=="yes"){
			$("#fromdate1").hide();
			$("#cancelreason").hide();
			$("#editcomment").prop("enable",true);
			$("#editentryDate").datepicker('enable');
		}
	});
	
	$("#editDialog").dialog({
	    autoOpen  : false,
	    maxWidth  :	750,
        maxHeight : 500,
        width     : 760,
        height    : 450,
	    modal     : true,
	    title     : "Edit WithHeld",
	    buttons   : {
	    	'Update'  : function() {
	    		 var currentId = editid[0];
	    		 var currentDate = $("#editentryDate").val();
	    		 var CurrentComments = $("#editcomment").val();
	    		 var editsectionid=  $("#editsectionid").val();
	    		 var editfromdate=$("#editfromdate").val();
	    		 var editcancelcomment=$("#editcancelcomment").val();
	    		 
	    		 if(editsectionid=="cancel"){
	    			    $("#fromdate1").show();
	    				$("#cancelreason").show();
	    			 if(currentDate == "" || currentDate == null){
	    				 $(".errormessagediv").show();
	    					$(".validateTips").text("Select Date");
	    					showError("#editentryDate");
	    					setTimeout(function() {
	    						$('.errormessagediv').fadeOut();
	    					}, 3000);
	    			 }
	    			 else if(CurrentComments == "" || CurrentComments == null){
	    				 $(".errormessagediv").show();
	    					$(".validateTips").text("Field Required - Comment");
	    					showError("#comment");
	    					setTimeout(function() {
	    						$('.errormessagediv').fadeOut();
	    					}, 3000);
	    			 }
	    			 else if(editfromdate == "" || editfromdate == undefined || editfromdate == null){
	    				 $(".errormessagediv").show();
	    					$(".validateTips").text("select Cancel Date");
	    					showError("#editfromdate");
	    					setTimeout(function() {
	    						$('.errormessagediv').fadeOut();
	    					}, 3000);
	    			 }
	    		 else if(editcancelcomment == "" || editcancelcomment == undefined || editcancelcomment == null){
	    				 $(".errormessagediv").show();
	    					$(".validateTips").text("Field Required -Cancel Reason");
	    					showError("#editcancelcomment");
	    					setTimeout(function() {
	    						$('.errormessagediv').fadeOut();
	    					}, 3000);
	    			 }
	    			 else{  
	    				 setTimeout(function() {
	  						location.reload();
	  					}, 3000);
	    				 $(this).dialog('close');
	    				 editConfidetial();
	    			 }
	    		 }
	    		 else{
    			 if(currentDate == "" || currentDate == null){
    				 $(".errormessagediv").show();
    					$(".validateTips").text("Select Date");
    					showError("#editentryDate");
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
    			 }
    			 else if(CurrentComments == "" || CurrentComments == null){
    				 $(".errormessagediv").show();
    					$(".validateTips").text("Field Required - Comment");
    					showError("#comment");
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
    			 }
    			 else{  
    				 setTimeout(function() {
  						location.reload();
  					}, 3000);
    				 $(this).dialog('close');
    				 editConfidetial();
    			 }
	    		}
	    	}
	    }
	});
	$("#deleteDialog").dialog({
		autoOpen  : false,
		maxWidth  :	300,
		maxHeight : 200,
		width     : 300,
		height    : 200,
		modal     : true,
		title     : "Delete Report",
		buttons   : {
			'Yes'  : function() {
				 deleteReport(deleteid);
				$(this).dialog('close');
			},
			'No' : function() {
				$(this).dialog('close');
				$(".errormessagediv").hide();
			}
		}
	});
});
	function showCancelDetails(){
		var studentId = $('#hstudentid').val();
		var accyear = $('#hacademicYearId').val();
		var locationId = $('#hschoolNameId').val();
		var editsectionid=$('#editsectionid').val();
		$("#addconfidential").hide();
		$('#individualstudenttable').hide();
		$('#individualstudentcanceltable').show();
		$('#studenttable').hide();
		$('#individualstudentcanceltable').empty();
		$("#individualstudentcanceltable").append("<table class='table'  id='allstudent'  width='100%'" +">"
	    
				+"<center><tr><th>SI No</th>"+
				"<th>WithHeld Date</th>"+
				"<th>Reason</th>"+
				"<th>WithHeld Cancel Date</th>"+
				"<th>WithHeld Cancel Comments</th>"+
				"</center></tr>"+
				"</table>"
		);
		$.ajax({
			type : "POST",
			url : "studentRegistration.html?method=singleStudentWithHeldDetailsList",
			data : {"studentId":studentId,
					"accyear":accyear,
					"locationId":locationId,
					"flag":"cancel",
					"editsectionid":editsectionid
			},
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				for (var j = 0; j < result.studentSearchList.length; j++) {
			
					$("#individualstudentcanceltable #allstudent").append(
							"<tr>"
							+"<td class='"+result.studentSearchList[j].confidentialId+"_"+result.studentSearchList[j].confidentialEntryDate+"_"+result.studentSearchList[j].status+"_"+result.studentSearchList[j].confidentialComments+"_"+result.studentSearchList[j].cancelDate+"_"+result.studentSearchList[j].cancelcomment+"_"+" splitid"+"'>"+result.studentSearchList[j].count+"</td>"
							+"<td> "+result.studentSearchList[j].confidentialEntryDate+" </td>"
							+"<td ><span class='comments'>"+result.studentSearchList[j].confidentialComments+" </td>"
							+"<td> "+result.studentSearchList[j].cancelDate+" </td>"
							+"<td> "+result.studentSearchList[j].cancelcomment+"<span class='deletecancel buttons' id='"+result.studentSearchList[j].confidentialId+"'>Delete</span></td>"
							+"</tr>"
						);
					deletecancel();
					}		
			}
		});}
	function reportHistory(){
		var studentId = $('#hstudentid').val();
		var accyear = $('#hacademicYearId').val();
		var locationId = $('#hschoolNameId').val();
		var editsectionid=$('#editsectionid').val();
		
		$('#addconfidential').show();
		$('#individualstudenttable').show();
		$("#individualstudentcanceltable").hide();
		$('#studenttable').hide();
		
		$('#individualstudenttable').empty();
		$("#individualstudenttable").append("<table class='table addtable' id='allstudent' left='100px' margin-top='59px' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>WithHeld Date</th>" +
				"<th>Reason</th>" +
				"</center></tr>" +
				"</table>"
		);
		$.ajax({
			type : "POST",
			url : "studentRegistration.html?method=singleStudentWithHeldDetailsList",
			data : {"studentId":studentId,
					"accyear":accyear,
					"locationId":locationId,
					"flag":"withheld",
					"editsectionid":editsectionid
			},
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				for (var j = 0; j < result.studentSearchList.length; j++) {
					$("#individualstudenttable #allstudent").append(
							"<tr>"
							+"<td class='"+result.studentSearchList[j].confidentialId+"_"+result.studentSearchList[j].confidentialEntryDate+"_"+result.studentSearchList[j].status+"_"+result.studentSearchList[j].confidentialComments+"_"+result.studentSearchList[j].cancelDate+"_"+result.studentSearchList[j].cancelcomment+"_"+" splitid"+"'>"+result.studentSearchList[j].count+"</td>"
							+"<td> "+result.studentSearchList[j].confidentialEntryDate+" </td>"
							+"<td ><span class='comments'>"+result.studentSearchList[j].confidentialComments+"</span><span class='edit buttons' id='"+result.studentSearchList[j].confidentialId+"'>Edit</span>&nbsp;&nbsp;&nbsp;<span class='delete buttons' id='"+result.studentSearchList[j].confidentialId+"'>Delete</span></td>"
							+"</tr>"
						);
					edit();
					deletewithheld();
					}		
			}
		});
	}
	function addConfidetial(){
		 var entryDate = $("#entryDate").val();
		 var comments = $("#comment").val();
		 var Fromdate = $("#Fromdate").val();
		 var cancelcomment = $("#cancelcomment").val();
		 var student_id = $("#hstudentid").val();
		 var academicyear_id = $("#hacademicYearId").val();
		 var location_id = $("#hschoolNameId").val();
		 var sectionid= $("#sectionid").val();
		 var withheldId= $("#withheldId").val();
		 $.ajax({
				type : "POST",
				url : "studentRegistration.html?method=AddWithHeldDetails",
				data : {"entryDate":entryDate,
						"comments":comments,
						"student_id":student_id,
						"academicyear_id":academicyear_id,
						"location_id":location_id,
						"cancelcomment":cancelcomment,
						"sectionid":sectionid,
						"Fromdate":Fromdate,
						"withheldId":withheldId,
				},
				async : false,
				success : function(response) {
					var result = $.parseJSON(response);
					if(result.studentSearchList =="confidentialReportSuccess"){
						$(".successmessagediv").show();
    					$(".successmessage").text("Adding Record Progressing...");
    					 setTimeout(function() {
    	 						location.reload();
    	 					}, 3000);
					}else{
						$(".errormessagediv").show();
    					$(".validateTips").text("WithHeld Detail Adding Failed ");
    					setTimeout(function() {
    						$('.errormessagediv').fadeOut();
    					}, 3000);
					}
					
				}
			});
	}
	function editConfidetial(){
		 var currentDate = $("#editentryDate").val();
		 var currentId = editid[0];
		 var editsectionid = $("#editsectionid").val();
		 var CurrentComments = $("#editcomment").val();
		 var Fromdate = $("#editfromdate").val();
		 var CancelComment = $("#editcancelcomment").val();
		 var student_id = $("#hstudentid").val();
		 var withheldId= $("#withheld").val();
		 $.ajax({
				type : "POST",
				url : "studentRegistration.html?method=EditWithHeldDetails",/*EditConfidentialDetails*/
				data : {"entrydate":currentDate,
					     "sectionid":editsectionid,          
						"comment":CurrentComments,
						"Fromdate":Fromdate,
						"student_id":student_id,
						"cancelcomment":CancelComment,
						"editid":currentId,
						"withheldId":withheldId,
				},
				async : false,
				success : function(response) {
					var result = $.parseJSON(response);
					if(result.studentSearchList =="EditSuccess"){
						$(".successmessagediv").show();
						$(".successmessage").text("Updating Record Progressing...");
						 setTimeout(function() {
							 $('.successmessagediv').fadeOut();
		 					}, 3000);
					}else{
						$(".errormessagediv").show();
						$(".validateTips").text("Confidential Report Updating Failed ");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
					}
				}
			});
		}
	function deleteReport(deleteid){
		 datalist = {
			"deleteid" :deleteid
		 };
		 $.ajax({
				type : "POST",
				url : "studentRegistration.html?method=deleteWithHeldDetails",
				data : datalist,
				async : false,
				success : function(data){
					var result = $.parseJSON(data);
					
					if(result.studentSearchList == "DeleteSuccess"){
				    	 $(".successmessagediv").show();
							$(".successmessage").text("Deleting Record Progressing...");
							setTimeout(function() {
								$('.successmessagediv').fadeOut();
								location.reload();
							}, 3000);
				     }
				}
			});
	}
function deletecancel(){
	$('.deletecancel').click(function(){
		deletevalues=$( this ).parent("td").parent("tr").find(".splitid").attr("class").split("_");
		deleteid=deletevalues[0];
		$("#deleteDialog").dialog("open");
	});
}
	function edit(){
		$("#allstudent tbody td .edit").click(function(){
			editid=$( this ).parent("td").parent("tr").find(".splitid").attr("class").split("_");
			$('#editentryDate').val(editid[1]);
			$('#editcomment').val($(this).closest('tr').find('.comments').text());
			$("#editsectionid").val(editid[2]);
			if($("#editsectionid").val() == "cancel"){
				$("#editfromdate").val(editid[4]);
				$("#editcancelcomment").val(editid[5]);
				$("#fromdate1").show();
				$("#cancelreason").show();
				$("#editDialog").dialog("open");
			}
			$("#editDialog").dialog("open");
		});
	}
	function deletewithheld(){
		$('.delete').click(function(){
			deletevalues=$( this ).parent("td").parent("tr").find(".splitid").attr("class").split("_");
			deleteid=deletevalues[0];
			$("#deleteDialog").dialog("open");
		});
	}
	
function showError(id){
		$(id).css({
			"border":"1px solid #AF2C2C",
			"background-color":"#FFF7F7"
		});
		$(".form-control").not(id).css({
			"border":"1px solid #ccc",
			"background-color":"#fff"
		});
		
	}