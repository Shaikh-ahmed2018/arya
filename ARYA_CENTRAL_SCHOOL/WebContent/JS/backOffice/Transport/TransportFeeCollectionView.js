$(document).ready(function(){
	count=2;

	$("#dd_cheque_date").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",

	});
	
	$(".selectmonth.Paid").change(function(){
		if($(this).attr("class").split(" ")[1]=="Paid")
		{
		$(this).prop("checked",false);
		}
	});
	$(".selectmonth.Paid").each(function(){
		if($(this).attr("class").split(" ")[1]=="Paid")
		{	
			count++;
		}
		
	});
	
	$("#paymentMode").change(function(){
		
		if($(this).val()=="cash" || $(this).val()==""){
			$("#paymentParticulars,#dd_cheque_date,#dd_cheque_bank").hide();
		}
		else{
			$("#paymentParticulars,#dd_cheque_date,#dd_cheque_bank").show();
			$("#paymentParticulars").attr("placeholder","Enter "+$(this).val()+" number.");
			
		}
	});
	
	
	$(".table.allstudent tbody tr:nth-child("+count+")").find(".refund").show();
	
	$(".print").click(function(){
		$(".ui-dialog-buttonset button:nth-child(1)").show();
		
		$("#Dialog").empty();
		termname=$(this).closest("tr").find(".heading").text();
		feeCollectionDetailsPrint($(this));
		$("#Dialog").dialog('open');
		
	});
	
	
	
	$(".pay").click(function(){
		 if($(".selectmonth.Not.Paid:checked").length>0){
				$("#mainDialog .paymentMode").show();
			 $('.errormessagediv').hide();
			// $("#myDialog").empty();
			 termname=$(this).closest("tr").find(".heading").text();
			 feeCollectionDetails($(this));
		$("#mainDialog").dialog('open');
		$(".ui-dialog-titlebar").text($(".feeName").text());
		$(".ui-dialog-buttonset button:nth-child(1)").show();
		 }
		 else{
			 $('.errormessagediv').show();
				$('.validateTips').text("Select Any Record for Payement ");
		 }
		
		
	});
	$("#mainDialog").dialog({
	    autoOpen  : false,
	    modal     : true,
	    buttons   : {
	              'Pay' : function() {
	            	  
	            	  var flag=true;
		            	 if($("#paymentMode").val() !="" && $("#paymentMode").val() !=undefined){
		            		 if($("#paymentMode").val() !="cash"){
		            			 flag=false;
		            			 message="Select Payment Particulars type.";
		            		
		            		
		            		 if($("#paymentParticulars").val().length>0){
		            			 
		            			 flag=true;
		            		 }
		            		 else{
		            			 flag=false;
		            			 message="Enter DD/Cheque No.";
		            			 getError(message,"#paymentParticulars");
		            			 return false;
		            		 }
		            		 if($("#dd_cheque_bank").val().length>0){
		            			 
		            			 flag=true;
		            		 }
		            		 else{
		            			 flag=false;
		            			 message="Enter Bank Name.";
		            			 getError(message,"#dd_cheque_bank");
		            			 return false;
		            		 }
		            		 if($("#dd_cheque_date").val().length>0){
		            			 
		            			 flag=true;
		            		 }
		            		 else{
		            			 flag=false;
		            			 message="Select DD/Cheque Date.";
		            			 getError(message,"#dd_cheque_date");
		            			 return false;
		            		 }
		            	 }
		            		
		            if(isNaN($(".payingAmount").val()) || $(".payingAmount").val()==0){
		            			 flag=false;
		            			 message="Enter Number greater than zreo.";
		            }
	            	  
	            	  var termid=[];
	            	  var monthName=[];
	            	  var monthlyAmount=[];
	            	  $(".selectmonth.Not.Paid:checked").each(function(){
	            		  termid.push($(this).val().split(",")[0]);
	            		  monthName.push($(this).val().split(",")[1]);
	            		  monthlyAmount.push($(this).val().split(",")[2]);
	            	  });
					var datalist={
						
							"addmissionNo":$("#hstudentid").val(),
							"termid":termid.toString(),
							'accodemicyear':$("#haccYear").val(),
							"totalAmount":$(".totalAmount").val(),
							"monthlyAmount":monthlyAmount.toString(),
							"monthName":monthName.toString(),
							"paymentMode":$("#paymentMode").val(),
							"dd_cheque_bank":$("#dd_cheque_bank").val(),
							"dd_cheque_date":$("#dd_cheque_date").val(),
							"paymentParticulars":$("#paymentParticulars").val(),
							"payingAmount":$(".payingAmount").val(),
					};
					if(flag){
					$.ajax({
						type : "GET",
						url : "feecollection.html?method=saveTransportFeeCollection",
						data :datalist,
						async : false,
						success : function(data) {
							var result = $.parseJSON(data);
							
							
							if(result.status=="true"){
								
								$('.successmessagediv').show();
								$('.sucessmessage').text("Fee Submit progressing... ");
								
							
							setTimeout(function(){
								
								 location. reload();
							 
							 },2000);
							
							}else{
								
								$('.errormessagediv').show();
								$('.validateTips').text("Fee collection not done.Try later");

							}
						
						}
					});
					 $("#myDialog").empty();
	            	  $(this).dialog('close');
		          }
					else{
	            		 getError(message,"#paymentMode");
					}
		        }
	            	  else{
		            		 $("#paymentMode").focus();
		            		 $("#paymentMode").css({
		            			 "border-color":"#f00"
		            		 });
		            		 $('.errormessagediv').show();
		            		 $('.errormessagediv').delay(2000).fadeOut();
								$('.validateTips').text("Select Payment Mode type."); 
		            		 
		            	 }
	            	  
	              },
	              'Close' : function() {
	            	  $("#myDialog").empty();
	           
	                  $(this).dialog('close');
	              }
	                }
	});

	$(".refund").click(function(){
		$("#Dialog").empty();
		termname=$(this).closest("tr").find(".heading").text();
		$("#Dialog").append("<p>Are You Sure to Refund?</p>");
		$("#Dialog").dialog('open');
		refundTermId=$(this).attr("id").split(",")[0];
		refundTermMonth=$(this).attr("id").split(",")[1];
		refundAmount=$(this).attr("id").split(",")[2];
		refrecieptNo=$(this).attr("id").split(",")[3];
		$(".ui-dialog-titlebar").text("Refund "+termname+" "+$(this).attr("id").split(",")[1]);
	});
	
	$("#Dialog").dialog({
	    autoOpen  : false,
	    modal     : true,
	    buttons   : {
	              'Refund' : function() {
	            	 
	            	
	            	
	            	 
					var datalist={
						
							"addmissionNo":$("#hstudentid").val(),
							"termid":refundTermId,
							'accodemicyear':$("#haccYear").val(),
							"monthlyAmount":refundAmount,
							"monthName":refundTermMonth,
							"refrecieptNo":refrecieptNo,
							"refundstatus":"rf",
							
					};
					
					$.ajax({
						type : "GET",
						url : "feecollection.html?method=refundTransportFeeCollection",
						data :datalist,
						async : false,
						success : function(data) {
							var result = $.parseJSON(data);
							
							
							if(result.status=="true"){
								
								$('.successmessagediv').show();
								$('.sucessmessage').text("Fee Refund progressing... ");
								
							
							setTimeout(function(){
								
								 location. reload();
							 
							 },2000);
							
							}else{
								
								$('.errormessagediv').show();
								$('.validateTips').text("Fee Refunding not done.Try later");

							}
						
						}
					});
					 $("#Dialog").empty();
	            	  $(this).dialog('close');
	              },
	              'Close' : function() {
	            	  $("#Dialog").empty();
	           
	                  $(this).dialog('close');
	              }
	                }
	});
	
	
	$(".selectmonth.Not.Paid").change(function(){
		if($(this).closest("tr").prev("tr").find(".selectmonth.Not.Paid").prop("checked")!= undefined){
		if($(this).closest("tr").prev("tr").find(".selectmonth.Not.Paid").prop("checked")==true ){
			if($(this).prop("checked")==true){
			$(this).prop("checked",true);
			}
			else{
				if($(this).closest("tr").next("tr").find(".selectmonth.Not.Paid").prop("checked")==true )
				$(this).prop("checked",true);
				else
				$(this).prop("checked",false);
			}
		}
		else{
			$(this).prop("checked",false);
		}
		}
	});
	
	
});
function feeCollectionDetails(pointer){
	var advance=0.0;
	var balance=0.0;
	$.ajax({
		type:'POST',
		url:'feecollection.html?method=getAdvanceOrBalanceForTransportFee',
		data:{stuId:$("#hstudentid").val()},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			advance=result.data[0].advanceAmt;
			balance=result.data[0].dueAmt;
		}
	});
	 i=1;
			var totalAmount=0.0;
			$("#myDialog").append("<table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' width='100%' id='allstudent'>" +
					"<tr><th>Sl.No.</th>" +
					"<th style='text-align:left !important'>Fee Description</th>" +
					"<th>Fee Amount</th>"+
					"</tr>" +
					"</table>");
			$(".selectmonth.Not.Paid:checked").each(function(){
				
				
				$("#myDialog #allstudent").append("<tr id=rowid"+i+">" +
						"<td>"+i+"</td>" +
						"<td style='text-align:left !important;padding-left:5px;'>"+$(this).closest("tr").find(".heading").text()+" "+$(this).closest("tr").find(".transportMonth").text()+" Fee</td>" +
						"<td ><input type='text' class='Feeamount' name='transportfee' value='"+$(this).closest("tr").find(".transportfee").text()+"' readonly='readonly' style='text-align:right;' /></td>" +
						"</tr>");
				i++;
			});
			$("#myDialog #allstudent").append("<tr id=rowid"+i+">" +
					"<td>"+i+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Advance</td>" +
					"<td ><input type='text' class='advance' name='advanceamt' value='"+advance.toFixed(2)+"' readonly='readonly' style='text-align:right;' /></td>" +
					"</tr>");
		
			$("#myDialog #allstudent").append("<tr id=rowid"+(i+1)+">" +
					"<td>"+(i+1)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Dues</td>" +
					"<td ><input type='text' class='Feeamount' name='dueamt' value='"+balance.toFixed(2)+"' readonly='readonly' style='text-align:right;' /></td>" +
					"</tr>");
			$("#myDialog #allstudent").append("<tr>" +
					"<th></th>" +
					"<th>Total</th>" +
					"<th ><input type='text' class='totalAmount' name='totalAmount' value='' readonly='readonly' style='text-align:right;background-color:rgba(255, 224, 0, 0.22);' /></th>" +
					"" +
					"</tr>");
			
				$("#myDialog #allstudent tbody").find(".Feeamount").each(function(){
					totalAmount=totalAmount+parseFloat($(this).val());
				});
				totalAmount=totalAmount-Number($("#myDialog #allstudent tbody").find(".advance").val());
				$("#myDialog #allstudent").append("<tr>" +
						"<th></th>" +
						"<th>Paying</th>" +
						"<th ><input type='text' class='payingAmount' name='payingAmount' value='"+totalAmount.toFixed(2)+"'  style='text-align:right;' onkeypress='return CheckIsNumeric(event);' /></th>" +
						"" +
						"</tr>");
				$("#myDialog #allstudent tbody").find(".totalAmount").val(parseFloat(totalAmount).toFixed(2));
				$(".Feeamount").each(function(){
					$(this).val(parseFloat($(this).val()).toFixed(2));
				});
}
function feeCollectionDetailsPrint(pointer){

			var totalAmount=0.0;
			$("#Dialog").append("<h3 style='text-align:center;'>ARYA CENTRAL SCHOOL</h3>" +
					"<h3 style='text-align:center;'>Pattom Trinuvananthapuram-695 004.Kerla</h3>" +
					"<hr>" +
					"<span>AdmnNo:</span><span>"+$("#haddmissionno").val()+"</span>"+"&nbsp;&nbsp;&nbsp;&nbsp"+"<span class='paiddate'>Paid Date:</span><span><b>"+pointer.attr('id')+"</b></span>" +
					"<br>" +
					"<span>Name:</span>"+$("#hstuName").val()+"</span>"+"&nbsp;&nbsp;&nbsp;&nbsp"+"<span  class='receipt'>Receipt No:</span><span><b>"+pointer.attr('name')+"</b></span>" +
					"<br>" +
					"<span>Class & Div:</span>"+$("#hclassname").val() +
					"<hr>" +
					"<h3 style='text-align:center;'>FEE RECEIPT</h3>" +
					"<div style='text-align:center;'>"+termname +"</div>"+
					"<hr>" +
					"");
			$("#Dialog").append("<table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' style='width:100%' id='printtable'>" +
				
					"<th style='text-align:left !important'>Fee Description</th>" +
					"<th style='text-align:right;'>Fee Amount</th>"+
					"</tr>" +
					"</table>");
				
				
		
				$(".selectmonth.Not.Paid:checked").each(function(){
					
					i++;
					$("#Dialog #printtable").append("<tr id=rowid"+i+">" +
							"<td>"+i+"</td>" +
							"<td style='text-align:left !important;padding-left:5px;'>"+$(this).closest("tr").find(".heading").text()+" "+$(this).closest("tr").find(".transportMonth").text()+" Fee</td>" +
							"<td ><input type='text' class='Feeamount' name='transportfee' value='"+$(this).closest("tr").find(".transportfee").text()+"' readonly='readonly' style='text-align:right;' /></td>" +
							"</tr>");
		
				});
			
			$("#Dialog #printtable tbody").find(".Feeamount").each(function(){
				totalAmount=totalAmount+parseFloat($(this).val());
			});
			$("#Dialog #printtable").append("<tr>" +
			
					"<th style='text-align:left;'> Total</th>" +
					"<th style='text-align:right;'><input type='text' class='totalAmount' name='totalAmount' value='"+totalAmount+"' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					"" +
					"</tr>");
			
				
				$("#Dialog #printtable tbody").find(".totalAmount").val(parseFloat(totalAmount).toFixed(2));
				$(".Feeamount").each(function(){
					$(this).val(parseFloat($(this).val()).toFixed(2));
				});
}

function getError(message,id){
	$("input,select").not(id).css({
		 "border-color":"#ddd"
	});
	$(id).focus();
	 $(id).css({
		 "border-color":"#f00"
	 });
	$('.errormessagediv').show();
	 $('.errormessagediv').delay(2000).fadeOut();
		$('.validateTips').text(message);
}