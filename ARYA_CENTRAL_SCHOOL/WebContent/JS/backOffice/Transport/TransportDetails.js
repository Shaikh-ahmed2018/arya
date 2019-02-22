function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction() {

	document.getElementById("myForm").submit();   
}

$(document).ready(function() {	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='3'>NO Records Found</td></tr>");
	}
	$("#selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));	 
	});

	setTimeout("removeMessage()", 2000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 2000);


	$("#edit").click(function(){	
		
		$(".successmessagediv").hide();

		var cnt = 0;

		$('input[type="checkbox"]:checked').map(function() {											
			getData = $(this).attr("id");	
			cnt++;
		});

		if (cnt == 0 || cnt > 1) {							
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one Transport Type");
			$('.errormessagediv').delay(3000).slideUp();

			return false;
		} 					
		else
		{
			var vehicleid = getData;
			window.location.href = "transport.html?method=edittransporttype&vehicleid="+vehicleid;
		}
	});

	$("#delete").click(function(){
		var count =0;
		vehicleid=[];	

		$(".select:checked").each(function(){
			var list=$(this).attr("id");
			vehicleid.push(list);
			count ++;
		});	

		if(count == 0){
			$('.errormessagediv').show();
			$('.validateTips').text("Select Transport Type to Delete");
			$('.errormessagediv').delay(3000).slideUp();
		}
		else {
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are you sure to delete?</p>");
		}
	});

	$("#dialog").dialog({
		autoOpen: false,
		modal: true,					    
		title:'Vehicle Type',
		buttons : {
			"Yes" : function() {
				
				$.ajax({
					type : 'POST',
					url : "transport.html?method=deleteVehicleType",
					data : {"vehicleid":vehicleid.toString()},
					cache: false,
					
					success : function(response) {
						var result = $.parseJSON(response);

						$('.errormessagediv').hide();

						if (result.status == "Vehicle Deleted SuccessFully") {

							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
							$(".validateTips").text("Delete Unmapped Transport Type Progressing...");
							$('.successmessagediv').delay(3000).slideUp();

						} 
						else if(result.status == "Vehicle not Deleted SuccessFully"){
							$(".errormessagediv").show();
							$(".validateTips").text("Selected Transport Type is Not Deleted");
							$('.errormessagediv').delay(3000).slideUp();
						}
						else{
							$(".errormessagediv").show();

							$(".validateTips").text("Selected Transport Type is Mapped Cannot Delete");
							$('.errormessagediv').delay(3000).slideUp();
						}
						setTimeout(function(){
							window.location.href="adminMenu.html?method=transportCategory";
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

	$("#searchname").keydown(function(event) {
		var searchText = $("#searchname").val();
		if (event.keyCode == 13) {
			window.location.href = "adminMenu.html?method=transportCategory&searchText="+ searchText;
		}
	});

	$("#search").click(function(){
		var searchText = $("#searchname").val().trim();
		window.location.href ="adminMenu.html?method=transportCategory&searchText="+ searchText;
	});

});				


