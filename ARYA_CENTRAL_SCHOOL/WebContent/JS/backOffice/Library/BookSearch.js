 flag="anywhere";

$(document).ready(function(){
	
	if($("input[name='requested_by']:checked").val()=="all" || $("input[name='requested_by']:checked").val()=="issued" || $("input[name='requested_by']:checked").val()=="available" || $("input[name='requested_by']:checked").val()=="availableavailable")
	 {
		if($("input[name='order_by']:checked").val()=="accessionNo"||$("input[name='order_by']:checked").val()=="title"||$("input[name='order_by']:checked").val()=="author"||$("input[name='order_by']:checked").val()=="location"||$("input[name='order_by']:checked").val()=="category")
		   {
			   getAllBookDetails();
		   }
		else if($("input[name='order_by']:checked").val()=="publisher")
		   {
			   getAllBookPublisherDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="itemtype")
		   {
			   getAllBookItemTypeDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="ddc")
		   {
			   getAllBookDDCDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="content")
		   {
			   getAllBookContentDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="language")
		   {
			   getAllBookLanguageDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="supplier")
		   {
			   getAllBookSupplierDetails();
		   }
	}
	
	$("input[name='requested_by']").change(function(){
		if($("input[name='order_by']:checked").val()=="accessionNo"||$("input[name='order_by']:checked").val()=="title"||$("input[name='order_by']:checked").val()=="author"||$("input[name='order_by']:checked").val()=="location"||$("input[name='order_by']:checked").val()=="category")
		   {
			   getAllBookDetails();
		   }
		else if($("input[name='order_by']:checked").val()=="publisher")
		   {
			   getAllBookPublisherDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="itemtype")
		   {
			   getAllBookItemTypeDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="ddc")
		   {
			   getAllBookDDCDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="content")
		   {
			   getAllBookContentDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="language")
		   {
			   getAllBookLanguageDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="supplier")
		   {
			   getAllBookSupplierDetails();
		   }
	});
	
	$("input[name='order_by']").change(function(){
		if($("input[name='order_by']:checked").val()=="accessionNo"||$("input[name='order_by']:checked").val()=="title"||$("input[name='order_by']:checked").val()=="author"||$("input[name='order_by']:checked").val()=="location"||$("input[name='order_by']:checked").val()=="category")
		   {
			   getAllBookDetails();
		   }
		else if($("input[name='order_by']:checked").val()=="publisher")
		   {
			   getAllBookPublisherDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="itemtype")
		   {
			   getAllBookItemTypeDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="ddc")
		   {
			   getAllBookDDCDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="content")
		   {
			   getAllBookContentDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="language")
		   {
			   getAllBookLanguageDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="supplier")
		   {
			   getAllBookSupplierDetails();
		   }
	});
	
	$("#excelDownload").click(function(){

		var id = "excel";
		
		
		if($("input[name='order_by']:checked").val()=="accessionNo"||$("input[name='order_by']:checked").val()=="title"||$("input[name='order_by']:checked").val()=="author"||$("input[name='order_by']:checked").val()=="location"||$("input[name='order_by']:checked").val()=="category")
		   {
			var requested_by=$("input[name='requested_by']:checked").val();
			var order_by=$("input[name='order_by']:checked").val();
			var started_by=$("input[name='started_by']:checked").val();
			var searchValue=$("#searchValue option:selected").text();
			var entryid=$("input[name='entryid']:checked").val();
			
			
			window.location.href = 'LibraryMenu.html?method=getAllBookDetailsDownloadandPrint&id='+id+'&requested_by='+requested_by+'&order_by='+order_by+'&started_by='+started_by+'&searchValue='+searchValue+'&entryid='+entryid+'';
			     
		   }
		   else if($("input[name='order_by']:checked").val()=="publisher")
		   {
			   getAllBookPublisherDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="itemtype")
		   {
			   getAllBookItemTypeDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="ddc")
		   {
			   getAllBookDDCDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="content")
		   {
			   getAllBookContentDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="language")
		   {
			   getAllBookLanguageDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="supplier")
		   {
			   getAllBookSupplierDetails();
		   }
	
	});
	
	$("#pdfDownload").click(function(){

		var id = "pdf";
		
		
		if($("input[name='order_by']:checked").val()=="accessionNo"||$("input[name='order_by']:checked").val()=="title"||$("input[name='order_by']:checked").val()=="author"||$("input[name='order_by']:checked").val()=="location"||$("input[name='order_by']:checked").val()=="category")
		   {
			var requested_by=$("input[name='requested_by']:checked").val();
			var order_by=$("input[name='order_by']:checked").val();
			var started_by=$("input[name='started_by']:checked").val();
			var searchValue=$("#searchValue option:selected").text();
			var entryid=$("input[name='entryid']:checked").val();
			
			
			window.location.href = 'LibraryMenu.html?method=getAllBookDetailsDownloadandPrint&id='+id+'&requested_by='+requested_by+'&order_by='+order_by+'&started_by='+started_by+'&searchValue='+searchValue+'&entryid='+entryid+'';
			     
		   }
		   else if($("input[name='order_by']:checked").val()=="publisher")
		   {
			   getAllBookPublisherDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="itemtype")
		   {
			   getAllBookItemTypeDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="ddc")
		   {
			   getAllBookDDCDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="content")
		   {
			   getAllBookContentDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="language")
		   {
			   getAllBookLanguageDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="supplier")
		   {
			   getAllBookSupplierDetails();
		   }

	
	});
	
	$("#print").click(function(){

		var id = "print";
		
		
		if($("input[name='order_by']:checked").val()=="accessionNo"||$("input[name='order_by']:checked").val()=="title"||$("input[name='order_by']:checked").val()=="author"||$("input[name='order_by']:checked").val()=="location"||$("input[name='order_by']:checked").val()=="category")
		   {
			var requested_by=$("input[name='requested_by']:checked").val();
			var order_by=$("input[name='order_by']:checked").val();
			var started_by=$("input[name='started_by']:checked").val();
			var searchValue=$("#searchValue option:selected").text();
			var entryid=$("input[name='entryid']:checked").val();
			
			
			window.location.href = 'LibraryMenu.html?method=getAllBookDetailsDownloadandPrint&id='+id+'&requested_by='+requested_by+'&order_by='+order_by+'&started_by='+started_by+'&searchValue='+searchValue+'&entryid='+entryid+'';
			     
		   }
		   else if($("input[name='order_by']:checked").val()=="publisher")
		   {
			   getAllBookPublisherDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="itemtype")
		   {
			   getAllBookItemTypeDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="ddc")
		   {
			   getAllBookDDCDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="content")
		   {
			   getAllBookContentDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="language")
		   {
			   getAllBookLanguageDetails();
		   }
		   else if($("input[name='order_by']:checked").val()=="supplier")
		   {
			   getAllBookSupplierDetails();
		   }

	
	});

	
	$("input[name='requested_by']").change(function(){
		 $("#searchValue").val("");
	});
	$("input[name='order_by']").change(function(){
		 $("#searchValue").val("");
	});
	
	
	 $("#searchValue").keypress(function(e){
		 var searchname = $("#searchValue").val().trim();
		    if(e.keyCode == 13){
		        e.preventDefault();
		       if(flag=="anywhere" || flag=="startwith" || flag=="endswith") 
		    	   {
		    		   if($("input[name='order_by']:checked").val()=="accessionNo"||$("input[name='order_by']:checked").val()=="title"||$("input[name='order_by']:checked").val()=="author"||$("input[name='order_by']:checked").val()=="location"||
		    				   $("input[name='order_by']:checked").val()=="category")
		    		   {
		    			   SearchBookSearchByaccNoandTitleAnyWhere();
		    		   }
		    		   else if($("input[name='order_by']:checked").val()=="publisher"){
			    	    SearchBookSearchByPublisherAnyWhere();
		    		   }
		    		   else if($("input[name='order_by']:checked").val()=="itemtype")
		    		   {
		    			 SearchBookSearchByItemTypeAnyWhere();
		    		   }
		    		   else if($("input[name='order_by']:checked").val()=="ddc")
		    		   {
		    		     SearchBookSearchByDDCAnyWhere();
		    		   }
		    		   else if($("input[name='order_by']:checked").val()=="content")
		    		   {
		    			 SearchBookSearchByContentAnyWhere();
		    		   }
		    		   else if($("input[name='order_by']:checked").val()=="language")
		    		   {
		    			 SearchBookSearchByLanguageAnyWhere();
		    		   }
		    		   else if($("input[name='order_by']:checked").val()=="supplier")
		    		   {
		    			SearchBookSearchBySupplierAnyWhere();
		    		   }
 		       }
		    }
		});	

	 
	
	  /*..........................GoTo Click..........................*/
  $("#gobutton").click(function(){
	    
		var selection=$("#goto").val();
		var EntryId=$("input[name='entryid']:checked").val();
		
		 if((selection==undefined||selection=="----GoTo----" ||selection=="")){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Details");
			document.getElementById('goto').style.border = "1px solid #AF2C2C";
			$(".errormessagediv").fadeOut(3000);
		}
		 else if((EntryId==undefined||EntryId=="----GoTo----" ||EntryId=="" || EntryId==null)){
				$(".errormessagediv").show();
				$(".validateTips").text("select any one Record");
				$(".errormessagediv").fadeOut(3000);
			}
		else if(selection=="stockdetail"){
		 window.location.href ="LibraryMenu.html?method=gotostockDetails&EntryId="+EntryId;
		}
		else if(selection=="issuestate"){
		 window.location.href ="LibraryMenu.html?method=IssueStatementByStockEntryId&EntryId="+EntryId+"&selection="+selection;
				}
		else if( selection=="issuereturn"){
			  window.location.href ="LibraryMenu.html?method=ReturnStatementByEntryId&EntryId="+EntryId+"&selection="+selection;
					}
		 else {
			 $(".errormessagediv").show();
				$(".validateTips").text("No Issue and Returns");
				$(".errormessagediv").fadeOut(3000);
		 }
	});

	 $("input[name='started_by']").change(function(){
     	if($(this).val()=="startwith"){
     		flag="startwith";	
     	}
     	else if($(this).val()=="endswith"){
     		flag="endswith";
     	}
         else {
        	 flag="anywhere";
         }
     }); 
});

