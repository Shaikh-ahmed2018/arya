$(document).ready(function(){
	
	
	$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:$("#hiddenendaccyear").val()-1,
       
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
	        var max = $(this).datepicker('getDate');
			$("#ToDate").datepicker("option","minDate", max || $("#hiddenendaccyear").val());
		}
	});	

	$("#ToDate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:$("#hiddenendaccyear").val()-1,
        minDate :$("#hiddenstartaccyear").val(),
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			var max = $(this).datepicker('getDate');
			$("#datepicker").datepicker("option","minDate",max || '+1Y');
		}
	});
	
	/*if($("input[name='requested_by']:checked").val()=="accNo"){
	
		getNewArrivalList();
		
		
		
	}else if($("input[name='requested_by']:checked").val()=="category"){
		
		
		
	}*/
	
	/*$("#excelDownload").click(function(){

		var event = $("#event").val();
		var stage=$("#stage").val();
		
		if(event==""){
			event="all";
		}
		

			window.location.href = 'EventsMenu.html?method=getProgramNumberingExcelReport&event='+event+'&stage='+stage+'';
				
		
	});*/
	
	

	$("#pdfDownload").click(function(){
		
		var checkedVal = $("input[name='requested_by']:checked").val();
		var Fromdate=$("#Fromdate").val();
		var ToDate=$("#ToDate").val();
		if(Fromdate==null||Fromdate==undefined||Fromdate=="")
			{
			showError("#Fromdate","Enter New Arrival From Date ");
			return false;
			}
		else if(ToDate==null||ToDate==undefined||ToDate=="")
			{
			showError("#ToDate","Enter New Arrival From Date ");
			return false;
			}
		else
			{
			window.location.href = 'LibraryMenu.html?method=getNewArrivalListPDFreport&checkedVal='+checkedVal+'&Fromdate='+Fromdate+'&ToDate='+ToDate+'';
			}
	
		
	});
	$("#print").click(function(){
		
		var checkedVal = $("input[name='requested_by']:checked").val();
		var Fromdate=$("#Fromdate").val();
		var ToDate=$("#ToDate").val();
		
		
		
		$.ajax({
			type: "POST",
			url:"LibraryMenu.html?method=printNewArrivalList&checkedVal="+checkedVal+"&Fromdate="+Fromdate+"&ToDate="+ToDate,
			success : function(data){
				
			}
		});
	});
	
	
		});

function getNewArrivalList(){
	datalist={
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getStockEntryBookList",
		data : datalist,
		async : false,
		success : function(response) {/*

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
		
			for(var i=0;i<result.StockList.length;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td><input type='checkbox' name='select' class='select'  value='"+result.StockList[i].enteryid+"'/></td>"+
						"<td>"+result.StockList[i].accessionNo+"</td>"+
						"<td>"+result.StockList[i].itemType+"</td>"+
						"<td>"+result.StockList[i].regdate+"</td>"+
						"<td>"+result.StockList[i].bookTitle+"</td>"+
						"<td>"+result.StockList[i].author+"</td>"+
						"<td>"+result.StockList[i].ddc+"</td>"+
						"<td>"+result.StockList[i].no_of_Copies+"</td>"+
						"<td>"+result.StockList[i].location +"</td>"+
						"<td>"+result.StockList[i].currentStatus+"</td>"+
						"</tr>"
				
				);
				
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.StockList.length);
		*/}
	});
		
}
function showError(id,errorMessage){
	
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(2000).fadeOut();
}