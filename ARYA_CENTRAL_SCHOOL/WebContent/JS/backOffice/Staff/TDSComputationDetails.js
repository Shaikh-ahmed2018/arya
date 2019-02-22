$(document).ready(function() {
	$('#empprovidentfund').val((($('#pfamount').val())*12).toFixed(2));
	var itdeclare=$("#itdeclaration").val();
	if(itdeclare == "" || itdeclare == undefined || itdeclare == null){
		$('#collapseOne').show();
		$('#add').hide();
	}else{
		$('#collapseOne').hide();
		$('#add').show();
		$('#displayId').show();
		$('#submit').hide();
		$('.panel.panel-primary .panel.panel-primary').css({
			"border-bottom":"none",
			"border-left":"none",
			"border-right":"none"
		});
		
	}

	addOtherIncomeAmount();
	addExemptionAmount();
	addSection80CAmount();
	addSection80DAmount();
	multiplyMonthAmount();
	getMonthlyDeduction();
	
	/*$('#taxablebasicperYear').val(Number($('#basicperYear').val()).toFixed(2));*/
	
	$("#add").click(function(){
		window.location.href="TDSComputationDetails.html?method=AddTdsComputationDetails";
	});		
	
	
	$('#houseincomefromother,#houseintrestloan,#otherIncomeSelf,#hraExemptionSection,#childeducationexemption,#medicalsectionexemption,#transportsectionexemption,#ltasectionexemption,#foodsectionexemption,#houseamount').keypress(function(event) {
	    if (event.which != 46 && (event.which < 47 || event.which > 59))
	    {
	        event.preventDefault();
	        if ((event.which == 46) && ($(this).indexOf('.') != -1)) {
	            event.preventDefault();
	        }
	    }
	    
	});
	
	$('#telephoneexemption,#carreimburseexemption,#internetsectionexemption,#driversectionexemption,#otherssectionexpense').keypress(function(event) {
	    if (event.which != 46 && (event.which < 47 || event.which > 59))
	    {
	        event.preventDefault();
	        if ((event.which == 46) && ($(this).indexOf('.') != -1)) {
	            event.preventDefault();
	        }
	    }
	});
	
	/* section 80c */
	$('#empprovidentfund,#tutionfee,#fixeddeposit,#lifeinspremium,#mutualfund,#nationalsavecertificate,#accruednsci,#publicpf').keypress(function(event) {
	    if (event.which != 46 && (event.which < 47 || event.which > 59))
	    {
	        event.preventDefault();
	        if ((event.which == 46) && ($(this).indexOf('.') != -1)) {
	            event.preventDefault();
	        }
	    }
	});
	
	$('#repaymenthouseloan,#unitLinkInsPlan,#scss,#postsavingbank,#othersection').keypress(function(event) {
	    if (event.which != 46 && (event.which < 47 || event.which > 59))
	    {
	        event.preventDefault();
	        if ((event.which == 46) && ($(this).indexOf('.') != -1)) {
	            event.preventDefault();
	        }
	    }
	});

	/* section 80d to 80u */
	$('#mediclaimself,#mediclaimparents,#meditreatbeloweighty,#meditreataboveeighty,#interesteduloan,#additionalcontribute,#deductionofphysicalbelow,#deductionofphysicalabove').keypress(function(event) {
	    if (event.which != 46 && (event.which < 47 || event.which > 59))
	    {
	        event.preventDefault();
	        if ((event.which == 46) && ($(this).indexOf('.') != -1)) {
	            event.preventDefault();
	        }
	    }
	});
	
	$('#rajivgandhiequity,#eightyttainterestsaving,#eightyddmedicaltreatbelow,#eightyddmedicaltreatabove,#mediclaimparentsabove').keypress(function(event) {
	    if (event.which != 46 && (event.which < 47 || event.which > 59))
	    {
	        event.preventDefault();
	        if ((event.which == 46) && ($(this).indexOf('.') != -1)) {
	            event.preventDefault();
	        }
	    }
	});
	
	$('#houseincomefromother').blur(function(){
		addOtherIncomeAmount();
		getTotalTax();
	});
	$('#houseintrestloan').blur(function(){
		housingloanletout();
		getTotalTax();
	});
	$('#otherIncomeSelf').blur(function(){
		housingloanself();
		getTotalTax();
	});
	/* other than salary */
	$('#hraExemptionSection,#childeducationexemption,#ltasectionexemption,#foodsectionexemption,#telephoneexemption,#carreimburseexemption,#internetsectionexemption,#otherssectionexpense').blur(function(){
		addExemptionAmount();
		getTotalTax();
	});
	$('#medicalsectionexemption').blur(function(){
		medicalreimburse();
		getTotalTax();
	});
	$('#transportsectionexemption').blur(function(){
		transportExemption();
		getTotalTax();
	});
	
	/* section 80c */
	$('#empprovidentfund,#tutionfee,#fixeddeposit,#lifeinspremium,#mutualfund,#nationalsavecertificate,#accruednsci,#publicpf,#repaymenthouseloan,#unitLinkInsPlan,#scss,#postsavingbank,#othersection').blur(function(){
		addSection80CAmount($(this));
		getTotalTax();
	});
	
	/* section 80d to 80u */
	$('#mediclaimself').blur(function(){
		mediClaimSelf();
		getTotalTax();
	});
	$('#mediclaimparents').blur(function(){
		mediClaimParents();
		getTotalTax();
	});
	$('#meditreatbeloweighty').blur(function(){
		mediTreatBelowEighty();
		getTotalTax();
	});
	$('#meditreataboveeighty').blur(function(){
		mediTreatAboveEighty();
		getTotalTax();
	});
	$('#interesteduloan').blur(function(){
		addSection80DAmount();
		getTotalTax();
	});
	$('#additionalcontribute').blur(function(){
		additionalContribute();
		getTotalTax();
	});
	$('#deductionofphysicalbelow').blur(function(){
		deductionOfPhysicalBelow();
		getTotalTax();
	});
	$('#deductionofphysicalabove').blur(function(){
		deductionOfPhysicalAbove();
		getTotalTax();
	});
	$('#rajivgandhiequity').blur(function(){
		rajivGandhiEquity();
		getTotalTax();
	});
	$('#eightyttainterestsaving').blur(function(){
		eightyttaInterestSaving();
		getTotalTax();
	});
	$('#eightyttainterestsaving').blur(function(){
		eightyttaInterestSaving();
		getTotalTax();
	});
	$('#eightyddmedicaltreatbelow').blur(function(){
		eightyddMedicalTreatBelow();
		getTotalTax();
	});
	$('#eightyddmedicaltreatabove').blur(function(){
		eightyddMedicalTreatAbove();
		getTotalTax();
	});
	$('#mediclaimparentsabove').blur(function(){
		mediClaimParentsAbove();
		getTotalTax();
	});
		
	$("#searchname").hide();
	$("#search").hide();

	$(".perMonthAmount").val(parseFloat($(".perMonthAmount").val()).toFixed(2));	
	
	$("#submit").click(function() {
		/*if (validation()) {*/
			document.getElementById("staffform").submit();
		//}
	});

	$("#startdate").datepicker({
		dateFormat : "dd-mm-yy",
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onClose : function(selectedDate) {
			if ((selectedDate != "")&& (selectedDate != undefined)) {
				var date2 = $('#startdate').datepicker('getDate');
				date2.setDate(date2.getDate() + 1);
				$("#enddate").datepicker("option","minDate", date2);
			}
		}
	});
	
	$("#enddate").datepicker({
		dateFormat : "dd-mm-yy",
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onClose : function(selectedDate) {
			if ((selectedDate != "") && (selectedDate != undefined)) {
				
				var date2 = $('#enddate').datepicker('getDate');
				date2.setDate(date2.getDate() - 1);
				$("#startdate").datepicker("option", "maxDate", date2);
			}
		}
	});
	
	if($('#houseamount').val() != 0.0 || $('#houseamount').val() != 0.00){
		getHraCalculation();	
	}		
	
	$("#houseamount").blur(function(){
		getHraCalculation();
		getTotalTax();
	});	
	getTotalTax();
});

