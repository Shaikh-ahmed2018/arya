$(document).ready(function()
{
	var saveFlag=true;
	$(".statusvalue").change(function(){
		if(this.value=="Absent"){
			$(this).closest("tr").find(".scoredmarks").val(0);
			$(this).closest("tr").find(".scoredmarks").attr("readonly",true);
		}
		else{
			$(this).closest("tr").find(".scoredmarks").val("");
			$(this).closest("tr").find(".scoredmarks").attr("readonly",false);
		}
		
  });
	
	if($("#tablesize").val() == "nodata"){
	
		$("#allstudent tbody").append("<tr><td colspan='7'>No Records Found</td></tr>");
	}
	
	
	$(".scoredmarks").blur(function(){
		
		var maxmarks = $(this).closest("tr").find(".totalmarks").text(); 
			marks = parseInt(maxmarks);
		var getData = $(this).val();
			data = parseInt(getData);
			 if(data < 0)
		        {
				 $(".errormessagediv").show();
					$(".scoredmarks").val("");
					document.getElementsByClassName("scoredmarks").style.border = "1px solid #AF2C2C";
					document.getElementsByClassName("scoredmarks").style.backgroundColor = "#FFF7F7";
					$(".validateTips").text("Scored Marks Should be Greater than or Equal to 0");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			 }
			 else if(data > marks){
					 $(this).val("");
					 $(this).css({'border-color':'red'});
					 $(".errormessagediv").show();
					 $(".validateTips").text("Scored Marks Should be Less Than Maximum Marks");
					 setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
					 return false;
			}
	});
	
	
	
	    $("#save").click(function(){
	    scoredmarks=[];
	    studentids=[];
		statusvalues=[];
        count=0;
	
		  $('.scoredmarks').each(function() {
					var getData = $(this).val();
				    data = parseInt(getData);
		            if(getData !="" && getData!= undefined){
		        		count++;
		        	}
					scoredmarks.push(getData);
				
		  });
		  

		  $('.stuid').each(function()
				  {
				var getData = $(this).attr("class").split(" ")[1];
				studentids.push(getData);
				
		  });
		  $('.statusvalue').each(function()
				  {
				var getData = $(this).val();
				statusvalues.push(getData);
				
		  });
		  primaryid=[];
		  $('.subjectid').each(function() {
				var getData = $(this).val();
				primaryid.push(getData);
		  });
		
		 /* $(".scoredmarks").change(function(){
			  if(this.value < 0)
			        {
					 $(".errormessagediv").show();
						$(".scoredmarks").val("");
						document.getElementsByClassName("scoredmarks").style.border = "1px solid #AF2C2C";
						document.getElementsByClassName("scoredmarks").style.backgroundColor = "#FFF7F7";
						$(".validateTips").text("Scored Marks Should be Greater than 0");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false;
				 }
		  });*/
		  
		  if(count!= $('.totalmarks').length)
			{
			
			  $("#allstudent tbody tr[id]").each(function(){
	 				
					if($(this).find('input.scoredmarks').val() == "" || $(this).find('.scoredmarks').val()==undefined )
					{
						
					$(this).find("input.scoredmarks").css({'border-color':'red'});
					$(".errormessagediv").show();
					$(".validateTips").text("Enter marks");

					}
				});
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementByClass("scoredmarks").style.border = "1px solid #ccc";
				document.getElementByClass("scoredmarks").style.backgroundColor = "#fff";
			}, 500);
		 }
	 		
		  
		  else
			  {
		var classId =$("#classhiddenid").val();
        var sectionId =$("#hiddensectionid").val();
  		var examid = $("#hiddenexamid").val();
 		var accyear = $("#hiddenaccyid").val();
 		var hiddensubid=$("#hiddensubid").val();
 		
 	
		 var datalist = {
				  "primaryid":primaryid,
				  "classId":classId,
				  "sectionId":sectionId,
				  "examid":examid,
				  "accyear":accyear,
				  "scoredmarks":scoredmarks,
				  "statusvalues":statusvalues,
				  "studentids":studentids,
				  "hiddensubid":hiddensubid,
				  "location" :$("#hiddenlocid").val()
		  };

 	      $.ajax({
 			type : "POST",
 			url : "examCreationPath.html?method=InsertMarkEntrySubjectWise",
 			data : datalist,
 			async : false,
 			success : function(data) {
 				var result = $.parseJSON(data);
 				if(result.status =="inserted") {
 					$(".successmessagediv").show();
 					$(".validateTips").text("Adding Marks is Progressing...");
 					var examid =$("#hiddenexamid").val();
 					var classId=$("#classhiddenid").val();
 					var sectionId=$("#hiddensectionid").val();
 					var accyear=$("#hiddenaccyid").val();
 					hschoolLocation=$("#hiddenlocid").val();
 					setTimeout(function(){
 						window.location.href="examCreationPath.html?method=classWiseSubject&classId="+classId+"&sectionId="+sectionId+"&examid="+examid+"&accyear="+accyear+"&hschoolLocation="+hschoolLocation; 
 					},3000);

 				}

 			}
 		});
			  }
});
       $("#back").click(function(){
     	  var examid =$("#hiddenexamid").val();
           var classId=$("#classhiddenid").val();
           var sectionId=$("#hiddensectionid").val();
           var accyear=$("#hiddenaccyid").val();
           hschoolLocation=$("#hiddenlocid").val();
     	window.location.href="examCreationPath.html?method=classWiseSubject&classId="+classId+"&sectionId="+sectionId+"&examid="+examid+"&accyear="+accyear+"&hschoolLocation="+hschoolLocation; 
      });
       
       $('#allstudent tbody tr td').each(function(){
    	   
      		
    	   	
    	   var status=$(this).find('.statusvalue').attr('class');
   		if(status!=undefined){
   			status1 = status.split(" ")[1];
   	
   		var rowid=$(this).find('.statusvalue').attr("id");
   		
   		$('#'+rowid).find('option').remove();
   	
   		var statusList=[];
   		statusList.push("Present");
   		statusList.push("Absent");
   	
   		for (var j = 0; j < statusList.length; j++) {
   			

   			$("#"+rowid).append(
   							'<option value="'
   									+ statusList[j]
   									+ '">'
   									+  statusList[j]
   									+ '</option>');
   		}
   		
   		if(status1 != undefined)
   		$("#"+rowid+" option[value="+status1+"]").attr('selected', 'true');
   		if(status1=="Absent"){
			$(this).closest("tr").find(".scoredmarks").val(0);
			$(this).closest("tr").find(".scoredmarks").attr("readonly",true);
		}
   		}
   		
   		
   	});
       
       
       

});