function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function callAjax(urlWithMethod, dataToBeSend) {
	
	$.ajax({
         
		url : urlWithMethod,
		data : dataToBeSend,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			jsonResult = result.status;
			
			 setTimeout(function(){
				   
				 location.reload();
			 
			 },5000);
	
		}
	});
	return jsonResult;
}

function insertValidate() {

	
	
	$(".errormessagediv").hide();
	
	tips = $(".validateTips");
	var bValid = true;

	bValid = bValid && checkLengthText($('#streamName'), "Stream Name", 0, 30);

	bValid = bValid
			&& checkRegexpText($('#streamName'), /^[a-zA-Z\s]+$/g,
					"Name should be Alphabet");

	if (!bValid) {
		$(".errormessagediv").show();
	} else {

		$(".errormessagediv").hide();
	}

	return bValid;
		
}

$(document).ready(function() 
		{
			if($("#schoolId").val()!=""){
				$("#locationname").val($("#schoolId").val());
				$("#locationname").find("option").not("option[value*=LOC]").remove();
			}
			var pageurl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
		
			setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
				
					$("#savestreamid").click(function(){
						
				              	$(".successmessagediv").hide();
				              	$(".errormessagediv").hide();
						
						
						var streamname = $("#streamName").val().trim();
						var description = $("#description").val().trim();
						var streamId = $("#streamId").val().trim();
						
						if($("#locationname").val()=="" || $("#locationname").val()==null){
							
							$(".errormessagediv").show();
							$(".validateTips").text("Select School Name");
							$("#locationname").focus();
							document.getElementById("locationname").style.border = "1px solid #AF2C2C";
							document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							
							return false;
						}
						else if (streamname == "" || streamname==null) {
							
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Stream Name");
							$("#streamName").focus();
							document.getElementById("streamName").style.border = "1px solid #AF2C2C";
							document.getElementById("streamName").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							
							return false;
						}
						else if(!streamname.match(/^[a-zA-Z0-9\s]*$/)){
							
							$(".errormessagediv").show();
							$(".validateTips").text("Stream Name should contain only Characters");
							$("#streamName").focus();
							document.getElementById("streamName").style.border = "1px solid #AF2C2C";
							document.getElementById("streamName").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							
							return false;
							
						}
							else if(streamname.length < 4 ){
							
							$(".errormessagediv").show();
							$(".validateTips").text("Stream Name Should be Atleast 4 Characters");
							$("#streamName").focus();
							document.getElementById("streamName").style.border = "1px solid #AF2C2C";
							document.getElementById("streamName").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							
							return false;
							
						}
						else if(streamname.length > 50){
							
							$(".errormessagediv").show();
							$(".validateTips").text("Stream Name Too Long!!!");
							$("#streamName").focus();
							document.getElementById("streamName").style.border = "1px solid #AF2C2C";
							document.getElementById("streamName").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							
							return false;
							
						}
						else if(pageurl=="adminMenu.html?method=addStream" && validateStreamName() == 1){
						
	                            $(".errormessagediv").show();
								
								$(".validateTips").text(" Stream Name already exists");
								
								return false;
								
								
						}
						else if(pageurl != "adminMenu.html?method=addStream" && $("#schoolId").val() != $("#locationname").val() && (validateStreamName() == 1)){	
					
							
							
                            $(".errormessagediv").show();
							
							$(".validateTips").text(" Stream Name already exists");
							
							return false;
							
						}
						else{
							
								datalist = {
										"locationname":$("#locationname").val(),"streamId" : streamId,"streamname" : streamname, "description" :description
										
									};
								
								$
								.ajax({
									type : 'POST',
									url : "streamDetails.html?method=insertStreamDetailsAction",
									data : datalist,
									async : false,
									success : function(
											data) {
									
										
										var result = $.parseJSON(data);
										//alert("response"+result.jsonResponse);
										
										if(result.jsonResponse == "Added Successfully"){
											
											$(".errormessagediv").hide();
											$(".successmessagediv").show();
											 $(".validateTips").text("Adding Stream Progressing...");
											 setTimeout(function() {
													$('.successmessagediv').fadeOut();
												}, 3000);
											 setTimeout(function(){
													window.location.href = "adminMenu.html?method=streamList";
											 },3000);
											
										}
											
										if(result.jsonResponse=="Stream Update progressing"){
											
											$(".errormessagediv").hide();
											$(".successmessagediv").show();
											 $(".validateTips").text("Updating Stream Progressing...");
											 setTimeout(function() {
													$('.successmessagediv').fadeOut();
												}, 3000);
											
											 setTimeout(function(){
													
													window.location.href = "adminMenu.html?method=streamList";
											 
											 },3000);
											 
										}		
										
										
										
										
									}

								});
						
								
						}
						
						
						
					});
					
					
						
				});


function validateStreamName(){
	
	var streamname_validate=0;
	
		var streamId = $("#streamId").val().trim();
		var locationId=$("#locationname").val();
	 	var streamname=$("#streamName").val();
		var streamObject = {
			"locationId":locationId,"streamname" : streamname, "streamId" : streamId
		};
	
	
	$.ajax({

		type : "GET",
		url : 'streamDetails.html?method=validateStreamName',
		data : streamObject,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			
		if (result.status=="true") {
				
				streamname_validate = 1;

			}
			else 
				
			{
				streamname_validate = 0;
			}
		
		}
	});
	
	return streamname_validate;
	
}

function HideError() 
{
	
document.getElementById("streamName").style.border = "1px solid #ccc";
document.getElementById("streamName").style.backgroundColor = "#fff";

}