function getTotalTax(){
	var taxableincome=Number($('#tdsctc').val())-Number($('#tdspt').val())-Number($('#tdssd').val())-Number($('#totalSectionAmount').val())-Number($('#totalChapter80U').val())-Number($('#totalChapter').val())+Number($('#totalofOtherIncome').val());
	var months=$('#months').val();
	var totalExemption=Number($('#totalSectionAmount').val());
	var totalAnyOtherIncome=Number($('#totalofOtherIncome').val());
	var totalUnderVia=Number($('#totalChapter80U').val())+Number($('#totalChapter').val())
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
	var totalTaxAmount=tax_payble;
	/*educess=(totalTaxAmount*2)/100;
	$('#educationcess').val(educess.toFixed(2));
	highereducess=(totalTaxAmount*1)/100;
	$('#highereducationcess').val(highereducess.toFixed(2));
	
	$('#totaleducationcess').val((Number($('#educationcess').val())+Number($('#highereducationcess').val())).toFixed(2));*/
	
	$('#taxableExemption').val((totalExemption).toFixed(2));
	$('#taxableAnyOtherIncome').val((totalAnyOtherIncome).toFixed(2));
	$('#taxworkUnderCapterVIA').val((totalUnderVia).toFixed(2));
	$('#taxableTotalAmount').val((taxableincome).toFixed(2));
	$('#totalTaxAmount').val((totalTaxAmount).toFixed(2));
	$('#netTax').val((totalTaxAmount).toFixed(2));
	var netTax=Number($('#netTax').val());
	$('#taxworkPerMonth').val((netTax/12).toFixed(2));
	$('#TDSDeductionfromsal').val(Math.ceil((netTax/12)).toFixed(1));
	getMonthlyDeduction();
}

