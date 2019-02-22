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
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>

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

<script type="text/javascript"
	src="JS/backOffice/Transport/DriverEntryPage.js"></script>

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
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Expenses Details</span>
		</p>


		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>



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
						class="validateTips"></span> <bean:write name="errormessagediv"
							scope="request" /></a>
				</div>
			</div>
		</logic:present>



		<form action="driverDetailsPath.html?method=savedriverval"
			id="driverformid" enctype="multipart/form-data" method="post">
			<div class="panel-group" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Add
								New Expenses
							</h4></a>
						

						<div class="navbar-right">

							<a id="savedriver" class="buttons"
								data-toggle="tooltip" data-placement="bottom" title="Save">Save
							</a>&nbsp; <span id="back" class="buttons" >Back</span></a>
						</div>

					</div>



					<input type="hidden" name="drivercode" id="drivercode"
						value="<logic:present name="driverlist" ><bean:write name="driverlist" property="driverCode"/></logic:present>" />
					<input type="hidden" name="vehiclecode" id="vehiclecode"
						value="<logic:present name="fuellist" ><bean:write name="fuellist" property="vehicleCode"/></logic:present>" />
					<input type="hidden" name="fuelcode" id="fuelcode"
						value="<logic:present name="fuellist" ><bean:write name="fuellist" property="fuelCode"/></logic:present>" />
					<input type="hidden" name="license" id="hlicensetodrive"
						value='<logic:present name="driverlist"><bean:write name="driverlist" property="license" /></logic:present>'></input>
					<input type="hidden" name="licenseupload" id="hlicenseupload"
						value='<logic:present name="driverlist"><bean:write name="driverlist" property="licensedrive" /></logic:present>'></input>




					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6" id="txtstyle">






								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Date</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											onkeypress="HideError()" name="dateofBirth"
											id="dateofBirthId" onchange="ageCalculateAdd();"
											value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dateofBirth"/></logic:present>" />
									</div>
								</div>

								<br />



								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										Expenses  Applicable To</label>
									<div class="col-xs-7">
										<select name="driving_license_types" id="vehicletodrive"
											onkeypress="HideError()" class="form-control">
											<option value="0"></option>
											<option value="10">School</option>
											<option value="11">Class</option>
											

										</select>
									</div>

								</div>
								
								<br />
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										Status</label>
									<div class="col-xs-7">
										<select name="driving_license_types" id="vehicletodrive"
											onkeypress="HideError()" class="form-control">
											<option value="0"></option>
											<option value="pending">Pending</option>
											<option value="approved">Approved</option>
											<option value="rejected">Rejected</option>
											
											

										</select>
									</div>

								</div>
								
								<br />
								
									<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Description
									</label>
									<div class="col-xs-7">
										<textarea name="address" id="address"  onkeypress="HideError()"
											class="form-control">
											
										</textarea>
									</div>
								</div>

							</div>
							

							<div class="col-md-6" id="txtstyle">


								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Type Of
										Expenses </label>
									<div class="col-xs-7">
										<select name="driving_license_types" id="vehicletodrive"
											onkeypress="HideError()" class="form-control">
											<option value="0"></option>
											<option value="1">Vehicle Maintenance</option>
											<option value="2">Stationary Expenses</option>
											<option value="3">Events Expenses</option>
											<option value="4">Food Expenses</option>
											<option value="5">Daily Wages</option>
											<option value="6">Advances</option>
											<option value="7">Others</option>

										</select>
									</div>

								</div>

								<br />
								

								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Amount </label>
									<div class="col-xs-7">
										<input type="text" name="father_name" id="father_name"
											class="form-control" onkeypress="HideError()"
											value="<logic:present name="driverlist" ><bean:write name="driverlist" property="father_name"/></logic:present>" />
									</div>
								</div>
								
								<br /> 
								
								
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Upload
										Bill Copies</label>
									<div class="col-xs-7">
									
									
										<input type="file" id="uploadlicence" name="licensedrive" class="custom-file-uploadfile" style="height: 20%;" />
										<input type="button" id="downloadlicenceid" name="profile" class="downloadDoc1" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="deleteProfile">  x</span>
								
									</div>
								</div><br> <br />
							</div>
						</div>
					</div>
				</div>
			</div>

		</form>
	</div>




</body>

</html>
