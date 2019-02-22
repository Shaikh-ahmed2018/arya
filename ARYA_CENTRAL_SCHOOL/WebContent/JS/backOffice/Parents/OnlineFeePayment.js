$(document).ready(function(){
	$(".selectAll").change(function(){
		$(this).closest("table").find(".select").prop("checked",$(this).prop("checked"));
	});
	$(".select").change(function(){
		
		
		if($(this).closest("tr").prev("tr").find(".select").length>0){
			if($(this).closest("tr").prev("tr").find(".select").prop("checked")==true){
				$(this).prop("checked",$(this).prop("checked"));
			}
			else{
				if($(this).closest("tr").next("tr").find(".select").prop("checked")==false){
					$(this).prop("checked",$(this).prop("checked"));
				}
				else if($(this).closest("tr").next("tr").find(".select").prop("checked")==true){
					$(this).prop("checked",true);
				}
				else{
					$(this).prop("checked",false);	
				}
			
			}
		}
		if($(this).closest("tr").prev("tr").find(".select").length==0){
			if($(this).prop("checked")==false){
				$(this).closest("table").find(".select").prop("checked",false);
			}
		}
		
		if($(this).closest("table").find(".select").length==$(this).closest("table").find(".select:checked").length){
		
			$(this).closest("table").find(".selectAll").prop("checked",true);
		}
		else{
			$(this).closest("table").find(".selectAll").prop("checked",false);
		}
		
		
		
		
		
	});
		$(".tselectAll").change(function(){
			$(this).closest("table").find(".tselect").prop("checked",$(this).prop("checked"));
		});
		$(".tselect").change(function(){
			if($(this).closest("tr").prev("tr").find(".tselect").length>0){
				if($(this).closest("tr").prev("tr").find(".tselect").prop("checked")==true){
					$(this).prop("checked",$(this).prop("checked"));
				}
				else{
					if($(this).closest("tr").prev("tr").find(".tselect").prop("checked")==false){
						$(this).prop("checked",$(this).prop("checked"));
					}
					else if($(this).closest("tr").prev("tr").find(".tselect").prop("checked")==true){
						$(this).prop("checked",true);
					}
					else{
						$(this).prop("checked",false);	
					}
				}
			}
			if($(this).closest("table").find(".tselect").length==$(this).closest("table").find(".tselect:checked").length){
				$(this).closest("table").find(".tselectAll").prop("checked",true);
			}
			else{
				$(this).closest("table").find(".tselectAll").prop("checked",false);
			}
	});
		
	
	$(".payment").click(function(){
		var pointer=$(this);
		
	if($(this).closest(".stu").find(".tselect:checked").length==0 && $(this).closest(".stu").find(".select:checked").length==0)
		{
		alert("Select Fee to Pay");
		}
	else{

		grandtotal=0.0;
		$("#myDialog .paymentMode").show();
		$("#myDialog .myDailogTable").empty();
		studentId=$(this).closest("div").find(".stuId").val();
		feeCode=[];
		termcode=[];
		fineAmt=[];
		dueAmt=$(this).closest("div").find(".dueAmt").val();
		pointer.closest(".stu").find(".select:checked").each(function(){
			feeCollectionDetails($(this));
			grandtotal=grandtotal+Number($(this).val().split(",")[5]);
			feeCode.push($(this).val().split(",")[6]);
			termcode.push($(this).val().split(",")[0]);
			fineAmt.push($(this).val().split(",")[4]);
		});
		pointer.closest(".stu").find(".tselect:checked").each(function(){
			transportFeeCollectionPopUp($(this));
			grandtotal=grandtotal+Number($(this).val().split(",")[5]);
			feeCode.push($(this).val().split(",")[6]);
			termcode.push($(this).val().split(",")[0]);
			fineAmt.push($(this).val().split(",")[4])
		});
		$(".grandTotal").text(grandtotal);
		$("#myDialog").dialog('open');
		
		$(".ui-dialog-titlebar").text($(this).closest("tr").find(".heading").text()+" Fee Collection");
		$(".ui-dialog-buttonset button:nth-child(1)").show();
		
	
	}
	
	
	});
	
	$("#myDialog").dialog({
	    autoOpen  : false,
	    modal     : true,
	    buttons   : {
	              'Pay' : function() {
	            	  var flag=true;
	            	 if($("#paymentMode").val() !="" && $("#paymentMode").val() !=undefined){
	            		 $.ajax({
	            			 type:'POST',
	            			 url:'parentMenu.html?method=onlinefeetransactionId',
	            			 data:{
	            				 "studentId":studentId,
	            				 "feeCode":feeCode.toString(),
	            				 "termcode":termcode.toString(),
	            				 "bank":$("#paymentMode option:selected").text(),
	            				 "bankcode":$("#paymentMode").val(),
	            				 "grandtotal":grandtotal,
	            				 "fineAmt":fineAmt.toString(),
	            			 },
	            			 async:false,
	            			 
	            			 success : function(hresponse){
	            				 var result=$.parseJSON(hresponse);
	            				 if(result.status=="KVB"){
	            					 $("<form action='"+result.redirecturl+"' method='POST'>"+
   	            							"<input type='hidden' name='fldClientCode' value='"+result.fldClientCode+"' />"+
   	            							"<input type='hidden' name='fldMerchCode' value='"+result.fldMerchCode+"' />"+
   	            							"<input type='hidden' name='fldTxnCurr' value='"+result.fldTxnCurr+"' />"+
   	            							"<input type='hidden' name='fldTxnAmt' value='"+result.fldTxnAmt+"' />"+
   	            							"<input type='hidden' name='fldTxnScAmt' value='"+result.fldTxnScAmt+"' />"+
   	            							"<input type='hidden' name='fldMerchRefNbr' value='"+result.fldMerchRefNbr+"' />"+
   	            							"<input type='hidden' name='fldSucStatFlg' value='"+result.fldSucStatFlg+"' />"+
   	            							"<input type='hidden' name='fldFailStatFlg' value='"+result.fldFailStatFlg+"' />"+
   	            							"<input type='hidden' name='fldDatTimeTxn' value='"+result.fldDatTimeTxn+"' />"+
   	            							
   	            							"<input type='hidden' name='fldOrgDatTimeTxn' value='"+result.fldOrgDatTimeTxn+"' />"+
   	            							"<input type='hidden' name='fldClientAccount' value='"+result.fldClientAccount+"' />"+
   	            							"<input type='hidden' name='fldTxnId' value='"+result.fldTxnId+"' />"+
   	            							 " </form>").appendTo('body').submit();
	            				 }
	            				 
	            				 if(result.status=="FDB"){
	            					 $("<form action='"+result.redirecturl+"' method='POST'>"+
   	            							"<input type='hidden' name='user_code' value='"+result.user_code+"' />"+
   	            							"<input type='hidden' name='pass_code' value='"+result.pass_code+"' />"+
   	            							"<input type='hidden' name='amount' value='"+result.amount+"' />"+
   	            							"<input type='hidden' name='tran_id' value='"+result.tran_id+"' />"+
   	            							"<input type='hidden' name='charge_code' value='"+result.charge_code+"' />"+
   	            							"<input type='hidden' name='hash_value' value='"+result.hash_value+"' />"+
   	            							 " </form>").appendTo('body').submit();
	            				 }
	            				 else{
	            						var jsonObj=$.parseJSON(result.requestjson);
	   	            				if(result.status !="false"){
	   	            					
	   	            					 $("<form action='"+result.redirecturl+"' method='POST'>"+
	   	            							 "<input type='hidden' name='reqjson' value='"+result.requestjson+"' />"+
	   	            							 " </form>").appendTo('body').submit();
	   	            					
	   	            				 }
	            				 }
	            				
	            				 
	            			 }
	            		 });
	            		 
	            	 }
	            	 else{
	            		 $("#paymentMode").focus();
	            		 $("#paymentMode").css({
	            			 "border-color":"#f00"
	            		 });
	            		 $('.errormessagediv').show();
	            		 $('.errormessagediv').delay(2000).fadeOut();
							$('.validateTips').text("Select Bank Name."); 
	            	 }
	              },
	              'Close' : function() {
	            	  $("#myDialog .myDailogTable").empty();
	           
	                  $(this).dialog('close');
	              }
	                }
	});
	var flagclassfee=true;
	var pointerclassfee="";
	
	var flagtransportfee=true;
	var pointertransportfee="";
	$(".classfeetable").each(function(){
		if($(this).find("tbody tr").length==0){
			$(this).closest("div").remove();
			flagclassfee=false;
			pointerclassfee=$(this);
		}
		
	});
	$(".transortfeetable").each(function(){
		if($(this).find("tbody tr").length==0){
			$(this).closest("div").remove();
			flagtransportfee=false;
			pointertransportfee=$(this);
		}
	});
	$(".stu").each(function(){
		if($(this).find("table").length==0){
			$(this).find(".payment").remove();
		}
	});
	
	
	$("#printreciept").click(function(){

		
		
		var a=$("#printing-css").val();
		var b = document.getElementById("onlinefeeReceipt").innerHTML;

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
	});
});

