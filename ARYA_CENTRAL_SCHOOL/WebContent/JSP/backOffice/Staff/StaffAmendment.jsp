<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>



<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />

<link href="CSS/newUI/custome.css" rel="stylesheet">

<script type="text/javascript"
	src="JS/backOffice/Staff/StaffAmendment.js"></script>

<script type="text/javascript">
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<script>
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})

</script>

<style>
	.form-group{
	margin-bottom: 10px;
	}
#allstudent tr {
	font-size: 14px;
	border-bottom: 1px solid #ddd;
	border-left: 1px solid #ddd;
}

#allstudent th {
	background: #f9f9f9 !important;
	border-bottom: 1px solid #ddd;
	border-top: 1px solid #ddd;
	border-right: 1px solid #ddd;
	font-size: 11pt;
	color: #767676 !important;
	text-align: center;
	font-family: Roboto Medium;
}

#addstaffsubject tr {
	font-size: 14px;
	border-bottom: 1px solid #ddd;
	border-left: 1px solid #ddd;
}

#addstaffsubject tr:nth-child(n) {
	background-color: #F2FAFC;
}

#addstaffsubject tr:nth-child(2n) {
	background-color: #FFFFFF;
}

#addstaffsubject tr:hover {
	background: #9CDDE3;
	color: #fff;
	cursor: pointer;
}

#addstaffsubject th {
	background: #f9f9f9 !important;
	border-bottom: 1px solid #ddd;
	border-top: 1px solid #ddd;
	border-right: 1px solid #ddd;
	font-size: 11pt;
	color: #767676 !important;
	text-align: center;
	font-family: Roboto Medium;
}

#addstaffsubject th a {
	color: #767676 !important;
}

#addstaffsubject tr :HOVER {
	color: #000 !important;
}

#addstaffsubject  tr td {
	border-right: 1px solid #ddd;
	border-left: 0;
	border-top: 0;
	border-bottom: 0;
	color: #000000;
}

.inputbox {
	display: block;
	width: 100%;
	height: 34px;
	padding: 6px 12px;
	font-size: 13px;
	line-height: 1.42857;
	color: #767676;
	background-color: #fff;
	background-image: none;
	border: 1px solid #cbd5dd;
	border-radius: 0;
	border: 0;
}

.select-control {
	border: 0;
	display: block;
	width: 100%;
	height: 34px;
	padding: 6px 12px;
	font-size: 13px;
	line-height: 1.42857;
	color: #767676;
	background-color: #fff;
	background-image: none;
	border-radius: 0;
}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 0px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
		
			<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STAFFDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
			<img src="images/addstu.png" />&nbsp;<span>Modify Staff</span>
			</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>		
		</p>

<center>
		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"> <span><bean:write
								name="successmessagediv" scope="request" /></span>
					</a>
				</div>
			</div>
		</logic:present>
