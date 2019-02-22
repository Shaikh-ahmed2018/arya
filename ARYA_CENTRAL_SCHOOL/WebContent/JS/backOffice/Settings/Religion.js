function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}


$(document)
.ready(
		function() {
          
			if($("#allstudent tbody tr").length==0){
				$("#allstudent tbody").append("<tr><td ColSpan='2'>No Records Found</td></tr>");
			}

			setTimeout("removeMessage()", 3000);
			setInterval(function() {
				$(".errormessagediv").hide();
			}, 3000);


			$("#editReligion").click(function(){

				$(".successmessagediv").hide();

				var cnt = 0;

				$('input[name="select"]:checked').map(function() {
					getData = $(this).attr("id");

					cnt++;
				});



				if (cnt == 0 || cnt > 1) {

					$(".errormessagediv").show();
					$(".validateTips").text("Select any one Religion");

					return false;
				} 
				else
				{

					var religionId = getData;

					window.location.href = "religionCastCasteCategory.html?method=editReligion&religionId="+ religionId;
					var result = $.parseJSON(response);
				}

			});

			$('#selectall').click(function(){
				$(".select").prop('checked', $(this).prop("checked"));
			});


			$("#delete").click(function(e){
				var cnt=0;
				religionList=[];
				$('input[name="select"]:checked').each(function() {

					var getData = $(this).attr("id");

					religionList.push(getData);
					cnt++;
				});

				if(cnt==0){
					$(".errormessagediv").show();
					$(".validateTips").text("Select atleast one Religion");

					return false;

				}else{

					$("#dialog").dialog("open");
					$("#dialog").empty();
					$("#dialog").append("<p>Are you sure to delete?</p>");
				}

			});
			$("#dialog").dialog({	

				autoOpen: false,
				modal: true,
				title:'Religion Details',
				buttons : {
					"Yes" : function() {

						$.ajax({
							type : 'POST',
							url : "religionCastCasteCategory.html?method=deleteReligion",
							data : {'religionList':religionList},
							success : function(
									response) {
								var result = $
								.parseJSON(response);


								$('.errormessagediv').hide();

								if(result.status=="Selected Religion Deleted"){

									$(".successmessagediv").show();
									$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
									$(".validateTips").text("Delete Unmapped Religion Progressing...");

									$(".successmessagediv").css({
										'z-index':'9999999',
									});
								}

								else if(result.status=="Selected Religion Not Deleted"){
									$('.successmessagediv').show();
									$('.validateTips').text("Selected Religion is not Deleted");
									$(".successmessagediv").css({
										'z-index':'9999999'
									});
								}

								setTimeout(function(){
									$(".select").prop('unchecked');  
									window.location.href = "adminMenu.html?method=religionDetails";	 

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








			/*			
					$('#excelDownload')
					.click(
							function() {


								var searchTerm = $("#searchexamid").val().trim();



								window.location.href = 'streamDetails.html?method=downloadStreamDetailsXLS&searchTerm='+ searchTerm;

							});


					$("#pdfDownload").click(function(){


						var searchTerm = $("#searchexamid").val().trim();

            			window.location.href = 'streamDetails.html?method=downloadStreamDetailsPDF&searchTerm='+ searchTerm;

            		});*/




		});			

function myFunction() {

	document.getElementById("myForm").submit();   
}
