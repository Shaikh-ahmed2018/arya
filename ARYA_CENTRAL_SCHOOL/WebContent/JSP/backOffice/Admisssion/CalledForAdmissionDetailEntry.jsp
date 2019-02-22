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
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Admission/CalledForAdmisssionDetails.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<style>
.save:hover {
	cursor: pointer;
}

.panel-collapse {
	display: block !important;
}

.
collapse in {
	display: block !important;
}
</style>
<style>
.buttons{

vertical-align: -5px;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Temporary
				Registration</span>
		</p>
		<center>
			<logic:present name="successMessage" scope="request">

				<div class="successmessagediv">
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
						style="width: 30%;"><span class="validateTips"></span></a>
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



		<input type="hidden" id="classhiddenid"
			value="<logic:present name="studentSearchList" property="classname"><bean:write name="studentSearchList" property="classname" />
													</logic:present>">


		<input type="hidden" id="hprevious_classname"
			value="<logic:present name="studentSearchList" property="previous_classname"><bean:write name="studentSearchList" property="previous_classname" />
													</logic:present>">


		<input type="hidden" id="haccyear"
			value="<logic:present name="studentSearchList" property="accyear"><bean:write name="studentSearchList" property="accyear" />
													</logic:present>">

		<!-- chiru -->

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"
							style="color: #767676; vertical-align: middle;"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Temporary Registration
						</h4></a>
					

					<div class="navbar-right">

						<span class="buttons" id="save"
							class="save" data-toggle="tooltip" data-placement="bottom"
							title="Save" >Save
						</span>&nbsp; <span
							class="buttons" id="back">Back</span>
						
					</div>

				</div>

				

				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;"> Temp.
									Admission No </label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="temporary_id"
										onkeypress="HideError()" id="temporary_id" readonly="readonly"
										maxlength="25"
										value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="temporary_id"></bean:write></logic:present>' />
								</div>
							</div>
							<br />

							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Student
									Name </label>
								<div class="col-xs-7">
									<input type="text" name="studentfirstName"
										onkeypress="HideError()" id="studentfirstName"
										readonly="readonly" maxlength="25" class="form-control"
										value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentfirstName"/></logic:present>' />



								</div>
							</div>


							<br />

							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Class </label>
								<div class="col-xs-7">
									<select id="previous_classname" name="previous_classname"
										disabled="disabled" class="form-control" required>
										<option value=""></option>
										<logic:present name="classList">
											<logic:iterate id="classrec" name="classList">
												<option
													value="<bean:write name="classrec" property="classId"/>">
													<bean:write name="classrec" property="classname" />
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


							<div class="form-group">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Date Of
									Birth </label>
								<div class="col-xs-7">
									<input type="text" placeholder="" onkeypress="HideError()"
										readonly="readonly" name="dateofBirthId" id="dateofBirthId"
										class="form-control"
										value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="dateofBirthId"></bean:write></logic:present>' />
								</div>
							</div>

							<br />

							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;"> Previous
									School Name </label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="school_name"
										id="school_name" onkeypress="HideError()" maxlength="25"
										readonly="readonly"
										value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="school_name"></bean:write></logic:present>' />
								</div>
							</div>


							<br />

							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4" id=""
									style="text-align: right; line-height: 35px;">Grade /
									Percentage</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="percentage"
										id="percentage" onkeypress="HideError()" maxlength="25"
										readonly="readonly"
										value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="percentage"></bean:write></logic:present>' />
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
							&nbsp;&nbsp;Looking Admission For
						</a>
					</h4>
				</div>
				<div id="collapseThree" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingThree">
					<div class="panel-body">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Class</label>
								<div class="col-xs-7">
									<select id="classname" name="classname" class="form-control"
										disabled="disabled" required>
										<option value=""></option>
										<logic:present name="classList">
											<logic:iterate id="classrec" name="classList">
												<option
													value="<bean:write name="classrec" property="classId"/>">
													<bean:write name="classrec" property="classname" />
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>

						</div>
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									id="group_label" style="text-align: right; line-height: 35px;">Group
								</label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="group_name"
										id="group_name" readonly="readonly" onkeypress="HideError()"
										maxlength="25"
										value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="group_name"></bean:write></logic:present>' />
								</div>
							</div>

							<%--<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Academic Year </label>
								 <div class="col-xs-7">
									<select id="accyear" name="accyear" class="form-control"
										disabled="disabled" required>
										<option value=""></option>
										<logic:present name="AccYearList">
											<logic:iterate id="AccYear" name="AccYearList">
												<option
													value=" <bean:write name="AccYear" property="accyearId"/>">
													<bean:write name="AccYear" property="accyearname" />
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</div> --%>
						</div>

					</div>
				</div>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingFour">
				<h4 class="panel-title">
					<a class="collapsed" role="button" data-toggle="collapse"
						data-parent="#accordion" href="#collapseFour"
						style="color: #767676" aria-expanded="false"
						aria-controls="collapseFour"> <i
						class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;For
						Office/Administration Department
					</a>
				</h4>
			</div>
			<div id="collapseFour" class="panel-collapse collapse"
				role="tabpanel" aria-labelledby="headingFour">
				<div class="panel-body">
					<div class="col-md-6"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">




						<div class="form-group">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Evaluated
								By </label>
							<div class="col-xs-7">
								<input type="text" name="evaluated_by" onkeypress="HideError()"
									id="evaluated_by" readonly="readonly" maxlength="25"
									class="form-control"
									value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="current_user"/></logic:present>' />



							</div>
						</div>

						<br />
						<div class="form-group">
							<label for="inputPassword" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Interview
								/ Written Test</label>

							<div class="col-xs-7">
								<select id="test" name="test" class="form-control">
									<option value="">Select</option>
									<option value="test">Test</option>
									<option value="viva">Viva</option>
									<option value="written">Written</option>


								</select>
							</div>



						</div>

						<br />

						<div class="form-group">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right; line-height: 35px;">
								Max Marks </label>
							<div class="col-xs-7">
								<input type="text" class="form-control" name="max_marks"
									id="max_marks" onkeypress="HideError()" maxlength="25" value='' />
							</div>
						</div>

						<br />

						<div class="form-group">
							<label for="inputPassword" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Recommended Status</label>
							<div class="col-xs-7">
								<select id="recommended_status" name="recommended_status" class="form-control">
									<option value="">Select</option>
									<option value="Recommended">Recommended </option>
									<option value="Not Recommended">Not Recommended </option>
									<option value="Hold">Hold</option>


								</select>
							</div>
						</div>

					</div>
					<div class="col-md-6"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">



						<div class="form-group">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Date Of
								Evaluation </label>
							<div class="col-xs-7">
								<input type="text" placeholder="" onkeypress="HideError()"
									name="evaluation_date" id="evaluation_date"
									class="form-control" value='' />
							</div>
						</div>

						<br />

						<div class="form-group">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;"> </label>
						</div>
						<br />

						<div class="form-group">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right; line-height: 35px;">Marks
								Secured </label>
							<div class="col-xs-7">
								<input type="text" class="form-control" name="marks_secured" id="marks_secured"
									onkeypress="HideError()" maxlength="25" value='' />
							</div>
						</div>
						<br />
						<div class="form-group">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right; line-height: 35px;">Remarks
							</label>
							<div class="col-xs-7">
								<textarea name="remarks" id="remarks" onkeypress="HideError()"
									class="form-control"></textarea>
							</div>
						</div>


						<br />



					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- <button type="submit" class="btn btn-info col-md-offset-5">Savechanges</button>
				<button type="reset" class="btn btn-info">Clear</button> -->
	</div>
</html>
