<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



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
<script type="text/javascript"
	src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet" />
<link href="CSS/newUI/modern-business.css" rel="stylesheet" />
<link href="CSS/newUI/custome.css" rel="stylesheet" />
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript" src="JS/backOffice/Exam/classsubjectexamtimetable.js"></script>

<style>
.save:hover {
	cursor: pointer;
}


.ui-dialog-buttonset button{
	 padding: 5px;
    font-size: 14px;
    background-color: #07AAB9;
    color: #fff;
    border-radius: 3px;
    cursor: pointer;

}

 #myDialog{
 height : 400px !important;
	overflow-y: scroll;

 } 
 





.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable.ui-dialog-buttons{

width : 600px !important;
height : 500px !important;
}

</style>

<style>
#list:hover {
	cursor: pointer;
}
.add-on{

top:1px;
right: 8px;
}
</style>

<style>
.buttons{

vertical-align: 0px;

}

.button-right{
	position: absolute;
    right: 15px;
    top: 82px;
    float: none !important;
    display: block;
    bottom: 0;
    margin: auto auto;
    vertical-align: middle;
}

.stagebutton{ padding: 5px;
    font-size: 14px;
    background-color: #07AAB9;
    vertical-align: 5px;
    color: #fff;
    border-radius: 3px;
    cursor: pointer;
    font-family: Roboto Medium;
    }
    
.stagetable{
max-height:350px !important;
overflow-y: scroll;

}
</style>


</head>

<body>
<div class="col-md-10 col-md-offset-2" id="div1">
	<div class="searchWrap">
		<p>
			<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Exam Time Table</span>
		</p>
	</div>
	
	<div style="margin-bottom: 10px;vertical-align: top;line-height: 0;">
		<span style="font-size: 18px; color: #000; font-weight: normal;">Academic Year</span>
		<label style="font-size: 18px; color: #767676; font-weight: normal;"><input readonly="readonly" style="font-size: 17px;margin-left: 20px; width: 100px;text-align: left;" value="<logic:present name="accYear"  scope="request"><bean:write name="accYear" /></logic:present>" /></label>
		<div class="button-right">
		<span id="back" class="buttons">Back</a></span>
		</div>
	</div>
	
	<div style="margin-bottom: 10px;vertical-align: top;line-height: 0;">
		<span style="font-size: 18px; color: #000; font-weight: normal;margin-right: 76px;">Class</span>
		<label style="font-size: 18px; color: #767676; font-weight: normal;"><input readonly="readonly" style="font-size: 17px;margin-left: 20px; width: 100px;text-align: left;" value="<logic:present name="className"  scope="request"><bean:write name="className" /></logic:present>" /></label>
		<span style="font-size: 18px; color: #000; font-weight: normal;margin-left: 200px;">Section</span>
		<label style="font-size: 18px; color: #767676; font-weight: normal;"><input readonly="readonly" style="font-size: 17px;margin-left: 70px; width: 100px;text-align: left;" value="<logic:present name="sectionName"  scope="request"><bean:write name="sectionName" /></logic:present>" /></label>
	</div>
	
	<div style="margin-bottom: 10px;vertical-align: top;line-height: 0;">
		<span style="font-size: 18px; color: #000; font-weight: normal;margin-right: 30px;">Exam Code</span>
		<label style="font-size: 18px; color: #767676; font-weight: normal;"><input readonly="readonly" style="font-size: 17px;margin-left: 20px; width: 100px;text-align: left;" value="<logic:present name="examCode"  scope="request"><bean:write name="examCode" /></logic:present>" /></label>
		<span style="font-size: 18px; color: #000; font-weight: normal;margin-left: 200px;">Exam Name</span>
		<label style="font-size: 18px; color: #767676; font-weight: normal;"><input readonly="readonly" style="font-size: 17px;margin-left: 35px; width: 100px;text-align: left;" value="<logic:present name="examName"  scope="request"><bean:write name="examName" /></logic:present>" /></label>
	</div>
	
	<div style="margin-bottom: 10px;vertical-align: top;line-height: 0;">
		<span style="font-size: 18px; color: #000; font-weight: normal;margin-right: 40px;">Start Date</span>
		<label style="font-size: 18px; color: #767676; font-weight: normal;"><input id="startdate" readonly="readonly" style="font-size: 17px;margin-left: 20px; width: 100px;text-align: left;" value="<logic:present name="examStartDate"  scope="request"><bean:write name="examStartDate" /></logic:present>" /></label>
		<span style="font-size: 18px; color: #000; font-weight: normal;margin-left: 200px;">End Date</span>
		<label style="font-size: 18px; color: #767676; font-weight: normal;"><input id="enddate" readonly="readonly" style="font-size: 17px;margin-left: 60px; width: 100px;text-align: left;" value="<logic:present name="examEndDate"  scope="request"><bean:write name="examEndDate" /></logic:present>" /></label>
	</div>
				
	<logic:present name="success" scope="request">

			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span> <bean:write
								name="success" scope="request" />
					</span></a>
				</div>
			</div>
		</logic:present>
		<logic:present name="error" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span> <bean:write
								name="error" scope="request" />
					</span></a>
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
		
		<div class="panel panel-primary">
			<div class="panel-heading clearfix" style="margin-bottom: -1px;">
			<a data-toggle="collapse" data-parent="#accordion2"	href="#collapseOne" style="color: #fff;">
				<h3 class="panel-title" style="color: #767676; line-height: 18px;">
					<span class="glyphicon glyphicon-menu-hamburger"></span>&nbsp;&nbsp;Exam Time Table
				</h3></a>
			</div>		
				<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
					<display:table name="requestScope.classSubjectTimeTableList"
						requestURI="/examTimetablePath.html?method=setClassSubjectExamTimeTableDetails" pagesize="8"
						export="false" class="table" id="allstudent" decorator="com.centris.campus.decorator.ExamAccYearDecorator">

						<display:column property="sno1" title="Sl.No." class="${allstudent.accyearid},${allstudent.classid},${allstudent.sectionid}"></display:column> 

						<display:column property="subjectId" sortable="true" title="Subject Code <img src='images/sort1.png' style='float: right'/>" /> 

						<display:column property="subjectName" sortable="true" title="Subject Name <img src='images/sort1.png' style='float: right'/>" /> 
						
						<display:column sortable="true" title="Exam Date <img src='images/sort1.png' style='float: right'/>" ><input type='text' class='examdate' readonly="readonly"/></display:column>
						
						<display:column sortable="true" title="Start Time <img src='images/sort1.png' style='float: right'/>" ><div class="datetimepicker input-append" ><input type="text" data-format="hh:mm:ss" size="8" readonly="readonly" name="starttime" class="starttime" class="form-control DatePicker" /><span><img src="./images/time1.jpg" width="30" height="30" class="add-on" /></span></div></display:column>
						
						<display:column sortable="true" title="End Time <img src='images/sort1.png' style='float: right'/>" ><div class="datetimepicker1 input-append" ><input type="text" data-format="hh:mm:ss" size="8" readonly="readonly" name="starttime" class="endtime" class="form-control DatePicker" /><span><img src="./images/time1.jpg" width="30" height="30" class="add-on" /></span></div></display:column>
				
						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>

					</display:table>
					
				</div>
			</div>

			</div>
		</div>
	
</body>

</html>
