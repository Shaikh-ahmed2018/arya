$(document).ready(function(){
	
	$("#imagePreview").attr('src',$("#hidenimage").val());
	
	$("input[name='requested_by'][value="+$("#hiddenUserType").val()+"]").prop('checked',true);
	
	if($("#hiddenUserType").val()=="Teacher")
	{
	 
		$("#usertype").show();
		$(".admissionno").hide();
		$("#class").hide();
		$("#divission").hide();
		$("#studentname").hide();
		$("#userType").prop("readonly", true);
		$("#othersname").hide();
		$("#department").show();
		$("#designation").show();
		$("#contactno").hide();
		$("#addr").hide();
			$("#radiotype").text("Subscriber No");
	}
	else if($("#hiddenUserType").val()=="Student")
		{
	
		$("#usertype").show();
		$(".admissionno").show();
		$("#class").show();
		$("#divission").show();
		$("#teacher").hide();
		$("#othersname").hide();
		$("#userType").prop("readonly", true);
		$("#department").hide();
		$("#designation").hide();
		$("#contactno").hide();
		$("#addr").hide();
		
		$("#radiotype").text("Subscriber No");
		
		}
	else if($("#hiddenUserType").val()=="other")
		{
	
		$("#usertype").show();
		$(".admissionno").hide();
		$("#class").hide();
		$("#divission").hide();
		$("#teacher").hide();
		$("#department").hide();
		$("#designation").hide();
		$("#studentname").hide();
		$("#userType").prop("readonly", true);
		$("#contactno").show();
		$("#addr").show();
		
		$("#radiotype").text("Subscriber No");
}
	
	$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:$("#hiddenendaccyear").val()-1,
        minDate :0,
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
	        var max = $(this).datepicker('getDate');
			$("#ToDate").datepicker("option","minDate", max || $("#hiddenendaccyear").val());
		}
	});	

	$("#ToDate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:$("#hiddenendaccyear").val()-1,
        minDate :$("#hiddenstartaccyear").val(),
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			var max = $(this).datepicker('getDate');
			$("#datepicker").datepicker("option","minDate",max || '+1Y');
		}
	});
	
	
	
	
	
	$("#reserve").click(function(){
		
	
		
		if($("#accession_no").val()=="" ||$("#accession_no").val()==null){
			$(".errormessagediv").show();
			$(".validateTips").text(" Field Required-Accession No ");
			showError("#accession_no");
			
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			},1000);
			return false;
		}
		else if($("#itemtype").val()=="" ||$("#itemtype").val()==null){
			$(".errormessagediv").show();
			$(".validateTips").text(" Field Required-Item Type ");
			showError("#itemtype");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			},1000);
			return false;
		}
		  else if($("input[name='requested_by']:checked").val()==undefined){
			$(".errormessagediv").show();
			$(".validateTips").text(" Field Required- Issuing To ");
			showError("#student");
						setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 1000);
			return false;
		
		}	 else if($("#userType").val()=="" ||$("#userType").val()==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required -"+$("input[name='requested_by']:checked").val()+" "+"ID");
			showError("#userType");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 1000);
			return false;
		}
		else if($("#Fromdate").val()=="" || $("#Fromdate").val()==undefined){
			
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required -From Date");
			showError("#Fromdate");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 1000);
			return false;
	 }	
		else if($("#ToDate").val()=="" || $("#ToDate").val()==undefined){

			$(".errormessagediv").show();
			$(".validateTips").text("Field Required -To Date");
			showError("#ToDate");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 1000);
			return false;
		}	
		else if ( validateReservedBook() == 1)
		{

			$(".errormessagediv").show();
			$(".validateTips").text("Book is already reserved..");

			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 1000);
			return false;
		}
		else if ( validate() == 1)
		{

			$(".errormessagediv").show();
			$(".validateTips").text("Enter All The Details...");

			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 1000);
			return false;
		}

	
		else{
	
		
				datalist={
				"accession_no":$("#hidden_accessionNo").val(),	
				"itemtype" : $("#itemtype").val().trim(),
				"bookTitle" : $("#bookTitle").val().trim(),
				"author" : $("#author").val().trim(),
				"category" : $("#category").val().trim(),
				"ddc" : $("#ddc").val().trim(),
				"currentstatus" : $("#currentstatus").val(),
				"subid" : $("#hiddenstuid").val().trim(),
				"usertype":$("input[name='requested_by']:checked").val(),
				"imagePreview" :$("#imagePreview").val(),
				"shelfNo" : $("#shelfNo").val().trim(),
				"locationname" : $("#hiddenlocation").val(),
				"fromdate" : $("#Fromdate").val().trim(),
				"todate":$("#ToDate").val().trim(),
				"hiddenreserveid":$("#hiddenreserveid").val(),
				"userId" : $("#hiddenstuid").val().split("-")[1]
				
				
		},
		$.ajax({
			type:"post",
			url:"LibraryMenu.html?method=insertBookReservationDetails",
			data:datalist,
			async:false,
			success:function(data){
				
				var result=$.parseJSON(data);
				 if(result.status =="success"){
						$(".successmessagediv").show();
						$(".validateTips").text("Book Reserved Successfully");
						setTimeout(function() {
							window.location.href="LibraryMenu.html?method=BookReservationDetailslist";
						},3000);
					}else if(result.status =="fail"){
						$(".errormessagediv").show();
						$(".validateTips").text("Book Reservation Failed.... ");
						setTimeout(function() {
							window.location.href="LibraryMenu.html?method=BookReservationDetailslist";
						},3000);
					}
					else if(result.status=="successfully"){
						$(".successmessagediv").show();
						$(".validateTips").text("Update Record is  processing....");
						setTimeout(function() {
							window.location.href="LibraryMenu.html?method=BookReservationDetailslist";

						},1000);
					}else if(result.status=="failed"){
						$(".errormessagediv").show();
						$(".validateTips").text("Update Record failed....");
						setTimeout(function() {
							window.location.href="LibraryMenu.html?method=BookReservationDetailslist";


						},2000);

					}
					}
					});
		
	}
	});
	
	
	$("input[name='requested_by']").change(function(){
		
		if($(this).val()=="Teacher")
			{
			   $("input[name='usertype']").val("");
			$("#usertype").show();
			$(".admissionno").hide();
			$("#class").hide();
			$("#divission").hide();
			
			$("#department").show();
			$("#designation").show();
			$("#contactno").hide();
			$("#addr").hide();
				$("#radiotype").text("Subscriber No");
			}
			else if($(this).val()=="Student"){
				$("input[name='usertype']").val("");
				$("#usertype").show();
				$(".admissionno").show();
				$("#class").show();
				$("#divission").show();
			    
				$("#department").hide();
				$("#designation").hide();
				$("#contactno").hide();
				$("#addr").hide();
				
				$("#radiotype").text("Subscriber No");
}
			else if($(this).val()=="other"){
				$("input[name='usertype']").val("");
				$("#usertype").show();
				$(".admissionno").hide();
				$("#class").hide();
				$("#divission").hide();
				
				$("#department").hide();
				$("#designation").hide();
				
				$("#contactno").show();
				$("#addr").show();
				
				$("#radiotype").text("Subscriber No");
}
});
		$("#locationname").change(function(){
			$("#searchvalue").val("");
			var classname=$("#classname").val();
		
		});
		
		
		
		$("#userType").autocomplete({
			source : function(request,response) { 
				if($("input[name='requested_by']:checked").val() == "Student")
				{
					$.ajax({

					url : "LibraryMenu.html?method=studentSearchbyadmissionNo",
					data : {
						searchTerm : request.term,
						locid : $("#locationname").val()
					},
					
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						
						response($.map(	result.jsonResponse,function(item) {
							return {
								label : item.subscriberNumber,
								value : item.studentId,
							}
						}));
					}
				});
			    }
		else if($("input[name='requested_by']:checked").val() == "Teacher")
		        { 
		   
		
						$.ajax({
							

					url : "LibraryMenu.html?method=teacherSearchbyId",
					data : {
						searchTerm : request.term,
						locid : $("#locationname").val()
					},
					
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						
						response($.map(	result.jsonResponse,function(item) {
							return {
								label : item.subscriberNumber,
								value : item.teacherId,
							}
						}));
					}
				});
			    }
				
		else if($("input[name='requested_by']:checked").val() =="other")
        { 
           	$.ajax({
           		url : "LibraryMenu.html?method=othersSearchbyId",
           		
		 	data : {
				searchTerm : request.term,
				locid : $("#locationname").val()
			},
			
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				
				response($.map(	result.jsonResponse,function(item) {
					return {
						label : item.subscriberNumber, 
						value : item.teacherId,
					}
				}));
			}
		});
	    }
			

			},
			select : function(event, ui) {

				var searchTerm = ui.item.value;

				studentDetails = {
						'searchTerm' : searchTerm,
						'userType':userType
						
				};
				if($("input[name='requested_by']:checked").val() =="Student"){
					getSudentDetails(searchTerm.split("-")[0]);
				}
				else if($("input[name='requested_by']:checked").val() =="Teacher"){
					getTeacherDetails(searchTerm.split("-")[0]);
				}
                 else if($("input[name='requested_by']:checked").val() =="other"){
                	 getOtherDetails(searchTerm.split("-")[0]);
				}

				$("#userType").val(ui.item.label);
				$("#hiddenstuid").val(ui.item.value);
				return false;

			}
		});
		
		$("#accession_no").autocomplete({
			
			source : function(request, response) {
				$("#hidden_accessionNo").val("");
				$("#hidden_teacherId").val("");
				$.ajax({
					
					url : "LibraryMenu.html?method=getAccessionNoByIssueStatus",
					data:{
						searchTerm : request.term,
						accessionId : $("#accession_no").val()
					},
					async:false,
					success:function(data) {
						
						var result=$.parseJSON(data);
						response($.map(result.jsonResponse,function(item){
							return {
								label : item.accessionNo ,
								value : item.accessionNoId ,
							}
					}));
					}
					});
				
		},
		select : function(event, ui)
		{
			var searchTerm = ui.item.value;
			
			accessionDetails={
					'searchTerm' : searchTerm
			};
				$.ajax({
					type:"POST",
					url:"LibraryMenu.html?method=getBookReservationDetailsByAccNo",
					data :accessionDetails,
					async : false,
					success:function(data){
						var result = $.parseJSON(data);
						for(var i=0;i<result.accessionList.length;i++){
					
							$("#itemtype").val(result.accessionList[i].itemType);
							$("#bookTitle").val(result.accessionList[i].bookTitle);
							$("#author").val(result.accessionList[i].author);
							$("#category").val(result.accessionList[i].category);
							$("#ddc").val(result.accessionList[i].ddc);
							$("#currentstatus").val(result.accessionList[i].currentStatus);
							$("#imagePreview").attr("src",result.accessionList[i].imageurl);
							$("#shelfNo").val(result.accessionList[i].shelfNo);
							$("#locationname").val(result.accessionList[i].location);
							$("#hiddenlocation").val(result.accessionList[i].libLocId);
							$("#hiddencategory").val(result.accessionList[i].catid);
						
						}
					}
				});
			$("#accession_no").val(ui.item.label);
			$("#hidden_accessionNo").val(searchTerm);
			return false;
		}
	});
		
		
		
		
		

});

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

