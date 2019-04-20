$(document).ready(function(){
	
	$("#dd_cheque_date").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",

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
	$(".pay").each(function(){
		if($(this).attr("name")=="Paid")
		{
			$(this).hide();
			$(this).next(".view").show();
			$(this).next().next(".print").show();
		}
		
	});
	$(".print").click(function(){
		$(".ui-dialog-buttonset button:nth-child(1)").show();
		
		$("#Dialog").empty();
		termname=$(this).closest("tr").find(".heading").text();
		
		
		feeCollectionDetailsPrint($(this));
		$("#Dialog").dialog('open');
		
	});
	//
	jQuery(document).ready(function($) {

		//find <span data-convert="price_convert">50000</span>
		$('span[data-convert="price_convert"]').each(function() {
		
			//rounds a number DOWNWARDS to the nearest integer
			 var price =Math.floor($(this).html()); 
		     
		     var price_str		=	new String(price);
		     var price_length 	= 	price_str.length;
		     var price_split  	= 	price_str.split('');

		     if(price_length <= 2 ) {
			     return true;
		     } 

		     var word  = '';
			 var digit = '';

			 switch(price_length) {
		     	case 3 :
			     	digit = price_split[0];
			     	word  = 'Hundred';
			     	break ;
		     	case 4 :
			     	digit = price_split[0];
			     	word  = 'Thousand';
			     	break ; 
		     	case 5 :
			     	digit = price_split[0]+price_split[1];
			     	word  = 'Thousand';
			     	break ;
		     	case 6 :
			     	digit = price_split[0];
			     	word  = 'Lakhs';
			     	break ; 
		     	case 7 :
			     	digit = price_split[0]+price_split[1];
			     	word  = 'Lakhs';
			     	break ;
		     	case 8 :
			     	digit = price_split[0]+'.'+price_split[1]+price_split[2];
			     	word  = 'Crores';
			     	break ; 
		     	case 9 :
			     	//alert(price_split[0]+price_split[1]+price_split[3]+price_split[4]);
			     	digit = price_split[0]+price_split[1]+'.'+price_split[2]+price_split[3];
			     	word  = 'Crores';
			     	break ;
		     	case 10 :
			     	digit = price_split[0]+price_split[1]+price_split[2]+'.'+price_split[3]+price_split[4];
			     	word  = 'Crores';
			     	break ; 
		     	case 11 :
			     	digit = price_split[0]+price_split[1]+price_split[2]+price_split[3]+'.'+price_split[4]+price_split[5];
			     	word  = 'Crores';
			     	break ;
			     	
		     }

		     var value =  ' ₹ '+digit+' '+word;
		     $(this).html(value); 
		     
				});
			});
	//
	$("#Dialog").dialog({
	    autoOpen  : false,
	    modal     : true,
	    buttons   : {
	              'Print' : function() {
	            	  var a=$("#printing-css").val();
	            	  
	            	   var b= document.getElementById("Dialog").innerHTML;
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
		    	      	frameDoc.document.write('<style>div,th,td,input{letter-spacing: normal;outline:none;font-weight:200 !important;border:none !important;font-size:13px !important;font-family:Coinage Caps Kruger Gray !important;font-weight:bold;}</style>');
		    	        frameDoc.document.write('</head><body>');
		    	      
		    	        
		    	        //Append the DIV contents.
		    	        frameDoc.document.write(abd);
		    	        frameDoc.document.write('</body></html>');
		    	        frameDoc.document.close();
		    	        setTimeout(function () {
		    	            window.frames["frame1"].focus();
		    	            window.frames["frame1"].print();
		    	            frame1.remove();
		    	        }, 100);
	            	
					 $("#Dialog").empty();
	            	  $(this).dialog('close');
	              },
	              'Close' : function() {
	            	  $("#Dialog").empty();
	           
	                  $(this).dialog('close');
	              }
	                }
	});
	
	
	
	
	$(".pay").each(function(){
		if($(this).attr("name")=="Not Paid")
		{
			$(".pay").not(this).hide();
			return false;
		}
		
	});
	
	$(".pay").click(function(){
		$("#myDialog .paymentMode").show();
		$("#myDialog .myDailogTable").empty();
		termname=$(this).closest("tr").find(".heading").text();
		feeCollectionDetails($(this));
		$("#myDialog").dialog('open');
		
		$(".ui-dialog-titlebar").text($(this).closest("tr").find(".heading").text()+" Fee Collection");
		$(".ui-dialog-buttonset button:nth-child(1)").show();
		
	});
	$("#myDialog").dialog({
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
	            	  var feeAmountArray=[];
	            	  var feeIdArray=[];
	            	  var advanceCarry=0.0;
	            	  var duesCarry=0.0;
					$("#myDialog #allstudent tr[id]").each(function(){	
					var	amount=$(this).find('.Feeamount').val();
						feeAmountArray.push(amount);
						var feeId=$(this).find('.Feeamount').attr("name");
						feeIdArray.push(feeId);
				
						});
					if($(".payingAmount").val()>$(".totalAmount").val()){
						advanceCarry=$(".payingAmount").val()-$(".totalAmount").val();
					}
					else{
						duesCarry=$(".totalAmount").val()-$(".payingAmount").val();
					}
					var datalist={
							"concessionAmount":$(".concessionAmount").val(),
							"addmissionNo":$("#hstudentid").val(),
							"termid":$("#myDialog #allstudent").attr("name"),
							'accodemicyear':$("#haccYear").val(),
							'classd':$("#hclassId").val(),
							'specialization':$("#hspecialization").val(),
							"feeIdArray" : feeIdArray.toString(),
							"paymentMode":$("#paymentMode").val(),
							"dd_cheque_bank":$("#dd_cheque_bank").val(),
							"dd_cheque_date":$("#dd_cheque_date").val(),
							"paymentParticulars":$("#paymentParticulars").val(),
							"feeSetting":$("#myDialog #allstudent").attr("id"),
							"feeAmountArray" : feeAmountArray.toString(),
							"totalAmount":$(".totalAmount").val(),
							"fineAmount":$("#FineAmount").val(),
							"payingAmount":$(".payingAmount").val(),
							"advanceCarry":advanceCarry,
							"duesCarry":duesCarry,
					};
					if(flag){
					$.ajax({
						type : "GET",
						url : "feecollection.html?method=saveFeeCollection",
						data :datalist,
						async : false,
						success : function(data) {
							var result = $.parseJSON(data);
							if(result.status=="true"){
								$('.successmessagediv').show();
								$('.sucessmessage').text("Fee Submit progressing... ");
							
							setTimeout(function(){
								
								 location.reload();
							 
							 },2000);
							
							}else{
								
								$('.errormessagediv').show();
								$('.validateTips').text("Fee collection not done.Try later");

							}
						
						}
					});
					 $("#myDialog .myDailogTable").empty();
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
	            	  $("#myDialog .myDailogTable").empty();
	           
	                  $(this).dialog('close');
	              }
	                }
	});

	$(".view").click(function(){
		$("#myDialog .paymentMode").hide();
		$("#myDialog .myDailogTable").empty();
		termname=$(this).closest("tr").find(".heading").text();
		feepaidDetails($(this));
		$("#myDialog").dialog('open');
		
		$(".ui-dialog-titlebar").text($(this).closest("tr").find(".heading").text()+" Fee Collection");
		$(".ui-dialog-buttonset button:nth-child(1)").hide();
	});
	
});
function feeCollectionDetails(pointer){
	var dataList={'term':pointer.closest("tr").find(".heading").attr("id"),
			'accYear':$("#haccYear").val(),
			'classId':$("#hclassId").val(),
			'specialization':$("#hspecialization").val(),
			'student':$("#hstudentid").val()
			};
	$.ajax({
		type:'post',
		url:'feecollection.html?method=feeCollectionDetails',
		data:dataList,

		success : function(
				response) {
			var result = $.parseJSON(response);
			var totalAmount=0.0;
			$("#myDialog .myDailogTable").append("<table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' width='100%' id='allstudent'>" +
					"<tr><th>Sl.No.</th>" +
					"<th style='text-align:left !important'>Fee Description</th>" +
					"<th>Fee Amount</th>"+
					"</tr>" +
					"</table>");
		
			for(var i=0;i<result.FeeCollectionDetails.length;i++){
				
				$("#myDialog .myDailogTable #allstudent").append("<tr id=rowid"+result.FeeCollectionDetails[i].sno+">" +
						"<td>"+result.FeeCollectionDetails[i].sno+"</td>" +
						"<td style='text-align:left !important;padding-left:5px;'>"+result.FeeCollectionDetails[i].feename+"</td>" +
						"<td ><input type='text' class='Feeamount' name='"+result.FeeCollectionDetails[i].feecode+"' value='"+result.FeeCollectionDetails[i].actualAmt+"'  style='text-align:right;' onkeyup='feeCalculation();' /></td>" +
						"</tr>");
			}
			$("#myDialog .myDailogTable #allstudent").append("<tr>" +
					"<td>"+(result.FeeCollectionDetails.length+1)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Advance</td>" +
					"<td ><input type='text' id='advanceAmount' class='advanceAmount' name='advanceAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[1]).toFixed(2)+"' style='text-align:right;' onkeyup='feeCalculation();' /></td>" +
					"</tr>");
			$("#myDialog .myDailogTable #allstudent").append("<tr>" +
					"<td>"+(result.FeeCollectionDetails.length+2)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Dues</td>" +
					"<td ><input type='text' id='dueAmount' class='Feeamount' name='dueAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[2]).toFixed(2)+"'  style='text-align:right;' onkeyup='feeCalculation();' /></td>" +
					"</tr>");
			$("#myDialog .myDailogTable #allstudent").append("<tr>" +
					"<td>"+(result.FeeCollectionDetails.length+3)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Fine</td>" +
					"<td ><input type='text' id='FineAmount' class='Feeamount' name='FineAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[0]).toFixed(2)+"'  style='text-align:right;' onkeyup='feeCalculation();' /></td>" +
					"</tr>");
			$("#myDialog .myDailogTable #allstudent").append("<tr>" +
					"<td>"+(result.FeeCollectionDetails.length+4)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Concession</td>" +
					"<td ><input type='text' id='concessionAmount' class='concessionAmount' name='concessionAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[3]).toFixed(2)+"' readonly='readonly' style='text-align:right;' /></td>" +
					"</tr>");
			$("#myDialog .myDailogTable #allstudent").append("<tr>" +
					"<th></th>" +
					"<th>"+termname+" Total</th>" +
					"<th ><input type='text' class='totalAmount' name='totalAmount' value=''  style='text-align:right;background-color:rgba(255, 224, 0, 0.22);' /></th>" +
					"" +
					"</tr>");
			
			
			
				$("#myDialog .myDailogTable #allstudent tbody").find(".Feeamount").each(function(){
					totalAmount=totalAmount+parseFloat($(this).val());
				});
				totalAmount=totalAmount-(parseFloat($(".advanceAmount").val())+parseFloat($(".concessionAmount").val()));
				$("#myDialog .myDailogTable #allstudent").append("<tr>" +
						"<th></th>" +
						"<th>Paying</th>" +
						"<th ><input type='text' class='payingAmount' name='payingAmount' value='"+totalAmount.toFixed(2)+"'  style='text-align:right;' /></th>" +
						"" +
						"</tr>");
			
				
				$("#myDialog .myDailogTable #allstudent tbody").find(".totalAmount").val(parseFloat(totalAmount).toFixed(2));
				
				
				$(".Feeamount").each(function(){
					$(this).val(parseFloat($(this).val()).toFixed(2));
				});
				
		}
		
	});
}
function feeCalculation(){

	totalAmount=0.0;
	$("#myDialog .myDailogTable #allstudent tbody").find(".Feeamount").each(function(){
	var amt=$(this).val();
		if(isNaN($(this).val())){
		amt=0.0;
		$(this).val(amt);
	}
		
		totalAmount=totalAmount+parseFloat(amt);
	});
	totalAmount=totalAmount-(parseFloat($(".advanceAmount").val())+parseFloat($(".concessionAmount").val()));
	$("#myDialog .myDailogTable #allstudent tbody").find(".payingAmount").val(parseFloat(totalAmount).toFixed(2));
	$("#myDialog .myDailogTable #allstudent tbody").find(".totalAmount").val(parseFloat(totalAmount).toFixed(2));

}

