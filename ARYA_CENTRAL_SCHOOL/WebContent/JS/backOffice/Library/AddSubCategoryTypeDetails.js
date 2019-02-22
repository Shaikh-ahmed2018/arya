$(document).ready(function()
{
	$("#status").val($("#hiddenstatus").val());
	
	var hiddencategoryId=$('#categoryId').val();
	 updatesubcategorytypename=$("#subcategorytypecode").val();
	 if(updatesubcategorytypename > 0){
		 updatesubcategorytypename = 1;
	 }
	 var hiddenSubcategoryId=$("#hiddenSubcategoryId").val();

	 
	 
	if(hiddenSubcategoryId != ""){
		$("#subcategorytypecode").prop("readonly",true);
	}

	if(hiddencategoryId != "" || hiddencategoryId != undefined ){
		$("#categorytype  option[value='"+hiddencategoryId.trim()+"' ]").attr('selected', 'true');
	}
	
	$('#save').click(function() {
		
		var id=hiddenSubcategoryId;
		var categorytype = $("#categorytype").val().trim();
		var subcategorytypecode = $("#subcategorytypecode").val().trim();
		var subcategorytypename = $("#subcategorytypename").val().trim();
		var status = $("#status").val().trim();		
		var description = $("#description").val().trim();
		
		if (categorytype == "" || categorytype==undefined || categorytype == null) {
			
			$(".errormessagediv").show();
			$(".validateTips").text("Select Category Type");
			showError("#categorytype");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if(!subcategorytypecode.match(/^[a-zA-Z0-9]*$/)){

			$(".errormessagediv").show();
			$(".validateTips").text("Subcategory type Code Should Contain Only Characters and Numbers With No Spaces");
			showError("#subcategorytypecode");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if(subcategorytypecode=="" || subcategorytypecode == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Sub Category Type Code");
			showError("#subcategorytypecode");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if(subcategorytypename=="" || subcategorytypename == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Sub Category Type Name");
			showError("#subcategorytypename");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;	
		}/*else if (subcategorytypecode == "Sub Category Type Code Already Exist"){
			$('.errormessagediv').show();
			$('.validateTips').text("Sub Category Type Code Already Exist");
			return false;			
		}*/		
		else{
			datalist = {
					"categorytype" : categorytype,
					"subcategorytypecode" : subcategorytypecode,
					"subcategorytypename" : subcategorytypename,
					"status" : status,
					"description" : description,
					"subcategory_id": id
			},
			$.ajax({
				type : "POST",
				url : "LibraryMenu.html?method=insertSubCategoryType",
				data : datalist,
				async : false,
				success : function(data) {

					var result = $.parseJSON(data);
					
					if(result.status == "Sub Category Type Code Already Exist"){
						$(".successmessagediv").hide();
						$('.errormessagediv').show();						
						$(".validateTips").text(result.status);
						$("#subcategorytypecode").focus();
						showError("#subcategorytypecode");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
					}else{$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".successmessage").text(result.status);
					setTimeout(function() {
							window.location.href="LibraryMenu.html?method=subCategoryType";
					}, 2000);
					}
					
				}

			});
		}
	});
});

function validateSubCatName() {
	var desname_validate="";
	
	var subname = $('#subcategorytypecode').val().trim();
	
		var subname_object = {
				"subname" : subname
		};

		$.ajax({

			type : "GET",
			url : "LibraryMenu.html?method=validatesubcatname",
			data : subname_object,
			async : false,

			success : function(data) {
				
				var result = $.parseJSON(data);
				if (result.des_available = "true") {
					desname_validate = "true";	
				} else {
					desname_validate = "false";
				}
				return desname_validate;
			}
		});
	
}

function validatesubcatnameupdate(){

	var desname_validate="";
	
	var subname = $('#subcategorytypename').val().trim();
	if(subname!=updatesubcategorytypename){
		var subname_object = {
				"subname" : subname
		};


		$.ajax({

			type : "GET",
			url : "LibraryMenu.html?method=validatesubcatnameupdate",
			data : subname_object,
			async : false,

			success : function(data) {

				var result = $.parseJSON(data);
								
				if (result.des_available = "true") {
					desname_validate = "true";
					$('.errormessagediv').show();
					$('.validateTips').text("Sub Category Type Name Already Exist");
					return false;		
				} else {
					desname_validate = "false";
				}
			}
		
		});
	}
	else{
		desname_validate="false";
	}

	return desname_validate;
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




