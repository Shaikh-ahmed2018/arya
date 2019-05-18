$(document).ready(function(){	
	$("#accyear").val($("#hacademicyaer").val());
	
	$("input,select,textarea").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0 ){
		hideError("#"+$(this).attr("id"));
		
		$(".errormessagediv").hide();
		}
	},
	});
	
	$("#PaymentType").change(function(){
		
		if($("#PaymentType").val() == 'OFFLINE'){
			
			$("#paymode").empty();
			$("#paymode").append('<option value="Cash">Cash</option>'
					+'<option value="Cheque">Cheque</option>'
					+'<option value="DD">DD</option>'
					+'');
			$(".paymenttype").show();	
			
		}
		else if($("#PaymentType").val()== 'ONLINE')
		{
			$("#paymode").empty();
			$("#paymode").append('<option value="Online">Online</option>'
					+'');
			
			$(".paymenttype").show();	
		}
		else{
		
			$(".paymenttype").hide();	
			$("#paymode").empty();
			$("#paymode").append('<option value="ALL">ALL</option>');
		}
		
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
	
$("#endDate").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",
		onSelect:function(dateStr){
			var max = $(this).datepicker('getDate'); // Get selected date
			$("#startDate").datepicker('option', 'maxDate', max); // Set other Max, default to today
		}
	});
	$("#class").change(function(){
		getSection();
		
	});
	
	$("#location").change(function(){
      	getClassList();
    	getTerm();
	});
	$("#reportType").change(function(){
		if($(this).val()=="Date Wise"){
			$(".start,.end").show();
			}
		else if($(this).val()=="Month Wise"){
			$(".start,.end").hide();
			$(".monthwisetext").show();
		}
		else{
			$(".start,.end,.monthwisetext").hide();
		}
	});
$("#search").click(function(){
	
	$(".feeHeader").empty();
	$(".feeHeader").text($("#reportType").val()+" Fee Report");
	if($("#reportType").val()=="Address Wise"){
		getSection();
	if($("#PaymentType").val()== 'ONLINE')
		{
			    getonlinelist();
		}
		else{
			getFeeCollectionPaymodeReport();
		}
		
	
	}
	else if($("#reportType").val()=="Date Wise"){
		getfeecollectiondatelist();
	}
	else if($("#reportType").val()=="Month Wise"){
		if($("#monnthName").val()==""){
			showError("#monnthName","Enter Month Name");
			return false;
		}
		else{
			getFeeCollectionMonthWiseReport();
		}
		
	}
	else if($("#reportType").val()=="Admission No Wise"){
		getFeeCollectionAdmissionWiseReport();
	}
	else if($("#reportType").val()=="Bill No. Wise"){
		getFeeCollectionBillWiseReport();
	}
});

       
       
       $("#print").click(function(){
    	   print();
    	   });
       
       $("#excel").click(function(){
    	   fnExcelReport();
       });
       
       
       
});
function print(){
	 var a=$("#printing").val();
	 var location=$("#location option:selected").text(); 
		var reportType=$("#reportType option:selected").text();
		var accyear=$("#accyear option:selected").text();
		var b = document.getElementById("studentlisttableprint").innerHTML;

	    var abd='<style>' + a +'</style>' + b;
	
	   
	    
   var frame1 = $('<iframe />');
   frame1[0].name = "frame1";
   frame1.css({ "position": "absolute", "top": "-1000000px" });
   $("body").append(frame1);
   var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
   frameDoc.document.open();
   //Create a new HTML document.
   frameDoc.document.write('<html><head><title>DIV Contents</title>');
   //Append the external CSS file.
   frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
   frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
   frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
   frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
   frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
   frameDoc.document.write('<style>th,td{border:1px solid #000 !important;}</style>');
   frameDoc.document.write('</head><body>');
   frameDoc.document.write('<div style="text-align:center;"><h2>'+location+'</h2></div>');
   frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Report Type:</label><div class="col-xs-7">'+reportType+'</div></div></div>');
   frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Academic Year:</label><div class="col-xs-7">'+accyear+'</div></div></div>');
   
   //Append the DIV contents.
   frameDoc.document.write(abd);
   frameDoc.document.write('</body></html>');
   frameDoc.document.close();
   setTimeout(function () {
       window.frames["frame1"].focus();
       window.frames["frame1"].print();
       frame1.remove();
   }, 100);
	    


}
function getClassList(){
	var locationid=$("#location").val();
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

			$('#class').html("");

			$('#class').append('<option value="ALL">' + "ALL"+'</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#class').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}


