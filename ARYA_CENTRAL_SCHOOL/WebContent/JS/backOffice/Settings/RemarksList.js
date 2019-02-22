
function myFunction() {
	    
	    document.getElementById("myForm").submit();   
				  }

$(document).ready(function() {
	
	$(".errormessagediv").hide();
	
	
	$("#searchTextId").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true
	});
	
	$("#searchTextId2").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true
	});
	
	
	
	
	$("#plus").click(function(){
		
	
		
		var remarktype = $('#remarktype').val();
		
		
		
		
		if(remarktype=="Remarks"){
			
		
			
			window.location.href = "communicationPath.html?method=sendRemarkAction";
			
		}
		else if(remarktype=="meeting"){
			
			window.location.href = "communicationPath.html?method=createMeetingAction";
		}
		
		else if(remarktype=="bday"){
			
			
		
			
			window.location.href = "communicationPath.html?method=createBdayWishAction";
			
		}
		
	});
	
	
	
	
	$("#edit").click(function(){
		
		var remarktype = $('#remarktype').val();
		
		var hremarhcode = $('#hremarhcode').val();
		
		
		
		alert("hremarhcode"+hremarhcode);
		
       if(remarktype=="Remarks"){
		
    	  
    	   window.location.href = "communicationPath.html?method=editRemarkAction&hremarhcode="+hremarhcode;
    	   
    	   
    	   
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
	});
	
	
	
	
	$("#excelDownload").click(function(){
		
		
		var hremarksid = $('#hremarksid').val();
		var hfromdateid = $('#hfromdateid').val();
		var htodateid = $('#htodateid').val();
		
		
		
		
		var hremarksid1 = $('#hremarksid1').val();
		var hfromdateid1 = $('#hfromdateid1').val();
		var htodateid1 = $('#htodateid1').val();
		
		
		
		var hremarksid2 = $('#hremarksid2').val();
		var hfromdateid2 = $('#hfromdateid2').val();
		var htodateid2 = $('#htodateid2').val();
		
		
		var remarktype = $('#remarktype').val();
		
       if(remarktype=="Remarks"){
			
			window.location.href = "communicationPath.html?method=remarksDownloadXLS&hremarksid="+hremarksid
			+"&hfromdateid="+hfromdateid
			+"&htodateid="+htodateid;
		
		}
		else if(remarktype=="meeting"){
			
			window.location.href = "communicationPath.html?method=meetingDownloadXLS&hremarksid="+hremarksid1
			+"&hfromdateid="+hfromdateid1
			+"&htodateid="+htodateid1;
		}
		
		else if(remarktype=="bday"){
		
			window.location.href = "communicationPath.html?method=bdayWishDownloadXLS&hremarksid="+hremarksid2

			+"&hfromdateid="+hfromdateid2
			+"&htodateid="+htodateid2;
			
		}
		
		
		
		
	});
	
	


	
	
	$("#pdfDownload").click(function(){
		
		var hremarksid = $('#hremarksid').val();
		var hfromdateid = $('#hfromdateid').val();
		var htodateid = $('#htodateid').val();
		
		
		
		
		var hremarksid1 = $('#hremarksid1').val();
		var hfromdateid1 = $('#hfromdateid1').val();
		var htodateid1 = $('#htodateid1').val();
		
		
		
		var hremarksid2 = $('#hremarksid2').val();
		var hfromdateid2 = $('#hfromdateid2').val();
		var htodateid2 = $('#htodateid2').val();
		
		var remarktype = $('#remarktype').val();
		
       if(remarktype=="Remarks"){
			
			window.location.href = "communicationPath.html?method=remarksDownloadPDF&hremarksid="+hremarksid
			+"&hfromdateid="+hfromdateid
			+"&htodateid="+htodateid;;
			
		}
		else if(remarktype=="meeting"){
			
			window.location.href = "communicationPath.html?method=meetingDownloadPDF&hremarksid="+hremarksid1
			+"&hfromdateid="+hfromdateid1
			+"&htodateid="+htodateid1;;
		}
		
		else if(remarktype=="bday"){
		
			window.location.href = "communicationPath.html?method=bdayWishDownloadPDF&hremarksid="+hremarksid2

			+"&hfromdateid="+hfromdateid2
			+"&htodateid="+htodateid2;;
			
		}
		
		
		
		
	});
	
	

	
	
	
	
	
});




function getvaldetails(value){
	
	var s1 =value.id;
	
		var assgnmentid = s1;
		
	
	
	$("#hremarhcode").val(assgnmentid);
	
}




















