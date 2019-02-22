function myFunction()

{
	
	var id = $('#feemasterid').val();
	var feeType=$('#feeType').val();
	var feeTypeId=$('#feeTypeId').val();
	
	var name = $('#feename').val();
	var concessiontype = $('.concession[name="concession"]:checked').val();

	if (feeType == "" || feeType == null || feeType.trim()=="")

	{

		$(".successmessagediv").hide();

		$(".errormessagediv1").show();

		$(".validateTips1").text("Choose Fee Type");
		
		document.getElementById("feeType").style.border = "1px solid #AF2C2C";
		document.getElementById("feeType").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		

		return false;

	}
	if($("#Acyearid").val()==""){
		$(".successmessagediv").hide();

		$(".errormessagediv1").show();

		$(".validateTips1").text("Select Academic Year");
		
		document.getElementById("Acyearid").style.border = "1px solid #AF2C2C";
		document.getElementById("Acyearid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		

		return false;
	}
	if($("#locationname").val()==""){
		$(".successmessagediv").hide();

		$(".errormessagediv1").show();

		$(".validateTips1").text("Select Location");
		
		document.getElementById("locationname").style.border = "1px solid #AF2C2C";
		document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		

		return false;
	}
	var status1=0;
	
	if(feeType == feeTypeId){
		status1=0;
	}else{

		datalist1={
				"feeTypeId":feeType,
				"feeType":feeType,
				"locationId":$("#locationname").val(),
				"accyear":$("#Acyearid").val()
		},
		
		
		
		$.ajax({
			

			type : "POST",

			url : "addfee.html?method=getFeeTypeCount",

			data : datalist1,
			async : false,

			success : function(data)

			{

				var result = $.parseJSON(data);

				if (result.message)

				{

					$(".successmessagediv").hide();

					$(".errormessagediv1").show();

					$(".validateTips1").text("Fee Type Already Exists");
					
					status1=1;

				}

				else

				{ 
					status1=0;

				}

			}

		
		});
		
		
	}
	
	
	
	if(status1 == 1){
		return false;
	}
	
	
	
	
	
	if (name == "" || name == null || name.trim()=="")

	{

		$(".successmessagediv").hide();

		$(".errormessagediv1").show();

		$(".validateTips1").text("Enter Fee Name");
		
		document.getElementById("feename").style.border = "1px solid #AF2C2C";
		document.getElementById("feename").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		

		return false;

	}

	if (!name.match(/^[a-zA-Z ]*$/))

	{

		$(".").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Enter Valid Fee Name");
		document.getElementById("feename").style.border = "1px solid #AF2C2C";
		document.getElementById("feename").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		

		return false;

	}

	var status = false;

	datalist = {
		"name" : name,
		"id" : id,
		"locationId":$("#locationname").val(),
		"accyear":$("#Acyearid").val()
	},

	$.ajax({

		type : "POST",

		url : "addfee.html?method=getnamecount",

		data : datalist,
		async : false,

		success : function(data)

		{

			var result = $.parseJSON(data);

			if (result.message)

			{

				$('#feename').val("");

				$(".successmessagediv").hide();

				$(".errormessagediv1").show();

				$(".validateTips1").text("Name Already Exists");

				status = false;

			}

			else

			{

				status = true;

			}

		}

	});

	return status;

}

function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document).ready(function(){
	
					loadFeeTypeList();
					if($("#hlocationId").val()!=""){
						
						$("#locationname").val($("#hlocationId").val());
					}
					if($("#haccyear").val()!=""){
						
						$("#Acyearid").val($("#haccyear").val());
					}
					var hiddenfeetype=$('#feeTypeId').val();
					if(hiddenfeetype != "" || hiddenfeetype != undefined){
						$("#feeType option[value="+ $('#feeTypeId').val() + "]").attr('selected', 'true');
					}
					
					$("#feename").keydown(function(e){
						var key=e.keyCode;
						if(!((key == 8) || ((key == 16)) || (key == 32) || (key == 46) || (key == 9) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))){
							e.preventDefault();
							$(".errormessagediv1").show();
							$(".validateTips1").text("Enter Only Text.");
							return false;
						}
						
					});
					
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv1").hide();
					}, 3000);

					var type = $('#concession').val();

					if (type == "Y") {
						$("#yes").attr("checked", true);
					} else if (type == "N") {
						$("#no").attr("checked", true);
					}

					$("#save").click(function(event)

									{
										var id = $('#feemasterid').val();
										var name = $('#feename').val();
										var description = $('#feedescription').val();
										var feeTypeId=$('#feeType').val();
										var academicYear=$('#Acyearid').val();
										var locationId=$('#locationname').val();
										

										if (myFunction())

										{

											datalist = {
													"name" : name,
													"feeTypeId" : feeTypeId,
													"description" : description,
													"id" : id,
													"academicYear":academicYear,
													"locationId":locationId,
													"concessiontype" : 'N'
											},

											$.ajax({

												type : "POST",

												url : "addfee.html?method=AddFeeDetailsMaster",

												data : datalist,

												success : function(data)

												{

													var result = $.parseJSON(data);

													if (result.jsonResponse == "Adding Record Progressing...") {
														$(
														".errormessagediv")
														.hide();
														$(
														".successmessagediv")
														.show();
														$(
														".validateTips")
														.text(
														"Adding Record Progressing...");

														setTimeout(
																function() {

																	window.location.href = "adminMenu.html?method=feeDetailsList";

																},
																2000);

													}

													if (result.jsonResponse == "Fee Details not Created Successfully") {

														$(
														".errormessagediv")
														.hide();
														$(
														".successmessagediv")
														.show();
														$(
														".validateTips")
														.text(
														"Fee Details not Created.");

														setTimeout(
																function() {

																	window.location.href = "addfee.html?method=addfeedetails";

																},
																2000);

													}
													if (result.jsonResponse == "Fee Details Updated Successfully") {
														$(
														".errormessagediv")
														.hide();
														$(
														".successmessagediv")
														.show();
														$(
														".validateTips")
														.text(
														"Updating Record Progressing...");

														setTimeout(
																function() {

																	window.location.href = "adminMenu.html?method=feeDetailsList";

																},
																2000);

													}

													if (result.jsonResponse == "Fee Details not Updated Successfully") {

														$(
														".errormessagediv")
														.hide();
														$(
														".successmessagediv")
														.show();
														$(
														".validateTips")
														.text(
														"Fee Details not Updated Successfully");

														setTimeout(
																function() {

																	window.location.href = "addfee.html?method=addfeedetails";

																},
																3000);

													}

													/*	var result = $.parseJSON(data);



																		window.location.href = "addfee.html?method=addfeedetails&result="+ result.jsonResponse;


																		if(result.jsonResponse=='')
																		{


																		 setTimeout(function(){

																			 window.location.href="adminMenu.html?method=examList";

																		 },2000);
																		}*/

												}

											});

										}

									});

					$("#view")
							.click(
									function(event)

									{

										window.location.href = "adminMenu.html?method=feeDetailsList";

									});

				});

function HideError() 
{
	
document.getElementById("feename").style.border = "1px solid #ccc";



}

function loadFeeTypeList(){

	$.ajax({
		type : 'POST',
		url : 'addfee.html?method=getFeeTypeList',
		async : false,
		success : function(response) {
			var data = $.parseJSON(response);
			$('#feeType').empty();
			$('#feeType').append('<option value="">'+ "----------Select----------" + '</option>');
			for ( var j = 0; j < data.FeeTypeList.length; j++) {

				$('#feeType').append(
						'<option value="'
						+ data.FeeTypeList[j].feeTypeId
						+ '">'
						+ data.FeeTypeList[j].feeType
						+ '</option>');
			}

		}

	});
}