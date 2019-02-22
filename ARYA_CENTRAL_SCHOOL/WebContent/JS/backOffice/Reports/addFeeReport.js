function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
$(document).ready(function() {
	
	$(".successmessagediv").hide();
	setTimeout(function() {
		
	}, 7000);
	$("#examname").val("");
	$("#examdate").val("");
	$("#description").val("");
	$(".errormessagediv").hide();
	

});
$(document).ready(function() {
	
				var hidden = $('#acaid').val();
				$("#accyear option[value=" + hidden + "]").attr('selected', 'true');
				setTimeout("removeMessage()", 3000);
				setInterval(function() {
					$(".errormessagediv").hide();
				}, 3000);
				
				
						
});		

$(document).ready(function() {
	
	$("#classid").change(function(){
		
		var classId=$("#classid").val();
		
		
		$.ajax({
			type : "GET",
			url : "reportaction.html?method=getSectionByClass",
			data : {"classId":classId},
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				
				$("#section").html("");
				$("#section").append(
						'<option value="%%' + "" + '">' + ""
								+ 'All</option>');
				
				for (var j = 0; j < result.SectionList.length; j++) {
					

					$("#section").append(
									'<option value="'
											+ result.SectionList[j].sectionId
											+ '">'
											+ result.SectionList[j].sectionname
											+ '</option>');
				}
			
				
			
			}

		});
		
	});
	$('#accyear').change(function(){
		var accyearid =  $(this).val();
		$.ajax({
			type : "GET",
			url : "feeReport.html?method=AccYearwiseDate",
			data : {"accyearid":accyearid},
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				
				
			
			$("#hstartdate").val(result.enddate);	
			$("#henddate").val(result.startdate);
			//alert(result.enddate);
			$("#examdate").datepicker({
				maxDate:result.enddate-1,
				minDate:result.startdate,
				changeMonth : true,
				changeYear : true,
				dateFormat : "dd-mm-yy",
				onSelect:function(dateStr){
					
					
					        var min = $(this).datepicker('getDate'); // Get selected date
					        $("#enddate").datepicker('option', 'minDate', min || $("#lastDate").val()); // Set other min, default to today
					   
					
				}

			});

			$("#enddate").datepicker({
				maxDate:result.enddate-1,
				minDate:result.startdate,
				changeMonth : true,
				changeYear : true,
				dateFormat : "dd-mm-yy",
				onSelect:function(){
					
					var max = $(this).datepicker('getDate'); // Get selected date
			        $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
				}

			});
			}

		});
		
		
	});
	
	
$("#search").click(function(){

 if ($('#location').val()==""){
		$('.errormessagediv').show();
		$('.validateTips').text("Please Select location");
		document.getElementById('location').style.border = "1px solid #AF2C2C";

		
		
		return false;
	}
	
	else if($('#accyear').val()==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Please Select Academic Year");
		document.getElementById('accyear').style.border = "1px solid #AF2C2C";
		
		return false;
		
		
	}if($('#classid').val()==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Please Select Class");
		document.getElementById('classid').style.border = "1px solid #AF2C2C";
		
		return false;
		
	}else if($('#section').val()==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Please Select Section");
		document.getElementById('section').style.border = "1px solid #AF2C2C";
		return false;
		
	}
	else if($('#examdate').val()==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Please Select Start Date");
		document.getElementById('examdate').style.border = "1px solid #AF2C2C";
		return false;
		
	}else if($('#enddate').val()==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Please Select End Date");
		document.getElementById('enddate').style.border = "1px solid #AF2C2C";
		return false;
		
	}else

	datalist ={
			"accyear" : $('#accyear').val(),
			"classid" :  $('#classid').val(),
			"location" : $('#location').val(),
			"section" :  $('#section').val(),
			"enddate" : $('#enddate').val(),
			"examdate" : $('#examdate').val(),
			
	}
	$.ajax({
		type : "GET",
		url : "feeReport.html?method=getFeeReport",
		data : datalist,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			
		
			
			$("#feetable").empty();
			$("#feetable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Sl.No.</th>"+
					"<th>Student Name</th>" +
					"<th>Term name</th>"+
					"<th>TotalAmount</th>"+
					"<th>PaidAmount</th>"+
					"<th>PaidDate Name</th>" +
					"<th>Status</th>" +
					"</tr>" +
					"</table>"
					);
			for(var i=0;i<result.feelist.length;i++){
			
				$("#feetable #allstudent").append(
						"<tr>"+
				
						"<td>"+result.feelist[i].sno+"</td>"+
						"<td>"+result.feelist[i].studentName+"</td>"+
						"<td>"+result.feelist[i].termname+"</td>"+
						"<td>"+result.feelist[i].totalAmount+"</td>"+
						
						"<td>"+result.feelist[i].paidAmount+"</td>"+
						"<td>"+result.feelist[i].paidDate+"</td>"+
						"<td>"+result.feelist[i].status+"</td>"+
						
						+"</tr>"

				);
				
			}
		}
		
	});


	$("#excelDownload").click(function(){
		
		var haccyear=$("#accyear").val();
		var hstream=$("#stream").val();
		var hclass=$("#class").val();
		
		var hsection=$("#section").val();
		var hlocation=$("#location").val();
	
	
		
		if(haccyear=="" && hstream=="" && hclass=="" && hsection=="" && hlocation=="" ){
			
			$('.errormessagediv').show();
			$('.validateTips').text("First Search the Student Details");
			
		}
		else{
			
			window.location.href = 'feeReport.html?method=FeeExcelReport&accyear='+haccyear+'&stream='+hstream+'&section='+hsection+'&location='+hlocation;
		}	
	});
	
	$("#pdfDownload").click(function(){
		
		
		var haccyear=$("#accyear").val();
		var hstream=$("#stream").val();
		var hclass=$("#class").val();
		
		var hsection=$("#section").val();
		var hlocation=$("#location").val();
	
	
		
		if(haccyear=="" && hstream=="" && hclass=="" && hsection=="" && hlocation=="" ){
			
			$('.errormessagediv').show();
			$('.validateTips').text("First Search the Student Details");
			
		}
		else{
			
			window.location.href = 'feeReport.html?method=FeePDFReport&accyear='+haccyear+'&stream='+hstream+'&section='+hsection+'&location='+hlocation;
	
		}	
	
		
	});
	});
	});


function HideError() 
{
	document.getElementById("accyear").style.border = "1px solid #ccc";
	document.getElementById("accyear").style.backgroundColor = "#fff";

	document.getElementById("classid").style.border = "1px solid #ccc";
	document.getElementById("classid").style.backgroundColor = "#fff";
	
	document.getElementById("location").style.border = "1px solid #ccc";
	document.getElementById("location").style.backgroundColor = "#fff";
	
	document.getElementById("section").style.border = "1px solid #ccc";
	document.getElementById("section").style.backgroundColor = "#fff";
	
	document.getElementById("enddate").style.border = "1px solid #ccc";
	document.getElementById("enddate").style.backgroundColor = "#fff";
	
	document.getElementById("examdate").style.border = "1px solid #ccc";
	document.getElementById("examdate").style.backgroundColor = "#fff";


}