function getHraCalculation(){
	var basicperYear=$('#basicperYear').val();
	var hraperYear=$('#hraperYear').val();
	var houseamount=$('#houseamount').val();
	var hrapercentage=$('#hrapercentage').val();
	var foutyper=((basicperYear/100) * hrapercentage).toFixed(2);
	var tenper=((basicperYear/100) * 10).toFixed(2);
	var leastamt=houseamount-tenper;
	var hraamt=Math.min(hraperYear,foutyper,leastamt);
	
	$('#hraExemptionSection').val(Math.ceil(hraamt).toFixed(1));
	
	addExemptionAmount();
}

function addOtherIncomeAmount(){
	var otherincomeamount=Number($('#houseincomefromother').val())+Number($('#houseintrestloan').val())+Number($('#otherIncomeSelf').val());
	
	$('#totalofOtherIncome').val(otherincomeamount.toFixed(2));	
}

function addExemptionAmount(){
	var exemptionamount=Number($('#hraExemptionSection').val())+Number($('#childeducationexemption').val())+Number($('#medicalsectionexemption').val())+Number($('#transportsectionexemption').val())+Number($('#ltasectionexemption').val())+Number($('#foodsectionexemption').val())+Number($('#telephoneexemption').val())+Number($('#carreimburseexemption').val())+Number($('#internetsectionexemption').val())+Number($('#driversectionexemption').val())+Number($('#otherssectionexpense').val());
	
	$('#totalSectionAmount').val(exemptionamount.toFixed(2));	
}

function addSection80CAmount(pointer){
	var section80Camount=Number($('#empprovidentfund').val())+Number($('#tutionfee').val())+Number($('#fixeddeposit').val())+Number($('#lifeinspremium').val())+Number($('#mutualfund').val())+Number($('#nationalsavecertificate').val())+Number($('#accruednsci').val())+Number($('#publicpf').val())+Number($('#repaymenthouseloan').val())+Number($('#unitLinkInsPlan').val())+Number($('#scss').val())+Number($('#postsavingbank').val())+Number($('#othersection').val());
	
	if(section80Camount<=150000){
		$('#totalChapter').val(section80Camount.toFixed(2));
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than 150000");
		pointer.focus();
		pointer.css({
			"border":"1px solid #AF2C2C"
		});
		
		setTimeout(function() {
			$('#errorhover').fadeOut();
			pointer.css({
				"border":"1px solid #ccc"
			});
		}, 5000);
		pointer.val('0.0');
	}
}

