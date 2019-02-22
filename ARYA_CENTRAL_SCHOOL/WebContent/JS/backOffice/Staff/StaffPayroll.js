$(document).ready(function() {
	
	
	$('#editPayroll')
	.click(
			function() {
				
				var teacherId="";
				
				
				var count = 0;
				$(
						'input.vehicle_Checkbox_Class:checkbox:checked')
						.map(
								function() {

									 teacherId = $(this).attr("id");
									

									count++;
								});
				if (count == 0 || count > 1) {
					$(".errormessagediv").show();
					$(".validateTips").text("Check any one checkbox");
					return false;

				} else {
				
					window.location.href = "staffPayroll.html?method=getPayrolEntry&teachercode="+teacherId;
				
				}
			});
	

	$("#excelDownload").click(function(){
		
		
		window.location.href = 'staffPayroll.html?method=payrollExcelReport&month='
			+ $("#hmonth").val()
			+ ' &year='
			+ $("#year").val();
		
	});
	
	$("#pdfDownload").click(function(){
		
		window.location.href = 'staffPayroll.html?method=payrollPDFReport&month='
			+ $("#hmonth").val()
			+ ' &year='
			+ $("#year").val();
	});
	
	
	$("#generatePayslip").click(function(){
		
		var count = 0;
		var psyslipId="";
		$('input.sno:checkbox:checked').map(function() {
			

			psyslipId = $(this).attr("id");

			count++;
		});
		
		if (count == 0 || count > 1) {
			
			$(".errormessagediv").show();
			$(".validateTips").text("Check any one checkbox");
			return false;

		} else {
		
			
			$.ajax({
				type : "GET",
				url : "staffPayroll.html?method=createPayslip",
				data : {
					
					"psyslipId" :psyslipId
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					
					
					if(result.SuccessMessage!=undefined){
						
						
						$(".successmessagediv").show();
						$("#successmessagediv").text(result.SuccessMessage);
						
					}else{
						
						$(".errormessagediv").show();
						$(".validateTips").text(result.errorMessage);
						
						
						
					}
					
				
				
				}

			});
			
		
		}
		
	});
	
	
	
	$('#excel')
	.click(
			function() {
				
				window.location.href = 'staffPayroll.html?method=downloadpayrolllistingXLS';
				
			});
	$("#pdf").click(function(){
		
		window.location.href = "staffPayroll.html?method=downloadpayrolllistingPDF";
			
	});
	
	
	
});
