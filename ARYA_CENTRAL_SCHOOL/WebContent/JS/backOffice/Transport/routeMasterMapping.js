$(document).ready(function(){
	
	if($("#haccyear").val()!=""){
		$("#routeNo").autocomplete({
			source : function(request, response) {

				$.ajax({

					url : "transport.html?method=getRouteScreen",
					data : {
						searchTerm : request.term
					},
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						response($.map(	result.jsonResponse,function(item) {
							return {
								label : item.routeName,
								value : item.routeCode,
							}
						}));
					}
				});
			},
			select : function(event, ui) {

				var searchTerm = ui.item.value;

				studentDetails = {
						'searchTerm' : searchTerm
				};
				var routeDetails = callAjax("transport.html?method=getRouteScreenDetails",studentDetails);
				
				viewRouteDetails(routeDetails);
				$("#routeNo").val(ui.item.label);
				$("#hRouteCode").val(searchTerm);

				return false;
			}
		});
	}
	
});

function viewRouteDetails(routeDetails){
	
	$("#routeLogicName").val(routeDetails[0].routeLogicName);
	$("#totalDistance").val(routeDetails[0].totalDistance);
	$("#starttime").val(routeDetails[0].stratTime);
	$("#endtime").val(routeDetails[0].endTime);
	$("#routeName").val(routeDetails[0].routeName);
	$("#totalStops").val(routeDetails[0].totalStops);
	$("#halttime").val(routeDetails[0].halttime);
}
function callAjax(urlWithMethod, dataToBeSend) {
	var jsonResult = "";
	try {
		$.ajax({
			type : "GET",
			url : urlWithMethod,
			data : dataToBeSend,
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);

				jsonResult = result.jsonResponse;
			}
		});
	} catch (e) {
		jsonResult = "";
	}
	return jsonResult;
}