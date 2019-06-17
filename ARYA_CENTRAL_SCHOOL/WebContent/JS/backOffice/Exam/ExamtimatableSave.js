language=false;
$(document).ready(function(){
	var saveFlag=true;
	
	getsubdetails();
	

	
	$(".class").click(function(){
		$("#classOne").slideToggle();
	});
	
	$("#back").click(function(){
		window.location.href ="examTimetablePath.html?method=getexamtimetableclasssection&accyear="+$("#hiddenaccyear").val()+"&locid="+$("#hiddenloc").val()+"&classid="+$("#hiddenclass").val()+"&classname="+$("#class").val()+"&examid="+$("#hiddenexamid").val();
	});
	
	$(".startdate").change(function(){
		$('.startdate').css({'border':'1px solid #ccc'});
		
		var timeFlag=false;
		var starttime = $('.starttime').val();
		var endtime = $('.endtime').val();
		var currenth=$(this);
		var date=$(this).val();
		
		$("#allstudent tbody tr[id]").find(".startdate").not(currenth).each(function(){
			if(currenth.closest('tr').attr('class')=="Y"){
				if(currenth.closest('tr').attr('class')==$(this).closest('tr').attr('class')){
					language=true;
				}
			}
			if((date == $(this).val()) && language){
				
				if(currenth.closest('tr').find(".starttime").val() <= starttime && currenth.closest('tr').find(".endtime").val() > starttime){
				
					timeFlag=true;
					return false;
				}else if(currenth.closest('tr').find(".starttime").val() >= starttime && currenth.closest('tr').find(".starttime").val() < endtime){
			
					timeFlag=true;
					return false;
				}
				
			}
		
		});
		if(timeFlag){
			currenth.closest('tr').find(".starttime").val("");
			currenth.closest('tr').find(".endtime").val("");
			$(".errormessagediv").show();
			$(".errormessagediv .validateTips").text("Timings Should be Different for Exams for the Same Dates");
			currenth.closest('tr').find(".starttime").css({'border-color':'#B70606'});
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
 			}, 3500);
		}
	});
	
	$('.endtime').change(function(){
		$('.endtime').css({'border':'1px solid #ccc'});
		
		var timeFlag=false;
		var starttime = $('.starttime').val();
		var endtime = $('.endtime').val();
		var currenth=$(this);
		var date=$(".startdate").val();
		
		$("#allstudent tbody tr[id]").find(".endtime").not(currenth).each(function(){
			if(currenth.closest('tr').attr('class')=="Y"){
				if((currenth.closest('tr').attr('class')==$(this).closest('tr').attr('class'))){
					language=true;
				}
		}
			
			if(($(this).closest('tr').find(".startdate").not(currenth.closest('tr').find('.startdate')).val() == currenth.closest('tr').find(".startdate").val())){
				
				if(currenth.closest('tr').find(".starttime").val() <= starttime && currenth.closest('tr').find(".endtime").val() < endtime){
					timeFlag=true;
					return false;
				}
				else if(currenth.closest('tr').find(".starttime").val() >= starttime && currenth.closest('tr').find(".starttime").val() < endtime){
					timeFlag=true;
					return false;
				}
			}
			
		});
		if(timeFlag){
			currenth.closest('tr').find(".starttime").val("");
			currenth.closest('tr').find(".endtime").val("");
			$(".errormessagediv").show();
			$(".errormessagediv .validateTips").text("Timings Should be Different for Exams for the Same Dates");
			currenth.closest('tr').find(".starttime").css({'border-color':'#B70606'});
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
 			}, 3500);
		}
	
	});
	
	var rows = $("#allstudent tr");
	
	
	$('.starttime').change(function(){
		$('.starttime').css({'border':'1px solid #ccc'});
		var language=false;
		var timeFlag=false;
		var starttime=$(this).val();
		var endtime = $('.endtime').val();
		var currenth=$(this);
		var date = $('.startdate').val();
		
				
				$("#allstudent tbody tr[id]").find(".starttime").not(currenth).each(function(){
					
					if((currenth.closest('tr').attr('class')=="Y")){
						if(currenth.closest('tr').attr('class')==$(this).closest('tr').attr('class')){
							language=true;
						}
					}
					
					
					if(($(this).closest('tr').find(".startdate").not(currenth.closest('tr').find('.startdate')).val() ==currenth.closest('tr').find(".startdate").val()) && language){
			
					if(starttime >= $(this).val() && starttime<$(this).closest('tr').find(".endtime").not(currenth.closest('tr').find('.endtime')).val()){
					
						timeFlag=true;
						return false;
					}
					/*if(starttime <= $(this).val() && starttime > $(this).closest('tr').find(".endtime").not(currenth.closest('tr').find('.endtime')).val()){
						alert("2");
						timeFlag=true;
						return false;
					}*/
					}
				});
			
		
		if(timeFlag){
			$(this).val("");
			$(".errormessagediv").show();
			$(".errormessagediv .validateTips").text("Timings Should be Different for Exams for the Same Dates");
			$(this).css({'border-color':'#B70606'});
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
 			}, 3500);
		}
		
		
	});

	saveflag = true;
	$("#save").click(function(){
		var rowCount = 1;
		
		subid1 = [];
		subname1 = [];
		subcode1= [];
		starttime1 = [];
		endtime1 =[];
		startdate1 =[];
		$('table#allstudent tr[id]').each(function(){
			
			var subid = $(this).find('.subid').attr("class").split(" ")[0];
			var subcode = $(this).find('.subcode').text();
			var subname = $(this).find('.subid').text();
			var starttime = $(this).find('[name=starttime]').val();
			var endtime = $(this).find('[name=endtime]').val();
			var startdate = $(this).find('[name=startdate]').val();
			
			subid1.push(subid);
			subname1.push(subcode);
			subcode1.push(subname);
			if(startdate == "" || startdate == undefined){
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required - Exam Date in Row "+rowCount);
				$(this).find('[name=startdate]').css({'border':'1px solid #AF2C2C'});
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				saveflag = false;
				return false;
			}
			else if(starttime == "" || starttime == undefined){
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required - Start Time in Row "+rowCount);
				$(this).find('[name=starttime]').css({'border':'1px solid #AF2C2C'});
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				saveflag = false;
				return false;
			}
			else if(endtime == "" || endtime == undefined){
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required - End Time in Row "+rowCount);
				$(this).find('[name=endtime]').css({'border':'1px solid #AF2C2C'});
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				saveflag = false;
				return false;
			}
			if(startdate !="" && startdate!=undefined){
				
				startdate1.push(startdate);
				saveflag = true;
			}
			if(starttime !="" && starttime!=undefined){
				$(this).find('[name=starttime]').css({'border':'1px solid #ccc'});
				starttime1.push(starttime);
				saveflag = true;
			}
			if(endtime !="" && endtime!=undefined){
				$(this).find('[name=endtime]').css({'border':'1px solid #ccc'});
				endtime1.push(endtime);
				saveflag = true;
			}
			
			if(endtime <= starttime){
				$(".errormessagediv").show();
				$(".validateTips").text("End Time Must be Greater Then Start Time in Row "+rowCount);
				$(this).find('[name=endtime]').css({'border':'1px solid #AF2C2C'});
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				saveflag = false;
				return false;
			}
			rowCount ++;
		});
		
		if ($('#allsection').is(":checked"))
		{
			sectionid = "all";
		
		}
		else{
			sectionid = $("#hiddensection").val();
		}
		datalist = {
				"accyear" : $("#hiddenaccyear").val(),
				"locid" : $("#hiddenloc").val(),
				"classid" : $("#hiddenclass").val(),
				"sectionid" :sectionid,
				 "examid" : $("#hiddenexamid").val(),
				 "subid1" : subid1,
				 "subname1" : subname1,
				 "subcode1" : subcode1,
				 "startdate" :startdate1,
				 "starttime1" : starttime1,
				 "endtime1" :endtime1
		};
		if(saveflag){
				$.ajax({
			 		 type: "POST",
			 		  url: "examTimetablePath.html?method=savetimetabledetails",
			           data:datalist,
			           async:false,
			 		   success: function(data) {
			 			var result = $.parseJSON(data);
			 			if(result.status == "true"){
			 			$(".errormessagediv").hide();
			 			$(".successmessagediv").show();
						$(".validateTips").text("Adding Record progressing...");		
			 			}
			 			else{
			 				$(".successmessagediv").hide();
			 				$(".errormessagediv").show();
							$(".validateTips").text("Record Not Added.");
			 			}
						setTimeout(function() {
							window.history.back();
						},2000);		 
			 		  }
			 	 });
		}
	});
	
	$('.form_time').datetimepicker({
		 
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
	});
		
	
	
	
	
});

