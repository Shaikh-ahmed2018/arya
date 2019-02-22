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
	src="JS/backOffice/Exam/Examtimetableclass.js"></script>
<style>
/* #allstudent tr td {
	color: black;
	text-align: center;
} */

#allstudent {
	width: 100%;
}
#allstudent tr td{
	text-align: left;
}
#allstudent td:nth-child(1){
	text-align: center;
}
#allstudent th:nth-child(2){
	width:350px;
}

#accyear {
	width: 190px;
	text-align: center;
}
.Set{
    background-color: rgba(0, 158, 0, 0.66);
    min-width: 80px;
    display: inline-block;
    text-align: center;
    color: #fff !important;
}
.Not{
	background-color: #FF0000;
    color: #fff !important;
    min-width: 80px;
    display: inline-block;
    text-align: center;
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

		<input type="hidden" id="hiddenaccyear" value='<logic:present name="accyearid"  scope="request"><bean:write name="accyearid" /></logic:present>' />
		<input type="hidden" id="hiddenloc" value='<logic:present name="locid" scope="request"><bean:write name="locid"  /></logic:present>' />
         
       
		
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a href="#" style="color: #767676"><h4 class="panel-title class"><i class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Exam Details</h4></a>
		 			
					 <div class="navbar-right">
			<span class="buttons" id="back" style="top:7.5px;">Back</span>
		</div>
				</div>
				
				<div id="classOne" class="panel-collapse collapse in"
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
							onkeypress="HideError()" id="location" class="form-control"
							style="text-align: left;"
							value='<logic:present name="location" scope="request" ><bean:write name="location" scope="request"></bean:write></logic:present>' />

					</div>
				</div>
			</div>
			<div class="col-md-12" id="inputcolor"></div>
			<div class="col-md-6" id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Academic Year</label>
					<div class="col-xs-5">
						<input type="text" readonly="readonly" name="accyear" onkeypress="HideError()" id="accyear" class="form-control" style="text-align: left;"
							value='<logic:present name="accyName" scope="request" ><bean:write name="accyName" scope="request"></bean:write></logic:present>' />
					</div>
				</div>
			</div>
			<div class="col-md-6" id="txtstyle" style="font-size: 11pt; color: #5d5d5d;">
				<div class="form-group clearfix">
					<label for="inputEmail" class="control-label col-xs-4" style="text-align: right; line-height: 35px;">Exam</label>
					<div class="col-xs-5">
						<select class="form-control" onkeypress="HideError()" tabindex="1"name="exam" id="exam" >
							<!-- <option value="">----------Select----------</option> -->
							<!-- <option value="%%">All</option> -->
						</select>
					</div>
				</div>
			</div>
		</div>
		<div id="mytable"></div>
						<%-- <display:table name="requestScope.ClassList"
						export="false" class="table" id="allstudent" decorator="com.centris.campus.decorator.ExamAccYearDecorator">

						<display:column property="sno1" sortable="true"  title="Sl No <img src='images/sort1.png' style='float: right'/>" />
						
						<display:column property="classname" sortable="true" class="${allstudent.classId} classid" title="Class <img src='images/sort1.png' style='float: right'/>" />

						<display:column property="status" sortable="true" title="Status <img src='images/sort1.png' style='float: right'/>" />
						
						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>

					</display:table> --%>
						
					</div>
					<br />
				</div>
			</div>
		</div>
	</div>
</body>

</html>
