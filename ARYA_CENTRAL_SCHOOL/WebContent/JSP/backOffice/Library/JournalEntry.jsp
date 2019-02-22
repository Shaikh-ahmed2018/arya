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
<script type="text/javascript" src="JS/backOffice/Library/JournalEntry.js"></script>

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
			<img src="images/addstu.png" /><span id="pageHeading">Journal Entry Details</span></p>
		</p>

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
							style="color: #767676; vertical-align: text-top;"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Journal Entry
							</h4></a>
				

                     <div class="navbar-right">
					<span class="buttons" id="save">Save</span>
					
					<!-- <span class="buttons" id="">Back </span></a> &nbsp; -->


				</div>
			</div>

			<div id="collapseOne" class="panel-collapse collapse in "
				role="tabpanel" aria-labelledby="headingOne">


				<div class="panel-body own-panel">
					<div class="row">
							
                             <div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">
							
							<div class="form-group clearfix ">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Code
								</label>
								<div class="col-xs-7" >
							   <select  name="code" class="form-control" id="code" >
								<option value="all">---------Select-------</option>
								 </select>
								</div>
							</div>
							
							
							
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Name</label>
								<div class="col-xs-7">
                            <select  name="name" class="form-control" id="name" >
								<option value="all">---------Select-------</option>
								 </select>
								</div>
							</div>
							
							
							
						</div>

						<div class="col-md-6"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d; margin-top: 10px;">

									<div class="form-group clearfix">

								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Due Date</label>
								<div class="col-xs-7">
							
									<input type="text" id="" name="" tabindex="1" class="form-control" ></input>

								</div>
							</div>

							<div class="form-group clearfix">

								<label for="inputPassword" class="control-label col-xs-5"
									style="text-align: right; line-height: 35px;">Copy Number</label>
								<div class="col-xs-7">
							
									<input type="text" id="copynumber" name="numberofcopy" tabindex="1" class="form-control" ></input>
                                   </div>
								
							</div>
						</div>
		




					</div>
				</div>
				
					<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="" style="padding: 15px;">
				
					<div class="row">
			<div id="collapseOne" class="panel-collapse collapse in ">
					<div class="panel-body own-panel">
						<div class="row"></div>
                        
						<table class='table' id='allstudent' style="width: 100%">
							<thead>
								<tr>
									<th style="text-align: center"><input type='checkbox' name='select' class='selectall' id='selectall' style='text-align: center' /></th>
									<th>Journal Name</th>
									<th>Accession Number</th>
									<th>Due Date</th>
									<th>Copy Number</th>
									<th>Received</th>
									</tr>
							</thead>
							<tbody>

							</tbody>

						</table>
						
					

			</div>
     	 </div>
     	 
					
					<div class='pagebanner' class="panel panel-primary clearfix">
				
					<select id='show_per_page' >
					<option value='50'>50</option>
					<option value='100'>100</option>
					<option value='200'>200</option>
					<option value='300'>300</option>
					<option value='400'>400</option>
					<option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
					<div class='pagination pagelinks' style="margin-left:650px; padding-bottom:20px;"></div>
					
				</div>
				<br />
			</div>
		</div>
	</div>
				
			</div>
			
			
			
		</div>
	</div>
</body>
</html>
