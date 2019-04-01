var hteacherID;

$(document).ready(function() {

			var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
			if(pageUrl=="teacherregistration.html?method=teacherUpdation"){
				if($(".errormessagediv .validateTips").text()==""){
					$(".successmessagediv").show();
					setTimeout(function(){
						window.location.href="adminMenu.html?method=staffList";
					},2000);
				}
			}

			
			var is_student_studying = $('.radio-inline[name="is_student_studying"]:checked').val();

			if($("#reportingTo").val() !=null || $("#reportingTo").val() != undefined){
				$("#reportingTo").val($("#reportingToHidden").val());
			}

			if($("#fatherMobile").val() !=null || $("#fatherMobile").val() != undefined){
				$("#fatherMobile").val($("#fatherMobileHidden").val());
			}

			if($("#motherMobile").val() !=null || $("#motherMobile").val() != undefined){
				$("#motherMobile").val($("#motherMobileHidden").val());
			}

			$('#student_admission_id').hide();
			$('#student_admission_label').hide();
			$("#idproof").hide();
			$("#teaprofile").hide();
			
			getSchoolName();

			$("#usertype").change(function() {

						var usertype = $("#usertype").val();

						if (usertype == "admin") {
							$("#roleId option[value='ROL1' ]").attr('selected', 'true');
							$("#roleId").attr('disabled', true);
						}

						if (usertype == "teacher") {
							$("#roleId option[value='ROL2' ]").attr('selected', 'true');
							$("#roleId").attr('disabled', true);
						}

						if (usertype == "custum") {
							$("#roleId option[value=' ' ]").attr('selected', 'true');
							$("#roleId").attr('disabled', false);
						}

						$("#selectedRole").val($("#roleId").val().trim());

					});

			if($("#hiddenimage").val()!="" && $("#hiddenimage").val()!=undefined){


				$('#imagePreview').attr('src', $("#hiddenimage").val());

				$('#teaImageId').attr('src', $("#hiddenimage").val());

				$("#document1btn").attr('name',$("#hiddenprofile").val());

				$("#document2btn").attr('name',$("#hiddenidproof").val());

				$("#document3btn").attr('name',$("#hiddenimage").val());

				$("#document4btn").attr('name', $("#hiddenattachment1").val());
				$("#document5btn").attr('name', $("#hiddenattachment2").val());
				$("#document6btn").attr('name', $("#hiddenattachment3").val());
				$("#document7btn").attr('name', $("#hiddenattachment4").val());
				$("#document8btn").attr('name', $("#hiddenattachment5").val());

			}
			$('.downloadDoc').click(function() {

						var path = $(this).attr('name');
						window.location.href = "teacherregistration.html?method=downloadDocument&Path="
							+ path.trim();
					});

			$("#deleteIDProof").click(function(){

				$("#idproof").show();

				$("#document2btn").hide();
				$("#deleteIDProof").hide();
				$("#downloadIdTitle").hide();

			});

			$("#deleteProfile").click(function(){

				$("#teaprofile").show();

				$("#document1btn").hide();
				$("#deleteProfile").hide();
				$("#downloadProfileTitle").hide();

			});


			$("#deleteTeacherImage").click(function(){

				$("#teaImageId").show();

				$("#document3btn").hide();
				$("#deleteTeacherImage").hide();
				$("#downloadTeacherImage").hide();

			});

			if($("#hiddenimage").val()!="" && $("#hiddenimage").val()!=undefined){

				$("#teaImageId").hide();
				$("#document3btn").show();
				$("#deleteTeacherImage").show();
				$("#downloadTeacherImage").show();
			}

			if($("#hiddenattachment1").val()!="" && $("#hiddenattachment1").val()!=undefined){
				$("#fileattachment1Div").show();
				$("#document4btn").show();
				$("#deleteFile1").show();
				$("#downloadFileAttachment1Title").show();
			}

			if($("#hiddenattachment2").val()!="" && $("#hiddenattachment2").val()!=undefined){
				$("#fileattachment2Div").show();
				$("#document5btn").show();
				$("#deleteFile2").show();
				$("#downloadFileAttachment2Title").show();
			}

			if($("#hiddenattachment3").val()!="" && $("#hiddenattachment3").val()!=undefined){
				$("#fileattachment3Div").show();
				$("#document6btn").show();
				$("#deleteFile3").show();
				$("#downloadFileAttachment3Title").show();
			}

			if($("#hiddenattachment4").val()!="" && $("#hiddenattachment4").val()!=undefined){
				$("#fileattachment4Div").show();
				$("#document7btn").show();
				$("#deleteFile4").show();
				$("#downloadFileAttachment4Title").show();
			}

			if($("#hiddenattachment5").val()!="" && $("#hiddenattachment5").val()!=undefined){
				$("#fileattachment5Div").show();
				$("#document8btn").show();
				$("#deleteFile5").show();
				$("#downloadFileAttachment5Title").show();
			}







			$("#teaImageId").change(function(){


				//$("#imagePreview").show();
				readURL(this);

			});
			$("#deleteFile1").click(function(){
				$("#filaattachment1").show();

				$("#document4btn").remove();
				$("#deleteFile1").hide();
				$("#downloadFileAttachment1Title").hide();

			});

			$("#deleteFile2").click(function(){
				$("#filaattachment2").show();
				$("#document5btn").remove();
				$("#deleteFile2").hide();
				$("#downloadFileAttachment2Title").hide();

			});

			$("#deleteFile3").click(function(){
				$("#fileattachment3label").hide();
				$("#document6btn").hide();
				$("#deleteFile3").hide();
				$("#downloadFileAttachment3Title").hide();

			});
			$("#deleteFile4").click(function(){
				$("#fileattachment4label").hide();
				$("#document7btn").hide();
				$("#deleteFile4").hide();
				$("#downloadFileAttachment4Title").hide();

			});
			$("#deleteFile5").click(function(){

				$("#fileattachment5label").hide();
				$("#document8btn").hide();
				$("#deleteFile5").hide();
				$("#downloadFileAttachment5Title").hide();

			});







			hteacherID=$("#teacherid").val();
			if(hteacherID!=undefined){
			if(hteacherID !=""){
				$.ajax({

					type : 'POST',
					data : {"TeacherID" : hteacherID},
					url : 'teacherregistration.html?method=getMappingClasses',
					async : false,
					success : function(response) {
						var data = $.parseJSON(response);  


						for(var k = 0; k < data.MappingList.length; k++){


							var elementCount = parseInt(document.getElementById("count").value);

							var tr1 = document.createElement('tr');
							tr1.setAttribute("class","form-group");



							var th0 = document.createElement('td');

							var check = document.createElement("input");
							check.type = "radio";
							check.id = "check" + elementCount;
							check.setAttribute("class","ckeckboxclass");
							th0.align = 'center';

							th0.appendChild(check);
							tr1.appendChild(th0); 


							/*var th1 = document.createElement('td');

								var lable1 = document.createElement("lable");
								lable1.id = "lable" + elementCount;
								th1.align = 'center';

								th1.appendChild(lable1);
								tr1.appendChild(th1); */


							var th2 = document.createElement('td');

							var element1 = document.createElement("select");
							element1.name = "value(name"+elementCount+")";
							element1.id = "nameid"+elementCount;
							element1.setAttribute("class","select-control");

							th2.appendChild(element1);
							tr1.appendChild(th2);


							/*  var th3 = document.createElement('td');

						     	var lable2 = document.createElement("lable");
						    	lable2.id = "lablevalue" + elementCount;
						    	th3.align = 'center';

						    	th3.appendChild(lable2);
						    	tr1.appendChild(th3); */


							var th4 = document.createElement('td');

							var element2 = document.createElement("select");
							element2.name = "value(value"+elementCount+")";
							element2.id = "valueid"+elementCount;
							element2.setAttribute("class","inputbox");
							th4.appendChild(element2);

							tr1.appendChild(th4);


							document.getElementById("allstudent").appendChild(tr1);


							/*  $("#"+lable1.id).text("Class");
						        $("#"+lable2.id).text("Section");*/



							$.ajax({
								type : "GET",
								url : "teacherregistration.html?method=getCLasses",
								data :{schoolId:$('#schoolName').val()},
								async : false,

								success : function(data) {
									var result = $.parseJSON(data);

									$("#"+element1.id).append(
											'<option value="'
											+ ""
											+ '">'
											+ ""
											+ '</option>');


									for(var i=0;i<result.classList.length;i++){


										$("#"+element1.id).append(
												'<option value="'
												+ result.classList[i].classId.trim()
												+ '">'
												+ result.classList[i].className.trim()
												+ '</option>');


									}

								}
							});

							

							$("#"+element1.id+" option[value=" + data.MappingList[k].classId + "]").attr('selected', 'true');

							/* $("#"+"nameid"+elementCount).change(function(){*/

							getSection($("#"+"nameid"+elementCount).val(),element2.id,data.MappingList[k].sectionId);

							/*});*/
							$("#"+"nameid"+elementCount).change(function(){
								$("#valueid"+elementCount).empty();

								getSection(this.value,element2.id,"");

							});

							document.getElementById("count").value = elementCount + parseInt(1);


						}

					}

				});


				$.ajax({

					type : 'POST',
					data : {"TeacherID" : hteacherID},
					url : 'teacherregistration.html?method=getHpl',
					async : false,
					success : function(response) {
						var data = $.parseJSON(response);  
						$("#hpl").val(data.hpl);
						
					}
					});

				$.ajax({

					type : 'POST',
					data : {"TeacherID" : hteacherID},
					url : 'teacherregistration.html?method=getMappingSubjects',
					async : false,
					success : function(response) {
						var data = $.parseJSON(response);  


						for(var k = 0; k < data.subjectList.length; k++){


							var elementCount = parseInt(document.getElementById("subjectcount").value);

							var tr1 = document.createElement('tr');

							var th0 = document.createElement('td');

							var check = document.createElement("input");
							check.type = "radio";
							check.id = "subjectcheck" + elementCount;
							check.setAttribute("class","ckeckboxclass");
							th0.align = 'center';

							th0.appendChild(check);
							tr1.appendChild(th0); 


							var th2 = document.createElement('td');

							var element1 = document.createElement("select");
							element1.name = "talue(name"+elementCount+")";
							element1.id = "subjectnameid"+elementCount;
							element1.setAttribute("class","select-control");

							th2.appendChild(element1);
							tr1.appendChild(th2);


							var th4 = document.createElement('td');

							var element2 = document.createElement("select");
							element2.name = "talue(value"+elementCount+")";
							element2.id = "subjectvalueid"+elementCount;
							element2.setAttribute("class","inputbox");
							th4.appendChild(element2);

							tr1.appendChild(th4);


							document.getElementById("addstaffsubject").appendChild(tr1);


							$.ajax({
								type : "GET",
								url : "teacherregistration.html?method=getCLasses",
								async : false,

								success : function(data) {
									var result = $.parseJSON(data);

									$("#"+element1.id).append(
											'<option value="'
											+ ""
											+ '">'
											+ ""
											+ '</option>');


									for(var i=0;i<result.classList.length;i++){


										$("#"+element1.id).append(
												'<option value="'
												+ result.classList[i].classId.trim()
												+ '">'
												+ result.classList[i].className.trim()
												+ '</option>');


									}

								}
							});


							$("#"+element1.id+" option[value=" + data.subjectList[k].classId + "]").attr('selected', 'true');

							getSubject($("#"+"subjectnameid"+elementCount).val(),element2.id,data.subjectList[k].subjectId);

							$("#subjectnameid"+elementCount).change(function(){
								$("#subjectvalueid"+elementCount).empty();

								getSubject(this.value,element2.id,"");

							});

							document.getElementById("subjectcount").value = elementCount + parseInt(1);
						}

					}

				});
			}
			
}

			setTimeout("removeMessage()" ,3000);
			setInterval(function() {
				$(".errormessagediv").hide();
			}, 3000);

			var primary = "#" + "primaryid";
			var secondary = "#" + "secondaryid";

			$
			.ajax({
				type : 'POST',
				url : 'teacherregistration.html?method=getSubjects',
				async : false,
				success : function(response) {
					var data = $.parseJSON(response);




					$(primary).append(

							'<option value="' + "all" + '">' + "ALL"

							+ '</option>');

					$(secondary).append(

							'<option value="' + "all" + '">' + "ALL"

							+ '</option>');
					for (var j = 0; j < data.SubjectList.length; j++) {

						$(primary)
						.append(
								'<option value="'
								+ data.SubjectList[j].subjectid
								+ '">'
								+ data.SubjectList[j].subjectname
								+ '</option>');

						$(secondary)
						.append(
								'<option value="'
								+ data.SubjectList[j].subjectid
								+ '">'
								+ data.SubjectList[j].subjectname
								+ '</option>');

					}

				}

			});

			var department = "#" + "department";

			$
			.ajax({
				type : 'POST',
				url : 'teacherregistration.html?method=getDepartment',
				async : false,
				success : function(response) {
					var data = $.parseJSON(response);

					for (var j = 0; j < data.DEPARTMENTLIST.length; j++) {

						$(department)
						.append(
								'<option value="'
								+ data.DEPARTMENTLIST[j].depId
								+ '">'
								+ data.DEPARTMENTLIST[j].depName
								+ '</option>');
					}

					var hiddendept=$("#hiddendept").val();
					$("#department option[value=" + hiddendept + "]").attr('selected', 'true');

				}

			});

			var designation = "#" + "designation";

			$
			.ajax({
				type : 'POST',
				url : 'teacherregistration.html?method=getDesignation',
				async : false,
				success : function(response) {
					var data = $.parseJSON(response);

					for (var j = 0; j < data.DESIGNATIONLIST.length; j++) {

						$(designation)
						.append(
								'<option value="'
								+ data.DESIGNATIONLIST[j].desgid
								+ '">'
								+ data.DESIGNATIONLIST[j].desgname
								+ '</option>');
					}

					var hiddendes=$("#hiddendes").val();
					$("#designation option[value=" + hiddendes + "]").attr('selected', 'true');

				}



			});

			/*var hiddenprimary=$("#hiddenprimary").val();

					$("#primaryid option[value=" + hiddenprimary + "]").attr('selected', 'true');

					var hiddensecoundary=$("#hiddensecoundary").val();
					$("#secondaryid option[value=" + hiddensecoundary + "]").attr('selected', 'true');
			 */





			var hiddenteatype=$("#hiddenteatype").val();
			
			$("#teachingtype option[value='"+hiddenteatype+"']").attr('selected', 'true');

			var hiddengender=$("#hiddengender").val();
			$("#gender option[value='"+hiddengender+"']").attr('selected', 'true');

			var hiddenroleid=$("#hroleid").val();
			$("#roleId option[value='"+hiddenroleid+"']").attr('selected', 'true');

			var husertype=$("#husertype").val();
			$("#usertype option[value='"+husertype+"']").attr('selected', 'true');

			if($('#hstudent_admission_id').val()!=null)			
			{
				$("#student_admission_id option[value="+ $('#hstudent_admission_id').val() + "]").attr('selected', 'true');

				$('input[name=is_student_studying][value='+ $('#his_student_studying').val() + ']').attr('checked', true);
				
				if($('#his_student_studying').val()=="Y")
				{
					$('#student_admission_label').show();
					$('#student_admission_id').show();
					$('#studentName').parent("div").parent("div").show();
					
				}
				
				else
				{
					$('#student_admission_label').hide();
					$('#student_admission_id').hide();
					$('#studentName').parent("div").parent("div").hide();

				}


			}  

			var hmaritalstatus=$("#hmaritalstatus").val();
			$("#maritalstatus option[value=" + hmaritalstatus + "]").attr('selected', 'true');


			if($("#hiddenattachment1").val()!="" && $("#hiddenattachment1").val()!=undefined){

				$("#document4btn").attr('name',$("#hiddenattachment1").val());

			} 

			if($("#hiddenattachment2").val()!="" && $("#hiddenattachment2").val()!=undefined){

				$("#document5btn").attr('name',$("#hiddenattachment2").val());

			}  

			if($("#hiddenattachment3").val()!="" && $("#hiddenattachment3").val()!=undefined){

				$("#document6btn").attr('name',$("#hiddenattachment3").val());

			}   

			if($("#hiddenattachment4").val()!="" && $("#hiddenattachment4").val()!=undefined){

				$("#document7btn").attr('name',$("#hiddenattachment4").val());

			}   

			if($("#hiddenattachment5").val()!="" && $("#hiddenattachment5").val()!=undefined){

				$("#document8btn").attr('name',$("#hiddenattachment5").val());

			}  






			/*  var hiddenblood=$("#hiddenblood").val();

					    $("#bloodId option[value="+hiddenblood+"]").attr('selected', 'true');*/

			$("#dateofbirthid").datepicker(
					{

						dateFormat : "dd-mm-yy",
						yearRange : 'c-65:c+65',
						maxDate : -1,
						changeMonth : "true",
						changeYear : "true",
						onClose : function(selectedDate) {
							$("#joindateid").datepicker("option",
									"minDate", selectedDate);

						}

					});

			$("#joindateid").datepicker(
					{

						dateFormat : "dd-mm-yy",
						yearRange : 'c-65:c+65',
						maxDate : 0,
						changeMonth : "true",
						changeYear : "true",
						onClose : function(selectedDate) {
							$("#dateofbirthid").datepicker("option",
									"maxDate", selectedDate);

						}

					});



			$('#save')
			.click(
					function() {

						if(validateform())
						{


							/*if(status=="update")
										{
										document.getElementById("teacherupdateform").submit();

										}else{*/
							document.getElementById("teacherupdateform").submit();

						}; 




					});


			/*$('#editTeacher')
					.click(
							function() {


								var count = 0;
								$(
										'input.vehicle_Checkbox_Class:checkbox:checked')
										.map(
												function() {

													var vehicle_id = $(
															this).attr(
															"id");
													var split_id = vehicle_id
															.split('_');
													getData = split_id[1]
															.split(',');

													count++;
												});
								if (count == 0 || count > 1) {
									$(".errormessagediv").show();
									$(".validateTips").text(
											"Check any one checkbox");
									return false;

								} else {

									window.location.href = "subject.html?method=getSubjectDetails&subjectcode="+getData[0];

								}
							});*/


			var cnt=0;
			$(".fileattachmentDiv").each(function(){
				var styleId=$(this).attr("style");
				if(styleId=="display: none;"){
					var fileId=$(this).attr("id");
					cnt=parseInt(fileId.split("ment")[1]);
					return false;
				}
			});
			$("#fileUploaddynmic").click(function(){

				var check = null;

				if(cnt<5){

					$("#fileattachment"+cnt+"Div").show();
					$("#fileattachment"+cnt+"label").show();
					$("#filaattachment"+cnt).show();

					cnt++;
				}else{

					$("#errorhover").show();

					$(".error").text("You can add only Five  files");

					setTimeout(function() {$('#errorhover').fadeOut();}, 3000);

					$('html, body').animate({ scrollTop: $('#errorhover').offset().top }, 'fast');
				}




			});
			$('.radio-inline').change(function(){
				var is_student_studying = $('.radio-inline[name="is_student_studying"]:checked').val();
				

				if(is_student_studying=="Y")
				{
					$('#student_admission_label').show();
					$('#student_admission_id').show();
					$('#studentName').parent("div").parent("div").show();
				}
				
				else
				{
					$('#student_admission_label').hide();
					$('#student_admission_id').hide();
					$('#studentName').parent("div").parent("div").hide();

				}
			});

			$('#student_admission_id').change(function(){



				var student_admission_id = $("#student_admission_id").val();



				datalist = {"student_admission_id" : student_admission_id},

				$.ajax(
						{

							type: "POST",

							url: "teacherregistration.html?method=getStudentDetails",

							data: datalist,

							success: function(data)

							{

								var result = $.parseJSON(data);

								$("#studentName").val(result.status);
							}


						});
			});	

		});


