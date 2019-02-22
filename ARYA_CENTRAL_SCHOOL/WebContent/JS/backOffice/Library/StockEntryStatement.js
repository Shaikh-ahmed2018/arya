$(document).ready(function(){
/*	getstockEntryList();*/

	$("#regDate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
	});
	
	$("#schoolName").change(function(){
		getLibraryLocation();
	});
	$("#locationName").change(function() {
		
		if(this.value ==""){
			$("#itemType").empty();
			$("#itemType").append('<option value=ALL>----------Select----------</option>');
			$("#bookTitle").empty();
			$("#bookTitle").append('<option value=ALL>----------Select----------</option>');
			$("#author").empty();
			$("#author").append('<option value=ALL>----------Select----------</option>');
			$("#publisher").empty();
			$("#publisher").append('<option value=ALL>----------Select----------</option>');
		
		
		}
		else{
			$(".pagebanner").show();
			$(".numberOfItem").show();
			$(".pagination").show();
			$("#allstudent").show();
			
			$("#itemType").empty();
			getItemList();
			$("#bookTitle").empty();
			getbookList();
			$("#author").empty();
			getAuthorList();
			$("#publisher").empty();
			getPublist();
			getstockEntryList();
			
		}
		
	});
	$("#itemType").change(function() {
	
		getstockEntryList();
	});
	$("#bookTitle").change(function() {
	
		getstockEntryList();
	});
	$("#author").change(function() {
	
		getstockEntryList();
	});
	$("#publisher").change(function() {
	
		getstockEntryList();
	});
	
	
	$("#pdfDownload").click(function(){
		if($("#locationName").val()=="all" ||$("#locationName").val()=="" || $("#locationName").val()==null || 
				$("#locationName").val()==undefined)
 	   {
			$(".errormessagediv").show();
   			$(".validateTips").text(" Select Location ");
   			showError("#locationName");
   			setTimeout(function() {
   				$('.errormessagediv').fadeOut();
   			  }, 3000);
   			return false;
 	   }
		else{
	   			window.location.href ='LibraryMenu.html?method=StockEntryReportPdf&LocId='+$("#locationName").val()
	   			+'&itemType='+$("#itemType").val()+'&bookTitle='+$("#bookTitle").val()
	   			+'&author='+$("#author").val()+'&publisher='+$("#publisher").val()
	   			
	   			+'&locationname='+$("#locationName option:selected").text()+
	   			"&itemType="+$("#itemType option:selected").text()+
	   			"&bookTitle="+$("#bookTitle option:selected").text()+
	   			"&author="+$("#author option:selected").text()+"&publishernm="+$("#publisher option:selected").text();
		}
	});
	
       $("#excelDownload").click(function(){
    	   
    	   if($("#locationName").val()=="all" ||$("#locationName").val()=="" || $("#locationName").val()==null || 
   				$("#locationName").val()==undefined)
    	   {
   			$(".errormessagediv").show();
      			$(".validateTips").text(" Select Location ");
      			showError("#locationName");
      			setTimeout(function() {
      				$('.errormessagediv').fadeOut();
      			  }, 3000);
      			return false;
    	   }
    	   else{
    	   window.location.href = 'LibraryMenu.html?method=StockEntryExelReport&LocId='+$("#locationName").val()
   		+'&itemType='+$("#itemType").val()+'&bookTitle='+$("#bookTitle").val()
   		+'&author='+$("#author").val()+'&publisher='+$("#publisher").val()
   		
   		+'&locationname='+$("#locationName option:selected").text()+
   		"&itemType="+$("#itemType option:selected").text()+
   		"&bookTitle="+$("#bookTitle option:selected").text()+
   		"&author="+$("#author option:selected").text()+"&publishernm="+$("#publisher option:selected").text();
    	   }
    	   
		});
	

       $("#print").click(function(){
    	   
    	   if($("#locationName").val()=="all" ||$("#locationName").val()=="" || $("#locationName").val()==null || 
      				$("#locationName").val()==undefined)
       	   {
      			$(".errormessagediv").show();
         			$(".validateTips").text(" Select Location ");
         			showError("#locationName");
         			setTimeout(function() {
         				$('.errormessagediv').fadeOut();
         			  }, 3000);
         			return false;
       	   }
    	   else{
   		$.ajax({
   			type:"post",
   			url:'LibraryMenu.html?method=StockEntryReportPrint&LocId='+$("#locationName").val()
   	   		+'&itemType='+$("#itemType").val()+'&bookTitle='+$("#bookTitle").val()
   	   		+'&author='+$("#author").val()+'&publisher='+$("#publisher").val()
   	   		
   	   		+'&locationname='+$("#locationName option:selected").text()+
   	   		"&itemType="+$("#itemType option:selected").text()+
   	   		"&bookTitle="+$("#bookTitle option:selected").text()+
   	   		"&author="+$("#author option:selected").text()+"&publishernm="+$("#publisher option:selected").text(),
   			success:function(data){
   				
   			}
   		   });
    	 }
    	   
   	});
	
       pagination(100);
});



