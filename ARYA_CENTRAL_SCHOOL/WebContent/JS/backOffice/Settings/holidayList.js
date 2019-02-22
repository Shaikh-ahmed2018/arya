function myFunction() {
	    
        document.getElementById("myForm").submit();   
	  }
$(document).ready(function(){
	
	if($("#allstudent tbody tr").length==0){
		$("#allstudent tbody").append("<tr><td ColSpan='5'>No Records Found</td></tr>");
	}
	$("select[name='location']").val($("#searchexamid").val());
	
	$("#selectall").change(function(){

		 $(".select").prop('checked', $(this).prop("checked"));
		});
	
	$("#editspec").click(function(){
		
		var cnt = 0;
		var holiday_Code = null;

		$('.select:checked').map(function() {
		
			holiday_Code = $(this).attr("id");
			
			cnt++;
		});

		if (cnt == 0 || cnt > 1) {
			
			$(".errormessagediv").show();
				$(".validateTips").text("Select any one Holiday");
				return false;
		} else {
			$(".errormessagediv").hide();
			window.location.href="holidayMaster.html?method=editHolidayMaster&deptId="+holiday_Code;
		}
	});
	
	$("#delete").click(function(){
		
		 studentIdlist=[];
		var count =0;
		$(".select:checked").each(function(){
			 
			var list=$(this).attr("id");
			studentIdlist.push(list);
			 count ++;
		 });	
		if(count==0){
			
			$(".errormessagediv").show();
			$(".validateTips").text("Select the Holidays to be Deleted");
			$('.errormessagediv').delay(3000).slideUp();
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
	     title:'Holiday List',
	     buttons : {
	    	 
	    	 "Yes" : function() {
	    		 $.ajax({
					 type: 'POST', 
					  url: "holidayMaster.html?method=deleteHolidayData",
			          data:{"studentIdlist":studentIdlist},
			         
					  success: function(response) {
						  var result = $.parseJSON(response);
						  
						  $('.errormessagediv').hide();
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
							$(".validateTips").text(result.status);	
							
							setTimeout(function() {
								window.location.href="adminMenu.html?method=holidaymaster&status="+result.status;
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