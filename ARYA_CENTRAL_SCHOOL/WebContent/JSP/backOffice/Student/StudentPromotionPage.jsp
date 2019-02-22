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
	src="JS/backOffice/Student/StudentPromotionPage.js"></script>
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

</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">Student Promotion</span></p>
			
			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"></span></a>
				</div>
			</div>
			
			<div class="errormessagediv1" style="display: none;" align="center">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips1"></span></a>
				</div>
			</div>
		
			<div class="errormessagediv" style="display: none;" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>
													
			<!-- chiru -->

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Student Promotion</h4></a>
						

						<div class="navbar-right">
							<span class="buttons" id="save">Save</span>
			 				<span class="buttons" id="back">Back</span>
						</div>
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
						<logic:iterate id="studentPromotionSetting" name="studentPromotionSetting">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
							
							<input type="hidden" id="studentid" name="studentId"
								value="<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="studentId"/></logic:present>" />
							
							<input type="hidden" id="hiddenClassId" name="hiddenClassId"
								value="<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="classcode"/></logic:present>" />
								
							<input type="hidden" id="hiddenSectionId" name="hiddenSectionId"
								value="<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="sectioncode"/></logic:present>" />
								
							<input type="hidden" id="hiddenSpecilizationId" name="hiddenSpecilizationId"
								value="<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="specilization"/></logic:present>" />
								
							<input type="hidden" id="hiddenFromAccyearId" name="hiddenFromAccyearId"
								value="<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="currentAccyearId"/></logic:present>" />
							
							<input type="hidden" id="hiddenToAccyearId" name="hiddenToAccyearId"
								value="<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="academicYearId"/></logic:present>" />
								
							<input type="hidden" id="hiddenLocationId" name="hiddenLocationId"
								value="<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="locationId"/></logic:present>" />
								
							<input type="hidden" id="hiddenPhotoId" name="hiddenPhotoId"
								value="<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="studentPhoto"/></logic:present>" />
							
							
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="academicYear" tabindex="1"	onkeypress="HideError()" id="academicYear"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="academicYear"/></logic:present>' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<input type="text" name="schoolName" tabindex="1"	onkeypress="HideError()" id="schoolName"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="location"/></logic:present>' />
									</div>
								</div>
								
								
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentFullName" tabindex="1"	onkeypress="HideError()" id="studentFullName"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="studentFullName"/></logic:present>' />
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission	No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="admissionNo" tabindex="4" onkeypress="HideError()" id="admissionNo"
											onchange="" maxlength="25" readonly="readonly" 
											value='<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="studentAdmissionNo"></bean:write></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">House</label>
									<div class="col-xs-7">
										<input type="text" name="houseId" tabindex="1"	onkeypress="HideError()" id="houseId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="houseName"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Roll No</label>
									<div class="col-xs-7">
										<input type="text" name="studentRollNo" tabindex="1"	onkeypress="HideError()" id="studentRollNo"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="studentrollno"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Status</label>
									<div class="col-xs-7">
									<select class="form-control" onkeypress="HideError()" tabindex="8" name="" id="statusId" style="color: white;font-weight: 700;">
											<option value="promoted" style="background-color:green;">PROMOTED</option>
											<option value="demoted" style="background-color:red;">DEMOTED</option>
										</select>
										
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Specialization</label>
									<div class="col-xs-7">
										<input type="text" name="specId" tabindex="1"	onkeypress="HideError()" id="specId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="specilizationname"/></logic:present>' />
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
												<input id="studentImageId" name="studentImage" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;">
												</div>
										</div>
									</div>
								</div>
								 
								<div class="form-group clearfix" id="studentstatuslable">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student Status</label>
									<div class="col-xs-7">
										<input type="text" name="studentStatusId" tabindex="1"	onkeypress="HideError()" id="studentStatusId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="studentStatus"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
										<input type="text" name="classId" tabindex="1"	onkeypress="HideError()" id="classId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="classname"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix" >
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<input type="text" name="sectionId" tabindex="1"	onkeypress="HideError()" id="sectionId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="studentPromotionSetting"><bean:write name="studentPromotionSetting" property="sectionnaem"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix" id="newsectiondivid">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">New Division<font color="red">*</font></label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="8"	name="newdivision" id="newdivisionId">
											<option value=""></option>
											
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix" id="newspecdivid">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">New Specialization</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="8"	name="newspecilaization" id="newspecilaizationId">
											<option value=""></option>
											
										</select>
									</div>
								</div>
							</div>
							</logic:iterate>
							<div class="row">
							<div class="col-md-12" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;width:21%;">Comment</label>
									<div class="col-xs-7">
									<textarea style="border: 1px solid #ccc;" rows="3" cols="113" id="comments" name="comments"></textarea>
										
									</div>
								</div>
								<br />
								
								
								</div></div>
						</div>
						
	</div>
</html>
