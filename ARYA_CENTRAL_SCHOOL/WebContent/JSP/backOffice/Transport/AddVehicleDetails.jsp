<!DOCTYPE html>
<html lang="en">
<%@  taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<!-- <script type="text/javascript" src="JS/backOffice/Admin/Latheef.js"></script> -->
<script type="text/javascript"
	src="JS/backOffice/Transport/AddVehicleDetails.js"></script>
<style>
.buttons{

vertical-align: 0px;

}

.panel-primary>.panel-heading {
    margin-bottom: -11px;
    padding-left:15px;
 }   
 
 
 
</style>

</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading"><logic:present name="vehicledetails" scope="request"><bean:write name="vehicledetails"></bean:write></logic:present></span>
		</p>

	
			<logic:present name="successmessagediv" scope="request">
				<div class="successmessagediv" align="center">
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-success bg-msg-succes"><span><bean:write
									name="successmessagediv" scope="request" /></span></a>
					</div>
				</div>
			</logic:present>


			<div class="errormessagediv" style="display: none; text-align:center;">
				<div class="message-item" align="center">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>

	



		<form action="transport.html?method=saveVehicleDetails"  id="myForm"  method="post" enctype="multipart/form-data">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676;vertical-align: text;"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;<span
								style="margin-top: -1px; position: absolute;">Vehicle
									Details</span>
							</h4></a>
                          
							<div class="navbar-right" >
								
								<span id="saveid" class="buttons"
									style="cursor: pointer; margin-right:-11px;">Save</span>&nbsp;
									 <span id="back" class="buttons" style="margin-right:6px;">Back</a></span>
							</div>
						
					</div>
	

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" style="padding-top:25px;">
							<div class="col-md-6" id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 18px;">Registration
										No<font color="red"> *</font></label> 
									<div class="col-xs-7">

										<input type="text" name="vehicleregno" class="form-control"
											id="vehicleregno" onblur="registernumberValidation()" onkeypress="HideError()"
											placeholder=""
											value="<logic:present name="vehicleDetails" scope="request" ><bean:write name="vehicleDetails" property="vehicleregno"/></logic:present>" />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Engine Number<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text" name="enginenumber" id="enginenumber" onkeypress="HideError()"
											class="form-control" placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="enginenumber"/></logic:present>" />
									</div>
								</div>

								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Vehicle Type<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text" name="vehicletype" id="vehicletype" onkeypress="HideError()"
											class="form-control" placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="vehicletype"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Tax Issued Date<font color="red"> *</font>
										</label>
									<div class="col-xs-7">
										<input type="text" name="taxpaid" id="taxpaid" onkeypress="HideError()"
										readonly="readonly"
										class="form-control" value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="taxpaid"/></logic:present>" />
									</div>
								</div>
								
								
							</div>
							
							<div class="col-md-6" id="txtstyle"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Vehicle Name<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="vehiclename" onkeypress="HideError()"
											id="vehiclename" placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="vehiclename"/></logic:present>" />
									</div>
								</div>


								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Chassis
										Number<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text" name="chassisno" id="chassisno" onkeypress="HideError()"
											onblur="chassisnovalidation()" class="form-control"
											placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="chassisno"/></logic:present>" />
									</div>
								</div>

								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Last Emission Test<font color="red"> *</font>
										</label>
									<div class="col-xs-7">
										<input type="text" name="pollution" id="pollution"
										readonly="readonly"
										class="form-control" value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="pollution"/></logic:present>" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px; padding-right:26px;">Tax Expiry
										Date</label>
									<div class="col-xs-7">
										<input type="text" name="taxexpirydate" id="taxexpirydate" 
											 class="form-control" onkeypress="HideError()"
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="taxexpirydate"/></logic:present>" />
									</div>
								</div>
								
								
							<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px; padding-right:26px;">RC
										Upload</label>
									<div class="col-xs-7">
									
										<input type="file" id="uploadrcfile" name="rcfile" class="custom-file-uploadfile form-control" style="height: 20%;" />
										<input type="button" id="document2btn" name="idproof" class="downloadDoc2" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="deleteIDProof">x</span>
								
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingTwo">
						<h4 class="panel-title">
							<a class="collapsed" role="button" data-toggle="collapse"
								data-parent="#accordion" href="#collapseTwo"
								style="color: #767676" aria-expanded="false"
								aria-controls="collapseTwo"> <i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;<span
								style="margin-top: -1px;padding-right:26px; position: absolute;">Insurance & Others
									Details</span>
							</a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingTwo">
						<div class="panel-body" style="padding-top:15px;">
					
							<div class="col-md-6" id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Company
										Name<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text" name="companyname" id="companyname" onkeypress="HideError()"
											class="form-control" placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="companyname"/></logic:present>" />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Expiry
										Date</label>
									<div class="col-xs-7">
										<input type="text" name="expirydate" id="expirydate" 
											 class="form-control" onkeypress="HideError()"
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="expirydate"/></logic:present>" />
									</div>
								</div>
								
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> FC<font color="red"> *</font>
										</label>
									<div class="col-xs-7">
										<input type="text" name="fc" id="fc"
											placeholder="" readonly="readonly" class="form-control" onkeypress="HideError()"
											placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="fc"/></logic:present>" />
									</div>
								</div>

							

							</div>

							<div class="col-md-6" id="txtstyle"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Issued
										Date<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text" name="issueddate" id="issueddate"
											placeholder="" readonly="readonly" class="form-control" onkeypress="HideError()"
											placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="issueddate"/></logic:present>" />
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Policy Number<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text" name="doneby" id="doneby" onkeypress="HideError()"
											class="form-control" placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="doneby"/></logic:present>" />
									</div>
								</div>


								
								
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Permit
										Validity<font color="red"> *</font></label>
									<div class="col-xs-7">
										<input type="text" name="permitvalidity" id="permitvalidity"
											placeholder="" readonly="readonly" class="form-control" onkeypress="HideError()"
											placeholder=""
											value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="permitvalidity"/></logic:present>" />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;padding-left:10px; padding-right:26px;margin-left:">Insurance
										Upload</label>
									<div class="col-xs-7">
									
										<input type="file" id="uploadinsurance" name="insurancefile" class="custom-file-uploadfile form-control" style="height: 20%;margin-bottom:8px;" />
										<input type="button" id="document1btn" name="profile" class="downloadDoc1" value="Download" />
										<span style="font-size:20px;color:red;cursor:pointer;" id="deleteProfile">  x</span>
								
									</div>
								</div>

							</div>
						</div>
					</div>

				</div>

				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingTwo">
						<h4 class="panel-title">
							<a class="collapsed" role="button" data-toggle="collapse"
								data-parent="#accordion" href="#collapseThree"
								style="color: #767676" aria-expanded="false"
								aria-controls="collapseTwo"> <i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;<span
								style="margin-top: -1px; position: absolute;">Driver
									Mapping</span>
							</a>
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingTwo">
						<div class="panel-body" style="padding-top:15px;">
							<div class="col-md-6" id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Driver
										Name</label>
									<div class="col-xs-7">
										<select name="driverName" id="drivername" class="form-control"
											onchange="getDriverEntireDetails()" onkeypress="HideError()">
											<option value="">-----Select-----</option>
										</select>
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										Experience </label>
									<div class="col-xs-7">
										<input type="text" name="experience" id="experience"
											readonly="readonly" class="form-control"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="experience"/></logic:present>" />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">DL
										Issued Date</label>
									<div class="col-xs-7">
										<input type="text" name="dl_issued_date" id="dlissued"
											readonly="readonly" class="form-control"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="dl_issued_date"/></logic:present>" />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Licence
										To Drive </label>
									<div class="col-xs-7">
										<input type="text" name="licensetodrive" id="licencedrive"
											readonly="readonly" class="form-control"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="license"/></logic:present>" />
									</div>
								</div>
							</div>

							<div class="col-md-6" id="txtstyle"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Mobile
										Number</label>
									<div class="col-xs-7">
										<input type="text" name="mobile" id="phoneno"
											readonly="readonly" class="form-control"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="mobile"/></logic:present>" />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Date Of
										Join</label>
									<div class="col-xs-7">
										<input type="text" name="dateofJoin" id="doj" readonly="readonly"
											class="form-control"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="dateofJoin"/></logic:present>" />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">DL
										Expiry Date</label>
									<div class="col-xs-7">
										<input type="text" name="dl_validity" id="dlexpiray"
											readonly="readonly" class="form-control"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="dl_validity"/></logic:present>" />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Driving
										Licence No.</label>
									<div class="col-xs-7">
										<input type="text" name="drivingliecenseNo" id="dlno" readonly="readonly"
											class="form-control"
											value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="drivingliecenseNo"/></logic:present>" />
									</div>
								</div>

							</div>
						</div>
					</div>

				</div>

				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingTwo">
						<h4 class="panel-title">
							<a class="collapsed" role="button" data-toggle="collapse"
								data-parent="#accordion" href="#collapseFourth"
								style="color: #767676" aria-expanded="false"
								aria-controls="collapseTwo"> <i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;<span
								style="margin-top: -1px; position: absolute;">Route
									Mapping</span>
							</a>
						</h4>
					</div>
					<div id="collapseFourth" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="headingTwo">
						<div class="panel-body" style="padding-top:15px;">
							<div class="col-md-6" id="txtstyle"
								style="font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Route
										Name</label>
									<div class="col-xs-7">
										<select name="routename" id="routename" class="form-control"
											onchange="getRouteEntireDetails()" onkeypress="HideError()" >
											<option value=""></option>
										</select>
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Halt
										time(min)</label>
									<div class="col-xs-7">
										<input type="text" name="halttime" id="halttime"
											readonly="readonly" class="form-control"
											value="<logic:present name="RouteDetails" ><bean:write name="RouteDetails" property="halttime"/></logic:present>" />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Total
										Distance(km)</label>
									<div class="col-xs-7">
										<input type="text" name="totaldistance" id="totaldistance"
											readonly="readonly" class="form-control"
											value="<logic:present name="RouteDetails" ><bean:write name="RouteDetails" property="totalDistance"/></logic:present>" />
									</div>
								</div>

							</div>

							<div class="col-md-6" id="txtstyle"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Route
										No </label>
									<div class="col-xs-7">
										<input type="text" name="routeno" id="routeno"
											readonly="readonly" class="form-control"
											value="<logic:present name="RouteDetails" ><bean:write name="RouteDetails" property="routeNo"/></logic:present>" />
									</div>
								</div>
								<%--  <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Destination</label>
									<div class="col-xs-7">
										<input type="text" name="destination" id="destination"
											readonly="readonly" class="form-control"
											value="<logic:present name="RouteDetails" ><bean:write name="RouteDetails" property="destination"/></logic:present>" />
									</div>
								</div>

								<br />  --%>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Total
										Stops</label>
									<div class="col-xs-7">
										<input type="text" name="totalstops" id="totalstops"
											readonly="readonly" class="form-control"
											value="<logic:present name="RouteDetails" ><bean:write name="RouteDetails" property="totalStops"/></logic:present>" />
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>


				<input type="hidden" name="updatevehicleCode" id="hvehicleid"
					value="<logic:present name="vehiclecode" ><bean:write name="vehiclecode" /></logic:present>" />

				<input type="hidden" name="driverCode" id="driverCode"
					value="<logic:present name="driverDetails" ><bean:write name="driverDetails" property="driverCode"/></logic:present>" />

				<input type="hidden" name="status" id="statusId"
					value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="status"/></logic:present>" />

				<input type="hidden" name="hiddenfuel" id="hiddenfuelId"
					value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="fuelusedintheengine"/></logic:present>" />

				<input type="hidden" name="routecodeid" id="routecodeid"
					value="<logic:present name="RouteDetails" ><bean:write name="RouteDetails" property="routeCode"/></logic:present>" />



	<input type="hidden" name="hrcfileid" id="hrcfileid"
					value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="rcfile"/></logic:present>" />


	<input type="hidden" name="hinsurancefileid" id="hinsurancefileid"
					value="<logic:present name="vehicleDetails" ><bean:write name="vehicleDetails" property="insurancefile"/></logic:present>" />





			</div>



		</form>
	</div>

</body>

</html>
