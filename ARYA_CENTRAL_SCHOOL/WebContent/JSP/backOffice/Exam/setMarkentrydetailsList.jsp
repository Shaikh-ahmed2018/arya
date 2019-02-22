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
<link href="CSS/Exam/MarkEntryStudentList.css" rel="stylesheet"
	type="text/css">
<link href="CSS/Exam/Examsettings.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript"
	src="JS/backOffice/Exam/setmarkentryexamdetails.js"></script>
<style>
#allstudent tr td {
	color: black;
	text-align: center;
}

#allstudent {
	width: 100%;
}

#accyear {
	width: 190px;
	text-align: center;
}
</style>
</head>
<body>

	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Mark
				Entry-Student Wise </span>
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

		<input type="hidden" id="hiddenstartaccyear"
			value='<logic:present name="startDate"  scope="request"><bean:write name="startDate" /></logic:present>' />
		<input type="hidden" id="hiddenendaccyear"
			value='<logic:present name="endDate" scope="request"><bean:write name="endDate"  /></logic:present>' />
                        
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a href="#" style="color: #767676"><h4 class="panel-title examdetails"><i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Exam
							Details
						</h4></a>
					
				<div class="navbar-right">
	 					<span class="buttons" id="back">Back</span>
					</div>
					
				</div>
				<input type="hidden" id="hiddenaccyear"
					value='<logic:present name="accyear"  scope="request"><bean:write name="accyear" /></logic:present>' />
				<input type="hidden" id="hiddenlocation"
					value='<logic:present name="locationid"  scope="request"><bean:write name="locationid" /></logic:present>' />
				
				<div id="exmdetail" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">

					<div class="clearfix" id="inputcolor">
			<div class="col-md-6" id="txtstyle"
				style="font-size: 11pt; color: #5d5d5d;">
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">School Name</label>
					<div class="col-xs-5">
						<input type="text" readonly="readonly" name="accyear"
							onkeypress="HideError()" id="accyear" class="form-control"
							style="text-align: left;"
							value='<logic:present name="currentlocation" scope="request" ><bean:write name="currentlocation" scope="request"></bean:write></logic:present>' />

					</div>
				</div>
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Academic
						Year</label>
					<div class="col-xs-5">
						<input type="text" readonly="readonly" name="accyear"
							onkeypress="HideError()" id="accyear" class="form-control"
							style="text-align: left;"
							value='<logic:present name="accyName" scope="request" ><bean:write name="accyName" scope="request"></bean:write></logic:present>' />

					</div>
				</div>

			</div>

		</div>

						<div class="form-group">

							<table style="background: #fff;" class="table" id="allstudent">
							<thead>
								<tr>
									<th style="font-size: 13px; text-align: center; width: 95px; padding: 9px;">Class Name</th>
									<th style="font-size: 13px; text-align: center; width: 95px; padding: 9px;">Exam Code</th>
									<th style="font-size: 13px; text-align: center; padding: 9px;">Exam Name</th>
									<th style="font-size: 13px; text-align: center; padding: 9px;">Start Date</th>
									<th style="font-size: 13px; text-align: center; padding: 9px;">End Date</th>
									<th align="center" style="text-align: center; padding: 9px;">Status</th>
								</tr>
							</thead>
								<tbody>
									<logic:present name="examsettings" scope="request">
										<logic:iterate id="examsettings" name="examsettings"
											scope="request">
											<tr>
												<td class="classid" id="<bean:write name="examsettings" property="classid"/>">
													<bean:write name="examsettings" property="classname" /></td>
												<td class="examCode" id="<bean:write name="examsettings" property="examid"/>">
													<bean:write name="examsettings" property="examCode" /></td>
												<td class="examname"><bean:write name="examsettings" property="examName" /></td>
												<td class="startdate"><bean:write name="examsettings" property="startDate" /></td>
												<td class="enddate"><bean:write name="examsettings" property="endDate" /></td>
												<td><span class="<bean:write name="examsettings" property="status"/>">
													<bean:write name="examsettings" property="status" /></span></td>
											</tr>
										</logic:iterate>
									</logic:present>
								</tbody>
							</table>
							<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
								<span  class='numberOfItem'></span>	
								</div>
							<div class='pagination pagelinks' style="top:-15px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>

</html>