function getSection(){
	
	datalist={
			"classidVal" : $("#class").val(),
			"locationId":$("#location").val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#section').html("");
			
			$('#section').append('<option value="ALL">' + " ALL "	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#section').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});

	
}
function getFeeCollectionReport(){
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			termId:$("#termName").val(),
			
			
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getFeeCollectionReport",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$(".feefilterheading").empty();
			$(".feefilterheading").append("<hr><div class='col-md-3'><span>Payment Mode : </span><span>"+$("#paymode option:selected").text()+"</span></div><div class='col-md-3'><span>Payment Type : </span><span>"+$("#PaymentType option:selected").text()+"</span></div><div class='col-md-3'><span>Standard : </span><span>"+$("#class option:selected").text()+"</span></div><div class='col-md-3'><span>Division : </span><span>"+$("#section option:selected").text()+"</span></div>");
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+
					"<thead><tr><th>Sl.No.</th>"+
					"<th>Bill Date</th>" +
					"<th>Bill No</th>"+
					"<th>Class</th>"+
					"<th>Term</th>"+
					"<th>Name</th>"+
					"<th>Permanent Address</th>"+
					"<th>Nature Of Payment</th>"+
					"<th>Amount</th>"+
					"</tr></thead>" +
					"<tbody></tbody</table>"
			);
			if(result.FeeReport.length>0){
				
			var totalAmount=0.0;	
			for(var i=0;i<result.FeeReport.length;i++){

				$("#studentlisttable #allstudent tbody").append(
						"<tr>"+
						"<td  style='width:5px;'>"+result.FeeReport[i].sno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].billdate+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].chlnno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].classname+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].termName+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].studentname+"</td>"+
						"<td style='width:252px;'>"+result.FeeReport[i].permanentaddress+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].paymentMode+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].amount_paid_so_far+"</td>"+
						"</tr>"

				);
				totalAmount=totalAmount+Number(result.FeeReport[i].amount_paid_so_far);
			}
			$("#studentlisttable #allstudent tbody").append("<tr>" +
					"<td colspan='8' style='text-align:right;'>Total</td>"+
					"<td>"+totalAmount+"</td>"+
					"</tr>"
					);
			}
			else{
				$("#studentlisttable tbody").append("<tr><td colspan='9'>No Records Found</td></tr>");
			}
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append("No. of Records  "+result.FeeReport.length);

		}
	 
	});
	
}

