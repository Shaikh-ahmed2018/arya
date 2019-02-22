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
<script type="text/javascript" src="JS/backOffice/Student/staffSingleIDCardPrint.js"></script> 

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
.slideTab{
	padding:20px;
	font-family: Roboto Medium;
    font-size: 14px;
    font-weight: lighter;
}
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
<input type="hidden" id="templatename" value='<logic:present name="template" scope="request"><bean:write name="template" /></logic:present>' />
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">Print Staff ID Card - Single</span></p>
		<div id="admissionDialog" style="display: none">
			<div id="admissionclose" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				</div>
				<br/>
			</div>
		</div>
	
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: middle;"> 
							<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Staff Details
							</h4></a>
						


						
						<div class="navbar-right">
					<span class="buttons" id="printsingle">Print</span>
					<span style="margin-left:5px" id="back" class="buttons">Back</a></span>
					</div>
				</div>

				
			<logic:iterate id="staffDetails" name="staffDetails">
					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
							
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
										<div class="col-xs-7">
								<input  type="text" id="academicYearNmae" name="tempale" class="form-control" readonly="readonly"
								value='<logic:present name="staffDetails"><bean:write name="staffDetails" property="academicYear"/></logic:present>' />
									</div>
								</div>
								
								
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
										<div class="col-xs-7">
									<input  type="text" id="schoolNameTemplate" name="tempale"  class="form-control" readonly="readonly" 
									value='<logic:present name="staffDetails"><bean:write name="staffDetails" property="locationName"/></logic:present>' />
											
									</div>
								</div>
							
							
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 35px;">Staff Name</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name="staffDetails"><bean:write name="staffDetails" property="teacherName"/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Staff Number</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="" tabindex="4" onkeypress="HideError()" id=""
											onchange="" maxlength="25"
											value='<logic:present name="staffDetails"><bean:write name="staffDetails" property="teacherID"></bean:write></logic:present>' />
									</div>
								</div>
								 
		
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Department</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name="staffDetails"><bean:write name="staffDetails" property="departmentName"/></logic:present>' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Mobile Number</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name="staffDetails"><bean:write name="staffDetails" property="mobile"/></logic:present>' />
									</div>
								</div>
								
								 <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Address</label>
									<div class="col-xs-7">
										<input type="text"style="height:90px" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name="staffDetails"><bean:write name="staffDetails" property="address"/></logic:present>' />
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
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Staff Status</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name="staffDetails"><bean:write name="staffDetails" property="status"/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Designation</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name="staffDetails"><bean:write name="staffDetails" property="designationName"/></logic:present>' />
									</div>
								</div>
								 
								
								
								
			
						
							</div>
						</div>

				</div>
				<div id="studentpreview" style="display: none;">
				<section class="col-md-4 section">
				
				<div class="main-div transport" id="main-div" >
			 <img src='images/IdCard/<logic:present name="template" scope="request" ><bean:write name="template" /></logic:present>.jpg' width="100%" height="100%" id="layoutImage" />
			  <header>
				<div class="schoolName" id="schoolName"><span id="schoolNameChange"><logic:present name="studentSearchList"><bean:write name="studentSearchList" property="location"/></logic:present></span></div>
			</header>
			
				<div class="photo" id="photo">
					<img src='' alt="image" width="100%" height="100%" />
				</div>
				<div class="namediv" id="namediv">
					<span class="name label" id="name">Staff Name : </span><span id="nametext"><logic:present name="staffDetails"><bean:write name="staffDetails" property="teacherName"/></logic:present></span>
				</div>
				<div class="classDivision" id="classDivision">
							<span class="label" id="classlebel">Staff ID:</span><span id="classtext"><logic:present name="staffDetails"><bean:write name="staffDetails" property="teacherID"/></logic:present></span>
						</div>
				<div class="point" id="point">
					<span class="label">Department:</span><span id="pointr" class="pointr"><logic:present name="staffDetails"><bean:write name="staffDetails" property="departmentName"/></logic:present></span>
				</div>
				

					<div class="routeNo" id="routeNo">
					<div class="routetext"><span id="routeh" class="routeh">Desigantion</span></div>
						<span id="route" class="route"><logic:present name="staffDetails"><bean:write name="staffDetails" property="designationName"/></logic:present></span>
					</div>

				
			</div>
			</section>
				</div>
				</logic:iterate>
				
				
				
				
				
				
			</div>
		</div>
	</div>
</html>


	
