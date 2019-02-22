$(document).ready(function() {

					$("#status").val($("#hiddenstatus").val());
					$("#cattypeid").val($("#categoryId").val());
					
					
					if ($("#categorytype").val() != ""&& $("#categorytype").val() != null) {
						$('#categorytype').val($("#hiddencat").val());
						 getSubCategoryTypeList();
						
					}
					
					if ($("#subcategorytype").val() != ""&& $("#subcategorytype").val() != null) {
						$('#subcategorytype').val($("#hiddensubcat").val());
						getSubCategoryType1List();
					}

					if ($("#subcategorytype1").val() != ""&& $("#subcategorytype1").val() != null) {
						$('#subcategorytype1').val($("#hiddensubcat1code").val());
					}

					$("#categorytype").change(function() {
						getSubCategoryTypeList();
					});
					$("#subcategorytype").change(function() {
						getSubCategoryType1List();
					});

					var cat2code=$('#hiddensubcat2code').val();
					if(cat2code!= ""){
						$("#subcategorytype2code").prop("readonly",true);
					}
					$('#save').click(function() {

										var cat_type2_id = $('#hiddensubcat2id').val();
										var categorytypecode = $("#categorytype").val();
										var subcategorytypecode = $("#subcategorytype").val();
										var subcategorytype1code = $("#subcategorytype1").val();
										var subcategorytype2code = $("#subcategorytype2code").val();
										var subcategorytype2name = $("#subcategorytype2name").val();
										var Status = $("#status").val().trim();
										var description = $("#description").val().trim();
										var subcatid=$('#hiddensubcat2code').val();
										var hiddencode=$('#updatecategorytypecode').val();

										if(categorytypecode == "all" || categorytypecode==null || categorytypecode==undefined){
											
											$(".errormessagediv").show();
											$(".validateTips").text("Enter Category Type Name");
											showError("#categorytype");
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}
										else if (subcategorytypecode=="all" || subcategorytypecode == null || categorytypecode==undefined) {
											
											$(".errormessagediv").show();
											$(".validateTips").text("Enter Sub Category Type Name");
											showError("#subcategorytype");
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										} else if (subcategorytype1code=="all" || subcategorytype1code == null || categorytypecode==undefined) {
											
											$(".errormessagediv").show();
											$(".validateTips").text("Enter Sub  Category Type 1 Name");
											showError("#subcategorytype1");
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}else if(!subcategorytype2code.match(/^[a-zA-Z0-9]*$/)){

											$(".errormessagediv").show();
											$(".validateTips").text("Subcategory type 2 Code Should Contain Only Characters and Numbers With No Spaces");
											showError("#subcategorytype2code");
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}else if (subcategorytype2code=="" || subcategorytype2code == null) {
											
											$(".errormessagediv").show();
											$(".validateTips").text("Enter Sub Category Type 2 Code");
											showError("#subcategorytype2code");
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}else if (subcategorytype2name=="" || subcategorytype2name == null) {
											
											$(".errormessagediv").show();
											$(".validateTips").text("Enter Sub Category Type 2 Name");
											showError("#subcategorytype2name");
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
											return false;
										}
										else{
												datalist = {
													"hidden_subcat2_id" : cat_type2_id,
													"categorytypecode" : categorytypecode,
													"subcategorytypecode" : subcategorytypecode,
													"subcategorytype1code" : subcategorytype1code,
													"subcategorytype2code" : subcategorytype2code,
													"subcategorytype2name" : subcategorytype2name,
													"Status" : Status,
													"description" : description,
													"subcat2code":subcatid,
													"hiddencatcode":hiddencode

												},

												$.ajax({
													       type : "POST",
															url : "LibraryMenu.html?method=insertSubCategoryType2",
															data : datalist,
															async : false,
															success : function(data) {
																var result = $.parseJSON(data);
																if (result.status == "Sub Catagory Type 2 Code is Already Exist") {
																	$('.errormessagediv').show();
																	$(".successmessagediv").hide();
																	$(".validateTips").text(result.status);
																	$("#subcategorytype2code").focus();
																} else {
																	$('.errormessagediv').hide();
																	$(".successmessagediv").show();
																	$(".successmessage").text(result.status);
																	setTimeout(function() {
																		window.location.href = "LibraryMenu.html?method=subCategoryType2";
																	},2000);
																}

															}

														});
										}
									});

				});

function getSubCategoryTypeList() {

	var categorycode = $("#categorytype").val();
	var subcategorycode=$("#subcategory").val();
	var subcategory1code=$("#subcategorytype1").val();
	var subcategory2code=$("#subcategorytype1").val();

	datalist = {
		"categoryid" : categorycode,
		"subcategorycode":subcategorycode,
		"subcategory1code":subcategory1code,
		"subcategory2code":subcategory2code
	},

	$.ajax({

		type : 'POST',
		url : "LibraryMenu.html?method=getSubCategoryByCategory",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#subcategorytype').html("");

			$('#subcategorytype').append(
					'<option value="all">' + "----------Select----------"
							+ '</option>');

			for ( var j = 0; j < result.SubCategoryList.length; j++) {

				$('#subcategorytype').append('<option value="'

				+ result.SubCategoryList[j].subcategorytypecode + '">'

				+ result.SubCategoryList[j].subcategorytypename

				+ '</option>');
			}
		}
	});

}
function getSubCategoryType1List() {

	var subcategorycode = $("#subcategorytype").val();
			datalist = {
				"subcategorycode" : subcategorycode

			},

			$
					.ajax({

						type : 'POST',
						url : "LibraryMenu.html?method=getSubCategory1ByCategoryAndSubCategory",
						data : datalist,
						async : false,
						success : function(response) {

							var result = $.parseJSON(response);

							$('#subcategorytype1').html("");

							$('#subcategorytype1').append(
									'<option value="all">'
											+ "----------Select----------"
											+ '</option>');

							for ( var j = 0; j < result.SubCategoryType1List.length; j++) {

								$('#subcategorytype1')
										.append(
												'<option value="'

														+ result.SubCategoryType1List[j].subcategorytype1code
														+ '">'

														+ result.SubCategoryType1List[j].subcategorytype1name

														+ '</option>');
							}
						}
					});

}


function showError(id){
	
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	
}