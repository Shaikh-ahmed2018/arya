<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>
<link href="CSS/newUI/custome.css" rel="stylesheet"> 
<script type="text/javascript" src="JS/common.js"></script>
<script type="text/javascript" src="JS/backOffice/Settings/ExcelUploadStudentMarks.js">
</script>
<style>
.form-control {
	width: 70%;
}
</style>
<style>
.buttons{
vertical-align: 0;
}
#txtstyle {
    font-family: Open Sans Light;
    font-size: 11pt;
    color: #767676;
    text-align: left;
    text-decoration: u;
    font-size: 15px;
    font-style: bo;
    font-weight: 900;
}
#inputnames{
    text-align: right;
    padding: 7px 0px;
}

  #allstudent td:nth-child(7),td:nth-child(8){
 		min-width:4px;
  }
  #allstudent td:nth-child(1){
 		min-width:150px;
  }
  #back1,#saveid{
  margin-right:5px;
  }
</style>
</head>
<body>

	<div class="col-md-10 col-md-offset-2" id="div-main"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Student Marks Excel upload</span>
		</p>
		
		<div class="errormessagediv" align="center" style="display: none;">
			<div class="message-item">
				<!-- Warning -->
				<a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
			</div>
		</div>
		<logic:present name="errorMessage" scope="request">
			<div class="errormessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span><bean:write
								name="errorMessage" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>
		<logic:present name="successmessagediv" scope="request">
			<div class="successmessagediv" align="center">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span><bean:write
								name="successmessagediv" scope="request" /></span></a>
				</div>
			</div>
		</logic:present>

		<form action="uploadStudentXSL.html?method=uploadStudentExamDetails_byExcel"
			id="excelfileupload" name="SettingExcelUpload" method="post" enctype="multipart/form-data">
			<input type="hidden" name="inserted" id="inserted" value='<logic:present name="insertion" scope="request"><bean:write name="insertion" /></logic:present>' />
			
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingTwo">
						<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" style="color: #767676">
							<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Download Class-Section Wise Marks Excel Upload Format
						</h4></a>
						
						<div class="navbar-right">
						 	<span id="downloadid" class="buttons">Download</Span> 
						</div>
					</div>
				
				<div class="feebody panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					<div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo" align="center">
						<div class="panel-body">
							<div class="tab-row" >
								<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">School Name<font color="red">*</font></label>
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
											style="text-align: right; line-height: 35px;"> Class<font color="red">*</font></label>
										<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" name="classname" id="class">
												<option value="all">ALL</option>
										</select>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Exam Name<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="examname" name="examname" class="form-control">
												<option value="">----------Select----------</option>
											</select>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Exam Start Date</label>
										<div class="col-xs-7">
											<input type="text" id="examstartdate" class="form-control" readonly="readonly"/>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Exam Code</label>
										<div class="col-xs-7">
											<input type="text" id="examcode" class="form-control" readonly="readonly"/>
										</div>
									</div>
									
								</div>

								<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4"
											id="inputnames" style="text-align: right;padding: 0px;">Academic Year<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="accyear" name="accyear" class="form-control" required>
												<logic:present name="AccYearList">
													<logic:iterate id="AccYear" name="AccYearList">
														<option value="<bean:write name="AccYear" property="accyearId"/>">
															<bean:write name="AccYear" property="accyearname" />
														</option>
													</logic:iterate>
												</logic:present>
											</select>
										</div>
									</div>
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4" id="inputnames" style="text-align: right;padding: 0px;">Division<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="section" name="section" class="form-control" required>
												<option value="all">ALL</option>
											</select>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4"
											style="text-align: right; padding: 0px;">Subject Name<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="subjectid" name="subjectid" class="form-control">
												<option value="">----------Select----------</option>
											</select>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4" id="inputnames" style="text-align: right;padding: 0px;">Exam End Date</label>
										<div class="col-xs-7">
											<input type="text" id="examenddate" class="form-control" readonly="readonly"/>
										</div>
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
			
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style="color: #767676">
								<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Upload Student Marks details 
							</h4></a>
						<div class="navbar-right">
						 	<span id="saveid" class="buttons">Upload</Span> 
						</div>
					</div>
					<div class="feebody panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne" align="center">
							<div class="panel-body">
								<div class="col-md-6" id="txtstyle" style="padding: 0;">
									<div class="form-group">
										<label for="inputEmail" class="control-label col-xs-4"
											id="inputnames">Choose File</label>
										<div class="col-xs-8">
											<input type="file" name="formFile" id="exam"
												class="form-control" style="height: auto;"></input>
										</div>
										<br />
									</div>
								</div>

								<div class="col-md-3" id="txtstyle" style="padding: 10px 0px;">
									<a href="uploadStudentXSL.html?method=studentExcelUpload_instructions">Download Excel upload Instructions</a>
								</div>
								
							 	<logic:present name="FailedExamUploadList" scope="request">
									<div class="col-md-12" style="padding: 0px; height: 250px; overflow: scroll;">
										<p class="theading">
											<span class="displayno"></span>
										</p>
										<p>
											<font size="4">Following Records are not Uploaded!</font>
										</p>

										<display:table id="allstudent" name="requestScope.FailedExamUploadList" class="table" requestURI="/uploadStudentXSL.html?method=uploadExcelStudentMarks">
											<display:column property="reason" title="Reason" headerClass="heading1"/>
											<display:column property="academicYear" title="Academic Year" headerClass="heading1" />
											<display:column property="schoolName" title="School Name" headerClass="heading1" />
											<display:column property="studentIdNo" title="Student Id No" headerClass="heading1" />
											<display:column property="className" title="Class" headerClass="heading1" />
											<display:column property="sectionName" title="Section" headerClass="heading1" />
											<display:column property="examName" title="Exam Name" headerClass="heading1" />
											<display:column property="examCode" title="Exam Code" headerClass="heading1" />
											<display:column property="subjectName" title="Subject Name" headerClass="heading1" />
										</display:table>
									</div>
									<div class="paginationbox">
										<p class="paginationp"></p>
									</div>
								</logic:present> 
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingTwo">
						<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" style="color: #767676">
							<h4 class="panel-title"><i class="glyphicon glyphicon-menu-hamburger"></i>
							&nbsp;&nbsp;Download Class-Section Wise Co-Scholastic Areas Excel Upload Format
						</h4></a>
						
						<div class="navbar-right">
						 	<span id="downloadid1" class="buttons">Download</Span> 
						</div>
					</div>
				
				<div class="feebody panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					<div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo" align="center">
						<div class="panel-body">
							<div class="tab-row" >
								<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">School Name<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="location1" name="location1" class="form-control" required>
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
											style="text-align: right; line-height: 35px;">Class<font color="red">*</font></label>
										<div class="col-xs-7">
										<select class="form-control" onkeypress="HideError()" name="classname1" id="class1">
												<option value="all">ALL</option>
										</select>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Exam Name<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="examname1" name="examname1" class="form-control">
												<option value="">----------Select----------</option>
											</select>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-5"
											style="text-align: right; line-height: 35px;">Exam Start Date</label>
										<div class="col-xs-7">
											<input type="text" id="examstartdate1" class="form-control" readonly="readonly"/>
										</div>
									</div>
									
								</div>

								<div class="col-md-6" id="txtstyle">
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4"
											id="inputnames" style="text-align: right;padding: 0px;">Academic Year<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="accyear1" name="accyear1" class="form-control" required>
												<logic:present name="AccYearList">
													<logic:iterate id="AccYear" name="AccYearList">
														<option value="<bean:write name="AccYear" property="accyearId"/>">
															<bean:write name="AccYear" property="accyearname" />
														</option>
													</logic:iterate>
												</logic:present>
											</select>
										</div>
									</div>
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4" id="inputnames" style="text-align: right;padding: 0px;">Division<font color="red">*</font></label>
										<div class="col-xs-7">
											<select id="section1" name="section" class="form-control" required>
												<option value="all">ALL</option>
											</select>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4" id="inputnames" style="text-align: right;padding: 0px;">Exam Code</label>
										<div class="col-xs-7">
											<input type="text" id="examcode1" class="form-control" readonly="readonly"/>
										</div>
									</div>
									
									<div class="form-group clearfix">
										<label for="inputPassword" class="control-label col-xs-4" id="inputnames" style="text-align: right;padding: 0px;">Exam End Date</label>
										<div class="col-xs-7">
											<input type="text" id="examenddate1" class="form-control" readonly="readonly"/>
										</div>
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
</body>
</html>
