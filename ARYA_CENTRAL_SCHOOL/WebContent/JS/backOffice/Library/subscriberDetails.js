$(document).ready(function(){
	
	$("#locationName").change(function(){
		$('.own-panel input[type=text],textarea').val("");
		 //$('.own-panel select').not(this).val("");
		$('#studentImageId1').attr('src','./images/profile_photo.png');
	});
	$("#otherUserContact").keypress(function(e){
		if(e.which !=8 && e.which!=0 && (e.which<48 || e.which>57)){
			return false;
		}
	});
	
	
	if($("#status").val()=="PAID" || $("#status").val()=="PAID"){
		$("#depositType").prop("disabled",true);
		$("#status").prop("readonly",true);
              }
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	
	//getLocation();
	$("#schoolName").val($("#hiddenmainloc").val());
	
	$('#schoolName option').each(function(){
	    if ($("#schoolName").val($("#hiddenmainloc").val())!="" || $("#schoolName").val($("#hiddenmainloc").val())!="all") {
	    	getLibraryLocation();
	    }
	});
	$("#depositType").val($("#hiddendeposittype").val());
	$("#status").val($("#hiddenstatus").val());
	$("#locationName").val($("#hiddenlocation").val());
	
	
	if($("#hiddenusetype").val()=="student"){
		$("#studentImageId1").attr('src',$("#hidenimage").val());
		$("#locationName").prop("disabled",true);
		$("#schoolName").prop("disabled",true);
		
		$("#back1").show();
		$("#updatesubscriber").show();
		$("#heading").hide();
		$("input[name='requested_by'][value='student']").prop('checked','checked');
		$("#teacher").hide();
		$("#others").hide();
		
		$("#saveid").hide();
		$(".teacher").hide();
		$(".others").hide();
		$(".othersAddr").hide();
		
		$("#adminssionNo").prop("readonly",true);
		$("#studentName").prop("readonly",true);
		$("#gender").prop("readonly",true);    
		$("#rollNo").prop("readonly",true);
		$("#className").prop("readonly",true);
		$("#divisionName").prop("readonly",true);
		$("#contactNumber").prop("readonly",true);
		$("#email").prop("readonly",true);
		$("#studentAddress").prop("readonly",true);
	}
	else if($("#hiddenusetype").val()=="teacher"){
		
		
		$("#teacherTeacher").attr('src',$("#hidenimage").val());
		$("#locationName").prop("disabled",true);
		$("#schoolName").prop("disabled",true);
		
		$("#back1").show();
		$("#saveid").hide();
		$("#updatesubscriber").show();
		$("#heading").hide();
		$("input[name='requested_by'][value='teacher']").prop('checked','checked');
		$("#others").hide();
		$("#student").hide();
		
		$(".student").hide();
		$(".others").hide();
		$(".othersAddr").hide();
		
		$("#subscriberNo").prop("readonly",true);
		$("#staffRegId").prop("readonly",true);      
		$("#abbrId").prop("readonly",true);
		$("#staffName").prop("readonly",true);
		$("#gender1").prop("readonly",true);
		$("#department").prop("readonly",true);
		$("#designation").prop("readonly",true);
		$("#mobileNo").prop("readonly",true);
		$("#email1").prop("readonly",true);
		$("#teacherAddress").attr("readonly", true);
	}
	else if($("#hiddenusetype").val()=="others"){
		$("#locationName").prop("disabled",true);
		$("#schoolName").prop("disabled",true);
		
		$("#saveid").hide();
		$("#back1").show();
		$("#updatesubscriber").show();
		$("#heading").hide();
		$("input[name='requested_by'][value='others']").prop('checked','checked');
		if($("#hiddenOthersType").val()=="female"){
			$("#male").hide();
			$("input[name='otherGender'][value='female']").prop('checked','checked');
			
		}else{
			$("#female").hide();
			$("input[name='otherGender'][value='male']").prop('checked','checked');
		}
		$("#student").hide();
		$("#teacher").hide();
		$(".teacher").hide();
		$(".student").hide();
		
		$("input[name='otherGender']").val();
		$("#subscriberNo").prop("readonly",true);   
	}
	
	$(".teacher").hide();
	$(".others").hide();
	$(".othersAddr").hide();
	$(".paymentByCash").hide();
	if(pageUrl !="LibraryMenu.html?method=subscribersDetails"){
		pageview($("input[name='requested_by']:checked"));
		
		$("#reset").hide();
		$("#update").show();
		$("#save").hide();
	}

	$("#paymentDate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			$("#paymentDate").datepicker("option","maxDate",selectedDate);
		}
	});	

	$("input[type='radio']").change(function(){
		pageview($(this));
		});
	
	$("#depositType").change(function(){
		$('div[class^="paymentBy"]').not($(".paymentBy"+$(this).val())).hide();
			$(".paymentBy"+$(this).val()).show();
			$(".paymentBy"+$(this).val()).find("input[type='text']").css({
				"border-color":"#000",
				"background-color":"#ffffe6"
			});
	});

	$("#staffRegId").change(function(){
		getStaffData();
		duplicateDataCheck();
	});
	
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	});
	
	$("#schoolName").change(function(){
		getLibraryLocation();
	});
	
	$("#locationName").click(function(){
		if($("#schoolName").val()==""){
			showError("#schoolName","Select School Name");
		}
	});
	
	//-------------save operation----------------
	$("#save").click(function(){
		
	
		var schoolName = $("#schoolName").val();
		var locationName = $("#locationName").val();
		var accyear = $("#hacademicyaer").val();
		var classId = $("#classId").val();
		var divisionId = $("#divisionId").val();
		var teacher = $("#teacher").val();
		var student = $("#student").val();
		var others = $("#others").val();
		var subscriberNo = $("#subscriberNo").val();
		var adminssionNo = $("#adminssionNo").val();
		var studentIdHidden = $("#studentIdHidden").val();
		
		var staffRegId = $("#staffRegId").val();
		var staffId = $("#staffId").val();
		var deptId = $("#deptId").val();
		var desigId = $("#desigId").val();

		var depositType = $("#depositType").val();
		var enterAmount = $("#enterAmount").val();
		var cardNo = $("#cardNo").val();
		var checkNo = $("#checkNo").val();
		
		var status = $("#status").val();
		var paymentDate = $("#paymentDate").val();
		
		var otherUserAddr = $("#otherUserAddr").val();
		var otherUserName = $("#otherUserName").val();
		var otherUserGender = $("input[name='otherGender']:checked").val();
		var otherUserContact = $("#otherUserContact").val();
		var otherUserEmail = $("#otherUserEmail").val();
		var cardNoAmount = $("#cardNoAmount").val(); 		
		var checkNoAmount = $("#checkNoAmount").val();
		
		if (schoolName == "" || schoolName == null) {
			showError("#schoolName","Enter School  Name ");
			return false;
		} 
		
		else if (locationName == "" || locationName == null) {
			showError("#locationName","Enter Library Location ");
			return false;
		} 
		else
		if(subscriberNo=="" || subscriberNo==null){
			showError("#subscriberNo","Enter Subscriber Number");
			return false;
		}
		
	
		else if ($("input[name='requested_by']:checked").val()=="teacher" &&  depositType == "" ) {
			showError("#depositType","Select Payment Mode");
			return false;
		} 
		
		else if($("input[name='requested_by']:checked").val()=="teacher" && $("#depositType").val()=="Cash" && $("#enterAmount").val()==""){
			showError("#enterAmount","Enter The Valid Amount");
			return false;
			}
			else if($("input[name='requested_by']:checked").val()=="teacher" && $("#depositType").val()=="Card" && $("#cardNo").val()==""){
			showError("#cardNo","Enter Valid Card Number");
			return false;
			}
			else if($("input[name='requested_by']:checked").val()=="teacher" && $("#depositType").val()=="Check" && $("#Check").val()==""){
			showError("#Check","Enter Valid Check Number");
			return false;
			}
		
			else if($("input[name='requested_by']:checked").val()=="teacher" && $("#depositType").val()=="Check" && $("#checkNoAmount").val()==""){
				showError("#checkNoAmount","Enter Valid Amount to be paid by Check");
				return false;
				}
			else if($("input[name='requested_by']:checked").val()=="teacher" && $("#depositType").val()=="Card" && $("#cardNoAmount").val()==""){
				showError("#cardNoAmount","Enter Valid Amount to be paid by Card");
				return false;
				}
		
		
		
		else if ($("input[name='requested_by']:checked").val()=="teacher" &&  staffRegId == "" ) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Valid Staff ID ");
			document.getElementById("staffRegId").style.border = "1px solid #AF2C2C";
			document.getElementById("staffRegId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		
		else if ($("input[name='requested_by']:checked").val()=="student" &&  adminssionNo == "" ) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Admission Number ");
			document.getElementById("adminssionNo").style.border = "1px solid #AF2C2C";
			document.getElementById("adminssionNo").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		else if ($("input[name='requested_by']:checked").val()=="student" &&  depositType == "" ) {
			showError("#depositType","Select Payment Mode");
			return false;
		} 
		
		else if($("input[name='requested_by']:checked").val()=="student" && $("#depositType").val()=="Cash" && $("#enterAmount").val()==""){
			showError("#enterAmount","Enter The Valid Amount");
			return false;
			}
			else if($("input[name='requested_by']:checked").val()=="student" && $("#depositType").val()=="Card" && $("#cardNo").val()==""){
			showError("#cardNo","Enter Valid Card Number");
			return false;
			}
			else if($("input[name='requested_by']:checked").val()=="student" && $("#depositType").val()=="Check" && $("#Check").val()==""){
			showError("#Check","Enter Valid Check Number");
			return false;
			}
		
			else if($("input[name='requested_by']:checked").val()=="student" && $("#depositType").val()=="Check" && $("#checkNoAmount").val()==""){
				showError("#checkNoAmount","Enter Valid Amount to be paid by Check");
				return false;
				}
			else if($("input[name='requested_by']:checked").val()=="student" && $("#depositType").val()=="Card" && $("#cardNoAmount").val()==""){
				showError("#cardNoAmount","Enter Valid Amount to be paid by Card");
				return false;
				}
		
		else if($("input[name='requested_by']:checked").val()=="others" && otherUserName == "" ){
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Subscriber  Name ");
			document.getElementById("otherUserName").style.border = "1px solid #AF2C2C";
			document.getElementById("otherUserName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		
		else if($("input[name='requested_by']:checked").val()=="others" && $(".Gender:checked").length==0){
			showError(".Gender","Select Subscriber Gender");
			return false;
		}
		
		else if($("input[name='requested_by']:checked").val()=="others" && (otherUserContact.length < 10 ||otherUserContact.length >10)){
			$(".errormessagediv").show();
			$(".validateTips").text("Enter 10 digit Contact Number");
			document.getElementById("otherUserContact").style.border = "1px solid #AF2C2C";
			document.getElementById("otherUserContact").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		
		else if($("input[name='requested_by']:checked").val()=="others" && depositType == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Select the Payment Mode");
			document.getElementById("depositType").style.border = "1px solid #AF2C2C";
			document.getElementById("depositType").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		
		else if($("input[name='requested_by']:checked").val()=="others" && $("#depositType").val()=="Cash" && $("#enterAmount").val()==""){
		showError("#enterAmount","Enter The Valid Amount");
		return false;
		}
		else if($("input[name='requested_by']:checked").val()=="others" && $("#depositType").val()=="Card" && $("#cardNo").val()==""){
		showError("#cardNo","Enter Valid Card Number");
		return false;
		}
		else if($("input[name='requested_by']:checked").val()=="others" && $("#depositType").val()=="Check" && $("#Check").val()==""){
		showError("#Check","Enter Valid Check Number");
		return false;
		}
		
		else if($("input[name='requested_by']:checked").val()=="others" && $("#depositType").val()=="Check" && $("#checkNoAmount").val()==""){
			showError("#checkNoAmount","Enter Valid Amount to be paid by Check");
			return false;
			}
		else if($("input[name='requested_by']:checked").val()=="others" && $("#depositType").val()=="Card" && $("#cardNoAmount").val()==""){
			showError("#cardNoAmount","Enter Valid Amount to be paid by Card");
			return false;
			}
		
		else if($("input[name='requested_by']:checked").val()=="others" && otherUserContact == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Subscriber  Contact Number ");
			document.getElementById("otherUserContact").style.border = "1px solid #AF2C2C";
			document.getElementById("otherUserContact").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(status =="" || status == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Subscriber Status ");
			document.getElementById("status").style.border = "1px solid #AF2C2C";
			document.getElementById("status").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(paymentDate =="" || paymentDate == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Payment Date ");
			document.getElementById("paymentDate").style.border = "1px solid #AF2C2C";
			document.getElementById("paymentDate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		 
		else if($("input[name='requested_by']:checked").val()=="others" &&($("textarea#otherUserAddr").val().length ==0)){
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Subscriber Address");
			document.getElementById("otherUserAddr").style.border = "1px solid #AF2C2C";
			document.getElementById("otherUserAddr").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		
		datalist ={"locationName":locationName,
					"accyear":accyear,
					
					"staffId":staffId,  
					"deptId": deptId,
					"desigId":desigId,
					"classId":classId,
					"divisionId":divisionId,
					"studentIdHidden":studentIdHidden,
				
					"teacher":teacher,
					"student":student,
					"others":others,
					
					"subscriberNo":subscriberNo,
					"adminssionNo":adminssionNo,
				
					"suscriberType":$("input[name='requested_by']:checked").val(),
					"staffRegId":staffRegId,
					
					"depositType":depositType,
					"enterAmount":enterAmount,
					"cardNo":cardNo,
					"checkNo":checkNo,
					
					"status":status,
					"paymentDate":paymentDate,
					
					"otherUserAddr":otherUserAddr,
					"otherUserName":otherUserName,
					"otherUserGender":otherUserGender,
					"otherUserContact":otherUserContact,
					"otherUserEmail":otherUserEmail,
					"cardNoAmount":cardNoAmount,
					"checkNoAmount":checkNoAmount,
		};
		
		$.ajax({
			type:'POST',
			url:"LibraryMenu.html?method=saveSubscriberDetails",
			data:datalist,
			async:false,
			success: function(data){
				var result =$.parseJSON(data);
				
				if(result.resultData=="true"){
		 			$('.errormessagediv').hide();
					$('.successmessagediv').show();
					$(".validateTips").text("Adding Record Progressing...");
					$(".successmessagediv").delay(3000).fadeOut();
					setTimeout(function() {

					location.reload();
				}, 2000);
			 }	
				else if(result.resultData=="dupSubNo"){
					$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Subscriber Number Assigned Already");
					 document.getElementById("subscriberNo").style.border = "1px solid #AF2C2C";
					 document.getElementById("subscriberNo").style.backgroundColor = "#FFF7F7";
					$(".errormessagediv").delay(3000).fadeOut();
				}
				else{
		 			$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Fail to Add Record...");
					$(".errormessagediv").delay(3000).fadeOut();
		 		 }
			}
		});
		
	});
	
	/*-----------------Update------------*/
	$("#update").click(function(){
		
		var locationName = $("#locationName").val();
		var accyear = $("#hacademicyaer").val();

		var teacher = $("#teacher").val();
		var student = $("#student").val();
		var others = $("#others").val();
		
		var subscriberNo = $("#subscriberNo").val();
		var subscriberId = $("#subscriberId").val();
		var staffRegId = $("#staffRegId").val();
		var staffId = $("#staffId").val();
		var depositType = $("#depositType").val();
		var enterAmount = $("#enterAmount").val();
		var cardNo = $("#cardNo").val();
		var checkNo = $("#checkNo").val();
		var status = $("#status").val();
		var paymentDate = $("#paymentDate").val();
		var otherUserAddr = $("#otherUserAddr").val();
		var otherUserName = $("#otherUserName").val();
		var otherUserContact = $("#otherUserContact").val();
		var otherUserEmail = $("#otherUserEmail").val();
		var otherUserGender = $("#otherUserGender").val();

		if (locationName == "" || locationName == null) {
			showError("#locationName","Enter Location Name");
			return false;
		} 
		
		else if (subscriberNo == "" || subscriberNo == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Subscriber Number ");
			document.getElementById("subscriberNo").style.border = "1px solid #AF2C2C";
			document.getElementById("subscriberNo").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		
		else if (paymentDate == "" || paymentDate == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Dates ");
			document.getElementById("paymentDate").style.border = "1px solid #AF2C2C";
			document.getElementById("paymentDate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		else if ($("input[name='requested_by']:checked").val()=="teacher" &&  staffRegId == "" ) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Valid Staff ID ");
			document.getElementById("staffRegId").style.border = "1px solid #AF2C2C";
			document.getElementById("staffRegId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		else if ($("input[name='requested_by']:checked").val()=="teacher" &&  staffRegId == "" ) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Valid Staff ID ");
			document.getElementById("staffRegId").style.border = "1px solid #AF2C2C";
			document.getElementById("staffRegId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		else if($("input[name='requested_by']:checked").val()=="others" &&  otherUserName == ""){
			showError("#otherUserName","Enter Subscriber Name");
			return false;
		} 
		
		else if($("input[name='requested_by']:checked").val()=="others" &&  otherUserGender == ""){
			showError("#otherUserGender","Select Subscriber Gender");
			return false;
		} 
		
		else if($("input[name='requested_by']:checked").val()=="others" &&  otherUserContact == ""){
			showError("#otherUserContact","Enter Contact Number");
			return false;
		} 
		
		else if($("input[name='requested_by']:checked").val()=="others" &&  depositType == ""){
			showError("#depositType","Select Deposit Type");
			return false;
		} 
		else if($("input[name='requested_by']:checked").val()=="others" &&  paymentDate == ""){
			showError("#paymentDate","Enter Payment Date");
			return false;
		} 
		else if($("input[name='requested_by']:checked").val()=="others" &&  otherUserAddr == ""){
			showError("#otherUserAddr","Enter Subscriber Address");
			return false;
		} 
		datalist ={
					"locationName":locationName,
					"accyear":accyear,
					"teacher":teacher,
					"student":student,
					"others":others,
					"subscriberId":subscriberId,
					"subscriberNo":subscriberNo,
					"suscriberType":$("input[name='requested_by']:checked").val(),
					"staffRegId":staffRegId,
					"depositType":depositType,
					"enterAmount":enterAmount,
					"cardNo":cardNo,
					"checkNo":checkNo,
					"status":status,
					"paymentDate":paymentDate,
					"otherUserAddr":otherUserAddr,
					"otherUserName":otherUserName,
					"otherUserContact":otherUserContact,
					"otherUserEmail":otherUserEmail,
					"otherUserGender":otherUserGender,
		};
		$.ajax({
			type:'POST',
			url:"LibraryMenu.html?method=updateSubscriberDetails",
			data:datalist,
			async:false,
			success: function(data){
				var result =$.parseJSON(data);
				
				if(result.resultData=="true"){
		 			$('.errormessagediv').hide();
					$('.successmessagediv').show();
					$(".validateTips").text("Update Record Progressing...");
					$(".successmessagediv").show();
					setTimeout(function() {
						window.location.href ="LibraryMenu.html?method=SearchSubscriberDetails";
					}, 3000);
			 }	
				else{
		 			$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Fail to Updates Record...");
					$(".errormessagediv").delay(3000).fadeOut();
		 		 }
			}
		});
	});
	$("#enterAmount,#cardNo,#checkNo").keypress(function (e) { //accept only numbers
	    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	     return false;
	    }
	});
	
	$("#adminssionNo").autocomplete({
		source : function(request, response) {
			var  locid = $("#locationName").val();
			$.ajax({
				url : "LibraryMenu.html?method=studentSearchbyadmissionNoForlibrary",
				data : {
					searchTerm : request.term,
					"locid" :locid,
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					response($.map(	result.jsonResponse,function(item) {
						return {
							label : item.admissionNo,
							value : item.admissionNo,
						}
					}));
				}
			});
		},
		select : function(event, ui) {
			var searchTerm = ui.item.value;
			studentDetails = {
					'searchTerm' : searchTerm
			};
			$("#adminssionNo").val(ui.item.label);
			getStudentData();
			duplicateDataCheck();
			return false;
		}
	});
	
$("#staffRegId").autocomplete({
		source : function(request, response) {
			var loc = $("#locationName").val();
			$.ajax({
				url : "LibraryMenu.html?method=getStaffRegId",
				data : {
					searchTerm : request.term,
					"loc" :loc,
				}, 
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					response($.map(	result.jsonResponse,function(item) {
						return {
							label : item.teacherId,
							value : item.teacherId,
						}
					}));
				}
			});
		},
		select : function(event, ui) {
			var searchTerm = ui.item.value;
			studentDetails = {
					'searchTerm' : searchTerm
			};
			$("#staffRegId").val(ui.item.label);
			getStaffData();
			return false;
		}
	});
});	
	


function getStudentData(){
	
	var admission = $("#adminssionNo").val();
	var location =$("#locationName").val();
		datalist={
				"academicYear":$("#hacademicyaer").val(),
				"location":location,
				"adminssionNo":admission,
		};
		$.ajax({
			type:'POST',
			url :"LibraryMenu.html?method=getStudentData",
			data: datalist,
			async :false,
			success :function(data)
			{
				var result = $.parseJSON(data);
				if(result.studentData.length>0){
					$("#studentName").val(result.studentData[0].studentName);
					$("#studentIdHidden").val(result.studentData[0].studentIdHidden);
					$("#rollNo").val(result.studentData[0].rollNumber);
					//$("#gender").val(result.studentData[0].className);
					$("#className").val(result.studentData[0].className);
					$("#classId").val(result.studentData[0].classId);
					$("#divisionName").val(result.studentData[0].sectionName);
					$("#divisionId").val(result.studentData[0].sectionId);
					$("#gender").val(result.studentData[0].gender);
					$("#contactNumber").val(result.studentData[0].phoneNumber);
					$("#email").val(result.studentData[0].email);
					$("#studentAddress").val(result.studentData[0].presentAddress);
					
					$("#studentImageId1").attr("src","./"+result.studentData[0].imageUrl);
				}
			}
	});	
	}
 function getStaffData(){
	 var staffRegId = $("#staffRegId").val();
	 var locId = $("#locationName").val();
	 
	 datalist={
			 "staffRegId":staffRegId,
			 "locId":locId,
	 };
	 
	 $.ajax({
		type:'POST',
		url:"LibraryMenu.html?method=getStaffData",
		data: datalist,
	 	async:false,
	 	success: function(data){
	 		
	 		var result= $.parseJSON(data);
	 		if(result.teacherData.length){
	 			$("#abbrId").val(result.teacherData[0].abbrId);
	 			$("#staffName").val(result.teacherData[0].teacherName);
	 			$("#gender1").val(result.teacherData[0].gender);
	 			$("#department").val(result.teacherData[0].department);
	 			$("#designation").val(result.teacherData[0].designation);
	 			$("#teacherImage").val(result.teacherData[0].imageUrl);
	 			$("#mobileNo").val(result.teacherData[0].phoneNumber);
	 			$("#email1").val(result.teacherData[0].email);
	 			$("#teacherAddress").val(result.teacherData[0].presentAddress);
	 			$("#staffId").val(result.teacherData[0].staffID);
	 			$("#deptId").val(result.teacherData[0].departmentId);
	 			$("#desigId").val(result.teacherData[0].desigantionId);
	 			$("#teacherTeacher").attr("src","./"+result.teacherData[0].imageUrl);
	 		}
	 		
	 	}
	 });
 }
 function pageview(pointer){

	 if(pointer.val()=="teacher"){
			$(".teacher").show();
			$(".student").hide();
			$(".others").hide();
			$(".othersAddr").hide();
		}
		else if(pointer.val()=="student"){
			$(".student").show();
			$(".teacher").hide();
			$(".others").hide();
			$(".othersAddr").hide();
		}
			else if(pointer.val()=="others"){
			$(".others").show();
			$(".teacher").hide();
			$(".student").hide();
			$(".othersAddr").show();
		}
 }

 function duplicateDataCheck(){
	 var locationName = $("#locationName").val();
	 var accyear = $("#hacademicyaer").val();
	 var staffId =$("#staffId").val();
	 var suscriberType =$("input[name='requested_by']:checked").val(),
	 
	 datalist={
			 "studentIdHidden": $("#studentIdHidden").val(),
			 "suscriberType":suscriberType,
			 "locationName":locationName,
			 "accyear":accyear,
			 "staffId":staffId,
	 };
	 
	 $.ajax({
		 type:'POST',
		 url:"LibraryMenu.html?method=duplicateDataCheck",
		 data:datalist,
		async: false,
		success:function(data){
			var result = $.parseJSON(data);
			if(result.status=="dupStudent"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				showError("#adminssionNo","This Student Already Subscribed !");
				$("#save").hide();
				$("#reset").hide();
				$("input[name='requested_by']").change(function(){
					$("#save").show();
					$("#reset").show();
				});
				return false;
			}
			else if(result.status=="dupTeacher"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
					showError("#staffRegId","This Staff Already Subscribed !");
					$("#save").hide();
					$("#reset").hide();
					$("input[name='requested_by']").change(function(){
						$("#save").show();
						$("#reset").show();
					});
					return false;
		 }	
			else if(result.status=="noDup"){
				$("#save").show();
				$("#reset").show();
			}
		}
	 });
	 
 }
 
 function hideError(id){
		$(id).css({
			"border":"1px solid #ccc",
			"background-color":"#fff"
			});
	}
 
 function showError(id,errorMessage){
		$(id).css({
			"border":"1px solid #AF2C2C",
			"background-color":"#FFF7F7"
		});
		$(".form-control").not(id).css({
			"border":"1px solid #ccc",
			"background-color":"#fff"
		});
		$('.successmessagediv').hide();
		$(".errormessagediv").show();
		$(".validateTips").text(errorMessage);
		$(".errormessagediv").delay(3000).fadeOut();
	}
 
 
function getLibraryLocation(){
	 var id = $("#schoolName").val();
	 datalist={
			 "id":id,
	 };
	 $.ajax({
		type:'POST',
		url:"LibraryMenu.html?method=getLibraryLocationBySchool",
		data: datalist,
	 	async:false,
	 	success: function(data){
	 		var result = $.parseJSON(data);
			$("#locationName").empty();
			$("#locationName").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
			$("#locationName").append("<option value='"+result.data[i].libraryLocationName+"'>" 
					+result.data[i].libraryLocationId+"</option>");
			}
	 		}
	 });
}	
 
 
 
 
 
 
 
 
 
 
 
 
 
function HideError(){
	document.getElementById("subscriberNo").style.backgroundColor = "#fff";
	document.getElementById("subscriberNo").style.border = "1px solid #ccc";
	
	document.getElementById("adminssionNo").style.backgroundColor= "#fff";
	document.getElementById("adminssionNo").style.border = "1px solid #ccc";	
}




    /*--------------Update operation----------------*/



