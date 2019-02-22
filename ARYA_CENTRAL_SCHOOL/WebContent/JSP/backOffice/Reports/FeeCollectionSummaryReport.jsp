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
<script src="JS/backOffice/Reports/FeeCollectionSummary.js"></script>

<style>
.modal-body {
	text-align: center;
}

</style>


</head>



<body>


	<div class="col-md-10 col-md-offset-2"
		style="font-family: Roboto Medium; font-size: 20pt; color: #07aab9; border-bottom: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; margin-top: -6px;">
		<p style="margin: 20px 0px;">
			<img src="images/addstu.png" />&nbsp;<span>Fee Collection Summary</span>
		</p>

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

		<form action="feecollectionsummaryreport.html?method=getFeecollectionSummaryReport"
			method="post">
			<p id="parent1" style="display: none;">
				<a href="">Expand all</a>
			</p>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary">

					<div class="panel-heading" role="tab" id="headingOne">

						
							<a data-toggle="collapse" data-parent="#accordion2"
								href="#collapseOne" style="color: #767676"><h4 class="panel-title" id="beforeparent"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Fee Collection Summary
							</h4></a>
						

						<div class="navbar-right">
						
						<span  class="buttons" id="iconsimg" data-toggle="modal" data-target="#myModal" 
						 data-toggle="tooltip" data-placement="bottom" title="Download">Download </span>

						</div>
						<script>
							$(document).ready(function() {
								$('[data-toggle="tooltip"]').tooltip();
							});
						</script>
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
						<div class="panel-body" id="tabletxt" style="padding: 15px;">

							<div class="col-md-6" id="txtstyle">

								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames">Academic Year</label>
									<div class="col-xs-7">
										<select id="accyear" name="accyear" class="form-control"
											required>
											<option value=""></option>

											<logic:present name="AccYearList">

												<logic:iterate id="AccYear" name="AccYearList">

													<option
														value=" <bean:write name="AccYear" property="accyearId"/>">
														<bean:write name="AccYear" property="accyearname" />
													</option>

												</logic:iterate>

											</logic:present>
										</select>
									</div>
								</div>
								<br />

								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames">Section</label>
									<div class="col-xs-7">
										<select id="section" name="section" class="form-control"
											required>
											<option value=""></option>
											
										</select>
									</div>
								</div>
								<br />
							</div>
							<div class="col-md-6" id="txtstyle">

								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames">Class</label>
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
								<br />
								<div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames">Term</label>
									<div class="col-xs-7">
										<select id="term" name="term" class="form-control"
											required>
											<option value=""></option>
											<logic:present name="TermList">
											
												<logic:iterate id="termid" name="TermList">
													
													<option value="<bean:write name="termid" property="termid"/>">
														<bean:write name="termid" property="termname" />
													</option>
												
												</logic:iterate>
											
											</logic:present>

										</select>
									</div>
								</div>
								<br />
								<br />
							</div>
							<button type="submit" class="btn btn-info col-md-offset-5"
								id="search" onclick="return validate()">Search</button>
							<!-- data-toggle="modal" data-target="#myModal" -->
							<br />

								
						<div class="col-md-12 selecteditems">
								<br /> 
								
								<input type="hidden" id="haccyear" name="haccyear" value="" />
									
								<input type="hidden" id="hclass" name="hclass" value="" />
									
								<input type="hidden" id="hsection" name="hsection" value="" />
									
								<input type="hidden" id="hterm" name="hterm" value="" />
								
								<logic:present name="feeReportForm">

								<span><b>Academic Year :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present name="feeReportForm">
										<bean:write name="feeReportForm" property="haccyear" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Class :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="feeReportForm">
										<bean:write name="feeReportForm" property="hclass"/>
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Section :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="feeReportForm">
										<bean:write name="feeReportForm" property="hsection"/>
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span ><b>Term :</b></span>&nbsp;&nbsp;&nbsp;<span id="termname"><logic:present
										name="feeReportForm">
										<bean:write name="feeReportForm" property="hterm"/>
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
									
									<input type="hidden" id="haccyearid"  value="<logic:present name="feeReportForm"><bean:write name="feeReportForm" property="accyear"/></logic:present>"/>
									<input type="hidden" id="hclassid" value="<logic:present name="feeReportForm"><bean:write name="feeReportForm" property="classname"/></logic:present>"/>
									<input type="hidden" id="hsectionid" value="<logic:present name="feeReportForm"><bean:write name="feeReportForm" property="section"/></logic:present>"/>
									<input type="hidden" id="htermid" value="<logic:present name="feeReportForm"><bean:write name="feeReportForm" property="term"/></logic:present>"/>
									
								</logic:present>
							</div>
							<br />
							
								<logic:present name="feeCollectionReport" scope="request">
								
									<input type="hidden" id="hideenId" value="studentlist" />

									<table  class="table" id="allstudent" style="font-family: Open Sans Light;color: #897676;" >
										
										<tr>
											<th class="headth">Sno</th>
											<th class="headth">Class Name</th>
											<th class="headth">Total Students</th>
											<th class="headth">Actual amount</th>
											<th class="headth">Paid amount</th>
											<th class="headth">Balance amount</th>
											
										</tr>
											<logic:iterate name="feeCollectionReport" id="dateVO">
												
												<tr>
													<td class="sno" ><bean:write name="dateVO" property="sno" /></td>
													<td class="feename"><bean:write name="dateVO" property="className" /></td>
													<td class="feename"><bean:write name="dateVO" property="studTotal" /></td>
													<td class="actualamount"><bean:write name="dateVO" property="actualAmount" /></td>
													<td class="paidamount"><bean:write name="dateVO" property="paidAmount" /></td>
													<td class="paidamount"><bean:write name="dateVO" property="balanceAmount" /></td>
												</tr>
												
												
												
												
											</logic:iterate>
											<tr style="border-top: 1px solid;border-color: #ddd">
											
											<td colspan="3" align="center" style="text-align: center;">Total</td>
											<td id="tot_actual_amt"><bean:write  name="tot_actual_amt" /></td>
											<td id="tot_concession_amt"><bean:write  name="tot_paid_amt" /></td>
											<td id="tot_paid_amt"><bean:write  name="tot_balance_amt" /></td>
											
											</tr>
											
										</table>
				
					</logic:present>

						<%-- 	<logic:present name="feeCollectionReport">

								<input type="hidden" id="hideenId" value="studentlist" />

								<display:table class="table" id="allstudent"
									name="requestScope.feeCollectionReport"
									requestURI="/adminMenu.html?method=studentList?"
									decorator="com.centris.campus.decorator.StudentRegistrationDecorator">

									<display:column property="sno" sortable="true"
										title="S.No	<img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>


									<display:column property="className" sortable="true"
										title="Class Name	<img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>


									<display:column property="studTotal" sortable="true"
										title="Total Students <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>



									<display:column property="actualAmount" sortable="true"
										title="Actual Amount <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>



									<display:column property="paidAmount" sortable="true"
										title="Paid Amount <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>


									<display:column property="balanceAmount" sortable="true"
										title="Balance Amount <img src='images/sort1.png' style='float: right'/>"
										media="html"></display:column>

								</display:table>

							</logic:present> --%>

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
