<!DOCTYPE html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html lang="en">

<head>

<title>eCampus Pro</title>

<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect-blind.js"></script>
<script type="text/javascript"
	src="JQUERY/js/jquery.ui.effect-explode.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />

<link href="CSS/newUI/custome.css" rel="stylesheet">
<script src="JS/backOffice/Reports/CustomizableStudentReport.js"></script>

<style>
.modal-body {
	text-align: center;
}
</style>
<style>
.buttons {
	vertical-align: -28px;
}
.tolalListField,.SelectedListField{
border: 1px solid #dedede;
height: 300px;
overflow: scroll;
}
.tolalListField ul,.SelectedListField ul {
margin: 0;
padding: 0;
}
.tolalListField ul li,.SelectedListField ul li{
cursor: pointer;
padding: 3px;
background: #FEFEFE;
border: 1px solid;
}
.tolalListField ul li:hover{
background: #DEDEDE;
}
.tolalListField ul li:visited{
background: #c7e7c7;
}
#orderb{
display: inline-block;
}
</style>
</head>



<body>


	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">

		<p>
			<img src="images/addstu.png" />&nbsp;<span style="font-size: 16pt;">All Student </span>
		</p>

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
					class="successmessage"></span></a>
			</div>
		</div>
		
		<p id="parent1" style="display: none;">
			<a href="">Expand all</a>
		</p>
		<div class=" -group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary panel-list">
					<div class="panel-heading" role="tab" id="headingOne">

					
						<a data-toggle="collapse" data-parent="#accordion2"
							href="#collapseOne" style="color: #767676"><h4 class="panel-title" id="beforeparent"><i
							class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Student
						</h4></a>
					

					<div class="navbar-right">
						<span class="buttons" id="downloadexcell">Download</span>

					</div>
					
				</div>

		
			<!-- pop up -->

			

			<!-- Filters -->

			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt" style="padding: 15px;">
				<div class="tab-row" style="margin-bottom: 15px;">
					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-5"
										style="text-align: right; line-height: 35px;">School Name</label>
									<div class="col-xs-7">
										<select id="location" name="location" class="form-control" required>
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
									name="classname" id="class">
									<option value="all">ALL</option>
							</select>
							</div>
						</div>
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-5"
								style="text-align: right; line-height: 35px;"> Select Field</label>
							<div class="col-xs-7">
							<div class="tolalListField">
							<ul style="list-style: none">
							<li id="student_fname_var">Student First Name</li>
							<li id="student_lname_var">Student Last Name</li>
							<li id="Location_Name">School Name</li>
							<li id="student_rollno">Roll No.</li>
							<li id="student_admissionno_var">Admission No.</li>
							<li id="student_doj_var">Admission Date</li>
							<li id="classstream_name_var">Stream</li>
							<li id="classdetails_name_var">Standard</li>
							<li id="classsection_name_var">Division</li>
							<li id="Specialization_name">Specilization</li>
							<li id="housename">House</li>
							<li id="student_dob_var">D.O.B</li>
							<li id="student_gender_var">Gender</li>
							<li id="student_bloodgroup_var">Blood Group</li>
							<li id="religion">Religion</li>
							<li id="motherTounge">Mother Tounge</li>
							<li id="adharNo">Adhar No.</li>
							<li id="smsNo">Sms No.</li>
							<li id="emergencyNo">Emergency No.</li>
							<li id="caste">Caste</li>
							<li id="casteCategory">Caste Category</li>
							<li id="student_identificationmarks_var">Identification Marks 1</li>
							<li id="student_identificationmarks1_var">Identification Marks 2</li>
							<li id="student_nationality_var">Nationality</li>
							<li id="subjectName_s">2nd Language</li>
							<li id="subjectName_t">3rd Language</li>
							<li id="FatherName">Father Name</li>
							<li id="mobileno">Father. Mobile No.</li>
							<li id="fatherOfficeAddress">Father Office Address</li>
							<li id="fatherAnnualIncome">Father Annual Income</li>
							<li id="email">Father Email</li>
							<li id="Qualification">Father Qualification</li>
							
							<li id="student_mothername_var">Mother Name</li>
							<li id="student_mothermobileno_var">Mother. Mobile No.</li>
							
							<li id="motherOfficeAddress">Mother Office Address</li>
							<li id="motherAnnualIncome">Mother Annual Income</li>
							<li id="student_mother_mailid">Mother Email</li>
							<li id="student_motherqualification_var">Mother Qualification</li>
							
							<li id="student_gaurdianname_var">Guardian Name</li>
							<li id="student_gardian_mobileno">Guardian. Mobile No.</li>
							<li id="guardianOfficeAddress">Guardian Office Address</li>
							<li id="guardianAnnualIncome">Guardian Annual Income</li>
							<li id="student_gardian_mailid">Guardian Email</li>
							<li id="guardianQualification">Guardian Qualification</li>
							
							<li id="address">Permanent Address </li>
							<li id="presentAddress">Present Address</li>
							</ul>
							
							</div>
							</div>
						</div>
						
					</div>

					<div class="col-md-6" id="txtstyle">
						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Academic Year</label>
							<div class="col-xs-7">
								<select id="accyear" name="accyear" class="form-control"
									required>
									
									<logic:present name="AccYearList">
										<logic:iterate id="AccYear" name="AccYearList">
											<option
												value="<bean:write name="AccYear" property="accyearId"/>">
												<bean:write name="AccYear" property="accyearname" />
											</option>
										</logic:iterate>
									</logic:present>
								</select>
							</div>
						</div>
					

						<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								id="inputnames" style="text-align: right;">Division</label>
							<div class="col-xs-7">
								<select id="section" name="section" class="form-control"
									required>
									<option value="all">ALL</option>
								</select>
							</div>
						</div>
						
					<div class="form-group clearfix">
							<label for="inputPassword" class="control-label col-xs-4"
								style="text-align: right; line-height: 35px;"> Selected Field</label>
							<div class="col-xs-7">
							<form id="customizable">
							<div class="SelectedListField">
							<ul style="list-style: none">
							
							</ul>
							
							</div>
							</form>
							</div>
						</div>
					
					</div>
				<div id="orderby"> 
				<input type="checkbox" id="orderbycheck" /><p id="orderb"> ORDER BY</p>
				<div class="orderby"></div>
				</div>	
					</div>
					
					<div class="col-md-12 selecteditems">
						<br /> <input type="hidden" id="haccyear"
							value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="accyearId"/></logic:present>" />
						<input type="hidden" id="hstream"
							value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="streamId"/></logic:present>" />
						<input type="hidden" id="hclass"
							value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="classId"/></logic:present>" />
						<input type="hidden" id="hsection"
							value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="sectionId"/></logic:present>" />
						<input type="hidden" id="hlocation"
							value="<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="locationId"/></logic:present>" />

						<span><b>Location :</b></span>&nbsp;&nbsp;&nbsp;<span>
						<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="locationName" />
						</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						
						<span><b>Academic Year :</b></span>&nbsp;&nbsp;&nbsp;<span>
						<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="accyearname" />
						</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
											
						<span><b>Class :</b></span>&nbsp;&nbsp;&nbsp;<span>
						<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="classname" />
						</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						
						<span><b>Section :</b></span>&nbsp;&nbsp;&nbsp;<span>
						<logic:present name="CurrentForm"><bean:write name="CurrentForm" property="sectionname" />
						</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>

					</div>
		
					<div id="studentlisttable"></div>
				</div>
			</div>
			</div>
		</div>
		</div>
	<!-- Button trigger modal -->
	<span>&nbsp;</span>
	
	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>

</html>