function feepaidDetails(pointer){

	var dataList={'term':pointer.closest("tr").find(".heading").attr("id"),
			'accYear':$("#haccYear").val(),
			'classId':$("#hclassId").val(),
			'specialization':$("#hspecialization").val(),
			'student':$("#hstudentid").val()
			};
	$.ajax({
		type:'post',
		url:'feecollection.html?method=feePaidDetails',
		data:dataList,

		success : function(
				response) {
			var result = $.parseJSON(response);
			var totalAmount=0.0;
			$("#myDialog .myDailogTable").append("<table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' width='100%' id='allstudent'>" +
					"<tr><th>Sl.No.</th>" +
					"<th style='text-align:left !important'>Fee Description</th>" +
					"<th>Fee Amount</th>"+
					"</tr>" +
					"</table>");
		
			for(var i=0;i<result.FeeCollectionDetails.length;i++){
				
				$("#myDialog .myDailogTable #allstudent").append("<tr id=rowid"+result.FeeCollectionDetails[i].sno+">" +
						"<td>"+result.FeeCollectionDetails[i].sno+"</td>" +
						"<td style='text-align:left !important;padding-left:5px;'>"+result.FeeCollectionDetails[i].feename+"</td>" +
						"<td ><input type='text' class='Feeamount' name='"+result.FeeCollectionDetails[i].feecode+"' value='"+result.FeeCollectionDetails[i].actualAmt+"' readonly='readonly' style='text-align:right;' /></td>" +
						"</tr>");
			}
			$("#myDialog .myDailogTable #allstudent").append("<tr>" +
					"<td>"+(result.FeeCollectionDetails.length+1)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Fine</td>" +
					"<td ><input type='text' id='FineAmount' class='Feeamount' name='FineAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[0]).toFixed(2)+"' readonly='readonly' style='text-align:right;' /></td>" +
					"</tr>");
			$("#myDialog .myDailogTable #allstudent").append("<tr>" +
					"<th></th>" +
					"<th>"+termname+" Total</th>" +
					"<th ><input type='text' class='totalAmount' name='totalAmount' value='' readonly='readonly' style='text-align:right;background-color:rgba(255, 224, 0, 0.22);' /></th>" +
					"" +
					"</tr>");
			
				$("#myDialog .myDailogTable #allstudent tbody").find(".Feeamount").each(function(){
					totalAmount=totalAmount+parseFloat($(this).val());
					
				});
				
				
				$("#myDialog .myDailogTable #allstudent").append("<tr>" +
						"<th></th>" +
						"<th>Paid Amount</th>" +
						"<th ><input type='text' class='paidAmount' name='paidAmount' value='"+parseFloat(pointer.attr("name")).toFixed(2)+"' readonly='readonly'  style='text-align:right;' /></th>" +
						"" +
						"</tr>");
				$("#myDialog .myDailogTable #allstudent tbody").find(".totalAmount").val(parseFloat(totalAmount).toFixed(2));
				$(".Feeamount").each(function(){
					$(this).val(parseFloat($(this).val()).toFixed(2));
				});
		}
		
	});

	
}
function feeCollectionDetailsPrint(pointer){
	var due=0.0;
	var adv=0.0;
	if(isNaN(parseFloat(pointer.closest('tr').prev('tr').find(".print").parent('td').attr('id')))){
		due=0.0;
	}
	else{
		due=parseFloat(pointer.closest('tr').prev('tr').find(".print").parent('td').attr('id').split(",")[2]).toFixed(2);
	}
	
	var dataList={'term':pointer.closest("tr").find(".heading").attr("id"),
			'accYear':$("#haccYear").val(),
			'classId':$("#hclassId").val(),
			'specialization':$("#hspecialization").val(),
			'student':$("#hstudentid").val()
			};
	$.ajax({
		type:'post',
		url:'feecollection.html?method=feePaidDetails',
		data:dataList,

		success : function(
				response) {
			var result = $.parseJSON(response);
			var totalAmount=0.0;
			$("#Dialog").append("<h3 style='text-align:center;'>"+$("th.schoolname").text()+"</h3>" +
					"<h3 style='text-align:center;'>Pattom Thiruvananthapuram-695 004.Kerala</h3>" +
					"<hr>" +
					"<div style='display:inline-block;width:210px;'><span>AdmnNo:</span><span>"+$("#haddmissionno").val()+"</span></div>"+""+"<div style='display:inline-block;width:210px; text-align:right'><span class='paiddate'>Paid Date:</span><span><b>"+pointer.attr('id')+"</b></span></div>" +
					"<br>" +
					"<div style='display:inline-block;width:210px;'><span>Name:</span>"+$("#hstuName").val()+"</span></div>"+""+"<div style='display:inline-block;width:210px; text-align:right'><span  class='receipt'>Receipt No:</span><span><b>"+pointer.attr('name').split(",")[0]+"</b></span></div>" +
					"<br>" +
					"<div style='display:inline-block;width:210px;'><span>Class & Div:</span>"+$("#hclassname").val() +"</div>"+"<div style='display:inline-block;width:210px; text-align:right'><span  class='receipt'>Payment Mode:</span><span><b>"+pointer.attr('name').split(",")[2]+"</b></span></div>" +
					"<br>" +
					
					"<div style='display:inline-block;width:210px;'><span class='ddetails'>DD No.:</span><span class='ddetails'>"+pointer.attr('name').split(",")[3] +"</span></div>"+"<div style='display:inline-block;width:210px; text-align:right'><span class='ddetails'>DD Date:</span><span class='ddetails'><b>"+pointer.attr('name').split(",")[4]+"</b></span></div>" +
					"<hr>" +
					"<h3 style='text-align:center;'>FEE RECEIPT</h3>" +
					"<div style='text-align:center;'>"+termname +"</div>"+
					"<hr>" +
					"");
		
			if((pointer.attr('name').split(",")[2]).toLowerCase()=='dd'){
				$('.ddetails').show();
			}
			else{
				$('.ddetails').hide();
			}
			$("#Dialog").append("<table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' style='width:100%' id='printtable'>" +
				
					"<th style='text-align:left !important'>Fee Description</th>" +
					"<th style='text-align:right;'>Fee Amount</th>"+
					"</tr>" +
					"</table>");
		
			for(var i=0;i<result.FeeCollectionDetails.length;i++){
				
				$("#Dialog #printtable").append("<tr id=rowid"+result.FeeCollectionDetails[i].sno+">" +
						
						"<td style='text-align:left !important;padding-left:5px;'>"+result.FeeCollectionDetails[i].feename+"</td>" +
						"<td style='text-align:right;'><input type='text' class='Feeamount' name='"+result.FeeCollectionDetails[i].feecode+"' value='"+parseFloat(result.FeeCollectionDetails[i].actualAmt).toFixed(2)+"' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
						"</tr>");
			}
			$("#Dialog #printtable").append("<tr>" +
					"<td style='text-align:left !important;padding-left:5px;'>Advance</td>" +
					"<td style='text-align:right;'><input type='text' id='advanceAmount' class='advanceAmount' name='advanceAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[1]).toFixed(2)+"' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
					"</tr>");
			
			$("#Dialog #printtable").append("<tr>" +
					"<td style='text-align:left !important;padding-left:5px;'>Fine</td>" +
					"<td style='text-align:right;'><input type='text' id='FineAmount' class='Feeamount' name='FineAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[0]).toFixed(2)+"' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
					"</tr>");
			
			
			
			$("#Dialog #printtable").append("<tr style='display:none;>" +
					"<td style='text-align:left !important;padding-left:5px;'>Previous Dues</td>" +
					"<td style='text-align:right;'><input type='text' id='dueAmount' class='Feeamount' name='dueAmount' value='"+due+"'  readonly='readonly' style='text-align:right;width:100px;' /></td>" +
				"</tr>");
			$("#Dialog #printtable").append("<tr style='display:none;'>" +
					
					"<td style='text-align:left !important;padding-left:5px;'>Concession</td>" +
					"<td style='text-align:right;'><input type='text' id='concessionAmount' class='concessionAmount' name='concessionAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[3]).toFixed(2)+"' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
					"</tr>");
			$("#Dialog #printtable tbody").find(".Feeamount").each(function(){
				totalAmount=totalAmount+parseFloat($(this).val());
			});
			totalAmount=totalAmount-parseFloat($("#Dialog #printtable #advanceAmount").val());
			$("#Dialog #printtable").append("<tr>" +
			
					"<th style='text-align:left;'> Total</th>" +
					"<th style='text-align:right;'><input type='text' class='totalAmount' name='totalAmount' value='"+parseFloat(totalAmount).toFixed(2)+"' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					"" +
					"</tr>");
			
			
			$("#Dialog #printtable").append("<tr>" +
					
					"<th style='text-align:left;'>Paid Amount</th>" +
					"<th style='text-align:right;'><input type='text' class='paidAmount' name='paidAmount' value='"+parseFloat(pointer.attr("name").split(",")[1]).toFixed(2)+"' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					"" +
					
					"</tr>");
				$("#Dialog #printtable").append("<tr>" +
					
					"<th><hr></th>" +
					 "<td><hr></td>"+
					
					
					"</tr>");
				$("#Dialog #printtable").append("<tr>" +
				
					"<th style='text-align:left;'>In Words</th>" +
					"<th style='text-align:right;'>"+inWords(pointer.attr("name").split(",")[1])+"</th>" +
					"" +
					"</tr>");
				$("#Dialog #printtable").append("<tr>" +
						
						"<th><hr></th>" +
						 "<td><hr></td>"+
						
						
						"</tr>");
				$("#Dialog #printtable tbody").find(".totalAmount").val(parseFloat(totalAmount).toFixed(2));
				$(".Feeamount").each(function(){
					$(this).val(parseFloat($(this).val()).toFixed(2));
				});
		}
		
	});
}

var a = ['','one ','two ','three ','four ', 'five ','six ','seven ','eight ','nine ','ten ','eleven ','twelve ','thirteen ','fourteen ','fifteen ','sixteen ','seventeen ','eighteen ','nineteen '];
var b = ['', '', 'twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety'];

function inWords(num){
	
    if ((num = Math.trunc(num).toString()).length > 9) return 'overflow';
    n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
    if (!n) return; var str = '';
    str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'crore ' : '';
    str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'lakh ' : '';
    str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'thousand ' : '';
    str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'hundred ' : '';
    str += (n[5] != 0) ? ((str != '') ? 'and' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + 'only' : '';
    //alert(str);
    return str;
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
