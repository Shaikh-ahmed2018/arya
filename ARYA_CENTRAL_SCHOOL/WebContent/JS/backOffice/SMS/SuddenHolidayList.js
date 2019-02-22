function myFunction() {
	    
			         document.getElementById("myForm").submit();   
				  }


	$(document).ready(function() {
		
		if($("#allstudent tbody tr").length ==0){
			$("#allstudent tbody").append("<tr><td colspan='3'>NO Records Found</td></tr>");
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
						'<thead><tr><th class="sortable"><input type="checkbox" name="selectall" id="selectall" onclick="selectAll()"></a></th>'+
						'<th class="sortable">Holiday Date<img src="images/sort1.png" style="float: right"></a></th>'+
						'<th class="sortable">Holiday Reason<img src="images/sort1.png" style="float: right"></a></th>'+
						'</tr></thead>'+
						'<tbody><tr><td colspan="3">No Records Found</td></tr></tbody></table>');
				
		}
		
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1;
		var yyyy = today.getFullYear();
		
		var today = dd + '-' + mm + '-' + yyyy;

		$("#dateId").datepicker(
				{
					dateFormat : "dd-mm-yy",
					yearRange : 'c-65:c+65',
					minDate : 0,
					changeMonth : "true",
					changeYear : "true",
					yearRange : '2010:' + (new Date).getFullYear(),
					onClose : function(selectedDate) {
						if ((selectedDate != "")
								&& (selectedDate != undefined)) {
							var date2 = $('#date')
									.datepicker('getDate');
							date2.setDate(date2.getDate() + 1);
							$("#Holidaydate").datepicker("option",
									"minDate", date2);
						}
					}
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
						
						
						
						window.location.href="suddenHolidays.html?method=SuddenHolidayFilter";
					});
					
					
					$('#HolidaysSave').click(function(){
											
						
						
						var classid = $('#classid').val();
						var section = $('#sectionid').val();
						var date = $('#dateId').val();
						var smstext = $('#smstext').val();
						var Holidaydate=$('#hdateId').val();
						var today_date=$('#hdateId').val();
						var Holidayreason=null;
						
						if (date == "") {
							
							
						
							$('.errormessagediv').show();
							$('.validateTips').text("Please Select Date");

							return false;

						}else if (Holidaydate == "") {
							

							$('.errormessagediv').show();
							$('.validateTips').text("Please Select Holiday Date");

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
						else if (smstext == "") {
							

							$('.errormessagediv').show();
							$('.validateTips').text("Please Enter text for sms");

							return false;

						} else if(validateEventSms()){
						
								
								var x = confirm("Sudden holiday already exist with same content and same date.Do you want to send the same?");
								
								if(x){
									
								
									today_date=$('#hdateId').val();
									Holidayreason=$('#Holidayreason').val().trim();
									if(Holidayreason==null || Holidayreason==""){
										
										$('#smstext').val("Please note that "+today_date+" is a special holiday. The School will be closed that day.Enjoy the holiday" );
									}else{
										
										$('#smstext').val("Please note that "+today_date+" is a "+Holidayreason+" holiday. The School will be closed that day.Enjoy the holiday" );
									}
								
									return true;
									
								}else{
									
									$('.errormessagediv').show();
									$('.validateTips').text("Sudden holiday already exist");
									
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
					
					
					$('#sectionid,#classid')
					.change(
							function() {
								var secList = []; 
								var classList = []; 
								
								$('#sectionid :selected').each(function(i, selected){ 
									secList[i] = $(selected).val(); 
									
									
									
									$('#secCode').val(secList);
									
								});
					
								$('#classid :selected').each(function(i, selected){ 
									classList[i] = $(selected).val(); 
									
							
									$('#classCode').val(classList);
									
								});
					
				});
					
					
					var today_date=$('#hdateId').val();
					var Holidayreason=null;
					
					$('#smstext').val("Please note that "+today_date+" is a special holiday. The School will be closed that day.Enjoy the holiday" );
					var sms=$('#smstext').val().length;
					
					$("#hdateId").change(function(){
						
						today_date=$('#hdateId').val();
						Holidayreason=$('#Holidayreason').val();
						
										
						if(Holidayreason==null){
							
							$('#smstext').val("Please note that "+today_date+" is a special holiday. The School will be closed that day.Enjoy the holiday" );
						}else{
							
							$('#smstext').val("Please note that "+today_date+" is a "+Holidayreason+" holiday. The School will be closed that day.Enjoy the holiday" );
						}
					
						
					});	
					
					$("#dateId").change(function(){
						
						today_date=$('#hdateId').val();
						Holidayreason=$('#Holidayreason').val();
						if(Holidayreason==null){
							
							$('#smstext').val("Please note that "+today_date+" is a special holiday. The School will be closed that day.Enjoy the holiday" );
						}else{
							
							$('#smstext').val("Please note that "+today_date+" is a "+Holidayreason+" holiday. The School will be closed that day.Enjoy the holiday" );
						}
					
						
					});
					
					$('#Holidayreason').change(function(){
						today_date=$('#hdateId').val();
						 Holidayreason=$('#Holidayreason').val();
						
						$('#smstext').val("Please note that "+today_date+" is a "+Holidayreason+" holiday. The School will be closed that day.Enjoy the holiday" );
						
						
					});
				
					
					var maxlength=$('#smstext').attr('maxlength');
					
									
					$('#maxlimit').text((maxlength)-parseInt(sms));
					$('#Holidayreason').keyup(function(){
						var maxlength=$('#smstext').attr('maxlength');
						 Holidayreason=$('#Holidayreason').val().trim();
							$('#smstext').val("Please note that "+today_date+" is a "+Holidayreason+" holiday. The School will be closed that day.Enjoy the holiday" );
						$('#maxlimit').text(parseInt((maxlength)-parseInt(sms)+parseInt("7")-parseInt(Holidayreason.length)));
					});
					
					
					
	
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
		var date=$('#hdateId').val();
		var smstext=$('#smstext').val();
		
		
		var validatedetails = {
				"date" : date,
				"smstext" : smstext
				
			};
		
			$.ajax({
						type : 'POST',
						url : "suddenHolidays.html?method=validateSuddenHolidaysSms",
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	