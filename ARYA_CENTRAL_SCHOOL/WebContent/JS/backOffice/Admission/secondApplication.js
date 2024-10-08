
function readURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imagePreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}

}
$(document).ready(function() {
	
	$("#fatherNameId").keydown(function(e){
		var key=e.keyCode;
		if(!((key == 8) || ((key == 16)) || (key == 32) || (key == 46) || (key == 9) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))){
			e.preventDefault();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Text.");
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});
	
	$("#fatherMobileNoId").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Number."); 
			$(".errormessagediv").delay(2000).fadeOut();
			return false;
		}
	});
	
	$("#emergencyNo").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) 
		{
		$(".errormessagediv").show();
		$(".validateTips").text("Enter Only Number."); 
		$(".errormessagediv").delay(2000).fadeOut();
		return false;
	}});
	
	
	$("#motherNameId").keydown(function(e){
		var key=e.keyCode;
		if(!((key == 8) || ((key == 16)) || (key == 32) || (key == 46) || (key == 9) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))){
			e.preventDefault();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Text.");
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});
	
	$("#motherMobileNoId").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Number."); 
			$(".errormessagediv").delay(2000).fadeOut();
			return false;
		}
	});
	
	/*hidden div value*/
	var enquiryId=$("#enquiryid").val();
	$('#enquiryid').val(enquiryId);
	$("#hiddingotheregion").hide();
	$("#hiddingothercaste").hide();
	$("#hiddingothercastecategory").hide();
	$("#hiddenanyotherboard").hide();
	$("#hiddensiblingid").hide();
	$("#hiddensiblingname").hide();
	$("#hiddenfatheralumniname").hide();
	$("#hiddenfatheralumniyear").hide();
	$("#hiddenmotheralumniname").hide();
	$("#hiddenmotheralumniyear").hide();

	/*onload functions*/

	getReligionDetails();
	getClassName();
	getOccupation();
	$("#isSibling option[value='No']").attr('selected', 'true');
	$('.setImage').attr('src','images/profile_photo.png');

	/*onchange functions*/

	var setImage=$('.setImage').attr("src");
	if(setImage == 'images/profile_photo.png'){
		$("#removeSpanId").hide();
	}else{
		$("#removeSpanId").show();
	}

	$("#studentImageId1").change(function() {
		$("#removeSpanId").show();
		if(checkFile1() == false){
			$("#imagePreview").hide();

		}else{
			$("#imagePreview").show();
			/*$("#removeSpanId").hide();*/
			readURL(this);
		}
	});

	$("#removeSpanId").click(function(){
		$('.setImage').attr('src','images/profile_photo.png');
		$("#removeSpanId").hide();
	});

	$("#religion").change(function() {
		if($('#religion').val() == "Other"){
			$("#hiddingotheregion").show();
			$("#hiddingothercaste").hide();
			$('#casteId').empty();
			$('#casteId').append('<option value="' + "" + '">'+ "----------Select----------"+ '</option>');
			$('#casteId').append('<option value="Other">'+ "Others"+ '</option>');
		}else{
			$("#hiddingotheregion").hide();
			getCasteDetails();
		}
	});

	$("#casteId").change(function() {
		if($('#casteId').val() == "Other"){
			$("#hiddingothercaste").show();
		}else{
			$("#hiddingothercaste").hide();
			getCasteCategoryDetails();
		}
	});
	
	$("#casteCategoryId").change(function() {
		if($('#casteCategoryId').val() == "Other"){
			$("#hiddingothercastecategory").show();
		}else{
			$("#hiddingothercastecategory").hide();
		}
	});

	$("#schemeofstudyId").change(function() {
		if($('#schemeofstudyId').val() == "Other"){
			$("#hiddenanyotherboard").show();
		}else{
			$("#hiddenanyotherboard").hide();
		}
	});

	$("#isSibling").change(function() {
		if($('#isSibling').val() == "Yes"){
			$("#hiddensiblingid").show();
			$("#isSiblingId").val("");
			$("#hiddensiblingname").show();
			$("#isSiblingName").val("");
		}else{
			$("#hiddensiblingid").hide();
			$("#hiddensiblingname").hide();
		}
	});

	$("#parentsAlumni").change(function() {
		if($('#parentsAlumni').val() == "Father"){
			$("#hiddenfatheralumniname").show();
			$("#fatherAlumniname").val("");
			$("#hiddenfatheralumniyear").show();
			$("#fatherAlumniyear").val("");
			$("#hiddenmotheralumniname").hide();
			$("#hiddenmotheralumniyear").hide();
		}else if($('#parentsAlumni').val() == "Mother"){
			$("#hiddenmotheralumniname").show();
			$("#motherAlumniname").val("");
			$("#hiddenmotheralumniyear").show();
			$("#motherAlumniyear").val("");
			$("#hiddenfatheralumniname").hide();
			$("#hiddenfatheralumniyear").hide();
		}else if($('#parentsAlumni').val() == "Father/Mother"){
			$("#hiddenfatheralumniname").show();
			$("#fatherAlumniname").val("");
			$("#hiddenfatheralumniyear").show();
			$("#fatherAlumniyear").val("");
			$("#hiddenmotheralumniname").show();
			$("#motherAlumniname").val("");
			$("#hiddenmotheralumniyear").show();
			$("#motherAlumniyear").val("");
		}else{
			$("#hiddenfatheralumniname").hide();
			$("#hiddenfatheralumniyear").hide();
			$("#hiddenmotheralumniname").hide();
			$("#hiddenmotheralumniyear").hide();
		}
	});
	
	
	var enquiryid=$('#enquiryid').val();
	if(enquiryid != null){
		if(validateEnquiryIdStatus(enquiryid)){
			admissionId = {
					'enquiryid' : enquiryid
			};
			var admissionStudentRecord = callAjax("parentrequiresappointment.html?method=getAdmissionRegistrationDetails",admissionId);
			
			viewAdmissionStudentDetails(admissionStudentRecord);
		}
		
	}
	
	$("#save").click(function() {
		$("#dialog").dialog("open");
		$("#dialog").empty();
		$("#dialog").append("<p>Are you sure to Submit?</p>");
	});

	$('#checkAddressId').click(function(){
        if($(this).prop("checked") == true){
            var postadd=$('#paddrs').val();
        
            if(postadd != ""){
            	$('#addressofcommunicationid').val(postadd);
            }else{
            	$(".errormessagediv").show();
            	
            	$(".validateTips").css({
            		'width':'300px'
            	});
        		$(".validateTips").text("Field Required Permanent Address");
        		$("#paddrs").focus();
        		document.getElementById("paddrs").style.border = "1px solid #AF2C2C";
        		document.getElementById("paddrs").style.backgroundColor = "#FFF7F7";
        		setTimeout(function() {
        			$('#errorhover').fadeOut();
        		}, 3000);
        		
        		$('#checkAddressId').prop("checked",false) ;
            }
        }
        else if($(this).prop("checked") == false){
        	$('#addressofcommunicationid').val('');
        }
    });
	
	$("#dialog").dialog({		

		autoOpen: false,
		modal: true,
		title:'Registration Details',
		buttons : {
			"Yes" : function()
			{
				var hiddenval=$('#enquiryid').val();
				if (Validate() && hiddenval !=null )
				{
					$("#formstudent").submit();
					$("#formstudent").attr('action','parentrequiresappointment.html?method=saveSecondAdmissionApplication');
					/*window.location.href="parentrequiresappointment.html?method=Successmessagedisplay";*/
				}
				$(this).dialog("close");
			},
			"No" : function() {
				$(this).dialog("close");
			}
		}
	});