function getLibraryLocation(){
	 var id = $("#schoolName").val();
	 datalist={
			 "id":id,
	 };
	 $.ajax({
		type:'POST',
		url:"LibraryMenu.html?method=getLibraryLocationBySchool",
		data: datalist,
	 	async:false,
	 	success: function(data){
	 		var result = $.parseJSON(data);
			$("#locationName").empty();
			$("#locationName").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
			$("#locationName").append("<option value='"+result.data[i].libraryLocationName+"'>" 
					+result.data[i].libraryLocationId+"</option>");
			}
	 		}
	 });
}	



function getItemList(){
	datalist={
			
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getItemList",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#itemType").empty();
			 $("#itemType").append("<option value='ALL'>ALL</option>");
			for(var i=0;i<result.itemlist.length;i++){
				 $("#itemType").append("<option value='"+result.itemlist[i].itemType+"'>"+result.itemlist[i].itemType+"</option>");
			}
		}
	});
		
}

function getbookList(){
	datalist={
			
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getbookList",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#bookTitle").empty();
			 $("#bookTitle").append("<option value='ALL'>ALL</option>");
			for(var i=0;i<result.booklist.length;i++){
				 $("#bookTitle").append("<option value='"+result.booklist[i].bookTitle+"'>"+result.booklist[i].bookTitle+"</option>");
			}
		}
	});
		
}

function getAuthorList(){
	datalist={
			
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getAuthorList",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#author").empty();
			 $("#author").append("<option value='ALL'>ALL</option>");
			for(var i=0;i<result.authorlist.length;i++){
				 $("#author").append("<option value='"+result.authorlist[i].author+"'>"+result.authorlist[i].author+"</option>");
			}
		}
	});
		
}



function getPublist(){
	datalist={
			
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=	getPublist",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#publisher").empty();
			 $("#publisher").append("<option value='ALL'>ALL</option>");
			for(var i=0;i<result.publist.length;i++){
				 $("#publisher").append("<option value='"+result.publist[i].entry_id+"'>"+result.publist[i].publisher+"</option>");
			}
		}
	});
		
}







function getstockEntryList(){
	datalist={
			"location":$("#locationName").val(),
			"itemType":$("#itemType").val(),
			"regDate":$("#regDate").val(),
			"bookTitle":$("#bookTitle").val(),
			"author":$("#author").val(),
			"publisher":$("#publisher").val(),
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getstockEntryList",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.stocklist.length;
		  if(len > 0){
			for(i=0;i<len;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td>"+(i+1)+"</td>"+
						"<td>"+result.stocklist[i].accessionNo+"</td>"+
						"<td>"+result.stocklist[i].itemType+"</td>"+
						"<td>"+result.stocklist[i].regDate+"</td>"+
						"<td>"+result.stocklist[i].bookTitle+"</td>"+
						"<td>"+result.stocklist[i].author+"</td>"+
						"<td>"+result.stocklist[i].publisher+"</td>"+
						"<td>"+result.stocklist[i].no_of_Copies+"</td>"+
						"<td>"+result.stocklist[i].location +"</td>"+
						"</tr>"
				    );
				
			}
		  }
			 else{
				 $("#allstudent tbody").append("<tr><td colspan='8'>No Record Found</td></tr>");
			    }
			$(".numberOfItem").empty();
			$(".numberOfItem").append(" No. of Records "+len);
			pagination(100);
		}
	});
		
}

function getlocationlist(){
	datalist={
			"location":$("#locationName").val(),
			"itemType":$("#itemType").val(),
			"regDate":$("#regDate").val(),
			"bookTitle":$("#bookTitle").val(),
			"author":$("#author").val(),
			"publisher":$("#publisher").val(),
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getlocationlist",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
		    if(result.stocklist.length>0){		   
			for(var i=0;i<result.stocklist.length;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td><input type='checkbox' name='select' class='select'  value='"+result.stocklist[i].entry_id+"'/></td>"+
						"<td>"+result.stocklist[i].accessionNo+"</td>"+
						"<td>"+result.stocklist[i].itemType+"</td>"+
						"<td>"+result.stocklist[i].regDate+"</td>"+
						"<td>"+result.stocklist[i].bookTitle+"</td>"+
						"<td>"+result.stocklist[i].author+"</td>"+
						"<td>"+result.stocklist[i].publisher+"</td>"+
						"<td>"+result.stocklist[i].no_of_Copies+"</td>"+
						"<td>"+result.stocklist[i].location +"</td>"+
						"</tr>"
				
				);
				
			}
		    }
		    else{
		    	$("#allstudent tbody").append("<tr><td ColSpan='10'>No Records Found</td></tr>");
		    }
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.stocklist.length);
			pagination(100);
		}
	});
}

function showError(id){
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
}

