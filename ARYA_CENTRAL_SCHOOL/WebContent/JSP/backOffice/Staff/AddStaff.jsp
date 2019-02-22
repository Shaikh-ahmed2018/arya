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
<script type="text/javascript" src="JS/Admin/Latheef.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JS/backOffice/Staff/AddStaff.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/custome.css" rel="stylesheet">


<script type="text/javascript">
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	});
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
.buttons {
	vertical-align: 0px;
}
</style>
<style>
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

.own-panel {
	padding: 10px 0px;
}

form .panel.panel-primary {
    padding-bottom: 0px!important;
}

</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 0px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span>New Staff</span>
		</p>


		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>


		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>



		<form id="teacherform" action="teacherregistration.html?method=teacherregister"
			enctype="multipart/form-data" method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion2"
								href="#collapseOne" style="color: #767676"> <h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Staff
								Details
							</h4></a>
						

						<div class="navbar-right">

							<span id="save" class="buttons" style="cursor: pointer">Save</span>

							<span id="back" class="buttons"> Back</a></span>

						</div>

					</div>

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" id="txtstyle">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Staff
										Id <font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="regno"
											id="regno" onkeypress="HideError()" placeholder=""
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="regsitrationNo"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">First
										Name <font color="red">*</font>
									</label>
									<div class="col-xs-7">

										<input type="text" name="tfastname" id="fname"
											class="form-control" placeholder="" maxlength="25"
											onkeypress="HideError()"
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="tfastname"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Department
										<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select name="department" id="department"
											onkeypress="HideError()" class="form-control">
											<option value=" ">----------Select----------</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Date of
										Joining <label style="color: red;"></label>
									</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" name="joiningdate"
											id="joindateid" class="form-control" onkeypress="HideError()"
											placeholder=""
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="joiningdate"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">UserType
										<!-- <font color="red">*</font> -->
									</label>
									<div class="col-xs-7">
										<select name="usertype" id="usertype" class="form-control"
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
										style="text-align: right; line-height: 35px;">Reporting
										To <!-- <font color="red">*</font> -->
									</label>
									<div class="col-xs-7">
										<select name="reportingTo" id="reportingTo"
											class="form-control" onkeypress="HideError()">
											<option value=" ">----------Select----------</option>
											<logic:present name="reportingToList">
												<logic:iterate id="reportingTo" name="reportingToList">
													<option
														value='<bean:write name="reportingTo" property="teacherId"/>'>
														<bean:write name="reportingTo" property="teacherName" />
													</option>
												</logic:iterate>
											</logic:present>
										</select> <input type="hidden" name="StudentAdmissionNumber"
											id="teacherId" value="" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Is
										Student Studying <font color="red">*</font>
									</label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
										<label> <input type="radio" class="radio-inline"
											name="is_student_studying" value="Y"
											style="margin-bottom: 4px;" />&nbsp;Yes&nbsp;&nbsp;&nbsp;
										</label> <label><input type="radio" class="radio-inline"
											name="is_student_studying" value="N" checked="checked"
											style="margin-bottom: 4px;" />&nbsp;No</label>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"
										id="student_admission_label">Admission No <label
										style="color: red;">*</label></label>

									<div class="col-xs-7">
										<select name="student_admission_id" id="student_admission_id"
											class="form-control" onkeypress="HideError()">
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
											id="teacherId" value="" />
									</div>
								</div>
							</div>
							<div class="col-md-6" id="txtstyle">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										Abbreviate Id <!-- <font color="red">*</font> -->
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="abbreviate"
											id="abbreviate" onkeypress="HideError()" placeholder=""
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="regsitrationNo"/></logic:present>" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Last
										Name
									</label>
									<div class="col-xs-7">
										<input type="text" name="tlastname" id="lname" maxlength="12"
											onkeypress="HideError()" class="form-control" placeholder=""
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="tlastname"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Designation
										<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select name="designation" id="designation"
											onkeypress="HideError()" class="form-control">
											<option value=" ">----------Select----------</option>
										</select>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Staff
										Type  <font color="red">*</font> 
									</label>
									<div class="col-xs-7">
										<select name="teachingtype" id="teachingtype"
											class="form-control" onkeypress="HideError()">
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
										style="text-align: right; line-height: 35px;">Role <!-- <label
										style="color: red;">*</label> --></label>
									<div class="col-xs-7">
										<select name="role" id="roleId" class="form-control"
											onkeypress="HideError()">
											<option value=" ">----------Select----------</option>
											<logic:present name="RolePermission" scope="request">
												<logic:iterate id="role" name="RolePermission" >
													<option
														value='<bean:write name="role" property="roleCode"/>'><bean:write name="role" property="roleName" /></option>
												</logic:iterate>
											</logic:present>
										</select> <input type="hidden" name="roleId" id="selectedRole" value="" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">User
										Name <!-- <font color="red">*</font> -->
									</label>
									<div class="col-xs-7">
										<input type="text" name="username" id="uname"
											onkeypress="HideError()" maxlength="25" placeholder=""
											class="form-control"
											value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="username"/></logic:present>" />
									</div>
								</div>
								
								
								
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name
										<font color="red">*</font>
									</label>
									<div class="col-xs-7">
										<select name="schoolName" id="schoolName"
											onkeypress="HideError()" class="form-control">
											<option value=" ">----------Select----------</option>
										</select>
									</div>
								</div>
								

								<div class="form-group clearfix" style="display: none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> </label>

								</div>
								 


								<div class="form-group clearfix" style="display: none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student
										Name </label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
										<input type="text" name="studentName" id="studentName"
											class="form-control" readonly="readonly" />
									</div>
								</div>
							</div>


						</div>
					</div>

					<div class="panel panel-primary" style="margin-top: 5px;">
						<div class="panel-heading" role="tab" id="headingTwo">
							
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseTwo"
									style="color: #767676" aria-expanded="false"
									aria-controls="collapseTwo"> <h4 class="panel-title"><i
									class="glyphicon glyphicon-menu-hamburger"></i>
									&nbsp;&nbsp;Personal Details
								</h4></a>
							
						</div>
						<div id="collapseTwo" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTwo">
							<div class="panel-body own-panel">
								<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">

										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Gender
											<!-- <font color="red">*</font> -->
										</label>
										<div class="col-xs-7">
											<select name="gender" id="gender" onkeypress="HideError()"
												class="form-control">
												<option value=" ">----------Select----------</option>
												<option value="MALE">Male</option>
												<option value="FEMALE">Female</option>

											</select>
										</div>
									</div>
									

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Mobile
											No <font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<input type="text" name="teachermobno" id="mnumber"
												onkeypress="HideError()" maxlength="11" placeholder=""
												class="form-control"
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="teachermobno"/></logic:present>" />
										</div>
									</div>

									

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Qualification</label>
										<div class="col-xs-7">
											<input type="text" name="tqualification" id="qualification"
												maxlength="12" onkeypress="HideError()" class="form-control"
												placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="tqualification"/></logic:present>" />
										</div>
									</div>
									


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Bank
											Name </label>
										<div class="col-xs-7">
											<input type="text" name="bankName" id="bankName"
												maxlength="45" class="form-control" placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="bankName"/></logic:present>" />
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Pan
											Number </label>


										<div class="col-xs-7">
											<input type="text" name="panNumber" id="panNumber"
												maxlength="45" class="form-control" placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="panNumber"/></logic:present>" />
										</div>
									</div>
									







									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Id
											Proof</label>
										<div class="col-xs-7">
											<input type="file" class="form-control" name="idproof"
												id="idproof"><span
												style="font-size: 20px; color: red; cursor: pointer; position: absolute; right: 0; top: 1px;"
												id="deleteFileIdProof"> x</span>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Profile
										</label>
										<div class="col-xs-7">
											<input type="file" class="form-control" name="teacherprofile"
												id="teaprofile"><span
												style="font-size: 20px; color: red; cursor: pointer; position: absolute; right: 0; top: 1px;"
												id="deleteFileProfile"> x</span>
										</div>
									</div>

									 <span id="fileUploaddynmic"
										style="text-decoration: underline; color: #2f91c1; cursor: pointer; font-family: Roboto Light; font-weight: bold; margin-left: 45%;">Add
										More Attachments</span>
									<div class="form-group clearfix" id="fileattachment1Div"
										style="display: none;">
										 <label for="inputPassword"
											class="control-label col-xs-5" id="fileattachment1label"
											style="text-align: right; line-height: 35px;">File
											Attachment</label>
										<div class="col-xs-7">
											<input type="file" class="form-control" id="filaattachment1"
												name="filaattachment1"><span
												style="font-size: 20px; color: red; cursor: pointer; position: absolute; right: 0; top: 1px; display: none;"
												id="deleteFile1"> x</span> <input type="button"
												id="document4btn" name="profile" class="downloadDoc"
												value="Download" style="display: none;" /> <span
												id="downloadFileAttachment1Title" style="display: none;">
												downloadFileAttachment1 </span>
										</div>
										
									</div>

									<div class="form-group clearfix" id="fileattachment2Div"
										style="display: none;">
										<label for="inputPassword" class="control-label col-xs-5"
											id="fileattachment2label"
											style="text-align: right; line-height: 35px;">File
											Attachment</label>
										<div class="col-xs-7">
											<input type="file" class="form-control" id="filaattachment2"
												name="filaattachment2"><span
												style="font-size: 20px; color: red; cursor: pointer; display: none; position: absolute; right: 0; top: 1px;"
												id="deleteFile2"> x</span> <input type="button"
												id="document5btn" name="filaattachment2" class="downloadDoc"
												value="Download" style="display: none;" /> <span
												id="downloadFileAttachment2Title" style="display: none;">
												downloadFileAttachment2</span>
										</div>
										
									</div>

									<div class="form-group clearfix" id="fileattachment3Div"
										style="display: none;">
										<label for="inputPassword" class="control-label col-xs-5"
											id="fileattachment3label"
											style="text-align: right; line-height: 35px;">File
											Attachment </label>
										<div class="col-xs-7">
											<input type="file" class="form-control" id="filaattachment3"
												name="filaattachment3"> <span
												style="font-size: 20px; color: red; cursor: pointer; display: none; position: absolute; right: 0; top: 1px;"
												id="deleteFile3"> x</span> <input type="button"
												id="document6btn" name="filaattachment2" class="downloadDoc"
												value="Download" style="display: none;" /> <span
												id="downloadFileAttachment3Title" style="display: none;">
												downloadFileAttachment3 </span>
										</div>
										
									</div>

									<div class="form-group clearfix" id="fileattachment4Div"
										style="display: none;">
										<label for="inputPassword" class="control-label col-xs-5"
											id="fileattachment4label"
											style="text-align: right; line-height: 35px;">File
											Attachment</label>
										<div class="col-xs-7">
											<input type="file" class="form-control" id="filaattachment4"
												name="filaattachment4"> <span
												style="font-size: 20px; color: red; cursor: pointer; display: none; position: absolute; right: 0; top: 1px;"
												id="deleteFile4"> x</span> <input type="button"
												id="document7btn" name="filaattachment4" class="downloadDoc"
												value="Download" style="display: none;" /> <span
												id="downloadFileAttachment4Title" style="display: none;">
												downloadFileAttachment4 </span>
										</div>
										
									</div>

									<div class="form-group clearfix" id="fileattachment5Div"
										style="display: none;">
										<label for="inputPassword" class="control-label col-xs-5"
											id="fileattachment5label"
											style="text-align: right; line-height: 35px;">File
											Attachment</label>
										<div class="col-xs-7">
											<input type="file" class="form-control" id="filaattachment5"
												name="filaattachment5"><span
												style="font-size: 20px; color: red; cursor: pointer; display: none; position: absolute; right: 0; top: 1px;"
												id="deleteFile5"> x</span> <input type="button"
												id="document8btn" name="filaattachment5" class="downloadDoc"
												value="Download" style="display: none;" /> <span
												id="downloadFileAttachment5Title" style="display: none;">
												downloadFileAttachment5 </span>
										</div>
										
									</div>






								</div>







								<div class="col-md-6"
									style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;"> Date
											of Birth <font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<input type="text" readonly="readonly"
												onkeypress="HideError()" name="dateofbirth"
												id="dateofbirthid" placeholder="" class="form-control"
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="dateofbirth"/></logic:present>" />
										</div>
									</div>
									



									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Blood
											Group </label>
										<div class="col-xs-7">
											<select name="blood" id="bloodId" class="form-control">
												<option value="">----------Select----------</option>
												<option value="A+ve">A+ve</option>
												<option value="A-ve">A-ve</option>
												<option value="A1+ve">A1+ve</option>
												<option value="A1-ve">A1-ve</option>
												<option value="A2+ve">A2+ve</option>
												<option value="A2-ve">A2-ve</option>
												<option value="A1B+ve">A1B+ve</option>
												<option value="A1B-ve">A1B-ve</option>
												<option value="AB+ve">AB+ve</option>
												<option value="AB-ve">AB-ve</option>
												<option value="AB1+ve">AB1+ve</option>
												<option value="AB1-ve">AB1-ve</option>
												<option value="A+B+ve">A+B+ve</option>
												<option value="B+ve">B+ve</option>
												<option value="B-ve">B-ve</option>
												<option value="O+ve">O+ve</option>
												<option value="O-ve">O-ve</option>
											</select>
										</div>
									</div>
									


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Email
											ID <!-- <font color="red">*</font> -->
										</label>
										<div class="col-xs-7">
											<input type="text" name="teacheremail" id="teacherEmail"
												maxlength="45" class="form-control" placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="teacheremail"/></logic:present>" />
										</div>
									</div>
									

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Account
											Number </label>
										<div class="col-xs-7">
											<input type="text" name="accountNumber" id="accountNumber"
												maxlength="19" class="form-control" placeholder=""
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
												maxlength="12" class="form-control" placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="panNumber"/></logic:present>" />
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Marital
											Status <font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<select name="maritalstatus" id="maritalstatus"
												class="form-control">
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

											<input type="file" class="image" id="teaImageId"
												name="teacherImage"> <img src="images/camera.png"
												style="width: 16%; display: inline-block;" class="camera"
												id="teaImageId" name="teacherImage"> <img
												src="images/gallery.jpg" style="width: 16%;" class="gallery"
												id="teaImageId" name="teacherImage">
										</div>
									</div>

									




									<div class="form-group clearfix">

										<div class="col-xs-7">
											<img id="imagePreview" src="#" alt="image" width="80px"
												height="70px" style="margin-left: 62%;" /> <span
												style="font-size: 20px; color: red; cursor: pointer; position: absolute; right: 0; top: 1px;"
												id="deleteteacher_image"> x</span>
										</div>
									</div>

								</div>
							</div>
						</div>

					</div>



					<div class="panel panel-primary">
						<div class="panel-heading" role="tab" id="headingThree">
							
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
							<div class="panel-body own-panel">
								<div class="col-md-6"
									style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Father
											Name</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" id="fathername"
												name="fathername" onkeypress="HideError()" placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="fathername"/></logic:present>">
										</div>
									</div>
									

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Mother
											Name</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" id="mothername"
												name="mothername" onkeypress="HideError()" placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="mothername"/></logic:present>">
										</div>
									</div>
									

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Spouse
											Name</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" id="spousename"
												name="spousename" onkeypress="HideError()" placeholder=""
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="mothername"/></logic:present>">
										</div>
									</div>
									

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Present
											Address <font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<textarea rows="3px" cols="3px" name="presentadd"
												id="presentadd" onkeypress="HideError()"
												class="form-control" placeholder="">
												<logic:present name="teacherdetails">
													<bean:write name="teacherdetails" property="presentadd" />
												</logic:present>
											</textarea>
										</div>
									</div>

								</div>
								<div class="col-md-6"
									style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;"> Father
											Mobile No</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" id="fatherMobile"
												name="fatherMobile" onkeypress="HideError()" placeholder=""
												maxlength="11"
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="fathername"/></logic:present>">
										</div>
									</div>
									

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;"> Mother
											Mobile No</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" id="motherMobile"
												name="motherMobile" onkeypress="HideError()" placeholder=""
												maxlength="11"
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="fathername"/></logic:present>">
										</div>
									</div>
									

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;"> Spouse
											Mobile No</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" id="spouseMobile"
												name="spouseMobile" onkeypress="HideError()" placeholder=""
												maxlength="11"
												value="<logic:present name="teacherdetails" ><bean:write name="teacherdetails" property="fathername"/></logic:present>">
										</div>
									</div>
									

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px; padding-left: 0px;">Permanent
											Address <font color="red">*</font>
										</label>
										<div class="col-xs-7">
											<textarea rows="3px" cols="3px" name="permanentadd"
												id="permanentadd" class="form-control"
												onkeypress="HideError()" placeholder="">
												<logic:present name="teacherdetails">
													<bean:write name="teacherdetails" property="permanentadd" />
												</logic:present>
											</textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


					<div class="panel panel-primary">
						<div class="panel-heading" role="tab" id="headingFour">
							
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseFour"
									style="color: #767676" aria-expanded="false"
									aria-controls="collapseFour"> <h4 class="panel-title"><i
									class="glyphicon glyphicon-menu-hamburger"></i>
									&nbsp;&nbsp;Class Teacher Mapping Details
								</h4></a>
							

							<div class="navbar-right" style="margin: -32px -17px">

								<span id="save" onclick="add()" class="glyphicon glyphicon-plus"
									style="margin-top: 23px"></span> <span id="delete"
									onclick="deleteClassMapping()"
									class="glyphicon glyphicon-trash" style="margin-top: 23px"></span>
							</div>

						</div>


						<div id="collapseFour" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingFour">
							<html:hidden property="count" styleId="count" value="0" />
							<div class="panel-body own-panel">
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



					<div class="panel panel-primary">
						<div class="panel-heading" role="tab" id="headingFive">
							
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseFive"
									style="color: #767676" aria-expanded="false"
									aria-controls="collapseFive"> <h4 class="panel-title"><i
									class="glyphicon glyphicon-menu-hamburger"></i>
									&nbsp;&nbsp;Subject
								</h4></a>
							

							<div class="navbar-right" style="margin: -32px -17px">

								<span id="addsubject" onclick="addSubject()"
									class="glyphicon glyphicon-plus" style="margin-top: 23px"></span>

								<span id="deletesubject" onclick="deleteSubject()"
									class="glyphicon glyphicon-trash" style="margin-top: 23px"></span>

							</div>

						</div>


						<div id="collapseFive" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingFour">
							<html:hidden property="subjectcount" styleId="subjectcount"
								value="0" />
							<div class="panel-body" style="padding: 10px;">
								<div class="col-md-11" id="dynamicdiv"
									style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
									<div class="col-xs-12">
										<table id="addstaffsubject" class="teachersubject table">
											 <tr>
												<th align="center" style="width: 70px"><label>Check</label></th>
												<th align="center"><label>Class</label></th>
												<th align="center"><label>Subject</label></th>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="panel panel-primary">
						<div class="panel-heading" role="tab" id="headingsix">
							
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseSix"
									style="color: #767676" aria-expanded="false"
									aria-controls="collapseFive"><h4 class="panel-title"> <i
									class="glyphicon glyphicon-menu-hamburger"></i>
									&nbsp;&nbsp;Leave Types
								</h4></a>
							

							<div class="navbar-right" style="margin: -32px -17px">

								<span id="addleave" onclick="addLeave()"
									class="glyphicon glyphicon-plus" style="margin-top: 23px"></span>

								<span id="deleteLeave" onclick="deleteLeave()"
									class="glyphicon glyphicon-trash" style="margin-top: 23px"></span>

							</div>

						</div>


						<div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
							<html:hidden property="leavecount" styleId="leavecount" value="0" />
							<div class="panel-body " style="padding:10px;">
								<div class="col-md-11" id="dynamicdiv"
									style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
									<div class="col-xs-12">
										<table id="addTeacherLeave" class="teacherLeave table" border="1">
											<tr>
												<th align="center" style="width: 70px"><label>Check</label></th>
												<th align="center"><label>Leave Types</label></th>
												<th align="center"><label>Days</label></th>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					
					
					
				</div>
			</div>
		</form>

		<!-- </form> -->
	</div>

</body>

</html>
