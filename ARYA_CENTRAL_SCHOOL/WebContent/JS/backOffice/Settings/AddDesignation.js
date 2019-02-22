



function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction()

{

	var id = $('#designationid').val();

	var name = $('#designation').val();

	if (name == "" || name == null)

	{

		$(".errormessagediv1").show();

		$(".validateTips1").text("Enter Designation Name");

		document.getElementById("designation").style.border = "1px solid #AF2C2C";
		document.getElementById("designation").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);

		return false;

	}

	if (name.match(/^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$/i)==null)

	{

		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Enter Valid Designation Name");

		return false;

	}


	if (name.match(/^[0-9\s]+$/i))

	{

		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Only Numbers Not Allowed in Designation Name");

		return false;

	}

	var status = false;

	datalist = {
			"name" : name,
			"id" : id
	},

	$.ajax({

		type : "POST",

		url : "adddesignation.html?method=getnamecount",

		data : datalist,

		async : false,

		success : function(data)

		{

			var result = $.parseJSON(data);

			if (result.message)

			{

				$('#designation').val("");

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

$(document).ready(function(){
	$("#selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	}
	);

	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);

	$('#view')
	.click(
			function()

			{

				window.location.href = "adminMenu.html?method=designationList";

			});

	$('#submit')
	.click(
			function()

			{

				var id = $('#designationid').val();

				var name = $('#designation').val();

				var description = $('#description')
				.val();

				if (myFunction())

				{

					datalist = {
							"name" : name,
							"description" : description,
							"id" : id
					},

					$
					.ajax({

						type : "POST",

						url : "adminMenu.html?method=submit",

						data : datalist,

						success : function(
								data)

								{
							var result = $.parseJSON(data);


							$('.errormessagediv').hide();

							if (result.status == "Designation Added Successfully") {

								$(".errormessagediv1").hide();
								$(".successmessagediv").show();
								/*$(".successmessagediv").attr("style","width:150%;margin-left:350px;");*/
								$(".successmessage").text("Add Designation Progressing...");


							}
							else if (result.status == "Designation Update Successfully") {

								$(".errormessagediv1").hide();
								$(".successmessagediv").show();
								$(".successmessage").text("Update Designation Progressing...");

							}
							else {
								$(".errormessagediv1").show();
								$(".validateTips1").text(result.status);
							}

							setTimeout(function(){

								window.location.href = "adminMenu.html?method=designationList";


							},2000);

								}

					});

				}

			});

	// edit function

	$('#editDesignationId')
	.click(
			function()

			{
				var cnt = 0;

				$('input[type="checkbox"]:checked')
				.map(
						function() {

							/*var term_id = $(this).attr("id");*/
							getData = $(this).attr("id");
							/*var split_id = term_id
																	.split('_');
															getData = split_id[1]
																	.split(',');*/

							cnt++;
						});

				if (cnt == 0 || cnt > 1) {

					$(".errormessagediv").show();
					$(".validateTips").text("Select Any One Designation");

				}

				else {

					var name = getData;

					window.location.href = "adddesignation.html?method=Edit&name="
						+ name;

				}

			});

	// to delete

	var designation_list=[];

	$("#deleteDesignationId").click(function(){

		var count = 0;

		var event_Code = null;

		$(".select:checked").each(function(){

			var list=$(this).attr("id");
			designation_list.push(list);
			count ++;


		});

		if(count == 0)	{

			$('.errormessagediv').show();
			$('.validateTips').text("Select Designation to Delete");
			$('.errormessagediv').delay(2000).slideUp();

		}
		else 	{

			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are you sure to delete?</p>");
		}


	});

	$("#dialog").dialog({



		autoOpen: false,
		modal: true,
		title:'Delete Designation Details',
		buttons : {
			"Yes" : function() {

				$.ajax({
					type : 'POST',
					url : "adddesignation.html?method=Delete",
					data : {"designation_list":designation_list.toString()},
					success : function(
							response) {
						var result = $
						.parseJSON(response);


						$('.errormessagediv').hide();

						if (result.status == "UnMapped Designation Details Deleted Successfully") {

							$(".errormessagediv").hide();
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:60%;margin-left:310px;");
							$(".successmessage").text("Delete UnMapped Designation Details Progressing...");

						} else {
							$(".errormessagediv").show();
							$(".validateTips").text(result.status);
						}

						setTimeout(function(){

							window.location.href="adminMenu.html?method=designationList";

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








	$("#search")
	.click(
			function()

			{
				var searchvalue = $('#searchname').val();


				window.location.href = "adminMenu.html?method=designationList&searchvalue="
					+ searchvalue;


			});

	$("#xlss")
	.click(
			function()

			{

				window.location.href = "adddesignation.html?method=downloadDesignationDetailsXLS";

			});




	$("#pdfDownload")
	.click(
			function()

			{

				window.location.href = "adddesignation.html?method=downloadDesignationDetailsPDF";

			});

});

function HideError() 
{

	document.getElementById("designation").style.border = "1px solid #ccc";
	document.getElementById("designation").style.backgroundColor = "#fff";

}