</center>


		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>


		<form id="teacherupdateform"
			action="teacherregistration.html?method=teacherUpdation" enctype="multipart/form-data" method="post">
			<div class="panel panel-group" id="accordion" role="tablist" aria-multiselectable="true" style="margin-bottom: 0px !important;">
			
					<div class="panel panel-primary" style="padding-bottom: 0px; margin-bottom: 5px;">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676"> 
							<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Staff Details
							</h4></a>
						

						<div class="navbar-right">
						<logic:present name="UserDetails" scope="session">
							<logic:iterate id="daymap" name="UserDetails"
								property="permissionmap" scope="session">
								<logic:equal value="STAFFDIS" name="daymap" property="key">
									<logic:equal value="true" name="daymap" property="value">
										<span id="save" class="buttons" style="cursor: pointer">Save</span>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</logic:present>					

							<span id="back" class="buttons">Back</span>


						</div>

					</div>




					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" style="padding: 10px;">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Staff
										Id <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control clearfix" readonly="readonly"
											name="regno" id="regno" placeholder="Teacher Id"
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="regsitrationNo"/></logic:present>" />
									</div>
								</div>

								 
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">First
										Name<font color="red">*</font>
									</label>
									<div class="col-xs-7">

										<input type="text" name="tfastname" id="fname"
											class="form-control clearfix" placeholder="First Name" maxlength="25"
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="tfastname"/></logic:present>" />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Department<font
										color="red">*</font></label>
									<div class="col-xs-7">
										<select name="department" id="department" class="form-control clearfix">
											<option value=" ">------Select----------</option>
										</select>
									</div>
								</div>

								 

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Date of
										Joining<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" name="joiningdate"
											id="joindateid" class="form-control clearfix" placeholder="Click Here"
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="joiningdate"/></logic:present>" />
									</div>
								</div>

								 
								

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">UserType
										<!-- <font color="red">*</font> -->
									</label>
									<div class="col-xs-7">
										<select name="usertype" id="usertype" class="form-control clearfix"
											onkeypress="HideError()">
											<option value=" ">----------Select----------</option>
											<option value="admin">Admin</option>
											<option value="teacher">Teacher</option>
											<option value="custum">Custom</option>
										</select>



									</div>
								</div>

								 

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Reporting To</label>
									<div class="col-xs-7">
										<select name="reportingTo" id="reportingTo"
											class="form-control clearfix" onkeypress="HideError()">
											<option value=" "></option>
											<logic:present name="reportingToList">
												<logic:iterate id="reportingTo" name="reportingToList">
													<option
														value='<bean:write name="reportingTo" property="teacherId"/>'>
														<bean:write name="reportingTo" property="teacherName" />
													</option>
												</logic:iterate>
											</logic:present>
										</select>



									</div>
								</div>
								 

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Is
										Student Studying<font color="red">*</font> </label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
										<label> <input type="radio" class="radio-inline" style="vertical-align: middle; margin: 0px;"
											name="is_student_studying" value="Y" />&nbsp;Yes&nbsp;&nbsp;&nbsp;
										</label> <label><input type="radio" class="radio-inline" style="vertical-align: middle; margin: 0px;"
											name="is_student_studying" value="N" />&nbsp;No</label>
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"
										id="student_admission_label">Admission No</label>
									<div class="col-xs-7">
										<select name="student_admission_id" id="student_admission_id"
											class="form-control clearfix" onkeypress="HideError()">
											<option value=" "></option>
											<logic:present name="StudentAdmissionNumber">
												<logic:iterate id="reportingTo"
													name="StudentAdmissionNumber">
													<option
														value='<bean:write name="reportingTo" property="studentid"/>'>
														<bean:write name="reportingTo" property="admission" />
													</option>
												</logic:iterate>
											</logic:present>
										</select> <input type="hidden" name="StudentAdmissionNumber"
											id="StudentAdmissionNumber" value="" />
									</div>
								</div>



							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										Abbreviate Id <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control clearfix" name="abbreviate"
											id="abbreviate" onkeypress="HideError()" placeholder=""
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="abbreviate"/></logic:present>" />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Last
										Name</label>
									<div class="col-xs-7">
										<input type="text" name="tlastname" id="lname" maxlength="50"
											class="form-control clearfix" placeholder="Last Name "
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="tlastname"/></logic:present>" />
									</div>
								</div>

								 


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Designation<font
										color="red">*</font></label>
									<div class="col-xs-7">
										<select name="designation" id="designation"
											class="form-control clearfix">
											<option value=" ">------Select----------</option>
										</select>
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Staff
										Type<!-- <font color="red">*</font> -->
									</label>
									<div class="col-xs-7">
										<select name="teachingtype" id="teachingtype"
											class="form-control clearfix">
											<option value=" ">----------Select----------</option>
											<option value="TEACHING">TEACHING</option>
											<option value="NON TEACHING">NON TEACHING</option>
											<option value="OFFICE STAFF">OFFICE STAFF</option>
											<option value="SUBJECT TEACHER">SUBJECT TEACHER</option>
											<option value="MISC">MISC</option>
											<option value="PROBATION">PROBATION</option>
											<option value="GENERAL">GENERAL</option>
											<option value="TEMPORARY">TEMPORARY</option>
											<option value="PEON">PEON</option>
											<option value="HOUSEKEEPING">HOUSEKEEPING</option>
										</select>
									</div>
								</div>

								 


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Role<!-- <font color="red">*</font> --></label>
									<div class="col-xs-7">
										<select name="roleId" id="roleId" class="form-control clearfix">
											<option value=" "></option>
											<logic:present name="RolePermission" scope="request">
												<logic:iterate id="role" name="RolePermission" >
													<option
														value='<bean:write name="role" property="roleCode"/>'><bean:write name="role" property="roleName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								 

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">User
										Name<!-- <font color="red">*</font> --></label> 
									<div class="col-xs-7">
										<input type="text" name="username" id="uname" maxlength="25"
											placeholder="User Name" class="form-control clearfix"
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="username"/></logic:present>" />
									</div>
								</div>


						<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">HPL
									</label>
									<div class="col-xs-7">
										<input type="text" name="hpl" id="hpl"
											onkeypress="HideError()" maxlength="25" placeholder=""
											class="form-control"
											value="" />
									</div>
								</div>

							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name
										<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select name="schoolName" id="schoolName"
											onkeypress="HideError()" class="form-control clearfix">
											<option value=" ">----------Select----------</option>
										</select>
									</div>
								</div>
								
								 


								<div class="form-group clearfix" style="display: none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student
										Name </label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
										<input type="text" name="studentName" id="studentName"
											class="form-control clearfix" readonly="readonly"
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="studentName"/></logic:present>" />
									</div>
								</div>




							</div>
						</div>

					</div>


				</div>
			</div>

			<div class="panel panel-primary" style="padding-bottom: 0px; margin-bottom: 5px;">
				<div class="panel-heading" role="tab" id="headingTwo" style="margin-bottom: 0px !important;">
					
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseTwo"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseTwo"><h4 class="panel-title"> <i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Personal Details
					</h4></a>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body" style="padding: 10px;">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Gender<font
									color="red">*</font></label>
								<div class="col-xs-7">
									<select name="gender" id="gender" class="form-control clearfix">
										<option value=" ">------Select----------</option>
										<option value="MALE">Male</option>
										<option value="FEMALE">Female</option>

									</select>
								</div>
							</div>
							 


							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Mobile No<font
									color="red">*</font></label>
								<div class="col-xs-7">
									<input type="text" name="teachermobno" id="mnumber"
										maxlength="11" placeholder="Mobile No" class="form-control clearfix"
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="teachermobno"/></logic:present>" />
								</div>
							</div>

							 

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Qualification
								</label>
								<div class="col-xs-7">
									<input type="text" name="tqualification" id="qualification"
										maxlength="12" class="form-control clearfix"
										placeholder="Qualification"
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="tqualification"/></logic:present>" />
								</div>
							</div>
							 



							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Bank Name
								</label>
								<div class="col-xs-7">
									<input type="text" name="bankName" id="bankName" maxlength="45"
										class="form-control clearfix" placeholder="Bank Name"
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="bankName"/></logic:present>" />
								</div>
							</div>
							 


							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Pan
									Number</label>
								<div class="col-xs-7">
									<input type="text" name="panNumber" id="panNumber"
										maxlength="45" class="form-control clearfix" placeholder="Pan Number"
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="panNumber"/></logic:present>" />
								</div>
							</div>
							 


							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Id Proof</label>
								<div class="col-xs-7">
									<input type="file" class="docFile form-control clearfix" name="idproof"
										id="idproof"> <input type="button" id="document2btn"
										name="idproof" class="downloadDoc" value="Download" /> <span
										id="downloadIdTitle"> downloadIdProof</span> <span
										style="font-size: 20px; color: red; cursor: pointer;"
										id="deleteIDProof">x</span>
								</div>
							</div>
							 
							
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Profile </label>
								<div class="col-xs-7">
									<input type="file" class="form-control clearfix" name="teacherprofile"
										id="teaprofile"> <input type="button"
										id="document1btn" name="profile" class="downloadDoc"
										value="Download" /> <span id="downloadProfileTitle">
										downloadProfile</span> <span
										style="font-size: 20px; color: red; cursor: pointer;"
										id="deleteProfile"> x</span>
								</div>
							</div>

							
							 <span id="fileUploaddynmic"
								style="text-decoration: underline; color: #2f91c1; cursor: pointer; font-family: Roboto Light; font-weight: bold; margin-left: 45%;">Add
								More Attachments</span>


							<div class="form-group clearfix fileattachmentDiv" id="fileattachment1Div"
								style="display: none;">
								    <label for="inputPassword"
									class="control-label col-xs-5" id="fileattachment1label">File
									Attachment :</label>
								<div class="col-xs-8">
									<input type="file" class="form-control clearfix" id="filaattachment1"
										name="filaattachment1"
										style="height: 35px !important; display: none;"> <input
										type="button" id="document4btn" name="profile"
										class="downloadDoc" value="Download" style="display: none;" />
									<span id="downloadFileAttachment1Title" style="display: none;">
										Download File Attachment </span> <span
										style="font-size: 20px; color: red; cursor: pointer; display: none;"
										id="deleteFile1"> x</span>
								</div>
								 
							</div>

							<div class="form-group clearfix fileattachmentDiv" id="fileattachment2Div"
								style="display: none;">
								<label for="inputPassword" class="control-label col-xs-5"
									id="fileattachment2label">File Attachment :</label>
								<div class="col-xs-8">
									<input type="file" class="form-control clearfix" id="filaattachment2"
										name="filaattachment2"
										style="height: 35px !important; display: none;"> <input
										type="button" id="document5btn" name="filaattachment2"
										class="downloadDoc" value="Download" style="display: none;" />
									<span id="downloadFileAttachment2Title" style="display: none;">
										Download File Attachment </span> <span
										style="font-size: 20px; color: red; cursor: pointer; display: none;"
										id="deleteFile2"> x</span>
								</div>
								 
							</div>

							<div class="form-group clearfix fileattachmentDiv" id="fileattachment3Div"
								style="display: none;">
								<label for="inputPassword" class="control-label col-xs-5"
									id="fileattachment3label">File Attachment :</label>
								<div class="col-xs-8">
									<input type="file" class="form-control clearfix" id="filaattachment3"
										name="filaattachment3"
										style="height: 35px !important; display: none;"> <input
										type="button" id="document6btn" name="filaattachment3"
										class="downloadDoc" value="Download" style="display: none;" />
									<span id="downloadFileAttachment3Title" style="display: none;">
										Download File Attachment </span> <span
										style="font-size: 20px; color: red; cursor: pointer; display: none;"
										id="deleteFile3"> x</span>
								</div>
								 
							</div>

							<div class="form-group clearfix fileattachmentDiv" id="fileattachment4Div"
								style="display: none;">
								<label for="inputPassword" class="control-label col-xs-5"
									id="fileattachment4label">File Attachment :</label>
								<div class="col-xs-8">
									<input type="file" class="form-control clearfix" id="filaattachment4"
										name="filaattachment4"
										style="height: 35px !important; display: none;"> <input
										type="button" id="document7btn" name="filaattachment4"
										class="downloadDoc" value="Download" style="display: none;" />
									<span id="downloadFileAttachment4Title" style="display: none;">
										Download File Attachment </span> <span
										style="font-size: 20px; color: red; cursor: pointer; display: none;"
										id="deleteFile4"> x</span>
								</div>
								 
							</div>

							<div class="form-group clearfix fileattachmentDiv" id="fileattachment5Div"
								style="display: none;">
								<label for="inputPassword" class="control-label col-xs-5"
									id="fileattachment5label">File Attachment :</label>
								<div class="col-xs-8">
									<input type="file" class="form-control clearfix" id="filaattachment5"
										name="filaattachment5"
										style="height: 35px !important; display: none;"> <input
										type="button" id="document8btn" name="filaattachment5"
										class="downloadDoc" value="Download" style="display: none;" />
									<span id="downloadFileAttachment5Title" style="display: none;">
										Download File Attachment </span> <span
										style="font-size: 20px; color: red; cursor: pointer; display: none;"
										id="deleteFile5"> x</span>
								</div>
								 
							</div>

						</div>

						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"> Date of
									Birth<font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<input type="text" readonly="readonly" name="dateofbirth"
										id="dateofbirthid" placeholder="Click Here"
										class="form-control clearfix"
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="dateofbirth"/></logic:present>" />
								</div>
							</div>
							 



							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Blood
									Group</label>
								<div class="col-xs-7">
									<select name="blood" id="bloodId" class="form-control clearfix bloodId">

										<option
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="blood" /></logic:present>">
											<logic:present name="teacherdetails">
												<bean:write name="teacherdetails" property="blood" />
											</logic:present>
										</option>
										<option value="A+ve">A+ve</option>
										<option value="O+ve">O+ve</option>
										<option value="B+ve">B+ve</option>
										<option value="AB+ve">AB+ve</option>
										<option value="A-ve">A-ve</option>
										<option value="O-ve">O-ve</option>
										<option value="B-ve">B-ve</option>
										<option value="AB-ve">AB-ve</option>
									</select>
								</div>
							</div>
							 
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Email ID<!-- <font color="red">*</font> -->
								</label>
								<div class="col-xs-7">
									<input type="text" name="teacheremail" id="teacherEmail"
										maxlength="45" class="form-control clearfix" placeholder="Email ID"
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="teacheremail"/></logic:present>" />
								</div>
							</div>
							 

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Account
									Number </label>
								<div class="col-xs-7">
									<input type="text" name="accountNumber" id="accountNumber"
										maxlength="19" class="form-control clearfix"
										placeholder="Account Number"
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="accountNumber"/></logic:present>" />
								</div>
							</div>
							 

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Aadhaar
									Number
								</label>
								<div class="col-xs-7">
									<input type="text" name="aadhaarnumber" id="aadhaarnumber"
										maxlength="12" class="form-control clearfix" placeholder=""
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="aadhaarnumber"/></logic:present>" />
								</div>
							</div>
							 
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Marital
									Status <font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<select name="maritalstatus" id="maritalstatus"
										class="form-control clearfix">
										<option value="">----------Select----------</option>
										<option value="Married">Married</option>
										<option value="Single">Single</option>

									</select>
								</div>
							</div>
							 
							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"> Image</label>
								<div class="col-xs-7">
									<input type="file" class="form-control clearfix" id="teaImageId"	name="teacherImage"> <input type="button"
										id="document3btn" name="teaImageId" class="downloadDoc"	value="Download" /> 
										<span id="downloadTeacherImage" style="margin: 0px -4px;">downloadTeacherImage</span> 
										<span style="font-size: 20px; color: red; cursor: pointer;"	id="deleteTeacherImage">x</span>
								</div>
							</div>
							
							 

							<div class="form-group clearfix">

								<div class="col-xs-7">
									<img id="imagePreview" src="#" alt="image" width="80px"
										height="70px" style="margin-left: 211px;"/>
								</div>
							</div>




						</div>
					</div>
				</div>

			</div>



			<div class="panel panel-primary" style="padding-bottom: 0px; margin-bottom: 5px;">
				<div class="panel-heading" role="tab" id="headingThree" style="margin-bottom: 0px !important;">
					
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseThree"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseThree"><h4 class="panel-title"> <i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Contact Details
						</h4></a>
					
				</div>
				<div id="collapseThree" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingThree">
					<div class="panel-body" style="padding: 10px;">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Father's
									Name
								</label>
								<div class="col-xs-7">
									<input type="text" class="form-control clearfix" id="fathername"
										name="fathername" placeholder="Father's Name"
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="fathername"/></logic:present>">
								</div>
							</div>
							 
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Mother's
									Name
								</label>
								<div class="col-xs-7">
									<input type="text" class="form-control clearfix" id="mothername"
										name="mothername" placeholder="Mother's Name"
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="mothername"/></logic:present>">
								</div>
							</div>
							 

							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Spouse
									Name</label>
								<div class="col-xs-7">
									<input type="text" class="form-control clearfix" id="spousename"
										name="spousename" onkeypress="HideError()" placeholder=""
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="spousename"/></logic:present>">
								</div>
							</div>
							 



							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Present
									Address<font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<textarea rows="3px" cols="3px" name="presentadd"
										id="presentadd" class="form-control clearfix" placeholder="Description"><logic:present name="teacherdetails"><bean:write name="teacherdetails" property="presentadd" />
										</logic:present>
									</textarea>
								</div>
							</div>

						</div>
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Mobile No

								</label>
								<div class="col-xs-7">
									<input type="text" class="form-control clearfix" id="fatherMobile"
										name="fatherMobile" placeholder="Father's Mobile No."
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="fatherMobile"/></logic:present>">
								</div>
							</div>
							 


							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Mobile No

								</label>
								<div class="col-xs-7">
									<input type="text" class="form-control clearfix" id="motherMobile"
										name="motherMobile" placeholder="Mother's Mobile No"
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="motherMobile"/></logic:present>">
								</div>
							</div>
							 
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;"> Spouse
									Mobile No</label>
								<div class="col-xs-7">
									<input type="text" class="form-control clearfix" id="spouseMobile"
										name="spouseMobile" onkeypress="HideError()" placeholder=""
										maxlength="11"
										value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="spouseMobile"/></logic:present>">
								</div>
							</div>
							 



							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Permanent
									Address<font color="red">*</font>
								</label>
								<div class="col-xs-7">
									<textarea cols="3px" rows="3px" name="permanentadd"
										id="permanentadd" class="form-control clearfix"
										placeholder="Description"><logic:present name="teacherdetails"><bean:write name="teacherdetails" property="permanentadd" /></logic:present>
									</textarea>
								</div>
							</div>



							<%-- <input type="hidden" name="teacherdetails" id="reportingToHidden" value='<bean:write name="teacherdetails" property="teacherId"/>' <bean:write name="reportingTo" property="teacherName"/>/> --%>
							<input type="hidden" name="reportingTo" id="reportingToHidden"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="reportingTo" /></logic:present>" />
							<input type="hidden" name="status" id="status"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="status" /></logic:present>" />
							<input type="hidden" name="hiddenprimary" id="hiddenprimary"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="primary" /></logic:present>" />
							<input type="hidden" name="hiddensecoundary"
								id="hiddensecoundary"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="secondary" /></logic:present>" />
							<input type="hidden" name="hiddendept" id="hiddendept"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="dept" /></logic:present>" />
							<input type="hidden" name="hiddendes" id="hiddendes"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="designation" /></logic:present>" />
							<input type="hidden" name="hiddenteatype" id="hiddenteatype"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="teachingtype" /></logic:present>" />
							<input type="hidden" name="hiddengender" id="hiddengender"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="gender" /></logic:present>" />
							<input type="hidden" name="hiddenblood" id="hiddenblood"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="blood" /></logic:present>" />

							<input type="hidden" name="defaultimage" id="hiddenimage"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="imgpath" /></logic:present>" />

							<input type="hidden" name="defaultidproof" id="hiddenidproof"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="idproof" /></logic:present>" />
							<input type="hidden" name="defaultprofile" id="hiddenprofile"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="profilepath" /></logic:present>" />
							<input type="hidden" name="teacherid" id="teacherid"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="teacherId" /></logic:present>" />
							<input type="hidden" name="hroleud" id="hroleid"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="role" /></logic:present>" />

							<input type="hidden" name="defaultattachment1"
								id="hiddenattachment1"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="document1" /></logic:present>" />
							<input type="hidden" name="defaultattachment2"
								id="hiddenattachment2"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="document2" /></logic:present>" />
							<input type="hidden" name="defaultattachment3"
								id="hiddenattachment3"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="document3" /></logic:present>" />
							<input type="hidden" name="defaultattachment4"
								id="hiddenattachment4"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="document4" /></logic:present>" />
							<input type="hidden" name="defaultattachment5"
								id="hiddenattachment5"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="document5" /></logic:present>" />

							<input type="hidden" name="student_studying" id="his_student_studying"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="is_student_studying" /></logic:present>" />
								
							<input type="hidden" name="student_admission"
								id="hstudent_admission_id"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="student_admission_id" /></logic:present>" />

							<input type="hidden" name="fatherMobile" id="fatherMobileHidden"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="fatherMobile" /></logic:present>" />

							<input type="hidden" name="fatherMobileHidden"
								id="fatherMobileHidden"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="fatherMobile" /></logic:present>" />

							<input type="hidden" name="motherMobileHidden"
								id="motherMobileHidden"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="motherMobile" /></logic:present>" />

							<input type="hidden" name="husertype" id="husertype"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="usertype" /></logic:present>" />

							<input type="hidden" name="hmaritalstatus" id="hmaritalstatus"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="maritalstatus" /></logic:present>" />
							
							<input type="hidden" name="hschoolname" id="hschoolname"
								value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="schoolNameId" /></logic:present>" />
						</div>


					</div>


				</div>
			</div>



			<div class="panel panel-primary" style="padding-bottom: 0px; margin-bottom: 5px;">
				<div class="panel-heading" role="tab" id="headingFour" style="margin-bottom: 0px !important;">
					
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseFour"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseFour"><h4 class="panel-title"> <i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Class Teacher Mapping
							Details
						</h4></a>
					

					<div class="navbar-right">

						<span id="save" onclick="add()" class="glyphicon glyphicon-plus" style="cursor: pointer; margin-top: -10px;"></span>

						<span id="delete" onclick="deleteClassMapping()" class="glyphicon glyphicon-trash" style="cursor: pointer; margin-top: -8px;"></span>

					</div>

				</div>


				<div id="collapseFour" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingFour">
					<html:hidden property="count" styleId="count" value="0" />
					<div class="panel-body" style="padding: 10px;">
						<div class="col-md-11" id="dynamicdiv"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							<div class="col-xs-12">
								<table id="allstudent" class="teacherclass table">
									<tr>
										<th align="center"><label>Check</label></th>
										<th align="center"><label>Class</label></th>
										<th align="center"><label>Section</label></th>
									</tr>

								</table>
							</div>

						</div>

					</div>


				</div>
			</div>



			<div class="panel panel-primary" style="padding-bottom: 0px; margin-bottom: 5px;">
				<div class="panel-heading" role="tab" id="headingFive" style="margin-bottom: 0px !important;">
					
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseFive"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseFive"><h4 class="panel-title"> <i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Subject Mapping
						</h4></a>
					

					<div class="navbar-right">

						<span id="addsubject" onclick="addSubject()" class="glyphicon glyphicon-plus" style="cursor: pointer; margin-top: -10px;"></span>
						<span id="deletesubject" onclick="deleteSubject()" class="glyphicon glyphicon-trash" style="cursor: pointer;    margin-top: -8px;"></span>

					</div>

				</div>


				<div id="collapseFive" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingFive">
					<html:hidden property="subjectcount" styleId="subjectcount"
						value="0" />
					<div class="panel-body" style="padding: 10px;">
						<div class="col-md-11" id="dynamicdiv"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							<div class="col-xs-12">
								<table id="addstaffsubject" class="teachersubject table">
									<tr>
										<th align="center" style="width: 70px;"><label>Check</label></th>
										<th align="center"><label>Class</label></th>
										<th align="center"><label>Subject</label></th>
									</tr>
								</table>
							</div>

						</div>

					</div>

				</div>
			</div>
			
		
					
						
			
			
			


	</form>

	</div>

</body>

</html>
