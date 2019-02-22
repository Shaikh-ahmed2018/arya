$(document).ready(function(){
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0 ){
		hideError("#"+$(this).attr("id"));
		
		$(".errormessagediv").hide();
		}
	},
	});
	$("#Acyearid").val($("#hidenaccyear").val());
	
	/*getaccessionList($("input[name='requested_by']:checked").val());*/

	/*getbooktitleList($("input[name='requested_by']:checked").val());*/
	$("#locationname").change(function(){
		$(".pagebanner").show();
		$(".numberOfItem").show();
		$(".pagination").show();
		$("#allstudent").show();
		
		getReservationListReport();
		getaccessionList($("input[name='requested_by']:checked").val());
	});
	$("#Acyearid").change(function(){
		getReservationListReport();
		getaccessionList($("input[name='requested_by']:checked").val());
	});	
	$("#AccNo").change(function(){
		getReservationListReport();
		getbooktitleList($("input[name='requested_by']:checked").val());
	});
	
	$("#booktitle").change(function(){
		getReservationListReport();
	});
	
	$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		
		changeMonth : true,
		changeYear : true,
	});
	$("#Todate").datepicker({
		dateFormat : "dd-mm-yy",
	
		changeMonth : true,
		changeYear : true,
	});
	
	 $("input[name='started_by']").change(function(){
		 if($(this).val() =="allfordate"){
				$(".dateshow").hide();
				/*getReservationListReport();*/
		   }
		 
		 if($(this).val() =="all"){
				$(".dateshow").show();
		}
	 });
	
	 $("#Todate").change(function(){
		 getReservationListReport();
	 });
	 
	 $("input[name='requested_by']").change(function(){
			
		 if($(this).val() == "others"){
				getReservationListReport($("input[name='requested_by']:checked").val());
			}
		 else if($(this).val() == "Student"){
				getReservationListReport();
				getaccessionList($("input[name='requested_by']:checked").val());
			}
			
			else if($(this).val()=="Teacher"){
				getReservationListReport();
				getaccessionList($("input[name='requested_by']:checked").val());
			
			}
			else {
				getReservationListReport();
				getaccessionList($("input[name='requested_by']:checked").val());	
			}
		
		});
	 $("#pdfDownload").click(function(){
		 if ($("#locationname").val() == "" || $("#locationname").val()  == undefined) { 
				$(".errormessagediv").show();
				showError("#locationname","Field Required -School Name");
					return false;
			     }	
		 else{
			window.location.href ='LibraryMenu.html?method=ReservationReportPdf&LocId='+$("#locationname").val()
			+'&AccId='+$("#Acyearid").val()+'&All='+$("#All").val()
			+'&AccNo='+$("#AccNo").val()+'&booktitle='+$("#booktitle").val()
			+'&Subscriber='+$("input[name='requested_by']:checked").val()
			+'&date='+$("input[name='started_by']:checked").val()
			+'&locationname='+$("#locationname option:selected").text()+
			"&Acyearid="+$("#Acyearid option:selected").text()+
			"&All="+$("#All option:checked").text()+
			"&AccNo="+$("#AccNo option:selected").text()+"&booktitle="+$("#booktitle option:selected").text()
			+"&fromdate="+$("#Fromdate").val()+"&todate="+$("#Todate").val();
		 }
		});
		
	       $("#excelDownload").click(function(){
	    	   if ($("#locationname").val() == "" || $("#locationname").val()  == undefined) { 
					$(".errormessagediv").show();
					showError("#locationname","Field Required -School Name");
						return false;
				     }	
			 else{
				window.location.href ='LibraryMenu.html?method=ReservationReportExcel&LocId='+$("#locationname").val()
				+'&AccId='+$("#Acyearid").val()+'&All='+$("#All").val()
				+'&AccNo='+$("#AccNo").val()+'&booktitle='+$("#booktitle").val()
				+'&Subscriber='+$("input[name='requested_by']:checked").val()
				+'&date='+$("input[name='started_by']:checked").val()
				+'&locationname='+$("#locationname option:selected").text()+
				"&Acyearid="+$("#Acyearid option:selected").text()+
				"&All="+$("#All option:checked").text()+
				"&AccNo="+$("#AccNo option:selected").text()+"&booktitle="+$("#booktitle option:selected").text()
				+"&fromdate="+$("#Fromdate").val()+"&todate="+$("#Todate").val();
			 }
	   	
			});
	 
	  $("#print").click(function(){
	    	
		  if ($("#locationname").val() == "" || $("#locationname").val()  == undefined) { 
				$(".errormessagediv").show();
				showError("#locationname","Field Required -School Name");
					return false;
			     }	
		  else{
	   		$.ajax({
	   			type:"post",
	   			url:'LibraryMenu.html?method=ReservationReportPrint&&LocId='+$("#locationname").val()
				+'&AccId='+$("#Acyearid").val()+'&All='+$("#All").val()
			+'&AccNo='+$("#AccNo").val()+'&booktitle='+$("#booktitle").val()
			+'&Subscriber='+$("input[name='requested_by']:checked").val()
			+'&date='+$("input[name='started_by']:checked").val()
			+'&locationname='+$("#locationname option:selected").text()+
			"&Acyearid="+$("#Acyearid option:selected").text()+
			"&All="+$("#All option:checked").text()+
			"&AccNo="+$("#AccNo option:selected").text()+"&booktitle="+$("#booktitle option:selected").text()
			+"&fromdate="+$("#Fromdate").val()+"&todate="+$("#Todate").val(),
	   			success:function(data){
	   				
	   			}
	   		});
		  }
	   	});
	pagination(100);
	
});
	
