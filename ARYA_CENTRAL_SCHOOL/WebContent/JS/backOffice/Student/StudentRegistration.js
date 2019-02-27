$(document).ready(function() {
	
	
	$("#Print").click(function(){
		id=$(".select:checked").attr("id");
		getAdmissionDetails(id);
		printApplication();
		
	});
	

	var categoryId = "category";
	var url=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(url.split("&")[0] == "studentRegistration.html?method=editStudent"){
		$('#admissionform').hide();
	}
	
	if(url.split("&")[0]!= "adminMenu.html?method=studentList"){
		$("#dateofBirthId").datepicker({
			dateFormat : "dd-mm-yy",
			yearRange : 'c-65:c+65',
			maxDate : -730,
			changeMonth : "true",
			changeYear : "true",
			yearRange : '1997:' + (new Date).getFullYear(),
			onClose : function(selectedDate) {
				if ((selectedDate != "") && (selectedDate != undefined)) {
					var date2 = $('#dateofBirthId').datepicker('getDate');
					date2.setDate(date2.getDate() + 1);
					$("#dateofJoinId").datepicker("option","minDate", date2);
				}
			}
		});
		$("#dateofJoinId").datepicker({

			dateFormat : "dd-mm-yy",
			yearRange : 'c-65:c+65',
			maxDate : 0,
			changeMonth : "true",
			changeYear : "true",
			onClose : function(selectedDate) {
				if ((selectedDate != "") && (selectedDate != undefined)) {
					var date2 = $('#dateofJoinId').datepicker('getDate');
					date2.setDate(date2.getDate() - 1);
					$("#dateofBirthId").datepicker("option", "maxDate", date2);
				}
			}
		
		});
		
		$("#SearchStudent").autocomplete({
			source : function(request, response) {

				$("#studentSibilingIdIntId").val("");

				$("#fatherNameId").attr('readonly', false);
				$("#fatherMobileNoId").attr('readonly', false);

				$("#fatherQualificationId").attr('readonly', false);
				$("#fatheremailId").attr('readonly', false);

				$("#motherNameId").attr('readonly', false);
				$("#motherMobileNoId").attr('readonly', false);
				$("#motherQualificationId").attr('readonly', false);
				$("#motheremailId").attr('readonly', false);

				$("#gaurdianNameId").attr('readonly', false);
				$("#guardianMobileNoId").attr('readonly', false);
				$("#guardianemailId").attr('readonly', false);

				$("#motheroccupationId").attr('readonly', false);
				$("#fatheroccupationId").attr('readonly', false);
				$("#paddrs").attr('readonly', false);
				
				$("#motherNameId").val('');
				$("#motherMobileNoId").val('');
				$("#motherQualificationId").val('');
				$("#fatherNameId").val('');
				$("#fatherMobileNoId").val('');
				$("#fatherQualificationId").val('');
				$("#fatheremailId").val('');
				$("#motheremailId").val('');
				$("#gaurdianNameId").val('');
				$("#guardianMobileNoId").val('');
				$("#guardianemailId").val('');
				$("#parentId").val('');
				$("#sibilingClassId").val('');
				$("#studentSibilingIdIntId").val('');
				$("#sibilingadminnoId").val('');
				$("#paddrs").val('');
				$("#motheroccupationId").val('');
				$("#fatheroccupationId").val('');
				$("#primarypersonId option[value='']").attr('selected', 'true');
				$("#hprymarycntperson").val("");
				/*	}*/


				$.ajax({

					url : "studentRegistration.html?method=studentSearchbySibling",
					data : {
						searchTerm : request.term
					},
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						response($.map(	result.jsonResponse,function(item) {
							return {
								label : item.studentnamelabel,
								value : item.studentidlabel,
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
				var studentList = callAjax("studentRegistration.html?method=studentSearchByParent",studentDetails);
				
				viewStudentDetails(studentList);
				$("#SearchStudent").val(ui.item.label);
				$("#studentSibilingIdIntId").val(searchTerm);

				return false;
			}
		});
		
		$("#temp_studentName").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "studentRegistration.html?method=searchStudentByStudentName",
					data : {
						searchTerm : request.term
					},
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						response($.map(	result.jsonResponse,function(item) {
							return {
								label : item.studentnamelabel,
								value : item.studentidlabel,
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
				$("#temp_studentName").val(ui.item.label);
				getTempAdmissionDetailsList();
				
				return false;
			}
		});
		
		$("#temp_parentName").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "studentRegistration.html?method=searchStudentByParentName",
					data : {
						searchTerm : request.term
					},
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						response($.map(	result.jsonResponse,function(item) {
							return {
								label : item.parentNameLabel,
								value : item.parentNameLabel,
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
				$("#temp_parentName").val(ui.item.label);
				getTempAdmissionDetailsList();
				
				return false;
			}
		});
		
		$("#temp_mobileNo").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "studentRegistration.html?method=searchStudentByMobileNo",
					data : {
						searchTerm : request.term
					},
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						response($.map(	result.jsonResponse,function(item) {
							return {
								label : item.parentMobileLabel,
								value : item.parentMobileLabel,
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
				$("#temp_mobileNo").val(ui.item.label);
				getTempAdmissionDetailsList();
				
				return false;
			}
		});
		
		$("#admissionDialog").dialog({
		    autoOpen  : false,
		    maxWidth  :	1000,
	        maxHeight : 500,
	        width     : 1000,
	        height    : 500,
		    modal     : true,
		    title     : "Admission Filled Data",
		    buttons   : {
		    	'OK'  : function() {
		    		var val= $("#admissionstudent input[name='select']:checked").attr("id");
		    		tempDetails = {
							'tempid' : val
					};
					var tempAdmissionStudentList = callAjax("studentRegistration.html?method=studentTempRegistrationList",tempDetails);
					
					viewTempAdmissionStudentDetails(tempAdmissionStudentList,val);
					$(this).dialog('close');
					
					var classId = "studClassId";
		    		getClassName(classId, category);
		    		$("#studClassId option[value='"+ tempAdmissionStudentList[0].classname +"']").attr('selected', 'true');
		    		var section = "#" + "studSectionId";
		    		getClassSection(section, studClassId);
		    		
		    		getClassSpecilization($('#studClassId').val(),$("#schoolLocationId").val());
		    		$("#specilization option[value='"+ tempAdmissionStudentList[0].specilization +"']").attr('selected', 'true');
		    		
		    		getCasteDetails();
		    		$("#casteId option[value='"+ tempAdmissionStudentList[0].caste +"']").attr('selected', 'true');
					getCasteCategoryDetails();
					$("#casteCategoryId option[value='"+ tempAdmissionStudentList[0].casteCategory +"']").attr('selected', 'true');
		    	
					$("#birthcertificatehiddenid").val(tempAdmissionStudentList[0].birthcertificate);
					var birthcertificate=$("#birthcertificatehiddenid").val();
					if(birthcertificate!="" && birthcertificate!=undefined){

						$("#document1btn").attr('name',birthcertificate);
						$("#uploadBirth").hide();
						$("#document1btn").show();
						$("#deleteProfile").show();

					}
					if($("#schoolLocationId").val()!=""){
					getHouse($("#schoolLocationId").val());
					if($("#studentid").val() != ""){
							
						}else{
							getAdmissionNo();
						}
					}
		    	},
		    	'Close' : function() {
	                 $(this).dialog('close');
	             }
		    }
		});
	}
	
	
	if(url.split("&")[0] == "studentRegistration.html?method=modifyStudentDetails"){
		if($(".errormessagediv1 .validateTips1").text()=="")
		{
			$(".successmessagediv1").show();
			setTimeout(function(){
				window.location.href="adminMenu.html?method=studentList";
			},2000);
		}
	}
	
	if(url.split("&")[0] == "studentRegistration.html?method=saveStudentRegistration"){
		if($(".errormessagediv1 .validateTips1").text()=="")
		{
			$(".successmessagediv1").show();
			setTimeout(function(){
				window.location.href="adminMenu.html?method=studentList";
			},2000);
		}
	}
	
	
	
	
	$('.setImage').attr('src','images/profile_photo.png');
	
	if($('#nationalityhiddenid').val() != ""){
		$("#nationalityId  option[value='"+$('#nationalityhiddenid').val()+"' ]").attr('selected', 'true');
	}
	getAcademicYear();
	$("#workingTeacherId").change(function(){
		var staffId=$(this).val();
		var validatation=0;
		$.ajax({
			type : 'POST',
			url : "teacherregistration.html?method=getTeacherName",
			data: {'teachercode':staffId},
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				validatation=result.jsonResponse.length;
				
				if(validatation >0){
					$("#workingTeacherName").val(result.jsonResponse[0].tfastname+" "+result.jsonResponse[0].tlastname);

				}else{
					$('#workingTeacherId').val("");
					$('#workingTeacherName').val("");
					$('.errormessagediv').css({
						'display' : 'block'
					});
					$('.validateTips').text("Invalid Id!!! Field Required A Valid Id");
					$("#workingTeacherId").css({
						'border':'1px solid #f00'
					});
					setTimeout(function() {
	        			$('#errorhover').fadeOut();
	        			document.getElementById("workingTeacherId").style.border = "1px solid #ccc";
	            		document.getElementById("workingTeacherId").style.backgroundColor = "#fff";
	        		}, 3000);
					return false;
				}
			}

		});
	});	
	
	$('#checkAddressId').click(function(){
        if($(this).prop("checked") == true){
            var postadd=$('#paddrs').val();
        
            if(postadd != ""){
            	$('#presentadd').val(postadd);
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
        	$('#presentadd').val('');
        }
    });
	
	$('#relationshipId').hide();
	$('#relationshipLabel').hide();

	$('#workingTeacherId').hide();
	$('#workingTeacherIdLabel').hide();

	$('#workingTeacherNameLabel').hide();
	$('#workingTeacherName').hide();


	$('#primarypersonId').change(function(){
		var primaryPerson=$(this).val();
		if(primaryPerson=="guardian"){
			//alert("show relationship");
			$('#relationshipId').show();
			$('#relationshipLabel').show();
		}else{
			//alert("hide relationship");
			$('#relationshipId').hide();
			$('#relationshipLabel').hide();
		}

	});

	$('#isWorking').change(function(){
		var isWorking=$(this).val();
		if(isWorking=="Yes"){
			//alert("show relationship");
			$('#workingTeacherId').show();
			$('#workingTeacherIdLabel').show();
			$('#workingTeacherNameLabel').show();
			$('#workingTeacherName').show();
		}else{
			//alert("hide relationship");
			$('#workingTeacherId').hide();
			$('#workingTeacherIdLabel').hide();
			$('#workingTeacherNameLabel').hide();
			$('#workingTeacherName').hide();
			$('#workingTeacherId').val("");
			$('#workingTeacherName').val("");
		}

	});

	


	if($("#sibilingadminnoId").val()!="" && $("#sibilingadminnoId").val()!=undefined){

		$("#fatherNameId").attr('readonly', true);
		$("#fatherMobileNoId").attr('readonly', true);

		$("#fatherQualificationId").attr('readonly', true);
		$("#fatheremailId").attr('readonly', true);

		$("#motherNameId").attr('readonly', true);
		$("#motherMobileNoId").attr('readonly', true);
		$("#motherQualificationId").attr('readonly', true);
		$("#motheremailId").attr('readonly', true);

		$("#gaurdianNameId").attr('readonly', true);
		$("#guardianMobileNoId").attr('readonly', true);
		$("#guardianemailId").attr('readonly', true);
		$("#motheroccupationId").attr('readonly', true);
		$("#fatheroccupationId").attr('readonly', true);
		$("#paddrs").attr('readonly', true);


		
	}


	$("#primarypersonId").change(function(){

		$("#hprymarycntperson").val($("#primarypersonId").val());
	});

	// saving detils with validations

	$("#document1btn").hide();
	$("#document2btn").hide();
	$("#deleteProfile").hide();
	$("#deleteIDProof").hide();

	$("#save").click(function() {
		if (validateFunction())
		{
			if (($('#studentid').val().trim() == "" || $('#studentid').val().trim() == null) ) {

				$("#formstudent").attr('action','studentRegistration.html?method=saveStudentRegistration');

				$("#formstudent").submit();
			} 
			else
			{
				$("#formstudent").attr('action','studentRegistration.html?method=modifyStudentDetails');
				$("#formstudent").submit();
			}
		}

	});

	$("#imagePreview").show();
	$("#imageFatherPreview").hide();
	$("#imageMotherPreview").hide();
	$("#imageGuardianPreview").hide();
	$("#studentstatuslable").hide();
		
	var setImage=$('.setImage').attr("src");
	var setHiddenImage=$('#photohiddenid').val();
	if(setImage == 'images/profile_photo.png' && (setHiddenImage == 'images/profile_photo.png' || setHiddenImage == "")){
		$("#removeSpanId").hide();
	}else if(setHiddenImage == 'images/profile_photo.png'){
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
		$('#photohiddenid').val("");
		$("#removeSpanId").hide();
	});

	$("#fatherImageId").change(function() {
		if(checkFile2() == false){
			$("#imageFatherPreview").hide();
			
		}else{
			$("#imageFatherPreview").show();
			readFatherURL(this);
		}
	});

	$("#motherImageId").change(function() {
		if(checkFile4() == false){
			$("#imageMotherPreview").hide();
		}else{
			$("#imageMotherPreview").show();
			readMotherURL(this);
		}
	});

	$("#guardianImageId").change(function() {
		if(checkFile3() == false){
			$("#imageGuardianPreview").hide();
		}else{
			$("#imageGuardianPreview").show();
			readGuardianURL(this);
		}
	});

	$("#cencessionY").click(function() {
		$("#scholarShipId").show();
		$("#cencessionlable").show();

	});

	$("#cencessionN").click(function() {

		$("#scholarShipId").hide();
		$("#cencessionlable").hide();

	});

	$("#transportId").change(function() {
		if($("#transportId").val() == 'Y'){
			

			getTransportCategory();
		}else{
			$("#transportlocationlabel").hide();
			$("#transcategory").hide();
			$("#transportcategorylabel").hide();
			$("#translocation").hide();

			$("#routelabel").hide();
			$("#route").hide();
		}
		

	});

	$("#translocation").change(function() {
		getRouteDetails();

	});

	getOccupation();
	getSchoolLocation();

	$("#physicalchalreason").hide();
	$("#physicalchlngres").hide();

	$("#physicallyChallengedId").click(function(){
		if($("#physicallyChallengedId").val() == "YES"){
			$("#physicalchalreason").show();
			$("#physicalchlngres").show();
		}else{
			$("#physicalchalreason").val("");
			$("#physicalchalreason").hide();
			$("#physicalchlngres").hide();
		}
		

	});

	$("#transportIdN").click(function() {

		$("#transportlocationlabel").hide();
		$("#transcategory").hide();
		$("#transportcategorylabel").hide();
		$("#translocation").hide();

		$("#routelabel").hide();
		$("#route").hide();

	});
	$("#transcategory").change(function() {
		if ($("#transcategory").val().trim() != '') {

			if (getTransportCategoryType($("#transcategory").val().trim()) == 'Y') {

				$("#trnporttypestatus").val('Y');

				$("#transportlocationlabel").show();
				$("#translocation").show();
				getTransportLocations();

			} else {
				$("#trnporttypestatus").val('N');
				$("#transportlocationlabel").hide();
				$("#translocation").hide();
				$("#routelabel").hide();
				$("#route").hide();
			}

		} else {
			$("#trnporttypestatus").val('N');
			$("#transportlocationlabel").hide();
			$("#translocation").hide();
			$("#routelabel").hide();
			$("#route").hide();
		}

	});



	$("#translocation").change(function() {
		$("#routelabel").show();
		$("#route").show();
		
	});


	// hiding ids
	$('.successmessagediv').hide();

	$('#cencessionlable').hide();
	$('#transportcategorylabel').hide();
	$('#transportlocationlabel').hide();

	$("#transcategory").hide();
	$("#translocation").hide();

	$("#routelabel").hide();
	$("#route").hide();
	$('#admissionDialog').hide();

	// Editing Fuction

	

	$('#excelDownload').click(function() {

		window.location.href = 'studentRegistration.html?method=downloadStudentDetailsXLS';

	});
	$("#pdfDownload").click(function(){

		window.location.href = "studentRegistration.html?method=downloadStudentDetailsPDF";

	});
	
	
	$('#admissionform').click(function() {
		$("#temp_studentName").val("");
		$("#temp_parentName").val("");
		$("#temp_mobileNo").val("");
		getTempAdmissionDetailsList();
		$("#admissionDialog").dialog("open");
	});
	
	// calling methods

	getLocalAcademicYear();
	getReligionDetails();

	$('#religion').change(function() {
		getCasteDetails();
		$('#casteCategoryId').val("");
	});
	
	$('#casteId').change(function() {
		getCasteCategoryDetails();
		
	});
	
	$('#studClassId').change(function() {
		var classId=$('#studClassId').val();
		var section = "#" + "studSectionId";
		//var subjectId = "#" + "studSubId";
		getClassSection(section, studClassId);
		getClassSpecilization(classId,$("#schoolLocationId").val().trim());
		
	});

	$('workingTeacherId').change(function(){
		var workingTeacherId=$('workingTeacherId').val();

	});
$("#schoolLocationId").change(function(){
	 categoryId = "category";
	getCategory(categoryId);
	getHouse($(this).val());
	
	if($("#studentid").val() != ""){
		
	}else{
		getAdmissionNo();
	}
	
});

if($("#schoolLocationId").val() != undefined){
	categoryId = "category";
	getCategory(categoryId);
	
}
	

	$('#category').change(function() {
		var classId = "studClassId";
		getClassName(classId, category);
		$('#studSectionId').val("");
		$('#specilization').val("");
	});
	
	$("#transportId option[value='N' ]").attr('selected', 'true');
	$("#isWorking option[value='No' ]").attr('selected', 'true');
	$("#studentMediumId option[value='English' ]").attr('selected', 'true');
	
	$('#removefileattach1').click(function(){
		$('#fileattachment1Div').remove();
	});
	$('#removefileattach2').click(function(){
		$('#fileattachment2Div').remove();
	});

	$('#removefileattach3').click(function(){
		$('#fileattachment3Div').remove();
	});
	$('#removefileattach4').click(function(){
		$('#fileattachment4Div').remove();
	});
	$('#removefileattach5').click(function(){
		$('#fileattachment5Div').remove();
	});


	if ($('#streamhiddenId').val() != "" && $('#streamhiddenId').val()!=null){
		
		getLocalAcademicYear();
		/*getQuota();
		getConssition();*/
		getTransportLocations();
		getTransportCategory();

		var categoryId = "category";

		getCategory(categoryId);

		var classId1 = "studClassId";

		getClassName(classId1, $('#streamhiddenId').val());

		var subjectId1 = "#" + "studSectionId";

		getClassSection(subjectId1, $('#classhiddenid').val());

		$('#studentIdhidden').val($('#studentid').val());
		if($('#studentid').val()!=undefined)
		$('#studentIdhidden').val($('#studentid').val().trim());
		
		
		if($('#classhiddenid').val()!=undefined && $('#schoollocationhiddenid').val()!=undefined)
		getClassSpecilization($('#classhiddenid').val().trim(),$('#schoollocationhiddenid').val().trim());
		if($('#schoollocationhiddenid').val()!=undefined){
			getHouse($('#schoollocationhiddenid').val());
		}
		
		
		var StudentImage=$("#photohiddenid").val();

		if(StudentImage!=""){

			$("#imagePreview").show();
			$('#imagePreview').attr('src', StudentImage);
		}

		var fatherImage=$("#fatherphotohiddenid").val();

		if(fatherImage!=undefined && fatherImage!="" && fatherImage.split("/")[2] != "noImage.png"){

			$("#imageFatherPreview").show();
			$('#imageFatherPreview').attr('src', fatherImage);
		}else{
			$("#imageFatherPreview").hide();
		}
		
		var motherImage=$("#motherphotohiddenid").val();
		if(motherImage!=undefined && motherImage!="" && motherImage.split("/")[2] != "noImage.png"){

			$("#imageMotherPreview").show();
			$('#imageMotherPreview').attr('src', motherImage);
		}else{
			$("#imageMotherPreview").hide();
		}
		
		var guardImage=$("#guardphotohiddenid").val();

		if(guardImage!=undefined && guardImage!="" && guardImage.split("/")[2] != "noImage.png"){

			$("#imageGuardianPreview").show();
			$('#imageGuardianPreview').attr('src', guardImage);
		}else{
			$("#imageGuardianPreview").hide();
		}
		
		var birthcertificate=$("#birthcertificatehiddenid").val();
		var transfercertificate=$("#transfercertificatehiddenid").val();
		var certificate1=$("#certificate1hiddenid").val();
		var certificate2=$("#certificate2hiddenid").val();
		var certificate3=$("#certificate3hiddenid").val();
		var certificate4=$("#certificate4hiddenid").val();
		var certificate5=$("#certificate5hiddenid").val();


		if(birthcertificate!="" && birthcertificate!=undefined){

			$("#document1btn").attr('name',birthcertificate);
			$("#uploadBirth").hide();
			$("#document1btn").show();
			$("#deleteProfile").show();

		}

		if(transfercertificate!="" && transfercertificate!=undefined){

			$("#document2btn").attr('name',transfercertificate);
			$("#uploadTransfer").hide();
			$("#document2btn").show();
			$("#deleteIDProof").show();
		}
		
		if(certificate1!="" && certificate1!=undefined){

			$("#document4btn").attr('name',certificate1);
			$("#filaattachment1").hide();
			$("#document4btn").show();
			$("#deleteFile1").show();
		}
		
		if(certificate2!="" && certificate2!=undefined){

			$("#document5btn").attr('name',certificate2);
			$("#filaattachment2").hide();
			$("#document5btn").show();
			$("#deleteFile2").show();
		}
		if(certificate3!="" && certificate3!=undefined){

			$("#document6btn").attr('name',certificate3);
			$("#filaattachment3").hide();
			$("#document6btn").show();
			$("#deleteFile3").show();
		}
		if(certificate4!="" && certificate4 !=undefined){

			$("#document7btn").attr('name',certificate4);
			$("#filaattachment4").hide();
			$("#document7btn").show();
			$("#deleteFile4").show();
		}
		if(certificate15="" && certificate5 !=undefined){

			$("#document2btn").attr('name',certificate5);
			$("#filaattachment5").hide();
			$("#document7btn").show();
			$("#deleteFile5").show();
		}


		$('.downloadDoc').click(function() {

			var path = $(this).attr('name');
			window.location.href = "studentRegistration.html?method=downloadDocument&Path="
				+ path.trim();
		});

		$("#deleteIDProof").click(function(){

			$("#uploadTransfer").show();
			$("#document2btn").hide();
			$("#deleteIDProof").hide();
		});

		$("#deleteProfile").click(function(){

			$("#uploadBirth").show();
			$("#document1btn").hide();
			$("#deleteProfile").hide();
		});
	
		$("#primarypersonId option[value='"+ $('#selected_Primary_hiddenId').val()+"']").attr('selected', 'true');

		$('#parentId').val($('#parenthiddenId').val());

		$("#academicYear option[value="+ $('#acchiddenId').val() + "]").attr('selected', 'true');

		$("#category option[value="+ $('#streamhiddenId').val() + "]").attr('selected', 'true');

		$("#studClassId option[value="+ $('#classhiddenid').val() + "]").attr('selected', 'true');

		$("#studSectionId option[value="+ $('#sectionhiddenid').val() + "]").attr('selected', 'true');

		$("#studentquotaname option[value="+ $('#quotahiddenid').val() + "]").attr('selected', 'true');

		$('input[name=rte][value='+ $('#rtehiddenid').val() + ']').attr('checked', true);

		$('input[name=hostel][value='+ $('#hostelhiddenid').val() + ']').attr('checked', true);

		$('input[name=cencession][value='+ $('#concessionaplicablehidden').val()+ ']').attr('checked', true);

		if ($("#concessionaplicablehidden").val()!=undefined && $("#concessionaplicablehidden").val().trim() == "Y") {

			$('#cencessionlable').show();

			$("#scholarShipId option[value="+ $('#concessionhiddenid').val()+ "]").attr('selected', 'true');
		}
		
		$("#schoolLocationId option[value="+ $('#schoollocationhiddenid').val() + "]").attr('selected', 'true');
		
		$("#isWorking option[value="+ $('#parentsguardianhiddenid').val() + "]").attr('selected', 'true');
		
		$("#studentMediumId option[value="+ $('#mediumhiddenid').val() + "]").attr('selected', 'true');
		
		$("#specilization option[value="+ $('#specilizationhiddenid').val() + "]").attr('selected', 'true');
		

		$('#transportId option[value='+ $('#transporthiddenid').val() + ']').attr('selected', true);
		
		$('#firstlang option[value='+ $('#firstlanghiddenid').val() + ']').attr('selected', true);
		
		$('#secondlang option[value='+ $('#secondlanghiddenid').val() + ']').attr('selected', true);
		
		$('#thirdlang option[value='+ $('#thirdlanghiddenid').val() + ']').attr('selected', true);
		
		if($('#parentsguardianhiddenid').val()!=undefined && $('#parentsguardianhiddenid').val().trim() == 'Yes'){
			$('#workingTeacherId').show();
			$('#workingTeacherIdLabel').show();
			$('#workingTeacherNameLabel').show();
			$('#workingTeacherName').show();
		}

		
		getReligionDetails();
		
		getCasteDetails();
		
		getCasteCategoryDetails();
		
		$('#genderId option[value='+ $('#genderhiddenid').val().trim()+ ']').attr('selected', true);

		$("#bloodGroupId  option[value='"+$('#bloodhiddenid').val().trim()+"' ]").attr('selected', 'true');
		
		$("#mothertongueId  option[value='"+$('#mothertongehiddenid').val().trim()+"' ]").attr('selected', 'true');
		$("#religion  option[value='"+$('#religionhiddenid').val().trim()+"' ]").attr('selected', 'true');
		$("#casteId  option[value='"+$('#castehiddenid').val().trim()+"' ]").attr('selected', 'true');
		$("#casteCategoryId  option[value='"+$('#castecategoryhiddenid').val().trim()+"' ]").attr('selected', 'true');
		$("#studentStatusId  option[value='"+$('#statushiddenid').val().trim()+"' ]").attr('selected', 'true');

		$('#physicallyChallengedId option[value='+ $('#physicallychallengedhiddenid').val().trim() + ']').attr('selected', true);
		if ($('#physicallychallengedhiddenid').val().trim() == "YES") {

			$("#physicalchalreason").val($('#physicallychallengeddescriptionhiddenid').val().trim());

			$("#physicalchlngres").show();
			$("#physicalchalreason").show();
			
		}
		
		$("#studentstatuslable").show();

		$("#studentStatusId option[value="+ $('#studentStatushiddentid').val()+ "]").attr('selected', 'true');

		$('input[name=primaryPerson][value='+ $('#selected_Primary_hiddenId').val()+ ']').attr('checked', true);

		$("#paddrs").val($('#addresshiddenid').val());
		$("#fatherofficeaddress").val($("#fatherOfficeAddresshiddenid").val());
		$("#motherofficeaddress").val($("#motherOfficeAddresshiddenid").val());
		$("#guardianofficeaddress").val($("#guardianOfficeAddresshiddenid").val());
		$("#presentadd").val($('#presentaddresshiddenid').val());
		
		$("#fatherOccupation option[value="+ $('#fatheroccupationhiddenid').val()+ "]").attr('selected', 'true');
		
		$("#motherOccupation option[value="+ $('#motheroccupationhiddenid').val()+ "]").attr('selected', 'true');
		
		$("#guardianOccupation option[value="+ $('#guardoccupationhiddenid').val()+ "]").attr('selected', 'true');
		
	}

	// date pickers
	
	var admissionhiddenid=$('#admissionhiddenid').val();
	if(admissionhiddenid != "" || admissionhiddenid != null){
		$("#dateofJoinId").val(admissionhiddenid);
	}else{
		var dNow = new Date();
		var Day=dNow.getDate();
		if (Day < 10) {
			Day = '0' + Day;
		} //end if
		var Month = dNow.getMonth() + 1;
		if (Month < 10) {
			Month = '0' + Month;
		} 

		var localdate= Day + '-' + Month + '-' + dNow.getFullYear();
		$("#dateofJoinId").val(localdate);
	}
	
	$("#studentFirstNameId").keydown(function(e){
		var key=e.keyCode;
		if(!((key == 8) || ((key == 16)) || (key == 32) || (key == 46) || (key == 9) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))){
			e.preventDefault();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Text."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
		
	});
	$("#applicationNoId").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Number."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});
	
	
	
	
	$("#studentLastNameId").keydown(function(e){
		var key=e.keyCode;
		if(!((key == 8) || ((key == 16)) || (key == 32) || (key == 46) || (key == 9) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))){
			e.preventDefault();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Text.");
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});
	
	
	
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
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Number."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});
	$("#smsnumber").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Number."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});
	$("#emergencynumber").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Number."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});
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
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Number."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});
	
	$("#gaurdianNameId").keydown(function(e){
		var key=e.keyCode;
		if(!((key == 8) || ((key == 16)) || (key == 32) || (key == 46) || (key == 9) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))){
			e.preventDefault();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Text.");
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});
	
	$("#guardianMobileNoId").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Number."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});

	
	
	if($("span.successmessage").text()=="Student details updated Successfully"){
		$("#div1 #successmessages").attr("style","display:block");
	} 

	var cnt=0;
	$("#fileUploaddynmic").click(function(){

		var check = null;

		if(cnt<5){
			cnt++;

			$("#fileattachment"+cnt+"Div").show();
			$("#fileattachment"+cnt+"label").show();
			$("#filaattachment"+cnt).show();
			$("#deleteFile" + cnt).show();
		}else{
			$("#errorhover").show();

			$(".error").text("You can add only Five  files");

			setTimeout(function() {$('#errorhover').fadeOut();}, 1000);

			$('html, body').animate({ scrollTop: $('#errorhover').offset().top }, 'fast');
		}
	});
	
	$("#deleteFile1").click(function() {

		$("#fileattachment" + 1 + "Div").hide();
		$("#fileattachment" + 1 + "label").hide();
		$("#filaattachment" + 1).hide();
		$("#filaattachment" + 1).val("");
		$("#deleteFile" + 1).hide();
		cnt = 0;

	});
	$("#deleteFile2").click(function() {

		$("#fileattachment" + 2 + "Div").hide();
		$("#fileattachment" + 2 + "label").hide();
		$("#filaattachment" + 2).hide();
		$("#filaattachment" + 2).val("");
		$("#deleteFile" + 2).hide();
		cnt = 0;

	});

	$("#deleteFile3").click(function() {

		$("#fileattachment" + 3 + "Div").hide();
		$("#fileattachment" + 3 + "label").hide();
		$("#filaattachment" + 3).hide();
		$("#filaattachment" + 3).val("");
		$("#deleteFile" + 3).hide();
		cnt = 0;

	});
	$("#deleteFile4").click(function() {

		$("#fileattachment" + 4 + "Div").hide();
		$("#fileattachment" + 4 + "label").hide();
		$("#filaattachment" + 4).hide();
		$("#filaattachment" + 4).val("");
		$("#deleteFile" + 4).hide();
		cnt = 0;

	});

	$("#deleteFile5").click(function() {

		$("#fileattachment" + 5 + "Div").hide();
		$("#fileattachment" + 5 + "label").hide();
		$("#filaattachment" + 5).hide();
		$("#filaattachment" + 5).val("");
		$("#deleteFile" + 5).hide();
		cnt = 0;

	});
	
	
	if($("#hschoolLocation").val() !="all"){
		$("#schoolLocationId").find("option").not("option[value="+$("#hschoolLocation").val()+"],option[value='']").remove();
		
	}
	if($("#househiddenid").val()!=""){
		getHouse($("#schoolLocationId").val());
	}
	
	$("#primarypersonId").val($("#hprymarycntperson").val());
	
	
	$("#academicYear").change(function(){
		getHouse($("#schoolLocationId").val());
	});
});
// fuctions to get values
function getLocalAcademicYear(academicYear) {
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAcademicYear",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#academicYear").html("");
			

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$("#academicYear").append(
						'<option value="'
						+ result.jsonResponse[j].academicYearId + '">'
						+ result.jsonResponse[j].academicYear
						+ '</option>');
			}
		}
	
	});
	
	$("#academicYear option[value="+$("#hacademicyaer").val().trim()+"]").attr("selected",true);
}

function getCategory(categoryval) {
	var category = "#" + categoryval;
	if($("#schoollocationhiddenid").val()==""){
		locationId=$("#schoolLocationId").val();
	}
	else{
		locationId=$("#schoollocationhiddenid").val();
		if($("#schoollocationhiddenid").val()!=undefined)
		locationId=$("#schoollocationhiddenid").val().trim();
	}
	var dataList={
			"locationId":locationId,
	};
	$.ajax({
		type : 'GET',
		url : "studentRegistration.html?method=getCategory",
		data:dataList,
		async : false,
		success : function(response) {
			$(category).html("");
			$(category).append('<option value="' + "" + '">' + "----------Select----------" + '</option>');
			var result = $.parseJSON(response);

			for ( var j = 0; j < result.CategoryList.length; j++) {

				$(category).append(
						'<option value="' + result.CategoryList[j].streemcode
						+ '">' + result.CategoryList[j].streemname
						+ '</option>');
			}
		}
	});
}
function getClassName(classidval, category) {

	var classid = "#" + classidval;
	var categoryVal = null;
	if (($('#streamhiddenId').val() != "" && $('#streamhiddenId').val() != null && $("#category").val() =="" )) {
		categoryVal = $("#streamhiddenId").val();
	}else if($('#streamhiddenId').val() == $("#category").val()){
		categoryVal = $("#streamhiddenId").val();
	}else {
		categoryVal = $('#category').val();
	}

	datalist = {
			"categoryVal" : categoryVal
	}, 
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassDetail",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$(classid).html("");
			$(classid).append('<option value="' + "" + '">' + "----------Select----------" + '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {
				$(classid).append(
						'<option value="'
						+ result.ClassList[j].classcode + '">'
						+ result.ClassList[j].classname
						+ '</option>');
			}

		}
	});

}
function getClassSection(sectionid, studClassId) {
	var classidVal = null;
	
	if (($('#classhiddenid').val() != "" && $('#classhiddenid').val() != null && $("#studClassId").val() =="" )) {
		classidVal = $("#classhiddenid").val();
	}else if($('#classhiddenid').val() == $("#studClassId").val()){
		classidVal = $("#classhiddenid").val();
	}else {
		classidVal = $('#studClassId').val();
	}
	if($("#schoollocationhiddenid").val()==""){
		locationId=$("#schoolLocationId").val();
	}
	else{
		locationId=$("#schoollocationhiddenid").val();
		if($("#schoollocationhiddenid").val()!=undefined)
		locationId=$("#schoollocationhiddenid").val().trim();
	}
	
	/*if ($('#classhiddenid').val() == null || $('#classhiddenid').val() == "") {
		classidVal = $("#studClassId").val();
	} else {
		classidVal = $('#classhiddenid').val();
	}*/
	datalist = {
		"classidVal" : classidVal,
		"locationId":locationId,
	}, $.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$(sectionid).html("");
			$(sectionid).append('<option value="' + "" + '">' + "----------Select----------" + '</option>');

			for ( var j = 0; j < result.sectionList.length; j++) {
				$(sectionid).append(
						'<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}

		}
	});

}