function feeCollectionDetails(pointer){
	var dataList={'term':pointer.val().split(",")[0],
			'accYear':$("#hacademicyaer").val(),
			'classId':pointer.val().split(",")[1],
			'specialization':pointer.val().split(",")[2]
			};
	$.ajax({
		type:'post',
		url:'feecollection.html?method=feeCollectionDetails',
		data:dataList,

		success : function(
				response) {
			var result = $.parseJSON(response);
			var totalAmount=0.0;
			$("#myDialog .myDailogTable").append("<table class='allstudent'  width='100%' id='"+pointer.val().split(",")[0]+"'>" +
					"<tr><th>Sl.No.</th>" +
					"<th style='text-align:left !important'>"+pointer.closest("tr").find("td:nth-child(2)").text()+" Fee Description</th>" +
					"<th>Fee Amount</th>"+
					"</tr>" +
					"</table>");
		
			for(var i=0;i<result.FeeCollectionDetails.length;i++){
				
				$("#myDialog .myDailogTable #"+pointer.val().split(",")[0]+"").append("<tr id=rowid"+result.FeeCollectionDetails[i].sno+">" +
						"<td>"+result.FeeCollectionDetails[i].sno+"</td>" +
						"<td style='text-align:left !important;padding-left:5px;'>"+result.FeeCollectionDetails[i].feename+"</td>" +
						"<td style='text-align:right;'>"+result.FeeCollectionDetails[i].actualAmt+"</td>" +
						"</tr>");
			}
			
			$("#myDialog .myDailogTable #"+pointer.val().split(",")[0]+"").append("<tr>" +
					"<td>"+(result.FeeCollectionDetails.length+1)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Fine</td>" +
					"<td style='text-align:right;'>"+pointer.val().split(",")[4]+"</td>" +
					"</tr>");
			
			$("#myDialog .myDailogTable #"+pointer.val().split(",")[0]+"").append("<tr>" +
					"<th></th>" +
					"<th>"+pointer.closest("tr").find("td:nth-child(2)").text()+" Total</th>" +
					"<th style='text-align:right;'>"+pointer.val().split(",")[5]+"</th>" +
					"" +
					"</tr>");
			
				
		}
		
	});
}