function getaccessionList(value){
	
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getReservationAccNo",
		data : {"subtype":value,"accyear":$("#Acyearid").val()},
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#AccNo").empty();
			$("#AccNo").append('<option value=ALL>ALL</option>');
			for(var i=0;i<result.Accnolist.length;i++){
				 $("#AccNo").append("<option value='"+result.Accnolist[i].enteryid+"'>"+result.Accnolist[i].accessionNo+"</option>");
			}
		}
	});
}



function getbooktitleList(value){
	
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getbooktitleList",
		data : {"subtype":value,"accyear":$("#Acyearid").val(),
			 "accNo":$("#AccNo").val(),},
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#booktitle").empty();
			$("#booktitle").append('<option value=ALL>ALL</option>');
			for(var i=0;i<result.booklist.length;i++){
				 $("#booktitle").append("<option value='"+result.booklist[i].enteryid+"'>"+result.booklist[i].bookTitle+"</option>");
			}
		}
	});
}



function getReservationListReport(){
	datalist={
			
			"Location":$("#locationname").val(),
			"AccYear":$("#Acyearid").val(),
			"AccNo":$("#AccNo").val(),
			"booktitle":$("#booktitle").val(),
			"Subscriber":$("input[name='requested_by']:checked").val(),
			"fromdate":$("#Fromdate").val(),
			"todate":$("#Todate").val(),
			"date" :$("input[name='started_by']:checked").val()
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getReservationListReport",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
		     if(result.reservationList.length>0){
			for(var i=0;i<result.reservationList.length;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td>"+(i+1)+"</td>"+
						"<td>"+result.reservationList[i].accessionNo+"</td>"+
						"<td>"+result.reservationList[i].bookTitle+"</td>"+
						"<td>"+result.reservationList[i].author+"</td>"+
						"<td>"+result.reservationList[i].location+"</td>"+
						"<td class='"+result.reservationList[i].subscriber_id+" "+result.reservationList[i].name+" "+result.reservationList[i].userType+"'>"+result.reservationList[i].subscriberName+"</td>"+
						"<td>"+result.reservationList[i].fromDate+"</td>"+
						"<td>"+result.reservationList[i].toDate+"</td>"+
						"<td>"+result.reservationList[i].userType+"</td>"+
						"</tr>"
				  );
		     	}
		     }
		     else{
		    	 $("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
		     }
			$(".numberOfItem").empty();
			$(".numberOfItem").append(" No. of Records  "+result.reservationList.length);
			pagination(100);
		}
	});
}

function showError(id,errorMessage){
	$(id).focus();
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
	$(".errormessagediv").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}



