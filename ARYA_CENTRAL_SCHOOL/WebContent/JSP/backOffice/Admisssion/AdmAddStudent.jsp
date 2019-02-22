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
<link href=	"CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
	
	
<body>
     <div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
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
									<a href="#" class="msg-warning bg-msg-warning"><span style="color:red ;">
											<bean:write name="errorMessage" scope="request" />
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

		<form action="parentrequiresappointment.html?method=InsertTemporaryStudentRegistration" enctype="multipart/form-data" id="formstudent" method="post">

			


			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading clearfix" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#" style="color: #767676"> <h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;New Registration
							</h4></a>
  


						<div class="navbar-right">
		                  <span id="save2" class="buttons" style="top:9px;right:8px">Save</span>
		                  <span id="back" class="buttons" style="top:9px;right:2px">Back</span>
		                  
		         
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
										<input type="text" name="parentfirstName"   tabindex="1"
											onkeypress="HideError()" id="parentfirstName" maxlength="25"
											class="form-control" value='' />
									</div>
								</div>
		



								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Parents
										Name <span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="parents_name"  tabindex="3"
											id="parents_name" onkeypress="HideError()" 
											value='' />
									</div>
								</div>
			



								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Email Id <span
										style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text"  class="form-control" name="parentEmailId"  tabindex="5"
											onkeypress="HideError()" id="parentEmailId" maxlength="100"
											value='' />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;" id="inputnames">School
										<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<select id="location" name="location" class="form-control"  tabindex="8">
											<option value="">----------Select----------</option>
											<logic:present name="locationList">

												<logic:iterate id="locationList" name="locationList">

													<option
														value="<bean:write name="locationList" property="locationId"/>"><bean:write name="locationList" property="locationName" /></option>

												</logic:iterate>

											</logic:present>
										</select>

									</div>
								</div>
						
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Address
										<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<textarea name="address" id="address" onkeypress="HideError()"  tabindex="7"
											class="form-control"></textarea>



									</div>
								</div>
							</div>

							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Last
										Name 
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control"  tabindex="2"
											name="parent_LastName" id="parent_LastName"
											onkeypress="HideError()" value='' />
									</div>
								</div>
                   
								
                                    <div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Relationship <span
										style="color: red;">*</span></label>
									<div class="col-xs-7">
									<select name="stu_parrelation" id="stu_parrelation"  tabindex="4"
											class="form-control" onkeypress="HideError()">
											<option value="">----------Select----------</option>
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
										<input type="text" class="form-control" name="mobile_number"  tabindex="6"
											id="mobile_number" onkeypress="HideError()" maxlength="10"
											value='' />



									</div>
								</div>
					


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;" id="inputnames">Stream
										<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<select id="stream" name="stream" class="form-control"  tabindex="8">
											<option value="">----------Select----------</option>
											
										</select>

									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;" id="inputnames">Class
										<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<select id="class" name="classname" class="form-control" onkeypress="HideError()" tabindex="9">
											<option value="">----------Select----------</option>

										</select>
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;" id="inputnames">Academic Year
										<span style="color: red;">*</span>
									</label>
									<div class="col-xs-7">
										<select id="AccyearId" name="accyearId" class="form-control" >
											<option value="">----------Select----------</option>
											<logic:present name="AccYearList">

												<logic:iterate id="AccYearList" name="AccYearList">

													<option
														value="<bean:write name="AccYearList" property="accId"/>"><bean:write name="AccYearList" property="accName" /></option>

												</logic:iterate>

											</logic:present>
										</select>

									</div>
								</div>
			


							</div>
						</div>
						
					</div>
					
				</div>
				
</div>
	</form>
	</div>
