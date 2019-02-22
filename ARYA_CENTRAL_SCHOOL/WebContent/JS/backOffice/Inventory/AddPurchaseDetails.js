
$(document)
		.ready(
				function() {
					
					
// maping item type//
					
					$("#department").change(function(){
						var department = $('#department').val();

						$.ajax({

							type : "POST",
							url : "adminMenu.html?method=getItemtypeByDepartmnet",
							data : {"department":department},
							async : false,
							success : function(data) {
								
								var result = $.parseJSON(data);
								$("#item_type").empty();
								$("#item_type").append(
										'<option value="' + "" + '">' + ""
												+ '---select---</option>');
								for (var j = 0; j < result.status.length; j++) {
									
									$("#item_type").append(
											'<option value="'
													+ result.status[j].item_type_id
													+ '">'
													+ result.status[j].item_type_name
													+ '</option>');
									
								}
								
							}
						});
						});
					
					
					if($("#hdepartment").val!=null || $("#hdepartment").val!=undefined){
						var department = $('#hdepartment').val();

						$.ajax({

							type : "POST",
							url : "adminMenu.html?method=getItemtypeByDepartmnet",
							data : {"department":department},
							async : false,
							success : function(data) {
								
								var result = $.parseJSON(data);
								$("#item_type").empty();
								$("#item_type").append(
										'<option value="' + "" + '">' + ""
												+ '---select---</option>');
								for (var j = 0; j < result.status.length; j++) {
									
									$("#item_type").append(
											'<option value="'
													+ result.status[j].item_type_id
													+ '">'
													+ result.status[j].item_type_name
													+ '</option>');
									
								}
							
							}
						});
					}
					
					setTimeout("removeMessage()", 2000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 2000);
					
					
					$("#search")
					.click(
							function()

							{
								var searchname = $('#searchterm').val();

								window.location.href = "adminMenu.html?method=AddorModifyorDeleteList&searchname="
										+ searchname;

							});
					
					$('#editpurchasedetails')
					.click(
							function()

							{
								var cnt = 0;

								$('input.list_Checkbox_Class:checkbox:checked')
										.map(
												function() {

													var term_id = $(
															this).attr(
															"id");

													var split_id = term_id
															.split('_');
													getData = split_id[4]
															.split(',');

													cnt++;
												});

								if (cnt == 0 || cnt > 1) {

									$(".errormessagediv").show();
									$(".validateTips")
											.text(
													"Select Any One Purchase Type");

								}

								else {

									var name = getData[0];
									

									window.location.href = "inventorymenu.html?method=EditAddorModifyorDelete&name="
											+ name;

								}

							});
					//Delete Purchase Details
					
					$('#delete')
					.click(
							function()

							{
								var cnt = 0;
								var getDataArray=[]; 
								$('input.list_Checkbox_Class:checkbox:checked')
										.map(
												function() {

													var term_id = $(
															this).attr(
															"id");

													var split_id = term_id
															.split('_');
													getData = split_id[4]
															.split(',');
													getDataArray.push(getData[0]);

													cnt++;
												});

								var datalist = {'getDataArray':getDataArray.toString()};//create json data3
						
									
								$
								.ajax({
									type : "GET",
									url : "inventorymenu.html?method=DeleteAddorModifyorDelete",
									data : datalist,
									async : false,

									success : function(
											response) {

										var result = $
												.parseJSON(response);

										if (result.jsonResponse == "Term Details Deleted Successfully") {

											window.location.href = "inventorymenu.html?method=termList&result="
													+ result.jsonResponse;

											window.location.href = "inventorymenu.html?method=DeleteAddorModifyorDelete&name="
												+ name;
											
										}
										if (result.jsonResponse == "Inventory Type Deleted Successfully") {
											$(
													".errormessagediv")
													.show();
											$(
													".validateTips")
													.text(
															result.jsonResponse);
										}

										else if (result.jsonResponse == "Inventory Type Not Deleted Successfully") {

											$(
													".errormessagediv")
													.show();
											$(
													".validateTips")
																	.text(
																			result.jsonResponse);

														}

														setTimeout(function() {

															location.reload();

														}, 3000);

													}
												});

									});
					

								

								

							});
					
					
					
					var hdepartment = $("#hdepartment").val();
					if (hdepartment != undefined) {

						$("#department option[value=" + hdepartment + "]").attr('selected', 'true');

					}
					
					var hitem_type = $("#hitem_type").val();
					if (hitem_type != undefined) {

						$("#item_type option[value=" + hitem_type + "]").attr('selected', 'true');

					}
					
					
					
					
					
					$('#saveid')
							.click(
									function() {

										var department = $("#department").val();
										var item_name = $("#item_name").val();
										var total_quantity = $("#total_quantity").val();
										var manufacturer = $("#manufacturer").val();
										var address = $("#address").val();
										var item_type = $("#item_type").val();
										var item_id = $("#item_id").val();
										var purchased_date = $("#purchased_date").val();
										var purchased_by = $("#purchased_by").val();
										var contact_number = $("#contact_number").val();

										if (department == ""|| department == null) {
											$(".errormessagediv").show();
											$(".validateTips").text("Select the Department");

											document.getElementById("department").style.border = "1px solid #AF2C2C";
											document.getElementById("department").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);

											return false;
										}else if (item_type == ""|| item_type == null){
											$(".errormessagediv").show();
											$(".validateTips").text("Select Item Type");

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
										}else if (item_id == ""|| item_id == null) {
											$(".errormessagediv").show();
											$(".validateTips").text("Enter Item Id");

											document.getElementById("item_id").style.border = "1px solid #AF2C2C";
											document.getElementById("item_id").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {	$('.errormessagediv').fadeOut();}, 3000);

											return false;
										} else if (total_quantity == ""|| total_quantity == null) {
											$(".errormessagediv").show();
											$(".validateTips").text(
													"Enter Total Quantity");
											document.getElementById("total_quantity").style.border = "1px solid #AF2C2C";
											document.getElementById("total_quantity").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);

											return false;
										} else if (purchased_date == ""|| purchased_date == null) {
											$(".errormessagediv").show();
											$(".validateTips").text("Enter Purchase Date");

											document.getElementById("purchased_date").style.border = "1px solid #AF2C2C";
											document.getElementById("purchased_date").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);
											return false;
										} else if (manufacturer == ""|| manufacturer == null) {
											$(".errormessagediv").show();
											$(".validateTips").text("Enter Manufacturer Name");
											document.getElementById("manufacturer").style.border = "1px solid #AF2C2C";
											document.getElementById("manufacturer").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);
											return false;
										} else if (purchased_by == ""|| purchased_by == null) {
											$(".errormessagediv").show();
											$(".validateTips").text("Enter Purchased By");
											document.getElementById("purchased_by").style.border = "1px solid #AF2C2C";
											document.getElementById("purchased_by").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);
											return false;
										} else if (address == ""|| address == null) {
											$(".errormessagediv").show();
											$(".validateTips").text("Enter The Address");
											document.getElementById("address").style.border = "1px solid #AF2C2C";
											document.getElementById("address").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);
											return false;
										} else if (contact_number == ""|| contact_number == null) {
											$(".errormessagediv").show();
											$(".validateTips").text("Enter Contact Number");
											document.getElementById("contact_number").style.border = "1px solid #AF2C2C";
											document.getElementById("contact_number").style.backgroundColor = "#FFF7F7";
											setTimeout(
													function() {
														$('.errormessagediv')
																.fadeOut();
													}, 3000);
											return false;
										}

										else {
											
											
											

											document.getElementById("myForm").submit();

											
											setTimeout(function(){
																									
																	window.location.href = "adminMenu.html?method=AddorModifyorDeleteList";
																							 
																	},3000);
											
											
											
											
											
										}

									});
					
					
					
					
					
					
					
					
					
					
					
					
					$("#excelDownload")
					.click(
							function()

							{
								var searchname = $('#searchterm').val();

								window.location.href = "inventorymenu.html?method=AddorModifyorDeleteXLS&searchname="
												+ searchname;

							});
			
			
			
			
			$("#pdfDownload")
			.click(
					function()

					{
						
						var searchname = $('#searchterm').val();

						window.location.href = "inventorymenu.html?method=AddorModifyorDeletePDF&searchname="
												+ searchname;

					});
				
			$("#purchased_date").datepicker(
					{

						dateFormat : "dd-mm-yy",
						maxDate : 0,
						yearRange : "1997",
						changeMonth : true,
						changeYear : true,
						dateFormat : 'dd-mm-yy',
						numberOfMonths : 1,

					/*
						dateFormat : "dd-mm-yy",
						yearRange : 'c-65:c+65',
						maxDate : -1,
						changeMonth : "true",
						changeYear : "true",
						yearRange : '1997:' + (new Date).getFullYear(),
						onClose : function(selectedDate) {
							if ((selectedDate != "")
									&& (selectedDate != undefined)) {
								var date2 = $('#purchased_date')
										.datepicker('getDate');
								date2.setDate(date2.getDate() + 1);
								$("#purchased_date").datepicker("option",
										"minDate", date2);
							}
						}
					*/});
				

			

