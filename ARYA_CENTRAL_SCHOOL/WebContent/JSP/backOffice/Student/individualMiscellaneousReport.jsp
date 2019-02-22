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
<script type="text/javascript" src="JQUERY/development-bundle/ui/jquery.ui.tooltip.js"></script>
<script type="text/javascript" src="JS/newUI/bootstrap.min.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">
<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="CSS/Admin/StudentNew.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Student/individualmisreport.js"></script>

<style>
#allstudent th:nth-child(1) {
    text-align: center;
    width: 70px;
}
</style>

</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">MIS Report</span></p>
				<div class="panel-body clearfix"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				</div>
	
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;MIS Report
							</h4></a>
						

						<div class="navbar-right">
							<span id="back" class="buttons" style="line-height: 1.95;">Back</span>
						</div>
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
							
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
								
								
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
							
							
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" 
										style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission	No</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" name="" tabindex="4" onkeypress="HideError()" id=""
											onchange="" maxlength="25"
											value='<logic:present name=""><bean:write name="" property=""></bean:write></logic:present>' />
									</div>
								</div>
								 
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Roll No</label>

									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Section</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputEmail" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px; margin-top: 40px;">Address
									</label>
									<div class="col-xs-7">
										<textarea name="" id="" onkeypress="HideError()" 
											class="form-control" style="height: 70px;width: 271px"></textarea>
									</div>
								</div>
								
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 172px; height: 172px;">
												<img id="imagePreview" class="setImage" alt="image" src="#" style="height: 45mm; width: 45mm;">
												<div style="position: absolute;top: 0;height: 160px;">
												<input type="file" id="studentImageId1" name="studentImage" class="form-control" style=" height: 170px !important;width:170px; opacity: 0; z-index: 99999999;">
												</div>
										</div>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student Status</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">House</label>
									<div class="col-xs-7">
										<input type="text" name="" tabindex="1"	onkeypress="HideError()" id=""
											maxlength="25" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
							</div>
						</div>
						
						<hr style="height:1px;border:none;color:#333;background-color:#ddd;"/>
					
						<div>
						<div class="slideTab clearfix">
						<div class="tab">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#contacts" id="contacts">Contacts</a></li>
								<li><a data-toggle="tab" href="#classHistory"  id="classHistory">Class History</a></li>
								<li><a data-toggle="tab" href="#examDetails"  id="examDetails">Exam Details</a></li>
								<li><a data-toggle="tab" href="#feedetails"  id="feedetails">Fee Details</a></li>
								<li><a data-toggle="tab" href="#attendance"  id="attendance">Attendance</a></li>
								<li><a data-toggle="tab" href="#libraryInfo"  id="libraryInfo">Library Info</a></li>
								<li><a data-toggle="tab" href="#appraisal"  id="appraisal">Appraisal</a></li>
							</ul>
	
						
						<div id="contacts" class="tab-pane">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap">
									<div class="col-md-8" id=div2></div>
										<div id="contacttable"></div>
										<div id="individualtable"></div>
										<div id="examdetailtable"></div>
										<div id="feedetailtable"></div>
										<div id="attendancetable"></div>
										<div id="libraryinfotable"></div>
										<div id="appraisaltable"></div>
								</div>
							</div>
						</div>
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</html>


	
