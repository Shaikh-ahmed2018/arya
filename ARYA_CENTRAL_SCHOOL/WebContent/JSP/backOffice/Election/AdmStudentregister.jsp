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
	src="/JS/frontOffice/New_Registration_Of_Users/New_Registration_Of_Users.js"></script>
<script type="text/javascript"
	src="/JS/backOffice/Admission/TemporaryAdmissionDetails.js"></script>
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
.buttons {
	vertical-align: -6px;
}
</style>
</head>

<body>
	<%-- <span><logic:present name="TempAdmNo" scope="request"><bean:write name="TempAdmNo" scope="request" /></logic:present></span> --%>
	<label for="inputPassword" class="control-label col-xs-4"
		style="text-align: right; margin-left: 10px; line-height: 35px;">
		
	</label>
	<div class="col-md-10 col-md-offset-2"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">
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


		<form
			action="parentrequiresappointment.html?method=InsertTemporaryStudentRegistration"
			enctype="multipart/form-data" id="formstudent" method="post">

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
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"
								style="color: #767676; vertical-align: middle;"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Registration
							</h4></a>
						



						<div class="navbar-right">

							<span class="save2" id="save"> <img src="images/save.png"
						class="save" style="margin-top: 8px;" data-toggle="tooltip"
						data-placement="bottom" title="Save" />
					</span>
						

						</div>

					</div>



					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

								<%-- <div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;"> Temp.
									Admission No </label>
								<div class="col-xs-7">
									<input type="text" class="form-control" name="temporary_id"
										onkeypress="HideError()" id="temporary_id" 
										maxlength="25"
										value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="temporary_id"></bean:write></logic:present>' />
								</div>
							</div>
							<br /> --%>

								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">First
										Name <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" name="parentfirstName"
											onkeypress="HideError()" id="parentfirstName" maxlength="25"
											class="form-control" value='' />




									</div>
								</div>
								<br />



								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Parents
										Name <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="parents_name"
											id="parents_name" onkeypress="HideError()" maxlength="10"
											value='' />
									</div>
								</div>
								<br />



								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Email Id<span
										style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="parentEmailId"
											onkeypress="HideError()" id="parentEmailId" maxlength="100"
											value='' />
									</div>
								</div>

								<br />






								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Address
										<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<textarea name="address" id="address" onkeypress="HideError()"
											class="form-control"></textarea>



									</div>
								</div>

								<br /> <br />










							</div>

							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Last
										Name <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" maxlength="50"
											name="parent_LastName" id="parent_LastName"
											onkeypress="HideError()" value='' />
									</div>
								</div>

								<br />





								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Relationship<span
										style="color: red;">*</span></label>
									<div class="col-xs-7">
									<select name="stu_parrelation" id="stu_parrelation"
											class="form-control" onkeypress="HideError()">
											<option value=" "></option>
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
										No <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="mobile_number"
											id="mobile_number" onkeypress="HideError()" maxlength="10"
											value='' />



									</div>
								</div>
								<br />


								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;" id="inputnames">Stream
										<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<select id="stream" name="stream" class="form-control"
											required>
											<option value=""></option>
											<logic:present name="StreamList">

												<logic:iterate id="StreamRec" name="StreamList">

													<option
														value="<bean:write name="StreamRec" property="streamId"/>">
														<bean:write name="StreamRec" property="streamname" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>

									</div>
								</div>

								<br />




								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;" id="inputnames">Class
										<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<select id="class" name="classname" class="form-control"
											required>
											<option value=""></option>

										</select>
									</div>
								</div>

								<br />


							</div>
						</div>
						
					</div>
					
				</div>
		<b style="padding-left: 441px;">NOTE : All <span style="color:red;">'*'</span> Fields are Mandatory</b>
	</form>
	</div>			
	
</html>
