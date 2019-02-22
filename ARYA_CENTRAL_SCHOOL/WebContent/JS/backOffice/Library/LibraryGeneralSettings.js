$(document).ready(function(){
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
	
	$("#todaydate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
	});
	$("#duedate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate :0,
		changeMonth : true,
		changeYear : true,
	});
	
	
	$("#save").click(function(){
		 if($("#itemdescription").val() == "" || $("#itemdescription").val()==null){
			 $(".errormessagediv").show();
			 showError("#itemdescription","Field Required - Item Description");
				return false;
		
		 }
		 
		 
		 
		 else if($("#noofday").val() == "" ||$("#noofday").val() == null){
			 $(".errormessagediv").show();
			 showError("#noofday","Field Required - No of Day This Item Lend ");
				return false;
				
		 }
		
		 else if($("#fineamount").val() == "" || $("#fineamount").val() == null){
			 $(".errormessagediv").show();
			 showError("#fineamount","Field Required -Fine Amount");
				return false;
				
		 }
		 
		 else if($("#amountperday").val() == "" || $("#amountperday").val() == null){
			 $(".errormessagediv").show();
			 showError("#amountperday","Field Required - Amount Per Day");
				return false;
				
		 }
		 
		 else if($("#fineincrement").val() == "" || $("#fineincrement").val() == null){
			 $(".errormessagediv").show();
			 showError("#fineincrement","Field Required - Fine Increment In");
				return false;
				
		 }
		 else if($("#todaydate").val() == "" || $("#todaydate").val() == null){
			 $(".errormessagediv").show();
			 showError("#todaydate","Field Required - Today Date");
				return false;
				
		 }
		
		 
		
		 else if($("#duedate").val() == "" || $("#duedate").val() == null){
			 $(".errormessagediv").show();
			 showError("#duedate","Field Required -Due Date");
				return false;
				
		 }
		 
		 else {
			
			 datalist = {
				"itemdescription" :$("#itemdescription").val(),
				"noofday" : $("#noofday").val(),
				"fineamount" : $("#fineamount").val(),
				"todaydate" :$("#todaydate").val(),
				"amountperday":$("#amountperday").val(),
				"fineincrement":$("#fineincrement").val(),
				"duedate":$("#duedate").val(),
				"hiddenid":$("#hiddenid").val(),
				
			 };
			 
			 $.ajax({
				 type : "POST",
				 url : "LibraryMenu.html?method=addGeneralSettings",
				 data : datalist,
				 success:function(data){
					 var result = $.parseJSON(data);
					 if(result.status == "addsuccess" ){
						 $('.successmessagediv').show();
						 $(".validateTips").text("Adding Record Progressing");
						 setTimeout(function() {
								window.location.href="LibraryMenu.html?method=ListgeneralSettings";
							}, 3000);
					 }
					  if(result.status == "success" ){
						 $('.successmessagediv').show();
						 $(".validateTips").text("Updating Record Progressing");
						 setTimeout(function() {
								window.location.href="LibraryMenu.html?method=ListgeneralSettings";
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

	/*function validationsubsettings(){

		flag = 0;
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
		return flag;
	}

*/

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
	
