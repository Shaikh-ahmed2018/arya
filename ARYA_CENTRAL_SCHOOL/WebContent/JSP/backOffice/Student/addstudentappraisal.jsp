<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
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
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>

<link href="CSS/Admin/Admission/StudentNew.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript"
	src="JS/backOffice/Student/AddStudentAppraisalReport.js"></script>
<style>
.form-group {
	margin-bottom: 5px;
}

.save:hover {
	cursor: pointer;
}

#individualstudenttable th:nth-child(2),th:nth-child(3) {
	text-align: center;
}

#individualstudenttable td:nth-child(2) {
	text-align: center;
	width: 20%;
}

#studenttable th:nth-child(2),th:nth-child(4),th:nth-child(5) {
	width: 20%;
}

#studenttable th:nth-child(2),th:nth-child(4),th:nth-child(5) {
	text-align: center;
}

#studenttable td:nth-child(4),td:nth-child(5) {
	text-align: center;
}

#allstudent tbody tr {
	position: relative;
}

.edit {
	position: absolute;
	right: 100px;
}

.delete {
	position: absolute;
	right: 40px;
}

fieldset {
	width: 512px;
	display: block;
	/*  margin-left: auto;
    margin-right: 2px; */
	margin-bottom: 5px;
	padding-bottom: 0.625em;
	padding-left: 7px;
	padding-right: 0px;
	border: 0.5px solid #ccc;
}

legend {
	display: inline-block;
	width: auto;
	padding: 0;
	margin-bottom: 0px;
	font-size: 16px;
	line-height: inherit;
	color: #333;
	border: 0;
}

.form-control[readonly] {
	background-color: #fff;
	opacity: 1;
}
</style>
<style>
.buttons {
	vertical-align: -5px;
}

.navbar-right {
	margin-top: 3px
}

.edit.buttons {
	padding: 4px;
	margin-top: -5px;
}

.delete.buttons {
	padding: 4px;
	margin-top: -5px
}

.form-control[readonly] {
	background-color: #fff;
	opacity: 1;
}

.panel-primary>.panel-heading {
	margin-bottom: 0px;
}

form .panel.panel-primary.panel-list {
	padding-bottom: 0px;
}

@media ( min-width :320px) and (max-width:767px) {
	br {
		display: none;
	}
}

.slideTab {
	padding: 10px;
	font-family: Roboto Medium;
	font-size: 14px;
	font-weight: lighter;
}

.add-on {
	position: absolute;
	top: 0px;
	right: 8px;
	height: 28px;
}

.bootstrap-datetimepicker-widget {
	z-index: 999999;
}

.abc {
	font-family: Open Sans Light;
	font-size: 11pt;
	color: #5d5d5d;
	margin-top: 10px;
}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" /><span id="pageHeading">Student
				Appraisal Action</span>
		</p>

		<div class="panel-body clearfix"
			style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
			<div id="admissionDialog" style="display: none">
				<div class="col-md-12">
					<div class="col-md-6 abc">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Action</label>
							<div class="col-xs-7">
								<select name="" class="form-control" id="actiontaken">
									<option value="">---------Select-------</option>
									<option value="Appraisal">Appraisal</option>
									<option value="PT meeting">PT meeting</option>
								</select>
							</div>
							
						</div>

						<div class="form-group clearfix">

							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Schedule Date</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" onkeypress="HideError()"
									name="entryDate" id="schedualdate" class="entryDates" >

							</div>
						</div>
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Meeting Date</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" onkeypress="HideError()"
									name="meetingdate" id="meetingDate" >

							</div>
						</div>


					</div>


					<div class="col-md-6 abc">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Recommented By</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" id="recommented" >

							</div>
						</div>
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Meeting With </label>
							<div class="col-xs-7">
								<select name="" class="form-control" id="meetingwith">
									<option value="">---------Select-------</option>
									<option value="Father">Father</option>
									<option value="Mother">Mother</option>
									<option value="Guardian">Guardian</option>
								</select>
							</div>
							
						</div>


						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Meeting On</label>
							<div class="col-xs-7">
								<div id="datetimepicker3" class="input-append DatePicker">
									<input type="text" data-format="hh:mm:ss" size="8"
										readonly="readonly" name="starttime" id="starttime"
										onchange="HideError()" class="form-control"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="stratTime"></bean:write></logic:present>' />
									<span class="add-on"> <img src="./images/time1.jpg"
										width="30" height="30" class="add-on" />
									</span>
								</div>

							</div>
						</div>


					</div>
					<div class="col-md-12 abc">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-2"
								style="text-align: left;"> Remark </label>
							<div class="col-xs-10" style="padding-left: 40px;">
								<textarea style="border: 1px solid #ccc; width: 100%"
									name="comment" id="remark" rows="3" ></textarea>

							</div>
						</div>
					</div>
				</div>
			</div>


			<div id="editDialog" style="display: none">
				<div class="col-md-12">
					<div class="col-md-6 abc">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Action</label>
							<div class="col-xs-7">
								<select name="" class="form-control" id="actiontaken1">
									<option value="">---------Select-------</option>
									<option value="Appraisal">Appraisal</option>
									<option value="PT meeting">PT meeting</option>
								</select>
							</div>
						</div>

						<div class="form-group clearfix">

							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Schedule Date</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" onkeypress="HideError()"
									name="entryDate" id="schedualdate1" class="entryDates" >

							</div>
						</div>
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Meeting Date</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" onkeypress="HideError()"
									name="meetingdate" id="meetingDate1" >

							</div>
						</div>


					</div>


					<div class="col-md-6 abc">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Recommented By</label>
							<div class="col-xs-7">
								<input type="text" class="form-control" id="recommented1">

							</div>
						</div>
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Meeting With </label>
							<div class="col-xs-7">
								<select name="" class="form-control" id="meetingwith1">
									<option value="Null">---------Select-------</option>
									<option value="Father">Father</option>
									<option value="Mother">Mother</option>
									<option value="Guardian">Guardian</option>
								</select>
							</div>
						</div>