function getAllBookDetails(){
	var orderby=$("input[name='order_by']:checked").val();
	var select=$("input[name='requested_by']:checked").val();
	
    $("#individualstudenttable").show();
    $("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent' width='100%'" +">"
			+"<tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>Category</th>"
			+"</tr>"
			+"</table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getAllBookDetails",
		data : {
			"select":select,
			"orderby":orderby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
				$("#individualstudenttable #allstudent").append("<tr>"
						+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
						+"<td>"+result.studentData[i].accessionNo+"</td>"
						+"<td>"+result.studentData[i].bookTitle+"</td>"
						+"<td>"+result.studentData[i].bookAuthor+"</td>"
						+"<td>"+result.studentData[i].location+"</td>"
						+"<td>"+result.studentData[i].category+"</td>"
						+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records "+result.studentData.length);
		}
	});
}

function getAllBookPublisherDetails(){
	var orderby=$("input[name='order_by']:checked").val();
	var  select=$("input[name='requested_by']:checked").val();
	
    $("#individualstudenttable").show();
    $("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>Publisher</th>"
			+"</tr>"
			+"</table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getAllBookPublisherDetails",
		data : {
			"select":select,
			"orderby":orderby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
				$("#individualstudenttable #allstudent").append("<tr>"
						+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
						+"<td>"+result.studentData[i].accessionNo+"</td>"
						+"<td>"+result.studentData[i].bookTitle+"</td>"
						+"<td>"+result.studentData[i].bookAuthor+"</td>"
						+"<td>"+result.studentData[i].location+"</td>"
						+"<td>"+result.studentData[i].publisher+"</td>"
						+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records "+result.studentData.length);
		}
	});
}

