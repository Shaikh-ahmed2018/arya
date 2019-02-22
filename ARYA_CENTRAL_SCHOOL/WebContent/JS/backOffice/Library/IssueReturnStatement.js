 flag="anywhere";

$(document).ready(function(){
	
	$(".allstudenttable").hide();
	$(".pagebanner").hide();
	$(".pagination").hide();
	
	 $("#locationid").change(function()
	   {
		 if($("#locationid").val()!="all"){
			 $(".allstudenttable").show();
			 $(".pagebanner").show();
			 $(".pagination").show();
		    }
	   });
	
   if($("input[name='statement_by']:checked").val()=="issue" || $("input[name='statement_by']:checked").val()=="return" )
   {
	   if($("input[name='requested_by']:checked").val()=="all" || $("input[name='requested_by']:checked").val()=="Student" || $("input[name='requested_by']:checked").val()=="staff" || $("input[name='requested_by']:checked").val()=="others")
	  {
		  
	    		   if($("input[name='order_by']:checked").val()=="subno"||$("input[name='order_by']:checked").val()=="accno"||$("input[name='order_by']:checked").val()=="title"||$("input[name='order_by']:checked").val()=="author")
	    		   {
	    			   if($("#locationid").val()!="all"){
	    					 $(".allstudenttable").show();
	    					 $(".pagebanner").show();
	    					 $(".pagination").show();
	    					 getAllIssueReturnDetails();
	    				    }else{
	    				    	$(".allstudenttable").hide();
	    					$(".pagebanner").hide();
	    					$(".pagination").hide();
	    				    }
	    			   $("#locationid").change(function(){
	    				   if($("#locationid").val()!="all"){
		    					 $(".allstudenttable").show();
		    					 $(".pagebanner").show();
		    					 $(".pagination").show();
		    					 getAllIssueReturnDetails();
		    				    }else{
		    				    	$(".allstudenttable").hide();
			    					$(".pagebanner").hide();
			    					$(".pagination").hide();
			    				    }
	    			   });
	    		   }
	    		   else if($("input[name='order_by']:checked").val()=="name")
	    		   {
	    			   if($("#locationid").val()!="all"){
	    					 $(".allstudenttable").show();
	    					 $(".pagebanner").show();
	    					 $(".pagination").show();
	    					 getAllIssueReturnDetails();
	    				    }else{
	    				    	$(".allstudenttable").hide();
	    					$(".pagebanner").hide();
	    					$(".pagination").hide();
	    				    }
	    			   $("#locationid").change(function(){
	    				   if($("#locationid").val()!="all"){
		    					 $(".allstudenttable").show();
		    					 $(".pagebanner").show();
		    					 $(".pagination").show();
		    					 getAllIssueReturnDetails();
		    				    }else{
		    				    	$(".allstudenttable").hide();
			    					$(".pagebanner").hide();
			    					$(".pagination").hide();
			    				    }
	    			   });
	    		   }
	 }
	   
 }
   
   $("#subscriberno").change(function(){
	   getAccessionNo();
	   });
   $("#accession_no").change(function(){
	   getBookIssueReturnDetailsByAccessionNo();
	   });
   
   $("#subscriberno").click(function(){
	   
	   if($("input[name='requested_by']:checked").val()=="individual")
	   {
     $("#subscriberno").autocomplete({
		source : function(request,response) { 
				$.ajax({
				url : "LibraryMenu.html?method=IndividualSearchInIssueReturnStatement",
				data : {
					searchTerm : request.term,
					locid : $("#locationid").val(),
					 statement:$("input[name='statement_by']:checked").val(),
				    orderby:$("input[name='order_by']:checked").val(),
				    select:$("input[name='requested_by']:checked").val()
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					
					response($.map(	result.jsonResponse,function(item) {
						return {
							label : item.subscriberName,
							value : item.subscriber_id,
						}
					}));
				}
			});
		},
		select : function(event, ui) {
			var searchTerm = ui.item.value;
			
			studentDetails = {
					'searchTerm' : searchTerm.split("-")[0]
			};
			$("#subscriberno").val(ui.item.label);
			$("#hiddenstuid").val(ui.item.value);
			return false;
		}
    });
	}
	   else{
		   $(".successmessages").hide();
		   $(".errormessagediv").show();
			$(".validateTips").text("Select Individual Option");
			$(".errormessagediv").fadeOut(3000);
	   }
	   
  });
   
 
  $("input[name='statement_by']").change(function(){
	  
	  if($("input[name='statement_by']:checked").val()=="issue" || $("input[name='statement_by']:checked").val()=="return" )
	  {
		  if($("input[name='requested_by']:checked").val()=="all" || $("input[name='requested_by']:checked").val()=="Student" || $("input[name='requested_by']:checked").val()=="staff" || $("input[name='requested_by']:checked").val()=="others")
		  {
			  if($("input[name='order_by']:checked").val()=="subno"||$("input[name='order_by']:checked").val()=="name"||$("input[name='order_by']:checked").val()=="accno"||$("input[name='order_by']:checked").val()=="title"||$("input[name='order_by']:checked").val()=="author")
			 {
				  if($("#locationid").val()!="all"){
 					 $(".allstudenttable").show();
 					 $(".pagebanner").show();
 					 $(".pagination").show();
 					 getAllIssueReturnDetails();
 				    }else{
 				    	$(".allstudenttable").hide();
 					$(".pagebanner").hide();
 					$(".pagination").hide();
 				    }
 			   $("#locationid").change(function(){
 				   if($("#locationid").val()!="all"){
	    					 $(".allstudenttable").show();
	    					 $(".pagebanner").show();
	    					 $(".pagination").show();
	    					 getAllIssueReturnDetails();
	    				    }else{
	    				    	$(".allstudenttable").hide();
		    					$(".pagebanner").hide();
		    					$(".pagination").hide();
		    				    }
 			   });
		    }
		else if($("input[name='order_by']:checked").val()=="name")
		   {
			 $("#subscriberno").prop("readonly",true);
			 if($("#locationid").val()!="all"){
				 $(".allstudenttable").show();
				 $(".pagebanner").show();
				 $(".pagination").show();
				 getAllIssueReturnDetails();
			    }else{
			    	$(".allstudenttable").hide();
				$(".pagebanner").hide();
				$(".pagination").hide();
			    }
		   $("#locationid").change(function(){
			   if($("#locationid").val()!="all"){
					 $(".allstudenttable").show();
					 $(".pagebanner").show();
					 $(".pagination").show();
					 getAllIssueReturnDetails();
				    }else{
				    	$(".allstudenttable").hide();
    					$(".pagebanner").hide();
    					$(".pagination").hide();
    				    }
		   });
		   }
	  }
	  }
	});
  
  $("input[name='requested_by']").change(function(){
	  
	  if($("input[name='statement_by']:checked").val()=="issue" || $("input[name='statement_by']:checked").val()=="return" )
	  {
		  if($("input[name='requested_by']:checked").val()=="all" || $("input[name='requested_by']:checked").val()=="Student" || $("input[name='requested_by']:checked").val()=="staff" || $("input[name='requested_by']:checked").val()=="others")
		  {
			  if($("input[name='order_by']:checked").val()=="subno"||$("input[name='order_by']:checked").val()=="name"||$("input[name='order_by']:checked").val()=="accno"||$("input[name='order_by']:checked").val()=="title"||$("input[name='order_by']:checked").val()=="author")
			 {
				  if($("#locationid").val()!="all"){
 					 $(".allstudenttable").show();
 					 $(".pagebanner").show();
 					 $(".pagination").show();
 					 getAllIssueReturnDetails();
 				    }else{
 				    	$(".allstudenttable").hide();
 					$(".pagebanner").hide();
 					$(".pagination").hide();
 				    }
 			   $("#locationid").change(function(){
 				   if($("#locationid").val()!="all"){
	    					 $(".allstudenttable").show();
	    					 $(".pagebanner").show();
	    					 $(".pagination").show();
	    					 getAllIssueReturnDetails();
	    				    }else{
	    				    	$(".allstudenttable").hide();
		    					$(".pagebanner").hide();
		    					$(".pagination").hide();
		    				    }
 			   });
		    }
		else if($("input[name='order_by']:checked").val()=="name")
		   {
			if($("#locationid").val()!="all"){
				 $(".allstudenttable").show();
				 $(".pagebanner").show();
				 $(".pagination").show();
				 getAllIssueReturnDetails();
			    }else{
			    	$(".allstudenttable").hide();
				$(".pagebanner").hide();
				$(".pagination").hide();
			    }
		   $("#locationid").change(function(){
			   if($("#locationid").val()!="all"){
					 $(".allstudenttable").show();
					 $(".pagebanner").show();
					 $(".pagination").show();
					 getAllIssueReturnDetails();
				    }else{
				    	$(".allstudenttable").hide();
   					$(".pagebanner").hide();
   					$(".pagination").hide();
   				    }
		   });
		   }
	  }
	  }
	});
$("input[name='order_by']").change(function(){
	  
	  if($("input[name='statement_by']:checked").val()=="issue" || $("input[name='statement_by']:checked").val()=="return" )
	  {
		  if($("input[name='requested_by']:checked").val()=="all" || $("input[name='requested_by']:checked").val()=="Student" || $("input[name='requested_by']:checked").val()=="staff" || $("input[name='requested_by']:checked").val()=="others")
		  {
			  if($("input[name='order_by']:checked").val()=="subno"||$("input[name='order_by']:checked").val()=="name"||$("input[name='order_by']:checked").val()=="accno"||$("input[name='order_by']:checked").val()=="title"||$("input[name='order_by']:checked").val()=="author")
			 {
				  if($("#locationid").val()!="all"){
 					 $(".allstudenttable").show();
 					 $(".pagebanner").show();
 					 $(".pagination").show();
 					 getAllIssueReturnDetails();
 				    }else{
 				    	$(".allstudenttable").hide();
 					$(".pagebanner").hide();
 					$(".pagination").hide();
 				    }
 			   $("#locationid").change(function(){
 				   if($("#locationid").val()!="all"){
	    					 $(".allstudenttable").show();
	    					 $(".pagebanner").show();
	    					 $(".pagination").show();
	    					 getAllIssueReturnDetails();
	    				    }else{
	    				    	$(".allstudenttable").hide();
		    					$(".pagebanner").hide();
		    					$(".pagination").hide();
		    				    }
 			   });
		    }
		else if($("input[name='order_by']:checked").val()=="name")
		   {
			if($("#locationid").val()!="all"){
				 $(".allstudenttable").show();
				 $(".pagebanner").show();
				 $(".pagination").show();
				 getAllIssueReturnDetails();
			    }else{
			    	$(".allstudenttable").hide();
				$(".pagebanner").hide();
				$(".pagination").hide();
			    }
		   $("#locationid").change(function(){
			   if($("#locationid").val()!="all"){
					 $(".allstudenttable").show();
					 $(".pagebanner").show();
					 $(".pagination").show();
					 getAllIssueReturnDetails();
				    }else{
				    	$(".allstudenttable").hide();
   					$(".pagebanner").hide();
   					$(".pagination").hide();
   				    }
		   });
		   }
	  }
			 }
	});


$("#pdfDownload").click(function(){
	 
		window.location.href ='LibraryMenu.html?method=IssueReissuePdf&locationid='+$("#locationid").val()
		+'&locationname='+$("#locationid option:selected").text()+"&statement="+$("input[name='statement_by']:checked").val()
		+"&orderby="+$("input[name='order_by']:checked").val()+"&select="+$("input[name='requested_by']:checked").val();
		
	
	});
$("#excelDownload").click(function(){
	 
	window.location.href ='LibraryMenu.html?method=IssueReissueExcel&locationid='+$("#locationid").val()
	+'&locationname='+$("#locationid option:selected").text()+"&statement="+$("input[name='statement_by']:checked").val()
	+"&orderby="+$("input[name='order_by']:checked").val()+"&select="+$("input[name='requested_by']:checked").val();
	

});



$("#print").click(function(){
	
		$.ajax({
			type:"post",
			url:'LibraryMenu.html?method=IssueReturnPrint&locationid='+$("#locationid").val()
			+'&locationname='+$("#locationid option:selected").text()+"&statement="+$("input[name='statement_by']:checked").val()
			+"&orderby="+$("input[name='order_by']:checked").val()+"&select="+$("input[name='requested_by']:checked").val(),
			success:function(data){
				
			}
		});
	});
   


});

