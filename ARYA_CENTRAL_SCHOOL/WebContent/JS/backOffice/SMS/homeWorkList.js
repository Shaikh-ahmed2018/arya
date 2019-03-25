
function myFunction() {
	    
			         document.getElementById("myForm").submit();   
				  }


$(document).ready(function() {
	
	 $("#send").click(function() { alert('sending');
		 $.ajax({
			    type: "POST",
			    url: "http://sms.textidea.com/app/smsapi/index.php?key=25BB891406C392&campaign=5043&routeid=100233&type=text&contacts=9447497755&senderid=ARYACS&msg=Hello+People%2C+have+a+great+day",
			    data: dataString,
			    success: function() {
			      $('#contact_form_message').html("<div id='message'></div>");
			      $('#message').html("<h2>Contact Form Submitted!</h2>")
			      .append("<p>We will be in touch soon.</p>")
			      .hide()
			      .fadeIn(1500, function() {
			        $('#message').append("Tick");
			      });
			    }
		 	});
		 alert('completed');
	 });
	 
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='5'>NO Records Found</td></tr>");
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
					'<th class="sortable">subjectname<img src="images/sort1.png" style="float: right"></a></th>'+
					'<th class="sortable">description<img src="images/sort1.png" style="float: right"></a></th></tr></thead>'+
					'<tbody><tr><td colspan="5">No Records Found</td></tr></tbody></table>');
			
	}
			
	$("#search")
	.click(function(){
				
		var searchvalue = $('#searchname')
		.val();
				
				
		window.location.href = "adminMenu.html?method=homeworklist&searchvalue="
			+ searchvalue;
		
				
			});
					
					
	$("#delete")
	.click(function(){
		
		var hhomeworkid = $('#hhomeworkid').val();
		
		
		
		if(hhomeworkid == null || hhomeworkid==""){
			
			$(".errormessagediv").show();
			
			 $(".validateTips").text("Select Any CheckBox");
			 
			 return false;
		}
		
		else{
			
			datalist={
					
					"hhomeworkid" : hhomeworkid
			},
			
			$.ajax({
				
				type : "GET",
				url : "smsPath.html?method=deleteHomeWork",
				data : datalist,
				async : false,

				success : function(
						response) {
					
					var result = $.parseJSON(response);
					
					if(result.jsonResponse=="HomeWork Deleted Successfully"){
						
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						 $(".validateTips").text("HomeWork Deleted Successfully");
						 
						 setTimeout(function(){
								
								window.location.href = "adminMenu.html?method=homeworklist";
						 
						 },3000);
					}
					
					else{
						
						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						 $(".validateTips").text("HomeWork Deleting Failed");
						 
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
		
	
	
	$("#hhomeworkid").val(assgnmentid);
	
}