<input type="hidden" id="hiddenid"/> 
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-lable col-xs-5"
								style="text-align: left;">Meeting On</label>
							<div class="col-xs-7">
								<div id="datetimepicker4" class="input-append DatePicker">
									<input type="text" data-format="hh:mm:ss" size="8"
										readonly="readonly" name="starttime" id="starttime1"
										onchange="HideError()" class="form-control"
										value='<logic:present name="masterDetails"><bean:write name="masterDetails" property="stratTime"></bean:write></logic:present>' />
									<span class="add-on"> <img src="./images/time1.jpg"
										width="30" height="30" class="add-on" />
									</span>
								</div>

							</div>
						</div>


					</div>
					<div class="col-md-12 abc">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-2"
								style="text-align: left;"> Remark </label>
							<div class="col-xs-10" style="padding-left: 40px;">
								<textarea style="border: 1px solid #ccc; width: 100%"
									name="comment" id="remark1" rows="3"></textarea>

							</div>
						</div>
					</div>
				</div>
			</div>



			<div id="deleteDialog" style="display: none">
				<p>Are You Sure to Delete?</p>
			</div>
		</div>

		<div class="errormessagediv1" style="display: none;" align="center">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1" style="width: 30%;"><span
					class="validateTips1"></span></a>
			</div>
		</div>


		<div class="errormessagediv" style="display: none;" align="center">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<div id="successmessages" class="successmessagediv"
			style="display: none;">
			<div class="message-item">
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>

		<!-- chiru -->

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"
							style="color: #767676; vertical-align: text-top;"><h4 class="panel-title"> <i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Student Appraisal Action
						</h4></a>
					

					<div class="navbar-right">
						<span class="buttons" id="back">Back</span>

					</div>
				</div>
				<logic:iterate id="studentSearchList" name="studentSearchList">
					<div id="collapseOne" class="panel-collapse collapse in "
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic
										Year</label>
									<div class="col-xs-7">
										<input type="text" name="academicYear" tabindex="1"
											onkeypress="HideError()" id="academicYear" maxlength="25"
											class="form-control" readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="academicYear"/></logic:present>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School
										Name</label>
									<div class="col-xs-7">
										<input type="text" name="schoolName" id="schoolName"
											maxlength="25" class="form-control" readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="location"/></logic:present>' />
									</div>
								</div>
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student
										Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentFullName" id="studentFullName"
											maxlength="25" class="form-control" readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentFullName"/></logic:present>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission
										No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="admissionNo"
											id="admissionNo" onchange="" maxlength="25"
											readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentAdmissionNo"></bean:write></logic:present>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Roll No</label>

									<div class="col-xs-7">
										<input type="text" name="studentRollNo" id="studentRollNo"
											maxlength="25" class="form-control" readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentrollno"/></logic:present>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" name="classId" id="classId" maxlength="25"
											class="form-control" readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="classname"/></logic:present>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<input type="text" name="sectionId" tabindex="1"
											id="sectionId" maxlength="25" class="form-control"
											readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="sectionnaem"/></logic:present>' />
									</div>
								</div>

							</div>

							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div
											style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
											<img id="imagePreview" class="setImage" alt="image" src="#"
												style="height: 45mm; width: 45mm;">
											<div style="position: absolute; top: 0; height: 160px;">
												<!-- <input type="file" id="studentImageId1" name="studentImage" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;"> -->
											</div>

										</div>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student
										Status</label>
									<div class="col-xs-7">
										<input type="text" name="studentStatusId" id="studentStatusId"
											maxlength="25" class="form-control" readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentStatus"/></logic:present>' />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House</label>
									<div class="col-xs-7">
										<input type="text" name="houseId" tabindex="1"
											onkeypress="HideError()" id="houseId" maxlength="25"
											class="form-control" readonly="readonly"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="houseName"/></logic:present>' />
									</div>
								</div>


								<input type="hidden" id="hstudentid" name="studentId"
									value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentId"/></logic:present>" />

								<input type="hidden" id="hacademicYearId" name="academicYearId"
									value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="academicYearId"/></logic:present>" />

								<input type="hidden" id="hschoolNameId" name="schoolNameId"
									value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="locationId"/></logic:present>" />

								<input type="hidden" id="photohiddenid" name="previousImage"
									value="<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentPhoto" /></logic:present>">
							</div>
						</div>
				</logic:iterate>
			</div>
		</div>
		<hr
			style="height: 1px; border: none; color: #333; background-color: #ddd;" />

		<div>
			<div class="slideTab clearfix">
				<div class="tab">
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#reportHistory"
							id="reportHistory">Report History</a></li>
						<li><a data-toggle="tab" href="#contacts" id="contacts">Contacts</a></li>

						<span class="buttons" id="addconfidential"
							style="position: absolute; right: 38px; margin-top: 6px;">Add</span>
					</ul>


					<div id="contacts" class="tab-pane">
						<div class="col-md-12"
							style="border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
							<div class="searchWrap">
								<div class="col-md-8" id=div2></div>
								<div id="studenttable"></div>
								<div id="individualstudenttable"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</html>
