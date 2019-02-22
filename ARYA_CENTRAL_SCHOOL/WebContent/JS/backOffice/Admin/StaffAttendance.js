$(document)
		.ready(
				function() {
					
					if($("#allstudent tbody tr").length ==0){
						$("#allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
					}
					
					$(".errormessagediv").hide();
					
					$("#date").datepicker({
						dateFormat : "dd-mm-yy",
						maxDate : 0,
						changeMonth : "true",
						changeYear : "true",
						buttonImage : "images/calendar.GIF",
						buttonImageOnly : true
					});
					
					$("#startdate").datepicker({
						dateFormat : "dd-mm-yy",
						maxDate : 0,
						changeMonth : "true",
						changeYear : "true",
						buttonImage : "images/calendar.GIF",
						buttonImageOnly : true,
						onClose : function(selectedDate) {
							if ((selectedDate != "")&& (selectedDate != undefined)) {
								var date2 = $('#startdate').datepicker('getDate');
								date2.setDate(date2.getDate() + 1);
								$("#enddate").datepicker("option","minDate", date2);
							}
						}
					});
					
					$("#enddate").datepicker({
						dateFormat : "dd-mm-yy",
						maxDate : 0,
						changeMonth : "true",
						changeYear : "true",
						buttonImage : "images/calendar.GIF",
						buttonImageOnly : true,
						onClose : function(selectedDate) {
							if ((selectedDate != "") && (selectedDate != undefined)) {
								
								var date2 = $('#enddate').datepicker('getDate');
								date2.setDate(date2.getDate() - 1);
								$("#startdate").datepicker("option", "maxDate", date2);
							}
						}
					});
					
					var hdeptId=$("#hdeptId").val();
					if(hdeptId!=null && hdeptId!=undefined){
					$("#department option[value='"+hdeptId.trim()+"']").attr('selected', 'true');
					}
					
				
					$('#allstudent tr').each(function(){
						
						var status=$(this).find('.statusclass').val();
						
						if(status!=undefined){
						
						var rowid=$(this).find('.statusclass').attr("id");
						
						
						$('#'+rowid).find('option').remove();
					
						var statusList=[];
						statusList.push("Present");
						statusList.push("Absent");
						statusList.push("Half Day");
						statusList.push("CL");
						statusList.push("EL");
						statusList.push("HPL");
						statusList.push("Present/EL");
						statusList.push("Present/CL");
						statusList.push("Present/HPL");
						statusList.push("EL/Present");
						statusList.push("CL/Present");
						statusList.push("HPL/Present");
						statusList.push("Absent/EL");
						statusList.push("Absent/CL");
						statusList.push("Absent/HPL");
						statusList.push("EL/Absent");
						statusList.push("CL/Absent");
						statusList.push("HPL/Absent");
						statusList.push("LWA");
						statusList.push("Holiday");
						
						
						for (var j = 0; j < statusList.length; j++) {
							

							$("#"+rowid).append(
											'<option value="'
													+ statusList[j]
													+ '">'
													+ statusList[j]
													+ '</option>');
						}
						
						
						$("#"+rowid).val(status);
						
						}
						
					});
					
					$("#save2").click(function(){
						
						var date =$("#date").val();
						
						var teacherIdArray=[];
						var statusArray=[];
						
						$('#allstudent tr').each(function(){
							
							
							var teacherID=$(this).find('.hiddenclass').text();
							var status=$(this).find('.statusclass').val();
							
							
							if(teacherID!="TeacherId"){
								
								teacherIdArray.push(teacherID.trim());
							}
							
							if(status!=undefined && status!=""){
								
								statusArray.push(status);
							}
							
						});
						
						
						var datalist={
								
								"date" : date,
								"teacherIdArray" : teacherIdArray.join(),
								"statusArray" : statusArray.join()
								
						};
						
						
						
						$.ajax({
							type : "POST",
							url : "staffattendancepath.html?method=updateAttendanceStatus",
							data :datalist,
							async : false,
							success : function(data) {
								var result = $.parseJSON(data);
								
								if(result.status=="true"){
									
									$('.successmessagediv').show();
									$('.successmessage').text("Attendance updated successfully");
									
								setTimeout(function(){
									
									 window.location.href="adminMenu.html?method=getStaffAttendance";
								 
								 },6000);
								
								}else{
									
									$('.errormessagediv').show();
									$('.validateTips').text("Attendance not updated,Try later");

								}
							
							}
						});
						
						
					});
					
		$("#refresh").click(function(){
			$.ajax({
				type : "POST",
				url : "staffattendancepath.html?method=RefreshAttendanceStatus",
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					
					if(result.status=="true"){
						 window.location.href="adminMenu.html?method=getStaffAttendance";
					}
				
				}
			});
			
			
		});
	
				$("#searchAttendanceList").click(function(){
					
			//	alert("djfjdsnfd");	
					
					var startDate=$("#startdate").val();
					var endDate=$("#enddate").val();
					
					window.location.href="adminMenu.html?method=getStaffAttendance&startDate="+startDate+"&endDate="+endDate;	
					
					
				});
				
				$("#search").click(function(){
					
					var attDate=$("#date").val();
					var department=$("#department").val();
					
					
					window.location.href="staffattendancepath.html?method=searchStaffAttendaceUpload&attDate="+attDate+"&department="+department;	
					
				});
					
				$('#excelDownload')
				.click(
						function() {
							
							var startDate=$("#startdate").val();
							var endDate=$("#enddate").val();
							
							window.location.href = "staffattendancepath.html?method=downloadStaffAttendanceDetailsXLS&startDate=" + startDate + "&endDate=" + endDate;
							
						});
				$("#pdfDownload").click(function(){
					
					
					var startDate=$("#startdate").val();
					var endDate=$("#enddate").val();
					
					window.location.href = "staffattendancepath.html?method=downloadStaffAttendanceDetailsPDF&startDate=" + startDate + "&endDate=" + endDate;
						
				});
		});

		


		


function submitAttendence() {

	document.location.href = "teacherAttendance.html?method=UploadAttendence";

}

