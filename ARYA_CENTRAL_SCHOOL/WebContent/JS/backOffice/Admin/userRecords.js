
function callAjax(urlWithMethod, dataToBeSend) {
	var jsonResult = "";
	try {
		$.ajax({
			type : "GET",
			url : urlWithMethod,
			data : dataToBeSend,
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);

				jsonResult = result.teacherdetails;

			}
		});
	} catch (e) {
		jsonResult = "";
	}
	return jsonResult;
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
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}
function Search(){
	var type=$("#typename").val();
	var searchText = $("#searchTextId")	.val();
	if(type==""){
		$(".errormessagediv").show();
		$(".validateTips").text("Select Any One Type");
		 showError("#typename");
		 setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
		return false;
	}else if(searchText== ""){
		window.location.href = "adminMenu.html?method=getUserRecords";
	}else{
		window.location.href = "adminMenu.html?method=getUserRecords&searchText="+ searchText+"&type="+ type;
	}

}



$(document).ready(function() {
	
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	});
	
	var hserachText = $("#hsearchTextId").val();
	var htype = $("#htype").val();
		
		if(htype!=""){
			
			$("#typename option[value="+htype+"]").attr('selected','true');
			
		}
		
	    if(hserachText!=""){
			
	    	$("#searchTextId").val(hserachText);
			
		}


 $("#searchButtonId").click(function() {
	 Search();
 });


 
 	$('#Edit').click(function() {
		
		
			var count = 0;
			var user_Code = null;

			$('input[name="userid"]:checked').each(function() {
				count = count + 1;
				user_Code = this.value;
			});
			if (count > 1 || count == 0) {
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Any User");

				return false;
			} else {
				$('.errormessagediv').hide();
				
				window.location.href="userManagement.html?method=changePasswordHome&userId="+user_Code;
				
				
			}					
	});
 	
 	
	$('#excelDownload')
	.click(
			function() {
				
				var hsearchTextId=$("#hsearchTextId").val();
				var type=$("#htype").val();
				window.location.href = 'userManagement.html?method=downloaduserManagementXLS&searchterm='+hsearchTextId+"&type="+ type;
		
			});
	$("#pdfDownload").click(function(){
		var hsearchTextId=$("#hsearchTextId").val();
		var type=$("#htype").val();
		
		window.location.href = "userManagement.html?method=downloaduserManagementPDF&searchterm="+hsearchTextId+"&type="+ type;
			
	});
		
 	

 	$("#delete").click(function(e){

 		count = 0;
	 user_Code = null;
	

		$('input[name="userid"]:checked').each(function() {
			count = count + 1;
			user_Code = this.value;
			
		});
		
		 if (count == 0 ) {
				$('.errormessagediv').show();
				$(".errormessagediv").attr("style","margin-left:290px;");
				$('.validateTips').text("Select Any Role");
				return false;
			}
		
		else{

			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are you sure to Block?</p>");
		}

	});
 	$("#dialog").dialog({	

		autoOpen: false,
		modal: true,
		title:'User Details',
		buttons : {
			"Yes" : function() {

				var user = {
						"userId" : user_Code,
						
					};
					$
							.ajax({
								type : "GET",
								url : "userManagement.html?method=blockUser",
								data : user,
								async : false,

								success : function(data) {
									var result = $.parseJSON(data);
									
									$('.errormessagediv').hide();
									
									if(result.status=="Block User Details Progressing..."){
										
									$(".successmessagediv").show();
									$(".successmessage").text(result.status);
									
									}else{
										
										$('.errormessagediv').show();
										$('.validateTips').text(result.status);
										
									}
									
									
									 setTimeout(function(){									   
										
										 location.reload();
									 
									 },3000);
									
								
								}

							});
				$(this).dialog("close");

			},
			"No" : function() {
				$(this).dialog("close");
			}
		}


	});
 	
 
});


	
			

