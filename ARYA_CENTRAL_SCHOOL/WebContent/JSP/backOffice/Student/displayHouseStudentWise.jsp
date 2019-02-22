<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/newUI/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery-ui.custom.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="CSS/Admin/StudentNew.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Student/displayHouseSettingClassWise.js"></script>
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
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.menu.js"></script>
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="JQUERY/js/bootstrap-datetimepicker.min.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<style type="text/css">

.table{
width:100%;
}

</style>
</head>

<body>
<div id="dialog" style="display:none;"><p>Re-Generation of House will Over-write Existing House Name.</p><p>Are you sure want to Re-Generate?</p></div>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
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
		<p><img src="images/addstu.png"/><span id="pageHeading">Generate House</span></p>
				<div class="panel-body clearfix" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				</div>
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a href="#" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title class"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Generate House</h4></a>
						
						
					 	<div class="navbar-right">
							<span class="buttons" id="house" style="top:4px;">Re-Generate</span>
							<span class="buttons" id="back" style="top:4px;">Back</span>
							<!-- <span class="buttons" id="save">Save</span> -->
						</div>
						
					</div>
				<input type="hidden" id ="hiddengen" value='<logic:present name="genhouid"><bean:write name="genhouid"/></logic:present>'/>
					<div id="classOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel" id="inputcolor">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<input type="text" name="location" id="locName" class="form-control" readonly="readonly"
											value='<logic:present name="locName"><bean:write name="locName"/></logic:present>'/>
										<input type="hidden" id = "hiddenlocid" value='<logic:present name="locid"><bean:write name="locid"/></logic:present>'/>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="accyName"  id="accyName" class="form-control" readonly="readonly"
											value='<logic:present name="accyName"><bean:write name="accyName"/></logic:present>' />
										<input type="hidden" id="hiddenaccyear" value='<logic:present name="accid"><bean:write name="accid"/></logic:present>'/>	
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" name="classname" id="classname" class="form-control" readonly="readonly"
											value='<logic:present name="classname"><bean:write name="classname" /></logic:present>'/>
										<input type="hidden" id="hiddenclassname" value='<logic:present name="classid"><bean:write name="classid"/></logic:present>'/>
									</div>
								</div>
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">No. Of Students</label>
									<div class="col-xs-7">
										<input type="text" name="noofstudents" id="noofstudents" class="form-control" readonly="readonly"
											value='<logic:present name="totalstu"><bean:write name="totalstu"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Allocated Students</label>
									<div class="col-xs-7">
										<input type="text" name="allostu" id="allostu" class="form-control" readonly="readonly" value='<logic:present name="allostu"><bean:write name="allostu"/></logic:present>' />
									</div>
								</div>
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Filter</label>
									<div class="col-xs-7">
										<input type="text" name="filter" id="filter" class="form-control" readonly="readonly" value='<logic:present name="filter"><bean:write name="filter"/></logic:present>' />
									</div>
								</div>
							</div>
							
					<table class='table' id='allstudent'>
						<tr>
							<th>Sl.No.</th>
							<th style="text-align:center">Admission No</th>
							<th style="text-align:center">Student Name</th>
							<th style="text-align:center">Class</th>
							<th style="text-align:center">Section</th>
							<th style="text-align:center">House Name</th>
						</tr>
						<logic:present name="housestudentwise" scope="request">
					   	<logic:iterate id="housestudentwise" name="housestudentwise" scope="request">
					   		<tr>
					   		<td><bean:write name="housestudentwise" property="slno" /></td>
					   		<td><bean:write name="housestudentwise" property="admissionno" /></td>
					   		<td class="stuId" id="<bean:write name="housestudentwise" property="stuid"/>"><bean:write name="housestudentwise" property="stuname" /></td>
					   		<td class="classId" id="<bean:write name="housestudentwise" property="classid"/>"><bean:write name="housestudentwise" property="classname"/></td>
					   		<td class="sectionid" id="<bean:write name="housestudentwise" property="sectionid"/>"><bean:write name="housestudentwise" property="sectionname"/></td>
					   		<td class="housename"><bean:write name="housestudentwise" property="housename"/></td>
					   		
					   		</tr>
					   	</logic:iterate>
					   </logic:present>						
						
						</table>
						</div>
					</div>
				</div>
			</div>
	</div>
</html>
