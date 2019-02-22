window.onload = function() 

{
	
	getClass();
	getTeacher();
	
};



function getsection()

{
	var classid=$('#idclass').val();
	var classteacherid = $("#classteacherid").val();
	
	
	datalist = {"classid" : classid, "classteacherid" : classteacherid},
	
	$.ajax({

		type : "POST",

		url : "teachmap.html?method=getsection",
		
		data : datalist,

		success : function(response) {

			var result = $.parseJSON(response);
			
			if(result.updatesectionlist)
			
			{
				
				$('#sectionid').html("");
				
				$('#sectionid').append(

						'<option value="' + "" + '">' + "----Select----"

						+ '</option>');

				
				for ( var j = 0; j < result.updatesectionlist.length; j++) {

					$('#sectionid').append(

					'<option value="'

					+ result.updatesectionlist[j].sectionid + '">'

					+ result.updatesectionlist[j].sectionname

					+ '</option>');

				}
				
				if(classteacherid!="" || classteacherid!=undefined){
					
					
					$("#sectionid option[value=" + result.updatesectionlist[0].sectionid + "]").attr('selected', 'true');
					
				}
				
				
				
				
			}
			

		}

	});
	
		
	
	
}




function getClass()

{
	
	
	var Classteacherid = $("#classteacherid").val();
	var classteacherMappingId=$("#classteacherid").val();
	
	datalist = {"Classteacherid" : Classteacherid},
	
	$.ajax({

		type : "POST",

		url : "teachmap.html?method=getclass",
		
		data : datalist,

		success : function(response) {

			var result = $.parseJSON(response);
			
			
			$('#classid').append(

					'<option value="' + "" + '">' + "----Select----"

					+ '</option>');
			
			for ( var j = 0; j < result.jsonResponse.length; j++) {

				$('#classid').append(

				'<option value="'

				+ result.jsonResponse[j].classid + '">'

				+ result.jsonResponse[j].classname

				+ '</option>');

			}
			
			if(classteacherMappingId!="" || classteacherMappingId!=undefined){
				
				
				$("#classid option[value=" + result.jsonupdateResponse[0].classid + "]").attr('selected', 'true');
				
			}
			
			$('#idclass').val($('#classid').val());
			var classid=$('#idclass').val();
			getsection();
			
		
		}
	
	
	

	});
	
	
}


function getTeacher()

{
	
	var classid=$('#classid').val();
	
	
	var classteacherid = $("#classteacherid").val();
	
	datalist = {"Classteacherid" : classteacherid},
	
	$.ajax({

		type : "POST",

		url : "teachmap.html?method=getteacher",
		
		data : datalist,

		success : function(response) {

			var result = $.parseJSON(response);
			
			if(result.jsonResponse)
			
			{			
			
			$('#teacherid').append(

					'<option value="' + "" + '">' + "----Select----"

					+ '</option>');
			
		for ( var j = 0; j < result.jsonResponse.length; j++) {

				$('#teacherid').append(

				'<option value="'

				+ result.jsonResponse[j].teacherid + '">'

				+ result.jsonResponse[j].teachername

				+ '</option>');

			}
			}
			
			
			
			if(result.jsonupdateteacherResponse || result.jsonupteacherlist)
				
			{
				
				$('#teacherid').append(

						'<option value="' + "" + '">' + "----Select----"

						+ '</option>');
		
			for ( var j = 0; j < result.jsonupdateteacherResponse.length; j++) {
				
				$('#teacherid').append(

				'<option value="'

				+ result.jsonupdateteacherResponse[j].teacherid + '">'

				+ result.jsonupdateteacherResponse[j].teachername

				+ '</option>');

			}
			
		for ( var j = 0; j < result.jsonupteacherlist.length; j++) {
			
			$('#teacherid').append(

			'<option value="'

			+ result.jsonupteacherlist[j].teacherid + '">'

			+ result.jsonupteacherlist[j].teachername

			+ '</option>');

			}
		
		
		if(classteacherid!="" || classteacherid!=undefined){
			
			
			$("#teacherid option[value=" + result.jsonupdateteacherResponse[0].teacherid + "]").attr('selected', 'true');
			
		}
		
		
			
			}
			
			
			
			

		}

	});



}


function myFunction()

{
	
	var classid=$('#classid').val();
	var sectionid=$('#sectionid').val();
	var teacherid=$('#teacherid').val();
	
	if(classid=="" || classid==null)
		
	{
	
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Select Class Name");
		
		return false;
	
	}
	
	
	if (sectionid == "" || sectionid == undefined) {
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Select Section Name");
		return false;
	}
	
	if (teacherid == "" || teacherid == undefined) {
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Select Teacher Name");
		return false;
	}
	
	
	return true;	




}