function transportFeeCollectionPopUp(pointer){
	
			var totalAmount=0.0;
			$("#myDialog .myDailogTable").append("<table class='allstudent'  width='100%' id='"+pointer.val().split(",")[0]+"'>" +
					"<tr><th>Sl.No.</th>" +
					"<th style='text-align:left !important'>"+pointer.closest("tr").find("td:nth-child(2)").text()+" Fee Description</th>" +
					"<th>Fee Amount</th>"+
					"</tr>" +
					"</table>");
			$("#myDialog .myDailogTable #"+pointer.val().split(",")[0]+"").append("<tr>" +
					"<td>1</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>"+pointer.closest("tr").find("td:nth-child(2)").text()+" Fee</td>" +
					"<td style='text-align:right;'>"+pointer.val().split(",")[3]+"</td>" +
					"</tr>");
			
			$("#myDialog .myDailogTable #"+pointer.val().split(",")[0]+"").append("<tr>" +
					"<td>2</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Fine</td>" +
					"<td style='text-align:right;'>"+pointer.val().split(",")[4]+"</td>" +
					"</tr>");
			
			$("#myDialog .myDailogTable #"+pointer.val().split(",")[0]+"").append("<tr>" +
					"<th></th>" +
					"<th>"+pointer.closest("tr").find("td:nth-child(2)").text()+" Total</th>" +
					"<th style='text-align:right;'>"+pointer.val().split(",")[5]+"</th>" +
					"" +
					"</tr>");
			
				
		
		
		
		
		
		
		





}