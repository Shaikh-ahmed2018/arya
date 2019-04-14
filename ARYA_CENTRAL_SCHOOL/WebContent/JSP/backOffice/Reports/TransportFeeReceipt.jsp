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
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="JQUERY/development-bundle/themes/base/jquery.ui.all.css"
	rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script src="JS/backOffice/Reports/TransportFeeReport.js"></script>
 
<style>
.modal-body {
	text-align: center;
}
.navbar-right span {
    margin-right: 4px;
    }

.transportFeeReportandStudentClassWise,.transportOtherReport{
display: none;
}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<!-- <p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>All Student</span>
		</p> -->

		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Transport Report </span>
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
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne" style="color: #767676;vertical-align: text-top"><h4 class="panel-title" id="beforeparent"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Transport
						</h4></a>
					

					<div class="navbar-right">
							<span class="buttons" id="print">Print </span>
							<span class="buttons" id="excel">Excel </span>
					</div>

					
				</div>

				

				<!-- Filters -->

				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body" id="tabletxt" style="padding: 15px;">
					
					<div class="clearfix transportReportSlection">
					<div class="col-md-4" id="txtstyle">
					
					<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Location</label>
								<div class="col-xs-7">
									<select id="location" name="location" class="form-control"
										required>
										<option value="">----------Select----------</option>

										<logic:present name="locationList">
											<logic:iterate id="Location" name="locationList">
												<option
													value="<bean:write name="Location" property="locationId"/>">
													<bean:write name="Location" property="locationName" />
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>
							
							<input type="hidden" id="hacademicyaer" value='<logic:present name="accYear"><bean:write name="accYear" ></bean:write></logic:present>'></input>
						</div>	
					<div class="col-md-4" id="txtstyle">
					<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Academic Year</label>
								<div class="col-xs-7">
									<select id="accyear" name="accyear" class="form-control"
										required>
										<option value="">----------Select----------</option>
										<logic:present name="AccYearList">
											<logic:iterate id="AccYear" name="AccYearList">
												<option value="<bean:write name="AccYear" property="accyearId"/>">
													<bean:write name="AccYear" property="accyearname" />
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>
						
					
					</div>
					
					<div class="col-md-4" id="txtstyle">
					<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Report Type</label>
								<div class="col-xs-7">
									<select id="reportType" name="reportType" class="form-control">
										<option value="">----------Select----------</option>
										<option value="TransportFee" id="transpt">Transport Fee</option>
										<option value="BusRouteDetail">Bus Route Detail</option>
										<option value="RouteWiseStudentDetail">Routewise Student</option>
										<option value="RouteWiseStudentDetailwithClassAndSection">Routewise Student With Class & Section</option>
										
									</select>
								</div>
							</div>
						
					
					</div>
					</div>

					<div class="clearfix transportFeeReportandStudentClassWise">

						<div class="col-md-6" id="txtstyle">

							
							
							
							<div class="form-group clearfix transportFeeReport StudentClassWise">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Class</label>
								<div class="col-xs-7">
									<select id="class" name="classname" class="form-control"
										required >
										<option value="all">ALL</option>
										
									</select>
								</div>
							</div>
						
							<div class="form-group clearfix transportFeeReport">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Term</label>
								<div class="col-xs-7">
									<select id="term" name="term" class="form-control" required>
										<option value="all">ALL</option>
										
										<logic:present name="termlist">
											<logic:iterate id="Term" name="termlist">
												<option
													value=" <bean:write name="Term" property="termId"/>">
													<bean:write name="Term" property="termname" />
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</div>
							</div>
							
							 <div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								id="lblPaymentType" style="text-align: right;">PayMent Type</label>
							<div class="col-xs-7">
								<select id="PaymentType" name="" class="form-control"
									>
									<option value="ALL">ALL</option>
									<option value="ONLINE">ONLINE</option>
									<option value="OFFLINE">OFFLINE</option>
									
								</select>
							</div>
						</div>
							<div class="form-group clearfix start">
							<label for="inputPassword" class="control-label col-xs-5"
								id="lblstartDate" style="text-align: right;">Start Date</label>
							<div class="col-xs-7">
								<input type="text" id="startDate"  class="form-control" readonly="readonly" />
							</div>
						</div>
						<div class="form-group clearfix end">
							<label for="inputPassword" class="control-label col-xs-5"
								id="lblendDate" style="text-align: right;">End Date</label>
							<div class="col-xs-7">
								<input type="text" id="endDate"  class="form-control" readonly="readonly" />
							</div>
						</div>
							
						</div>

						<div class="col-md-6" id="txtstyle">
							

					<div class="form-group clearfix transportFeeReport StudentClassWise">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Section</label>
								<div class="col-xs-7">
									<select id="section" name="section" class="form-control" required>
										<option value="all">ALL</option>
										
										
									</select>
								</div>
							</div>
							
							
							<div class="form-group clearfix transportFeeReport">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Status</label>
								<div class="col-xs-7">
									<select id="termstatus" name="termstatus" class="form-control" required>
									<option value="all">ALL</option>
									<option value="Y">Paid</option>
									<option value="N">Unpaid</option>
									</select>
								</div>
							</div>
							<div class="form-group clearfix paymenttype" style="display:none;">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;">Pay Mode</label>
							<div class="col-xs-7">
							<select class="form-control" name="" id="paymode">
									<option value="ALL">ALL</option>
							</select>
							</div>
						</div>
							<br/>

							<!-- <button type="button" class="btn btn-info" id="search">Search</button> -->
							<br/>
						</div>	
						</div>
						<div class="clearfix transportOtherReport">
						
						<div class="col-md-4">
						
						</div>
						<div class="col-md-4" id="txtstyle">
						<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									id="inputnames" style="text-align: right;">Route</label>
								<div class="col-xs-7">
									<select id="route" name="route" class="form-control"
										required >
										<option value="all">ALL</option>
										
									</select>
								</div>
							</div>
						
						</div>
						
						<div class="col-md-4">
						
						</div>
						</div>
						
						
						
							
						<div id="studenttable"></div>
						
					</div>
				</div>
			</div>
			<!-- Button trigger modal -->

		</div>

		
  
  </div>
</body>

</html>
