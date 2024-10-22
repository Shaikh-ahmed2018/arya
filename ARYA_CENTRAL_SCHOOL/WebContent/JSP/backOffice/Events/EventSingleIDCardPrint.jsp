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

<script type="text/javascript" src="JS/backOffice/Events/EventSingleIDCardPrint.js"></script>
<script type="text/javascript" src="JS/qrcode.min.js"></script>
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
		<p><img src="images/addstu.png" /><span id="pageHeading">Print Event ID Card - Single</span></p>
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
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Student Details
							</h4></a>
						

					
						
						<div class="navbar-right">
					<span class="buttons" id="printsingle">Print</span>
					<span style="margin-left:5px" id="back" class="buttons">Back</span>
					</div>
					</div>
					
					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						
							<div class="panel-body own-panel">
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
							
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission	No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="admissionNo" tabindex="4" onkeypress="HideError()" id="admissionNo"
											onchange="" maxlength="25" readonly="readonly" 
											value='<logic:present name="admno"><bean:write name="admno"></bean:write></logic:present>' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<input type="text" name="schoolName" tabindex="1"	onkeypress="HideError()" id="schoolName"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="schlnm"><bean:write name="schlnm"/></logic:present>' />
									</div>
								</div>
							
							
									<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentFullName" tabindex="1"	onkeypress="HideError()" id="studentFullName"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="stunm"><bean:write name="stunm" /></logic:present>' />
									</div>
								</div>
								 
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" name="classId" tabindex="1"	onkeypress="HideError()" id="classId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="cls"><bean:write name="cls"/></logic:present>' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">RollNo</label>
									<div class="col-xs-7">
										<input type="text" name="sectionId" tabindex="1"	onkeypress="HideError()" id="sectionId"
											maxlength="25" class="form-control" readonly="readonly"  
											value='<logic:present name="rolno"><bean:write name="rolno"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Event Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentStatusId" tabindex="1"	onkeypress="HideError()" id="studentStatusId"
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="evenm"><bean:write name="evenm"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Event Category</label>
									<div class="col-xs-7">
										<input type="text" name="studentStatusId" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="evecat"><bean:write name="evecat"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Programme Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentStatusId" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" readonly="readonly" 
											value='<logic:present name="prognm"><bean:write name="prognm"/></logic:present>' />
									</div>
								</div>
								
															
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
												<img id="imagePreview" class="setImage" alt="image" src='<logic:present name="img"><bean:write name="img"/></logic:present>' style="height: 45mm; width: 45mm;">
												<div style="position: absolute;top: 0;height: 160px;">
												</div>
										</div>
									</div>
								</div>
								
							</div>
						</div>

				</div>
				
				<div id="studentpreview" style="display: none;">
				<section class="col-md-4 section" >
			<div class="main-div" id="main-div" >
			 <img src='images/IdCard/<logic:present name="img" scope="request" ><bean:write name="img" /></logic:present>.jpg' width="100%" height="100%" id="layoutImage" />
			  <header>
				<div class="schoolName" id="schoolName"><span id="schoolNameChange"><logic:present name="schlnm"><bean:write name="schlnm" /></logic:present></span></div>
				<!-- <div class="branch" id="branch"><span id="branchName">PATTOM,THIRUVANANTHAPURAM</span></div>
				<div class="phone" id="phone"><span id="teleName">Tel.1234567</span></div> -->
			</header>
				<div class="namediv" id="namediv">
					<span class="name label" id="name">Name : </span><span id="nametext"><logic:present name="stunm"><bean:write name="stunm"/></logic:present></span>
				</div>


				<div class="photo" id="photo">
					<img src='<logic:present name="img"><bean:write name="img"/></logic:present>' alt="image" width="100%" height="100%" />
				</div>


				
					<div class="studentdetails" id="studentdetails">
						<div class="classDivision" id="classDivision">
							<span class="label" id="classlebel">Class<span class="column">:</span></span><span id="classtext"><logic:present name="cls"><bean:write name="cls"/></logic:present></span>
						</div>
						<div class="admission" id="admission">
							<span class="label" id="admNo">Adm No.<span class="column">:</span></span><span id="admNotext"><logic:present name="admno"><bean:write name="admno"></bean:write></logic:present></span>
						</div>
						<div class="house" id="house">
							<span class="label" id="houNo">Roll No.<span class="column">:</span></span><span id="houNotext"><logic:present name="rolno"><bean:write name="rolno"></bean:write></logic:present></span>
						</div>
						<div class="valid" id="valid">
							<span class="label" id="eventName">Event Name<span class="column">:</span></span><span id="eventname"><logic:present name="evenm"><bean:write name="evenm"></bean:write></logic:present></span>
						</div>
						<div class="valid" id="valid">
							<span class="label" id="eventCat">Event Category<span class="column">:</span></span><span id="eventcat"><logic:present name="evecat"><bean:write name="evecat"></bean:write></logic:present></span>
						</div>
						<div class="valid" id="valid">
							<span class="label" id="progName">Program Name<span class="column">:</span></span><span id="programname"><logic:present name="prognm"><bean:write name="prognm"></bean:write></logic:present></span>
						</div>
						
					</div>
				
			</div>
			</section>
			</div>
			
<textarea id="printing-css-a4" style="display:none;">
.col-md-4 {width:32.5%;}
.col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9 {
    float: left;
}
.col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
    position: relative;
    min-height: 1px;
    padding-right: 10px;
    padding-left: 10px;
}

body {-webkit-print-color-adjust: exact;margin:0;padding:0;}

</textarea>
			</div>
		</div>
	</div>
</html>


	
