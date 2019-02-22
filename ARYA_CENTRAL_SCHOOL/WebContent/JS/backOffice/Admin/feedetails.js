function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
$(document).ready(function() 

		{
	$("#Acyearid").val($("#hacademicyaer").val());
	$("#locationname").change(function(){
		var locationId=$("#locationname").val();
		if(locationId==""){
			locationId="all";
		}
		var academicYear=$("#Acyearid").val();

		var searchTerm=$("#searchvalue").val();
		if(searchTerm==""){
			searchTerm="all";
		}
		getFeeList(locationId,academicYear,searchTerm);
	});
	$("#Acyearid").change(function(){
		var locationId=$("#locationname").val();
		if(locationId==""){
			locationId="all";
		}
		var academicYear=$("#Acyearid").val();

		var searchTerm=$("#searchvalue").val();
		if(searchTerm==""){

			searchTerm="all";
		}
		if(locationId !="all" || locationId != undefined){

			getFeeList(locationId,academicYear,searchTerm);
		}
	});
	$("#searchvalue").keypress(function(e){
		if(e.keyCode==13){
			var locationId=$("#locationname").val();
			if(locationId==""){
				locationId="all";
			}
			var academicYear=$("#Acyearid").val();

			var searchTerm=$("#searchvalue").val();
			if(searchTerm==""){
				searchTerm="all";
			}
			if(locationId !="all" || locationId !=undefined){

				getFeeList(locationId,academicYear,searchTerm);
			}
		}
	});

	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("?")+1);
	if(pageUrl.split("=")[1]=="feeDetailsList&searchvalue"){

		$("#searchvalue").focus();
	}
	$("#selectall").change(function(){
		$(".select").prop("checked",$(this).prop("checked"));
	});

	$(".select").change(function(){
		if($(".select").length==$(".select:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
		}
	});

	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv1").hide();
	}, 3000);


	$("#delete").click(function(){
		getDataArray=[]; //add array1
		var cnt = 0;

		$('.select:checked').each(function() {
			getDataArray.push($(this).val());
			cnt++;
		});
		if ( cnt > 0) {
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure to Delete?</p>");
		}

		else{
			$(".successmessagediv").hide();
			$(".errormessagediv1").show();
			$(".validateTips1").text(
			"Select any one record");
		}

	});


	$("#dialog").dialog({
		autoOpen  : false,
		modal     : true,
		title     : "Fee Details",
		buttons   : {
			'Yes' : function() {

				var datalist = {'getDataArray':getDataArray.toString()};//create json data3
				$
				.ajax({
					type : "GET",
					url : "addfee.html?method=deleteFeeDetails",
					data : datalist,
					async : false,

					success : function(
							response) {

						var result = $
						.parseJSON(response);

						if (result.jsonResponse == true) {
							$(".successmessagediv.feedetails").show();
							$(".sucessmessage") .text("Deleting Unmmaped Record Progressing...");

							$(".successmessagediv.feedetails").delay(3000).slideUp("slow");
							/*setTimeout(function(){
											window.location.href = "adminMenu.html?method=feeDetailsList&result="+result.jsonResponse;
											},3000);*/

						}
						else {
							$(".errormessagediv").show();
							$(".validateTips").text("Fee Already Mapped.");
							$(".errormessagediv").delay(3000).slideUp("slow");
						}

						setTimeout(function(){
							window.location.href = "adminMenu.html?method=feeDetailsList";
						},2000);

					}

				});
				$(this).dialog('close');

			},
			'No' : function() {

				$(this).dialog('close');
			}
		}
	});


	$("#feeedit").click(function() {
				var value = $('.select:checked').val();
				if (value == undefined || value =="") {
					$(".successmessagediv").hide();
					$(".errormessagediv1").show();
					$(".validateTips1").text("Select any one record");
				} 
				else if($('.select:checked').length > 1){
					$(".successmessagediv").hide();
					$(".errormessagediv1").show();
					$(".validateTips1").text("Select any one record");
				}
				else {
					window.location.href = "addfee.html?method=editFeeDetails&name="+value;
				}
	});

	$("#search").click(function() 
			{
				var searchvalue=$('#searchvalue').val().trim();
				window.location.href = "adminMenu.html?method=feeDetailsList&searchvalue="+ searchvalue;
			});
	$('#searchvalue').keypress(function(e){
		if(e.which==13)
			window.location.href = "adminMenu.html?method=feeDetailsList&searchvalue="+$(this).val().trim();

	});

	$('#excelDownload').click(function() {

		var searchvalue=$('#feesearchid').val();


		window.location.href = 'addfee.html?method=FeeDetailsXLS&searchvalue='+searchvalue;

	});

	$("#pdfDownload").click(function(){

		var searchvalue=$('#feesearchid').val();


		window.location.href = "addfee.html?method=FeeDetailsPDFReport&searchvalue="+searchvalue;

	});	

		});


function getFeeList(locationId,academicYear,searchTerm){
	var dataList={
			"locationId":locationId,
			"academicYear":academicYear,
			"searchTerm":searchTerm,
	};
	$.ajax({
		type:'POST',
		url:'feecollection.html?method=feeDetailsListbyjs',
		data:dataList,
		async:false,
		success: function(data){
			var result=$.parseJSON(data);
			$("#allstudent tbody").empty();
			if(result.feelist.length>0){
				for(var i=0;i<result.feelist.length;i++){
					$("#allstudent tbody").append("<tr>" +
							"<td><input type='checkbox' class='select' value='"+result.feelist[i].id+"' /></td>" +
							"<td>"+result.feelist[i].academicYearName+"</td>" +
							"<td>"+result.feelist[i].locationName+"</td>" +
							"<td>"+result.feelist[i].feeType+"</td>" +
							"<td>"+result.feelist[i].name+"</td>" +
							"<td>"+result.feelist[i].description+"</td>" +
					"</tr>");
				}
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='6'>No Records Found</td></tr>");
			}
		}
	});
}