function getsubdetails(locid,classid){
	
		$.ajax({
			type : 'POST',
			url : "examTimetablePath.html?method=getsubdetails",
			data : {"location":$("#hiddenloc").val(),"classid":$("#hiddenclass").val(),"examid":$("#hiddenexamid").val(),"accyear":$("#hiddenaccyear").val()},
			async : false,
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#mytable").empty();
					$("#mytable").append("<table class='table' name='stuid' id='allstudent' width='100%'>"+"<tr>"+
							"<th>Sl.No</th>" +
							"<th>Subject Code</th>" +
							"<th>Subject</th>" +
							"<th>Exam Date</th>"+
							"<th>Start Time</th>"+
							"<th>End Time</th>"+
							"</tr>" +
							"</table>"
							);
					rowdate = 1;
					for(var i=0;i<result.sublist.length;i++){
					
						$("#allstudent tbody").append("<tr id='"+result.sublist[i].sno1+"' class='"+result.sublist[i].islanguage+"' >"
							+"<td>"+result.sublist[i].sno1+"</td>" 
							+"<td class='subcode'>"+result.sublist[i].subCode+"</td>"
							+"<td  class='"+result.sublist[i].subId+" subid'>"+result.sublist[i].subjectName+"</td>"
							+"<td><input type='text' name='startdate' readonly='readonly' id='startdate"+rowdate+"' class='form-control date startdate'  placeholder='Select Date' value=''/></td></td>"
							+"<td style='text-align:center;'>"+
							"<div style='width:100%;' class='input-group date form_time col-md-8' data-date='' data-date-format='hh:ii' data-link-field='dtp_input3' data-link-format='hh:ii' style='z-index:9'>"
							+"<input class='form-control inputcolor starttime'  type='text' name='starttime' value='' readonly>"
						
							+"<span class='input-group-addon'><span class='glyphicon glyphicon-time'></span></span>"
							+"</div></td>"
							+"<td>" +
							"<div style='width:100%;' class='input-group date form_time col-md-8' data-date='' data-date-format='hh:ii' data-link-field='dtp_input3' data-link-format='hh:ii'>"
							+"<input class='form-control endtime'  type='text' name='endtime' value='' readonly>"
							
							+"<span class='input-group-addon'><span class='glyphicon glyphicon-time'></span></span>"
							+"</div></td>"
							+"</tr>");
					$('#startdate'+rowdate+'.date').datepicker({
				    	dateFormat : "dd-mm-yy",
						yearRange : 'c-65:c+65',
						changeMonth : "true",
						changeYear : "true",
						numberOfMonths : 1,
						minDate : $("#minDate").val(),
						maxDate : $("#maxDate").val()
					});
						rowdate ++;
					}	

					$("#allstudent").after("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select></div><div class='pagination pagelinks'></div>");
					pagination(50);
					$("#show_per_page").change(function(){
						pagination($(this).val());
					});
					
			}
		
	});
}


	
	