$("#dateofBirthId").datepicker({
	dateFormat : "dd-mm-yy",
	defaultDate : "+1w",
	maxDate : 0,
	changeYear : true,
	changeMonth : true,
	numberOfMonths : 1
});

});

function getReligionDetails(){

	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=getReligionForDropDown",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#religion').empty();
			$('#religion').append('<option value="' + "" + '">'	+ "----------Select----------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#religion').append(
						'<option value="'
						+ result.jsonResponse[j].religionId
						+ '">'
						+ result.jsonResponse[j].religion
						+ '</option>');
			}
			$('#religion').append('<option value="Other">'	+ "Other" + '</option>');
		}
	});
}

function getCasteDetails(){

	var religionId = null;
	if (($('#religionhiddenid').val() != "" && $('#religionhiddenid').val() != null && $("#religion").val() =="" )) {
		religionId = $("#religionhiddenid").val();
	}else if($('#religionhiddenid').val() == $("#religion").val()){
		religionId = $("#religionhiddenid").val();
	}else {
		religionId = $('#religion').val();
	}

	var data = {
			"religionId" : religionId
	};

	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=getCasteForDropDown",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#casteId').empty();
			$('#casteId').append('<option value="' + "" + '">'+ "----------Select----------"+ '</option>');
			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#casteId').append(
						'<option value="'
						+ result.jsonResponse[j].casteId
						+ '">'
						+ result.jsonResponse[j].caste
						+ '</option>');
			}
		}
	});
}

