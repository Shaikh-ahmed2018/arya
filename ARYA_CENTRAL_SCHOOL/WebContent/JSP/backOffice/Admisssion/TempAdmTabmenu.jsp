<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html>
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
<script type="text/javascript" src="JS/common.js"></script>

<script type="text/javascript"
	src="JS/frontOffice/New_Registration_Of_Users/New_Registration_Of_Users.js"></script>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="CSS/Admin/Admission/AdmissionNew.css" rel="stylesheet"
	type="text/css">
</head>
<body>
<div class="col-md-10 col-md-offset-2" id="div-main">

	<div class="tab">
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#issued">Issued</a></li>
			<li><a data-toggle="tab" href="#approvedlink">Approved</a></li>
			<li><a data-toggle="tab" href="#rejected">Rejected</a></li>
			<li><a data-toggle="tab" href="#cancelled">Cancelled</a></li>
			<li><a data-toggle="tab" href="#submitted">Submitted</a></li>
			<li><a data-toggle="tab" href="#processed">Processed</a></li>
			<span class="buttons"
				style="position: absolute; right: 38px; margin-top: 3px;"><a
				href="adminMenu.html?method=admissionaddmenu">Add New</a></span>
		</ul>
		<div class="tab-content">
			<div id="add" class="tab-pane">
				<div class="col-md-10 col-md-offset-2" 
					style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
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
									style="color: red;"> <bean:write name="errormessagediv"
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
										&nbsp;&nbsp;New Registration
									</h4></a>
									
									<div class="navbar-right">
										<span class="buttons" id="save2">Save</span>
									</div>
								</div>
								<div id="collapseOne" class="panel-collapse collapse in"
									role="tabpanel" aria-labelledby="headingOne">
									<div class="panel-body">
										<div class="col-md-6" id="div-main"
											style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


											<div class="form-group">
												<label for="inputEmail" class="control-label col-xs-4"
													style="text-align: right; line-height: 35px;">First
													Name <span style="color: red;">*</span>
												</label>
												<div class="col-xs-7">
													<input type="text" name="parentfirstName"
														onkeypress="HideError()" id="parentfirstName"
														maxlength="25" class="form-control" value='' />
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
													style="text-align: right; line-height: 35px;">Email
													Id <span style="color: red;">*</span>
												</label>
												<div class="col-xs-7">
													<input type="text" class="form-control"
														name="parentEmailId" onkeypress="HideError()"
														id="parentEmailId" maxlength="100" value='' />
												</div>
											</div>

											<br />
											<div class="form-group">
												<label for="inputEmail" class="control-label col-xs-4"
													style="text-align: right; line-height: 35px;">Address
													<span style="color: red;">*</span>
												</label>
												<div class="col-xs-7">
													<textarea name="address" id="address"
														onkeypress="HideError()" class="form-control"></textarea>
												</div>
											</div>

											<br /> <br />
										</div>
										<div class="col-md-6" id="div-main"
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
													style="text-align: right; line-height: 35px;">Relationship
													<span style="color: red;">*</span>
												</label>
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
													<input type="text" class="form-control"
														name="mobile_number" id="mobile_number"
														onkeypress="HideError()" maxlength="10" value='' />
												</div>
											</div>
											<br />
											<div class="form-group">
												<label for="inputEmail" class="control-label col-xs-4"
													style="text-align: right; line-height: 35px;"
													id="inputnames">Stream <span style="color: red;">*</span>
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
													style="text-align: right; line-height: 35px;"
													id="inputnames">Class <span style="color: red;">*</span>
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
		</div>
	</div>
	<div id="issued" class="tab-pane">
		<div class="col-md-12" id="divIn">
			<div class="searchWrap">
				<div class="col-md-8" id=div2>

					<p>
						<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
							id="pageHeading">Issued Forms</span>
					</p>
				</div>


				<form id="myForm" action="adminMenu.html?method=tempadmissionMenu"
					method="post">

					<div class="input-group col-md-4">
						<input type="text" name="searchname" id="searchvalue"
							onkeypress="handle(event)" class="form-control"
							Placeholder="Search......"
							value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'>
						<span class="input-group-btn">
							<button class="btn btn-default" type="button" id="search"
								onclick="myFunction()">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
					<input type="hidden" name="searchterm" class="searchtermclass"
						id="searchexamid"
						value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />

		</logic:present>'></input>

				</form>

			</div>
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
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

			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"></span></a>
				</div>
			</div>


			<div class="panel panel-primary">
				<div class="panel-heading">
					<a data-toggle="" data-parent="#accordion2" href="#"
						style="color: #fff;"><h3 class="panel-title issued"
							style="color: #767676;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Issued
							Forms
						</h3></a>



					<div class="navbar-right">
						<span class="buttons" id="edit"
							style="top: 12px; margin-right: 7px;">Edit</span>
					</div>

				</div>
				<!-- pop up -->
				<div id="issuedOne" class="accordion-body collapse in">
					<div class="panel-body"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

						<logic:present name="issuedList" scope="request">
							<display:table class="table" id="allstudent" name="issuedList"
								
								requestURI="/adminMenu.html?method=tempadmissionMenu"
								decorator="com.centris.campus.decorator.issuedFormDetailsDecorator">


								<display:column
									title="Select">
									<input type='checkbox' name='select' class='select'
										style='text-align: center' id='${allstudent.enquiryid}' />
								</display:column>

								<display:column sortable="true" title="Student Name">
									<a class="anchor"><input type="button" class="buttonstyle"
										id="${allstudent.enquiryid}" value="${allstudent.fullname}" /></a>
								</display:column>

								<display:column property="parents_name" sortable="true"
									title="Parents Name" />

								<display:column property="parentEmailId" sortable="true"
									title="Email" />

								<display:column property="mobile_number" sortable="true"
									title="Mobile No" />

								<display:column property="address" sortable="true"
									title="Address" />

								<display:setProperty name="paging.banner.page.separator"
									value=""></display:setProperty>
								<display:setProperty name="paging.banner.placement"
									value="bottom"></display:setProperty>
							</display:table>

						</logic:present>

					</div>
					<br />
				</div>
			</div>
		</div>
	</div>
	<div id="approvedlink" class="tab-pane">

		<div class="col-md-12" id="divIn">
			<div class="searchWrap">
				<div class="col-md-8" id="div2">
					<p>
						<span class="glyphicon glyphicon-user"></span><span
							id="pageHeading" style="margin-left: 12px;">Approved Forms</span>
					</p>
				</div>
				<div class="input-group col-md-4">
					<input type="text" name="searchname" id="searchapprove"
						onkeypress="handle(event)" class="form-control"
						Placeholder="Search......"
						value='<logic:present name="searchname"><bean:write name="searchname"/></logic:present>'>
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" id="apprsearch"
							onclick="myFunction()">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
				<input type="hidden" name="searchterm" class="searchtermclass"
					id="searchexamid"
					value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />

		</logic:present>'></input>
			</div>
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

			<div class="panel panel-primary">
				<div class="panel-heading">
					<a data-toggle="collapse" data-parent="#accordion2" href="#"
						style="color: #fff;"><h3 class="panel-title class"
							style="color: #767676;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Approved
							Forms
						</h3></a>
					<script>
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});
				</script>
				</div>
				<!-- pop up -->
				<div id="classOne" class="accordion-body collapse in">
					<div class="panel-body"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

						<logic:present name="getapprvedlist" scope="request">
							<display:table class="table" id="allstudent"
								name="getapprvedlist" 
								requestURI="/adminMenu.html?method=tempadmissionMenu"
								decorator="com.centris.campus.decorator.issuedFormDetailsDecorator">
								<display:column sortable="true" class="parentfirstName"
									style='min-width:150px;text-align:left' title="Student Name">
									<a class="anchor"><input type="button"
										class="apprbuttonstyle" id="${allstudent.enquiryid}"
										value="${allstudent.fullname}" style='button-color: bll' /></a>
								</display:column>


								<display:column property="parents_name" sortable="true"
									title="Parents Name" />

								<display:column property="parentEmailId" sortable="true"
									title="Email" />

								<display:column property="mobile_number" sortable="true"
									title="Mobile No" />

								<display:column property="reason" sortable="true"
									title="Reasons" />

								<display:setProperty name="paging.banner.page.separator"
									value=""></display:setProperty>
								<display:setProperty name="paging.banner.placement"
									value="bottom"></display:setProperty>
							</display:table>
						</logic:present>
					</div>
					<br />
				</div>
			</div>
		</div>
	</div>
	<div id="rejected" class="tab-pane">
		<div class="col-md-12" id="divIn">
			<div class="searchWrap">
				<div class="col-md-8" id="div2">

					<p>
						<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
							id="pageHeading">Rejected Forms</span>
					</p>
				</div>
				<div class="input-group col-md-4">
					<input type="text" name="searchname" id="searchreject"
						onkeypress="handle(event)" class="form-control"
						Placeholder="Search......"
						value='<logic:present name="SearchName"><bean:write name="SearchName"/></logic:present>'>
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" id="searchrjct"
							onclick="myFunction()" value="Submitform">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
				<input type="hidden" name="searchterm" class="searchtermclass"
					id="searchexamid"
					value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />

		</logic:present>'></input>
			</div>
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
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

			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"></span></a>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<a data-toggle="collapse" data-parent="#accordion2" href="#"
						style="color: #fff;"><h3 class="panel-title reject"
							style="color: #767676;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Rejected
							Forms
						</h3></a>
				</div>
				<!-- pop up -->
				<div id="rejectedOne" class="accordion-body collapse in">
					<div class="panel-body"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

						<logic:present name="rejectlist" scope="request">
							<display:table class="table" id="allstudent" name="rejectlist"
								
								requestURI="/adminMenu.html?method=tempadmissionMenu"
								decorator="com.centris.campus.decorator.issuedFormDetailsDecorator">
								<display:column sortable="true" class="parentfirstName"
									style='min-width:150px;text-align:left' title="Student Name">
									<a class="anchor"><input type="button"
										class="rjtbuttonstyle" id="${allstudent.enquiryid}"
										value="${allstudent.fullname}" style='button-color: bll' /></a>
								</display:column>

								<display:column property="fullname" sortable="true"
									title="Parents Name" />

								<display:column property="parentEmailId" sortable="true"
									title="Email" />

								<display:column property="mobile_number" sortable="true"
									title="Mobile No" />

								<display:column property="rejectreason" sortable="true"
									title="Reason" />

								<display:setProperty name="paging.banner.page.separator"
									value=""></display:setProperty>
								<display:setProperty name="paging.banner.placement"
									value="bottom"></display:setProperty>
							</display:table>

						</logic:present>

					</div>
					<br />
				</div>
			</div>
		</div>
	</div>
	<div id="cancelled" class="tab-pane">
		<div class="col-md-12" id="divIn">
			<div class="searchWrap">
				<div class="col-md-8" id="div2">

					<p>
						<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
							id="pageHeading">Cancelled Forms</span>
					</p>
				</div>
				<div class="input-group col-md-4">
					<input type="text" name="searchname" id="searchcancelled"
						onkeypress="handle(event)" class="form-control"
						Placeholder="Search......"
						value='<logic:present name="Searchcancel"><bean:write name="Searchcancel"/></logic:present>'>
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" id="searchcacl"
							onclick="myFunction()">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
				<input type="hidden" name="searchterm" class="searchtermclass"
					id="searchexamid"
					value='<logic:present name="Searchcancel"><bean:write name="Searchcancel" />

		</logic:present>'></input>


			</div>
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
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

			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"></span></a>
				</div>
			</div>


			<div class="panel panel-primary">
				<div class="panel-heading">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne" style="color: #fff;"><h3
							class="panel-title cancelled" style="color: #767676;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Cancelled
							Forms
						</h3></a>
				</div>
				<!-- pop up -->
				<div id="cancelledOne" class="accordion-body collapse in">
					<div class="panel-body"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

						<logic:present name="getcancelledlist" scope="request">
							<display:table class="table" id="allstudent"
								name="getcancelledlist" 
								requestURI="/adminMenu.html?method=tempadmissionMenu"
								decorator="com.centris.campus.decorator.issuedFormDetailsDecorator">
								<display:column sortable="true" class="parentfirstName"
									style='min-width:150px;text-align:left' title="Student Name">
									<a class="anchor"><input type="button"
										class="cancelbuttonstyle" id="${allstudent.enquiryid}"
										value="${allstudent.fullname}" style='button-color: bll' /></a>
								</display:column>

								<display:column property="parents_name" sortable="true"
									title="Parents Name" />

								<display:column property="parentEmailId" sortable="true"
									title="Email" />

								<display:column property="mobile_number" sortable="true"
									title="Mobile No" />

								<display:column property="cancelreason" sortable="true"
									title="Reason" />

								<display:setProperty name="paging.banner.page.separator"
									value=""></display:setProperty>
								<display:setProperty name="paging.banner.placement"
									value="bottom"></display:setProperty>
							</display:table>

						</logic:present>

					</div>
					<br />
				</div>
			</div>
		</div>
	</div>
	<div id="submitted" class="tab-pane">

		<div class="col-md-12" id="divIn">
			<div class="searchWrap">
				<div class="col-md-8" id="div2">

					<p>
						<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
							id="pageHeading">Submitted Forms</span>
					</p>
				</div>
				<div class="input-group col-md-4">
					<input type="text" name="searchname" id="searchdsbmit"
						onkeypress="handle(event)" class="form-control"
						Placeholder="Search......"
						value='<logic:present name="Searchsubmit"><bean:write name="Searchsubmit"/></logic:present>'>
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" id="searchsubmit"
							onclick="myFunction()" value="Submitform">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
				<input type="hidden" name="searchterm" class="searchtermclass"
					id="searchexamid"
					value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />

		</logic:present>'></input>
			</div>
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
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
			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"></span></a>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne" style="color: #fff;"><h3
							class="panel-title submitted" style="color: #767676;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Submitted
							Forms
						</h3></a>
				</div>
				<!-- pop up -->
				<div id="submittedOne" class="accordion-body collapse in">
					<div class="panel-body"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

						<logic:present name="getsubmittedlist" scope="request">
							<display:table class="table" id="allstudent"
								name="getsubmittedlist" 
								requestURI="/adminMenu.html?method=tempadmissionMenu"
								decorator="com.centris.campus.decorator.issuedFormDetailsDecorator">



								<display:column sortable="true" class="parentfirstName"
									style='min-width:150px;text-align:left' title="Student Name">
									<a class="anchor"><input type="button"
										class="submitbuttonstyle" id="${allstudent.enquiryid}"
										value="${allstudent.fullname}" style='button-color: bll' /></a>
								</display:column>

								<display:column property="parents_name" sortable="true"
									title="Parents Name" />

								<display:column property="parentEmailId" sortable="true"
									title="Email" />

								<display:column property="mobile_number" sortable="true"
									title="Mobile No" />
								<display:setProperty name="paging.banner.page.separator"
									value=""></display:setProperty>
								<display:setProperty name="paging.banner.placement"
									value="bottom"></display:setProperty>
							</display:table>

						</logic:present>

					</div>
					<br />
				</div>
			</div>
		</div>
	</div>
	<div id="processed" class="tab-pane">

		<div class="col-md-12" id="divIn">
			<div class="searchWrap">
				<div class="col-md-8" id="div2">

					<p>
						<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
							id="pageHeading">Processed Forms</span>
					</p>
				</div>
				<div class="input-group col-md-4">
					<input type="text" name="searchname" id="searchprocess"
						onkeypress="handle(event)" class="form-control"
						Placeholder="Search......"
						value='<logic:present name="Searchprocess"><bean:write name="Searchprocess"/></logic:present>'>
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" id="searchpess"
							onclick="myFunction()">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>


				<input type="hidden" name="searchterm" class="searchtermclass"
					id="searchexamid"
					value='<logic:present name="searchnamelist"><bean:write name="searchnamelist" />

		</logic:present>'></input>


			</div>
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
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

			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"></span></a>
				</div>
			</div>


			<div class="panel panel-primary">
				<div class="panel-heading">
					<a data-toggle="collapse" data-parent="#accordion2"
						href="#collapseOne" style="color: #fff;"><h3
							class="panel-title processed" style="color: #767676;">
							<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Processed
							Forms
						</h3></a>
				</div>
				<!-- pop up -->
				<div id="processedOne" class="accordion-body collapse in">
					<div class="panel-body"
						style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

						<logic:present name="getprocessedlist" scope="request">
							<display:table class="table" id="allstudent"
								name="getprocessedlist" 
								requestURI="/adminMenu.html?method=tempadmissionMenu"
								decorator="com.centris.campus.decorator.issuedFormDetailsDecorator">
								<display:column sortable="true" class="parentfirstName"
									style='min-width:150px;text-align:left' title="Student Name">
									<a class="anchor"><input type="button"
										class="processbuttonstyle" id="${allstudent.enquiryid}"
										value="${allstudent.fullname}" style='button-color: bll' /></a>
								</display:column>

								<display:column property="parents_name" sortable="true"
									title="Parents Name" />

								<display:column property="parentEmailId" sortable="true"
									title="Email" />

								<display:column property="mobile_number" sortable="true"
									title="Mobile No" />


								<display:setProperty name="paging.banner.page.separator"
									value=""></display:setProperty>
								<display:setProperty name="paging.banner.placement"
									value="bottom"></display:setProperty>
							</display:table>

						</logic:present>

					</div>
					<br />
				</div>
		
		</div>
	</div>
	</div>
	</div>
</body>
</html>
