$(document).ready(function(){
	
	getPublisherSettingList();

	$("#selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});
	
	
	
	$(".select").change(function(){
		if($(".select").length==$(".select:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
		}
		
	});
	
	 $("#searchValue").keypress(function(e){
		 var searchname = $("#searchValue").val().trim();
	      $("input:checkbox").prop('checked', false); 
		
		    if(e.keyCode == 13){
		        e.preventDefault();      
		        publisherDetailsSearch();
		    	   
		    	  
 		 }	
 	 });
	
	 
	 

	
	$("#editId").click(function(){
			$(".successmessagediv1").hide();
			var cnt = 0;
			

			$('input[type="checkbox"]:checked').map(function() {
				getData = $(this).val();
				cnt++;
				
			});
			
			

		if ($("#allstudent tbody tr").length !=1 &&  (cnt == 0 || cnt > 1)) {
				$(".errormessagediv").show();
				$(".validateTips").text("Select Any One Publisher Name");
				return false;
			} 
			else
			{
				window.location.href = "LibraryMenu.html?method=editpublisherSetting&pubid="+getData;
			}
		});
	
	 deleteid=[];
	$('#delete').click(function() {

				var count =0;
				
				$(".select:checked").each(function(){

					var list=$(this).val();
					deleteid.push(list);
					count ++;
				});	
				
				if(count == 0)	{
			 		
			 		$('.errormessagediv').show();
					$('.validateTips').text("Select Publisher to Delete");
					$('.errormessagediv').delay(3000).slideUp();
			 		
			 	}
			 	else {
					
					  $("#dialog").dialog("open");
					  $("#dialog").empty();
					  $("#dialog").append("<p>Are you sure to delete?</p>");
				}
				
				//$(".select").prop("checked",false);
				
			});

	$("#dialog").dialog({
 		
 		 autoOpen: false,
	     modal: true,					    
	     title:'Publisher Settings',
	     buttons : {
	    	 "Yes" : function() {
	    		 
					$.ajax({
						type : "GET",
						url : "LibraryMenu.html?method=deletepublisherSetting",
						data :{
							"deleteid" :deleteid.toString()},

						success : function(response) {
							var result = $.parseJSON(response);
							$('.errormessagediv').hide();
							
							if(result.pubsetting=="DeleteSuccuss"){
								
								$(".successmessagediv").show();
								$(".validateTips").text("Unmapped Record Delete Progressing...");
							}
							
							else{
								$(".errormessagediv").show();

								$(".validateTips").text("Selected Publisher Name is Mapped Cannot be Deleted");
								$('.errormessagediv').delay(3000).slideUp();
							}

							setTimeout(function(){
								   
								 window.location.href="LibraryMenu.html?method=ListpublisherSettings";
							 
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
	

	pagination(100);	
	
});
function getPublisherSettingList(){
	datalist={
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getPublisherSettingList",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
		
			for(var i=0;i<result.pubList.length;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td><input type='checkbox' name='select' class='select' value='"+result.pubList[i].entry_id+"'/></td>"+
						"<td>"+result.pubList[i].publisher+"</td>"+
					   "<td>"+"<span>E-mail: "+result.pubList[i].email+"</span><br />"+
						"<span>Fax: "+result.pubList[i].fax+"</span><br />"+
						"<span>Tel-Phone: "+result.pubList[i].telephone+"</span><br />"+
						"<span>Mobile-No: "+result.pubList[i].mobilenum+"</span><br />"+"</td>"+
						"<td>"+result.pubList[i].address+"</td>"+
						"</tr>"
				
				);
				
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.pubList.length);
		}
	});
		
}

function publisherDetailsSearch(){
datalist={
		"publisher" : 	$("#Publisher").val(),
		"searchid"  :$("#searchValue").val()
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=publisherDetailsSearch",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
		  if(result.publist.length>0){
			for(var i=0;i<result.publist.length;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td><input type='checkbox' name='select' class='select' value='"+result.publist[i].entry_id+"'/></td>"+
						"<td>"+result.publist[i].publisher+"</td>"+
					   "<td>"+"<span>E-mail: "+result.publist[i].email+"</span><br />"+
						"<span>Fax: "+result.publist[i].fax+"</span><br />"+
						"<span>Tel-Phone: "+result.publist[i].telephone+"</span><br />"+
						"<span>Mobile-No: "+result.publist[i].mobilenum+"</span><br />"+"</td>"+
						"<td>"+result.publist[i].address+"</td>"+
						"</tr>"
				
				);
			}
				
			}
		  else{
				$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
			}
			
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.publist.length);

			if(result.publist.length >= 1){
				$(".select").change(function(){
					if($(".select").length==$(".select:checked").length){
						$("#selectall").prop("checked",true);
					}
					else{
						$("#selectall").prop("checked",false);
					}
				});
			}
		}
	});
		
	
}
