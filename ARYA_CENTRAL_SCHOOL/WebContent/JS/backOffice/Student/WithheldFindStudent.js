$(document).ready(function() {
	
		$("#allstudent tbody tr").click(function(){
			
			window.location.href="adminMenu.html?method=studentWithheld";

		});
		
		$("#classname").change(function(){
			
			
			var classname=$("#classname").val();
			
			
			
			datalist={
					"classname" : classname
					
			},
			
			$.ajax({
				
				type : 'POST',
				url : "studentTransferReport.html?method=getSection",
				data : datalist,
				async : false,
				success : function(response) {
					
					var result = $.parseJSON(response);
					
					$('#sectionid').html("");
					
					$('#sectionid').append(
							
							'<option value="' + "" + '">' + " "
									
							+ '</option>');
					
					for ( var j = 0; j < result.seclist.length; j++) {

						$('#sectionid').append(

						'<option value="'

						+ result.seclist[j].sectionId + '">'

						+ result.seclist[j].sectionName

						+ '</option>');

					}
					
				}
				
				
			});
			
		});
		
		
});