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
<script type="text/javascript"
	src="JS/backOffice/Parents/FeedbackList.js"></script>
<style>
.navbar-right {
	margin: -18px -14px;
}
#allstudent th:nth-child(1){
width: 20%;

}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">

		<p style="margin: 16px 0px;">
			<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
				id="pageHeading">Feed Back Details</span>
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
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Feed
						Back Details
					</h3></a>

				<div class="navbar-right">




					<a href="parentfeedback.html?method=feedbackentry"><span
						id="view" class="buttons" data-toggle="modal"
						data-toggle="tooltip" data-placement="bottom" title="List">Add</span></a>

				</div>

				<script>
								$(document).ready(function() {
									$('[data-toggle="tooltip"]').tooltip();
								});
							</script>



				<script>
				$(document).ready(function() {
					$('[data-toggle="tooltip"]').tooltip();
				});
			</script>


				<input type="hidden" name="feedbackhidden" id="feedbackid" value="" />

			</div>



			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">


					<logic:present name="feedbacklist" scope="request">
						<display:table class="remarks table" pagesize="10"
							name="requestScope.feedbacklist"
							requestURI="parentMenu.html?method=sendfeedback" id="allstudent">


							

							<display:column property="feedbackto" sortable="true"
								title="FeedBackTo<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="description" sortable="true"
								title="Description<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column sortable="true"
								title="File Download<img src='images/sort1.png' style='float: right'/>">
								<span class="" onclick="downloadfunction(this)"
									id='${allstudent.feedbackid},download'>Download</span>
							</display:column>
						</display:table>
					</logic:present>
				</div>
			</div>





			<br>
		</div>
</body>
</html:html>