function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction1() {
    
    document.getElementById("myForm").submit();   
 }



$(document).ready(function() {
	$("#Acyearlist").val($("#haccyearstage").val());
	$("#Acyearid").val($("#hacademicyaer").val());
					if($("#allstudent tbody tr").length ==0){
						$("#allstudent tbody").append("<tr><td colspan='4'>NO Records Found</td></tr>");
					}
					
					$("#selectall").change(function(){

						 $(".select").prop('checked', $(this).prop("checked"));
						 
						 
						});

					
					$("#stage").click(function(){
						$("#stageclose").slideToggle();
					});
					
					
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
					
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
					
					
					/*$("#allstudent tbody tr td:nth-child(3)").each(function(){
						var value=parseInt($(this).text());
						var digit=value.toFixed(2);
						$(this).text(digit);
					});*/
					
					$('#view')
							.click(
									function()

									{

										window.location.href = "adminMenu.html?method=StageList";

									});

					$('#savestage')
					.click(
							function()

							{
								var val = parseFloat($('#amount').val());
								var id = $('#stageid').val();

								var name = $('#stagename').val();
								var amount = $('#amount').val();


								var description = $('#description')
								.val();

								

								if (name == "" || name == null)

								{

									$(".errormessagediv").show();

									$(".validateTips").text("Field Required Stage Name");


									document.getElementById("stagename").style.border = "1px solid #AF2C2C";
									document.getElementById("stagename").style.backgroundColor = "#FFF7F7";
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);

									return false;
								}
								else if (name.trim().length <= 0)

								{

									$(".errormessagediv").show();

									$(".validateTips").text("Field Required Stage Name");


									document.getElementById("stagename").style.border = "1px solid #AF2C2C";
									document.getElementById("stagename").style.backgroundColor = "#FFF7F7";
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);

									return false;
								}
								else if

								(amount == "" || amount == null)

								{

									$(".errormessagediv").show();

									$(".validateTips").text("Field Required Amount Value");


									document.getElementById("amount").style.border = "1px solid #AF2C2C";
									document.getElementById("amount").style.backgroundColor = "#FFF7F7";
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);

									return false;
								}

								else if (!amount.match("^[0-9]+$")) {
									$('.errormessagediv').show();
									$('.validateTips')
									.text(
									"Amount should be Numbers");

									document.getElementById("amount").style.border = "1px solid #AF2C2C";
									document.getElementById("amount").style.backgroundColor = "#FFF7F7";
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);

									return false;
								}else if (isNaN(val) || (val === 0)) {
									
									$('.errormessagediv').show();
									$('.validateTips').text("Please Enter Correct value");
									document.getElementById("amount").style.border = "1px solid #AF2C2C";
									document.getElementById("amount").style.backgroundColor = "#FFF7F7";
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);

									return false;
								}
								
								
								datalist = {
										"name" : name,
										"amount" : amount,
										"description" : description,
										"id" : id,
										"accyear":$("#Acyearid").val(),
								},

								$.ajax({

									type : "POST",

									url : "adminMenu.html?method=savestage",

									data : datalist,

									success : function(data)

									{

										var result = $.parseJSON(data);
										if(result.jsonResponse=="Stage Added Successfully"){

											$(".errormessagediv").hide();
											$(".successmessagediv").show();
											$(".validateTips").text("Adding Record Progressing...");

											setTimeout(function(){

												window.location.href = "adminMenu.html?method=StageList&result="+ result.jsonResponse;

											},2000);
										}


										if(result.jsonResponse=="Stage Update Successfully"){

											$(".errormessagediv").hide();
											$(".successmessagediv").show();
											$(".validateTips").text("updating Record Progressing...");

											setTimeout(function(){

												window.location.href = "adminMenu.html?method=StageList&result="+ result.jsonResponse;

											},2000);
										}



									}

								});



							});

				
					// for edit 
					
					$('#editStageId')
					.click(
							function() {
								
								
								var cnt = 0;

								
								$('input[type="checkbox"]:checked').map(
												function() {
													
													getData = $(this).attr("id");
													
													cnt++;
												});
								
								
													
								if (cnt == 0 || cnt > 1) {
									
				     				$(".errormessagediv").show();
				     				$(".validateTips").text("Select any one Stage to Edit");
				     				
				     				return false;
				     			}  else {
									
									var stageCode = getData;
									window.location.href = "addstage.html?method=Edit&stageCode="+stageCode.trim();
								  	
									
								}
							});


					
					
					

					// to delete
					
					
					$('#delete').click(
							function() {

								var count =0;
								 stageCode=[];
								$(".select:checked").each(function(){

									var list=$(this).attr("id");
									stageCode.push(list);
									count ++;
								});	
								
								if(count == 0)	{
							 		
							 		$('.errormessagediv').show();
									$('.validateTips').text("Select Stage to Delete");
									$('.errormessagediv').delay(3000).slideUp();
							 		
							 	}
							 	else 	{
									
									  $("#dialog").dialog("open");
									  $("#dialog").empty();
									  $("#dialog").append("<p>Are you sure to delete?</p>");
								}
								
								//$(".select").prop("checked",false);
								
							});

					$("#search")
							.click(
									function()

									{
										var searchvalue = $('#searchvalue')
												.val();

										window.location.href = "adminMenu.html?method=StageList&searchvalue="
												+ searchvalue;

									});
					
					
					$("#dialog").dialog({
				 		
				 		 autoOpen: false,
					     modal: true,					    
					     title:'Stage Details',
					     buttons : {
					    	 "Yes" : function() {
									$.ajax({
										type : "GET",
										url : "addstage.html?method=Delete",
										data :{
											"stageCode" :stageCode.toString()},

										success : function(response) {
											var result = $.parseJSON(response);
											$('.errormessagediv').hide();
											
											if(result.jsonResponse=="Stage Details Deleted Successfully"){
												
												$(".successmessagediv").show();
												$(".validateTips").text("Deleting Record Progressing...");
											}
											
											else if(result.jsonResponse=="Stage Details Already Mapped"){
												$('.errormessagediv').show();
												$('.validateTips').text("Selected Stage is Mapped cannot Delete");
											}
											

											setTimeout(function(){
												   
												 window.location.href="adminMenu.html?method=StageList";
											 
											 },2000);
										
										}


									});
									
									
							 		 $(this).dialog("close");
								 	  
					          },
				 		
					          "No" : function() {
						            $(this).dialog("close");
						          }
				 		
					     }
				 	});
					

					$("#xls")
							.click(
									function()

									{
										var searchTerm = $("#searchexamid").val().trim();
										window.location.href = "addstage.html?method=downloadStageDetailsXLS&searchTerm="+ searchTerm;

									});
					
					
					
					
					$("#pdfDownload")
					.click(
							function()

							{
								var searchTerm = $("#searchexamid").val().trim();
								window.location.href = "addstage.html?method=downloadStageDetailsPDF&searchTerm="+ searchTerm;

							});
					
				});
			



function HideError() 
{
	

document.getElementById("stagename").style.border = "1px solid #ccc";
document.getElementById("stagename").style.backgroundColor = "#fff";


}





