$(document).ready(function(){
	
	$(".clearfix input[type=text]").prop('readonly', true);
	$("#studentAddress").prop('readonly', true);
	$("#paymentDate").attr('readOnly' , 'true' ); 
	
	getSubscriberNumber();
	showBlockListedData();

	//$(".teacher").hide();
	$(".student").hide();
	$(".others").hide();
	$(".othersAddr").hide();
	$(".paymentByCash").hide();
	
	$("input[type='radio']").click(function(){
		 $("input:text").val("");
	/*	 $('select').val("");
		 $('select').not("#locationName").val("all");*/
		 $('textarea').val("");
		 
		if($(this).val()=="teacher"){
			
			$(".teacher").show();
			$(".student").hide();
			$(".others").hide();
			$(".othersAddr").hide();
			$(".tab ul.nav.nav-tabs li").removeClass("active");
			$(".tab ul.nav.nav-tabs li").find("a[id='staffTab']").parent("li").addClass("active");
			staffFunction();
		}
		else if($(this).val()=="student"){
		
			$(".student").show();
			$(".teacher").hide();
			$(".others").hide();
			$(".othersAddr").hide();
			$(".tab ul.nav.nav-tabs li").removeClass("active");
			$(".tab ul.nav.nav-tabs li").find("a[id='studentsTab']").parent("li").addClass("active");
			studentFunction();
		}
			else if($(this).val()=="others"){
			$(".others").show();
			$(".teacher").hide();
			$(".student").hide();
			$(".othersAddr").show();
			$(".tab ul.nav.nav-tabs li").removeClass("active");
			$(".tab ul.nav.nav-tabs li").find("a[id='othersTab']").parent("li").addClass("active");
			otherFunction();
			showBlockListedData();
		}
		});
	
	$("#depositType").change(function(){
		$('div[class^="paymentBy"]').not($(".paymentBy"+$(this).val())).hide();
			$(".paymentBy"+$(this).val()).show();
			$(".paymentBy"+$(this).val()).find("input[type='text']").css({
				"border-color":"#000",
				"background-color":"#ffffe6"
			});
	});
	$("#locationName").change(function(){
		getSubscriberNumber();
		showBlockListedData();
		if($("input[name='requested_by']:checked").val()=="teacher"){
			$(".tab ul.nav.nav-tabs li").removeClass("active");
			$(".tab ul.nav.nav-tabs li").find("a[id='staffTab']").parent("li").addClass("active");
			staffFunction();
	}
		else if($("input[name='requested_by']:checked").val()=="student"){
			$(".tab ul.nav.nav-tabs li").removeClass("active");
			$(".tab ul.nav.nav-tabs li").find("a[id='studentsTab']").parent("li").addClass("active");
			studentFunction();
		}
		else{
			$(".tab ul.nav.nav-tabs li").removeClass("active");
			$(".tab ul.nav.nav-tabs li").find("a[id='othersTab']").parent("li").addClass("active");
			otherFunction();
		}
		
	});
	$("input[type='radio']").click(function(){
		getSubscriberNumber();
		showBlockListedData();
	});
	$("#subscriberNo").change(function(){
		showBlockListedData();
	});
	
	
$("#adminssionNo").change(function(){
		getStudentData();
	});
	$("#staffRegId").change(function(){
		getStaffData();
	});
	
	
$("#block").click(function(){
	var subscriberNo = $("#subscriberNo").val();
	var schoolName = $("#schoolName").val();
	if(schoolName=="" || schoolName==undefined){
		showError("#schoolName","Select School Name");
		return false;
	}
	else if($("#locationName").val()==""){
		showError("#locationName","Select Library Location");
		return false;
	}
	else if($("#subscriberNo").val()=="all"){
		showError("#subscriberNo","Select Subscriber Number");
		return false;
	}

	datalist ={
			"subscriberNo" : subscriberNo,
			};
	$.ajax({
		type:'POST',
		url:"LibraryMenu.html?method=blockTheSubscriber",
		data: datalist,
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
		if(result.status=="true"){ 
			$(".errormessagediv").hide();
			$(".successmessagediv").show();
			$(".validateTips").text("User Block progressing");
		}
		else{
			$(".errormessagediv").show();
			$(".successmessagediv").hide();
			$(".validateTips").text("Fail to Block ");
			$(".errormessagediv").delay(3000),fadeOut();
		}
		setTimeout(function() {
			location.reload();
		}, 2000);
		}
	});
});

//------------------- listing tables------------------
$('#othersTab').click(function(){
	otherFunction();
});


$("#studentsTab").click(function(){
	studentFunction();
});

$("#staffTab").click(function(){
	staffFunction();
});
$("#studentsTab").click(function(){
	studentFunction();
});
$("#othersTab").click(function(){
	otherFunction()
});


$("#schoolName").change(function(){
	getLibraryLocation();
});

$("#locationName").click(function(){
	if($("#schoolName").val()==""){
		showError("#schoolName","Select School Name");
	}
	else{
		document.getElementById("schoolName").style.backgroundColor = "#fff";
		document.getElementById("schoolName").style.border = "1px solid #ccc";
	}
});

});// ---end doc