function getTransportCategory() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getTransportCategory",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			$("#transcategory").html("");
			$("#transcategory").append('<option value="' + "" + '">' + "----------Select----------" + '</option>');
			for ( var j = 0; j < result.transportCategory.length; j++) {
				$("#transcategory").append(
						'<option value="'
						+ result.transportCategory[j].transptyId + '">'
						+ result.transportCategory[j].transptyname
						+ '</option>');
			}

		}
	});

}
function getTransportLocations() {
	
	
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getTransportStages",
		data:{"accyear":$("#academicYear").val()},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			$("#translocation").html("");
			$("#translocation").append('<option value="' + "" + '">' + "----------Select----------" + '</option>');
			for ( var j = 0; j < result.transportstages.length; j++) {
				$("#translocation").append(
						'<option value="' + result.transportstages[j].stageCode
						+ '">' + result.transportstages[j].stageName
						+ '</option>');
			}

		}
	});

}

function getTransportCategoryType(typeId) {
	var type = null;
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getTransportCategoryType",
		data : {
			"typeId" : typeId
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			type = result.transportTypeStatus;

		}
	});

	return type;

}
function validatePhoneNo() {
	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
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
}
function fathervalidatePhoneNo() {
	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
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
}

function gaurdianvalidatePhoneNo() {
	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
		var phoneId = $('#guardianMobileNoId').val();
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
}
function mothervalidateEmail() {

	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {

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
}

function fathervalidateEmail() {

	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
		var emailid = $('#fatheremailId').val();

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
}

function gaurdianvalidateEmail() {

	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
		var emailid = $('#guardianemailId').val();

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
}

function ageCalculate() {
	var doofBirth = $('#dateofBirthId').val();

	var birthYear = doofBirth.split("-")[2];

	var currentYear = new Date().getFullYear();

	var yearDiff = parseInt(currentYear) - parseInt(birthYear);

	$('#ageId').val(yearDiff);

}

// validations

function validateFunction() {
	var bValid = true;
	$("#hprymarycntperson").val($("#primarypersonId").val());

	//Student Details 
	
	var studentFirstName = $('#studentFirstNameId').val();
	var studentrollno = $('#studentrollno').val(); 
	var dateofJoin = $('#dateofJoinId').val();
	var academicYear = $('#academicYear').val(); 
	var category = $('#category').val(); 
	var studClassId = $('#studClassId').val(); 
	var studSectionId = $('#studSectionId').val(); 
	var schoolname=$("#schoolLocationId").val();
	var istransport = $('#transportId').val();
	var transportCategory = $('#transcategory').val(); 
	var transportLocation = $('#translocation').val();
	var staffid=$('#workingTeacherId').val();
	var isWorking=$('#isWorking').val();
	
	//For personal information
	
	var dateofBirth = $('#dateofBirthId').val(); 
	var gendertype = $('#genderId').val();
	var religion = $('#religion').val(); 
	var caste = $('#casteId').val(); 
	var castecatagory = $('#casteCategoryId').val();
	var physicallychallengedtype = $('#physicallyChallengedId').val();
	var physicallychallengereason=$("#physicalchalreason").val();
	var studentStatus=$("#studentStatusId").val();
	var mothertongueval=$("#mothertongueId").val();
	
	var aadharId = $("#aadharId").val().trim();
	
	//Prents Information
	
	
	var fathername = $('#fatherNameId').val();
	var fathermobileno = $('#fatherMobileNoId').val();
	var motherNameId = $('#motherNameId').val();
	var motherMobileNoId = $('#motherMobileNoId').val();
	var gaurdianNameId = $("#gaurdianNameId").val().trim();
	var guardianMobileNoId = $('#guardianMobileNoId').val().trim();
	var primaryperson = $('#hprymarycntperson').val();
	//var addr = $("#paddrs").val().trim();
	//var presentadd = $("#presentadd").val().trim();

	if (studentFirstName  == "" || studentFirstName  == null) {

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

	} else
	
	if (!studentFirstName.match(/[A-Za-z ]+$/i) ) {
		
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

	}
		else if(schoolname == null || schoolname == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required School Name.");
			$("#schoolLocationId").focus();
			document.getElementById("schoolLocationId").style.border = "1px solid #AF2C2C";
			document.getElementById("schoolLocationId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("schoolLocationId").style.border = "1px solid #ccc";
				document.getElementById("schoolLocationId").style.backgroundColor = "#fff";
			}, 500);

			bValid= false;
		}else if(dateofJoin == null || dateofJoin == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Admission Date.");
			$("#studentrollno").focus();
			document.getElementById("dateofJoinId").style.border = "1px solid #AF2C2C";
			document.getElementById("dateofJoinId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("dateofJoinId").style.border = "1px solid #ccc";
				document.getElementById("dateofJoinId").style.backgroundColor = "#fff";
			}, 500);

			bValid= false;
		}else if (studentrollno  == "" || studentrollno  == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Admission Number.");
			$("#studentrollno").focus();
			document.getElementById("studentrollno").style.border = "1px solid #AF2C2C";
			document.getElementById("studentrollno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("studentrollno").style.border = "1px solid #ccc";
				document.getElementById("studentrollno").style.backgroundColor = "#fff";
			}, 500);

			bValid= false;

		}else if(checkRollnumber()){
			$(".errormessagediv").show();
			$(".validateTips").text("Admission Number already exist.");
			$("#studentrollno").focus();
			document.getElementById("studentrollno").style.border = "1px solid #AF2C2C";
			document.getElementById("studentrollno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("studentrollno").style.border = "1px solid #ccc";
				document.getElementById("studentrollno").style.backgroundColor = "#fff";
			}, 500);

			bValid= false;
		}else if (!studentrollno.match(/[0-9\s]+$/i)) {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Valid Admission Number.");
			$("#studentrollno").focus();
			document.getElementById("studentrollno").style.border = "1px solid #AF2C2C";
			document.getElementById("studentrollno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("studentrollno").style.border = "1px solid #ccc";
				document.getElementById("studentrollno").style.backgroundColor = "#fff";
			}, 500);

			bValid= false;

		} else if (dateofJoin  == '') {
			$(".validateTips").text("Field Required Admission Date.");
			$(".errormessagediv").show();
			$("#dateofJoinId").focus();
			document.getElementById("dateofJoinId").style.border = "1px solid #AF2C2C";
			document.getElementById("dateofJoinId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("dateofJoinId").style.border = "1px solid #ccc";
				document.getElementById("dateofJoinId").style.backgroundColor = "#fff";
			}, 500);

			bValid= false;

		} else if (academicYear  == null || academicYear  == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Academic Year.");
			$("#academicYear").focus();
			document.getElementById("academicYear").style.border = "1px solid #AF2C2C";
			document.getElementById("academicYear").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("academicYear").style.border = "1px solid #ccc";
				document.getElementById("academicYear").style.backgroundColor = "#fff";
			}, 500);

			bValid= false;

		} else if (category  == null || category  == "") {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Stream.");
			$("#category").focus();
			document.getElementById("category").style.border = "1px solid #AF2C2C";
			document.getElementById("category").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("category").style.border = "1px solid #ccc";
				document.getElementById("category").style.backgroundColor = "#fff";
			}, 500);

			bValid= false;

		} else if (studClassId  == null || studClassId  == "") {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Class.");
			$("#studClassId").focus();
			document.getElementById("studClassId").style.border = "1px solid #AF2C2C";
			document.getElementById("studClassId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("studClassId").style.border = "1px solid #ccc";
				document.getElementById("studClassId").style.backgroundColor = "#fff";
			}, 500);

			bValid= false;

		} else if (studSectionId  == null || studSectionId  == "") {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Section.");
			$("#studSectionId").focus();
			document.getElementById("studSectionId").style.border = "1px solid #AF2C2C";
			document.getElementById("studSectionId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("studSectionId").style.border = "1px solid #ccc";
				document.getElementById("studSectionId").style.backgroundColor = "#fff";
			}, 500);

			bValid= false;

		}else if (istransport == "" || istransport == undefined) {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Transport.");

			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("transportId").style.border = "1px solid #ccc";
				document.getElementById("transportId").style.backgroundColor = "#fff";
			}, 500);

			bValid= false;

		} else if (isWorking == "Yes" && staffid.trim() == "") {
			$(".errormessagediv").show();
			$(".validateTips").text("Search Staff/Teacher ID.");
			$("#transcategory").focus();
			document.getElementById("workingTeacherId").style.border = "1px solid #AF2C2C";
			document.getElementById("workingTeacherId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("workingTeacherId").style.border = "1px solid #ccc";
				document.getElementById("workingTeacherId").style.backgroundColor = "#fff";
			}, 500);

			bValid=false;
		}else if (dateofBirth == null || dateofBirth == "") {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Date Of Birth.");
			$("#dateofBirthId").focus();
			document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
			document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("dateofBirthId").style.border = "1px solid #ccc";
				document.getElementById("dateofBirthId").style.backgroundColor = "#fff";
			}, 500);

			bValid=false;
		} else if (gendertype == "" || gendertype == null) {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Gender.");
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("genderId").style.border = "1px solid #ccc";
				document.getElementById("genderId").style.backgroundColor = "#fff";
			}, 500);

			bValid=false;
		}else if (religion == "" || religion == null){
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

		} else if (caste == "" || caste == null){
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
		}else if(castecatagory == null || castecatagory == ""){
			$(".errormessagediv").show();

			$(".validateTips").text("Field Required Caste Category.");
			$("#casteCategoryId").focus();
			document.getElementById("casteCategoryId").style.border = "1px solid #AF2C2C";
			document.getElementById("casteCategoryId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("casteCategoryId").style.border = "1px solid #ccc";
				document.getElementById("casteCategoryId").style.backgroundColor = "#fff";
			}, 500);

			bValid=false;
		}else if(mothertongueval == "" || mothertongueval == null){
			$(".errormessagediv").show();

			$(".validateTips").text("Field Required Mother Tongue.");
			$("#mothertongueId").focus();
			document.getElementById("mothertongueId").style.border = "1px solid #AF2C2C";
			document.getElementById("mothertongueId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("mothertongueId").style.border = "1px solid #ccc";
				document.getElementById("mothertongueId").style.backgroundColor = "#fff";
			}, 500);

			bValid=false;
		}
		else if (physicallychallengedtype == "" || physicallychallengedtype == null) {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Physically Challenged.");
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("physicallyChallengedId").style.border = "1px solid #ccc";
				document.getElementById("physicallyChallengedId").style.backgroundColor = "#fff";
			}, 500);

			bValid=false;
		}else if (physicallychallengedtype =='YES' && physicallychallengereason == "") {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Reason.");

			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("physicalchalreason").style.border = "1px solid #ccc";
				document.getElementById("physicalchalreason").style.backgroundColor = "#fff";
			}, 500);

			bValid=false;
		}
		else if(studentStatus == ""){

			$(".errormessagediv").show();

			$(".validateTips").text("Field Required Status.");
			$("#studentStatusId").focus();
			document.getElementById("studentStatusId").style.border = "1px solid #AF2C2C";
			document.getElementById("studentStatusId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("studentStatusId").style.border = "1px solid #ccc";
				document.getElementById("studentStatusId").style.backgroundColor = "#fff";
			}, 500);

			bValid=false;

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
			}, 500);

			bValid=false;

		}
		else if(fathermobileno == "") {

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
		else if (motherNameId == null || motherNameId == "") {

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

		}else if (!motherNameId.match(/[A-Za-z ]+$/i)) {

			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Father Name.");
			$("#motherNameId").focus();
			document.getElementById("motherNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("motherNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("motherNameId").style.border = "1px solid #ccc";
				document.getElementById("motherNameId").style.backgroundColor = "#fff";
			}, 500);

			bValid=false;

		} 
		else if (motherMobileNoId == "" || motherMobileNoId == null) {

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
		/*else if (gaurdianNameId == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Guardian Name.");
			$("#gaurdianNameId").focus();
			document.getElementById("gaurdianNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("gaurdianNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("gaurdianNameId").style.border = "1px solid #ccc";
				document.getElementById("gaurdianNameId").style.backgroundColor = "#fff";
			}, 1000);

			bValid= false;

		}*/else if (gaurdianNameId != "" && !gaurdianNameId.match(/[A-Za-z ]+$/i)) {

			$(".errormessagediv").show();
			$(".validateTips").text("Enter Valid Guardian Name");
			$("#gaurdianNameId").focus();
			document.getElementById("gaurdianNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("gaurdianNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("gaurdianNameId").style.border = "1px solid #ccc";
				document.getElementById("gaurdianNameId").style.backgroundColor = "#fff";
			}, 500);

			bValid=false;

		} 
		/*else if (guardianMobileNoId == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Mobile Number.");
			$("#guardianMobileNoId").focus();
			document.getElementById("guardianMobileNoId").style.border = "1px solid #AF2C2C";
			document.getElementById("guardianMobileNoId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("guardianMobileNoId").style.border = "1px solid #ccc";
				document.getElementById("guardianMobileNoId").style.backgroundColor = "#fff";
			}, 1000);

			bValid= false;
		}*/
		else if (primaryperson == null || primaryperson == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Primary Person.");
			$("#primarypersonId").focus();
			document.getElementById("primarypersonId").style.border = "1px solid #AF2C2C";
			document.getElementById("primarypersonId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("primarypersonId").style.border = "1px solid #ccc";
				document.getElementById("primarypersonId").style.backgroundColor = "#fff";
			}, 1000);

			bValid=false;

		} /*else if (addr == "" || addr == null){

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

		}
		else if (presentadd == "" || presentadd == null){

			$(".errormessagediv").show();

			$(".validateTips").text("Field Required Present Address.");
			$("#presentadd").focus();
			document.getElementById("presentadd").style.border = "1px solid #AF2C2C";
			document.getElementById("presentadd").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("presentadd").style.border = "1px solid #ccc";
				document.getElementById("presentadd").style.backgroundColor = "#fff";
			}, 1000);

			bValid= false;

		}*/
		
		else{
			return bValid;
		}
	
}


