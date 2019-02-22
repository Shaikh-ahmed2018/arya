$(document).ready(function(){
	
	 var  ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+" +
		"@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)" +
		"+[a-zA-Z]{2,}))$";
	
	
	$("input,select,textarea").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0 ){
		hideError("#"+$(this).attr("id"));
		
		$(".errormessagediv").hide();
		}
	},
	});
	$("#supdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
	});
	

$("#save").click(function(){
	 if($("#Supplier").val() == "" || $("#Supplier").val()==null){
		 $(".errormessagediv").show();
		 showError("#Supplier","Field Required - Supplier Name");
			return false;
	
	 }
	 /*else if(!($("#Supplier").val()).match(/^[a-zA-Z0-9]*$/)) {
			 $(".errormessagediv").show();
			 showError("#Supplier","Field Required - Special Characters are not allowed in Supplier Name ");
				return false; 
		}*/
	
	 else if($("#emailid").val() == "" || $("#emailid").val() == null){
		 $(".errormessagediv").show();
		 showError("#emailid","Field Required E-Mail");
			return false;
			
	 }
	 
	 
	 else if(!($("#emailid").val()).match(ePattern)){
	    	
    	 $(".errormessagediv").show();
		 showError("#emailid","Field Required - Enter Valid E-Mail ");
			return false;
			
}
	 
	 else if($("#Supplieraddress").val() == "" ||$("#Supplieraddress").val() == null){
		 $(".errormessagediv").show();
		 showError("#Supplieraddress","Field Required - Address");
			return false;
			
	 }
	
	 
	 else if($("#supdate").val() == "" || $("#supdate").val() == null){
		 $(".errormessagediv").show();
		 showError("#supdate","Field Required - Date");
			return false;
			
	 }
	 else if($("#telephone").val() == "" || $("#telephone").val() == null){
		 $(".errormessagediv").show();
		 showError("#telephone","Field Required - Tel-Phone");
			return false;
			
	 }
	 else if(($("#telephone").val()).length!=11 ) {
		 $(".errormessagediv").show();
		 showError("#telephone","Field Required - Tel-Phone Must Be 11 Digits.");
			return false;
			
	 }
	 
	 else if($("#supmobnum").val() == "" || $("#supmobnum").val() == null){
		 $(".errormessagediv").show();
		 showError("#supmobnum","Field Required - Mobile Number");
			return false;
			
	 }
	 else if(($("#supmobnum").val()).length!= 10  ){
		 $(".errormessagediv").show();
		 showError("#supmobnum","Field Required -Mobile Number Must Be 10 Digits.");
			return false;
			
	 }
	 
	/* else if($("#Fax").val() == "" || $("#Fax").val() == null){
		 $(".errormessagediv").show();
		 showError("#Fax","Field Required - Contact Number");
			return false;
			
	 }*/
	 
	 
	 else if(validationsubsettings() == 1){
		 $('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text(" Data Already Exists.");
			$(".errormessagediv").delay(3000).fadeOut();
	 
	 }
	 
	 
	 else {
		
		 datalist = {
			"supplier" : 	$("#Supplier").val(),
			"supplieradd" : $("#Supplieraddress").val(),
			"emailid" : $("#emailid").val(),
			"supdate" :$("#supdate").val(),
			"telephone":$("#telephone").val(),
			"supmobnum":$("#supmobnum").val(),
			"fax":$("#Fax").val(),
			"hiddenid":$("#hiddenid").val(),
			
		 };
		 
		 $.ajax({
			 type : "POST",
			 url : "LibraryMenu.html?method=addSupplierSettings",
			 data : datalist,
			 success:function(data){
				 var result = $.parseJSON(data);
				 if(result.status == "addsuccess" ){
					 $('.successmessagediv').show();
					 $(".validateTips").text("Adding Record Progressing");
					 setTimeout(function() {
							window.location.href="LibraryMenu.html?method=ListsupplierSettings";
						}, 3000);
				 }
				  if(result.status == "success" ){
					 $('.successmessagediv').show();
					 $(".validateTips").text("Updating Record Progressing");
					 setTimeout(function() {
							window.location.href="LibraryMenu.html?method=ListsupplierSettings";
						}, 3000);
				 }
				 /*else (result.status == "fail" ){
					 $('.successmessagediv').show();
					 $(".validateTips").text("Adding Record Failed");
					 setTimeout(function() {
							window.location.href="LibraryMenu.html?method=ListpublisherSettings";
						}, 3000);
				 }
				 */
			 }
		 });
		 
		 
	 }
	
	
});


});





function validationsubsettings(){

	flag = 0;


	 if($("#hiddenid").val()!="" && ($("#hiddensubnm").val().toLowerCase()!=$("#Supplier").val().toLowerCase())){
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=validationsubsettings",
		data : {
			"supplier" : $("#Supplier").val(),
			"supplieradd" : $("#Supplieraddress").val(),
			"emailid" : $("#emailid").val(),
			"telephone":$("#telephone").val(),
			"supmobnum":$("#supmobnum").val(),
			
			},
		async : false,
		success : function(response) {
		
		var result = $.parseJSON(response);
		if(result.result=="true"){
			flag = 1;
		}else{
			flag =0;
		}
	}
});
	 }
	 else if ($("#hiddenid").val()!="" && ($("#hiddensubadd").val().toLowerCase()!=$("#Supplieraddress").val().toLowerCase()) ){
		 
		 $.ajax({
				type : 'POST',
				url : "LibraryMenu.html?method=validationsubsettings",
				data : {
					"supplier" : $("#Supplier").val(),
					"supplieradd" : $("#Supplieraddress").val(),
					"emailid" : $("#emailid").val(),
					"telephone":$("#telephone").val(),
					"supmobnum":$("#supmobnum").val(),
					
					},
				async : false,
				success : function(response) {
				
				var result = $.parseJSON(response);
				if(result.result=="true"){
					flag = 1;
				}else{
					flag =0;
				}
			}
		});
		 
		 
	 }
	 
	 else if($("#hiddenid").val()=="" ){
		 
		 $.ajax({
				type : 'POST',
				url : "LibraryMenu.html?method=validationsubsettings",
				data : {
					"supplier" : $("#Supplier").val(),
					"supplieradd" : $("#Supplieraddress").val(),
					"emailid" : $("#emailid").val(),
					"telephone":$("#telephone").val(),
					"supmobnum":$("#supmobnum").val(),
					
					},
				async : false,
				success : function(response) {
				
				var result = $.parseJSON(response);
				if(result.result=="true"){
					flag = 1;
				}else{
					flag =0;
				}
			}
		});
		 
		 
	 }
	 else{
		 flag =0;
	 }
	 
	return flag;
}



function showError(id,errorMessage){
	$(id).focus();
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}
