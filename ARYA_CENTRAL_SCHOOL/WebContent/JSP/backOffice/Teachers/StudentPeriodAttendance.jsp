<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript"src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>

<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/backOffice/Teacher/AttendaceUpload.js"></script>

<style >

.subject{

width: 150px !important;
height: 30px !important;

}

.days{

	background: #f9f9f9 !important;
    border-bottom: 1px solid #ddd;
    border-top: 1px solid #ddd;
    font-family: Roboto Medium !important;
    font-size: 11pt !important;
    color: #767676 !important;


}

#allstudent th {
    color: #767676 !important;
}

#allstudent td {
    color: #767676 !important;
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
		<!-- <p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Syllabus Details</span>
		</p> -->

		

			<p style="margin: 16px 0px;">
				<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Period Attendance</span>
			</p>
			
	
		<center>
			
						<div class="errormessagediv" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>
				
			
					
						<div class="successmessagediv" style="display: none;">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>
	
		</center>
		<!-- <form method="post"> -->
		<div class="col-md-12" style="padding:0;">
		<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" style="color: #767676"><h4 class="panel-title"> <i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;
						</h4></a>
					
						<div class="navbar-right" >

							<span  id="UpdatePeriodAtt" class="buttons">Save</span>

							<span id="back" class="buttons">Back</span>
						</div>
					
		</div><br/>
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					
					<input type="hidden" id="hstudentId" value="<logic:present name="attendancepojo"><bean:write name="attendancepojo" property="studentid"/></logic:present>"/>
					<input type="hidden" id="hclassId" value="<logic:present name="attendancepojo"><bean:write name="attendancepojo" property="classname"/></logic:present>"/>
					<input type="hidden" id="hsectionId" value="<logic:present name="attendancepojo"><bean:write name="attendancepojo" property="sectionname"/></logic:present>"/>

				<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Class
										 </label>
									<div class="col-xs-7">
										<input type="text" name="studentFirstName" readonly="readonly" id="studentFirstNameId" maxlength="25" class="form-control"
											value='<logic:present name="attendancepojo"><bean:write name="attendancepojo" property="classId"/></logic:present>' />
									</div>
								</div>
								
								<br/>
								
									<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" name="studentFirstName" readonly="readonly" id="studentFirstNameId"
										maxlength="25" class="form-control" value='<logic:present name="attendancepojo"><bean:write name="attendancepojo" property="studentname"/></logic:present>' />
									</div>
								</div>
								
								
						</div>
						<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Section
										 </label>
									<div class="col-xs-7">
										<input type="text" name="studentFirstName" readonly="readonly" id="studentFirstNameId"
										maxlength="25" class="form-control" value='<logic:present name="attendancepojo"><bean:write name="attendancepojo" property="sectinId"/></logic:present>' />
									</div>
								</div>
								
								<br/>
								
									<div class="form-group">
									<label for="inputEmail" class="control-label col-xs-4"
										style="text-align: right; line-height: 35px;">Attendance Date
										 </label>
									<div class="col-xs-7">
										<input type="text" name="attendancedate" id="attendancedate" readonly="readonly"
										class="form-control" value='<logic:present name="attendancepojo"><bean:write name="attendancepojo" property="date"/></logic:present>' />
								
									</div>
								</div>
								
								
						</div>
						
					</div><br/>
					
	
					<div  id="scrolid"
						style="padding: 0; overflow: scroll;">
						
						
						<logic:present name="stuAttVo" scope="request">
						
						<display:table id="allstudent" name="stuAttVo" class="table"
							requestURI="/timeTable.html?parameter=getTimeTableDetails"
							decorator="com.centris.campus.decorator.StudentAttendanceDecorator"
							export="false">

							<display:column property="period1" title="Period 1"/>
							<display:column property="period2" title="Period 2" />
							<display:column property="period3" title="Period 3" />
							<display:column property="period4" title="Period 4" />
							<display:column property="period5" title="Period 5" />
							<display:column property="period6" title="Period 6" />
							<display:column property="period7" title="Period 7" />
							<display:column property="period8" title="Period 8" />
							<display:column property="period9" title="Period 9" />
						</display:table>
						
					</logic:present>
						
				</div>
					
					
				</div>
			</div><br/>
			</div>
			</div>
		

		<!-- </form>  -->
	
</body>

</html>
