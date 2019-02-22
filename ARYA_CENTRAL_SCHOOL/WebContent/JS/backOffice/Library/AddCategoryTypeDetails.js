$(document).ready(function()
{
	
	$("#status").val($("#hiddenstatus").val());
	
	var hidden=$('#hiddencode').val().trim();	
	if(hidden != ""){
		$("#categorytypecode").prop("readonly",true);
	}
	
	$('#save').click(function() {
		var id=$('#category_id').val().trim();	
		var categorytypecode = $("#categorytypecode").val().trim();
		var categorytypename = $("#categorytypename").val().trim();
		var Status = $("#status").val().trim();
		var description = $("#description").val().trim();
		var hidden=$('#hiddencode').val().trim();	
		
		  if (categorytypecode=="" ||categorytypecode == null) {
				
				$(".errormessagediv").show();
				$(".validateTips").text("Enter Category Type Code");
				showError("#categorytypecode");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}else if(!categorytypecode.match(/^[a-zA-Z0-9]*$/)){

				$(".errormessagediv").show();
				$(".validateTips").text("Category type Code Should Contain Only Characters and Numbers With No Spaces");
				showError("#categorytypecode");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}else if (categorytypename=="" || categorytypename == null) {
				
				$(".errormessagediv").show();
				$(".validateTips").text("Enter Category Type Name");
				showError("#categorytypename");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}
			else{
			datalist = {
				"categorytypecode" : categorytypecode,
				"categorytypename" : categorytypename,
				"Status" : Status,
				"description" : description,
				"category_id": id,
				"hiddencode" : hidden
			 },
			
		$.ajax({
			type : "POST",
			url : "LibraryMenu.html?method=insertCategoryType",
			data : datalist,
			async : false,
			success : function(data) {

				var result = $.parseJSON(data);
				if(result.status == "Catagory Type Code is Already Exist"){
					$('.errormessagediv').show();
					$(".successmessagediv").hide();
					$(".validateTips").text(result.status);
					$("#categorytypecode").focus();
				}else{
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".successmessage").text(result.status);
					setTimeout(function() {
						window.location.href="LibraryMenu.html?method=categoryType";
				}, 2000);
				}
				
			}

		});
			}
		return false;
	});
});

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