function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
function allStudent(){

	
	var classId=$("#classname").val();
	var sectionId=$("#sectionname").val();
	
	
	$.ajax({
		type : "GET",
		url : "reportaction.html?method=getStudentBySection",
		data : {"classId":classId,"sectionId":sectionId},
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			
			$("#student").html("");
			$("#student").append(
					'<option value="%%' + "" + '">' + ""
							+ 'All</option>');
			
			for (var j = 0; j < result.studentList.length; j++) {
				

				$("#student").append(
								'<option value="'
										+ result.studentList[j].studentid
										+ '">'
										+ result.studentList[j].studentFnameVar
										+ '</option>');
			}
		
			
		
		}

	});
	

}
function selectClass(){
	allStudent();
	var classId=$("#classname").val();
	
	$.ajax({
		type : "GET",
		url : "feecollection.html?method=getSectionByClass",
		data : {"classId":classId},
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			
			$("#sectionname").html("");
			$("#sectionname").append(
					'<option value="' + "" + '">' + ""
							+ '</option>');
			
			for (var j = 0; j < result.SectionList.length; j++) {
				

				$("#sectionname").append(
								'<option value="'
										+ result.SectionList[j].sectionId
										+ '">'
										+ result.SectionList[j].sectionname
										+ '</option>');
			}
		
			
		
		}

	});
	
	$("#sectionname").change(function(){
		allStudent();
	});
	
}
$(document)
		.ready(
				function() {
					var urlLinks=window.location.href.substring(window.location.href.lastIndexOf("=")+1);
					var urlLink=urlLinks.split(",")[4];
					var amount=0;
					var tot_conse_amt=0;
					allStudent();
					
					
					$(".notPaid #tot_conse_amt").text(tot_conse_amt);
					$("input.concession").change(function(){
						tot_conse_amt=0;
						var actualamount=$(this).parent("td").parent("tr").find(".feeAmount").val();
						var concession=parseFloat($(this).val());
						$(this).parent("td").parent("tr").find("input.consfeeAmount").val(parseFloat(actualamount)-((parseFloat(actualamount)*concession)/100));
						$("table tbody tr").each(function(){
							alert("alert");
							if(!(isNaN($(this).find(".feeAmount").val()))){
							var actualamount=$(this).find(".feeAmount").val();
							var concession=parseFloat($(this).find("input.concession").val());
							tot_conse_amt+=parseFloat(actualamount)-((parseFloat(actualamount)*concession)/100);
							$(this).find("input.consfeeAmount").val(parseFloat(actualamount)-((parseFloat(actualamount)*concession)/100));
							}
							
							if(!(isNaN($(this).find(".feePayingAmount").val()))){
								var collectedlamount=$(this).find(".feePayingAmount").val();
								tot_collect_amt+=parseFloat(collectedlamount);
							
								}
						});	
						alert(collectedlamount);
						$("toatalPayingAmount").val(tot_collect_amt);
						$("#tot_conse_amt").text(tot_conse_amt);
						
					
							 var paying_amount=0.00;
							 var outStandingAmount=0.00;
							$("#allstudent tbody tr").find(".notPaid .consfeeAmount").each(function(){
								var payingAmount=$(this).parent('td').parent('tr').find(".feePayingAmount").val();
								$(this).parent('td').parent('tr').find(".outStandingAmount").val((this.value)-payingAmount);						
								paying_amount+=Number($(this).val());
								outStandingAmount+=($(this).val()-payingAmount);
							});
							
							
							$("#tot_paid_amt").text(outStandingAmount);
					});
			
				$("table.pending td#tot_actual_amt").text((amount).toFixed(2));
					$("#search").click(function(){
						
					
						
						var stuId=$("#searchvalue").val();
						window.location.href="adminMenu.html?method=feeCollection&stuId="+stuId;
					
					});
					
					
					$('#excelDownload')
					.click(
							function() {
								
								window.location.href = "adminMenu.html?method=downloadfeecollectionXLS";
								
							});
					$("#pdfDownload").click(function(){
						
	        			window.location.href = "adminMenu.html?method=downloadfeecollectionPDF";
					
	        		});
					
					
					
					
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);

					
					var hclassid=$("#hclassid").val();
					var hSectionId=$("#hSectionId").val();
					var htermid=$("#htermId").val();
					
					
					if(hclassid!=""){
						
						$("#term option[value="+ htermid + "]").attr('selected', 'true');

						
						$("#classname option[value="+ hclassid + "]").attr('selected', 'true');
						
						selectClass();
						
						$("#sectionname option[value="+ hSectionId + "]").attr('selected', 'true');
						
					}
					
					
					
					
					$('#allstudent tr').each(function(){
						
						
						var isConcession=$(this).find('.isconcession').val();
						
						if(isConcession=='N'){
							
							$(this).find('.concessionamt').attr('readonly', true);
							
						}
					});
					
					var flag=true;
					
					$('.concessionamt ').change(function(){
						
						 flag=false;
						
						var actualAmt=$(this).parent().prev().text();
						
						var tot_concession=0;
						var tot_paidamt=0;
						
						var regexp = /^[0-9]+([\,\.][0-9]+)?$/g;
						
						 if((regexp.test($(this).val()))){
						
						if(parseInt($(this).val())<=parseInt(actualAmt)){
							
							flag=true;
							
							$(this).parent().next().text(parseInt(actualAmt)-parseInt($(this).val()));
						
							$('#allstudent tr').each(function(){
							
							var concessionamt=$(this).find('.concessionamt').val();
							var paidamount=$(this).find('.paidamount').text();
							
							if(concessionamt!=null && concessionamt!=undefined){
								
								tot_concession = tot_concession + parseInt(concessionamt);
							}
							
							if(paidamount !="" && paidamount!='Paid amount'){
								
								tot_paidamt = tot_paidamt + parseInt(paidamount);
								
							}
							
						});
						
						$("#tot_concession_amt").text(tot_concession);
						$("#tot_paid_amt").text(tot_paidamt);
						$("#opening_balance").text(tot_paidamt);
						
					}else{
						
						flag=false;
						
						$('.errormessagediv').show();
						$('.validateTips').text("Concession amount should not be more than Actual amount");
					}
						
				}else{
					
					flag=false;
					
					$('.errormessagediv').show();
					$('.validateTips').text("Enter valid Concession amount");
					
				}
						
			});
					
					
					  var  balance_amount=0.00;
					
						$(".feeAmount").change(function(){
							 var paying_amount=0.00;
							 var outStandingAmount=0.00;
							$("#allstudent tbody tr").find(".feeAmount").each(function(){
								var payingAmount=$(this).parent('td').parent('tr').find(".feePayingAmount").val();
								$(this).parent('td').parent('tr').find(".outStandingAmount").val($(this).parent('td').parent('tr').find(".consfeeAmount").val()-payingAmount);						
								$(this).parent('td').parent('tr').find(".consfeeAmount").val($(this).val());
								paying_amount+=Number($(this).val());
								
								outStandingAmount+=($(this).parent('td').parent('tr').find(".consfeeAmount").val()-payingAmount);
							});
									 
						
							  balance_amount=paying_amount;
						 $("#hopening_balance").val(balance_amount);
						 $("#tot_paid_amt,#outstanding_amount_after_the_current_payment").text(outStandingAmount+".00");
							  $("#opening_balance,#tot_actual_amt").text(balance_amount+".00");
						});
						
						
						$(".notPaid .feePayingAmount").change(function(){
							 var paying_amount=0.00;
							 var outStandingAmount=0.00;
							$("#allstudent tbody tr").find(".feePayingAmount").each(function(){
							var feeAmount=$(this).parent('td').parent('tr').find(".consfeeAmount").val();
							
									$(this).parent('td').parent('tr').find(".outStandingAmount").val(feeAmount-(this.value));	
									paying_amount+=Number($(this).val());
									outStandingAmount+=(feeAmount-$(this).val());
								
							});
							

							$(".toatalPayingAmount,#currentpayment1").val(paying_amount+".00");
							 $("#tot_paid_amt,#outstanding_amount_after_the_current_payment").text(outStandingAmount+".00");
							
						});
						
						$(".pending .feePayingAmount").change(function(){
							 var paying_amount=0.00;
							 var outStandingAmount=0.00;
							$("#allstudent tbody tr").find(".feePayingAmount").each(function(){
							var feeAmount=$(this).parent('td').parent('tr').find(".feeAmount").val();
							
									$(this).parent('td').parent('tr').find(".outStandingAmount").val(feeAmount-(this.value));	
									paying_amount+=Number($(this).val());
									outStandingAmount+=(feeAmount-$(this).val());
								
							});
							

							$(".toatalPayingAmount,#currentpayment1").val(paying_amount+".00");
							 $("#tot_paid_amt,#outstanding_amount_after_the_current_payment").text(outStandingAmount+".00");
							
						});
						$('#currentpayment1 ').change(function(){
							
							
							 var paying_amount = $("#currentpayment1").val();
							 var opening_balance = $("#hopening_balance").val();
							 
							 
							    balance_amount =opening_balance-paying_amount;
							 
							 $("#outstanding_amount_after_the_current_payment").text(balance_amount+".00");
							
				});
						/*$("#allstudent tbody tr").find(".dateofJoinId").each(function(){
					var checkDate=$(this).val();
					if(checkDate == undefined && checkDate == null){
						$(".dateofJoinId").val("00-00-0000");
					}
				});*/
						
						
					
					
					
					if(urlLink=="Pending"){
						$("table.pending").show();
						$("table.notPaid").hide("fast");
						$("table.notPaid").empty();
						$("table.notPaid input").attr("name","disabled");
						$("table.notPaid input").attr("id","disabled");
						
						$(".dateofJoinId").datepicker(
								{

									dateFormat : "dd-mm-yy",
									yearRange : 'c-65:c+65',
									maxDate : 0,
									changeMonth : "true",
									changeYear : "true",
								});
							var date2 = new Date();
							day  = date2.getDate();  
							if(day<10){
								day="0"+day;
							}
				            month = date2.getMonth() + 1;   
				            if(month<10){
				            	month="0"+month;
				            }
				            year =  date2.getFullYear();
						
$("#saveid").click(function(){
							
							
							if(flag){
								
								 
								var urlLink=window.location.href.substring(window.location.href.lastIndexOf("=")+1);
								var studentid=urlLink.split(",")[0];
								
								
								var addmissionNo=studentid;
								var accodemicyear=$("#hyear").val();
								var termid=$("#hterm").val();
								var classd=$("#hclass").val();
								var concession=$("#concessionPercent").text();
								
								var tot_actual_amt=parseFloat($("#tot_actual_amt").text())+parseFloat($("#hamount_paid").val());
								var tot_opning_amt=$("#tot_actual_amt").text();
								var tot_concession_amt=$("#htot_concession_amt").val();
								var tot_paid_amt=parseFloat($("#hamount_paid").val())+parseFloat($("#currentpayment1").val());
							
								var feeIdArray=[];
								var dateofJoinIdArray=[];
								var feeAmountArray=[];
								var concessionpercentArray=[];
								var consfeeAmountArray=[];
								var feePayingAmountArray=[];
								var outStandingAmountArray=[];
								var payingAmountArray=[];
								var updateFeeAmountArray=[];
								var hamount_paid_so_far=$("#hamount_paid_so_far").val();
								var hopening_balance=$("#hopening_balance").val();
								var currentpayment1=$("#currentpayment1").val();
								var outstanding_amount_after_the_current_payment=$("#outstanding_amount_after_the_current_payment").text();
								
								$('#allstudent tr').each(function(){
									
									feeId=$(this).find('.feecode').val();
									dateofJoinId=$(this).find('.dateofJoinId').val();
									feeAmount=$(this).find('.hfeeAmount').val();
									updateFeeAmount=$(this).find('.feeAmount').val();
									concessionpercent=$(this).find('input.hconcessionpercentArray').val();
									
									
									
									consfeeAmount=$(this).find('.feeBalanceAmount').val()-(($(this).find('input.hconcessionpercentArray').val()*$(this).find('.feeBalanceAmount').val())/100);
								
									feePayingAmount=$(this).find('.feePayingAmount').val();
									outStandingAmount = $(this).find('.outStandingAmount').val();
								
									
									if(feeId!=undefined && feeId!=""){
										
										feeIdArray.push(feeId.trim());
									}
									if(dateofJoinId!=undefined && dateofJoinId!=""){
										
										dateofJoinIdArray.push(dateofJoinId.trim());
									}
									if(feeAmount!=undefined && feeAmount!=""){
										
										feeAmountArray.push(feeAmount.trim());
										updateFeeAmountArray.push(updateFeeAmount.trim());
									}
									if(feePayingAmount!=undefined && feePayingAmount!=""){
										
										feePayingAmountArray.push(parseFloat($(this).find(".feePayingAmount").val())+parseFloat($(this).find(".feePaidAmount").val()));
										payingAmountArray.push($(this).find(".feePayingAmount").val());
									}
									
									if(outStandingAmount!=undefined && outStandingAmount!=""){
										
										outStandingAmountArray.push(outStandingAmount.trim());
									}
									if(concessionpercent!=undefined && concessionpercent!=""){
										
										concessionpercentArray.push(concessionpercent);
									
									}
									if(consfeeAmount!=undefined && consfeeAmount!=""){
										
										consfeeAmountArray.push(consfeeAmount);
									}
									
									
								});
								
								var datalist={
										"tot_opning_amt":tot_opning_amt.trim(),
										"addmissionNo":addmissionNo.trim(),
										"accodemicyear":accodemicyear.trim(),
										"termid":termid.trim(),
										"classd":classd.trim(),
										"concession":concession.trim().substring(0, concession.trim().length-1),
										"tot_actual_amt":tot_actual_amt,
										"tot_concession_amt":tot_concession_amt.trim(),
										"tot_paid_amt":tot_paid_amt,
										
										"feeIdArray" : feeIdArray.join(","),
										"dateofJoinIdArray" : dateofJoinIdArray.join(","),
										"feeAmountArray" : feeAmountArray.join(","),
										"payingAmountArray":payingAmountArray.join(","),
										"feePayingAmountArray" : feePayingAmountArray.join(","),
										"outStandingAmountArray" : outStandingAmountArray.join(","),
										"concessionpercentArray":concessionpercentArray.join(","),
										"consfeeAmountArray":consfeeAmountArray.join(","),
										"updateFeeAmountArray":updateFeeAmountArray.join(","),
										"hamount_paid_so_far":hamount_paid_so_far,
										"hopening_balance":hopening_balance,
										"currentpayment1":currentpayment1,
										"outstanding_amount_after_the_current_payment":outstanding_amount_after_the_current_payment
								};
								
								
								$.ajax({
									type : "GET",
									url : "feecollection.html?method=saveFeeCollection",
									data :datalist,
									async : false,
									success : function(data) {
										var result = $.parseJSON(data);
										
										
										if(result.status=="true"){
											
											$('.successmessagediv').show();
											$('.validateTips').text("Fee collection details saved successfully And Please Issue Fee Reciept To Student");
											var accyear=$("#hyear").val();
										     var sectionid=$("#hsection").val();
											 var classname=$("#hclass").val();
										   	var studentname=studentid;
											 
											 window.location.href ='onlinefeereceipt.html?method=onlineFeeReceiptPDFReport&accyear='
													+accyear
													+ ' &sectionid='
													+sectionid
													+ ' &classname='
													+classname
													+ ' &studentname='
													+studentname;
										setTimeout(function(){
											
											 window.location.href ='adminMenu.html?method=feeCollection';
										 
										 },3000);
										
										}else{
											
											$('.errormessagediv').show();
											$('.validateTips').text("Fee collection not done.Try later");

										}
									
									}
								});
							
							
							 
								
							}else{
								
								$('.errormessagediv').show();
								$('.validateTips').text("Enter valid Concession amount");
							}
							
						});
					
					
					
					}
					else{
						$("table.notPaid").show();
						$("table.pending").empty();
						$("table.pending").hide("fast");
						$("table.pending input").attr("name","disabled");
						$("table.pending input").attr("id","disabled");
						$(".dateofJoinId").val("00-00-0000");
						$(".dateofJoinId").datepicker(
								{

									dateFormat : "dd-mm-yy",
									yearRange : 'c-65:c+65',
									maxDate : 0,
									changeMonth : "true",
									changeYear : "true",
									onClose : function(selectedDate) {
										if ((selectedDate != "")
												&& (selectedDate != undefined)) {
											var date2 = $('.dateofJoinId')
													.datepicker('getDate');
											date2.setDate(date2.getDate() - 1);
											$(".dateofBirthId").datepicker(
													"option", "maxDate", date2);
										}
									}
								});
					
					
					
						$("#saveid").click(function(){
							
							
							if(flag){
								
								
								var urlLink=window.location.href.substring(window.location.href.lastIndexOf("=")+1);
								var studentid=urlLink.split(",")[0];
								
								
								var addmissionNo=studentid;
								var accodemicyear=$("#hyear").val();
								var termid=$("#hterm").val();
								var classd=$("#hclass").val();
								var concession=$("#concessionPercent").text();
								var tot_opning_amt=$("#tot_actual_amt").text();
								var tot_actual_amt=$("#tot_actual_amt").text();
								var tot_concession_amt=$("#htot_concession_amt").val();
								var tot_paid_amt=$("input.toatalPayingAmount").val();
								
								var feeIdArray=[];
								var dateofJoinIdArray=[];
								var feeAmountArray=[];
								var concessionpercentArray=[];
								var consfeeAmountArray=[];
								var feePayingAmountArray=[];
								var outStandingAmountArray=[];
								var payingAmountArray=[];
								var updateFeeAmountArray=[];
								
								var hamount_paid_so_far=$("#hamount_paid_so_far").val();
								var hopening_balance=$("#hopening_balance").val();
								var currentpayment1=$("#currentpayment1").val();
								var outstanding_amount_after_the_current_payment=$("#outstanding_amount_after_the_current_payment").text();
								
								$('#allstudent tr').each(function(){
									
									feeId=$(this).find('.feecode').val();
									dateofJoinId=$(this).find('.dateofJoinId').val();
									feeAmount=$(this).find('.hfeeAmount').val();
									updateFeeAmount=$(this).find('.feeAmount').val();
									concessionpercent=$(this).find('input.concession').val();
								
									consfeeAmount=$(this).find("input.consfeeAmount").val();
								
									
									feePayingAmount=$(this).find('.feePayingAmount').val();
									outStandingAmount = $(this).find('.outStandingAmount').val();
								
									
									if(feeId!=undefined && feeId!=""){
										
										feeIdArray.push(feeId.trim());
									}
									if(dateofJoinId!=undefined && dateofJoinId!=""){
										
										dateofJoinIdArray.push(dateofJoinId.trim());
									}
									if(feeAmount!=undefined && feeAmount!=""){
										
										feeAmountArray.push(feeAmount.trim());
										updateFeeAmountArray.push(updateFeeAmount.trim());
									}
									if(feePayingAmount!=undefined && feePayingAmount!=""){
										
										feePayingAmountArray.push(parseFloat($(this).find(".feePayingAmount").val())+parseFloat($(this).find(".feePaidAmount").val()));
										payingAmountArray.push($(this).find(".feePayingAmount").val());
										
									}
									
									if(outStandingAmount!=undefined && outStandingAmount!=""){
										
										outStandingAmountArray.push(outStandingAmount.trim());
									}
									if(concessionpercent!=undefined && concessionpercent!=""){
										
										concessionpercentArray.push(concessionpercent.trim());
									
									}
									if(consfeeAmount!=undefined && consfeeAmount!=""){
										
										consfeeAmountArray.push(consfeeAmount.trim());
									}
									
									
								});
								
								var datalist={
										"tot_opning_amt":tot_opning_amt.trim(),
										"addmissionNo":addmissionNo.trim(),
										"accodemicyear":accodemicyear.trim(),
										"termid":termid.trim(),
										"classd":classd.trim(),
										"concession":concession.trim().substring(0, concession.trim().length-1),
										"tot_actual_amt":tot_actual_amt.trim(),
										"tot_concession_amt":tot_concession_amt.trim(),
										"tot_paid_amt":tot_paid_amt.trim(),
										"payingAmountArray":payingAmountArray.join(","),
										"feeIdArray" : feeIdArray.join(","),
										"dateofJoinIdArray" : dateofJoinIdArray.join(","),
										"feeAmountArray" : feeAmountArray.join(","),
										"feePayingAmountArray" : feePayingAmountArray.join(","),
										"outStandingAmountArray" : outStandingAmountArray.join(","),
										"concessionpercentArray":concessionpercentArray.join(","),
										"consfeeAmountArray":consfeeAmountArray.join(","),
										"updateFeeAmountArray":updateFeeAmountArray.join(","),
										"hamount_paid_so_far":hamount_paid_so_far,
										"hopening_balance":hopening_balance,
										"currentpayment1":currentpayment1,
										"outstanding_amount_after_the_current_payment":outstanding_amount_after_the_current_payment
								};
								
								
								$.ajax({
									type : "GET",
									url : "feecollection.html?method=saveFeeCollection",
									data :datalist,
									async : false,
									success : function(data) {
										var result = $.parseJSON(data);
										
										
										if(result.status=="true"){
											
											$('.successmessagediv').show();
											$('.validateTips').text("Fee collection details saved successfully And Please Issue Fee Reciept To Student");
											 var accyear=$("#hyear").val();
										     var sectionid=$("#hsection").val();
											 var classname=$("#hclass").val();
										   	var studentname=studentid;
											 
											 window.location.href ='onlinefeereceipt.html?method=onlineFeeReceiptPDFReport&accyear='
													+accyear
													+ ' &sectionid='
													+sectionid
													+ ' &classname='
													+classname
													+ ' &studentname='
													+studentname;
										setTimeout(function(){
											
											 window.location.href ='adminMenu.html?method=feeCollection';
										 
										 },3000);
										
										}else{
											
											$('.errormessagediv').show();
											$('.validateTips').text("Fee collection not done.Try later");

										}
									
									}
								});
							
							
							 
								
							}else{
								
								$('.errormessagediv').show();
								$('.validateTips').text("Enter valid Concession amount");
							}
							
						});
					
					}
				
					
				
			
				
				
				
				
				});

