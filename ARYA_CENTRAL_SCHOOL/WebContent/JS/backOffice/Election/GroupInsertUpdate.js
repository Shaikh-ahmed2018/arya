
$(document).ready(function(){
	var count=1;	
	if($("#accyearh").val()=="" || $("#accyearh").val()==undefined){
	getAccyear(count);
	$("#academicYear").val($("#hacademicyaer").val());
	count=Number($("#academicYear :selected").index());
	getAccyear(count);
	$("#academicYear").val($("#hacademicyaer").val());
	}
	rowHoliday =$("#allstudent tbody tr").length-1;
	

	var size = parseInt($("#size").val());
	$("#save").click(function(){
		
		var locid=[];
		var accyearid = $("#academicYear").val();
		var groupname = $("#groupname").val().trim();
		var location = $(".location").val();
		
		//alert($("#groupname").val());
		
		//alert(accyearid);
		if(accyearid == "" ||accyearid == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Feild Required Academic Year");
			document.getElementById("accyearid").style.border = "1px solid #AF2C2C";
			document.getElementById("accyearid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}
		
		if(groupname == "" ||groupname == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Group Name");
			document.getElementById("groupname").style.border = "1px solid #AF2C2C";
			document.getElementById("groupname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}
		else if(location == "" ||location == null){
				$(".errormessagediv").show();
				$(".validateTips").text("Feild Required School Name");
				document.getElementById("location").style.border = "1px solid #AF2C2C";
				document.getElementById("location").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}
		else{
		$("table#allstudent tr[id]").each(function(){
			var loc=$(this).find('[name=location]').val();
			if(loc != "" && loc != undefined){
				locid.push(loc);
			}
		});
		
		datalist = {
			"locid":locid.toString(),
			"accyearid":accyearid,
			"groupname":groupname
		};
		$.ajax({
	 		 type: "POST",
	 		 url: "ElectionMenu.html?method=saveGroupdetails",
	         data:datalist,
	         async:false,
	 		 success: function(data) {
	 		 var result = $.parseJSON(data);
	 		 
	 		 if(result.status=="true"){
	 			$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Adding Record Progressing...");
				$(".successmessagediv").delay(3000).fadeOut();
				setTimeout(function() {

					window.location.href="adminMenu.html?method=GroupList";
			}, 2000);
	 		 }
	 		 else if(result.status=="exists"){
	 			$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Group Name Already Exists");
				document.getElementById("groupname").style.border = "1px solid #AF2C2C";
				document.getElementById("groupname").style.backgroundColor = "#FFF7F7";
				$(".errormessagediv").delay(3000).fadeOut();
				
	 		 }
	 		 else{
		 			$('.successmessagediv').hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Fail to Add Record...");
					$(".errormessagediv").delay(3000).fadeOut();
					
		 		 }
	 		}
		});
		return false;
	}
});
	
	$("#update").click(function(){
		
		
		//var schoolIdArray=[];
		var acchidden =$("#accyearh").val();
		var groupname =$("#groupname").val();
		var groupnamehidden = $("#groupnameh").val();
		
		if(groupname == "" ||groupname == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Please Enter Group Name");
			document.getElementById("groupname").style.border = "1px solid #AF2C2C";
			document.getElementById("groupname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}
		
		
	
		 var schoolIdArray = new Array();
		    $("select.location").each(function() {

		        doesExisit = ($.inArray($(this).val(), schoolIdArray) == -1) ? false : true;
		        if (!doesExisit) {
		        	schoolIdArray.push($(this).val());
		        } else {
		        	$(".errormessagediv").show();
					$(".validateTips").text("Please Remove Duplicate data");
					document.getElementByClass("location").style.border = "1px solid #AF2C2C";
					document.getElementByClass("location").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					
					return false;
				}
		      
		    });
		
		   // to check array elements
		
		datalist = {
			"acchidden" : acchidden,
			"grouphidden" :groupnamehidden,
			"schoolIdArray":schoolIdArray.toString(),
			"groupname": groupname,
			"hiddengroupname": $("#hiddengroupname").val()
	
			
		};
		$.ajax({
			type :'POST',
			url : "ElectionMenu.html?method=updateElectionGroup",
			data:datalist,
			async : false,
			success : function(data) {
			
				 var result = $.parseJSON(data);
				 
				 
				 if(result.status=="true"){
			 			$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Adding Record Progressing...");
						$(".successmessagediv").delay(3000).fadeOut();
						setTimeout(function() {

							window.location.href="adminMenu.html?method=GroupList";
					}, 2000);
				 }	
				
				 
				 //checking the repeated data for update time
				 else  if(result.status=="exists"){
			 			$('.successmessagediv').hide();
						$(".errormessagediv").show();
						$(".validateTips").text("Group Name Already Exists");
						document.getElementById("groupname").style.border = "1px solid #AF2C2C";
						document.getElementById("groupname").style.backgroundColor = "#FFF7F7";
						$(".errormessagediv").delay(3000).fadeOut();
						
			 		 }
				  else{
			 			$('.successmessagediv').hide();
						$(".errormessagediv").show();
						$(".validateTips").text("Fail to Add Record...");
						$(".errormessagediv").delay(3000).fadeOut();
						
				  }
		 		}
			});
			return false;
			 		 
				
		
});
	if(rowHoliday<1)
	addMoreHoliday();
});
function addMoreHoliday(frm) {
	
if(rowHoliday<5){
	rowHoliday++;
	$('#allstudent tr').last().after(
					'<tr id="holidayId'
							+ rowHoliday
						    + '"><td>'+rowHoliday+'</td><td ><select name="location" id="location'+rowHoliday+'" class="form-control location" style="min-width:150px;"><option value="">----Select----</option></select></td><td align="right"><a href="javascript:void(0);" class="glyphicon glyphicon-trash" onclick="removeHoliday('
						    + rowHoliday +');"></a></td></tr>');
		getLocation(rowHoliday);
		
		$(".location").change(function(){
			pointer=$(this);
			$("#allstudent tbody tr[id]").find(".location").not(pointer).each(function(){
				if($(this).val()==$(pointer).val()){
					pointer.val("");
				}
			});
		});
	}
}

function removeHoliday(removeNum) {
	jQuery('#holidayId' + removeNum).remove();
rowHoliday--;
}

function getLocation(pointer){
	
	$.ajax({
		type : 'POST',
		url : "ElectionMenu.html?method=getLocation",
		async : false,
		success : function(data) {
		var result = $.parseJSON(data);
	
	$("#location"+pointer).empty();
	$("#location"+pointer).append('<option value="">-------Select-------</option>');
		for ( var j = 0; j < result.locList.length; j++) {

				$("#location"+pointer).append('<option value="'+ result.locList[j].locationId+ '">'
					+ result.locList[j].locationName+ '</option>');
		}	
		}
	});
}

function getAccyear(count){

	$.ajax({
		type : 'POST',
		url : "ElectionMenu.html?method=getAccYear",
		async : false,
		success : function(data) {
			
		var result = $.parseJSON(data);
	
		$('#academicYear').empty();
		$('#academicYear').append('<option value="">----Select----</option>');
		for ( var j = count-1; j < result.accyar.length; j++) {

			$('#academicYear').append('<option value="'+ result.accyar[j].accyearId+ '">'
					+ result.accyar[j].accyearname+ '</option>');

		}
		
		}
	});	
}

function HideError() {
	document.getElementById("groupname").style.backgroundColor = "#fff";
	document.getElementById("groupname").style.border = "1px solid #ccc";

}


