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
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/timepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>

<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/backOffice/Admin/TimeTable.js"></script>

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
.aligntext{
text-align: center;
}

#allstudent th {
    color: #767676 !important;
    text-align: center;
}

#allstudent td {
    color: #767676 !important;
}
.wrapper{
border: 1px solid #CCC;
padding: 2px;
border-radius:3px;
}
</style>

<style>
.buttons{

vertical-align: 0px;

}

.ui-dialog .ui-dialog-buttonpane {
    text-align: left;
    border-width: 1px 0 0 0;
    background-image: none;
    margin: 0.5em 0 0 0;
    padding: 1.3em 5em .5em .4em;
}

</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<!-- <p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Syllabus Details</span>
		</p> -->
		<div id="dialog"></div>
		<div class="searchWrap">
			<div class="col-md-5" id="div2">
				<p>
					<img src="images/addstu.png" />&nbsp;<span style="font-size: 20px;"><logic:present name="title"><bean:write name="title"></bean:write></logic:present></span>
				</p>
			</div>

			<div class="input-group col-md-7">
				<label class="hedderstyle form-control"
					style="text-align:right;width: 26% !important; border: none; font-family: Roboto Medium; font-size: 14px; font-weight: lighter; margin-left: 0% !important;background-color:transparent">Teacher Name</label>

				<input type="text" name="emppfno" readonly="readonly"
					style="width: 30%;" id="teacherid" maxlength="25"
					class="form-control"
					value="<logic:present name="selected_teacher"><bean:write name="selected_teacher"/></logic:present>" />
					
				<label class="hedderstyle form-control"
					style="text-align:right;width: 11% !important; border: none; font-family: Roboto Medium; font-size: 14px; font-weight: lighter; margin-left: 0% !important;background-color:transparent">Class</label>

				<input type="text" name="emppfno" readonly="readonly"
					style="width: 11%;" id="classid" maxlength="25"
					class="form-control"
					value="<logic:present name="selected_classid"><bean:write name="selected_classid"/></logic:present>" />

				<label
					style="width: 12% !important; border: none; font-family: Roboto Medium; font-size: 14px; font-weight: lighter;background-color:transparent"
					class="form-control">Section</label> 
					
				<input type="text"
					name="section" style="width: 10%;" id="section" maxlength="25"
					class="form-control" readonly="readonly"
					value="<logic:present name="selected_sectionid"><bean:write name="selected_sectionid"/></logic:present>" />
					<input type="hidden" id="locationId" value="<logic:present name="locationId"><bean:write name="locationId"/></logic:present>" />
				<input type="hidden" id="hiddenClass"
					value="<logic:present name="selected_classid"><bean:write name="selected_classid"/></logic:present>" />
				
				<input type="hidden" id="hiddenClassId"
					value="<logic:present name="classId"><bean:write name="classId"/></logic:present>" />
					
				<input type="hidden" id="hiddenSection"
					value="<logic:present name="selected_sectionid"><bean:write name="selected_sectionid"/></logic:present>" />
				<input type="hidden" id="hviewBy" value="class" />
			</div>
		</div>


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
				<a href="#" class="msg-success bg-msg-succes"><span
					class="successmessage"></span></a>
			</div>
		</div>

		<!-- <form method="post"> -->
		<div class="col-md-12" style="padding: 0;">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp; 
						Time Table Details</h4></a>
					
					

					<div class="navbar-right">

						<span id="UpdateTimeTable" class="buttons" style="display: disable;" >Save</span> 
						<span id="back" class="buttons">Back</span>
					</div>

				</div>
				<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					<div id="scrolid" style="padding: 0; overflow: scroll;height: 400px;">
						<logic:present name="timeTableDetails" scope="request">
							<input type="hidden" value="<bean:write name="TimetableID"/>" id="hiddenId" />
							<display:table id="allstudent" name="timeTableDetails" class="table"
								requestURI="/TimeTableActionPath.html?parameter=getTimeTableDetails"
								decorator="com.centris.campus.decorator.TimeTableDecorator"	export="false">

								<display:column property="dayid" title="Day" class="hidden"	headerClass="hidden" />

								<display:column property="dayname" title="Day" />
								<display:column property="period1" title="Period 1" class="aligntext"/>
								<display:column property="period2" title="Period 2" class="aligntext"/>
								<display:column property="period3" title="Period 3" class="aligntext"/>
								<display:column >SHORT BREAK</display:column>
								<display:column property="period4" title="Period 4" class="aligntext"/>
								<display:column property="period5" title="Period 5" class="aligntext"/>
								<display:column >LUNCH BREAK</display:column>
								<display:column property="period6" title="Period 6" class="aligntext"/>
								<display:column property="period7" title="Period 7" class="aligntext"/>
								<display:column >SHORT BREAK</display:column>
								<display:column property="period8" title="Period 8" class="aligntext"/>
								<display:column property="period9" title="Period 9" class="aligntext"/>

							</display:table>

						</logic:present>

					</div>


				</div>
			</div>
			<br />
		</div>
	</div>


	<!-- </form>  -->
	
</body>

</html>