function checkRollnumber() {

	var status = false;

	var rollNumber = $("#studentrollno").val();
	var hiddenRollNumber=$("#studentrollnohiddenid").val().trim();
	
	if($("#schoollocationhiddenid").val()==""){
		locationId=$("#schoolLocationId").val();
	}
	else{
		locationId=$("#schoollocationhiddenid").val().trim();
	}

	if ((rollNumber != '' && rollNumber != undefined )) {
		if(rollNumber == hiddenRollNumber){
			status= false;
		}else{
			rollNumberCheck = {
					"rollNumber" : rollNumber,
					"locationId":locationId
			},

			$.ajax({
				url : "studentRegistration.html?method=validateroleNumber",
				data : rollNumberCheck,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);

					if (response.message == "True") {
						$('.errormessagediv').css({
							'display' : ''
						});
						$('.validateTips').text("Admission Number already exist");

						status = true;

					} else {

						$('.errormessagediv').css({
							'display' : 'none'
						});

						status = false;
					}
				}

			});
		}
		

		return status;
	}
}

function readyOnly() {}

function HideError() {
	document.getElementById("studentFirstNameId").style.border = "1px solid #ccc";
	document.getElementById("studentFirstNameId").style.backgroundColor = "#fff";

	document.getElementById("studentFirstNameId").style.border = "1px solid #ccc";
	document.getElementById("studentFirstNameId").style.backgroundColor = "#fff";

	document.getElementById("studentLastNameId").style.border = "1px solid #ccc";
	document.getElementById("studentLastNameId").style.backgroundColor = "#fff";

	document.getElementById("studentrollno").style.border = "1px solid #ccc";
	document.getElementById("studentrollno").style.backgroundColor = "#fff";

	document.getElementById("applicationNoId").style.border = "1px solid #ccc";
	document.getElementById("applicationNoId").style.backgroundColor = "#fff";

	document.getElementById("dateofJoinId").style.border = "1px solid #ccc";
	document.getElementById("dateofJoinId").style.backgroundColor = "#fff";

	document.getElementById("academicYear").style.border = "1px solid #ccc";
	document.getElementById("academicYear").style.backgroundColor = "#fff";

	document.getElementById("category").style.border = "1px solid #ccc";
	document.getElementById("category").style.backgroundColor = "#fff";

	document.getElementById("studClassId").style.border = "1px solid #ccc";
	document.getElementById("studClassId").style.backgroundColor = "#fff";

	document.getElementById("studSectionId").style.border = "1px solid #ccc";
	document.getElementById("studSectionId").style.backgroundColor = "#fff";

	/*document.getElementById("studentquotaname").style.border = "1px solid #ccc";
	document.getElementById("studentquotaname").style.backgroundColor = "#fff";*/

	/*document.getElementById("gradeId").style.border = "1px solid #ccc";
	document.getElementById("gradeId").style.backgroundColor = "#fff";*/

	document.getElementById("dateofBirthId").style.border = "1px solid #ccc";
	document.getElementById("dateofBirthId").style.backgroundColor = "#fff";

	document.getElementById("identificationMarksId").style.border = "1px solid #ccc";
	document.getElementById("identificationMarksId").style.backgroundColor = "#fff";

	document.getElementById("bloodGroupId").style.border = "1px solid #ccc";
	document.getElementById("bloodGroupId").style.backgroundColor = "#fff";

	/*document.getElementById("religionId").style.border = "1px solid #ccc";
	document.getElementById("religionId").style.backgroundColor = "#fff";

	document.getElementById("casteId").style.border = "1px solid #ccc";
	document.getElementById("casteId").style.backgroundColor = "#fff";*/

	document.getElementById("nationalityId").style.border = "1px solid #ccc";
	document.getElementById("nationalityId").style.backgroundColor = "#fff";

	document.getElementById("primarypersonId").style.border = "1px solid #ccc";
	document.getElementById("primarypersonId").style.backgroundColor = "#fff";

	
	 document.getElementById("fatherNameId").style.border = "1px solid #ccc";
	  document.getElementById("fatherNameId").style.backgroundColor = "#fff";
	  
	  document.getElementById("fatherMobileNoId").style.border = "1px solid  #ccc";
	  document.getElementById("fatherMobileNoId").style.backgroundColor =
	  "#fff";
	  
	  document.getElementById("motherNameId").style.border = "1px solid #ccc";
	  document.getElementById("motherNameId").style.backgroundColor = "#fff";
	  
	  document.getElementById("motherMobileNoId").style.border = "1px solid #ccc"; 
	  document.getElementById("motherMobileNoId").style.backgroundColor =
	  "#fff";
	  
	  document.getElementById("gaurdianNameId").style.border = "1px solid #ccc";
	  document.getElementById("gaurdianNameId").style.backgroundColor =
	  "#fff";
	  
	  document.getElementById("guardianMobileNoId").style.border = "1px solid #ccc";
	  document.getElementById("guardianMobileNoId").style.backgroundColor =
	  "#fff";
	 

	document.getElementById("paddrs").style.border = "1px solid #ccc";
	document.getElementById("paddrs").style.backgroundColor = "#fff";

}