function getAllIssueReturnDetails(){
	var locationid=$("#locationid").val();
	var statement=$("input[name='statement_by']:checked").val();
	var orderby=$("input[name='order_by']:checked").val();
	var select=$("input[name='requested_by']:checked").val();
	
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getAllIssueReturnDetails",
		data : {
			"statement":statement,
			"locationid":locationid,
			"select":select,
			"orderby":orderby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					var myString = result.studentData[i].from_date.split('/');
					var arr=myString;
				$("#allstudent tbody").append("<tr id='"+result.studentData[i].subscriber_id+"'>"
						+"<td>"+(i+1)+"</td>" 
						+"<td>"+result.studentData[i].subscriberNo+"</td>"
						+"<td>"+result.studentData[i].subscriberName+"</td>"
						+"<td>"+result.studentData[i].accessionNo+"</td>"
						+"<td>"+result.studentData[i].bookTitle+"</td>"
						+"<td>"+result.studentData[i].author+"</td>"
						+"<td> &nbsp  From  &nbsp "+arr[0]+"  &nbsp  To  &nbsp "+arr[1]+"</td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='7'>No Records Found</td></tr>");
				
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records "+result.studentData.length);
		}
	});
}

function getAccessionNo(){
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getIssueReturnAccessionNo",
		data:{
				accessionId : $("#accession_no").val(),
				subid : $("#hiddenstuid").val(),
				statement:$("input[name='statement_by']:checked").val(),
				orderby:$("input[name='order_by']:checked").val(),
				select:$("input[name='requested_by']:checked").val()
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#accession_no').empty("");
			$('#accession_no').append('<option value="all">' +"---select---"+ '</option>');
			var j=0;
			var len=result.ClassList.length;
			for ( j = 0; j < len; j++) {
				$('#accession_no').append('<option value="'
						+ result.ClassList[j].accessionNoId + '">'
						+ result.ClassList[j].accessionNo
						+ '</option>');
			}
		}
	});
}

function getBookIssueReturnDetailsByAccessionNo(){
	
	var accession_no=$("#accession_no").val();
	var subscriberId=$("#hiddenstuid").val();
	var statement=$("input[name='statement_by']:checked").val();
    var orderby=$("input[name='order_by']:checked").val();
    var select=$("input[name='requested_by']:checked").val();
    var location=$("#locationid").val();
	
	accessionDetails={
			'subscriberId':subscriberId,
			'statement':statement,
			'orderby':orderby,
			'select':select,
			'location':location,
			'accession_no':accession_no
	 };
		$.ajax({
			type:"POST",
			url:"LibraryMenu.html?method=getBookIssueReturnDetailsByAccessionNo",
			data :accessionDetails,
			async : false,
			success:function(data){
				var result = $.parseJSON(data);
				var i=0;
				var len=result.accessionList.length;
				if(len >0){
				for(i=0;i<len;i++){
					$("#hiddentitle").val(result.accessionList[i].bookTitle);
					$("#fromdate").val(result.accessionList[i].issued_date);
					$("#todate").val(result.accessionList[i].issued_return_date);
				}
				}
				else{
					$("#title,#fromdate,#todate").val("");
				}
			}
		});
}