function otherFunction(){
	 var location = $("#locationName").val();
	 if(location == "----------Select----------"){
		 $(".errormessagediv").show();
		 $(".validateTips").text("Please Select Location");
		 document.getElementById("locationName").style.border = "1px solid #AF2C2C";
		 document.getElementById("locationName").style.backgroundColor = "#FFF7F7";
	 return false;
	 }
	 else{
	$("#studentTable").hide();
	$("#staffTable").hide();
	$("#othersTable").show();
	$('#othersTable').empty();
	$("#othersTable").append("<table class='allstudent' id='otherblck' width='100%'>"
			+"<center><thead><tr><th>SI No</th>"+
			"<th> Subscriber Number</th>" +
			"<th>Subscriber Name</th>" +
			"<th>Contact Number</th>"+
			"<th>Email ID</th>"+
			"</center></tr></thead><tbody></tbody>" +
			"</table>"
	);
	
$.ajax({
		type:'POST',
		url:"LibraryMenu.html?method=getBlockListedOtherData",
		data:{
			"location" :location,
			},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#otherblck tbody").empty();
			if(result.data.length > 0){
			for(var i=0;i<result.data.length;i++){
				$("#otherblck tbody").append("<tr class='"+result.data[i].subscriberNo+"'>"
				+"<td>"+(i+1)+"</td>"
				+"<td>"+result.data[i].subscriberNo+"</td>"
				+"<td>"+result.data[i].otherUserName+"</td>"
				+"<td>"+result.data[i].otherUserContact+"</td>"
				+"<td>"+result.data[i].otherUserEmail+"</td>"
				+"<td><span id='"+result.data[i].subscriberNo+"' class='unblock buttons'>Unblock Subscriber</span></td>"
				+"</tr>"
				);
			}
			$("#otherblck tbody td .unblock").click(function(){
				unblockOtherSubscriber($(this).attr("id"));
			});
			}
			else{
				$("#otherblck tbody").append("<tr><td colspan='5'>No Record Found</td></tr>");
			}
		}
	});
	 }
}
function staffFunction(){
	var location = $("#locationName").val();
	 if(location == "----------Select----------"){
		 $(".errormessagediv").show();
		 $(".validateTips").text("Please Select Location");
		 document.getElementById("locationName").style.border = "1px solid #AF2C2C";
		 document.getElementById("locationName").style.backgroundColor = "#FFF7F7";
	 return false;
	 }
	 else{
	$("#othersTable").hide();
	$("#studentTable").hide();
	$("#staffTable").show();
	
	$("#staffTable").empty();
	$("#staffTable").append("<table class='allstudent' id='staff' width='100%'" +">"
			+"<center><thead><tr><th>SI No</th>"
			+"<th>Subcriber Number</th>"
		    +"<th>Satff Name</th>"
		    +"<th>Department</th>"
		    +"<th>Designation</th>"
			+"</center></tr></thead><tbody></tbody>" 
		    +"</table>");
	
	$.ajax({
		type:'POST',
		url:"LibraryMenu.html?method=getBlockListedStaffData",
		data:{
		"location" :location,
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#staff tbody").empty();
			if(result.data.length>0){
			for(var i=0;i<result.data.length;i++){
				$("#staff tbody").append("<tr class='"+result.data[i].subscriberNo+"'>"
				+"<td>"+(i+1)+"</td>"
				+"<td>"+result.data[i].subscriberNo+"</td>"
				+"<td>"+result.data[i].staffName+"</td>"
				+"<td>"+result.data[i].department+"</td>"
				+"<td>"+result.data[i].designation+"</td>"
				+"<td><span id='"+result.data[i].subscriberNo+"' class='unblock buttons'>Unblock Subscriber</span></td>"
				+"</tr>"
				);
			}
			$("#staff tbody td .unblock").click(function(){
				unblockOtherSubscriber($(this).attr("id"));
			});
			}
			else{
				$("#staff tbody").append("<tr><td colspan='5'>No Record Found</td></tr>");
			}
			}
			});
	 }

}

