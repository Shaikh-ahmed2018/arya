$(document)
.ready(
		function() {

			$("#selectall").change(function(){
				$(".Checkbox_Class").prop("checked",$(this).prop("checked"));
			});

			$('.errormessagediv1').hide();
			$('.successmessagediv').hide();

			if ($('#jobid').val() == null || $('#jobid').val() == "") {
				$("#statusid").hide();
			} else {
				$("#statusid").show();
				$("#description").val($("#descriptionId").val());

				$("#status option[value='"+ $('#staid').val().trim() + "']").attr('selected', 'true');
			}

			$("#editDesignationId").click(function() {
				var cnt = 0;
				$('input.Checkbox_Class:checkbox:checked').map(function() {

					var term_id = $(this).attr("id");
					var split_id = term_id.split('_');
					getData = split_id[1].split(',');

					cnt++;
				});
				if (cnt == 0 || cnt > 1) {
					$('.errormessagediv1').show();
					$('.validateTips1').text("Select Any One Job");
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 1500);
					return false;
				} else {

					window.location.href = "careerview.html?method=careerView&Code="
						+ getData[0];

				}

			});

			$("#save").click(function() {

				var title = $('#title').val().trim();
				var qualification = $('#qualification').val().trim();
				var noofposition = $('#noofposition').val().trim();
				var experience = $('#experience').val().trim();
				var description = $('#description').val().trim();
				var jobid = $('#jobid').val().trim();
				var status = $('#status').val().trim();

				if (title == "" || title == null) {
					$('.errormessagediv1').show();
					$('.validateTips1').text("Enter Title");
					document.getElementById("title").style.border = "1px solid #AF2C2C";											document
					document.getElementById("title").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 3000);
					return false;



				}

				else if (qualification == ""|| qualification == null) {
					$('.errormessagediv1').show();
					$('.validateTips1').text("Enter Qualification");

					document.getElementById("qualification").style.border = "1px solid #AF2C2C";											document
					document.getElementById("qualification").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 3000);
					return false;
				} else if (!qualification.match("^[a-zA-Z-\\s]+$")) {
					$('.errormessagediv1').show();
					$('.validateTips1').text("Qualification should be Alphabets");
					document.getElementById("qualification").style.border = "1px solid #AF2C2C";											document
					document.getElementById("qualification").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 3000);
					return false;
				} else if (experience == ""|| experience == null) {
					$('.errormessagediv1').show();
					$('.validateTips1').text("Enter Experience");
					document.getElementById("experience").style.border = "1px solid #AF2C2C";											document
					document.getElementById("experience").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 3000);
					return false;
				} else if (noofposition == ""
					|| noofposition == null) {
					$('.errormessagediv1').show();
					$('.validateTips1').text("Enter No Of Position");
					document.getElementById("noofposition").style.border = "1px solid #AF2C2C";											document
					document.getElementById("noofposition").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 3000);
					return false;
				} else if (!noofposition.match("^[0-9]+$")) {
					$('.errormessagediv1').show();
					$('.validateTips1').text("Please Enter the Numbers");

					document.getElementById("noofposition").style.border = "1px solid #AF2C2C";											document
					document.getElementById("noofposition").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 3000);
					return false;
				} else {
					if (jobid == "" || jobid == null) {
						jobid = "NULL";
					}

					var Check = {
							"title" : title,
							"qualification" : qualification,
							"experience" : experience,
							"description" : description,
							"noofposition" : noofposition,
							"jobid" : jobid,
							"status" : status
					};
					$.ajax({
						type : "POST",
						url : "careerview.html?method=addJobs",
						data : Check,
						async : false,
						success : function(data) {
							var result = $
							.parseJSON(data);
							if (result.status == "success") {

								$('.errormessagediv1').hide();

								$('.successmessagediv').show();
								var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("?")+1);
								if(pageUrl.split("&")[0]=="method=careerView"){
									$('.successmessage').text("Update Job Details Progressing...");
								}
								else{
									$('.successmessage').text("Add Job Details Progressing...");
								}


								setTimeout(function() {

									window.location.href = "adminMenu.html?method=careerupdate";

								}, 3000);
							} else if (result.status == "duplicate") {

								$('.errormessagediv1').show();
								$('.validateTips1').text("Job already mapped can't Deactivated");
								setTimeout(function() {
									window.location.reload();
								}, 3000);

							} else {
								$('.errormessagediv1').show();
								$('.validateTips1').text("Job Insertion failed");
								setTimeout(function() {
									window.location.href = "adminMenu.html?method=careerupdate";
								}, 3000);
							}
						}
					});
				}
			});

			$("#list").click(function() {
				window.location.href = "adminMenu.html?method=careerupdate";
			});

			$("#searchname").keydown(function(event) {
				var searchText = $("#searchname").val();
				if (event.keyCode == 13) {
					window.location.href = "adminMenu.html?method=careerupdate&searchText="
						+ searchText;
				} 

			});

			$("#search").click(function(){
				var searchText = $("#searchname").val().trim();
				window.location.href ="adminMenu.html?method=careerupdate&searchText="
					+ searchText;
			});


			$("#plus").click(function() {
				window.location.href = "adminMenu.html?method=addJob";
			});

			$('#excelDownload').click(function() {

				var searchTerm = $("#searchexamid").val().trim();

				window.location.href = 'careerview.html?method=InternaljobXLS&searchTerm='
					+ searchTerm;

			});

			$("#pdfDownload").click(function() {
				var searchTerm = $("#searchexamid")
				.val().trim();

				window.location.href = "careerview.html?method=InternaljobPDFReport&searchTerm="
					+ searchTerm;
			});

			$("#trash").click(function() {

				var cnt = 0;
				$('input.Checkbox_Class:checkbox:checked').map(function() {
					statusss=$(this).val();
					var term_id = $(this).attr("id");
					var split_id = term_id.split('_');
					getData = split_id[1].split(',');

					cnt++;
				});
				if (cnt == 0 || cnt > 1) {
					$('.errormessagediv1').show();
					$('.validateTips1').text("Select Any One Job");
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 1500);
					return false;
				}else if(statusss=="deactive"){
					$('.errormessagediv1').show();
					$('.validateTips1').text("Already Deactivated.");
					$('.errormessagediv1').delay(3000).fadeOut("slow");
				}else {

					var Check = {
							"Code" : getData[0]
					};
					$.ajax({
						type : "POST",
						url : "careerview.html?method=careerDelete",
						data : Check,
						async : false,
						success : function(data) {
							var result = $.parseJSON(data);
							if (result.status == true) {
								$('.successmessagediv').show();
								$('.successmessage').text("Job Deactivation Progressing...");
								setTimeout(function() {

									location.reload();

								}, 3000);
							} else {
								$('.errormessagediv1').show();
								$('.validateTips1').text("Job already mapped can't Deactivated");

								setTimeout(function() {

									location.reload();

								}, 3000);

							}

						}
					});

				}

			});

			$(".headClass").click(function(){
				$("#collapsable").slideToggle();
			});

		});

function HideError() 
{

	document.getElementById("title").style.border = "1px solid #ccc";
	document.getElementById("title").style.backgroundColor = "#fff";
	document.getElementById("qualification").style.border = "1px solid #ccc";
	document.getElementById("qualification").style.backgroundColor = "#fff";
	document.getElementById("noofposition").style.border = "1px solid #ccc";
	document.getElementById("noofposition").style.backgroundColor = "#fff";
	document.getElementById("experience").style.border = "1px solid #ccc";
	document.getElementById("experience").style.backgroundColor = "#fff";


}
function myFunction() {

	document.getElementById("myForm").submit();   
}