function getRouteDetails(){
	var value = null;
	if (($('#transportlocationhiddenid').val() != "" && $('#transportlocationhiddenid').val() != null && $("#translocation").val() =="" )) {
		value = $("#transportlocationhiddenid").val();
	}else if($('#transportlocationhiddenid').val() == $("#translocation").val()){
		value = $("#transportlocationhiddenid").val();
	}else {
		value = $('#translocation').val();
	}
	
	var data = {
		"value" : value
	};

	$.ajax({
		type : 'POST',
		url : "transport.html?method=getRouteDetails",
		data : data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#route').html("");
			$('#route').append('<option value="' + "" + '">' + "----------Select----------"	+ '</option>');
			for ( var j = 0; j < result.routelist.length; j++) {
				$('#route').append('<option value="' + result.routelist[j].routeCode + '">' + result.routelist[j].routeName	+ '</option>');
			}


		}
	});
}



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
			$('#casteCategoryId').empty();
			$('#casteCategoryId').append('<option value="' + "" + '">'+ "----------Select----------"+ '</option>');
		}
	});
}


function getCasteCategoryDetails(){
	//var casteId=$('#casteId').val();
	var casteId = null;
	if (($('#castehiddenid').val() != "" && $('#castehiddenid').val() != null && $("#casteId").val() =="" )) {
		casteId = $("#castehiddenid").val();
	}else if($('#castehiddenid').val() == $("#casteId").val()){
		casteId = $("#castehiddenid").val();
	}else {
		casteId = $('#casteId').val();
	}
	
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

function getClassSpecilization(classId,locationId){
	var data = {
			"classId" : classId,
			"locationId":locationId,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specilization').empty();
			$('#specilization').append('<option value="' + "" + '">'+ "----------Select----------" + '</option>');
			if(result.jsonResponse.length>0){
				for ( var j = 0; j < result.jsonResponse.length; j++) {
					$('#specilization').append(
							'<option value="'
							+ result.jsonResponse[j].spec_Id
							+ '">'
							+ result.jsonResponse[j].spec_Name
							+ '</option>');
				}
			}
			else{
				getClassLanguage(classId,locationId,"-");
			}
		}
	});
}

