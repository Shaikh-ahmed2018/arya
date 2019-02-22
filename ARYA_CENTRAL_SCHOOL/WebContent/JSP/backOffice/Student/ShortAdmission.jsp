<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Student/StudentRegistration.js"></script>
<style>
.save:hover {
	cursor: pointer;
}
</style>
<style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Student Registration</span>
		</p>
		<center>
			<logic:present name="successMessage" scope="request">

				<div class="successmessagediv" >
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
									name="successMessage" scope="request" />
						</span></a>
					</div>
				</div>

			</logic:present>

			<logic:present name="errorMessage" scope="request">

				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
									name="errorMessage" scope="request" />
						</span></a>
					</div>

				</div>

			</logic:present>

			<logic:present name="duplicateMessage" scope="request">

				<div class="successmessagediv">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
									name="duplicateMessage" scope="request" />
						</span></a>
					</div>

				</div>

			</logic:present>

			<div class="errormessagediv1" style="display: none;">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips1"></span></a>
				</div>
			</div>

		</center>
		<center>
			<!-- <div class="successmessagediv" style="display: none;">
				<div class="message-item">
					Warning <a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div> -->
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>

		</center>
		<form action="studentRegistration.html?method=saveStudentRegistration"
			enctype="multipart/form-data" id="formstudent" method="post" >
			
			<input type="hidden" id="studentid" name="studentId"
				value="<logic:present name="studentSearchList" property="studentId"><bean:write name="studentSearchList" property="studentId" />
													</logic:present>" />


			<input type="hidden" id="parenthiddenId" name="parenthiddenId"
				value="<logic:present name="studentSearchList" property="parentId"><bean:write name="studentSearchList" property="parentId" />
													</logic:present>">


			<input type="hidden" id="acchiddenId"
				value="<logic:present name="studentSearchList" property="academicYearId"><bean:write name="studentSearchList" property="academicYearId" />
													</logic:present>">

			<input type="hidden" id="streamhiddenId" 
				value="<logic:present name="studentSearchList" property="category"><bean:write name="studentSearchList" property="category" />
													</logic:present>">

			<input type="hidden" id="classhiddenid"
				value="<logic:present name="studentSearchList" property="studClassId"><bean:write name="studentSearchList" property="studClassId" />
													</logic:present>">
			<input type="hidden" id="sectionhiddenid"
				value="<logic:present name="studentSearchList" property="studSectionId"><bean:write name="studentSearchList" property="studSectionId" />
													</logic:present>">

			<input type="hidden" id="quotahiddenid" name="enquiryId"
				value="<logic:present name="studentSearchList" property="studentquotaname"><bean:write name="studentSearchList" property="studentquotaname" />
													</logic:present>">

			<input type="hidden" id="rtehiddenid"
				value="<logic:present name="studentSearchList" property="rte"><bean:write name="studentSearchList" property="rte" />
													</logic:present>">
			<input type="hidden" id="hostelhiddenid"
				value="<logic:present name="studentSearchList" property="hostel"><bean:write name="studentSearchList" property="hostel" />
													</logic:present>">
			<input type="hidden" id="concessionaplicablehidden"
				value="<logic:present name="studentSearchList" property="isconcession"><bean:write name="studentSearchList" property="isconcession" />
													</logic:present>">
			<input type="hidden" id="concessionhiddenid"
				value="<logic:present name="studentSearchList" property="scholarShip"><bean:write name="studentSearchList" property="scholarShip" />
													</logic:present>">
			<input type="hidden" id="transporthiddenid"
				value="<logic:present name="studentSearchList" property="transport"><bean:write name="studentSearchList" property="transport" />
													</logic:present>">

			<input type="hidden" id="transportcategoryhiddenid"
				value="<logic:present name="studentSearchList" property="transporttypeId"><bean:write name="studentSearchList" property="transporttypeId" />
													</logic:present>">
			<input type="hidden" id="transportlocationhiddenid"
				value="<logic:present name="studentSearchList" property="transportlocationId"><bean:write name="studentSearchList" property="transportlocationId" />
													</logic:present>">

			<input type="hidden" id="typecollectfeehidden"
				value="<logic:present name="studentSearchList" property="transportcollectType"><bean:write name="studentSearchList" property="transportcollectType" />
													</logic:present>">
			<input type="hidden" id="photohiddenid" name="previousImage"
				value="<logic:present name="studentSearchList" property="imageFileName"><bean:write name="studentSearchList" property="imageFileName" />
													</logic:present>">
			<input type="hidden" id="genderhiddenid"
				value="<logic:present name="studentSearchList" property="gender"><bean:write name="studentSearchList" property="gender" />
													</logic:present>">

			<input type="hidden" id="bloodhiddenid"
				value="<logic:present name="studentSearchList" property="bloodGroup"><bean:write name="studentSearchList" property="bloodGroup" />
													</logic:present>">

			<input type="hidden" id="physicallychallengedhiddenid"
				value="<logic:present name="studentSearchList" property="physicallyChallenged"><bean:write name="studentSearchList" property="physicallyChallenged" />
													</logic:present>">

			<input type="hidden" id="physicallychallengeddescriptionhiddenid"
				value="<logic:present name="studentSearchList" property="physicalchalreason"><bean:write name="studentSearchList" property="physicalchalreason" />
													</logic:present>">

			<input type="hidden" id="addresshiddenid"
				value="<logic:present name="studentSearchList" property="address"><bean:write name="studentSearchList" property="address" />
													</logic:present>">

			<input type="hidden" id="studentStatushiddentid"
				value="<logic:present name="studentSearchList" property="studentStatus"><bean:write name="studentSearchList" property="studentStatus" />
													</logic:present>">

			<input type="hidden" id="selected_Primary_hiddenId"
				value="<logic:present name="studentSearchList" property="primaryPerson"><bean:write name="studentSearchList" property="primaryPerson" />
													</logic:present>" />

			<input type="hidden" id="birthcertificatehiddenid" name="previousBirthCer"
				value="<logic:present name="studentSearchList" property="birthcertificate"><bean:write name="studentSearchList" property="birthcertificate" />
													</logic:present>">

			<input type="hidden" id="transfercertificatehiddenid" name="previousTransferCer"
				value="<logic:present name="studentSearchList" property="transferfile"><bean:write name="studentSearchList" property="transferfile" />
													</logic:present>">

			<!-- chiru -->

			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"
								style="color: #767676; vertical-align: text-top;"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Student Registration
							</h4></a>
						

						<div class="navbar-right">

							<span class="buttons" id="save" data-toggle="tooltip" data-placement="bottom" title="Save">Save
							</span>&nbsp;
							
							
							 	<span class="buttons" id="back">Back</span>
							</a>
						</div>

					</div>

					

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
										<input type="text" name="studentFirstName"
											onkeypress="HideError()" id="studentFirstNameId"
											maxlength="25" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentFirstName"/></logic:present>' />
								
								
								
									</div>
								</div>
								<br />

						<!-- 		<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Admission
										No </label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="studentrollno" onkeypress="HideError()"
											id="studentrollno" onblur="checkRollnumber()" maxlength="25"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentAdmissionNo"></bean:write></logic:present>' />
									</div>
								</div>
								<br />  -->
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Admission
										Date </label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" name="dateofJoin"  onkeypress="HideError()"
											id="dateofJoinId" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="dateofJoin"></bean:write></logic:present>'/>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Stream</label>
									<div class="col-xs-7">
										<select class="form-control" name="category" onkeypress="HideError()" id="category">
											<option value="<logic:present name="studentSearchList" property="category"><bean:write name="studentSearchList" property="category" /></logic:present>"></option>
										</select>
									</div>
								</div>
								<br />
					<!-- 		<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Section</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" name="studSectionId"
											id="studSectionId">
											<option value=""></option>
										</select>
									</div>
								</div>
								<br />  -->	
					<!--			<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Grade</label>
									<div class="col-xs-7">
										<input type="text" name="grade" onkeypress="HideError()" id="gradeId" maxlength="2"
											class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="grade"></bean:write></logic:present>' />
									</div>
								</div>
								<br />  -->
					<!--			<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">EMIS
										Number</label>
									<div class="col-xs-7">
										<input class="form-control" type="text"  name="emisNo"
											id="emisNo" maxlength="20"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="emisNo"></bean:write></logic:present>'/>
									</div>
								</div>
								<br />  -->
						<!-- 		<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px; padding: 0;">Concession
										Applicable </label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
										<label ><input type="radio" class="radio-inline"
											name="cencession" class="cencession" id="cencessionY"
											value="Y" />&nbsp;Yes&nbsp;&nbsp;&nbsp;</label><label > <input
											type="radio" class="radio-inline" name="cencession" id="cencessionN"
											class="cencession" value="N" />&nbsp;No
										</label>
									</div>
								</div>

								<br />  -->
								<div class="form-group" id="cencessionlable">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Concession
									</label>
									<div class="col-xs-7">
										<select class="form-control" name="scholarShip"
											id="scholarShipId">
											<option value=""></option>

										</select>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Transport</label>
								<!-- 	<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
										<label class="radio-inline"><input type="radio"
											name="transport" id="transportIdY" class="transportId"
											value="Y" />Yes</label><label class="radio-inline"> <input
											type="radio" name="transport" id="transportIdN"
											class="transportId" value="N" />No
										</label>

									</div> -->
									
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
										<label ><input type="radio" class="radio-inline"
											name="transport" class="transport" id="transportIdY"
											value="Y" />&nbsp;Yes&nbsp;&nbsp;&nbsp;</label><label > <input
											type="radio" class="radio-inline" name="transport" id="transportIdN"
											class="transport" value="N" />&nbsp;No
										</label>
									</div>
								</div>
								<br />

								<div class="form-group" id="transportcategorylabel">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Transport
										Category </label>
									<div class="col-xs-7">
										<select name="transcategory" id="transcategory"
											class="form-control">
											<option value=""></option>


										</select>
									</div>
								</div>
								<br />
								<div class="form-group" id="transportlocationlabel">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">
										Transport Location </label>
									<div class="col-xs-7">
										<select name="translocation" id="translocation"
											class="form-control">
											<option value=""></option>

										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Last
										Name</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" maxlength="20"
											name="studentLastName" id="studentLastNameId" onkeypress="HideError()"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentLastName"></bean:write></logic:present>' />
									</div>
								</div>
								<br />
				<!--  				<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Application
										No </label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="applicationNo"
											id="applicationNoId" onkeypress="HideError()" maxlength="25"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="applicationNo"></bean:write></logic:present>' />
									</div>
								</div>
								<br /> -->
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Academic
										Year </label>
									<div class="col-xs-7">
										<select name="academicYear" onkeypress="HideError()" id="academicYear"
											class="form-control">
											<option value=" "></option>
										</select>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" name="studClassId"
											id="studClassId">
											<option value=""></option>
										</select>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Quota</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" name="studentquotaname"
											id="studentquotaname">
											
											<option selected="selected" value='<logic:present name="studentSearchList">
											<bean:write name="studentSearchList" property="emisNo"></bean:write></logic:present>'></option>
										</select>
									</div>
								</div>
								<br />

								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">RTE </label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
										<label >
										<input type="radio" class="radio-inline"  name="rte" id="rteId" value="Y" />&nbsp;Yes&nbsp;&nbsp;&nbsp;</label>
										
										<label><input type="radio" class="radio-inline"  name="rte" id="rteId" value="N" />&nbsp;No</label>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Hostel </label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
										<label ><input type="radio" class="radio-inline"
											name="hostel"  id="hostelId" value="Y" />&nbsp;Yes&nbsp;&nbsp;&nbsp;</label> <label>
											<input type="radio" class="radio-inline"
											name="hostel"  id="hostelId" value="N" />&nbsp;No</label>

									</div>
								</div>

								

								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Add
										Photo</label>
									<div class="col-xs-7">
										<input type="file" id="studentImageId1" name="studentImage"
											class="form-control" />
									</div>
								</div>
								<br />
								<div class="form-group">

									<div class="col-xs-7">
										<img id="imagePreview" src="#" alt="image" width="80px"
											height="70px" style="margin-left: 62%;" />
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
					


							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Age</label>
									<div class="col-xs-7">
										<input type="text" name="age"  id="ageId" readonly="readonly"
											class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="age"></bean:write></logic:present>' />
									</div>
								</div>
								<br />

								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Identification
										Marks</label>
									<div class="col-xs-7">
										<input class="form-control" type="text"
											name="identificationMarks" onkeypress="HideError()" id="identificationMarksId"
											maxlength="40"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="identificationMarks"></bean:write></logic:present>' />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Religion</label>
									<div class="col-xs-7">
										<input type="text" name="religion" id="religionId" onkeypress="HideError()"
											maxlength="20" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="religion"></bean:write></logic:present>' />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Medical
										History</label>
									<div class="col-xs-7">
										<input type="text" name="previousHistory"
											id="previousHistoryId" maxlength="20" class="form-control" onkeypress="HideError()"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="previousHistory"></bean:write></logic:present>' />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Physically
										Challenged </label>
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
										<label ><input type="radio" class="radio-inline"
											name="physicallyChallenged" id="physicallyChallengedIdY"
											value="YES" />&nbsp;Yes&nbsp;&nbsp;&nbsp;</label><label > <input
											type="radio" name="physicallyChallenged" class="radio-inline"
											id="physicallyChallengedIdN" value="NO" />&nbsp;No
										</label>
									</div>
								</div>
								<br /> 
								<div class="form-group" id="studentstatuslable">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Status</label>
									<div class="col-xs-7">
										<select name="studentstatus" id="studentStatusId"
											class="form-control">
											<option value=""></option>
											<option value="active">Active</option>
											<option value="inactive">InActive</option>
										</select>
									</div>
								</div>
								<br />

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
								&nbsp;&nbsp;Parent Information
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
										style="text-align: right; line-height: 35px;">Sibling
										Name</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" onchange="readyOnly()"
											name="searchTerm" id="SearchStudent"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentSibilingName"></bean:write></logic:present>' />
										<input type="hidden" name="studentSibilingIdInt" id="studentSibilingIdIntId" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentSibilingIdInt"></bean:write></logic:present>' />
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Sibling
										class</label>
									<div class="col-xs-7">
										<input type="text" name="sibilingClass" id="sibilingClassId"
											readonly="readonly" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="sibilingClassId"></bean:write></logic:present>'/>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Father
										Name </label>
									<div class="col-xs-7">
									
									<input type="hidden" name="parentId" id="parentId"/>
										<input type="text" name="fatherName" id="fatherNameId"  onkeypress="HideError()"
											maxlength="30" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatherName"></bean:write></logic:present>'/>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Mobile
										Number </label>
									<div class="col-xs-7">
										<input type="text" name="fatherMobileNo" id="fatherMobileNoId"  onkeypress="HideError()"
											onblur="fathervalidatePhoneNo()" maxlength="10"
											class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatherMobileNo"></bean:write></logic:present>'/>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Mother
										Name </label>
									<div class="col-xs-7">
										<input type="text" name="motherName" id="motherNameId"  onkeypress="HideError()"
											maxlength="30" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motherName"></bean:write></logic:present>'/>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Mobile
										Number </label>
									<div class="col-xs-7">
										<input type="text" name="motherMobileNo" id="motherMobileNoId"  onkeypress="HideError()"
											maxlength="10" onblur="validatePhoneNo()"
											class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motherMobileNo"></bean:write></logic:present>'/>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Guardian
										Name</label>
									<div class="col-xs-7">
										<input type="text" name="guardianName" id="gaurdianNameId"  onkeypress="HideError()"
											maxlength="30" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="guardianName"></bean:write></logic:present>'/>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Email Id</label>
									<div class="col-xs-7">
										<input type="text" maxlength="45" name="guardianemailId"
											id="guardianemailId" onblur="gaurdianvalidateEmail()"
											class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="guardianemailId"></bean:write></logic:present>'/>
									</div>
								</div>

								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Father
										Occupation </label>
									<div class="col-xs-7">
										<input type="text" name="fatheroccupation"
											id="fatheroccupationId" maxlength="10" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatheroccupation"></bean:write></logic:present>'/>
									</div>
								</div>
								
								<br />
							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Admission
										No</label>
									<div class="col-xs-7">
										<input type="text" name="studentRegNo" id="sibilingadminnoId"
										value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentSibilingRegNo"></bean:write></logic:present>'
											readonly="readonly" class="form-control" />
									</div>
								</div>
								<br />
								
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Primary
										Person </label>
									<div class="col-xs-7">
										<select name="primaryPersondisables"  id="primarypersonId" onkeypress="HideError()"
											class="form-control">
											<option value=""></option>
											<option value="father">Father</option>
											<option value="mother">Mother</option>
											<option value="guardian">Guardian</option>
										</select>
									</div>
									<input type="hidden" id="hprymarycntperson" name="primaryPerson"/>
								</div>
								
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Qualification</label>
									<div class="col-xs-7">
										<input name="fatherQualification" id="fatherQualificationId" onkeypress="HideError()"
											maxlength="30" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatherQualification"></bean:write></logic:present>'/>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Email Id</label>
									<div class="col-xs-7">
										<input type="text" name="fatheremailId" id="fatheremailId"
											maxlength="45" onblur="fathervalidateEmail()"
											class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="fatheremailId"></bean:write></logic:present>'/>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Qualification</label>
									<div class="col-xs-7">
										<input type="text" name="motherQualification" onkeypress="HideError()"
											id="motherQualificationId" maxlength="30"
											class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motherQualification"></bean:write></logic:present>'/>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Email Id</label>
									<div class="col-xs-7">
										<input type="text" name="motheremailId" id="motheremailId" 
											maxlength="45" onblur="mothervalidateEmail()"
											class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motheremailId"></bean:write></logic:present>'/>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Mobile
										Number</label>
									<div class="col-xs-7">
										<input type="text" name="guardianMobileNo" onkeypress="HideError()" 
											id="guardianMobileNoId" maxlength="10"
											onblur="gaurdianvalidatePhoneNo()" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="guardianMobileNo"></bean:write></logic:present>'/>
									</div>
								</div>
								<%-- <br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Guardian
										Occupation </label>
									<div class="col-xs-7">
										<input type="text" name="quardianoccupation"
											id="quardianoccupationId" maxlength="10" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="quardianoccupation"></bean:write></logic:present>'/>
									</div>
								</div> --%>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Mother
										Occupation </label>
									<div class="col-xs-7">
										<input type="text" name="motheroccupation"
											id="motheroccupationId" maxlength="10" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="motheroccupation"></bean:write></logic:present>'/>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Address
									</label>
									<div class="col-xs-7">
										<textarea name="address" id="paddrs" onkeypress="HideError()" class="form-control"></textarea>
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
								&nbsp;&nbsp;Certificates Uploads
							</a>
						</h4>
					</div>
					<div id="collapsefour" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingfour">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Birth
										Certificate</label>
									<div class="col-xs-7">
									
										<input type="file" id="uploadBirth" name="birthFile" class="form-control" style="height: 20%;" />
										<input type="button" id="document1btn" name="profile" class="downloadDoc" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="deleteProfile">  x</span>
								
									</div>
								</div>
								<br />
							</div>
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Transfer Certificate</label>
									<div class="col-xs-7">
									
										<input type="file" id="uploadTransfer" name="transferFile" class="form-control" style="height: 20%;" />
										<input type="button" id="document2btn" name="idproof" class="downloadDoc" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="deleteIDProof">x</span>
								
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</div>
			</div>
			<!-- <button type="submit" class="btn btn-info col-md-offset-5">Savechanges</button>
				<button type="reset" class="btn btn-info">Clear</button> -->
		</form>
	</div>
</html>
