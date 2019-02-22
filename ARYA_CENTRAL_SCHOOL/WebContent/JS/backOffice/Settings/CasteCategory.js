function removeMessage() {
	$(".successmessagediv").hide();
	$(".successmessagediv").hide();
}

function myFunction() {
	document.getElementById("myForm").submit();   
}


$(document).ready(function() {

	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);
	
	
	if($("#allstudent tbody tr").length==0){
		$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
	}


	$("#editReligion").click(function(){

		$(".successmessagediv").hide();

		var cnt = 0;

		$('input[name="select"]:checked').map(function() {
			getData = $(this).attr("id");

			cnt++;
		});



		if (cnt == 0 || cnt > 1) {

			$(".errormessagediv").show();
			$(".validateTips").text("Select any one Caste Category");

			return false;
		} 
		else
		{

			var religionId = getData;

			window.location.href = "religionCastCasteCategory.html?method=editCasteCategory&religionId="+ religionId;
			var result = $.parseJSON(response);
		}

	});

	$("#selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});


	$("#delete").click(function(){

		var cnt = 0;
		casteCategoryList=[];
		$('input[name="select"]:checked').each(function() {
			getData = $(this).attr("id");
			casteCategoryList.push(getData);
			cnt++;
		});

		if (cnt == 0) {

			$(".errormessagediv").show();
			$(".validateTips").text("Select atleast one Caste Category");

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
		title:'Caste Category Details',
		buttons : {
			"Yes" : function() {

				$.ajax({
					type : 'POST',
					url : "religionCastCasteCategory.html?method=deleteCasteCategory",
					data : {'casteCategoryList':casteCategoryList},
					success : function(
							response) {
						var result = $
						.parseJSON(response);


						$('.errormessagediv').hide();

						if(result.status=="Selected Caste Category Deleted"){

							$(".successmessagediv").show();

							$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
							$(".validateTips").text("Delete Unmapped Caste Catagory Progressing...");
							$(".successmessagediv").css({
								'z-index':'9999999'
							});
						}

						else if(result.status=="Selected Caste Category Not Deleted"){
							$('.successmessagediv').show();
							$('.validateTips').text("Failed to delete Caste Catagory");
							$(".successmessagediv").css({
								'z-index':'9999999'
							});
						}

						setTimeout(function(){
							$(".select").prop('unchecked');
							window.location.href = "adminMenu.html?method=casteCategoryDetails";

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
		
					
});				