function getfeecollectiondatelist(){
	$("#loder").show();
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			classid:$("#class").val(),
			termId:$("#termName").val(),
			startdate:$("#startDate").val(),
			enddate:$("#endDate").val(),
			PaymentType:$("#PaymentType").val(),
			paymode:$("#paymode").val(),
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getfeecollectiondatelist",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$(".feefilterheading").empty();
			$(".feefilterheading").append("<hr><div class='col-md-3'><span>Payment Mode : </span><span>"+$("#paymode option:selected").text()+"</span></div><div class='col-md-3'><span>Payment Type : </span><span>"+$("#PaymentType option:selected").text()+"</span></div><div class='col-md-3'><span>Standard : </span><span>"+$("#class option:selected").text()+"</span></div><div class='col-md-3'><span>Division : </span><span>"+$("#section option:selected").text()+"</span></div>");
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+
					"<thead><tr><th>Sl.No.</th>"+
					"<th>Bill Date</th>" +
					"<th>Bill No</th>"+
					"<th>Class</th>"+
					"<th>Term</th>"+
					"<th>Name</th>"+
					"<th>Nature Of Payment</th>"+
					"<th>Amount</th>"+
					"<th>Fine</th>"+
					"</tr></thead>" +
					"<tbody></tbody</table>"
			);
			if(result.FeeReport.length>0){
				var totalAmount=0.0;
			for(var i=0;i<result.FeeReport.length;i++){

				$("#studentlisttable #allstudent tbody").append(
						"<tr>"+
						"<td style='width:5px;'>"+result.FeeReport[i].sno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].billdate+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].chlnno+"</td>"+
						"<td  style='width:10px;'>"+result.FeeReport[i].classname+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].termName+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].studentname+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].paymentMode+"</td>"+
						"<td style='width:50px;'>"+result.FeeReport[i].amount_paid_so_far+"</td>"+
						"<td style='width:50px;'>"+result.FeeReport[i].fineAmount+"</td>"+
						
						"</tr>"

				);

				totalAmount=totalAmount+Number(result.FeeReport[i].amount_paid_so_far);
			}
			$("#studentlisttable #allstudent tbody").append("<tr>" +
					"<td colspan='8' style='text-align:right;'>Total</td>"+
					"<td>"+totalAmount+"</td>"+
					"</tr>"
					);
			}
			else{
				$("#studentlisttable tbody").append("<tr><td colspan='9'>No Records Found</td></tr>");
			}
			$("#loder").hide();
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append("No. of Records  "+result.FeeReport.length);

		}
	 
	});
	
}
function getFeeCollectionMonthWiseReport(){
	$("#loder").show();
	datalist = {
			"location":$("#location").val(),
			"accyear":$("#accyear").val(),
			"classid":$("#class").val(),
			"sectionid":$("#section").val(),
			"termId":$("#termName").val(),
			"monthName":$("#monnthName").val(),
			"PaymentType":$("#PaymentType").val(),
			"paymode":$("#paymode").val(),
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getFeeCollectionMonthWiseReport",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$(".feefilterheading").empty();
			$(".feefilterheading").append("<hr><div class='col-md-3'><span>Payment Mode : </span><span>"+$("#paymode option:selected").text()+"</span></div><div class='col-md-3'><span>Payment Type : </span><span>"+$("#PaymentType option:selected").text()+"</span></div><div class='col-md-3'><span>Standard : </span><span>"+$("#class option:selected").text()+"</span></div><div class='col-md-3'><span>Division : </span><span>"+$("#section option:selected").text()+"</span></div>");
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+
					"<thead><tr><th>Sl.No.</th>"+
					"<th>Bill Date</th>" +
					"<th>Bill No</th>"+
					"<th>Class</th>"+
					"<th>Term</th>"+
					"<th>Name</th>"+
					"<th>Nature Of Payment</th>"+
					"<th>Month Name</th>"+
					"<th>Amount</th>"+
					"<th>Fine</th>"+
					
					"</tr></thead>" +
					"<tbody></tbody</table>"
			);
			if(result.FeeReport.length>0){
				var totalAmount=0.0;
			for(var i=0;i<result.FeeReport.length;i++){
				
				$("#studentlisttable #allstudent tbody").append(
						"<tr>"+
						"<td style='width:5px;'>"+result.FeeReport[i].sno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].billdate+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].chlnno+"</td>"+
						"<td  style='width:10px;'>"+result.FeeReport[i].classname+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].termName+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].studentname+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].paymentMode+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].singleMonthName+"</td>"+
						"<td style='width:50px;'>"+result.FeeReport[i].amount_paid_so_far+"</td>"+
						"<td style='width:50px;'>"+result.FeeReport[i].fineAmount+"</td>"+
						
						"</tr>"

				);

				totalAmount=totalAmount+Number(result.FeeReport[i].amount_paid_so_far);
			}
			
			$("#studentlisttable #allstudent tbody").append("<tr>" +
					"<td colspan='8' style='text-align:right;'>Total</td>"+
					"<td>"+totalAmount+"</td>"+
					"</tr>");
			}
			else{
				$("#studentlisttable tbody").append("<tr><td colspan='10'>No Records Found</td></tr>");
			}
			$("#loder").hide();
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append("No. of Records  "+result.FeeReport.length);

		}
	 
	});
	
}
function getFeeCollectionAdmissionWiseReport(){
	$("#loder").show();
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			classid:$("#class").val(),
			sectionid:$("#section").val(),
			termId:$("#termName").val(),
			PaymentType:$("#PaymentType").val(),
			paymode:$("#paymode").val(),
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getFeeCollectionAdmissionWiseReport",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$(".feefilterheading").empty();
			$(".feefilterheading").append("<hr><div class='col-md-3'><span>Payment Mode : </span><span>"+$("#paymode option:selected").text()+"</span></div><div class='col-md-3'><span>Payment Type : </span><span>"+$("#PaymentType option:selected").text()+"</span></div><div class='col-md-3'><span>Standard : </span><span>"+$("#class option:selected").text()+"</span></div><div class='col-md-3'><span>Division : </span><span>"+$("#section option:selected").text()+"</span></div>");
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+
					"<thead><tr><th>Sl.No.</th>"+
					"<th>Admission No.</th>" +
					"<th>Bill Date</th>" +
					"<th>Bill No</th>"+
					"<th>Class</th>"+
					"<th>Term</th>"+
					"<th>Name</th>"+
					"<th>Nature Of Payment</th>"+
					"<th>Amount</th>"+
					"<th>Fine</th>"+
					
					"</tr></thead>" +
					"<tbody></tbody</table>"
			);
			if(result.FeeReport.length>0){
			var totalAmount=0.0;
				for(var i=0;i<result.FeeReport.length;i++){
				
				$("#studentlisttable #allstudent tbody").append(
						"<tr>"+
						"<td style='width:5px;'>"+result.FeeReport[i].sno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].addmissionno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].billdate+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].chlnno+"</td>"+
						"<td  style='width:10px;'>"+result.FeeReport[i].classname+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].termName+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].studentname+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].paymentMode+"</td>"+
						"<td style='width:50px;'>"+result.FeeReport[i].amount_paid_so_far+"</td>"+
						"<td style='width:50px;'>"+result.FeeReport[i].fineAmount+"</td>"+
						
						"</tr>"

				);

				totalAmount=totalAmount+Number(result.FeeReport[i].amount_paid_so_far)+Number(result.FeeReport[i].fineAmount);
			}
				$("#studentlisttable #allstudent tbody").append("<tr>" +
						"<td colspan='9' style='text-align:right;'>Total</td>"+
						"<td>"+totalAmount+"</td>"+
						"</tr>");
			}
			else{
				$("#studentlisttable tbody").append("<tr><td colspan='9'>No Records Found</td></tr>");
			}
			$("#loder").hide();
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append("No. of Records  "+result.FeeReport.length);

		}
	 
	});
	
}

