function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}


$(document).ready(function() {
		$("#dialog").hide();
			setTimeout("removeMessage()", 3000);
			
			setInterval(function() {
				$(".errormessagediv").hide();}, 3000);


			$("#editdep").click(function(){

				$(".errormessagediv").hide();
				$(".errormessagediv1").hide();

				var count = 0;

				$(".select:checked").map(function() {

					 checkdep_id = $(this).attr("id");
					count++;
				});

				if (count == 0 || count > 1) {

					$(".errormessagediv").show();
					$(".validateTips").text("Select any one Department");

					return false;
				} 
				else
				{

					var id = checkdep_id;

					window.location.href = "departmentMenu.html?method=editDepartment&name=" + id;
					var result = $.parseJSON(response);
				}

			});

			$('#selectall').click(function(){
				$(".dep_Checkbox_Class").prop('checked', $(this).prop("checked"));
			});


			$("#deleteid").click(function(e){
				var count = 0;
				DepartmentDetails=[];
				$(".select:checked").map(function() {

					var checkdep_id = $(this).attr("id");
					DepartmentDetails.push(checkdep_id);
					count++;
				});
				if(count==0){
					$(".errormessagediv").show();
					$(".validateTips").text("Select atleast one Department");

					return false;

				}else{
					var depcode1 = DepartmentDetails.toString();
					var termcodeToBeDeleted = {
							"depid" : depcode1
					};
					
					deleteDialogRecord(termcodeToBeDeleted);
				}

			});
			
});			
$('#searchname').click(function() {

	var searchname = $("#searchname").val().trim();
	window.location.href = "departmentMenu.html?method=searchDepartment&searchname="+searchname;

});
$('#searchexamid').click(function() {
	
	var searchname = $("#searchname").val().trim();
	window.location.href = "departmentMenu.html?method=searchDepartment&searchname="+searchname;
	
});
$("#hidelist").click(function(){

	$("#coll1").slideToggle();

});
$('#xls').click(function() {

	var searchTerm = $("#searchexamid").val().trim();
	window.location.href = 'departmentMenu.html?method=downloadDepartmentDetailsXLS&searchTerm='+ searchTerm;
});



$('#pdfDownload').click(function() {

	var searchTerm = $("#searchexamid").val().trim();
	window.location.href = 'departmentMenu.html?method=downloadDepartmentDetailsPDF&searchTerm='+ searchTerm;
});	


function myFunction() {

	document.getElementById("myForm").submit();   
}

function deleteDialogRecord(termcodeToBeDeleted){
	$("#dialog").empty();
	$("#dialog").append("<p>Are you sure to delete?</p>");
	
	$("#dialog").dialog({	

		autoOpen: true,
		modal: true,
		title:'Department Details',
		buttons : {
			"Yes" : function() {

				$.ajax({
					type : 'POST',
					url : "departmentMenu.html?method=deleteDepartment",
					async : false,
					data : termcodeToBeDeleted,
					success : function(response) {
						var result = $.parseJSON(response);

						$('.errormessagediv').hide();


						if(result.status=="true"){

							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
							$(".validateTips").text("Delete Unmapped Department Details Progressing...");
							$(".successmessagediv").css({
								'z-index':'9999999',
							});
							setTimeout(function(){
								
								window.location.href = "adminMenu.html?method=departmentDetails";	 

							},2000);
						}

						else if(result.status=="false"){
							$('.errormessagediv').show();
							$('.validateTips').text("Selected Department is not Deleted");
							$(".errormessagediv").css({
								'z-index':'9999999'
							});
							setTimeout(function(){
							
								window.location.href = "adminMenu.html?method=departmentDetails";	 

							},2000);
						}
						else{
							$('.errormessagediv').show();
							$('.validateTips').text("Selected Department is Mapped Cannot Delete");
							$(".errormessagediv").css({
								'z-index':'9999999'
							});
							
						}


					}

				});
				$(this).dialog("close");

			},
			"No" : function() {
				$(this).dialog("close");
			}
		}


	});
}
