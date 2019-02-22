
function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
function myFunction() {
	    
			         document.getElementById("InventoryTransactionForm").submit();   
}

$(document)
		.ready(
				function() {
					var avail=false;
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
					
					
					
					
				
					
				$("#item_type").change(function(){
						
						var item_type = $('#item_type').val();
						alert(item_type)
						$.ajax({

							type : "POST",
							url : "adminMenu.html?method=getItemnameByItemtype",
							data : {"item_type":item_type},
							async : false,
							success : function(data) {
								
								var result = $.parseJSON(data);
								alert($.parseJSON(data));
								$("#item_name").empty();
								$("#item_name").append(
										'<option value="' + "" + '">' + ""
												+ '---select---</option>');
								for (var j = 0; j < result.status.length; j++) {
							
									
									$("#item_name").append(
											'<option value="'
													+ result.status[j].item_id
													+ '">'
													+ result.status[j].item_name
													+ '</option>');
									
								}
								
							}
						});
						});
					
					
					
						
						$('#issued_quantity').on('change',function (){

							var issued_quantity = $('#issued_quantity').val();
							var item_id = $('#item_id').val();
							var data = {
								"issued_quantity" : issued_quantity,
								"item_id" : item_id,
							};
							$.ajax({

								type : "POST",
								url : "adminMenu.html?method=getAvailableQuantity",
								data : data,
								async : false,
								success : function(response) {
									
									var result = $.parseJSON(response);
									
									if (result.status=="false") {
										
										avail=true
										$(".errormessagediv").show();
										  $(".errormessagediv").text("Enter valid Quantity");
										  $("#issued_quantity").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
										setTimeout(function() {$('#errorhover').fadeOut();}, 3000);
								   	  return false;
										
										
									} else {
										
										 avail = false;
										
										$(".errormessagediv").hide();
										$("#issued_quantity").css({'backgroundColor' : 'transparent','border':'1px solid #ccc'});
										
									}
								}
							});
							});
						
						
						
						
					
					$('#saveid').click(function() {
						
						var item_type = $("#item_type").val();
						var item_name = $("#item_name").val();
						var issued_quantity = $("#issued_quantity").val();
						var item_id = $("#item_id").val();
						var issued_to = $("#issued_to").val();
						var issued_date = $("#issued_date").val();
						var issued_by = $("#issued_by").val();
						var returned_by = $("#returned_by").val();
						var returned_date = $("#returned_date").val();
						var status = $("#status").val();
						var timeid = $("#timeid").val();
						/*var timeid = $("#issuedate").val();
						var timeid = $("#returndate").val();*/
						if (item_type == ""|| item_type == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Select the Item Type");

							document.getElementById("item_type").style.border = "1px solid #AF2C2C";
							document.getElementById("item_type").style.backgroundColor = "#FFF7F7";
							setTimeout(
									function() {
										$('.errormessagediv')
												.fadeOut();
									}, 3000);
							return false;

						}else if (item_name == ""|| item_name == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Item Name");

							document.getElementById("item_name").style.border = "1px solid #AF2C2C";
							document.getElementById("item_name").style.backgroundColor = "#FFF7F7";
							setTimeout(
									function() {
										$('.errormessagediv')
												.fadeOut();
									}, 3000);

							return false;
						}else if (issued_quantity == ""|| issued_quantity == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Item Quantity");

							document.getElementById("issued_quantity").style.border = "1px solid #AF2C2C";
							document.getElementById("issued_quantity").style.backgroundColor = "#FFF7F7";
							setTimeout(
									function() {
										$('.errormessagediv')
												.fadeOut();
									}, 3000);

							return false;
						}else if (item_id == ""|| item_id == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Item Id");

							document.getElementById("item_id").style.border = "1px solid #AF2C2C";
							document.getElementById("item_id").style.backgroundColor = "#FFF7F7";
							setTimeout(
									function() {
										$('.errormessagediv')
												.fadeOut();
									}, 3000);

							return false;
						}else if (issued_to == ""|| issued_to == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Issue to");

							document.getElementById("issued_to").style.border = "1px solid #AF2C2C";
							document.getElementById("issued_to").style.backgroundColor = "#FFF7F7";
							setTimeout(
									function() {
										$('.errormessagediv')
												.fadeOut();
									}, 3000);

							return false;
						}else if (issued_date == ""|| issued_date == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Issue date");

							document.getElementById("issued_date").style.border = "1px solid #AF2C2C";
							document.getElementById("issued_date").style.backgroundColor = "#FFF7F7";
							setTimeout(
									function() {
										$('.errormessagediv')
												.fadeOut();
									}, 3000);

							return false;
						}else if (issued_by == ""|| issued_by == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Issued by");

							document.getElementById("issued_by").style.border = "1px solid #AF2C2C";
							document.getElementById("issued_by").style.backgroundColor = "#FFF7F7";
							setTimeout(
									function() {
										$('.errormessagediv')
												.fadeOut();
									}, 3000);

							return false;
						}else if (returned_date == ""|| issued_by == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Return Date");

							document.getElementById("returned_date").style.border = "1px solid #AF2C2C";
							document.getElementById("returned_date").style.backgroundColor = "#FFF7F7";
							setTimeout(
									function() {
										$('.errormessagediv')
												.fadeOut();
									}, 3000);

							return false;
						}
							
						else {
							
						
						var editPage=window.location.href.substr(window.location.href.lastIndexOf("/") +1).split("=")[1];		
						
						if(editPage == "EditTransactionDetails&transactionItemId"){
							
						
							var transactionItemId=window.location.href.substr(window.location.href.lastIndexOf("/") +1).split("=")[2]
							
									alert("hello update");
							if(!avail){
						$("#addtransaction").attr("action","transactionymenu.html?method=CreateTransactionDetails&transactionItemId="+transactionItemId).submit();
						setTimeout(function(){
							window.location.href="adminMenu.html?method=TransactionList";
						},2000);
					}
							else{
								$(".errormessagediv").show();
								  $(".errormessagediv").text("Enter valid Quantity");
								  $("#issued_quantity").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
								setTimeout(function() {$('#errorhover').fadeOut();}, 3000);
							}
				}
						
					
						else if(editPage == "returnInventoryItem&transactionItemId"){
							
							if (returned_by == ""|| returned_by == null) {
								$(".errormessagediv").show();
								$(".validateTips").text("Enter Return by");

								document.getElementById("returned_by").style.border = "1px solid #AF2C2C";
								document.getElementById("returned_by").style.backgroundColor = "#FFF7F7";
								setTimeout(
										function() {
											$('.errormessagediv')
													.fadeOut();
										}, 3000);

								return false;
							}
							else if (status == ""|| status == null) {
								$(".errormessagediv").show();
								$(".validateTips").text("Enter Status remarks");

								document.getElementById("status").style.border = "1px solid #AF2C2C";
								document.getElementById("status").style.backgroundColor = "#FFF7F7";
								setTimeout(
										function() {
											$('.errormessagediv')
													.fadeOut();
										}, 3000);

								return false;
							}else{
							
							
							
							var transactionItemId=window.location.href.substr(window.location.href.lastIndexOf("/") +1).split("=")[2]
						alert("hello  return update");
							
						$("#addtransaction").attr("action","transactionymenu.html?method=updateReturnItem&transactionItemId="+transactionItemId).submit();
						setTimeout(function(){
							window.location.href="adminMenu.html?method=TransactionList";
						},2000);
						}
						}
						 else{
								if(!avail){
							 $("#addtransaction").submit(); 
							 setTimeout(function(){
									window.location.href="adminMenu.html?method=TransactionList";
								},2000);
						 }
								else{
									$(".errormessagediv").show();
									  $(".errormessagediv").text("Enter valid Quantity");
									  $("#issued_quantity").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
									setTimeout(function() {$('#errorhover').fadeOut();}, 3000);
								}
						}
						
						}
					});
					
				
				
					$("#edit").click(function(){
						
						
						 var transactionItem = $("#allstudent tbody tr td").find($('input[name="check_box"]:checked')).prop("id");
						 
						 if (transactionItem != undefined){

							 var splitval = transactionItem.split(",");
							

							 var transactionItemId=splitval[0];
							 var status=splitval[1];
							
							if(status !="returned"){
								window.location.href = "adminMenu.html?method=EditTransactionDetails&transactionItemId="
									+ transactionItemId;
							}else {
										$(".errormessagediv").show();
										$(".validateTips")
										.text(
												"Already returend");
							}	

							
						 }else{
								$(".errormessagediv").show();
								$(".validateTips")
										.text(
												"Select Any Item!");
						}
						
							
						
				 });
							
					
					// for return//
					
					$("#returnitem").click(function(){
						
						
						 var transactionItem = $("#allstudent tbody tr td").find($('input[name="check_box"]:checked')).prop("id");
						 
						 
						 if (transactionItem != undefined){

							 var splitval = transactionItem.split(",");
								
							 var transactionItemId=splitval[0];
							
							 var status=splitval[1];
							
							if(status != "returned"){
								window.location.href = "inventorymenu.html?method=returnInventoryItem&transactionItemId="
									+ transactionItemId;
								
							}else{
								$(".errormessagediv").show();
								$(".validateTips")
										.text(
												"Already returend");
								}
				}else{
					
					$(".errormessagediv").show();
					$(".validateTips")
							.text(
									"Select Any Item!");
						 
						 
					 }
					 
							
});	
					
					
					
					$("#delete").click(function(){
						 var transactionItem = $("#allstudent tbody tr td").find($('input[name="check_box"]:checked')).prop("id");
												 
						 if (transactionItem !==undefined){
							 
							 var splitval = transactionItem.split(",");
							
							 var transactionItemId=splitval[0];
							 var returnStatus=splitval[1];
							if(returnStatus=="returned"){
								$.ajax({
									 
									 type: 'POST', 
									 url: "transactionymenu.html?method=deleteInventoryTransaction",
									 data:{'transactionItemId':transactionItemId },
									 async:false,
									 
									 success: function(response) {
										 var result = $.parseJSON(response);
										 if(result.status == "true"){
											 $(".successmessagediv").show();
											 $(".message-item").text(transactionItemId+"Transaction Deleted from transaction Successfully")
											 setTimeout(function(){
											 window.location.href ="adminMenu.html?method=TransactionList"; },
											 3000);
											 $(".successmessagediv").delay(3000).slideUp("slow");
										 }
										 setTimeout(function(){
										 window.location.href ="adminMenu.html?method=TransactionList"; },
				      	 			     3000);
										 $(".successmessagediv").delay(3000).slideUp("slow"); 
										}
									 
								 });
								
								
							}else{
								$(".errormessagediv").show();
								$(".validateTips")
										.text(
												"Not Deleted! Item is not returned.");
									 
									 
								 }
								
							}
						
						 else{
							
							 $(".errormessagediv").show();
								$(".validateTips")
										.text(
												"Select Any One Item!");
							}
						 
					 });		
					
					
					
		
								
					$('#excelDownload')
					.click(
							function() {
								
								
								var searchTerm = $("#searchexamid").val().trim();
								
							
								
								window.location.href = 'streamDetails.html?method=downloadStreamDetailsXLS&searchTerm='+ searchTerm;
								
							});
					
					
					$("#pdfDownload").click(function(){
						
						
						var searchTerm = $("#searchexamid").val().trim();
            			
            			window.location.href = 'streamDetails.html?method=downloadStreamDetailsPDF&searchTerm='+ searchTerm;
            				
            		});
					$("#issued_date").datepicker({
						dateFormat : "dd-mm-yy",
						maxDate : 0,
						changeMonth : "true",
						changeYear : "true",
						buttonImage : "images/calendar.GIF",
						buttonImageOnly : true

					});

					$("#returned_date").datepicker({
						dateFormat : "dd-mm-yy",
						maxDate : 0,
						changeMonth : "true",
						changeYear : "true",
						buttonImage : "images/calendar.GIF",
						buttonImageOnly : true
					});
					
					$('#datetimepicker3').datetimepicker({
						pickDate : false
					});
					
					$('#datetimepicker4').datetimepicker({
						pickDate : false
					});
				
					    	//$(".timpicker input").timepicker();
						
					function selectAll(){
						$("input[name='check_box']").prop("checked","checked");
					}
			var editPage=window.location.href.substr(window.location.href.lastIndexOf("/") +1).split("=")[1];		
					
					if(editPage == "EditTransactionDetails&transactionItemId"){
						$("#returned_by").parent('div').parent('div').hide();
						$("#status").parent('div').parent('div').hide();
					
						var transactionItemId=window.location.href.substr(window.location.href.lastIndexOf("/") +1).split("=")[2]
						 $.ajax({
							 
							 type: 'POST', 
							 url: "adminMenu.html?method=singleItemDetails",
							 data:{'transactionItemId':transactionItemId },
					 
							 success: function(data) {
								 var result = $.parseJSON(data);
								
								
								 $("#item_type").val(result.status[0].item_type);
								
								 $("#item_id").val(result.status[0].purchase_item_id);
								 $("#issued_quantity").val(result.status[0].issued_quantity);
								 $("input[name='requested_by']").val(result.status[0].requested_by).attr("checked",true);
								 $("#issued_to").val(result.status[0].issued_to);
								 $("#issued_date").val(result.status[0].issued_date);
								 $("#issued_by").val(result.status[0].issued_by);
								 $("#returned_by").val(result.status[0].returned_by);
								 $("#returned_date").val(result.status[0].returned_date);
								 //$("#status").val(result.status[0].status);
								 if($("#item_type").val !=undefined || $("#item_type").val !=null || $("#item_type").val !="" ){
										
										var item_type = $('#item_type').val();
										
										$.ajax({

											type : "POST",
											url : "adminMenu.html?method=getItemnameByItemtype",
											data : {"item_type":item_type},
											async : false,
											success : function(data) {
												
												var result = $.parseJSON(data);
												
												$("#item_name").empty();
												$("#item_name").append(
														'<option value="' + "" + '">' + ""
																+ '---select---</option>');
												for (var j = 0; j < result.status.length; j++) {
											
													
													$("#item_name").append(
															'<option value="'
																	+ result.status[j].item_id
																	+ '">'
																	+ result.status[j].item_name
																	+ '</option>');
													
												}
												
											}
										});
									}	
								 $("#item_name").val(result.status[0].purchase_item_id);
								 }
						 });
						
					}
					
					
					var editPage1=window.location.href.substr(window.location.href.lastIndexOf("/") +1).split("=")[1];		
					
				 
					if(editPage1 == "returnInventoryItem&transactionItemId"){
						
						var transactionItemId=window.location.href.substr(window.location.href.lastIndexOf("/") +1).split("=")[2]
						 $.ajax({
							 
							 type: 'POST', 
							 url: "adminMenu.html?method=singleItemDetails",
							 data:{'transactionItemId':transactionItemId },
					 
							 success: function(data) {
								 var result = $.parseJSON(data);
								 $("#item_id").val(result.status[0].item_id);
								 $("#item_type").val(result.status[0].item_type);
								 $("#item_name").val(result.status[0].purchase_item_name);
								 $("#item_id").val(result.status[0].purchase_item_id);
								 $("#issued_quantity").val(result.status[0].issued_quantity);
								$("input[name='requested_by']").val(result.status[0].requested_by).attr("checked",true);
								$("#issued_to").val(result.status[0].issued_to);
								$("#issued_date").val(result.status[0].issued_date);
								$("#issued_by").val(result.status[0].issued_by);
								$("#returned_by").val(result.status[0].returned_by);
								$("#returned_date").val(result.status[0].returned_date);
								//$("#status").val(result.status[0].status);
								
								
								$("#issued_quantity").attr('readonly', true);
								$("#issued_to").attr('readonly', true);
								$("#issued_by").attr('readonly', true);
								$("#issuetime").attr('readonly', true);
								$("#returntime").attr('readonly', true);
								
								if($("#item_type").val !=undefined || $("#item_type").val !=null || $("#item_type").val !="" ){
									
									var item_type = $('#item_type').val();
									
									$.ajax({

										type : "POST",
										url : "adminMenu.html?method=getItemnameByItemtype",
										data : {"item_type":item_type},
										async : false,
										success : function(data) {
											
											var result = $.parseJSON(data);
											
											$("#item_name").empty();
											$("#item_name").append(
													'<option value="' + "" + '">' + ""
															+ '---select---</option>');
											for (var j = 0; j < result.status.length; j++) {
										
												
												$("#item_name").append(
														'<option value="'
																+ result.status[j].item_id
																+ '">'
																+ result.status[j].item_name
																+ '</option>');
												
											}
											
										}
									});
								}	
							 $("#item_name").val(result.status[0].purchase_item_id);
							 }
								
								
						 });
						/*$("#overlay").css({
							'display':'block',
							'height': '220px',
							'width' :'100%',
							'position' : 'absolute',
							'z-index':'9999999',
							'top':'128px'
						});*/
					}
					
					
					if(editPage == "AddTransactionDetails"){
						$("#returned_by").parent('div').parent('div').hide();
						$("#status").parent('div').parent('div').hide();
						
					}
						
					
					
});				