function validateform() {


	var teacherId = $("#regno").val();
	var abbreviate = $("#abbreviate").val();
	var firstname = $("#fname").val();
	var lastname = $("#lname").val();
	var department = $("#department").val();
	var designation = $("#designation").val();
	var teachingtype = $("#teachingtype").val();
	var usertype = $("#usertype").val();
	var role = $("#roleId").val();
	var reportingTo=$("#reportingTo").val();
	var username = $("#uname").val();
	var is_student_studying = $('.radio-inline[name="is_student_studying"]:checked').val();
	var student_admission_id = $("#student_admission_id").val();
	var gender = $("#gender").val();
	var dateofbirth = $("#dateofbirthid").val();
	var mobilenumber = $("#mnumber").val();
	var teacheremail = $("#teacherEmail").val();
	var panNumber = $("#panNumber").val();
	var aadhaarnumber = $("#aadhaarnumber").val();
	var maritalstatus = $("#maritalstatus").val();
	var presentadd=$("#presentadd").val().trim();
	var permanentadd=$("#permanentadd").val().trim();
	var joiningdate = $("#joindateid").val();
	var accountNumber=$("#accountNumber").val();


	var teaprofile = $("#teaprofile").val();
	var teaImageId = $("#teaImageId").val();
	var bvalid=true;


	var dob = Date.parse(dateConverter(dateofbirth));
	var doj = Date.parse(dateConverter(joiningdate));


	if (teacherId.length == 0) {
		$(".validateTips").text("Enter Staff Id");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	if (checkregistrationNo(teacherId)) {
		$(".validateTips").text("Staff Id Exists");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	if (abbreviate.length == 0) {
		$(".validateTips").text("Enter Abbreviate Id");
		$(".errormessagediv").show();

		bvalid = false;
		return false;
	}

	if (firstname.length == 0) {
		$(".validateTips").text("Enter First Name");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	if (firstname.length <= 2) {
		$(".validateTips").text("Name contain minimum 3");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	if (!firstname.match(/^[a-z]([a-z_" "])+$/i)) {
		$(".validateTips").text("Name should be Alphabet");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
/*	if (lastname.length == 0) {

		$(".validateTips").text("Enter Last Name");
		$(".errormessagediv").show();

		bvalid = false;
		return false;
	}*/
	if(!(lastname =="" || lastname==undefined)){
		if (!lastname.match(/^[a-zA-Z\s]+$/g)) {
			$(".validateTips").text("Name should be Alphabet");
			$(".errormessagediv").show();
			bvalid = false;
			return false;
		}
	}else{}

	if (department.length == 1) {
		$(".validateTips").text("Select Department");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}


	if (designation.length == 1) {
		$(".validateTips").text("Select Designation");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	/*if (teachingtype.length == 1) {
		$(".validateTips").text("Select Staff Type ");
		$(".errormessagediv").show();
		bvalid=false;
		return false;

	}*/

	/*if (usertype.length == 1) {

		$(".validateTips").text("Select UserType");
		$(".errormessagediv").show();
		document.getElementById("usertype").style.border = "1px solid #AF2C2C";
		document.getElementById("usertype").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}*/

	/*if (role.length == 1) {
		$(".validateTips").text("Select Role");
		$(".errormessagediv").show();

		document.getElementById("roleId").style.border = "1px solid #AF2C2C";
		document.getElementById("roleId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}
*/
	/*if (reportingTo.length == 1) {
		$(".validateTips").text("Select Reporting To");
		$(".errormessagediv").show();
		document.getElementById("reportingTo").style.border = "1px solid #AF2C2C";
		document.getElementById("reportingTo").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}*/

	/*if (username.length == 0) {
		$(".validateTips").text("Enter Username");
		$(".errormessagediv").show();

		document.getElementById("uname").style.border = "1px solid #AF2C2C";
		document.getElementById("uname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}*/
	/*if (username.length < 3) {
		$(".validateTips").text("Username contain minimum 5");
		$(".errormessagediv").show();
		document.getElementById("uname").style.border = "1px solid #AF2C2C";
		document.getElementById("uname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}*/
	/*if (!username.match(/^[a-z]([a-zA-Z0-9~@#$^*()_+=[\]{}|\\,.?:-])+$/i)) {
		$(".validateTips").text("Username should start with a Alphabet");
		$(".errormessagediv").show();
		document.getElementById("uname").style.border = "1px solid #AF2C2C";
		document.getElementById("uname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}*/
	/*if (checkusername(username)) {
		$(".validateTips").text("User Name already Exists");
		$(".errormessagediv").show();
		document.getElementById("uname").style.border = "1px solid #AF2C2C";
		document.getElementById("uname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}*/



	if (is_student_studying == "Y") {
		if (student_admission_id.length == 1) {
			$(".validateTips").text("Select Admission Number");
			$(".errormessagediv").show();

			document.getElementById("is_student_studying").style.border = "1px solid #AF2C2C";
			document.getElementById("is_student_studying").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			bvalid = false;
			return false;
		}
	}


	if (gender.length == 1) {
		$(".validateTips").text("Select Gender");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}

	if (dateofbirth.length == 0) {
		$(".validateTips").text("Select Date of Birth");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}

	if (dob >= doj) {
		$(".validateTips").text(
		"Date of Birth should be lesser than Date of Joining");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}

	if (mobilenumber.length == 0) {
		$(".validateTips").text("Enter Mobile No");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	if (mobilenumber.length < 10) {
		$(".validateTips").text("Mobile No should be min 10 & max 11 digits ");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	if (!mobilenumber.match(/^(?!0{6})([0-9])+$/i)) {
		$(".validateTips").text("Enter valid Mobile No");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}

	if(accountNumber!=0){
		if (!accountNumber.match(/^(?!0{6})([0-9])+$/i)) {
			$(".validateTips").text("Enter Only Numbers");
			$(".errormessagediv").show();
			bvalid=false;
			return false;
		}
	}


	if(teacheremail != ""){
		if (!teacheremail
				.match(/^([a-zA-Z])(([a-zA-Z0-9])*([\._-])?([a-zA-Z0-9]))*@(([a-zA-Z0-9\-])+(\.))+([a-zA-Z]{2,4})+$/)) {
			$(".validateTips").text("Enter valid Email ID");
			$(".errormessagediv").show();
			bvalid=false;
			return false;
		}

		if (checkmail(teacheremail)) {

			$(".validateTips").text("Email ID already Exists");
			$(".errormessagediv").show();
			bvalid=false;
			return false;
		}

	}

/*	if (panNumber.length == 0) {
		$(".validateTips").text("Enter Pan No");
		$(".errormessagediv").show();

		document.getElementById("panNumber").style.border = "1px solid #AF2C2C";
		document.getElementById("panNumber").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}*/

/*	if (aadhaarnumber.length == 0) {
		$(".validateTips").text("Enter Aadhaar No");
		$(".errormessagediv").show();

		document.getElementById("aadhaarnumber").style.border = "1px solid #AF2C2C";
		document.getElementById("aadhaarnumber").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}*/
	if(!(aadhaarnumber=="" || aadhaarnumber==undefined)){
		if (aadhaarnumber.length > 12) {
			$(".validateTips").text("Aadhaar No should be min 12 & max 12 digits ");
			$(".errormessagediv").show();
	
			document.getElementById("aadhaarnumber").style.border = "1px solid #AF2C2C";
			document.getElementById("aadhaarnumber").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
	
			bvalid = false;
			return false;
		}
	}else{}
	if(!(aadhaarnumber=="" || aadhaarnumber==undefined)){
		if (!aadhaarnumber.match(/^(?!0{6})([0-9])+$/i)) {
			$(".validateTips").text("Enter valid Aadhaar No");
			$(".errormessagediv").show();
	
			document.getElementById("aadhaarnumber").style.border = "1px solid #AF2C2C";
			document.getElementById("aadhaarnumber").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
	
			bvalid = false;
			return false;
		}
	}else{}

	if (!mobilenumber.match(/^(?!0{6})([0-9])+$/i)) {
		$(".validateTips").text("Enter valid Mobile No");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}

	if (maritalstatus.length == 1 || maritalstatus == null
			|| maritalstatus == "") {
		$(".validateTips").text("Select Marital Status");
		$(".errormessagediv").show();

		document.getElementById("maritalstatus").style.border = "1px solid #AF2C2C";
		document.getElementById("maritalstatus").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}


	if (presentadd.length == 0) {
		$(".validateTips").text("Enter Present Address");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	if (presentadd.length < 5) {
		$(".validateTips").text("Present Address contain minimum 5");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}


	if (permanentadd.length == 0) {
		$(".validateTips").text("Enter Permanent Address");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	if (permanentadd.length < 5) {
		$(".validateTips").text("Permanent Address contain minimum 5");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}



	var docreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.doc|.docx|.DOC|.DOCX)$/;
	var pdfreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.pdf|.PDF)$/;

	if (teaprofile.length > 0) {

		if (!teaprofile.match(docreg) && !teaprofile.match(pdfreg)) {
			$(".validateTips").text("Profile should be pdf or docs Format ");
			$(".errormessagediv").show();
			bvalid=false;
			return false;
		}
	}

	var imgReg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.jpg|.jpeg|.JPEG|.gif|.JPG|.png|.PNG)$/;
	if (teaImageId.length > 0) {
		if (!teaImageId.match(imgReg)) {
			$(".validateTips").text("Upload only Image Format Image");
			$(".errormessagediv").show();
			bvalid=false;
			return false;
		}
	}

	if (dob >= doj) {
		$(".validateTips").text(
		"Joining date should be greater than Date of Birth");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}

	var classarayy=[];
	var sectionarray=[];

	$(".teacherclass tr").each(function(){

		var classvalue =$(this).find('.select-control').val();

		if(classvalue!=undefined){

			classarayy.push(classvalue.trim());

		}

		var sectionval =$(this).find('.inputbox').val();

		if(sectionval!=undefined){

			sectionarray.push(sectionval.trim());

		}


	});

	for (var i = 0; i < classarayy.length; i++) {

		if(classarayy[i]==""){

			$(".validateTips").text("Class should not be empty");
			$(".errormessagediv").show();
			bvalid=false;
			return false;
		}

		if(sectionarray[i]==""){

			$(".validateTips").text("Delete Class & Section from last row");
			$(".errormessagediv").show();

			bvalid=false;
			return false;

		}
	}

	var classsesction=[];

	for(var i=0;i<classarayy.length;i++){

		classsesction.push(classarayy[i]+"-"+sectionarray[i]);
	}

	var sortclasssesction=classsesction.sort();
	for (var j = 0; j < classsesction.length-1; j++) {

		if(classsesction[j]!="" && sortclasssesction[j + 1] ){
			if (sortclasssesction[j + 1] == classsesction[j]) {
				$(".validateTips").text("Class & section should not be duplicate");
				$(".errormessagediv").show();

				bvalid=false;
				return false;
			}
		}

	}


	return bvalid;

}

function dateConverter(dateString) {
	var dateArray = [];
	var dateStringArray = dateString.split("-");
	dateArray.push(dateStringArray[2]);
	dateArray.push(dateStringArray[1]);
	dateArray.push(dateStringArray[0]);
	var dateString1 = dateArray.join("-");
	return dateString1;
}

function checkusername(username,teacherid) {
	var teacherid=$("#teacherid").val();


	var userdata = {
			"username" : username,
			"teacherId"  : teacherid
	};
	var status1 = false;

	$.ajax({

		type : "GET",
		url : "teacherregistration.html?method=checkUsername",
		data : userdata,
		async : false,

		success : function(data) {

			var result = $.parseJSON(data);

			status1 = result.status;

		}

	});

	return status1;
}

function checkAbreviativeId(abbreviate) {

	var userdata = {
			"abbreviate" : abbreviate,
	};
	var status1 = false;

	$.ajax({

		type : "GET",
		url : "teacherregistration.html?method=checkAbbreviativeId",
		data : userdata,
		async : false,

		success : function(data) {

			var result = $.parseJSON(data);

			status1 = result.status;

		}

	});

	return status1;
}

function checkmail(mailid) {

	var teacherId=$("#teacherid").val();
	var maildata = {
			"mailid" : mailid,
			"teacherId" : teacherId
	};
	var status1 = false;

	$.ajax({

		type : "GET",
		url : "teacherregistration.html?method=checkMail",
		data : maildata,
		async : false,

		success : function(data) {

			var result = $.parseJSON(data);

			status1 = result.status;

		}

	});

	return status1;

}

function removeMessage() {

	$(".successmessagediv").hide();

}

function checkregistrationNo(username,teacherid) {
	var teacherid=$("#teacherid").val();


	var userdata = {
			"registrtno" : username,
			"teacherId"  : teacherid
	};
	var status1 = false;

	$.ajax({

		type : "GET",
		url : "teacherregistration.html?method=checkRegistrationNo",
		data : userdata,
		async : false,

		success : function(data) {

			var result = $.parseJSON(data);

			status1 = result.status;

		}

	});

	return status1;
}


function add(){


	var elementCount = parseInt(document.getElementById("count").value);


	var tr1 = document.createElement('tr');


	var th0 = document.createElement('td');

	var check = document.createElement("input");
	check.type = "radio";
	check.id = "check" + elementCount;
	check.name="selectSection";
	check.setAttribute("class","ckeckboxclass");
	th0.align = 'center';

	th0.appendChild(check);
	tr1.appendChild(th0); 


	/*var th1 = document.createElement('td');

	var lable1 = document.createElement("lable");
	lable1.id = "lable" + elementCount;
	th1.align = 'center';

	th1.appendChild(lable1);
	tr1.appendChild(th1); */


	var th2 = document.createElement('td');

	var element1 = document.createElement("select");
	element1.name = "value(name"+elementCount+")";
	element1.id = "nameid"+elementCount;
	element1.setAttribute("class","select-control");

	th2.appendChild(element1);
	tr1.appendChild(th2);


	/*    var th3 = document.createElement('td');

 	var lable2 = document.createElement("lable");
	lable2.id = "lablevalue" + elementCount;
	th3.align = 'center';

	th3.appendChild(lable2);
	tr1.appendChild(th3); */


	var th4 = document.createElement('td');

	var element2 = document.createElement("select");
	element2.name = "value(value"+elementCount+")";
	element2.id = "valueid"+elementCount;
	element2.setAttribute("class","inputbox");
	th4.appendChild(element2);

	tr1.appendChild(th4);



	document.getElementById("allstudent").appendChild(tr1);


	/*  $("#"+lable1.id).text("Class");
    $("#"+lable2.id).text("Section");*/



	$.ajax({
		type : "GET",
		url : "teacherregistration.html?method=getCLasses",
		data :{schoolId:$('#schoolName').val()},
		async : false,

		success : function(data) {
			var result = $.parseJSON(data);

			$("#"+element1.id).append(
					'<option value="'
					+ ""
					+ '">'
					+ ""
					+ '</option>');


			for(var i=0;i<result.classList.length;i++){

				$("#"+element1.id).append(
						'<option value="'
						+ result.classList[i].classId.trim()
						+ '">'
						+ result.classList[i].className.trim()
						+ '</option>');


			}

		}
	});


	$("#"+"nameid"+elementCount).change(function(){
		$("#valueid"+elementCount).empty();

		getSection(this.value,element2.id,"");

	});


	document.getElementById("count").value = elementCount + parseInt(1);

	$(".inputbox")
	.change(
			function() {
				var currentClassVal = $(this).parent("td").parent("tr")
				.find(".select-control").val();
				var sectionVal = $(this).val();
				var sectionpoint = $(this).parent("td").parent("tr");

				$(".teacherclass tr td")
				.each(
						function() {

							if (currentClassVal == $(this)
									.parent("tr").not(
											sectionpoint).find(
											".select-control")
											.val()
											&& $(this).parent("tr")
											.not(sectionpoint)
											.find(".inputbox")
											.val() == sectionVal) {

								$(".validateTips")
								.text(
								"Section is Already Selected");
								$(".errormessagediv").show();
								sectionpoint.find(".inputbox")
								.val("");
							}

						});
			});

}


function getSection(clasvalue,sectionid,selectedsection){



	$.ajax({
		type : "GET",
		url : "teacherregistration.html?method=getMappedSection",
		data : {"classidVal":clasvalue,"TeacherID":hteacherID,"sectionID":selectedsection},
		async : false,

		success : function(data) {
			var result = $.parseJSON(data);


			$("#"+sectionid).append(
					'<option value="'
					+ ""
					+ '">'
					+ "-----select-----"
					+ '</option>');


			for(var i=0;i<result.sectionList.length;i++){


				$("#"+sectionid).append(
						'<option value="'
						+ result.sectionList[i].sectionId
						+ '">'
						+ result.sectionList[i].sectionname
						+ '</option>');

			}

		}
	});


	$("#"+sectionid+" option[value=" + selectedsection + "]").attr('selected', 'true');

}


function deleteClassMapping(){

	var checkcount=0;


	$(".teacherclass tr").each(function(){

		var elementCount = parseInt(document.getElementById("count").value);

		var checkboxvalue=$(this).find('.ckeckboxclass').attr("id");


		if(checkboxvalue!=undefined && checkboxvalue!=""){

			var count=parseInt(checkboxvalue.substr(5,checkboxvalue.length));

			if($('#'+checkboxvalue).is(":checked")){

				checkcount++;

				if((count+1)==elementCount){

					$("#"+checkboxvalue).parent().parent().remove();
					document.getElementById("count").value = elementCount - parseInt(1);

				}else{

					$(".validateTips").text("Delete Class & Section from last row");
					$(".errormessagediv").show();

				}
			}

		}

	});

	if(checkcount==0){


		$(".validateTips").text("Select record to delete");
		$(".errormessagediv").show();
	}

}



function addSubject(){

	var elementCount = parseInt(document.getElementById("subjectcount").value);

	var tr1 = document.createElement('tr');


	var th0 = document.createElement('td');

	var check = document.createElement("input");
	check.type = "radio";
	check.id = "subjectcheck" + elementCount;
	check.name="selectSubject";
	check.setAttribute("class","ckeckboxclass");
	th0.align = 'center';

	th0.appendChild(check);
	tr1.appendChild(th0); 




	var th2 = document.createElement('td');

	var element1 = document.createElement("select");
	element1.name = "talue(name"+elementCount+")";
	element1.id = "subjectnameid"+elementCount;
	element1.setAttribute("class","select-control");

	th2.appendChild(element1);
	tr1.appendChild(th2);


	var th4 = document.createElement('td');

	var element2 = document.createElement("select");
	element2.name = "talue(value"+elementCount+")";
	element2.id = "subjectvalueid"+elementCount;
	element2.setAttribute("class","inputbox");
	th4.appendChild(element2);

	tr1.appendChild(th4);




	document.getElementById("addstaffsubject").appendChild(tr1);

	$.ajax({
		type : "GET",
		url : "teacherregistration.html?method=getCLasses",
		data :{schoolId:$('#schoolName').val()},
		async : false,

		success : function(data) {
			var result = $.parseJSON(data);

			$("#"+element1.id).append(
					'<option value="'
					+ ""
					+ '">'
					+ ""
					+ '</option>');


			for(var i=0;i<result.classList.length;i++){

				$("#"+element1.id).append(
						'<option value="'
						+ result.classList[i].classId.trim()
						+ '">'
						+ result.classList[i].className.trim()
						+ '</option>');


			}

		}
	});


	$("#subjectnameid"+elementCount).change(function(){
		$("#subjectvalueid"+elementCount).empty();

		getSubject(this.value,element2.id,"");

	});




	document.getElementById("subjectcount").value = elementCount + parseInt(1);



}


function addLeave(){
	var elementCount = parseInt(document.getElementById("leavecount").value);
	var tr1 = document.createElement('tr');

	var th0 = document.createElement('td');

	var check = document.createElement("input");
	check.type = "radio";
	check.id = "leavecheck" + elementCount;
	check.name="selectleave";
	check.setAttribute("class", "ckeckboxclass");
	th0.align = 'center';
	th0.appendChild(check);
	tr1.appendChild(th0);

	var th2 = document.createElement('td');

	var element1 = document.createElement("select");
	element1.name = "leaves(name" + elementCount + ")";
	element1.id = "leavenameid" + elementCount;
	element1.setAttribute("class", "select-control");

	th2.appendChild(element1);
	tr1.appendChild(th2);

	var th4 = document.createElement('td');

	var element2 = document.createElement("input");
	element2.type="text";
	element2.name = "leaves(value" + elementCount + ")";
	element2.id = "leavevalueid" + elementCount;
	element2.setAttribute("class", "inputbox");
	th4.appendChild(element2);

	tr1.appendChild(th4); 
	document.getElementById("addTeacherLeave").appendChild(tr1);
	
	var location=$("#schoolName").val();

	$.ajax({
		type : "GET",
		url : "teacherregistration.html?method=getLeaveTypes",
		data:{'location':location},
		async : false,

		success : function(data) {
			var result = $.parseJSON(data);

			$("#" + element1.id).append('<option value="' + "" + '">' + "" + '</option>');
			
			for ( var i = 0; i < result.leaveTypeList.length; i++) {

				$("#" + element1.id).append('<option value="' + result.leaveTypeList[i].leaveID
								+ '">' + result.leaveTypeList[i].leaveName
								+ '</option>');

			}

		}
	});

	
	
	$("#" + "leavenameid" + elementCount).change(function() {
		
		$("#leavevalueid" + elementCount).empty();
		var leaveType=$("#" + "leavenameid" + elementCount).val();
		
		$.ajax({
			type : "GET",
			url : "teacherregistration.html?method=getNoOfLeave",
			data:{"leaveType":leaveType,"location":location},
			async : false,

			success : function(data) {
				var result = $.parseJSON(data);
				$("#leavevalueid" + elementCount).empty();
				$("#leavevalueid" + elementCount).val(result.noOfLeave[0].noofleaves);
				
			}
		});
		
		
		
		
		

	});

	document.getElementById("leavecount").value = elementCount + parseInt(1);
	
	//Code for showing error on duplicacy

	$(".inputbox")
			.change(
					function() {
						var currentClassVal = $(this).parent("td").parent("tr").find(".select-control").val();
						var subjectval = $(this).val();
						var subjectpoint = $(this).parent("td").parent("tr");

						$(".teachersubject  tr td").each(function() {

											if (currentClassVal == $(this)
													.parent("tr").not(
															subjectpoint).find(
															".select-control")
													.val()
													&& $(this).parent("tr")
															.not(subjectpoint)
															.find(".inputbox")
															.val() == subjectval) {

												$(".validateTips")
														.text(
																"Subject is Already Selected");
												$(".errormessagediv").show();
												sectionpoint.find(".inputbox")
														.val("");
											}

										});
					});

	
}










function getSubject(clasvalue,subjectID,selectedsubject){

	$.ajax({
		type : "GET",
		url : "teacherregistration.html?method=getSubject",
		data : {"classidVal":clasvalue},
		async : false,

		success : function(data) {
			var result = $.parseJSON(data);

			$("#"+subjectID).append(
					'<option value="'
					+ ""
					+ '">'
					+ "-----select-----"
					+ '</option>');

			for(var i=0;i<result.subjectList.length;i++){

				$("#"+subjectID).append(
						'<option value="'
						+ result.subjectList[i].subjectid
						+ '">'
						+ result.subjectList[i].subjectname
						+ '</option>');

			}

		}
	});

	$("#"+subjectID+" option[value=" + selectedsubject + "]").attr('selected', 'true');

}

function deleteSubject(){

	var checkcount=0;

	$(".teachersubject tr").each(function(){


		var elementCount = parseInt(document.getElementById("subjectcount").value);

		var checkboxvalue=$(this).find('.ckeckboxclass').attr("id");


		if(checkboxvalue!=undefined && checkboxvalue!=""){

			var count=parseInt(checkboxvalue.substr(12,checkboxvalue.length));


			if($('#'+checkboxvalue).is(":checked")){

				checkcount++;

				if((count+1)==elementCount){

					$("#"+checkboxvalue).parent().parent().remove();
					document.getElementById("subjectcount").value = elementCount - parseInt(1);

				}else{

					$(".validateTips").text("Delete Class & Subject from last row");
					$(".errormessagediv").show();
				}
			}

		}


	});

	if(checkcount==0){


		$(".validateTips").text("Select record to delete");
		$(".errormessagediv").show();
	}

}


function deleteLeave() { 
	
	var checkcount = 0;

	$(".teacherLeave tr").each(function() {

						var elementCount = parseInt(document.getElementById("leavecount").value);

						var checkboxvalue = $(this).find('.ckeckboxclass').attr("id");

						if (checkboxvalue != undefined && checkboxvalue != "") {

							var count = parseInt(checkboxvalue.substr(12,checkboxvalue.length));

							if ($('#' + checkboxvalue).is(":checked")) {

								checkcount++;

								if ((count + 1) == elementCount) {

									$("#" + checkboxvalue).parent().parent().remove();
									document.getElementById("leavecount").value = elementCount - parseInt(1);

								} else {

									$(".validateTips").text("Delete Leave Type from last row");
									$(".errormessagediv").show();
								}
							}

						}

					});

	if (checkcount == 0) {

		$(".validateTips").text("Select record to delete");
		$(".errormessagediv").show();
	}

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


function getSchoolName(){
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getSchoolLocation",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#schoolName').empty();
			$('#schoolName').append('<option value="' + "" + '">'+ "----------Select----------"+ '</option>');

			for ( var j = 0; j < result.locationList.length; j++) {

				$('#schoolName').append(
						'<option value="'
						+ result.locationList[j].location_id
						+ '">'
						+ result.locationList[j].locationname
						+ '</option>');
			}
			var hschoolname=$("#hschoolname").val();
			
			$("#schoolName option[value=" + hschoolname + "]").attr('selected', 'true');
			
			
		}
	});
}





