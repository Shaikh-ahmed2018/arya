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

<script type="text/javascript" src="JS/backOffice/Election/voterDetails.js"></script>

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
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">Voter Details</span></p>
		
<!-- pop up -->
		
		
		<div id="admissionDialog" style="display: none">
		
		
		
			<div class="col-md-12">
				<p style="margin-left: 29px;">
				
				</p>
				
				<div class="col-md-12"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">

					<div class="form-group clearfix">
						<label for="inputPassword" class="control-label col-xs-5"
							style="text-align: left;">Select Machine</label>
						<div class="col-xs-7">
								<select id="stream" name="stream" class="form-control"
											required>
											<option value="">---Select---</option>
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
					
					
				</div>

			</div>


		</div>
		
		
		
		
		
<!-- popup end -->
	
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: middle;"> 
							<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Individual Voter Details
							</h4></a>
						
				<div class="navbar-right">
					
					<a class="btn" data-popup-open="popup-2" href="#" style="position: absolute;margin-left:-130px;margin-top:-7px;">
					<span id="popUp"  class="buttons" >Assign Machine</span></a>
					<span style="margin-left:5px" id="back" class="buttons" style="margin-bottom:-30px;">Back</a></span>
				</div>
				</div>

		<logic:present name="voterDetails" scope="request"> 
				<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
			
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""  class="form-control" 
											value='<logic:present name="yearName"  scope="request"><bean:write name="yearName" /></logic:present>' />
											
										<input type="hidden" name="yearIdHidden" id="yearIdHidden"
									value='<logic:present name="yearIdHidden" scope="request"><bean:write name="yearIdHidden" /></logic:present>' />		
										
									</div>
								</div>

								
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<input type="text" id="location" name="location" onkeypress="HideError()" class="form-control" 
										value='<logic:present name="location" scope="request"><bean:write name="location" /></logic:present>' />
									</div>
								</div>
							
							
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" id="studentName" name="studentName" onkeypress="HideError()" class="form-control" 
										value='<logic:present name="studentName" scope="request"><bean:write name="studentName" /></logic:present>' />	
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission	No</label>
									<div class="col-xs-7">
										<input type="text" id="admissionNo" name="admissionNo" onkeypress="HideError()" class="form-control" 
										value='<logic:present name="admissionNo" scope="request"><bean:write name="admissionNo" /></logic:present>' />	
									</div>
								</div>
								 
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Roll Number</label>
									<div class="col-xs-7">
										<input type="text" id="rollNo" name="rollNo" onkeypress="HideError()" class="form-control" 
										value='<logic:present name="rollNo" scope="request"><bean:write name="rollNo" /></logic:present>' />	
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" id="className" name="className" onkeypress="HideError()" class="form-control" 
										value='<logic:present name="className" scope="request"><bean:write name="className" /></logic:present>' />	
									</div>
								</div>
								
								 <div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Verify</label>
									<div class="col-xs-7">
										<input type="text" id="verify" name="verify" onkeypress="HideError()" class="form-control" 
										value='<logic:present name="" scope=""><bean:write name="" /></logic:present>' />	
									</div>
								</div>
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 130px;">
												<img id="imagePreview" class="setImage" alt="image" src="#" style="height: 45mm; width: 45mm;">
												<div style="position: absolute;top: 0;height: 160px;">
												<input type="file" id="studentImageId1" name="studentImage" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;" 
												value='<logic:present name="imageUrl" scope="request"><bean:write name="imageUrl" /></logic:present>' />
												</div>
												<span id="removeSpanId" class="close" style="position: absolute; top: 0px; right: 100px;color: red;" >X</span>
										</div>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student Status</label>
									<div class="col-xs-7">
										<input type="text" id="status" name="status" onkeypress="HideError()" class="form-control" 
										value='<logic:present name="status" scope="request"><bean:write name="status" /></logic:present>' />	
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House</label>
									<div class="col-xs-7">
										<input type="text" id="house" name="house" onkeypress="HideError()" class="form-control" 
										value='<logic:present name="house" scope="request"><bean:write name="house" /></logic:present>' />	
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Section</label>
									<div class="col-xs-7">
										<input type="text" id="sectionName" name="sectionName" onkeypress="HideError()" class="form-control" 
										value='<logic:present name="sectionName" scope="request"><bean:write name="sectionName" /></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Voting Status</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
							</div>
		
		
				</div>
				
</div>
</logic:present>
				
				
				
				
			</div>
		</div>
	</div>
</html>


	
