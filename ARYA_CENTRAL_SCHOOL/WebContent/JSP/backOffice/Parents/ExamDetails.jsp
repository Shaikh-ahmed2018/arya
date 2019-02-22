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
	src="JS/backOffice/Parents/ExamDetails.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.buttons {
	vertical-align: 0px;
}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<div class="col-md-7" id="div2">
			<p style="margin: 16px 0px;">
				<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Exam Details </span>
			</p>
		</div>

		<div class="input-group col-md-5" style="margin: 20px 0px;">

			<div class="form-group">
				<label for="inputPassword" class="control-label col-xs-3"
					style="text-align: left; line-height: 35px; color: #797676; font-size: 16px; font-family: Open Sans Light;">Student</label>
				<div class="col-xs-7">
					<logic:present name="studentlist" scope="request">
						<select name="studentFname" id="parentchild" class="form-control">
							<logic:iterate id="stream" name="studentlist" scope="request">
								<option
									value='<bean:write name='stream' property='stdAdmisiionNo'/>'>
									<bean:write name='stream' property='studentFnameVar' /></option>
							</logic:iterate>
						</select>
					</logic:present>
				</div>
			</div>
		</div>

		<input type="hidden" name="hidden" class="hiddenclass" id="hiddenid" value='<logic:present name="studentexam"><bean:write name="studentexam" />
	  	</logic:present>'></input>
		<input type="hidden" name="parenthidden" id="parenthidden" value="<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="parentID"/></logic:present>" />

		<input type='hidden' id='hiddenloc' value='<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="locid"/></logic:present>'>
		<input type='hidden' id='hiddenclassid' value='<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="classDetailId"/></logic:present>'>
		<input type='hidden' id='hiddensectionid' value='<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" property="sectionid"/></logic:present>'>

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
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write name="successmessagediv" scope="request" /></span></a>
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
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write name="fail" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>


		<div class="panel panel-primary">
			<div class="panel-heading">
				
				<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne"  style="color: #767676">
						<span class="glyphicon glyphicon-menu-hamburger"><h3 class="panel-title" style="color: #767676;"></span>&nbsp;&nbsp;Exam Details</h3></a>
					
				<div class="navbar-right" style="float: right; margin: -31px 0;">
					<span id="viewExamdetails" class="buttons" style="top: 38px; margin-right: 1px;">View</span>
				</div>
			</div>
			

			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

					<logic:present name="examlist" scope="request">
						<display:table class="remarks table" name="requestScope.examlist"
							requestURI="parentMenu.html?method=examdetails" id="allstudent">

							<%-- <display:column property="sno1" sortable="true" title="S.No<img src='images/sort1.png' style='float: right'/>"></display:column> --%>

							<display:column sortable="true" title="Select"><input type="radio" name='examid' value="${allstudent.examid}" />
							</display:column>

							<display:column property="examName" sortable="true"
								title="Exam Name<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="startDate" sortable="true" title="Start Date<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="endDate" sortable="true" title="End Date<img src='images/sort1.png' style='float: right'/>"></display:column>

						</display:table>
					</logic:present>
				</div>
				<br>
			</div>
		</div>
</body>
</html:html>