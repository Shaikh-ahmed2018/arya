
function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}



$(document).ready(function() {
	
	
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);
	
	

	
	
	$("#startdate").datepicker({
		dateFormat : "dd-mm-yy",
		defaultDate : "+1w",
		changeMonth : true,
		changeYear : true
		
	});	

	
	$("#todate").datepicker({
		dateFormat : "dd-mm-yy",
		defaultDate : "+1w",
		changeMonth : true,
		changeYear : true
		
	});	
	
	
	$("#dl_issued_date").datepicker(
			{
				dateFormat : "dd-mm-yy",
				yearRange : 'c-65:c+65',
				maxDate : -1,
				changeMonth : "true",
				changeYear : "true",
				yearRange : '1997:' + (new Date).getFullYear(),
				onClose : function(selectedDate) {
					var date2 = $('#dl_issued_date').datepicker(
							'getDate');
					date2.setDate(date2.getDate() + 1);
					$("#dl_validity").datepicker("option",
							"minDate", date2);

				}
			});
	
	
	$("#dl_validity").datepicker(
			{

				dateFormat : "dd-mm-yy",
				yearRange : 'c-65:c+65',
				changeMonth : "true",
				changeYear : "true",
				onClose : function(selectedDate) {

					var date2 = $('#dl_validity').datepicker(
							'getDate');
					date2.setDate(date2.getDate() - 1);
					$("#dl_issued_date").datepicker("option",
							"maxDate", date2);

				}
			});
	
	
	
	
	$("#search").click(function(){
		
		var startDate=$("#startdate").val();
		var toDate=$("#todate").val();
		var department=$("#department").val();
		var itemType=$("#item_type").val();
		
		/*if(startDate=="" && toDate=="" && itemType=="" && department==""){
			
			$("#txtstyle, #txtstyle").slideToggle();
			
			
		}*/
		
		if(startDate==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Start Date");
			
			return false;
			
		}
		
		if(toDate==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select End Date");
			
			return false;
			
		}if(department==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Department");
			
			return false;
			
		}if(itemType==""){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Select Item Type");
			$("#item_type").css({
				border: '1px solid red'
			});
			
			
			return false;
			
		}
		else{
			
			
				$("#notreturnedreport").submit();
			
		}
		
	});
	
	$("#item_type").change(function(){
	$("#item_type").css({
		border: '1px solid transparent'
	});
	});
	
	$("#department").change(function(){
		var department = $('#department').val();

		$.ajax({

			type : "POST",
			url : "adminMenu.html?method=getItemtypeByDepartmnet",
			data : {"department":department},
			async : false,
			success : function(data) {
				
				var result = $.parseJSON(data);
				$("#item_type").empty();
				$("#item_type").append(
						'<option value="' + "" + '">' + ""
								+ '---select---</option>');
				for (var j = 0; j < result.status.length; j++) {
					
					$("#item_type").append(
							'<option value="'
									+ result.status[j].item_type_id
									+ '">'
									+ result.status[j].item_type_name
									+ '</option>');
					
				}
				
			}
		});
		});
	
});


function HideError() 

{
	
document.getElementById("name").style.border = "1px solid #ccc";
document.getElementById("name").style.backgroundColor = "#fff";


}
