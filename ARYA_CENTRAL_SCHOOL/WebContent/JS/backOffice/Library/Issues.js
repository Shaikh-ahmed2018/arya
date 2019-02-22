$(document).ready(function(){
	$("#Acyearid").val($("#hidenaccyear").val());
	$("#imagePreview").attr('src',$("#hidenimage").val());
	
	if($("#hiddenusetype").val()=="Student"){
		$("input[name='requested_by'][value='Student']").prop('checked','checked');
		$("#teacher").hide();
		$("#othersname").hide();
		$("#studentname").show();
		
		$(".admissionno").show();
		$("#class").show();
		$("#divission").show();
		$("#department").hide();
		$("#designation").hide();
		$("#contactno").hide();
		$("#addr").hide();
		
		$("#studentname").prop("disable","disable");
		$("#student").attr("disable",true);
		$("#student1").attr("disable",true);
		$("#userType").prop("readonly",true);
		$("#accession_no").prop("readonly",true);
		$("#Fromdate").prop("readonly",true);
		
		$(".back").show();
		$("#issue").hide();
		$("#usertype").show();
		$("#radiotype").text("Student");
	}
	else if($("#hiddenusetype").val()=="staff"){
		
		$("input[name='requested_by'][value='Teacher']").prop('checked','checked');
		
		$("#teacher").show();
		$("#othersname").hide();
		$("#studentname").hide();
		$("#student1").hide();
		
		$("#userType").prop("readonly",true);
		$("#accession_no").prop("readonly",true);
		$("#Fromdate").prop("readonly",true);
		
		$(".back").show();
		$("#issue").hide();
		
		$(".admissionno").hide();
		$("#class").hide();
		$("#divission").hide();
		
		$("#department").show();
		$("#designation").show();
		$("#contactno").hide();
		$("#addr").hide();
		
		$("#usertype").show();
		$("#radiotype").text("Teacher");
	}
     else if($("#hiddenusetype").val()=="others"){
		
    	 $("input[name='requested_by'][value='other']").prop('checked','checked');
    	 $("#other").show();
    	 $("#othersname").show();
 		$("#teacher").hide();
 		$("#studentname").hide();
 		$("#student1").hide();
 		
 		$("#userType").prop("readonly",true);
 		$("#accession_no").prop("readonly",true);
 		$("#Fromdate").prop("readonly",true);
 		
 		$(".back").show();
 		$("#issue").hide();
 		
 		$(".admissionno").hide();
		$("#class").hide();
		$("#divission").hide();
		$("#department").hide();
		$("#designation").hide();
		
		$("#contactno").show();
		$("#addr").show();
		
		$("#hiddenotheraddr").val($("#otheradderss").val()).show();
		$("#otheradderss").show();
 		
 		$("#usertype").show();
 		$("#radiotype").text("Other");
	}

	$('#issue').click(function() {
		
		if($("#accession_no").val()=="" ||$("#accession_no").val()==null){
			$(".errormessagediv").show();
			$(".validateTips").text(" Field required-Accession number ");
			showError("#accession_no");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			},3000);
			return false;
		}
		else if($("#itemtype").val()=="" ||$("#itemtype").val()==null){
			$(".errormessagediv").show();
			$(".validateTips").text(" Field required-Item Type ");
			showError("#itemtype");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			},3000);
			return false;
		}
		  else if($("input[name='requested_by']:checked").val()==undefined){
			$(".errormessagediv").show();
			$(".validateTips").text(" Field Required- Issued To ");
			showError("#student");
						setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		
		}	 else if($("#userType").val()=="" ||$("#userType").val()==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required -"+$("input[name='requested_by']:checked").val()+" "+"ID");
			document.getElementById("userType").style.border = "1px solid #AF2C2C";
			document.getElementById("userType").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}
	else if($("#Fromdate").val()=="" || $("#Fromdate").val()==undefined){
					$(".errormessagediv").show();
					$(".validateTips").text("Field Required -From Date");
					showError("#Fromdate");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 2000);
					return false;
			 }
	else if($("#todate1").val()=="" || $("#todate1").val()==undefined){
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required -To Date");
		showError("#todate1");
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 2000);
		return false;
    }
		else{
			
		datalist = {
				"accession_no":$("#hidden_accessionNo").val().trim(),	
				"itemtype" : $("#itemtype").val().trim(),
				"bookTitle" : $("#bookTitle").val().trim(),
				"author" : $("#author").val().trim(),
				"category" : $("#category").val().trim(),
				"ddc" : $("#ddc").val().trim(),
				"currentstatus" : $("#currentstatus").val().trim(),
				"usertype":$("input[name='requested_by']:checked").val(),
				"imagePreview" :$("#imagePreview").val(),
				"shelfNo" : $("#shelfNo").val().trim(),
				"locationname" : $("#hiddenlocation").val(),
				"fromdate" : $("#Fromdate").val().trim(),
				"subscriberId" : $("#subscriberId").val(),
				"admissionno" : $("#admissionno").val().trim(),
				"hiddenclassId" : $("#hiddenclassId").val().trim(),
				"hiddendivissionId" : $("#hiddendivissionId").val().trim(),
				"todate" : $("#todate1").val().trim(),
				"userId" : $("#hiddenstuid").val().split("-")[1]
		},
		$.ajax({ 
			type : "POST",
			url : "LibraryMenu.html?method=insertBookIssueDetails",
			data : datalist,
			async : false,
			success : function(data) {
			
				var result = $.parseJSON(data);
				
				 if(result.status =="success"){
						$(".successmessagediv").show();
						$(".validateTips").text("Book Issued Successfully");
						setTimeout(function() {
							window.location.href="LibraryMenu.html?method=issues";
						},3000);
					}else if(result.status =="fail"){
						$(".errormessagediv").show();
						$(".validateTips").text("Book Issued Failed Please Try Again... ");
						setTimeout(function() {
							window.location.href="LibraryMenu.html?method=issues";
						},3000);
					}
					}
					});
		}
	});
	

	$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			$("#todate").datepicker("option","maxDate",selectedDate);
		}
	});
	
	$("#todate1").datepicker({
	     beforeShow: function () { 
	         $('#ui-datepicker-div').css('z-index',9999); 
	     },
	     dateFormat: 'dd-mm-yy',
	     beforeShowDay: function(date){
	          return [(date >= ($("#Fromdate").datepicker("getDate") 
	                          || new Date()))];
	     }
	});
	
	
	
	
	$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			$("#todate").datepicker("option","maxDate",selectedDate);
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
			
			//getClassList();
			var classname=$("#classname").val();
			//getSectionList(classname);
		});
		
		
		var userType=$("input[name='requested_by']:checked").val();
		
		
		$("#userType").autocomplete({
		source : function(request,response) { 
			
				if($("input[name='requested_by']:checked").val() =="Student")
				{
					$.ajax({

					url : "LibraryMenu.html?method=studentSearchbyadmissionNo",
					data : {
						searchTerm : request.term,
						locid : $("#locationname").val(),
						accid : $("#hidenaccyear").val()
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
		else if($("input[name='requested_by']:checked").val() =="Teacher")
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
						'searchTerm' : searchTerm.split("-")[0],
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
		
		$('#accession_no').on('focus', function(){
			if($('#accession_no').val()==""||$('#hidden_accessionNo').val()==null||$('#hidden_accessionNo').val()==undefined){
				$("#itemtype,#bookTitle,#author,#category,#ddc,#currentstatus,#shelfNo,#locationname").val("");
			}
		});
		
		
		$("#accession_no").autocomplete({
			source : function(request, response) {
				$("#hidden_accessionNo").val("");
				$("#hidden_teacherId").val("");
				var len=$("#accession_no").val().length;
				$.ajax({
					url : "LibraryMenu.html?method=getAccessionNoByIssue",
					data:{
						searchTerm : request.term,
						accessionId : $("#accession_no").val()
					},
					async:false,
					success:function(data) {
						var result=$.parseJSON(data);
						response($.map(result.jsonResponse,function(item){
							
							return {
								label : item.accessionNo,
								value : item.accessionNoId
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
					url:"LibraryMenu.html?method=getBookIssueDetailsByAccessionNo",
					data :accessionDetails,
					async : false,
					success:function(data){
						var result = $.parseJSON(data);
						var i=0;
						var len=result.accessionList.length;
						
						if(len > 0){
						for(i=0;i<len;i++){
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
						}
						}
						else{
							$('.successmessagediv').hide();
							$(".errormessagediv").show();
							$(".validateTips").text("Stock not Available");
							$(".errormessagediv").fadeOut(3000);
							$("#itemtype,#bookTitle,#author,#category,#ddc,#currentstatus,#shelfNo,#locationname").val("");
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
			var i=0;
			var len=result.accessionList.length;
			if(len >0){
			for(i=0;i<len;i++){
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
			var i=0;
			var len=result.accessionList.length;
			if(len >0){
			for(i=0;i<len;i++){
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
			var i=0;
			var len=result.accessionList.length;
			if(len >0){
			for(i=0;i<len;i++){
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
