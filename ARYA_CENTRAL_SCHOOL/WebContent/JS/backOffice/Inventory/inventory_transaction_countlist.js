function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
$(document)
		.ready(
				function() {

					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);

								

					$('#excelDownload')
							.click(
									function() {

										var searchTerm = $("#searchterm").val().trim();
										

										window.location.href = "adminMenu.html?method=InventoryListExcelDownload&searchTerm="
												+ searchTerm;

									});
					$("#pdfDownload")
							.click(
									function() {

										var searchTerm = $("#searchterm").val().trim();
										

										window.location.href = "adminMenu.html?method=InventoryListPDFDownload&searchTerm="
												+ searchTerm;

									});

					$("#search")
							.click(
									function() {

										var searchTerm = $("#searchterm").val().trim();
										

										window.location.href = "adminMenu.html?method=InventoryList&searchTerm="
												+ searchTerm;

									});

				});
