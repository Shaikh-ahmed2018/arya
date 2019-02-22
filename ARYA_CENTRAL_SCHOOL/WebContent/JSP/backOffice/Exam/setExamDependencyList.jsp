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
<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
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
<link href="CSS/Exam/Examsettings.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Exam/AddDependency.js"></script>
<style>
#allstudent tr td {
color: black;
text-align: center;

}
#allstudent {
	width : 100%;
}
#allstudent tr th:nth-child(1) {
	width : 150px;
}
#allstudent tr th{
	text-align: center;
}

</style>
</head>
<body>

		<div class="errormessagediv" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
	<div id="dialog" style="display: none;">		
		<div id="dialog2">
		<input type="hidden" id="examcode1"/>
		<input type="hidden" id="examname1"/>
		<input type="hidden" id="examId">
			<div class="col-md-12" id="txtstyle"
				style="font-size: 11pt; color: #5d5d5d;">
				
				<table style="width: 100%;" id="dependency">
							<tr>
								<td ><label for="inputEmail"style="text-align: center; line-height: 35px; width: 88px">Exam Code</label></td>
								<td ><select id="code0" style="width: 150px;"></select></td>
								<td ><input type="text" style="width: 50px;" id="perse0"/></td>
								<td >%</td>
								<td ><span style="margin-top: 2px;margin-right: -10px;"
									id="addgroup" onclick="addMoreRow()"
									class="glyphicon glyphicon-plus"
									></span></td>
							</tr>

						</table>
				
			</div>
		</div>
	</div>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Exam Dependency
				Setting</span>
		</p>

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>

        
		<input type="hidden" id="hiddenstartaccyear" value='<logic:present name="startDate"  scope="request"><bean:write name="startDate" /></logic:present>' />
		<input type="hidden" id="hiddenendaccyear" value='<logic:present name="endDate" scope="request"><bean:write name="endDate"  /></logic:present>' />		

		<input type="hidden" id="hiddenstartaccyear" value='<logic:present name="startDate"  scope="request"><bean:write name="startDate" /></logic:present>' />
		<input type="hidden" id="hiddenendaccyear" value='<logic:present name="endDate" scope="request"><bean:write name="endDate"  /></logic:present>' />		
	
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-primary">
				<div class="panel-heading clearfix" role="tab" id="headingOne">
					
						<a href="#" style="color: #767676"><h4 class="panel-title examdetails"><i
							class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Exam Dependency
							Details
						</h4></a>
					

					<div class="navbar-right">
						<span class="buttons" id="back">Back</span>
					</div>
				</div>
				<input type="hidden" id="hiddenaccyear"
					value='<logic:present name="accyear"  scope="request"><bean:write name="accyear" /></logic:present>' />
				<input type="hidden" id="hiddenloc"
					value='<logic:present name="locid"  scope="request"><bean:write name="locid" /></logic:present>' />
				
				<div id="exmdetail" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
					<div id="inputcolor">
						<div class="col-md-6" id="txtstyle"
							style="font-size: 11pt; color: #5d5d5d;">
							<div class="form-group clearfix" >
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">Academic
									Year</label>
								<div class="col-xs-5">
									<input type="text" readonly="readonly" name="accyear" style="text-align: left;"
										id="accyear" class="form-control"
										value='<logic:present name="accyName" scope="request"><bean:write name="accyName" scope="request"></bean:write></logic:present>' />

								</div>
							</div>
							<div class="form-group clearfix" >
								<label for="inputEmail" class="control-label col-xs-4"
									style="text-align: right; line-height: 35px;">School Name</label>
									
								<div class="col-xs-5">
									<input type="text" readonly="readonly" name="locname" style="text-align: left;"
									 id="locname" class="form-control"
										value='<logic:present name="locName" scope="request"><bean:write name="locName" scope="request"></bean:write></logic:present>' />
								</div>
							</div>
					</div>
					</div>
							<div class="form-group">
								<div id="markstable"></div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
</body>

</html>
