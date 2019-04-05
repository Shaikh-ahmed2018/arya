$(document).ready(function(){	
	$("#accyear").val($("#hacademicyaer").val());

 $("#location").change(function(){
	
		var location=$("#location").val();
		getClassList();
		var classname=$("#class").val();
		var paymenttype=$(this).val();
		var paymode=$(this).val();
		if($("#PaymentType").val()== 'ONLINE')
		{		
		getonlinelist();
		}
		else if($("#paymode").val()== 'Cash')
		{ 
			getFeeCollectionPaymodeReport();
		}
		else if($("#paymode").val()== 'DD')
		{ 
			getFeeCollectionPaymodeReport();
		}
		else if($("#paymode").val()== 'Cheque')
		{ 
			getFeeCollectionPaymodeReport();
		}
	else{
	}
		//getTransportFeeList();
		
	});
	
	$("#accyear").change(function(){
		var location=$("#location").val();
		var accyear=$("#accyear").val();
		getClassList();
		getTerm();
		getTransportFeeList();
		
	});
	
	$("#class").change(function(){
		var accyear=$("#accyear").val();
		var classId=$("#class").val();
		var location=$("#location").val();
		getSectionList(classId,location);
		if($("#reportType").val()=="TransportFee"){
			getTerm();
			getTransportFeeList();
		}
		
		else{
			getRouteWiseStudentDetailwithClassAndSection();
		}

	});
	
	$("#section").change(function()
			{
		var location=$("#location").val();
		var accyear=$("#accyear").val();
		var section=$("#section").val();
		if($("#reportType").val()=="TransportFee"){
			getTerm();
			getTransportFeeList();
		}
		else{
			getRouteWiseStudentDetailwithClassAndSection();
		}
		
		
			});
	$("#term").change(function(){
		getTransportFeeList();
	});
	
	$("#termstatus").change(function(){
		var termstatus=$("#termstatus").val();
		getTransportFeeList();
	});
	
//edited by anu
	
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
		getfeecollectiondatelist();
	}
});
$("#transpt").change(function(){
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


$("#endDate").change(function(){
		getfeecollectiondatelist();
		
	});




//payment online
$("#PaymentType").change(function(){
	
		var paymenttype=$(this).val();
		//alert(paymenttype)
		if($("#PaymentType").val()== 'ONLINE')
			{		
			getonlinelist();
			}
		else if($("#PaymentType").val()== 'OFFLINE')
		{		
			getFeeCollectionPaymodeReport();
		}
		else{
			getTransportFeeList();
		}
	
	
});
//payment offline
$("#paymode").change(function(){
	
		if($("#paymode").val()== 'DD')
			{ 
				getFeeCollectionPaymodeReport();
			}
		else if($("#paymode").val()== 'Cash')
			{ 
				getFeeCollectionPaymodeReport();
			}
		else if($("#paymode").val()== 'Cheque')
			{ 
				getFeeCollectionPaymodeReport();
			}
		else{
		}
	
});

//end
	$("#excel").click(function(){
		fnExcelReport();
	});

	$("#pdfDownload").click(function(){
		var location=$("#location").val();
		var accyear=$("#accyear").val();
		var classId=$("#class").val();
		var section=$("#section").val();
		var term=$("#term").val();
		var termstatusId=$("#termstatus").val();
		var classname=$("#class option:selected").text()+" - "+$("#section option:selected").text(); 
		var accYear=$("#accyear option:selected").text();
		
		
		
	
			window.location.href = 'transportfeereceipt.html?method=getTransportFeePDFReport&location='+location+'&accyear='+accyear+'&classId='+classId+'&section='+section+'&term='+term+'&termstatusId='+termstatusId+'&classname='+classname+'&accYear='+accYear+'';
		
	});
	
	
	$("#print").click(function(){
		var location=$("#location option:selected").text(); 
		var reportType=$("#reportType option:selected").text();
		var accyear=$("#accyear option:selected").text();
		
		var classname=$("#class option:selected").text();
		var section=$("#section option:selected").text();
		var route=$("#route option:selected").text();
		var termstatus=$("#termstatus option:selected").text();
		var term=$("#term option:selected").text();
		var report=$("#reportType").val();
		
		 var b= document.getElementById("studenttable").innerHTML;
	  	   var abd= b;
	  	    var frame1 = $('<iframe />');
		        frame1[0].name = "frame1";
		        frame1.css({ "position": "absolute", "top": "-1000000px" });
		        $("body").append(frame1);
		        var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
		        frameDoc.document.open();
		        //Create a new HTML document.
		        frameDoc.document.write('<html><head><title>DIV Contents</title>');
		        //Append the external CSS file.
		        frameDoc.document.write('<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>');
		        frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
		        frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
		        frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
		        frameDoc.document.write('<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>');
		        frameDoc.document.write('<script type="text/javascript" src="JS/backOffice/Reports/TransportFeeReport.js"></script>');
		        frameDoc.document.write('<style>th,td{border:1px solid #000 !important;}</style>');
		        frameDoc.document.write('</head><body>');
		      
		        
		        frameDoc.document.write('<div style="text-align:center;"><h2>'+location+'</h2></div>');
		        frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Report Type :</label><div class="col-xs-7">'+reportType+'</div></div></div>');
		        frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Academic Year :</label><div class="col-xs-7">'+accyear+'</div></div></div>');
		       
		        if(report=="TransportFee"){
		        	frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Class :</label><div class="col-xs-7">'+classname+'</div></div></div>');
			        frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Division :</label><div class="col-xs-7">'+section+'</div></div></div>');
			        
			        frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Term :</label><div class="col-xs-7">'+term+'</div></div></div>');
			        frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">payment Status :</label><div class="col-xs-7">'+termstatus+'</div></div></div>');
				}
		        else if(report=="BusRouteDetail"){
		        	frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Route :</label><div class="col-xs-7">'+route+'</div></div></div>');
		        } 
		        else if(report=="RouteWiseStudentDetail"){
		        	frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Route :</label><div class="col-xs-7">'+route+'</div></div></div>');
		        } 
		        else if(report=="RouteWiseStudentDetailwithClassAndSection"){
		        	frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Class :</label><div class="col-xs-7">'+classname+'</div></div></div>');
			        frameDoc.document.write('<div class="col-xs-6"><div class="form-group clearfix"><label for="inputPassword" class="control-label col-xs-5" id="inputnames" style="text-align: right;">Division :</label><div class="col-xs-7">'+section+'</div></div></div>');
		        } 
		        
		        frameDoc.document.write(abd);
		        
		        frameDoc.document.write('</body></html>');
		        frameDoc.document.close();
		        setTimeout(function () {
		            window.frames["frame1"].focus();
		            window.frames["frame1"].print();
		            frame1.remove();
		        }, 100);
		
	});

	
	$("#reportType").change(function(){
	var reporttype=$(this).val();
		if($(this).val()=="TransportFee"){
			$(".transportOtherReport").slideUp("fast",function(){
				$(".transportFeeReportandStudentClassWise,.transportFeeReport").show();
				getTransportFeeList();
				
			});
			/*if($("#PaymentType").val()== 'ONLINE')
			{
				    getonlinelist();
			}
			else{
				getFeeCollectionPaymodeReport();
			}*/
		}
		else if($(this).val()==""){
			$(".transportFeeReportandStudentClassWise,.transportFeeReport").hide();
		}
		
		else if($(this).val()=="RouteWiseStudentDetailwithClassAndSection"){
			$(".transportOtherReport,.transportFeeReport").fadeOut("fast",function(){
				$(".StudentClassWise,.transportFeeReportandStudentClassWise").show();
				
				getRouteWiseStudentDetailwithClassAndSection();
			});
		}
		else{
			
			getRoute();
		
			$(".transportFeeReportandStudentClassWise").slideUp("fast",function(){
				$(".transportOtherReport").show();
				if(reporttype=="BusRouteDetail"){
					getBusRouteDetail();
				}
				else{
					getRouteWiseStudentDetail();
				}
			});
		}
	});
	
	$("#route").click(function(){
		if($("#reportType").val()=="BusRouteDetail")
		getBusRouteDetail();
		else 
			getRouteWiseStudentDetail();	
	});
	
});

