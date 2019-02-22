function myFunction() {
	    
			         document.getElementById("myForm").submit();   
				  }


	$(document).ready(function() {

		if($("#allstudent tbody tr").length ==0){
			$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
		}
		
		$("#selectall").change(function() {
			$(".select").prop('checked', $(this).prop("checked"));
		});
		
		$(".select").change(function() {
			if($(".select").length == $(".select:checked").length){
				$("#selectall").prop("checked",true);
			}
			else{
				$("#selectall").prop("checked",false);
			}
		});
		
		if($("#div1 .panel-body").text().trim()=="Nothing found to display."){
			$("#div1 .panel-body").empty();
				$("#div1 .panel-body").append('<table id="allstudent" class="table">' +
						'<thead><tr>' +
						'<th class="sortable"><input type="checkbox" name="selectall" id="selectall" onclick="selectAll()"></a></th>'+
						'<th class="sortable">date<img src="images/sort1.png" style="float: right"></a></th>'+
						'<th class="sortable">studentName<img src="images/sort1.png" style="float: right"></a></th>'+
						'<th class="sortable">classname<img src="images/sort1.png" style="float: right"></a></th>'+
						'<th class="sortable">section<img src="images/sort1.png" style="float: right"></a></th>'+
						'<th class="sortable">smstext<img src="images/sort1.png" style="float: right"></a></th>'+
						'<tbody><tr><td colspan="6">No Records Found</td></tr></tbody></table>');
				
		}
		
		
		 var today = new Date();
		    var dd = today.getDate();
		    var mm = today.getMonth()+1;
		    var yyyy = today.getFullYear();
		    if(dd<10){
		        dd='0'+dd;
		    } 
		    if(mm<10){
		        mm='0'+mm;
		    } 
		    var today = dd+'-'+mm+'-'+yyyy;
		
		$("#dateId").datepicker({

			
			dateFormat : "dd-mm-yy",
			minDate : -30,
			maxDate : 0,
			changeMonth : "true",
			changeYear : "true",
		
		}).datepicker('setDate', today);

			
		$("#hdateId").datepicker(
				{

					dateFormat : "dd-mm-yy",
					yearRange : 'c-65:c+65',
					minDate : 0,
					changeMonth : "true",
					changeYear : "true",
					onClose : function(selectedDate) {
						if ((selectedDate != "")
								&& (selectedDate != undefined)) {
							var date2 = $('#Holidaydate')
									.datepicker('getDate');
							date2.setDate(date2.getDate() - 1);
							$("#date").datepicker(
									"option", "maxDate", date2);
						}
					}
				}).datepicker('setDate', today);
		
		
		
					$('#add').click(function(){
						
						window.location.href="adminMenu.html?method=absentlistFilter";
					});
					
					
					$('#AbsentSave').click(function(){
											
						var classid = $('#classid').val();
						var section = $('#sectionid').val();
						var date = $('#dateId').val();
						var smstext = $('#smstext').val();
						var student = $('#studentid').val();
						var today_date=$('#hdateId').val();
						
						if (date == "") {
						
							$('.errormessagediv').show();
							$('.validateTips').text("Please Select Date");

							return false;

						}
						
						else if (classid == "" || classid==null) {
							$('.errormessagediv').show();
							$('.validateTips').text("Please Select Class");

							return false;

						}
						
						else if (section == "" || section==null ) {

							$('.errormessagediv').show();
							$('.validateTips').text("Please Select Section");

							return false;

						}
						
						else if (student == "" || student==null ) {

							$('.errormessagediv').show();
							$('.validateTips').text("Please Select Student");

							return false;

						}
						else if (smstext == "") {

							$('.errormessagediv').show();
							$('.validateTips').text("Please Enter text for sms");

							return false;

						} else if(validateEventSms()){
								
								var x = confirm("Absent content with same date has already been sent.Do you want to send the again?");
								
								if(x){
								
																
									$("#dateId").change(function(){
										today_date=$('#dateId').val();
												$('#smstext').val("Your child did not come to school on "+today_date+", Please see that your ward is regular to school.");
										});

									$('#smstext').val("Your child did not come to school today, Please see that your ward is regular to school.");
								
								
									return true;
									
								}else{
									
									$('.errormessagediv').show();
									$('.validateTips').text("Absent date already exist");
									
									return false;
									
									
								}
							}
							else{
								
								
								document.getElementById("SuddenHolidays").submit();   
							}
					
						
					});
					
					$("#classid").change(function(){
						
						
						
						 $('#classid').show();
					    $('#sectionid').show();
						
						getSection();
						
					});
					
					
					$("#sectionid").change(function(){
						
						   $('#classid').show();
						    $('#sectionid').show();
						    
						    getStudent();
						
					});
					
					
					$('#sectionid,#classid,#studentid')
					.change(
							function() {
								var secList = []; 
								var classList = []; 
								var studenList = [];
								
								$('#sectionid :selected').each(function(i, selected){ 
									secList[i] = $(selected).val(); 
									
									
									
									$('#secCode').val(secList);
									
								});
					
								$('#classid :selected').each(function(i, selected){ 
									classList[i] = $(selected).val(); 
									
							
									$('#classCode').val(classList);
									
								});
					
								$('#studentid :selected').each(function(i, selected){ 
									studenList[i] = $(selected).val(); 
									
								
									$('#studentCode').val(studenList);
									
								});
				});
					
					
					var today_date=$('#dateId').val();
					var Holidayreason=null;
					
					var sms=$('#smstext').val().length;
					
			
					
					$("#dateId").change(function(){
							
							today_date=$('#dateId').val();
								$('#smstext').val("Your child did not come to school on "+today_date+", Please see that your ward is regular to school.");
						});

					$('#smstext').val("Your child did not come to school today, Please see that your ward is regular to school.");
				
				
					
					var maxlength=$('#smstext').attr('maxlength');
					
					
					$('#maxlimit').text((maxlength)-parseInt(sms));
					
					
					/*$("#dateId").change(function(){
						var maxlength=$('#smstext').attr('maxlength');
						 Holidayreason=$('#Holidayreason').val().trim();
							$('#smstext').val("Please note that "+today_date+" is a "+Holidayreason+" holiday. The School will be closed that day.Enjoy the holiday" );
						$('#maxlimit').text(parseInt((maxlength)-parseInt(sms)+parseInt("7")-parseInt(Holidayreason.length)));
					});*/
					
					
					
	
	});
	
	
	var sectionlength=0;
	function getSection() {
		
		var classid = $("#classid").val();
		
		
		datalist={
				"classid" : classid.join()
		},
		
		
		
		$.ajax({
			
			type : 'POST',
			url : "communicationPath.html?method=getSection",
			data : datalist,
			success : function(response) {
				
				var result = $.parseJSON(response);
				
				$('#sectionid').html("");
				
				$('#sectionid').append();
						
						/*'<option value="' + "" + '">' + "---Select---"
								
						+ '</option>');*/
				
				for ( var j = 0; j < result.seclist.length; j++) {

					$('#sectionid').append(

					'<option value="'

					+ result.seclist[j].sectionId + '">'

					+ result.seclist[j].sectionName

					+ '</option>');

				}
				
			}
			
			
		});
		
		
	}
	
	
	
	
	function  validateEventSms(){
		
		var meetingstatus=false;
		var date=$('#dateId').val();
		var smstext=$('#smstext').val();
		
		
		var validatedetails = {
				"date" : date,
				"smstext" : smstext
				
			};
		
			$.ajax({
						type : 'POST',
						url : "absentSMS.html?method=validateAbsentSms",
						data : validatedetails,
						async:false,
						
						success : function(response) {
							var result = $
							.parseJSON(response);
							
							meetingstatus=result.status;
							
													
						}
					});
			
			return meetingstatus;
			
	}

	var sectionlength=0;
	function getStudent() {
		
	var sectionid = $("#sectionid").val();
		
		
		datalist={
				"sectionid" : sectionid.join() 
		},
		
	$.ajax({
		
		type : 'POST',
		url : "smsPath.html?method=getStudentMeeting",
		data : datalist,
		success : function(response) {
			

	        var result = $.parseJSON(response);
				
	        $('#studentid').html("");
				
				$('#studentid').append();
				
				('<option value="' + "" + '">' + ""
						
				+ '</option>');
						
					for(var j = 0; j < result.studentlist.length; j++){
						
						$('#studentid').append(

								'<option value="'

								+ result.studentlist[j].id + '">'

								+ result.studentlist[j].name

								+ '</option>');
					}
			
			
			
			
		}
		
		
	});
		
		
	}

	
	