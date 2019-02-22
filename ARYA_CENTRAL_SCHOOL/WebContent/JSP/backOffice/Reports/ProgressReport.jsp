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
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />
<script type="text/javascript" src="JS/backOffice/Reports/ProgressReport.js"></script>
<script type="text/javascript">

function handle(e){ 
	
	
	
    if(e.keyCode === 13){
        e.preventDefault(); // Ensure it is only this code that rusn

        searchList();
    }
}

</script>


</head>


<body>
<div id="dialog" style="display: none;">		
		<div id="dialog1">
			<div class="col-md-12" id="txtstyle"
				style="font-size: 11pt; color: #5d5d5d;">
				
				<table style="width: 100%;" id="dependency">
							<tr>
								<td ><label for="inputEmail"style="text-align: center; line-height: 35px; width: 88px">Select Exam</label></td>
								<td ><select id="Exam" style="width: 150px;"></select></td>
							</tr>

						</table>
				
			</div>
		</div>
	</div>
<input type="hidden" id="hstudentId"/>
<input type="hidden" id="haccyear"/>
<input type="hidden" id="hlocationId"/>
<input type="hidden" id="hclassId"/>
<input type="hidden" id="hSectionId"/>
<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
	<div class="col-md-10 col-md-offset-2" id="div-main" style="font-size: 16pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; ">
		
		<p><img src="images/addstu.png" /><span id="pageHeading">Progress Reports</span></p>
				<div class="panel-body clearfix"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">
					
				</div>

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" style="color: #767676; vertical-align: text-top;"> 
							<h4 class="panel-title"><i	class="glyphicon glyphicon-menu-hamburger"></i>	&nbsp;&nbsp;Progress Reports</h4></a>
						
					</div>

					<div id="collapseOne" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body own-panel">
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="locationname" name="locationnid" class="form-control" required>
											<option value="all">ALL</option>
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
									
									<select class="form-control" onkeypress="HideError()" 
											name="classname" id="classname">
											<option value="all">ALL</option>
										</select>
									

									</div>
								</div>
								
								
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align:right; line-height: 35px;">Search By</label>
									<div class="col-xs-7">
										<input type="text" class="form-control" id="searchvalue" Placeholder="Search......" onkeypress="handle(event)"
										value="<logic:present name='SearchList' scope='request' ><bean:write name='SearchList'/></logic:present>">
									</div>
								</div>
								
							</div>
							
							<div class="col-md-6" style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;margin-top: 20px;">
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
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">Division</label>
									<div class="col-xs-7">
										<select id="sectionid" name="sectionid" class="form-control" required>
											<option value="all">ALL</option>
										</select>
									</div>
								</div>
							
								
								<p align="center"style="margin-left:17%">
								<button type="button" class="btn btn-info" id="search" >Search</button>
								<button type="reset" class="btn btn-info" id="resetbtn" style="height:28px;" >Reset</button></p>
							</div>
						<input type="hidden" name="Acyearid" id="Acyearid" value='<logic:present name="Acyearid"><bean:write name="Acyearid"/></logic:present>'></input>
							
				<div id="collapseOne" class="accordion-body collapse in">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">	
					
					<%-- <display:table class="table" id="allstudent"
						name="requestScope.studentList"
						requestURI="/adminMenu.html?method=studentSearch?">
						<display:column property="sno" sortable="true"	title="S.No"/>
						<display:column property="studentAdmissionNo" class="${allstudent.studentId} ${allstudent.academicYearId} ${allstudent.locationId} ${allstudent.classDetailId} ${allstudent.sectioncode} studentid" sortable="true" title="Admission No <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="studentFullName" sortable="true" title="Student Name <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="studentrollno" sortable="true" title="Roll No <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="classname" sortable="true" title="Class <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						<display:column property="sectionnaem" sortable="true" title="Division <img src='images/sort1.png' style='float: right'/>"
							media="html"></display:column>
						
					</display:table> --%>
					
					<logic:present name="studentList" scope="request">
						<table class="table" id="allstudent">
							<thead>
							<tr>
							<th>S.No</th>
							<th>Admission No</th>
							<th>Student Name</th>
							<th>Roll No</th>
							<th>Class</th>
							<th>Division</th>
							
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="studentList" name="studentList">
								<tr>
								<td><bean:write name='studentList' property="sno"/></td>
								<td class="<bean:write name="studentList" property='studentId'/> <bean:write name="studentList" property='academicYearId'/><bean:write name="studentList" property='locationId'/><bean:write name="studentList" property='classDetailId'/><bean:write name="studentList" property='sectioncode'/> studentid" ><bean:write name="studentList" property='studentAdmissionNo'/></td>
								<td><bean:write name="studentList" property='studentFullName'/></td>
								<td><bean:write name="studentList" property='studentrollno'/></td>
								<td><bean:write name="studentList" property='classname'/></td>
								<td><bean:write name="studentList" property='sectionnaem'/></td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</logic:present>
				
					</div>
<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select>
	<span  class='numberOfItem'></span>	
	</div><div class='pagination pagelinks' style='top:-9px'></div>
					</div>
					
						</div>
					</div>
				</div>
			</div>
	</div>
</html>
