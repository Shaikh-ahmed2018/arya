<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>eCampus Pro</title>

<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">

<link href="CSS/newUI/modern-business.css" rel="stylesheet">
<link href="CSS/newUI/custome.css" rel="stylesheet">
<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JQUERY/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.button.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.position.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="JQUERY/js/jquery.ui.effect.js"></script>
<link href="JQUERY/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="JS/backOffice/Student/ProgressReportIndividual.js"></script>


 

<style>


#editStudent:hover {
	cursor: pointer;
}

#allstudent{
    width: 78%;
    margin-left: 120px;
}

.allstudent{
width: 100%;
margin-top: 10px;
margin-bottom: 10px;
}

.allstudent th{
text-align: center;
width: 100px;
}
.allstudent tr{
text-align: center;
}
#trash:hover {
	cursor: pointer;
}

#allstudent th {
	width: 100px;
	text-align: center;
}

#allstudent tr {
	text-align: center;
}

.allstudent th:nth-child(1) {
    text-align: center;
    width: 100px;
}
</style>

</head>

<body>

	<div class="col-md-10 col-md-offset-2" id="div1">
		<div class="searchWrap">
			<div id="div2">

				<p>
					<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<span
						id="pageHeading">Exam Term Details</span>
				</p>
			</div>

		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#collapseOne">
					<h3 class="panel-title"
						style="color: #767676; vertical-align: text-top;">
						<span class="glyphicon glyphicon-menu-hamburger"></span>
						&nbsp;&nbsp;Exam Term Details
					</h3>
				</a>

				<div class="navbar-right">
                          <span class="buttons" id="iconsimg" data-toggle="modal"
								data-target="#myModal" 
								 style="margin-right: 6px; vertical-align: top;">Download </span>
                          <span class="buttons" id="back" style="vertical-align: top;">Back</span>
				</div>
			</div>
			<!-- pop up -->
			
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Download</h4>
								</div>
								<div class="modal-body">
									<span id="excelDownload"><img src="images/xl.png"
										class="xl"></span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
										id="pdfDownload"><img src="images/pdf.png" class="pdf"></span>
								</div>

							</div>
						</div>
					</div>
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body" id="tabletxt">
				<logic:iterate id="studentSearchList" name="studentSearchList">
                       <div class="col-md-12">
						<table class="allstudent">
						<tr>
						<th>Admission No</th>
						<th>Student Name</th>
						<th>Class</th>
						<th>Section</th>
						<th>Result</th>
						</tr>
                        <tbody>
                        
                        <tr><td><logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentAdmissionNo"></bean:write></logic:present></td>
                        <td><logic:present name="studentSearchList"><bean:write name="studentSearchList" property="studentFullName"/></logic:present></td>
                        <td><logic:present name="studentSearchList"><bean:write name="studentSearchList" property="classname"/></logic:present></td>
                        <td><logic:present name="studentSearchList"><bean:write name="studentSearchList" property="sectionnaem"/></logic:present></td>
                        <td><logic:present name="result" scope="request"><bean:write name="result"/></logic:present></td>
                        </tr>
                        </tbody>
						</table>
						
						</div>
						</logic:iterate>
                      </div>

					<div class="row">
					
					<div class="panel-body" id="tabletxt">
					
					<div class="col-md-12">
						<table id="allstudent" class="col-md-8">
						<tr>
						
						<th>Subject Code</th>
						<th>Subject Name</th>
						<th>Max Marks</th>
						<th>Obtained Marks</th>
						<th>Subject Grade</th>
						</tr>
                        <tbody>
                        <logic:iterate id="subjectList" name="subjectList">
                        <tr><td><logic:present name="subjectList"><bean:write name="subjectList" property="subCode"></bean:write></logic:present></td>
                        <td><logic:present name="subjectList"><bean:write name="subjectList" property="subjectName"></bean:write></logic:present></td>
                        <td><logic:present name="subjectList"><bean:write name="subjectList" property="tot_marks"></bean:write></logic:present></td>
                        <td><logic:present name="subjectList"><bean:write name="subjectList" property="scoredmarks"></bean:write></logic:present></td>
                        <td><logic:present name="subjectList"><bean:write name="subjectList" property="gradename"></bean:write></logic:present></td></tr>
						</logic:iterate>
                        </tbody>
 
                                <tfoot>
                               <tr>
                               <td>TOTAL</td>
                               <td></td>
                               <td><logic:present name="total" scope="request"><bean:write name="total"/></logic:present></td>
                               <td><logic:present name="scored" scope="request"><bean:write name="scored"/></logic:present></td>
                               <td></td>
                               </tr>
                              </tfoot>		 				
						</table>
						</div>
						
						</div>
					</div>
					
					<div id="row">
						<div class="panel-body" id="tabletxt"  style="padding: 7px;">
						<div class="col-md-12">
							<table style="width: 100%";>
								<tr>
								<logic:present name="Dependency">
									<logic:iterate id="Dependency" name="Dependency">
										<td>
											<table class="allstudent">
												<tr>
													<th colspan="2"><bean:write name="Dependency"
															property="mainexam" /></th>
												</tr>
												<tr>
													<th>Mark</th>
													<th>Grade</th>
												</tr>
												<tr>
													<td><bean:write name="Dependency"
															property="mainexammark" /></td>
													<td><bean:write name="Dependency"
															property="mainexamgrade" /></td>
												</tr>
											</table>
										</td>
										<logic:present property="examlist" name="Dependency">
											<logic:iterate name="Dependency" id="depedencylist"
												property="examlist">
												<td>
													<table class="allstudent">
														<tr>
															<th colspan="2"><bean:write name="depedencylist"
																	property="examName" /></th>
														</tr>
														<tr>
															<th>Mark</th>
															<th>Grade</th>
														</tr>
														<tr>
															<td><bean:write name="depedencylist" property="depExamScoredMarks" /></td>
															<td><bean:write name="depedencylist" property="depExamGrade" /></td>
														</tr>
													</table>
												</td>
											</logic:iterate>
										</logic:present>
									</logic:iterate>
								</logic:present>
								<td>
									<table class="allstudent">
										<tr>
											<th colspan="2">Total</th>
										</tr>
										<tr>
											<th>Mark</th>
											<th>Grade</th>
										</tr>
										<tr>
											<td><logic:present name="Grandtotal" scope="request"><bean:write name="Grandtotal"/></logic:present></td>
											<td><logic:present name="GrandGrade" scope="request"><bean:write name="GrandGrade"/></logic:present></td>
										</tr>
									</table>
								</td>
							</tr>
							</table>
							</div>
	                    </div>
					</div>
				</div>
			</div>
		
	
</body>
</html>