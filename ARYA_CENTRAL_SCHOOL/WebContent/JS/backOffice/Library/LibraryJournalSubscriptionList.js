$(document).ready(function(){
	
	getJournalSubscriptionList();

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
		       
		        journalsubscriptionDetailsSearch();
 		 }	
 	 });
	
	$("#editId").click(function(){
			$(".successmessagediv").hide();
			var cnt = 0;
			$('input[type="checkbox"]:checked').map(function() {
				getData = $(this).val();
				cnt++;
				
			});
			if ($("#allstudent tbody tr").length !=1 &&  (cnt == 0 || cnt > 1)) {
				$(".errormessagediv").show();
				$(".validateTips").text("Select Any One Record");
				return false;
			} 
			else 
				{			
				window.location.href = "LibraryMenu.html?method=editeLibraryJournalSubscription&journalId="+getData;
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
					$('.validateTips').text("Select Any One Record");
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
	     title:'Journal Subscription',
	     buttons : {
	    	 "Yes" : function() {
					$.ajax({
						type : "GET",
						url : "LibraryMenu.html?method=deleteJournalSbscription",
						data :{
							"deleteid" :deleteid.toString()},

						success : function(response) {
							var result = $.parseJSON(response);
							$('.errormessagediv').hide();
							
							if(result.journalsub=="Success"){
								$(".successmessagediv").show();
								$(".validateTips").text("Deleting Record Progressing...");
							}
							setTimeout(function(){
								   
								 window.location.href="LibraryMenu.html?method=LibraryjournalSubscriptionList";
							 
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
function getJournalSubscriptionList(){
	datalist={
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getJournalSubscriptionList",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
		     var i=0;
			var len=result.journal.length;
			if(len>0){
			for(i=0;i<len;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td><input type='checkbox' name='select' class='select' value='"+result.journal[i].entryId+"'/></td>"+
						"<td>"+result.journal[i].name+"</td>"+
						"<td>"+result.journal[i].accessionno+"</td>"+
						"<td>"+result.journal[i].journaltype+"</td>"+
						"<td>"+result.journal[i].ratepercopy+"</td>"+
						"<td>"+result.journal[i].numberofcopy+"</td>"+
					   "<td>"+"<span>Subscription Periode: "+result.journal[i].subscriptionperiode+"</span><br />"+
						"<span>To Date: "+result.journal[i].todate+"</span><br />"+
						"<span>Due Date: "+result.journal[i].duedate+"</span><br />"+"</td>"+
						"<td>"+result.journal[i].publisher+"</td>"+
						"<td>"+result.journal[i].supplier+"</td>"+
						"</tr>"
				);
			  }
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='9'>No Record Found</td></tr>");
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records "+len);
		}
	});
		
}


function journalsubscriptionDetailsSearch(){
	datalist={
			 "Name":$("#categoryname").val(),
			"searchid":$("#searchValue").val(),
			    	
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=journalsubscriptionDetailsSearch",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
	         var i=0;
			var len=result.jurserch.length;
	        if(len>0){
			for(i=0;i<len;i++){
		
				$("#allstudent tbody").append("<tr>" +
						"<td><input type='checkbox' name='select' class='select' value='"+result.jurserch[i].entryId+"'/></td>"+
						"<td>"+result.jurserch[i].name+"</td>"+
						"<td>"+result.jurserch[i].accessionno+"</td>"+
						"<td>"+result.jurserch[i].journaltype+"</td>"+
						"<td>"+result.jurserch[i].ratepercopy+"</td>"+
						"<td>"+result.jurserch[i].numberofcopy+"</td>"+
					   "<td>"+"<span>Subscription Periode: "+result.jurserch[i].subscriptionperiode+"</span><br />"+
						"<span>To Date: "+result.jurserch[i].todate+"</span><br />"+
						"<span>Due Date: "+result.jurserch[i].duedate+"</span><br />"+"</td>"+
						"<td>"+result.jurserch[i].publisher+"</td>"+
						"<td>"+result.jurserch[i].supplier+"</td>"+
						"</tr>"
				);
			}
	        }
		 else{
				$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
		 }
		 
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records "+len);
			
			if(result.jurserch.length >= 1){
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








