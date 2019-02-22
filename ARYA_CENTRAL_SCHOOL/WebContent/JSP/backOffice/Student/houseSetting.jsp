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
<script type="text/javascript" src="JS/backOffice/Student/HouseSetting.js"></script>

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

</style>



</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><img src="images/addstu.png"/><span id="pageHeading">House Setting</span></p>
				<div class="panel-body clearfix" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				</div>
				
				<div class="errormessagediv" align="center" style="display: none;">
							<div class="message-item">
								<!-- Warning -->
								<a href="#" class="msg-warning bg-msg-warning"><span
									class="validateTips"></span></a>
							</div>
						</div>
			
					<div class="clearfix" id="inputcolor">
						<div class="col-md-12">
						
						 <div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1"name="accyear" id="accyear" >
												<logic:present name="accYearList" scope="request">
												<logic:iterate id="accYearList" name="accYearList">
												<option value='<bean:write name="accYearList" property="accyearId"/>'><bean:write name="accYearList" property="accyearname"/></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
							
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="3"
											name="location" id="location" >
											<option value="%%">ALL</option>
											<logic:present name="locationDetailsList" scope="request">
											<logic:iterate id="locationDetailsList" name="locationDetailsList">
											<option value='<bean:write name="locationDetailsList" property="location_id"/>'><bean:write name="locationDetailsList" property="locationname"/></option>
											</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
						</div>
				</div>
				</div>
				<input type="hidden" name="hiddenlocationname" id="hiddenlocationname" value='<bean:write name="currentlocation"/>'/>
				<input type="hidden" name="hiddenlocationid" id="hiddenlocationid" value='<bean:write name="locationId"/>'/>
			<input type="hidden" name="academic_year" id="hiddenaccyear" value='<bean:write name="academic_year"/>'/>
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne" style="margin-bottom: 10px;">
						
							<a href="#" style="color: #767676; vertical-align: super;"> 
							<h4 class="panel-title class"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;House Setting</h4></a>
						
					</div>

				<div id="classOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

					<div id="markstable"></div>
					<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
						<span  class='numberOfItem'></span>	
						</div><div class='pagination pagelinks' id="page"></div>

				</div>
				<br />
				
					
					
			</div>
				</div>
			</div>
	</div>
</html>
