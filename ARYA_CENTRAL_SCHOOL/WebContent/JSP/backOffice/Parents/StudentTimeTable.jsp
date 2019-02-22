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

<script type="text/javascript"
	src="JS/backOffice/Parents/StudentTimeTable.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
#editAssId:hover {
	cursor: pointer;
}
</style>
</head>

<body>
 
	<div class="col-md-10 col-md-offset-2" style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">

		<div class="col-md-7" id="div2">
			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span id="pageHeading">Time Table</span>
			</p>
		</div>

		<div class="input-group col-md-5" style="margin: 20px 0px;">
			<div class="form-group">
				<label for="inputPassword" class="control-label col-xs-3"
					style="text-align: left; line-height: 35px; color: #797676; font-size: 16px; font-family: Open Sans Light;">Student</label>
				<div class="col-xs-7">
					<logic:present name="studentlist" scope="request">
						<select class="form-control" name="stuid" id="timetableid">
							<logic:iterate id="stuid" name="studentlist" scope="request">
								<option value='<bean:write name='stuid' property='stdAdmisiionNo'/>'>
									<bean:write name='stuid' property='studentFnameVar' /></option>
							</logic:iterate>
						</select>
					</logic:present>
				</div>
			</div>
		</div>
		
		<input type="hidden"  id="hiddenclass" value='<logic:present name="classid"><bean:write name="classid" /></logic:present>'></input>
		<input type="hidden"  id="hiddenloc" value="<logic:present name="locid" ><bean:write name="locid"/></logic:present>" />
		<input type="hidden"  id="hiddensec" value="<logic:present name="secid" ><bean:write name="secid" /></logic:present>" />
		<input type="hidden"  id="parenthidden" value="<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" /></logic:present>" />
		
		<div class="errormessagediv" align="left" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="successmessagediv" align="left" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span class="validateTips"></span></a>
			</div>
		</div>

		<logic:present name="fail" scope="request">
			<div class="successmessagediv1">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write name="fail" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="panel panel-primary">
			<div class="panel-heading">
				
					<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" style="color: #767676;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;<h3 class="panel-title" style="color: #767676;">Time Table</h3>
					</a>
				
				<div class="navbar-right">
					<!--  <span id="editAssId" class="glyphicon glyphicon-pencil2"   data-toggle="tooltip" data-placement="bottom" title="Edit"></span>
				 -->
				</div>
			</div>

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<div id="mytable"></div>
					<%-- <div class="col-md-12" id="scrolid" style="padding: 0; overflow: scroll;">
						<logic:present name="timeTableDetails" scope="request">
							<input type="hidden" value="<bean:write name="timeTableDetails" />" id="hiddenId" />
							<display:table id="allstudent" name="timeTableDetails" class="table" 
								requestURI="/parentMenu.html?method=getStudentTimetable" export="false">
								<display:column property="dayid" title="Day" class="hidden" headerClass="hidden" />
								<display:column property="dayname" title="Day" />
								<display:column property="period1" title="Period 1" />
								<display:column property="period2" title="Period 2" />
								<display:column property="period3" title="Period 3" />
								<display:column property="period4" title="Period 4" />
								<display:column property="period5" title="Period 5" />
								<display:column property="period6" title="Period 6" />
								<display:column property="period7" title="Period 7" />
								<display:column property="period8" title="Period 8" />
							</display:table>
						</logic:present>
						<logic:present name="hiddentstudentid" scope="request"> 
							<input type="hidden" id="studentid" value="<bean:write name="hiddentstudentid" scope="request"/>">
						</logic:present>
					</div> --%>
					<br>
				</div>
			</div>
			</div>
</body>
</html:html>