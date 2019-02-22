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
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>

<link href="CSS/Admin/Admission/StudentNew.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="JS/backOffice/Student/StudentAppraisalPage.js"></script>
<style>
.form-group{
margin-bottom: 5px;}
.save:hover {
	cursor: pointer;
}


/* #individualstudenttable th:nth-child(2),th:nth-child(3){
  text-align: center;
  }
#individualstudenttable td:nth-child(2){
  text-align: center;
  width: 20%;
  }
#studenttable th:nth-child(2),th:nth-child(4),th:nth-child(5){
width:20%;
  }
#studenttable th:nth-child(2),th:nth-child(4),th:nth-child(5){
  text-align: center;

  }

#studenttable td:nth-child(4),td:nth-child(5){
text-align:center;
  }
   */
  

fieldset { 
	width:512px;
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
</style>
<style>
.buttons{

vertical-align:-5px;

}
.navbar-right {
    top: -3px;
}
.panel-primary>.panel-heading{
margin-bottom: 0px;
}
form .panel.panel-primary.panel-list{
padding-bottom: 0px;

}    

@media (min-width:320px) and (max-width:767px){
br{
display: none;
}

}
.slideTab{
	padding:10px;
	font-family: Roboto Medium;
    font-size: 14px;
    font-weight: lighter;
}

</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" /><span id="pageHeading">Student
				Appraisal</span>
		</p>
		<div id="admissionDialog" style="display: none">
			<div class="col-md-12">
				<p style="margin-left: 29px;">
					<span id="pageHeading">Student Appraisal</span>
				</p>
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left;">Action Taken</label>
								<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()" style="Width:150%;"
										tabindex="3" name="" id="">
										<option value="">Select</option>
										<option value="">Given Warning</option>
										<option value=""></option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">


					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: left;">Schedule Date</label>
						<div class="col-xs-7">
							<input type="text" name="" readonly="readonly"
								onkeypress="HideError()" id=""
								class="form-control" />
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: left;">Meeting Date</label>
						<div class="col-xs-7">
							<input type="text" name="" readonly="readonly"
								placeholder="10-Apr-2017" onkeypress="HideError()" id=""
								class="form-control" />
						</div>
					</div>

					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: left;">Meeting On</label>
						<div class="col-xs-7">
							<input type="text" name="" onkeypress="HideError()" id=""
								class="form-control" />
						</div>
					</div>
				</div>


				<div class="col-md-6"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-5"
							style="text-align: left; line-height: 14px;">Recommented
							By</label>
						<div class="col-xs-7">
							<input type="text" name="" onkeypress="HideError()" id=""
								class="form-control" placeholder="" />
						</div>
					</div>

					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-5"
							style="text-align: left;">Meeting With</label>
						<div class="col-xs-7">
							<select class="form-control" onkeypress="HideError()"
								tabindex="3" name="" id="">
								<option value="">Select</option>
								<option value="">Father</option>
								<option value="">Mother</option>
								<option value="">Guardian</option>
							</select>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: left;">Remarks</label>
								<div class="col-xs-7">
									<textarea style="border: 1px solid #ccc;" rows="2" cols="52"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>


			</div>


		</div>
		<logic:present name="successMessage" scope="request">

			<div class="successmessagediv1"
				style="width: auto !important; display: none" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successMessage" scope="request" /></span></a>
				</div>
			</div>

		</logic:present>

		<logic:present name="errorMessage" scope="request">

			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="errorMessage" scope="request" /></span></a>
				</div>
			</div>

		</logic:present>

		<logic:present name="duplicateMessage">

			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="duplicateMessage" scope="request" />
					</span></a>
				</div>

			</div>

		</logic:present>
		</center>
		<center>
			<div class="errormessagediv1" style="display: none;" align="center">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips1"></span></a>
				</div>
			</div>
		</center>
		<center>

			<div class="errormessagediv" style="display: none;" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>
		</center>
		<form action="studentRegistration.html?method=saveStudentRegistration"
			enctype="multipart/form-data" name="StudentRegistrationForm"
			id="formstudent" method="post">



			<!-- chiru -->

			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"
								style="color: #767676; vertical-align: text-top;"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Student Appraisal
							</h4></a>
						

						<div class="navbar-right">
			 				<span class="buttons" id="back">Back</span>

						</div>
					</div>

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
											class="form-control" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School
										Name</label>
									<div class="col-xs-7">
										<input type="text" name="schoolLocation" tabindex="1"
											onkeypress="HideError()" id="schoolLocationId" maxlength="25"
											class="form-control" />
									</div>
								</div>


								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student
										Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentFirstName" tabindex="1"
											onkeypress="HideError()" id="studentFirstNameId"
											maxlength="25" class="form-control"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentFirstName"/></logic:present>' />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission
										No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="studentrollno"
											tabindex="4" onkeypress="HideError()" id="studentrollno"
											onchange="checkRollnumber()" maxlength="25"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentAdmissionNo"></bean:write></logic:present>' />
									</div>
								</div>




								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House</label>
									<div class="col-xs-7">
										<input type="text" name=house tabindex="1"
											onkeypress="HideError()" id="house" maxlength="25"
											class="form-control" />
									</div>
								</div>


								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Roll No</label>
									<div class="col-xs-7">
										<input type="text" name=rollNo tabindex="1"
											onkeypress="HideError()" id="rollNo" maxlength="25"
											class="form-control" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="studSectionId"
											tabindex="4" onkeypress="HideError()" id="studSectionId"
											onchange="checkRollnumber()" maxlength="25" />


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
												<input type="file" id="studentImageId1" name="studentImage"
													class="form-control"
													style="height: 170px !important; width: 170px; opacity: 0; z-index: 99999999;">
											</div>
											<span id="removeSpanId" class="close"
												style="position: absolute; top: 0px; right: 100px; color: red;">X</span>
										</div>
									</div>
								</div>

								<div class="form-group clearfix" id="studentstatuslable">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student
										Status</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="studentstatus"
											tabindex="4" onkeypress="HideError()" id="studentStatusId"
											onchange="checkRollnumber()" maxlength="25" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="studClassId"
											tabindex="4" onkeypress="HideError()" id="studClassId"
											onchange="checkRollnumber()" maxlength="25" />

									</div>
								</div>
							</div>

							<p align="center"style="margin-left:17%">
								<button type="button" class="btn btn-info"style="width: 8%;margin-left: 20px;"
								id="addappraisal" >Add</button></p>
							<!-- <p align="right">
							<div>
								<span class="buttons" id="addappraisal">Add</span></div>
							</p> -->
							<table class='table' id='allstudent' width='100%'>
								<tr>
									<th>Sl.No.</th>
									<th style="text-align: center; width: 15%;">Entry Date</th>
									<th style="text-align: center">Schedule Date</th>
									<th style="text-align: center">Action Taken</th>
									<th style="text-align: center">With Whom</th>
									<th style="text-align: center">Meeting Date</th>
									<th style="text-align: center">Meeting On</th>
									<th style="text-align: center">Status</th>
									<th style="text-align: center"></th>
								</tr>
								<tr>
									<td>1</td>
									<td style="text-align: center">10-Apr-2017</td>
									<td style="text-align: center">10-Apr-2017</td>
									<td style="text-align: center">Given Warning</td>
									<td>Father</td>
									<td style="text-align: center">10-Apr-2017</td>
									<td style="text-align: center">10:20 AM</td>
									<td style="text-align: center">Completed</td>
									<td style="text-align: center"><input type="button" value="Edit"class="edit"></td>

								</tr>
								<tr>
									<td>2</td>
									<td style="text-align: center">10-Apr-2017</td>
									<td style="text-align: center">10-Apr-2017</td>
									<td style="text-align: center">Given Warning</td>
									<td>Father</td>
									<td style="text-align: center">10-Apr-2017</td>
									<td style="text-align: center">10:20 AM</td>
									<td style="text-align: center">Completed</td>
									<td style="text-align: center"><input type="button" value="Edit"class="edit"></td>
								</tr>
								<tr>

									<td>3</td>
									<td style="text-align: center">10-Apr-2017</td>
									<td style="text-align: center">10-Apr-2017</td>
									<td style="text-align: center">Given Warning</td>
									<td>Father</td>
									<td style="text-align: center">10-Apr-2017</td>
									<td style="text-align: center">10:20 AM</td>
									<td style="text-align: center">Completed</td>
									<td style="text-align: center"><input type="button" value="Edit"class="edit"></td>
								</tr>
								<tr>
									<td>4</td>
									<td style="text-align: center">10-Apr-2017</td>
									<td style="text-align: center">10-Apr-2017</td>
									<td style="text-align: center">Given Warning</td>
									<td>Father</td>
									<td style="text-align: center">10-Apr-2017</td>
									<td style="text-align: center">10:20 AM</td>
									<td style="text-align: center">Completed</td>
									<td style="text-align: center"><input type="button" value="Edit"class="edit"></td>
								</tr>
							</table>
						</div>

					</div>
				</div>
		</form>
	</div></html>
