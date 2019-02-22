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
<script src="JS/backOffice/Reports/StudentFeeStatusReport.js"></script>
<!-- <link href="CSS/newUI/examtimetable.css" rel="stylesheet"> -->

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
			<img src="images/addstu.png" />&nbsp;<span style="font-size: 16pt;">Student Fee Payment  </span>
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

		<form action="reportaction.html?method=getStdFeeStatusReportDetails"
			method="post">
			<p id="parent1" style="display: none;">
				<a href="">Expand all</a>
			</p>
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<div class="panel panel-primary" style="margin-top: -20px;">

					<div class="panel-heading" role="tab" id="headingOne">

						
							<a data-toggle="collapse" data-parent="#accordion2"
								href="#collapseOne" style="color: #767676;vertical-align: text-top"><h4 class="panel-title" id="beforeparent"><i
								class="glyphicon glyphicon-menu-hamburger"></i>
								&nbsp;&nbsp;Current Student Fee Payment 
							</h4></a>
						

						<div class="navbar-right">
						
							<img src="images/rightline.png" style="margin-top: -2px;margin-right: 11px;">
							<img src="images/download.png" class="download" id="iconsimg" style="margin-top:4px"
								data-toggle="modal" data-target="#myModal" data-toggle="tooltip"
								data-placement="bottom" title="Download">

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
								

								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right;">Section</label>
									<div class="col-xs-7">
										<select id="section" name="section" class="form-control"
											required>
											<option value=""></option>
											
										</select>
									</div>
								</div>
							
								
								 <div class="form-group clearfix">
								<label for="inputPassword" class="control-label col-xs-4" style="text-align: right;">Student Name
								</label>		
									<div class="col-xs-7">
										<select id="studentname" name="studentId" class="form-control"
											required>
											<option value=""></option>

										
										</select>
									</div>
								</div>
								
								<!-- <div class="form-group">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames">Status</label>
									<div class="col-xs-7">
										<select id="status" name="status" class="form-control"
											required>
											<option value=""></option>
											<option value="All">All</option>
											<option value="Paid">Paid</option>
											<option value="NotPaid">NotPaid</option>
											
										</select>
									</div>
								</div> -->
							
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
								 
								 
								<div class="form-group clearfix">
									<label for="inputPassword" class="control-label col-xs-4"
										id="inputnames" style="text-align: right;">Term</label>
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
								<div class="col-xs-4"></div>
							<div class="col-xs-8">
							<button type="submit" class="btn btn-info "id="search" onclick="return validate()">Generate Report</button>
							</div>
								<br></br>
									<br></br>
							</div>
							
							<!-- data-toggle="modal" data-target="#myModal" -->
							<br />

								
						<div class="col-md-12 selecteditems">
								<br /> 
								
								<input type="hidden" id="haccyear" name="haccyear" value="" />
									
								<input type="hidden" id="hclass" name="hclass" value="" />
									
								<input type="hidden" id="hsection" name="hsection" value="" />
									
								<input type="hidden" id="hterm" name="hterm" value="" />
								
								
								<logic:present name="reportForm">
					<input type="hidden" id="hhaccyear" name="haccyear" value="<logic:present name="reportForm">
										<bean:write name="reportForm" property="haccyear" />
										
									</logic:present>" />
									
								<input type="hidden" id="hhclass" name="hclass" value="<logic:present
										name="reportForm">
										<bean:write name="reportForm" property="hclass"/>
										
									</logic:present>" />
									
								<input type="hidden" id="hhsection" name="hsection" value="<logic:present
										name="reportForm">
										<bean:write name="reportForm" property="hsection"/>
										
									</logic:present>" />
									
								<input type="hidden" id="hhterm" name="hterm" value="<logic:present
										name="reportForm">
										<bean:write name="reportForm" property="hterm"/>
									
									</logic:present>" />
									
								<span><b>Academic Year :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present name="reportForm">
										<bean:write name="reportForm" property="haccyear" />
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Class :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="reportForm">
										<bean:write name="reportForm" property="hclass"/>
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><b>Section :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="reportForm">
										<bean:write name="reportForm" property="hsection"/>
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span ><b>Term :</b></span>&nbsp;&nbsp;&nbsp;<span id="termname"><logic:present
										name="reportForm">
										<bean:write name="reportForm" property="hterm"/>
									
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span ><b>Status :</b></span>&nbsp;&nbsp;&nbsp;<span><logic:present
										name="reportForm">
										<bean:write name="reportForm" property="status"/>
										
									</logic:present></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
									
									<input type="hidden" id="haccyearid"  value="<logic:present name="reportForm"><bean:write name="reportForm" property="accyear"/></logic:present>"/>
									<input type="hidden" id="hclassid" value="<logic:present name="reportForm"><bean:write name="reportForm" property="classname"/></logic:present>"/>
									<input type="hidden" id="hsectionid" value="<logic:present name="reportForm"><bean:write name="reportForm" property="section"/></logic:present>"/>
									<input type="hidden" id="htermid" value="<logic:present name="reportForm"><bean:write name="reportForm" property="term"/></logic:present>"/>
									<input type="hidden" id="hstatus" value="<logic:present name="reportForm"><bean:write name="reportForm" property="status"/></logic:present>"/>
									<input type="hidden" id="hstudent" value="<logic:present name="reportForm"><bean:write name="reportForm" property="studentId"/></logic:present>"/>
 								</logic:present>
							</div>
							<br />
							
							
		<div id="diaplytable">
				<div class="panel-body"
					style="font-family: Open Sans Light; font-size: 11pt; color: #5d5d5d;">

					<logic:present name="feeStatusReport" scope="request">
					
					<input type="hidden" id="hideenId" value="studentlist" />
					
						<logic:iterate id="calMap" name="feeStatusReport" scope="request">
							<%-- <h3 class="accordHead" id="newstyleforaccordin">
								<span class="glyphicon glyphicon-eject"
									style="transform: rotate(180deg); color: #A1A1A1;margin-top: 5px;"></span>
								<bean:write name="calMap" property="key" />
							</h3> --%>
							<div class="accBody">
								<table class="table" id="allstudent" style="text-align: center;">
									<tr class="headth">
										<th>Sno</th>
										<th>Admission No</th>
										<th>Student Name</th>
										<th>Class name</th>
										<th> Amount</th>
										<th>Amount After Concession</th>
										
										<th>Paid Amount</th>
										<th>Balance</th>
										<th>Status</th>
									</tr>
									
									
										<logic:iterate name="calMap"  property="value" id="datalist">
											<tr class="accordHead">
											
												<td><bean:write name="datalist" property="sno" /></td>
												<td><bean:write name="datalist" property="admissionNo" /></td>
												<td id="<bean:write name="datalist" property="studentId" />"><bean:write name="datalist" property="studentName" /></td>
												
												<td><bean:write name="datalist" property="className" /></td>
												<td><bean:write name="datalist" property="actualAmount" /></td>
												<td><bean:write name="datalist" property="totalAmount" /></td>
						
												<td><bean:write name="datalist" property="paidAmount" /></td>
												<td><bean:write name="datalist" property="balanceAmount" /></td>
												<td><bean:write name="datalist" property="status" /></td>
												
											</tr>
										</logic:iterate>
									
								</table>
							</div>
						</logic:iterate>
					</logic:present>
				</div>
				<br />
		</div>
							
					

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