function getClassName() {
	$.ajax({
		type : 'POST',
		url : "parentrequiresappointment.html?method=getClassDetail",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#classId').html("");
			$('#classId').append('<option value="' + "" + '">' + "----------Select----------" + '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {
				$('#classId').append(
						'<option value="'
						+ result.ClassList[j].classcode + '">'
						+ result.ClassList[j].classname
						+ '</option>');
			}

		}
	});
}

function checkFile1(){
	var fileval = $("#studentImageId1").val();

	if (fileval != undefined && fileval != ''){
		var extval = fileval.substring(fileval.lastIndexOf('.') + 1);
		if(extval != "jpeg" && extval != "jpg" && extval != "png" && extval != "PNG" && extval != "JPG" && extval != "JPEG"){
			$('.errormessagediv').show();
			$('.validateTips').text("Student Photo Should be a jpeg,jpg and png File");
			document.getElementById("studentImageId1").style.border = "1px solid #AF2C2C";
			document.getElementById("studentImageId1").style.backgroundColor = "#FFF7F7";
			$('.errormessagediv').delay(3000).fadeOut("slow");
			$("#studentImageId1").val("");
			return false;
		}
	}
}

function getOccupation(){
	$.ajax({
		type : 'POST',
		url : "teacherregistration.html?method=getOccupation",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#fatherOccupation,#motherOccupation,#guardianOccupation').empty();
			$('#fatherOccupation,#motherOccupation,#guardianOccupation').append('<option value="' + "" + '">'+ "----------Select----------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#fatherOccupation,#motherOccupation,#guardianOccupation').append(
						'<option value="'
						+ result.jsonResponse[j].occupationId
						+ '">'
						+ result.jsonResponse[j].occupation
						+ '</option>');
			}
		}
	});
}

function fatherValidatePhoneNo() {
	var phoneId = $('#fatherMobileNoId').val();
	if ((phoneId != '' && phoneId != undefined)) {
		phoneNo = {
				"phoneId" : phoneId
		},
		$.ajax({
			url : "studentRegistration.html?method=validatePhoneNo",
			data : phoneNo,
			async : false,
			success : function(data) {

				var response = $.parseJSON(data);
				$('.errormessagediv').css({
					'display' : ''
				});
				$('.validateTips').text(response.message);
			}

		});
	}
}

function motherValidatePhoneNo() {
	var phoneId = $('#motherMobileNoId').val();
	if ((phoneId != '' && phoneId != undefined)) {
		phoneNo = {
				"phoneId" : phoneId
		},
		$.ajax({
			url : "studentRegistration.html?method=validatePhoneNo",
			data : phoneNo,
			async : false,
			success : function(data) {

				var response = $.parseJSON(data);
				$('.errormessagediv').css({
					'display' : ''
				});
				$('.validateTips').text(response.message);
			}

		});
	}
}

function motherValidateEmail() {
	var emailid = $('#motheremailId').val();

	if ((emailid != '' && emailid != undefined)) {
		emailCheck = {
				"emailid" : emailid
		},
		$.ajax({
			url : "studentRegistration.html?method=validateEmail",
			data : emailCheck,
			async : false,
			success : function(data) {
				var response = $.parseJSON(data);
				$('.errormessagediv').css({
					'display' : ''
				});
				$('.validateTips').text(response.message);
			}

		});
	}
}

function fatherValidateEmail() {
	var emailid = $('#fatherEmail').val();
	if ((emailid != '' && emailid != undefined)) {
		emailCheck = {
				"emailid" : emailid
		},

		$.ajax({
			url : "studentRegistration.html?method=validateEmail",
			data : emailCheck,
			async : false,
			success : function(data) {
				var response = $.parseJSON(data);

				$('.errormessagediv').css({
					'display' : ''
				});
				$('.validateTips').text(response.message);
			}

		});
	}
}

