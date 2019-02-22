
<!-- Written By Swathi -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="JS/backOffice/Exam/examcreation.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />

<style>
.save:hover {
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

	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Exam Time Table
				Details</span>
		</p>

		<center>
			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>

			<div class="errormessagediv1" style="display: none;">
				<div class="message-item1">
					<!-- Warning -->
					<a href="#" class="msg-warning1 bg-msg-warning1"
						style="width: 40%;"><span class="validateTips"></span></a>
				</div>
			</div>


		</center>

		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Exam
							Timetable Details
						</h4></a>
						
						<div class="navbar-right">

							<span class="buttons"  id="save">Save</span>&nbsp;
							<span id="back" class="buttons">Back</span></a>
						</div>

				</div>
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">

						<div class="col-md-6" id="txtstyle">


							<br />
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Start
									Date </label>
								<div class="col-xs-7">
									<div id="" class="input-append">
										<input type="text" readonly="readonly" name="date"
											id="startDate" class="form-control" placeholder="Click Here"
											value="<bean:write name="examDate" property="startDate"/>" />

									</div>
								</div>
							</div>
							<br />

						</div>
						<div class="col-md-6" id="txtstyle">

							<br />
							<div class="form-group">
								<label for="inputPassword" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">End Date</label>
								<div class="col-xs-7">
									<div id="" class="input-append">
										<input type="text" readonly="readonly" name="date"
											id="endDate" class="form-control" placeholder="Click Here"
											value="<bean:write name="examDate" property="endDate"/>" />

									</div>
								</div>
							</div>
						</div>

						<logic:present name="exam_list" scope="request">

							<table id="exam" class="view" style="visibility: hidden">

								<logic:iterate id="exam" name="exam_list" indexId="index">
									<tr>
										<td><input type="hidden" name="examId" id="examId"
											value="<bean:write name="exam" property="examId"/>"
											readonly="readonly" /></td>

										<td><input type="hidden" name="classid" id="classid"
											value="<bean:write name="exam" property="classId"/>"
											readonly="readonly" /></td>
									</tr>
								</logic:iterate>

							</table>

						</logic:present>



						<logic:present name="subject_list" scope="request">
							<logic:notEmpty name="subject_list" scope="request">
								<table id="allstudent" class="table">

									<tr class="headth">

										<td align="center" style="color: #767685;">S.No</td>
										<td align="center" style="color: #767685;">Subject Name</td>
										<td align="center" style="color: #767685;">Examination
											Date</td>
										<td align="center" style="color: #767685;">Start Time</td>
										<td align="center" style="color: #767685;">End Time</td>

									</tr>


									<logic:iterate id="subject" name="subject_list" indexId="index">



										<tr>
											<td align="center" valign="middle" style="color: #767685;"><%=index.intValue() + 1%></td>

											<td><input type="text" id="subName" class="subjectName"
												readonly="readonly"
												value="<bean:write name="subject" property="subName" />"
												style="color: #767685; text-align: center" /></td>



											<td align="center" valign="middle"><input type="text"
												class='datepicker' name="startDate"
												style="color: #767685; text-align: center"
												style="width: 60%; margin-left: 20%;" readonly="readonly"
												value="<bean:write name="subject"  property="examDate" />"
												placeholder="Click Here" /></td>


											<td align="left" valign="middle"><div
													class="input-append">
													<input type="text" readonly="readonly" name="starttime"
														id="startTime" data-format="hh:mm:ss"
														style="margin-left: 20%; width: 50%; color: #767685;"
														value="<bean:write name="subject" property="examStartTime" />"
														class="timepicker" /><span class="add-on"
														style="margin-top: 3px;"><img
														src="images/time2.png" width="25" height="12"
														style="width: 10%; height: 10%; margin-left: -11%;"></span>
												</div></td>

											<td align="left" valign="middle"><div
													class="input-append">
													<input type="text" readonly="readonly" name="endtime"
														id="endTime" data-format="hh:mm:ss"
														style="margin-left: 20%; width: 50%; color: #767685;"
														value="<bean:write name="subject" property="examEndTime" />"
														class="endtimepicker" /><span class="add-on"
														style="margin-top: 3px;"><img
														src="images/time2.png" width="25" height="12"
														style="width: 10%; height: 10%; margin-left: -11%;"></span>
												</div></td>



											<td align="center" valign="middle" class="subjectid"><bean:write
													name="subject" property="subId" /></td>

											<td align="center" valign="middle" class="classid"><bean:write
													name="subject" property="classId" /></td>

											<%-- <td><input type="hidden" name="hiddenclassId"
												id="hiddenclassId"
												value="<bean:write name="subject" property="classId"/>" /></td> --%>
										</tr>
									</logic:iterate>
								</table>
								<br />

							</logic:notEmpty>
							<logic:empty name="subject_list" scope="request">
								<span
									style="margin-left: 35%; font-family: segoe Ui; font-size: 12px;">Nothing
									found to display</span>
							</logic:empty>
						</logic:present>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>