function removeMessage() {

	$(".successmessagediv").hide();

}
function HideError() {
	
	document.getElementById("department").style.border = "1px solid #ccc";
	document.getElementById("department").style.backgroundColor = "#fff";
	document.getElementById("item_name").style.border = "1px solid #ccc";
	document.getElementById("item_name").style.backgroundColor = "#fff";
	document.getElementById("total_quantity").style.border = "1px solid #ccc";
	document.getElementById("total_quantity").style.backgroundColor = "#fff";
	document.getElementById("manufacturer").style.border = "1px solid #ccc";
	document.getElementById("manufacturer").style.backgroundColor = "#fff";
	document.getElementById("address").style.border = "1px solid #ccc";
	document.getElementById("address").style.backgroundColor = "#fff";
	document.getElementById("item_type").style.border = "1px solid #ccc";
	document.getElementById("item_type").style.backgroundColor = "#fff";
	document.getElementById("item_id").style.border = "1px solid #ccc";
	document.getElementById("item_id").style.backgroundColor = "#fff";
	document.getElementById("purchased_date").style.border = "1px solid #ccc";
	document.getElementById("purchased_date").style.backgroundColor = "#fff";
	document.getElementById("purchased_by").style.border = "1px solid #ccc";
	document.getElementById("purchased_by").style.backgroundColor = "#fff";
	document.getElementById("contact_number").style.border = "1px solid #ccc";
	document.getElementById("contact_number").style.backgroundColor = "#fff";

}