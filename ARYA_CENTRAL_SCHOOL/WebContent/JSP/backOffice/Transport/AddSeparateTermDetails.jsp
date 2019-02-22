<!DOCTYPE html>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/backOffice/Transport/AddSeparateTermDetails.js"></script>

<style>
.glyphicon:hover {
	cursor: pointer;
}

#save:hover {
	cursor: pointer;
}
</style>

<style>
.buttons{

vertical-align: 0px;
    margin-left: 2px;


}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 15pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p>
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Add Transport Term Details</span>
		</p>

	

		<div class="successmessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-success bg-msg-succes"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<logic:present name="errormessagediv" scope="request">
			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"><bean:write name="errormessagediv"
								scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<div class="errormessagediv1"
			style="display: none; text-align: center;">
			<div class="message-item1">
				<!-- Warning -->
				<a href="#" class="msg-warning1 bg-msg-warning1"
					style="width: 35%; font-size: 13px; color: red;"><span
					class="validateTips1"></span></a>
			</div>
		</div>




		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676;vertical-align: text;"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i> &nbsp;&nbsp;Term
								Details
							</h4></a>
						

						<div class="navbar-right">
							<span id="save" class="buttons" style="margin-right: -2px;">Save</span>
							<span id="back" class="buttons">Back</span>
						</div>
				</div>
					 
					
					
					<input type="hidden" id="enddates" value="<logic:present name="enddate"><bean:write name="enddate" /></logic:present>" />
					<input type="hidden" id="lastDate" value="<logic:present name="statuss"><bean:write name="statuss" /></logic:present>" />
					<input type="hidden" id="termmasterid"
						value='<logic:present name="editlist"><bean:write name="editlist" property="termid" /></logic:present>'></input>
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">School Name <span style="color: red;">*</span></label>
									<div class="col-xs-7">
									<select id="locationname" name="locationId" class="form-control">
										<option value="">-------------Select-----------</option>
										
										<logic:present name="locationList">
										<logic:iterate id="Location" name="locationList">
											<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
										</logic:iterate>
										</logic:present>
						
									</select>
									</div>
								<input type="hidden" name="schoolId" class="form-control" id="schoolId" value='<logic:present name="list"><bean:write name="list" property="locationId" /></logic:present>'></input>
							</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Term
										Name<span style="color: #F00;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="termname"
											onkeypress="HideError()" placeholder=""
											value='<logic:present name="editlist"><bean:write name="editlist" property="termname" /></logic:present>'></input>
									</div>
								</div>
								

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Description</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="termdescription"
											placeholder=""
											value='<logic:present name="editlist"><bean:write name="editlist" property="description" /></logic:present>'></input>
									</div>
								</div>
								

								<div class="col-xs-7">
									<input type="hidden" class="form-control" id="AccStartDate"
										value='<logic:present name="acclist"><bean:write name="acclist" property="acastartdate"/></logic:present>'></input>
								</div>
								<div class="col-xs-7">
									<input type="hidden" class="form-control" id="AccEndDate"
										value='<logic:present name="acclist"><bean:write name="acclist" property="acaenddate"/></logic:present>'></input>
								</div>

								
							</div>

							<div class="col-md-6"
								style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
										
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Start
										Date<span style="color: #F00;">*</span>
									</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											id="startdate" placeholder="" onkeypress="HideError()"
											value='<logic:present name="editlist"><bean:write name="editlist" property="startdate" /></logic:present>'></input>
									</div>
								</div>

								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">End Date<span
										style="color: #F00;">*</span></label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" class="form-control"
											id="enddate" placeholder="" onkeypress="HideError()"
											value='<logic:present name="editlist"><bean:write name="editlist" property="enddate" /></logic:present>'></input>
									</div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
		</form>
	</div>

</body>

</html>
