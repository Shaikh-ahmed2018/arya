$(document).ready(function() {
	
	
	

	if(!$("#successid").val()=="")
	
	{
		$("#txtstyle, #txtstyle").hide();
		
		$("#allstudent").show();
		$(".selecteditems").show();
		
		
	}
	else
	{
		$("#allstudent").hide();
		$(".selecteditems").hide();
		
	}
	
	
	
	
	
	
	
	$("#category").change(function(){
		
		
		
		var category=$("#category").val();
		
		
		
		

		datalist={
				"category" : category
				
		},
		
		$.ajax({
			
			type : 'POST',
			url : "phonedirectory.html?method=getPersonNameListAction",
			data : datalist,
			async : false,
			success : function(response) {
				
				
				var result = $.parseJSON(response);
				
				
				
				
				$('#selectname').html("");
				
				$('#selectname')
				.append(
						'<option value="'
						+ ""
						+'">'
						+ ""
								
								
								+ '</option>');
		$('#selectname').append(
				'<option value="'
						+ "all" + '">'
						+ "ALL"
						+ '</option>');
		for ( var j = 0; j < result.phonedirectorylist.length; j++) {
			$('#selectname')
					.append(
							'<option value="'
									+ result.phonedirectorylist[j].id
									+ '">'
									+ result.phonedirectorylist[j].name
									+ '</option>');
		}
				
			}
			
		});
		
		
		
	});
	
	
	
	
	$("#search").click(function(){
		
		
		var category=$("#category").val();
	     var selectname=$("#selectname").val();
		
		
	     if(category=="" && selectname==""){
				
				$("#txtstyle, #txtstyle").slideToggle();
				
			}
	 	if(category==""){
	 		
	 		$('.errormessagediv').show();
	 		$('.validateTips').text("Select Category Year");
	 		
	 		return false;
	 		
	 	}
	 	
	 	if(selectname==""){
	 		
	 		$('.errormessagediv').show();
	 		$('.validateTips').text("Select Name");
	 		
	 		return false;
	 		
	 	}
	 	else{
	 		
	 		return true;
	 	}
	 	
	 	
		
		
		
	});
	
	
	
	
	
	$("#excelDownload").click(function(){
		
		
		var category=$("#hcategory").val();
		  var selectname=$("#hname").val();
		  
		  
		  
		  
			window.location.href = 'phonedirectory.html?method=phonedirectoryExcelReport&category='
				+category
				+ ' &selectname='
				+selectname
				 ;
		  
		
		
	});
	
	
	
	
	$("#pdfDownload").click(function(){
		
		
		var category=$("#hcategory").val();
		  var selectname=$("#hname").val();
		  
		  
			window.location.href = 'phonedirectory.html?method=phonedirectoryPdfReport&category='
				+category
				+ ' &selectname='
				+selectname
				 ;
		  
		
		
	});
	
	
	
	
	
});