function getOccupation(){
	
	$.ajax({
		type : 'POST',
		url : "teacherregistration.html?method=getOccupation",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#fatherOccupation,#motherOccupation,#guardianOccupation').empty();
			$('#fatherOccupation,#motherOccupation,#guardianOccupation').append(
					'<option value="' + "" + '">'
							+ "----------Select--------"
							+ '</option>');
		
			for ( var j = 0; j < result.jsonResponse.length; j++) {
				
				
			
				$('#fatherOccupation,#motherOccupation,#guardianOccupation')
						.append(
								'<option value="'
										+ result.jsonResponse[j].occupationId
										+ '">'
										+ result.jsonResponse[j].occupation
										+ '</option>');
			}
		}
	});
}



function getSchoolLocation(){
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getSchoolLocation",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#schoolLocationId').empty();
			$('#schoolLocationId').append('<option value="' + "" + '">'+ "----------Select----------"+ '</option>');

			for ( var j = 0; j < result.locationList.length; j++) {

				$('#schoolLocationId').append(
						'<option value="'
						+ result.locationList[j].location_id
						+ '">'
						+ result.locationList[j].locationname
						+ '</option>');
			}
		}
	});
}


function getAcademicYear() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAcademicYear",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#globalAcademicYear").html("");
			

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$("#globalAcademicYear").append(
						'<option value="'
								+ result.jsonResponse[j].academicYearId + '">'
								+ result.jsonResponse[j].academicYear
								+ '</option>');
			}
			$("#globalAcademicYear option[value="+$("#hacademicyaer").val()+"]").attr("selected",true);
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