function SearchBookSearchByaccNoandTitleAnyWhere(){
	var searchname = $("#searchValue").val();
	var select=$("input[name='order_by']:checked").val();
	var searching=$("input[name='started_by']:checked").val();
	var orderby=$("input[name='requested_by']:checked").val();
	
	$("#individualstudenttable").show();
	$("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<center><tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>Category</th>"
			+"</tr>"
			+"</center></table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchBookSearchByaccNoandTitleAnyWhere",
		data : {
			"searchname":searchname,
			"select":select,
			"searching":searching,
			"orderby":orderby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#individualstudenttable #allstudent").append("<tr>"
							+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
							+"<td>"+result.studentData[i].accessionNo+"</td>"
							+"<td>"+result.studentData[i].bookTitle+"</td>"
							+"<td>"+result.studentData[i].bookAuthor+"</td>"
							+"<td>"+result.studentData[i].location+"</td>"
							+"<td>"+result.studentData[i].category+"</td>"
							+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}

function SearchBookSearchByPublisherAnyWhere(){
	var searchname = $("#searchValue").val();
	var select=$("input[name='order_by']:checked").val();
	var searching=$("input[name='started_by']:checked").val();
	var orderby=$("input[name='requested_by']:checked").val();
	
	$("#individualstudenttable").show();
	$("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<center><tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>Publisher</th>"
			+"</tr>"
			+"</center></table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchBookSearchByPublisherAnyWhere",
		data : {
			"searchname":searchname,
			"select":select,
			"searching":searching,
			"orderby":orderby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#individualstudenttable #allstudent").append("<tr>"
							+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
							+"<td>"+result.studentData[i].accessionNo+"</td>"
							+"<td>"+result.studentData[i].bookTitle+"</td>"
							+"<td>"+result.studentData[i].bookAuthor+"</td>"
							+"<td>"+result.studentData[i].location+"</td>"
							+"<td>"+result.studentData[i].publisher+"</td>"
							+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}

function getAllBookItemTypeDetails(){
	var orderby=$("input[name='order_by']:checked").val();
	var  select=$("input[name='requested_by']:checked").val();
	
    $("#individualstudenttable").show();
    $("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>Item Type</th>"
			+"</tr>"
			+"</table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getAllBookItemTypeDetails",
		data : {
			"select":select,
			"orderby":orderby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
				$("#individualstudenttable #allstudent").append("<tr>"
						+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
						+"<td>"+result.studentData[i].accessionNo+"</td>"
						+"<td>"+result.studentData[i].bookTitle+"</td>"
						+"<td>"+result.studentData[i].bookAuthor+"</td>"
						+"<td>"+result.studentData[i].location+"</td>"
						+"<td>"+result.studentData[i].itemType+"</td>"
						+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records "+result.studentData.length);
		}
	});
}

function SearchBookSearchByItemTypeAnyWhere(){
	var searchname = $("#searchValue").val();
	var select=$("input[name='order_by']:checked").val();
	var searching=$("input[name='started_by']:checked").val();
	var orderby=$("input[name='requested_by']:checked").val();
	
	$("#individualstudenttable").show();
	$("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<center><tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>Item Type</th>"
			+"</tr>"
			+"</center></table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchBookSearchByItemTypeAnyWhere",
		data : {
			"searchname":searchname,
			"select":select,
			"searching":searching,
			"orderby":orderby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#individualstudenttable #allstudent").append("<tr>"
							+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
							+"<td>"+result.studentData[i].accessionNo+"</td>"
							+"<td>"+result.studentData[i].bookTitle+"</td>"
							+"<td>"+result.studentData[i].bookAuthor+"</td>"
							+"<td>"+result.studentData[i].location+"</td>"
							+"<td>"+result.studentData[i].itemType+"</td>"
							+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
function getAllBookDDCDetails(){
	var orderby=$("input[name='order_by']:checked").val();
	var  select=$("input[name='requested_by']:checked").val();
	
    $("#individualstudenttable").show();
    $("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>DDC</th>"
			+"</tr>"
			+"</table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getAllBookDDCDetails",
		data : {
			"select":select,
			"orderby":orderby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
				$("#individualstudenttable #allstudent").append("<tr>"
						+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
						+"<td>"+result.studentData[i].accessionNo+"</td>"
						+"<td>"+result.studentData[i].bookTitle+"</td>"
						+"<td>"+result.studentData[i].bookAuthor+"</td>"
						+"<td>"+result.studentData[i].location+"</td>"
						+"<td>"+result.studentData[i].ddc+"</td>"
						+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records "+result.studentData.length);
		}
	});
}
function SearchBookSearchByDDCAnyWhere(){
	var searchname = $("#searchValue").val();
	var select=$("input[name='order_by']:checked").val();
	var searching=$("input[name='started_by']:checked").val(); 
	var orderby=$("input[name='requested_by']:checked").val();
	
	$("#individualstudenttable").show();
	$("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<center><tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>DDC</th>"
			+"</tr>"
			+"</center></table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchBookSearchByDDCAnyWhere",
		data : {
			"searchname":searchname,
			"select":select,
			"searching":searching,
			"orderby":orderby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#individualstudenttable #allstudent").append("<tr>"
							+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
							+"<td>"+result.studentData[i].accessionNo+"</td>"
							+"<td>"+result.studentData[i].bookTitle+"</td>"
							+"<td>"+result.studentData[i].bookAuthor+"</td>"
							+"<td>"+result.studentData[i].location+"</td>"
							+"<td>"+result.studentData[i].ddc+"</td>"
							+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}

function getAllBookContentDetails(){
	var orderby=$("input[name='order_by']:checked").val();
	var  select=$("input[name='requested_by']:checked").val();
	
    $("#individualstudenttable").show();
    $("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>Content</th>"
			+"</tr>"
			+"</table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getAllBookContentDetails",
		data : {
			"select":select,
			"orderby":orderby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
				$("#individualstudenttable #allstudent").append("<tr>"
						+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
						+"<td>"+result.studentData[i].accessionNo+"</td>"
						+"<td>"+result.studentData[i].bookTitle+"</td>"
						+"<td>"+result.studentData[i].bookAuthor+"</td>"
						+"<td>"+result.studentData[i].location+"</td>"
						+"<td>"+result.studentData[i].content+"</td>"
						+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records "+result.studentData.length);
		}
	});
}
function SearchBookSearchByContentAnyWhere(){
	var searchname = $("#searchValue").val();
	var select=$("input[name='order_by']:checked").val();
	var searching=$("input[name='started_by']:checked").val();
	var ordeby=$("input[name='requested_by']:checked").val();
	
	$("#individualstudenttable").show();
	$("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<center><tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>Content</th>"
			+"</tr>"
			+"</center></table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchBookSearchByContentAnyWhere",
		data : {
			"searchname":searchname,
			"select":select,
			"searching":searching,
			"ordeby":ordeby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#individualstudenttable #allstudent").append("<tr>"
							+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
							+"<td>"+result.studentData[i].accessionNo+"</td>"
							+"<td>"+result.studentData[i].bookTitle+"</td>"
							+"<td>"+result.studentData[i].bookAuthor+"</td>"
							+"<td>"+result.studentData[i].location+"</td>"
							+"<td>"+result.studentData[i].content+"</td>"
							+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}
function getAllBookLanguageDetails(){
	var orderby=$("input[name='order_by']:checked").val();
	var  select=$("input[name='requested_by']:checked").val();
	
    $("#individualstudenttable").show();
    $("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>Language</th>"
			+"</tr>"
			+"</table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getAllBookLanguageDetails",
		data : {
			"select":select,
			"orderby":orderby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
				$("#individualstudenttable #allstudent").append("<tr>"
						+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
						+"<td>"+result.studentData[i].accessionNo+"</td>"
						+"<td>"+result.studentData[i].bookTitle+"</td>"
						+"<td>"+result.studentData[i].bookAuthor+"</td>"
						+"<td>"+result.studentData[i].location+"</td>"
						+"<td>"+result.studentData[i].language+"</td>"
						+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records "+result.studentData.length);
		}
	});
}

function SearchBookSearchByLanguageAnyWhere(){
	var searchname = $("#searchValue").val();
	var select=$("input[name='order_by']:checked").val();
	var searching=$("input[name='started_by']:checked").val();
	var orderby=$("input[name='requested_by']:checked").val();

	$("#individualstudenttable").show();
	$("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<center><tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>Language</th>"
			+"</tr>"
			+"</center></table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchBookSearchByLanguageAnyWhere",
		data : {
			"searchname":searchname,
			"select":select,
			"searching":searching,
			"orderby":orderby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#individualstudenttable #allstudent").append("<tr>"
							+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
							+"<td>"+result.studentData[i].accessionNo+"</td>"
							+"<td>"+result.studentData[i].bookTitle+"</td>"
							+"<td>"+result.studentData[i].bookAuthor+"</td>"
							+"<td>"+result.studentData[i].location+"</td>"
							+"<td>"+result.studentData[i].language+"</td>"
							+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
		}
	});
}

function getAllBookSupplierDetails(){
	var orderby=$("input[name='order_by']:checked").val();
	var  select=$("input[name='requested_by']:checked").val();
	
    $("#individualstudenttable").show();
    $("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>Supplier</th>"
			+"</tr>"
			+"</table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getAllBookSupplierDetails",
		data : {
			"select":select,
			"orderby":orderby
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
				$("#individualstudenttable #allstudent").append("<tr>"
						+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
						+"<td>"+result.studentData[i].accessionNo+"</td>"
						+"<td>"+result.studentData[i].bookTitle+"</td>"
						+"<td>"+result.studentData[i].bookAuthor+"</td>"
						+"<td>"+result.studentData[i].location+"</td>"
						+"<td>"+result.studentData[i].supplier+"</td>"
						+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records "+result.studentData.length);
		}
	});
}
function SearchBookSearchBySupplierAnyWhere(){
	var searchname = $("#searchValue").val();
	var select=$("input[name='order_by']:checked").val();
	var searching=$("input[name='started_by']:checked").val();
	var orderby=$("input[name='requested_by']:checked").val();
	
	$("#individualstudenttable").show();
	$("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<center><tr>"
			+"<th>Select</th>" 
			+"<th>AccessionNo</th>"
			+"<th>Title</th>"
			+"<th>Author</th>"
			+"<th>Location</th>"
			+"<th>Supplier</th>"
			+"</tr>"
			+"</center></table>");
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=SearchBookSearchBySupplierAnyWhere",
		data : {
			"searchname":searchname,
			"select":select,
			"searching":searching,
			"orderby":orderby,
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			var i=0;
			var len=result.studentData.length;
			if(len > 0){
				for(i=0;i<len;i++){
					$("#individualstudenttable #allstudent").append("<tr>"
							+"<td><input type='radio' name='entryid' value='"+result.studentData[i].entryId+"' class='accessNo'/></td>" 
							+"<td>"+result.studentData[i].accessionNo+"</td>"
							+"<td>"+result.studentData[i].bookTitle+"</td>"
							+"<td>"+result.studentData[i].bookAuthor+"</td>"
							+"<td>"+result.studentData[i].location+"</td>"
							+"<td>"+result.studentData[i].supplier+"</td>"
							+"</tr>");
				}	
			}
			else{
				$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records "+result.studentData.length);
		}
	});

}