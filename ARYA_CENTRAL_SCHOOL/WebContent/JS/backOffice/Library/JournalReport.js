$(document).ready(function(){
	getjournalName();
	
	$("#todate").change(function()
	{
		getjournalName();
	});
	
	$("#fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:$("#hiddenendaccyear").val()-1,
       
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
	        var max = $(this).datepicker('getDate');
			$("#ToDate").datepicker("option","minDate", max || $("#hiddenendaccyear").val());
		}
	});	

	$("#todate").datepicker({
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
	
$("#pdfDownload").click(function(){
	
		
		var checkedVal = $("input[name='requested_by']:checked").val();
		var Fromdate=$("#fromdate").val();
		var ToDate=$("#todate").val();
		var journalName=$("#journalName").val();
	
		if(Fromdate==null||Fromdate==undefined||Fromdate=="")
			
			{
			showError("#Fromdate","Enter From Date ");
			return false;
			}
else  if (ToDate==null||ToDate==undefined||ToDate=="")
	{
	showError("#ToDate","Enter To Date ");
	return false;
	}
else {
	

	window.location.href = 'LibraryMenu.html?method=getJournalListPDFreport&checkedVal='+checkedVal+'&Fromdate='+Fromdate+'&ToDate='+ToDate+'&journalName='+journalName+'';
}	
	});
	$("#print").click(function(){
		
		var checkedVal = $("input[name='requested_by']:checked").val();
		var Fromdate=$("#fromdate").val();
		var ToDate=$("#todate").val();
		var journalName=$("#journalName").val();
		if(Fromdate==null||Fromdate==undefined||Fromdate=="")
			
		{
		showError("#Fromdate","Enter From Date ");
		return false;
		}
else  if (ToDate==null||ToDate==undefined||ToDate=="")
{
showError("#ToDate","Enter To Date ");
return false;
}
else {
		
		
		$.ajax({
			type: "POST",
			url:"LibraryMenu.html?method=printJournalNameList&checkedVal="+checkedVal+"&Fromdate="+Fromdate+"&ToDate="+ToDate+"&journalName="+journalName,
			success : function(data){
				
			}
		
		});
}
	});
	
	
});

function getjournalName()
{

$.ajax({
	type:'post',
	url:"LibraryMenu.html?method=getJournalNameList",
	async:false,
	
	success:function(data){
		var result = $.parseJSON(data);
		$("#journalName").empty();
		$("#journalName").append("<option value=''>All</option>");
		
		for(var i=0;i<result.data.length;i++){
		$("#journalName").append("<option value='"+result.data[i].journalName+"'>" 
				+result.data[i].journalName+"</option>");
		}
	}
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