<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script> 

<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript" src="JS/backOffice/Transport/DriverEntryPage.js"></script> 

<!-- <script >
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
	$(document).scroll(function() {
		var y = $(this).scrollTop();
		if (y > 100) {
			$('.topimg').fadeIn();
		} else {
			$('.topimg').fadeOut();
		}
	});
</script>
<script>
	$('.carousel').carousel({
		interval : 5000
	//changes the speed
	})
</script> -->

<style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="driverdetails" scope="request"><bean:write name="driverdetails"></bean:write></logic:present></span>
		</p>
		
		
				<!-- <div class="successmessagediv" align="center" style="display: none;">
						<div class="message-item">
							Warning
							<a href="#" class="msg-success bg-msg-succes"><span
								class="validateTips"></span></a>
						</div>
					</div>	 -->
					

					
				<div class="errormessagediv" align="center" style="display: none;">
					<div class="message-item">
					<!-- Warning -->
				    <a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
					</div>
					</div>	
				

			<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>
		
					<logic:present name="errormessagediv" scope="request">
		
						<div class="errormessagediv" align="center" style="display: none;">
					<div class="message-item">
					<!-- Warning -->
				    <a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span>
					<bean:write	name="errormessagediv" scope="request" /></a>
					</div>
					</div>	
							</logic:present>
					
				

		<form action="driverDetailsPath.html?method=savedriverval" id="driverformid" enctype="multipart/form-data"  method="post">
			<div class="panel-group"  role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676;vertical-align: text;"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Add New Driver
							</h4></a>
						

							<div class="navbar-right" >
							
									
									<span id="savedriver" class="buttons" style=" margin-right: -10px;"
									style="cursor: pointer">Save</span>&nbsp;
									
							
								<span class="buttons" id="back">Back</a></span>
							</div>
						
					</div>
					


			<input	type="hidden" name="drivercode" id="drivercode" value="<logic:present name="driverlist" ><bean:write name="driverlist" property="driverCode"/></logic:present>"/>
		    <input  type="hidden" name="vehiclecode" id="vehiclecode" value="<logic:present name="fuellist" ><bean:write name="fuellist" property="vehicleCode"/></logic:present>"/>
            <input type="hidden" name="fuelcode" id="fuelcode"  value="<logic:present name="fuellist" ><bean:write name="fuellist" property="fuelCode"/></logic:present>"/>
		    <input type="hidden" name="license" id="hlicensetodrive" value='<logic:present name="driverlist"><bean:write name="driverlist" property="license" /></logic:present>'></input>
		    <input type="hidden" name="licenseupload" id="hlicenseupload" value='<logic:present name="driverlist"><bean:write name="driverlist" property="licensedrive" /></logic:present>'></input>




			<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6" style="margin-top: 12px;"
								id="txtstyle">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Driver Name</label>
									<div class="col-xs-7">
									
											<input type="text" name="driverName" class="form-control" id="name" onkeypress="HideError()"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="driverName"/></logic:present>"/>
									</div>
								</div>
								<br />
                                
							<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										Date Of Birth</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control" onkeypress="HideError()"  name="dateofBirth" id="dateofBirthId" onchange="ageCalculateAdd();"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dateofBirth"/></logic:present>"/>
									</div>
								</div>
								
							    <br />
                                <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Gender</label>
									
									<div class="col-xs-7" id="radiostyle" style="margin-top: 8px">
									
									<input type="hidden" id="radio" value='<logic:present name="driverlist"><bean:write name="driverlist" property="gender" /></logic:present>'></input>
									
									<input type="radio" class="istrans" name="gender" id="genderM" value="Male" allign="center" /><label for="Gender">&nbsp;&nbsp;&nbsp;Male</label>
										
										<input type="radio" class="istrans" name="gender" id="genderF" value="Female" /><label for="Gender">&nbsp;&nbsp;&nbsp;Female </label>
									
