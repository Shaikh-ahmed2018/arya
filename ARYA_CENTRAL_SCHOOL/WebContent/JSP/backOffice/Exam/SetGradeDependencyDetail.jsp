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
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.menu.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Exam/SetGradeDependencyDetail.js"></script>
<style>
#allstudent {
	width: 100%;
}
.allstudent {
	width: 100%;
}
.form-control[disabled],.form-control[readonly],fieldset[disabled] .form-control
	{
	background-color: #eee;
	opacity: 1;
	width: 191px;
}

.scrollbar {
	overflow-y: scroll;
	height: 400px;
}

#allstudent th {
	text-align: center;
	width: 37px; 
}
.allstudent td {
	text-align: center;
}
</style>
</head>
<body>
	
		<div id="dialog2">
			<div class="col-md-12" id="txtstyle"
				style="font-size: 11pt; color: #5d5d5d;">
				

				<table id="allstudent" class="dialog2">
					<tr>
						<th class="project" style="display: none;">Project</th>
						<th class="assignment" style="display: none;">Assignment</th>
						<th class="practical" style="display: none;">Practical</th>
                        <th class="attendance" style="display: none;">Attendance</th>
					</tr>
					<tr>

					<td class="project" style="display: none; text-align: center;"><input
						type="text" id="projectmark" style="width: 50px;" /></td>
					<td class="assignment" style="display: none; text-align: center;"><input
						type="text" id="assignmentmark" style="width: 50px;" /></td>
					<td class="practical" style="display: none; text-align: center;"><input
						type="text" id="practicalmark" style="width: 50px;" /></td>
					<td class="attendance" style="display: none; text-align: center;"><input
						type="text" id="attendancemark" style="width: 50px;" /></td>
				</tr>

				</table>
			</div>
		</div>
		
	
		<div id="dialog1">
			<div class="col-md-12" id="txtstyle"
				style="font-size: 11pt; color: #5d5d5d;">

				<table class="table allstudent" >
				<tr id="theory">
					<td> <input type="checkbox" id="selectall"/></td>
						<td>Select all </td>
					</tr>
					<tr id="project">
					<td><input type="checkbox" value="project" class="select"/></td>
						<td>Project</td>
					</tr>
					<tr id="assignment">
					<td><input type="checkbox" value="assignment" class="select"/></td>
						<td>Assignment</td>
					</tr>
					<tr id="practical">
					<td><input type="checkbox" value="practical" class="select"/></td>
						<td>Practical</td>
					</tr>
					<tr id="attendance">
					<td><input type="checkbox" value="attendance" class="select"/></td>
						<td>Attendance</td>
					</tr>

				</table>
			</div>
		</div>


	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Set
				Grade Dependency</span>
		</p>

		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="validateTips"><bean:write name="successmessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>




            
		<input type="hidden" id="hiddenlocid"
			value='<logic:present name="locationid"  scope="request"><bean:write name="locationid" /></logic:present>' />
		<input type="hidden" id="hiddenclassid"
			value='<logic:present name="subjectClassList"  scope="request"><bean:write name="subjectClassList" /></logic:present>' />
		<input type="hidden" id="hiddenstartaccyear"
			value='<logic:present name="startDate"  scope="request"><bean:write name="startDate" /></logic:present>' />
		<input type="hidden" id="hiddenendaccyear"
			value='<logic:present name="endDate" scope="request"><bean:write name="endDate"  /></logic:present>' />
     <input type="hidden" id="hclass"/>
     <input type="hidden" id="hsection">
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a href="#" style="color: #767676"><h4 class="panel-title examdetails"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Set Grade Dependency
						</h4></a>
					
					<div class="navbar-right">
						<span class="buttons" id="back">Back</span>
					</div>
				</div>
				<div class='clearfix' id="inputcolor">
					<div class="col-md-6" id="txtstyle"
						style="font-size: 11pt; color: #5d5d5d;">
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: left; line-height: 35px;">School Name</label>
							<div class="col-xs-5">
								<input type="text" readonly="readonly" name="accyear"
									onkeypress="HideError()" id="location" class="form-control"
									style="text-align: left;"
									value='<logic:present name="currentlocation" scope="request"><bean:write name="currentlocation" ></bean:write></logic:present>' />
							</div>
							<input type="hidden" name="hiddenaccyid" id="hiddenaccyid"
								value='<logic:present name="accyId" scope="request"><bean:write name="accyId"></bean:write></logic:present>' />
						</div>
						<div class="form-group clearfix">
							<label for="inputEmail" class="control-label col-xs-4"
								style="text-align: left; line-height: 35px;">Academic
								Year</label>
							<div class="col-xs-5">
								<input type="text" readonly="readonly" name="accyear"
									onkeypress="HideError()" id="accyear" class="form-control"
									style="text-align: left;"
									value='<logic:present name="accyName" scope="request"><bean:write name="accyName" ></bean:write></logic:present>' />
							</div>

						</div>

					</div>
					<div class="col-md-12">
						<div class="col-md-6" id="txtstyle"
							style="font-size: 11pt; color: #5d5d5d;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: left; line-height: 35px; margin-left: -11px;">Exam
									Code</label>
								<div class="col-xs-5">
									<input type="text" readonly="readonly" name="examCode"
										onkeypress="HideError()" id="examCode" class="form-control"
										style="text-align: left;"
										value='<logic:present name="examCode" scope="request"><bean:write name="examCode" ></bean:write></logic:present>' />
								</div>
								<input type="hidden" name="hiddenexamid" id="hiddenexamid"
									value='<logic:present name="examid" scope="request"><bean:write name="examid"></bean:write></logic:present>' />
							</div>
						</div>

						<div class="col-md-6" id="txtstyle"
							style="font-size: 11pt; color: #5d5d5d;">
							<div class="form-group clearfix">
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: left; line-height: 35px;">Exam Name</label>
								<div class="col-xs-5">
									<input type="text" readonly="readonly" name="examName"
										onkeypress="HideError()" id="examName" class="form-control"
										style="text-align: left;"
										value='<logic:present name="examName" scope="request"><bean:write name="examName" ></bean:write></logic:present>' />
								</div>

							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="hiddenaccyear"
					value='<logic:present name="accyear"  scope="request"><bean:write name="accyear" /></logic:present>' />
				<div class="form-group scrollbar">

					<table style="background: #fff;" class="allstudent">
						<thead>
							<tr>
								<th
									style="font-size: 13px; text-align: center; width: 95px; padding: 9px;">Sl.
									No</th>
								<th style="font-size: 13px; text-align: center; padding: 9px;">Class</th>
								<th style="font-size: 13px; text-align: center; padding: 9px;">Section</th>
								<th style="font-size: 13px; text-align: center; padding: 9px;">No
									Of Students</th>
								<th style="font-size: 13px; text-align: center; padding: 9px;">Status</th>
							</tr>
						</thead>
						<tbody>
							<logic:present name="subjectClassList" scope="request">
								<logic:iterate id="subjectClassList" name="subjectClassList"
									scope="request">
									<tr>
										<td class="examid"><bean:write name="subjectClassList"
												property="sno1"/></td>
										<td class="classId"
											id="<bean:write name="subjectClassList" property="classId"/>"><bean:write
												name="subjectClassList" property="classname" /></td>
										<td class="sectionId"
											id="<bean:write name="subjectClassList" property="section" />"><bean:write
												name="subjectClassList" property="sectionName" /></td>

										<td class="tot_strenght"><bean:write
												name="subjectClassList" property="tot_strength" /></td>
										<td style='text-align: center;'><span
											class='buttons depend'>Dependency</span></td>
									</tr>
								</logic:iterate>
							</logic:present>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>

</html>