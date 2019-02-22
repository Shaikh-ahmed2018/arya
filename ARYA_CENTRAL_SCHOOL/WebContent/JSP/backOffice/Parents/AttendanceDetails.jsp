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

<script type="text/javascript" src="JS/backOffice/Parents/AttendanceDetails.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.buttons{

vertical-align: 0px;

}
.navbar-right {
   margin: -18px -14px;
}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">

		<div class="col-md-7" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Attendance Details</span>
			</p>
			</div>
			
			
			<div class="input-group col-md-5" style="margin: 20px 0px;">

			<div class="form-group">
				<label for="inputPassword" class="control-label col-xs-3"
					style="text-align: left; line-height: 35px; color: #797676; font-size: 16px; font-family: Open Sans Light;">Student</label>
			<div class="col-xs-7">
				<logic:present name="studentlist" scope="request">
					<select class="form-control" name="studSectionId" id="studAttnId">
						<logic:iterate id="stream" name="studentlist" scope="request">
							<option value='<bean:write name='stream' property='stdAdmisiionNo'/>'>
								<bean:write name='stream' property='studentFnameVar'/></option>
						</logic:iterate>
					 </select>
				</logic:present>
			</div>
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
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>



		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="fail" scope="request">
			<div class="successmessagediv">
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
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Attendance
						Details
					</h3></a>
				
			
			<div class="navbar-right">
				 <span id="viewAttendanceId" class="buttons" style="top:25px;margin-right:13px;">View</span>
			</div>
			</div>
			<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>
			
			<input	type="hidden" name="studenthidden" id="studentid" value='<logic:present name="attnstudentid"><bean:write name="attnstudentid" /></logic:present>'></input>	
		 	<input	type="hidden" name="attnhidden" id="monthid" value=""/>		
		 	<input	type="hidden" name="attnhidden1" id="currentyearid" value=""/>		
			
			<input	type="hidden" name="parenthidden" id="parenthidden" value="<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="parentID"/></logic:present>"/>		
	
			<input	type="hidden" name="studenthidden" id="studentid1" value='<logic:present name="hiddenstudentid"><bean:write name="hiddenstudentid" /></logic:present>'></input>			
			
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								
					<logic:present name="attendancelist" scope="request">
						<display:table class="table" id="allstudent" name="requestScope.attendancelist"
							requestURI="/parentMenu.html?method=attendancedetails" export="false">
							
 							 <display:column title="Select" headerClass="heading1">
								<input type="checkbox" name="getempid" onClick='getvaldetails(this)' value=""
								id="${allstudent.month},${allstudent.currentyear}"> </>
							</display:column>
							
							<display:column property="month" sortable="true"
								title="Month<img src='images/sort1.png' style='float: right'/>" />
							<display:column property="totaldays" sortable="true"
								title="TotalDays <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="tot_count" sortable="true"
								title="Total WorkingDays <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="present_count" sortable="true"
								title="Total PresentDays <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="absent_count" sortable="true"
								title="Total AbsentDays <img src='images/sort1.png' style='float: right'/>" />
							<display:column property="leave_count" sortable="true"
								title="Total Leaves <img src='images/sort1.png' style='float: right'/>" />
								<display:column property="holiday_count" sortable="true"
								title="Total Holidays <img src='images/sort1.png' style='float: right'/>" />
						</display:table>
					</logic:present>
				</div>
				<br />
			</div>
		</div>
</body>
</html:html>