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
	src="JS/backOffice/Student/GetStudentConfidential.js"></script>
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


</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">Confidential Report > New > Find Student</span></p>
		
	
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
							<h4 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Find Student</h4></a>
						
						
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<option value="all">----------Select----------</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="">----------Select----------</option>
										</select>
									
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search By</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" 
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>
								
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
											<option value="all">----------Select----------</option>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
							
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="">----------Select----------</option>
										</select>
									</div>
								</div>
							
								
								<p align="center"style="margin-left:17%">
								<button type="button" class="btn btn-info" id="search" >Search</button>
								<button type="reset" class="btn btn-info" id="resetbtn" >Reset</button></p>
								
							</div>
							
							<!-- <div id ="studentlisttable"></div> -->
					<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
							
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
					<display:table class="table" id="allstudent"
						name="requestScope.studentList" 
						requestURI="/adminMenu.html?method=getStudentConfidentialReport?">

						<display:column property="count" sortable="true"
							title="Sl.No.<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="studentAdmissionNo" class="${allstudent.studentId} ${allstudent.academicYearId} ${allstudent.locationId} studentid" sortable="true"	title="Admission No"/>

						<display:column property="studentnamelabel" sortable="true"
							title="Student Name <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
							
						<display:column property="studentrollno" sortable="true"
							title="Roll No <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="classname" sortable="true"
							title="Class<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="sectionnaem" sortable="true"
							title="Division <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
							
						 <display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>
						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty> 
							
					</display:table>
		

				
				</div>
				<br />
			</div>
					
							
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</html>
