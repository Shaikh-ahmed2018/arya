$(document).ready(function() {
	var hacademicyear=$('#hacademicyaer').val();
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');				

	getMonthList();
	$('#Acyearid').change(function(){
		getMonthList();
		getPayRollYearListByLocation();
	});
	$('#monthId').change(function(){
		getPayRollYearListByLocation();
	});
	
	$('#locationname').change(function(){
		getPayRollYearListByLocation();
	});
	/*var url=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(url.split("&")[0] == "teachermenuaction.html?method=createPayslip"){
		setTimeout(function(){
			window.location.href="teachermenuaction.html?method=generatePayroll";
		},3000);
	}*/
	
	$('#generatepayroll').click(function(){
		var status= $("input[type='radio']:checked").closest("tr").find('.status').text();
	
		 if($('input[type=radio]:checked').length == 0) {
			  $(".errormessagediv").show();
			  $(".validateTips").text("Select Any One Record");
			  setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			 return false;
		 }else if(status=="Generated"){
			 var x = confirm("Payroll already Generated. Do you want to Continue");
			 if (x){
				 document.getElementById("submitPayrollForm").submit();
			 }
		 }else{
			 document.getElementById("submitPayrollForm").submit();
		 }
	});
	
	$('#payslipid').click(function(){
		 if($('input[type=radio]:checked').length == 0) {
			  $(".errormessagediv").show();
			  $(".validateTips").text("Select Any One Record");
			  setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			 return false;
		 }else {
			 var status= $("input[type='radio']:checked").val();
				checkval=status.split(",");
			 if(checkval[1]=="Not Generated"){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Payroll Not Generated For That Month,Please Generate Payroll First");
				 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
				 return false; 
			 }else{
				 window.location.href="teachermenuaction.html?method=createPayslip&accyearId="+checkval[0]
				 	+"&monthId="+checkval[2]
					+"&locationId="+checkval[3]
					+"&yearId="+checkval[4];
			 }
		 }
	});
	
	$('#excelid').click(function(){
		 if($('input[type=radio]:checked').length == 0) {
			  $(".errormessagediv").show();
			  $(".validateTips").text("Select Any One Record");
			  setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			 return false;
		 }else {
			 var status= $("input[type='radio']:checked").val();
				checkval=status.split(",");
			 if(checkval[1]=="Not Generated"){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Payroll Not Generated For That Month,Please Generate Payroll First");
				 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
				 return false; 
			 }else{
				 window.location.href="teachermenuaction.html?method=downloadPayrollExcel&accyearId="+checkval[0]
				 	+"&monthId="+checkval[2]
					+"&locationId="+checkval[3]
					+"&yearId="+checkval[4];
			 }
		 }
	});
	
	$('#pdfid').click(function(){
		 if($('input[type=radio]:checked').length == 0) {
			  $(".errormessagediv").show();
			  $(".validateTips").text("Select Any One Record");
			  setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			 return false;
		 }else {
			 var status= $("input[type='radio']:checked").val();
				checkval=status.split(",");
			 if(checkval[1]=="Not Generated"){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Payroll Not Generated For That Month,Please Generate Payroll First");
				 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
				 return false; 
			 }else{
				 window.location.href="teachermenuaction.html?method=downloadPayrollPdf&accyearId="+checkval[0]
				 	+"&monthId="+checkval[2]
					+"&locationId="+checkval[3]
					+"&yearId="+checkval[4];
			 }
		 }
	});
	
	var Thead = $('#depRecords thead').find('tr');
	$('<tr><th rowspan="2" width=""><input type="checkbox" name="selectall" class="selectall" style="text-align:center" ></th><th  rowspan="2" style="text-align:center;width:100px">PF NO</th><th  rowspan="2" style="text-align:center;width:100px">ESI NO</th><th  rowspan="2" style="text-align:center;width:50px">Reg Id</th><th rowspan="2" style="text-align:center;width:4%">EmployeeName</th><th rowspan="2" style="text-align:center;width:100px">Designation</th><th rowspan="2" style="text-align:center;width:100px">Department</th><th colspan="16" style="text-align: center;">ACTUAL</th><th colspan="16" style="text-align: center;">EARNED</th><th colspan="7" style="text-align: center;">DEDUCTION</th><th  rowspan="2" style="text-align:center;width:100px">Take Home</th><th  rowspan="2" style="text-align:center;width:100px">Salary Pending</th><th  rowspan="2" style="text-align:center;width:103px">Advance Salary</th><th  rowspan="2" style="text-align:center;width:100px">Net Pay</th></tr>').insertBefore(Thead);
	
	$('#depRecords thead').find('tr:nth-child(2)').find('th:lt(7)').remove();
	$('#depRecords thead').find('tr:nth-child(2),tr:nth-child(3)').find(
			'th:nth-last-child(1),th:nth-last-child(2),th:nth-last-child(3),th:nth-last-child(4)').remove();
	
	$(".selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});
	
	$("#payrollstatus").change(function(){
		getPayRollListByStatus();
	});

	$("#generatepayrollid").click(function(){
		var teacherId=[];
		var monthdays=[];
		var payabledays=[];
		var earnbasic=[];
		var earnda=[];
		var earnhra=[];
		var earnconvience=[];
		var earnperform=[];
		var earnfood=[];
		var earnspecial=[];
		var earnchild=[];
		var earnarrears=[];
		var earnreimburse=[];
		var earninternet=[];
		var earndriver=[];
		var earnleave=[];
		var earnmedical=[];
		var earntotal=[];
		var earnpfempr=[];
		var earnpfempy=[];
		var earnesiempr=[];
		var earnesiempy=[];
		var ptax=[];
		var incometax=[];
		var totaldeduction=[];
		var takehome=[];
		var salpending=[];
		var advance=[];
		var netpay=[];
		
		var cnt = 0;
		var accyearId=$('#hiddenaccyearId').val();
		var monthId=$('#hiddenmonthvalcode').val();
		var locationId=$('#hiddenlocationId').val();
		var yearId=$('#hiddenyearname').val();
		
		$("#depRecords tbody tr").find(".select:checked").each(function(){
			teacherId.push($(this).val());
			monthdays.push($(this).closest("tr").find('td.monthdays').text());
			payabledays.push($(this).closest("tr").find('td.payabledays').text());
			earnbasic.push($(this).closest("tr").find('td.earnbasic').text());
			earnda.push($(this).closest("tr").find('td.earnda').text());
			earnhra.push($(this).closest("tr").find('td.earnhra').text());
			earnconvience.push($(this).closest("tr").find('td.earnconvience').text());
			earnperform.push($(this).closest("tr").find('td.earnperform').text());
			earnfood.push($(this).closest("tr").find('td.earnfood').text());
			earnspecial.push($(this).closest("tr").find('td.earnspecial').text());
			earnchild.push($(this).closest("tr").find('td.earnchild').text());
			earnarrears.push($(this).closest("tr").find('td.earnarrears').text());
			earnreimburse.push($(this).closest("tr").find('td.earnreimburse').text());
			earninternet.push($(this).closest("tr").find('td.earninternet').text());
			earndriver.push($(this).closest("tr").find('td.earndriver').text());
			earnleave.push($(this).closest("tr").find('td.earnleave').text());
			earnmedical.push($(this).closest("tr").find('td.earnmedical').text());
			earntotal.push($(this).closest("tr").find('td.earntotal').text());
			
			earnpfempr.push($(this).closest("tr").find('td.earnpfempr').text());
			earnpfempy.push($(this).closest("tr").find('td.earnpfempy').text());
			earnesiempr.push($(this).closest("tr").find('td.earnesiempr').text());
			earnesiempy.push($(this).closest("tr").find('td.earnesiempy').text());
			ptax.push($(this).closest("tr").find('td.ptax').text());
			incometax.push($(this).closest("tr").find('td.incometax').text());
			totaldeduction.push($(this).closest("tr").find('td.totaldeduction').text());
			takehome.push($(this).closest("tr").find('td.takehome').text());
			salpending.push($(this).closest("tr").find('td.salpending').text());
			advance.push($(this).closest("tr").find('td.advance').text());
			netpay.push($(this).closest("tr").find('td.netpay').text());
			
			cnt++;
		});
		
		if (cnt == 0) {
			
			$(".errormessagediv").show();
			$(".validateTips").text("Please Select Atleast One Record !");
			$(".errormessagediv").delay(2000).fadeOut();
			
			return false;
		} else{
			var datalist = {
					"accyearId":accyearId,
					"monthId":monthId,
					"locationId":locationId,
					"yearId":yearId,
					
					"teacherId":teacherId.toString(),
					"monthdays":monthdays.toString(),
					"payabledays":payabledays.toString(),
					"earnbasic":earnbasic.toString(),
					"earnda":earnda.toString(),
					"earnhra":earnhra.toString(),
					"earnconvience":earnconvience.toString(),
					"earnperform":earnperform.toString(),
					"earnfood":earnfood.toString(),
					"earnspecial":earnspecial.toString(),
					"earnchild":earnchild.toString(),
					"earnarrears":earnarrears.toString(),
					"earnreimburse":earnreimburse.toString(),
					"earninternet":earninternet.toString(),
					"earndriver":earndriver.toString(),
					"earnleave":earnleave.toString(),
					"earnmedical":earnmedical.toString(),
					"earntotal":earntotal.toString(),
					"earnpfempr":earnpfempr.toString(),
					"earnpfempy":earnpfempy.toString(),
					"earnesiempr":earnesiempr.toString(),
					"earnesiempy":earnesiempy.toString(),
					"monthlyptax":ptax.toString(),
					"monthlyincometax":incometax.toString(),
					"monthlytotaldeduction":totaldeduction.toString(),
					"monthlytakehome":takehome.toString(),
					"monthlysalpending":salpending.toString(),
					"monthlyadvance":advance.toString(),
					"monthlynetpay":netpay.toString()
			};
			$.ajax({
				type : 'POST',
				url : "teachermenuaction.html?method=GenerateMultiplePayroll",
				data : datalist,
				success : function(response) {
					var result = $.parseJSON(response);
					$('.errormessagediv').hide();

					if (result.getPayrollStatus == "success") {
						$(".successmessagediv").show();
						$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
						$(".validateTips").text("Generate Payroll Progressing...");
						$('.successmessagediv').delay(3000).slideUp();

					} /*else(result.getPayrollStatus == "fail"){
						$(".errormessagediv").show();
						$(".validateTips").text("Generate Payroll Failed.");
						$('.errormessagediv').delay(3000).slideUp();
					}*/
					
					setTimeout(function(){
						window.location.href="teachermenuaction.html?method=generatePayroll";
					},3000);

				}

			});  
		}
	});
	if($("#payslipmessage").val()!=""){
		$(".successmessagediv").show();
		$(".validateTips").text($("#payslipmessage").val());
	}
});

function getMonthList(){
	var accyearid=$('#Acyearid').val();
	datalist={
		"accyearid" : accyearid
	},
	
	$.ajax({
		
		type : 'POST',
		url : "teachermenuaction.html?method=getPayRorllMonthList",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#monthId').empty();
			
			$('#monthId').append('<option value="all">' + "ALL"+ '</option>');
			
			for ( var j = 0; j < result.getMonthList.length; j++) {

				$('#monthId').append('<option value="' + result.getMonthList[j].monthcount
						+ '">' + result.getMonthList[j].monthName
						+ '</option>');
			}
		}
	});
}