function getFeeCollectionBillWiseReport(){
	
	$("#loder").show();
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			classid:$("#class").val(),
			sectionid:$("#section").val(),
			termId:$("#termName").val(),
			PaymentType:$("#PaymentType").val(),
			paymode:$("#paymode").val(),
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getFeeCollectionBillWiseReport",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$(".feefilterheading").empty();
			$(".feefilterheading").append("<hr><div class='col-md-3'><span>Payment Mode : </span><span>"+$("#paymode option:selected").text()+"</span></div><div class='col-md-3'><span>Payment Type : </span><span>"+$("#PaymentType option:selected").text()+"</span></div><div class='col-md-3'><span>Standard : </span><span>"+$("#class option:selected").text()+"</span></div><div class='col-md-3'><span>Division : </span><span>"+$("#section option:selected").text()+"</span></div>");
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+
					"<thead><tr><th>Sl.No.</th>"+
					"<th>Admission No.</th>" +
					"<th>Bill Date</th>" +
					"<th>Bill No</th>"+
					"<th>Class</th>"+
					"<th>Term</th>"+
					"<th>Name</th>"+
					"<th>Nature Of Payment</th>"+
					"<th>Amount</th>"+
					"<th>Fine</th>"+
					
					"</tr></thead>" +
					"<tbody></tbody</table>"
			);
			if(result.FeeReport.length>0){
			var totalAmount=0.0;
				for(var i=0;i<result.FeeReport.length;i++){
				
				$("#studentlisttable #allstudent tbody").append(
						"<tr>"+
						"<td style='width:5px;'>"+result.FeeReport[i].sno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].addmissionno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].billdate+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].chlnno+"</td>"+
						"<td  style='width:10px;'>"+result.FeeReport[i].classname+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].termName+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].studentname+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].paymentMode+"</td>"+
						"<td style='width:50px;'>"+result.FeeReport[i].amount_paid_so_far+"</td>"+
						"<td style='width:50px;'>"+result.FeeReport[i].fineAmount+"</td>"+
						
						"</tr>"

				);

				totalAmount=totalAmount+Number(result.FeeReport[i].amount_paid_so_far)+Number(result.FeeReport[i].fineAmount);
			}
				$("#studentlisttable #allstudent tbody").append("<tr>" +
						"<td colspan='9' style='text-align:right;'>Total</td>"+
						"<td>"+totalAmount+"</td>"+
						"</tr>");
			}
			else{
				$("#studentlisttable tbody").append("<tr><td colspan='9'>No Records Found</td></tr>");
			}
			$("#loder").hide();
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append("No. of Records  "+result.FeeReport.length);

		}
	 
	});
	
}

