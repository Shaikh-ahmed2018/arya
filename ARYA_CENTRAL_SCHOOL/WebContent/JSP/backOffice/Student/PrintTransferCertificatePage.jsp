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
<script type="text/javascript"
	src="JS/backOffice/Student/PrintTransferCertificate.js"></script>
<style>
.form-group{
margin-bottom: 5px;}
.save:hover {
	cursor: pointer;
}

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
.textar{
line-height: 21px;
margin-bottom: 33px;
}

</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">Transfer Certificate</span></p>
		<div id="admissionDialog" style="display: none">
			<div id="admissionclose" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<display:table class="table" id="admissionstudent"
						name="requestScope.getTempAdmissionDetailsList" 
						defaultsort="3"	>
						
						<display:column  style="text-align:center ">
							<input type='radio' name='select' class='select' style='text-align:center' id='${admissionstudent.temp_regid }'  /></display:column>
						<display:column property="studentname" sortable="true"	title="Student Name" />
						<display:column property="fatherName" sortable="true" title="Parents Name" />
						<display:column property="fatherMobileNo" sortable="true" title="Mobile Number " />
						<display:column property="dateofBirth" sortable="true" title="Date Of Birth" />
						<display:column property="schemeofstudy" sortable="true" title="Scheme Of Study" />
						<%-- <display:column property="classname" sortable="true" title="Class" /> --%>
						<display:column property="created_date" sortable="true" title="Created Date" />
						
						
					</display:table>

					
				</div>
				<br />
			</div>
		</div>
	
			<logic:present name="successMessage" scope="request">

				<div class="successmessagediv1" style="width:auto !important;display:none" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write	name="successMessage" scope="request" /></span></a>
					</div>
				</div>

			</logic:present>
	
			<logic:present name="errorMessage" scope="request">

				<div class="successmessagediv" align="center" style="display: none;" >
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write name="errorMessage" scope="request" /></span></a>
					</div>
				</div>

			</logic:present>
		
			<logic:present name="duplicateMessage" >

				<div class="successmessagediv" align="center" style="display: none;">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write name="duplicateMessage" scope="request" />
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
			enctype="multipart/form-data" name="StudentRegistrationForm" id="formstudent" method="post">

			
													
			<!-- chiru -->

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Transfer Certificate</h4></a>
						

						<div class="navbar-right">
						<span class="buttons" id="cenceltc">Cancel TC</span>
							<span class="buttons" id="save">Print</span>
		 					<span class="buttons" id="back">Back</span>
						</div>
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="academicYear" tabindex="1"
											onkeypress="HideError()" id="academicYear"
											maxlength="25" class="form-control" />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
									<input type="text" name="schoolLocation" tabindex="1"
											onkeypress="HideError()" id="schoolLocationId"
											maxlength="25" class="form-control" />
									</div>
								</div>
								
								
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentFirstName" tabindex="1"
											onkeypress="HideError()" id="studentFirstNameId"
											maxlength="25" class="form-control" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentFirstName"/></logic:present>' />
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission	No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="studentrollno" tabindex="4"
											onkeypress="HideError()" id="studentrollno"
											onchange="checkRollnumber()" maxlength="25"
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentAdmissionNo"></bean:write></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Date Of Admission</label>
									<div class="col-xs-7">
										<input type="text" name= 
											onkeypress="HideError()" id=""
											maxlength="25" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Date Of Leaving</label>
									<div class="col-xs-7">
										<input type="text" name= 
											onkeypress="HideError()" id=""
											maxlength="25" class="form-control" />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Purpose</label>
									<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()" tabindex="8"
											name="" id="">
											<option value="">Select</option>
											<option value="">Pass Out</option>
											<option value="">Detained</option>
											<option value="">Drop Out</option>
										</select>
										
									</div>
								</div>
								
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Religion/Caste/Category</label>
									<div class="col-xs-7">
										<input type="text" name= ""
											onkeypress="HideError()" id="" 
											maxlength="25" class="form-control" />
									</div>
								</div>
								
								 
								 <div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Father's Name</label>
									<div class="col-xs-7">
										<input type="text" name= ""
											onkeypress="HideError()" id="" 
											maxlength="25" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Mother's Name</label>
									<div class="col-xs-7">
										<input type="text" name= ""
											onkeypress="HideError()" id=""
											maxlength="25" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 18px;">StudentCharactor Or Conduct</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="8"
											name="" id="">
											<option value="">Select</option>
											<option value="">Good</option>
											<option value="">Bad</option>
											
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 18px;">No Of School Days Upto That Date</label>
									<div class="col-xs-7">
										<input type="text" name= 
											onkeypress="HideError()" id="" 
											maxlength="25" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Last Attendance Date</label>
									<div class="col-xs-7">
										<input type="text" name= 
											onkeypress="HideError()" id="" 
											maxlength="25" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 18px;">Number Of School Meetings Upto Date</label>
									<div class="col-xs-7">
										<input type="text" name= 
											onkeypress="HideError()" id="" 
											maxlength="25" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 18px;">Number Of School Meetings Pupil Attended</label>
									<div class="col-xs-7">
										<input type="text" name= 
											onkeypress="HideError()" id="" 
											maxlength="25" class="form-control" />
									</div>
								</div>
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Roll No</label>
									<div class="col-xs-7">
										<input type="text" name=rollNo tabindex="1"
											onkeypress="HideError()" id="rollNo"
											maxlength="25" class="form-control" />
									</div>
								</div>
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Withheld</label>
									<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()" tabindex="8"
											name="" id="">
											<option value="">Select</option>
											<option value="">YES</option>
											<option value="">CANCELLED</option>
										</select>
										
									</div>
								</div>
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
												<img id="imagePreview" class="setImage" alt="image" src="#" style="height: 45mm; width: 45mm;">
												<div style="position: absolute;top: 0;height: 160px;">
												<input type="file" id="studentImageId1" name="studentImage" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;">
												</div>
												<span id="removeSpanId" class="close" style="position: absolute; top: 0px; right: 100px;color: red;" >X</span>
										</div>
									</div>
								</div>
								 
								<div class="form-group clearfix" id="studentstatuslable">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student Status</label>
									<div class="col-xs-7">
									<input type="text" class="form-control" name="studentstatus" tabindex="4"
											onkeypress="HideError()" id="studentStatusId"
											onchange="checkRollnumber()" maxlength="25"/>
									</div>
								</div>
								
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class & Division</label>
									<div class="col-xs-7">
									<input type="text" class="form-control" name="studClassId" tabindex="4"
											onkeypress="HideError()" id="studClassId"
											onchange="checkRollnumber()" maxlength="25"/>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Date Of Birth</label>
									<div class="col-xs-7">
									<input type="text" class="form-control" name="" tabindex="4"
											onkeypress="HideError()" id=""
											 maxlength="25"/>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Fee Full Paid</label>
									<div class="col-xs-7">
									<input type="text" class="form-control" name="" tabindex="4"
											onkeypress="HideError()" id=""
											 maxlength="25"/>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Fee Concession</label>
									<div class="col-xs-7">
									<input type="text" class="form-control" name="" tabindex="4"
											onkeypress="HideError()" id=""
											 maxlength="25"/>
										
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Qualified For Higher Studies</label>
									<div class="col-xs-7">
									<input type="text" class="form-control" name="" tabindex="4"
											onkeypress="HideError()" id=""
											 maxlength="25"/>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Date Of Application</label>
									<div class="col-xs-7">
									<input type="text" class="form-control" name="" tabindex="4"
											onkeypress="HideError()" id=""
											 maxlength="25"/>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Promotion Of That Class</label>
									<div class="col-xs-7">
									<input type="text" class="form-control" name="" tabindex="4"
											onkeypress="HideError()" id=""
											 maxlength="25"/>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 18px;">No of School Days Pupil Attended</label>
									<div class="col-xs-7">
									<input type="text" class="form-control" name="" tabindex="4"
											onkeypress="HideError()" id=""
											 maxlength="25"/>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 18px;">Date In Which Remove From Roll</label>
									<div class="col-xs-7">
									<input type="text" class="form-control" name="" tabindex="4"
											onkeypress="HideError()" id=""
											 maxlength="25"/>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Identification Mark 1</label>
									<div class="col-xs-7">
									<input type="text" class="form-control" name="" tabindex="4"
											onkeypress="HideError()" id=""
											 maxlength="25"/>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Identification Mark 2</label>
									<div class="col-xs-7">
									<input type="text" class="form-control" name="" tabindex="4"
											onkeypress="HideError()" id=""
											 maxlength="25"/>
										
									</div>
								</div>
							</div>
							
							<div class="row">
							<div class="col-md-12" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
							
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5 textar"
										style="text-align: right;width:21%;">Name Of the School To Join</label>
									<div class="col-xs-7">
									<textarea style="border: 1px solid #ccc;" rows="2" cols="113"></textarea>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5 textar"
										style="text-align: right;width:21%;">School/Board Annual Exam Last Taken With Result</label>
									<div class="col-xs-7">
									<textarea style="border: 1px solid #ccc;" rows="3" cols="113"></textarea>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5 textar"
										style="text-align: right;width:21%;">Whether Failed in Once/Twice in the Same</label>
									<div class="col-xs-7">
									<textarea style="border: 1px solid #ccc;" rows="3" cols="113"></textarea>
										
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5 textar"
										style="text-align: right;width:21%;">Game Played or Extra Curriculum Activities</label>
									<div class="col-xs-7">
									<textarea style="border: 1px solid #ccc;" rows="3" cols="113"></textarea>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5 textar"
										style="text-align: right;;width:21%;">Whether NCC Cadet/Boy Scout/Girl/Guide/Details</label>
									<div class="col-xs-7">
									<textarea style="border: 1px solid #ccc;" rows="3" cols="113"></textarea>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5 textar"
										style="text-align: right;width:21%;">Subject Studies</label>
									<div class="col-xs-7">
									<textarea style="border: 1px solid #ccc;" rows="3" cols="113"></textarea>
										
									</div>
								</div>
								
								<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
									<input type="text" name="schoolLocation" tabindex="1"
											onkeypress="HideError()" id="schoolLocationId"
											maxlength="25" class="form-control" />
									</div>
								</div>
									</div>
								<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Elective Subject</label>
									<div class="col-xs-7">
									<input type="text" name="schoolLocation" tabindex="1"
											onkeypress="HideError()" id="schoolLocationId"
											maxlength="25" class="form-control" />
									</div>
									</div>
								</div>
						
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5 textar"
										style="text-align: right;width:21%;">Remarks</label>
									<div class="col-xs-7">
									<textarea style="border: 1px solid #ccc;" rows="3" cols="113"></textarea>
										
									</div>
								</div>
							
								<br />
								</div></div>
						</div>
						
		</form>
	</div>
</html>
