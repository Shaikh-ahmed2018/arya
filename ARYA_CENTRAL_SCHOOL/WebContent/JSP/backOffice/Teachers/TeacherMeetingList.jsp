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

<!-- <script type="text/javascript" src="JS/backOffice/Parents/ExamDetails.js"></script>  -->

<script type="text/javascript"
	src="JS/backOffice/Teacher/TeacherMeetingAndEvent.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.downloadlast:hover{

cursor: pointer;
}
#excelDownload:hover {
	cursor: pointer;
}
#pdfDownload:hover {
	cursor: pointer;
}
</style><style>
.buttons{

vertical-align: 0px;

}
</style>
</head>

<body>

	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">


		<div class="col-md-7" id="div2">

			<p style="margin: 16px 0px;">

				<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Meetings & Events
				</span>


			</p>
		</div>

		<div class="input-group col-md-5" style="margin: 20px 0px;">


			<div class="form-group">
				<label for="inputPassword" class="control-label col-xs-3"
					style="text-align: left; line-height: 35px; color: #797676; font-size: 16px; font-family: Open Sans Light;">Teacher</label>
				<div class="col-xs-7">

					<logic:present name="teacherlist" scope="request">
						<select name="studentFname" id="parentchild" class="form-control">
							<option value="">---Select---</option>
							<logic:iterate id="stream" name="teacherlist" scope="request">
								<option value='<bean:write name='stream' property='teacherID'/>'>
									<bean:write name='stream' property='userName' />
								</option>
							</logic:iterate>
						</select>
					</logic:present>

				</div>
			</div>


		</div>

		<input type="hidden" name="hidden" class="hiddenclass" id="hiddenid"
			value='<logic:present name="studentmeeting"><bean:write name="studentmeeting" />
													</logic:present>'></input>

		<input type="hidden" name="parenthidden" id="parenthidden"
			value="<logic:present name="parenthiddenid" ><bean:write name="parenthiddenid" /></logic:present>" />

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
						<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Meetings&Events

					</h3></a>

				<div class="navbar-right">
					 <span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span>

				</div>
				<script>
					$(document).ready(function() {
						$('[data-toggle="tooltip"]').tooltip();
					});
				</script>
			</div>
			<!-- pop up -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
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
								id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
						</div>

					</div>
				</div>
			</div>


			<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">







					<logic:present name="meetinglist" scope="request">
						<display:table class="meeting table" pagesize="10"
							name="requestScope.meetinglist"
							requestURI="teachermenuaction.html?method=viewMeetingdandEvents"
							decorator="com.centris.campus.decorator.MeetingDecorator"
							id="allstudent">
							<display:column property="check" sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" />

							<display:column property="title" sortable="true"
								title="Subject Name <img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="meetingDate" sortable="true"
								title="Meeting Date<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="startTime" sortable="true"
								title="Start Time<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="endTime" sortable="true"
								title="End Time<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="meetingwith" sortable="true"
								title="Name<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="classname" sortable="true"
								title="Class Name<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="sectionname" sortable="true"
								title="Section Name<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="subjectName" sortable="true"
								title="Class Subject<img src='images/sort1.png' style='float: right'/>"></display:column>


						</display:table>

					</logic:present>










					<%-- 
					<logic:present name="meetinglist" scope="request">
						<display:table class="meeting table" pagesize="10"
							name="requestScope.meetinglist"
							requestURI="parentMenu.html?method=meetingandeventsdetails"
							decorator="com.centris.campus.decorator.MeetingDecorator"
							id="allstudent">
							<display:column property="check" sortable="true"
								title="<input type='checkbox' name='selectall' id='selectall' onClick='selectAll()' />" />

							<display:column property="title" sortable="true"
								title="Subject Name <img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="meetingDate" sortable="true"
								title="Meeting Date<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="startTime" sortable="true"
								title="Start Time<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="endTime" sortable="true"
								title="End Time<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="meetingwith" sortable="true"
								title="Name<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="classname" sortable="true"
								title="Class Name<img src='images/sort1.png' style='float: right'/>"></display:column>
							<display:column property="sectionname" sortable="true"
								title="Section Name<img src='images/sort1.png' style='float: right'/>"></display:column>

							<display:column property="subjectName" sortable="true"
								title="Class Subject<img src='images/sort1.png' style='float: right'/>"></display:column>


						</display:table>

					</logic:present>	
					 --%>



				</div>
				<br>
			</div>
		</div>
</body>
</html:html>