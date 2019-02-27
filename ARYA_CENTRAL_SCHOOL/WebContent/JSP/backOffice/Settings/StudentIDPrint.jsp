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
<link href="CSS/IdCard/IdCard.css" rel="stylesheet" type="text/css" />
<link href="CSS/newUI/custome.css" rel="stylesheet">
<script type="text/javascript" src="JS/spectrum.js"></script>
<script type="text/javascript" src="JS/backspectrum.js"></script>
<script type="text/javascript" src="JS/qrcode.min.js"></script>
<link href='CSS/IdCard/<logic:present name="templateClass" scope="request" ><bean:write name="templateClass" /></logic:present>.css' rel="stylesheet" type="text/css" />

<link href="CSS/spectrum.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="JS/backOffice/Settings/StudentIdCreation.js"></script>

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
#main-div div#parentDetail{
    position: relative !important;
    width: 100%;
    top: -30px;
}
.frnt-bck{
position: relative;
top: -10px;
left: 10px;
}
.main-div.idBack{
display: none;
}
</style>

</head>



<body>

<input type="hidden" id="layoutDetails" value='<logic:present name="templateClass" scope="request" ><bean:write name="templateClass" /></logic:present>' />
	<div class="col-md-10 col-md-offset-2" style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
	<p>
		<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Design Student ID Card</span>
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
		

		
			<p id="parent1" style="display: none;">
				<a href="">Expand all</a>
			</p>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">

					<div class="panel-heading" role="tab" id="headingOne" >

						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title" id="beforeparent" style="color: #767676;"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Student Details
							</h4></a>
						
				<div class="navbar-right">
					
					<span style="margin-left:5px" id="back" class="buttons">Back</span>
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
										id="inputnames">Stream</label>
									<div class="col-xs-7">
								<input  type="text" id="streamName" name="tempale"  class="form-control" readonly="readonly" 
								value='<logic:present name="template"  scope="request" ><logic:iterate id="template" name="template" ><bean:write name="template" property="streamName" /></logic:iterate></logic:present>' />
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
		<span class="buttons frnt-bck" id="idfront" >Front</span>
		<span class="buttons frnt-bck" id="idback">Back</span>		
				
			<div class="navbar-right">
			<label class="idCardDesignListDropDownlabel" >Select Previous Design</label><span class="idCardDesignListDropDownSpan"><select id="idCardDesignListDropDown"><option value="">-------Select-----</option></select></span> <span class="buttons" id="saveChanges" style="top:4px;">Save</span>
				</div>
				
				
			</div>	
		<section class="col-md-4 section">
			<div class="main-div idFront" id="main-div" >
			 <img src='images/IdCard/<logic:present name="templateClass" scope="request" ><bean:write name="templateClass" /></logic:present>.jpg' width="100%" height="100%" id="layoutImage" />
			  <header>
				 <div class="logodiv" id="logodiv">
			 	 <img src="./images/school-logo.png" width="100%" height="100%"  />
				</div>	
				<div class="schoolName" id="schoolName"><span id="schoolNameChange"><logic:present name="locationNmae"  scope="request" ><bean:write name="locationNmae" /></logic:present></span></div>
				<div class="branch" id="branch"><span id="branchName"><logic:present name="locationAddress" scope="request"><bean:write name="locationAddress" /></logic:present></span></div>
				<div class="phone" id="phone"><span id="teleName">Tel.<logic:present name="locationPhone" scope="request"><bean:write name="locationPhone" /></logic:present></span></div>
			</header>
				<div class="namediv" id="namediv">
					<span class="name label" id="name">Name : </span><span id="nametext">Kamran Ansari</span>
				</div>


				<div class="photo" id="photo">
					<img src="./images/profile_photo.png" alt="image" width="100%" height="100%" />
				</div>

					<div class="studentdetails" id="studentdetails">
						<div class="classDivision" id="classDivision">
							<span class="label" id="classlebel">Class/Div<span class="column">:</span></span><span id="classtext">Pre-KG/A</span>
						</div>
						<div class="admission" id="admission">
							<span class="label" id="admNo">Adm No.<span class="column">:</span></span><span id="admNotext">123456</span>
						</div>
						<div class="house" id="house">
							<span class="label" id="houNo">House<span class="column">:</span></span><span id="houNotext">Red</span>
						</div>
						<div class="valid" id="valid">
							<span class="label" id="validUpto">Valid Upto<span class="column">:</span></span><span id="validUptotext">2017</span>
						</div>
					</div>
	
					<div class="dob" id="dob"><span class="label" id="doblabel">D.O.B<span class="column">:</span></span><span id="dobtext">12-05-2000</span></div>
					<div class="bgroup" id="bgroup"><span class="label" id="bgrouplabel">B Group<span class="column">:</span></span><span id="bgrouptext">O (+)</span></div>

					<div class="addressdetails" id="address">
						<span class="addresss" id="addresslebel">Address: </span> <span id="addresstext">Bangalore</span>
					</div>


					<div class="phonedetails" id="phonedetails">
						<span class="phones" id="phonelebel">Ph: </span><span id="phonetext">9999999999</span>
					</div>
					<div class="qrdetails" id="qrdetails">
						
					</div>
					<div class="parents" id="parents">
						<span class="label" id="patentlabel">Parents:</span><div class="parentDetail" id="parentDetail"><span id="faterName" style="display:block;">test Name</span><span style="display:block;"><span id="motherName">test Name</span></span></div>
						<div class="emerencyNodiv" id="emerencyNodiv"><span class="label" id="emergency">Emergency No:</span><span id="emergencyNo">9999999999</span></div>
					</div>
				
			</div>
			
			<div class="main-div idBack" >
		<img src='images/IdCard/<logic:present name="templateClass" scope="request" ><bean:write name="templateClass" /></logic:present>_bck.jpg' width="100%" height="100%" id="layoutImagebck" />
					
						<div class="classDivision-bck" id="classDivision-bck">
							<span class="label" id="classlebel-bck">Class/Div<span class="column">:</span></span><span id="classtext-bck">Pre-KG/A</span>
						</div>
						<div class="admission-bck" id="admission-bck">
							<span class="label" id="admNo-bck">Adm No.<span class="column">:</span></span><span id="admNotext-bck">123456</span>
						</div>
						<div class="house-bck" id="house-bck">
							<span class="label" id="houNo-bck">House<span class="column">:</span></span><span id="houNotext-bck">Red</span>
						</div>
						<div class="valid-bck" id="valid-bck">
							<span class="label" id="validUpto-bck">Valid Upto<span class="column">:</span></span><span id="validUptotext-bck">2017</span>
						</div>
					



					<div class="addressdetails-bck" id="address-bck">
						<span class="addresss-bck" id="addresslebel-bck">Address: </span> <span id="addresstext-bck">Bangalore</span>
					</div>


					<div class="phonedetails-bck" id="phonedetails-bck">
						<span class="phones-bck" id="phonelebel-bck">Ph: </span><span id="phonetext-bck">9999999999</span>
					</div>
					<div class="qrdetails-bck" id="qrdetails-bck">
						
					</div>
					<div class="parents-bck" id="parents-bck">
						<span class="label" id="patentlabel-bck">Parents:</span><div class="parentDetail-bck" id="parentDetail-bck"><span id="faterName-bck" style="display:block;">test Name</span><span style="display:block;"><span id="motherName-bck">test Name</span></span></div>
						
					</div>
				<div class="emerencyNodiv-bck" id="emerencyNodiv-bck"><span class="label" id="emergency-bck">Emergency No:</span><span id="emergencyNo-bck">9999999999</span></div>
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
      <li id="changeBackgroundImage" >Layout Front Background Image</li>
      <li id="changeBackgroundImageBack" >Layout Back Background Image</li>
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



</body>


</html>