$(document).ready(function() {
	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='8'>NO Records Found</td></tr>");
	}
	
	$("h4.panel-title").click(function(){
		
		$("#collapseOne").toggleClass("in");
	});
	
	var projectCode = [];
	
	$("#selectAll").change(function(){
		
		$(".Checkbox_Class").prop("checked",$(this).prop("checked"));
	});
	
	$(".Checkbox_Class").change(function(){
		
		if($(".Checkbox_Class:checked").length==$(".Checkbox_Class").length){
			
			$("#selectAll").prop("checked",true);
		}
		else{
			$("#selectAll").prop("checked",false);
		}
	});
	
	$('#edit').click(function() {
		var count = 0;
		var projectId = null;

		$('input[name="selectBox"]:checked').each(function() {
			count = count + 1;
			projectId = this.id.trim();
		});

		if (count > 1 || count == 0) {
			$('.errormessagediv').show();
			$('.validateTips').text("Select any one record");

		} else {
			window.location.href = "assignmentupload.html?method=editProject&projectId="
				+ projectId;

		}

	});

	$('#projectList').on('focus', '.actualcompleteDate', function() {
		$(this).datepicker({
			dateFormat : "dd-mm-yy",
		});
		$('div#ui-datepicker-div').css({
			'font-size' : '15px'
		});
	});

	$("#saveid").click(function(){

		var projectId=$("#projectId").val();
		var maxmarks=$("#maxmarks").text();

		var studentIdArray=[];
		var actualcompletionArray=[];
		var aquiredmarksArray=[];
		var remarksArray=[];

		$('#projectList tbody tr').each(function(){

			var studentId=$(this).find('.hiddenclass').text();
			var actualDate=$(this).find('.actualcompleteDate').val();
			var aquiredmarks=$(this).find('.acquiredmarks').val();
			
			var remarks=$(this).find('.remarks').val();


			if(studentId!=undefined){
				studentIdArray.push(studentId.trim());

			}

			if(actualDate!=undefined ){
				actualcompletionArray.push(actualDate.trim());
			}

			if(!(isNaN(aquiredmarks))){
				aquiredmarksArray.push(aquiredmarks.trim());
			}


			if(remarks!=undefined){

				if(remarks.trim()==""){

					remarksArray.push("null");

				}else{

					remarksArray.push(remarks.trim());
				}
			}
		});

		var reg = /^\d+$/;

		for(var i=0;i<studentIdArray.length;i++){


			if(aquiredmarksArray[i]==undefined && aquiredmarksArray[i]==""){

				$('.errormessagediv').show();
				$('.validateTips').text("Aquired marks should not empty");

				return false;

			}
			else if(parseInt(maxmarks)<parseInt(aquiredmarksArray[i])){

				$('.errormessagediv').show();
				$('.validateTips').text("Aquired marks should not greater than Max marks");

				return false;

			}

		}

		var datalist={

				"projectId":projectId.trim(),
				"studentIdArray" : studentIdArray.join(),
				"actualcompletionArray" : actualcompletionArray.join(),
				"aquiredmarksArray" : aquiredmarksArray.join(),
				"remarksArray" : remarksArray.join()
		};
		$.ajax({
			type : "GET",
			url : "assignmentupload.html?method=updateProjectDetails",
			data :datalist,
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);


				if(result.status=="true"){

					$('.successmessagediv').show();
					$('.validateTips').text("Project Details Updated Successfully");

					setTimeout(function(){

						window.location.href="teachermenuaction.html?method=projectAssign";

					},3000);

				}else{

					$('.errormessagediv').show();
					$('.validateTips').text("Project Details Not Updated,Try Again!");

				}

			}
		});
	});
	
	$("#searchname").keydown(function(event) {
		var searchText = $("#searchname").val();
		if (event.keyCode == 13) {
			window.location.href = "teachermenuaction.html?method=projectAssign&searchTerm="
				+ searchText;
		}

	});

	$("#search").click(function(){
		var searchText = $("#searchname").val().trim();
		window.location.href ="teachermenuaction.html?method=projectAssign&searchTerm="+ searchText;
	});
	
	$("#trash").click(function(){
		
		var count = 0;

		$('input[name="selectBox"]:checked').each(function() {
			
			var list=$(this).attr("id");
			projectCode.push(list);
			count ++;
			
		});

		if (count == 0) {
			$('.errormessagediv').show();
			$(".validateTips").text("Select the Assignment to be Deleted");
			$('.errormessagediv').delay(2000).slideUp();
		}
		else{
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are you sure to delete?</p>");
		}
	});
	
	$("#dialog").dialog({
		
		 autoOpen: false,
	     modal: true,
	     title:'Project List',
	     buttons : {
	    	 
	    	 "Yes" : function() {
	    		 
	    		 $.ajax({
					 type: 'POST', 
					  url: "assignmentupload.html?method=deleteProject",
			          data:{"projectCode":projectCode.toString()},
			         
			          success : function(data) {
							var result = $.parseJSON(data);

							$('.errormessagediv').hide();

							if(result.status=="true"){
								$(".successmessagediv").show();
								$(".validateTips").text("Project Deleted Successfully");

							}else{

								$('.errormessagediv').show();
								$('.validateTips').text("Project Not Deleted,Try Again");
							}

							setTimeout(function(){
								location.reload();
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