function addSection80DAmount(){
	var section80Damount=Number($('#mediclaimself').val())+Number($('#interesteduloan').val())+Number($('#additionalcontribute').val())+Number($('#rajivgandhiequity').val())+Number($('#eightyttainterestsaving').val())+Number($('#eightyddmedicaltreatbelow').val())+Number($('#eightyddmedicaltreatabove').val())+Number($('#mediclaimparentsabove').val());
	
	$('#totalChapter80U').val(section80Damount.toFixed(2));	
}

function housingloanletout(){
	var maxlimit=$('#maxhouseintrestloan').val();
	
	var declaredamt=$('#houseintrestloan').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addOtherIncomeAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+$('#maxhouseintrestloan').val()+"");
		$("#houseintrestloan").focus();
		document.getElementById("houseintrestloan").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("houseintrestloan").style.border = "1px solid #ccc";
		}, 5000);
		$('#houseintrestloan').val('0.00');
	}
}

function housingloanself(){
	var maxlimit=$('#maxotherIncomeSelf').val();
	var declaredamt=$('#otherIncomeSelf').val();
	if(Number(declaredamt) <= Number(maxlimit)){
		addOtherIncomeAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+$('#maxotherIncomeSelf').val()+"");
		$("#otherIncomeSelf").focus();
		document.getElementById("otherIncomeSelf").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("otherIncomeSelf").style.border = "1px solid #ccc";
		}, 5000);
		$('#otherIncomeSelf').val('0.00');
	}
}

function medicalreimburse(){
	var maxlimit=$('#maxmedicalsectionexemption').val();
	
	var declaredamt=$('#medicalsectionexemption').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addExemptionAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+$('#maxmedicalsectionexemption').val()+"");
		$("#medicalsectionexemption").focus();
		document.getElementById("medicalsectionexemption").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("medicalsectionexemption").style.border = "1px solid #ccc";
		}, 5000);
		$('#medicalsectionexemption').val('0.00');
	}
}

function transportExemption(){
	var maxlimit=$('#maxtransportsectionexemption').val();
	
	var declaredamt=$('#transportsectionexemption').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addExemptionAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+$('#maxtransportsectionexemption').val()+"");
		$("#transportsectionexemption").focus();
		document.getElementById("transportsectionexemption").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("transportsectionexemption").style.border = "1px solid #ccc";
		}, 5000);
		$('#transportsectionexemption').val('0.00');
	}
}


function section80c(){
	var maxlimit=$('#maxemployeeprovidentfund').val();
	
	var declaredamt=$('#transportsectionexemption').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addExemptionAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+$('#maxemployeeprovidentfund').val()+"");
		$("#transportsectionexemption").focus();
		document.getElementById("transportsectionexemption").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("transportsectionexemption").style.border = "1px solid #ccc";
		}, 5000);
		$('#transportsectionexemption').val('0.00');
	}
}

function mediClaimSelf(){
	var maxlimit=$('#maxmediclaimself').val();
	var declaredamt=$('#mediclaimself').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addSection80DAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+$('#maxmediclaimself').val()+"");
		$("#mediclaimself").focus();
		document.getElementById("mediclaimself").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("mediclaimself").style.border = "1px solid #ccc";
		}, 5000);
		$('#mediclaimself').val('0.00');
	}
}

function mediClaimParents(){
	var maxlimit=$('#maxmediclaimself').val();
	var declaredamt=$('#mediclaimparents').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addSection80DAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+$('#maxmediclaimself').val()+"");
		$("#mediclaimparents").focus();
		document.getElementById("mediclaimparents").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("mediclaimparents").style.border = "1px solid #ccc";
		}, 5000);
		$('#mediclaimparents').val('0.00');
	}
}