function getTerm(){

	var accyear=$("#accyear").val();
	var location=$("#location").val();
	datalist={
			"accyear" : accyear,
			"location":location
	},
	$.ajax({
		type : 'POST',
		url : "transportfeereceipt.html?method=getTerm",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#term').empty();
			$('#term').append('<option value="">'+ "ALL"+ '</option>');

			for ( var j = 0; j < result.termlist.length; j++)//Here termlist is the name given on key as JSONObject.
			{
				$('#term').append(
						'<option value="'+ result.termlist[j].termId+ '">'//Here termId is same name of vo class name.
						+ result.termlist[j].termname+'</option>');
			}
		}
	});
}

function validateloc()
{
	var location=$("#location").val();
	
	if(location==""){
		$('.errormessagediv').show();
		$('.validateTips').text("Select Location");
		return false;
	}else{
		return true;
	}

	
	}

function validate(){
  
	var location=$("#location").val();
	var accyear=$("#accyear").val();
	var classId=$("#class").val();
	var section=$("#section").val();
	var term=$("#term").val();

	if(location=="" && accyear=="" && classId=="" && section=="" && term==""){

		$("#txtstyle, #txtstyle").slideToggle();
	}

	if(location==""){
		$('.errormessagediv').show();
		$('.validateTips').text("Select Location");
		return false;
	}
	else if(accyear==""){
		$('.errormessagediv').show();
		$('.validateTips').text("Select Academic Year");
		return false;
	}
	else if(classId==""){
		$('.errormessagediv').show();
		$('.validateTips').text("Select Class");
		return false;
	}
	else if(section==""){
		$('.errormessagediv').show();
		$('.validateTips').text("Select Section");
		return false;
	}
	else if(term==""){
		$('.errormessagediv').show();
		$('.validateTips').text("Select Term");
		return false;
	}
	else{
		return true;
	}
}
function getBusRouteDetail(){

	var location=$("#location").val();
	var accyear=$("#accyear").val();
	
	var routeNo=$("#route").val();
	
	
	
	datalist ={
			"location":location,
			"accyear" :accyear,
			"routeNo":routeNo,
	};
	$.ajax({

		type : "POST",
		url :"transportfeereceipt.html?method=getBusRouteDetail",
		data : datalist,
		async:false,
		success:function(data){
			var result = $.parseJSON(data);

			$("#studenttable").empty();
			$("#studenttable").append("<table class='table' id='allstudent' width='100%'" +">"
					+"<tr><th>SI No</th>"+
					
					"<th>Route No.</th>" +
					"<th>Bus Point Name</th>" +
					"<th>Amount</th>"+
					"</table>"
			);
			if(result.busroute.length>0)
				{
		
				for(var i=0;i<result.busroute.length;i++){

					$("#allstudent").append(
							"<tr>"+
							"<td>"+result.busroute[i].sno+"</td>"+
							"<td>"+result.busroute[i].routeName+"</td>"+
							"<td>"+result.busroute[i].stage_name+"</td>"+
							"<td>"+result.busroute[i].amount+"</td>"+
							
							+"</tr>"
					);
				}
				}else{
				$("#allstudent tbody").append("<tr><td colspan=4'>No Record Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.busroute.length);
			
		}
	});

	


	
	
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

			$('#class').append('<option value="">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#class').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}


function getSectionList()
{
	
	var classId=$("#class").val();
  var location=$("#location").val();

	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getSectionByClassLoc",
		data : {"classId":classId,
			     "location":location
			     },
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);

			$("#section").html("");
			$("#section").append('<option value="">' + "All"	+ '</option>');
			//alert(result.SectionList.length);
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

}
function getRouteWiseStudentDetail(){
	var location=$("#location").val();
	var accyear=$("#accyear").val();
	
	var routeNo=$("#route").val();
	
	
	
	datalist ={
			"location":location,
			"accyear" :accyear,
			"routeNo":routeNo,
	};
	$.ajax({

		type : "POST",
		url :"transportfeereceipt.html?method=getRouteWiseStudentDetail",
		data : datalist,
		async:false,
		success:function(data){
			var result = $.parseJSON(data);

			$("#studenttable").empty();
			$("#studenttable").append("<table class='table' id='allstudent' width='100%'" +">"
					+"<tr><th>SI No</th>"+
					"<th>Admission No.</th>" +
					"<th>Name</th>" +
					"<th>Class Div.</th>"+
				
					"<th>Route</th>"+
					"<th>Buspoint</th>"+
					"<th>Amount</th>"+
					"<th>Contact Person</th>"+
					"<th>Contact No.</th>"+
					"</tr>" +
					"</table>"
			);
			if(result.studentList.length>0)
				{
		
				for(var i=0;i<result.studentList.length;i++){

					$("#allstudent").append(
							"<tr>"+
							"<td>"+(i+1)+"</td>"+
							"<td>"+result.studentList[i].admisssion_no+"</td>"+
							"<td>"+result.studentList[i].student_name+"</td>"+
							"<td>"+result.studentList[i].classname+"</td>"+
							"<td>"+result.studentList[i].routeName+"</td>"+
							"<td>"+result.studentList[i].stage_name+"</td>"+
							"<td>"+result.studentList[i].distance+"</td>"+
							"<td>"+result.studentList[i].costPerPerson+"</td>"+
							"<td>"+result.studentList[i].mobileNo+"</td>"+
							+"</tr>"
					);
				}
				}else{
				$("#allstudent tbody").append("<tr><td colspan=9'>No Record Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentList.length);
			
		}
	})
	
}
function getRouteWiseStudentDetailwithClassAndSection()
{
	var location=$("#location").val();
	var accyear=$("#accyear").val();
	var classId=$("#class").val();
	var section=$("#section").val();
	
	
	
	datalist ={
			"location":location,
			"accyear" :accyear,
			"classId" :classId,
			"section" :section,
			
	};
	$.ajax({

		type : "POST",
		url :"transportfeereceipt.html?method=getRouteWiseStudentDetailwithClassAndSection",
		data : datalist,
		async:false,
		success:function(data){
			var result = $.parseJSON(data);

			$("#studenttable").empty();
			$("#studenttable").append("<table class='table' id='allstudent' width='100%'" +">"
					+"<tr><th>SI No</th>"+
					"<th>Admission No.</th>" +
					"<th>Name</th>" +
					"<th>Class Div.</th>"+
				
					"<th>Route</th>"+
					"<th>Buspoint</th>"+
					"<th>Amount</th>"+
					"<th>Contact Person</th>"+
					"<th>Contact No.</th>"+
					"</tr>" +
					"</table>"
			);
			if(result.studentList.length>0)
				{
		
				for(var i=0;i<result.studentList.length;i++){

					$("#allstudent").append(
							"<tr>"+
							"<td>"+(i+1)+"</td>"+
							"<td>"+result.studentList[i].admisssion_no+"</td>"+
							"<td>"+result.studentList[i].student_name+"</td>"+
							"<td>"+result.studentList[i].classname+"</td>"+
							"<td>"+result.studentList[i].routeName+"</td>"+
							"<td>"+result.studentList[i].stage_name+"</td>"+
							"<td>"+result.studentList[i].distance+"</td>"+
							"<td>"+result.studentList[i].costPerPerson+"</td>"+
							"<td>"+result.studentList[i].mobileNo+"</td>"+
							+"</tr>"
					);
				}
				}else{
				$("#allstudent tbody").append("<tr><td colspan=9'>No Record Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentList.length);
			
		}
	});



}
function getTransportFeeList()
{
	var location=$("#location").val();
	var accyear=$("#accyear").val();
	var classId=$("#class").val();
	var section=$("#section").val();
	var term=$("#term").val();
	var termstatusId=$("#termstatus").val();
	
	
	
	datalist ={
			"location":location,
			"accyear" :accyear,
			"classId" :classId,
			"section" :section,
			"term":term,
			"termstatusId":termstatusId,
	};
	$.ajax({

		type : "POST",
		url :"transportfeereceipt.html?method=gettransportfeeDetails",
		data : datalist,
		async:false,
		success:function(data){
			var result = $.parseJSON(data);

			$("#studenttable").empty();
			$("#studenttable").append("<table class='table' id='allstudent' width='100%'" +">"
					+"<center><tr><th>SI No</th>"+
					"<th>Admission No.</th>"  +
					"<th>Term</th>" +
					"<th>Name</th>" +
					"<th>Class</th>"+
					"<th>Section</th>"+
					"<th>Status</th>"+
					"<th>Amount paid</th>"+
					"</center></tr>" +
					"</table>"
			);
			if(result.studentList.length>0)
				{
				var totalAmount=0.0;
				for(var i=0;i<result.studentList.length;i++){

					$("#allstudent").append(
							"<tr>"+
							"<td>"+result.studentList[i].sno+"</td>"+
							"<td>"+result.studentList[i].admisssion_no+"</td>"+
							"<td>"+result.studentList[i].termname+"</td>"+
							"<td>"+result.studentList[i].studentnamelabel+"</td>"+
							"<td>"+result.studentList[i].classname+"</td>"+
							"<td>"+result.studentList[i].sectionname+"</td>"+
							"<td>"+result.studentList[i].status+"</td>"+
							"<td>"+result.studentList[i].amount_paid+"</td>"+
							+"</tr>"
					);
					totalAmount=totalAmount+Number(result.studentList[i].amount_paid);
					
				}
				$("#studenttable #allstudent tbody").append("<tr>" +
						"<td colspan='7' style='text-align:right;'>Total</td>"+
						"<td>"+totalAmount+"</td>"+
						"</tr>"
						);
				}else{
				$("#allstudent tbody").append("<tr><td colspan=7'>No Record Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentList.length);
			
		}
	});



}

function getRoute(){
	
	
	var accyear=$("#accyear").val();
	
	datalist ={
			"accyear" :accyear,
	};
	
	$.ajax({
		type:"POST",
		url :"transportfeereceipt.html?method=route",
		data : datalist,
		async:false,
		success : function(data) {
			var result = $.parseJSON(data);

			$("#route").html("");
			$("#route").append('<option value="all">' + "ALL"	+ '</option>');

			for (var j = 0; j < result.routeList.length; j++) {
				$("#route").append(
						'<option value="'
						+ result.routeList[j].routeCode
						+ '">'
						+ result.routeList[j].routeNo
						+ '</option>');

			}
		}
	});
}
function getonlinelist(){
	//alert("lout")
	$("#loder").show();
	//alert("lout2")
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			classid:$("#class").val(),
			sectionid:$("#section").val(),
			term:$("#term").val(),
			termstatusId:$("#termstatus").val(),
			paymenttype:$("#paymode").val(),
			paymodid:$("#PaymentType").val(),
	},

$.ajax({

		type : "POST",
		url : "transportfeereceipt.html?method=getonlinelist",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			//console.log(result);
			$(".feefilterheading").empty();
			$(".feefilterheading").append("<hr><div class='col-md-3'><span>Payment Mode : </span><span>"+$("#paymode option:selected").text()+"</span></div><div class='col-md-3'><span>Payment Type : </span><span>"+$("#PaymentType option:selected").text()+"</span></div><div class='col-md-3'><span>Standard : </span><span>"+$("#class option:selected").text()+"</span></div><div class='col-md-3'><span>Section : </span><span>"+$("#section option:selected").text()+"</span></div>");
			$("#studenttable").empty();
			$("#studenttable").append("<table class='table' id='allstudent' width='100%'" +">"
					+"<center><tr><th>Sl.No.</th>"+
					"<th>Name</th>" +
					"<th>Class</th>"+
					"<th>Term</th>"+
					"<th>Payment Type</th>"+
					"<th>Bill Date</th>"+
					"<th>Bill No</th>"+
					"<th>Amount</th>"+
					"</tr></center>" +
					"</table>"
			);
			if(result.studentList.length>0){
				var totalAmount=0.0;
			for(var i=0;i<result.studentList.length;i++){
				$("#studenttable #allstudent tbody").append(
						"<tr>"+
						"<td>"+result.studentList[i].sno+"</td>"+
						"<td>"+result.studentList[i].studentname+"</td>"+
						"<td>"+result.studentList[i].classname+"</td>"+
						"<td>"+result.studentList[i].termName+"</td>"+
						"<td>"+result.studentList[i].paymentMode+"</td>"+
						"<td>"+result.studentList[i].billdate+"</td>"+
						//"<td style='width:252px;'>"+result.studentList[i].permanentaddress+"</td>"+
						"<td>"+result.studentList[i].chlnno+"</td>"+
						"<td>"+result.studentList[i].amount_paid_so_far+"</td>"+
						+"</tr>"

				);

				totalAmount=totalAmount+Number(result.studentList[i].amount_paid_so_far);
				
			}
			$("#studenttable #allstudent tbody").append("<tr>" +
					"<td colspan='7' style='text-align:right;'>Total</td>"+
					"<td>"+totalAmount+"</td>"+
					"</tr>"
					);
			}
			else{
				$("#studenttable tbody").append("<tr><td colspan='7'>No Records Found</td></tr>");
			}
			$("#loder").hide();
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append("No. of Records  "+result.studentList.length);

		}
	 
	});

}
function getFeeCollectionPaymodeReport($type=null){
	$("#loder").show();
	//alert(innn)
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			classid:$("#class").val(),
			sectionid:$("#section").val(),
			paymodid:$("#paymode").val(),
			paymenttype:$("#PaymentType").val(),
			termId:$("#term").val(),
	},

	$.ajax({

		type : "POST",
		url : "transportfeereceipt.html?method=getFeeCollectionPaymodeReport",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$(".feefilterheading").empty();
			$(".feefilterheading").append("<hr><div class='col-md-3'><span>Payment Mode : </span><span>"+$("#paymode option:selected").text()+"</span></div><div class='col-md-3'><span>Payment Type : </span><span>"+$("#PaymentType option:selected").text()+"</span></div><div class='col-md-3'><span>Standard : </span><span>"+$("#class option:selected").text()+"</span></div><div class='col-md-3'><span>Division : </span><span>"+$("#section option:selected").text()+"</span></div>");
			$("#studenttable").empty();
			$("#studenttable").append("<table class='table' id='allstudent' width='100%'" +">"
					+"<center><tr><th>Sl.No.</th>"+
					"<th>Name</th>" +
					"<th>Class</th>"+
					"<th>Term</th>"+
					"<th>Payment Type</th>"+
					"<th>Bill Date</th>"+
					//"<th>Permanent Address</th>"+
					"<th>Bill No</th>"+
					"<th>Amount</th>"+
					"</tr></center>" +
					"</table>"
			);
			if(result.studentList.length>0){
				var totalAmount=0.0;
			for(var i=0;i<result.studentList.length;i++){

				$("#studenttable #allstudent tbody").append(
						"<tr>"+
						"<td>"+result.studentList[i].sno+"</td>"+
						"<td>"+result.studentList[i].studentname+"</td>"+
						"<td>"+result.studentList[i].classname+"</td>"+
						"<td>"+result.studentList[i].termName+"</td>"+
						"<td>"+result.studentList[i].paymentMode+"</td>"+
						"<td>"+result.studentList[i].billdate+"</td>"+
						//"<td style='width:252px;'>"+result.studentList[i].permanentaddress+"</td>"+
						"<td>"+result.studentList[i].chlnno+"</td>"+
						"<td>"+result.studentList[i].amount_paid_so_far+"</td>"+
						+"</tr>"

				);

				totalAmount=totalAmount+Number(result.studentList[i].amount_paid_so_far);
			}
			$("#studenttable #allstudent tbody").append("<tr>" +
					"<td colspan='7' style='text-align:right;'>Total</td>"+
					"<td>"+totalAmount+"</td>"+
					"</tr>"
					);
			}
			else{
				$("#studenttable tbody").append("<tr><td colspan='9'>No Records Found</td></tr>");
			}
			$("#loder").hide();
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append("No. of Records  "+result.studentList.length);

		}
	 
	});
	
}
function getfeecollectiondatelist(){
	$("#loder").show();
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			classid:$("#class").val(),
			termId:$("#term").val(),
			startdate:$("#startDate").val(),
			enddate:$("#endDate").val(),
			PaymentType:$("#PaymentType").val(),
			paymode:$("#paymode").val(),
	},

	$.ajax({

		type : "POST",
		url : "transportfeereceipt.html?method=getfeecollectiondatelist",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$(".feefilterheading").empty();
			$(".feefilterheading").append("<hr><div class='col-md-3'><span>Payment Mode : </span><span>"+$("#paymode option:selected").text()+"</span></div><div class='col-md-3'><span>Payment Type : </span><span>"+$("#PaymentType option:selected").text()+"</span></div><div class='col-md-3'><span>Standard : </span><span>"+$("#class option:selected").text()+"</span></div><div class='col-md-3'><span>Division : </span><span>"+$("#section option:selected").text()+"</span></div>");
			$("#studenttable").empty();
			$("#studenttable").append("<table class='table' id='allstudent' width='100%'" +">"
					+"<center><tr><th>Sl.No.</th>"+
					"<th>Name</th>" +
					"<th>Class</th>"+
					"<th>Term</th>"+
					"<th>Payment Type</th>"+
					"<th>Bill Date</th>"+
					"<th>Bill No</th>"+
					"<th>Amount</th>"+
					"</tr></center>" +
					"</table>"
			);
			if(result.studentList.length>0){
				var totalAmount=0.0;
			for(var i=0;i<result.studentList.length;i++){

				$("#studenttable #allstudent tbody").append(
						"<tr>"+
						"<td>"+result.studentList[i].sno+"</td>"+
						"<td>"+result.studentList[i].studentname+"</td>"+
						"<td>"+result.studentList[i].classname+"</td>"+
						"<td>"+result.studentList[i].termName+"</td>"+
						"<td>"+result.studentList[i].paymentMode+"</td>"+
						"<td>"+result.studentList[i].billdate+"</td>"+
						"<td>"+result.studentList[i].chlnno+"</td>"+
						"<td>"+result.studentList[i].amount_paid_so_far+"</td>"+
						
						"</tr>"

				);

				totalAmount=totalAmount+Number(result.studentList[i].amount_paid_so_far);
			}
			$("#studenttable #allstudent tbody").append("<tr>" +
					"<td colspan='7' style='text-align:right;'>Total</td>"+
					"<td>"+totalAmount+"</td>"+
					"</tr>"
					);
			}
			else{
				$("#studenttable tbody").append("<tr><td colspan='9'>No Records Found</td></tr>");
			}
			$("#loder").hide();
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append("No. of Records  "+result.studentList.length);

		}
	 
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