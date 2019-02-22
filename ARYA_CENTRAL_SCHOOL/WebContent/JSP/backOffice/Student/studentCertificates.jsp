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
<script type="text/javascript" src="JS/backOffice/Student/issueCertificate.js"></script>
<style type="text/css">
#allstudent th:nth-child(2){
text-align:center;
width:70px;
}
#allstudent td:nth-child(2){  
text-align:center;

}
.table-striped tbody td:nth-child(1),.table-striped thead th:nth-child(1){
width:70px !important;
}
.table-striped tbody td:nth-child(2),.table-striped thead th:nth-child(2){
width:70px !important;
}
.table-striped tbody td:nth-child(3),.table-striped thead th:nth-child(3){
width:150px;
}
.table-striped tbody td:nth-child(4),.table-striped thead th:nth-child(4){
width:300px;
}
.table-striped tbody td:nth-child(5),.table-striped thead th:nth-child(5){
width:100px;
}
.table-striped tbody td:nth-child(6),.table-striped thead th:nth-child(6){
width:190px;
}
.table-striped tbody td:nth-child(7),.table-striped thead th:nth-child(7){
width:170px;
}
</style>


</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><img src="images/addstu.png" /><span id="pageHeading">Issue Certificate</span></p>
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
						
							<a  href="#" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title class"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Issue Certificate</h4></a>
						
						
						<div class="navbar-right" style="top:-9px;">
							<span class="search">
							
							<label style="text-align: right;color:#767676;font-family: Open Sans Light;font-weight: 600;font-size: 15px;padding: 4px;">Select Certificate Type</label>	 
							 				<select name="findcerti" id="findcerti" style="color:#767676;font-family:inherit;font-size: 14px;padding: 6px;padding-bottom: 2px;">
							 					<option value="">-----Select-----</option>
							 					<option value="age">Age Certificate</option>
							 					<option value="bonafide">Bonafide Certificate</option>
							 					<option value="conduct">Course Certificate</option>
							 					<option value="visa">Student VISA</option>
											 </select>
							</span>
							<span class="buttons" style="line-height: 2.5" id="findcertificate">Go</span>
							<!-- <span class="buttons" id="" class="save" style="line-height: 1.89;">Go</span> -->
						</div>
						
					</div>
			
					<div id="classOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1"name="location" id="location" >
											<option value="">----------Select----------</option>
										
												<logic:present name="locationDetailsList" scope="request">
												<logic:iterate id="locationDetailsList" name="locationDetailsList">
												<option value='<bean:write name="locationDetailsList" property="location_id"/>'><bean:write name="locationDetailsList" property="locationname"/></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
										<input type="hidden" name="hiddenlocationname" id="hiddenlocationname" value='<bean:write name="currentlocation"/>'/>
										<input type="hidden" name="hiddenlocationid" id="hiddenlocationid" value='<bean:write name="locationId"/>'/>
										<input type = "hidden" name="hiddenaccyear" id="hiddenaccyear" value='<bean:write name="academic_year"/>'/>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="1"name="class" id="classid" >
											<option value="all">All</option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search By</label>
									<div class="col-xs-7">
										<input type="text" name="searchtext" tabindex="1" id="searchtext"maxlength="25" class="form-control"  />
									</div>
								</div>
								
							</div>
							
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
										style="text-align: right; line-height: 35px;">Section</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="8"
											name="section" id="section">
											<option value="all">All</option>
										</select>
									</div>
								</div>
								
								<p align="center"style="margin-left:17%">
								<button type="button" class="btn btn-info "
								id="search" >Search</button>
								<button type="button" class="btn btn-info "
								id="Reset" style="height:28px;">Reset</button></p>
								
							</div>
							<div id="studenttable" class="clearfix">
							</div>
					<!-- <table class='table' id='allstudent' width='100%'>
						<tr>
							<th>Select</th>
							<th>Sl.No.</th>
							<th style="text-align:center">Admission No</th>
							<th style="text-align:center">Student Name</th>
							<th style="text-align:center">Roll No</th>
							<th style="text-align:center">Class</th>
							<th style="text-align:center">Division</th>
							<th style="text-align:center">Issue Date</th>
							<th style="text-align:center">Certificate Type</th>
						</tr>
				   </table> -->
						</div>
					</div>
				</div>
			</div>
	</div>
</html>
