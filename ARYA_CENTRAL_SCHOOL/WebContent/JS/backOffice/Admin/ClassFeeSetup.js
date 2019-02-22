
$(document).ready(function(){
	$("#Acyearid").val($("#hacademicyaer").val());
	
	getclassdetailList("",$("#Acyearid").val());
	
	$("#allstudent tbody tr").click(function(){
		var splitVal=$(this).find("td:first").attr("class").split(",");
		window.location.href="classfeesetup.html?method=getEditedFeeSetupDetails&accYear="+splitVal[0]+"&classId="+splitVal[1]+"&loc_id="+splitVal[2];

	});
	
	$("#locationname").change(function(){
		var locationId=$("#locationname").val();
		
		var accyear=$("#Acyearid").val();
		getclassdetailList(locationId,accyear);
	});
	$("#Acyearid").change(function(){
		var locationId=$("#locationname").val();
		
		var accyear=$("#Acyearid").val();
		getclassdetailList(locationId,accyear);
	});
});
function getclassdetailList(locationId,accyear){
	$.ajax({
		type:'POST',
		url:'classfeesetup.html?method=getClassFeeSetupByJs',
		data:{"locationId":locationId,"accyear":accyear},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.classSetupList.length>0){
				for(var i=0;i<result.classSetupList.length;i++){
					$("#allstudent tbody").append("<tr>" +
							"<td class='"+result.classSetupList[i].accyearid+","+result.classSetupList[i].classid+","+result.classSetupList[i].locationId+"'>"+(i+1)+"</td>" +
							"<td>"+result.classSetupList[i].accyear+"</td>" +
							"<td>"+result.classSetupList[i].locationName+"</td>" +
							"<td>"+result.classSetupList[i].classname+"</td>" +
							"<td><span class='class_name "+result.classSetupList[i].status+"' style='min-width:80px;display:inline-block;text-align:center; color:#fff;'>"+result.classSetupList[i].status+"</span></td>" +
							"</tr>");
					
				}
				
				pagination(100);
				$("#allstudent tbody tr").click(function(){
					var splitVal=$(this).find("td:first").attr("class").split(",");
					window.location.href="classfeesetup.html?method=getEditedFeeSetupDetails&accYear="+splitVal[0]+"&classId="+splitVal[1]+"&loc_id="+splitVal[2];

				});
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='5'>NO Records Found</td></tr>");
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.classSetupList.length);
		}
	});
	
}