$(document).ready(function() 
		
	{
	
	
	$('.accordHead').click(function() {
		var displaypro = $(this).next('div').css('display');
		if (displaypro == 'none') {
			$(this).next('div').css({
				'display' : 'block'
			});
		} else {
			$(this).next('div').css({
				'display' : 'none'
			});
		}
	});
	
	
	
	
	$(".check")
	.click(
			function()
			{
				
				var id = this.id;
				
				 $("#checkval").val(id);
			});
	
	
	
	
	$("#mapedit")
	.click(
			function() {
				
				 var id=$("#checkval").val();
				 
				 if ($('input[type=checkbox]:checked').length == 0) {
					 $(".successmessagediv").hide();
						$(".errormessagediv1").show();
						$(".validateTips1").text("Select Any One Checkbox");
						return false;
			        }
			        else if($('input[type=checkbox]:checked').length >= 2){
			        	$(".successmessagediv").hide();
						$(".errormessagediv1").show();
						$(".validateTips1").text("Select Any One Checkbox");
						 return false;
			        }
				
				else {
					
					
					window.location.href = "teachmap.html?method=editclassdetails&id="+id;
				
}
			 	   	
				});
	
	
	
	
	
	
	
	
		$('#classid').change(function() // name exist or not
			
 			{
			
			
			var classid=$('#classid').val();
			
			var classteacherid = $("#classteacherid").val();
			
			datalist = {"classid" : classid, "classteacherid" : classteacherid},
			
			$.ajax({

				type : "POST",

				url : "teachmap.html?method=getsection",
				
				data : datalist,
				
				async : false,

				success : function(response) {

					var result = $.parseJSON(response);
					
					$('#sectionid').html("");
					
					$('#sectionid').append(

							'<option value="' + "" + '">' + "----Select----"

							+ '</option>');
					
					if(result.jsonResponse)
						
					{
						
						for ( var j = 0; j < result.jsonResponse.length; j++) {

						$('#sectionid').append(

						'<option value="'

						+ result.jsonResponse[j].sectionid + '">'

						+ result.jsonResponse[j].sectionname

						+ '</option>');

					}
					
					}
					
					if(result.updatesectionlist)
						
					{
						
						for ( var k = 0; k < result.updatesectionlist.length; k++) {
						
						$('#sectionid').append(

						'<option value="'

						+ result.updatesectionlist[k].sectionid + '">'

						+ result.updatesectionlist[k].sectionname

						+ '</option>');

					}
					
					
					if(classteacherid!="" || classteacherid!=undefined){
						
						
						$("#sectionid option[value=" + result.updatesectionlist[0].sectionid + "]").attr('selected', 'true');
						
					}
					
					}
					
					
				if(result.jsonupdateResponse || result.jsonupclasslist)
						
					{
						
					for ( var j = 0; j < result.jsonupdateResponse.length; j++) {
						
						$('#classid').append(

						'<option value="'

						+ result.jsonupdateResponse[j].classid + '">'

						+ result.jsonupdateResponse[j].classname

						+ '</option>');

					}
					
				for ( var j = 0; j < result.jsonupclasslist.length; j++) {

					$('#classid').append(

					'<option value="'

					+ result.jsonupclasslist[j].classid + '">'

					+ result.jsonupclasslist[j].classname

					+ '</option>');

					}
					}
					
					
					
					
			

				}

			});
			
 			});
		
		
		
		$("#save")
		.click(
				function() 
				
				{
					
					var classid=$('#classid').val();
					var sectionid=$('#sectionid').val();
					var teacherid=$('#teacherid').val();
					var classteacherid = $("#classteacherid").val();
					
				if (myFunction())
					
					{
						
					datalist = {"classid" : classid, "sectionid" : sectionid, "teacherid":teacherid, "classteacherid":classteacherid},
					
					$.ajax({
						
					type : "POST",
					
					url : "teachmap.html?method=addmappingdetails",
					
					data : datalist,
					
					success : function(data)
					
						{
						
							var result = $.parseJSON(data);
							
						if(result.jsonResponse=="Class Teacher Not Mapped Successfully")
								
								{
								
								$(".successmessagediv").hide();
								$(".errormessagediv1").show();
								$(".validateTips1").text(result.jsonResponse);
								
								}
							
							if(result.jsonResponse=="Class Teacher Not Updated Successfully")
								
							{
							
							$(".successmessagediv").hide();
							$(".errormessagediv1").show();
							$(".validateTips1").text(result.jsonResponse);
							
							}
							
							window.location.href = "teachmap.html?method=adddisplaydetails&result="+ result.jsonResponse;
							
							
						}
			 	   	
				});
					
				
					}
				
				
				});
		
		
		
		
		
		$("#delete")
		.click(
				function() 
				
				{
					
					 var classteacher=$("#checkval").val();
					 
					 if ($('input[type=checkbox]:checked').length == 0) {
						 $(".successmessagediv").hide();
							$(".errormessagediv1").show();
							$(".validateTips1").text("Select Any One Checkbox");
				            return false;
				        }
				        else if($('input[type=checkbox]:checked').length >= 2){
				        	$(".successmessagediv").hide();
							$(".errormessagediv1").show();
							$(".validateTips1").text("Select Any One Checkbox");
							 return false;
				        }
					
					
					else {
						
						
						var x = confirm("Are you sure want to delete this fee details");
						if (x)
						{
							
							var datalist = 'classteacher=' + classteacher;
							


							$
									.ajax({
										type : "GET",
										url : "teachmap.html?method=deletemappingDetails",
										data : datalist,
										async : false,

										success : function(response) {
											
											var result = $.parseJSON(response);
											
											if (result.jsonResponse == "Teacher Details Deleted Successfully")
											{
												
												
												window.location.href = "adminMenu.html?method=teachermapping&result="+ result.jsonResponse;
												
											}
											if(result.jsonResponse == "Teacher Mapping Not Deleted Successfully")
											{
												$(".errormessagediv").show();
												$(".validateTips").text(result.jsonResponse);
											}
											
											
											
											 setTimeout(function(){										 
												   
												 location.reload();
											 
											 },3000);
											
											
										
											
										}
									});

						}

					
					}
				 	   	
			
				});
		
		
		
		$("#download")
		.click(
				function() 
				
				{
					
					
					window.location.href = "teachmap.html?method=downloadteachermapping";
					
					
				});
				
				
				
				$("#view")
				.click(
						function() 
						
						{
							
							window.location.href = "adminMenu.html?method=teachermapping";
							
							
						});
		
		
		
	});




