<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>

<script type="text/javascript" src="JS/common.js"></script>


<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/common.js"></script>
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/fancybox/1.3.4/jquery.fancybox-1.3.4.pack.min.js"></script> -->
<script type="text/javascript" src="JS/backOffice/Events/Resultprinting.js"></script> 

<style>
.glyphicon:hover {
	cursor: pointer;
}

.buttons {
	top: 10px !important;
}

.controls.style {
	margin-top: -32px;
}

.searchWrap {
	padding: 6px;
}

#individualPart thead tr {
	border: 1px solid #dedede;
}

#individualPart thead,#individualPart thead tr th:nth-child(9) {
	border: none;
}

#allstudent thead tr {
	border: 1px solid #dedede
}

#allstudent thead,#allstudent thead tr th:nth-child(4) {
	border: none;
}
#allstudent tr td:hover,.allstudent tr td:hover,#allstudents tr td:hover
	{
	background: #9CDDE3;
}
#allstudent tr:nth-child(2n),.allstudent tr:nth-child(2n),#allstudents tr:nth-child(2n)
	{
	background-color: transparent;
}

#allstudent tr:nth-child(n) td,.allstudent tr:nth-child(n) td,#allstudents tr:nth-child(n) td
	{
	background-color: #F2FAFC;
}

#allstudent tr:nth-child(2n) td,.allstudent tr:nth-child(2n) td,#allstudents tr:nth-child(2n) td
	{
	background-color: #FFFFFF;
}

#allstudent tbody tr td,#allstudent thead tr th,.allstudent tbody tr td,.allstudent thead tr th
	{
	position: relative;
	min-width: 30px;
}

.deleteRow {
	height: 20px;
	line-height: 16px;
	position: absolute;
	right: 0px;
	top: 0;
	bottom: 0;
	margin: auto;
}

.deleteRowInd {
	height: 20px;
	line-height: 16px;
	positionglyphicon:hover: absolute;
	right: 0px;
	top: 0;
	bottom: 0;
	margin: auto;
}

#allstudent tbody td {
	border: 1px solid #dedede;
	padding: 5px;
}

.pagebanner {
	margin-top: 13px;
}

.pagination {
	margin-right: 10px;
	float: right;
	margin-top: 10px;
}

.form-control multiple {
	height: auto !important;
}

.glyphicon-plus:before {
	content: "\2b";
	margin-right: -3px;
}

div[class^=col-md] {
	font-family: Open Sans Light;
	font-size: 11pt;
	color: #5d5d5d;
}

table[id^=participants] {
	width: 100%;
}

select#participantsList {
	width: 116%;
	height: 190px !important;
}

.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons
	{
	width: 75% !important;
	left: 0 !important;
	right: 0 !important;
	margin: auto;
}

#allstudent tbody  tr table tr.captainHighlight td {
	background-color: #07aab9;
	color: #fff;
}

#allstudent tbody td {
	border: none;
	vertical-align: middle;
}

.navbar-right {
	top: -3px;
}
</style>
</head>
<body>


	<div class="col-md-10 col-md-offset-2" id="div1">
	<div id="dialog"></div>
	<div id="deleteDialog"></div>
		<div class="searchWrap">
			<div id="div2"><p><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Result Printing</span></p>
			</div>
		</div>
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<div class="panel panel-primary clearfix">
			<div class="panel-heading clearfix">
				<a data-toggle="collapse" data-parent="#accordion2" href="#"
					style="color: #fff;"><h3 class="panel-title class" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Result Printing</h3></a>
						
				<div class="navbar-right">
						<span class="buttons" id="print" style="display: none;">Print</span> 
				</div>		
						
			</div>

			<div id="classOne" class="accordion-body collapse in">
				<div class="panel-body"id="printArea">

					<div class="col-md-6 groupData" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames">Event Name</label>
							<div class="col-xs-7">
								<select id="eventname" name="eventname" class="form-control">
									<logic:present name="eventList">
										<option value="">----------Select----------</option>
										<logic:iterate id="eventList" name="eventList">
											<option
												value="<bean:write name="eventList" property="eventId"/>"><bean:write name="eventList" property="eventName" /></option>
										</logic:iterate>
									</logic:present>
								</select>
							</div>
						</div>
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames">Category Name</label>
							<div class="col-xs-7">
								<select id="categoryName" name="categoryName"
									class="form-control">
									<option value="">----------Select----------</option>
								</select>
							</div>
						</div>

					</div>


					<div class="col-md-6 groupData" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames">Program Name</label>
							<div class="col-xs-7">
								<select id="programName" name="programName" class="form-control">
									<option value="">----------Select----------</option>
								</select>
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								align="right" id="inputnames">Result Type</label>
							<div class="col-xs-7">
								<select id="resultType" name="resultType" class="form-control">
									<option value="Tabulation">Tabulation</option>
									<option value="Criteriawise">Criteriawise</option>
									<option value="Postionwise">Postionwise</option>
								</select>
							</div>
						</div>
					</div>



			<table style="width: 100%;" id='second' class='allstudent'>
						<thead>	<tr>
								<th>Sl.No.</th>
								<th>Programme Name</th>
								<th>Student Details</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
						</table>






				</div>
			</div>


			<div></div>

		
				
			</div>

		</div>
</body>
</html>