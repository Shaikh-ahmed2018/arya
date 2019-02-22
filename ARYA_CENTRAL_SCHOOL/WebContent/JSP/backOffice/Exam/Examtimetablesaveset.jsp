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
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="CSS/Exam/MarkEntryStudentList.css" rel="stylesheet"
	type="text/css">
<link href="CSS/Exam/Examsettings.css" rel="stylesheet" type="text/css">
 <link href="JQUERY/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JQUERY/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="JQUERY/bootstrap/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<script type="text/javascript" src="JS/backOffice/Exam/ExamtimatableSaveset.js"></script>	
<style>
.glyphicon {
top:0;
}
#allstudent {
	width: 100%;
}
#allstudent td:nth-child(2){
	text-align: left;
}
#allstudent td:nth-child(1){
	text-align: center;
}
#allstudent th:nth-child(3){
width:250px;
}

#allstudent tr:nth-child(1){
	text-align: center !important;
}
#allstudent th:nth-child(3),td:nth-child(3){
	text-align: left !important;
}

#allstudent th:nth-child(4),th:nth-child(5),th:nth-child(6){
	text-align: center !important;
	width:200px;
}
#accyear {
	width: 190px;
	text-align: center;
}
.set{
	background-color: rgba(0, 158, 0, 0.66);
    min-width: 80px;
    display: inline-block;
    text-align: center;
    color: #fff;
}
.not{
    background-color: #FF0000;
    min-width: 80px;
    display: inline-block;
    text-align: center;
    color: #fff;
}
#mytable input[type='text']{
background-color: transparent;
}
#mytable span{
background-color: transparent;
}
.ui-corner-all, .ui-corner-bottom, .ui-corner-right, .ui-corner-br{
z-index: 999999999999 !important;
}
</style>
</head>
<body>

	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Exam Time Table </span>
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
	

		<input type="hidden" id="hiddenaccyear" value='<logic:present name="accyear"  scope="request"><bean:write name="accyear" /></logic:present>' />
		<input type="hidden" id="hiddenloc" value='<logic:present name="locid" scope="request"><bean:write name="locid"  /></logic:present>' />
        <input type="hidden" id="hiddenclass" value='<logic:present name="classid" scope="request"><bean:write name="classid"  /></logic:present>' />
        <input type="hidden" id="hiddensection" value='<logic:present name="section" scope="request"><bean:write name="section"  /></logic:present>' />
		<input type="hidden" id="hiddenexamid" value='<logic:present name="examid" scope="request"><bean:write name="examid"  /></logic:present>' />
		<input type="hidden" id="minDate" value="<logic:present name="minDate" scope="request"><bean:write name="minDate"  /></logic:present>" />
		<input type="hidden" id="maxDate" value="<logic:present name="maxDate" scope="request"><bean:write name="maxDate"  /></logic:present>" />
		<input type="hidden" id="hiddentimeid" value="<logic:present name="timetableid" scope="request"><bean:write name="timetableid"  /></logic:present>" />
		
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a href="#" style="color: #767676"><h4 class="panel-title class"><i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Exam Time Table</h4></a>
					
					 <div class="navbar-right">
			<span class="buttons" id="save" style="top: 7px;">Save</span>
	 		<span class="buttons" id="back" style="top: 7px;">Back</span>
		</div>
				</div>
				
				<div id="classOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
					<div class="clearfix">
	
		<div class="col-md-12" id="inputcolor">
			<div class="col-md-6" id="txtstyle"
				style="font-size: 11pt; color: #5d5d5d;">
				
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Academic Year</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear"  id="accyear" class="form-control" style="text-align: left;" value='<logic:present name="accyName" scope="request" ><bean:write name="accyName" scope="request"></bean:write></logic:present>' />
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Class</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="class"  id="class" class="form-control" style="text-align: left;"
							value='<logic:present name="examdetails" scope="request" ><bean:write name="examdetails" scope="request" property="classname"></bean:write></logic:present>' />
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Exam Code</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="examcode" class="form-control" style="text-align: left;"
							value='<logic:present name="examdetails" scope="request" ><bean:write name="examdetails" scope="request" property="examCode"></bean:write></logic:present>' />
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Start Date</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="startdate" class="form-control" style="text-align: left;"
							value='<logic:present name="examdetails" scope="request" ><bean:write name="examdetails" scope="request" property="examstartdate"></bean:write></logic:present>' />
						</div>
					</div>
			</div>
			
			<div class="col-md-6" id="txtstyle"
				style="font-size: 11pt; color: #5d5d5d;">
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">School Name</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear"
							onkeypress="HideError()" id="location" class="form-control"
							style="text-align: left;"
							value='<logic:present name="location" scope="request" ><bean:write name="location" scope="request"></bean:write></logic:present>' />
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Section </label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear"
							onkeypress="HideError()" id="section" class="form-control"
							style="text-align: left;"
							value='<logic:present name="examdetails" scope="request" ><bean:write name="examdetails" scope="request" property="sectionName"></bean:write></logic:present>' />
						
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">Exam Name</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="examname" class="form-control" style="text-align: left;"
							value='<logic:present name="examdetails" scope="request" ><bean:write name="examdetails" scope="request" property="examName"></bean:write></logic:present>' />
						</div>
					</div>
					<div class="form-group clearfix">
						<label for="inputEmail" class="control-label col-xs-4"
						style="text-align: right; line-height: 35px;">End Date</label>
						<div class="col-xs-5">
							<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="enddate" class="form-control" style="text-align: left;"
							value='<logic:present name="examdetails" scope="request" ><bean:write name="examdetails" scope="request" property="examenddate"></bean:write></logic:present>' />
						</div>
					</div>
					<div class="form-group clearfix">
					<div class="col-xs-12" style="text-align:right;">
						 <input type="checkbox" id='allsection' name='allsection' value='Y'/><span style="font-weight: 700;margin-left: 5px;">Apply for All Section</span>
					</div>
						
					</div>
			</div>
		</div>
	</div>				
						<div id="mytable" class='inputcolor'></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
