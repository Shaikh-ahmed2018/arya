<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<!-- <script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script> -->
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>

<link href="CSS/newUI/custome.css" rel="stylesheet" />

<script type="text/javascript"
	src="JS/backOffice/Transport/routeMaster.js"></script>

<style>
.savestops:hover {
	cursor: pointer;
}
</style>

<style>
#list:hover {
	cursor: pointer;
}
</style>

<style>
.buttons{

vertical-align: 0px;

}
</style>


</head>

<body>
	<div class="col-md-10 col-md-offset-2">
		<p>
			<img src="images/addstu.png" />&nbsp;<span>New Stop</span>
		</p>

		
			<div class="successmessagediv" align="center" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>

			<div class="errormessagediv1" align="center"  style="display: none;">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 30%;"><span class="validateTips1"></span></a>
				</div>
			</div>
		

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #767676"> <i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;<h4 class="panel-title">Stop
							Details
						</h4></a>
					

						<div class="navbar-right" >
							
							<span class="buttons" id="savestops"> Save</span>
							
							<span class="buttons" id="list">List</span>
							
							<a href=""><img src="images/download.png" class="download"></a>
							
						</div>
					
				</div>
				<form action="transport.html?method=addRoute" id="formRoute"
					method="post">
					<div id="collapseOne" class="accordion-body collapse in">
						<div class="panel-body"
							style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">




							<table class="table" id="allstudent">
								<tr id="rowId0" class="headth">
									<td style="font-size: 14px; text-align: center;">Stop No</td>
									<td style="font-size: 14px; text-align: center;">Stop Name</td>
									<td style="font-size: 14px; text-align: center;">Arrival
										Time</td>
									<td style="font-size: 14px; text-align: center;">Halt Time
										(in Mins)</td>
									<td style="font-size: 14px; text-align: center;">Departure
										Time</td>
									<td style="font-size: 14px; text-align: center;">Distance
										(in Km)</td>
								</tr>
							</table>

							<input type="hidden" name="routeCode" id="routeCode"
								value='<logic:present name="stopdetails"><bean:write name="stopdetails" property="routeCode"/></logic:present>' />

							<input type="hidden" name="totalstopsid" id="totalstopsid"
								value='<logic:present name="stopdetails"><bean:write name="stopdetails" property="totalStops"/></logic:present>' />

							<input type="hidden" name="hidstopid" id="hidstopid"
								value='<logic:present name="stopdetails"><bean:write name="stopdetails" property="halttime"/></logic:present>' />

							<input type="hidden" name="stopNoArray" id="stopNoArray" /> 
							<input	type="hidden" name="stopArrivalTimeArray" id="stopArrivalTimeArray" /> 
							<input type="hidden" name="stopNameArray" id="stopNameArray" /> 
							<input type="hidden" name="stopHaltTimeArray" id="stopHaltTimeArray" />
							<input type="hidden" name="stopDistanceArray" id="stopDistanceArray" />
							<input type="hidden" name="stopDepartureTimeArray" 	id="stopDepartureTimeArray" /> 
							<input type="hidden" name="routeCode" id="routeCode" />
							 <input type="hidden" name="parameter" id="methodNameId" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>
