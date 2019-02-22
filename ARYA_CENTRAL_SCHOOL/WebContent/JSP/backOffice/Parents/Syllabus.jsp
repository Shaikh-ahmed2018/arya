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
	src="JS/backOffice/Parents/SyllabusDetails.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
#allstudent th:nth-child(1) {
	width: 70px;
	text-align: center;
}
#allstudent th:nth-child(4) {
	width: 200px;
	text-align: center;
}
#allstudent td:nth-child(4) {
	text-align: center;
}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">

		<div class="col-md-8" id="div2">

			<p style="margin: 16px 0px;">
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
					id="pageHeading">Syllabus Details</span>
			</p>
		</div>


		<input type="hidden" id="hclassid" value="<logic:present name="studentData"><bean:write name="studentData" property="classDetailId" /></logic:present>" />
		<input type="hidden" id="hlocid" value="<logic:present name="studentData"><bean:write name="studentData" property="locid" /></logic:present>" />

		<div class="input-group col-md-4" style="margin: 20px 0px;">
				<div class="form-group" style="margin-bottom: 0;">
						<label for="inputPassword" class="control-label col-xs-3" style="text-align: right; line-height: 35px; color: #797676; font-size: 16px; font-family: Open Sans Light;">Student</label>
					<div class="col-xs-9">
							<select class="form-control" name="stuid" id="stuid">
							<logic:present name="stuList" scope="request">
							<logic:iterate id="stuList" name="stuList" scope="request">
								<option value='<bean:write name='stuList' property='stdAdmisiionNo'/>'>
									<bean:write name='stuList' property='studentFnameVar' /></option>
							</logic:iterate>
							</logic:present>
							</select>
					</div>
				</div>

			<!-- <div class="form-group" style="margin-bottom: 0;">
				<label for="inputPassword" class="control-label col-xs-2"
					style="text-align: right; line-height: 35px; color: #797676; font-size: 16px; font-family: Open Sans Light;">Class</label>
				<div class="col-xs-4">
					<select class="form-control" name="studSectionId" id="classid">
						<option value=""></option>
					</select>
				</div>
			</div> -->
		</div>

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
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Syllabus
						Details
					</h3></a>

				<div class="navbar-right"></div>
			</div>

			<input type="hidden" name="subjectid" id="subjectid" value="" /> <input
				type="hidden" name="attnhidden" id="monthid" value="" /> <input
				type="hidden" name="attnhidden1" id="currentyearid" value="" /> <input
				type="hidden" name="parenthidden" id="parenthidden"
				value="<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="parentID"/>
	</logic:present>" />

			<input type="hidden" name="studenthidden" id="studentid1"
				value='<logic:present name="hiddenstudentid"><bean:write name="hiddenstudentid" />
													</logic:present>'></input>



			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					<div id="mytable"></div>
				</div>
				<br>
			</div>
		</div>
</body>
</html:html>