function studentFunction(){
	 var location = $("#locationName").val();
	 if(location == "----------Select----------"){
		 $(".errormessagediv").show();
		 $(".validateTips").text("Please Select Location");
		 document.getElementById("locationName").style.border = "1px solid #AF2C2C";
		 document.getElementById("locationName").style.backgroundColor = "#FFF7F7";
	 return false;
	 }
	 else{
	$("#othersTable").hide();
	$("#staffTable").hide();
	$("#studentTable").show();
	$("#studentTable").empty();
	$("#studentTable").append("<table class='allstudent' id='student' width='100%'" +">"
			+"<center><thead><tr><th>SI No</th>"+
			"<th>Subscriber Number</th>" +
			"<th>Admission Number</th>" +
			"<th>Student Name</th>"+
			"<th>Class</th>"+
			"<th>Division</th>"+
			"</center></tr></thead><tbody></tbody>" +
			"</table>"
			);
	$.ajax({
		type:'POST',
		url:"LibraryMenu.html?method=getBlockListedStudentData",
		data:{
			"location" :location,
			},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#student tbody").empty();
			if(result.data.length>0){
			for(var i=0;i<result.data.length;i++){
				$("#student tbody").append("<tr class='"+result.data[i].subscriberNo+"'>"
				+"<td>"+(i+1)+"</td>"
				+"<td>"+result.data[i].subscriberNo+"</td>"
				+"<td>"+result.data[i].adminssionNo+"</td>"
				+"<td>"+result.data[i].studentName+"</td>"
				+"<td>"+result.data[i].className+"</td>"
				+"<td>"+result.data[i].sectionName+"</td>"
				+"<td><span id='"+result.data[i].subscriberNo+"' class='unblock buttons'>Unblock Subscriber</span></td>"
				+"</tr>"
				);
			}
			$("#student tbody td .unblock").click(function(){
				unblockOtherSubscriber($(this).attr("id"));
			});
			}
			else {
				$("#student tbody").append("<tr><td colspan='6'>No Records Found</td></tr>");
				}
			
		}
	});
	 }
}
function getSubscriberNumber(){
	datalist ={
		"location" : $("#locationName").val(),
		"suscriberType":$("input[name='requested_by']:checked").val(),
	};
	$.ajax({
		type : 'POST',
		url :"LibraryMenu.html?method=getSubscriberNumber",
		data : datalist,
		async : false,
		success:function(data){
		
			var result =$.parseJSON(data);
			
			$("#subscriberNo").empty();
			$('#subscriberNo').append('<option value="all">----------Select----------</option>');
			for ( var j = 0; j < result.subscriberNumber.length; j++) {
				$('#subscriberNo').append('<option value="'+ result.subscriberNumber[j].otherId+'">'
						+ result.subscriberNumber[j].subscriberNo+ '</option>');
			}
			}
	});
}
function showBlockListedData(){
	var locationName = $("#locationName").val();
	datalist ={
			"locationName":locationName,
			"subscriberNo":$("#subscriberNo").val(),
			"suscriberType":$("input[name='requested_by']:checked").val(),
		};
	$.ajax({
		type:'POST',
		url:"LibraryMenu.html?method=showBlockListedData", //for other
		data:datalist,
		async:false,
		success:function(data){
			var result =$.parseJSON(data);
			if(result.data.length>0){
				$("#otherUserName").val(result.data[0].otherUserName);
				$("#otherUserGender").val(result.data[0].otherUserGender);
				$("#otherUserContact").val(result.data[0].otherUserContact);
				$("#otherUserEmail").val(result.data[0].otherUserEmail);
				$("#otherUserAddr").val(result.data[0].otherUserAddr);
				$("#paymentDate").val(result.data[0].paymentDate);
				$("#status").val(result.data[0].status);
				$("#depositType").val(result.data[0].depositType);
				$("#enterAmount").val(result.data[0].status);
				$("#depositType").val(result.data[0].enterAmount);
				$("#cardNo").val(result.data[0].cardNo);
				$("#checkNo").val(result.data[0].checkNo);
				//staff data display based on Subscriber number
				$("#staffRegId").val(result.data[0].regid);
				$("#abbrId").val(result.data[0].abbrId);
				$("#staffName").val(result.data[0].staffName);
				$("#gender1").val(result.data[0].gender);
				$("#department").val(result.data[0].department);
				$("#designation").val(result.data[0].designation);
				$("#mobileNo").val(result.data[0].phoneNumber);
				$("#email1").val(result.data[0].email);
				$("#teacherAddress").val(result.data[0].presentAddress);
				$("#paymentDate").val(result.data[0].paymentDate);
				$("#status").val(result.data[0].status);
				$("#staffId").val(result.data[0].staffID);
				//$("#imagePreviewstff").attr("src","./"+result.data[0].imageUrl);
				$("#imagePreviewstff").attr("src","./"+result.data[0].imageUrl);
				//student data
				$("#adminssionNo").val(result.data[0].adminssionNo);
				$("#studentName").val(result.data[0].studentName);
				$("#gender").val(result.data[0].gender);
				$("#rollNo").val(result.data[0].rollNumber);
				$("#className").val(result.data[0].className);
				$("#divisionName").val(result.data[0].sectionName);
				$("#contactNumber").val(result.data[0].phoneNumber);
				$("#email").val(result.data[0].email);
				$("#studentAddress").val(result.data[0].presentAddress);
				$("#paymentDate").val(result.data[0].paymentDate);
				$("#status").val(result.data[0].status);
				$("#imagePreview").attr("src","./"+result.data[0].imageUrl);
			}
		}
	});
}

