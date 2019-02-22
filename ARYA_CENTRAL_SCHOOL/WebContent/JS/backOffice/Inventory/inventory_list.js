$(document).ready(	function()
	
				{

					$("#search")
							.click(
									function()

									{
										var searchname = $('#searchvalue').val();

										window.location.href = "adminMenu.html?method=InventoryTypeList&searchname="
												+ searchname;

									});
					
					
				
					
					
					$("#selectall").change(function(){
						$(".InventoryTypesList_Checkbox_Class").prop("checked",$(this).prop("checked"));
					});
					
					
					
					
					$("#saveinventory")
					.click(
							function() {
								
								var inventory_type_name=$("#inventory_type_name").val();
								var department=$("#department").val();
								var description=$("#description").val();
								
								
								if(inventory_type_name==null||inventory_type_name=="")
									{

										$(".errormessagediv").show();

										$(".validateTips").text("Enter Inventory Name");
										$("#studentFirstNameId").focus();
										document.getElementById("inventory_type_name").style.border = "1px solid #AF2C2C";
										document.getElementById("inventory_type_name").style.backgroundColor = "#FFF7F7";
										setTimeout(function() {
											$('#errorhover').fadeOut();
										}, 3000);
										
										return false;

									}else if (department == ""|| department == null){
										$(".errormessagediv").show();
										$(".validateTips").text("Select Department Name");

										document.getElementById("department").style.border = "1px solid #AF2C2C";
										document.getElementById("department").style.backgroundColor = "#FFF7F7";
										setTimeout(
												function() {
													$('.errormessagediv')
															.fadeOut();
												}, 3000);

										return false;
									}else if (description == ""|| description == null){
										$(".errormessagediv").show();
										$(".validateTips").text("Select Item Type");

										document.getElementById("description").style.border = "1px solid #AF2C2C";
										document.getElementById("description").style.backgroundColor = "#FFF7F7";
										setTimeout(
												function() {
													$('.errormessagediv')
															.fadeOut();
												}, 3000);

										return false;
									}
								else
								{	
										$("#inventory").attr('action','inventorymenu.html?method=AddInventoryType');
										$("#inventory").submit();
								}
						

							});
					
					
					
					
					$('#editinventoryType')
					.click(
							function()

							{
								var cnt = 0;

								$('input.InventoryTypesList_Checkbox_Class:checkbox:checked')
										.map(
												function() {

													var term_id = $(
															this).attr(
															"id");

													var split_id = term_id
															.split('_');
													getData = split_id[2]
															.split(',');

													cnt++;
												});

								if (cnt == 0 || cnt > 1) {

									$(".errormessagediv").show();
									$(".validateTips")
											.text(
													"Select Any One Inventory Type");

								}

								else {

									var name = getData[0];

									window.location.href = "inventorymenu.html?method=EditInventoryType&name="
											+ name;

								}

							});
					
					
					
					
					var hdepartment = $("#hdepartment").val();
					if (hdepartment != undefined) {
						$("#department").val(hdepartment);

					}
					
					
					$('#deleteinventoryType')
					.click(
							function()

							{
								var cnt = 0;
								
								var getDataArray=[]; //add array1
								$('input.InventoryTypesList_Checkbox_Class:checkbox:checked')
										.map(
												function() {

													var term_id = $(
															this).attr(
															"id");

													var split_id = term_id
															.split('_');
													getData = split_id[2]
															.split(',');
													getDataArray.push(getData[0]);

													cnt++;
												});

								
								var datalist = {'getDataArray':getDataArray.toString()};//create json data3
								$
								.ajax({
									type : "GET",
									url : "inventorymenu.html?method=DeleteInventoryType",
									data : datalist,
									async : false,

									success : function(
											response) {

										var result = $
												.parseJSON(response);

										
										if (result.jsonResponse == "true") {
											$(
													".successmessagediv")
													.show();
											$(
													".validateTips")
													.text("Inventory Type Deleted Successfully");
											setTimeout(function() {

												location.reload();

											}, 3000);
										}

										else {

											$(
													".errormessagediv")
													.show();
											$(
													".validateTips")
																	.text("Inventory Type Not Deleted");

														}

														

													}
												});

									});
					
					
					
					
					$("#excelDownload")
					.click(
							function()

							{
								var searchname = $('#searchvalue').val();


								window.location.href = "inventorymenu.html?method=InventoryTypeXLS&searchname="
												+ searchname;

							});
			
			
			
			
			$("#pdfDownload")
			.click(
					function()

					{
						var searchname = $('#searchvalue').val();


						window.location.href = "inventorymenu.html?method=InventoryTypePDF&searchname="
												+ searchname;

					});
			
			
				});

function HideError() {
	document.getElementById("inventory_type_name").style.border = "1px solid #ccc";
	document.getElementById("inventory_type_name").style.backgroundColor = "#fff";
}