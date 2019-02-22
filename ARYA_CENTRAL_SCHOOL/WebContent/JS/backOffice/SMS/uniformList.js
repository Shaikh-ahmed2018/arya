
function myFunction() {
	    
			         document.getElementById("myForm").submit();   
				  }


$(document).ready(function() {
	
	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
	}
		$("#selectall").change(function() {
			$(".select").prop('checked', $(this).prop("checked"));
		});
		
		$(".select").change(function() {
			if($(".select").length == $(".select:checked").length){
				$("#selectall").prop("checked",true);
			}
			else{
				$("#selectall").prop("checked",false);
			}
		});
	
	if($("#div1 .panel-body").text().trim()=="Nothing found to display."){
		$("#div1 .panel-body").empty();
			$("#div1 .panel-body").append('<table id="allstudent" class="table">' +
					'<thead><tr>' +
					'<th class="sortable"><input type="checkbox" name="selectall" id="selectall" onclick="selectAll()"></a></th>'+
					'<th class="sortable">date<img src="images/sort1.png" style="float: right"></a></th>'+
					'<th class="sortable">classname<img src="images/sort1.png" style="float: right"></a></th>'+
					'<th class="sortable">sectionname<img src="images/sort1.png" style="float: right"></a></th>'+
					'<th class="sortable">studentname<img src="images/sort1.png" style="float: right"></a></th>'+
					'<th class="sortable">smstext<img src="images/sort1.png" style="float: right"></a></th>'+
					'<tbody><tr><td colspan="6">No Records Found</td></tr></tbody></table>');
			
	}
					
					
					
				
	$("#search")
	.click(function(){
				
		
		
		var searchvalue = $('#searchname').val();
				
				
		window.location.href = "adminMenu.html?method=meetingslist&searchvalue="+searchvalue;
		
				
			});
					
					
	$("#delete")
	.click(function(){
		
		var hmeetingid = $('#hmeetingid').val();
		
		
		
		if(hmeetingid == null || hmeetingid==""){
			
			$(".errormessagediv").show();
			
			 $(".validateTips").text("Select Any CheckBox");
			 
			 return false;
		}
		
		else{
			
			datalist={
					
					"hmeetingid" : hmeetingid
			},
			
			$.ajax({
				
				type : "GET",
				url : "smsPath.html?method=deleteMeeting",
				data : datalist,
				async : false,

				success : function(
						response) {
					
					var result = $.parseJSON(response);
					
					if(result.jsonResponse=="Meeting/Event Deleted Successfully"){
						
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						 $(".validateTips").text("Meeting/Event Deleted Successfully");
						 
						 setTimeout(function(){
								
								window.location.href = "adminMenu.html?method=homeworklist";
						 
						 },3000);
					}
					
					else{
						
						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						 $(".validateTips").text("Meeting/Event Deleting Failed");
						 
						 setTimeout(function(){
								
								window.location.href = "adminMenu.html?method=homeworklist";
						 
						 },3000);
						
					}
					   
				}
				
				
			});
			
		}
		
		
		
		
	});
	
	
	
					
					
				});






function getvaldetails(value){
	
	var s1 =value.id;
	
		var assgnmentid = s1;
		
	
	
	$("#hmeetingid").val(assgnmentid);
	
}