function checkFile2(){
	var fileval = $("#fatherImageId").val();
	
	if (fileval != undefined && fileval != ''){
		var extval = fileval.substring(fileval.lastIndexOf('.') + 1);
		if(extval != "jpeg" && extval != "jpg" && extval != "png" && extval != "PNG" && extval != "JPG" && extval != "JPEG"){
			$('.errormessagediv').show();
			$('.validateTips').text("Photo Should be a jpeg,jpg and png File");
			document.getElementById("fatherImageId").style.border = "1px solid #AF2C2C";
    		document.getElementById("fatherImageId").style.backgroundColor = "#FFF7F7";
			$('.errormessagediv').delay(3000).fadeOut("slow");
			$("#fatherImageId").val("");
			return false;
		}
	}
}

function checkFile3(){
	var fileval = $("#guardianImageId").val();
	
	if (fileval != undefined && fileval != ''){
		var extval = fileval.substring(fileval.lastIndexOf('.') + 1);
		if(extval != "jpeg" && extval != "jpg" && extval != "png" && extval != "PNG" && extval != "JPG" && extval != "JPEG"){
			$('.errormessagediv').show();
			$('.validateTips').text("Photo Should be a jpeg,jpg and png File");
			document.getElementById("guardianImageId").style.border = "1px solid #AF2C2C";
    		document.getElementById("guardianImageId").style.backgroundColor = "#FFF7F7";
			$('.errormessagediv').delay(3000).fadeOut("slow");
			$("#guardianImageId").val("");
			return false;
		}
	}
}