function getStudentData(){
	
	var admission = $("#adminssionNo").val();
		datalist={
				"academicYear":$("#hacademicyaer").val(),
				"adminssionNo":admission
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
				//	$("#studentImageId1").val(result.studentData[0].imageUrl);
				}
				else{
		 			$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Please Enter Valid Admission Number !");
					document.getElementById("adminssionNo").style.border = "1px solid #AF2C2C";
					document.getElementById("adminssionNo").style.backgroundColor = "#FFF7F7";
					$(".errormessagediv").delay(3000).fadeOut();
					//$("#admissionNo").val("");
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
	 			
	 		}else{
	 			$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Please Enter Valid Staff ID  !");
				document.getElementById("staffId").style.border = "1px solid #AF2C2C";
				document.getElementById("staffId").style.backgroundColor = "#FFF7F7";
				$(".errormessagediv").delay(3000).fadeOut();
				//$("#staffId").val("");
	 			
	 		}
	 	}
	 });
	 
 }
 
function unblockOtherSubscriber(id){
		$("#dialog").dialog({
			autoOpen: false,
			modal: true,
			title:'	Block Listed Subscriber',
			buttons : {
				"Yes" : function() {
					$.ajax({
						type:'POST',
						url:"LibraryMenu.html?method=unblockSubscriber",
						data:{"id":id,},
						async:false,
						success:function(data){
							var result = $.parseJSON(data);
							if(result.status=="true"){
								$('.errormessagediv').hide();
								$(".successmessagediv").show();
								$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
								$(".validateTips").text("Unblock Record Progressing");
								$(".successmessagediv").css({
									'z-index':'9999999'
								});
							}

							else if(result.status=="false"){
								$('.successmessagediv').hide();
								$(".errormessagediv").show();
								$('.validateTips').text("Fail to Unblock");
								$(".successmessagediv").css({
									'z-index':'9999999'
								});
							}

							setTimeout(function(){
								location.reload();
						},1000);

						}

					});
					$(this).dialog("close");

				},
				
				"No" : function() {
					$(this).dialog("close");
				}
			}

		});
		
		  $("#dialog").dialog("open");
		  $("#dialog").empty();
		  $("#dialog").append("<p>Are you sure to Unblock?</p>");
	
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
	
	document.getElementById("locationName").style.backgroundColor= "#fff";
	document.getElementById("locationName").style.border = "1px solid #ccc";
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

