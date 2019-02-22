function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();
	
}

$(document).ready(function() {
	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
	}
	$("#selectall").change(function(){
		$(".select").prop("checked",$(this).prop("checked"));
	});
	$(".select").change(function(){
		if($(".select").length==$(".select:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
		}
	});
	$('#basictotal').val(($('#basic').val()*12).toFixed(2));
	$('#datotal').val(($('#da').val()*12).toFixed(2));
	$('#hratotal').val(($('#hra').val()*12).toFixed(2));
	$('#allowancetotal').val(($('#allowance').val()*12).toFixed(2));
	$('#childedutotal').val(($('#childedu').val()*12).toFixed(2));
	$('#specialtotal').val(($('#special').val()*12).toFixed(2));
	$('#arrearstotal').val(($('#arrears').val()*12).toFixed(2));
	$('#performacetotal').val(($('#performace').val()*12).toFixed(2));
	$('#medicaltotal').val(($('#medical').val()*12).toFixed(2));
	$('#leavetotal').val(($('#leave').val()*12).toFixed(2));
	$('#foodtotal').val(($('#food').val()*12).toFixed(2));
	$('#reimbursetotal').val(($('#reimburse').val()*12).toFixed(2));
	$('#internettotal').val(($('#internet').val()*12).toFixed(2));
	$('#drivertotal').val(($('#driver').val()*12).toFixed(2));
	$('#othertotal').val(($('#other').val()*12).toFixed(2));
	$('#yearlytotalpfamount').val(($('#pfamount').val()*12).toFixed(2));
	$('#yearlytotalesiamount').val(($('#esiamount').val()*12).toFixed(2));
	$('#standarddedu').val('250000.00');
	addYearlyAmount();
	addMonthlyAmount();
	$('#basic,#da,#hra,#allowance,#childedu,#special,#arrears,#performace,#medical,#leave,#food,#reimburse,#internet,#driver,#other,#pfemployerbasic,#esiemployertotal,#staffadvance,#otherdeduction').keypress(function(event) {
	    if (event.which != 46 && (event.which < 47 || event.which > 59))
	    {
	        event.preventDefault();
	        if ((event.which == 46) && ($(this).indexOf('.') != -1)) {
	            event.preventDefault();
	        }
	    }
	});
	
	$('#basic').blur(function(){
		$('#basictotal').val(($('#basic').val()*12).toFixed(2));
		pfCalculator();
		addMonthlyAmount();
		addYearlyAmount();
		getHraPercentage();
	});
	$('#da').blur(function(){
		pfCalculator();
		$('#datotal').val(($('#da').val()*12).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#hra').blur(function(){
		$('#hratotal').val(($('#hra').val()*12).toFixed(2));
		addYearlyAmount();
		addMonthlyAmount();
	});
	$('#allowance').blur(function(){
		$('#allowancetotal').val(($('#allowance').val()*12).toFixed(2));
		addYearlyAmount();
		addMonthlyAmount();
	});
	$('#childedu').blur(function(){
		$('#childedutotal').val(($('#childedu').val()*12).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#special').blur(function(){
		$('#specialtotal').val(($('#special').val()*12).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#arrears').blur(function(){
		$('#arrearstotal').val(($('#arrears').val()*12).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#performace').blur(function(){
		$('#performacetotal').val(($('#performace').val()*12).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#medical').blur(function(){
		$('#medicaltotal').val(($('#medical').val()*12).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#leave').blur(function(){
		$('#leavetotal').val(($('#leave').val()*12).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#food').blur(function(){
		$('#foodtotal').val(($('#food').val()*12).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#reimburse').blur(function(){
		$('#reimbursetotal').val(($('#reimburse').val()*12).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#internet').blur(function(){
		$('#internettotal').val(($('#internet').val()*12).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#driver').blur(function(){
		$('#drivertotal').val(($('#driver').val()*12).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#other').blur(function(){
		$('#othertotal').val(($('#other').val()*12).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	//pfYearlyCalculation();
	
	$('#pfemployerbasic').change(function(){
		var basic=$('#basic').val();
		var da=$('#da').val();
		total=Number(basic)+Number(da);
		if(total >= $('#pfemployerbasic').val()){
			var totalpfamount=$('#pfemployerbasic').val();
			pfamount = ( (totalpfamount/100) * 12 );
			$('#pfamount').val(pfamount.toFixed(2));
			$('#yearlytotalpfamount').val(($('#pfamount').val()*12).toFixed(2));
		}else{
			$(".errormessagediv").show();
			$(".validateTips").text("PF Employer Amount Should be more than "+total+" and less than Basic+DA Amount.");
			$("#pfemployerbasic").focus();
			document.getElementById("pfemployerbasic").style.border = "1px solid #AF2C2C";
			document.getElementById("pfemployerbasic").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("pfemployerbasic").style.border = "1px solid #ccc";
				document.getElementById("pfemployerbasic").style.backgroundColor = "#fff";
			}, 5000);

			pfCalculator();
		}
	});
	
	pfCalculator();
	
	$("#employerpfpercentage").hide();
	$("#contributionId").hide();
	$("#employeepfamount").hide();
	
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);

	var hpaymenttype = $("#hpaymenttype").val();

	if (hpaymenttype != "" && hpaymenttype != undefined) {

		$("#paymenttype option[value="+ hpaymenttype.trim() + "]").attr('selected', 'true');
	}

	var hbankname = $("#hbankname").val();

	if (hbankname != "" && hbankname != undefined) {

		$("#bankid option[value=" + hbankname.trim() + "]")
		.attr('selected', 'true');
	}

	$("#searchbtn").click(function() {

		var searhname = $("#searchname").val();

		window.location.href = "adminMenu.html?method=staffEmployementList&searhname="
			+ searhname;

	});

	$('#editTeacher').click(function() {

		var count = 0;
		$('input.select:checkbox:checked').map(function() {
			 teacher_id = $(this).attr("id");
			count++;
		});
		if (count == 0 || count > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text(
			"Check any one checkbox");
			return false;

		} else {

			window.location.href = "staffemployement.html?method=getStaffEmployementEntry&teachercode="
				+ teacher_id;

		}
	});

	$('#basic,#da').change(function() {

		var da = $("#da").val();
		var basic = $("#basic").val();

		var total=da+basic;

		if(total>=15000.0||total>=15000)
		{
			$("#employerpfpercentage").show();
			$("#contributionId").show();
			$("#employeepfamount").hide();
		}

		else
		{
			$("#employerpfpercentage").hide();
			$("#contributionId").hide();
			$("#employeepfamount").hide();

		}
	});


	$("input[name='employee_contribution']").change(function(){
		var employee_contribution = $('.radio-inline[name="employee_contribution"]:checked').val();

		if(employee_contribution=='Y')
			$("#employeepfamount").show();
		else
			$("#employeepfamount").hide(); 

	});

	$("#submit").click(function() {
		/*if (validation()) {*/
			document.getElementById("teacherform").submit();
		//}
	});

	$('#excelDownload').click(function() {

		window.location.href = 'staffemployement.html?method=downloadStaffEmploymentDetailsXLS';

	});
	
	$("#pdfDownload").click(function() {

		window.location.href = "staffemployement.html?method=downloadStaffEmploymentDetailsPDF";

	});
	
	$('#hrapercentage').change(function(){
		var basic=$('#basic').val();
		var hra=$('#hra').val();
		var hrapercentage=$('#hrapercentage').val();
		if(hra != "0.0"){
			monthlyhraamount = ( (basic/100) * hrapercentage );
			$('#monthlyhraamount').val(monthlyhraamount.toFixed(2));
			$('#yearlyhraamount').val(($('#monthlyhraamount').val()*12).toFixed(2));
		}else{
			$(".errormessagediv").show();
			$(".validateTips").text("Requeired Field HRA.");
			$("#hra").focus();
			document.getElementById("hra").style.border = "1px solid #AF2C2C";
			document.getElementById("hra").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("hra").style.border = "1px solid #ccc";
				document.getElementById("hra").style.backgroundColor = "#fff";
			}, 5000);
			$('#hrapercentage option[value="0"]').attr('selected', true);
		}
		
	});
	
	var hiddenhrapercentage=$('#hiddenhrapercentage').val();
	if(hiddenhrapercentage !="0"){
		$("#hrapercentage option[value='"+ $('#hiddenhrapercentage').val()+"']").attr('selected', 'true');
		$("#monthlyhraamount").val($('#hiddenmonthlyhraamount').val());
		$("#yearlyhraamount").val(($('#hiddenmonthlyhraamount').val()*12).toFixed(2));
	}

});

function addMonthlyAmount(){
	var monthlyamount=Number($('#basic').val())+Number($('#da').val())+Number($('#hra').val())+Number($('#allowance').val())+Number($('#childedu').val())+Number($('#special').val())+Number($('#arrears').val())+Number($('#performace').val())+Number($('#medical').val())+Number($('#leave').val())+Number($('#food').val())+Number($('#reimburse').val())+Number($('#internet').val())+Number($('#driver').val())+Number($('#other').val());
	
	$('#totalsalary').val(monthlyamount.toFixed(2));
	if(monthlyamount<=15000){
		$('#pt').val('0.0');
	}else{
		$('#pt').val('200.0');
	}
	
	esiCalculator();
}

function addYearlyAmount(){
	var yearlyamount=Number($('#basictotal').val())+Number($('#datotal').val())+Number($('#hratotal').val())+Number($('#allowancetotal').val())+Number($('#childedutotal').val())+Number($('#specialtotal').val())+Number($('#arrearstotal').val())+Number($('#performacetotal').val())+Number($('#medicaltotal').val())+Number($('#leavetotal').val())+Number($('#foodtotal').val())+Number($('#reimbursetotal').val())+Number($('#internettotal').val())+Number($('#drivertotal').val())+Number($('#othertotal').val());
	
	$('#yearlytotalamount').val(yearlyamount.toFixed(2));	
	$('#tdsctc').val(yearlyamount.toFixed(2));	
	
	if($('#tdsctc').val()>$('#standarddedu').val()){
		$('#tdspt').val(($('#pt').val()*12).toFixed(2));
		$('#tdsexmption').val();
		
		if($('#htdsundervia').val() != 0){
			$('#tdsundervia').val($('#htdsundervia').val());
		}else{
			$('#tdsundervia').val(($('#demployeepf').val()*12).toFixed(2));
		}
		$('#taxableincome').val((Number($('#tdsctc').val())-Number($('#standarddedu').val())-Number($('#tdspt').val())-Number($('#tdsexmption').val())-Number($('#tdsundervia').val())+Number($('#taxableincomefromothers').val())).toFixed(2));
		var taxableincome=$('#taxableincome').val();
		if(taxableincome <= 250000){
			tax_payble = Math.round((taxableincome * 5)/100);
		}else if(taxableincome >= 250001 && taxableincome <= 500000 ){
			tax_payble = Math.round((taxableincome * 5)/100);
		
		}else if(taxableincome >= 500001 &&  taxableincome <= 1000000){
		
			var amount= taxableincome- 500000.0;
		
			var  one = Math.round((500000.0 * 5)/100);
			var  two = Math.round((amount * 20)/100);
			
			tax_payble = one + two;
			
		}else{
			
			var amount= taxableincome- 500000.0;
			
			var  one = Math.round((500000.0 * 5)/100);
			var  two = Math.round((amount * 30)/100);
			
			tax_payble = one + two;;
		}
		$('#taxpayble').val(tax_payble.toFixed(2));
		$('#incometax').val(Math.round(($('#taxpayble').val()/12)).toFixed(2));
	}else{
		$('#tdspt').val('0.0');
		$('#tdsexmption').val('0.0');
		$('#taxbleincomestatury').val('0.0');
	}
	
}

function pfCalculator(){
	var basic=$('#basic').val();
	var da=$('#da').val();
	total=Number(basic)+Number(da);
	if(total>=15000){
		$('#pfemployerbasic').val('15000.00');
		var totalpfamount=$('#pfemployerbasic').val();
		pfamount = ( (totalpfamount/100) * 12 );
		$('#pfamount').val(pfamount.toFixed(2));
		$('#demployerpf').val(pfamount.toFixed(2));
		$('#demployeepf').val(pfamount.toFixed(2));
		$('#yearlytotalpfamount').val(($('#pfamount').val()*12).toFixed(2));
	}else{
		$('#pfemployerbasic').val(total.toFixed(2));
		var totalpfamount=$('#pfemployerbasic').val();
		pfamount = ( (totalpfamount/100) * 12 );
		$('#pfamount').val(pfamount.toFixed(2));
		$('#demployerpf').val(pfamount.toFixed(2));
		$('#demployeepf').val(pfamount.toFixed(2));
		$('#yearlytotalpfamount').val(($('#pfamount').val()*12).toFixed(2));
	}
}

function esiCalculator(){
	if($('#totalsalary').val() <= 20000){
		$('#esiemployertotal').val($('#totalsalary').val());
		var totalesiamount=$('#esiemployertotal').val();
		esiamount = ( (totalesiamount/100) * 4.75 );
		employeesiamount = ( (totalesiamount/100) * 1.75 );
		$('#esiamount').val(esiamount.toFixed(2));
		$('#demployeeESI').val(employeesiamount.toFixed(2));
		$('#dedmployerESI').val(esiamount.toFixed(2));
		$('#yearlytotalesiamount').val(($('#esiamount').val()*12).toFixed(2));
	}else{
		$('#esiemployertotal').val('0.00');
		$('#esiamount').val('0.00');
		$('#dedmployerESI').val('0.00');
		$('#demployeeESI').val('0.00');
		$('#yearlytotalesiamount').val('0.00');
	}
}

function getHraPercentage(){
	var basic=$('#basic').val();
	var hra=$('#hra').val();
	var hrapercentage=$('#hrapercentage').val();
	if(hra != "0.0"){
		monthlyhraamount = ( (basic/100) * hrapercentage );
		$('#monthlyhraamount').val(monthlyhraamount.toFixed(2));
		$('#yearlyhraamount').val(($('#monthlyhraamount').val()*12).toFixed(2));
	}else{
		$('#hrapercentage option[value="0"]').attr('selected', true);
		$('#monthlyhraamount').val("0.00");
		$('#yearlyhraamount').val("0.00");
	}
}

