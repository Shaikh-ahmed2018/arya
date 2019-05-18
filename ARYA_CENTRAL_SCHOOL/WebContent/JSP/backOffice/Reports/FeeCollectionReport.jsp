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

<link href="CSS/newUI/custome.css" rel="stylesheet">
<script src="JS/backOffice/Reports/FeeCollectionReport.js"></script>

<style>
.modal-body {
	text-align: center;
}
</style>
<style>
.buttons {
	vertical-align: -28px;
}
.schoolLogo,.schoolheader,.feefilterheading{
display: none;
}
.start,.end,.monthwisetext{
display: none;
}
</style>
</head>



<body>

<div id="loder" style="display: none; position:absolute; height: 200px;width: 200px;left: 0;right: 0;top: 0;bottom: 0;margin: auto;z-index: 99999; "><img style="width: 50px;position: absolute;left: 0;right: 0;height: 50px;bottom: 0;top: 0;margin: auto;" src="./images/ajax-loader-blue.gif"/></div>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px; color: #07aab9;">

		<p>
			<img src="images/addstu.png" />&nbsp;<span style="font-size: 16pt;">Fee Collection </span>
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
		<div class=" -group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">

					
						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne" style="color: #767676"><h4 class="panel-title" id="beforeparent"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Fee Collection
						</h4></a>
					

				            <div class="navbar-right">
				             <span class="buttons" id="print" >Print</span>
				             <span class="buttons" id="excel" >Excel</span>
         	            
				</div>
					
				</div>

		
			<!-- pop up -->


			<!-- Filters -->

			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">
				<div class="tab-row" style="margin-bottom: 125px;">
					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="location" name="location" class="form-control" required>
											<option value="all">----------Select----------</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
					
						
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;"> Class</label>
							<div class="col-xs-7">
							<select class="form-control" onkeypress="HideError()" 
									name="classname" id="class">
									<option value="all">----------Select----------</option>
							</select>
							</div>
						</div>
						  
						  <div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								id="inputnames" style="text-align: right;">PayMent Type</label>
							<div class="col-xs-7">
								<select id="PaymentType" name="" class="form-control"
									>
									<option value="ALL">ALL</option>
									<option value="ONLINE">ONLINE</option>
									<option value="OFFLINE">OFFLINE</option>
									
								</select>
							</div>
						</div>
						
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								id="inputnames" style="text-align: right;">Report Type</label>
							<div class="col-xs-7">
								<select id="reportType" name="" class="form-control">
									<option value="">--------Select---------</option>
									<option value="Admission No Wise">Admission No Wise</option>
									<option value="Bill No. Wise">Bill No. Wise</option>
									<option value="Month Wise">Month Wise</option>
									<option value="Address Wise">Address Wise</option>
									<option value="Date Wise">Date Wise</option>
								</select>
							</div>
						</div>
						<div class="form-group clearfix start">
							<label for="inputPassword" class="control-label col-xs-5"
								id="inputnames" style="text-align: right;">Start Date</label>
							<div class="col-xs-7">
								<input type="text" id="startDate"  class="form-control" readonly="readonly" />
							</div>
						</div>
						<div class="form-group clearfix end">
							<label for="inputPassword" class="control-label col-xs-5"
								id="inputnames" style="text-align: right;">End Date</label>
							<div class="col-xs-7">
								<input type="text" id="endDate"  class="form-control" readonly="readonly" />
							</div>
						</div>
						
						
						
						
						<div class="form-group clearfix monthwisetext">
							<label for="inputPassword" class="control-label col-xs-5"
								id="inputnames" style="text-align: right;">Enter Month Name</label>
							<div class="col-xs-7">
								<input type="text" id="monnthName"  class="form-control" />
							</div>
						</div>
						
					</div>

					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Academic Year</label>
							<div class="col-xs-7">
								<select id="accyear" name="accyear" class="form-control">
									<option value="all">-----------Select----------</option>
									<logic:present name="AccYearList">
										<logic:iterate id="AccYear" name="AccYearList">
											<option
												value="<bean:write name="AccYear" property="accyearId"/>">
												<bean:write name="AccYear" property="accyearname" />
											</option>
										</logic:iterate>
									</logic:present>
								</select>
							</div>
						</div>
					

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Division</label>
							<div class="col-xs-7">
								<select id="section" name="section" class="form-control">
									<option value="all">ALL</option>
								</select>
							</div>
						</div>
						
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Term</label>
							<div class="col-xs-7">
								<select id="termName" name="termName" class="form-control">
									<option value="all">ALL</option>
								</select>
							</div>
						</div>
						
						<div class="form-group clearfix paymenttype" style="display:none;">
							<label for="inputPassword" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;">Pay Mode</label>
							<div class="col-xs-7">
							<select class="form-control" name="" id="paymode">
									<option value="ALL">ALL</option>
							</select>
							</div>
						</div>
							
							
							
							
							
			<div class="col-xs-4"></div>
					<div class="col-xs-8">
						<button type="button" class="btn btn-info " id="search"  Style ="margin-bottom: 9px;">Search</button>
					</div>
					</div>
					
					</div>
					
					<div id="studentlisttableprint" >
					
					<div class="feefilterheading"><hr></div>
					<div id="studentlisttable" >
					<div class="schoolLogo"><img src='images/arya_logo.png' width="100px" height="100px"></div><div class="schoolheader"><h3 class="feeHeader"></h3> </div>
					</div>
					</div>
				</div>
			</div>
			</div>
		</div>
		</div>
		<textarea id="printing" style="display: none;">
		.schoolLogo{
			width:20%;
			display:inline-block;
			vertical-align:top;
}
.schoolheader{
width:75%;
display:inline-block;
vertical-align:top;
}
.schoolheader h1,.schoolheader h3,.schoolheader{
margin:0px;
}
.feefilterheading hr{
border-color:#000;
}
.col-md-3{
width:24%;
display:inline-block;
vertical-align:top;
}
.feeHeader{
margin-top:10px !important;
}
</textarea>
	<!-- Button trigger modal -->
	<span>&nbsp;</span>
	
	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>

</html>
