var rowHoliday=0;
var flag=false;
var saveFlag=true;
var errorMsg=null;
$(document).ready(function(){
	
	addMoreHoliday();
			
	
	$("#saveid").click(function(){
		$("#saveid").hide();
		
		var location = $("#locationname").val();
		var year =$("#accyYear").val().trim();
		var rowCount=1;
		
		if(location == "" || location == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Location Name");
			document.getElementById("locationname").style.border = "1px solid #AF2C2C";
			document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			$("#saveid").attr("style","display:inline");
			return false;

			
		}
		else{
			$("#locationname").css({'border-color':'rgb(221, 221, 221)'});
		}
		
		
		
		
		
		if(year == "" || year == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Academic Year");
			document.getElementById("accyYear").style.border = "1px solid #AF2C2C";
			document.getElementById("accyYear").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			$("#saveid").attr("style","display:inline");
			return false;

			
		}
		else{
			$("#accyYear").css({'border-color':'rgb(221, 221, 221)'});
		}
		
		
		
		
			var holiday_date = [];
			var holiday_week = [];
			var holiday_description = [];
			var holiday_type=[];
			
			
			
			$('table#addholiday tr[id]')
			.each(
					function() {
						var date = $(this).find('[name=date]').val().trim();
						var weekday = $(this).find('[name=weekday]').val().trim();
						var desc = $(this).find('[name=holiday]').val().trim();
						var holidaytype=$(this).find('[name=holidaytype]').val();
						
						if(date == '' || date == null){
							$(".errormessagediv").show();
							$(".errormessagediv .validateTips").text("Enter the Date");
							errorMsg="Enter the Date in Row "+rowCount;
							$(this).find(".date").css({'border-color':'#B70606'});
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							$("#saveid").attr("style","display:inline");
							saveFlag=false;
							return false;
							
						}
						
						
						else if (date != undefined && date != '' ) {
							
							holiday_date.push(date);
						 }
						 
						 
						 
						 if (weekday != undefined && weekday != '' ) {
							 holiday_week.push(weekday);
						}
						 
						 if(desc == '' || desc == null){
							 $(".errormessagediv").show();
								$(".validateTips").text("Enter the Holiday Name");
								errorMsg="Enter the Holiday Name in Row "+rowCount;
								$(this).find(".holiday").css({'border-color':'#B70606'});
								setTimeout(function() {
									$('.errormessagediv').fadeOut();
								}, 3000);
								$("#saveid").attr("style","display:inline");
								saveFlag=false;
								return false;
							
						}
						 
						 else if(!desc.match(/^[a-zA-Z-,]+(\s{0,1}[a-zA-Z-, ])*$/)){
							 $(".errormessagediv").show();
								$(".validateTips").text("Holiday Name should be characters");
								errorMsg="Holiday Name should be characters in Row "+rowCount;
								$(this).find(".holiday").css({'border-color':'#B70606'});
								setTimeout(function() {
									$('.errormessagediv').fadeOut();
								}, 3000);
								$("#saveid").attr("style","display:inline");
								saveFlag=false;
								return false;
						 } 
						
						 else if(holiday_description != undefined && holiday_description != null ){
						holiday_description.push(desc);
						saveFlag=true;
						}
						 
						 
						 
						 
						 
						 if(holidaytype == '' || holidaytype == null){
							 
							 $(".errormessagediv").show();
								$(".validateTips").text("Select Holiday Type");
								$(this).find(".holidaytype").css({'border-color':'#B70606'});
								errorMsg="Select Holiday Type in Row "+rowCount;
								setTimeout(function() {
									$('.errormessagediv').fadeOut();
								}, 3000);
								$("#saveid").attr("style","display:inline");
								saveFlag=false;
								return false;

							 
						 }
						 
						if(holidaytype != undefined && holidaytype != ''){
							holiday_type.push(holidaytype);
							$(this).find(".holidaytype").css({'border-color':'rgb(221, 221, 221)'});
							saveFlag=true;
						}
						
						
						rowCount++;
					});
			
		
			var jsonObject = {
					
					'location': location,
					'year':year,
					'holiday_date':holiday_date,
					'weekday':holiday_week,
					'holiday':holiday_description,
					'holidaytype':holiday_type
			};
			
	if($("#addholiday tbody tr").length > 1){
			if(saveFlag){
			$.ajax({
		 		 type: "GET",
		 		  url: "holidayMaster.html?method=addMultiHolidayDetail",
		           data:jsonObject,
		           async:false,
		           
		 		   success: function(data) {
		 		
		 			var result = $.parseJSON(data);
		 			if(result.status == "true"){
		 			$(".errormessagediv").hide();
		 			$(".successmessagediv").show();
					$(".validateTips").text("Add Holiday progressing...");		
		 			}
		 			else{
		 				$(".successmessagediv").hide();
		 				$(".errormessagediv").show();
						$(".validateTips").text("Holiday Not Added.");
		 			}
					setTimeout(function() {
		 			window.location.href="adminMenu.html?method=holidaymaster";
					},2000);		 
					
		 			

		 			
		 		  }
		 		  
		 	 
		 	 });
		
	}
			else{
				$("#saveid").attr("style","display:inline");
				$(".errormessagediv").show();
				$(".validateTips").text(errorMsg);
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
			}
	}
	else{
		$("#saveid").attr("style","display:inline");
		$(".errormessagediv").show();
		$(".validateTips").text("No Record Found please Add Rows");
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
	}
		
	});
	
	

	
	
	
	
	
	
});


