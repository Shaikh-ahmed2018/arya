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
	$("#pubdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
	});

	

$("#save").click(function(){
	 if($("#Publisher").val() == "" || $("#Publisher").val()==null){
		 $(".errormessagediv").show();
		 showError("#Publisher","Field Required - Publisher Name");
			return false;
	
	 }
	 
	 
	 /*else if(!($("#Publisher").val()).match(/^[a-zA-Z0-9]*$/)) {
		 $(".errormessagediv").show();
		 showError("#Publisher","Field Required - Special Characters are not allowed in Supplier Name ");
			return false; 
	}*/
	 else if($("#Email").val() == "" || $("#Email").val() == null){
		 $(".errormessagediv").show();
		 showError("#Email","Field Required - E-Mail");
			return false;
	 }	
	 else if(!($("#Email").val()).match(ePattern)){
		    	
		    	 $(".errormessagediv").show();
				 showError("#Email","Field Required - Enter Valid E-Mail");
					return false;
					
	 }
	 else if($("#Address").val() == "" ||$("#Address").val() == null){
		 $(".errormessagediv").show();
		 showError("#Address","Field Required - Address");
			return false;
			
	 }
	 
	 else if($("#pubdate").val() == "" || $("#pubdate").val() == null){
		 $(".errormessagediv").show();
		 showError("#pubdate","Field Required -Date");
			return false;
			
	 }
	 else if($("#telephone").val() == "" || $("#telephone").val() == null){
		 $(".errormessagediv").show();
		 showError("#telephone","Field Required -Tel-phone");
			return false;
			
	 }
	
	 else if(($("#telephone").val()).length!=11 ) {
		 $(".errormessagediv").show();
		 showError("#telephone","Field Required - Tel-Phone Must Be 11 Digits.");
			return false;
			
	 }
	 else if($("#mobilenum").val() == "" || $("#mobilenum").val() == null){
		 $(".errormessagediv").show();
		 showError("#mobilenum","Field Required -Mobile Number");
			return false;
			
	 }
	 else if(($("#mobilenum").val()).length!= 10  ){
		 $(".errormessagediv").show();
		 showError("#mobilenum","Field Required -Mobile Number Must Be 10 Digits.");
			return false;
			
	 }
	/* else if($("#Fax").val() == "" || $("#Fax").val() == null){
		 $(".errormessagediv").show();
		 showError("#Fax","Field Required -Fax");
			return false;
			
	 }*/
	 
	
	 else if(validationpubsettings() == 1){
		 $('.successmessagediv1').hide();
			$(".errormessagediv").show();
			$(".validateTips").text(" Data Already Exists.");
			$(".errormessagediv").delay(3000).fadeOut();
			return false;
			 
	 }
	 
	 
	 else {
		
		 datalist = {
			"publisher" : 	$("#Publisher").val(),
			"address" : $("#Address").val(),
			"email" : $("#Email").val(),
			"pubdate" : $("#pubdate").val(),
			"telphone" : $("#telephone").val(),
			"mobilenum" : $("#mobilenum").val(),
			"fax" : $("#Fax").val(),
			"hiddenid":$("#hiddenid").val()
		 };
		 
		 $.ajax({
			 type : "POST",
			 url : "LibraryMenu.html?method=addPublisherSettings",
			 data : datalist,
			 success:function(data){
				 var result = $.parseJSON(data);
				 if(result.status == "addsuccess" ){
					 $('.successmessagediv').show();
					 $(".validateTips").text("Adding Record Progressing");
					 setTimeout(function() {
							window.location.href="LibraryMenu.html?method=ListpublisherSettings";
						}, 3000);
				 }
				  if(result.status =="success" ){
					 $('.successmessagediv').show();
					 $(".validateTips").text("Updating Record Progressing");
					 setTimeout(function() {
							window.location.href="LibraryMenu.html?method=ListpublisherSettings";
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

function validationpubsettings(){
	flag = 0;
	
 
	 if($("#hiddenid").val() !="" && ($("#hiddenpubname").val().toLowerCase() !=$("#Publisher").val().toLowerCase()) || $(".hiddenpubadd").val()!=$(".address").val()){

		
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=validationpubsettings",
		data : {
			 "publisher" : 	$("#Publisher").val(),
			 "address" : $("#Address").val(),
			 "email" : $("#Email").val(),
			 "telphone" : $("#telephone").val(),
			 "mobilenum" : $("#mobilenum").val(),
			 
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
	 /* else if($("#hiddenid").val()!="" && $(".hiddenpubadd").val()!=$(".address").val()){
			alert($("#hiddenid").val());
			alert($(".hiddenpubadd").val()!= $(".address").val());
			$.ajax({
				type : 'POST',
				url : "LibraryMenu.html?method=validationpubsettings",
				data : {
					"publisher" : 	$("#Publisher").val(),
					"address" : $("#Address").val(),
					"email" : $("#Email").val(),
					"telphone" : $("#telephone").val(),
					"mobilenum" : $("#mobilenum").val(),
					
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
		} */
else if($("#hiddenid").val()==""){
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=validationpubsettings",
		data : {
			"publisher" : 	$("#Publisher").val(),
			"address" : $("#Address").val(),
			"email" : $("#Email").val(),
			"telphone" : $("#telephone").val(),
			"mobilenum" : $("#mobilenum").val(),
			
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
	flag = 0;
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
