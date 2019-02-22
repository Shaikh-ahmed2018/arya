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
	src="JS/backOffice/Student/NewStudentPromotionList.js"></script>
<style>
.form-group{
margin-bottom: 5px;}  
.save:hover {
	cursor: pointer;
}

#individualstudenttable th:nth-child(2),th:nth-child(3),th:nth-child(6){
  text-align: center;
  }
#individualstudenttable td:nth-child(2),td:nth-child(4){
  text-align: center;
  width: 20%;
  }
#studenttable th:nth-child(2),th:nth-child(4),th:nth-child(5){
width:20%;
  }
#studenttable th:nth-child(2),th:nth-child(4),th:nth-child(5){
  text-align: center;

  }

#studenttable td:nth-child(2),td:nth-child(4),td:nth-child(5),td:nth-child(6){
text-align:center;
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
   .tab-pane.active{
display: block !important;
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

.slideTab{
	padding:10px;
	font-family: Roboto Medium;
    font-size: 14px;
    font-weight: lighter;
}
}
</style>
</head>

<body>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		<p>
				<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span	id="pageHeading">Promotion</span>
			</p>
	
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Promotion</h4></a>
						

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
								<li class="active"><a data-toggle="tab" href="#promotedId"  id="promoted" class="promoted">Promoted</a></li>
								<li><a data-toggle="tab"  href="#demotedDiv" id="demoted" class="demoted">Demoted</a></li>
							</ul>
						
						<div id="promotedId" class="tab-pane active" style="display: none;">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap" style="margin-top: 10px; margin-bottom: -5px;">
									<div class="col-md-8 clearfix" id=div2></div>
									<div class="col-md-6 clearfix" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								 
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<option value="all">----------Select----------</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
										<select class="form-control" 
											name="classname" id="classname">
											<option value=""></option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search By</label>
									<div class="col-xs-7">
										<input type="text" name="searchBy" tabindex="1"
											id="searchBy"
											maxlength="25" class="form-control" 
											value='' />
									</div>
								</div>
								
							</div>
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="Acyearid" name="accyear" class="form-control" required>
											<option value="all">----------Select----------</option>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="all">----------Select----------</option>
										</select>
									</div>
								</div>
								
								<p align="center"style="margin-left:17%">
									<button type="button" class="btn btn-info " id="search" >Search</button>
									<button type="button" class="btn btn-info " id="Reset" >Reset</button></p>
							</div>
									
								<!-- 	<div id="studenttable"></div>
									<div id="individualstudenttable"></div>	 -->
									
			<div id="collapseOne" class="accordion-body collapse in">
								
				<div class="panel-body clearfix"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
					<div class="col-md-12" >
					<%-- <display:table class="table" id="allstudent"
						name="requestScope.studentdetailslist" 
						requestURI="/adminMenu.html?method=NewstudentPromotionList"
						decorator="com.centris.campus.decorator.StudentPromotedDecorator">
						<display:column title="Select">
    						  <input type="radio" name="selectBox" id="selectBox" value="${allstudent.studentId }" />      
						</display:column>
						<display:column property="count" sortable="true"
							title="Sl No <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						
						<display:column property="studentAdmissionNo" sortable="true"
							title="Admission No	<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
							
						<display:column property="studentFullName" sortable="true"
							title="Student Name	<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="studentrollno" sortable="true"
							title="Roll No.<img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="classname" sortable="true"
							title="Class <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="sectionnaem" sortable="true"
							title="Division <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:column property="specilizationname" sortable="true"
							title="Specilization <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>

						<display:setProperty name="paging.banner.page.separator" value=""></display:setProperty>

						<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
							
					</display:table>  --%>
					
					<logic:present name="studentdetailslist" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>Sl No</th>
							<th>Admission No</th>
							<th>Student Name</th>
							<th>Roll No.</th>
							<th>Class</th>
							<th>Division</th>
							<th>Specilization</th>
							
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="studentdetailslist" name="studentdetailslist">
								<tr>
								<td><bean:write name='studentdetailslist' property="count"/></td>
								<td><bean:write name="studentdetailslist" property='studentAdmissionNo'/></td>
								<td><bean:write name="studentdetailslist" property='studentFullName'/></td>
								<td><bean:write name="studentdetailslist" property='studentrollno'/></td>
								<td><bean:write name="studentdetailslist" property='classname'/></td>
								<td><bean:write name="studentdetailslist" property='sectionnaem'/></td>
								<td><bean:write name="studentdetailslist" property='specilizationname'/></td>
								
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
					
					
					
					</div>
					<div class="col-md-12">
					<div class="row">
				<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
					<span  class='numberOfItem'></span>	
					</div>
					<div class='pagination pagelinks'></div>
				</div>
				</div>
			</div>
			</div>		
					
								</div>
								</div>
							</div>
							
							<div id="demotedDiv" class="tab-pane" style="display: none;">
							<div class="col-md-12" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;border-right: 1px solid #ddd;">
								<div class="searchWrap" style="margin-top: 10px; margin-bottom: -5px;">
									<div class="col-md-8" id=div2></div>
									<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								 
									<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="locationname1" name="locationnid" class="form-control locationname" required>
											<option value="all">----------Select----------</option>
											<logic:present name="locationList">
												<logic:iterate id="Location" name="locationList">
													<option value="<bean:write name="Location" property="locationId"/>"><bean:write name="Location" property="locationName" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;"> Class</label>
									<div class="col-xs-7">
										<select class="form-control" 
											name="classname" id="classid">
											<option value=""></option>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search By</label>
									<div class="col-xs-7">
										<input type="text" name="searchBy" tabindex="1"
											id="searchBy1"
											maxlength="25" class="form-control" 
											value='' />
									</div>
								</div>
								
							</div>
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Academic Year</label>
									<div class="col-xs-7">
										<select id="acyearid" name="accyear" class="form-control Acyearid" required>
											<option value="all">----------Select----------</option>
											<logic:present name="AccYearList">
												<logic:iterate id="AccYear" name="AccYearList">
													<option	value="<bean:write name="AccYear" property="accyearId"/>"><bean:write name="AccYear" property="accyearname" /></option>
												</logic:iterate>
											</logic:present>
										</select>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid1" name="sectionid" class="form-control" required>
											<option value="all">----------Select----------</option>
										</select>
									</div>
								</div>
								
								<p align="center"style="margin-left:17%">
									<button type="button" class="btn btn-info " id="searchdemoted" >Search</button>
									<button type="button" class="btn btn-info " id="demotedReset" >Reset</button></p>
							</div>
							
						</div>
						<div id="demotedTable">
						<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select></div><div class='pagination pagelinks'></div>
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
</div>
					
</html>
