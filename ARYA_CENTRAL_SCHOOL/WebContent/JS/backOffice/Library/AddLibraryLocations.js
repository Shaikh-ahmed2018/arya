$(document).ready(function()

		{
	$("#checkbox_id").change(function() {

		$(".select").prop('checked', $(this).prop("checked"));

	});
	
	$("#libloc").change(function()
			{
		value=$("#libloc").val();
	var  id="libloc";
		capital(value,id);
		$('#save').show();
			});
	
	
	$("#locationname").val($("#hiddenloc").val());
	$('#save').click(function() {
		$('#save').hide();
		
		var libraryLocations=$("#libloc").val().trim();
		var schoolName=$("#locationname").val().trim();
		
		if (schoolName == "" || schoolName==null) {

			$(".errormessagediv").show();
			$(".validateTips").text("Select School Name...");
			showError("#locationname");
			
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				
			}, 2000);
			$('#save').show();
			return false;
		
		
			
		}else if (libraryLocations == "" || libraryLocations==null) {

			$(".errormessagediv").show();
			$(".validateTips").text("Enter Library Location Name...");
			showError("#libloc");
			
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			$('#save').show();
			return false;
		} else if ( validateLibLocUpdate() == 1) {
						$(".errormessagediv").show();
		
			$(".validateTips").text("Library Location Name Already Exist..");
			$("#libloc").focus();
			document.getElementById("libloc").style.border = "1px solid #AF2C2C";
			document.getElementById("libloc").style.backgroundColor = "#FFF7F7";
			
			
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
		
			return false;
			
		}
		
			
		else if (validateLibUpdate() == 1) {
			
				$(".errormessagediv").show();
				$(".validateTips").text("Library Location-Name already Exist.....");
				$("#libloc").focus();
				document.getElementById("libloc").style.border = "1px solid #AF2C2C";
				document.getElementById("libloc").style.backgroundColor = "#FFF7F7";
				
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 2000);
				$('#save').show();
				return false;
			}
		
		else{
				
		datalist = {

				"hiddenlocationid":$("#hiddenlibid").val().trim(),	
				"schoolName" : $("#locationname").val().trim(),
				"libraryLocations" : $("#libloc").val().trim(),
				"description" : $("#description").val().trim(),

		},

		$.ajax({ 

			type : "POST",
			url : "LibraryMenu.html?method=insertLibraryLocations",
			data : datalist,
			async : false,
			success : function(data) {

				var result = $.parseJSON(data);

				
				 if(result.status == "success"){
					$(".successmessagediv").show();
					$(".validateTips").text("Adding Record is Processing...");
				
					setTimeout(function() {
						window.location.href="LibraryMenu.html?method=libraryLocations";
					},2000);
				}else if(result.status == "fail"){
					$(".errormessagediv").show();
					$(".validateTips").text("Adding Record Failed");
					setTimeout(function() {
						window.location.href="LibraryMenu.html?method=libraryLocations";
					},2000);
				}else if(result.status=="successfully"){
					$(".successmessagediv").show();
					$(".validateTips").text("Update Record is  processing....");
					setTimeout(function() {
						window.location.href="LibraryMenu.html?method=libraryLocations";

					},1000);
				}else if(result.status=="failed"){
					$(".errormessagediv").show();
					$(".validateTips").text("Update Record failed....");
					setTimeout(function() {
						window.location.href="LibraryMenu.html?method=libraryLocations";


					},2000);

				}else if(result.status=="deletesuccess"){
					$(".successmessagediv").show();
					$(".validateTips").text("Deleting Unmapped Record processing....");
					setTimeout(function() {
						window.location.href="LibraryMenu.html?method=libraryLocations";


					},1000);
				}else if(result.status=="deletefail"){

					$(".errormessagediv").show();
					$(".validateTips").text("Deleting Record failed....");
					setTimeout(function() {
						window.location.href="LibraryMenu.html?method=libraryLocations";


					},2000);

				}
				else{
					$(".errormessagediv").show();

					$(".validateTips").text("Selected Location is Mapped Cannot Delete");
					$('.errormessagediv').delay(3000).slideUp();
				}
				 setTimeout(function() {
						window.location.href="LibraryMenu.html?method=libraryLocations";


					},2000);

			}

		});
		
	}
});
		});	

function validateLibLocUpdate(){
	
	Lib_Loc_Name_validate_update = 0;
	var data={
			"schoolName" : $("#locationname").val().trim(),
			"libraryLocations" : $("#libloc").val().trim(),
			
	};
	
	if($("#hiddenlibid").val() != ""){
		
		
			Lib_Loc_Name_validate_update = 0;
		
	}
		
	else{
		$.ajax({
			type : "post",
			url : "LibraryMenu.html?method=validateLibLocationUpdate",
			data : data,
			async : false,

			success : function(data){
				var result = $.parseJSON(data);

				if (result.status == "true") {
					Lib_Loc_Name_validate_update = 1;
				} 
			}
		});
	}

return Lib_Loc_Name_validate_update;

}
function validateLibUpdate(){
	
	
	
	
	
	Lib_Loc_Name_validate_update = 0;
	var data={
			"schoolName" : $("#locationname").val().trim(),
			"libraryLocations" : $("#libloc").val().trim(),
			
	};
	
	if(($("#hiddenlibid").val()!="") && (($("#hiddenschoolname").val()!= $("#locationname option:selected").text()) || $("#libloc").val().toLowerCase()!= $("#hiddenlocname").val().toLowerCase())){
				$.ajax({  
			type : "post",
			url : "LibraryMenu.html?method=validateLibLocationUpdate",
			data : data,
			async : false,

			success : function(data){
				var result = $.parseJSON(data);

				if (result.status == "true") {
					Lib_Loc_Name_validate_update = 1;

				} else {
					Lib_Loc_Name_validate_update = 0;
				}
			}
		});
}else{
	Lib_Loc_Name_validate_update = 0; 
}

return Lib_Loc_Name_validate_update;
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
function capital(value,id)
{
	if(value.length>0)
		{
		var str=value.replace(value.substr(0,1),value.substr(0,1).toUpperCase());
		document.getElementById(id).value=str;
		
		}
		}
	