function getSudentDetails(searchTerm){
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getStudentIssueDetailsBySubscriberNo",
		data : {
			"searchTerm" : searchTerm,
			"userType" : $("#userType").val()
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			if(result.accessionList.length >0){
			for(var i=0;i<result.accessionList.length;i++){
				$("#admissionno").val(result.accessionList[i].admissionNo);
				$("#hiddenclassId").val(result.accessionList[i].className);
				$("#hiddendivissionId").val(result.accessionList[i].sectionName);
			}
			}
			else{
				$("#itemtype,#bookTitle,#author,#category,#ddc,#currentstatus,#shelfNo,#locationname").val("");
			}
		}
	});
	
	return false;
}

function getTeacherDetails(searchTerm){
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getTeacherIssueDetails",
		data : {
			"searchTerm" : searchTerm,
			"userType" : $("#userType").val()
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			if(result.accessionList.length >0){
			for(var i=0;i<result.accessionList.length;i++){
				$("#hiddendepartment").val(result.accessionList[i].department);
				$("#hiddendesignation").val(result.accessionList[i].designation);
			}
			}
			else{
				$("#itemtype,#bookTitle,#author,#category,#ddc,#currentstatus,#shelfNo,#locationname").val("");
			}
		}
	});
	return false;
}

function getOtherDetails(searchTerm){
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getOtherIssueDetails",
		data : {
			"searchTerm" : searchTerm,
			"userType" : $("#userType").val()
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			if(result.accessionList.length >0){
			for(var i=0;i<result.accessionList.length;i++){
				$("#hiddencontactno").val(result.accessionList[i].contactNo);
				$("#hiddenotheraddr").val(result.accessionList[i].address);
			}
			}
			else{
				$("#itemtype,#bookTitle,#author,#category,#ddc,#currentstatus,#shelfNo,#locationname").val("");
			}
		}
	});
	
	return false;
}

