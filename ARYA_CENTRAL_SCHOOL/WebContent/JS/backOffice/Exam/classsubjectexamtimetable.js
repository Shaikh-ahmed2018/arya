/*classsubjectexamtimetable.js*/

$(document).ready(function() {
	
	$("input.examdate").datepicker({
		dateFormat : "dd-mm-yy",
		yearRange : 'c-65:c+65',
		changeMonth : "true",
		changeYear : "true",
		yearRange : '1997:' + (new Date).getFullYear(),
		onClose : function(selectedDate) {
			var startdate=$('#startdate').val().trim();
			var enddate=$('#enddate').val().trim();
			
			if (($(this).val() != "") && ($(this).val() != undefined) && (startdate <= $(this).val()) && (enddate >= $(this).val())) {
				$(this).datepicker('getDate');
			}else{
				$(".validateTips").text("The Exam Date should be in between the Start Date and End Date");
				$(".errormessagediv").show();
				$(this).val('');
			}
		}
	});
	
	$('.datetimepicker').datetimepicker({
		pickDate : false
	});
	
	$('.datetimepicker1').datetimepicker({
		pickDate : false
	});
	
	
	});