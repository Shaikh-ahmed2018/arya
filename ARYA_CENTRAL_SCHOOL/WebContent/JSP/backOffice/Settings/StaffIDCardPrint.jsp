<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<title>eCampus Pro</title>

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="CSS/newUI/custome.css" rel="stylesheet">

<script type="text/javascript" src="JS/backOffice/Settings/StaffIdCreation.js"></script>
<script type="text/javascript" src="JS/spectrum.js"></script>
<script type="text/javascript" src="JS/backspectrum.js"></script>
<link href="CSS/IdCard/StaffId/StaffIdCard.css" rel="stylesheet" type="text/css" />

<link href='CSS/IdCard/StaffId/<logic:present name="templateClass" scope="request" ><bean:write name="templateClass" /></logic:present>.css' rel="stylesheet" type="text/css" />

<link href="CSS/spectrum.css" rel="stylesheet" type="text/css" />


<style>
.modal-body {
	text-align: center;
}
span.idCardDesignListDropDownSpan{
position: relative;
top:4px;
color:#767676;
font-family:inherit;
font-size: 14px;
padding: 2px
}
label.idCardDesignListDropDownlabel{
position: relative;
top:-10px;;
color:#767676;
font-family:inherit;
font-size: 14px;
padding: 2px
}
#logodiv{
width: 30px;
height: 30px;
position: absolute;
z-index: 99999;
 background: transparent;
}
</style><style>

</style>
</head>



<body>

<input type="hidden" id="layoutDetails" value='<logic:present name="templateClass" scope="request" ><bean:write name="templateClass" /></logic:present>' />
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">


		
	<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Design Staff ID Card</span>
			</p>
		
		

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
						class="successmessage"></span></a>
				</div>
			</div>
		

			
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">

					<div class="panel-heading" role="tab" id="headingOne" >

						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title" id="beforeparent" style="color: #767676;"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Staff Details
							</h4></a>
						

						
						
							<div class="navbar-right">
					
					
					
					<span style="margin-left:2px"class="buttons" id="back">Back</span> 
				</div>
					</div>
					<!-- pop up -->



					<!-- Filters -->

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt" style="padding: 15px;">

							<div class="col-md-6" id="txtstyle">
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">School Name</label>
									<div class="col-xs-7">
									<input  type="text" id="schoolNameTemplate" name="tempale"  class="form-control" readonly="readonly" value='<logic:present name="locationNmae"  scope="request" ><bean:write name="locationNmae" /></logic:present>' />
									</div>
								</div>
									
									
									
									
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Designation</label>
									<div class="col-xs-7">
								<input  type="text" id="streamName" name="tempale"  class="form-control" readonly="readonly"  value='<logic:present name="departmentName"  scope="request" ><bean:write name="departmentName"  /></logic:present>' />
									</div>
								</div>
									
								


							
							</div>
							<div class="col-md-6" id="txtstyle">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
								<input  type="text" id="academicYearNmae" name="tempale" class="form-control" readonly="readonly"
								 value='<logic:present name="accYearName"  scope="request" ><bean:write name="accYearName" /></logic:present>' />
									</div>
								</div>
								
								
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4" align="right"
										id="inputnames">Template Name</label>
									<div class="col-xs-7">
								<input  type="text" id="templateid" name="tempale" class="form-control" readonly="readonly" value="Student ID Card">
									</div>
								</div>
								
								
								
								
							</div>
							

							
 </div>
 </div>
 </div>
 </div>


		
				<div class="panel panel-primary clearfix">
			<div class="panel-heading">
				<h3 class="panel-title id-head" style="color: #767676;vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp; Design Layout
					</h3>

				
				
				
			<div class="navbar-right">
						<label class="idCardDesignListDropDownlabel" >Select Previous Design</label><span class="idCardDesignListDropDownSpan"><select id="idCardDesignListDropDown"><option value="">-------Select-----</option></select></span> <span class="buttons" id="saveChanges" style="top:4px;">Save</span>
				</div>
				
			</div>	
		<section class="col-md-4 section">
			<div class="main-div" id="main-div" >
			 <img src='images/IdCard/<logic:present name="templateClass" scope="request" ><bean:write name="templateClass" /></logic:present>.jpg' width="100%" height="100%" id="layoutImage" />
			  <header>
				<div class="schoolName" id="schoolName"><span id="schoolNameChange"><logic:present name="locationNmae"  scope="request" ><bean:write name="locationNmae" /></logic:present></span></div>
				<div class="branch" id="branch"><span id="branchName"><logic:present name="locationAddress"  scope="request" ><bean:write name="locationAddress" /></logic:present></span></div>
				<div class="phone" id="phone"><span id="teleName">Tel.<logic:present name="locationPhone"  scope="request" ><bean:write name="locationPhone" /></logic:present></span></div>
			</header>
				<div class="namediv" id="namediv">
					<span id="nametext">Staff Name</span>
				</div>


				<div class="photo" id="photo">
					<img src="images/profile_photo.png" alt="image" width="100%" height="100%" />
				</div>


				
					
						<div class="abbravative" id="abbravative">
							<span id="abbrativeText">Abbrivate Name</span>
						</div>
						<div class="registrationId" id="registrationId">
							<span id="regNotext">Department</span>
						</div>
						<div class="designation" id="designation">
							<span id="degIdtext">Desgination Name</span>
						</div>
						<div class="valid" id="valid">
							<span class="label" id="validUpto">Valid upto : </span><span id="validUptotext">2020</span>
						</div> 
					



					<div class="addressdetails" id="address">
						<span id="addresstext">Staff Address</span>
					</div>


					<div class="phonedetails" id="phonedetails">
						<span class="phones" id="phonelebel">Mob : </span><span id="phonetext">9999999999</span>
					</div>
					<div class="qrdetails" id="qrdetails">
						<img src="images/qr.jpg" width="100%" height="100%"/>
					</div>

				
			</div>
	<div  class="cntr">
    <ul id='items'>
    	<li class="headerfontSize">Header Font Size</li>
    	<li class="labelfontSize">Font Size</li>
       <li class="changeColor">Font Color</li>
       <li class="labelBackgroundColor">Label Background Color</li>
      <li class="changeBackgroundColor">Box Background Color</li>
      <li class="changeBorderColor">Box Border Color</li>
      <li id="boxCorner0" >Box Corner Curve-0</li>
      <li id="boxCorner2" >Box Corner Curve-2</li>
      <li id="boxCorner4" >Box Corner Curve-4</li>
      <li id="boxCorner6" >Box Corner Curve-6</li>
      <li id="boxCorner8" >Box Corner Curve-8</li>
      <li id="changeBackgroundImage" >Layout Background Image</li>
    	<li id="restoreSize" > Vertical Id Card Size</li>
      <li id="restoreHSize" > Horizontal Id Card Size</li>
    </ul>
  </div>
  <input id="backgroundfile" type="file" style="display: none;" />
  <select id="hfontval" style="display:none;"></select>
  <select id="lfontval" style="display:none;"></select>
		</section>
		</div>
 </div>
 


	<span>&nbsp;</span>


	<!-- jQuery -->
	<script src="js/jquery.js"></script>


	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>


	<script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>

</body>


</html>
