<html>
<head>
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
<script type="text/javascript" src="/JS/common.js"></script>
<script type="text/javascript"
	src="JS/frontOffice/New_Registration_Of_Users/New_Registration_Of_Users.js"></script>

<link href=	"CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>
<body>
<div id="add" class="tab-pane">
     <div class="col-md-10 col-md-offset-2"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">
				Registration</span>
		</p>
		

		<form
			action="parentrequiresappointment.html?method=InsertTemporaryStudentRegistration"
			enctype="multipart/form-data" id="formstudent" method="post">

			


			<div class="panel-group" id="accordion">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"
								style="color: #767676; vertical-align: middle;"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Registration
							</h4></a>
						



						<div class="navbar-right">
		              <span class="buttons" id="approve"
						data-toggle="tooltip" data-placement="bottom" title="approve">Approve</span>
			
						  <span class="buttons" id="reject"
						data-toggle="tooltip" data-placement="bottom" title="reject">Reject</span>
						
						  <span class="buttons" id="back">Back</span>
						

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
										style="text-align: right; line-height: 35px;">Email Id <span
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
										style="text-align: right; line-height: 35px;">Relationship <span
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
				

	</form>
	</div>
	</div>
</body>
</head>
</html>