<%-- 									<input type="hidden" name="updatevehicleCode" id="hdrivercode" 	value="<logic:present name="drivercode" ><bean:write name="drivercode" /></logic:present>" />
 --%>										<input type="hidden" name="driver_code" id="drivercode" value="<logic:present name="drivercode" ><bean:write name="drivercode" /></logic:present>" />
									
										<!-- <label class="radio-inline"><input type="radio"
											name="gender" id="genderM" value="Male" />Male</label> <label
											class="radio-inline"><input type="radio"
											name="gender" id="genderF" value="Female" />Female</label> -->
									</div>
								</div>
								
								 <br />
								 
                                <div class="form-group">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Emergency Contact No.</label>
									<div class="col-xs-7">
									<input type="text" name="emerg_contact" id="emerg_contact"  class="form-control" onkeypress="HideError()"  maxlength="10"
									 value="<logic:present name="driverlist" ><bean:write name="driverlist" property="emerg_contact"/></logic:present>" />
									</div>
								</div>
								<br>
								
								 
                               <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Experience</label>
										<div class="col-xs-7">
									<input type="text" name="experience" id="experience"  class="form-control" onkeypress="HideError()"
									 value="<logic:present name="driverlist" ><bean:write name="driverlist" property="experience"/></logic:present>" />
									</div>
								</div>
							
								 <br />
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Driving License No</label>
										<div class="col-xs-7">
									<input type="text" name="drivingliecenseNo" id="drivingliecenseNo"  class="form-control" onkeypress="HideError()"
									 value="<logic:present name="driverlist" ><bean:write name="driverlist" property="drivingliecenseNo"/></logic:present>" />
									</div>
								</div>
								<br>
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										DL Issued Date</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control" onkeypress="HideError()"  name="dl_issued_date" id="dl_issued_date"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dl_issued_date"/></logic:present>"/>
									</div>
								</div>
								
								<br>
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">DL Validity Upto</label>
										<div class="col-xs-7">
									<input type="text" name="dl_validity" readonly="readonly" id="dl_validity" onkeypress="HideError()"  class="form-control" 
									 value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dl_validity"/></logic:present>" />
									</div>
								</div>
								
							</div>
							
							
							
							<div class="col-md-6" style="margin-top: 12px;"
								id="txtstyle">
								
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Father's Name</label>
									<div class="col-xs-7">
											<input type="text"  name="father_name" id="father_name" class="form-control" onkeypress="HideError()"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="father_name"/></logic:present>"/>
									</div>
								</div>
								
								<br />
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Age</label>
									<div class="col-xs-7">
											<input type="text" readonly="readonly" name="age" id="ageId" class="form-control"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="age"/></logic:present>"/>
									</div>
								</div>
								
								<br />
                                <div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Mobile No.</label>
									<div class="col-xs-7">
											<input type="text"  name="mobile" id="mobile" maxlength="10" class="form-control"  onkeypress="HideError()"
										value="<logic:present name="driverlist" ><bean:write name="driverlist" property="mobile"/></logic:present>"/>
									</div>
								</div>
								 <br />
								 
                                 <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">
										Date Of Joining</label>
									<div class="col-xs-7">
										<input type="text"  class="form-control" readonly="readonly" onkeypress="HideError()" name="dateofJoin" id="dateofJoinId"  value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dateofJoin"/></logic:present>"/>
									</div>
								</div>
								
								 <br />
								 
                                <div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Address
									</label>
									<div class="col-xs-7">
										<textarea name="address" id="address" onkeypress="HideError()" class="form-control"><logic:present name="driverlist"><bean:write name="driverlist" property="address"/></logic:present></textarea>
									</div>
								</div>
								
								<br>
								<br />
								
								<%-- <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">
										DL Issued Date</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"  name="dl_issued_date" id="dl_issued_date" placeholder="Click here" value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dl_issued_date"/></logic:present>"/>
									</div>
								</div> --%>
								
							
							<!-- 	<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">License
										Upload</label>
									<div class="col-xs-7">
									
										<input type="file" id="uploadBirth" name="licensedrive" class="custom-file-uploadfile" style="height: 20%;" />
										<input type="button" id="document1btn" name="profile" class="downloadDoc" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="deleteProfile">  x</span> 
										

									</div>
								</div> -->
								
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">License
										Upload</label>
									<div class="col-xs-7">
									
										<input type="file" id="uploadlicence" name="licensedrive" class="custom-file-uploadfile" style="height: 20%;" />
										<input type="button" id="downloadlicenceid" name="profile" class="downloadDoc1" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="deleteProfile">  x</span>
								
									</div>
								</div>
								
								
								
								
								<br /><br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">License To Drive
									</label>
									<div class="col-xs-7">
										<select name="driving_license_types" multiple id="vehicletodrive" onkeypress="HideError()"
											class="form-control">
											<option value="CYCL">CYCL</option>
											<option value="LMV">LMV</option>
											<option value="HMV">HMV</option>
											
										</select>
									</div>
									
									
									
								</div>
								
								
							</div>
							
							
						
						</div>
					</div>
					</div>
					</div>
					
</form>
				</div>
			
				
			
	
</body>

</html>
</html>