function validateReservedBook(){
	
	
	validate_book = 0;
	var data={
			"accession_no" : $("#accession_no").val().trim(),
			"subscriberid" : $("#hiddenstuid").val().split("-")[1],
			"Fromdate": $("#Fromdate").val().trim() ,
			"ToDate": $("#ToDate").val().trim(), 
			
	};
	
	/*if($("#hiddenlibid").val() != ""){
		
		validate_book = 0;
		
	}
		
	else{*/
		$.ajax({
			type : "post",
			url : "LibraryMenu.html?method=validateReservedBook",
			data : data,
			async : false,

			success : function(data){
				var result = $.parseJSON(data);

				if (result.status == "true") {
					validate_book = 1;

				} else {
					validate_book = 0;
				}
			}
		});
	

return validate_book;
}

function validate(){
	flag = 0;
	if($("input[name='requested_by']:checked").val()=='Teacher')
	{
		
		if($("#hiddendepartment").val()=="" ||$("#hiddendepartment").val()==null)
			{
		$(".errormessagediv").show();
		$(".validateTips").text(" Field Required- Subscriber No ");
		showError("#hiddendepartment");
					setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
					flag = 1;
	}
		else{
			flag=0;
		}	
	}
	else if($("input[name='requested_by']:checked").val()=='Student')
	{
		if($("#admissionno").val()=="" ||$("#admissionno").val()==null)
			{
		$(".errormessagediv").show();
		$(".validateTips").text(" Field Required- Subscriber No ");
		showError("#admissionno");
					setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
					flag = 1;
	}else{
		flag = 0;
	}
	}
	else if($("input[name='requested_by']:checked").val()=='other')
	{
		if($("#hiddencontactno").val()=="" ||$("#hiddencontactno").val()==null)
			{
		$(".errormessagediv").show();
		$(".validateTips").text(" Field Required- Subscriber No ");
		showError("#hiddencontactno");
					setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
					flag = 1;
	}
		else{
			flag = 0;
			}
	}

	return flag;
	
}





