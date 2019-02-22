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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Library/NewArrivalList.js"></script>
<style>
.form-group {
	margin-bottom: 5px;
}

.save:hover {
	cursor: pointer;
}

fieldset {
   
	width: 512px;
	display: block;
	/*  margin-left: auto;
    margin-right: 2px; */
	margin-bottom: 5px;
	padding-bottom: 0.625em;
	padding-left: 7px;
	padding-right: 0px;
	border: 1.5px solid #ccc;
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

.text2 {
	margin-left: 211px !important;
	width: 819px !important;
}
</style>

<style>
.buttons {
	vertical-align: -5px;
}

.navbar-right {
	top: -3px;
}

.panel-primary>.panel-heading {
	margin-bottom: 0px;
}

form .panel.panel-primary.panel-list {
	padding-bottom: 0px;
}

.ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all {
	z-index: 1;
	display: block;
	left: 478px;
	width: 346px;
	height: 300px;
	overflow: scroll;
	position: absolute;
	top: 200px !important;
}

.back {
	display: none;
}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" /><span id="pageHeading">New Arrival List</span></p>
		</p>
	<logic:present name="errorMessage" scope="request">

				<div class="errormessagediv" align="center" >
					<div class="message-item">
						<!-- Warning -->
						<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write name="errorMessage" scope="request" /></span></a>
					</div>
				</div>

			</logic:present>
			
		<div class="successmessagediv"
			style="width: auto !important; display: none" align="center">
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

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary panel-list">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"
							style="color: #767676; vertical-align: text-top;"><h4 class="panel-title" style="vertical-align: text-top;"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;New Arrival List
							</h4></a>
				

                     <div class="navbar-right">
					<span class="buttons" id="pdfDownload" data-toggle="modal"
							data-target="#myModal" 
							data-placement="bottom"  style="top:8px;margin-right:6px;">Download </span>
							<span class="buttons" id="print" data-toggle="modal"
							data-placement="bottom"  style="top:8px;">Print </span>


				</div>
			</div>
		<!-- 	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content" style="margin-left: 161px;width: 274px;height: 154px;">
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
										id="pdfDownload"><img src="images/pdf.png" style=" margin-left: -73px;width: 85px;height: 78px;"class="pdf"></span>
								</div>

							</div>
						</div>
					</div> -->

			<div id="collapseOne" class="panel-collapse collapse in "
				role="tabpanel" aria-labelledby="headingOne">


				<div class="panel-body own-panel">
					<div class="row">
                             <div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
							
							<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">New Arrival From
								</label>
								<div class="col-xs-7" >
								
									<input type="text"  readonly="readonly"   id="Fromdate" name="" tabindex="1" class="form-control" ></input>
								</div>
							</div>
							
							
							
							
						</div>
						

						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">

							<div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">To
									</label>
								<div class="col-xs-7">
								
									<input type="text"  readonly="readonly" id="ToDate" name="" tabindex="1" class="form-control" ></input>
								</div>
							</div>
                           </div>

                     <div class="col-md-12"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
						<fieldset class="fieldset" Style="height:150px;width: 965px; margin-left: 83px;border-radius: 4px;">
								<legend
									style="font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 16px; color: #767676; margin-left: 56px;">
									Print In The Order:</legend>
								<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
						         <div class="form-group clearfix"
									style="padding-top: 17px; padding-left:80px;">
									<label style="width: 100px; margin-left: 2px;width: 190px;"> <input
										type="radio" style="top: -2px;" class="radio-inline"
										name="requested_by" id="" class="cencession"
										value="accNo" checked="checked" />&nbsp;By Accession No
									</label> <br/>
									<label style="width: 100px; margin-left: 2px;width: 190px;"> <input
										type="radio" style="top: -2px;" class="radio-inline"
										name="requested_by" id="" class="cencession"
										value="author"  />&nbsp;By Author
									</label> 
									</div>
									</div>
									<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
									  <div class="form-group clearfix"
									style="padding-top: 17px; padding-left:80px;">
									<label style="width: 100px; margin-left: 2px;width: 190px;"> <input
										type="radio" style="top: -2px;" class="radio-inline"
										name="requested_by" id="" class="cencession"
										value="category"  />&nbsp;By Category
									</label><br />
										<label style="width: 100px; margin-left: 2px;width: 190px;"> <input
										type="radio" style="top: -2px;" class="radio-inline"
										name="requested_by" id="" class="cencession"
										value="title"  />&nbsp;By Title
									</label>
									</div>
									</div>
						
							</fieldset>
				
							</div>
							
				
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
