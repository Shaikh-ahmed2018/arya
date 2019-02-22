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
<script src="JQUERY/kendoJs/kendo.all.min.js"></script>
<script src="JS/backOffice/Reports/ExamClassWiseReport.js"></script>

<style>
.modal-body {
	text-align: center;
}


</style>
</head>



<body>


	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<div class="searchWrap">
		<p>
			<img src="images/addstu.png" />&nbsp;<span style="font-size: 16pt;">Class Wise Exam Details </span>
		</p>
	</div>
		<center>


			<div class="errormessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-warning bg-msg-warning"><span
						class="validateTips"></span></a>
				</div>
			</div>



			<div class="successmessagediv" style="display: none;">
				<div class="message-item">
					<!-- Warning -->
					<a href="#" class="msg-success bg-msg-succes"><span
						class="successmessage"></span></a>
				</div>
			</div>
		</center>

		<form action="reportaction.html?method=examReportClassWiseDetails"
			method="post">
			<p id="parent1" style="display: none;">
				<a href="">Expand all</a>
			</p>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary clearfix"  >

					<div class="panel-heading" role="tab" id="headingOne">

						
							<a data-toggle="collapse" data-parent="#accordion2"
								href="#collapseOne" style="color: #767676;vertical-align: text-top"><h4 class="panel-title" id="beforeparent"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Exam Details
							</h4></a>
						

						<div class="navbar-right">
						
							
							<span class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" style="top:-8px;">Download</span>

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

					<!-- Filters -->

					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" id="tabletxt" style="padding: 15px;margin-top: 19px;">

							<div class="col-md-6" id="txtstyle">
							
							

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right;">Academic Year</label>
									<div class="col-xs-7">
										<select id="accyear" name="accyear" class="form-control"
											required>
											<option value=""></option>

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
							

															
							</div>
							<div class="col-md-6" id="txtstyle">

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right;">Class</label>
									<div class="col-xs-7">
										<select id="class" name="classname" class="form-control"
											required>
											<option value=""></option>
											<logic:present name="classList">

												<logic:iterate id="classrec" name="classList">

													<option
														value="<bean:write name="classrec" property="classId"/>">
														<bean:write name="classrec" property="classname" />
													</option>

												</logic:iterate>

											</logic:present>

										</select>
									</div>
								</div>
								<div class="col-xs-4"></div>
							<div class="col-xs-8">
							<button type="submit" class="btn btn-info"
								id="search" onclick="return validate()">Search</button>
								</div>
								<br />
							
									<br></br>
							</div>
							
							<!-- data-toggle="modal" data-target="#myModal" -->
							<br />

								
						<div class="col-md-12 selecteditems">
								<br /> 
								
								<input type="hidden" id="haccyear" name="haccyear" value="" />
									
								<input type="hidden" id="hclass" name="hclass" value="" />
									
								
								<logic:present name="reportForm">

								<span><b>Academic Year :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present name="reportForm">
										<bean:write name="reportForm" property="haccyear" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Class :</b></span>&nbsp;&nbsp;&nbsp;<span id="classnameid"><logic:present
										name="reportForm">
										<bean:write name="reportForm" property="hclass"/></logic:present>
										
									</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
									
									<input type="hidden" id="haccyearid"  value="<logic:present name="reportForm"><bean:write name="reportForm" property="accyear"/></logic:present>"/>
									<input type="hidden" id="hclassid" value="<logic:present name="reportForm"><bean:write name="reportForm" property="classname"/></logic:present>"/>
									
								</logic:present>
							</div>
							<br />
		
					<logic:present name="examDetailsReport">

								<input type="hidden" id="hideenId" value="studentlist" />

								<display:table class="table" id="allstudent"
									name="requestScope.examDetailsReport" 
									requestURI="/adminMenu.html?method=studentList?">

									<display:column property="sno1" sortable="true"
										title="S.No	<img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>


									<display:column property="examName" sortable="true"
										title="Exam Name	<img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>


									<display:column property="subjectName" sortable="true"
										title="Subject Name <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>



									<display:column property="maxmarks" sortable="true"
										title="Maximum Marks <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>



									<display:column property="requiredmarks" sortable="true"
										title="Required Marks <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>


									<display:column property="examinationdate" sortable="true"
										title="Exam Date <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>

									<display:column property="startTime" sortable="true"
										title="Start Time <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>

									<display:column property="endTime" sortable="true"
										title="End Time <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>

								</display:table>

							</logic:present>
		
		

						</div>
					</div>
				</div>
			</div>
			<!-- Button trigger modal -->

		</form>
	</div>

	<span>&nbsp;</span>


	<!-- jQuery -->
	<script src="js/jquery.js"></script>


	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>


	<script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>

</body>

</html>
