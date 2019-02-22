$(document).ready(function(){
	
	getGenarelSettingList();
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
		       
		       GenarelDetailsSearch();
		    	   
		    	  
 		 }	
 	 });
	
	
	
	
	$("#editId").click(function(){
		$(".successmessagediv").hide();
		var cnt = 0;
		

		$('input[type="checkbox"]:checked').map(function() {
			getData = $(this).val();
			cnt++;
			
		});
		
		

		 if ($("#allstudent tbody tr").length !=1 &&  (cnt == 0 || cnt > 1))  {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one Item Description ");
			return false;
		} 
		else
		{
			window.location.href = "LibraryMenu.html?method=editGenarelSetting&genid="+getData;
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
						$('.validateTips').text("Select Item Description to Delete");
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
		     title:'Genarel Settings',
		     buttons : {
		    	 "Yes" : function() {
		    		 
						$.ajax({
							type : "GET",
							url : "LibraryMenu.html?method=deleteGenarelSetting",
							data :{
								"deleteid" :deleteid.toString()},

							success : function(response) {
								var result = $.parseJSON(response);
								$('.errormessagediv').hide();
								
								if(result.gensetting=="deletesuccuss"){
									
									$(".successmessagediv").show();
									$(".validateTips").text("Deleting Record Progressing...");
								}
								
								/*else if(result.jsonResponse=="Stage Details Already Mapped"){
									$('.errormessagediv').show();
									$('.validateTips').text("Selected Stage is Mapped cannot Delete");
								}
								*/

								setTimeout(function(){
									   
									 window.location.href="LibraryMenu.html?method=ListgeneralSettings";
								 
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
function getGenarelSettingList(){
	datalist={
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getGenarelSettingList",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
		
			for(var i=0;i<result.genlist.length;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td><input type='checkbox' name='select' class='select'  value='"+result.genlist[i].entry_id+"'/></td>"+
						"<td>"+result.genlist[i].itemType+"</td>"+
						"<td>"+result.genlist[i].noofdaytaken+"</td>"+
						"<td>"+result.genlist[i].amountperday+"</td>"+
						"<td>"+result.genlist[i].fineamount+"</td>"+
						"<td>"+result.genlist[i].fineincperday+"</td>"+
						"</tr>"
				
				);
				
				  
				
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.genlist.length);
		}
	});
		
}
function  GenarelDetailsSearch(){

	datalist={
			
			"searchid"  :$("#searchValue").val()
		},
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=GenarelDetailsSearch",
			data : datalist,
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
			  if(result.genlist.length>0){
				  
				  for(var i=0;i<result.genlist.length;i++){
						$("#allstudent tbody").append("<tr>" +
								"<td><input type='checkbox' name='select' class='select'  value='"+result.genlist[i].entry_id+"'/></td>"+
								"<td>"+result.genlist[i].itemType+"</td>"+
								"<td>"+result.genlist[i].noofdaytaken+"</td>"+
								"<td>"+result.genlist[i].amountperday+"</td>"+
								"<td>"+result.genlist[i].fineamount+"</td>"+
								"<td>"+result.genlist[i].fineincperday+"</td>"+
								"</tr>"
						
						);
						
						
				}
					
				}
			  else{
					$("#allstudent tbody").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
				}
				
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.genlist.length);
				
				
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