function addMoreHoliday(frm) {
	rowHoliday++;
	$('#addholiday tr')
			.last()
			.after(
					'<tr id="holidayId'
							+ rowHoliday
						    + '"><td><input type="text" name="date" readonly="readonly"  class="form-control date"  placeholder="Holiday Date" type="text"/></td><td><input type="text" name="weekday"  readonly="readonly" class="form-control"  /></td><td><input type="text" name="holiday" id="holiday'+rowHoliday+'" class="form-control holiday" /></td><td ><select name="holidaytype" class="form-control holidaytype" style="min-width:150px;"><option value="">----Select----</option><option value="FirstHalf">First Half</option><option value="Second Half">Second Half</option><option value="Full Day">Full Day</option></select></td>        <td align="center"><a href="javascript:void(0);" class="glyphicon glyphicon-trash" onclick="removeHoliday('
						    + rowHoliday +');"></a></td></tr>');
	
	var weekday=new Array(7);
	
	weekday[0]="Monday";
	weekday[1]="Tuesday";
	weekday[2]="Wednesday";
	weekday[3]="Thursday"; 
	weekday[4]="Friday";
	weekday[5]="Saturday";
	weekday[6]="Sunday";
	
	   $('#holidayId'+rowHoliday+' .date').datepicker({
	    	dateFormat : "dd-mm-yy",
			yearRange : 'c-65:c+65',
			changeMonth : "true",
			changeYear : "true",
			numberOfMonths : 1,
			onSelect: function(dateText, inst) { 
				
				var dateFlag=false;
				var enterDate=$(this).val();
				var currentd=$(this);
				$("#addholiday tbody tr[id]").find(".date").not(currentd).each(function(){
					
					if($(this).val() == enterDate){
						
						dateFlag=true;
					}
					
		 		});
		 		if(dateFlag){
					$(this).val("");
					$(this).parent("td").parent("tr").find("input[name='weekday']").val("");
					$(this).css({'border-color':'#B70606'});
					$(".errormessagediv").show();
					$(".errormessagediv .validateTips").text("Duplicate Date Not Allowed");
					
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
		 			}, 3000);
				}
				else{
		        var date = $(this).datepicker('getDate');
		            day  = date.getUTCDay();
		            $(this).parent('td').parent('tr').find('input[name=weekday]').val(weekday[day]);
				}
		         
		    },
	   onClose:function(){
		  var thispointer=$(this);
			var dateval= $(this).val();	
			var location = $("#locationname").val();
			
			datalist = {
				
					"date":dateval,
					"location":location,
					"accYear":$("#accyYear").val()
			},	
			$.ajax({
				
				type : "POST",
				
				url :"holidayMaster.html?method=dateValidate",
				
				data : datalist,
					
				success: function(data) {
				 var result=$.parseJSON(data);	
				 flag =true;
					if(result.dateVal=='exit')
					{
						 
						 $(".errormessagediv").show();
						 errorMsg="Date Already Exists, Select Another Date";
						$(".validateTips").text("Date Already Exists, Select Another Date");
						$(thispointer).css({'border-color':'#B70606'});
						$(thispointer).val("");
						$(thispointer).parent("td").parent("tr").find("input[name='weekday']").val("");
						  setTimeout(function() {$('#errorhover').fadeOut();}, 3000);
						 saveFlag=false;
						
					}
					else{
						$(thispointer).css({'border-color':'#ddd'});
					}
			 			
			 		  }
			
			}); 
		   
	   }
		});
	
	   $("#accyYear").change(function(){
		
			$("#addholiday tbody tr[id]").each(function(){
				holidayValidate($(this).find(".holiday"), $("#locationname").val(),$("#accyYear").val());
				
			});
	   });
			$("#locationname").change(function(){
				$("#addholiday tbody tr[id]").each(function(){
					holidayValidate($(this).find(".holiday"), $("#locationname").val(),$("#accyYear").val());
					DateValidate($(this).find("input[name='date']"),$("#locationname").val(),$("#accyYear").val());
				
				});
				
				
			});
			
			$("#accyYear").change(function(){
				$("#addholiday tbody tr[id]").each(function(){
					holidayValidate($(this).find(".holiday"), $("#locationname").val(),$("#accyYear").val());
					DateValidate($(this).find("input[name='date']"),$("#locationname").val(),$("#accyYear").val());
				
				});
				
				
			});
	
		$(".holiday").change(function(){
			var holidayFlag=false;
			var enterholiday=$(this).val();
			var currenth=$(this);
			$("#addholiday tbody tr[id]").find(".holiday").not(currenth).each(function(){
				
				if(enterholiday.toLowerCase()==$(this).val().toLowerCase()){
				
					holidayFlag=true;
				}
			});
			if(holidayFlag){
				$(this).val("");
				$(".errormessagediv").show();
				$(".errormessagediv .validateTips").text("Duplicate Holiday Name Not Allowed");
				$(this).css({'border-color':'#B70606'});
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
	 			}, 3000);
			}
			else{
				holidayValidate($(this),$("#locationname").val(),$("#accyYear").val());
			}
			}); 
	
}
function holidayValidate(holiday,location,academicYear){

	
	datalist = {
		
			"holiday":holiday.val(),
			"location":location,
			"academicYear":academicYear
	},	
	$.ajax({
		
		type : "POST",
		
		url :"holidayMaster.html?method=HolidayNameValidate",
		
		data : datalist,
			
		success: function(data) {
		 var result=$.parseJSON(data);	
		 flag =true;
			if(result.dateVal=='exit')
			{
				
				 $(".errormessagediv").show();
				 errorMsg="Holiday Name Already Exist";
				$(".validateTips").text("Holiday Name '"+$(holiday).val()+"' Already Exist");
				
				$(holiday).css({'border-color':'#B70606'});
				$(holiday).val("");
				  setTimeout(function() {$('#errorhover').fadeOut();}, 3000);
				 saveFlag=false;
				
			}
			else{
				$(holiday).css({'border-color':'#ddd'});
				 saveFlag=true;
			}
	 			
	 		  }
	
	}); 
   


}
function DateValidate(dateVal,locations,accYear){
	if(flag){
		var dateval= dateVal.val();	
		var location =locations;
		var accYears=accYear;
		datalist = {
			
				"date":dateval,
				"location":location,
				"accYear":accYears
		},	
		$.ajax({
			
			type : "POST",
			
			url :"holidayMaster.html?method=dateValidate",
			
			data : datalist,
				
			success: function(data) {
			 var result=$.parseJSON(data);	
				if(result.dateVal=='exit')
				{
					
					 $(".errormessagediv").show();
					 errorMsg="Date Already Exists, Select Another Date";
					$(".validateTips").text("Date "+$(dateVal).val()+" Already Exists, Select Another Date");
					$(dateVal).css({'border-color':'#B70606'});
					$(dateVal).val("");
					$(dateVal).parent("td").parent("tr").find("input[name='weekday']").val("");
					  setTimeout(function() {$('#errorhover').fadeOut();}, 3000);
					 
					  saveFlag=false;
				}
				else{
					$(dateVal).css({'border-color':'#ddd'});
				}
		 			
		 		  }
		
		}); 
		} 
	
}
function removeHoliday(removeNum) {
	jQuery('#holidayId' + removeNum).remove();
}