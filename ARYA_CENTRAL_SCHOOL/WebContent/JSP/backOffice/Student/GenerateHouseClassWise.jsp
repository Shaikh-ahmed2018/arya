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
<script type="text/javascript" src="JS/backOffice/Student/HouseSettingClassWise.js"></script>
<style type="text/css">


.Not{
background-color:#FF0000;
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
.Set{
background-color:rgba(0, 158, 0, 0.66);
min-width:80px;
display:inline-block;
text-align:center;
 color:#fff;
}
#allstudent{
width: 100%;
}
</style>

</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><img src="images/addstu.png"/><span id="pageHeading">Generate House</span></p>
				<div class="panel-body clearfix" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				</div>
			<div class="errormessagediv" align="center" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>
			
					
						<div class="successmessagediv" align="center"  style="display: none;">
								<div class="message-item">
									<!-- Warning -->
									<a href="#" class="msg-success bg-msg-succes"><span class="successmessage"></span></a>
								</div>
						</div>
			
						
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a  href="#" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title class"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Generate House</h4></a>
						
						
						<div class="navbar-right">
						<span id="back" class="buttons" style="line-height: 1.9;">Back</a></span>
						</div>
						
					</div>

					<div id="classOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel" style="margin-top : 10px;">
							
							<div class="col-md-12" id="inputcolor">
				<div class="col-md-6" id="txtstyle" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<input type="text" name="locName" id="locName"class="form-control" readonly="readonly"
											value='<logic:present name="locName"><bean:write name="locName"/></logic:present>' />
											<input type="hidden" id="hiddenaccyear" value='<logic:present name="accid"><bean:write name="accid"/></logic:present>'/>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text"   id="accyName" class="form-control" readonly="readonly"
											value='<logic:present name="accyName"><bean:write name="accyName"/></logic:present>' />
										<input type="hidden" id = "hiddenlocid" value='<logic:present name="locid"><bean:write name="locid"/></logic:present>'/>
									</div>
								</div>
								
								<div class="form-group clearfix filters" style="display: none;">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Filter</label>
									<div class="col-xs-7">
										<%-- <input type="text" id="nameWise" class="form-control" readonly="readonly"
											value='<logic:present name="filter"><bean:write name="filter"/></logic:present>' /> --%>
											
											<input type="text" id="filter" class="form-control" readonly="readonly"
											value=""/>
											
									
									</div>
								</div>
							  </div>
								<input type="hidden" id="filter2" class="form-control" readonly="readonly" value='<logic:present name="filter1"><bean:write name="filter1"/></logic:present>' />
					
								<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">No. Of Students</label>
									<div class="col-xs-7">
										<input type="text" name="noofstudents" id="noofstudents" class="form-control" readonly="readonly"
											value='<logic:present name="noofstudents"><bean:write name="noofstudents" /></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Allocated Students</label>
									<div class="col-xs-7">
										<input type="text" name="allocatedstudents" id="allocatedstudents" class="form-control" readonly="readonly"
											value='<logic:present name="allocatedstudents"><bean:write name="allocatedstudents"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix selections" style="display: none;">
								<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Filter</label>
								<div class="col-xs-7 add">
									<select class="form-control" onkeypress="HideError()" tabindex="2"name="selection" id="selection" >
										<option value="">----------Select----------</option>
										<option value="namewise">Name Wise</option>
										<option value="namewisedesc">Name Wise Desc</option>
										<option value="admissionwise">By Admission No.</option>
										<option value="byadminodesc">By Admission No. Desc</option>
										<option value="byadminoeven">By Admission No. Even</option>
										<option value="byadminoodd">By Admission No. Odd</option>
										<!-- <option value="admisnamewise">By Admission No Name Wise</option>
										<option value="admisnamewisedesc">By Admission No Name Wise Desc</option> -->
									</select>
								</div>
							</div>
							</div>
				</div>	
							
							
					<table class='table studenttable' id='allstudent' width='100%'>
						<tr>
							<th>Sl.No.</th>
							<th style="text-align:center">Class</th>
							<th style="text-align:center">Strength</th>
							<th style="text-align:center">Allocated</th>
							<th style="text-align:center">Status</th>
						</tr>
						<logic:present name="classwisehouse" scope="request">
					   	<logic:iterate id="classwisehouse" name="classwisehouse" scope="request">
					   		<tr >
					   		<td class="genehouid" id="<bean:write name="classwisehouse" property="genhouid"/>"><bean:write name="classwisehouse" property="slno" /></td>
					   		<td class="classId" id="<bean:write name="classwisehouse" property="classid"/>"><bean:write name="classwisehouse" property="classname"/></td>
					   		<td class="total"><bean:write name="classwisehouse" property="totalclassStrength"/></td>
					   		<td class="allocated"><bean:write name="classwisehouse" property="allocatedstudents"/></td>
							<td><span class="<bean:write name="classwisehouse" property="statusid"/> status"><bean:write name="classwisehouse" property="status"/></span></td>
					   		</tr>
					   	</logic:iterate>
					   </logic:present>
						</table>
						<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
							<span  class='numberOfItem'></span>	
							</div><div class='pagination pagelinks'></div>
						</div>
					</div>
				</div>
			</div>
	</div>
</html>
