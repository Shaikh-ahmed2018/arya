
function myFunction() {
	    
			         document.getElementById("myForm").submit();   
				  }




$(document).ready(function() {
	$("#Acyearid").change(function(){
		getClassList();
		//getTerm();
		//getDefaulterFeeList();
		var classname=$("#classname").val();
		});
	$("#locationname").change(function(){

		getClassList();
		//getTerm();
		//getDefaulterFeeList();
		var classname=$("#classname").val();
	
});
	$("#classname").change(function(){
		
		
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		getSectionList(classname);
		//getDefaulterFeeList();
		
	});
	$("#sectionid").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		//getDefaulterFeeList();
		
	});
$("#startDate").datepicker({
		
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",
		onSelect:function(dateStr){
			
			
			        var min = $(this).datepicker('getDate'); // Get selected date
			        $("#endDate").datepicker('option', 'minDate', min); // Set other min, default to today
			   
			
		}

	});

	
});



function getClassList(){
	var locationid=$("#locationname").val();
	datalist={
			"locationid" : locationid
	},

	$.ajax({

		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classname').html("");

			$('#classname').append('<option value="All">' + "----------Select----------"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}
function getSectionList(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname").val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});}