function mediTreatBelowEighty(){
	var maxlimit=$('#maxmeditreatbeloweighty').val();
	var declaredamt=$('#meditreatbeloweighty').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addSection80DAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+maxlimit+"");
		$("#meditreatbeloweighty").focus();
		document.getElementById("meditreatbeloweighty").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("meditreatbeloweighty").style.border = "1px solid #ccc";
		}, 5000);
		$('#meditreatbeloweighty').val('0.00');
	}
}

function mediTreatAboveEighty(){
	var maxlimit=$('#maxmeditreataboveeighty').val();
	var declaredamt=$('#meditreataboveeighty').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addSection80DAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+maxlimit+"");
		$("#meditreataboveeighty").focus();
		document.getElementById("meditreataboveeighty").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("meditreataboveeighty").style.border = "1px solid #ccc";
		}, 5000);
		$('#meditreataboveeighty').val('0.00');
	}
}

function additionalContribute(){
	var maxlimit=$('#maxadditionalcontribute').val();
	var declaredamt=$('#additionalcontribute').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addSection80DAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+maxlimit+"");
		$("#additionalcontribute").focus();
		document.getElementById("additionalcontribute").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("additionalcontribute").style.border = "1px solid #ccc";
		}, 5000);
		$('#additionalcontribute').val('0.00');
	}
}

function deductionOfPhysicalBelow(){
	var maxlimit=$('#maxdeductionofphysicalbelow').val();
	var declaredamt=$('#deductionofphysicalbelow').val();
	if(Number(declaredamt) <= Number(maxlimit)){
		addSection80DAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+maxlimit+"");
		$("#deductionofphysicalbelow").focus();
		document.getElementById("deductionofphysicalbelow").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("deductionofphysicalbelow").style.border = "1px solid #ccc";
		}, 5000);
		$('#deductionofphysicalbelow').val('0.00');
	}
}

function deductionOfPhysicalAbove(){
	var maxlimit=$('#maxdeductionofphysicalabove').val();
	var declaredamt=$('#deductionofphysicalabove').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addSection80DAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+maxlimit+"");
		$("#deductionofphysicalabove").focus();
		document.getElementById("deductionofphysicalabove").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("deductionofphysicalabove").style.border = "1px solid #ccc";
		}, 5000);
		$('#deductionofphysicalabove').val('0.00');
	}
}

function rajivGandhiEquity(){
	var maxlimit=$('#maxrajivgandhiequity').val();
	var declaredamt=$('#rajivgandhiequity').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addSection80DAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+maxlimit+"");
		$("#rajivgandhiequity").focus();
		document.getElementById("rajivgandhiequity").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("rajivgandhiequity").style.border = "1px solid #ccc";
		}, 5000);
		$('#rajivgandhiequity').val('0.00');
	}
}

function eightyttaInterestSaving(){
	var maxlimit=$('#maxeightyttainterestsaving').val();
	var declaredamt=$('#eightyttainterestsaving').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addSection80DAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+maxlimit+"");
		$("#eightyttainterestsaving").focus();
		document.getElementById("eightyttainterestsaving").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("eightyttainterestsaving").style.border = "1px solid #ccc";
		}, 5000);
		$('#eightyttainterestsaving').val('0.00');
	}
}

function eightyddMedicalTreatBelow(){
	var maxlimit=$('#maxeightyddmedicaltreatbelow').val();
	var declaredamt=$('#eightyddmedicaltreatbelow').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addSection80DAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+maxlimit+"");
		$("#eightyddmedicaltreatbelow").focus();
		document.getElementById("eightyddmedicaltreatbelow").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("eightyddmedicaltreatbelow").style.border = "1px solid #ccc";
		}, 5000);
		$('#eightyddmedicaltreatbelow').val('0.00');
	}
}