function checkFile4(){
	var fileval = $("#motherImageId").val();
	
	if (fileval != undefined && fileval != ''){
		var extval = fileval.substring(fileval.lastIndexOf('.') + 1);
		if(extval != "jpeg" && extval != "jpg" && extval != "png" && extval != "PNG" && extval != "JPG" && extval != "JPEG"){
			$('.errormessagediv').show();
			$('.validateTips').text("Photo Should be a jpeg,jpg and png File");
			document.getElementById("motherImageId").style.border = "1px solid #AF2C2C";
    		document.getElementById("motherImageId").style.backgroundColor = "#FFF7F7";
			$('.errormessagediv').delay(3000).fadeOut("slow");
			$("#motherImageId").val("");
			return false;
		}
	}
}


function viewStudentDetails(studentList) {

	$("#fatherNameId").val(studentList[0].fatherName);
	$("#fatherMobileNoId").val(studentList[0].fatherMobileNo);
	$("#fatherQualificationId").val(studentList[0].fatherQualification);
	$("#fatheremailId").val(studentList[0].fatheremailId);

	$("#motherNameId").val(studentList[0].motherName);
	$("#motherMobileNoId").val(studentList[0].motherMobileNo);
	$("#motherQualificationId").val(studentList[0].motherQualification);
	$("#motheremailId").val(studentList[0].motheremailId);

	$("#gaurdianNameId").val(studentList[0].gaurdianName);
	$("#guardianMobileNoId").val(studentList[0].guardianMobileNo);
	$("#guardianemailId").val(studentList[0].guardianemailId);

	$("#sibilingadminnoId").val(studentList[0].sibilingadminno);
	$("#parentId").val(studentList[0].parentId);
	$("#sibilingClassId").val(studentList[0].sibilingClass);
	$("#primarypersonId option[value='"+ studentList[0].primaryPerson+"']").attr('selected', 'true');
	$("#hprymarycntperson").val(studentList[0].primaryPerson);
	$("#studentSibilingIdIntId").val(studentList[0].studentSibilingIdInt);
	$("#sibilingClassID").val(studentList[0].sibilingClassId);
	
	$("#motheroccupationId").val(studentList[0].motheroccupation);
	$("#fatheroccupationId").val(studentList[0].fatheroccupation);
	$("#paddrs").val(studentList[0].address);
	
	
	
	$("#fatherNameId").attr('readonly', true);
	$("#fatherMobileNoId").attr('readonly', true);

	$("#fatherQualificationId").attr('readonly', true);
	$("#fatheremailId").attr('readonly', true);

	$("#motherNameId").attr('readonly', true);
	$("#motherMobileNoId").attr('readonly', true);
	$("#motherQualificationId").attr('readonly', true);
	$("#motheremailId").attr('readonly', true);

	$("#gaurdianNameId").attr('readonly', true);
	$("#guardianMobileNoId").attr('readonly', true);
	$("#guardianemailId").attr('readonly', true);

	$("#motheroccupationId").attr('readonly', true);
	$("#fatheroccupationId").attr('readonly', true);
	$("#paddrs").attr('readonly', true);
	
	
}

function viewTempAdmissionStudentDetails(tempStudentList,temid) {
	
	$("#hiddentempregid").val(temid);
	$("#studentFirstNameId").val(tempStudentList[0].studentFirstName);
	$("#studentLastNameId").val(tempStudentList[0].studentLastName);
	$("#dateofJoinId").val(tempStudentList[0].createDate);
	$("#category option[value='"+ tempStudentList[0].streemcode +"']").attr('selected', 'true');
	//$("#studClassId").val(tempStudentList[0].classname);
	var stream=$("#category").val();
	if(stream == "CLS1"){
		$("#schoolLocationId option[value='LOC1']").attr('selected', 'true');
	}else{
		$("#schoolLocationId option[value='LOC2']").attr('selected', 'true');
	}
	$("#dateofBirthId").val(tempStudentList[0].dateofBirth);
	ageCalculate($("#dateofBirthId").val());
	$("#genderId option[value='"+ tempStudentList[0].gender +"']").attr('selected', 'true');
	$("#nationalityId option[value='"+ tempStudentList[0].nationality +"']").attr('selected', 'true');
	$("#religion option[value='"+ tempStudentList[0].religion +"']").attr('selected', 'true');
	$("#casteId option[value='"+ tempStudentList[0].caste +"']").attr('selected', 'true');
	$("#casteCategoryId option[value='"+ tempStudentList[0].casteCategory +"']").attr('selected', 'true');
	$("#mothertongueId option[value='"+ tempStudentList[0].mothertongue +"']").attr('selected', 'true');
	$("#aadharId").val(tempStudentList[0].aadharno);
	$("#fatherNameId").val(tempStudentList[0].fatherName);
	$("#fatherMobileNoId").val(tempStudentList[0].fatherMobileNo);
	$("#fatherQualification").val(tempStudentList[0].fatherQualification);
	$("#fatherOccupation option[value='"+ tempStudentList[0].fatheroccupation +"']").attr('selected', 'true');
	$("#fatherDepartment").val(tempStudentList[0].fatherDepartment);
	$("#fatherDesignation").val(tempStudentList[0].fatherDesignation);
	$("#fatherofficeaddress").val(tempStudentList[0].fatherOfficeAddress);
	$("#fatherAnnualIncome").val(tempStudentList[0].fatherAnnualIncome);
	$("#fatherEmail").val(tempStudentList[0].fatheremailId);
	$("#motherNameId").val(tempStudentList[0].motherName);
	$("#motherMobileNoId").val(tempStudentList[0].motherMobileNo);
	$("#motherQualificationId").val(tempStudentList[0].motherQualification);
	$("#motherOccupation option[value='"+ tempStudentList[0].motheroccupation +"']").attr('selected', 'true');
	$("#motherDepartment").val(tempStudentList[0].motherDepartment);
	$("#motherDesignation").val(tempStudentList[0].motherDesignation);
	$("#motherofficeaddress").val(tempStudentList[0].motherOfficeAddress);
	$("#motherAnnualIncome").val(tempStudentList[0].motherAnnualIncome);
	$("#motherEmail").val(tempStudentList[0].motheremailId);
	$("#paddrs").val(tempStudentList[0].address);
	$('#checkAddressId').prop("checked",true) ;
	$("#presentadd").val(tempStudentList[0].presentaddress);
	$("#primarypersonId option[value='"+ tempStudentList[0].primaryPerson +"']").attr('selected', 'true');
	$('#imagePreview').attr('src', tempStudentList[0].imageFileName);
	$('#photohiddenid').val(tempStudentList[0].imageFileName),
	$("#hiddenenquiryid").val(tempStudentList[0].enquiryId);
	$("#smsnumber").val(tempStudentList[0].smsnumber);
	
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
function readURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imagePreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}

}

function readFatherURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imageFatherPreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}
}


function readMotherURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imageMotherPreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}

}



function readGuardianURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imageGuardianPreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}

}
function getClassList(){
	var locationid=$("#locationname").val();
	datalist={
			"locationid" : locationid
	},

	$.ajax({

		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classname').html("");

			$('#classname').append('<option value="">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}
function getSectionList(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$('#locationname').val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
			
		}
	});
}


function getClassLanguage(classId,location,specializationId){
	var data = {
			"classId" : classId,
			"locationId":location,
			"specializationId":specializationId,
	};
	$.ajax({
		type : 'POST',
		url : "subject.html?method=getLangauageOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#firstlang').empty();
			$('#firstlang').append('<option value="all">'+ "------select------" + '</option>');
			$('#secondlang').empty();
			$('#secondlang').append('<option value="all">'+ "------select------" + '</option>');
			$('#thirdlang').empty();
			$('#thirdlang').append('<option value="all">'+ "------select------" + '</option>');
			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#firstlang').append('<option value="'+ result.jsonResponse[j].subjectCode+ '">'+ result.jsonResponse[j].subjectname + '</option>');
				$('#secondlang').append('<option value="'+ result.jsonResponse[j].subjectCode+ '">'+ result.jsonResponse[j].subjectname + '</option>');
				$('#thirdlang').append('<option value="'+ result.jsonResponse[j].subjectCode+ '">'+ result.jsonResponse[j].subjectname + '</option>');
			}
			$('#secondlang').change(function(){
				var firstlang=$('#firstlang').val();
				var secondlang=$(this).val();
				if(firstlang == secondlang){
					$("#secondlang").val("all");
					$("#thirdlang").val("all");
				}else{
					$("#secondlang  option[value='"+secondlang+"' ]").attr('selected', 'true');
				}
			});
			$('#thirdlang').change(function(){
				var firstlang=$('#firstlang').val();
				var secondlang=$('#secondlang').val();
				var thirdlang=$(this).val();
				if((secondlang == thirdlang) || (firstlang == thirdlang)){
					$("#thirdlang").val("all");
				}else{
					$("#thirdlang  option[value='"+thirdlang+"' ]").attr('selected', 'true');
				}
			});	

		}
	});
}
function getTempAdmissionDetailsList(){
		var temp_stu_Name = $("#temp_studentName").val();
		var temp_par_Name = $("#temp_parentName").val();
		var temp_mob_No	= $("#temp_mobileNo").val();
		
			
		$.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=searchStudentByTempAdmission",
			data : {
				'studentName' : temp_stu_Name,
				'parentName' : temp_par_Name,
				'mobileNo' : temp_mob_No,
			},
				async : false,
				success : function(response) 
				{
					var result = $.parseJSON(response);
				
					$("#admissionstudent tbody").empty();
					for ( var j = 0; j < result.jsonResponse.length; j++) {
						$("#admissionstudent tbody").append(
						"<tr class='"+result.jsonResponse[j].temp_regid+"'>"+
						"<td><input type='radio' name='select' class='select' id='"+result.jsonResponse[j].temp_regid+"'></td>" +
						"<td>"+result.jsonResponse[j].temp_regid+"</td>"+
						"<td>"+result.jsonResponse[j].studentname+"</td>"+
						"<td>"+result.jsonResponse[j].fatherName+"</td>"+
						"<td>"+result.jsonResponse[j].fatherMobileNo+"</td>"+
						"<td>"+result.jsonResponse[j].dateofBirth+"</td>"+
						"<td>"+result.jsonResponse[j].created_date+"</td>"+
						"<td style='position:relative'><span' class='glyphicon glyphicon-trash delete'  style='position: absolute;top:0;bottom:0;margin:auto;'></span'></td>"+
						"</tr>"
						
						);
					}
					
					deleteRecord();
				
			},
		});
			

}
function getHouse(locationId){
	$.ajax({

		type : 'POST',
		url : "studentRegistration.html?method=getHouse",
		data : {"locationId":locationId,
				"accyearid":$("#academicYear").val()
		},
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#studHouseId').empty();

			$('#studHouseId').append('<option value="">' + "--------Select----------"	+ '</option>');

			for ( var j = 0; j < result.houseList.length; j++) {

				$('#studHouseId').append('<option value="' + result.houseList[j].houseId
						+ '">' + result.houseList[j].houseName
						+ '</option>');
			}
			$('#studHouseId').val($("#househiddenid").val());
		}
	});
}

function getAdmissionNo(){
	var locationId=$('#schoolLocationId').val();
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAdmissionNo",
		data : {"locationId":locationId},
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$('#studentrollno').empty();
			$('#studentrollno').val(result.admissionNo);
		}
	});
}
function printApplication(){
	 var a="";
	 if($(".className").text()=="XI" || $(".className").text()=="XII"){
	 	a=$("#css_for-senior").val();
	 	}
	 else if($(".className").text()=="LKG" || $(".className").text()=="UKG"){
	 	a=$("#css_for-kg").val();
	 	}
	 else{
	 	
	 	a=$("#css_for-school").val();
	 }
	 		var b = document.getElementById("container").innerHTML;

	 	  
	 	
	 	    var abd='<style>' + a +'</style>' + b;
	 	    
	  var frame1 = $('<iframe />');
	  frame1[0].name = "frame1";
	  frame1.css({ "position": "absolute", "top": "-1000000px" });
	  $("body").append(frame1);
	  var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
	  frameDoc.document.open();
	  //Create a new HTML document.
	  frameDoc.document.write('<html><head><title>DIV Contents</title>');
	  //Append the external CSS file.
	  
	  frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
	  frameDoc.document.write('<link href="CSS/Parent/PrintAppForm.css" rel="stylesheet">');
	   frameDoc.document.write('</head><body>');

	  
	  //Append the DIV contents.
	  frameDoc.document.write(abd);
	  frameDoc.document.write('</body></html>');
	  frameDoc.document.close();
	  setTimeout(function () {
	      window.frames["frame1"].focus();
	      window.frames["frame1"].print();
	      frame1.remove();
	  }, 100);
	 	
}

function getAdmissionDetails(id){
	
	$.ajax({
		type : "POST",
		url : "studentRegistration.html?method=getAdmissionDetails",
		data : {"temadmissionid" : id},
		async :false,
		success : function(data){
			var result = $.parseJSON(data);
			$("#container .className").text(result.stuList[0].classname);
			$("#container .stuName").text(result.stuList[0].studentFirstName.toUpperCase());
			$("#container .dobf").text(result.stuList[0].dateofBirth);
			$("#container .dobw").text(result.stuList[0].dateofBirthInWords);
			$("#container .gender").text(result.stuList[0].gender);
			$("#container .ageason").text(result.stuList[0].age);
			
			if(result.stuList[0].casteCategory=="SC" || result.stuList[0].casteCategory=="ST" || result.stuList[0].casteCategory=="OBC")
			$("#container .isscst").text("YES");
			else
				$("#container .isscst").text("NO");	
			$("#container .mottongue").text(result.stuList[0].mothertongue);
			$("#container .fatname").text(result.stuList[0].fatherName);
			$("#container .motName").text(result.stuList[0].motherName);
			$("#container .fatOccu").text(result.stuList[0].fatheroccupation);
			$("#container .motOccu").text(result.stuList[0].motheroccupation);
			$("#container .fatdes").text(result.stuList[0].fatherDesignation);
			$("#container .motdes").text(result.stuList[0].motherDesignation);
			$("#container .fathoffiaddr").text(result.stuList[0].fatherOfficeAddress);
			$("#container .motoffiaddr").text(result.stuList[0].motherOfficeAddress);
			$("#container .fatincome").text(result.stuList[0].fatherAnnualIncome);
			$("#container .motincome").text(result.stuList[0].motherAnnualIncome);
			$("#container .peraddr").text(result.stuList[0].address);
			$("#container .fathphone").text(result.stuList[0].fatherMobileNo);
			$("#container .motphone").text(result.stuList[0].motherMobileNo);
			$("#container .smsNo").text(result.stuList[0].smsnumber);
			$("#container .stuimg").attr('src',result.stuList[0].image);
			$("#container .nationality").text(result.stuList[0].nationality);
			$("#container .religion").text(result.stuList[0].religion);
			$("#container .casteName").text(result.stuList[0].caste);
			$("#container .preaddr").text(result.stuList[0].presentaddress);
			$("#container .lastKindergartenName").text(result.stuList[0].lastKindergartenName);
			$("#container .secondLanguage").text(result.stuList[0].secondLanguage);
			$("#container .thirdLanguage").text(result.stuList[0].thirdLanguage);
			$("#container .schemeofstudy").text(result.stuList[0].schemeofstudy);
			$("#container .opt_subjects").text(result.stuList[0].opt_subjects)
			$("#container .aadharNo").text(result.stuList[0].aadharno);
			$("#container .istc").text(result.stuList[0].isTc);
			$("#container .isMigration").text(result.stuList[0].isMigration);
			
			$("#container .emergencyNo").text(result.stuList[0].emergencyNo);
			$("#container .fatPreAddLandLiNo").text(result.stuList[0].communication_landline)
			$("#container .landlineNo").text(result.stuList[0].landLine);
			$("#container .father_office_landline").text(result.stuList[0].father_office_landline);
			$("#container .mother_office_landline").text(result.stuList[0].mother_office_landline);
			
			
		}
	});
}

function deleteRecord(){
$(".delete").click(function(){
	var row=$(this).closest("tr");
	var id=$(this).closest("tr").attr("class");
	$.ajax({
		type : "POST",
		url : "studentRegistration.html?method=deleteTemRecord",
		data : {"id" : id},
		async :false,
		success : function(data){
			var result = $.parseJSON(data);
			if(result.resultStatus="true"){
				row.remove();
			}
		}
	});
	
	
});
}

