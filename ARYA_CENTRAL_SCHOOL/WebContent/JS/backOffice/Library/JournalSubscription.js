$(document).ready(function(){
	
	var accyear=$("#Acyearid").val($("#hidenaccyear").val());
	
	$("input,select").on({
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

/*	$("#code").val($("#hiddencode").val());*/
	$("#dateon").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
	});
	$("#todate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,   
		changeYear : true,
	});
	
/*	$("#todate").val(GetTodayDate()).datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,   
		changeYear : true,
	});*/
	
	$("#duedate").datepicker({
	     beforeShow: function () { 
	         $('#ui-datepicker-div').css('z-index',9999); 
	     },
	     dateFormat: 'dd-mm-yy',
	     beforeShowDay: function(date){
	          return [(date >= ($("#todate").datepicker("getDate") 
	                          || new Date()))];
	     }
	});
	
	if($("#hiddenid").val()!="")
	{
		$("#edit11").show();
		$("#save11").hide();
/*
    $("#code").val($("#categoryname").val());*/
    $("#publisher").val($("#hiddenpubname").val());
    $("#Supplier").val($("#hiddenname").val());
    $("#subscription").val($("#hiddensubperiode").val().split("-")[0]);
    $("#subscriptiondate").val($("#hiddensubperiode").val().split("-")[1]);
    if($("#hiddenaccesion").val() !=""){
    	
    	 $("#select").prop("checked",true);
      }
	}
   
	$("#save").click(function(){
		if($("#select").prop("checked")){
			 accessionno="true";
		} else{
			 accessionno="false";
		}
		
		if ($("#categoryname").val() == "" || $("#categoryname").val()  == undefined) { 
				$(".errormessagediv").show();
				showError("#categoryname","Field Required -Name");
					return false;
		}	
		  
		
		else if ($("#code").val() == "" || $("#code").val()  == undefined) {
			$(".errormessagediv").show();
			showError("#code","Field Required -Code");
			return false;
			
}
		
		else if ($("#journaltype").val() == "" || $("#journaltype").val()  == undefined) {
					$(".errormessagediv").show();
					showError("#journaltype","Field Required -Journal Type");
					return false;
					
		}
		else if ($("#publisher").val() == "" || $("#publisher").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#publisher","Field Required -Publisher");
				return false;
		}
		else if ($("#Supplier").val() == "" || $("#Supplier").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#Supplier","Field Required -Supplier");
				return false;
				
		}
	
		  else if ($("#ratepercopy").val() == "" || $("#ratepercopy").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#ratepercopy","Field Required -Rate Per Copy");
				return false;
				
		  }
	
		  else if ($("#noofcopy").val() == "" || $("#noofcopy").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#noofcopy","Field Required -No Of Copy");
				return false;
				
		  }
		  else if ($("#department").val() == "" || $("#department").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#department","Field Required -Department");
				return false;
				
		  }
		  else if ($("#dateon").val() == "" || $("#dateon").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#dateon","Field Required -Registration On");
				return false;
				
		  }
		  else if ($("#subscription").val() == "" || $("#subscription").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#subscription","Field Required -Subscription Periode");
				return false;
				
		  }
		  else if ($("#subscriptiondate").val() == "" || $("#subscriptiondate").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#subscriptiondate","Field Required -Subscription Periode");
				return false;
				
		  }
		 
		
		  else if ($("#todate").val() == "" || $("#todate").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#todate","Field Required -Today Date");
				return false;
				
		  }
		  else if ($("#duedate").val() == "" || $("#duedate").val()  == undefined) {
				$(".errormessagediv").show();
				showError("#duedate","Field Required -Due Date");
				return false;
				
		  }
		  else{
				savejournalsubscriptiondetail(accessionno);
		  }
	});
	
	$("#ratepercopy,#noofcopy").change(function(){
		$("#totalrate").val("");
	
		if($("#ratepercopy").val() !="" && $("#noofcopy").val()!=""){
			$("#totalrate").val(parseFloat($("#ratepercopy").val())*parseFloat($("#noofcopy").val()));
		}
		
		
	});
	
	
	$("#ratepercopy,#noofcopy").change(function(){
		$("#annualratepercopy").val("");
	
		if($("#ratepercopy").val() !="" && $("#noofcopy").val()!=""){
			$("#annualratepercopy").val(parseFloat($("#ratepercopy").val())*((parseFloat($("#noofcopy").val())/parseFloat($("#noofcopy").val()))*12));
		}
		
		
	});
	/*$("#categoryname").change(function(){
		$("#code").val($(this).val());
	});
	*/
 
	
});
function savejournalsubscriptiondetail(accessionno){

	datalist = {
		  "subscription" :$("#subscription").val()+"-"+$("#subscriptiondate").val(),
		    "Name":$("#categoryname").val(),
		    "code":$("#code").val(),
		    "journaltype":$("#journaltype").val(),
		    "publisher":$("#publisher").val(),
		    "Supplier":$("#Supplier").val(),
		    "ratepercopy":$("#ratepercopy").val(),
		    "noofcopy":$("#noofcopy").val(),
		    "department":$("#department").val(),
   		    "otherdetails":$("#otherdetails").val(),
   		    "select":$("#select").val(),
   		    "dateon":$("#dateon").val(),
   		    "todate":$("#todate").val(),
   		    "duedate":$("#duedate").val(),
   	        "totalrate":$("#totalrate").val(),
   		    "annualratepercopy":$("#annualratepercopy").val(),
   		    "hiddenid":$("#hiddenid").val(),
   		    "accessionno":accessionno,
   		   
		 };
		 
		 $.ajax({
			 type : "POST",
			 url : "LibraryMenu.html?method=savejournalsubscriptiondetail",
			 data : datalist,
			 success:function(data){
				 var result = $.parseJSON(data);
				 if(result.status == "addsuccess" ){
					 $('.successmessagediv').show();
					 $(".validateTips").text("Adding Record Progressing");
					 setTimeout(function() {
							window.location.href="LibraryMenu.html?method=LibraryjournalSubscriptionList";
						}, 3000);
				 }
				 
				 if(result.status == "success" ){
					 $('.successmessagediv').show();
					 $(".validateTips").text("Updating Record Progressing");
					 setTimeout(function() {
							window.location.href="LibraryMenu.html?method=LibraryjournalSubscriptionList";
						}, 3000);
				 }
			 }
			 });
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

