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
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="CSS/Admin/StudentNew.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Student/individualStudentSearch.js"></script>

<style>
#allstudent th:nth-child(1) {
    text-align: center;
    width: 70px;
} 
.table {
    width: 70%;
    max-width: 100%;
    margin-bottom: 20px;
    margin: 0 auto;
}
.form-control[readonly] {
    background-color: #fff;
    opacity: 1;
}
</style>

</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">Student Search</span></p>
				<div class="panel-body clearfix"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				</div>
	
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Student Search
							</h4></a>
						
						
						<div class="navbar-right">
							<span class="search"  style="top:0px">
							<label style="text-align: right;color:#767676;font-family:inherit;font-weight: 500;font-size: 16px;padding: 4px;">Select Action</label>	 
							 				<select class="nil" style="color:#767676;font-family:inherit;font-size: 14px;padding: 2px">
							 					<option value="">-----Select-----</option>
							 					<option value="adm_Form">Registration Form</option>
							 					<option value="conf_Report">Disciplinary Action</option>
							 					<option value="id_Card">Single ID Card</option>
							 					<option value="mis_Report">MIS Report</option>
							 					<!-- <option value="tc">Transfer Certificate</option> -->
											 </select>
							</span>
							<span class="buttons" id="goPage" class="save">Go</span>
							<span id="back" class="buttons">Back</a></span>
						</div>
					</div>
					<logic:iterate id="studentSearchList" name="studentSearchList">
					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
							
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="academicYear" tabindex="1"	onkeypress="HideError()" id="academicYear"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="academicYear"/></logic:present>' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<input type="text" name="schoolName" tabindex="1"	onkeypress="HideError()" id="schoolName"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="location"/></logic:present>' />
									</div>
								</div>
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentFullName" tabindex="1"	onkeypress="HideError()" id="studentFullName"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentFullName"/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission	No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="admissionNo" tabindex="4" onkeypress="HideError()" id="admissionNo"
											onchange="" maxlength="25" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentAdmissionNo"></bean:write></logic:present>' />
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Roll No</label>

									<div class="col-xs-7">
										<input type="text" name="studentRollNo" tabindex="1"	onkeypress="HideError()" id="studentRollNo"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentrollno"/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" name="classId" tabindex="1"	onkeypress="HideError()" id="classId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="classname"/></logic:present>' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<input type="text" name="sectionId" tabindex="1"	onkeypress="HideError()" id="sectionId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="sectionnaem"/></logic:present>' />
									</div>
								</div>
								
								 <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Bus Route </label>
									<div class="col-xs-7">
										<input type="text" name="routeId" tabindex="1"	onkeypress="HideError()" id="routeId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="route"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Bus Boarding Point</label>
									<div class="col-xs-7">
										<input type="text" name="stageId" tabindex="1"	onkeypress="HideError()" id="stageId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="stage_name"/></logic:present>' />
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
										</div>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student Status</label>
									<div class="col-xs-7">
										<input type="text" name="studentStatusId" tabindex="1"	onkeypress="HideError()" id="studentStatusId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentStatus"/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Disciplinary Action</label>
									<div class="col-xs-7">
										<input type="text" name="confidentialStatusId" tabindex="1"	onkeypress="HideError()" id="confidentialStatusId" 
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="confidentialStatus"/></logic:present>' />
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House</label>
									<div class="col-xs-7">
										<input type="text" name="houseId" tabindex="1"	onkeypress="HideError()" id="houseId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="houseName"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Second Language</label>
									<div class="col-xs-7">
										<input type="text" name="secondLanguageId" tabindex="1"	onkeypress="HideError()" id="secondLanguageId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="secondLanguage"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Third Language</label>
									<div class="col-xs-7">
										<input type="text" name="thirdLanguageId" tabindex="1"	onkeypress="HideError()" id="thirdLanguageId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="thirdLanguage"/></logic:present>' />
									</div>
								</div>
								
								<input type="hidden" id="hstudentid" name="studentId"
									value="<logic:present name="studentSearchList" property="studentId"><bean:write name="studentSearchList" property="studentId"/></logic:present>"/>
									
								<input type="hidden" id="hacademicYearId" name="academicYearId"
									value="<logic:present name="studentSearchList" property="studentId"><bean:write name="studentSearchList" property="academicYearId"/></logic:present>"/>
									
								<input type="hidden" id="hschoolNameId" name="schoolNameId"
									value="<logic:present name="studentSearchList" property="studentId"><bean:write name="studentSearchList" property="locationId"/></logic:present>"/>
								
								<input type="hidden" id="photohiddenid" name="previousImage"
									value="<logic:present name="studentSearchList" property="studentPhoto"><bean:write name="studentSearchList" property="studentPhoto" /></logic:present>">
							</div>
						</div>
						</logic:iterate>
						<hr style="height:1px;border:none;color:#333;background-color:#ddd;"/>
					
					
						<div>
						<div class="slideTab clearfix">
						<div class="tab">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#contacts"  id="contacts">Contacts</a></li>
								<li><a data-toggle="tab" href="#classHistory" id="classHistory">Class History</a></li>
							<li><a data-toggle="tab" href="#ContactAddr" id="ContactAddr">Address</a></li>
							</ul>
						
							<div id="contacts" class="tab-pane">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap">
									<div class="col-md-8" id=div2></div>
									<div id="studenttable"></div>
									<div id="individualstudenttable"></div>	
									<div id="Addressstudenttable"></div>	
								</div>
								</div>
							</div>
							
						</div>
					</div>
					</div>
					
					
					
				
				
				
			</div>
		</div>
	</div>
</div>
</html>


	
