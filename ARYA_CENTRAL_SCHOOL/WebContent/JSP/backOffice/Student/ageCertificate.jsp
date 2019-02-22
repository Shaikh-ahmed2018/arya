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

<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Student/ageCertificate.js"></script>

<style>

#allstudent th:nth-child(1) {
    text-align: center;
    width: 70px;
}
.tab{
	background-color:#ffffff;
}
.readonly{
    background-color: #fff;
}
.form-group{
margin-bottom: 5px;
}
</style>

</head>

<body>
	<div id="dialog" style="display:none;"><p>Are you sure want to Save?</p></div>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p><img src="images/addstu.png" /><span id="pageHeading">Age Certificate</span></p>
				<div class="panel-body clearfix"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
				</div>
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
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>&nbsp;&nbsp;Age Certificate
								</h4></a>
					
						
						<div class="navbar-right">
							<span class="buttons" id="save"  style="top:4px;">Save</span>
							<span class="buttons" id="reset"  style="top:4px;">Reset</span>
							<span class="buttons" id="print"  style="top:4px;">Print</span>
							<span id="back" class="buttons" style="top:4px;">Back</span>
						</div>
						
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
							
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<input type="text" id="accyearname" style="background-color: #fff;" class="form-control" readonly="readonly"
											value='<logic:present name="stuList"><bean:write name="stuList" property="accyearid"/></logic:present>' />
									<input type="hidden" id="hiddenaccyearid" value="<logic:present name="accyearid"><bean:write name="accyearid"/></logic:present>"/>
									</div>
								</div>
							<input type="hidden" id="hiddenadd" value="<logic:present name="stuList"><bean:write name="stuList" property="address"/></logic:present>"/>
								
							<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" id="schoolName" style="background-color: #fff;" class="form-control" value='<logic:present name="stuList"><bean:write name="stuList" property="locname"/></logic:present>' />
										<input type="hidden" id="hiddenlocationid" value="<logic:present name="locationid"><bean:write name="locationid"/></logic:present>"/>
									</div>
								</div>
								<div class="form-group clearfix ">
									<label for="inputEmail" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Student Name</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" style="background-color: #fff;" id="stuname" class="form-control" 
											value='<logic:present name="stuList"  scope="request"><bean:write name="stuList" property="stuname"/></logic:present>' />
									<input type="hidden" id="hiddenstuid" value='<logic:present name="stuid" scope="request"><bean:write name="stuid"/></logic:present>'/>
									<input type="hidden" id="hiddenstugender" value='<logic:present name="stuList" scope="request"><bean:write name="stuList" property="gender"/></logic:present>'/>
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Admission	No</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" style="background-color: #fff;" class="form-control" id="admissionno"
											value='<logic:present name="stuList" scope="request"><bean:write name="stuList" property="admissionno"></bean:write></logic:present>' />
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Class</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" id="classname" style="background-color: #fff;" class="form-control" 
											value='<logic:present name="stuList" scope="request"><bean:write name="stuList" property="classname"/></logic:present>' />
										<input type="hidden" id="hiddenclassid" value='<logic:present name="classid" scope="request"><bean:write name="classid"/></logic:present>'/>
									</div>
								</div>
		
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Father's Name</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" style="background-color: #fff;" id="fathername"
											 class="form-control" 
											value='<logic:present name="stuList"><bean:write name="stuList" property="fathername"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Date Of Birth</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" style="background-color: #fff;" id="dob" class="form-control" 
											value='<logic:present name="stuList"><bean:write name="stuList" property="dob"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Purpose</label>
									<div class="col-xs-7">
										<input type="text" id="purpose" class="form-control" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">Other Info</label>
									<div class="col-xs-7">
										<input type="text" id="otherinfo" class="form-control" />
									</div>
								</div>
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 10px;">
								<div class="form-group clearfix" style="height: 87px;">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;"></label>
									<div class="col-xs-7">
										<!-- <div style="border: 1px solid #B3B0B0; margin-bottom: 10px; width: 132px; height: 125px;">
										</div> -->							
												<!-- <div style="position: absolute;top: 0;height: 160px;"></div> -->
												<div class="form-group clearfix">
												<img alt="no image found" height="132px" id="imageurl" width="125px" src='<logic:present name="stuList"><bean:write name="stuList" property="imgurl"/></logic:present>'>
												</div>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Student Status</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" style="background-color: #fff;" id="studentstatus" class="form-control" 
											value='<logic:present name="stuList"><bean:write name="stuList" property="studentstatus"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Section</label>
									<div class="col-xs-7">
										<input type="text"  id="sectionname" readonly="readonly" style="background-color: #fff;" class="form-control" 
											value='<logic:present name="stuList"><bean:write name="stuList" property="sectionname"/></logic:present>' />
										<input type="hidden" id="hiddensectionid" value='<logic:present name="sectionid"><bean:write name="sectionid"/></logic:present>'>
									</div>
								</div>
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Mother's Name</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" style="background-color: #fff;" 	id="motherName" class="form-control" 
											value='<logic:present name="stuList"><bean:write name="stuList" property="motherName"/></logic:present>' />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5" style="text-align: right; line-height: 35px;">DOB In Words</label>
									<div class="col-xs-7">
										<input type="text" readonly="readonly" style="background-color: #fff;" id="dobwords" class="form-control" 
											value='<logic:present name=""><bean:write name="" property=""/></logic:present>' />
									</div>
								</div>
							</div>
						</div>
						
						<hr id ="heading" style="height:1px;border:none;color:#333;background-color:#ddd;"/>
					
						<div>
						<div class="slideTab clearfix" id="issued">
						<div class="tab">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="issue"  id="issue">Issue History</a></li>
							</ul>
							
							<!-- <ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#contacts" onclick="OpenContacts" id="#contacts" class="contacts">Contacts</a></li>
								<li><a data-toggle="tab" href="#classHistory" onclick="classHistory" id="#classHistory" class="classhistory">Class History</a></li>
							</ul> -->
						
						<div id="issue" class="tab-pane">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap">
									<div class="col-md-8" id=div2></div>
									<div id="certificatetable"></div>
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


	
