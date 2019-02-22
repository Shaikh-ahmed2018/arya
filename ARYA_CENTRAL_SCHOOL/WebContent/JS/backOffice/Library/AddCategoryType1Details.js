$(document).ready(function()
{
	$("#categorytype").val($("#hiddencat").val());
	$("#status").val($("#hiddenstatus").val());
	$("#status").val($("#hiddenstatus").val());
	
	if($("#categorytype").val()!="" && $("#categorytype").val()!=null){
		getSubCategoryTypeList();
		$('#subcategory').val($("#hiddensubcat").val());
	}
	var hiddenid=$("#hiddensubcatcode").val();
	if(hiddenid != ""){
		$("#subcategorytype1code").prop("readonly",true);
	}
	
	$('#save').click(function() {
		var hiddenid=$("#hiddensubcatcode").val();
		var categorytypecode = $("#categorytype").val();
		var subcategorytypecode = $("#subcategory").val();
		var subcategorytype1code = $("#subcategorytype1code").val();
		var subcategorytype1name = $("#subcategorytype1name").val();
		var Status = $("#status").val().trim();
		var description = $("#description").val().trim();
		var id=$('#hiddensubcat1').val().trim();
		
		if(categorytypecode == "all" || categorytype==undefined || categorytypecode==null){
			
			$(".errormessagediv").show();
			$(".validateTips").text("Select Category Type");
			showError("#categorytype");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}

		


		else if (subcategorytypecode=="all" ||subcategorytypecode==undefined|| subcategorytypecode == null) {
			

			$(".errormessagediv").show();
			$(".validateTips").text("Select Sub Category Type");
			showError("#subcategory");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} /*else if(!subcategorytyp1ecode.match(/^[a-zA-Z0-9]*$/)){

			$(".errormessagediv").show();
			$(".validateTips").text("Subcategory type 1 Code Should Contain Only Characters and Numbers With No Spaces");
			showError("#subcategorytyp1ecode");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}*/else if(!subcategorytype1code.match(/^[a-zA-Z0-9]*$/)){
			$(".errormessagediv").show();
			$(".validateTips").text("Subcategory type 1 Code Should Contain Only Characters and Numbers With No Spaces");
			showError("#subcategorytype1code");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if (subcategorytype1code==""||subcategorytype1code == null) {
			
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Sub Category Type 1 code");
			showError("#subcategorytype1code");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if (subcategorytype1name=="" ||subcategorytype1name == null) {
			
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Sub Category Type 1 name");
			showError("#subcategorytype1name");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}/*else if(validatSubCategoryType1Code() == 1){

			$(".errormessagediv").show();

			$(".validateTips").text("Sub Category Type1 Code already exists");

			return false;
		}*/
		
		else{
			datalist = {
				"categorytypecode":categorytypecode,
				"subcategorytypecode":subcategorytypecode,
				"subcategorytype1code" : subcategorytype1code,
				"subcategorytype1name" : subcategorytype1name,
				"Status" : Status,
				"description" : description,
				"subcategorytype1_id": id,
				"hiddenid":hiddenid
			 },
			
		$.ajax({
			type : "POST",
			url : "LibraryMenu.html?method=insertSubCategoryType1",
			data : datalist,
			async : false,
			success : function(data) {

				var result = $.parseJSON(data);
				if(result.status == "Sub Catagory Type 1 Code is Already Exist"){
					$('.errormessagediv').show();
					$(".successmessagediv").hide();
					$(".validateTips").text(result.status);
					$("#subcategorytype1code").focus();
				}else{
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".successmessage").text(result.status);
					setTimeout(function() {
						window.location.href="LibraryMenu.html?method=subCategoryType1";
				}, 2000);
				}
				
			}
		

		});
		}
	
});
	
	$("#categorytype").change(function(){
		getSubCategoryTypeList();
	});
});


function getSubCategoryTypeList(){
	


	var categorycode=$("#categorytype").val();
	datalist={
			"categoryid" : categorycode
	},

	$.ajax({

		type : 'POST',
		url : "LibraryMenu.html?method=getSubCategoryByCategory",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#subcategory').html("");

			$('#subcategory').append('<option value="all">' +"----------Select----------"+ '</option>');

			for ( var j = 0; j < result.SubCategoryList.length; j++) {

				$('#subcategory').append('<option value="'

						+ result.SubCategoryList[j].subcategorytypecode + '">'

						+ result.SubCategoryList[j].subcategorytypename

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
/*function validatSubCategoryType1Code(){
	
	var subCategoryType1Validate=0;
	
	var categorytypecode = $("#categorytype").val();
	var subcategorytypecode = $("#subcategory").val();
	var subcategorytype1code = $("#subcategorytype1code").val();
		var streamObject = {
			"categorytypecode":categorytypecode,
			"subcategorytypecode" : subcategorytypecode,
			"subcategorytype1code" : subcategorytype1code
		};
	$.ajax({
		type : "GET",
		url : 'LibraryMenu.html?method=validateSubCatagoryType1Code',
		data : streamObject,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
		if (result.status=="true") {
			subCategoryType1Validate = 1;
			}
			else 
			{
				subCategoryType1Validate = 0;
			}
		}
	});
	return subCategoryType1Validate;
}*/