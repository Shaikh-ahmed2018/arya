<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<title>Campus School Stream</title>
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
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/Admin/Latheef.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.navbar-right {
    margin: -18px -10px;
}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">


		<p style="margin: 16px 0px;">
			<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
				id="pageHeading">Monthly Attendance</span>
		</p>
		<div class="errormessagediv" align="left" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<div class="successmessagediv" align="left" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="fail" scope="request">
			<div class="successmessagediv1">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
								name="fail" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne" style="color: #fff;"><h3
						class="panel-title" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Monthly Attendance
						
					</h3></a>
				
			<div class="navbar-right">
		
				<span id="back" class="buttons" style="margin-right: 15px;top:25px;" >Back</span>
									</a>
				</div>
			</div>
			<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				


					
					<logic:present name="attendancelist" scope="request">
						<display:table class="firststudent table" id="allstudent"
							name="requestScope.attendancelist"
							requestURI="/parentMenu.html?method=getAttendanceView" export="false">
							
							
							
							<display:column property="sno" sortable="true"
								title="S.No<img src='images/sort1.png' style='float: right'/>" />
							 
							
							<display:column property="date" sortable="true"
								title="Date<img src='images/sort1.png' style='float: right'/>" />
								
									<display:column property="weekday" sortable="true"
								title="WeekDay<img src='images/sort1.png' style='float: right'/>" />
						
							
							<display:column property="status" sortable="true"
								title="Status <img src='images/sort1.png' style='float: right'/>" />
						
						</display:table>

					</logic:present>
					
					<%-- <logic:present name="attendancelist1" scope="request">
						<display:table class="secondstudent table" id="allstudent"
							name="requestScope.attendancelist1" requestURI="/parentMenu.html?method=getAttendanceView" export="false" pagesize="10">
							
							
							
							<display:column property="sno" sortable="true"
								title="S.No<img src='images/sort1.png' style='float: right'/>" />
							 
							
							<display:column property="date" sortable="true"
								title="Date<img src='images/sort1.png' style='float: right'/>" />
								
									<display:column property="weekday" sortable="true"
								title="WeekDay<img src='images/sort1.png' style='float: right'/>" />
							
							
							<display:column property="status" sortable="true"
								title="Status <img src='images/sort1.png' style='float: right'/>" />
						
						</display:table>

					</logic:present>
					 --%>
				

				</div>
				<br>
			</div>
		</div>
</body>
</html:html>