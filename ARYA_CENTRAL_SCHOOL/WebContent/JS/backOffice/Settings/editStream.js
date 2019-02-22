$(document)
.ready(
		function() {

			$("#searchname")
			.keypress(
					function(e) {
						if (e.keyCode == 13) {
							window.location.href = "adminMenu.html?method=streamList&searchname="
								+ $("#searchname").val()
								.trim()
								+ "&school="
								+ $("#school").val().trim();
						}
					});

			checkboxSelection();

			setTimeout("removeMessage()", 3000);
			setInterval(function() {
				$(".errormessagediv").hide();
			}, 3000);

			$("#locationname").change(function() {
				$("#searchvalue").val("");
				changeAccYear();
			});

			$("#editstream")
			.click(
					function() {
						$(".successmessagediv").hide();
						var cnt = 0;
						$('input[type="checkbox"]:checked')
						.map(
								function() {
									check_id = $(this)
									.attr("id");
									cnt++;
								});

						if (cnt == 0 || cnt > 1) {
							$(".errormessagediv").show();
							$(".validateTips").text(
							"Select any one Stream");
							return false;
						} else {
							var streamid = check_id;
							window.location.href = "streamDetails.html?method=editStreamDetailsAction&streamid="
								+ streamid;
						}

					});

			$("#delete").click(
					function() {
						var depcode = [];
						var count = 0;
						streamIdlist = [];
						$(".select:checked").each(function() {
							var list = $(this).attr("id");
							streamIdlist.push(list);
							count++;
						});

						if (count == 0) {
							$(".errormessagediv").show();
							$(".validateTips").text(
							"Select Stream to Delete");
							return false;

						} else {
							$("#dialog").dialog("open");
							$("#dialog").empty();
							$("#dialog").append(
							"<p>Are you sure to delete?</p>")
						}

					});

			$("#dialog")
			.dialog(
					{
						autoOpen : false,
						modal : true,
						title : 'Stream Details',
						buttons : {
							"Yes" : function() {

								var termcodeToBeDeleted = {
										"streamid" : streamIdlist
								};
								$
								.ajax({
									type : 'POST',
									url : "streamDetails.html?method=deleteStreamDetailsAction",
									data : termcodeToBeDeleted,
									success : function(
											response) {
										var result = $
										.parseJSON(response);

										$(
												'.errormessagediv')
												.hide();

										if (result.status == "Delete Stream Progeressing") {

											$(
											".successmessagediv")
											.show();
											$(
											".successmessagediv")
											.attr(
													"style",
											"width:150%;margin-left:-280px;");
											$(
											".validateTips")
											.text(
											"Delete Unmapped Stream Details Progressing...");
											$(
											'.successmessagediv')
											.delay(
													3000)
													.slideUp();
										} else if (result.status == "Stream cannot be removed") {
											$(
											'.errormessagediv')
											.show();
											$(
											'.validateTips')
											.text(
											"Selected Stream is Not Deleted");
										} else {
											$(
													".errormessagediv")
													.show();
											$(
											".validateTips")
											.text(
											"Selected Stream is Mapped Cannot Delete");
											$(
											'.errormessagediv')
											.delay(
													3000)
													.slideUp();
										}
										setTimeout(
												function() {
													window.location.href = "adminMenu.html?method=streamList"
												}, 3000);
									}
								});
								$(this).dialog("close");
							},
							"No" : function() {
								$(this).dialog("close");
							}
						}
					});

			$('#excelDownload')
			.click(
					function() {
						var searchTerm = $("#searchexamid")
						.val().trim();
						window.location.href = 'streamDetails.html?method=downloadStreamDetailsXLS&searchTerm='
							+ searchTerm;
					});

			$("#pdfDownload")
			.click(
					function() {
						var searchTerm = $("#searchexamid")
						.val().trim();
						window.location.href = 'streamDetails.html?method=downloadStreamDetailsPDF&searchTerm='
							+ searchTerm;
					});

		});

function changeAccYear() {
	var locationId = $("#locationname").val();

	$
	.ajax({
		type : 'POST',
		url : "adminMenu.html?method=searchByLocationOnly",
		data : {
			"locationId" : locationId,
		},
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			$("#selectall").prop('checked',false);
			var len=result.SearchList.length;
			var i=0;
			if(len>0){
			for ( i = 0; i < len; i++) {
				$("#allstudent tbody")
				.append(
						"<tr>"
						+ "<td><input type='checkbox' name='select' class='select' id='"
						+ result.SearchList[i].streamId
						+ "'</td>"
						+ "<td> "
						+ result.SearchList[i].locationName
						+ " </td>"
						+ "<td> "
						+ result.SearchList[i].streamName
						+ "</td>"
						+ "<td> "
						+ result.SearchList[i].description
						+ " </td>" + "</tr>");
			  }
		   }
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
			}
			
			checkboxSelection();
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
   		 }
	});
}
function checkboxSelection(){
	$("#selectall").change(function() {
		$(".select").prop('checked', $(this).prop("checked"));

	});
	$(".select").change(function(){
		if($(".select:checked").length==$(".select").length){
			$("#selectall").prop('checked',true);
		}
		else{
			$("#selectall").prop('checked',false);
		}
	});
}
function removeMessage() {
	$(".successmessagediv").hide();
}

function myFunction() {

	window.location.href = "adminMenu.html?method=streamList&searchname="
		+ $("#searchname").val().trim() + "&school="
		+ $("#school").val().trim();
}