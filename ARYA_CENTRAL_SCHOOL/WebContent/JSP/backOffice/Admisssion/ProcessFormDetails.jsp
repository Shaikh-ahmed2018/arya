<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

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
<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript"
	src="JS/frontOffice/New_Registration_Of_Users/New_Registration_Of_Users.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="add" class="tab-pane">
		<div class="col-md-10 col-md-offset-2" id="div-main"
			style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
			<div id="dialog3"></div>
			<p>
				<img src="images/addstu.png" />&nbsp;<span id="pageHeading">
					Registration</span>
			</p>

			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
									name="successmessagediv" scope="request" /></span></a>
					</div>
				</div>
			</logic:present>

			<logic:present name="errormessagediv" scope="request">

				<div class="errormessagediv" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span
							style="color: red;"> <bean:write name="errorMessage"
									scope="request" />
						</span></a>
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

			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"></span></a>
				</div>
			</div>

			<form action="" enctype="multipart/form-data" id="formstudent"
				method="post">


				<input type="hidden" id="updatelocationname"
					value='<logic:present name="edit_issuedlist"><bean:write name="edit_issuedlist"/></logic:present>' />
				<input type="hidden" id="updateenquiryid"
					value='<logic:present name="edit_issuedlist"><bean:write name="edit_issuedlist" 
property="enquiryid" />
		</logic:present>'></input>

				<div class="panel-group" id="accordion" role="tablist"
					aria-multiselectable="true">
					<div class="panel panel-primary">
						<div class="panel-heading clearfix" role="tab" id="headingOne">
							
								<a data-toggle="collapse" data-parent="#accordion" href="#"
									style="color: #767676"><h4 class="panel-title"><i
									class="glyphicon glyphicon-menu-hamburger"></i>
									&nbsp;&nbsp;Processed Registration
								</h4></a>
							


							<div class="navbar-right" style="top: 0px; right: 9px;">



								<span id="back" class="buttons">Back</span>
						      		
							</div>

						</div>



						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">
								<div class="col-md-6"
									style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">First
											Name <span style="color: red;">*</span>
										</label>
										<div class="col-xs-7">
											<input type="text" name="parentfirstName"
												onkeypress="HideError()" class="form-control"
												id="parentfirstName"
												value="<logic:present name="edit_issuedlist" ><bean:write name="edit_issuedlist" property="parentfirstName" /></logic:present>"
												readonly></input>


										</div>
									</div>
									



									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Parents
											Name <span style="color: red;">*</span>
										</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" name="parents_name"
												id="parents_name" onkeypress="HideError()" maxlength="10"
												value='<logic:present name="edit_issuedlist"><bean:write name="edit_issuedlist" property="parents_name" /></logic:present>'
												readonly />
										</div>
									</div>
								



									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Email
											Id <span style="color: red;">*</span>
										</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" name="parentEmailId"
												onkeypress="HideError()" id="parentEmailId"
												value='<logic:present name="edit_issuedlist" ><bean:write name="edit_issuedlist" property="parentEmailId" /></logic:present>'
												readonly></input>
										</div>
									</div>

									





									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Address
											<span style="color: red;">*</span>
										</label>
										<div class="col-xs-7">
											<textarea class="form-control" name="address" onkeypress="HideError()" readonly id="address"><logic:present name="edit_issuedlist"><bean:write name="edit_issuedlist" property="address" /></logic:present></textarea>
										</div>
									</div>
									
									
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Processed Reason<span style="color: red;">*</span>
										</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" name="cancelreason"
												id="parents_name" onkeypress="HideError()" maxlength="10"
												value='<logic:present name="edit_issuedlist"><bean:write name="edit_issuedlist" property="cancelreason" /></logic:present>'
												readonly />
										</div>
									</div>
									<br />
									
									
								</div>

								<div class="col-md-6"
									style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Last
											Name <span style="color: red;">*</span>
										</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" maxlength="50"
												name="parent_LastName" id="parent_LastName"
												onkeypress="HideError()" readonly
												value='<logic:present name="edit_issuedlist"><bean:write name="edit_issuedlist" property="parent_LastName" /></logic:present>' />
										</div>
									</div>

								
									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Relationship
											<span style="color: red;">*</span>
										</label>
										<div class="col-xs-7">
											<select name="stu_parrelation" id="stu_parrelation"
												disabled="disabled" class="form-control"
												onkeypress="HideError()">

												<option value="father">Father</option>
												<option value="mother">Mother</option>
												<option value="guardian">Guardian</option>
											</select>
										</div>
									</div>
								

									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;">Mobile
											No <span style="color: red;">*</span>
										</label>
										<div class="col-xs-7">
											<input type="text" class="form-control" name="mobile_number"
												id="mobile_number" onkeypress="HideError()" maxlength="10"
												readonly
												value="<logic:present name="edit_issuedlist" ><bean:write name="edit_issuedlist" property="mobile_number" /></logic:present>"></input>
										</div>
									</div>
									


									<div class="form-group clearfix">
										<label for="inputEmail" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;" id="inputnames">Stream
											<span style="color: red;">*</span>
										</label>
										<div class="col-xs-7">
											<select id="stream" disabled="disabled" name="stream"
												class="form-control" required>

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

									<input type="hidden" name="updateClassCode"
										id="updateClassCode"
										value="<logic:present name="edit_issuedlist" ><bean:write name="edit_issuedlist" property="classid"/></logic:present>" />
									<input type="hidden" name="hiddenStreamId" id="hiddenStreamId"
										value="<logic:present name="edit_issuedlist" ><bean:write name="edit_issuedlist" property="streamId"/></logic:present>" />
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4"
											style="text-align: right; line-height: 35px;" id="inputnames">Class
											<span style="color: red;">*</span>
										</label>
										<div class="col-xs-7">
											<select id="class" disabled="disabled" name="classname"
												class="form-control" required>
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
