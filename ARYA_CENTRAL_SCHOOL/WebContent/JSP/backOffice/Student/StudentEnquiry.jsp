<!DOCTYPE html>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Admin/Latheef.js"></script>

<script type="text/javascript"
	src="JS/backOffice/Student/StudentEnquiry.js"></script>
<title>eCampus Pro</title>

<style>
#save:hover {
	cursor: pointer;
}

#trash:hover {
	cursor: pointer;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="col-md-8" id="div2">

			<p style="margin: 20px 0px;">
				<img src="images/addstu.png" />&nbsp;<span id="pageHeading">New
					Student Enquiry</span>
			</p>
		</div>
		<br /> <br />
		<center>
			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>

			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>
		</center>


		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Student Details
						</h4></a>
						

						<div class="navbar-right">
							<span class="buttons" id="save"
								data-toggle="tooltip" data-placement="bottom" title="Save">Save

							</span> <span id="back" class="buttons" >Back</span></a>
						</div>
					
				</div>

				<script>
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});
				</script>

				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">First
									Name </label>
								<div class="col-xs-7">
									<input type="text" name="enq_FirstName" class="form-control" onkeypress="HideError()"
										id="FirstNameId" maxlength="25"
										value='<logic:present name="EnquiryDetails" property="enq_fname"><bean:write name="EnquiryDetails" property="enq_fname" /></logic:present>' />
								</div>
							</div>
							<br />
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Class</label>
								<div class="col-xs-7">
									<td align="left" valign="text-top"><select
										name="enq_studClassId" id="studClassId" onkeypress="HideError()" class="form-control">
											<option value="">------Select--------</option>
									</select></td>
								</div>
							</div>

							<br />
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Application
									No </label>
								<div class="col-xs-7">
									<input type="text" maxlength="20" name="appno"
										class="form-control" id="appnoid" onkeypress="HideError()"
										value='<logic:present name="EnquiryDetails" property="enq_app_no"><bean:write name="EnquiryDetails" property="enq_app_no" /></logic:present>'
										onchange="appnovalidate()" />
								</div>
							</div>
							<br />
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Date Of
									Join </label>
								<div class="col-xs-7">
									<input type="text" readonly="readonly" name="enq_dateofJoin" onkeypress="HideError()"
										class="form-control" id="dateofJoinId"
										value='<logic:present name="EnquiryDetails" property="enq_doj"><bean:write name="EnquiryDetails" property="enq_doj" /></logic:present>'
										placeholder="Click Here" />
								</div>
							</div>

						</div>
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Last Name</label>
								<div class="col-xs-7">
									<input type="text" name="enq_LastName" id="LastNameId" onkeypress="HideError()"
										class="form-control" maxlength="20"
										value='<logic:present name="EnquiryDetails" property="enq_lname"><bean:write name="EnquiryDetails" property="enq_lname" /></logic:present>' />
								</div>
							</div>


							<br />
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Source</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="enq_source" onkeypress="HideError()"
										id="source" maxlength="12"
										value='<logic:present name="EnquiryDetails" property="enq_enquiredby"><bean:write name="EnquiryDetails" property="enq_enquiredby" /></logic:present>' />
								</div>
							</div>

							<br />
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Academic
									Year </label>
								<div class="col-xs-7">
									<select name="enq_academicYear" class="form-control" onkeypress="HideError()"
										id="academicYear">
										<option value=" ">----------Select----------</option>
									</select>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingTwo">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseTwo"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseTwo"> <i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Personal Information
						</a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Date Of
									Birth </label>
								<div class="col-xs-7">
									<input type="text" placeholder="Click Here" onkeypress="HideError()"
										class="form-control" readonly="readonly"
										name="enq_dateofBirth" id="dateofBirthId"
										onchange="ageCalculate();"
										value='<logic:present name="EnquiryDetails" property="enq_dob"><bean:write name="EnquiryDetails" property="enq_dob" /></logic:present>' />
								</div>
							</div>
							<br />
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Religion</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="enq_religion" onkeypress="HideError()"
										id="religionId" class="form-control"
										value='<logic:present name="EnquiryDetails" property="enq_religion"><bean:write name="EnquiryDetails" property="enq_religion" /></logic:present>' />

								</div>
							</div>
							<br />
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px; width: 34.333333%;">Physically
									Challenged </label>
								<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
									<label class="radio-inline"><input type="radio"
										name="enq_physicallyChallenged" id="physicallyChallengedId"
										value="YES" />Yes<br /> <input type="radio"
										name="enq_physicallyChallenged" id="physicallyChallengedId"
										value="NO" />No </label>
								</div>
							</div>
						</div>
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Age</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="enq_age" onkeypress="HideError()"
										id="ageId" maxlength="03" readonly="readonly"
										value='<logic:present name="EnquiryDetails" property="enq_age"><bean:write name="EnquiryDetails" property="enq_age" /></logic:present>' />
								</div>
							</div>


							<br />

							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Gender</label>
								<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
									<label class="radio-inline"><input type="radio"
										name="enq_gender" id="genderId" value="Boy" />Boy <br /> <input
										type="radio" name="enq_gender" id="genderId" value="Girl" />Girl
									</label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingThree">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseThree"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapseThree"> <i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Contact Information
						</a>
					</h4>
				</div>
				<div id="collapseThree" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingThree">
					<div class="panel-body">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Contact
									Type </label>
								<div class="col-xs-7">
									<select name="enq_contacttype" id="contacttypeId" onkeypress="HideError()"
										class="form-control">
										<option value="">------Select----------</option>
										<option value="father">Father</option>
										<option value="mother">Mother</option>
										<option value="guardian">Guardian</option>

									</select>
								</div>
							</div>
							<br />
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Mobile
									Number </label>
								<div class="col-xs-7">
									<input type="text" name="enq_MobileNo" id="MobileNoid"
										class="form-control" onblur="fathervalidatePhoneNo()"
										maxlength="11" onchange=validateMobileno() onkeypress="HideError()"
										value='<logic:present name="EnquiryDetails" property="enq_conPhno"><bean:write name="EnquiryDetails" property="enq_conPhno" /></logic:present>' />
								</div>
							</div>

							<br />
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Address </label>
								<div class="col-xs-7">
									<textarea name="address" id="paddrs" onkeypress="HideError()" class="form-control"></textarea>
								</div>
							</div>

						</div>
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">



							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Contact
									Name</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="enq_contactname"
										id="contactnameid" maxlength="12" onkeypress="HideError()"
										value='<logic:present name="EnquiryDetails"  property="enq_conname"><bean:write name="EnquiryDetails" property="enq_conname" /></logic:present>' />
								</div>
							</div>
							<br />
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Email Id</label>
								<div class="col-xs-7">
									<input type="text" class="form-control"
										name="enq_contactmailId" id="contactmailId" maxlength="25" onkeypress="HideError()"
										onblur="fathervalidateEmail()"
										value='<logic:present name="EnquiryDetails" property="enq_conemailId"><bean:write name="EnquiryDetails" property="enq_conemailId" /></logic:present>' />
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingfour">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapsefour"
							style="color: #767676" aria-expanded="false"
							aria-controls="collapsefour"> <i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Settings
						</a>
					</h4>
				</div>
				<div id="collapsefour" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingfour">
					<div class="panel-body">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Interaction
									Status </label>
								<div class="col-xs-7">
									<select name="interaction" id="interaction"
										class="form-control">
										<option value="">----------Select----------</option>
										<option value="completed">Completed</option>
										<option value="rejected">Rejected</option>
										<option value="visited">Visited</option>
										<option value="yettovisit">Yet To Visit</option>
									</select>
								</div>
							</div>
							<br />
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Admission
									Status </label>
								<div class="col-xs-7">
									<select class="form-control" name="admissionstatus"
										class="form-control" id="admissionstatus">
										<option value="">----------Select----------</option>
										<option value="completed">Completed</option>
										<option value="rejected">Rejected</option>
										<option value="visited">Visited</option>
										<option value="yettovisit">Yet To Visit</option>
									</select>
								</div>
							</div>
							<br />
							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Joining
									Status</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" maxlength="45"
										name="joiningId" id="joiningId" class="form-control"
										value='<logic:present name="EnquiryDetails" property="enq_join_status"><bean:write name="EnquiryDetails" property="enq_join_status" /></logic:present>' />
								</div>
							</div>
						</div>

						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

							<div class="form-group" id="completiondatelable">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Completion
									Date </label>
								<div class="col-xs-7">
									<input type="text" placeholder="Click Here" readonly="readonly"
										name="completiondate" id="completiondate" class="form-control"
										value='<logic:present name="EnquiryDetails" property="enq_int_date"><bean:write name="EnquiryDetails" property="enq_int_date" /></logic:present>' />
								</div>
							</div>
							<br />
							<div class="form-group" id="admissiondatelable">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Completion
									Date </label>
								<div class="col-xs-7">
									<input type="text" placeholder="Click Here" readonly="readonly"
										name="admissiondate" id="admissiondate" class="form-control"
										value='<logic:present name="EnquiryDetails" property="enq_adm_date"><bean:write name="EnquiryDetails" property="enq_adm_date" /></logic:present>' />
								</div>
							</div>

						</div>
					</div>
				</div>
				<input type="hidden" id="enquiryid"
					value='<logic:present name="EnquiryDetails" property="enq_Id"><bean:write name="EnquiryDetails" property="enq_Id" /></logic:present>' />
				<input type="hidden" id="hidclassname"
					value='<logic:present name="EnquiryDetails" property="enq_classId"><bean:write name="EnquiryDetails" property="enq_classId" /></logic:present>' />
				<input type="hidden" id="hidaccId"
					value='<logic:present name="EnquiryDetails" property="enq_accId"><bean:write name="EnquiryDetails" property="enq_accId" /></logic:present>' />
				<input type="hidden" id="hidgender"
					value='<logic:present name="EnquiryDetails" property="enq_gender"><bean:write name="EnquiryDetails" property="enq_gender" /></logic:present>' />
				<input type="hidden" id="hidphyhand"
					value='<logic:present name="EnquiryDetails" property="enq_phyhand"><bean:write name="EnquiryDetails" property="enq_phyhand" /></logic:present>' />
				<input type="hidden" id="hidcontype"
					value='<logic:present name="EnquiryDetails" property="enq_contype"><bean:write name="EnquiryDetails" property="enq_contype" /></logic:present>' />
				<input type="hidden" id="hidaddress"
					value='<logic:present name="EnquiryDetails" property="enq_address"><bean:write name="EnquiryDetails" property="enq_address" /></logic:present>' />

				<input type="hidden" id="hidint_status"
					value='<logic:present name="EnquiryDetails" property="enq_int_status"><bean:write name="EnquiryDetails" property="enq_int_status" /></logic:present>' />
				<input type="hidden" id="hidreg_status"
					value='<logic:present name="EnquiryDetails" property="enq_reg_status"><bean:write name="EnquiryDetails" property="enq_reg_status" /></logic:present>' />
				<input type="hidden" id="hidstatus"
					value='<logic:present name="EnquiryDetails" property="status"><bean:write name="EnquiryDetails" property="status" /></logic:present>' />

			</div>
		</div>
	</div>
</body>
</html>