function getPayRollListByStatus(){
	var accyearId=$('#hiddenaccyearId').val();
	var monthId=$('#hiddenmonthvalcode').val();
	var locationId=$('#hiddenlocationId').val();
	var yearId=$('#hiddenyearname').val();
	var status=$('#payrollstatus').val();

	var data = {
			"locationId":locationId,
			"accyearid" : accyearId,
			"monthId" : monthId,
			"yearId" : yearId,
			"status" : status
	};

	$.ajax({
		type : 'POST',
		url : "teachermenuaction.html?method=generatePayrollListByStatus",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			$('#depRecords tbody').empty();
			if(result.EmployeePayroll.length>0){
				for(var i=0;i<result.EmployeePayroll.length;i++){
					$('#depRecords tbody').append("<tr>" +
							"<td><input type='checkbox' name='select' class='select' style='text-align:center' value="+result.EmployeePayroll[i].empId+"/></td>" +
							"<td>"+result.EmployeePayroll[i].pfnumber+"</td>" +
							"<td>"+result.EmployeePayroll[i].esinumber+"</td>" +
							"<td>"+result.EmployeePayroll[i].regId+"</td>" +
							"<td>"+result.EmployeePayroll[i].empName+"</td>" +
							"<td>"+result.EmployeePayroll[i].designationname+"</td>" +
							"<td>"+result.EmployeePayroll[i].deptName+"</td>" +
							"<td>"+result.EmployeePayroll[i].monthDays+"</td>" +
							"<td>"+result.EmployeePayroll[i].basic+"</td>" +
							"<td>"+result.EmployeePayroll[i].da+"</td>" +
							"<td>"+result.EmployeePayroll[i].hra+"</td>" +
							"<td>"+result.EmployeePayroll[i].convieance+"</td>" +
							"<td>"+result.EmployeePayroll[i].performanceIncentive+"</td>" +
							"<td>"+result.EmployeePayroll[i].foodAllowance+"</td>" +
							"<td>"+result.EmployeePayroll[i].specialAllowance+"</td>" +
							"<td>"+result.EmployeePayroll[i].childEdu+"</td>" +
							"<td>"+result.EmployeePayroll[i].arrears+"</td>" +
							"<td>"+result.EmployeePayroll[i].reimbursement+"</td>" +
							"<td>"+result.EmployeePayroll[i].internetExpense+"</td>" +
							"<td>"+result.EmployeePayroll[i].driverSalary+"</td>" +
							"<td>"+result.EmployeePayroll[i].leaveEncash+"</td>" +
							"<td>"+result.EmployeePayroll[i].medicalReimbursement+"</td>" +
							"<td>"+result.EmployeePayroll[i].totalsalary+"</td>" +
							"<td>"+result.EmployeePayroll[i].payabledays+"</td>" +
							"<td>"+result.EmployeePayroll[i].basic_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].da_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].hra_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].convinience_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].performanceIncentive_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].foodAllowance_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].specialAllowance_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].childEdu_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].arrears_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].reimbursement_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].internetExpense_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].driverSalary_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].leaveEncash_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].medicalReimbursement_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].totalsalary_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].pfempr_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].pfempy_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].esiempr_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].esiempy_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].ptax_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].incometax_earned+"</td>" +
							"<td>"+result.EmployeePayroll[i].totaldeductions+"</td>" +
							"<td>"+result.EmployeePayroll[i].takehome+"</td>" +
							"<td>"+result.EmployeePayroll[i].netpay+"</td>" +
							"<td>0.0</td>" +
							"<td>"+result.EmployeePayroll[i].netpay+"</td>" +
					"</tr>");
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.EmployeePayroll.length);
			}
			else{
				$('#depRecords tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}
		}

	});
}	
function getPayRollYearListByLocation(){
	var accyearId=$('#Acyearid').val();
	var monthId=$('#monthId').val();
	var monthYear=""
	if(monthId!="all"){
		monthYear=$('#monthId option:selected').text().split("-")[1];
	}
	else{
		monthYear="all";
	}
	
	var locationId=$('#locationname').val();

	var data = {
			"locationId":locationId,
			"accyearid" : accyearId,
			"monthId" : monthId,
			"monthYear":monthYear,
	};

	$.ajax({
		type : 'POST',
		url : "teachermenuaction.html?method=getPayRollYearListByLocation",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			$('#tableid tbody').empty();
			if(result.payroll_list_status.length>0){
				for(var i=0;i<result.payroll_list_status.length;i++){
					$('#tableid tbody').append("<tr>" +
							"<td><input type='radio' name='payrollid' value='"+result.payroll_list_status[i].yearvalcode+","+result.payroll_list_status[i].status+","+result.payroll_list_status[i].monthvalcode+","+result.payroll_list_status[i].locationId+","+result.payroll_list_status[i].yearName+"'/></td>" +
							"<td>"+result.payroll_list_status[i].locationName+"</td>" +
							"<td>"+result.payroll_list_status[i].yearval+"</td>" +
							"<td>"+result.payroll_list_status[i].monthval+"</td>" +
							"<td>"+result.payroll_list_status[i].status+"</td>" +
							"<td>"+result.payroll_list_status[i].createdby+"</td>" +
							"<td>"+result.payroll_list_status[i].createddate+"</td>" +
							"<td>"+result.payroll_list_status[i].createdtime+"</td>" +
					"</tr>");
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.payroll_list_status.length);
			}
			else{
				$('#tableid tbody').append("<tr><td colspan='8'>NO Records Found</td></tr>");
			}
		}
	});
}
	

	
