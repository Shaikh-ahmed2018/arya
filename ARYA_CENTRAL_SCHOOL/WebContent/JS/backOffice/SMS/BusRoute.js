$(document).ready(function(){
	
		getRouteNameList();
		
	
	
	
		
});
function getRouteNameList(){

	   var locationid=$("#locationname").val();
	   //alert(locationid)
	   datalist = {
			   "locationid" :locationid
		},
		$.ajax({
			type : 'POST',
			url : "transport.html?method=getRouteNameList",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
				alert(result)
					$(".route").empty();
					$('.route').append('<option value="">' + "----------Select----------"	+ '</option>');
					for ( var j = 0; j < result.routelist.length; j++) {
                    
						$('.route').append('<option value="'

								+ result.routelist[j].routeCode + '">'

								+ result.routelist[j].routeName

								+ '</option>');
					}
			}
		});
}

function getstoplist(routeid){

	   datalist = {
				"routeid" :routeid,
				"accyear":$("#AccYear").val()
			}, $.ajax({
				type : 'POST',
				url : "transport.html?method=getstoplist",
				data : datalist,
	 			async : false,
				
				success : function(response) {
					
					var result = $.parseJSON(response);
						$(".stopname").empty();
						$('.stopname').append('<option value="">' + "----------Select----------"	+ '</option>');
						for ( var j = 0; j < result.stoplist.length; j++) {
	                       
							$('.stopname').append('<option value="'

									+ result.stoplist[j].stage_id + '">'

									+ result.stoplist[j].stopname

									+ '</option>');
						}
				}
			});
	}
