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
	src="JS/backOffice/Inventory/UsageReports.js"></script>

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
</script> --><style>
.buttons{

vertical-align: -28px;

}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Usage Reports</span>
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



		<form action="adminMenu.html?method=getUsageReportAction" name="InventoryTransactionForm"
			 method="post" id="udagereportformid" enctype="multipart/form-data">
			<div class="panel-group" role="tablist" aria-multiselectable="true">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Usage Reports
							</h4></a>
						

				 <div class="navbar-right">

							 <!--	<a id="savedriver" class="save2"> <img src="images/save.png"
								data-toggle="tooltip" data-placement="bottom" title="Save">
							</a> <a href="adminMenu.html?method=driverList"> <span
								class="glyphicon glyphicon-list2" data-toggle="tooltip"
								data-placement="bottom" title="List"></span></a>
						</div> 
						 -->
						

						<!-- <div class="navbar-right">
						
						<!-- 	<img src="images/rightline.png" style="margin-top: -2px;margin-right: 11px;"> -->
							
					<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span>

						</div>
						<script>
							$(document).ready(function() {
								$('[data-toggle="tooltip"]').tooltip();
							});
						</script>
					</div>
						<!-- -->
						 <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Download</h4>
								</div>
								<div class="modal-body">
									<span id="excelDownload"><img src="images/xl.png"
										class="xl"></span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
										id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
								</div>

							</div>
						</div>
					</div> 
					
					<!-- end -->
					
					
						

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
										style="text-align: right; line-height: 35px;">Date</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											onkeypress="HideError()" name="startdate"
											id="startdate" 
											value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dateofBirth"/></logic:present>" />
									</div>
								</div>
								<br />
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										Department</label>
									<div class="col-xs-7">

										<select name="department" id="department"
											onkeypress="HideError()" class="form-control">
											<option value="">-----Select-----</option>
											<logic:present name="DepartmentDetails" scope="request">
												<logic:iterate id="DeoartmentId" name="DepartmentDetails"
													scope="request">
													<option
														value="<bean:write name="DeoartmentId" property="depId"/>">
														<bean:write name="DeoartmentId" property="depName" />
													</option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>

								</div>
								
								<br />

							</div>
							

							<div class="col-md-6" id="txtstyle">


								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">End Date</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											onkeypress="HideError()" name="todate"
											id="todate" 
											value="<logic:present name="driverlist" ><bean:write name="driverlist" property="dateofBirth"/></logic:present>" />
									</div>
								</div>

								<br />
								
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">
										Item Type</label>
									<div class="col-xs-7">
										<select name="item_type" id="item_type"
											onkeypress="HideError()" class="form-control">
											<option value=""></option>
										</select>
									</div>

								</div>
								
								<br />
								

								
								
								
								






									<br> <br />
							</div>
							
							
							<button type="submit" class="btn btn-info col-md-offset-5"
								id="search" onclick="return validate()">Search</button>
							
							
						</div>
					</div>
				</div>
			

		</form>
	</div>



</body>

</html>
