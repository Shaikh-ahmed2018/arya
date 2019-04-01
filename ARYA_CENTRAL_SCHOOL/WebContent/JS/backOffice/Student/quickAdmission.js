$(document).ready(function(){
	$("#Acyearid").change(function(){
		//$("#searchvalue").val("");
		//changeAccYear();
		getClassList();
		var classname=$("#classname").val();
		getSectionList(classname);
	});
	$("#locationname").change(function(){
		//$("#searchvalue").val("");
		//changeAccYear();
		getClassList();
		var classname=$("#classname").val();
		getSectionList(classname);
	});
	$("#classname").change(function(){
		//$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		
		
		getSectionList(classname);
		//getStudentList(locationid,accyear,classname);
	});
	$("#sectionid").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		
		//getStudentListBySection(locationid,accyear,classname,sectionid);
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

// saving detils with validations

$("#save").click(function()  {
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var stream=$("sectionid").val();
	var studname=$("#studnameid").val();
	var parentname=$("#fathernameid").val();
	var dateofadmsn=$("#startDate").val();
	var gender=$("input[name='gender']:checked").val();
	if(studname==""){

		$('.errormessagediv').show();
		$('.validateTips').text("Field  Required  Name");
	}
	else if(parentname=="")
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Field  Required  parent Name");
		}
	else if(stream=="")
	{
		$('.errormessagediv').show();
		$('.validateTips').text("Field Required Stream");

	}
	else if(classname=="")
	{
		$('.errormessagediv').show();
		$('.validateTips').text("Field Required Class");
	}
	else if(gender=="")
		{
		$('.errormessagediv').show();
		$('.validateTips').text("Field Required Gender (Male/Female)");
		}	
	
	else
	{
		var datalist = {
				"studname" : studname,
				"parentname" : parentname,
				"dateofadmsn" : dateofadmsn,
				"stream" : stream,
				"classname" : classname,
				
			
		};


		$.ajax({

			type : "POST",

			url : "adminMenu.html?method=quickAdmission",

			data : datalist,

			success : function(data) {
				var result = $.parseJSON(data);
				alert(result)
				$(".errormessagediv").hide();
				if(result.status == "saved successfully"){
			        $(".successmessagediv").show();
					//$(".validateTips").text("Updating Record Progressing...");
					
				}
			}



		});
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