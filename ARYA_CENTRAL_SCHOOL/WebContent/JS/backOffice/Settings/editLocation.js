function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction() {

	document.getElementById("myForm").submit();   
}

$(document).ready(function() {

	$("#selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});

	setTimeout("removeMessage()", 2000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 2000);


	$("#editstream").click(function(){
		$(".successmessagediv").hide();
		var cnt = 0;

		$('input[type="checkbox"]:checked').map(function() {
			getData = $(this).attr("id");
			cnt++;
		});

		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one Location");
			return false;
		} 
		else
		{
			var locid = getData;
			window.location.href = "locationDetails.html?method=editLocation&locid="+locid;
		}
	});

	$("#delete").click(function(){
		var count =0;
		studentIdlist=[];
		$(".select:checked").each(function(){

			var list=$(this).attr("id");
			studentIdlist.push(list);
			count ++;
		});	

		if(count == 0)	{
			$('.errormessagediv').show();
			$('.validateTips').text("Select Location to Delete");
			$('.errormessagediv').delay(3000).slideUp();
		}
		else{
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are you sure to delete?</p>");
		}
	});


	$("#searchname").keydown(function(event) {
		var searchText = $("#searchname").val();
		if (event.keyCode == 13) {
			window.location.href = "adminMenu.html?method=locationList&searchText="
				+ searchText;
		}

	});

	$("#search").click(function(){
		var searchText = $("#searchname").val().trim();
		window.location.href ="adminMenu.html?method=locationList&searchText="+ searchText;
	});


	$("#dialog").dialog({
		autoOpen: false,
		modal: true,					    
		title:'Location Details',
		buttons : {
			"Yes" : function() {

				$.ajax({
					type : 'POST',
					url : "locationDetails.html?method=deleteLocation",
					data : {"locid":studentIdlist},
					success : function(
							response) {
						var result = $
						.parseJSON(response);

						$('.errormessagediv').hide();

						if (result.status == "true") {

							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
							$(".validateTips").text("Delete Unmapped Location Details Progressing...");
							$('.successmessagediv').delay(3000).slideUp();

						} else if(result.status == "false"){
							$(".errormessagediv").show();
							$(".validateTips").text("Selected Location is Not Deleted");
							$('.errormessagediv').delay(3000).slideUp();
						}
						else{
							$(".errormessagediv").show();

							$(".validateTips").text("Selected Location is Mapped Cannot Delete");
							$('.errormessagediv').delay(3000).slideUp();
						}
						setTimeout(function(){
							window.location.href="adminMenu.html?method=locationList";
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

	$('#excelDownload').click(function() {
		var searchTerm = $("#searchexamid").val().trim();
		window.location.href = 'locationDetails.html?method=downloadLocationDetailsXL&searchTerm='+ searchTerm;
	});

	$("#pdfDownload").click(function(){
		var searchTerm = $("#searchexamid").val().trim();
		window.location.href = 'locationDetails.html?method=downloadLocationDetailsPDF&searchTerm='+ searchTerm;

	});
});				