function getFeeCollectionPaymodeReport(){
	$("#loder").show();
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			classid:$("#class").val(),
			sectionid:$("#section").val(),
			paymodid:$("#paymode").val(),
			paymenttype:$("#PaymentType").val(),
			termId:$("#termName").val(),
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getFeeCollectionPaymodeReport",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$(".feefilterheading").empty();
			$(".feefilterheading").append("<hr><div class='col-md-3'><span>Payment Mode : </span><span>"+$("#paymode option:selected").text()+"</span></div><div class='col-md-3'><span>Payment Type : </span><span>"+$("#PaymentType option:selected").text()+"</span></div><div class='col-md-3'><span>Standard : </span><span>"+$("#class option:selected").text()+"</span></div><div class='col-md-3'><span>Division : </span><span>"+$("#section option:selected").text()+"</span></div>");
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+
					"<thead><tr><th>Sl.No.</th>"+
					"<th>Bill Date</th>" +
					"<th>Bill No</th>"+
					"<th>Class</th>"+
					"<th>Term</th>"+
					"<th>Name</th>"+
					"<th>Permanent Address</th>"+
					"<th>Nature Of Payment</th>"+
					"<th>Amount</th>"+
					"</tr></thead>" +
					"<tbody></tbody</table>"
			);
			if(result.FeeReport.length>0){
				var totalAmount=0.0;
			for(var i=0;i<result.FeeReport.length;i++){

				$("#studentlisttable #allstudent tbody").append(
						"<tr>"+
						"<td style='width:5px;'>"+result.FeeReport[i].sno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].billdate+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].chlnno+"</td>"+
						"<td  style='width:10px;'>"+result.FeeReport[i].classname+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].termName+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].studentname+"</td>"+
						"<td style='width:252px;'>"+result.FeeReport[i].permanentaddress+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].paymentMode+"</td>"+
						"<td style='width:150px;'>"+result.FeeReport[i].amount_paid_so_far+"</td>"+
						"</tr>"

				);

				totalAmount=totalAmount+Number(result.FeeReport[i].amount_paid_so_far);
			}
			$("#studentlisttable #allstudent tbody").append("<tr" +
					"<td colspan='8' style='text-align:right;'>Total</td>"+
					"<td>"+totalAmount+"</td>"+
					"</tr>"
					);
			}
			else{
				$("#studentlisttable tbody").append("<tr><td colspan='9'>No Records Found</td></tr>");
			}
			$("#loder").hide();
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append("No. of Records  "+result.FeeReport.length);

		}
	 
	});
	
}

