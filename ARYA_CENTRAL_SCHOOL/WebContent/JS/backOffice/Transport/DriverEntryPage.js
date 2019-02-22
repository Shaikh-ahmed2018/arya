
function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}



$(document).ready(function() {
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if (pageUrl == "driverDetailsPath.html?method=savedriverval"){
		
		$(".errormessagediv").hide();
		$(".successmessagediv").show();

		 setTimeout(function(){
			   
			 window.location.href = "adminMenu.html?method=driverList";
		 
		 },2000);
		
		
	}
	
	
	
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);
	
	
var licensetodrive = $('#hlicensetodrive').val();
	
if(licensetodrive!=null)
	{
	
	 var licensetodrive1 = licensetodrive.split(",");
	
	var s1 = licensetodrive1[0];
	
	var s2 = licensetodrive1[1];
	
	var s3 = licensetodrive1[2];
	
	$("#vehicletodrive option[value="+s1+"]").attr("selected",'true');
	$("#vehicletodrive option[value="+s2+"]").attr("selected",'true');
	$("#vehicletodrive option[value="+s3+"]").attr("selected",'true');

	}
	
	
	
var trans = $('#radio').val();
	
	if (trans == "Male") {
		$("#genderM").attr("checked", true);
	} else if (trans == "Female") {
		$("#genderF").attr("checked", true);
	}
	
	
	
	
	$("#dateofBirthId").datepicker({
		dateFormat : "dd-mm-yy",
		yearRange : 'c-65:c+65',
		maxDate : -1,
		changeMonth : "true",
		changeYear : "true",
		onClose : function(selectedDate) {
			$("#dateofJoinId").datepicker("option", "minDate", selectedDate);
		}
	});
	
	
	
	$("#dateofJoinId").datepicker({
		
		dateFormat : "dd-mm-yy",
		yearRange : 'c-65:c+65',
		maxDate : -1,
		changeMonth : "true",
		changeYear : "true",
		onClose : function(selectedDate) {
			$("#dateofBirthId").datepicker("option", "maxDate", selectedDate);
		}
	});
	
	
	
	$("#dl_issued_date").datepicker(
			{
				dateFormat : "dd-mm-yy",
				yearRange : 'c-65:c+65',
				maxDate : -1,
				changeMonth : "true",
				changeYear : "true",
				yearRange : '1997:' + (new Date).getFullYear(),
				onClose : function(selectedDate) {
					var date2 = $('#dl_issued_date').datepicker(
							'getDate');
					date2.setDate(date2.getDate() + 1);
					$("#dl_validity").datepicker("option",
							"minDate", date2);

				}
			});
	
	
	$("#dl_validity").datepicker(
			{

				dateFormat : "dd-mm-yy",
				yearRange : 'c-65:c+65',
				changeMonth : "true",
				changeYear : "true",
				onClose : function(selectedDate) {

					var date2 = $('#dl_validity').datepicker(
							'getDate');
					date2.setDate(date2.getDate() - 1);
					$("#dl_issued_date").datepicker("option",
							"maxDate", date2);

				}
			});
	
	
	
	
	
	
	
	$("#savedriver").click(function(){
		
		
		
		
		
		/*
		$("#driverid")
		.attr('action',
				'driverDetailsPath.html?method=saveDriver');

		$("#driverid").submit();*/
		
		
		
	/*	var locList = "";
		
		
            $('#licensetodrive :selected').each(function(i, selected){
            
            	locList = $(selected).join(",");

            	
            	
           
            });
            
            
            alert("locList"+locList);*/
		
            
            var drivercode = $('#drivercode').val();
			var name =  $("#name").val();
			var fatherName =  $("#father_name").val();
			var dob =  $("#dateofBirthId").val();
			var gender=$('input[name=gender]:checked').val();
			var mobile = $("#mobile").val();
			var emerg_contact = $("#emerg_contact").val();
			var enq_dateofJoin = $("#dateofJoinId").val();
			var exp = $("#experience").val();
			var address = $("#address").val();
			var drivingLicenseNo = $("#drivingliecenseNo").val();
			var dl_issued_date = $("#dl_issued_date").val();
			var dlValidity = $("#dl_validity").val();
			var age = $("#ageId").val();
			var locList = $("#vehicletodrive").val();

			var mob = parseFloat($('#mobile').val());
			var ecmob = parseFloat($('#emerg_contact').val());
			
			
			
			
			
			
			
			var fileName=$("#uploadlicence").val().split('.').pop().toLowerCase();
			var fileNameCheck=$("#uploadlicence").val();
			
			
			
			
			if(name==null || name==""){
				
				  $(".errormessagediv").show();
					$(".validateTips").text("Field Required Driver Name");
					
					document.getElementById("name").style.border = "1px solid #AF2C2C";
					document.getElementById("name").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			}
			else if(!name.match(/[A-Za-z]+$/i))
			{
				
				 $(".errormessagediv").show();
				$(".validateTips").text("Driver Name Should Be Alphabet");
				document.getElementById("name").style.border = "1px solid #AF2C2C";
				document.getElementById("name").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
				
			}
			else if(fatherName==null || fatherName==""){
				
				 $(".errormessagediv").show();
			    $(".validateTips").text("Field Required Father's Name");
			    document.getElementById("father_name").style.border = "1px solid #AF2C2C";
				document.getElementById("father_name").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false; 
				
			}
			else if(!fatherName.match(/[A-Za-z]+$/i))
			{
				 $(".errormessagediv").show();
				 $(".validateTips").text("Father's Name Should Be Alphabet");
				 document.getElementById("father_name").style.border = "1px solid #AF2C2C";
					document.getElementById("father_name").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if(dob==null || dob==""){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Select Date Of Birth");
				 document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
					document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
				
			}
			else if(age < 18){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Age Should be 18 or Older");
				 document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
					document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
				
			}
			else if(gender==null || gender==""){
				
				 $(".errormessagediv").show();
				 $(".validateTips").text("Select Gender");
				return false;
				
			}

			else if(mobile==null || mobile==""){
				
				 $(".errormessagediv").show();
				 $(".validateTips").text("Field Required Mobile No");
				 document.getElementById("mobile").style.border = "1px solid #AF2C2C";
					document.getElementById("mobile").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if(!mobile.match(/^([0-9])+$/i)){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Mobile No Should be Numeric");
				 document.getElementById("mobile").style.border = "1px solid #AF2C2C";
					document.getElementById("mobile").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if(mobile.length < 10){
				
				 $(".errormessagediv").show();
				 $(".validateTips").text("Enter valid Mobile No");
				 document.getElementById("mobile").style.border = "1px solid #AF2C2C";
					document.getElementById("mobile").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}else if (isNaN(mob) || (mob === 0)) {
				
				$('.errormessagediv').show();
				$('.validateTips').text("Enter valid Mobile No");
				document.getElementById("mobile").style.border = "1px solid #AF2C2C";
				document.getElementById("mobile").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);

				return false;
			}

			


			else if(emerg_contact==null || emerg_contact==""){
				
				 $(".errormessagediv").show();
				 $(".validateTips").text("Field Required Emergency Contact No");
				 document.getElementById("emerg_contact").style.border = "1px solid #AF2C2C";
					document.getElementById("emerg_contact").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if(emerg_contact.length < 10){
				
				 $(".errormessagediv").show();
				 $(".validateTips").text("Enter valid Emergency Contact No");
				 document.getElementById("emerg_contact").style.border = "1px solid #AF2C2C";
					document.getElementById("emerg_contact").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if (isNaN(ecmob) || (ecmob === 0)) {
				
				$('.errormessagediv').show();
				$('.validateTips').text("Enter valid Emergency Contact No");
				document.getElementById("emerg_contact").style.border = "1px solid #AF2C2C";
				document.getElementById("emerg_contact").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);

				return false;
			}

			else if(enq_dateofJoin==null || enq_dateofJoin==""){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Select Date of Joining");
				 document.getElementById("dateofJoinId").style.border = "1px solid #AF2C2C";
					document.getElementById("dateofJoinId").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if(exp==null || exp==""){
				
				 $(".errormessagediv").show();
				 $(".validateTips").text("Field Required Experience");
				 document.getElementById("experience").style.border = "1px solid #AF2C2C";
					document.getElementById("experience").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}

			else if(!exp.match("^[0-9]+$")){
				
				 $(".errormessagediv").show();
				 $(".validateTips").text("Experience Sholud be Numeric");
				 document.getElementById("experience").style.border = "1px solid #AF2C2C";
					document.getElementById("experience").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}

			else if(address==null || address==""){
				 $(".errormessagediv").show();
			    $(".validateTips").text("Field Required Address");
			    document.getElementById("address").style.border = "1px solid #AF2C2C";
				document.getElementById("address").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}
			
			else if(drivingLicenseNo==null || drivingLicenseNo==""){
				 
				 $(".errormessagediv").show();
					$(".validateTips").text("Field Required Driving License No");
					document.getElementById("drivingliecenseNo").style.border = "1px solid #AF2C2C";
					document.getElementById("drivingliecenseNo").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					
					return false;
				}
			
			else if(dl_issued_date==null || dl_issued_date==""){
				
				  $(".errormessagediv").show();
					$(".validateTips").text("Select DL Issued Date");
					document.getElementById("dl_issued_date").style.border = "1px solid #AF2C2C";
					document.getElementById("dl_issued_date").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			}else if(fileName == "exe" || fileName == "ini" || fileName == "html" || fileName == "htm" || fileName == "zip"){
				
				$(".errormessagediv").show();
				$(".validateTips").text("Invalid file!");
				document.getElementById("uploadlicence").style.border = "1px solid #AF2C2C";
				document.getElementById("uploadlicence").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}
			
			
			else if(dlValidity==null || dlValidity==""){
				
				  $(".errormessagediv").show();
					$(".validateTips").text("Select DL Validity Upto");
					document.getElementById("dl_validity").style.border = "1px solid #AF2C2C";
					document.getElementById("dl_validity").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
			
			else if(locList==null || locList==""){
				
				  $(".errormessagediv").show();
					$(".validateTips").text("Select License To Drive");
					document.getElementById("vehicletodrive").style.border = "1px solid #AF2C2C";
					document.getElementById("vehicletodrive").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
			/*else if(validateDriver()== 1){
				 $(".errormessagediv").show();
					$(".validateTips").text(" Driver already exists");
					document.getElementById("name").style.border = "1px solid #AF2C2C";
					document.getElementById("name").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			        }*/
			
			
			else if(validateLicense() == 1 ) {
				
                $(".errormessagediv").show();
				
				$(".validateTips").text(" License No already exists");
				document.getElementById("drivingliecenseNo").style.border = "1px solid #AF2C2C";
				document.getElementById("drivingliecenseNo").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				
				return false;
				
			}
			else{
				document.getElementById("driverformid").submit();
			}
			 
			
			
			
			
			
	
		/*	datalist = {
				"drivercode" : drivercode,
				 "name" : name,
				 "fatherName" : fatherName,
					"dob" : dob ,
					"gender" : gender ,
					"mobile" : mobile ,
					 "emerg_contact" : emerg_contact,
					"enq_dateofJoin" : enq_dateofJoin,
					"exp" : exp,
					 "address" : address,
				    "drivingLicenseNo" : drivingLicenseNo,
				    "dl_issued_date" :  dl_issued_date,
				     "dlValidity" : dlValidity,
					"age" : age ,
					"license" : locList.join(",")
						
			};*/
			
			/*$.ajax({
				type : 'POST',
				url : "driverDetailsPath.html?method=saveDriver",
				data : datalist ,
				async : false,
				success : function(data){
					
					
					var result = $.parseJSON(data);
					
				
					
					if(result.jsonResponse=="Driver Created Successfully")
						{
						
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						 $(".validateTips").text("Driver Created Successfully");
						 
						 setTimeout(function(){
							 
						 location.reload();
						 
						 },5000);
						
						}
					
					
					 if(result.jsonResponse=="Driver Update Successfully")
					{
					
						$(".errormessagediv").hide();
					    $(".successmessagediv").show();
					    $(".validateTips").text("Driver Update Successfully");
					    
					     setTimeout(function(){
					    	 
					     window.location.href="adminMenu.html?method=driverget";
					     
						 },7000);
						
						}
					    
					    
					 setTimeout(function(){
							
						 location.reload();
					 
					 },3000); 
					    
					
				
					}
					
					
				//}
			
			
			});*/
			
			/*}*/	
			
	
			/* setTimeout(function(){
				
				 location.reload();
			 
			 },3000);*/
			
			
	});
	
	
/*$("#uploadlicence").attr('val',$('#').val());

	
	if ($('#hlicenseupload').val() == "") {

		$('#downloadlicenceid').hide();
		
		$('#deleteProfile').hide();

	  }
		else 
		{
			
     	$('#downloadlicenceid').show();
		$('#deleteProfile').show();

     	
    	}
	
	
	$("#downloadlicenceid").click(function(){
		
		window.location.href = "driverDetailsPath.html?method=downloaddriverlicence&filepath="
				+ $('#uploadlicence').attr('val');
			
			var filepath = {'filepath':$('#fileupload').attr('name'),
					         'sno':$('#sno').val()};
			alert(JSON.stringify(filepath));
			var result = callAjax("LeaveStatus.do?parameter=download",filepath);
		});
	*/
	
	
	
	
	var birthcertificate=$("#hlicenseupload").val();
	
	
	if(birthcertificate!="" && birthcertificate!=undefined){
		
		$("#downloadlicenceid").attr('name',birthcertificate);
		
		$("#uploadlicence").hide();
		$("#downloadlicenceid").show();
		$("#deleteProfile").show();
		
		}
	else
		{
		$("#uploadlicence").show();
		$("#downloadlicenceid").hide();
		$("#deleteProfile").hide();
		}
	
		
		
		$('.downloadDoc1').click(
				
				function() {
					
					var path = $(this).attr('name');
					window.location.href = "driverDetailsPath.html?method=downloaddriverlicenc&Path="
							+ path.trim();
				});
		
		$("#deleteProfile").click(function(){
			
			$("#uploadlicence").show();
			
			$("#downloadlicenceid").hide();
			$("#deleteProfile").hide();
			
			$("#hlicenseupload").val("");
			
			
		});
		
		
		
});



function ageCalculateAdd() {
	
	var doofBirth = $('#dateofBirthId').val();

	var birthYear = doofBirth.split("-")[2];

	var currentYear = new Date().getFullYear();

	var yearDiff = parseInt(currentYear) - parseInt(birthYear);

	$('#ageId').val(yearDiff);

}



function validateDriver(){
	
	var driver_validate=0;
	
	var name =  $("#name").val();
	
	var dob =  $("#dateofBirthId").val();
	
	var mobile = $("#mobile").val();
	
	var enq_dateofJoin = $("#dateofJoinId").val();
	
	 var drivercode = $('#drivercode').val();
	 
	 var streamObject = {
				"name" : name,  "dob" : dob,  "mobile" : mobile,"enq_dateofJoin" : enq_dateofJoin ,"drivercode" : drivercode	};
	 
	 $.ajax({

			type : "GET",
			url : 'driverDetailsPath.html?method=validateDriver',
			data : streamObject,
			async : false,
			success : function(data) {
				
				var result = $.parseJSON(data);
				
			
					
			if (result.status=="true") {
				
				driver_validate = 1;
				
				}
				else 
					
				{
					driver_validate = 0;
				}
			
			}
		});
		
		return driver_validate;
	 
	 
}


// for license validation//

function validateLicense(){
	var License_validate=0;
	if($("#hlicense").val()==$("#drivingliecenseNo").val()){
		License_validate=0;
	}else{
		var drivingliecenseNo =  $("#drivingliecenseNo").val();

		streamObject = {"drivingliecenseNo" : drivingliecenseNo},

		$.ajax({
			type : "GET",
			url : 'driverDetailsPath.html?method=validateLicense',
			data : streamObject,
			async : false,
			success : function(data) {

				var result = $.parseJSON(data);
				
				if (result.status == true) {

					License_validate = 1;

				}
				else 
				{
					License_validate = 0;
				}
				
			}
		});
	}
	
	
	return License_validate;
}





function HideError() 

{
	
document.getElementById("name").style.border = "1px solid #ccc";
document.getElementById("name").style.backgroundColor = "#fff";

document.getElementById("father_name").style.border = "1px solid #ccc";
document.getElementById("father_name").style.backgroundColor = "#fff";

document.getElementById("dateofBirthId").style.border = "1px solid #ccc";
document.getElementById("dateofBirthId").style.backgroundColor = "#fff";

document.getElementById("mobile").style.border = "1px solid #ccc";
document.getElementById("mobile").style.backgroundColor = "#fff";

document.getElementById("emerg_contact").style.border = "1px solid #ccc";
document.getElementById("emerg_contact").style.backgroundColor = "#fff";

document.getElementById("dateofJoinId").style.border = "1px solid #ccc";
document.getElementById("dateofJoinId").style.backgroundColor = "#fff";

document.getElementById("experience").style.border = "1px solid #ccc";
document.getElementById("experience").style.backgroundColor = "#fff";

document.getElementById("address").style.border = "1px solid #ccc";
document.getElementById("address").style.backgroundColor = "#fff";

document.getElementById("drivingliecenseNo").style.border = "1px solid #ccc";
document.getElementById("drivingliecenseNo").style.backgroundColor = "#fff";

document.getElementById("dl_issued_date").style.border = "1px solid #ccc";
document.getElementById("dl_issued_date").style.backgroundColor = "#fff";

document.getElementById("dl_validity").style.border = "1px solid #ccc";
document.getElementById("dl_validity").style.backgroundColor = "#fff";

}
