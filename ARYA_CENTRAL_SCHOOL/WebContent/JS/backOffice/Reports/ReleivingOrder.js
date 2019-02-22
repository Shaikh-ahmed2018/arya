	
$(document)
		.ready(
				function() {

var date = new Date();
		            var currentMonth = date.getMonth();
		            var currentDate = date.getDate();
		            var currentYear = date.getFullYear();
		            $("#dateofRelievingId").datepicker({
		                                    pickTime: false,
		                format: "DD-MM-YYYY",   
		                maxDate: new Date(currentYear, currentMonth, currentDate)
		            });
		            
		            
		            
		            
		        	$("#teachertype").change(function(){
		        		
		        		var teachertype=$("#teachertype").val();
		        		var usertype = $("#usertype").val();
		        		
		        		if(teachertype=="teaching"){
		        			
		        			
		        		

			        		datalist={
			        				"teachertype" : teachertype,
			        				"usertype": usertype
			        		},
			        		
			        		$.ajax({
			        			
			        			type : 'POST',
			        			url : "staffreleivingorder.html?method=getTeachernameAction",
			        			data : datalist,
			        			async : false,
			        			success : function(response) {
			        				
			        				
			        				
			        				var result = $.parseJSON(response);
			        				
			        				
			        				
			        				
			        				$('#teachername').html("");
			        				
			        				$('#teachername')
			        				.append(
			        						'<option value="'
			        								+ ""
			        								
			        								
			        								+ '</option>');
			        		$('#teachername').append(
			        				'<option value="'
			        						+ "" + '">'
			        						+ ""
			        						+ '</option>');
			        		for ( var j = 0; j < result.teachinglist.length; j++) {
			        			$('#teachername')
			        					.append(
			        							'<option value="'
			        									+ result.teachinglist[j].teacherId
			        									+ '">'
			        									+ result.teachinglist[j].teacherName
			        									+ '</option>');
			        		}
			        				
			        			}
			        			
			        			
			        		});
		        			
		        			
		        		}
		        		
		        		
		        		else
		        			{
		        			
		        			
		        		

		        			datalist={
			        				"teachertype" : teachertype,
			        				"usertype": usertype
			        		},
			        		
			        		
			        		$.ajax({
			        			
			        			type : 'POST',
			        			url : "staffreleivingorder.html?method=getTeachernameAction",
			        			data : datalist,
			        			async : false,
			        			success : function(response) {
			        				
			        				
			        				var result = $.parseJSON(response);

			        				
			        				
			        				$('#teachername').html("");
			        				
			        				$('#teachername')
			        				.append(
			        						'<option value="'
			        								+ ""
			        								
			        								
			        								+ '</option>');
			        		$('#teachername').append(
			        				'<option value="'
			        						+ "" + '">'
			        						+ ""
			        						+ '</option>');
			        		for ( var j = 0; j < result.nonteachinglist.length; j++) {
			        			$('#teachername')
			        					.append(
			        							'<option value="'
			        									+ result.nonteachinglist[j].teacherId
			        									+ '">'
			        									+ result.nonteachinglist[j].teacherName
			        									+ '</option>');
			        		}
			        				
			        			}
			        			
			        			
			        		});
		        			
		        			
		        			}
		        			
		        			
		        		
		        		
		        	});	            
		            
		            
		            
		            
		        	$("#pdfDownload").click(function(){
		        		
		        		
		        		
		        		 var usertype=$("#usertype").val();
		        	     var teachername=$("#teachername").val();
		        		 var teachertype=$("#teachertype").val();
		        	   	var releivedate=$("#dateofRelievingId").val();
		        		
		        		
		        		
		        if(usertype==""){
		        	
		        	$('.errormessagediv').show();
					$('.validateTips').text("Select User Type");
					
					return false;
		        }	   	
		        	
		        if(teachertype==""){
		        	
		        	$('.errormessagediv').show();
					$('.validateTips').text("Select Teacher Type");
					
					return false;
		        }	 	   	
		        	   	
		        
		        if(teachername==""){
		        	
		        	$('.errormessagediv').show();
					$('.validateTips').text("Select Teacher Name");
					
					return false;
		        }	 	   	
		        	   	
		        if(releivedate==""){
		        	
		        	$('.errormessagediv').show();
					$('.validateTips').text("Select Releiving Date");
					
					return false;
		        }	 	   	
		        	   	
		        else{
		        	
		           	window.location.href = 'staffreleivingorder.html?method=staffReleivingPDFReport&usertype='
	        			+usertype
	        			+ ' &teachername='
	        			+teachername
	        			+ ' &teachertype='
	        			+ teachertype
	        			+ ' &releivedate='
	        			+releivedate
	        			;
	        		
	        		
		        	
		        }   	
		           	});
		            
		            
		  				 });        