function Validate() {
	var bValid = true;
	var studentFirstName = $('#studentFirstNameId');
	var studentLastName = $('#studentLastNameId');
	var dateofBirth = $('#dateofBirthId').val();
	var gendertype = $('#genderId').val();
	var state = $('#stateId').val();
	var religion=$('#religion');
	var otherreligion=$('#otherreligionId');
	var caste=$('#casteId');
	var othercaste=$('#othercasteId');
	var othercastecategory=$('#othercastecategoryId');
	var castecategory=$('#casteCategoryId');
	var addrs=$('#paddrs').val();
	var addrsofcommun=$('#addressofcommunicationid').val();
	var preferedphno=$('#preferedphno').val();
	var schemeofstudy=$('#schemeofstudyId').val();
	var anyotherboard=$('#anyotherboardId').val();
	var classid=$('#classId').val();
	var isSibling=$('#isSibling').val();
	var isSiblingId=$('#isSiblingId').val();
	var isSiblingName=$('#isSiblingName').val();
	var fathername=$('#fatherNameId').val();
	var fathermobile=$('#fatherMobileNoId').val();
	var mothername=$('#motherNameId').val();
	var mothermobile=$('#motherMobileNoId').val();
	var parentsalumni=$('#parentsAlumni').val();
	var fatheralumniname=$('#fatherAlumniname').val();
	var fatheralumniyear=$('#fatherAlumniyear').val();
	var motheralumniname=$('#motherAlumniname').val();
	var motheralumniyear=$('#motherAlumniyear').val();


	if (studentFirstName.val() == "" || studentFirstName.val() == null) {

		$(".errormessagediv").show();
		$(".validateTips").text("Field Required First Name.");
		$("#studentFirstNameId").focus();
		document.getElementById("studentFirstNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("studentFirstNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("studentFirstNameId").style.border = "1px solid #ccc";
			document.getElementById("studentFirstNameId").style.backgroundColor = "#fff";
		}, 300);

		bValid= false;

	} else if (!studentFirstName.val().match(/[A-Za-z]+$/i)) {

		$('.errormessagediv').show();
		$('.validateTips').text("Field Required Valid First Name.");
		$("#studentFirstNameId").focus();
		document.getElementById("studentFirstNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("studentFirstNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("studentFirstNameId").style.border = "1px solid #ccc";
			document.getElementById("studentFirstNameId").style.backgroundColor = "#fff";
		}, 500);

		bValid= false;

	}else if(dateofBirth == null || dateofBirth == ""){
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Date Of Birth.");
		$("#studentrollno").focus();
		document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
		document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("dateofBirthId").style.border = "1px solid #ccc";
			document.getElementById("dateofBirthId").style.backgroundColor = "#fff";
		}, 500);

		bValid= false;
	}else if (gendertype == "" || gendertype == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Field Required Gender.");
		$("#genderId").focus();
		document.getElementById("genderId").style.border = "1px solid #AF2C2C";
		document.getElementById("genderId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("genderId").style.border = "1px solid #ccc";
			document.getElementById("genderId").style.backgroundColor = "#fff";
		}, 500);

		bValid=false;
	}else if (state == "" || state == null) {
		$('.errormessagediv').show();
		$('.validateTips').text("Field Required State.");
		$("#stateId").focus();
		document.getElementById("stateId").style.border = "1px solid #AF2C2C";
		document.getElementById("stateId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("stateId").style.border = "1px solid #ccc";
			document.getElementById("stateId").style.backgroundColor = "#fff";
		}, 500);

		bValid=false;
	}else if (religion.val() == "" || religion.val() == null){
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Religion.");
		$("#religion").focus();
		document.getElementById("religion").style.border = "1px solid #AF2C2C";
		document.getElementById("religion").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("religion").style.border = "1px solid #ccc";
			document.getElementById("religion").style.backgroundColor = "#fff";
		}, 500);

		bValid=false;

	}else if (religion.val() == 'Other' && otherreligion.val() == "") {
		$('.errormessagediv').show();
		$('.validateTips').text("Field Required Other Religion Name.");
		$("#otherreligionId").focus();
		document.getElementById("otherreligionId").style.border = "1px solid #AF2C2C";
		document.getElementById("otherreligionId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("otherreligionId").style.border = "1px solid #ccc";
			document.getElementById("otherreligionId").style.backgroundColor = "#fff";
		}, 500);

		bValid=false;
	}else if (caste.val() == "" || caste.val() == null){
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Caste.");
		$("#casteId").focus();
		document.getElementById("casteId").style.border = "1px solid #AF2C2C";
		document.getElementById("casteId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("casteId").style.border = "1px solid #ccc";
			document.getElementById("casteId").style.backgroundColor = "#fff";
		}, 500);

		bValid=false;
	}else if (caste.val() == 'Other' && othercaste.val() == "") {
		$('.errormessagediv').show();
		$('.validateTips').text("Field Required Other Caste.");
		$("#othercasteId").focus();
		document.getElementById("othercasteId").style.border = "1px solid #AF2C2C";
		document.getElementById("othercasteId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("othercasteId").style.border = "1px solid #ccc";
			document.getElementById("othercasteId").style.backgroundColor = "#fff";
		}, 500);

		bValid=false;
	}
   else if (castecategory.val() == "" || castecategory.val() == null) {
	    $("#dialog").dialog("close");
		$('.errormessagediv').show();
		$('.validateTips').text("Field Required  Caste Category.");
		$("#castecategory").focus();
		document.getElementById("castecategory").style.border = "1px solid #AF2C2C";
		document.getElementById("castecategory").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("castecategory").style.border = "1px solid #ccc";
			document.getElementById("castecategory").style.backgroundColor = "#fff";
		}, 500);

		bValid=false;
	}
	else if (addrs == "" || addrs == null){

		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Permanent Address.");
		$("#paddrs").focus();
		document.getElementById("paddrs").style.border = "1px solid #AF2C2C";
		document.getElementById("paddrs").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("paddrs").style.border = "1px solid #ccc";
			document.getElementById("paddrs").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if (addrsofcommun == "" || addrsofcommun == null){

		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Address of Communication.");
		$("#addressofcommunicationid").focus();
		document.getElementById("addressofcommunicationid").style.border = "1px solid #AF2C2C";
		document.getElementById("addressofcommunicationid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("addressofcommunicationid").style.border = "1px solid #ccc";
			document.getElementById("addressofcommunicationid").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if (preferedphno == "" || preferedphno == null){

		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Preferred Phone No. for School SMS.");
		$("#preferedphnoId").focus();
		document.getElementById("preferedphnoId").style.border = "1px solid #AF2C2C";
		document.getElementById("preferedphnoId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("preferedphnoId").style.border = "1px solid #ccc";
			document.getElementById("preferedphnoId").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if (schemeofstudy == "" || schemeofstudy == null){

		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Scheme Of Study.");
		$("#schemeofstudyId").focus();
		document.getElementById("schemeofstudyId").style.border = "1px solid #AF2C2C";
		document.getElementById("schemeofstudyId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("schemeofstudyId").style.border = "1px solid #ccc";
			document.getElementById("schemeofstudyId").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if (schemeofstudy == "Other" && anyotherboard == ""){

		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Any Other State Board.");
		$("#anyotherboardId").focus();
		document.getElementById("anyotherboardId").style.border = "1px solid #AF2C2C";
		document.getElementById("anyotherboardId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("anyotherboardId").style.border = "1px solid #ccc";
			document.getElementById("anyotherboardId").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if (classid == "" || classid == null){

		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Class Looking for Admission.");
		$("#classId").focus();
		document.getElementById("classId").style.border = "1px solid #AF2C2C";
		document.getElementById("classId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("classId").style.border = "1px solid #ccc";
			document.getElementById("classId").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if (isSibling == "Yes" && isSiblingId == ""){

		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Sibling ID.");
		$("#isSiblingId").focus();
		document.getElementById("isSiblingId").style.border = "1px solid #AF2C2C";
		document.getElementById("isSiblingId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("isSiblingId").style.border = "1px solid #ccc";
			document.getElementById("isSiblingId").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if (isSibling == "Yes" && isSiblingName == ""){

		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Sibling Name.");
		$("#isSiblingName").focus();
		document.getElementById("isSiblingName").style.border = "1px solid #AF2C2C";
		document.getElementById("isSiblingName").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("isSiblingName").style.border = "1px solid #ccc";
			document.getElementById("isSiblingName").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if (fathername == null || fathername == "") {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Father Name.");
		$("#fatherNameId").focus();
		document.getElementById("fatherNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("fatherNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("fatherNameId").style.border = "1px solid #ccc";
			document.getElementById("fatherNameId").style.backgroundColor = "#fff";
		}, 500);

		bValid=false;
	}else if (!fathername.match(/[A-Za-z ]+$/i)) {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Father Name.");
		$("#fatherNameId").focus();
		document.getElementById("fatherNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("fatherNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("fatherNameId").style.border = "1px solid #ccc";
			document.getElementById("fatherNameId").style.backgroundColor = "#fff";
		}, 1000);

		bValid=false;
	}
	else if(fathermobile == "") {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Mobile Number.");
		$("#fatherMobileNoId").focus();
		document.getElementById("fatherMobileNoId").style.border = "1px solid #AF2C2C";
		document.getElementById("fatherMobileNoId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("fatherMobileNoId").style.border = "1px solid #ccc";
			document.getElementById("fatherMobileNoId").style.backgroundColor = "#fff";
		}, 500);

		bValid= false;

	} 
	else if (!fathermobile.match(/^(?!0{6})([0-9])+$/i)) {
		$(".errormessagediv").show();

		$(".validateTips").text("Enter Valid Mobile");
		$("#fatherMobileNoId").focus();

        
		document.getElementById("fatherMobileNoId").style.border = "1px solid #AF2C2C";
		document.getElementById("fatherMobileNoId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errormessagediv').fadeOut();
		}, 3000);

	}
	
	
	else if (mothername == null || mothername == "") {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Mother Name.");
		$("#motherNameId").focus();
		document.getElementById("motherNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("motherNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("motherNameId").style.border = "1px solid #ccc";
			document.getElementById("motherNameId").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;

	}else if (!mothername.match(/[A-Za-z ]+$/i)) {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Father Name.");
		$("#motherNameId").focus();
		document.getElementById("motherNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("motherNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("motherNameId").style.border = "1px solid #ccc";
			document.getElementById("motherNameId").style.backgroundColor = "#fff";
		}, 1000);

		bValid=false;
	}
	else if(mothermobile == "") {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Mobile Number.");
		$("#motherMobileNoId").focus();
		document.getElementById("motherMobileNoId").style.border = "1px solid #AF2C2C";
		document.getElementById("motherMobileNoId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("motherMobileNoId").style.border = "1px solid #ccc";
			document.getElementById("motherMobileNoId").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}
	
	else if (!mothermobile.match(/^(?!0{6})([0-9])+$/i)) {
		$(".errormessagediv").show();

		$(".validateTips").text("Enter Valid Mobile");
		$("#motherMobileNoId").focus();

        
		document.getElementById("motherMobileNoId").style.border = "1px solid #AF2C2C";
		document.getElementById("motherMobileNoId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errormessagediv').fadeOut();
		}, 3000);

	}
	else if(parentsalumni == "Father" && fatheralumniname == "") {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Father Name.");
		$("#fatherAlumniname").focus();
		document.getElementById("fatherAlumniname").style.border = "1px solid #AF2C2C";
		document.getElementById("fatherAlumniname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("fatherAlumniname").style.border = "1px solid #ccc";
			document.getElementById("fatherAlumniname").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if(parentsalumni == "Father" && fatheralumniyear == "") {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Year.");
		$("#fatherAlumniyear").focus();
		document.getElementById("fatherAlumniyear").style.border = "1px solid #AF2C2C";
		document.getElementById("fatherAlumniyear").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("fatherAlumniyear").style.border = "1px solid #ccc";
			document.getElementById("fatherAlumniyear").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if(parentsalumni == "Mother" && motheralumniname == "") {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Mother Name.");
		$("#motherAlumniname").focus();
		document.getElementById("motherAlumniname").style.border = "1px solid #AF2C2C";
		document.getElementById("motherAlumniname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("motherAlumniname").style.border = "1px solid #ccc";
			document.getElementById("motherAlumniname").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if(parentsalumni == "Mother" && motheralumniyear == "") {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Year.");
		$("#motherAlumniyear").focus();
		document.getElementById("motherAlumniyear").style.border = "1px solid #AF2C2C";
		document.getElementById("motherAlumniyear").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("motherAlumniyear").style.border = "1px solid #ccc";
			document.getElementById("motherAlumniyear").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if(parentsalumni == "Father/Mother" && fatheralumniname == "") {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Father Name.");
		$("#motherAlumniyear").focus();
		document.getElementById("motherAlumniyear").style.border = "1px solid #AF2C2C";
		document.getElementById("motherAlumniyear").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("motherAlumniyear").style.border = "1px solid #ccc";
			document.getElementById("motherAlumniyear").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if(parentsalumni == "Father/Mother" && fatheralumniyear == "") {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Year.");
		$("#motherAlumniyear").focus();
		document.getElementById("motherAlumniyear").style.border = "1px solid #AF2C2C";
		document.getElementById("motherAlumniyear").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("motherAlumniyear").style.border = "1px solid #ccc";
			document.getElementById("motherAlumniyear").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if(parentsalumni == "Father/Mother" && motheralumniname == "") {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Mother Name.");
		$("#motherAlumniyear").focus();
		document.getElementById("motherAlumniyear").style.border = "1px solid #AF2C2C";
		document.getElementById("motherAlumniyear").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("motherAlumniyear").style.border = "1px solid #ccc";
			document.getElementById("motherAlumniyear").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else if(parentsalumni == "Father/Mother" && motheralumniyear == "") {
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required Year.");
		$("#motherAlumniyear").focus();
		document.getElementById("motherAlumniyear").style.border = "1px solid #AF2C2C";
		document.getElementById("motherAlumniyear").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("motherAlumniyear").style.border = "1px solid #ccc";
			document.getElementById("motherAlumniyear").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;
	}else{
		return bValid;
	}
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

function viewAdmissionStudentDetails(admissionStudentRecord) {
	$("#studentFirstNameId").val(admissionStudentRecord[0].studentfirstName);
	$("#studentLastNameId").val(admissionStudentRecord[0].studentLastName);
	$("#streamid").val(admissionStudentRecord[0].stream);
	$("#classId option[value='"+ admissionStudentRecord[0].classid+"']").attr('selected', 'true');
	$("#paddrs").val(admissionStudentRecord[0].address);
	$("#preferedphnoId option[value='"+ admissionStudentRecord[0].relationship+"']").attr('selected', 'true');
	var parents=admissionStudentRecord[0].relationship;
	if(parents == "father"){
		$("#fatherNameId").val(admissionStudentRecord[0].parents);
		$("#fatherMobileNoId").val(admissionStudentRecord[0].mobile_number);
		$("#fatherEmail").val(admissionStudentRecord[0].emailId);
	}else if(parents == "mother"){
		$("#motherNameId").val(admissionStudentRecord[0].parents);
		$("#motherMobileNoId").val(admissionStudentRecord[0].mobile_number);
		$("#motherEmail").val(admissionStudentRecord[0].emailId);
	}else{
		$("#fatherNameId").val("");
		$("#fatherMobileNoId").val("");
		$("#fatherEmail").val("");
		$("#motherNameId").val("");
		$("#motherMobileNoId").val("");
		$("#motherEmail").val("");
	}
}

function validateEnquiryIdStatus(enquiryid){
	data={
		'enquiryid': enquiryid
	},
	
	$.ajax({
		type : "GET",
		url : "parentrequiresappointment.html?method=getValidateAdmissionNo",
		data : data,
		async : false,
		success : function(data) {
			$('.errormessagediv').hide();
			var result = $.parseJSON(data);
			if (result.validatenostatus == "true") {
				/*$('.errormessagediv').show();
				$('.validateTips').text("Application Already Submitted");*/
				window.location.href="adminMenu.html?method=OnlinereturnFormPage";
				status = true;
			} else {
				$('.errormessagediv').css({
					'display' : 'none'
				});

				status = false;
			}
		}
	});
	return status;
}

function getCasteCategoryDetails(){
	var casteId = null;
		casteId = $('#casteId').val();
	
	var data = {
			"casteId" : casteId
	};
	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=getCasteCategoryForDropDown",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#casteCategoryId').empty();
			$('#casteCategoryId').append('<option value="' + "" + '">'+ "----------Select----------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#casteCategoryId').append(
						'<option value="'
						+ result.jsonResponse[j].casteCategoryId
						+ '">'
						+ result.jsonResponse[j].casteCategory
						+ '</option>');
			}


		}
	});
}