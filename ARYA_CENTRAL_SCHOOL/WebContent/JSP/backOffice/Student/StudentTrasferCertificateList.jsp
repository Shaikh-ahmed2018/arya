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

<script type="text/javascript" src="JS/common.js"></script>
<link href="CSS/Admin/Admission/StudentNew.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="JS/backOffice/Student/StudentTransferCerticateList.js"></script>
<style>
.form-group{
margin-bottom: 5px;}
.save:hover {
	cursor: pointer;  
}



#individualstudenttable th:nth-child(3){
  width: 20%;
  }


#individualstudenttable td:nth-child(2),td:nth-child(4),td:nth-child(5),td:nth-child(6),td:nth-child(7){
  text-align: center;
  
  }
#individualstudenttable th:nth-child(4),th:nth-child(5){
  width: 10%;
  }
  
  
#studenttable th:nth-child(3){
  width: 20%;
  }

#studenttable td:nth-child(2),td:nth-child(4),td:nth-child(5),td:nth-child(6),td:nth-child(7){
  text-align: center;
  
  }
 #studenttable th:nth-child(4),th:nth-child(5){
  width: 10%;
  }



fieldset { 
	width:512px;
    display: block;
   /*  margin-left: auto;
    margin-right: 2px; */
    margin-bottom: 5px;
    padding-bottom: 0.625em;
    padding-left: 7px;
    padding-right: 0px;
    border: 0.5px solid #ccc;
   
}
legend {
    display: inline-block;
    width: auto;
    padding: 0;
    margin-bottom: 0px;
    font-size: 16px;
    line-height: inherit;
    color: #333;
    border: 0;
   }
</style>
<style>
.buttons{

vertical-align:-5px;

}
.navbar-right {
    top: -3px;
}
.panel-primary>.panel-heading{
margin-bottom: 0px;
}
form .panel.panel-primary.panel-list{
padding-bottom: 0px;

}    

@media (min-width:320px) and (max-width:767px){
br{
display: none;
}

}
.slideTab{
	padding:10px;
	font-family: Roboto Medium;
    font-size: 14px;
    font-weight: lighter;
}

</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Transfer Certificate</span>
			</p>
			
	
		<form action="studentRegistration.html?method=saveStudentRegistration"
			enctype="multipart/form-data" name="StudentRegistrationForm" id="formstudent" method="post">

			
													
			<!-- chiru -->

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Transfer Certificate</h4></a>
						

						<div class="navbar-right">
							<span class="buttons" id="addnew">Add New</span>
							
						</div>
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
					
						<div>
						<div class="slideTab clearfix">
						<div class="tab">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#reporthistory"  id="reporthistory" class="contacts">Issued</a></li>
								<li><a data-toggle="tab"  href="#contacts" id="contacts" class="classhistory">Cancelled</a></li>
							</ul>
						
						<div id="reporthistory" class="tab-pane">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap" style="margin-top: 10px; margin-bottom: -5px;">
									<div class="col-md-8" id=div2></div>
									<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								 
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="3"
											name="schoolLocation" id="schoolLocationId" >
											<option value=""></option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
									
										<select id="class" name="classnae" class="form-control"
											required>
											<option value="%%">All</option>

										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search By</label>
									<div class="col-xs-7">
										<input type="text" name="searchBy" tabindex="1"
											onkeypress="HideError()" id="searchBy"
											maxlength="25" class="form-control" 
											value='<logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentFirstName"/></logic:present>' />
									</div>
								</div>
								
							</div>
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select name="academicYear" onkeypress="HideError()" id="academicYear" class="form-control"></select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" tabindex="8"
											name="" id="">
											<option value="">All</option>
										</select>
									</div>
								</div>
								
								<p align="center"style="margin-left:17%">
								<button type="button" class="btn btn-info "
								id="search" >Search</button>
								<button type="button" class="btn btn-info "
								id="Reset" >Reset</button></p>
							</div>
									
									<div id="studenttable"></div>
									<div id="individualstudenttable"></div>	
					
								</div>
								</div>
							</div>
							
						</div>
					</div>
					</div>
						
						
						</div>
							
						</div>
						
		</form>
	</div>
</html>