function getonlinelist(){
	$("#loder").show();
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			classid:$("#class").val(),
			sectionid:$("#section").val(),
			paymenttype:$("#paymode").val(),
			paymodid:$("#PaymentType").val(),
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getonlinelist",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$(".feefilterheading").empty();
			$(".feefilterheading").append("<hr><div class='col-md-3'><span>Payment Mode : </span><span>"+$("#paymode option:selected").text()+"</span></div><div class='col-md-3'><span>Payment Type : </span><span>"+$("#PaymentType option:selected").text()+"</span></div><div class='col-md-3'><span>Standard : </span><span>"+$("#class option:selected").text()+"</span></div><div class='col-md-3'><span>Division : </span><span>"+$("#section option:selected").text()+"</span></div>");
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+
					"<thead><tr><th>Sl.No.</th>"+
					"<th>Bill Date</th>" +
					"<th>Bill No</th>"+
					"<th>Class</th>"+
					"<th>Term</th>"+
					"<th>Name</th>"+
					"<th>Permanent Address</th>"+
					"<th>Nature Of Payment</th>"+
					"<th>Amount</th>"+
					"</tr></thead>" +
					"<tbody></tbody</table>"
			);
			if(result.FeeReport.length>0){
				var totalAmount=0.0;
			for(var i=0;i<result.FeeReport.length;i++){

				$("#studentlisttable #allstudent tbody").append(
						"<tr>"+
						"<td style='width:5px;'>"+result.FeeReport[i].sno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].billdate+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].chlnno+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].classname+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].termName+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].studentname+"</td>"+
						"<td style='width:252px;'>"+result.FeeReport[i].permanentaddress+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].paymentMode+"</td>"+
						"<td style='width:150px;'>"+result.FeeReport[i].amount_paid_so_far+"</td>"+
						"</tr>"

				);

				totalAmount=totalAmount+Number(result.FeeReport[i].amount_paid_so_far);
			}
			$("#studentlisttable #allstudent tbody").append("<tr>" +
					"<td colspan='8' style='text-align:right;'>Total</td>"+
					"<td>"+totalAmount+"</td>"+
					"</tr>"
					);
			}
			else{
				$("#studentlisttable tbody").append("<tr><td colspan='9'>No Records Found</td></tr>");
			}
			$("#loder").hide();
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append("No. of Records  "+result.FeeReport.length);

		}
	 
	});

}
function getTerm(){
	datalist={
			"locId" : $("#location").val(),
			"accId" : $("#accyear").val(), 
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getTerm",
		data : datalist,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			var j;
			var len= result.data.length;
			$('#termName').html("");
			$('#termName').append('<option value="all">' +"ALL"+ '</option>');
			for ( j = 0; j <len; j++) {
				$('#termName').append('<option value="'
						+ result.data[j].termname+ '">'
						+ result.data[j].termId
						+ '</option>');
			}
		}
	});
}
function showError(id,errorMessage){
	$(id).focus();
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}

function fnExcelReport()
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('allstudent'); // id of table

    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to TransportReport.xls");
    }  
    else                 //other browser not tested on IE 11
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

    return (sa);
}



