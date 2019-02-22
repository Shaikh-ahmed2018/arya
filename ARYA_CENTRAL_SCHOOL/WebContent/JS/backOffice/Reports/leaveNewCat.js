$(document).ready(function() {
	
	 var categoryId =[];
	 var status = 0;
	 var leaveNames=[];
	 var shortNames= [];
	 var noofLeaves =[];
	 var saveFlag=true;
	 var shortnam = false;
	 var flag = false;
	 var statusflag = true;
	 $("#academicyear").val($("#hiddenaccyear").val());
	 $("#locationname").val($("#hiddenloc").val())
	 
	  var hiddenLEaveId =$(this).closest('tr').find('input[type=hidden]').val();
	
	  var pageurl=window.location.href.substring(window.location.href.lastIndexOf("=")+1);
		if(pageurl!="addingleaveCategoryscreen"){
			$("#myTable").attr("style","display:table");
			$("#myTable tbody tr").each(function(){
				$("#tableid .select[value="+$(this).attr("id")+"]").attr("checked",true);
			});
		}
		$(".select").change(function(){
			if($(this).prop("checked")==false){
				$(".selectAll").prop("checked",false);
			}
			else{
				$(".select").each(function(){
					if($(this).prop("checked")==false){
						selectstatus=false;
						return false;
					}
					else{
						selectstatus=true;
					}
					
				});
				if(selectstatus)
					$(".selectAll").prop("checked",true);
					else
					$(".selectAll").prop("checked",false);
			}
		});
	 	$(".selectAll").change(function(){
			$(".select").prop("checked", $(this).prop("checked"));
			
				if($(this).prop("checked")==true){
					$('.CatType').prop('disabled',false, $(this).is(":checked"));
					 $('.shortName').prop('disabled',false, $(this).is(":checked"));
					 $('.noofleaves').prop('disabled',false, $(this).is(":checked"));
				}
				else{
					
					 $(".errormessagediv").hide();
					 $('.CatType').css({'border-color':'initial'});
					 $('.shortName').css({'border-color':'initial'});
					 $('.noofleaves').css({'border-color':'initial'});
					 $('.CatType').val("");
					 $('.shortName').val("");
					 $('.noofleaves').val("");
					 $('.CatType').prop("disabled",true);
					 $('.shortName').prop('disabled',true);
					 $('.noofleaves').prop('disabled',true);
				}
		});
	 
	 	
			 
			 $('td input[type="checkbox"]').change(function () {
				 
				 if($(this).prop("checked")==true){
				 
				  $(this).closest('tr').find('input[type="text"]').prop('disabled',false, $(this).is(":checked"));
			
				 }
				 else{
					 $(this).closest('tr').find('input[type="text"]').val("");
					 $(this).closest('tr').find('input[type="text"]').css({'border-color':'initial'});
					 $(this).closest('tr').find('input[type="text"]').prop('disabled',true);
					 $(".errormessagediv").hide();
					 
				 }
			 });
	
	$(".shortName").keyup(function(){
		
		$(this).val($(this).val().toUpperCase());
		
		
	});
		
	
	
	
	$(".shortName").blur(function(){
		
		 var len = $(this).val();
		 
		 if(len.length > 3){
			 $(".errormessagediv1").show();
			 $(".validateTips1").text("Maximum 3 Charecters are allowed for Leave Short Name");
			 $(this).parent('td').parent('tr').find('input[name="shortName"]').val("");
			 $(this).parent('td').parent('tr').find('input[name="shortName"]').css({'border-color':'#f00'});
			
			setTimeout(function() {
				$('.errormessagediv1').fadeOut();
				}, 3000);
			
			}
		 else if(!len.match(/^[a-zA-Z-,]+(\s{0,1}[a-zA-Z-, ])*$/)){
			 $(".errormessagediv1").show();
			 $(".validateTips1").text("Only Charecters are allowed for Leave Short Name");
			 $(this).parent('td').parent('tr').find('input[name="shortName"]').val("");
			 $(this).parent('td').parent('tr').find('input[name="shortName"]').css({'border-color':'#f00'});
			setTimeout(function() {
				$('.errormessagediv1').fadeOut();
				}, 3000);
			
		 }
		 	var holidayFlag=false;
			var enterholiday=$(this).val();
			var currenth=$(this);
			
			/*$("#tableid tbody tr[id]").find(".shortName").not(currenth).each(function(){
				if(enterholiday.toLowerCase()==$(this).val().toLowerCase()){
					holidayFlag=true;
				}
			});
			if(holidayFlag){
				$(this).val("");
				$(".errormessagediv1").show();
				$(".errormessagediv1 .validateTips").text("Duplicate Leave Short Names Are Not Allowed");
				setTimeout(function() {
					$('.errormessagediv1').fadeOut();
	 			}, 3000);
			}*/

		 $('.select:checked').each(function(){
			  var cattype= $(this).closest('tr').find('input[name=CatType]').val();
       	  var shortName = $(this).closest('tr').find('input[name=shortName]').val();
       	  var noofleave =  $(this).closest('tr').find('input[name=noofleaves]').val();
       	 if(cattype == "" || shortName == "" || noofleave == undefined){
   		  statusflag = false;
       	 }
       	 else{
       		 statusflag = true;
       	 }
		  });
	});
	
	$(".CatType").change(function(){
			cat = $(this).val();
			
			if(!cat.match(/^[a-zA-Z-,]+(\s{0,1}[a-zA-Z-, ])*$/)){
				$(".errormessagediv1").show();
				$(".validateTips1").text("Only Charecters are allowed for Leave Type Name");
			 $(this).parent('td').parent('tr').find('input[name="CatType"]').val("");
			 $(this).parent('td').parent('tr').find('input[name="CatType"]').css({'border-color':'#f00'});
			setTimeout(function() {
				$('.errormessagediv1').fadeOut();
				}, 3000);
			}
			var holidayFlag=false;
			var enterholiday=$(this).val();
			var currenth=$(this);
			
			$("#tableid tbody tr[id]").find(".CatType").not(currenth).each(function(){
				
				if(enterholiday.toLowerCase()==$(this).val().toLowerCase()){
				
					holidayFlag=true;
				}
			});
			if(holidayFlag){
				$(this).val("");
				$(".errormessagediv1").show();
				$(".errormessagediv1 .validateTips1").text("Duplicate Leave Description Not Allowed");
				
				setTimeout(function() {
					$('.errormessagediv1').fadeOut();
	 			}, 3000);
			}
		/*	 checkLeaveType($(this));*/
			 $('.select:checked').each(function(){
				
				  var cattype= $(this).closest('tr').find('input[name=CatType]').val();
	        	  var shortName = $(this).closest('tr').find('input[name=shortName]').val();
	        	  var noofleave =  $(this).closest('tr').find('input[name=noofleaves]').val();
	        	 
	        	  if(cattype == "" || shortName == "" || noofleave == undefined){
	           		  statusflag = false;
	               	 }
	               	 else{
	               		 statusflag = true;
	               	 }
			  });
	});

	$(".noofleaves").change(function(){
		
		var no = $(this).val();
		
		if(no <=0){
			
			$(".errormessagediv1").show();
			 $(".validateTips1").text("No of Leaves Should be Greater Than '0' ");
			 $(this).parent('td').parent('tr').find('input[name="noofleaves"]').val("");
			 $(this).parent('td').parent('tr').find('input[name="noofleaves"]').css({'border-color':'#f00'});
			
			setTimeout(function() {
				$('errormessagediv1').fadeOut();
				}, 3000);
			
		}
		else if(!no.match(/^[0-9]+$/)){
			$(".errormessagediv1").show();
			 $(".validateTips1").text("Only Numbers are allowed for No.of Leaves");
			 $(this).parent('td').parent('tr').find('input[name="noofleaves"]').val("");
			 $(this).parent('td').parent('tr').find('input[name="noofleaves"]').css({'border-color':'#f00'});
	
			setTimeout(function() {
				$('.errormessagediv1').fadeOut();
				}, 3000);
			
		}
		
		
		 $('.select:checked').each(function(){
			  var cattype= $(this).closest('tr').find('input[name=CatType]').val();
       	  var shortName = $(this).closest('tr').find('input[name=shortName]').val();
       	  var noofleave =  $(this).closest('tr').find('input[name=noofleaves]').val();
       	  
       	if(cattype == "" || shortName == "" || noofleave == undefined){
     		  statusflag = false;
         	 }
         	 else{
         		 statusflag = true;
         	 }
       	  
		  });
		
	});	
	
	$("#dialog").dialog({
	
		 	autoOpen  : false,
		    modal     : true,
		    title     : "Leave Types",
		    buttons : {
		    	
		    	 "Yes" : function() {

		    			var accyear = $("#academicyear").val();
		    			var location = $("#locationname").val();
		    			
		    					var jsonObject = {
		    					
		    					"accyear" : accyear,
		    					'categorynames': leaveNameArray,
		    					'shortnames':shortNameArray,
		    					'noofleaves':noofLeavesArray,
		    					'catId' :cat_Id,
		    					"location" :location,
		    					"hiddenLEaveIdArray" : hiddenLEaveIdArray
		    					
		    			};
		    					
		    					$.ajax({
		    						
		    						type: "GET",
		    				 		url: "leavebank.html?method=addLeavesCategory",
		    				        data:jsonObject,
		    				        success: function(data) {
		    
		    				 			var result = $.parseJSON(data);
		    				 			if(result.validate == "Added Successfully"){
		    				 				
		    				 			$(".successmessagediv").show();
		    							$(".validateTips").text("Adding Record Progressing...");
		    							setTimeout(function() {
		    								window.location.href="adminMenu.html?method=LeaveCategoryList";
		    								}, 3000);
		    				 			}
		    				 		  } 
		    					});
		    				
		    		 $(this).dialog("close");
		    	 },
		    	 "No" : function() {
			            $(this).dialog("close");
			          }
		    }
	});
	
	
	$("#myDialog").dialog({
		
		 autoOpen  : false,
		    modal     : true,
		    title     : "Leave Types",
		    buttons   : {
		    	
		    	
	 			"Save": {
		    	 text: 'Save', 
		         id: 'test',  
		         click: function() {
		    		 cat_Id =[];
		    		 cat_type =[];
		    		 leaveNameArray=[];
		    		 shortNameArray= [];
		    		 noofLeavesArray =[];
		    		 hiddenLEaveIdArray=[];
		    		
		    		 count =0;
		    		 $("#myTable tbody").html(""); 
		    		 rowCount=1;
		    		 $('.select:checked').each(function(){
		    			
		    			  count++;
	                	  var check=$(this).val();
	                	  cat_Id.push(check);
	                	  cat_type.push($(this).parent('td').next().text());
	                	  var cattype= $(this).closest('tr').find('input[name=CatType]').val();
	                	  var shortName = $(this).closest('tr').find('input[name=shortName]').val();
	                	  var noofleave =  $(this).closest('tr').find('input[name=noofleaves]').val();
	                	  var hiddenLEaveId =$(this).closest('tr').find('input[name=hiddenleaveId]').val();
	                	
	                	  
	                	  if(cattype =="" || cattype == undefined ){
	                		  statusflag = false;
	                	  }
	                	  
	                	  else if(cattype !=undefined && cattype !=""){
	                		  leaveNameArray.push(cattype);
	                	  }
	                	 
	                	  if(shortName =="" || shortName ==undefined){
	                		  statusflag = false;
	                	  }
	                	  
	                	  else if(shortName !=undefined && shortName !=null){
	                		  shortNameArray.push(shortName);
	                	  }
	                	 
	                	  if(noofleave== undefined || noofleave == "")
		                	{
	                		  statusflag = false;
		                	}
	                	  
	                	  else if(noofleave!= undefined && noofleave != "")
	                	  {
	                		  noofLeavesArray.push(noofleave);
	                	  }
	                	 
	                	 if(hiddenLEaveId!= undefined && hiddenLEaveId != "")
		                	{
	                		 hiddenLEaveIdArray.push(hiddenLEaveId);
		                	}
	                	 
	                  });

		    		 
		    		 	if(leaveNameArray == "" || shortNameArray == "" || noofLeavesArray == undefined ){
		    		 		
		    		 		 flag = false;
			  				 $(".errormessagediv1").show();
			  				 $(".validateTips1").text("Fill the Required Information");
			  				setTimeout(function() {
			  					$('.errormessagediv1').fadeOut();
			  					}, 3000);
		    		 		
		    		 	}
		    		 
		    		 	else if(statusflag == false){
		    		 			flag = false;
		    		 		$(".errormessagediv1").show();
		    		 		 $(".validateTips1").text("Selected items cannot be Empty");
			  				setTimeout(function() {
			  					$('.errormessagediv1').fadeOut();
			  					}, 3000);
		    		 		
		    		 	}
		    		 else{ 
		    			 flag = true;
		    		   $("#myTable").attr("style","display:table");   
		    		  
		    		   for(var k=0;k<leaveNameArray.length;k++){ 
		    			   $("#myTable tbody").append("<tr><td>"+(k+1)+"</td><td>"+leaveNameArray[k]+"</td><td>"+shortNameArray[k]+"</td><td>"+noofLeavesArray[k]+"</td></tr>")
		    		   }
		    		   $(this).dialog('close');	
		    		}
		    	 }
	 			} ,
	 			'Close' : function() {
	                  $(this).dialog('close');
	                  $(".errormessagediv").hide();
	              }
		    }
	});
	
	
	$("#test").change(function(){
		

		 $('.select:checked').each(function(){
			 
			
			 var cattype = $(this).closest('tr').find('input[name=CatType]').val();
			 
		
		 });
	});
	
	
	$('#addnewCategory').click(function() {
		
			flag = true;
		  	var accyear = $("#academicyear").val();
			var location = $("#locationname").val();
			

			if(accyear == "" || accyear == null){
				flag = false;
				$(".errormessagediv").show();
				$(".validateTips").text(
						"Field Required - Accademic Year");
				$("#academicyear").focus();
				document.getElementById("academicyear").style.border = "1px solid #AF2C2C";
				document.getElementById("academicyear").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
				$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}
			else if(location == "" || location == null){
				flag = false;
				$(".errormessagediv").show();
				$(".validateTips").text(
						"Field Required - Location Name");
				$("#locationname").focus();
				document.getElementById("locationname").style.border = "1px solid #AF2C2C";
				document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
				$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
				
			}
			else if(accyear !="" && location != ""){
				count = checkDuplicacy();
				if(count == 1){
					flag = false;
					$(".errormessagediv").show();
		   			 $(".validateTips").text("Leave Types are Already Configured for the selected Combinations ");
		   			$(".errormessagediv").css({'margin-left':'0px'});
		   			$(".message-item").css({'width':'1500px'});
		   			 setTimeout(function() {
		   				$('.errormessagediv').fadeOut();
		   				}, 3000);
				}
				else{
					flag = false;
					$("#myDialog").dialog("open");
					return false;
				}
			}
	});

	$("#submit").click(function(){
		var accyear = $("#academicyear").val();
		var location = $("#locationname").val();
	if(flag){
		
		if(accyear == "" || accyear == null){
			$(".errormessagediv").show();
			$(".validateTips").text(
					"Field Required - Accademic Year");
			$("#academicyear").focus();
			document.getElementById("academicyear").style.border = "1px solid #AF2C2C";
			document.getElementById("academicyear").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
			$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(location == "" || location == null){
			$(".errormessagediv").show();
			$(".validateTips").text(
					"Field Required - Location Name");
			$("#locationname").focus();
			document.getElementById("locationname").style.border = "1px solid #AF2C2C";
			document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
			$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else{
	 $("#dialog").dialog("open");
	 return false;
		}
	}
	else{
		 $(".errormessagediv").show();
			 $(".validateTips").text("Add the Leave Types Information");
			 setTimeout(function() {
	   				$('.errormessagediv').fadeOut();
	   				}, 3000);
			 return false;
	}
	});
});

function checkLeaveType(cate){

	 var accyear = $("#academicyear").val();
	 var location = $("#locationname").val();
	 status = null;
	 datalist = {
			
			 "accyear":accyear,
			 "cat" : cate.val(),
			 'location' :location
			 
	 },
	 $.ajax({
		
		 	type: "GET",
	 		url: "leavebank.html?method=checkLeaveType",
	        data:datalist,
	        success: function(data) {
	        	var result = $.parseJSON(data);
	        	
	        	if(result.status == "true"){
	        	
	        	$(".errormessagediv1").show();
	        	 $(".validateTips1").text("*Leave Description already Exists");
	   			 cate.val("");
	   			 cate.css({'border-color':'#f00'});

	   			setTimeout(function() {
	   				$('.errormessagediv1').fadeOut();
	   				}, 3000);
	        	}
	        } 
	 });
	
}

function checkDuplicacy(){
	 status1 = 0;

	 var accyear = $("#academicyear").val();
	 var location = $("#locationname").val();
	datalist = {
			"accyear" : accyear,
			"location" : location
	}
	
	$.ajax({
		
	 	type: "GET",
 		url: "leavebank.html?method=checkDuplicacy",
        data:datalist,
		async : false,
        success: function(data) {
        	var result = $.parseJSON(data);
        	
        	if(result.status == "true"){
	   			status1 = 1;
        	}
        	
        	
        } 
	 
 });
	
return status1;
	
}
