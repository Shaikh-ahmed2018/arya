<!DOCTYPE html>
<html lang="en">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<head>

<link href="/CSS/newUI/custome.css" rel="stylesheet">
<script type="text/javascript" src="JS/backOffice/Student/MarksUpload.js"></script>
<script type="text/javascript" src="JS/common.js"></script>

<style >

.form-control{

width: 70%;
}

</style>

</head>

<body>
	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span id="pageHeading">Marks Upload</span>
		</p>
		
				<div class="successmessagediv" align="center" style="display: none;">
						<div class="message-item">
							<!-- Warning -->
							<a href="#" class="msg-success bg-msg-succes"><span
								class="validateTips"></span></a>
						</div>
				</div>	

					
				<div class="errormessagediv" align="center" style="display: none;">
					<div class="message-item">
					<!-- Warning -->
				    <a href="#" class="msg-warning bg-msg-warning"><span
					class="validateTips"></span></a>
					</div>
				</div>	
				

				<logic:present name="successmessagediv" scope="request">
					<div class="successmessagediv">
						<div class="message-item">
							<!-- Warning -->
							<a href="#" class="msg-success bg-msg-succes"><span><bean:write
										name="successmessagediv" scope="request" /></span></a>
						</div>
					</div>
				</logic:present>
		

		<form method="post">
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="headingOne">
						
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Student Marks Upload
							</h4></a>
						
					<div class="navbar-right">
							
							
							
							<span id="saveid" class="buttons">
							
							<img src="images/save.png" class="save" data-toggle="tooltip" data-placement="bottom" title="Upload Marks">Save
									
							</Span>	
							
								
					
							<span id="back" class="buttons">Back</span>
						</a>
					</div>
					
					</div>
					
					<script>
	$(document).ready(function(){
	    $('[data-toggle="tooltip"]').tooltip();   
	});
	</script>
					

			<div class="feebody panel-group" id="accordion" role="tablist" aria-multiselectable="true">

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne" align="center">
						<div class="panel-body">
						
						<input type="hidden" id="hExamId" value="<logic:present name="ExamId"><bean:write name="ExamId" /></logic:present>"/>
						<input type="hidden" id="hclass" value="<logic:present name="ClassId"><bean:write name="ClassId"/></logic:present>"/>
						<input type="hidden" id="hSectionId" value="<logic:present name="SectionId"><bean:write name="SectionId"/></logic:present>"/>
						<input type="hidden" id="hSubjectId" value="<logic:present name="SubjectId"><bean:write name="SubjectId"/></logic:present>"/>

							
							<div class="col-md-6" id="txtstyle">

								<div class="form-group">
									<label class="control-label col-xs-6"
										style="text-align: right; line-height: 35px;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;" >Class Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Open Sans Light" id="addmissionNo"><logic:present name="ClassName"><bean:write name="ClassName"/></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-xs-6"
										style="text-align: right; font-family: sans-serif; line-height: 35px;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;">Subject Name
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Open Sans Light"><logic:present name="SubjectName"><bean:write name="SubjectName"/></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-xs-6"
										style="text-align: right; font-family: sans-serif; line-height: 35px;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;">Max Marks
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<%-- <label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Open Sans Light"><logic:present name="MaxMarks"><bean:write name="MaxMarks" /></logic:present>-<logic:present name="FeeCollectionVo"><bean:write name="FeeCollectionVo" property="sectionname"/></logic:present>
										</label> --%>
										
										<input type="text" class="form-control" onkeypress="HideError()" style="margin-left: -36px;" id="maxmarks" value="<logic:present name="MaxMarks"><bean:write name="MaxMarks" /></logic:present>">
									</div>
								</div>
								</div>

							<div class="col-md-6" id="txtstyle">

								<div class="form-group">
									<label class="control-label col-xs-6"
										style="text-align: right; font-family: sans-serif; line-height: 35px;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;">Section Name
										Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Open Sans Light"><logic:present name="SectionName"><bean:write name="SectionName" /></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-xs-6"
										style="text-align: right; font-family: sans-serif; line-height: 35px;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;">Exam Name
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Open Sans Light"><logic:present name="ExamName"><bean:write name="ExamName" /></logic:present>
										</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-xs-6"
										style="text-align: right; font-family: sans-serif; line-height: 35px;font-family:Roboto Medium; font-size: 14px;font-weight:lighter;">Min Pass Marks
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
									<div class="col-xs-6">
										<%-- <label for="inputPassword" class="col-xs-12"
											style="text-align: left; line-height: 35px;font-size: 13px;font-family: Open Sans Light" id="concessionPercent"><logic:present name="MinMarks"><bean:write name="MinMarks" /></logic:present>%
										</label>
										 --%>
										<input type="text" class="form-control" onkeypress="HideError()" style="margin-left: -36px;" id="minmarks" value="<logic:present name="MinMarks"><bean:write name="MinMarks" /></logic:present>">
									</div>
								</div>
								

							</div>


						</div>
						
						
							<logic:present name="StudentMarksDetails" scope="request">
							
							<logic:notEmpty name="StudentMarksDetails">
							
									<table  class="table" id="allstudent" style="font-family: Open Sans Light;color: #897676;" >
										
										<tr>
											<th class="headth">Sno</th>
											<th class="headth">Sudent Name</th>
											<th class="headth">Admission No</th>
											<th class="headth">Scored Marks</th>
											
										</tr>
											<logic:iterate id="studentmarks" name="StudentMarksDetails"  >
												
												<logic:iterate id="dateVO" name="studentmarks" property="studentmarkslist">
												<tr>
												
													<input type="hidden" value="<bean:write name="dateVO" property="studentId" />" class="studentid"/>
													<td class="sno" ><bean:write name="dateVO" property="serialNumber" /></td>
													<td class="addmissionNo"><bean:write name="dateVO" property="studentAdmissionNo" /></td>
													<td class="studentname"><bean:write name="dateVO" property="studentFirstName" /></td>
													<td class="scoredmarks"><input type="text" class="concessionamt form-control" style="margin:-7px 0px" value="<bean:write name="dateVO" property="enterMarks" />"/></td>
												
												</tr>
												
												</logic:iterate>
												
												
											</logic:iterate>
											
											
										</table>
						</logic:notEmpty>
					</logic:present>
					
						
					</div>

			
				
			</div>
			
			</div>
					</div>

		</form>
				</div>
			
				
			
	
</body>

</html>