function eightyddMedicalTreatAbove(){
	var maxlimit=$('#maxeightyddmedicaltreatabove').val();
	var declaredamt=$('#eightyddmedicaltreatabove').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addSection80DAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+maxlimit+"");
		$("#eightyddmedicaltreatabove").focus();
		document.getElementById("eightyddmedicaltreatabove").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("eightyddmedicaltreatabove").style.border = "1px solid #ccc";
		}, 5000);
		$('#eightyddmedicaltreatabove').val('0.00');
	}
}

function mediClaimParentsAbove(){
	var maxlimit=$('#maxmediclaimparentsabove').val();
	var declaredamt=$('#mediclaimparentsabove').val();
	
	if(Number(declaredamt) <= Number(maxlimit)){
		addSection80DAmount();
	}else{
		$(".errormessagediv").show();
		$(".validateTips").text("The Decleared Amount Should be greater than "+maxlimit+"");
		$("#mediclaimparentsabove").focus();
		document.getElementById("mediclaimparentsabove").style.border = "1px solid #AF2C2C";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("mediclaimparentsabove").style.border = "1px solid #ccc";
		}, 5000);
		$('#mediclaimparentsabove').val('0.00');
	}
}

function multiplyMonthAmount(){
	$('#basicperYear').val(($('#basic').val()*$('#basicperMonth').val()).toFixed(2));
	$('#daperYear').val(($('#da').val()*$('#daPerMonth').val()).toFixed(2));
	$('#hraperYear').val(($('#hra').val()*$('#hraPerMonth').val()).toFixed(2));
	$('#transportYear').val(($('#transport').val()*$('#transportperMonth').val()).toFixed(2));
	$('#childeducationYear').val(($('#childeducation').val()*$('#childeducationperMonth').val()).toFixed(2));
	$('#specialYear').val(($('#special').val()*$('#specialperMonth').val()).toFixed(2));
	$('#arrearsYear').val(($('#arrears').val()*$('#arrearsperMonth').val()).toFixed(2));
	$('#performanceiIncentiveYear').val(($('#performanceiIncentive').val()*$('#performanceiIncentiveperMonth').val()).toFixed(2));
	$('#medicalReimbursmentYear').val(($('#medicalReimbursment').val()*$('#medicalReimbursmentperMonth').val()).toFixed(2));
	$('#leaveEncashmentYear').val(($('#leaveEncashment').val()*$('#leaveEncashmentperMonth').val()).toFixed(2));
	$('#foodcouponsYear').val(($('#foodcoupons').val()*$('#foodcouponspermonth').val()).toFixed(2));
	$('#reimbursmentsYear').val(($('#reimbursments').val()*$('#reimbursmentspermonth').val()).toFixed(2));
	$('#internetexpensesYear').val(($('#internetExpenses').val()*$('#internetExpensespermonth').val()).toFixed(2));
	$('#driversalaryYear').val(($('#driversalary').val()*$('#driversalarypermonth').val()).toFixed(2));
	$('#otherperyear').val(($('#other').val()*$('#otherpermonth').val()).toFixed(2));
	
	var yearlyamount=Number($('#basicperYear').val())+Number($('#daperYear').val())+Number($('#hraperYear').val())+Number($('#transportYear').val())+Number($('#childeducationYear').val())+Number($('#specialYear').val())+Number($('#arrearsYear').val())+Number($('#performanceiIncentiveYear').val())+Number($('#medicalReimbursmentYear').val())+Number($('#leaveEncashmentYear').val())+Number($('#foodcouponsYear').val())+Number($('#reimbursmentsYear').val())+Number($('#internetexpensesYear').val())+Number($('#driversalaryYear').val())+Number($('#otherperyear').val());
	
	$('#totalsalperyear').val(yearlyamount.toFixed(2));	
}

function getMonthlyDeduction(){
	var monthlydedamount=Number($('#TDSDeductionfromsal').val())+Number($('#employeesPFContribution').val())+Number($('#professionaltax').val())
	$('#totaldeductionsperyear').val(monthlydedamount.toFixed(2));	
}

  